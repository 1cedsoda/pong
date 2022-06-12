package controllers;

import common.models.GameModel;
import common.models.LobbyModel;
import common.models.PlayerModel;

import java.util.List;

public class Lobby extends LobbyModel<Game, Ball, Racket, Player> {
    public Lobby(List<Player> players) {
        super(players);
    }
}
