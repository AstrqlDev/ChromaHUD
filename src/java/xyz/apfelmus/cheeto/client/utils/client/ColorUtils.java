/*
 * Decompiled with CFR 0.150.
 */
package xyz.apfelmus.cheeto.client.utils.client;

import java.awt.Color;

public class ColorUtils {
    public static Color MENU_BG = new Color(22, 22, 22);
    public static Color TEXT_HOVERED = new Color(200, 200, 200);
    public static Color HUD_BG = new Color(0, 0, 0, 150);
    public static Color SELECT = new Color(132, 132, 132);
    public static Color LABEL = new Color(150, 150, 150);
    public static Color SUB_LABEL = new Color(100, 100, 100);
    public static Color SELECTED = new Color(55, 174, 160);
    public static Color M_BORDER = new Color(42, 42, 42);
    public static Color C_BORDER = new Color(55, 174, 160, 100);

    public static int getChroma(float speed, int offset) {
        return Color.HSBtoRGB((float)((System.currentTimeMillis() - (long)offset * 10L) % (long)speed) / speed, 0.88f, 0.88f);
    }
}

