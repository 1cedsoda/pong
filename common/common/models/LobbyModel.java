package common.models;

import java.util.ArrayList;
import java.util.List;

public class LobbyModel<
        GAME extends GameModel<GAME, BALL, RACKET, PLAYER>,
        BALL extends BallModel<GAME, BALL, RACKET, PLAYER>,
        RACKET extends RacketModel<GAME, BALL, RACKET, PLAYER>,
        PLAYER extends PlayerModel<GAME, BALL, RACKET, PLAYER>> {
    public List<PLAYER> players;
    public LobbyModel(List<PLAYER> players) {
        this.players = players;
    }
}
