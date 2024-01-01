package vodzinskiy.precipitationoff.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PptoffCommand implements CommandExecutor {

    private final List<Subcommand> subcommands = new ArrayList<>();

    public PptoffCommand() {
        subcommands.add(new SelectCommand());
        subcommands.add(new SetCommand());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Usage: /pptoff <subcommand> [arguments]");
            return true;
        }
        Subcommand subcommand = getSubcommand(args[0]);
        if (subcommand == null) {
            sender.sendMessage("Unknown subcommand.");
            return true;
        }
        subcommand.execute(sender, args);
        return true;
    }

    private Subcommand getSubcommand(String name) {
        for (Subcommand subcommand : subcommands) {
            if (subcommand.getName().equalsIgnoreCase(name)) {
                return subcommand;
            }
        }
        return null;
    }
}
