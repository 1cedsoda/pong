package common.messages;

import common.enums.MoveDirection;

public class MoveSignal extends Message {
    public String gameId;
    public MoveDirection moveDirection;
}
