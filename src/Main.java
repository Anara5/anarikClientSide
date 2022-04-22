import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

//CLIENT SIDE

public class Main {
    public static void main(String[] args) throws UnknownHostException {
        Scanner scanner = new Scanner(System.in);
        String host = "netology.homework";
        int port = 8080;

        // Create a connection to the server socket on the server
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println(host + ", ip address: " + inetAddress.getHostAddress());
        try (Socket socket = new Socket(host, port);
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())
        ) {
                // Read and display the response message sent by server
                String message = (String) ois.readObject();
                System.out.println("Message: " + message);

                // Send a message to the server
                oos.writeObject(scanner.nextLine());

                // Read and display the response message sent by server
                String message2 = (String) ois.readObject();
                System.out.println("Message: " + message2);

                do {
                    try {
                        // Send a reply to server
                        oos.writeObject(scanner.nextLine());

                        // Read and display a message from the server
                        String message3 = (String) ois.readObject();
                        System.out.println("Message: " + message3);

                        if(message3.startsWith("Welcome")) {
                            break;
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } while (true);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}