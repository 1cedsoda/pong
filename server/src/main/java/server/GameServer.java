package server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.*;
import common.messages.GameJoinMessage;
import common.messages.LobbyJoinMessage;
import common.messages.Network;
import controllers.Games;
import controllers.Lobby;

import java.io.IOException;

public class GameServer {
    static int portTcp = 2347;
    static int portUdp = portTcp;

    Lobby lobby;
    public Server server;

    public Games games;

    public static GameServer instance;

    public GameServer()  {
        games = new Games();
        lobby = new Lobby();

        System.out.println("Server started");

        instance = this;

        server = new Server() {
            protected Connection newConnection () {
                // By providing our own connection implementation, we can store per
                // connection state without a connection ID to state look up.
                return new PlayerConnection();
            }
        };

        Network.register(server);

        server.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                PlayerConnection playerConnection = (PlayerConnection) connection;

                System.out.println("-> " + object);
                if (object instanceof LobbyJoinMessage message) {
                    lobby.onLobbyJoinMessage(message, playerConnection);
                }

                if(object instanceof GameJoinMessage message) {
                    games.onGameJoinMessage(message, playerConnection);
                }
            }

            public void connected (Connection connection) {
                connection.setKeepAliveTCP(500);
                System.out.println("New connection");
            }

            public void disconnected (Connection connection) {
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
