package me.asakura_kukii.siegemounthandler.utility;

import me.asakura_kukii.siegemounthandler.main.Main;
import me.asakura_kukii.siegemounthandler.utility.colorcode.ColorCode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;

public class FileHandler {
    public FileConfiguration fC;
    public File f;


    public FileHandler(File f2) {
        f = f2;
        if(!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        fC = YamlConfiguration.loadConfiguration(f);
    }

    public void set(String path, Object val) {
        fC.set(path, val);
        save();
    }

    public void save(){
        try {
            fC.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyFileFromResource(String resource, File dest) {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = me.asakura_kukii.siegemounthandler.SiegeMountHandler.pluginInstance.getResource(resource);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while (true) {
                assert is != null;
                if (!((length = is.read(buffer)) > 0)) break;
                os.write(buffer, 0, length);
            }
            is.close();
            os.close();
        } catch (Exception ignored) {
        }
    }

    public static File loadSubfolder(String name) {
        if (new File(me.asakura_kukii.siegemounthandler.SiegeMountHandler.pluginFolder, name).exists()) {
            File newFolder = new File(me.asakura_kukii.siegemounthandler.SiegeMountHandler.pluginFolder, name);
            if (!newFolder.isDirectory()) {
                if(newFolder.delete()) {
                    if(newFolder.mkdir()) {
                        me.asakura_kukii.siegemounthandler.SiegeMountHandler.server.getConsoleSender().sendMessage(ColorCode.ANSI_GREEN + me.asakura_kukii.siegemounthandler.SiegeMountHandler.consolePluginPrefix + ColorCode.ANSI_WHITE + "Creating directory [" + name + "]");
                        return newFolder;
                    }
                }
            }
            return newFolder;
        } else {
            File newFolder = new File(me.asakura_kukii.siegemounthandler.SiegeMountHandler.pluginFolder, name);
            if (newFolder.mkdir()) {
                me.asakura_kukii.siegemounthandler.SiegeMountHandler.server.getConsoleSender().sendMessage(ColorCode.ANSI_GREEN + me.asakura_kukii.siegemounthandler.SiegeMountHandler.consolePluginPrefix + ColorCode.ANSI_WHITE + "Creating directory [" + name + "]");
                return newFolder;
            }
            return null;
        }
    }
}
