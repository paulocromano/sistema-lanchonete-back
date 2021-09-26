package br.com.sistemalanchonete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SistemaLanchoneteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaLanchoneteApplication.class, args);
	}
}
