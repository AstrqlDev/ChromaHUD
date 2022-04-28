/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.util.ResourceLocation
 */
package xyz.apfelmus.cheeto.client.modules.render;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cf4m.annotation.Event;
import xyz.apfelmus.cf4m.annotation.Setting;
import xyz.apfelmus.cf4m.annotation.module.Module;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cheeto.Cheeto;
import xyz.apfelmus.cheeto.client.events.Render2DEvent;
import xyz.apfelmus.cheeto.client.modules.render.ClickGUI;
import xyz.apfelmus.cheeto.client.settings.BooleanSetting;
import xyz.apfelmus.cheeto.client.settings.ModeSetting;
import xyz.apfelmus.cheeto.client.utils.client.ColorUtils;
import xyz.apfelmus.cheeto.client.utils.client.FontUtils;
import xyz.apfelmus.cheeto.client.utils.client.RotationUtils;
import xyz.apfelmus.cheeto.client.utils.render.Render2DUtils;

@Module(name="HUD", category=Category.RENDER)
public class HUD {
    @Setting(name="Watermark")
    private BooleanSetting watermark = new BooleanSetting(true);
    @Setting(name="ModuleList")
    private BooleanSetting moduleList = new BooleanSetting(true);
    @Setting(name="Logo")
    private ModeSetting logo = new ModeSetting("bing_chilling", Arrays.asList("smirk_cat", "bing_chilling"));

    @Event
    public void onRender(Render2DEvent event) {
        if (this.watermark.isEnabled()) {
            this.renderTopString();
        }
        if (!this.moduleList.isEnabled()) {
            return;
        }
        List<Object> modules = CF4M.INSTANCE.moduleManager.getModules().stream().filter(v -> CF4M.INSTANCE.moduleManager.isEnabled(v)).sorted((o1, o2) -> FontUtils.getStringWidth(this.getModuleName(o2)) - FontUtils.getStringWidth(this.getModuleName(o1))).collect(Collectors.toList());
        this.renderModuleList(modules);
    }

    private void renderTopString() {
        GlStateManager.func_179139_a((double)2.0, (double)2.0, (double)2.0);
        ClickGUI click = (ClickGUI)CF4M.INSTANCE.moduleManager.getModule("ClickGUI");
        if (click.theme.getCurrent().equals("Femboy")) {
            FontUtils.drawString(Cheeto.name, 2 + FontUtils.getFontHeight(), 2, ColorUtils.SELECTED.getRGB());
        } else {
            FontUtils.drawChromaString(Cheeto.name, 2 + FontUtils.getFontHeight(), 2, 0);
        }
        GlStateManager.func_179139_a((double)0.5, (double)0.5, (double)0.5);
        int nameLength = FontUtils.getStringWidth(Cheeto.name);
        if (click.theme.getCurrent().equals("Femboy")) {
            FontUtils.drawString(" v" + Cheeto.version, 2 + nameLength * 2 + FontUtils.getFontHeight() * 2, 2 + FontUtils.getFontHeight(), ColorUtils.SELECTED.getRGB());
        } else {
            FontUtils.drawChromaString(" v" + Cheeto.version, 2 + nameLength * 2 + FontUtils.getFontHeight() * 2, 2 + FontUtils.getFontHeight(), nameLength);
        }
        Render2DUtils.drawTexture(new ResourceLocation("chromahud:" + this.logo.getCurrent() + ".png"), 2, 2, FontUtils.getFontHeight() * 2, FontUtils.getFontHeight() * 2, FontUtils.getFontHeight() * 2, FontUtils.getFontHeight() * 2, 0, 0);
    }

    private void renderModuleList(List<Object> modules) {
        int yStart = 0;
        ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x());
        for (Object module : modules) {
            int sw = FontUtils.getStringWidth(this.getModuleName(module));
            int startX = sr.func_78326_a() - sw - 6;
            int animStartX = sr.func_78326_a();
            long start = CF4M.INSTANCE.moduleManager.getActivatedTime(module);
            float spentMillis = System.currentTimeMillis() - start;
            float relativeProgress = spentMillis / 500.0f;
            int relativeX = (int)((float)(startX - animStartX) * RotationUtils.easeOutCubic(relativeProgress) + (float)animStartX);
            relativeX = Math.max(startX, relativeX);
            int relativeHeight = (int)Math.ceil(13.0f * RotationUtils.easeOutCubic(relativeProgress));
            relativeHeight = Math.min(relativeHeight, 13);
            ClickGUI click = (ClickGUI)CF4M.INSTANCE.moduleManager.getModule("ClickGUI");
            Render2DUtils.drawRectWH(relativeX - 1, yStart, sr.func_78326_a() - startX + 1, relativeHeight, ColorUtils.HUD_BG.getRGB());
            Render2DUtils.drawLeftRoundedRect(relativeX - 1, yStart, 2.0f, relativeHeight, 2.0f, click.theme.getCurrent().equals("Femboy") ? ColorUtils.SELECTED.getRGB() : ColorUtils.getChroma(2000.0f, yStart / 2));
            FontUtils.drawString(this.getModuleName(module), relativeX + 2, yStart + 3, ColorUtils.TEXT_HOVERED.getRGB());
            yStart += relativeHeight;
        }
    }

    private String getModuleName(Object module) {
        return CF4M.INSTANCE.moduleManager.getName(module);
    }
}

