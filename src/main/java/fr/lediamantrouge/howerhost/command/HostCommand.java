package fr.lediamantrouge.howerhost.command;

import fr.lediamantrouge.howerhost.gui.MainGui;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HostCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        new MainGui(player.getUniqueId()).open(true);
        return true;
    }
}
