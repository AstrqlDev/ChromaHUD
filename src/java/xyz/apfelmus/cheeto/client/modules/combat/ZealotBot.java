/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.monster.EntityEnderman
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 */
package xyz.apfelmus.cheeto.client.modules.combat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import xyz.apfelmus.cf4m.annotation.Event;
import xyz.apfelmus.cf4m.annotation.Setting;
import xyz.apfelmus.cf4m.annotation.module.Enable;
import xyz.apfelmus.cheeto.client.events.Render3DEvent;
import xyz.apfelmus.cheeto.client.events.WorldUnloadEvent;
import xyz.apfelmus.cheeto.client.settings.BooleanSetting;
import xyz.apfelmus.cheeto.client.settings.IntegerSetting;
import xyz.apfelmus.cheeto.client.utils.client.Rotation;
import xyz.apfelmus.cheeto.client.utils.client.RotationUtils;

public class ZealotBot {
    @Setting(name="AimTime")
    private IntegerSetting aimTime = new IntegerSetting(100, 0, 1000);
    @Setting(name="ClickDelay")
    private IntegerSetting clickDelay = new IntegerSetting(100, 0, 1000);
    @Setting(name="Radius")
    private IntegerSetting radius = new IntegerSetting(30, 0, 30);
    @Setting(name="GodGamerMode")
    public static BooleanSetting godGamerMode = new BooleanSetting(false);
    private static Minecraft mc = Minecraft.func_71410_x();
    private Entity zealot;
    private KillState ks = KillState.SELECT;

    @Enable
    public void onEnable() {
        this.zealot = null;
        this.ks = KillState.SELECT;
    }

    @Event
    public void onRender(Render3DEvent event) {
        switch (this.ks) {
            case SELECT: {
                ArrayList<Entity> allPossible = new ArrayList<Entity>();
                for (Entity e3 : ZealotBot.mc.field_71441_e.field_72996_f) {
                    if (!(e3 instanceof EntityEnderman) || e3 == null) continue;
                    allPossible.add(e3);
                }
                allPossible.forEach(e -> {
                    MovingObjectPosition mop = ZealotBot.mc.field_71441_e.func_72933_a(ZealotBot.mc.field_71439_g.func_174824_e(1.0f), e.func_174791_d());
                    if (mop != null) {
                        System.out.println((Object)mop.field_72313_a);
                    }
                });
                if (allPossible.isEmpty()) break;
                this.zealot = Collections.min(allPossible, Comparator.comparing(e2 -> Float.valueOf(e2.func_70032_d((Entity)ZealotBot.mc.field_71439_g))));
                Vec3 vec = this.zealot.func_174791_d();
                vec = vec.func_72441_c(0.0, 1.0, 0.0);
                Rotation rot = RotationUtils.getRotation(vec);
                RotationUtils.setup(rot, (long)this.aimTime.getCurrent());
                this.ks = KillState.AIM;
                break;
            }
            case AIM: {
                if (System.currentTimeMillis() <= RotationUtils.endTime + (long)this.clickDelay.getCurrent().intValue()) {
                    RotationUtils.update();
                    break;
                }
                this.ks = KillState.KILL;
                break;
            }
            case KILL: {
                KeyBinding.func_74507_a((int)ZealotBot.mc.field_71474_y.field_74311_E.func_151463_i());
                this.ks = KillState.SELECT;
            }
        }
    }

    @Event
    public void onWorldLoad(WorldUnloadEvent event) {
        this.zealot = null;
        this.ks = KillState.SELECT;
    }

    static enum KillState {
        SELECT,
        AIM,
        KILL;

    }
}

