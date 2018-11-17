import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {

    public static void main(String[] args) {
        CalculatorInterface calcInt = new CalculatorInterface();
        calcInt.PanelEditor();
        calcInt.ActionButton();
    }
}

class CalculatorInterface extends JFrame {

    //создаем панель с дисплеем и кнопки
    private JTextArea display = new JTextArea(2, 1);
    private JPanel panel = new JPanel(new GridLayout(4, 4));
    private JButton button0 = new JButton("0");
    private JButton button1 = new JButton("1");
    private JButton button2 = new JButton("2");
    private JButton button3 = new JButton("3");
    private JButton button4 = new JButton("4");
    private JButton button5 = new JButton("5");
    private JButton button6 = new JButton("6");
    private JButton button7 = new JButton("7");
    private JButton button8 = new JButton("8");
    private JButton button9 = new JButton("9");
    private JButton buttonMyltiply = new JButton("*");
    private JButton buttonShare = new JButton("/");
    private JButton buttonSum = new JButton("+");
    private JButton buttonSubtract = new JButton("-");
    private JButton buttonAC = new JButton("C");
    private JButton buttonEqually = new JButton("=");

    //редактируем панель
    public void PanelEditor() {

        setLayout(new BorderLayout()); //для расположения кнопок
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(200, 250);
        setVisible(true);

        add(display, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(buttonAC);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(buttonMyltiply);
        panel.add(button7);
        panel.add(button8);
        panel.add(button9);
        panel.add(buttonShare);
        panel.add(button0);
        panel.add(buttonSum);
        panel.add(buttonSubtract);
        panel.add(buttonEqually);
    }

    private int FirstNumber = 0;
    private String operation;

    //описываем что делает каждая кнопка
    public void ActionButton() {

        button0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                display.setText(display.getText() + "0");
            }
        });

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                display.setText(display.getText() + "1");
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                display.setText(display.getText() + "2");
            }
        });

        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                display.setText(display.getText() + "3");
            }
        });

        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                display.setText(display.getText() + "4");
            }
        });

        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                display.setText(display.getText() + "5");
            }
        });

        button6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                display.setText(display.getText() + "6");
            }
        });

        button7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                display.setText(display.getText() + "7");
            }
        });

        button8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                display.setText(display.getText() + "8");
            }
        });

        button9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                display.setText(display.getText() + "9");
            }
        });

        buttonAC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String TextOnDisplay = display.getText();
                display.setText(TextOnDisplay.substring(0, TextOnDisplay.length() - 1));
            }
        });

        buttonSum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FirstNumber = Integer.valueOf(display.getText());
                display.setText("");
                operation = "+";
            }
        });
        buttonMyltiply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FirstNumber = Integer.valueOf(display.getText());
                display.setText("");
                operation = "*";
            }
        });
        buttonSubtract.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FirstNumber = Integer.valueOf(display.getText());
                display.setText("");
                operation = "-";
            }
        });
        buttonShare.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FirstNumber = Integer.valueOf(display.getText());
                display.setText("");
                operation = "/";
            }
        });

        buttonEqually.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int SecondNumber = Integer.valueOf(display.getText());

                if ("+".equals(operation))
                    display.setText((FirstNumber + SecondNumber) + "");

                if ("-".equals(operation))
                    display.setText((FirstNumber - SecondNumber) + "");

                if ("*".equals(operation))
                    display.setText((FirstNumber * SecondNumber) + "");

                if ("/".equals(operation))
                    display.setText((FirstNumber / SecondNumber) + "");
            }
        });
    }
}



