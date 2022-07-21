package me.asakura_kukii.siegemounthandler.deserializer.verifier.basic;

import me.asakura_kukii.siegemounthandler.deserializer.loader.common.Loader;
import me.asakura_kukii.siegemounthandler.deserializer.verifier.common.Verifier;
import me.asakura_kukii.siegemounthandler.deserializer.verifier.common.VerifierType;
import org.bukkit.configuration.ConfigurationSection;

import java.io.File;

public class SiegeBoolean extends Verifier {

    public SiegeBoolean() {}

    @Override
    public Object verify(ConfigurationSection cS, String fileName, String path, String root, Object obj, File folder) {
        String s = (String) VerifierType.STRING.f.verify(cS, fileName, path, root, obj, folder);
        return verifyBoolean(s, fileName, path, root, obj);
    }

    public static Object verifyBoolean(String s, String fileName, String path, String root, Object obj) {
        if (s.matches("true") || s.matches("false")) {
            return java.lang.Boolean.parseBoolean(s);
        } else {
            Loader.fileStatusMapper.put(fileName, false);
            Loader.fileMessageMapper.put(fileName, Loader.fileMessageMapper.get(fileName) + me.asakura_kukii.siegemounthandler.SiegeMountHandler.consolePluginPrefix + root + path + "-" + s + " is not SiegeBoolean\n");
            return obj;
        }
    }
}
