import java.io.*;
import java.net.*;

public class Server {
    private static Double resultat, first, second;
    private static String user, op;
   // private static ObjectOutputStream out = null;
   // private static ObjectInputStream in = null;
    //private static ServerSocket myServerSocket;

    public static void main(String[] args) {

        while(true) {
            try (ServerSocket myServerSocket = new ServerSocket(11111)) {
                System.out.println("Сервер готов!");
                Socket client = myServerSocket.accept();
                if(client.isConnected() != true){
                    System.out.println("Клиент отключился!");
                    myServerSocket.close();
                    System.exit(0);}

                else
                   System.out.println("Клиент подключился!");

                TextMessage tm = null;
                try {
                    ObjectInputStream in = new ObjectInputStream(client.getInputStream());
                    ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                    tm = (TextMessage) in.readObject();


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

                    tm.setResult(resultat);
                    out.writeObject(tm);
                } catch (IOException e) {
                    System.out.println("Out error!");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                try (FileWriter writer = new FileWriter("\\logger.txt", true)) {
                    writer.write(user + ": " + first + op + second + "=" + resultat + "\n");
                } catch (IOException ex) {
                    System.out.print("Ошибка при записи в логгер!");
                }


            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
