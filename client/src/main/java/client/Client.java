package client;

import common.messages.Message;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class Client implements Runnable {
    private Connection connection;

    public void connect(String host, int port) {
        try {
            Socket socket = new Socket(host, port);
            this.connection = new Connection(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
