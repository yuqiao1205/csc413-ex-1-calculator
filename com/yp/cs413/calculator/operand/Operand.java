package com.yp.cs413.calculator.operand;

public class Operand {

    private int value;

    public Operand(String token) {
        value = Integer.parseInt(token);
    }

    public Operand(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static boolean check(String token) {
        if (token == null) {
            return false;
        }
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
