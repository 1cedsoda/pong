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

    List<PlayerConnection> connections = new ArrayList<PlayerConnection>();

    public Lobby() {
        this.state = new LobbyState();
        this.state.players = new ArrayList<LobbyEntryState>();
    }

    public void onLobbyJoinMessage(LobbyJoinMessage message, PlayerConnection connection, GameServer gameServer) {
        if (connection.name == null) {
            connection.name = message.name;
        }

        sendLobbyState(gameServer);
    }

    private void sendLobbyState(GameServer gameServer) {
        this.updateLobbyState(gameServer);

        LobbyStateMessage lobbyStateMessage = new LobbyStateMessage();
        lobbyStateMessage.lobby = this.state;

        gameServer.server.sendToAllTCP(lobbyStateMessage);
    }

    public void updateLobbyState(GameServer gameServer) {
        this.state.players = new ArrayList<LobbyEntryState>();

        List<Connection> connections = List.of(gameServer.server.getConnections());
        for (Connection connection : connections) {
            PlayerConnection pc = (PlayerConnection) connection;
            LobbyEntryState lobbyEntryState = new LobbyEntryState();
            lobbyEntryState.name = pc.name;
            this.state.players.add(lobbyEntryState);
        }
    }
}
