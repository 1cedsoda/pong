package networking;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.FrameworkMessage;
import com.esotericsoftware.kryonet.Listener;
import common.messages.*;
import controllers.Lobby;
import views.MainFrame;

import java.io.IOException;

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
            public void connected(Connection connection) {
                log("Connected!");
                LobbyJoinMessage lobbyJoinMessage = new LobbyJoinMessage();
                lobbyJoinMessage.name = name;
                client.sendTCP(lobbyJoinMessage);
            }

            public void received(Connection connection, Object object) {

                if (object instanceof FrameworkMessage.KeepAlive message) {
                    client.sendTCP(message);
                    return;
                }

                log("-> " + object);

                if (object instanceof LobbyStateMessage message) {
                    lobby.onLobbyStateMessage(message, connection, gameClient);
                }

                if (object instanceof GameOpenMessage message) {
                    log("Opening game: " + message.gameId);
                    MainFrame.instance.showGamePanel();
                    MainFrame.instance.gamePanel.setGameId(message.gameId);
                    MainFrame.instance.gamePanel.refresh();
                }

                if (object instanceof GameStateMessage message) {
                    // check game id
                    if (MainFrame.instance.gamePanel.game.gameId.equals(message.gameState.gameId)) {
                        MainFrame.instance.gamePanel.game.updateState(message.gameState);
                        MainFrame.instance.gamePanel.refresh();
                    }
                }
            }

            public void disconnected(Connection serverConnection) {
            }
        });

        instance = this;
    }

    public void log(String s) {
        String ip = client.getRemoteAddressTCP().getHostString();
        System.out.println("[" + ip + "] " + s);
    }

    public void connect(String domain, int tcp, String username) throws IOException {
        this.name = username;
        this.client.start();

        new Thread("Connect") {
            public void run() {
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
