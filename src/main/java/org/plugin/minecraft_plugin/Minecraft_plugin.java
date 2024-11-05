package org.plugin.minecraft_plugin;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Minecraft_plugin extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("tpa").setExecutor(new TpaCommand());
        this.getCommand("tpaaccept").setExecutor(new TpaAcceptCommand());
        getServer().getPluginManager().registerEvents(new VeinMiner(), this);
        getServer().getPluginManager().registerEvents(new TreeCutterEvent(), this);
        this.getCommand("heal").setExecutor(new Heal());
        this.getCommand("sethome").setExecutor(new SetHome());
        this.getCommand("home").setExecutor(new Home());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
