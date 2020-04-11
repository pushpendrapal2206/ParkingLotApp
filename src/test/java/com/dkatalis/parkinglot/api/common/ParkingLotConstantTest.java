package com.dkatalis.parkinglot.api.common;

import org.junit.Assert;
import org.junit.Test;

import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.CAR_ALREADY_PARKED_MESSAGE;
import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.CAR_ALREADY_UNPARKED_MESSAGE;
import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.CAR_UNPARK_MESSAGE;
import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.CREATE_PARKING_LOT;
import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.EXIT;
import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.INVALID_COMMAND;
import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.LEAVE;
import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.NOT_FOUND;
import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.OPERATION_NOT_SUPPORTED;
import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.PARK;
import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.PARKING_LOT_CREATED_MESSAGE;
import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.PARKING_LOT_FULL_MESSAGE;
import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.PARKING_SLOT_ALLOCATED_MESSAGE;
import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.STATUS;
import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.UNABLE_TO_PARK_MESSAGE;
import static com.dkatalis.parkinglot.api.common.ReflectionUtils.isConstructorPrivateAndInstantiationThrowUnsupportedException;

public class ParkingLotConstantTest {

    @Test
    public void constructorShouldBePrivate() throws Exception {
        Assert.assertTrue(isConstructorPrivateAndInstantiationThrowUnsupportedException(ParkingLotConstant.class));
    }

    @Test
    public void createParkingLotMessageShouldBeCorrect() {
        Assert.assertEquals("create_parking_lot", CREATE_PARKING_LOT);
    }

    @Test
    public void parkMessageShouldBeCorrect() {
        Assert.assertEquals("park", PARK);
    }

    @Test
    public void leaveMessageShouldBeCorrect() {
        Assert.assertEquals("leave", LEAVE);
    }

    @Test
    public void statusMessageShouldBeCorrect() {
        Assert.assertEquals("status", STATUS);
    }

    @Test
    public void exitMessageShouldBeCorrect() {
        Assert.assertEquals("exit", EXIT);
    }

    @Test
    public void operationNotSupportedMessageShouldBeCorrect() {
        Assert.assertEquals("Operation not supported", OPERATION_NOT_SUPPORTED);
    }

    @Test
    public void parkingLotFullMessageShouldBeCorrect() {
        Assert.assertEquals("Sorry, parking lot is full", PARKING_LOT_FULL_MESSAGE);
    }

    @Test
    public void parkingSlotAllocatedMessageShouldBeCorrect() {
        Assert.assertEquals("Allocated slot number: %s", PARKING_SLOT_ALLOCATED_MESSAGE);
    }

    @Test
    public void carAlreadyParkedMessageShouldBeCorrect() {
        Assert.assertEquals("Car is already parked", CAR_ALREADY_PARKED_MESSAGE);
    }

    @Test
    public void carUnparkMessageShouldBeCorrect() {
        Assert.assertEquals("Registration number %s with Slot Number %s is free with Charge %s",
                CAR_UNPARK_MESSAGE);
    }

    @Test
    public void carAlreadyUnparkedMessageShouldBeCorrect() {
        Assert.assertEquals("Registration number %s not found", CAR_ALREADY_UNPARKED_MESSAGE);
    }

    @Test
    public void parkingLotCreatedMessageShouldBeCorrect() {
        Assert.assertEquals("Created parking lot with %s slots", PARKING_LOT_CREATED_MESSAGE);
    }

    @Test
    public void unableToParkCarMessageShouldBeCorrect() {
        Assert.assertEquals("Unable to park the car", UNABLE_TO_PARK_MESSAGE);
    }

    @Test
    public void InvalidCommandMessageShouldBeCorrect() {
        Assert.assertEquals("Invalid command", INVALID_COMMAND);
    }

    @Test
    public void NotFoundMessageShouldBeCorrect() {
        Assert.assertEquals("Not found", NOT_FOUND);
    }
}
