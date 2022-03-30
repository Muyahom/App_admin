package com.muyahom.checkin.model;

public class Reservation {
    private String subscriber;

    public Reservation(){}

    public Reservation(String subscriber){
        this.subscriber = subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    public String getSubscriber() {
        return subscriber;
    }
}
