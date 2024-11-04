package org.plugin.minecraft_plugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class Minecraft_plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new VeinMiner(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
