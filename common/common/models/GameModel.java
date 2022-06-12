package common.models;

public class GameModel<
        GAME extends GameModel<GAME, BALL, RACKET, PLAYER>,
        BALL extends BallModel<GAME, BALL, RACKET, PLAYER>,
        RACKET extends RacketModel<GAME, BALL, RACKET, PLAYER>,
        PLAYER extends PlayerModel<GAME, BALL, RACKET, PLAYER>> {

    public String gameId;
    public RACKET leftRacket;
    public RACKET rightRacket;
    public BALL ball;

    public GameModel(RACKET leftRacket, RACKET rightRacket, BALL ball) {
        this.leftRacket = leftRacket;
        this.rightRacket = rightRacket;
        this.ball = ball;
    }
}
