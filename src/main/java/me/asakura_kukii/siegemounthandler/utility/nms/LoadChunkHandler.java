package me.asakura_kukii.siegemounthandler.utility.nms;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerOptions;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.StructureModifier;
import me.asakura_kukii.siegemounthandler.SiegeMountHandler;
import me.asakura_kukii.siegemounthandler.handler.MountHandler;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;


public class LoadChunkHandler extends PacketAdapter {
    public LoadChunkHandler() {
        super(SiegeMountHandler.pluginInstance, ListenerPriority.HIGH,
                Arrays.asList(PacketType.Play.Server.MAP_CHUNK),
                ListenerOptions.ASYNC
                );
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {

    }

    @Override
    public void onPacketSending(PacketEvent event) {
        StructureModifier<Integer> integers = event.getPacket().getIntegers();
        Integer x = integers.read(0);
        Integer z = integers.read(1);
        new BukkitRunnable(){
            @Override
            public void run() {
                Player player = event.getPlayer();
                player.sendMessage(String.format("world: %s, x = %d, z = %d", player.getWorld().getName(), x, z));
                for (Player onlinePlayer : player.getWorld().getPlayers()) {
                    if (onlinePlayer.getLocation().getBlockX() / 16 == x && onlinePlayer.getLocation().getBlockZ() / 16 == z){
                        MountHandler.removeMount(onlinePlayer);
                        MountHandler.clearMountCache(onlinePlayer);
                        onlinePlayer.sendMessage("YOU ARE REMOVED");
                        new BukkitRunnable(){
                            @Override
                            public void run() {
                                MountHandler.orientMount(onlinePlayer);
                                MountHandler.orientMount(onlinePlayer);
                            }
                        }.runTaskLater(SiegeMountHandler.pluginInstance, 1);
                    }
                }
            }
        }.runTask(SiegeMountHandler.pluginInstance);
    }
}
