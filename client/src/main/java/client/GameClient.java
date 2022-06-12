package client;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.*;
import common.messages.LobbyJoinMessage;
import common.messages.Network;

import java.io.IOException;

public class GameClient {
    private Client client;
    private Kryo kryo;

    private String name;
    public GameClient() {
        client = new Client();
        client.start();

        Network.register(client);

        client.addListener(new Listener() {
            public void connected (Connection connection) {
                System.out.println("Connected!");
                LobbyJoinMessage lobbyJoinMessage = new LobbyJoinMessage();
                lobbyJoinMessage.name = name;
                client.sendTCP(lobbyJoinMessage);
            }

            public void received (Connection connection, Object object) {
                System.out.println("Received: " + object);
                if (object instanceof LobbyJoinMessage message) {
                    System.out.println(message);
                }
            }

            public void disconnected (Connection connection) {
            }
        });
    }

    public void connect(String domain, int tcp, String name) throws IOException {
        this.name = name;

        new Thread("Connect") {
            public void run () {
                try {
                    client.connect(5000, domain, tcp);
                    // Server communication after connection can go here, or in Listener#connected().
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        }.start();
    }

    public void disconnect() {
        client.stop();
    }
}
