package dev.angelsflyinhell.rin;

import dev.angelsflyinhell.rin.plugins.BetterSaplings;
import dev.angelsflyinhell.rin.plugins.QuickHomes;
import dev.angelsflyinhell.rin.tools.ConsoleUtils;
import dev.angelsflyinhell.rin.tools.RINConfig;
import dev.angelsflyinhell.rin.tools.props.QHSave;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class RIN extends JavaPlugin {

    @Override
    public void onEnable() {
        RINConfig.init();

        if(!RINConfig.propExist("bettersaplings")) {
            RINConfig.addKey("bettersaplings", "false");
        }
        if(!RINConfig.propExist("quickhomes")) {
            RINConfig.addKey("quickhomes", "false");
        }
        if(!RINConfig.propExist("quickhomesLimit")) {
            RINConfig.addKey("quickhomesLimit", "3");
        }

        if(Objects.equals(RINConfig.getValue("bettersaplings"), "true")) {
            getServer().getPluginManager().registerEvents(new BetterSaplings(), this);
        }

        if(Objects.equals(RINConfig.getValue("quickhomes"), "true")) {
            QHSave.init();
            Objects.requireNonNull(getCommand("sethome")).setExecutor(new QuickHomes());
            Objects.requireNonNull(getCommand("delhome")).setExecutor(new QuickHomes());
            Objects.requireNonNull(getCommand("homes")).setExecutor(new QuickHomes());
            Objects.requireNonNull(getCommand("home")).setExecutor(new QuickHomes());
        }
        getServer().getConsoleSender().sendMessage( ChatColor.DARK_PURPLE + ConsoleUtils.PREFIX + "Plugin loaded!");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.DARK_PURPLE + ConsoleUtils.PREFIX + "Plugin unloaded");
    }
}
