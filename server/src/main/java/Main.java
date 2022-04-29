import common.controllers.GamesController;
import common.controllers.LobbyController;
import geometry.Collision;
import common.geometry.Line;
import common.geometry.Point;
import server.MessageRouter;
import server.Server;

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

        Line l1 = new Line(new Point(0, -1), new Point(0, 1));
        Line l2 = new Line(new Point(-1, -1), new Point(1, 1));
        Collision c = Collision.calulateCollision(l1, l2);
    }
}