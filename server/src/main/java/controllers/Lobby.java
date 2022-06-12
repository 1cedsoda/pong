package controllers;

import com.esotericsoftware.kryonet.Connection;
import common.messages.LobbyJoinMessage;
import common.messages.LobbyStateMessage;
import common.states.LobbyEntryState;
import common.states.LobbyState;
import server.GameServer;
import server.PlayerConnection;

import java.util.ArrayList;
import java.util.List;

public class Lobby {

    LobbyState state;
    public Lobby(LobbyState state) {
        this.state = state;
    }

    List<Connection> connections = new ArrayList<Connection>();

    public Lobby() {
        this.state = new LobbyState();
        this.state.players = new ArrayList<LobbyEntryState>();
    }

    public void onLobbyJoinMessage(LobbyJoinMessage message, PlayerConnection connection, GameServer gameServer) {
        if (connection.name == null) {
            connection.name = message.name;
        }

        LobbyStateMessage lobbyStateMessage = new LobbyStateMessage();
        lobbyStateMessage.lobby = this.state;

        gameServer.server.sendToAllTCP(lobbyStateMessage);
    }
}
