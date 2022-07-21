package me.asakura_kukii.siegemounthandler.deserializer.verifier.basic;

import me.asakura_kukii.siegemounthandler.deserializer.verifier.common.Verifier;
import org.bukkit.configuration.ConfigurationSection;

import java.io.File;

public class SiegeString extends Verifier {

    public SiegeString() {}

    @Override
    public Object verify(ConfigurationSection cS, String fileName, String path, String root, Object obj, File folder) {
        return cS.getString(path);
    }
}
