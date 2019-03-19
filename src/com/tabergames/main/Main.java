package com.tabergames.main;

import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.tabergames.commands.SetRank;
import com.tabergames.commands.TeamChoose;
import com.tabergames.util.FileHandler;

public class Main extends JavaPlugin{

	com.tabergames.events.DoubleJump DoubleJump = new com.tabergames.events.DoubleJump();
	com.tabergames.events.PlayerJoin PlayerJoin = new com.tabergames.events.PlayerJoin();
	public static Main instance;
	public static ItemStack laser = new ItemStack(Material.DIAMOND_AXE);
	public static ItemMeta lmeta = laser.getItemMeta();
	FileHandler FileHandler = new FileHandler();
	
	@Override
	public void onEnable() {
		
		instance = this;
		
		this.getServer().getPluginManager().registerEvents(DoubleJump, this);
		this.getCommand("team").setExecutor((CommandExecutor)new TeamChoose());
	}

	@Override
	public void onDisable() {
		
		instance = null;
		
	}
	
}
