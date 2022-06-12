package controllers;

import common.geometry.Point;
import common.models.RacketModel;

public class Racket extends RacketModel<Game, Ball, Racket, Player> {
    protected Racket(int score, double size, Point position, Player player) {
        super(score, size, position, player);
    }

    public Racket(Player player, double x) {
        super(player, x);
    }
}
