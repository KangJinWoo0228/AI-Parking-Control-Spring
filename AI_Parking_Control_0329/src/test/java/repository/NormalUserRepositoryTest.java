package repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gailab.parking.EgovBootApplication;
import com.gailab.parking.dao.NormalUserRepository;
import com.gailab.parking.vo.NormalUser;

import lombok.extern.log4j.Log4j2;

@SpringBootTest(classes = EgovBootApplication.class)
@Log4j2
public class NormalUserRepositoryTest {
	@Autowired
	private NormalUserRepository normalUserRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void testRegisterNormalUser() {
		for (int i = 0; i < 10; i++) {
			NormalUser user = NormalUser.builder()
					.loginEmail("user" + i + "@gailab.co.kr")
					.loginPassword(passwordEncoder.encode("1111"))
					.build();
			
			normalUserRepository.register(user);
		}
	}
	
	@Test
	public void testRead() {
		String email = "user1@gailab.co.kr";
		NormalUser user = normalUserRepository.getUser(email);
		
		log.info("유저 등록 및 확인 테스트");
		log.info(user);
	}
}
