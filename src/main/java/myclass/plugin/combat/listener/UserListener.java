package myclass.plugin.combat.listener;

import myclass.plugin.combat.API;
import myclass.plugin.combat.manager.Database.PlayerData;
import myclass.plugin.combat.manager.Database.PlayerDataMap;
import myclass.plugin.combat.manager.Scoreboard.ScoreboardManager;
import myclass.plugin.combat.manager.States.StateManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

public class UserListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        StateManager.setPlayerState(player, StateManager.STATE_LOBBY);
        ScoreboardManager.createStatic(player);

        Bukkit.getScheduler().runTaskAsynchronously(API.getInstance().getMain(), () -> {
            PlayerData playerData = new PlayerData(player.getUniqueId());
            playerData.setName(player.getName());
            playerData.load();

            PlayerDataMap.getInstance().getPlayerDataMap().put(player.getUniqueId(), playerData);
        });

        event.setJoinMessage(null);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        StateManager.removePlayerState(player);
        ScoreboardManager.removeScoreboard(player);

        Bukkit.getScheduler().runTaskAsynchronously(API.getInstance().getMain(), () -> {
            PlayerData playerData = PlayerDataMap.getInstance().getPlayerDataMap().get(player.getUniqueId());
            playerData.save();

            PlayerDataMap.getInstance().getPlayerDataMap().remove(player.getUniqueId());
        });

        event.setQuitMessage(null);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        String worlds = player.getWorld().getName();
        List<String> worldList = API.getInstance().getSettings().getStringList("PROTECTION-WORLDS");

        if (worldList.contains(worlds)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        String worlds = player.getWorld().getName();
        List<String> worldList = API.getInstance().getSettings().getStringList("PROTECTION-WORLDS");

        if (worldList.contains(worlds)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPvP(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            Player player = (Player) event.getDamager();

            String worlds = player.getWorld().getName();
            List<String> worldList = API.getInstance().getSettings().getStringList("PROTECTION-WORLDS");

            if (worldList.contains(worlds)) {
                event.setCancelled(true);
            }

        }
    }
    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        Player killer = event.getEntity().getKiller();

        if (killer != null){
            PlayerDataMap.getInstance().getPlayerDataMap().get(player.getUniqueId()).addKill();
        }

        PlayerDataMap.getInstance().getPlayerDataMap().get(player.getUniqueId()).addDeath();
    }
}
