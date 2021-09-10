package com.yp.cs413.calculator.operator.common;

import com.yp.cs413.calculator.operand.Operand;


public class LeftBracket extends Operator {

    public LeftBracket() {
        super("(");
    }

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public Operand execute(Operand operandOne, Operand operandTwo) {
        throw new UnsupportedOperationException(token + "doesn't support execution!");
    }

}
