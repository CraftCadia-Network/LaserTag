package me.lukeben.lasertag;

import me.lukeben.lasertag.backend.Events;
import me.lukeben.lasertag.backend.Scoreboard;
import me.lukeben.lasertag.commands.LazertagCommand;
import me.lukeben.lasertag.utils.Files;
import me.lukeben.lasertag.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public final class LaserTag extends JavaPlugin {



    public static Files files = Files.getInstance();

    @Override
    public void onEnable() {
        files.setup(this);
        Bukkit.getConsoleSender().sendMessage(Utils.color( "&aEnabling lasertag ..."));
        getCommand("lasertag").setExecutor(new LazertagCommand());
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        Bukkit.getConsoleSender().sendMessage(Utils.color("&aLasertag has been enabled."));
        startRunnable();
    }

    private void startRunnable() {
        new BukkitRunnable() {

            @Override
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()) {
                    try {
                        Scoreboard.updateScoreboard(player);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }.runTaskTimer(this, 1, 20);
    }
}
