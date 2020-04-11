package com.dkatalis.parkinglot.api.common;

import org.junit.Assert;
import org.junit.Test;

public class ColorTest {

    private static final String WHITE_COLOR = "White";
    private static final String RED_COLOR = "Red";
    private static final String BLACK_COLOR = "Black";
    private static final String BLUE_COLOR = "Blue";
    private static final String GREEN_COLOR = "Green";
    private static final String GREY_COLOR = "Grey";
    private static final String INVALID_COLOR = "invalid";


    @Test
    public void fromValue() {
        Assert.assertEquals(Color.White, Color.fromValue(WHITE_COLOR));
        Assert.assertEquals(Color.Red, Color.fromValue(RED_COLOR));
        Assert.assertEquals(Color.Black, Color.fromValue(BLACK_COLOR));
        Assert.assertEquals(Color.Blue, Color.fromValue(BLUE_COLOR));
        Assert.assertEquals(Color.Green, Color.fromValue(GREEN_COLOR));
        Assert.assertEquals(Color.Grey, Color.fromValue(GREY_COLOR));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromValueShouldThrowExceptionForInvalidColor() {
        Assert.assertEquals(Color.White, Color.fromValue(INVALID_COLOR));
    }

    @Test
    public void shouldReturnColorWhenValueIsCalled() {
        Assert.assertEquals(WHITE_COLOR, Color.White.value());
        Assert.assertEquals(RED_COLOR, Color.Red.value());
        Assert.assertEquals(BLACK_COLOR, Color.Black.value());
        Assert.assertEquals(BLUE_COLOR, Color.Blue.value());
        Assert.assertEquals(GREEN_COLOR, Color.Green.value());
        Assert.assertEquals(GREY_COLOR, Color.Grey.value());
    }
}
