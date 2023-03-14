package com.example.Users.ThirdPartyApi;

import java.util.Map;

import org.patriques.AlphaVantageConnector;
import org.patriques.TimeSeries;
import org.patriques.output.AlphaVantageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.lettuce.core.dynamic.annotation.CommandNaming;

@Service
public class ApiServices {

	
	//private final AlphaVantageConnector connector=new AlphaVantageConnector(getApi(), 0);
	
//	private final AlphaVantageConnector connector;
//	
//	public ApiServices() {
//		this.connector=new AlphaVantageConnector("J7YSRP091PLJROKA", 0);
//		
//	}

		
	//String url="https://ntts.nimapinfotech.com/apps/myTopic";
	
	public String getApi()
	{
		String url="https://ntts.nimapinfotech.com/apps/myTopic";
		
		RestTemplate restTemplate=new  RestTemplate();
		
		String result=restTemplate.getForObject(url, String.class);
		
		return result;
	}
	
	
	


	public String getWeather(String city)
	{
		
		String apiKey="3f8324557698d19c98df1c866ab2d79d";
		String url="https://api.openweathermap.org/data/2.5/weather?q=+"+city+"+&appid="+apiKey;
		
		RestTemplate restTemplate= new RestTemplate();
		
		return restTemplate.getForObject(url, String.class);
		
		
	}
	
//	public Double getStockPrice(String symbol) throws AlphaVantageException {
//        TimeSeries stockTimeSeries = new TimeSeries(connector);
//        Map<String, String> stockPrices = ((Object) stockTimeSeries).getQuote(symbol);
//        return Double.parseDouble(stockPrices.get("05. price"));
//    }
	
	
	
	
	
	
	
}
