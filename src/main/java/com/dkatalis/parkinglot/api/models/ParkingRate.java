package com.dkatalis.parkinglot.api.models;

/**
 * @author Pushpendra Pal
 */
public class ParkingRate {
    private int ratePerHour;

    public ParkingRate(int rate) {
        this.ratePerHour = rate;
    }

    public int getRatePerHour() {
        return ratePerHour;
    }
}
