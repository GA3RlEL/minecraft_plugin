package org.plugin.minecraft_plugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class Minecraft_plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("heal").setExecutor(new Heal());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
