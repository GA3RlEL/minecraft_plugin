package org.plugin.minecraft_plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminSeeEnderchest implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) return false;
        if (args.length != 1) return false;

        Player player = (Player) commandSender;

        if (!player.isOp()) return false;

        Player playerTarget = Bukkit.getPlayer(args[0]);
        if (playerTarget == null)  {
            player.sendMessage(ChatColor.RED + "Nie znaleziono podanego gracza.");
            return false;
        }

        player.openInventory(playerTarget.getEnderChest());

        return false;
    }

}
