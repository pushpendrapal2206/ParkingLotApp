package com.dkatalis.parkinglot.api.strategy;

/**
 * @author Pushpendra Pal
 */
public interface BillingStrategy {
    int calculateCharges(int hours);
}
