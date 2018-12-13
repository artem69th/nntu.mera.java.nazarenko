import java.io.*;
import java.net.*;

public class Server {
    private static Double resultat, first, second;
    private static String user, op;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    //private static ServerSocket myServerSocket;

    public static void main(String[] args) {


        try {
            ServerSocket myServerSocket = new ServerSocket(11111);
            System.out.println("Сервер готов!");
            TextMessage tm = null;
            try {
                //ждем когда подключится клиент
                    Socket client = myServerSocket.accept();
                    System.out.println("Клиент подключился!");
                    in = new ObjectInputStream(client.getInputStream());
                    out = new ObjectOutputStream(client.getOutputStream());
                    tm = (TextMessage) in.readObject();
               } catch (Exception e) { System.out.println("Input/output error!"); }

            //выполнение операции и вывод в логгер
            user = tm.getUserName();
            first = tm.getFirstValue();
            second = tm.getSecondValue();
            op = tm.getOperation();

            if (op.equals("+"))
                resultat = first + second;
            if (op.equals("-"))
                resultat = first - second;
            if (op.equals("*"))
                resultat = first * second;
            if (op.equals("/"))
                if (second != 0)
                    resultat = first / second;
                else
                    System.out.println("Ошибка! Нельзя делить на 0!");

            try {
                tm.setResult(resultat);
                out.writeObject(tm);
            } catch (Exception e) {
                System.out.println("Out error!");
            }

            try (FileWriter writer = new FileWriter("\\logger.txt", true)) {
                writer.write(user + ": " + first + op + second + "=" + resultat + "\n");
            } catch (IOException ex) {
                System.out.print("Ошибка при записи в логгер!");
            }
        } catch (Exception e) {
            System.out.println("ServerSocket error!");
        }
    }
}
