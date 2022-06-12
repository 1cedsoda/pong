package common.models;

import common.geometry.Point;
import common.geometry.Vector;

public class BallModel<
        GAME extends GameModel<GAME, BALL, RACKET, PLAYER>,
        BALL extends BallModel<GAME, BALL, RACKET, PLAYER>,
        RACKET extends RacketModel<GAME, BALL, RACKET, PLAYER>,
        PLAYER extends PlayerModel<GAME, BALL, RACKET, PLAYER>> {
    public Point position;
    // percent per second -> Velocity
    public Vector velocity;

    protected BallModel(Point position, Vector velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public BallModel() {
        this.position = new Point(0, 0);
        this.velocity = new Vector(10, 0.5);
    }
}
