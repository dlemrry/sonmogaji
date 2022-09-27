package com.ssafy.sonmogaji;

import com.ssafy.sonmogaji.model.entity.room.Room;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
public class SonmogajiApplication {
//	public static final List<Room> roomList = new LinkedList<>();
	public static void main(String[] args) {
		SpringApplication.run(SonmogajiApplication.class, args);
	}

//	@Bean
//	public ApplicationRunner applicationRunner(){
//		return new ApplicationRunner() {
//			@Override
//			public void run(ApplicationArguments args) throws Exception {
//				roomList.add(new Room());
//			}
//		};
//	}
}
