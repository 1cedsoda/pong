import controllers.GamesController;
import controllers.LobbyController;
import geometry.Collision;
import common.geometry.Line;
import common.geometry.Point;
import server.MessageRouter;
import server.Server;

import java.util.ArrayList;

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

        Line l1 = new Line(new Point(0,0), new Point(0,200));
        Collision c = Collision.calculateCollisonWithWalls(l1);
        System.out.println(c);
    }
}