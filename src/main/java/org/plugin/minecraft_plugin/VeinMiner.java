package org.plugin.minecraft_plugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class VeinMiner implements Listener {

    public void destroyEveryBlockInArea(Set<Location> locations, Location block, Material material) {

        if (!block.getBlock().getType().equals(material)) return;
        if (locations.contains(block)) return;

        locations.add(block);
        block.getBlock().breakNaturally();

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    destroyEveryBlockInArea(locations, block.clone().add(x, y, z), material);
                }
            }
        }
    }

    public boolean isBlockOre(Block block) {
        Material material = block.getType();
        System.out.println(material.toString());
        if (material.toString().toLowerCase().endsWith("_ore")) {
            return true;
        }
        return false;
    }

    public boolean hasPlayerPickaxe(Material material) {
        if (material.toString().toLowerCase().endsWith("_pickaxe")) {
            return true;
        }
        return false;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (!hasPlayerPickaxe(item.getType())) return;

        if (isBlockOre(block)) {
            destroyEveryBlockInArea(new HashSet<Location>(), block.getLocation(), block.getType());
        }

    }
}
