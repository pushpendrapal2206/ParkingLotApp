package com.dkatalis.parkinglot.api.models;

import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ParkingSlotTest {

    @Test
    public void shouldConstructAndReturnParkingSlot() {
        ParkingSlot parkingSlot = new ParkingSlot(1);
        assertNotNull(parkingSlot);
        assertEquals(1, parkingSlot.getSlotNumber());
        assertTrue(parkingSlot.isFree());
    }

    @Test
    public void shouldReturnTrueWhenEqualsIsCalled() {
        ParkingSlot parkingSlot1 = new ParkingSlot(1);
        ParkingSlot parkingSlot2 = new ParkingSlot(1);
        assertEquals(parkingSlot1, parkingSlot2);
    }

    @Test
    public void shouldReturnFalseWhenEqualsIsCalled() {
        ParkingSlot parkingSlot1 = new ParkingSlot(1);
        ParkingSlot parkingSlot2 = new ParkingSlot(2);
        assertNotEquals(parkingSlot1, parkingSlot2);
    }

    @Test
    public void shouldReturnHashForParkingSlot() {
        ParkingSlot parkingSlot1 = new ParkingSlot(1);
        assertEquals(Objects.hash(1), parkingSlot1.hashCode());
    }
}
