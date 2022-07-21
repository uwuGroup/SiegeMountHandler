package me.asakura_kukii.siegemounthandler.deserializer.loader.plugin;

import me.asakura_kukii.siegemounthandler.data.ConfigData;
import me.asakura_kukii.siegemounthandler.data.FileData;
import me.asakura_kukii.siegemounthandler.deserializer.loader.common.Loader;
import me.asakura_kukii.siegemounthandler.deserializer.loader.common.LoaderType;
import me.asakura_kukii.siegemounthandler.deserializer.verifier.common.Verifier;
import me.asakura_kukii.siegemounthandler.deserializer.verifier.common.VerifierType;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ConfigLoader extends Loader {

    public static Class<ConfigData> dataClass = ConfigData.class;
    public static Constructor<ConfigData> constructor;

    public enum Map {
        REFRESH_DELAY("refreshDelay", (int) 0, VerifierType.INTEGER, true);

        public String path;
        public Object o;
        public VerifierType vT;
        public boolean nE;


        Map(String path, Object o, VerifierType vT, Boolean nE) {
            this.path = path;
            this.o = o;
            this.vT = vT;
            this.nE = nE;
        }
    }

    public ConfigLoader() {}

    static {
        try {
            constructor = dataClass.getConstructor(String.class, String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static Field verifyField(ConfigurationSection cS, String fileName, String path) {
        String root = cS.getCurrentPath() + ".";
        try {
            return dataClass.getField(path);
        } catch (Exception ignored) {
            Loader.fileStatusMapper.put(fileName, false);
            Loader.fileMessageMapper.put(fileName, Loader.fileMessageMapper.get(fileName) + me.asakura_kukii.siegemounthandler.SiegeMountHandler.consolePluginPrefix + root + path + " is missing field\n");
        }
        return null;
    }

    @Override
    public FileData loadData(FileConfiguration fC, String fN, LoaderType lT, String identifier, File folder) {
        FileData dataObject = null;
        try {
            dataObject = constructor.newInstance(identifier, fN);
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        try {

            for (Map m : Map.values()) {
                if (verifyField(fC, fN, m.path) != null) {
                    verifyField(fC, fN, m.path).set(dataObject, Verifier.get(fC, fN, m.path, m.o, m.vT, m.nE, folder));
                }
            }
            return dataObject;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return dataObject;
    }

    @Override
    public HashMap<String, Object> loadSubData(FileConfiguration fC, String fN, LoaderType lT, String identifier, File folder) {
        return null;
    }

    @Override
    public void loadDefault(LoaderType lT) {
    }
}
