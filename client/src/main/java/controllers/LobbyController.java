package controllers;

import common.models.LobbyModel;
import common.models.PlayerModel;

import java.util.List;

public class LobbyController extends common.controllers.LobbyController{
    public LobbyController(List<PlayerModel> players) {
        super(players);
    }

    public LobbyController(LobbyModel lobbyState) {
        super(lobbyState);
    }
}
