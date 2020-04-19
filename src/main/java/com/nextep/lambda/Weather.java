package com.nextep.lambda;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Weather {

	private String zipCode;
	private String temp;

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}
	
//	public static void main(String[] args) {
//		Weather weather1 = new Weather();
//		weather1.setTemp("72F");
//		weather1.setZipCode("48083");
//		
//		
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		String json = gson.toJson(weather1);
//		
//		System.out.println(json);
//	}

}
