package me.asakura_kukii.siegemounthandler.deserializer.verifier.basiclist;

import me.asakura_kukii.siegemounthandler.deserializer.verifier.basic.SiegeBoolean;
import me.asakura_kukii.siegemounthandler.deserializer.verifier.common.Verifier;
import org.bukkit.configuration.ConfigurationSection;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SiegeBooleanList extends Verifier {
    public SiegeBooleanList() {}

    @Override
    public Object verify(ConfigurationSection cS, String fileName, String path, String root, Object obj, File folder) {
        List<String> sL = cS.getStringList(path);

        List<java.lang.Boolean> bL = new ArrayList<>();
        for (String s : sL) {
            java.lang.Boolean b = (java.lang.Boolean) SiegeBoolean.verifyBoolean(s, fileName, path, root, obj);
            bL.add(b);
        }
        return bL;
    }
}
