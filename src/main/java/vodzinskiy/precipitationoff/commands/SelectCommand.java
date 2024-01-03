package vodzinskiy.precipitationoff.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vodzinskiy.precipitationoff.models.Coordinate;
import vodzinskiy.precipitationoff.services.AreaService;

public class SelectCommand extends Subcommand {

    private final AreaService areaService;

    public SelectCommand(AreaService areaService) {
        super("select");
        this.areaService = areaService;
    }
    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (commandSender instanceof Player player) {
            areaService.selection(new Coordinate(player.getLocation().getBlockX(),
                            player.getLocation().getBlockY(),
                            player.getLocation().getBlockZ()),
                    commandSender,
                    args);
        }
    }
}
