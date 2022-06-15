import networking.GameClient;
import views.MainFrame;

public class Main {
    public static void main(String[] args) {
        GameClient client = new GameClient();
        client.mainFrame = new MainFrame(client);
    }
}