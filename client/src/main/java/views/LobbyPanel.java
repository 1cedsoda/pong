package views;

import common.enums.PlayerActivity;
import common.messages.GameJoinMessage;
import common.states.LobbyEntryState;
import controllers.Lobby;
import networking.GameClient;
import utils.ImageCreate;

import javax.swing.*;
import java.awt.*;

public class LobbyPanel extends JPanel {

    Lobby lobby;

    public LobbyPanel() {
        lobby = GameClient.instance.lobby;
    }

    public void refresh() {
        removeAll();
        this.setLayout(new BorderLayout());

        // Title
        JPanel titleRow = new JPanel();
        String title = "Lobby: " + GameClient.instance.client.getRemoteAddressTCP().getHostString();
        titleRow.add(new JLabel(title, SwingConstants.CENTER));
        add(titleRow, BorderLayout.NORTH);

        // Player Column
        JPanel playerColumn = new JPanel();
        playerColumn.setLayout(new BoxLayout(playerColumn, BoxLayout.Y_AXIS));
        add(playerColumn, BorderLayout.CENTER);

        // Players
        for (LobbyEntryState lobbyEntryState : lobby.state.players) {
            JPanel playerRow = new JPanel();

            // State Icon
            ImageIcon playerActivityIcon = getPlayerActivityIcon(lobbyEntryState.activity);
            playerRow.add(new JLabel(playerActivityIcon));

            // Name
            JLabel label = new JLabel(lobbyEntryState.name);
            playerRow.add(label);
            playerColumn.add(playerRow, SwingConstants.CENTER);

            // If gameId is not null show join button
            if (lobbyEntryState.gameId != null && lobbyEntryState.activity == PlayerActivity.WAITING) {
                JButton joinButton = new JButton("Join");
                joinButton.addActionListener(e -> {
                    joinGame(lobbyEntryState.gameId);
                });
                playerRow.add(joinButton);
            }
        }
        this.add(playerColumn);

        // Bottom Row
        JPanel bottomRow = new JPanel();
        bottomRow.setLayout(new BoxLayout(bottomRow, BoxLayout.X_AXIS));

        // Create Game Button
        JButton createGameButton = new JButton("Create Game");
        createGameButton.addActionListener(e -> {
            joinGame(null);
        });
        bottomRow.add(createGameButton);

        // Exit Button server
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            GameClient.instance.disconnect();
            MainFrame.instance.showServerPanel();
        });
        bottomRow.add(exitButton);

        add(bottomRow, BorderLayout.SOUTH);

        this.revalidate();
        this.repaint();
    }

    private void joinGame(String gameId) {
        GameJoinMessage gameJoinMessage = new GameJoinMessage();
        gameJoinMessage.gameId = gameId;
        GameClient.instance.client.sendTCP(gameJoinMessage);
    }

    private ImageIcon getPlayerActivityIcon(PlayerActivity activity) {
        switch (activity) {
            case IDLE:
                return new ImageIcon(ImageCreate.player_idle);
            case PLAYING:
                return new ImageIcon(ImageCreate.player_playing);
            case WAITING:
                return new ImageIcon(ImageCreate.player_waiting);
            default:
                return new ImageIcon(ImageCreate.player_idle);
        }
    }
}
