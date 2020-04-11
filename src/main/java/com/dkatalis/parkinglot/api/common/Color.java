package com.dkatalis.parkinglot.api.common;

/**
 * @author Pushpendra Pal
 */
public enum Color {

    White,
    Red,
    Black,
    Blue,
    Green,
    Grey;

    Color() {
    }

    public String value() {
        return this.name();
    }

    public static Color fromValue(String v) {
        return valueOf(v);
    }
}
