package common.models;

public class GameModel {

    public String gameId;
    public RacketModel leftPlayerState;
    public RacketModel rightPlayerState;
    public BallModel ballState;

    public GameModel(RacketModel leftPlayerState, RacketModel rightPlayerState, BallModel ballState) {
        this.leftPlayerState = leftPlayerState;
        this.rightPlayerState = rightPlayerState;
        this.ballState = ballState;
    }
}
