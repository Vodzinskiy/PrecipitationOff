package vodzinskiy.precipitationoff;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import vodzinskiy.precipitationoff.commands.CommodoreCommands;
import vodzinskiy.precipitationoff.commands.PptoffCommand;
import java.nio.file.Files;
import java.nio.file.Path;
import me.lucko.commodore.Commodore;
import me.lucko.commodore.CommodoreProvider;


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

        PluginCommand pptoff = this.getCommand("pptoff");
        if (pptoff != null) {
            PptoffCommand pptoffCommand = new PptoffCommand();
            pptoff.setExecutor(pptoffCommand);
            try {
                if (CommodoreProvider.isSupported()) {
                    Commodore commodore = CommodoreProvider.getCommodore(this);
                    CommodoreCommands.registerCompletions(commodore);
                    logger.info("Successfully initialized commodore command completion");
                } else {
                    logger.warn("Commodore command completion is not supported");
                }
            } catch (Throwable t) {
                logger.warn("Failed to initialize commodore command completion", t);
            }
        }

        logger.info("Enabled");
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
