package com.tabergames.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GunRightClick implements Listener{
    //woOf
   
	@SuppressWarnings("unlikely-arg-type")
	public void rightClick(ItemStack istack, Player player) {
		
		Inventory i = player.getInventory();
		for(ItemStack iven : i.getContents()) {
			
			if(iven.getType().equals(istack)) {
				
				
				
			}
			
		}
		
	}


} 