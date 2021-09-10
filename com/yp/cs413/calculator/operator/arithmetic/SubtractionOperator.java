package com.yp.cs413.calculator.operator.arithmetic;

import com.yp.cs413.calculator.operand.Operand;

public class SubtractionOperator extends ArithmeticOperator {

    public SubtractionOperator() {
        super("-");
    }

    @Override
    public int priority() {
        return 2;
    }

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        return new Operand(operandOne.getValue() - operandTwo.getValue());
    }
}
