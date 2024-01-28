package myclass.plugin.combat.manager.Database;

import java.util.HashMap;
import java.util.UUID;

public class PlayerDataMap {

    private static PlayerDataMap instance;

    private HashMap<UUID, PlayerData> playerDataMap;

    private PlayerDataMap() {
        playerDataMap = new HashMap<>();
    }

    public static PlayerDataMap getInstance() {
        if (instance == null) {
            instance = new PlayerDataMap();
        }

        return instance;
    }

    public HashMap<UUID, PlayerData> getPlayerDataMap() {
        return playerDataMap;
    }
}