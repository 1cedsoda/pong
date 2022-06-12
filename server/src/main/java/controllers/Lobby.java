package controllers;

import common.messages.PlayerUpdate;
import common.models.LobbyModel;
import common.models.PlayerModel;

import java.util.ArrayList;
import java.util.List;

public class Lobby extends LobbyModel<Game, Ball, Racket, Player> {
    public Lobby(List<Player> players) {
        super(players);
    }

    public Lobby() {
        super(new ArrayList<Player>());
    }

    public void onPlayerMessage(PlayerUpdate message) {

    }
}
