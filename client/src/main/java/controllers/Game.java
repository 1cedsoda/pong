package controllers;

import common.models.BallModel;
import common.models.GameModel;
import common.models.RacketModel;

public class Game extends GameModel<Game, Ball, Racket, Player>  {
    public Game(Racket leftRacket, Racket rightRacket, Ball ball) {
        super(leftRacket, rightRacket, ball);
    }
}
