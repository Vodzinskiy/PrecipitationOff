package vodzinskiy.precipitationoff.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.bukkit.Color;
import org.bukkit.command.CommandSender;
import vodzinskiy.precipitationoff.models.Area;
import vodzinskiy.precipitationoff.models.Type;

import java.io.FileReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static vodzinskiy.precipitationoff.PrecipitationOff.logger;

public class SetService {

    public void set(CommandSender sender, String[] args) {
        if (args.length == 1) {
            sender.sendMessage(Color.RED + "Invalid command syntax");
            sender.sendMessage("Please specify the type of weather change ");
            return;
        }

        if (args.length >= 3) {
            setType(sender, args[2], Type.valueOf(args[1]));
        } else {
            String name = "";

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            java.lang.reflect.Type type = new TypeToken<List<Area>>() {}.getType();

            try (FileReader reader = new FileReader("./plugins/PrecipitationOff/area-list.json")) {
                List<Area> loadedData = gson.fromJson(reader, type);
                if (loadedData == null) {
                    name = "area1";
                } else {
                    Set<String> existingNames = new HashSet<>();
                    for (Area area : loadedData) {
                        existingNames.add(area.getName());
                    }
                    int nextIndex = 1;
                    while (existingNames.contains("area" + nextIndex)) {
                        nextIndex++;
                    }
                    name = "area" + nextIndex;
                }
            } catch (Exception e) {
                logger.error("area-list.json file is missing!\n" + e);
            }

            setType(sender, name, Type.valueOf(args[1]));
        }
    }

    private void setType(CommandSender sender, String name, Type type) {
        sender.sendMessage(name + " " + type);
    }
}
