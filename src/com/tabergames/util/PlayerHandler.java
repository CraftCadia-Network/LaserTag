package com.tabergames.util;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class PlayerHandler {

	public static int Rank_OWNER = 100;
	public static int Rank_DEVELOPER = 90;
	public static int Rank_ADMIN = 80;
	public static int Rank_DEFAULT = 0;
	
	public static void SetupPlayer(Player p) {
		
		File f = new File("plugins/LaserTag/PlayerData/" + p.getUniqueId() + ".yml");
		if(!f.exists()) {
			
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		yml.addDefault("Name", p.getName());
		yml.addDefault("Rank", Rank_DEFAULT);
		yml.options().copyDefaults(true);
		try {
			yml.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean setRank(Player p, int rank) {
		
		File f = new File("plugins/CadiaHQRanks/PlayerData/" + p.getUniqueId() + ".yml");
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		yml.set("Rank", rank);
		try {
			yml.save(f);
		} catch (IOException e) {

			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	public static int getRank(Player p) {
		
		File f = new File("plugins/CadiaHQRanks/PlayerData/" + p.getUniqueId() + ".yml");
		YamlConfiguration yml = YamlConfiguration.loadConfiguration(f);
		return yml.getInt("Rank");
	}
	
	
	public static String getRankPrefix(int Rank) {
		
		if(Rank == Rank_OWNER) {
			
			return ChatColor.LIGHT_PURPLE.toString() + "[" + ChatColor.AQUA.toString() + "Owner" + ChatColor.LIGHT_PURPLE.toString() + "] ";
		}else if(Rank == Rank_DEVELOPER) {
			
			return ChatColor.WHITE.toString() + "[" + ChatColor.RED.toString() + "Developer" + ChatColor.WHITE.toString() + "] ";
			
		}else if(Rank == Rank_ADMIN) {
			
			return ChatColor.WHITE.toString() + "[" + ChatColor.GOLD.toString() + "Administrator" + ChatColor.WHITE.toString() + "] ";
			
		}else {
			
			return ChatColor.WHITE.toString() + "[" + ChatColor.GRAY.toString() + "Member" + ChatColor.WHITE.toString() + "] ";
			
		}
		
	}
	
}
