package views;

import client.GameClient;
import utils.ImageCreate;

import javax.swing.*;

public class LobbyPanel extends JPanel {
    public LobbyPanel(MainFrame mainFrame, GameClient client) {
        add(new JLabel("Lobby Panel"));

        // Exit Button server
        Icon exitIcon = new ImageIcon(ImageCreate.exit);
        JButton exitButton = new JButton(exitIcon);
        exitButton.setBounds(0, 0, 100, 30);
        add(exitButton);

        // isconnect client if exitButton is pressed
        exitButton.addActionListener(e -> {
            client.disconnect();
            mainFrame.showServerPanel();
        });

    }
}
