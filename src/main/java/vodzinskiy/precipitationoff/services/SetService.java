package vodzinskiy.precipitationoff.services;

import org.bukkit.Color;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vodzinskiy.precipitationoff.models.Area;
import vodzinskiy.precipitationoff.models.Type;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class SetService {

    JsonService jsonService;

    public SetService() {
        this.jsonService = new JsonService();
    }

    public void set(CommandSender sender, String[] args) {
        if (args.length == 1) {
            sender.sendMessage(Color.RED + "Invalid command syntax");
            sender.sendMessage("Please specify the type of weather change ");
            return;
        }
        List<Area> data = jsonService.get();
        if (data == null) {
            return;
        }
        if (args.length >= 3) {
            setType(sender, args[2], Type.valueOf(args[1]), data);
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
            setType(sender, name, Type.valueOf(args[1]), data);
        }
    }

    private void setType(CommandSender sender, String areaName, Type type, List<Area> data) {
        if (sender instanceof Player player) {
            Area area = jsonService.getLastArea(data, player.getName());
            if (area == null) {
                sender.sendMessage("Please specify the area");
                return;
            }
            if (area.getEnd() == null) {
                sender.sendMessage("Please specify the end of the area");
                return;
            }
            switch (type) {
                case STANDARD:
                    break;
                case NO_PRECIPITATION:
                    break;
                case NO_SNOW_FORMATION:
                    break;
                default:
            }
        }
    }

    private void standard() {

    }

    private void noPrecipitation() {

    }

    private void noSnowFormation() {

    }
}
