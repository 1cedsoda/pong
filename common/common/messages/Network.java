package common.messages;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import common.states.*;

import java.util.ArrayList;

public class Network {
    // This registers objects that are going to be sent over the network.
    static public void register (EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();

        // Messages
        kryo.register(GameCreateMessage.class);
        kryo.register(GameJoinMessage.class);
        kryo.register(GameMoveMessage.class);
        kryo.register(GameStateMessage.class);
        kryo.register(LobbyJoinMessage.class);
        kryo.register(LobbyStateMessage.class);

        // States
        kryo.register(BallState.class);
        kryo.register(GameState.class);
        kryo.register(LobbyEntryState.class);
        kryo.register(LobbyState.class);
        kryo.register(RacketState.class);

        // Other
        kryo.register(ArrayList.class);

    }
}
