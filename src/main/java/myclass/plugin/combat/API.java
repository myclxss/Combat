package myclass.plugin.combat;

import myclass.plugin.combat.command.CombatCommand;
import myclass.plugin.combat.command.StateCommand;
import myclass.plugin.combat.listener.UserListener;
import myclass.plugin.combat.utils.Files;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class API {

    private static API instance;
    private final Combat main;
    private final Files lang;

    public API(final Combat plugin) {

        instance = this;
        main = plugin;

        lang = new Files(plugin, "lang");

        loadCommand();
        loadListener();

    }

    public void loadCommand() {

        main.getCommand("combat").setExecutor(new CombatCommand());
        main.getCommand("state").setExecutor(new StateCommand());

    }

    public void loadListener() {

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new UserListener(), main);

    }

    public Files getLang() {
        return lang;
    }

    public Combat getMain() {
        return main;
    }

    public static API getInstance() {
        return instance;
    }

}
