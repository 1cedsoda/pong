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
        showPanel("server");
        setSize(300, 200);
    }

    public void showLobbyPanel() {
        showPanel("lobby");
        setSize(300, 200);
    }

    public void showGamePanel() {
        showPanel("game");
        setSize(GameCanvas.width, GameCanvas.height + 60);
    }
}
