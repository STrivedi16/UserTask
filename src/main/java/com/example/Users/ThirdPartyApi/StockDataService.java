package com.example.Users.ThirdPartyApi;

import java.io.IOException;
import java.net.URISyntaxException;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



@Service
public class StockDataService {
	
	

	
	  
	  private final HttpClient httpClient;
	  private final ObjectMapper objectMapper;

	  public StockDataService(HttpClient httpClient, ObjectMapper objectMapper) {
	    this.httpClient = httpClient;
	    this.objectMapper = objectMapper;
	  }

	  public String getStockData(String symbol) throws IOException, URISyntaxException {
	    
		   String BASE_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol="+symbol+"&interval=5min&apikey=J7YSRP091PLJROKA";
		    String API_KEY = "J7YSRP091PLJROKA";

		    
		    
		    
		  RestTemplate restTemplate=new RestTemplate();
		  
		 String result= restTemplate.getForObject(BASE_URL, String .class);
		  
	 
		 return result;
		    
		  
//		  URIBuilder uriBuilder = new URIBuilder(BASE_URL)
//	      .setParameter("function", "GLOBAL_QUOTE")
//	      .setParameter("symbol", symbol)
//	      .setParameter("apikey", API_KEY);
//
//	    HttpGet httpGet = new HttpGet(uriBuilder.build());
//	    HttpResponse response = httpClient.execute(httpGet);
//	    String json = EntityUtils.toString(response.getEntity());
//
//	    JsonNode root = objectMapper.readTree(json);
//	    JsonNode dataNode = root.get("Global Quote");
//
//	    return objectMapper.treeToValue(dataNode, StockData.class);
	  }
	}


	

