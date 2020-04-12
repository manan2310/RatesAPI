package com.mycompany.ratesapiservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mycompany.ratesapiservice.controller.RetrieveRatesController;

@SpringBootTest
class SmokeTest
{

	@Autowired
	private RetrieveRatesController controller;

	@Test
	public void contexLoads() throws Exception
	{
		assertThat(controller).isNotNull();
	}

}
