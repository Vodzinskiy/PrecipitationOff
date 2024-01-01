package vodzinskiy.precipitationoff.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vodzinskiy.precipitationoff.models.Coordinate;
import vodzinskiy.precipitationoff.services.AreaService;

public class SelectCommand extends Subcommand {

    public SelectCommand() {
        super("select");
    }
    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if (commandSender instanceof Player player) {
            AreaService.selection(new Coordinate(player.getLocation().getBlockX(),
                            player.getLocation().getBlockY(),
                            player.getLocation().getBlockZ()),
                    null,
                    commandSender,
                    player.getWorld());
        }
    }
}
