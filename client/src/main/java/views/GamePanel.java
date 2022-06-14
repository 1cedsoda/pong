package views;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public String gameId;
    public GamePanel() {
        refresh();
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void refresh() {
        removeAll();
        this.setLayout(new BorderLayout());

        // Title Row
        JPanel titleRow = new JPanel();
        String title = "Game: " + gameId;
        titleRow.add(new JLabel(title, SwingConstants.CENTER));


        add(titleRow, BorderLayout.NORTH);
        revalidate();
        repaint();
    }
}
