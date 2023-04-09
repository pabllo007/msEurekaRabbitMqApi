package br.com.pabllo007.cartoesMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CartoesMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartoesMicroserviceApplication.class, args);
	}

}
