package controllers;

import common.geometry.Line;
import common.geometry.Point;
import common.models.RacketModel;

public class Racket extends RacketModel<Game, Ball, Racket, Player> {

    public Racket(int score, double size, Point position, Player player) {
        super(score, size, position, player);
    }

    public Racket(Player player, double x) {
        super(player, x);
    }

    public void incrementScore() {
        this.score++;
    }

    public Line getLine() {
        return new Line(new Point(position.x, position.y + size / 2), new Point(position.x, position.y - size / 2));
    }
}
