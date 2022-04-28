/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiChat
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.util.BlockPos
 *  net.minecraft.util.Vec3
 *  net.minecraft.util.Vec3i
 */
package xyz.apfelmus.cheeto.client.modules.movement;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;
import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cf4m.annotation.Event;
import xyz.apfelmus.cf4m.annotation.Setting;
import xyz.apfelmus.cf4m.annotation.module.Disable;
import xyz.apfelmus.cf4m.annotation.module.Enable;
import xyz.apfelmus.cf4m.annotation.module.Module;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cheeto.client.events.ClientTickEvent;
import xyz.apfelmus.cheeto.client.events.Render3DEvent;
import xyz.apfelmus.cheeto.client.settings.IntegerSetting;
import xyz.apfelmus.cheeto.client.utils.client.ChatUtils;
import xyz.apfelmus.cheeto.client.utils.client.ColorUtils;
import xyz.apfelmus.cheeto.client.utils.client.KeybindUtils;
import xyz.apfelmus.cheeto.client.utils.client.Rotation;
import xyz.apfelmus.cheeto.client.utils.client.RotationUtils;
import xyz.apfelmus.cheeto.client.utils.math.TimeHelper;
import xyz.apfelmus.cheeto.client.utils.math.VecUtils;
import xyz.apfelmus.cheeto.client.utils.pathfinding.Pathfinder;
import xyz.apfelmus.cheeto.client.utils.render.Render3DUtils;

@Module(name="Pathfinding", description="Do not toggle this module, it won't work", category=Category.MOVEMENT)
public class Pathfinding {
    @Setting(name="UnstuckTime")
    private IntegerSetting unstucktime = new IntegerSetting(3, 1, 10);
    @Setting(name="LookTime")
    private IntegerSetting lookTime = new IntegerSetting(150, 0, 1000);
    private static Minecraft mc = Minecraft.func_71410_x();
    private static int stuckTicks = 0;
    private static BlockPos oldPos;
    private static BlockPos curPos;
    private static TimeHelper unstucker;

    @Enable
    public void onEnable() {
        stuckTicks = 0;
        oldPos = null;
        curPos = null;
        if (!Pathfinder.hasPath()) {
            ChatUtils.send("Pussy bitch, no path found", new String[0]);
            CF4M.INSTANCE.moduleManager.toggle(this);
        } else {
            ChatUtils.send("Navigating to: " + (Object)Pathfinder.getGoal(), new String[0]);
        }
    }

    @Disable
    public void onDisable() {
        Pathfinder.path = null;
        KeyBinding.func_74510_a((int)Pathfinding.mc.field_71474_y.field_74370_x.func_151463_i(), (boolean)false);
        KeyBinding.func_74510_a((int)Pathfinding.mc.field_71474_y.field_74366_z.func_151463_i(), (boolean)false);
        KeyBinding.func_74510_a((int)Pathfinding.mc.field_71474_y.field_74351_w.func_151463_i(), (boolean)false);
        KeyBinding.func_74510_a((int)Pathfinding.mc.field_71474_y.field_74368_y.func_151463_i(), (boolean)false);
        KeyBinding.func_74510_a((int)Pathfinding.mc.field_71474_y.field_74314_A.func_151463_i(), (boolean)false);
    }

    @Event
    public void onTick(ClientTickEvent event) {
        if (Pathfinding.mc.field_71462_r != null && !(Pathfinding.mc.field_71462_r instanceof GuiChat)) {
            return;
        }
        if (Pathfinder.hasPath()) {
            if (++stuckTicks >= this.unstucktime.getCurrent() * 20) {
                curPos = Pathfinding.mc.field_71439_g.func_180425_c();
                if (oldPos != null && Math.sqrt(curPos.func_177951_i((Vec3i)oldPos)) <= 0.1) {
                    KeyBinding.func_74510_a((int)Pathfinding.mc.field_71474_y.field_74314_A.func_151463_i(), (boolean)true);
                    KeyBinding.func_74510_a((int)Pathfinding.mc.field_71474_y.field_74366_z.func_151463_i(), (boolean)true);
                    unstucker = new TimeHelper();
                    unstucker.reset();
                    return;
                }
                oldPos = curPos;
                stuckTicks = 0;
            }
            if (unstucker != null && unstucker.hasReached(2000L)) {
                KeyBinding.func_74510_a((int)Pathfinding.mc.field_71474_y.field_74314_A.func_151463_i(), (boolean)false);
                KeyBinding.func_74510_a((int)Pathfinding.mc.field_71474_y.field_74366_z.func_151463_i(), (boolean)false);
                unstucker = null;
            }
            Vec3 first = Pathfinder.getCurrent().func_72441_c(0.5, 0.0, 0.5);
            Rotation needed = RotationUtils.getRotation(first);
            needed.setPitch(Pathfinding.mc.field_71439_g.field_70125_A);
            if (VecUtils.getHorizontalDistance(Pathfinding.mc.field_71439_g.func_174791_d(), first) > 0.69) {
                if (RotationUtils.done && needed.getYaw() < 135.0f) {
                    RotationUtils.setup(needed, (long)this.lookTime.getCurrent());
                }
                if (Pathfinder.hasNext()) {
                    Vec3 next = Pathfinder.getNext().func_72441_c(0.5, 0.0, 0.5);
                    double xDiff = Math.abs(Math.abs(next.field_72450_a) - Math.abs(first.field_72450_a));
                    double zDiff = Math.abs(Math.abs(next.field_72449_c) - Math.abs(first.field_72449_c));
                    Pathfinding.mc.field_71439_g.func_70031_b(xDiff == 1.0 && zDiff == 0.0 || xDiff == 0.0 && zDiff == 1.0);
                }
                Vec3 lastTick = new Vec3(Pathfinding.mc.field_71439_g.field_70142_S, Pathfinding.mc.field_71439_g.field_70137_T, Pathfinding.mc.field_71439_g.field_70136_U);
                Vec3 diffy = Pathfinding.mc.field_71439_g.func_174791_d().func_178788_d(lastTick);
                Vec3 nextTick = Pathfinding.mc.field_71439_g.func_174791_d().func_178787_e(diffy);
                KeybindUtils.stopMovement();
                List<KeyBinding> neededPresses = VecUtils.getNeededKeyPresses(Pathfinding.mc.field_71439_g.func_174791_d(), first);
                if (!(Math.abs(nextTick.func_72438_d(first) - Pathfinding.mc.field_71439_g.func_174791_d().func_72438_d(first)) > 0.05) || !(nextTick.func_72438_d(first) > Pathfinding.mc.field_71439_g.func_174791_d().func_72438_d(first))) {
                    neededPresses.forEach(v -> KeyBinding.func_74510_a((int)v.func_151463_i(), (boolean)true));
                }
                if (Math.abs(Pathfinding.mc.field_71439_g.field_70163_u - first.field_72448_b) > 0.5) {
                    KeyBinding.func_74510_a((int)Pathfinding.mc.field_71474_y.field_74314_A.func_151463_i(), (Pathfinding.mc.field_71439_g.field_70163_u < first.field_72448_b ? 1 : 0) != 0);
                } else {
                    KeyBinding.func_74510_a((int)Pathfinding.mc.field_71474_y.field_74314_A.func_151463_i(), (boolean)false);
                }
            } else {
                RotationUtils.reset();
                if (!Pathfinder.goNext()) {
                    KeybindUtils.stopMovement();
                }
            }
        } else if (CF4M.INSTANCE.moduleManager.isEnabled(this)) {
            ChatUtils.send("Done navigating", new String[0]);
            CF4M.INSTANCE.moduleManager.toggle(this);
        }
    }

    @Event
    public void onRender(Render3DEvent event) {
        if (Pathfinder.path != null && !Pathfinder.path.isEmpty()) {
            Render3DUtils.drawLines(Pathfinder.path, 2.0f, event.partialTicks);
            Vec3 last = Pathfinder.path.get(Pathfinder.path.size() - 1).func_72441_c(0.0, -1.0, 0.0);
            Render3DUtils.renderEspBox(new BlockPos(last), event.partialTicks, ColorUtils.getChroma(3000.0f, (int)(last.field_72450_a + last.field_72448_b + last.field_72449_c)));
        }
        if (Pathfinding.mc.field_71462_r != null && !(Pathfinding.mc.field_71462_r instanceof GuiChat)) {
            return;
        }
        if (!RotationUtils.done) {
            RotationUtils.update();
        }
    }
}

