package me.asakura_kukii.siegemounthandler.deserializer.verifier.basiclist;

import me.asakura_kukii.siegemounthandler.deserializer.verifier.common.Verifier;
import me.asakura_kukii.siegemounthandler.deserializer.verifier.basic.SiegeDouble;
import org.bukkit.configuration.ConfigurationSection;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SiegeDoubleList extends Verifier {
    public SiegeDoubleList() {}

    @Override
    public Object verify(ConfigurationSection cS, String fileName, String path, String root, Object obj, File folder) {
        List<String> sL = cS.getStringList(path);

        List<java.lang.Double> dL = new ArrayList<>();
        for (String s : sL) {
            java.lang.Double d = (java.lang.Double) SiegeDouble.verifyDouble(s, fileName, path, root, obj);
            dL.add(d);
        }
        return dL;
    }
}
