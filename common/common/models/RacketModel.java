package common.models;

public class RacketModel {
    int score;
    double size;
    double y;
    PlayerModel player;

    RacketModel(int score, double size, double y, PlayerModel player) {
        this.score = score;
        this.size = size;
        this.y = y;
        this.player = player;
    }
}
