package org.plugin.minecraft_plugin;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class TpaAcceptCommand implements CommandExecutor {

    public static Multimap<Player, Player> dictionary = ArrayListMultimap.create();

    public boolean isRequestAvailable(Player player, Player playerToAccept) {

        for (Player p : dictionary.keySet()) {
            List<Player> playersToAccept = (List<Player>) dictionary.get(p);
            if (player.equals(p) && playersToAccept.contains(playerToAccept)) {
                playersToAccept.remove(playerToAccept);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (!(commandSender instanceof Player)) return false;
        if (args.length != 1) return false;

        Player player = (Player) commandSender;
        Player playerToAccept = Bukkit.getPlayer(args[0]);
        if (playerToAccept == null) return false;

        boolean foundPlayerToAccept = isRequestAvailable(player, playerToAccept);

        if (!foundPlayerToAccept) {
            player.sendMessage(ChatColor.RED +
                    "Nie masz prosby o teleport od gracza " +
                    ChatColor.WHITE +
                    playerToAccept.getDisplayName());
            return false;
        }

        Location playerLocation = player.getLocation();
        playerToAccept.teleport(playerLocation);

        playerToAccept.sendMessage(ChatColor.GREEN +
                                    "Pomyślnie przeteleportowano do gracza: " +
                                    ChatColor.WHITE +
                                    player.getDisplayName());

        return true;
    }
}