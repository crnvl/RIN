package dev.angelsflyinhell.rin;

import dev.angelsflyinhell.rin.events.BetterSaplings;
import dev.angelsflyinhell.rin.tools.ConsoleUtils;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class RIN extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BetterSaplings(), this);
        getServer().getConsoleSender().sendMessage( ChatColor.DARK_PURPLE + ConsoleUtils.PREFIX + "Loaded " + ConsoleUtils.FEATURES);
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.DARK_PURPLE + ConsoleUtils.PREFIX + "Unloaded " + ConsoleUtils.FEATURES);
    }
}
