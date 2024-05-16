package com.gailab.parking.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface ReservationRepository {
	void insertReservationFirst(Map<String, Object> request);
	void insertReservation(Map<String, Object> info);
}
