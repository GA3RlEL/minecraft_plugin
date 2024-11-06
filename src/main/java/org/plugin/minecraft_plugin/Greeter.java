package org.plugin.minecraft_plugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Greeter implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        Random random = new Random();

        List<String> greeaters = new ArrayList<String>(){{
            add("Welcome back " + ChatColor.BLUE + player.getDisplayName());
            add("Hey " + ChatColor.RED + player.getDisplayName() + ChatColor.WHITE + "! Ready for some fun?");
            add("Hey " + ChatColor.LIGHT_PURPLE + player.getDisplayName() + ChatColor.WHITE + "! Lets make this world unforgettable");
            add("Good to see you " + ChatColor.GREEN + player.getDisplayName());
        }};
        event.setJoinMessage(greeaters.get(random.nextInt(0,greeaters.size())));
    }
}
