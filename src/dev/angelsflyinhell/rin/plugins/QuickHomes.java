package dev.angelsflyinhell.rin.plugins;

import dev.angelsflyinhell.rin.tools.ConsoleUtils;
import dev.angelsflyinhell.rin.tools.RINConfig;
import dev.angelsflyinhell.rin.tools.props.QHSave;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class QuickHomes implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("sethome")) {

            if (!(args[0].length() >= 1)) {
                player.sendMessage(ConsoleUtils.PREFIX + "Home Point cannot be empty.");
            }

            int playerHomes = 0;
            for (int i = 0; i < QHSave.getKeySize(); i++) {
                String key = QHSave.properties.keySet().toArray()[i].toString();
                if(key.startsWith(String.valueOf(player.getUniqueId())) && !QHSave.getValue(key).equals("DELETED")) {
                    playerHomes++;
                }
            }

            if (RINConfig.propExist("quickhomesLimit") && Integer.parseInt(RINConfig.getValue("quickhomesLimit"))<=playerHomes) {
                player.sendMessage(ConsoleUtils.PREFIX + "Home Point couldn't be set: Exceeds limit of " + RINConfig.getValue("quickhomesLimit"));
                return true;
            }

            String key = player.getUniqueId() + "home" + args[0];
            String loc = player.getLocation().getX() + ";" + player.getLocation().getY() + ";" + player.getLocation().getZ() + ";" + player.getWorld().getName();
            QHSave.addKey(key, loc);
            player.sendMessage(ConsoleUtils.PREFIX + "Set Home Point: " + args[0]);
        }

        if (command.getName().equalsIgnoreCase("delhome")) {
            String key = player.getUniqueId() + "home" + args[0];
            if (QHSave.propExist(key)) {
                QHSave.addKey(key, "DELETED");
                player.sendMessage(ConsoleUtils.PREFIX + "Deleted Home Point: " + args[0]);
                return true;
            }
            player.sendMessage(ConsoleUtils.PREFIX + "Couldn't find Home Point \"" + args[0] + "\"");
        }

        if (command.getName().equalsIgnoreCase("homes")) {
            player.sendMessage(ConsoleUtils.PREFIX + "Your Homes: ");

            int playerHomes = 0;
            for (int i = 0; i < QHSave.getKeySize(); i++) {
                String key = QHSave.properties.keySet().toArray()[i].toString();
                if (key.startsWith(String.valueOf(player.getUniqueId())) && !Objects.equals(QHSave.getValue(key), "DELETED")) {
                    playerHomes++;
                    String outKey = key.replace(player.getUniqueId() + "home", "");
                    player.sendMessage(ConsoleUtils.PREFIX + (playerHomes) + ". " + outKey);
                }
            }
        }

        if (command.getName().equalsIgnoreCase("home")) {
            String key = player.getUniqueId() + "home" + args[0];
            if (!QHSave.propExist(key) || Objects.equals(QHSave.getValue(key), "DELETED")) {
                player.sendMessage(ConsoleUtils.PREFIX + "Couldn't find Home Point \"" + args[0] + "\"");
                return true;
            }

            String[] s = QHSave.getValue(key).split(";");
            Location loc = new Location(Bukkit.getWorld(s[3]), Double.parseDouble(s[0]), Double.parseDouble(s[1]), Double.parseDouble(s[2]));
            player.teleport(loc);
        }

        return true;
    }
}
