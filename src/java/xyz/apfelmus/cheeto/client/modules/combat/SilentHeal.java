/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package xyz.apfelmus.cheeto.client.modules.combat;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cf4m.annotation.Setting;
import xyz.apfelmus.cf4m.annotation.module.Enable;
import xyz.apfelmus.cf4m.annotation.module.Module;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cheeto.client.modules.render.ShieldCD;
import xyz.apfelmus.cheeto.client.settings.IntegerSetting;

@Module(name="SilentHeal", category=Category.COMBAT)
public class SilentHeal {
    private static Minecraft mc = Minecraft.func_71410_x();
    @Setting(name="AtomSlot")
    private IntegerSetting atomSlot = new IntegerSetting(0, 0, 8);
    @Setting(name="SussySlot")
    private IntegerSetting sussySlot = new IntegerSetting(0, 0, 8);
    @Setting(name="WandSlot")
    private IntegerSetting wandSlot = new IntegerSetting(0, 0, 8);
    @Setting(name="PigmanSlot")
    private IntegerSetting pigmanSlot = new IntegerSetting(0, 0, 8);

    @Enable
    public void onEnable() {
        if (this.sussySlot.getCurrent() > 0 && this.sussySlot.getCurrent() <= 8) {
            SilentHeal.mc.field_71439_g.field_71071_by.field_70461_c = this.sussySlot.getCurrent() - 1;
            SilentHeal.mc.field_71442_b.func_78769_a((EntityPlayer)SilentHeal.mc.field_71439_g, (World)SilentHeal.mc.field_71441_e, SilentHeal.mc.field_71439_g.func_70694_bm());
            ShieldCD.LastShield = System.currentTimeMillis();
        }
        if (this.wandSlot.getCurrent() > 0 && this.wandSlot.getCurrent() <= 8) {
            SilentHeal.mc.field_71439_g.field_71071_by.field_70461_c = this.wandSlot.getCurrent() - 1;
            SilentHeal.mc.field_71442_b.func_78769_a((EntityPlayer)SilentHeal.mc.field_71439_g, (World)SilentHeal.mc.field_71441_e, SilentHeal.mc.field_71439_g.func_70694_bm());
        }
        if (this.pigmanSlot.getCurrent() > 0 && this.pigmanSlot.getCurrent() <= 8) {
            SilentHeal.mc.field_71439_g.field_71071_by.field_70461_c = this.pigmanSlot.getCurrent() - 1;
            SilentHeal.mc.field_71442_b.func_78769_a((EntityPlayer)SilentHeal.mc.field_71439_g, (World)SilentHeal.mc.field_71441_e, SilentHeal.mc.field_71439_g.func_70694_bm());
        }
        if (this.atomSlot.getCurrent() > 0 && this.atomSlot.getCurrent() <= 8) {
            SilentHeal.mc.field_71439_g.field_71071_by.field_70461_c = this.atomSlot.getCurrent() - 1;
        }
        CF4M.INSTANCE.moduleManager.toggle(this);
    }
}

