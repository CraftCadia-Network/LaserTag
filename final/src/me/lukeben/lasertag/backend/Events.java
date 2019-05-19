package me.lukeben.lasertag.backend;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Particle;
import me.lukeben.lasertag.backend.laser.EntityData;
import me.lukeben.lasertag.backend.laser.LaserGun;
import me.lukeben.lasertag.utils.Arena;
import me.lukeben.lasertag.utils.Files;
import me.lukeben.lasertag.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.HashMap;

public class Events implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getSlotType().equals(InventoryType.SlotType.ARMOR)){
            if(e.getCurrentItem().equals(Utils.getBlueChest()) || e.getCurrentItem().equals(Utils.getRedChest())){
                e.setCancelled(true);
            }
        }

    }

    public HashMap<Player, String> team = new HashMap<>();

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        Player player2 = event.getEntity().getKiller();
        for(Arena arena : ArenaManager.getInstance().arenaList) {
            if (arena.getPlayers().contains(player.getUniqueId()) && arena.getPlayers().contains(player2.getUniqueId())) {
                team.put(player, Utils.getTeam(player));
                arena.addDeath(player);
                arena.addKill(player2);
                player.sendMessage(Utils.color(Files.getInstance().getLazertag().getString("lang.player-kill"))
                        .replace("[prefix]", Utils.getPrefix()).replace("[killer]", player2.getName()).replace("[player]", player.getName()));

            }
        }
    }

    //src edit
    @EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        Player player = e.getPlayer();
        Utils.equipArmor(player, Utils.getTeam(player));
        Utils.addLaserGun(player);
        team.remove(player);
        Arena arena = ArenaManager.getInstance().getArenaPlayer(player);
        if(arena != null){
            if(Utils.getTeam(player).equalsIgnoreCase(Utils.stripColor("&cRED"))){
                player.teleport(arena.getSpawnPoint1());
            } else {
                player.teleport(arena.getSpawnPoint2());
            }
        }
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Snowball) { //check if the damager is a snowball
            if(event.getEntity() instanceof Player && event.getDamager() instanceof Player){
                if(Utils.getTeam((Player) event.getDamager()).equalsIgnoreCase(Utils.getTeam((Player) event.getEntity()))){
                    event.setCancelled(true);
                    return;
                }
            }
            if (LaserGun.shotprojectiledata.containsKey(event.getDamager())) { //check if the snowball is one that is supposed to be doing a different damage
                EntityData eventdata = LaserGun.shotprojectiledata.get(event.getDamager()); //get the data we stored about the projectile
                if (event.getEntity().getLocation().distance(eventdata.getFiredFrom())<=eventdata.getRange()) { //check to see if the event is taking place outside of the range
                    event.setDamage(eventdata.getDamage()); //set the custom damage to the value stored earlier
                    LaserGun.shotprojectiledata.remove(event.getDamager()); //we can remove the projectile now so that the hashmap won't have unnecessary data in it.
                }
            }
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if(e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR){
            if(Utils.isLaserGun(player.getItemInHand())){
                if(ArenaManager.getInstance().getArenaPlayer(player) != null){
                    LaserGun.shoot(player);
                }
            }
        }
    }

}
