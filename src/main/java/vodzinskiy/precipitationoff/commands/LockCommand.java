package vodzinskiy.precipitationoff.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vodzinskiy.precipitationoff.services.LockService;

public class LockCommand extends Subcommand {

    private final LockService lockService;
    public LockCommand() {
        super("lock");
        lockService = new LockService();
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length >= 3) {
            sender.sendMessage("Unknown argument");
            return;
        }
        if (sender instanceof Player player) {
            switch (args[1]) {
                case "clear":
                    lockService.clear(player);
                    break;
                case "rain":
                    lockService.rain(player);
                    break;
                case "thunder":
                    lockService.thunder(player);
                    break;
                case "reset":
                    lockService.reset(player);
                    break;
                default:
                    sender.sendMessage("Unknown argument");
                    break;
            }
        }
    }
}
