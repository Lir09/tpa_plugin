import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class Tpa extends JavaPlugin implements CommandExecutor, Listener {

    private final Map<UUID, UUID> teleportRequests = new HashMap<>();

    @Override
    public void onEnable() {
        getLogger().info("Plugin enabled!");
        getCommand("tpa").setExecutor(this);
        getCommand("tpal").setExecutor(this);
        getCommand("tpaccept").setExecutor(this);
        getCommand("tpadeny").setExecutor(this);
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled!");
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        // Player teleport event handling can be added here
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command cannot be used from the console!");
            return true;
        }

        Player player = (Player) sender;

        String commandName = cmd.getName().toLowerCase();

        if (commandName.equals("tpa")) {
            if (args.length < 1) {
                player.sendMessage(ChatColor.RED + "Usage: /tpa [player]");
                return false;
            }

            Player target = Bukkit.getServer().getPlayer(args[0]);
            if (target == null || !target.isOnline() || player.equals(target)) {
                player.sendMessage(ChatColor.BLUE + "[TPA] " + ChatColor.RED + args[0] + " is either offline or the same as yourself!");
                return false;
            }

            Location loc_target = target.getLocation();
            player.sendMessage(ChatColor.BLUE + "[TPA] " + ChatColor.GREEN + "Teleport request sent to " + target.getName() + ". To accept, type '/tpaccept " + player.getName() + "', or to deny, type '/tpadeny " + player.getName() + "'.");
            target.sendMessage(ChatColor.BLUE + "[TPA] " + ChatColor.GREEN + player.getName() + " has sent you a teleport request. To accept, type '/tpaccept " + player.getName() + "', or to deny, type '/tpadeny " + player.getName() + "'.");

            teleportRequests.put(player.getUniqueId(), target.getUniqueId());
        } else if (commandName.equals("tpal")) {
            if (!player.isOp()) {
                player.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
                return false;
            }

            if (args.length < 1) {
                player.sendMessage(ChatColor.RED + "Usage: /tpal [player]");
                return false;
            }

            Player target = Bukkit.getServer().getPlayer(args[0]);
            if (target == null || !target.isOnline()) {
                player.sendMessage(ChatColor.BLUE + "[TPA] " + ChatColor.RED + args[0] + " is offline!");
                return false;
            }

            Location loc_target = target.getLocation();
            player.sendMessage(ChatColor.BLUE + "[TPA] " + ChatColor.GREEN + target.getName() + "'s coordinates are: "
                    + ChatColor.YELLOW + loc_target.getBlockX() + ", " + loc_target.getBlockY() + ", "
                    + loc_target.getBlockZ());
        } else if (commandName.equals("tpaccept")) {
            if (args.length < 1) {
                player.sendMessage(ChatColor.RED + "Usage: /tpaccept [player]");
                return false;
            }

            Player requester = Bukkit.getServer().getPlayer(args[0]);
            if (requester == null || !requester.isOnline()) {
                player.sendMessage(ChatColor.BLUE + "[TPA] " + ChatColor.RED + args[0] + " is offline!");
                return false;
            }

            if (teleportRequests.containsKey(requester.getUniqueId()) && teleportRequests.get(requester.getUniqueId()).equals(player.getUniqueId())) {
                player.sendMessage(ChatColor.BLUE + "[TPA] " + ChatColor.GREEN + "You have accepted the teleport request from " + requester.getName() + ".");
                requester.sendMessage(ChatColor.BLUE + "[TPA] " + ChatColor.GREEN + player.getName() + " has accepted your teleport request.");
                requester.teleport(player.getLocation());
                teleportRequests.remove(requester.getUniqueId());
            } else {
                player.sendMessage(ChatColor.BLUE + "[TPA] " + ChatColor.RED + "No teleport request found from " + requester.getName() + ".");
            }
        } else if (commandName.equals("tpadeny")) {
            if (args.length < 1) {
                player.sendMessage(ChatColor.RED + "Usage: /tpadeny [player]");
                return false;
            }

            Player requester = Bukkit.getServer().getPlayer(args[0]);
            if (requester == null || !requester.isOnline()) {
                player.sendMessage(ChatColor.BLUE + "[TPA] " + ChatColor.RED + args[0] + " is offline!");
                return false;
            }

            if (teleportRequests.containsKey(requester.getUniqueId()) && teleportRequests.get(requester.getUniqueId()).equals(player.getUniqueId())) {
                player.sendMessage(ChatColor.BLUE + "[TPA] " + ChatColor.RED + "You have denied the teleport request from " + requester.getName() + ".");
                requester.sendMessage(ChatColor.BLUE + "[TPA] " + ChatColor.RED + player.getName() + " has denied your teleport request.");
                teleportRequests.remove(requester.getUniqueId());
            } else {
                player.sendMessage(ChatColor.BLUE + "[TPA] " + ChatColor.RED + "No teleport request found from " + requester.getName() + ".");
            }
        }

        return true;
    }
}
