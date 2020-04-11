package com.dkatalis.parkinglot.api.models;

import com.dkatalis.parkinglot.api.common.VehicleType;
import com.dkatalis.parkinglot.api.common.Color;

/**
 * @author Pushpendra Pal
 */
public abstract class Vehicle {
    private String registrationNumber;
    private final VehicleType type;
    private Color color;
    private Ticket ticket;

    public Vehicle(VehicleType type) {
        this.type = type;
    }

    public void assignTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void assignColor(Color color) {
        this.color = color;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public VehicleType getType() {
        return type;
    }
}
