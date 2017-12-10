import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;

/**
 * Chat room server class
 */
public class Server {
    /**
     * The initial port number and should be the same in the client program
     */
    private int portNumber = 5555;
    /**
     * The start time of server
     */
    private static long serverStartTime;
    /**
     * SimpleDateFormat to display the current time
     */
    private SimpleDateFormat df;

    /**
     * Welcome message
     */
    private String welcome = "Please type your username.";
    /**
     * Accepted message
     */
    private String accepted = "Your username is accepted.";
    /**
     * All commands that user could use
     */
    private String[] commands = {"\\help : show all commands you can type", "\\quit : to quit the chat room",
            "\\getServerTime : You can get the time that the server has run", "\\getClientTime : You can get the time that you have entered the chat room",
            "\\getServerIP : You can get the server's IP address", "\\getClientIP : You can get your IP address",
            "\\getNumberClient : You can get the number of current clients",
            "\\pm (Username) : You can use this command to send a private message to another user"};

    /**
     * Server socket
     */
    private ServerSocket ss;
    /**
     * Store all connected clients name
     */
    private HashSet<String> clientNames = new HashSet<String>();
    /**
     * Store all connected clients name and out stream
     */
    private Hashtable<String, PrintWriter> clientWriters = new Hashtable<String, PrintWriter>();

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start();
    }

    /**
     * Initialize the SimpleDateFormat
     */
    public Server() {
        df = new SimpleDateFormat("HH:mm:ss");
    }

    /**
     * Create the
     * @throws IOException
     */
    private void start() throws IOException {
        ss = new ServerSocket(portNumber);
        serverStartTime = System.currentTimeMillis();
        System.out.println("Chat room server at " + InetAddress.getLocalHost() + " is waiting for connections ...");
        Socket socket;
        Thread thread;
        try {
            while (true) {
                socket = ss.accept();
                thread = new Thread(new HandleSession(socket));
                thread.start();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            shutDown();
        }
    }

    public void shutDown() {
        try {
            ss.close();
            System.out.println("Chat room server is shut down.");
        } catch (Exception e) {
            System.err.println("Problem shutting down the server");
            System.err.println(e.getMessage());
        }
    }

    class HandleSession implements Runnable {
        private Socket socket;
        private String name;
        private BufferedReader in = null;
        private PrintWriter out = null;
        private long clientStartTime;

        HandleSession(Socket socket) {
            this.socket = socket;
        }
        public void run() {
            try {
                createStreams();
                getClientUsername();
                listenForClientMessage();
            } catch (IOException e) {
                if(name != null) {
                    System.err.println(name + " has disconnected: " + e.getMessage());
                } else System.err.println(e.getMessage());
            }
            finally {
                closeConnection();
            }
        }

        private void createStreams() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
                System.out.println("One connection is created");
            } catch (IOException e) {
                System.err.println("Exception in createStreams(): " + e);
            }
        }

        private void getClientUsername() {
            while (true) {
                out.println(welcome);
                out.flush();
                try {
                    name = in.readLine();
                } catch (IOException e) {
                    System.err.println("Exception in getClientUserName: " + e.getMessage());
                }
                if (name == null)
                    return;
                synchronized (clientNames) {
                    if (!clientNames.contains(name)) {
                        clientNames.add(name);
                        clientWriters.put(name, out);
                        break;
                    }
                }
                out.println("Sorry, this username already exists. Please try another name.");
                out.flush();
            }
            clientStartTime = System.currentTimeMillis();
            out.println(accepted + "Please type messages.");
            out.flush();
            System.out.println("[" + df.format(new Date()) + "] " +  name + " has entered the chat room.");
            for (PrintWriter writer : clientWriters.values()) {
                if (!out.equals(writer)) {
                    writer.println("[" + df.format(new Date()) + "] " + name + " has entered the chat room.");
                    writer.flush();
                }
            }

        }

        private void listenForClientMessage() throws IOException {
            String line;
            while (in != null) {
                line = in.readLine();
                if (line == null) {
                    break;
                }
                if (line.startsWith("\\pm ")) {
                    String name = line.substring(4);
                    privateMessage(name);
                }
                if (line.startsWith("\\")) {
                    if(!processClientRequest(line))
                        return;
                }
                else broadCast("[" + df.format(new Date()) + "] " +  name + " : " + line);
            }
        }

        private void broadCast(String message) {
            for (PrintWriter writer : clientWriters.values()) {
                writer.println(message);
                writer.flush();
            }
            System.out.println(message);
        }

        private boolean processClientRequest(String command) {
            switch (command) {

                case "\\help":
                    for (String s : commands) {
                        out.println("Command " + s);
                        out.flush();
                    }
                    break;

                case "\\quit":
                    return false;

                case "\\getServerTime":
                    getServerTime();
                    break;

                case "\\getClientTime":
                    getClientTime();
                    break;

                case "\\getServerIP":
                    getServerIP();
                    break;

                case "\\getClientIP":
                    getClientIP();
                    break;

                case "\\getNumberClient":
                    getNumberOfClients();
                    break;


            }
            return true;
        }

        private void closeConnection() {
            if (name != null) {
                broadCast(name + " has left the chat room");
                clientNames.remove(name);
            }

            if (out != null) {
                clientWriters.remove(out);
            }
            try {
                socket.close();
                Thread.currentThread().interrupt();
            } catch (Exception e) {
                System.err.println("Exception when closing the client socket");
                System.err.println(e.getMessage());
            }
        }

        private void getServerTime() {
            long currentTime = ((System.currentTimeMillis() - serverStartTime) / 1000) / 60;
            out.println("The server has run " + currentTime + " minutes.");
            out.flush();
        }

        private void getClientTime() {
            long currentTime =((System.currentTimeMillis() - clientStartTime) / 1000) / 60;
            out.println("You have been in the chat room for  " + currentTime + " minutes.");
            out.flush();
        }

        private void getServerIP() {
            try {
                out.println("The server's IP address is " + InetAddress.getLocalHost().getHostAddress());
                out.flush();
            } catch (Exception e) {
                System.err.println("Exception in getServerIP(): " + e.getMessage());
            }
        }

        private void getClientIP() {
            try {
                out.println("Your IP address is " + socket.getInetAddress().getHostAddress());
                out.flush();
            } catch (Exception e) {
                System.err.println("Exception in getClientIP(): " + e.getMessage());
            }
        }

        private void getNumberOfClients() {
            out.println("There are " + clientNames.size() + " clients in this chat room.");
            out.flush();
        }

        private void privateMessage(String name) throws IOException{
            if (clientWriters.containsKey(name)) {
                out.print("To " + name + ": ");
                out.flush();
                synchronized (clientWriters.get(name)) {
                    clientWriters.get(name).print("[" + df.format(new Date()) + "] " + this.name + " send a private message to you: " + in.readLine());
                }
            } else {
                out.println("There is no such client.");
            }
        }



    }
}

