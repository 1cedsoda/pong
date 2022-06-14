package views;

import controllers.Game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public Game game;
    public GamePanel() {
        refresh();
    }

    public void setGameId(String gameId) {
        this.game = new Game(gameId);
    }

    public void refresh() {
        removeAll();
        this.setLayout(new BorderLayout());

        // Exit Button in the bottom
        JPanel exitButtonRow = new JPanel();
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            game.exitGame();
        });
        add(exitButton, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }
}
