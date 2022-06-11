package common.models;

import java.util.List;

public class LobbyModel {
    public List<PlayerModel> players;
    public LobbyModel(List<PlayerModel> players) {
        this.players = players;
    }
}
