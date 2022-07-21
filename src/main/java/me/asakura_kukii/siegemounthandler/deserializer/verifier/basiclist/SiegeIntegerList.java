package me.asakura_kukii.siegemounthandler.deserializer.verifier.basiclist;

import me.asakura_kukii.siegemounthandler.deserializer.verifier.basic.SiegeInteger;
import me.asakura_kukii.siegemounthandler.deserializer.verifier.common.Verifier;
import org.bukkit.configuration.ConfigurationSection;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SiegeIntegerList extends Verifier {
    public SiegeIntegerList() {}

    @Override
    public Object verify(ConfigurationSection cS, String fileName, String path, String root, Object obj, File folder) {
        List<String> sL = cS.getStringList(path);

        List<java.lang.Integer> iL = new ArrayList<>();
        for (String s : sL) {
            java.lang.Integer i = (java.lang.Integer) SiegeInteger.verifyInteger(s, fileName, path, root, obj);
            iL.add(i);
        }
        return iL;
    }
}
