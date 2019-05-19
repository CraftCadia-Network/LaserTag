package me.lukeben.lasertag.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Arena {

    private String name;
    private Location spawnPoint1;
    private Location spawnPoint2;
    private List<UUID> players;

    public Arena(String name, Location spawnPoint1, Location spawnPoint2, List<UUID> players){
        this.name = name;
        this.spawnPoint1 = spawnPoint1;
        this.spawnPoint2 = spawnPoint2;
        this.players = players;
    }

    public HashMap<UUID, Integer> kills = new HashMap<>();
    public HashMap<UUID, Integer> deaths = new HashMap<>();

    public void clearPlayer(Player p){
        if(kills.containsKey(p.getUniqueId())){
            kills.remove(p.getUniqueId());
        }
        if(deaths.containsKey(p.getUniqueId())){
            deaths.remove(p.getUniqueId());
        }
        this.players.remove(p.getUniqueId());
        p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        p.getInventory().clear();
        p.getInventory().setChestplate(new ItemStack(Material.AIR));

    }

    public String getName(){
        return name;
    }

    public Location getSpawnPoint1(){
        return spawnPoint1;
    }

    public void setSpawnPoint1(Location spawnPoint1){
        this.spawnPoint1 = spawnPoint1;
    }

    public Location getSpawnPoint2(){
        return spawnPoint2;
    }

    public void setSpawnPoint2(Location spawnPoint2){
        this.spawnPoint2 = spawnPoint2;
    }

    public List<UUID> getPlayers(){
        return players;
    }

    public void addPlayer(UUID player){
        players.add(player);
    }

    public void removePlayer(UUID player){
        players.remove(player);
    }

    public void addKill(Player p){
        if(kills.containsKey(p.getUniqueId())){
            kills.put(p.getUniqueId(), kills.get(p.getUniqueId()) + 1);
        } else {
            kills.put(p.getUniqueId(), 1);
        }
    }

    public Integer getDeaths(Player p){
        if(deaths.containsKey(p.getUniqueId())){
            return deaths.get(p.getUniqueId());
        } else {
            return 0;
        }
    }

    public void addDeath(Player p){
        if(deaths.containsKey(p.getUniqueId())){
            deaths.put(p.getUniqueId(), deaths.get(p.getUniqueId()) + 1);
        } else {
            deaths.put(p.getUniqueId(), 1);
        }
    }

    public Integer getKills(Player p){
        if(kills.containsKey(p.getUniqueId())){
            return kills.get(p.getUniqueId());
        } else {
            return 0;
        }
    }

}
