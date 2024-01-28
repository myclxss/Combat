package myclass.plugin.combat.manager.Database;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
public class DatabaseMethods {

    public static void saveAllPlayerData() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            PlayerData playerData = PlayerDataMap.getInstance().getPlayerDataMap().get(player.getUniqueId());
            playerData.save();
        }
    }
}
