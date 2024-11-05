package org.plugin.minecraft_plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;


public final class Heal extends JavaPlugin {
    @Override
    public void onEnable(){
        getLogger().info("Plugin sie wlaczyl!");
    }

    @Override
    public void onDisable(){
        getLogger().info("Plugin sie wylaczyl!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Sprawdź, czy komenda to "/heal"
        if (command.getName().equalsIgnoreCase("heal")) {
            // Sprawdź, czy wysyłający komendę to gracz
            if (sender instanceof Player) {
                Player player = (Player) sender;
                // Ustaw zdrowie i poziom głodu gracza na maksymalny
                player.setHealth(20.0); // Maksymalne zdrowie
                player.setFoodLevel(20); // Maksymalny poziom głodu
                player.sendMessage("Zostałeś uleczony!");
            } else {
                sender.sendMessage("Tę komendę może używać tylko gracz!");
            }
            return true;
        }
            if (command.getName().equalsIgnoreCase("randomeffect")){
                if (sender instanceof Player){
                    Player player = (Player) sender;

                    PotionEffectType[] effects = {
                            PotionEffectType.SPEED,
                            PotionEffectType.SLOWNESS,
                            PotionEffectType.HASTE,
                            PotionEffectType.MINING_FATIGUE,
                            PotionEffectType.STRENGTH,
                            PotionEffectType.INSTANT_HEALTH,
                            PotionEffectType.INSTANT_DAMAGE,
                            PotionEffectType.JUMP_BOOST,
                            PotionEffectType.NAUSEA,
                            PotionEffectType.REGENERATION,
                            PotionEffectType.RESISTANCE,
                            PotionEffectType.FIRE_RESISTANCE,
                            PotionEffectType.WATER_BREATHING,
                            PotionEffectType.INVISIBILITY,
                            PotionEffectType.BLINDNESS,
                            PotionEffectType.NIGHT_VISION,
                            PotionEffectType.HUNGER,
                            PotionEffectType.WEAKNESS,
                            PotionEffectType.POISON,
                            PotionEffectType.WITHER,
                            PotionEffectType.HEALTH_BOOST,
                            PotionEffectType.ABSORPTION,
                            PotionEffectType.SATURATION,
                            PotionEffectType.GLOWING,
                            PotionEffectType.LEVITATION,
                            PotionEffectType.LUCK,
                            PotionEffectType.UNLUCK,
                            PotionEffectType.SLOW_FALLING,
                            PotionEffectType.CONDUIT_POWER,
                            PotionEffectType.DOLPHINS_GRACE,
                            PotionEffectType.BAD_OMEN,
                            PotionEffectType.HERO_OF_THE_VILLAGE,
                            PotionEffectType.DARKNESS,
                            PotionEffectType.TRIAL_OMEN,
                            PotionEffectType.RAID_OMEN,
                            PotionEffectType.WIND_CHARGED,
                            PotionEffectType.WEAVING,
                            PotionEffectType.OOZING,
                            PotionEffectType.INFESTED,
                    };
                    Random random = new Random();

                    PotionEffectType randomEffect = effects[random.nextInt(effects.length)];

                    player.addPotionEffect(new PotionEffect(randomEffect,200 , 1));
                }return true;
            }
            return false;

        }

    }
