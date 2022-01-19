package co.aikar.commands;

import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandSender;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.Player;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MinestomCommandCompletions extends CommandCompletions<MinestomCommandCompletionContext> {

	public MinestomCommandCompletions(MinestomCommandManager manager) {
		super(manager);
		registerAsyncCompletion("mobs", c -> {
			final Stream<String> normal = EntityType.values().stream()
					.map(entityType -> ACFUtil.simplifyString(entityType.name()));
			return normal.collect(Collectors.toList());
		});
		registerCompletion("players", c -> {
			CommandSender sender = c.getSender();
			if (sender == null) {
				throw new RuntimeException("Sender cannot be null");
			}

			ArrayList<String> matchedPlayers = new ArrayList<>();
			for (Player player : MinecraftServer.getConnectionManager().getOnlinePlayers()) {
				String name = player.getUsername();
				if (name.toLowerCase().startsWith(c.getInput().toLowerCase())) {
					matchedPlayers.add(name);
				}
			}

			matchedPlayers.sort(String.CASE_INSENSITIVE_ORDER);
			matchedPlayers.add(0, "@p");
			return matchedPlayers;
		});
	}
}
