package com.tabergames.main;

import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.tabergames.commands.TeamChoose;

public class Main extends JavaPlugin{

	public static Main instance;
	public static ItemStack laser = new ItemStack(Material.DIAMOND_AXE);
	public static ItemMeta lmeta = laser.getItemMeta();
	
	@Override
	public void onEnable() {
		
		instance = this;
		
		this.getCommand("team").setExecutor((CommandExecutor)new TeamChoose());
		
	}

	@Override
	public void onDisable() {
		
		instance = null;
		
	}
	
}
