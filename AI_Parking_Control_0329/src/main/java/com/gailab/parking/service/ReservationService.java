package com.gailab.parking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gailab.parking.dao.ReservationRepository;
import com.gailab.parking.dao.VehicleRepository;

import java.util.Map;
import java.util.HashMap;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ReservationService {
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private VehicleRepository vehicleRepository;

	@Transactional
	public void registerReservation(Map<String, Object> request) {
		@SuppressWarnings("unchecked")
		Map<String, Object> info = (HashMap<String, Object>) request.get("info");
		System.out.println(info);
		Integer existVehicleNumber = vehicleRepository.existVehicleNumber(info.get("vehicleNumber").toString());

		// 받은 UTC 시간을 LocalDateTime으로 변환
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime inUtcDateTime = LocalDateTime.parse((CharSequence) info.get("inDate"), formatter);
		LocalDateTime outUtcDateTime = LocalDateTime.parse((CharSequence) info.get("outDate"), formatter);

		// UTC 시간을 서버의 시간대로 변환
		ZoneId serverZoneId = ZoneId.of("Asia/Seoul"); // 예시로 Asia/Seoul 시간대 사용
		ZonedDateTime inServerDateTime = inUtcDateTime.atZone(ZoneId.of("UTC")).withZoneSameInstant(serverZoneId);
		ZonedDateTime outServerDateTime = outUtcDateTime.atZone(ZoneId.of("UTC")).withZoneSameInstant(serverZoneId);

		inUtcDateTime = inServerDateTime.toLocalDateTime();
		outUtcDateTime = outServerDateTime.toLocalDateTime();

		info.put("inDate", inUtcDateTime);
		info.put("outDate", outUtcDateTime);
		System.out.println(info);

		if (existVehicleNumber == null) {
			vehicleRepository.insertVehicleInfo(info);
			reservationRepository.insertReservationFirst(info);
		} else {
			reservationRepository.insertReservation(info);
		}
	}
}
