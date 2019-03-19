package com.tabergames.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import net.jaxonbrown.guardianBeam.beam.Beam;

public class shoot {
	
public shoot(){
		
}
	
	
@EventHandler
 public void shootBeam(PlayerInteractEvent e) {
	 
	 Player p = e.getPlayer();
	 Action a = e.getAction();
	 
	Location target = p.getLocation().clone().add(5, 0.3, 5);
	 
	 if((a == Action.RIGHT_CLICK_AIR) || (e.getItem() == null)) {
		 Beam beam = new Beam(p.getEyeLocation(), target);
		 beam.setStartingPosition(p.getEyeLocation());
		 beam.start();
	 }	
 }
}
