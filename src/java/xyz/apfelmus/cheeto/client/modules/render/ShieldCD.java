/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 */
package xyz.apfelmus.cheeto.client.modules.render;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import xyz.apfelmus.cf4m.annotation.Event;
import xyz.apfelmus.cf4m.annotation.Setting;
import xyz.apfelmus.cf4m.annotation.module.Module;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cheeto.client.clickgui.ConfigGUI;
import xyz.apfelmus.cheeto.client.events.Render2DEvent;
import xyz.apfelmus.cheeto.client.settings.BooleanSetting;
import xyz.apfelmus.cheeto.client.settings.FloatSetting;
import xyz.apfelmus.cheeto.client.settings.IntegerSetting;
import xyz.apfelmus.cheeto.client.utils.client.FontUtils;

@Module(name="ShieldCD", category=Category.RENDER)
public class ShieldCD {
    @Setting(name="xPos")
    private IntegerSetting xPos = new IntegerSetting(0, 0, 1000);
    @Setting(name="yPos")
    private IntegerSetting yPos = new IntegerSetting(0, 0, 1000);
    @Setting(name="RGB")
    private BooleanSetting rgb = new BooleanSetting(true);
    @Setting(name="Scale")
    private FloatSetting scale = new FloatSetting(Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(2.5f));
    public static long LastShield = 0L;

    @Event
    public void onRender(Render2DEvent event) {
        long timeDiffy = System.currentTimeMillis() - LastShield;
        if (!(Minecraft.func_71410_x().field_71462_r instanceof ConfigGUI) && Minecraft.func_71410_x().field_71462_r != null) {
            return;
        }
        GlStateManager.func_179094_E();
        GlStateManager.func_179152_a((float)this.scale.getCurrent().floatValue(), (float)this.scale.getCurrent().floatValue(), (float)this.scale.getCurrent().floatValue());
        if (timeDiffy >= 5000L) {
            if (this.rgb.isEnabled()) {
                FontUtils.drawHVCenteredChromaString("Shield: Ready", this.xPos.getCurrent(), this.yPos.getCurrent(), 0);
            } else {
                FontUtils.drawHVCenteredString("Shield: Ready", this.xPos.getCurrent(), this.yPos.getCurrent(), Color.GREEN.getRGB());
            }
        } else {
            FontUtils.drawHVCenteredString(String.format("Shield: %.3fs", Float.valueOf((float)(5000L - timeDiffy) / 1000.0f)), this.xPos.getCurrent(), this.yPos.getCurrent(), Color.ORANGE.getRGB());
        }
        GlStateManager.func_179121_F();
    }
}

