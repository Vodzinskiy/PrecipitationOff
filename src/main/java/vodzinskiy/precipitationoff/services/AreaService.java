package vodzinskiy.precipitationoff.services;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;
import vodzinskiy.precipitationoff.PrecipitationOff;
import vodzinskiy.precipitationoff.models.Area;
import vodzinskiy.precipitationoff.models.Coordinate;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.bukkit.plugin.java.JavaPlugin.getPlugin;
import static vodzinskiy.precipitationoff.PrecipitationOff.logger;

public class AreaService {

    public static void selection(Coordinate coordinate, String name, CommandSender commandSender, World world) {
        Map<String, Area> data = new LinkedHashMap<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type type = new TypeToken<Map<String, Area>>() {
        }.getType();

        try (FileReader reader = new FileReader("./plugins/PrecipitationOff/area-list.json")) {
            Map<String, Area> loadedData = gson.fromJson(reader, type);
            if (loadedData != null) {
                data = loadedData;
                Area area = data.values().stream().reduce((first, second) -> second).orElse(null);
                assert area != null;
                if (area.getEnd() == null) {
                    area.setEnd(coordinate);
                    highlightPerimeter(area.getStart(), area.getEnd(), world);
                    commandSender.sendMessage("The area is defined, enter the type of change");
                } else {
                    if (name == null) {
                        int num = (int) data.entrySet().stream()
                                .filter(entry -> entry.getKey().startsWith("area"))
                                .count();
                        name = "area" + (num + 1);
                    }
                    data.put(name, new Area(coordinate));
                    commandSender.sendMessage("Start selected, please select the end");
                }
            } else {
                if (name == null) {
                    name = "area1";
                }
                data.put(name, new Area(coordinate));
            }

            try (FileWriter writer = new FileWriter("./plugins/PrecipitationOff/area-list.json")) {
                gson.toJson(data, writer);
            } catch (Exception ignored) {
            }
        } catch (Exception e) {
            logger.error("area-list.json file is missing!\n" + e);
        }
    }

    private static void highlightPerimeter(Coordinate start, Coordinate end, World world) {

        int x1 = start.getX();
        int z1 = start.getZ();

        int x2 = end.getX();
        int z2 = end.getZ();

        int minX = Math.min(x1, x2);
        int maxX = Math.max(x1, x2);
        int minZ = Math.min(z1, z2);
        int maxZ = Math.max(z1, z2);

        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.RED, 1);
        new BukkitRunnable() {

            @Override
            public void run() {

                for (int x = minX; x <= maxX; x++) {
                    for (int y = 0; y < world.getMaxHeight(); y++) {
                        world.spawnParticle(Particle.REDSTONE, x, y, minZ, 1, 0, 0, 0, 1, dustOptions);
                        world.spawnParticle(Particle.REDSTONE, x, y, maxZ, 1, 0, 0, 0, 1, dustOptions);
                    }
                }

                for (int z = minZ + 1; z < maxZ; z++) {
                    for (int y = 0; y < world.getMaxHeight(); y++) {
                        world.spawnParticle(Particle.REDSTONE, minX, y, z, 1, 0, 0, 0, 1, dustOptions);
                        world.spawnParticle(Particle.REDSTONE, maxX, y, z, 1, 0, 0, 0, 1, dustOptions);
                    }
                }
            }
        }.runTaskTimer(getPlugin(PrecipitationOff.class), 0, 10);
    }
}
