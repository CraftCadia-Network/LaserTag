package com.tabergames.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tabergames.util.PlayerHandler;

public class SetRank implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

if(label.equalsIgnoreCase("rank")) {
	
	Player player = (Player) sender;
	if(player.hasPermission("tabercore.general")) {
		int rank = PlayerHandler.getRank(player);	

			if(args.length == 3) {
				
				String targetName = args[1];
				Player target = Bukkit.getPlayer(targetName);
				if(target.isOnline()) {
					int rankValue = 0;
					String rankName = args[2].toLowerCase();
					if(rankName.equals("owner")) {
						
						rankValue = PlayerHandler.Rank_OWNER;
					}else if(rankName.equals("admin")) {
						
						rankValue = PlayerHandler.Rank_ADMIN;
					}else if(rankName.equals("developer") || rankName.equals("dev")) {
						
						rankValue = PlayerHandler.Rank_DEVELOPER;
					}else if(rankName.equals("member") || rankName.equals("default")) {
						
						rankValue = PlayerHandler.Rank_DEFAULT;
					}else {
						
						rankValue = -1;
						
					}
					
					if(rankValue >= 0) {
						
						if(rankValue < rank) {
							
							if(PlayerHandler.getRank(target) < rank) {
								if(PlayerHandler.setRank(target, rankValue)) {
									player.sendMessage(ChatColor.GREEN + "Successfully set " + target.getName() + "'s rank to " + rankName);
									target.sendMessage(ChatColor.GREEN + "Your rank has been changed to " + rankName);
									
								}

							}
							
						}else {
							player.sendMessage(ChatColor.RED + "Error: You can't use ranks bigger than or equal to yours!");
							
							
						}
						
					}else {
						
						player.sendMessage(ChatColor.RED + rankName + " is not a rank!");
						
					}
					
				}else {
					
					player.sendMessage(ChatColor.RED + "Error: " + targetName + "is not online!");
				}
				
				
			}else {
				
				player.sendMessage(ChatColor.RED + "Usage: /rank <name> <rank>");
				
			}
			
		
		
	}
	
	
}
		
		return false;
	}

	
	
}
