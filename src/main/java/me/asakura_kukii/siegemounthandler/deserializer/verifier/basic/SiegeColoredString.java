package me.asakura_kukii.siegemounthandler.deserializer.verifier.basic;

import me.asakura_kukii.siegemounthandler.deserializer.verifier.common.Verifier;
import me.asakura_kukii.siegemounthandler.deserializer.verifier.common.VerifierType;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;

import java.io.File;

public class SiegeColoredString extends Verifier {

    public SiegeColoredString() {}

    @Override
    public Object verify(ConfigurationSection cS, String fileName, String path, String root, Object obj, File folder) {
        String s = (String) VerifierType.STRING.f.verify(cS, fileName, path, root, obj, folder);
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
