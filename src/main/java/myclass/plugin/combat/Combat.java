package myclass.plugin.combat;

import myclass.plugin.combat.manager.Database.DatabaseConection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Combat extends JavaPlugin {

    @Override
    public void onEnable() {

        new API(this);

        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Combat encendido con exito");

    }

    @Override
    public void onDisable() {

        API.getInstance().close();

        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Combat apagado con exito");

    }
}
