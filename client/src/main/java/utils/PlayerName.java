package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

public class PlayerName {
    static String filename = ".winfpong";

    private static String getFilePath() {
        String home = System.getProperty("user.home");
        // if windows
        if (System.getProperty("os.name").contains("Windows")) {
            return home + "\\" + filename;
        } else {
            return home + "/" + filename;
        }
    }

    public static String getPlayerName() {
        String filePath = getFilePath();
        String playerName = PlayerName.getDefaultName();
        // read txt file if it exists
        if (Files.exists(Paths.get(filePath))) {
            try {
                // read file first line
                playerName = Files.lines(Paths.get(filePath)).findFirst().get();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return playerName;
    }

    public static void setPlayerName(String playerName) {
        String filePath = getFilePath();
        // write to txt file
        try {
            Files.write(Paths.get(filePath), Arrays.asList(playerName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getDefaultName() {
        return Arrays.stream(System
                        .getProperty("user.name")
                        .split("\\s+"))
                .map(t -> t.substring(0, 1).toUpperCase() + t.substring(1))
                .collect(Collectors.joining(" "));
    }
}
