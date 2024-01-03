package vodzinskiy.precipitationoff.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.command.CommandSender;

@AllArgsConstructor
@Getter
public abstract class Subcommand {

    private final String name;
    public abstract void execute(CommandSender sender, String[] args);
}