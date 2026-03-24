import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScientificCalculator extends JFrame implements ActionListener {

    JTextField display;
    double num1, num2, result;
    String operator;

    public ScientificCalculator() {

        setTitle("Scientific Calculator");
        setSize(420, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Setup the display screen
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 22));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        // Create button panel with 6 rows and 4 columns
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 4, 5, 5));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "√", "xʸ", "%", "C",
                "sin", "cos", "tan", "log"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 16));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();

        try {

            if (cmd.matches("[0-9.]")) {
                // Append digit or dot to display
                display.setText(display.getText() + cmd);
            }

            else if (cmd.equals("+") || cmd.equals("-") || cmd.equals("*") ||
                     cmd.equals("/") || cmd.equals("%") || cmd.equals("xʸ")) {
                // Store first number and the operator, then clear for second input
                num1 = Double.parseDouble(display.getText());
                operator = cmd;
                display.setText("");
            }

            else if (cmd.equals("=")) {

                num2 = Double.parseDouble(display.getText());

                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/":
                        if (num2 == 0)
                            throw new ArithmeticException("Divide by zero");
                        result = num1 / num2;
                        break;
                    case "%": result = num1 % num2; break;
                    case "xʸ": result = Math.pow(num1, num2); break;
                }
                display.setText(String.valueOf(result));
            }

            // Scientific functions — all take current display value as input
            else if (cmd.equals("√")) {
                num1 = Double.parseDouble(display.getText());
                display.setText(String.valueOf(Math.sqrt(num1)));
            }
            else if (cmd.equals("sin")) {
                num1 = Double.parseDouble(display.getText());
                display.setText(String.valueOf(Math.sin(Math.toRadians(num1))));
            }
            else if (cmd.equals("cos")) {
                num1 = Double.parseDouble(display.getText());
                display.setText(String.valueOf(Math.cos(Math.toRadians(num1))));
            }
            else if (cmd.equals("tan")) {
                num1 = Double.parseDouble(display.getText());
                display.setText(String.valueOf(Math.tan(Math.toRadians(num1))));
            }
            else if (cmd.equals("log")) {
                num1 = Double.parseDouble(display.getText());
                display.setText(String.valueOf(Math.log10(num1)));
            }

            else if (cmd.equals("C")) {
                // Clear the display
                display.setText("");
            }

        } catch (ArithmeticException ex) {
            JOptionPane.showMessageDialog(this, "Math Error!");
            display.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid Input!");
            display.setText("");
        }
    }

    public static void main(String[] args) {
        new ScientificCalculator();
    }
}