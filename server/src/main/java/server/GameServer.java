package server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.*;
import common.messages.LobbyJoinMessage;
import common.messages.Network;
import controllers.Lobby;

import java.io.IOException;

public class GameServer {
    static int portTcp = 2347;

    Lobby lobby;
    public Server server;

    private Kryo kryo;

    public GameServer()  {
        lobby = new Lobby();

        System.out.println("Server started");

        GameServer gameServer = this;

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

                System.out.println("Received: " + object);
                if (object instanceof LobbyJoinMessage) {
                    LobbyJoinMessage message = (LobbyJoinMessage) object;
                    lobby.onLobbyJoinMessage(message, playerConnection, gameServer);
                }
            }

            public void connected (Connection connection) {
                System.out.println("New connection");
            }

            public void disconnect (Connection connection) {
                System.out.println("Connection closed");
            }
        });

        try {
            server.bind(portTcp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        server.start();

    }
}