package com.dkatalis.parkinglot.api;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.CAR_ALREADY_PARKED_MESSAGE;
import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.CAR_ALREADY_UNPARKED_MESSAGE;
import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.CAR_UNPARK_MESSAGE;
import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.PARKING_LOT_CREATED_MESSAGE;
import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.PARKING_LOT_CREATION_ERROR_MESSAGE;
import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.PARKING_LOT_FULL_MESSAGE;
import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.PARKING_SLOT_ALLOCATED_MESSAGE;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class ParkingLotTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final ParkingLot parkingLot = ParkingLot.getInstance();

    @Before
    public void setUpStreams() {
        parkingLot.createParkingLot(new String[]{"3"});
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void shouldCreateParkingLot() {
        String[] input = new String[]{"3"};
        parkingLot.createParkingLot(input);
        Assert.assertEquals(String.format(PARKING_LOT_CREATED_MESSAGE + "\n", 3), outContent.toString());
    }

    @Test
    public void shouldPrintErrorMsgCreateParkingLot() {
        String[] input = new String[]{"0"};
        parkingLot.createParkingLot(input);
        Assert.assertEquals(String.format(PARKING_LOT_CREATION_ERROR_MESSAGE + "\n", 0), outContent.toString());
    }

    @Test
    public void shouldBeAbleToParkCar() {
        String[] input = new String[]{"regno01"};
        parkingLot.park(input);
        assertThat(outContent.toString(),
                containsString(String.format(PARKING_SLOT_ALLOCATED_MESSAGE + "\n", 1)));
    }

    @Test
    public void shouldPrintErrorMsgForAlreadyParkedCar() {
        String[] input = new String[]{"regno01"};
        parkingLot.park(input);
        parkingLot.park(input);
        assertThat(outContent.toString(),
                containsString(CAR_ALREADY_PARKED_MESSAGE + "\n"));
    }

    @Test
    public void shouldBeAbleToUnparkCar() {
        String[] input = new String[]{"regno01", "3"};
        parkingLot.park(input);
        parkingLot.leave(input);
        assertThat(outContent.toString(),
                containsString(String.format(CAR_UNPARK_MESSAGE + "\n", "regno01", 1, 20)));
    }

    @Test
    public void shouldPrintErrorMsgForInvalidCar() {
        String[] input = new String[]{"regno03", "3"};
        parkingLot.leave(input);
        Assert.assertEquals(String.format(CAR_ALREADY_UNPARKED_MESSAGE + "\n", "regno03"), outContent.toString());
    }

    @Test
    public void shouldPrintErrorMsgForFullParking() {
        String[] input = new String[]{"regno01"};
        parkingLot.park(input);
        parkingLot.park(new String[]{"regno02"});
        parkingLot.park(new String[]{"regno03"});
        parkingLot.park(new String[]{"regno04"});

        assertThat(outContent.toString(), containsString(PARKING_LOT_FULL_MESSAGE + "\n"));
    }

    @Test
    public void shouldBeAbleToPrintStatus() {
        String[] input = new String[0];
        parkingLot.park(new String[]{"regno01"});
        parkingLot.getParkingStatus(input);
        assertThat(outContent.toString(),
                containsString("Slot No." + "\t" + "Registration No." + "\n" +
                        "1" + "\t\t" + "regno01" + "\t\t" + "\n"));
    }
}
