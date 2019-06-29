package de.eldoria.worldguardbatch;

import de.eldoria.worldguardbatch.commands.basecommand.BaseCommand;
import de.eldoria.worldguardbatch.messages.MessageSender;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldGuardBatch extends JavaPlugin {

    /**
     * File Config.
     */
    public static FileConfiguration config;

    private static WorldGuardBatch instance;

    private boolean loaded;

    /**
     * Get the Plugin instance.
     *
     * @return Plugin instance
     */
    public static WorldGuardBatch getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        if (loaded) {
            reload();
        }

        if (!loaded) {
            config = getConfig();
            instance = this;
            saveDefaultConfig();
            RegionLoader regionLoader = new RegionLoader();
            this.getCommand("wgb").setExecutor(new BaseCommand(regionLoader));
            Bukkit.getLogger().info("World Guard Batch started");
            loaded = true;
        }
    }

    /**
     * Reloads the plugin.
     */
    public void reload() {
        config = getConfig();
        MessageSender.getInstance().reload();
        Bukkit.getLogger().info("World Guard Batch reloaded");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("World Guard Batch stopped");
    }
}
