package dev.daniboy.itemrestrictor;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;

public class ItemRestrictor extends JavaPlugin {

    private FileConfiguration config;



    @Override
    public void onEnable() {
        // Display a startup message with ANSI escape codes for colors
        getLogger().info("\u001B[32m================================");
        getLogger().info("\u001B[32m  ItemRestrictor Plugin Enabled");
        getLogger().info("\u001B[32m================================");
        getLogger().info("\u001B[33mVersion: " + getDescription().getVersion());
        getLogger().info("\u001B[33mAuthor: " + getDescription().getAuthors().get(0));
        getLogger().info("\u001B[33mDescription: " + getDescription().getDescription());
        getLogger().info("\u001B[32m================================");

        // Your other initialization code here
        saveDefaultConfig();
        // Register events and commands
        getServer().getPluginManager().registerEvents(new ItemRestrictionListener(this), this);
        Objects.requireNonNull(getCommand("itemrestrictor")).setExecutor(new ReloadCommand(this));
    }

    public FileConfiguration getCustomConfig() {
        if (config == null) {
            reloadCustomConfig();
        }
        return config;
    }

    public void reloadCustomConfig() {
        File customConfigFile = new File(getDataFolder(), "config.yml");
        config = YamlConfiguration.loadConfiguration(customConfigFile);
    }

    public void saveCustomConfig() {
        if (config == null) {
            return;
        }
        try {
            getCustomConfig().save(new File(getDataFolder(), "config.yml"));
        } catch (IOException ex) {
            getLogger().log(Level.SEVERE, "Could not save config to " + getDataFolder(), ex);
        }
    }
}
