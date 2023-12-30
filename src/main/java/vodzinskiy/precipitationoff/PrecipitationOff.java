package vodzinskiy.precipitationoff;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import vodzinskiy.precipitationoff.commands.Select;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;


public final class PrecipitationOff extends JavaPlugin {
    public static  final Logger logger = LogManager.getLogger("PrecipitationOff");

    @Override
    public void onEnable() {
        Path path = Path.of("./plugins/PrecipitationOff");
        try {
            Files.createDirectory(path);
        } catch (Exception ignored) {}
        try {
            Path yamlPath = path.resolve("area-list.json");
            Files.newBufferedWriter(yamlPath);
            logger.info("\"list.json\" created");
        } catch (Exception ignored) {}

        Objects.requireNonNull(this.getCommand("pptoff select")).setExecutor(new Select());
        logger.info("Enabled");
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
