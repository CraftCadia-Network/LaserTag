package me.lukeben.lasertag.commands;

import me.lukeben.lasertag.backend.ArenaManager;
import me.lukeben.lasertag.backend.ScoreHelper;
import me.lukeben.lasertag.utils.Arena;
import me.lukeben.lasertag.utils.Files;
import me.lukeben.lasertag.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LazertagCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){
            Bukkit.getConsoleSender().sendMessage(Utils.color(Utils.getPrefix() + "&cYou must be a player to use this command."));
            return false;
        }
        Player player = (Player) sender;
        if(!Utils.hasPermission(player, "use")){
            //The permission is lasertag.use
            player.sendMessage(Utils.color(Utils.getPrefix() + "&cYou do not have permission to use this command!"));
            return false;
        }

        if(args.length == 1){
            //Add an error message!
            if(args[0].equalsIgnoreCase("leave")){
                if(ArenaManager.getInstance().getArenaPlayer(player) != null){
                    Arena arena = ArenaManager.getInstance().getArenaPlayer(player);
                    arena.clearPlayer(player);
                    player.teleport(Utils.getLobby());
                    ScoreHelper.removeScore(player);
                    player.sendMessage(Utils.color(Files.getInstance().getLazertag().getString("lang.arena-leave")
                            .replace("[prefix]", Utils.getPrefix()).replace("[arena]", arena.getName())));
                }
            } else if(args[0].equalsIgnoreCase("reload")){
                Files.getInstance().reloadArenas();
                Files.getInstance().reloadLazertag();
                player.sendMessage(Utils.color(Files.getInstance().getLazertag().getString("lang.config-reloaded").replace("[prefix]", Utils.getPrefix())));
            }

            //add a check to make sure the error message doesn't loop multiple times!!!
        } else if(args.length == 2){
            if(args[0].equalsIgnoreCase("join")){
                if(ArenaManager.getInstance().isConfigArena(args[1])){
                    ArenaManager.getInstance().loadArena(args[1]);
                }
                if(ArenaManager.getInstance().isArena(args[1])){
                        ArenaManager.getInstance().queueGame(ArenaManager.getInstance().getArenaByName(args[1]), player);
                } else {
                    player.sendMessage(Utils.color(Files.getInstance().getLazertag().getString("lang.invalid-arena")
                            .replace("[prefix]", Utils.getPrefix()).replace("[entry]", args[1])));
                }
                return false;
            } else {
                player.sendMessage(Utils.color(Utils.getPrefix() + "&cInvalid format, please use: /lasertag <join|reload|leave> <arena>"));
            }
        }

        return false;
    }
}
