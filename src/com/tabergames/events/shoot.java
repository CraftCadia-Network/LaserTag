package com.tabergames.events;

import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import net.jaxonbrown.guardianBeam.beam.Beam;

public class shoot {
	
	
	
@EventHandler
 public void shoot(PlayerInteractEvent e) {
	 
	 Player p = e.getPlayer();
	 Action a = e.getAction();
	 
	Location target = p.getTargetBlock((Set) null, 200).getLocation().clone().add(0, 0.3, 0);
	 
	 if((a == Action.PHYSICAL) || (e.getItem() == null) || (e.getItem().getType() == Material.DIAMOND_AXE)) return;
	 Beam beam = new Beam(p.getEyeLocation(), target);
	 beam.setStartingPosition(p.getEyeLocation());
	 beam.start();
	 
	
 }
}
