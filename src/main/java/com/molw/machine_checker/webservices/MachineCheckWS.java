package com.molw.machine_checker.webservices;


import com.molw.machine_checker.data.DigitalCurrencies;
import com.molw.machine_checker.data.Machine;
import com.molw.machine_checker.sources.MachineStatusFetcher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MachineCheckWS {

	@GetMapping("/")
	public String baseURL(){
		return "Web Server working";
	}

	@GetMapping(value = "/machines", produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Machine> getMachineStatus(){
		  MachineStatusFetcher msFetcher = new MachineStatusFetcher();
		  return msFetcher.getAllStatus();
		  		
	}

	@GetMapping("/health")
	public String amIHealthy(){
		return "yes";
	}

	@GetMapping("/ready")
	public String amIReady(){
		//TODO once we plug in the cache we need to make sure we can actually access the
		// cache object before we are ready
		return "yes";
	}


}
