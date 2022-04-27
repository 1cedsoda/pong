package server;

import common.Pair;
import common.messages.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/// Singleton TCP communication.Server
public class Server implements Runnable {

    private final MessageRouter messageRouter;
    private ServerSocket serverSocket;
    private List<Connection> connections;

    private static final int PORT = 2347;

    public Server(MessageRouter messageRouter) {
        this.messageRouter = messageRouter;
        this.connections = new ArrayList<>();
    }

    private void acceptNewConnections() {
        try {
            Socket socket = serverSocket.accept();
            Connection connection = new Connection(socket);
            connections.add(connection);
            System.out.println("New connection from " + socket.getInetAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<ConnectedMessage> readMessages() {
        List<ConnectedMessage> messages = new ArrayList<>();
        for (Connection connection : connections) {
            messages.addAll(connection.readMessages());
        }
        return messages;
    }

    private void listen() {
        try {
            this.serverSocket = new ServerSocket(PORT);
            System.out.println("Server listening on port " + PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        listen();
        while (true) {
            acceptNewConnections();
            List<ConnectedMessage> messages = readMessages();
            messageRouter.routeMessages(messages);
        }
    }

    public void runThread() {
        new Thread(this).start();
    }
}
