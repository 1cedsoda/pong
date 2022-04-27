package views;

import client.Client;

import javax.swing.*;

public class LobbyPanel extends JPanel {
    public LobbyPanel(MainFrame mainFrame, Client client) {
        add(new JLabel("Lobby Panel"));

        // Exit BUtton server
        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(0, 0, 100, 30);
        add(exitButton);

        // isconnect client if exitButton is pressed
        exitButton.addActionListener(e -> {
            client.disconnect();
            mainFrame.showServerPanel();
        });

    }
}
