import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class AdvCalc implements ActionListener {
    JFrame frame;
    JTextField textfield;
    JPanel panel;

    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];

    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, clrButton, delButton, negButton;            //decimal,equal,clear,delete,negative

    Font myFont = new Font("Ink Free", Font.BOLD, 30);

    AdvCalc() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);

        textfield = new JTextField();
        textfield.setBounds(50, 25, 300, 50);
        textfield.setFont(myFont);
        textfield.setEditable(false);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Delete");
        clrButton = new JButton("Clear");
        negButton = new JButton("(-)");

        //Now adding the above functions in the functionButton array
        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        for (int i = 0; i < 9; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        delButton.setBounds(150, 430, 100, 50);
        clrButton.setBounds(250, 430, 100, 50);
        negButton.setBounds(50, 430, 100, 50);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(negButton);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));        //panel is 4X4
        panel.setBackground(Color.GRAY);

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(divButton);
        panel.add(numberButtons[0]);
        panel.add(decButton);
        panel.add(equButton);


        frame.add(panel);
        frame.add(textfield);                                      //add textfield to frame
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        AdvCalc calc = new AdvCalc();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource() == decButton) {
            textfield.setText(textfield.getText().concat("."));
        }
        if (e.getSource() == addButton) {
            textfield.setText(textfield.getText().concat("+"));
        }
        if (e.getSource() == subButton) {
            textfield.setText(textfield.getText().concat("-"));
        }
        if (e.getSource() == mulButton) {
            textfield.setText(textfield.getText().concat("*"));
        }
        if (e.getSource() == divButton) {
            textfield.setText(textfield.getText().concat("/"));
        }

        if (e.getSource() == equButton) {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
            try {
                String expression = textfield.getText();
                Object result = engine.eval(expression);
                textfield.setText(result.toString());
            } catch (ScriptException ex) {
                textfield.setText("Error");
            }
        }

        if (e.getSource() == clrButton) {
            textfield.setText("");
        }

        if (e.getSource() == delButton) {
            String text = textfield.getText();
            if (!text.isEmpty()) {
                textfield.setText(text.substring(0, text.length() - 1));
            }
        }

        if (e.getSource() == negButton) {
            String text = textfield.getText();
            if (!text.isEmpty()) {
                if (text.startsWith("-")) {
                    textfield.setText(text.substring(1));
                } else {
                    textfield.setText("-" + text);
                }
            }
        }
    }
}
