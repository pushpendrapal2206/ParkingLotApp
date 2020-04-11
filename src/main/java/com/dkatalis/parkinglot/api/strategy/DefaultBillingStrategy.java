package com.dkatalis.parkinglot.api.strategy;

import com.dkatalis.parkinglot.api.models.ParkingRate;

/**
 * @author Pushpendra Pal
 */
public class DefaultBillingStrategy implements BillingStrategy {
    private ParkingRate parkingRate;

    public DefaultBillingStrategy(ParkingRate parkingRate) {
        this.parkingRate = parkingRate;
    }

    @Override
    public int calculateCharges(final int numberOfHours) {
        if (numberOfHours <= 2) {
            return 10;
        } else {
            return 10 + (numberOfHours - 2) * parkingRate.getRatePerHour();
        }
    }
}
