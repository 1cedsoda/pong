package server;

import common.messages.Message;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Connection {
    private final Socket socket;
    private final ObjectInputStream objectInputStream;
    private final ObjectOutputStream objectOutputStream;

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        this.objectInputStream = new ObjectInputStream(socket.getInputStream());
        this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
    }

    public void close() throws IOException {
        socket.close();
    }

    public <Message> List<Message> readMessages() {
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

    public <Message> void sendMessage(Message message) throws IOException {
        System.out.println(message.toString());
        objectOutputStream.writeObject(message);
        objectOutputStream.flush();
    }
}
