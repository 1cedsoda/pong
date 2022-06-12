package server;

import common.messages.Message;

public class ConnectedMessage{
    public Connection connection;
    public Message message;

    public ConnectedMessage(Message message, Connection connection) {
        super();
    }
}
