package vodzinskiy.precipitationoff.services;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vodzinskiy.precipitationoff.models.Area;

import java.util.List;

import static vodzinskiy.precipitationoff.models.Type.NOT_DEFINED;

public class ListService {

    JsonService jsonService;

    public ListService() {
        this.jsonService = new JsonService();
    }

    public void printList(CommandSender sender) {
        if (sender instanceof Player player) {
            List<Area> areas = jsonService.getAllByName(player.getName()).stream()
                    .filter(a -> !a.getType().equals(NOT_DEFINED))
                    .toList();
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < areas.size(); i++) {
                Area a = areas.get(i);
                str.append(String.format("%d. %s start%s, end%s\n", (i+1), a.getName(), a.getStart(), a.getEnd()));
            }
            sender.sendMessage(str.toString());
        }
    }
}
