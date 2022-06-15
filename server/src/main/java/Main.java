import controllers.Games;
import controllers.Lobby;
import networking.GameServer;

public class Main {
    public static void main(String[] args) {
        // Setup 
        Games games = new Games();
        Lobby lobby = new Lobby();

        // Thread communication.Server
        GameServer server = new GameServer();
    }
}