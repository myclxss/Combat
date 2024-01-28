package myclass.plugin.combat;

import myclass.plugin.combat.command.CombatCommand;
import myclass.plugin.combat.command.StateCommand;
import myclass.plugin.combat.listener.UserListener;
import myclass.plugin.combat.manager.Database.DatabaseConection;
import myclass.plugin.combat.manager.Scoreboard.ScoreboardManager;
import myclass.plugin.combat.manager.States.StateManager;
import myclass.plugin.combat.utils.Files;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

public class API {

    private static API instance;
    private final Combat main;
    private final Files lang;
    private final Files settings;

    private DatabaseConection databaseConection;

    public API(final Combat plugin) {

        instance = this;
        main = plugin;

        lang = new Files(plugin, "lang");
        settings = new Files(plugin, "settings");

        databaseConection = new DatabaseConection();
        databaseConection.conectDB();

        loadCommand();
        loadListener();
        loadScoreboardTask();
    }

    public void close(){
        databaseConection.closeDB();
    }

    public void loadCommand() {

        main.getCommand("combat").setExecutor(new CombatCommand());
        main.getCommand("state").setExecutor(new StateCommand());

    }

    public void loadListener() {

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new UserListener(), main);

    }

    public void loadScoreboardTask() {
        this.getMain().getServer().getScheduler().runTaskTimerAsynchronously(this.getMain(), () -> {
            for (Player player : this.getMain().getServer().getOnlinePlayers()) {
                String state = StateManager.getPlayerState(player);
                if (state.equals(StateManager.STATE_LOBBY)) {
                    ScoreboardManager.updateLobby(player);
                } else if (state.equals(StateManager.STATE_ARENA)) {
                    ScoreboardManager.updateArena(player);
                }

            }
        }, 0, 20);
    }

    public Files getLang() {
        return lang;
    }

    public Files getSettings() {
        return settings;
    }

    public Combat getMain() {
        return main;
    }

    public static API getInstance() {
        return instance;
    }

    public DatabaseConection getDatabaseConection() {
        return databaseConection;
    }
}
