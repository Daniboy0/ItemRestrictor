package dev.daniboy.itemrestrictor;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

public class ItemRestrictionListener implements Listener {

	private final Plugin plugin;

	public ItemRestrictionListener(Plugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		World world = player.getWorld();
		String worldName = world.getName();

		// Check if the player is using a restricted item
		if (event.getItem() != null && isItemRestricted(event.getItem().getType().toString(), worldName)) {
			String message = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("worlds." + worldName + ".restriction_message"));
			player.sendMessage(message);
			event.setCancelled(true); // Cancel the event to prevent usage
		}
	}

	private boolean isItemRestricted(String item, String world) {
		return plugin.getConfig().getStringList("worlds." + world + ".restricted_items").contains(item);
	}
}
