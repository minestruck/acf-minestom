package co.aikar.commands;

import net.kyori.adventure.text.format.NamedTextColor;

import java.util.HashMap;
import java.util.Map;

public class MinestomMessageFormatter extends MessageFormatter<NamedTextColor> {

    private static Map<NamedTextColor, String> colourMap = new HashMap<>();
    static {
        colourMap.put(NamedTextColor.BLACK, "\u00a70");
        colourMap.put(NamedTextColor.DARK_BLUE, "\u00a71");
        colourMap.put(NamedTextColor.DARK_GREEN, "\u00a72");
        colourMap.put(NamedTextColor.DARK_AQUA, "\u00a73");
        colourMap.put(NamedTextColor.DARK_RED, "\u00a74");
        colourMap.put(NamedTextColor.DARK_PURPLE, "\u00a75");
        colourMap.put(NamedTextColor.GOLD, "\u00a76");
        colourMap.put(NamedTextColor.GRAY, "\u00a77");
        colourMap.put(NamedTextColor.DARK_GRAY, "\u00a78");
        colourMap.put(NamedTextColor.BLUE, "\u00a79");
        colourMap.put(NamedTextColor.GREEN, "\u00a7a");
        colourMap.put(NamedTextColor.AQUA, "\u00a7b");
        colourMap.put(NamedTextColor.RED, "\u00a7c");
        colourMap.put(NamedTextColor.LIGHT_PURPLE, "\u00a7d");
        colourMap.put(NamedTextColor.YELLOW, "\u00a7e");
        colourMap.put(NamedTextColor.WHITE, "\u00a7f");
    }

    public MinestomMessageFormatter(NamedTextColor... colors) {
        super(colors);
    }

    @Override
    String format(NamedTextColor color, String message) {
        return colourMap.get(color) + message;
    }

}
