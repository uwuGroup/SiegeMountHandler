package me.asakura_kukii.siegemounthandler.deserializer.verifier.common;

import me.asakura_kukii.siegemounthandler.deserializer.loader.common.Loader;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;

import java.io.File;
import java.lang.reflect.Field;

public abstract class Verifier {
    public abstract Object verify(ConfigurationSection cS, String fileName, String path, String root, Object obj, File folder);

    public static Object get(ConfigurationSection cS, String fileName, String path, Object obj, VerifierType lT, boolean notEmpty, File folder) {
        String root = cS.getCurrentPath() + ".";
        if (root.matches(".")) {
            root = "";
        }
        if (cS.contains(path)) {
            String s = cS.getString(path);
            assert s != null;
            if (s.matches("")) {
                if (notEmpty) {
                    Loader.fileStatusMapper.put(fileName, false);
                    Loader.fileMessageMapper.put(fileName, Loader.fileMessageMapper.get(fileName) + me.asakura_kukii.siegemounthandler.SiegeMountHandler.consolePluginPrefix + root + path + " is empty\n");
                } else {
                    Loader.fileMessageMapper.put(fileName, Loader.fileMessageMapper.get(fileName) + me.asakura_kukii.siegemounthandler.SiegeMountHandler.consolePluginPrefix + root + path + " is empty, neglected\n");
                }
                return obj;
            } else {
                return lT.f.verify(cS, fileName, path, root, obj, folder);
            }
        } else {
            if (notEmpty) {
                Loader.fileStatusMapper.put(fileName, false);
                Loader.fileMessageMapper.put(fileName, Loader.fileMessageMapper.get(fileName) + me.asakura_kukii.siegemounthandler.SiegeMountHandler.consolePluginPrefix + root + path + " is missing\n");
            } else {
                Loader.fileMessageMapper.put(fileName, Loader.fileMessageMapper.get(fileName) + me.asakura_kukii.siegemounthandler.SiegeMountHandler.consolePluginPrefix + root + path + " is missing, neglected\n");
            }
            return obj;
        }
    }
}
