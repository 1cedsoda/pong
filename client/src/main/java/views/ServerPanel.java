package views;

import client.Client;

import javax.swing.*;

public class ServerPanel extends JPanel {
    public ServerPanel(MainFrame mainFrame, Client client) {
        add(new JLabel("Server Panel"));

        // New COntainer
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
        hostInput.setText("localhost");
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
            } catch (Exception e2) {
                e2.printStackTrace();
            }

            mainFrame.showLobbyPanel();

        });
    }
}
