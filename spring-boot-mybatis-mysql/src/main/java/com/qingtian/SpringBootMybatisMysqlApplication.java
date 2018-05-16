package com.qingtian;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootMybatisMysqlApplication {



	public static void main(String[] args) {
		SpringApplication.run(SpringBootMybatisMysqlApplication.class, args);
	}

	private final UserMapper userMapper;

	public SpringBootMybatisMysqlApplication(UserMapper userMapper){
		this.userMapper = userMapper;
	}

	@Bean
	CommandLineRunner sampleCommandLineRunner() {
		return (args) -> System.out.println(this.userMapper.findByLastName("Bauer"));
	}

}
