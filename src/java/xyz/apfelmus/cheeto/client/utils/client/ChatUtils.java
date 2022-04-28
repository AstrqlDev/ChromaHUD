/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 */
package xyz.apfelmus.cheeto.client.utils.client;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import xyz.apfelmus.cheeto.client.utils.client.ChatColor;

public class ChatUtils {
    private static final String PREFIX = ChatColor.format("&6[&eCheeto&6]&f ");

    public static void send(String text, String ... args) {
        if (Minecraft.func_71410_x().field_71439_g == null) {
            return;
        }
        text = String.format(text, args);
        StringBuilder messageBuilder = new StringBuilder();
        for (String word : text.split(" ")) {
            word = ChatColor.format(ChatColor.getLastColors(text) + word);
            messageBuilder.append(word).append(" ");
        }
        Minecraft.func_71410_x().field_71439_g.func_145747_a((IChatComponent)new ChatComponentText(PREFIX + ChatColor.format(messageBuilder.toString().trim())));
    }
}

