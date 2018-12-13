import jdk.nashorn.internal.runtime.ECMAException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;

public class Gate extends JFrame{

    protected static String loginText;
    private static String passwordText;
    private JPanel panel;
    private JTextField enterLogin, enterPassword;
    private JButton login, registration;
    private JLabel textLogin, textPassword;
    private static BufferedReader reader;
    private  static FileInputStream file;
    private static ArrayList<String> lines = new ArrayList<String>();

    public static void main(String[] args) {
        Gate gate = new Gate();
    }
    public Gate()
    {
        // setLayout(new BorderLayout());
        panel = new JPanel();//new GridLayout(3,2));
        textLogin = new JLabel("USER NAME");
        textPassword = new JLabel("PASSWORD");
        login = new JButton("LOG IN");
        registration = new JButton("REGISTRATION");
        enterLogin = new JTextField(18);
        enterPassword = new JTextField(18);

        add(panel, BorderLayout.CENTER);
        panel.add(textLogin);
        panel.add(enterLogin);
        panel.add(textPassword);
        panel.add(enterPassword);
        panel.add(login);
        panel.add(registration);

        setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250, 170);
        setLocationRelativeTo(null);
        setResizable(false);


        //при нажатии на кнопку LOGIN
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                login();
                //запуск сервера и работа с ним
                /*Сервер подключается к порту на хосте и ждет соединения с клиентом;
                Клиент создает сокет и пытается соединить его с портом на хосте;
                Если создание сокета прошло успешно, то сервер переходит в режим ожидания команд от клиента;
                Клиент формирует команду и передает ее серверу, переходит в режим ожидания ответа;
                Сервер принимает команду, выполнеят ее и пересылает ответ клиенту.*/

            }
        });

        //при нажатии на кнопку REGISTRATION
        registration.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registration();
            }
        });
    }

    private void login()
    {
        loginText = enterLogin.getText();
        passwordText = enterPassword.getText();

        if(loginText.length() == 0 || passwordText.length() == 0)
        {

            JOptionPane.showMessageDialog(panel, "Вы не ввели логин или пароль!");
            enterLogin.setText("");
            enterPassword.setText("");
        }

        else {
            //ищем логин и пароль в файле users.txt
            try {
                file = new FileInputStream("\\users.txt");
                reader = new BufferedReader(new InputStreamReader(file));
                String line;
                while ((line = reader.readLine()) != null)
                    lines.add(line);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Не удалось произвести считывание с файла!");
                System.exit(-1);
            }
            int linesSize = lines.size();
            for (int i = 0; i < linesSize; i++) {
                String m = lines.get(i);
                String expression[] = m.split(";");
                if (expression[0].equals(loginText) && expression[1].equals(passwordText)) {
                    Calculator calculator = new Calculator();
                    panel.setVisible(false);
                    break;
                }
                if (i == (linesSize - 1)) {
                    if ((expression[0].equals(loginText) == false) && (expression[1].equals(passwordText) == false)) {
                        JOptionPane.showMessageDialog(panel, "Вы не зарегистрированы или введен неверный логин/пароль");
                        enterLogin.setText("");
                        enterPassword.setText("");
                    }
                }
            }
        }
    }

    private void registration()
    {
        loginText = enterLogin.getText();
        passwordText = enterPassword.getText();

        if (loginText.length() == 0 || passwordText.length() == 0)
        {
            JOptionPane.showMessageDialog(panel, "Вы не ввели логин или пароль!");
            enterLogin.setText("");
            enterPassword.setText("");
        }

        else
        {
            try(FileWriter writer = new FileWriter("\\users.txt", true))
            {
                writer.write(loginText + ";" + passwordText + "\n");
            }
            catch (IOException ex)
            {
                System.out.print("Ошибка при регистрации!!!");
            }

            enterLogin.setText("");
            enterPassword.setText("");
            JOptionPane.showMessageDialog(panel, "Вы зарегистрированы!\nВаш логин: " + loginText + "\nВаш пароль: " + passwordText + "\nВведите логин и пароль еще раз, чтобы войти :)");
        }
    }
}
