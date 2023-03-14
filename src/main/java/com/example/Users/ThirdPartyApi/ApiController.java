package com.example.Users.ThirdPartyApi;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.Users.Responce.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ApiController {

	@Autowired
	private ApiServices apiServices;
	
	@GetMapping("/ntts/task")
	public ResponseEntity<?> getApi()
	{
		try {
		
			String api=this.apiServices.getApi();
			
			return new ResponseEntity<>(api,HttpStatus.OK);
		}
		
		catch (Exception e) {
			
			return new ResponseEntity<>("not found",HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/report/{city}")
	public ResponseEntity<?> getWeatherReport(@PathVariable("city") String city)
	{
		try {
		
			String api=this.apiServices.getWeather(city);
			
			//ObjectMapper mapper= new ObjectMapper();
			
			
			return new ResponseEntity<>(api,HttpStatus.OK);
		}
		
		catch (Exception e) {
			
			return new ResponseEntity<>("not found",HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/nasa")
	public ResponseEntity<?> getNasaApi()
	{
		try {
			
			String api=this.apiServices.getNasaApi();
			
			return new ResponseEntity<>(api,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(new ErrorMessage("Api is not valid", "Api not get"),HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
