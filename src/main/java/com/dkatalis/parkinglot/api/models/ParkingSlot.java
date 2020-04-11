package com.dkatalis.parkinglot.api.models;

import java.util.Objects;

/**
 * @author Pushpendra Pal
 */
public class ParkingSlot {

    private int slotNumber;
    private boolean isFree;
    private Vehicle vehicle;

    public ParkingSlot(int slotNumber) {
        this.slotNumber = slotNumber;
        this.isFree = true;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public boolean isFree() {
        return isFree;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void assignVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isFree = false;
    }

    public void removeVehicle() {
        this.vehicle = null;
        this.isFree = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSlot that = (ParkingSlot) o;
        return slotNumber == that.slotNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(slotNumber);
    }

}
