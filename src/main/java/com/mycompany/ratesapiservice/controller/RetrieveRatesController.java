/**
 * RetrieveRatesController.java
 */
package com.mycompany.ratesapiservice.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RetrieveRatesController
{

	private Logger logger=LoggerFactory.getLogger(this.getClass());

	String baseDefault="EUR";
	String symbolDefault="GBP,USD,HKD";

	@GetMapping(path={"rates-details","rates-details/{base}/{symbols}"})
	public ModelAndView retrieveRates(@PathVariable("base") Optional<String> base,@PathVariable("symbols") Optional<String> symbols) throws InterruptedException
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("welcome");
		StringBuilder uri=new StringBuilder("https://api.exchangeratesapi.io/api/latest?");

		if(base.isPresent())
			uri.append("base=").append(base.get());
		else
			uri.append("base=").append(baseDefault);
		uri.append("&");

		if(symbols.isPresent())
			uri.append("symbols=").append(symbols.get());
		else
			uri.append("symbols=").append(symbolDefault);

		RestTemplate restTemplate=new RestTemplate();
System.out.println("======================================");
System.out.println("======================================");
		HttpHeaders headers=new HttpHeaders();
		headers.add("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity=new HttpEntity<String>(headers);

		ResponseEntity<String> result=restTemplate.exchange(uri.toString(),HttpMethod.GET,entity,String.class);
		mav.addObject("result",result);
		return mav;
	}
}
