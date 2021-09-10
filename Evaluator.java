import com.yp.cs413.calculator.operator.common.LeftBracket;
import com.yp.cs413.calculator.operator.common.RightBracket;

import com.yp.cs413.calculator.operator.arithmetic.ArithmeticOperator;
import com.yp.cs413.calculator.operand.Operand;
import com.yp.cs413.calculator.operator.common.Operator;
import com.yp.cs413.calculator.operator.common.StartOperator;

import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {
    private final Stack<Operand> operandStack;
    private final Stack<Operator> operatorStack;

    private static final String DELIMITERS = "+-*^/() ";

    public Evaluator() {
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
    }

    public int eval(String expression) {
        initializeStacks();

        parseExpression(expression);

        processRemainingOperators();

        return operandStack.peek().getValue();
    }

    private void parseExpression(String expression) {
        String token;
        StringTokenizer tokenizer = new StringTokenizer(expression, DELIMITERS, true);

        while (tokenizer.hasMoreTokens()) {
            // Filter out spaces
            token = tokenizer.nextToken();

            if (!token.equals(" ")) {
                processToken(token);
            }
        }
    }

    private void processToken(String token) {
        if (Operand.check(token)) {
            operandStack.push(new Operand(token));
        } else if (Operator.check(token)) {
            processOperator(token);
        } else {
            System.out.println("***** invalid token *****");
            System.exit(1);
        }
    }

    private void processOperator(String token) {
        Operator newOperator = Operator.lookUp(token);

        while (operatorStack.peek().priority() >= newOperator.priority()
                && !(newOperator instanceof LeftBracket)) {
            process();
        }

        operatorStack.push(newOperator);

        if (newOperator instanceof RightBracket) {
            evalUntilMatchingLeftBracket();
        }
    }

    private void processRemainingOperators() {
        while (!operatorStack.isEmpty()) {
            Operator operator = operatorStack.peek();

            if (operator instanceof StartOperator) {
                operatorStack.pop();
            } else if (operator instanceof ArithmeticOperator) {
                process();
            } else {
                throw new UnsupportedOperationException("Unexpected com.yp.cs413.calculator.operator");
            }
        }
    }

    private void initializeStacks() {
        operandStack.clear();
        operatorStack.clear();

        operatorStack.push(new StartOperator());
    }

    private void process() {
        Operator operator = operatorStack.pop();

        Operand secondOperand = operandStack.pop();
        Operand firstOperand = operandStack.pop();

        operandStack.push(operator.execute(firstOperand, secondOperand));
    }

    private void evalUntilMatchingLeftBracket() {
        // 1. Discard the right bracket
        operatorStack.pop();

        // 2. Evaluate stack top until the matching left bracket is met
        Operator operator;
        do {
            process();
            operator = operatorStack.peek();
        } while (!(operator instanceof LeftBracket));

        // 3. Discard the left bracket
        operatorStack.pop();
    }
}
