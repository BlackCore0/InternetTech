public class CommunicationModel {
    private static CommunicationModel ourInstance = new CommunicationModel();
    private Input inputReader;
    private Output outputWriter;
    private boolean isListening = false;
    private String message;
    private String userName;
    private CommunicationModel() {

    }
    public static CommunicationModel getInstance() {
        return ourInstance;
    }
    public Input getInputReader() {
        return inputReader;
    }

    public void setInputReader(Input inputReader) {
        this.inputReader = inputReader;
    }

    public Output getOutputWriter() {
        return outputWriter;
    }

    public void setOutputWriter(Output outputWriter) {
        this.outputWriter = outputWriter;
    }

    public boolean isListening() {
        return isListening;
    }

    public void setListening(boolean listening) {
        isListening = listening;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
