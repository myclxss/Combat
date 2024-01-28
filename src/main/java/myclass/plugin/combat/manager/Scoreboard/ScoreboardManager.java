package myclass.plugin.combat.manager.Scoreboard;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import fr.mrmicky.fastboard.FastBoard;
import me.clip.placeholderapi.PlaceholderAPI;
import myclass.plugin.combat.API;
import myclass.plugin.combat.manager.Database.PlayerDataMap;
import myclass.plugin.combat.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class ScoreboardManager {

    private static final Map<UUID, FastBoard> boards = new HashMap<>();

    public static void createStatic(Player player) {
        FastBoard board = new FastBoard(Bukkit.getPlayer(player.getUniqueId())) {

            @Override
            public boolean hasLinesMaxLength() {
                return Via.getAPI().getPlayerVersion(getPlayer()) < ProtocolVersion.v1_13.getVersion();
            }
        };

        String staticTitle = API.getInstance().getLang().getString("SCOREBOARD.LOADING.TITLE", true);
        List<String> staticLines = Color.set(API.getInstance().getLang().getStringList("SCOREBOARD.LOADING.LINES"));

        board.updateTitle(PlaceholderAPI.setPlaceholders(player, staticTitle));
        board.updateLines(PlaceholderAPI.setPlaceholders(player, staticLines));

        boards.put(player.getUniqueId(), board);
    }

    public static void updateLobby(Player player) {

        FastBoard board = boards.get(player.getUniqueId());

        if (board == null) return;

        String lobbyTitle = API.getInstance().getLang().getString("SCOREBOARD.LOBBY.TITLE", true);
        List<String> lobbyLines = Color.set(API.getInstance().getLang().getStringList("SCOREBOARD.LOBBY.LINES"));

        board.updateTitle(PlaceholderAPI.setPlaceholders(player, lobbyTitle));
        board.updateLines(PlaceholderAPI.setPlaceholders(player, lobbyLines));
    }

    public static void updateArena(Player player) {

        FastBoard board = boards.get(player.getUniqueId());

        if (board == null) return;

        String arenaTitle = API.getInstance().getLang().getString("SCOREBOARD.ARENA.TITLE", true);
        List<String> arenaLines = new ArrayList<>(Color.set(API.getInstance().getLang().getStringList("SCOREBOARD.ARENA.LINES")));

        String deathCount = String.valueOf(PlayerDataMap.getInstance().getPlayerDataMap().get(player.getUniqueId()).getDeaths());
        arenaLines.replaceAll(line -> line.replaceAll("%deaths%", deathCount));

        board.updateTitle(PlaceholderAPI.setPlaceholders(player, arenaTitle));
        board.updateLines(PlaceholderAPI.setPlaceholders(player, arenaLines));
    }

    public static void removeScoreboard(Player player) {
        FastBoard board = boards.get(player.getUniqueId());

        if (board == null) return;

        board.delete();
        boards.remove(player.getUniqueId());
    }
}