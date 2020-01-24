package br.com.ifood.climateservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ClimateServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClimateServiceApplication.class, args);
	}

}
