package me.asakura_kukii.siegemounthandler.deserializer.verifier.basiclist;

import me.asakura_kukii.siegemounthandler.deserializer.verifier.common.Verifier;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SiegeColoredStringList extends Verifier {

    public SiegeColoredStringList() {}

    @Override
    public Object verify(ConfigurationSection cS, String fileName, String path, String root, Object obj, File folder) {
        List<String> sL = cS.getStringList(path);

        List<String> cSL = new ArrayList<>();
        for (String s : sL) {
            cSL.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        return cSL;
    }
}
