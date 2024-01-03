package vodzinskiy.precipitationoff.commands;

import org.bukkit.command.CommandSender;
import vodzinskiy.precipitationoff.services.AreaService;
import vodzinskiy.precipitationoff.services.SetService;

public class SetCommand extends Subcommand  {

    private final SetService setService;
    public SetCommand(AreaService areaService) {
        super("setNoSnowFormation");
        setService = new SetService(areaService);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        setService.set(sender, args);
    }
}
