package com.nextep.lambda;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class APIGatewayEventHandler
		implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

	public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request,
			Context context) {

		String zipCode = request.getPathParameters().get("zipcode");
		context.getLogger().log("Zip code from path param = " + zipCode);
		
		WeatherRepository repo = new WeatherRepository();
		Weather weather = repo.getWeatherFor(zipCode);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String responseBody = gson.toJson(weather);
		
		context.getLogger().log("Response body to send back is " + responseBody);
		
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		
		headers.put("Access-Control-Allow-Headers", "Content-Type");
		headers.put("Access-Control-Allow-Origin", "*");
		headers.put("Access-Control-Allow-Methods", "OPTIONS,POST,GET");
		
		APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
		
		response.setBody(responseBody);
		response.setHeaders(headers);
		response.setStatusCode(200);
		
		context.getLogger().log("Entire response body is " + response.toString());

		return response;
	}
}