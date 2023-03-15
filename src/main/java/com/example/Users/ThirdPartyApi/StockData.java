package com.example.Users.ThirdPartyApi;

import java.util.Date;

public class StockData {

	private String symbol;
	
	private String open;
	
	private String high;
	
	private String low;
	
	private String price;
	
	private String volume;
	
	private String latestTradingDay;
	
	private String previousClose;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getLatestTradingDay() {
		return latestTradingDay;
	}

	public void setLatestTradingDay(String latestTradingDay) {
		this.latestTradingDay = latestTradingDay;
	}

	public String getPreviousClose() {
		return previousClose;
	}

	public void setPreviousClose(String previousClose) {
		this.previousClose = previousClose;
	}

	public StockData(String symbol, String open, String high, String low, String price, String volume,
			String latestTradingDay, String previousClose) {
		super();
		this.symbol = symbol;
		this.open = open;
		this.high = high;
		this.low = low;
		this.price = price;
		this.volume = volume;
		this.latestTradingDay = latestTradingDay;
		this.previousClose = previousClose;
	}

	public StockData() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "StockData [symbol=" + symbol + ", open=" + open + ", high=" + high + ", low=" + low + ", price=" + price
				+ ", volume=" + volume + ", latestTradingDay=" + latestTradingDay + ", previousClose=" + previousClose
				+ "]";
	}
	
	
	
	
	
	
}
