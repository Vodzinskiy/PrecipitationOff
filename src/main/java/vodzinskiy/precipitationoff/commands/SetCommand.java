package vodzinskiy.precipitationoff.commands;

import org.bukkit.command.CommandSender;
import vodzinskiy.precipitationoff.models.Type;

import java.util.ArrayList;
import java.util.List;

public class SetCommand extends Subcommand  {

    public SetCommand() {
        super("set");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (args.length >= 3) {
            sender.sendMessage(args[2]);
        } else {
            sender.sendMessage("===---===");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        List<String> list = new ArrayList<>();

        list.add(Type.STANDARD.toString().toLowerCase());
        list.add(Type.NO_PRECIPITATION.toString().toLowerCase());
        list.add(Type.NO_SNOW_FORMATION.toString().toLowerCase());

        return list;
    }
}
