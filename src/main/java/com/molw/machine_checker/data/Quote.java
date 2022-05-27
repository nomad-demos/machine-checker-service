package com.molw.machine_checker.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.cache.annotation.Cacheable;

@Cacheable("quotes")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {
	private String symbol = "";
	private Double price = 0.0;

	public Quote(String symbol, Double price) {
		this.symbol = symbol;
		this.price = price;
	}
	
	public Quote() {
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Quote{" +
				"symbol='" + symbol + '\'' +
				", price=" + price +
				'}';
	}
}
