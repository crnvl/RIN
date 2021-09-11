package dev.angelsflyinhell.rin.plugins;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;

import java.util.Random;

public class BetterSaplings implements Listener {

    @EventHandler
    public static void itemDrop(ItemSpawnEvent event) {
        if(event.getEntity().getName().contains("Sapling") && event.getEntity().getThrower() == null) {
            double locY = event.getEntity().getWorld().getHighestBlockYAt(event.getEntity().getLocation()) + 1;
            double locX = event.getEntity().getLocation().getX();
            double locZ = event.getEntity().getLocation().getZ();
            Location loc = new Location(event.getEntity().getWorld(), locX, locY, locZ);
            int r = new Random().nextInt(2);
            if(r == 0) {
                loc.getBlock().setType(event.getEntity().getItemStack().getType());
                event.getEntity().remove();
            }
        }
    }
}
