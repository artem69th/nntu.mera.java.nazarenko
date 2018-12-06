import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Calculator extends JFrame {

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

    ArrayList<String> cacheList1 =  new ArrayList<String>();
    ArrayList<String> cacheList2 =  new ArrayList<String>();
    int cache1size;
    private String cache;
    private Double result;
    boolean emplyCache1;
    private String resultInCache;


    protected Double FirstNumber;
    protected Double SecondNumber;
    protected String operation = "+";



    public Calculator() {

        setLayout(new BorderLayout()); //для расположения кнопок
        setLocationRelativeTo(null);
        setResizable(false);
        setSize(200, 250);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
                display.setText(TextOnDisplay.substring(0, 0));
            }
        });
        buttonSum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FirstNumber = Double.valueOf(display.getText());
                display.setText("");
                operation = "+";
            }
        });
        buttonMyltiply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FirstNumber = Double.valueOf(display.getText());
                display.setText("");
                operation = "*";
            }
        });
        buttonSubtract.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FirstNumber = Double.valueOf(display.getText());
                display.setText("");
                operation = "-";
            }
        });
        buttonShare.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FirstNumber = Double.valueOf(display.getText());
                display.setText("");
                operation = "/";
            }
        });


        buttonEqually.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SecondNumber = Double.valueOf(display.getText());

                emplyCache1 = cacheList1.isEmpty();

                if ("+".equals(operation))
                {
                    if(emplyCache1 == true)
                    {
                        result = FirstNumber + SecondNumber;
                        AddCacheList();
                        display.setText((result) + "");
                    }
                    else
                    {
                        //ищем результат в кеше
                        SearchResultInCache();

                        //если нашли
                        if(result.equals(Double.valueOf(resultInCache)))
                            display.setText(resultInCache);

                        //если не нашли
                        else
                        {
                            result = FirstNumber + SecondNumber;
                            AddCacheList();
                            display.setText((result) + "");
                        }
                    }

                    //Вывод кеша на экран для проверки
                    for(String n : cacheList1)
                    {
                        System.out.println("L1 " + n);
                    }

                    for(String n : cacheList2)
                    {
                        System.out.println("L2 " + n);
                    }
                }

                if ("-".equals(operation)){
                    cache1size = cacheList1.size();

                    result = FirstNumber - SecondNumber;
                    AddCacheList();
                    display.setText((result) + "");
                }


                if ("*".equals(operation))
                {


                    result = FirstNumber * SecondNumber;
                   AddCacheList();
                    display.setText((result) + "");
                }

                if ("/".equals(operation)) {
                    if (SecondNumber != 0)
                    {
                        result = FirstNumber / SecondNumber;
                        AddCacheList();
                        display.setText((result) + "");
                    }
                    else
                        display.setText("Ошибка! Нельзя делить на 0!");
                }
            }
        });
    }

    protected void AddCacheList()
    {

        cache1size = cacheList1.size();
        String i0 = String.valueOf(FirstNumber),i1 = String.valueOf(SecondNumber),i2 = operation, i3 = String.valueOf(result);
        cache = i0 + "|" + i1 + "|" + i2 + "|" + i3;

        if(cache1size == 10)
        {
            String i = cacheList1.get(0);
            cacheList2.add(i);
            cacheList1.remove(0);
            cacheList1.add(cache);
        }

        else
            cacheList1.add(cache);

        i0="";i1="";i2="";i3="";cache="";
    }



    protected void SearchResultInCache() {
        result = 0.0;
        String check[];
        String delitel = "|";
        String i0="", i1="", i2="";
        resultInCache="";
        for (String n : cacheList1)
        {
            check = n.split(delitel);
            i0 = check[0];
            i1 = check[1];
            i2 = check[2];
            resultInCache = check[3];

            if (Double.valueOf(i0).equals(FirstNumber) && Double.valueOf(i1).equals(SecondNumber) && i2.equals(operation))
            {
                result = Double.valueOf(resultInCache);
                break;
            }
        }
    }
}