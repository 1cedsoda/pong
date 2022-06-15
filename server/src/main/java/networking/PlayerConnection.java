package networking;

import com.esotericsoftware.kryonet.Connection;

public class PlayerConnection extends Connection {
    public String name;

    public void log(String log) {
        System.out.println("[" + getRemoteAddressTCP() + "] " + log);
    }

}
