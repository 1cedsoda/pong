package controllers;

import client.GameClient;
import com.esotericsoftware.kryonet.Connection;
import common.messages.LobbyJoinMessage;
import common.messages.LobbyStateMessage;
import common.states.LobbyEntryState;
import common.states.LobbyState;

import java.util.ArrayList;
import java.util.List;

public class Lobby {

    LobbyState state;
    public Lobby(LobbyState state) {
        this.state = state;
    }

    public Lobby() {
        this.state = new LobbyState();
        this.state.players = new ArrayList<LobbyEntryState>();
    }

    public void onLobbyStateMessage(LobbyStateMessage message, Connection connection, GameClient client) {

    }
}