package com.tabergames.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import net.jaxonbrown.guardianBeam.beam.Beam;

public class shoot {
	
	private Player player;
	private double end= +5;
	
 public shoot() {
	 
	 Beam beam = new Beam(player.getEyeLocation(), player.);
	 beam.start();
	 beam.setStartingPosition(player.getEyeLocation());
 }
}
