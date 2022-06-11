package common.models;

public class RacketModel {
    public int score;
    public double size;
    public double y;
    public PlayerModel player;

    protected RacketModel(int score, double size, double y, PlayerModel player) {
        this.score = score;
        this.size = size;
        this.y = y;
        this.player = player;
    }

    public RacketModel() {
        this.score = 0;
        this.size = 0.0;
        this.y = 0.0;
        this.player = new PlayerModel();
    }
}
