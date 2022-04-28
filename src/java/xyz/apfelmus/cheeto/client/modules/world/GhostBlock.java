/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  org.lwjgl.input.Keyboard
 */
package xyz.apfelmus.cheeto.client.modules.world;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cf4m.annotation.Event;
import xyz.apfelmus.cf4m.annotation.Setting;
import xyz.apfelmus.cf4m.annotation.module.Enable;
import xyz.apfelmus.cf4m.annotation.module.Module;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cheeto.client.events.Render3DEvent;
import xyz.apfelmus.cheeto.client.settings.BooleanSetting;
import xyz.apfelmus.cheeto.client.utils.skyblock.SkyblockUtils;

@Module(name="GhostBlock", category=Category.WORLD)
public class GhostBlock {
    @Setting(name="BrrrMode")
    public BooleanSetting brrrMode = new BooleanSetting(true);
    private static Minecraft mc = Minecraft.func_71410_x();

    @Enable
    public void onEnable() {
        if (!this.brrrMode.isEnabled()) {
            SkyblockUtils.ghostBlock();
            CF4M.INSTANCE.moduleManager.toggle(this);
        }
    }

    @Event
    public void onTick(Render3DEvent event) {
        if (this.brrrMode.isEnabled() && Keyboard.isKeyDown((int)CF4M.INSTANCE.moduleManager.getKey(this))) {
            if (GhostBlock.mc.field_71462_r == null) {
                SkyblockUtils.ghostBlock();
            }
        } else {
            CF4M.INSTANCE.moduleManager.toggle(this);
        }
    }
}

