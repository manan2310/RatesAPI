/**
 * RatesBean.java
 */
package com.mycompany.ratesapiservice.bean;

import java.util.Date;

public class RatesBean
{
	String base;
	String rates;
	Date date;
	

	public RatesBean()
	{
		super();
	}

	public RatesBean(String base,String rates)
	{
		this.base=base;
		this.rates=rates;
	}

	public String getbase()
	{
		return base;
	}

	public String getrates()
	{
		return rates;
	}

	public Date getdate()
	{
		return date;
	}



	public void setbase(String base)
	{
		this.base=base;
	}

	public void setrates(String rates)
	{
		this.rates=rates;
	}

	public void setdate(Date date)
	{
		this.date=date;
	}

	@Override
	public String toString()
	{
		return "RatesBean [base="+base+", rates="+rates+", date="+date+"]";
	}

}
