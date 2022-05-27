package com.molw.machine_checker.sources;

/*

Using this API
https://p.nomics.com/cryptocurrency-bitcoin-api

I think we will fetch - bitcoin, etherium and dogecoin

 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.molw.machine_checker.data.Machine;
import io.netty.handler.logging.LogLevel;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.json.JsonParser;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MachineStatusFetcher {

	/**
	 * connect_user_token
	 */
	private final String connect_user_token = System.getenv("connect_user_token");
	private final String url = "https://api.connect.jfrog.io/v1/devices_details";
 	private  final Map<String, String> jsonRequest = (HashMap) Stream.of(new String[][]
					{
							{"project_name", "SwampUp2022"},
							{"group_name", "CustomerModeling"},
							{"user_token", connect_user_token}
					}
			).collect(Collectors.toMap(data -> data[0], data -> data[1]));

	 //We need this because we are getting text/html as the content type for the returned JSON. We need to do manual
	 private ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	private ArrayList<Machine> machines;


	private WebClient webClient = WebClient.builder()
			.baseUrl(url)
			.build();

	public ArrayList<Machine> getAllStatus(){
		String result = "";

		//Technically get requests are not supposed to have a body but the Connect API uses it
		// And the Connect does not set the return type correctly - it actually returns Text/HTML as the return type
		Mono<String> response = webClient
				.method(HttpMethod.GET)
				.accept(MediaType.TEXT_HTML)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
				.bodyValue(jsonRequest)
				.retrieve()
				.bodyToMono(String.class);


		//First remove the message part of the JSON String and then parse it into an array of Machines
		String jsonString =  response.block().toString();
		jsonString = jsonString.substring(jsonString.indexOf('['), jsonString.length()-1);
		try {
			machines = objectMapper.readValue(jsonString, new TypeReference<ArrayList<Machine>>() {});
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		return machines;

	}


   	 
}
