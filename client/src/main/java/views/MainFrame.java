package views;

import client.GameClient;

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
        setTitle("PONG");
        setSize(300, 200);
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

    private  void showPanel(String panelName) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, panelName);
    }

    public void showServerPanel() {
        showPanel("server");
    }

    public void showLobbyPanel() {
        showPanel("lobby");
    }

    public void showGamePanel() {
        showPanel("game");
    }
}
