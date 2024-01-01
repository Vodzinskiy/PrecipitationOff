package vodzinskiy.precipitationoff.services;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import vodzinskiy.precipitationoff.PrecipitationOff;
import vodzinskiy.precipitationoff.models.Area;
import vodzinskiy.precipitationoff.models.Coordinate;
import vodzinskiy.precipitationoff.models.Owner;

import java.util.*;

import static org.bukkit.plugin.java.JavaPlugin.getPlugin;

public class AreaService {

    JsonService jsonService;

    public AreaService() {
        this.jsonService = new JsonService();
    }

    public void selection(Coordinate coordinate, CommandSender commandSender, World world) {
        if (commandSender instanceof Player player) {
            List<Area> data = jsonService.get();

            if (data == null) {
                return;
            }

            Area area = jsonService.getLastArea(data, player.getName());

            if (area != null) {
                if (area.getEnd() == null) {
                    area.setEnd(coordinate);
                    highlightPerimeter(area.getStart(), area.getEnd(), world);
                    commandSender.sendMessage("The area is defined, enter the type of change");
                } else {
                    data.add(new Area(coordinate, new Owner(player.getUniqueId(), player.getName())));
                    commandSender.sendMessage("Start selected, please select the end");
                }
            } else {
                data.add(new Area(coordinate, new Owner(player.getUniqueId(), player.getName())));
                commandSender.sendMessage("Start selected, please select the end");
            }
            jsonService.set(data);
        }
    }

    private void highlightPerimeter(Coordinate start, Coordinate end, World world) {

        int x1 = start.getX();
        int y1 = start.getY();
        int z1 = start.getZ();

        int x2 = end.getX();
        int y2 = end.getY();
        int z2 = end.getZ();

        int minX = Math.min(x1, x2);
        int maxX = Math.max(x1, x2);
        int minY = Math.min(y1, y2) - 3;
        int maxY = Math.max(y1, y2) + 3;
        int minZ = Math.min(z1, z2);
        int maxZ = Math.max(z1, z2);

        Color[] colors = new Color[]{Color.LIME, Color.RED, Color.AQUA, Color.FUCHSIA,
                Color.GREEN,Color.NAVY, Color.OLIVE, Color.YELLOW, Color.ORANGE, Color.TEAL};

        Particle.DustOptions dustOptions = new Particle.DustOptions(colors[new Random().nextInt(colors.length)], 2);
        new BukkitRunnable() {
            @Override
            public void run() {

                for (int x = minX; x <= maxX; x++) {
                    for (int y = minY; y < maxY; y++) {
                        world.spawnParticle(Particle.REDSTONE, x, y, minZ, 1, 0, 0, 0, 1, dustOptions);
                        world.spawnParticle(Particle.REDSTONE, x, y, maxZ, 1, 0, 0, 0, 1, dustOptions);
                    }
                }

                for (int z = minZ + 1; z < maxZ; z++) {
                    for (int y = minY; y < maxY; y++) {
                        world.spawnParticle(Particle.REDSTONE, minX, y, z, 1, 0, 0, 0, 1, dustOptions);
                        world.spawnParticle(Particle.REDSTONE, maxX, y, z, 1, 0, 0, 0, 1, dustOptions);
                    }
                }
            }
        }.runTaskTimer(getPlugin(PrecipitationOff.class), 0, 10);
    }
}
