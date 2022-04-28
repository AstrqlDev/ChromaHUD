/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 */
package xyz.apfelmus.cheeto.client.utils.client;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class FontUtils {
    private static FontRenderer fr = Minecraft.func_71410_x().field_71466_p;

    public static void drawString(String text, int x, int y, int color) {
        fr.func_175065_a(text, (float)x, (float)y, color, true);
    }

    public static void drawVCenteredString(String text, int x, int y, int color) {
        fr.func_175065_a(text, (float)x, (float)(y - FontUtils.fr.field_78288_b / 2), color, true);
    }

    public static void drawHVCenteredString(String text, int x, int y, int color) {
        fr.func_175065_a(text, (float)(x - fr.func_78256_a(text) / 2), (float)(y - FontUtils.fr.field_78288_b / 2), color, true);
    }

    public static void drawHVCenteredChromaString(String text, int x, int y, int offset) {
        FontUtils.drawChromaString(text, x - fr.func_78256_a(text) / 2, y - FontUtils.fr.field_78288_b / 2, offset);
    }

    public static int getStringWidth(String text) {
        return fr.func_78256_a(text);
    }

    public static int getFontHeight() {
        return FontUtils.fr.field_78288_b;
    }

    public static void drawChromaString(String text, int x, int y, int offset) {
        double tmpX = x;
        for (char tc : text.toCharArray()) {
            long t = System.currentTimeMillis() - ((long)((int)tmpX) * 10L - (long)y - (long)offset * 10L);
            int i = Color.HSBtoRGB((float)(t % 2000L) / 2000.0f, 0.88f, 0.88f);
            String tmp = String.valueOf(tc);
            fr.func_175065_a(tmp, (float)((int)tmpX), (float)y, i, true);
            tmpX += (double)fr.func_78263_a(tc);
        }
    }
}

