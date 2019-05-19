package me.lukeben.lasertag.backend;

import me.lukeben.lasertag.utils.Files;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.List;

public class Scoreboard implements Listener {

    private static int scoreboardLines() {
        List<String> linesConfig = Files.getInstance().getLazertag().getStringList("arena.scoreboard.lines");
        int lines = linesConfig.size();
        return lines;
    }

    public static void createScoreboard(Player player) throws Exception {
        ScoreHelper helper = ScoreHelper.createScore(player);

        helper.setTitle(Files.getInstance().getLazertag().getString("arena.scoreboard.title"));

        int lines = scoreboardLines();
        for (String msg : Files.getInstance().getLazertag().getStringList("arena.scoreboard.lines")) {
            helper.setSlot(lines, msg);
            lines--;
        }
    }

    public static void updateScoreboard(Player player) throws Exception {
        int lines = scoreboardLines();
        if (ScoreHelper.hasScore(player)){
            ScoreHelper helper = ScoreHelper.getByPlayer(player);
            helper.setTitle(Files.getInstance().getLazertag().getString("arena.scoreboard.title"));
            for (String msg : Files.getInstance().getLazertag().getStringList("arena.scoreboard.lines"))
            {
                helper.setSlot(lines, msg);
                lines--;
            }
        }
    }

}
