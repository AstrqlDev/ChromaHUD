/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package xyz.apfelmus.cheeto.client.modules.misc;

import net.minecraft.client.Minecraft;
import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cf4m.annotation.Event;
import xyz.apfelmus.cf4m.annotation.Setting;
import xyz.apfelmus.cf4m.annotation.module.Disable;
import xyz.apfelmus.cf4m.annotation.module.Enable;
import xyz.apfelmus.cf4m.annotation.module.Module;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cheeto.client.events.ClientTickEvent;
import xyz.apfelmus.cheeto.client.settings.BooleanSetting;
import xyz.apfelmus.cheeto.client.settings.IntegerSetting;
import xyz.apfelmus.cheeto.client.utils.client.Flags;
import xyz.apfelmus.cheeto.client.utils.client.KeybindUtils;
import xyz.apfelmus.cheeto.client.utils.skyblock.SkyblockUtils;

@Module(name="SilentUse4", category=Category.MISC)
public class SilentUse4 {
    @Setting(name="MainSlot")
    private IntegerSetting mainSlot = new IntegerSetting(0, 0, 8);
    @Setting(name="UseSlot")
    private IntegerSetting useSlot = new IntegerSetting(0, 0, 8);
    @Setting(name="LeftClick")
    private BooleanSetting leftClick = new BooleanSetting(false);
    private static Minecraft mc = Minecraft.func_71410_x();
    private static boolean leftFlag = false;
    private static int ticks = 0;

    @Enable
    public void onEnable() {
        Flags.silentUsing = true;
        if (this.leftClick.isEnabled()) {
            if (this.useSlot.getCurrent() > 0 && this.useSlot.getCurrent() <= 8) {
                SilentUse4.mc.field_71439_g.field_71071_by.field_70461_c = this.useSlot.getCurrent() - 1;
                leftFlag = true;
            }
        } else {
            SkyblockUtils.silentUse(this.mainSlot.getCurrent(), this.useSlot.getCurrent());
            CF4M.INSTANCE.moduleManager.toggle(this);
        }
    }

    @Disable
    public void onDisable() {
        Flags.silentUsing = false;
    }

    @Event
    public void onTick(ClientTickEvent event) {
        if (leftFlag) {
            if (++ticks == 2) {
                KeybindUtils.leftClick();
            } else if (ticks > 2) {
                if (this.mainSlot.getCurrent() > 0 && this.mainSlot.getCurrent() <= 8) {
                    SilentUse4.mc.field_71439_g.field_71071_by.field_70461_c = this.mainSlot.getCurrent() - 1;
                }
                leftFlag = false;
                ticks = 0;
                CF4M.INSTANCE.moduleManager.toggle(this);
            }
        }
    }
}

