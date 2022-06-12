package common.models;

public class PlayerModel<
        GAME extends GameModel<GAME, BALL, RACKET, PLAYER>,
        BALL extends BallModel<GAME, BALL, RACKET, PLAYER>,
        RACKET extends RacketModel<GAME, BALL, RACKET, PLAYER>,
        PLAYER extends PlayerModel<GAME, BALL, RACKET, PLAYER>> {
    public String name;
    public PlayerModel(String name){
        this.name = name;
    }
}
