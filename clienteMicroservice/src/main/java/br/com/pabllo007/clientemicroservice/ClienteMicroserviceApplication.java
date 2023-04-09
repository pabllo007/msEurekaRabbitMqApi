package br.com.pabllo007.clientemicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ClienteMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClienteMicroserviceApplication.class, args);
	}

}
