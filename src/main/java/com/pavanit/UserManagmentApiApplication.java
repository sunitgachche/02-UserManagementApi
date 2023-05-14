package com.pavanit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication
public class UserManagmentApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagmentApiApplication.class, args);
	}

}
