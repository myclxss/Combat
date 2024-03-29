package myclass.plugin.combat.command;

import myclass.plugin.combat.manager.Database.PlayerDataMap;
import myclass.plugin.combat.utils.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CombatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(Color.set("&cby myclass"));
            return true;
        }
        if (args[0].equalsIgnoreCase("muertes")){

            player.sendMessage("Tienes " + PlayerDataMap.getInstance().getPlayerDataMap().get(player.getUniqueId()).getDeaths() + " muertes.");
            player.sendMessage("Tienes " + PlayerDataMap.getInstance().getPlayerDataMap().get(player.getUniqueId()).getKills() + " kills");
            return true;
        }
        player.sendMessage(Color.set("&cAun no existe un sub argumento."));
        return true;
    }
}
