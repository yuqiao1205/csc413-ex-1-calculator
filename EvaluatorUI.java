import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvaluatorUI extends JFrame implements ActionListener {

    private final TextField txField = new TextField();
    private final Panel buttonPanel = new Panel();

    // total of 20 buttons on the calculator,
    private static final String[] bText = {
            "7", "8", "9", "+", "4", "5", "6", "- ", "1", "2", "3",
            "*", "0", "^", "=", "/", "(", ")", "C", "CE"
    };

    private Button[] buttons = new Button[bText.length];

    public static void main(String[] args) {
        new EvaluatorUI();
    }

    public EvaluatorUI() {
        setLayout(new BorderLayout());

        add(txField, BorderLayout.NORTH);
        txField.setEditable(false);

        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(5, 4));

        // Create 20 buttons, set up buttons to listen for mouse input
        for (int i = 0; i < 20; i++) {
            buttons[i] = new Button(bText[i]);
            buttonPanel.add(buttons[i]);
            buttons[i].addActionListener(this);
        }

        setTitle("Calculator");
        setSize(400, 400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() instanceof Button) {
            String label = ((Button) event.getSource()).getLabel();

            if ("=".equals(label)) {
                evalExpression();
            } else if ("CE".equals(label)) {
                // "CE" button clears last character
                clearLastCharacter();
            } else if ("C".equals(label)) {
                // "C" button clear all text field
                txField.setText("");
            } else {
                // Add text to expression
                txField.setText(txField.getText() + label);
            }
        }

    }

    private void clearLastCharacter() {
        String currentText = txField.getText();
        if (!currentText.isEmpty()) {
            txField.setText(currentText.substring(0, currentText.length() - 1));
        }
    }

     // "=" button pressed, triggers evaluation of arithmetic expression
    private void evalExpression() {
        Evaluator myEvaluator = new Evaluator();
        String expression = txField.getText();

        if (!expression.isEmpty()) {
            txField.setText(String.valueOf(myEvaluator.eval(expression)));
        }
    }

}

