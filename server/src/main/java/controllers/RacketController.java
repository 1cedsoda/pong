package controllers;

import common.geometry.Line;
import common.geometry.Point;
import common.models.PlayerModel;
import common.models.RacketModel;

public class RacketController extends common.controllers.RacketController{

    public RacketController(int score, double size, double y, PlayerModel player, GameController gameState) {
        super(score, size, y, player, gameState);
    }

    public RacketController(RacketModel racketState, GameController gameState) {
        super(racketState, gameState);
    }

    public Line getLine(double x) {
        return new Line(new Point(x, y+size/2), new Point(x, y-size/2));
    }
}
