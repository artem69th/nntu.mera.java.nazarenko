import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.net.Socket;
import java.util.ArrayList;

class Calculator extends JFrame implements Serializable {

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
    private ArrayList<String> cacheList1 = new ArrayList<String>();
    private ArrayList<String> cacheList2 = new ArrayList<String>();
    private int cacheSize;
    private String cache;
    private Double result;
    private boolean emplyCache1, emplyCache2;
    protected Double FirstNumber, SecondNumber;
    protected String operation;
    Socket socketToServer = null;
    private static Gate g = null;

    public Calculator(){

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
                emplyCache2 = cacheList2.isEmpty();
                EnterOperation(operation);
            }
        });
    }

    private void EnterOperation(String operation)
    {
        //если первый кеш пуст, то добавляем в него
        if (emplyCache1 == true)
        {
           TextMessage tm = new TextMessage();
           try {
               socketToServer = new Socket("127.0.0.1", 11111);
               tm.setUserName(g.loginText);
               tm.setFirstValue(FirstNumber);
               tm.setSecondValue(SecondNumber);
               tm.setOperation(operation);
               ObjectOutputStream out = new ObjectOutputStream(socketToServer.getOutputStream());
               out.writeObject(tm);
           }catch (Exception e){
               System.out.println("Error0");
           }
            try {
                Thread.sleep(500);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }

            try{
               ObjectInputStream in = new ObjectInputStream(socketToServer.getInputStream());
               tm = (TextMessage) in.readObject();
               result = tm.getResult();
           } catch (Exception e) { System.out.println("Error1!"); }

            AddCacheList();
            display.setText((result) + "");
        }

        //если в первом кеше есть элементы
        else {
            //ищем результат в кеше
            SearchResultInCache1(cacheList1);
            //если нашли
            if (SearchResultInCache1(cacheList1) == true)
                display.setText((result)+ "");

            //если не нашли
            else
            {
                //то проверяем, есть ли элементы во втором кеше
                //если нет, то добавляем в кеш
                if(emplyCache2 == true) {
                    TextMessage tm = new TextMessage();
                    try {
                        socketToServer = new Socket("127.0.0.1", 11111);
                        tm.setUserName(g.loginText);
                        tm.setFirstValue(FirstNumber);
                        tm.setSecondValue(SecondNumber);
                        tm.setOperation(operation);
                        ObjectOutputStream out = new ObjectOutputStream(socketToServer.getOutputStream());
                        out.writeObject(tm);
                    }catch (Exception e){
                        System.out.println("Error2");
                    }
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    try{
                        ObjectInputStream in = new ObjectInputStream(socketToServer.getInputStream());
                        tm = (TextMessage) in.readObject();
                        result = tm.getResult();
                    } catch (Exception e) { System.out.println("Error3!"); }

                    AddCacheList();
                    display.setText((result) + "");
                }
                //если есть, ищем во вотором кеше
                else
                {
                    SearchResultInCache1(cacheList2);
                    //если нашли
                    if(SearchResultInCache1(cacheList2) == true)
                        display.setText((result)+ "");

                    //если не нашли
                    else {
                        TextMessage tm = new TextMessage();
                        try {
                            socketToServer = new Socket("127.0.0.1", 11111);
                            tm.setUserName(g.loginText);
                            tm.setFirstValue(FirstNumber);
                            tm.setSecondValue(SecondNumber);
                            tm.setOperation(operation);
                            ObjectOutputStream out = new ObjectOutputStream(socketToServer.getOutputStream());
                            out.writeObject(tm);
                        }catch (Exception e){
                            System.out.println("Error4");
                        }
                        try {
                            Thread.sleep(1000);
                        }catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        try{
                            ObjectInputStream in = new ObjectInputStream(socketToServer.getInputStream());
                            tm = (TextMessage) in.readObject();
                            result = tm.getResult();
                        } catch (Exception e) { System.out.println("Error5!"); }

                        AddCacheList();
                        display.setText((result) + "");
                    }
                }
            }
        }
    }

    private boolean SearchResultInCache1(ArrayList<String> cacheList) {
        cacheSize = cacheList.size();
        String first="", second="",res="", op="";
        for(int i=0; i<cacheSize; i++)
        {
            String m = cacheList.get(i);
            String expression[] = m.split("\\s");
            first = expression[0]; second = expression[1]; op = expression[2]; res = expression[3];
            if(Double.valueOf(first).equals(FirstNumber) && Double.valueOf(second).equals(SecondNumber) && op.equals(operation)){
                result = Double.valueOf(res);
                return true;
            }
        }
        return false;
    }

    private void AddCacheList() {
        cacheSize = cacheList1.size();
        String i0 = String.valueOf(FirstNumber), i1 = String.valueOf(SecondNumber), i2 = operation, i3 = String.valueOf(result);
        cache = i0 + " " + i1 + " " + i2 + " " + i3;

        if (cacheSize == 10) {
            String i = cacheList1.get(0);
            cacheList2.add(i);
            cacheList1.remove(0);
            cacheList1.add(cache);
        } else
            cacheList1.add(cache);

        i0 = "";
        i1 = "";
        i2 = "";
        i3 = "";
        cache = "";
    }
}

