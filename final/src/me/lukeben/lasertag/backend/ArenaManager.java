package me.lukeben.lasertag.backend;


import me.lukeben.lasertag.LaserTag;
import me.lukeben.lasertag.utils.Arena;
import me.lukeben.lasertag.utils.Files;
import me.lukeben.lasertag.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class ArenaManager {

    private static ArenaManager manager;

    public static ArenaManager getInstance(){
        if(manager == null){
            manager = new ArenaManager();
        }
        return manager;
    }

    public ArrayList<Arena> arenaList = new ArrayList<>();

    public ArrayList getArenaList(){
        return arenaList;
    }

    public void createArena(String name, Location spawn1, Location spawn2){
        List<UUID> list = new ArrayList<>();
        Arena a = new Arena(name, spawn1, spawn2, list);
        arenaList.add(a);
        FileConfiguration arenas = Files.getInstance().getArenas();
        arenas.set("arenas." + a.getName() + ".spawn1.x", a.getSpawnPoint1().getX());
        arenas.set("arenas." + a.getName() + ".spawn1.y", a.getSpawnPoint1().getY());
        arenas.set("arenas." + a.getName() + ".spawn1.z", a.getSpawnPoint1().getZ());
        arenas.set("arenas." + a.getName() + ".spawn2.x", a.getSpawnPoint2().getX());
        arenas.set("arenas." + a.getName() + ".spawn2.y", a.getSpawnPoint2().getY());
        arenas.set("arenas." + a.getName() + ".spawn2.z", a.getSpawnPoint2().getZ());
        Files.getInstance().saveArenas();
        Files.getInstance().reloadArenas();
    }

    public void removeArena(String name){
        FileConfiguration arenas = Files.getInstance().getArenas();
        if(arenas.contains("arenas." + name)){
            arenas.set("arenas." + name + ".spawn1.x", null);
            arenas.set("arenas." + name + ".spawn1.y", null);
            arenas.set("arenas." + name + ".spawn1.z", null);
            arenas.set("arenas." + name + ".spawn2.x", null);
            arenas.set("arenas." + name + ".spawn2.y", null);
            arenas.set("arenas." + name + ".spawn2.z", null);
            if(arenaList.contains(name)){
                arenaList.remove(getArenaByName(name));
            }
            Files.getInstance().saveArenas();
            Files.getInstance().reloadArenas();

        }
    }

    public Arena getArenaByName(String s){
        loadArena(s);
        for(Arena a : arenaList){
            if(a.getName().equalsIgnoreCase(s)){
                return a;
            }
        }
        return null;
    }



    public void loadArena(String s){
        if(Files.getInstance().getArenas().contains("arenas." + s)){
            FileConfiguration arenas = Files.getInstance().getArenas();
            World world = Bukkit.getWorld(Files.getInstance().getLazertag().getString("arena.world"));
            Location spawn1 = new Location(world, arenas.getDouble("arenas." + s + ".spawn1.x"), arenas.getDouble("arenas." + s + ".spawn1.y"), arenas.getDouble("arenas." + s + ".spawn1.z"));
            Location spawn2 = new Location(world, arenas.getDouble("arenas." + s + ".spawn2.x"), arenas.getDouble("arenas." + s + ".spawn2.y"), arenas.getDouble("arenas." + s + ".spawn2.z"));
            List<UUID> list = new ArrayList<UUID>();
            Arena ar = new Arena(s, spawn1, spawn2, list);
            if(arenaList.contains(ar)){
                arenaList.remove(ar);
            }
            arenaList.add(ar);
        }
    }

    public void queueGame(Arena a, Player p){

        if(a == null){
            p.sendMessage(Utils.color(Files.getInstance().getLazertag().getString("lang.invalid-arena").replace("[prefix]", Utils.getPrefix()).replace("[entry]", a.toString())));
            return;
        }

        a.addPlayer(p.getUniqueId());
        if(Utils.getOneOrTwo() == 1){
            Utils.equipArmor(p, "red");
            Utils.addLaserGun(p);
            p.teleport(a.getSpawnPoint1());
        } else {
            Utils.equipArmor(p, "blue");
            Utils.addLaserGun(p);
            p.teleport(a.getSpawnPoint2());
        }
        try{
            Scoreboard.createScoreboard(p);
        } catch (Exception e){
            e.printStackTrace();
        }
        p.sendMessage(Utils.color(Files.getInstance().getLazertag().getString("lang.arena-join")
                .replace("[prefix]", Utils.getPrefix()).replace("[arena]", a.getName())));
    }

    public Arena getArenaPlayer(Player p){
        for(Arena arena : arenaList){
            if(arena.getPlayers().contains(p.getUniqueId())){
                return arena;
            }
        }
        return null;
    }

    public boolean isArena(String s){
        for(Arena a : arenaList){
            if(s.equalsIgnoreCase(a.getName())){
                return true;
            }
        }
        return false;
    }

    public boolean isConfigArena(String s){
        if(Files.getInstance().getArenas().contains("arenas." + s)){
            return true;
        }
        return false;
    }
}
