package server;

import common.messages.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/// Singleton TCP communication.Server
public class Server implements Runnable {

    private ServerSocket serverSocket;
    private List<Connection> connections;

    private static final int PORT = 2347;

    private void acceptNewConnections() {
        try {
            Socket socket = serverSocket.accept();
            Connection connection = new Connection(socket);
            connections.add(connection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Message> readMessages() {
        List<Message> messages = new ArrayList<Message>();
        for (Connection connection : connections) {
            messages.addAll(connection.readMessages());
        }
        return messages;
    }

    private void listen() {
        try {
            this.serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        listen();
        while (true) {
            acceptNewConnections();
            List<Message> messages = readMessages();
        }
    }

    public void runThread() {
        new Thread(this).start();
    }
}
