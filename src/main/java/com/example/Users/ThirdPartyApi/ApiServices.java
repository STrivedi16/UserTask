package com.example.Users.ThirdPartyApi;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.apache.http.HttpConnection;
import org.patriques.AlphaVantageConnector;
import org.patriques.TimeSeries;
import org.patriques.output.AlphaVantageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
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

	@Autowired
	private RestTemplate restTemplate;	
	//String url="https://ntts.nimapinfotech.com/apps/myTopic";
	
	public String getApi(String email, String password)
	{
		String url="https://ntts.nimapinfotech.com/auth/login?Email ID="+email+"&Password="+password;
 		
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
	
	
	public String getNasaApi()
	{
		String apiKey="YPq7PScD6LbDRfdE2nsohaCHuoNSApNl1AnTU99j";
		
		String url="https://api.nasa.gov/planetary/earth/assets?lon=-95.33&lat=29.78&date=2018-01-01&&dim=0.10&api_key="+apiKey;
	
		String result=this.restTemplate.getForObject(url, String.class);
		
		return result;
	}
	
//	public Double getStockPrice(String symbol) throws AlphaVantageException {
//        TimeSeries stockTimeSeries = new TimeSeries(connector);
//        Map<String, String> stockPrices = ((Object) stockTimeSeries).getQuote(symbol);
//        return Double.parseDouble(stockPrices.get("05. price"));
//    }
	
	
	
	public String getQuotes(String category) throws IOException
	{
//		URL url=new URL("https://api.api-ninjas.com/v1/quotes?category="+category);
//		HttpURLConnection connection=(HttpURLConnection) url.openConnection();
//		connection.setRequestProperty("accept", "application/json");
//		InputStream inputStream=connection.getInputStream();
//		ObjectMapper mapper=new ObjectMapper();
//		JsonNode root=mapper.readTree(inputStream);
//		
//		return root.path("fact").asText();
		
		String url="https://api.api-ninjas.com/v1/quotes?category="+category;
		
		RestTemplate restTemplate=new RestTemplate();
		
		String result= restTemplate.getForObject(url, String.class);
		
		System.out.println(result);
		
		return result;
		
	}
	
	
	
}
