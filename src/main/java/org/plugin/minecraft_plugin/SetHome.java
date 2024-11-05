package org.plugin.minecraft_plugin;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public final class SetHome implements CommandExecutor {

    String pathFile = new File("").getAbsolutePath() + "\\plugins\\setHomeConfig.yml";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if(sender instanceof Player)
        {
            Player player = (Player) sender;
            Location location = player.getLocation();
            UUID playerId = player.getUniqueId();
            double x = location.getX();
            double y = location.getY();
            double z = location.getZ();
            String record = playerId + " " + x + " " + y + " " + z;

            File file = new File(pathFile);
            BufferedWriter writter = null;
            BufferedReader reader = null;
            if(!file.exists())
            {
                try {
                    file.createNewFile();
                    writePlayerLoctaion(pathFile, record);
//                    System.out.println("Configuration file has been created");
                } catch (IOException e) {
                    System.out.println("An error during creating file occured!");
                    e.printStackTrace();
                }
            }else
            {
                try {
                    System.out.println("Record:" + record);
                    FileReader fr = new FileReader(file);
                    reader = new BufferedReader(fr);
                    StringBuilder newRecord = new StringBuilder();
                    String line = reader.lines().collect(Collectors.joining(System.lineSeparator()));
                    if(isUserWritten(line, playerId))
                    {
//                        System.out.println("User detected");
                        newRecord.append(overWriteUserLocation(x,y,z, playerId,line)).append("\n");
                    }else{
                        newRecord.append(line).append("\n").append(record);
                    }
                    System.out.println("line:" + line);
                    reader.close();
                    fr.close();


//                    System.out.println("Combined" + newRecord);

                    FileWriter fw = new FileWriter(file);
                    writter = new BufferedWriter(fw);
                    writter.write(newRecord.toString());
                    writter.close();
                    fw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }

        return true;
    }

    public static boolean isUserWritten(String data, UUID playerId)
    {
        return data.contains(String.valueOf(playerId));
    }

    public static String overWriteUserLocation(double x, double y , double z, UUID playerId, String data)
    {
        List<String> splittedData = List.of(data.split(System.lineSeparator()));

//        splittedData.stream().forEach(d-> System.out.println(d));
        String fixedData = splittedData.stream().map(d->{
            String[] splittedD = d.split(" ");
            if(d.contains(String.valueOf(playerId)))
            {
                splittedD[1] = String.valueOf(x);
                splittedD[2] = String.valueOf(y);
                splittedD[3] = String.valueOf(z);
            }
            return String.join(" ", splittedD);
        }).collect(Collectors.joining(System.lineSeparator()));

//        System.out.println("fixed data:" + fixedData);

        return fixedData;
    }

    public static void writePlayerLoctaion(String pathFile, String record)
    {
        Path path = Path.of(pathFile);
        try(BufferedWriter writer = Files.newBufferedWriter(path))
        {
            writer.append(record);
            writer.newLine();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
