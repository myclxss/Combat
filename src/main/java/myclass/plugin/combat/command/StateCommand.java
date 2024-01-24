package myclass.plugin.combat.command;

import myclass.plugin.combat.manager.States.StateManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Este comando solo puede ser ejecutado por un jugador.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 2) {
            player.sendMessage("Uso correcto: /state <set/get> <nick>");
            return true;
        }

        String targetName = args[1];
        Player target = Bukkit.getPlayerExact(targetName);

        if (target == null || !target.isOnline()) {
            player.sendMessage("El jugador no está en línea o no existe.");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "set":
                if (args.length > 2) {
                    switch (args[2].toLowerCase()) {
                        case "lobby":
                            // Aquí deberías tener lógica para establecer el estado del jugador a lobby
                            target.sendMessage("Tu estado se estableció a lobby.");
                            StateManager.setPlayerState(target, StateManager.STATE_LOBBY);
                            break;
                        case "arena":
                            // Aquí deberías tener lógica para establecer el estado del jugador a arena
                            target.sendMessage("Tu estado se estableció en arena.");
                            StateManager.setPlayerState(target, StateManager.STATE_ARENA);
                            break;
                        default:
                            player.sendMessage("Uso correcto: /state set <nick> lobby/arena");
                            break;
                    }
                } else {
                    player.sendMessage("Uso correcto: /state set <nick> lobby/arena");
                }
                break;
            case "get":
                // Aquí deberías tener lógica para obtener el estado del jugador
                target.sendMessage("El estado de " + target.getName() + " es " + StateManager.getPlayerState(player));
                break;
            default:
                player.sendMessage("Uso correcto: /state <set/get> <nick>");
                break;
        }
        return true;
    }
}
