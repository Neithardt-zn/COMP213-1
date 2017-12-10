import java.io.*;
import java.net.Socket;

/**
 * Created by Lumia on 2017/12/10.
 */
public class Client {
    public static void main(String[] args) {
        ClientInstance client = new ClientInstance();
        client.start();
    }

}

class ClientInstance {
    private int portNumber = 5555;
    private String welcome = "Please type your username";
    private String accepted = "Your username is accepted";

    private Socket socket = null;
    private BufferedReader in;
    private PrintWriter out;
    private boolean isAllowedToChat = false;
    private boolean isServerConnected = false;
    private String clientName;

    public void start() {
        establishConnection();
        handleOutgoingMessage();
        handleIncomingMessage();
    }

    private void establishConnection() {
        String serverAddress = getClientInput("What is the address of the server that you want to connect to");
        try {
            socket = new Socket(serverAddress, portNumber);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            isServerConnected = true;
        } catch (IOException e) {
            System.err.println("Exception in establishConnection(): " + e.getMessage());
        }
        handleProfileSetUp();
    }

    private void handleProfileSetUp() {
        String line = null;
        while (!isAllowedToChat) {
            try {
                line = in.readLine();
            } catch (IOException e) {
                System.err.println("Exception in handleProfileSetUp(): " + e.getMessage());

            }
            if (line.startsWith(welcome)) {
                out.println(getClientInput(welcome));
            } else if (line.startsWith(accepted)) {
                isAllowedToChat = true;
                System.out.println(accepted + " You can type message.");
                System.out.println("To see a list of commands, type \\help.");
            } else System.out.println(line);
        }
    }

    private void handleOutgoingMessage() {
        Thread senderThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isServerConnected) {
                    out.println(getClientInput(null));
                }
            }
        });
        senderThread.start();
    }

    private String getClientInput(String hint) {
        String message = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            if (hint != null) {
                System.out.println(hint);
            }
            message = reader.readLine();

        } catch (IOException e) {
            System.err.println("Exception in getClientInput(): " + e.getMessage());
        }
        return message;
    }

    private void handleIncomingMessage() {
        Thread listenerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isServerConnected) {
                    String line = null;
                    try {
                        line = in.readLine();
                        if (line == null) {
                            isServerConnected = false;
                            System.err.println("Disconnected from server");
                            closeConnection();
                            break;
                        }
                        System.out.println(line);
                    } catch (IOException e) {
                        isServerConnected = false;
                        System.err.println("IOException in handleIncomingMessage(): " + e.getMessage());
                    }
                }
            }
        });
        listenerThread.start();
    }

    void closeConnection() {
        try {
            socket.close();
            System.exit(0);
        } catch (IOException e) {
            System.err.println("Exception when closing the socket");
            System.err.println(e.getMessage());
        } finally {
            System.err.println("You have disconnected to the server");
        }
    }
}


