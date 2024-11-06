package org.plugin.minecraft_plugin;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Minecraft_plugin extends JavaPlugin {

    PluginManager pm = getServer().getPluginManager();

    @Override
    public void onEnable() {
        //Commands
        this.getCommand("tpa").setExecutor(new TpaCommand());
        this.getCommand("tpaaccept").setExecutor(new TpaAcceptCommand());
        this.getCommand("heal").setExecutor(new Heal());
        this.getCommand("sethome").setExecutor(new SetHome());
        this.getCommand("home").setExecutor(new Home());
        this.getCommand("seeinventory").setExecutor(new AdminSeeInventoryCommand());


        this.getCommand("enderchest").setExecutor(new AdminSeeEnderchest());

        //Events
        pm.registerEvents(new Greeter(), this);
        pm.registerEvents(new VeinMiner(), this);
        pm.registerEvents(new TreeCutterEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
