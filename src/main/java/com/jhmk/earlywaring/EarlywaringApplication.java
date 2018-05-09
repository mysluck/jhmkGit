package com.jhmk.earlywaring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class EarlywaringApplication {

	public static void main(String[] args) {
		SpringApplication.run(EarlywaringApplication.class, args);
	}
}
