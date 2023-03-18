package com.example.Users.ThirdPartyApi;

import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.Users.Responce.ErrorMessage;
import com.example.Users.Responce.Success;

@RestController
public class Stockcontroller {

	@Autowired
		private StockDataService dataService;

	@GetMapping("/stock/{symbol}")
	public ResponseEntity<?> getStockData(@PathVariable("symbol") String symbol)
	{
		try {
			String data=this.dataService.getStockData(symbol);
			
			return new ResponseEntity<>(data,HttpStatus.OK)
;		}
		catch (Exception e) {
			
			return new ResponseEntity<>(new ErrorMessage("Error", "Error"),HttpStatus.BAD_REQUEST);
		}
	}



}
