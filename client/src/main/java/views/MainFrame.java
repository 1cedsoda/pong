package views;

import networking.GameClient;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public ServerPanel serverPanel;
    public LobbyPanel lobbyPanel;
    public GamePanel gamePanel;

    private JPanel cards;

    //singleton
    public static MainFrame instance;

    public MainFrame(GameClient client) {
        setTitle("WinfPong");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        serverPanel = new ServerPanel(this, client);
        lobbyPanel = new LobbyPanel();
        gamePanel = new GamePanel();

        cards = new JPanel(new CardLayout());
        cards.add(serverPanel, "server");
        cards.add(lobbyPanel, "lobby");
        cards.add(gamePanel, "game");

        add(cards);

        showServerPanel();

        pack();

        instance = this;
    }

    private void showPanel(String panelName) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, panelName);
    }

    public void showServerPanel() {
        setSize(400, 400);
        showPanel("server");
    }

    public void showLobbyPanel() {
        setSize(400, 400);
        showPanel("lobby");
    }

    public void showGamePanel() {
        setSize(GameCanvas.width, GameCanvas.height + 60);
        showPanel("game");
    }
}
