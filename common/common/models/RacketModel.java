package common.models;

import common.geometry.Point;

public class RacketModel<
        GAME extends GameModel<GAME, BALL, RACKET, PLAYER>,
        BALL extends BallModel<GAME, BALL, RACKET, PLAYER>,
        RACKET extends RacketModel<GAME, BALL, RACKET, PLAYER>,
        PLAYER extends PlayerModel<GAME, BALL, RACKET, PLAYER>> {
    public int score;
    public double size;
    public Point position;
    public PLAYER player;

    protected RacketModel(int score, double size, Point position, PLAYER player) {
        this.score = score;
        this.size = size;
        this.position = position;
        this.player = player;
    }

    public RacketModel(PLAYER player, double x) {
        this.score = 0;
        this.size = 0.0;
        this.position = new Point(x, 0);
        this.player = player;
    }
}
