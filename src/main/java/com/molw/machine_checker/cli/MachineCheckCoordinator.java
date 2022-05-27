package com.molw.machine_checker.cli;

import com.molw.machine_checker.data.Machine;
import com.molw.machine_checker.sources.MachineStatusFetcher;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Component
@Profile(value= "!dev" )
public class MachineCheckCoordinator {


    @Scheduled(fixedRate = 15, timeUnit = TimeUnit.SECONDS)
    public void run() throws Exception {
        MachineStatusFetcher msFetcher = new MachineStatusFetcher();
        ArrayList <Machine> machines = msFetcher.getAllStatus();


        machines.forEach( machine ->{
            //pull out the % CPU and memory in use for each machine and send to the cache
            System.out.println("About to try with machine: " + machine.getDevice_id() + " : " + machine.getCpu_usage() + " : " + machine.getRam_usage());
            String cpuKey = machine.getDevice_id() + "-cpu";
            Integer cpuValue = machine.getCpu_usage();
            //cacheCommunicator.storeInCache(cpuKey, cpuValue);
            String ramKey = machine.getDevice_id() + "-ram";
            Integer ramValue = machine.getRam_usage();
            //cacheCommunicator.storeInCache(ramKey, ramValue);
            System.out.println("Done with Machine" + machine.getDevice_id());
        }

    );


    }
}
