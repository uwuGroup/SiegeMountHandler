package me.asakura_kukii.siegemounthandler.deserializer.verifier.basiclist;

import me.asakura_kukii.siegemounthandler.deserializer.verifier.common.Verifier;
import org.bukkit.configuration.ConfigurationSection;

import java.io.File;

public class SiegeStringList extends Verifier {

    public SiegeStringList() {}

    @Override
    public Object verify(ConfigurationSection cS, String fileName, String path, String root, Object obj, File folder) {
        return cS.getStringList(path);
    }
}
