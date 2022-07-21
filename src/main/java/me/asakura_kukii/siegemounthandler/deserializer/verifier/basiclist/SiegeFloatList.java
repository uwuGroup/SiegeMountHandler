package me.asakura_kukii.siegemounthandler.deserializer.verifier.basiclist;

import me.asakura_kukii.siegemounthandler.deserializer.verifier.common.Verifier;
import me.asakura_kukii.siegemounthandler.deserializer.verifier.basic.SiegeFloat;
import org.bukkit.configuration.ConfigurationSection;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SiegeFloatList extends Verifier {
    public SiegeFloatList() {}

    @Override
    public Object verify(ConfigurationSection cS, String fileName, String path, String root, Object obj, File folder) {
        List<String> sL = cS.getStringList(path);

        List<java.lang.Float> fL = new ArrayList<>();
        for (String s : sL) {
            java.lang.Float f = (java.lang.Float) SiegeFloat.verifyFloat(s, fileName, path, root, obj);
            fL.add(f);
        }
        return fL;
    }
}
