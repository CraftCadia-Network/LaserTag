package com.tabergames.events;

import org.bukkit.*;
import org.bukkit.entity.*;

public class GunRightClick {
    //woOf
    Player player = event.getPlayer;

        public void onShootClick(Event shoot) {
        ClickType clickType = shoot.getClick();
        if (clickType != ClickType.RIGHT) { 
            return;
        }

        if (e.getCurrentItem() == null || shoot.getCurrentItem().getType() != Material.DIAMOND_AXE) {
            return;
        }
    }

} 