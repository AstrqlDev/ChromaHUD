/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 */
package xyz.apfelmus.cheeto.client.utils.math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import xyz.apfelmus.cheeto.client.utils.client.Rotation;
import xyz.apfelmus.cheeto.client.utils.client.RotationUtils;

public class VecUtils {
    private static Minecraft mc = Minecraft.func_71410_x();
    private static Map<Integer, KeyBinding> keyBindMap = new HashMap<Integer, KeyBinding>(){
        {
            this.put(0, mc.field_71474_y.field_74351_w);
            this.put(90, mc.field_71474_y.field_74370_x);
            this.put(180, mc.field_71474_y.field_74368_y);
            this.put(-90, mc.field_71474_y.field_74366_z);
        }
    };

    public static Vec3 floorVec(Vec3 vec3) {
        return new Vec3(Math.floor(vec3.field_72450_a), Math.floor(vec3.field_72448_b), Math.floor(vec3.field_72449_c));
    }

    public static Vec3 ceilVec(Vec3 vec3) {
        return new Vec3(Math.ceil(vec3.field_72450_a), Math.ceil(vec3.field_72448_b), Math.ceil(vec3.field_72449_c));
    }

    public static double getHorizontalDistance(Vec3 vec1, Vec3 vec2) {
        double d0 = vec1.field_72450_a - vec2.field_72450_a;
        double d2 = vec1.field_72449_c - vec2.field_72449_c;
        return MathHelper.func_76133_a((double)(d0 * d0 + d2 * d2));
    }

    public static List<KeyBinding> getNeededKeyPresses(Vec3 from, Vec3 to) {
        ArrayList<KeyBinding> damnIThinkIShouldHaveRatherUsed4SwitchCasesToDetermineTheNeededKeyPresses = new ArrayList<KeyBinding>();
        Rotation neededRot = RotationUtils.getNeededChange(RotationUtils.getRotation(from, to));
        double neededYaw = neededRot.getYaw() * -1.0f;
        keyBindMap.forEach((k, v) -> {
            if (Math.abs((double)k.intValue() - neededYaw) < 67.5 || Math.abs((double)k.intValue() - (neededYaw + 360.0)) < 67.5) {
                damnIThinkIShouldHaveRatherUsed4SwitchCasesToDetermineTheNeededKeyPresses.add((KeyBinding)v);
            }
        });
        return damnIThinkIShouldHaveRatherUsed4SwitchCasesToDetermineTheNeededKeyPresses;
    }
}

