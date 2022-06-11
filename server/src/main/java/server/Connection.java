package server;

import common.Pair;
import common.messages.Message;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
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

    public List<ConnectedMessage> readMessages() {
        List<Message> messages = new ArrayList<>();
        try {
            messages = (List<Message>) objectInputStream.readObject();
            for (Message message : messages) {
                System.out.println(message.toString());
            }
        } catch (IOException e) {
            System.out.println("Connection closed from: " + socket.getInetAddress());
        }   catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        List<ConnectedMessage> connectedMessages = new ArrayList<>();
        for (Message message : messages) {
            connectedMessages.add(new ConnectedMessage(message, this));
        }
        return connectedMessages;
    }

    public void sendMessage(Message message) throws IOException {
        System.out.println(message.toString());
        objectOutputStream.writeObject(message);
        objectOutputStream.flush();
    }
}
