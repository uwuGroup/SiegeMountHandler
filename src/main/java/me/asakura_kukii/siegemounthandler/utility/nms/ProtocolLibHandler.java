package me.asakura_kukii.siegemounthandler.utility.nms;

import org.bukkit.Bukkit;
import com.comphenix.protocol.*;

public class ProtocolLibHandler {
    private static ProtocolManager protocolManager;

    public static void initProtocolLibHandler() {
        if (Bukkit.getPluginManager().isPluginEnabled("ProtocolLib")) {
            if (protocolManager == null) {
                protocolManager = ProtocolLibrary.getProtocolManager();
            }
            protocolManager.removePacketListeners(me.asakura_kukii.siegemounthandler.SiegeMountHandler.pluginInstance);
            protocolManager.addPacketListener(new SiegePacketAdapter());
            protocolManager.addPacketListener(new LoadChunkHandler());
        }
    }
}