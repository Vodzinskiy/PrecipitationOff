package vodzinskiy.precipitationoff.commands;

import org.bukkit.command.CommandSender;
import vodzinskiy.precipitationoff.services.SetService;

public class SetCommand extends Subcommand  {

    SetService setService;
    public SetCommand() {
        super("set");
        setService = new SetService();
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        setService.set(sender, args);
    }
}
