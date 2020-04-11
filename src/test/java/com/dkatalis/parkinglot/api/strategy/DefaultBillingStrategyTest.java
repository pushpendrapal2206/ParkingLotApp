package com.dkatalis.parkinglot.api.strategy;

import com.dkatalis.parkinglot.api.models.ParkingRate;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.MockitoAnnotations.initMocks;

public class DefaultBillingStrategyTest {

    @Test
    public void shouldConstructBillingStrategy() {
        Fixture fixture = new Fixture();
        BillingStrategy billingStrategy = new DefaultBillingStrategy(fixture.rate);
        Assert.assertNotNull(billingStrategy);
    }

    @Test
    public void shouldReturnParkingCharges() {
        Fixture fixture = new Fixture();
        int parkingCharges = fixture.billingStrategy.calculateCharges(2);
        Assert.assertEquals(10, parkingCharges);
    }

    @Test
    public void shouldReturnParkingChargesForMoreThan2Hours() {
        Fixture fixture = new Fixture();
        Mockito.when(fixture.rate.getRatePerHour()).thenReturn(10);
        int parkingCharges = fixture.billingStrategy.calculateCharges(3);
        Assert.assertEquals(20, parkingCharges);
    }

    private static class Fixture {

        @InjectMocks
        private DefaultBillingStrategy billingStrategy;

        @Mock
        private ParkingRate rate;

        Fixture() {
            initMocks(this);
        }
    }
}
