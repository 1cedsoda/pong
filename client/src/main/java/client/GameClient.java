package client;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.*;
import common.messages.*;
import controllers.Lobby;
import views.MainFrame;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class GameClient {

    static public GameClient instance;
    public Client client;
    private Kryo kryo;

    private String name;

    public Lobby lobby;

    public MainFrame mainFrame;

    public GameClient() {
        lobby = new Lobby();

        client = new Client();

        Network.register(client);

        GameClient gameClient = this;

        client.addListener(new Listener() {
            public void connected (Connection connection) {
                System.out.println("Connected!");
                LobbyJoinMessage lobbyJoinMessage = new LobbyJoinMessage();
                lobbyJoinMessage.name = name;
                client.sendTCP(lobbyJoinMessage);
            }

            public void received (Connection connection, Object object) {
                System.out.println("-> " + object);
                if (object instanceof FrameworkMessage.KeepAlive message) {
                    client.sendTCP(message);
                }
                if (object instanceof LobbyStateMessage message) {
                    lobby.onLobbyStateMessage(message, connection);
                }
            }

            public void disconnected (Connection connection) {
            }
        });

        instance = this;
    }

    public void connect(String domain, int tcp, String name) throws IOException {
        this.name = name;
        this.client.start();

        new Thread("Connect") {
            public void run () {
                try {
                    int udp = tcp;
                    client.connect(5000, domain, tcp, udp);
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

    public void createGame() {
        // Send GameOpenMessage to server
        // Server will create a new game and send it to all clients
        GameJoinMessage gameJoinMessage = new GameJoinMessage();

        // Show GamePanel
        mainFrame.showGamePanel();
    }
}
