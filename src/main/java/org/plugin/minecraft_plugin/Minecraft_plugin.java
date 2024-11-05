package org.plugin.minecraft_plugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class Minecraft_plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("tpa").setExecutor(new TpaCommand());
        this.getCommand("tpaaccept").setExecutor(new TpaAcceptCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
