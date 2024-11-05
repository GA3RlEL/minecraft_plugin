package org.plugin.minecraft_plugin;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class Home implements CommandExecutor {
    File file;
    String pathFile = new File("").getAbsolutePath() + "\\plugins\\setHomeConfig.yml";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if(sender instanceof Player)
        {
            Player player = (Player) sender;
            file = new File(pathFile);
            if(!file.exists())
            {
                player.sendMessage("Your home is not set - use /sethome command 1");
            }else{
                UUID playerid = player.getUniqueId();
                try(BufferedReader reader = Files.newBufferedReader(Path.of(pathFile)))
                {
                    String line;
                    boolean isPlayerExist = false;
                    while((line = reader.readLine())!=null)
                    {
                        String[] splittedLine = line.split(" ");
                            if(line.contains(String.valueOf(playerid))) {
                                Location location = new Location(player.getWorld(),
                                        Double.parseDouble(splittedLine[1]),
                                        Double.parseDouble(splittedLine[2]),
                                        Double.parseDouble(splittedLine[3]));
                                player.teleport(location);
                                isPlayerExist = true;
                                break;
                            }

                    }
                    if(!isPlayerExist)
                    {
                        player.sendMessage("Your home is not set - use /sethome command");
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

        }

        return true;
    }
}
