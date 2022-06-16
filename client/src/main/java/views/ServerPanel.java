package views;

import networking.GameClient;
import utils.PlayerName;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ServerPanel extends JPanel {
    public ServerPanel(MainFrame mainFrame, GameClient client) {

        // New Container
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        add(container);

        // Input Host
        JPanel inputHost = new JPanel();
        inputHost.setLayout(new BoxLayout(inputHost, BoxLayout.X_AXIS));
        container.add(inputHost);

        JLabel hostLabel = new JLabel("Host: ");
        inputHost.add(hostLabel);

        JTextField hostInput = new JTextField(20);
        hostInput.setText("winfpong.ddns.net");
        inputHost.add(hostInput);

        // Input Port
        JPanel inputPort = new JPanel();
        inputPort.setLayout(new BoxLayout(inputPort, BoxLayout.X_AXIS));
        container.add(inputPort);

        JLabel portLabel = new JLabel("Port: ");
        inputPort.add(portLabel);

        JTextField portInput = new JTextField(20);
        portInput.setText("2347");
        inputPort.add(portInput);

        // Input Name
        JPanel inputName = new JPanel();
        inputName.setLayout(new BoxLayout(inputName, BoxLayout.X_AXIS));
        container.add(inputName);

        JLabel nameLabel = new JLabel("Your Name: ");
        inputName.add(nameLabel);

        JTextField nameInput = new JTextField(20);
        nameInput.setText(PlayerName.getPlayerName());
        inputName.add(nameInput);

        // Connect Button
        JButton connectButton = new JButton("Join");
        container.add(connectButton);

        // Connect with host and port
        connectButton.addActionListener(e -> {
            String host = hostInput.getText();
            String port = portInput.getText();
            //port to int
            int portInt = Integer.parseInt(port);
            try {

                client.connect(host, portInt, nameInput.getText());
                PlayerName.setPlayerName(nameInput.getText());

                mainFrame.showLobbyPanel();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        });

        // Hyperlink to repo
        String url = "https://github.com/1cedsoda/pong";
        JLabel hyperlink = new JLabel("<html><a href=\"" + url + "\">Source Code</a></html>");
        hyperlink.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hyperlink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
            }
        });
        container.add(hyperlink, BorderLayout.PAGE_END);

    }
}
