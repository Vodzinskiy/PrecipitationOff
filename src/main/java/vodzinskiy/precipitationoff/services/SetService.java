package vodzinskiy.precipitationoff.services;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;
import vodzinskiy.precipitationoff.models.Area;
import vodzinskiy.precipitationoff.models.Type;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static vodzinskiy.precipitationoff.models.Type.*;


public class SetService implements Listener {

    JsonService jsonService;

    AreaService areaService;

    public SetService(AreaService areaService) {
        this.areaService = areaService;
        this.jsonService = new JsonService();
    }

    public SetService() {
        this.jsonService = new JsonService();
    }

    public void set(CommandSender sender, String[] args) {
        if (args.length >= 3) {
            sender.sendMessage("Unknown argument");
            return;
        }
        List<Area> data = jsonService.getAll();
        if (data == null) {
            return;
        }
        if (args.length == 2) {
            if (nameVerification(args[1], sender, data)) {
                return;
            }
            setType(sender, args[1], data);
        } else {
            String name;

            if (data.isEmpty()) {
                name = "area1";
            } else {
                Set<String> existingNames = new HashSet<>();
                for (Area area : data) {
                    existingNames.add(area.getName());
                }
                int nextIndex = 1;
                while (existingNames.contains("area" + nextIndex)) {
                    nextIndex++;
                }
                name = "area" + nextIndex;
            }
            setType(sender, name, data);
        }
    }

    private void setType(CommandSender sender, String areaName, List<Area> data) {
        if (sender instanceof Player player) {
            Area area = jsonService.getLastArea(data, player.getName());
            if (area == null || area.getType() != NOT_DEFINED) {
                sender.sendMessage("Please specify the area");
                return;
            }
            if (area.getEnd() == null) {
                sender.sendMessage("Please specify the end of the area");
                return;
            }
            area.setType(Type.NO_SNOW_FORMATION);
            area.setName(areaName);
            jsonService.set(data);
            areaService.clearHighlight(area.getId());
        }
    }

    private boolean nameVerification(String name, CommandSender sender, List<Area> areas) {
        if (name.equals("all") || name.equals("stop")) {
            sender.sendMessage("Invalid name");
            return true;
        }
        for (Area a : areas) {
            if (a.getName() != null) {
                if (a.getName().equals(name)) {
                    sender.sendMessage("Name already exists");
                    return true;
                }
            }
        }
        return false;
    }


    @EventHandler
    public void onBlockForm(BlockFormEvent event) {
        BlockState newState = event.getNewState();
        if (newState.getType() == Material.SNOW) {
            Block block = event.getBlock();
            List<Area> areas = jsonService.getAll();
            for (Area area : areas) {
                if (area.getType() == NO_SNOW_FORMATION) {
                    int x1 = Math.min(area.getStart().getX(), area.getEnd().getX());
                    int x2 = Math.max(area.getStart().getX(), area.getEnd().getX());
                    int z1 = Math.min(area.getStart().getZ(), area.getEnd().getZ());
                    int z2 = Math.max(area.getStart().getZ(), area.getEnd().getZ());
                    if (block.getX() >= x1 && block.getX() <= x2 && block.getZ() >= z1 && block.getZ() <= z2) {
                        event.setCancelled(true);
                        break;
                    }
                }
            }
        }
    }
}