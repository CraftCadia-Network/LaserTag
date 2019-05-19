package me.lukeben.lasertag.utils;

import me.lukeben.lasertag.backend.ArenaManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.HashMap;
import java.util.Random;

public class Utils {

    public static String color(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String stripColor(String s){
        return ChatColor.stripColor(s);
    }

    public static boolean hasPermission(Player p, String s){
        if(p.hasPermission("lasertag." + s) || p.hasPermission("lasertag.*")){
            return true;
        }
        return false;
    }

    public static String getPrefix(){
        return Files.getInstance().getLazertag().getString("prefix");
    }

    public static boolean isInt(String s){
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        return false;
    }
    public static ItemStack getBlueChest(){
        ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta lmeta = (LeatherArmorMeta) item.getItemMeta();
        lmeta.setColor(Color.BLUE);
        lmeta.setDisplayName(color(Files.getInstance().getLazertag().getString("teams.blue")));
        item.setItemMeta(lmeta);
        return item;
    }

    public static ItemStack getRedChest(){
        ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta lmeta = (LeatherArmorMeta) item.getItemMeta();
        lmeta.setColor(Color.RED);
        lmeta.setDisplayName(color(Files.getInstance().getLazertag().getString("teams.red")));
        item.setItemMeta(lmeta);
        return item;
    }

    public static void equipArmor(Player p, String color){
        if(color.equals("red")){
            p.getInventory().setChestplate(getRedChest());
        } else {
            p.getInventory().setChestplate(getBlueChest());
        }
    }

    public static boolean isLaserGun(ItemStack item){
        if(item.hasItemMeta() && item.getType().equals(Material.IRON_AXE) &&
                item.getItemMeta().getDisplayName().equals(color(Files.getInstance().getLazertag().getString("gun.title")))){
            return true;
        }
        return false;
    }

    public static void addLaserGun(Player player){
        ItemStack gun = new ItemStack(Material.IRON_AXE);
        ItemMeta meta = gun.getItemMeta();
        meta.setDisplayName(color(Files.getInstance().getLazertag().getString("gun.title")));
        gun.setItemMeta(meta);
        player.setItemInHand(gun);
    }

    public static String getTeam(Player p){
        ItemStack chest = p.getInventory().getChestplate();
        LeatherArmorMeta lmeta = (LeatherArmorMeta) chest.getItemMeta();
        if(lmeta.getColor().equals(Color.RED)){
            return "&cRED";
        } else {
            return "&1BLUE";
        }
    }

    public static String replaceValues(Player p, String s){
        if(s.contains("{team}")){
            s = s.replace("{team}", getTeam(p));
        }
        if(s.contains("{deaths}")){
            Arena arena = ArenaManager.getInstance().getArenaPlayer(p);
            int deaths = arena.getDeaths(p);
            s = s.replace("{deaths}", String.valueOf(deaths));
        }
        if(s.contains("{kills}")){
            Arena arena = ArenaManager.getInstance().getArenaPlayer(p);
            int kills = arena.getKills(p);
            s = s.replace("{kills}", String.valueOf(kills));
        }
        if(s.contains("{arena}")){
            Arena arena = ArenaManager.getInstance().getArenaPlayer(p);
            s = s.replace("{arena}", arena.getName());
        }
        return s;

    }

    public static Integer getOneOrTwo(){
        if(Math.random() < 0.5){
            return 1;
        }
        return 2;
    }

    public static Location getLobby(){
        World world = Bukkit.getWorld(Files.getInstance().getLazertag().getString("arena.world"));
        Double x = Files.getInstance().getLazertag().getDouble("arena.lobby.x");
        Double y = Files.getInstance().getLazertag().getDouble("arena.lobby.y");
        Double z = Files.getInstance().getLazertag().getDouble("arena.lobby.z");
        Location lobby = new Location(world, x,y ,z);
        return lobby;
    }





}
