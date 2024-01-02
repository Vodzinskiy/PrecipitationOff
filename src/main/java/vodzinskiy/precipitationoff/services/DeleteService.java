package vodzinskiy.precipitationoff.services;

import org.bukkit.command.CommandSender;
import vodzinskiy.precipitationoff.models.Area;

import java.util.List;
import java.util.UUID;

public class DeleteService {
    AreaService areaService;
    JsonService jsonService;

    public DeleteService(AreaService areaService) {
        this.areaService = areaService;
        jsonService = new JsonService();
    }

    public void deleteByName(String name, UUID id, CommandSender sender) {
        Area area = jsonService.getByName(name);
        if (area.getOwner().getUuid().equals(id)) {
            jsonService.deleteArea(area.getId());
            sender.sendMessage("Deleted");
        } else {
            sender.sendMessage("You do not have permission to delete this area");
        }
    }

    public void deleteAll(UUID id, CommandSender sender) {
        List<Area> areas = jsonService.getAll();
        for (int i = 0; i < areas.size(); i++) {
            Area area = areas.get(i);
            if (area.getOwner().getUuid().equals(id)) {
                areas.remove(i);
                i -= 1;
            }
        }
        jsonService.set(areas);
        sender.sendMessage("Deleted");
    }
}
