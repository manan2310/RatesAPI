/**
 * RatesBean.java
 */
package com.mycompany.ratesapiservice.bean;

import java.util.Date;
import java.util.Map;

public class RatesBean
{
	String base;
	Map<String,Double> rates;
	Date date;
	

	public String getbase()
	{
		return base;
	}

	

	public Date getdate()
	{
		return date;
	}



	public void setbase(String base)
	{
		this.base=base;
	}

	public Map<String,Double> getRates()
	{
		return rates;
	}

	public void setRates(Map<String,Double> rates)
	{
		this.rates=rates;
	}

	@Override
	public String toString()
	{
		return "RatesBean [base="+base+", rates="+rates+", date="+ date+"]";
	}

	
	public void setdate(Date date)
	{
		this.date=date;
	}

	

}
