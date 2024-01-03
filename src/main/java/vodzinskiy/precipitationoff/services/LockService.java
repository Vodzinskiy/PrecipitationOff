package vodzinskiy.precipitationoff.services;

import org.bukkit.entity.Player;

public class LockService {


    public void clear(Player player) {
        player.getWorld().setStorm(false);
        player.getWorld().setThundering(false);
        player.getWorld().setClearWeatherDuration(Integer.MAX_VALUE);
    }

    public void rain(Player player) {
        player.getWorld().setStorm(true);
        player.getWorld().setThundering(false);
        player.getWorld().setWeatherDuration(Integer.MAX_VALUE);
    }

    public void thunder(Player player) {
        player.getWorld().setStorm(true);
        player.getWorld().setThundering(true);
        player.getWorld().setWeatherDuration(Integer.MAX_VALUE);
    }

    public void reset(Player player) {
        player.getWorld().setStorm(false);
        player.getWorld().setThundering(false);
        player.getWorld().setWeatherDuration(0);
    }
}
