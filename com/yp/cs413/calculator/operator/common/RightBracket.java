package com.yp.cs413.calculator.operator.common;

import com.yp.cs413.calculator.operand.Operand;

public class RightBracket extends Operator {

    public RightBracket() {
        super(")");
    }

    @Override
    public int priority() {
        return 5;
    }

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        throw new UnsupportedOperationException(token + "doesn't support execution!");
    }
}