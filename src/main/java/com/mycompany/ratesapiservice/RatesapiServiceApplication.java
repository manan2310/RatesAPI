package com.mycompany.ratesapiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class RatesapiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatesapiServiceApplication.class, args);
	}

}
