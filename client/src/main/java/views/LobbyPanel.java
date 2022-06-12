package views;

import client.Client;
import utils.ImageCreate;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class LobbyPanel extends JPanel {
    public LobbyPanel(MainFrame mainFrame, Client client) {
        add(new JLabel("Lobby Panel"));

        // Exit BUtton server

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
