/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package xyz.apfelmus.cheeto.client.modules.combat;

import net.minecraft.client.Minecraft;
import xyz.apfelmus.cf4m.annotation.Event;
import xyz.apfelmus.cf4m.annotation.Setting;
import xyz.apfelmus.cf4m.annotation.module.Module;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cheeto.client.events.LeftClickEvent;
import xyz.apfelmus.cheeto.client.settings.IntegerSetting;
import xyz.apfelmus.cheeto.client.utils.client.Flags;
import xyz.apfelmus.cheeto.client.utils.skyblock.SkyblockUtils;

@Module(name="SilentHits", category=Category.COMBAT)
public class SilentHits {
    @Setting(name="UseSlot1")
    private IntegerSetting useSlot1 = new IntegerSetting(0, 0, 8);
    @Setting(name="UseSlot2")
    private IntegerSetting useSlot2 = new IntegerSetting(0, 0, 8);
    @Setting(name="UseSlot3")
    private IntegerSetting useSlot3 = new IntegerSetting(0, 0, 8);
    private static Minecraft mc = Minecraft.func_71410_x();

    @Event
    public void onLeftClick(LeftClickEvent event) {
        if (!Flags.silentUsing) {
            if (this.useSlot1.getCurrent() > 0 && this.useSlot1.getCurrent() <= 8) {
                SkyblockUtils.silentUse(SilentHits.mc.field_71439_g.field_71071_by.field_70461_c + 1, this.useSlot1.getCurrent());
            }
            if (this.useSlot2.getCurrent() > 0 && this.useSlot2.getCurrent() <= 8) {
                SkyblockUtils.silentUse(SilentHits.mc.field_71439_g.field_71071_by.field_70461_c + 1, this.useSlot2.getCurrent());
            }
            if (this.useSlot3.getCurrent() > 0 && this.useSlot3.getCurrent() <= 8) {
                SkyblockUtils.silentUse(SilentHits.mc.field_71439_g.field_71071_by.field_70461_c + 1, this.useSlot3.getCurrent());
            }
        }
    }
}

