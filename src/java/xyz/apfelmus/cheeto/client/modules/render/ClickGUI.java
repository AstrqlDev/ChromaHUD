/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 */
package xyz.apfelmus.cheeto.client.modules.render;

import java.awt.Color;
import java.util.Arrays;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import xyz.apfelmus.cf4m.annotation.Setting;
import xyz.apfelmus.cf4m.annotation.module.Enable;
import xyz.apfelmus.cf4m.annotation.module.Module;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cheeto.client.clickgui.ConfigGUI;
import xyz.apfelmus.cheeto.client.settings.ModeSetting;
import xyz.apfelmus.cheeto.client.utils.client.ColorUtils;

@Module(name="ClickGUI", category=Category.RENDER, key=157, silent=true)
public class ClickGUI {
    @Setting(name="Theme", description="Re-open your GUI to update theme")
    public ModeSetting theme = new ModeSetting("Classic", Arrays.asList("Classic", "Femboy"));
    private String lastTheme = this.theme.getCurrent();

    @Enable
    public void onEnable() {
        if (!this.theme.getCurrent().equals(this.lastTheme)) {
            if (this.theme.getCurrent().equals("Classic")) {
                ColorUtils.MENU_BG = new Color(22, 22, 22);
                ColorUtils.TEXT_HOVERED = new Color(200, 200, 200);
                ColorUtils.HUD_BG = new Color(0, 0, 0, 150);
                ColorUtils.SELECT = new Color(132, 132, 132);
                ColorUtils.LABEL = new Color(150, 150, 150);
                ColorUtils.SUB_LABEL = new Color(100, 100, 100);
                ColorUtils.SELECTED = new Color(55, 174, 160);
                ColorUtils.M_BORDER = new Color(42, 42, 42);
                ColorUtils.C_BORDER = new Color(55, 174, 160, 100);
            } else {
                ColorUtils.MENU_BG = new Color(22, 22, 22);
                ColorUtils.TEXT_HOVERED = new Color(200, 200, 200);
                ColorUtils.HUD_BG = new Color(0, 0, 0, 150);
                ColorUtils.SELECT = new Color(215, 173, 255, 100);
                ColorUtils.LABEL = new Color(150, 150, 150);
                ColorUtils.SUB_LABEL = new Color(100, 100, 100);
                ColorUtils.SELECTED = new Color(211, 165, 255);
                ColorUtils.M_BORDER = new Color(181, 140, 236);
                ColorUtils.C_BORDER = new Color(215, 173, 255, 100);
            }
            this.lastTheme = this.theme.getCurrent();
        }
        Minecraft.func_71410_x().func_147108_a((GuiScreen)new ConfigGUI());
    }
}

