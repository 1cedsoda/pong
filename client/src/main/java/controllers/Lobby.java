package controllers;

import com.esotericsoftware.kryonet.Connection;
import common.messages.LobbyStateMessage;
import common.states.LobbyEntryState;
import common.states.LobbyState;
import networking.GameClient;
import views.MainFrame;

import java.util.ArrayList;

public class Lobby {

    public LobbyState state;

    public Lobby(LobbyState state) {
        this.state = state;
    }

    public Lobby() {
        this.state = new LobbyState();
        this.state.players = new ArrayList<LobbyEntryState>();
    }

    public void onLobbyStateMessage(LobbyStateMessage message, Connection connection, GameClient client) {
        this.state = message.lobby;
        client.log("Lobby state updated!");
        for (LobbyEntryState lobbyEntryState : this.state.players) {
            client.log("  - " + lobbyEntryState.name);
        }
        MainFrame.instance.lobbyPanel.refresh();
    }
}