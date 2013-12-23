package com.RobertWebb38.SilenceMode;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SilenceMain extends JavaPlugin implements Listener {
	private boolean silencemode;
	
	public void onEnable() {
		getConfig().setDefaults(getConfig());
		getServer().getPluginManager().registerEvents(this, this);
	}
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("silence")) {
			if(cs.hasPermission("silence.toggle")) {
				if(silencemode) {
					silencemode = false;
					cs.sendMessage(ChatColor.GREEN + "Silece mode disabled!");
					return true;
				}
				silencemode = true;
				cs.sendMessage(ChatColor.RED + "Silence mode enabled!");
				return true;
				
			}
			cs.sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("permission-message")));
			return true;
		}
		return false;
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent event) {
		if(silencemode) {
			if(!event.getPlayer().hasPermission("silence.bypass")) {
				event.setCancelled(true);
				event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', getConfig().getString("silence-message")));
			}
		}
	}
}
