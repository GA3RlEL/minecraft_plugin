package org.plugin.minecraft_plugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Enumeration;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TpaCommand extends TpaAcceptCommand implements CommandExecutor {

    private int ExpirationSeconds = 15;

    public void createTimerToDeleteRequest(Player player, Player playerToTeleport) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {

                for (Player p : dictionary.keySet()) {
                    List<Player> playersToTeleport = (List<Player>) dictionary.get(p);
                    if (player.equals(p) && playersToTeleport.contains(playerToTeleport)) {
                        playersToTeleport.remove(playerToTeleport);

                        player.sendMessage(ChatColor.GRAY +
                                "Prosba o teleport do gracza " +
                                ChatColor.WHITE +
                                playerToTeleport.getName() +
                                ChatColor.GRAY +
                                " przedawniła sie.");

                        playerToTeleport.sendMessage(ChatColor.GRAY +
                                "Prosba o teleport od gracza " +
                                ChatColor.WHITE +
                                player.getName() +
                                ChatColor.GRAY +
                                " przedawniła sie.");
                    }
                }
            }
        }, ExpirationSeconds * 1000);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) return false;
        if (args.length != 1) return false;

        Player player = (Player) commandSender;
        Player playerToTeleport = Bukkit.getPlayer(args[0]);
        if (playerToTeleport == null) return false;

        dictionary.put(player, playerToTeleport);

        player.sendMessage(ChatColor.GRAY +
                "Wyslano prosbe o teleport do gracza: " +
                ChatColor.WHITE +
                playerToTeleport.getDisplayName());

        playerToTeleport.sendMessage(ChatColor.GRAY +
                "Otrzymano prosbe o teleport od gracza: " +
                ChatColor.WHITE +
                player.getDisplayName());

        playerToTeleport.sendMessage(ChatColor.GRAY +
                                    "Użyj " +
                                    ChatColor.WHITE  +
                                    "/tpaaccept " +
                                    player.getDisplayName() +
                                    ChatColor.GRAY +
                                    " aby akceptować prosbę.");

        playerToTeleport.sendMessage(ChatColor.GRAY +
                                    "Prośba o teleport przedawni się po " +
                                    ChatColor.WHITE +
                                    ExpirationSeconds +
                                    " sekundach.");

        createTimerToDeleteRequest(player, playerToTeleport);
        return false;
    }
}
