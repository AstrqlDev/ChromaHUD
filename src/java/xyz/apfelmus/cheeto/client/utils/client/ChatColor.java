/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  org.apache.commons.lang3.Validate
 */
package xyz.apfelmus.cheeto.client.utils.client;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.lang3.Validate;

public enum ChatColor {
    BLACK('0', 0),
    DARK_BLUE('1', 1),
    DARK_GREEN('2', 2),
    DARK_AQUA('3', 3),
    DARK_RED('4', 4),
    DARK_PURPLE('5', 5),
    GOLD('6', 6),
    GRAY('7', 7),
    DARK_GRAY('8', 8),
    BLUE('9', 9),
    GREEN('a', 10),
    AQUA('b', 11),
    RED('c', 12),
    LIGHT_PURPLE('d', 13),
    YELLOW('e', 14),
    WHITE('f', 15),
    MAGIC('k', 16, true),
    BOLD('l', 17, true),
    STRIKETHROUGH('m', 18, true),
    UNDERLINE('n', 19, true),
    ITALIC('o', 20, true),
    RESET('r', 21);

    public static final char COLOR_CHAR = '\u00a7';
    private static final Pattern STRIP_COLOR_PATTERN;
    private static final Map<Integer, ChatColor> BY_ID;
    private static final Map<Character, ChatColor> BY_CHAR;
    private final int intCode;
    private final char code;
    private final boolean isFormat;
    private final String toString;

    private ChatColor(char code, int intCode) {
        this(code, intCode, false);
    }

    private ChatColor(char code, int intCode, boolean isFormat) {
        this.code = code;
        this.intCode = intCode;
        this.isFormat = isFormat;
        this.toString = new String(new char[]{'\u00a7', code});
    }

    public static ChatColor getByChar(char code) {
        return BY_CHAR.get(Character.valueOf(code));
    }

    public static ChatColor getByChar(String code) {
        Validate.notNull((Object)code, (String)"Code cannot be null", (Object[])new Object[0]);
        Validate.isTrue((code.length() > 0 ? 1 : 0) != 0, (String)"Code must have at least one char", (Object[])new Object[0]);
        return BY_CHAR.get(Character.valueOf(code.charAt(0)));
    }

    public static String stripColor(String input) {
        if (input == null) {
            return null;
        }
        return STRIP_COLOR_PATTERN.matcher(input).replaceAll("");
    }

    public static String format(String textToTranslate) {
        char[] b = textToTranslate.toCharArray();
        for (int i = 0; i < b.length - 1; ++i) {
            if (b[i] != '&' || "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i + 1]) <= -1) continue;
            b[i] = 167;
            b[i + 1] = Character.toLowerCase(b[i + 1]);
        }
        return new String(b);
    }

    public static String getLastColors(String input) {
        StringBuilder result = new StringBuilder();
        int length = input.length();
        for (int index = length - 1; index > -1; --index) {
            char c;
            ChatColor color;
            char section = input.charAt(index);
            if (section != '\u00a7' || index >= length - 1 || (color = ChatColor.getByChar(c = input.charAt(index + 1))) == null) continue;
            result.insert(0, color.toString());
            if (color.isColor() || color.equals((Object)RESET)) break;
        }
        return result.toString();
    }

    public char getChar() {
        return this.code;
    }

    public String toString() {
        return this.toString;
    }

    public boolean isFormat() {
        return this.isFormat;
    }

    public boolean isColor() {
        return !this.isFormat && this != RESET;
    }

    static {
        STRIP_COLOR_PATTERN = Pattern.compile("(?i)" + String.valueOf('\u00a7') + "[0-9A-FK-OR]");
        BY_ID = Maps.newHashMap();
        BY_CHAR = Maps.newHashMap();
        for (ChatColor color : ChatColor.values()) {
            BY_ID.put(color.intCode, color);
            BY_CHAR.put(Character.valueOf(color.code), color);
        }
    }
}

