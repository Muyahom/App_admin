package com.muyahome.checkin.model;

import java.util.List;

public class Lodging {

    private static Lodging instance;
    private double latitute;
    private double longitute;
    private String host;
    private String address;
    private List<String> lodging_img;
    private List<String> convenience;
    private String introductory;
    private int acceptance;
    private int bedroom;
    private int bed;
    private int pet;
    private int type;

    public void setBedroom(int bedroom) {
        this.bedroom = bedroom;
    }

    public void setBed(int bed) {
        this.bed = bed;
    }

    public void setPet(int pet) {
        this.pet = pet;
    }

    public int getBedroom() {
        return bedroom;
    }

    public int getBed() {
        return bed;
    }

    public int getPet() {
        return pet;
    }

    public Lodging(){}

    public static Lodging getInstance(){
        if(instance==null){
            instance=new Lodging();
        }
        return instance;
    }


    public double getLatitute() {
        return latitute;
    }

    public double getLongitute() {
        return longitute;
    }

    public String getHost() {
        return host;
    }

    public String getAddress() {
        return address;
    }

    public List<String> getLodging_img() {
        return lodging_img;
    }

    public List<String> getConvenience() {
        return convenience;
    }

    public String getIntroductory() {
        return introductory;
    }

    public int getAcceptance() {
        return acceptance;
    }

    public int getType() {
        return type;
    }

    public void setLatitute(double latitute) {
        this.latitute = latitute;
    }

    public void setLongitute(double longitute) {
        this.longitute = longitute;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLodging_img(List<String> lodging_img) {
        this.lodging_img = lodging_img;
    }

    public void setConvenience(List<String> convenience) {
        this.convenience = convenience;
    }

    public void setIntroductory(String introductory) {
        this.introductory = introductory;
    }

    public void setAcceptance(int acceptance) {
        this.acceptance = acceptance;
    }

    public void setType(int type) {
        this.type = type;
    }
}
