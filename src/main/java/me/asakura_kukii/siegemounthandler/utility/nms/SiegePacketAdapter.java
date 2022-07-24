package me.asakura_kukii.siegemounthandler.utility.nms;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerOptions;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import me.asakura_kukii.siegemounthandler.SiegeMountHandler;
import me.asakura_kukii.siegemounthandler.handler.MountHandler;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

public class SiegePacketAdapter extends PacketAdapter {

    public SiegePacketAdapter() {
        super(SiegeMountHandler.pluginInstance, ListenerPriority.HIGH,
                Arrays.asList(
                        PacketType.Play.Client.LOOK, PacketType.Play.Client.POSITION_LOOK, PacketType.Play.Client.POSITION
                ),
                ListenerOptions.ASYNC
        );
    }

    @Override
    public void onPacketSending(PacketEvent event) {
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(event.getPacketType().name());
        new BukkitRunnable(){
            @Override
            public void run() {
                MountHandler.orientMount(player);
            }
        }.runTask(SiegeMountHandler.pluginInstance);
    }
}
