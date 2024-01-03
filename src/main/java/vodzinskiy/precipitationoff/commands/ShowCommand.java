package vodzinskiy.precipitationoff.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vodzinskiy.precipitationoff.services.ShowService;

public class ShowCommand extends Subcommand {

    ShowService showService;

    public ShowCommand() {
        super("show");
        showService = new ShowService();
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player player) {
            if (args.length >= 4) {
                sender.sendMessage("Unknown argument");
                return;
            }

            if (args.length == 3) {
                if (args[1].equals("stop")) {
                    showService.stopShowingByName(args[2], player.getUniqueId(), sender);
                } else {
                    sender.sendMessage("Unknown argument");
                }
                return;
            }

            if (args[1].equals("all")) {
                showService.showAll(player.getUniqueId(), sender, player.getWorld());
            } else if (args[1].equals("stop")) {
                showService.stopShowing(player.getUniqueId(), sender);
            } else {
                showService.showByName(args[1], player.getUniqueId(), sender, player.getWorld());
            }
        }

    }
}
