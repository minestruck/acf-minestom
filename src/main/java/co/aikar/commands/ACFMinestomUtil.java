package co.aikar.commands;

import net.kyori.adventure.text.Component;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandSender;
import net.minestom.server.entity.Player;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ACFMinestomUtil {

	public static Component color(String message) {
		return Component.text(message.replace("&", "\u00a7"));
	}

	public static Player findPlayerSmart(CommandIssuer issuer, String search) {
		CommandSender requester = issuer.getIssuer();
		if (search == null) {
			return null;
		}
		String name = ACFUtil.replace(search, ":confirm", "");

		if (!isValidName(name)) {
			issuer.sendError(MinecraftMessageKeys.IS_NOT_A_VALID_NAME, "{name}", name);
			return null;
		}

		List<Player> matches = matchPlayer(name);

		if (matches.size() > 1) {
			String allMatches = matches.stream().map(Player::getUsername).collect(Collectors.joining(", "));
			issuer.sendError(MinecraftMessageKeys.MULTIPLE_PLAYERS_MATCH,
					"{search}", name, "{all}", allMatches);
			return null;
		}

		//noinspection Duplicates
		if (matches.isEmpty()) {
			issuer.sendError(MinecraftMessageKeys.NO_PLAYER_FOUND_SERVER, "{search}", name);
			return null;
		}

		return matches.get(0);
	}

	private static List<Player> matchPlayer(String query) {
		List<Player> players = new ArrayList<>();
		for (Player player : MinecraftServer.getConnectionManager().getOnlinePlayers()) {
			if (player.getUsername().contains(query)) {
				players.add(player);
			}
		}
		return players;
	}

	public static boolean isValidName(String name) {
		return name != null && !name.isEmpty() && ACFPatterns.VALID_NAME_PATTERN.matcher(name).matches();
	}

	static boolean isValidItem(ItemStack item) {
		return item != null && item.getMaterial() != Material.AIR && item.getAmount() > 0;
	}


}
