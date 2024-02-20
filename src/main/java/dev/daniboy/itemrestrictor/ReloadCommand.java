package dev.daniboy.itemrestrictor;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class ReloadCommand implements CommandExecutor {

	private final Plugin plugin;

	public ReloadCommand(Plugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equalsIgnoreCase("itemrestrictor") && args.length == 1 && args[0].equalsIgnoreCase("reload")) {
			if (sender.hasPermission("itemrestrictor.reloadconfig")) {
				plugin.reloadConfig();
				String reloadMessage = getConfigMessage("messages.reload_success");
				sender.sendMessage(reloadMessage);
				return true;
			} else {
				sender.sendMessage(getConfigMessage("messages.no_permission"));
				return false;
			}
		} else {
			sender.sendMessage(getConfigMessage("messages.reload_command_usage"));
			return false;
		}
	}

	private String getConfigMessage(String path) {
		String message = plugin.getConfig().getString(path);
		return ChatColor.translateAlternateColorCodes('&', message);
	}
}
