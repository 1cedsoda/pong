package networking;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.FrameworkMessage;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import common.messages.*;
import controllers.Games;
import controllers.Lobby;

import java.io.IOException;

public class GameServer {
    static int portTcp = 2347;
    static int portUdp = portTcp;

    public Lobby lobby;
    public Server server;

    public Games games;

    public static GameServer instance;

    public GameServer() {
        games = new Games();
        lobby = new Lobby();

        System.out.println("Server started");

        instance = this;

        server = new Server() {
            protected Connection newConnection() {
                // By providing our own connection implementation, we can store per
                // connection state without a connection ID to state look up.
                return new PlayerConnection();
            }
        };

        Network.register(server);

        server.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                PlayerConnection playerConnection = (PlayerConnection) connection;

                if (object instanceof FrameworkMessage.KeepAlive) {
                    return;
                }

                System.out.println(playerConnection.getRemoteAddressTCP() + " -> " + object);

                if (object instanceof LobbyJoinMessage message) {
                    lobby.onLobbyJoinMessage(message, playerConnection);
                }

                if (object instanceof GameJoinMessage message) {
                    games.onGameJoinMessage(message, playerConnection);
                }

                if (object instanceof GameExitMessage message) {
                    games.onGameExitMessage(message, playerConnection);
                }

                if (object instanceof GameMoveMessage message) {
                    games.onGameMoveMessage(message, playerConnection);
                }
            }

            public void connected(Connection connection) {
                connection.setKeepAliveTCP(500);
                System.out.println("New connection");
            }

            public void disconnected(Connection connection) {
                System.out.println("Connection closed. " + server.getConnections().length + " connections remaining.");
                instance.lobby.sendLobbyState();
            }
        });

        try {
            server.bind(portTcp, portUdp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        server.start();
    }
}
