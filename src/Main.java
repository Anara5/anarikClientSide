import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

//CLIENT SIDE
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String host = "netology.homework";
        int port = 8080;
        try {
            // Create a connection to the server socket on the server
            InetAddress inetAddress = InetAddress.getLocalHost();
            Socket socket = new Socket(host, port);
            System.out.println(host + ", ip address: " + inetAddress.getHostAddress());

            // Read and display the response message sent by server
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Message: " + message);

            // Send a message to the server
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(scanner.nextLine());

            // Read and display the response message sent by server
            String message2 = (String) ois.readObject();
            System.out.println("Message: " + message2);

            // Send a reply to server
            oos.writeObject(scanner.nextLine());

            // Read and display a message from the server
            String message3 = (String) ois.readObject();
            System.out.println("Message: " + message3);

            ois.close();
            oos.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}