package org.plugin.minecraft_plugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class Minecraft_plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("siema treecuter odpalony");
        getServer().getPluginManager().registerEvents(new TreeCutterEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
