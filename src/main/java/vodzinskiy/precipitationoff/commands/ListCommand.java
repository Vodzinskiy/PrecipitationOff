package vodzinskiy.precipitationoff.commands;

import org.bukkit.command.CommandSender;
import vodzinskiy.precipitationoff.services.ListService;

public class ListCommand extends Subcommand {

    private final ListService listService;

    public ListCommand() {
        super("list");
        listService = new ListService();
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length >= 2) {
            sender.sendMessage("Unknown argument");
            return;
        }
        listService.printList(sender);
    }
}
