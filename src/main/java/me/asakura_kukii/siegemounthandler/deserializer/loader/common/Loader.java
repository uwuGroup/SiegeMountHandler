package me.asakura_kukii.siegemounthandler.deserializer.loader.common;

import me.asakura_kukii.siegemounthandler.data.FileData;
import me.asakura_kukii.siegemounthandler.deserializer.verifier.common.Verifier;
import me.asakura_kukii.siegemounthandler.deserializer.verifier.common.VerifierType;
import me.asakura_kukii.siegemounthandler.utility.FileHandler;
import me.asakura_kukii.siegemounthandler.utility.colorcode.ColorCode;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.*;

public abstract class Loader {
    public static HashMap<String, Boolean> fileStatusMapper = new HashMap<>();
    public static HashMap<String, String> fileMessageMapper = new HashMap<>();
    public static List<String> invalidFileNameList = new ArrayList<>();

    public static void loadAll() {
        fileStatusMapper.clear();
        fileMessageMapper.clear();
        invalidFileNameList.clear();

        for(int i = 0; i < LoaderType.values().length; i++) {
            LoaderType lT = LoaderType.values()[i];

            lT.map.clear();
            lT.subMap.clear();
            if (lT.folder.exists()) {
                lT.fIO.loadDefault(lT);
                for (File f : Objects.requireNonNull(lT.folder.listFiles())) {
                    if (f.getName().contains("yml")) {
                        FileConfiguration fC = YamlConfiguration.loadConfiguration(f);
                        fileStatusMapper.put(lT.folder.getName() + "." + f.getName(), true);
                        loadFile(fC, lT.folder.getName() + "." + f.getName(), lT, lT.folder);
                    }
                }
            } else {
                FileHandler.loadSubfolder(lT.folder.getName());
            }
        }
    }

    public static void loadFile(FileConfiguration fC, String fN, LoaderType lT, File folder) {
        fileMessageMapper.put(fN, "");
        String identifier = "";
        identifier = (String) Verifier.get(fC, fN, "identifier", identifier, VerifierType.STRING, true, folder);
        if (lT.map.containsKey(identifier)) {
            fileStatusMapper.put(fN, false);
            fileMessageMapper.put(fN, fileMessageMapper.get(fN) + me.asakura_kukii.siegemounthandler.SiegeMountHandler.consolePluginPrefix + "identifier [" + identifier + "] is used\n");
        }
        if (fileStatusMapper.get(fN)) {
            fileMessageMapper.put(fN, fileMessageMapper.get(fN) + me.asakura_kukii.siegemounthandler.SiegeMountHandler.consolePluginPrefix + "Loading file [" + fN + "]\n");
            //goto specific loader
            FileData fD = lT.fIO.loadData(fC, fN, lT, identifier, folder);
            HashMap<String, Object> subMap = lT.fIO.loadSubData(fC, fN, lT, identifier, folder);
            if (fD != null) {
                fD.fT = lT;
            }
            if (fileStatusMapper.get(fN)) {
                if (fD != null) {
                    lT.map.put(identifier,fD);
                }
                if (subMap != null) {
                    lT.subMap.putAll(subMap);
                }

                me.asakura_kukii.siegemounthandler.SiegeMountHandler.server.getConsoleSender().sendMessage(ColorCode.ANSI_GREEN + me.asakura_kukii.siegemounthandler.SiegeMountHandler.consolePluginPrefix + ColorCode.ANSI_WHITE + "Loaded " + lT.name().toLowerCase(Locale.ROOT) + " [" + identifier + "]");
            } else {
                invalidFileNameList.add(fN);
                me.asakura_kukii.siegemounthandler.SiegeMountHandler.server.getConsoleSender().sendMessage(ColorCode.ANSI_GREEN + me.asakura_kukii.siegemounthandler.SiegeMountHandler.consolePluginPrefix + ColorCode.ANSI_RED + "Failed when loading " + lT.name().toLowerCase(Locale.ROOT) + " [" + identifier + "]\n\n" + fileMessageMapper.get(fN) + ColorCode.ANSI_WHITE);
            }
        }
    }
    public abstract FileData loadData(FileConfiguration fC, String fN, LoaderType fT, String identifier, File folder);

    public abstract HashMap<String, Object> loadSubData(FileConfiguration fC, String fN, LoaderType fT, String identifier, File folder);

    public abstract void loadDefault(LoaderType fT);
}
