package me.asakura_kukii.siegemounthandler;

import me.asakura_kukii.siegemounthandler.deserializer.loader.common.Loader;
import me.asakura_kukii.siegemounthandler.utility.listener.SiegeMountHandlerListener;
import me.asakura_kukii.siegemounthandler.utility.nms.ProtocolLibHandler;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.util.HashMap;

import me.asakura_kukii.siegemounthandler.handler.MountHandler;

public class SiegeMountHandler extends JavaPlugin {
	public static Server server = null;
	public static Plugin pluginInstance = null;
	public static File pluginFolder = null;
	public static String pluginName = null;
	public static String pluginPrefix = null;
	public static String consolePluginPrefix = null;
	public static HashMap<Plugin, BukkitTask> updaterRegister = new HashMap<>();

	public static void SiegeMountHandlerListenerRegister(Plugin p) {
		Bukkit.getPluginManager().registerEvents(new SiegeMountHandlerListener(), p);
	}

	public static void SiegeMountHandlerLoader(Server s, File pF, String pN, String pP, String cPP, Plugin p) {
		server = s;
		pluginInstance = p;
		pluginFolder = pF;
		pluginName = pN;
		pluginPrefix = pP;
		consolePluginPrefix = cPP;

		Loader.loadAll();

		ProtocolLibHandler.initProtocolLibHandler();

		updater();
	}

	public static void updater() {
		if (updaterRegister.containsKey(pluginInstance)) {
			updaterRegister.get(pluginInstance).cancel();
			updaterRegister.remove(pluginInstance);
		}
		updaterRegister.put(pluginInstance, new BukkitRunnable() {

			@Override
			public void run() {

				for (Player p : Bukkit.getOnlinePlayers()) {
					me.asakura_kukii.siegemounthandler.handler.MountHandler.orientMount(p);
				}


			}
		}.runTaskTimer(me.asakura_kukii.siegemounthandler.SiegeMountHandler.pluginInstance, 0, 0));
	}

}