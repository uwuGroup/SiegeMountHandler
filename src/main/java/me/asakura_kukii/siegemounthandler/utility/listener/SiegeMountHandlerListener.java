package me.asakura_kukii.siegemounthandler.utility.listener;

import me.asakura_kukii.siegemounthandler.handler.MountHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class SiegeMountHandlerListener implements org.bukkit.event.Listener {
    public static HashMap<String, BukkitTask> holdDetectorForContinuousTrigger = new HashMap<>();
    public static List<UUID> inputBlockMap = new ArrayList<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        MountHandler.refreshMount(p);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        MountHandler.refreshMount(p);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
    }

    @EventHandler
    public void onGlide(EntityToggleGlideEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
        }
    }

    @EventHandler
    public void onSprint(PlayerToggleSprintEvent e) {
        Player p = (Player) e.getPlayer();
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {
        Player p = e.getPlayer();
    }

    @EventHandler
    public void onSwap(PlayerSwapHandItemsEvent e) {
        Player p = (Player) e.getPlayer();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player p = (Player) e.getWhoClicked();
        }
    }

    @EventHandler
    public void onHeld(PlayerItemHeldEvent e) {
        Player p = (Player) e.getPlayer();
    }

    @EventHandler
    public void onManipulateArmorStand(PlayerArmorStandManipulateEvent e) {
        Player p = (Player) e.getPlayer();
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
    }

    @EventHandler
    public void onInteractEntity(PlayerInteractAtEntityEvent e) {
        Player p = e.getPlayer();
    }
}

