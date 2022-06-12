import client.GameClient;
import com.esotericsoftware.kryonet.*;
import views.MainFrame;

public class Main {
    public static void main(String[] args) {
        GameClient client = new GameClient();

        MainFrame mainFrame = new MainFrame(client);
    }
}