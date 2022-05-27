package com.molw.machine_checker.data;

import java.time.ZonedDateTime;
import java.util.ArrayList;

public class Machine {

    private ArrayList<Updates_v2> updates = new ArrayList<Updates_v2>();
    private ArrayList<Apps> apps  = new ArrayList<Apps>();

    private int ram_usage = 0;
    private int cpu_usage = 0;
    private int disk_usage = 0;
    private int device_id = -1;
    private String device_name;
    private String device_uuid;
    private final ZonedDateTime checkTime = ZonedDateTime.now();

    public ArrayList<Updates_v2> getUpdates() {
        return updates;
    }

    public void setUpdates(ArrayList<Updates_v2> updates) {
        this.updates = updates;
    }

    public ArrayList<Apps> getApps() {
        return apps;
    }

    public void setApps(ArrayList<Apps> apps) {
        this.apps = apps;
    }

    public int getRam_usage() {
        return ram_usage;
    }

    public void setRam_usage(int ram_usage) {
        this.ram_usage = ram_usage;
    }

    public int getCpu_usage() {
        return cpu_usage;
    }

    public void setCpu_usage(int cpu_usage) {
        this.cpu_usage = cpu_usage;
    }

    public int getDisk_usage() {
        return disk_usage;
    }

    public void setDisk_usage(int disk_usage) {
        this.disk_usage = disk_usage;
    }

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDevice_uuid() {
        return device_uuid;
    }

    public void setDevice_uuid(String device_uuid) {
        this.device_uuid = device_uuid;
    }

    public ZonedDateTime getCheckTime() {
        return checkTime;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "updates=" + updates +
                ", apps=" + apps +
                ", ram_usage=" + ram_usage +
                ", cpu_usage=" + cpu_usage +
                ", disk_usage=" + disk_usage +
                ", device_id=" + device_id +
                ", device_name='" + device_name + '\'' +
                ", device_uuid='" + device_uuid + '\'' +
                ", checkTime=" + checkTime +
                '}';
    }
}
