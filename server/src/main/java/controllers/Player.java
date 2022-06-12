package controllers;

import common.models.PlayerModel;

public class Player extends PlayerModel<Game, Ball, Racket, Player> {
    public Player(String name) {
        super(name);
    }
}
