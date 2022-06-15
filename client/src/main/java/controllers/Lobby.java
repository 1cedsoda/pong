package controllers;

import com.esotericsoftware.kryonet.Connection;
import common.messages.LobbyStateMessage;
import common.states.LobbyEntryState;
import common.states.LobbyState;
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

    public void onLobbyStateMessage(LobbyStateMessage message, Connection connection) {
        this.state = message.lobby;
        System.out.println("Lobby state updated");
        for (LobbyEntryState lobbyEntryState : this.state.players) {
            System.out.println("  Player: "+lobbyEntryState.name);
        }
        MainFrame.instance.lobbyPanel.refresh();
    }
}