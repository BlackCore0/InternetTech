import java.io.Reader;
import java.net.Socket;

public class Input implements Runnable {
    CommunicationModel model = CommunicationModel.getInstance();
    private Reader reader;
    private Client client;
    private Socket socket;
    public Input(Client client) {
        this.client = client;
        reader = client.getReader();
        socket = client.getSocket();
    }

    @Override
    public void run() {
        System.out.println("Holla");
        boolean connected = true;
        while (client.getSocket().isConnected()){
        if(model.isListening()) {
//            try {
//                reader = new BufferedReader(
//                        new InputStreamReader(socket.getInputStream()));
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            String responce = client.listen();
            System.out.println(responce);
        }
        }
    }
}
