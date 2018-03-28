import java.io.*;
import java.net.Socket;

/**
 * Created by Remco Sander H on 24-11-2017.
 */
public class Client {
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private String username;

    public Client(String address, int port) {
        // Create a socket to connect to the server
        try {
            this.socket = new Socket(address, port);
            // Collect the input and output streams from the socket
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // create the reader and writer
            writer = new PrintWriter(outputStream);
            reader = new BufferedReader(
                    new InputStreamReader(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initiateStream(){

    }

    public boolean connect(String username) {
        String raw;
        this.username = username;
        CommunicationModel model = CommunicationModel.getInstance();
        model.setUserName(username);
            raw = listen();
            if (raw.charAt(0) == 'H') {
                // Send a line of text
                writer.println("HELO " + username);
                writer.flush();
            }
            raw = listen();

        return respond(raw);
    }

    public void broadcast(String message) {
        resendMessagesTill("BCST " + message, "+OK");
    }

    public void quit() {
        resendMessagesTill("QUIT", "+OK Goodbye");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    private boolean resendMessagesTill (String message, String response ) {
        boolean responseReceived = false;

        while (!responseReceived) {
            writer.println(message);
            writer.flush();

            try {
                String raw = reader.readLine();

                if (raw.equals(response)){
                    responseReceived = true;
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return responseReceived;
    }

    public Socket getSocket() {
        return socket;
    }

    public String listen() {
        String raw = "";
        try {
            raw = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return raw;
    }
    public boolean respond(String raw){
        if (raw.equals("+OK " + username)) {
            return true;
        } else if (raw.equals("-ERR user already logged in")) {
            System.out.println("Username: " + username + " is already logged in");
            return false;
        } else if (raw.equals("-ERR username has invalid format")) {
            System.out.println("Username: " + username + " is in an invalid format");
            System.out.println("A  username may only consist of characters, numbers and underscores('_')");
            return false;
        }
        return false;
    }
    public Reader getReader(){
        return reader;
    }
    public Writer getWriter(){
        return writer;
    }
}
