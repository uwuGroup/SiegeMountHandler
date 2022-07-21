package me.asakura_kukii.siegemounthandler.main;

import me.asakura_kukii.siegemounthandler.deserializer.loader.common.LoaderType;
import me.asakura_kukii.siegemounthandler.utility.colorcode.ColorCode;
import me.asakura_kukii.siegemounthandler.utility.command.CommandHandler;
import org.apache.commons.io.FileUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static me.asakura_kukii.siegemounthandler.SiegeMountHandler.SiegeMountHandlerListenerRegister;
import static me.asakura_kukii.siegemounthandler.SiegeMountHandler.SiegeMountHandlerLoader;

public class Main extends JavaPlugin {

    public static File configFile;
    public static File configFolder;
    public static File dataFolder;

    private static Main main;
    public static Main getInstance() {
        return main;
    }

    public static String pluginPrefix = ChatColor.translateAlternateColorCodes('&',"&8[&1SiegeMountHandler&8] &f");
    public static String consolePluginPrefix = "[SiegeMountHandler] ->> ";
    public static String pluginName = "SiegeMountHandler";


    @Override
    public void reloadConfig() {
        dataFolder = getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
            getServer().getConsoleSender().sendMessage(ColorCode.ANSI_GREEN + pluginPrefix + "Creating data folder" + ColorCode.ANSI_WHITE);
        }
        configFolder = new File(this.getDataFolder(), "config");
        if (!configFolder.exists()) {
            configFolder.mkdirs();
            getServer().getConsoleSender().sendMessage(ColorCode.ANSI_GREEN + pluginPrefix + "Creating config folder" + ColorCode.ANSI_WHITE);
        }
        configFile = new File(configFolder, "config.yml");
        if (!configFile.exists()) {
            try {
                Files.copy(Objects.requireNonNull(main.getResource("config.yml")), Paths.get(configFile.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void reloadValues() {
        reloadConfig();
        SiegeMountHandlerLoader(getServer(), dataFolder, pluginName, pluginPrefix, consolePluginPrefix, this);
    }

    @Override
    public void onEnable() {
        main = this;
        if(Bukkit.getPluginManager().getPlugin("ProtocolLib") == null){
            getServer().getConsoleSender().sendMessage(ColorCode.ANSI_RED + consolePluginPrefix + "Missing ProtocolLib, disabling..." + ColorCode.ANSI_WHITE);
            Bukkit.getPluginManager().disablePlugin(this);
        }
        SiegeMountHandlerListenerRegister(this);
        getServer().getConsoleSender().sendMessage(ColorCode.ANSI_GREEN + consolePluginPrefix + "Enabling " + pluginName + ColorCode.ANSI_WHITE);
        reloadValues();
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ColorCode.ANSI_GREEN + consolePluginPrefix + pluginName + " disabled" + ColorCode.ANSI_WHITE);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> s = new ArrayList<String>();
        if (args.length > 0) {
            s = CommandHandler.onTabComplete(sender, command, alias, args);
            return s;
        }
        return s;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase(pluginName)) {
            if (args.length > 0) {
                return CommandHandler.onCommand(sender, command, label, args);
            }
            return true;
        }
        return true;
    }
}

