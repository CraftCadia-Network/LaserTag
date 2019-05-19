package me.lukeben.lasertag.backend.laser;

import me.lukeben.lasertag.utils.Files;
import me.lukeben.lasertag.utils.Utils;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftSnowball;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;

import java.util.HashMap;

public class LaserGun {

    public final static HashMap<Entity, EntityData> shotprojectiledata = new HashMap<>();

    final static double DAMAGE = Files.getInstance().getLazertag().getDouble("gun.damage");
    final static int DISTANCE = Files.getInstance().getLazertag().getInt("gun.distance");

    public static void shoot(Player player) {
        Snowball projectile = player.launchProjectile(Snowball.class);
        EntityData data = new EntityData(projectile.getLocation(), DISTANCE, DAMAGE);
        shotprojectiledata.put(projectile, data);
        for (Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(((CraftSnowball) projectile).getHandle().getId()));
        }
        Location playerloc = player.getEyeLocation();
        Location blockloc = playerloc.clone().add(playerloc.getDirection().multiply(100));
        laserbeam(playerloc, blockloc, DISTANCE * 10, Utils.stripColor(Utils.getTeam(player)));

    }

    public static void laserbeam(Location start, Location end, int repeatCount, String color) {
        double dist = Math.abs(end.distance(start));
        Location l;
        Block block;
        for (int i = -1; i < repeatCount; i++) {
            double delta = i / 10.0D / dist;
            double x = (1.0D - delta) * start.getX() + delta * (end.getX() + 0.5D);
            double y = (1.0D - delta) * start.getY() + delta * (end.getY() + 0.5D);
            double z = (1.0D - delta) * start.getZ() + delta * (end.getZ() + 0.5D);

            l = new Location(start.getWorld(), x, y, z);
            if(color.equalsIgnoreCase("&cRED")){
                ParticleEffect.REDSTONE.display(new ParticleEffect.OrdinaryColor(255, 0, 0), l, 257.0D);
            } else {
                ParticleEffect.REDSTONE.display(new ParticleEffect.OrdinaryColor(51, 51, 255), l, 257.0D);
            }

            block = start.getWorld().getBlockAt(l);
            if (block.getType().isSolid()) {
                break;
            }
        }
    }
}