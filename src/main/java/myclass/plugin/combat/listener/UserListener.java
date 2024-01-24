package myclass.plugin.combat.listener;

import myclass.plugin.combat.manager.Scoreboard.ScoreboardManager;
import myclass.plugin.combat.manager.States.StateManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class UserListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        StateManager.setPlayerState(player, StateManager.STATE_LOBBY);
        ScoreboardManager.createStatic(player);

        event.setJoinMessage(null);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();

        StateManager.removePlayerState(player);
        ScoreboardManager.removeScoreboard(player);

        event.setQuitMessage(null);
    }

}
