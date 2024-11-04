package org.plugin.minecraft_plugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TreeCutterEvent implements Listener {

    public boolean isBlockLog(Block block) {
        Material material = block.getType();
        if (material.toString().toLowerCase().endsWith("log")) {
            return true;
        }
        return false;
    }

    public boolean hasPlayerAxe(Material material) {
        if (material.toString().toLowerCase().endsWith("_axe")) {
            return true;
        }
        return false;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        if (!hasPlayerAxe(itemInHand.getType())) return;

        int logsDestroyed = 0;

        Block b = event.getBlock();
        Material material = b.getType();

        while (isBlockLog(b)) {
            b.breakNaturally();
            b = b.getRelative(0, 1, 0);
            logsDestroyed++;
        }

        itemInHand.setDurability((short) (itemInHand.getDurability() + logsDestroyed));
    }
}
