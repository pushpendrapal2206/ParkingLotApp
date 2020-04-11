package com.dkatalis.parkinglot.api.cmd;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;

import static com.dkatalis.parkinglot.api.common.ParkingLotConstant.CREATE_PARKING_LOT;
import static org.mockito.MockitoAnnotations.initMocks;

public class CommandTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldBeAbleToConstructObjectOfCommand() {
        Fixture fixture = new Fixture();
        Command cmd = new Command(CREATE_PARKING_LOT, 1, fixture.binder);
        Assert.assertNotNull(cmd);
        Assert.assertEquals(CREATE_PARKING_LOT, cmd.getCommandName());
        Assert.assertEquals(1, cmd.getExpectedArguments());
    }

    @Test
    public void shouldBeAbleToExecuteCommand() {
        Fixture fixture = new Fixture();
        Command cmd = new Command(CREATE_PARKING_LOT, 1, fixture.binder);
        Mockito.doNothing().when(fixture.binder).execute(new String[]{"1"});
        cmd.execute(new String[]{"1"});
        Mockito.verify(fixture.binder, Mockito.times(1)).execute(new String[]{"1"});
    }

    @Test
    public void shouldThrowInvalidArgumentException() {
        Fixture fixture = new Fixture();
        Command cmd = new Command(CREATE_PARKING_LOT, 1, fixture.binder);
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(CREATE_PARKING_LOT + " requires " + 1 + " arguments!");
        cmd.execute(new String[]{});
        Mockito.verify(fixture.binder, Mockito.times(0)).execute(new String[]{"1"});
    }


    private static class Fixture {
        @Mock
        private CmdBinder binder;

        Fixture() {
            initMocks(this);
        }
    }
}
