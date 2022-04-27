import common.controllers.GamesController;
import common.controllers.LobbyController;
import server.MessageRouter;
import server.Server;
import controllers.GameController;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Setup state
        GamesController gamesController =new GamesController();
        LobbyController lobbyController = new LobbyController();

        // Create Message Router
        MessageRouter messageRouter = new MessageRouter(gamesController, lobbyController);

        // Thread communication.Server
        Server server = new Server(messageRouter);
        server.runThread();
    }
}