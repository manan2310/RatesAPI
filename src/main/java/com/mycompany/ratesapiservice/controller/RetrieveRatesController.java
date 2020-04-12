/**
 * RetrieveRatesController.java
 */
package com.mycompany.ratesapiservice.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.ratesapiservice.bean.RatesBean;


/*
 * RetrieveRatesController controller
 * Handles 2 functionalities
 * 1. Latest Rates API
 * 2. Historical Rates API
 */

@RestController
public class RetrieveRatesController
{

	String baseDefault="EUR";
	String symbolDefault="GBP,USD,HKD";
	int monthsDefault=6;

	/*
	 * GetMapping to retrieve latest Rates API data
	 */
	
	@GetMapping("rates-details")
	public ModelAndView retrieveRates(@RequestParam("base") Optional<String> base,@RequestParam("symbols") Optional<String> symbols) throws InterruptedException
	{
		ModelAndView mav=new ModelAndView();
		mav.setViewName("welcome");
		StringBuilder uri=new StringBuilder("https://api.exchangeratesapi.io/api/latest?");


		uri.append("base=").append(base.orElse(baseDefault));
		uri.append("&");
		uri.append("symbols=").append(symbols.orElse(symbolDefault));

		RestTemplate restTemplate=new RestTemplate();

		HttpHeaders headers=new HttpHeaders();
		headers.add("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<RatesBean> entity=new HttpEntity<RatesBean>(headers);

		ResponseEntity<RatesBean> result=restTemplate.exchange(uri.toString(),HttpMethod.GET,entity,RatesBean.class);
		mav.addObject("RatesBeanObject",result.getBody());

		return mav;
	}

	/*
	 * GetMapping to retrieve historical Rates API data
	 */
	@GetMapping(path={"rates-details/getHistoricalData","rates-details/getHistoricalData/{months}"})
	public ModelAndView getHistoricalRates(@PathVariable("months") Optional<Integer> months,@RequestParam("base") Optional<String> base,@RequestParam("symbols") Optional<String> symbols) throws InterruptedException
	{
		List<RatesBean> rateBeanList=new ArrayList<RatesBean>();
		ResponseEntity<RatesBean> result=null;

		HttpHeaders headers=new HttpHeaders();
		headers.add("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<RatesBean> entity=new HttpEntity<RatesBean>(headers);
		LocalDate date=LocalDate.now();
		DateTimeFormatter.ofPattern("yyyy-MM-dd");

		
		for(int i=1;i<=months.orElse(monthsDefault);i++)
		{

			StringBuilder uri=new StringBuilder("https://api.exchangeratesapi.io/api/");

			LocalDate currentDate=date.minusMonths(i);

			uri.append(currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"?");


			uri.append("base=").append(base.orElse(baseDefault));
			uri.append("&");
			uri.append("symbols=").append(symbols.orElse(symbolDefault));

			RestTemplate restTemplate=new RestTemplate();

			result=restTemplate.exchange(uri.toString(),HttpMethod.GET,entity,RatesBean.class);

			rateBeanList.add(result.getBody());

		}

		ModelAndView mav=formatData(rateBeanList);
		mav.addObject("base",base.orElse(baseDefault));
		mav.setViewName("historicalRates");

		return mav;
	}

	// Format historical data in a displayable arrangement  
	private ModelAndView formatData(List<RatesBean> rateBeanList)
	{

		Map<String,List<Double>> rateMap=new HashMap<>();
		List<Date> dateList=new ArrayList<>();

		rateBeanList.forEach(p -> {

			dateList.add(p.getdate());

			p.getRates().forEach((key,val) -> {
				if(rateMap.containsKey(key))
					rateMap.get(key).add(val);
				else
				{
					List<Double> arr=new ArrayList<>();
					arr.add(val);
					rateMap.put(key,arr);
				}
			});
		});
		ModelAndView mav=new ModelAndView();
		mav.addObject("dateList",dateList);
		mav.addObject("rateMap",rateMap);

		return mav;
	}
}
