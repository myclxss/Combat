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

    }

    @Override
    public void onDisable() {

        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Combat apagado con exito");

    }
}
