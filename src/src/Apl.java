/**
 * Created by Remco Sander H on 24-11-2017.
 */
public class Apl {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 1337;
    private static final String USERNAME = "Remcosmlu";
    CommunicationModel model = CommunicationModel.getInstance();

    private void run() {
        model.setUserName(USERNAME);
        Client client = new Client(SERVER_ADDRESS, SERVER_PORT);

        boolean connected = client.connect(USERNAME);
        if (connected){
            client.broadcast("Hello ChatServer");
            Input inputReader = new Input(client);
            Output outputWriter = new Output(client);
            Thread inputThread = new Thread(inputReader);
            Thread outputThread = new Thread(outputWriter);
            model.setInputReader(inputReader);
            model.setOutputWriter(outputWriter);
            inputThread.start();
            outputThread.start();
            try {
                inputThread.join();
                outputThread.join();
            }
            catch (InterruptedException iex){
                iex.printStackTrace();
            }
            client.broadcast("GETUSERS");
            client.quit();
            System.out.println("test");
        } else {
            System.out.println("Connection was terminated");
        }
    }


    public static void main(String[] args) {
        new Apl().run();
    }
}
