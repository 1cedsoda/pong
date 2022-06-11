package common.models;

public class GameModel {

    public String gameId;
    public RacketModel leftRacketState;
    public RacketModel rightRacketState;
    public BallModel ballState;

    public GameModel(RacketModel leftRacketState, RacketModel rightRacketState, BallModel ballState) {
        this.leftRacketState = leftRacketState;
        this.rightRacketState = rightRacketState;
        this.ballState = ballState;
    }
}
