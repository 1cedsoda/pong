package views;

import networking.GameClient;

import javax.swing.*;
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
        hostInput.setText("127.0.0.1");
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
        //JPanel inputName = new JPanel();
        //inputName.setLayout(new BoxLayout(inputName, BoxLayout.X_AXIS));
        //container.add(inputName);

        //JLabel nameLabel = new JLabel("Your Name: ");
        //inputName.add(nameLabel);

        //JTextField nameInput = new JTextField(20);
        //nameInput.setText();
        //inputName.add(nameInput);

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

                client.connect(host, portInt);

                mainFrame.showLobbyPanel();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        });
    }
}
