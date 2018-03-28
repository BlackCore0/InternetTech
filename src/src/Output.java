import java.io.Writer;

public class Output implements Runnable {
    CommunicationModel  model = CommunicationModel.getInstance();
    private Writer writer;
    private Client client;

    public Output(Client client) {
        this.client = client;
        this.writer = client.getWriter();
    }

    @Override
    public void run() {
        System.out.println("Hella");
        while(client.getSocket().isConnected()){
            if(!model.isListening()){
                String username = model.getUserName();
                String response = client.listen();

                if (response.equals("+OK " + username)) {
                } else if (response.equals("-ERR user already logged in")) {
                    System.out.println("Username: " + username + " is already logged in");
                } else if (response.equals("-ERR username has invalid format")) {
                    System.out.println("Username: " + username + " is in an invalid format");
                    System.out.println("A  username may only consist of characters, numbers and underscores('_')");

                }
            }
        }

    }

}
