package vodzinskiy.precipitationoff.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import vodzinskiy.precipitationoff.models.Coordinate;
import vodzinskiy.precipitationoff.services.AreaService;

public class Select implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player) {
            AreaService.selection(new Coordinate(player.getLocation().getBlockX(), player.getLocation().getBlockZ()),
                    null,
                    commandSender,
                    player.getWorld());
            commandSender.sendMessage("Start selected, please select the end");
        }
        return true;
    }
}
