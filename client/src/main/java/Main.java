import client.Client;
import views.MainFrame;

public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        client.runThread();

        MainFrame mainFrame = new MainFrame(client);
    }
}