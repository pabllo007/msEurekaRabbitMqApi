package br.com.pabllo007.avaliadorcreditomicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AvaliadorCreditoMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvaliadorCreditoMicroserviceApplication.class, args);
	}

}
