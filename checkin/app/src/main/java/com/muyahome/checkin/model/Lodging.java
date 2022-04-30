package com.muyahome.checkin.model;

import android.net.Uri;

import com.google.firebase.firestore.GeoPoint;

import java.util.ArrayList;
import java.util.List;

public class Lodging {

    private static Lodging instance;
    private double latitute;
    private double longitute;
    private String host;
    private String address;
    private List<Uri> toilet_lodging_img;
    private List<String> convenience;
    private List<Uri> bedroom_lodging_img;
    private List<Uri> livingroom_lodging_img;
    private int counttoilet;
    private int countlivingroom;
    private int countbedroom;
    private boolean nfc;
    private GeoPoint geoPoint;
    private String introductory;
    private int acceptance;
    private int bedroom;
    private int bed;
    private int pet;
    private int type;
    private String name;
    private int fare;
    private ArrayList<String> reservation_time_list= new ArrayList<String>(){{
        add("A");
        add("B");
        add("C");
    }};;
    private ArrayList<String> review=new ArrayList<String>(){{
        add("A");
        add("B");
        add("C");
    }};;

    public void setName(String name) {
        this.name = name;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public void setReservation_time_list(ArrayList<String> reservation_time_list) {
        this.reservation_time_list = reservation_time_list;
    }

    public void setReview(ArrayList<String> review) {
        this.review = review;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public int getFare() {
        return fare;
    }

    public List<String> getReservation_time_list() {
        return reservation_time_list;
    }

    public List<String> getReview() {
        return review;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    private String state;
    private String city;

    public void setGeoPoint(double latitute, double longitute) {
        geoPoint = new GeoPoint(latitute, longitute);
    }

    public GeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setNfc(boolean nfc) {
        this.nfc = nfc;
    }

    public boolean isNfc() {
        return nfc;
    }

    public void setCounttoilet(int counttoilet) {
        this.counttoilet = counttoilet;
    }

    public void setCountlivingroom(int countlivingroom) {
        this.countlivingroom = countlivingroom;
    }

    public void setCountbedroom(int countbedroom) {
        this.countbedroom = countbedroom;
    }

    public int getCounttoilet() {
        return counttoilet;
    }

    public int getCountlivingroom() {
        return countlivingroom;
    }

    public int getCountbedroom() {
        return countbedroom;
    }



    public List<Uri> getToilet_lodging_img() {
        return toilet_lodging_img;
    }

    public List<Uri> getBedroom_lodging_img() {
        return bedroom_lodging_img;
    }

    public List<Uri> getLivingroom_lodging_img() {
        return livingroom_lodging_img;
    }



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

    public void setToilet_lodging_img(List<Uri> toilet_lodging_img) {
        this.toilet_lodging_img = toilet_lodging_img;
    }

    public void setBedroom_lodging_img(List<Uri> bedroom_lodging_img) {
        this.bedroom_lodging_img = bedroom_lodging_img;
    }

    public void setLivingroom_lodging_img(List<Uri> livingroom_lodging_img) {
        this.livingroom_lodging_img = livingroom_lodging_img;
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
