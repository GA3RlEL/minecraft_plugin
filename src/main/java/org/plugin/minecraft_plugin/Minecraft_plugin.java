package org.plugin.minecraft_plugin;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Minecraft_plugin extends JavaPlugin {

    PluginManager pluginManager = getServer().getPluginManager();

    @Override
    public void onEnable() {
        this.getCommand("tpa").setExecutor(new TpaCommand());
        this.getCommand("tpaaccept").setExecutor(new TpaAcceptCommand());
        this.getCommand("heal").setExecutor(new Heal());
        this.getCommand("sethome").setExecutor(new SetHome());
        this.getCommand("home").setExecutor(new Home());
        this.getCommand("seeinventory").setExecutor(new AdminSeeInventoryCommand());

        pluginManager.registerEvents(new VeinMiner(), this);
        pluginManager.registerEvents(new TreeCutterEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
