package vodzinskiy.precipitationoff.services;

import org.bukkit.World;
import org.bukkit.command.CommandSender;
import vodzinskiy.precipitationoff.models.Area;

import java.util.List;
import java.util.UUID;

public class ShowService {

    JsonService jsonService;
    AreaService areaService;

    public ShowService() {
        this.jsonService = new JsonService();
        this.areaService = new AreaService();
    }

    public void showByName(String name, UUID id, CommandSender sender, World world) {
        Area area = jsonService.getByName(name);
        if (area.getOwner().getUuid().equals(id)) {
            areaService.highlightPerimeter(area, world);
        } else {
            sender.sendMessage("You do not have permission to delete this area");
        }
    }

    public void showAll(UUID id, CommandSender sender, World world) {
        List<Area> areas = jsonService.getAllByName(id);
        for (Area a : areas) {
            showByName(a.getName(), id, sender, world);
        }
    }

    public void stopShowing(UUID id, CommandSender sender) {
        List<Area> areas = jsonService.getAllByName(id);
        for (Area a : areas) {
            stopShowingByName(a.getName(), id, sender);
        }
    }

    public void stopShowingByName(String name, UUID id, CommandSender sender) {
        Area area = jsonService.getByName(name);
        if (area == null) {
            sender.sendMessage("Area with the name \"" + name + "\" was not found");
            return;
        }
        if (area.getOwner().getUuid().equals(id)) {
            areaService.clearHighlight(area.getId());
        } else {
            sender.sendMessage("You do not have permission to delete this area");
        }
    }
}
