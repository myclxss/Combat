package myclass.plugin.combat;

import myclass.plugin.combat.manager.Scoreboard.ScoreboardManager;
import myclass.plugin.combat.manager.States.StateManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Combat extends JavaPlugin {

    @Override
    public void onEnable() {

        new API(this);

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Combat encendido con exito");

        API.getInstance().getLang().save();

        getServer().getScheduler().runTaskTimerAsynchronously(this, () -> {
            for (Player player : getServer().getOnlinePlayers()) {
                String state = StateManager.getPlayerState(player);
                if (state.equals(StateManager.STATE_LOBBY)) {
                    ScoreboardManager.updateLobby(player);
                } else if (state.equals(StateManager.STATE_ARENA)) {
                    ScoreboardManager.updateArena(player);
                }

            }
        }, 0, 20);

    }

    @Override
    public void onDisable() {

        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Combat apagado con exito");

    }
}
