import controllers.Game;
import controllers.Games;
import controllers.Lobby;
import server.MessageRouter;
import server.Server;

public class Main {
    public static void main(String[] args) {
        // Setup 
        Games games =new Games();
        Lobby lobby = new Lobby();

        // Create Message Router
        MessageRouter messageRouter = new MessageRouter(games, lobby);

        // Thread communication.Server
        Server server = new Server(messageRouter);
        server.runThread();

        //Line movement = new Line(new Point(0.8, 0.8), new Point(1.2, 1.2));
        //Collision c = Collision.calculateCollisonWithWalls(movement);
        //System.out.println(c);

        Game g = Game.newGame();

        GameTimer gt = new GameTimer(g);
        gt.runThread();
    }
}