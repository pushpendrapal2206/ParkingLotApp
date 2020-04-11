package com.gojek.parkinglot.api.models;

import com.dkatalis.parkinglot.api.common.VehicleType;
import com.dkatalis.parkinglot.api.models.Car;
import com.dkatalis.parkinglot.api.models.Vehicle;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.MockitoAnnotations.initMocks;

public class CarTest {

    @Test
    public void shouldCreateAndReturnCarInstance() {
        Vehicle car = new Car();
        assertNotNull(car);
        assertEquals(VehicleType.CAR, car.getType());
    }

    private static class Fixture {

        @InjectMocks
        private Car car;

        Fixture() {
            initMocks(this);
        }
    }
}
