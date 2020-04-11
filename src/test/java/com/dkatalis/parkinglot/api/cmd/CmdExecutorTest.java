package com.dkatalis.parkinglot.api.cmd;

import com.dkatalis.parkinglot.api.cmd.service.CmdExecutionService;
import com.dkatalis.parkinglot.api.common.ParkingLotConstant;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CmdExecutorTest {
    private static final String CREATE_PARKING_LOT_COMMAND = ParkingLotConstant.CREATE_PARKING_LOT + " " + 1;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void shouldConstructObjectOfCmdExecutor() {
        Fixture fixture = new Fixture();
        CmdExecutor executor = new CmdExecutor(fixture.service);
        Assert.assertNotNull(executor);
    }

    @Test
    public void shouldProcessCommands() throws Exception {
        Fixture fixture = new Fixture();
        when(fixture.input.readLine()).thenReturn(CREATE_PARKING_LOT_COMMAND).thenReturn(null);
        when(fixture.service.validateCommand(anyString(), anyInt())).thenReturn(true);
        doNothing().when(fixture.service).execute(anyString(), any());
        fixture.executor.startCmdProcessing(fixture.input);
        verify(fixture.service, times(1)).validateCommand(anyString(), anyInt());
        verify(fixture.service, times(1)).execute(anyString(), any());
    }

    @Test
    public void shouldStopProcessingCommandsOnStopCommand() throws Exception {
        Fixture fixture = new Fixture();
        when(fixture.input.readLine()).thenReturn(CREATE_PARKING_LOT_COMMAND).thenReturn("stop");
        when(fixture.service.validateCommand(anyString(), anyInt())).thenReturn(true);
        doNothing().when(fixture.service).execute(anyString(), any());
        fixture.executor.startCmdProcessing(fixture.input);
        verify(fixture.service, times(1)).validateCommand(anyString(), anyInt());
        verify(fixture.service, times(1)).execute(anyString(), any());
    }

    @Test
    public void shouldNotProcessInvalidCommand() throws Exception {
        Fixture fixture = new Fixture();
        when(fixture.input.readLine()).thenReturn("ABC").thenReturn("stop");
        when(fixture.service.validateCommand(anyString(), anyInt())).thenReturn(false);
        doNothing().when(fixture.service).execute(anyString(), any());
        fixture.executor.startCmdProcessing(fixture.input);
        verify(fixture.service, times(1)).validateCommand(anyString(), anyInt());
        verify(fixture.service, times(0)).execute(anyString(), any());
        Assert.assertEquals(ParkingLotConstant.INVALID_COMMAND + "\n", outContent.toString());
    }

    private static class Fixture {
        @InjectMocks
        private CmdExecutor executor;

        @Mock
        private BufferedReader input;

        @Mock
        private CmdExecutionService service;

        Fixture() {
            initMocks(this);
        }
    }
}
