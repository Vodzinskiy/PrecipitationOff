package vodzinskiy.precipitationoff.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vodzinskiy.precipitationoff.services.AreaService;
import vodzinskiy.precipitationoff.services.DeleteService;

public class DeleteCommand extends Subcommand {

    private final DeleteService deleteService;
    public DeleteCommand(AreaService areaService) {
        super("delete");
        deleteService = new DeleteService(areaService);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            if (args.length >= 3) {
                sender.sendMessage("Unknown argument");
                return;
            }
            if (args[1].equals("all")) {
                deleteService.deleteAll(player.getUniqueId(), sender);
            } else {
                deleteService.deleteByName(args[1], player.getUniqueId(), sender);
            }
        }
    }
}
