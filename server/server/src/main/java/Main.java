import common.controllers.LobbyController;
import server.MessageRouter;
import server.Server;
import controllers.GameController;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Setup state
        List<GameController> gameControllers = new ArrayList<GameController>();
        LobbyController lobbyController = new LobbyController();

        // Create Message Router
        MessageRouter messageRouter = new MessageRouter(gameControllers, lobbyController);

        // Thread communication.Server
        Server server = new Server();
        server.runThread();
    }
}