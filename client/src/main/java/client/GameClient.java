package client;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.*;
import common.messages.*;
import controllers.Lobby;
import views.GamePanel;
import views.MainFrame;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.stream.Collectors;

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
                if (object instanceof FrameworkMessage.KeepAlive message) {
                    client.sendTCP(message);
                    return;
                }

                System.out.println("-> " + object);

                if (object instanceof LobbyStateMessage message) {
                    lobby.onLobbyStateMessage(message, connection);
                }

                if (object instanceof GameOpenMessage message) {
                    System.out.println("Game open message received" + message.gameId);
                    MainFrame.instance.showGamePanel();
                    MainFrame.instance.gamePanel.setGameId(message.gameId);
                    MainFrame.instance.gamePanel.refresh();
                }
            }

            public void disconnected (Connection connection) {
            }
        });

        instance = this;
    }

    public void connect(String domain, int tcp) throws IOException {
        this.name = getUsername();
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

    private String getUsername() {
        return Arrays.stream(System
                        .getProperty("user.name")
                        .split("\\s+"))
                .map(t -> t.substring(0, 1).toUpperCase() + t.substring(1))
                .collect(Collectors.joining(" "));
    }
}
