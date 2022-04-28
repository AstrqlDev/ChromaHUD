/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityOtherPlayerMP
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.Vec3
 */
package xyz.apfelmus.cheeto.client.modules.combat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import xyz.apfelmus.cf4m.annotation.Event;
import xyz.apfelmus.cf4m.annotation.Setting;
import xyz.apfelmus.cf4m.annotation.module.Enable;
import xyz.apfelmus.cf4m.annotation.module.Module;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cheeto.client.events.Render3DEvent;
import xyz.apfelmus.cheeto.client.events.WorldUnloadEvent;
import xyz.apfelmus.cheeto.client.settings.BooleanSetting;
import xyz.apfelmus.cheeto.client.settings.FloatSetting;
import xyz.apfelmus.cheeto.client.settings.IntegerSetting;
import xyz.apfelmus.cheeto.client.utils.client.Rotation;
import xyz.apfelmus.cheeto.client.utils.client.RotationUtils;

@Module(name="BloodCamp", category=Category.COMBAT)
public class BloodCamp {
    @Setting(name="AimTime")
    private IntegerSetting aimTime = new IntegerSetting(100, 0, 1000);
    @Setting(name="ClickDelay")
    private IntegerSetting clickDelay = new IntegerSetting(100, 0, 1000);
    @Setting(name="AimLow")
    private FloatSetting aimLow = new FloatSetting(Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(5.0f));
    @Setting(name="GodGamerMode")
    public static BooleanSetting godGamerMode = new BooleanSetting(false);
    private static Minecraft mc = Minecraft.func_71410_x();
    private List<String> names = new ArrayList<String>(Arrays.asList("Revoker", "Psycho", "Reaper", "Parasite", "Cannibal", "Mute", "Ooze", "Putrid", "Freak", "Leech", "Flamer", "Tear", "Skull", "Mr. Dead", "Vader", "Frost", "Walker"));
    private List<String> clickedNames = new ArrayList<String>();
    private KillState ks = KillState.SELECT;
    private long curEnd = 0L;

    @Enable
    public void onEnable() {
        this.curEnd = 0L;
        this.clickedNames.clear();
        this.ks = KillState.SELECT;
    }

    @Event
    public void onRender(Render3DEvent event) {
        switch (this.ks) {
            case SELECT: {
                for (Entity e : BloodCamp.mc.field_71441_e.field_72996_f) {
                    if (!(e instanceof EntityOtherPlayerMP) || this.clickedNames.contains(e.func_70005_c_().trim()) || !this.names.contains(e.func_70005_c_().trim())) continue;
                    Vec3 vec = e.func_174791_d();
                    vec = vec.func_72441_c(0.0, (double)(-1.0f * this.aimLow.getCurrent().floatValue()), 0.0);
                    Rotation rot = RotationUtils.getRotation(vec);
                    RotationUtils.setup(rot, (long)this.aimTime.getCurrent());
                    this.curEnd = RotationUtils.endTime;
                    this.clickedNames.add(e.func_70005_c_().trim());
                    this.ks = KillState.AIM;
                }
                break;
            }
            case AIM: {
                if (System.currentTimeMillis() <= this.curEnd + (long)this.clickDelay.getCurrent().intValue()) {
                    RotationUtils.update();
                    break;
                }
                this.ks = KillState.KILL;
                break;
            }
            case KILL: {
                KeyBinding.func_74507_a((int)BloodCamp.mc.field_71474_y.field_74312_F.func_151463_i());
                this.ks = KillState.SELECT;
            }
        }
    }

    @Event
    public void onWorldLoad(WorldUnloadEvent event) {
        this.clickedNames.clear();
    }

    static enum KillState {
        SELECT,
        AIM,
        KILL;

    }
}

