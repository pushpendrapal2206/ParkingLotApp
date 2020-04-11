package com.dkatalis.parkinglot.api.cmd;

import com.dkatalis.parkinglot.api.cmd.service.ParkingCmdExecutionService;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.CREATE_PARKING_LOT;
import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.OPERATION_NOT_SUPPORTED;
import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.PARKING_LOT_CREATED_MESSAGE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ParkingCmdExecutionServiceTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void shouldBeAbleToConstructObject() {
        ParkingCmdExecutionService service = new ParkingCmdExecutionService();
        assertNotNull(service);
    }

    @Test
    public void shouldReturnTrueForValidCommand() {
        ParkingCmdExecutionService service = new ParkingCmdExecutionService();
        boolean isValid = service.validateCommand(CREATE_PARKING_LOT, 1);
        assertTrue(isValid);
    }

    @Test
    public void shouldReturnFalseForInValidCommand() {
        ParkingCmdExecutionService service = new ParkingCmdExecutionService();
        boolean isValid = service.validateCommand("ABC", 1);
        assertFalse(isValid);
    }

    @Test
    public void shouldExecuteCommand() {
        ParkingCmdExecutionService service = new ParkingCmdExecutionService();
        service.execute(CREATE_PARKING_LOT, new String[]{"1"});
        assertEquals(String.format(PARKING_LOT_CREATED_MESSAGE, 1) + "\n", outContent.toString());
    }

    @Test
    public void shouldThrowExceptionWhenInvalidCmdIsExecuted() {
        ParkingCmdExecutionService service = new ParkingCmdExecutionService();
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage(OPERATION_NOT_SUPPORTED);
        service.execute("ABC", new String[]{"1"});
    }
}
