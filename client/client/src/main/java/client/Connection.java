package client;

import common.messages.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Connection {

    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    private String host;
    private int port;
    private Socket socket;

    public Connection(Socket socket) {
        try {
            this.socket = socket;
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void disconnect() {
        try {
            this.socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void send(Message message) {
        System.out.println(message.toString());
        try {
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Message> readMessages() {
        List<Message> messages = new ArrayList<Message>();
        try {
            messages = (List<Message>) objectInputStream.readObject();
            for (Message message : messages) {
                System.out.println(message.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return messages;
    }
}
