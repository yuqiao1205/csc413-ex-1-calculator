package com.yp.cs413.calculator.operator.arithmetic;

import com.yp.cs413.calculator.operand.Operand;

public class PowerOperator extends ArithmeticOperator {

    public PowerOperator() {
        super("^");
    }

    @Override
    public int priority() {
        return 4;
    }

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        // Only positive number is supported now
        return new Operand((int) Math.pow(operandOne.getValue(), operandTwo.getValue()));
    }
}
