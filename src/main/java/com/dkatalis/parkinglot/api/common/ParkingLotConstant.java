package com.dkatalis.parkinglot.api.common;

/**
 * @author Pushpendra Pal
 */
public final class ParkingLotConstant {

    public static final String CREATE_PARKING_LOT = "create_parking_lot";
    public static final String PARK = "park";
    public static final String LEAVE = "leave";
    public static final String STATUS = "status";
    public static final String EXIT = "exit";
    public static final String OPERATION_NOT_SUPPORTED = "Operation not supported";
    public static final String PARKING_LOT_FULL_MESSAGE = "Sorry, parking lot is full";
    public static final String PARKING_SLOT_ALLOCATED_MESSAGE = "Allocated slot number: %s";
    public static final String CAR_ALREADY_PARKED_MESSAGE = "Car is already parked";
    public static final String CAR_UNPARK_MESSAGE = "Registration number %s with Slot Number %s is free with Charge %s";
    public static final String CAR_ALREADY_UNPARKED_MESSAGE = "Registration number %s not found";
    public static final String PARKING_LOT_CREATED_MESSAGE = "Created parking lot with %s slots";
    public static final String PARKING_LOT_CREATION_ERROR_MESSAGE = "Can not create parking lot with %s slots";
    public static final String UNABLE_TO_PARK_MESSAGE = "Unable to park the car";
    public static final String INVALID_COMMAND = "Invalid command";
    public static final String NOT_FOUND = "Not found";


    private ParkingLotConstant() {
        throw new UnsupportedOperationException(OPERATION_NOT_SUPPORTED);
    }
}
