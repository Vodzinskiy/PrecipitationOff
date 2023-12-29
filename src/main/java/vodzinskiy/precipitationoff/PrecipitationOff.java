package vodzinskiy.precipitationoff;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.plugin.java.JavaPlugin;
import vodzinskiy.precipitationoff.commands.Select;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;


public final class PrecipitationOff extends JavaPlugin {
    private  static  final Logger logger = LogManager.getLogger("PrecipitationOff");

    @Override
    public void onEnable() {
        try {
            Path path = Path.of("./plugins/PrecipitationOff");
            Files.createDirectory(path);
            Path yamlPath = path.resolve("area-list.yaml");
            Files.newBufferedWriter(yamlPath);
            logger.info("\"list.yaml\" created");
        } catch (Exception ignored) {}

        Objects.requireNonNull(this.getCommand("select")).setExecutor(new Select());
        logger.info("Enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
