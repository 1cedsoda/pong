package common.controllers;

import common.messages.PlayerUpdate;
import common.models.LobbyModel;
import common.models.PlayerModel;

import java.util.List;

public class LobbyController extends LobbyModel {

    public LobbyController(List<PlayerModel> players) {
        super(players);
    }

    public LobbyController(LobbyModel lobbyState) {
        super(lobbyState.players);
    }

    public void onPlayerUpdate(PlayerUpdate message) {
    }
}
