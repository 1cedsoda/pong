package common.states;

import common.enums.PlayerActivity;

public class LobbyEntryState {
    public String name;
    public PlayerActivity activity = PlayerActivity.IDLE;
    public String gameId;
}
