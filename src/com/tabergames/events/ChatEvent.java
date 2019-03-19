package com.tabergames.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.tabergames.util.PlayerHandler;

public class ChatEvent implements Listener{

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		
		e.setCancelled(true);
		Player p = e.getPlayer();
		String name = p.getName();
		String prefix = PlayerHandler.getRankPrefix(PlayerHandler.getRank(p));
		String message = e.getMessage();
		String OWNER_CHATPRE = ChatColor.LIGHT_PURPLE + " >" + ChatColor.GRAY + "> " + ChatColor.LIGHT_PURPLE;
		String DEV_CHATPRE = ChatColor.RED + " >" + ChatColor.GRAY + "> " + ChatColor.RED;
		String ADM_CHATPRE = ChatColor.GOLD + " >" + ChatColor.GRAY + "> " + ChatColor.GOLD;
		String DEF_CHATPRE = ChatColor.WHITE  + " >" + ChatColor.GRAY + "> " + ChatColor.GRAY;
		if(PlayerHandler.getRank(p) == PlayerHandler.Rank_OWNER) {
			
			Bukkit.broadcastMessage(prefix + name + OWNER_CHATPRE + message);
			
		}else if(PlayerHandler.getRank(p) == PlayerHandler.Rank_DEVELOPER) {
			
			Bukkit.broadcastMessage(prefix + name + DEV_CHATPRE + message);
			
		}else if(PlayerHandler.getRank(p) == PlayerHandler.Rank_ADMIN) {
			
			Bukkit.broadcastMessage(prefix + name + ADM_CHATPRE + message);
			
		}else if(PlayerHandler.getRank(p) == PlayerHandler.Rank_DEFAULT) {
			
			Bukkit.broadcastMessage(prefix + name + DEF_CHATPRE + message);
			
		}
	
}
}
