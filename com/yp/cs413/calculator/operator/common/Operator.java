package com.yp.cs413.calculator.operator.common;

import com.yp.cs413.calculator.operand.Operand;
import com.yp.cs413.calculator.operator.arithmetic.AdditionOperator;
import com.yp.cs413.calculator.operator.arithmetic.DivisionOperator;
import com.yp.cs413.calculator.operator.arithmetic.MultiplicationOperator;
import com.yp.cs413.calculator.operator.arithmetic.PowerOperator;
import com.yp.cs413.calculator.operator.arithmetic.SubtractionOperator;

import java.util.HashMap;
import java.util.Map;

public abstract class Operator {

    protected String token;

    protected Operator(String token) {
        this.token = token;
    }

    protected static final Map<String, Operator> operators = new HashMap<>();

    static {
        operators.put("+", new AdditionOperator());
        operators.put("-", new SubtractionOperator());
        operators.put("*", new MultiplicationOperator());
        operators.put("/", new DivisionOperator());
        operators.put("^", new PowerOperator());

        operators.put("(", new LeftBracket());
        operators.put(")", new RightBracket());
    }

    public static boolean check(String token) {
        return operators.containsKey(token);
    }

    public static Operator lookUp(String token) {
        return operators.get(token);
    }

    public abstract int priority();

    public abstract Operand execute(Operand operandOne, Operand operandTwo);
}
