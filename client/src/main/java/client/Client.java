package client;

import common.messages.Message;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class Client implements Runnable {
    private Connection connection;
    private Socket socket;

    public void connect(String host, int port) {
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.connection = new Connection(socket);

    }

    public void disconnect() {
        this.connection.disconnect();
    }

    public List<Message> readMessages() {
        return this.connection.readMessages();
    }

    public void run() {
        while (true) {
            List<Message> messages = readMessages();
        }
    }

    public void runThread() {
        new Thread(this);
    }
}
