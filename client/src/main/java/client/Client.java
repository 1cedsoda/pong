package client;

import common.messages.Message;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class Client implements Runnable {
    private Connection connection;

    public void connect(String host, int port) {
        try {
            System.out.println("1");
            Socket socket = new Socket(host, port);
            System.out.println("2");
            this.connection = new Connection(socket);
            System.out.println("3");
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
