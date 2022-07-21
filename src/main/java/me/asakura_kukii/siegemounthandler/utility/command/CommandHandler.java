package me.asakura_kukii.siegemounthandler.utility.command;

import me.asakura_kukii.siegemounthandler.SiegeMountHandler;
import me.asakura_kukii.siegemounthandler.deserializer.loader.common.Loader;
import me.asakura_kukii.siegemounthandler.deserializer.loader.common.LoaderType;
import me.asakura_kukii.siegemounthandler.main.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class CommandHandler {
    public static boolean completeString(String arg, String startsWith) {
        if (arg == null || startsWith == null)
            return false;
        return arg.toLowerCase().startsWith(startsWith.toLowerCase());
    }

    public static boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("");
        sender.sendMessage(SiegeMountHandler.pluginPrefix + "Issued command:");
        sender.sendMessage(ChatColor.WHITE + ">> " + commandPainter(args));
        Arguments arguments = new Arguments(args);

        switch (arguments.nextString()) {
            case "reload":
                handleReload(sender, arguments);
                break;
            default:

                return true;
        }

        return true;
    }

    public static void handleReload(CommandSender sender, Arguments arguments) {
        if (sender instanceof Player) {
            if (sender.hasPermission(SiegeMountHandler.pluginName + ".reload")) {
                Main.getInstance().reloadConfig();
                Main.getInstance().reloadValues();
                sender.sendMessage(ChatColor.GRAY + ">> " + ChatColor.WHITE + "Plugin reloaded");
            } else {
                sender.sendMessage(SiegeMountHandler.pluginPrefix + ChatColor.RED + "Permission error - Missing permission");
            }
        } else {
            Main.getInstance().reloadConfig();
            Main.getInstance().reloadValues();
            sender.sendMessage(SiegeMountHandler.pluginPrefix + "Plugin reloaded");
        }
    }


    public static void listFile(CommandSender sender, LoaderType fT) {
        if (fT.map.keySet().size() == 0) {
            sender.sendMessage(ChatColor.GRAY + ">> " + ChatColor.WHITE + "NO ITEM");
        } else if (fT.map.keySet().size() == 1) {
            sender.sendMessage(ChatColor.GRAY + ">> " + ChatColor.WHITE + "" + fT.map.size() + " ITEM LISTED:");
        } else {
            sender.sendMessage(ChatColor.GRAY + ">> " + ChatColor.WHITE + "" + fT.map.size() + " ITEMS LISTED:");
        }
        for (String s : fT.map.keySet()) {
            sender.sendMessage(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY + " FILE_NAME: " + ChatColor.WHITE + fT.map.get(s).fileName + ChatColor.GRAY + " NAME: " + ChatColor.WHITE + fT.map.get(s).identifier);
        }
    }

    public static void listInvalid(CommandSender sender) {
        if (Loader.invalidFileNameList.size() == 0) {
            sender.sendMessage(ChatColor.GRAY + ">> " + ChatColor.WHITE + "NO ITEM");
        } else if (Loader.invalidFileNameList.size() == 1) {
            sender.sendMessage(ChatColor.GRAY + ">> " + ChatColor.WHITE + "" + Loader.invalidFileNameList.size() + " ITEM LISTED:");
        } else {
            sender.sendMessage(ChatColor.GRAY + ">> " + ChatColor.WHITE + "" + Loader.invalidFileNameList.size() + " ITEMS LISTED:");
        }
        for (String s : Loader.invalidFileNameList) {
            sender.sendMessage(ChatColor.DARK_GRAY + ">> " + ChatColor.GRAY + " FILE_NAME: " + ChatColor.WHITE + s);
        }
    }

    /*
    public static void giveItem(CommandSender sender, String[] args, LoaderType fT) {
        if (args.length>=4) {
            if (fT.map.containsKey(args[3])) {
                if (args.length>=5) {
                    if (Bukkit.getPlayer(args[4])!=null && Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[4]))) {
                        Player p = Bukkit.getPlayer(args[4]);
                        assert p != null;
                        PlayerData pD = PlayerHandler.getPlayerData(p);
                        if (args.length>=6) {
                            try {
                                int amount = Integer.parseInt(args[5]);
                                if (args.length>=7) {
                                    try {
                                        int index = Integer.parseInt(args[6]);
                                        if (args.length>=8) {
                                            try {
                                                int level = Integer.parseInt(args[7]);
                                                if (level >= 1 && level <= 30) {
                                                    ItemData.sendItemStack((ItemData) fT.map.get(args[3]),pD,amount,index,level);
                                                    sender.sendMessage(ChatColor.GRAY + ">> " + ChatColor.WHITE + "SEND ITEM TO SLOT WITH LEVEL");
                                                } else {
                                                    sender.sendMessage(me.asakura_kukii.siegemounthandler.SiegeMountHandler.pluginPrefix + ChatColor.RED + "VARIABLE ERROR - Invalid level");
                                                }
                                            } catch (Exception ignored) {
                                                sender.sendMessage(me.asakura_kukii.siegemounthandler.SiegeMountHandler.pluginPrefix + ChatColor.RED + "VARIABLE ERROR - Invalid level");
                                            }
                                        } else {
                                            ItemData.sendItemStack((ItemData) fT.map.get(args[3]),pD,amount,index);
                                            sender.sendMessage(ChatColor.GRAY + ">> " + ChatColor.WHITE + "SEND ITEM TO SLOT");
                                        }
                                    } catch (Exception ignored) {
                                        sender.sendMessage(me.asakura_kukii.siegemounthandler.SiegeMountHandler.pluginPrefix + ChatColor.RED + "VARIABLE ERROR - Invalid slot");
                                    }
                                } else {
                                    ItemData.sendItemStack((ItemData) fT.map.get(args[3]),pD,amount);
                                    sender.sendMessage(ChatColor.GRAY + ">> " + ChatColor.WHITE + "SEND ITEM");
                                }
                            } catch (Exception ignored) {
                                sender.sendMessage(me.asakura_kukii.siegemounthandler.SiegeMountHandler.pluginPrefix + ChatColor.RED + "VARIABLE ERROR - Invalid amount");
                            }
                        } else {
                            ItemData.sendItemStack((ItemData) fT.map.get(args[3]),pD,1);
                            sender.sendMessage(ChatColor.GRAY + ">> " + ChatColor.WHITE + "SEND ITEM");
                        }
                    } else {
                        sender.sendMessage(me.asakura_kukii.siegemounthandler.SiegeMountHandler.pluginPrefix + ChatColor.RED + "VARIABLE ERROR - Invalid player");
                    }
                } else {
                    if (sender instanceof Player) {
                        Player p = (Player) sender;
                        PlayerData pD = PlayerHandler.getPlayerData(p);
                        ItemData.sendItemStack((ItemData) fT.map.get(args[3]),pD,1);
                        sender.sendMessage(ChatColor.GRAY + ">> " + ChatColor.WHITE + "SEND ITEM");
                    } else {
                        sender.sendMessage(me.asakura_kukii.siegemounthandler.SiegeMountHandler.pluginPrefix + ChatColor.RED + "VARIABLE ERROR - Invalid player");
                    }
                }
            } else {
                sender.sendMessage(me.asakura_kukii.siegemounthandler.SiegeMountHandler.pluginPrefix + ChatColor.RED + "VARIABLE ERROR - Invalid item name");
            }
        } else {
            sender.sendMessage(me.asakura_kukii.siegemounthandler.SiegeMountHandler.pluginPrefix + ChatColor.RED + "VARIABLE ERROR - Missing item name");
        }
    }*/

    public static List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> s = new ArrayList<String>();
        switch (args.length) {
            case 1:
                if (completeString("reload", args[0])) s.add("reload");
                break;
            default:
                break;
        }
        return s;
    }

    public static String commandPainter(String[] args) {
        StringBuilder cSB = new StringBuilder();
        cSB.append(ChatColor.GRAY).append("/sw ");
        int cSBI = 0;
        for (String s : args) {
            if(cSBI == 0) {
                cSB.append(ChatColor.RED);
            }
            if(cSBI == 1) {
                cSB.append(ChatColor.WHITE);
            }
            if(cSBI == 2) {
                cSB.append(ChatColor.GOLD);
            }
            if(cSBI == 3) {
                cSB.append(ChatColor.LIGHT_PURPLE);
            }
            if(cSBI == 4) {
                cSB.append(ChatColor.AQUA);
            }
            if(cSBI == 5) {
                cSB.append(ChatColor.GREEN);
            }
            if(cSBI == 6) {
                cSB.append(ChatColor.BLUE);
            }
            if(cSBI == 7) {
                cSB.append(ChatColor.DARK_RED);
            }
            cSB.append(s).append(" ");
            cSBI++;
        }
        return cSB.toString();
    }
}
