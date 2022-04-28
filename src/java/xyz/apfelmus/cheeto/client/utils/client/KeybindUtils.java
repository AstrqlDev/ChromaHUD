/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.settings.KeyBinding
 */
package xyz.apfelmus.cheeto.client.utils.client;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class KeybindUtils {
    private static Minecraft mc = Minecraft.func_71410_x();
    private static Method clickMouse;
    private static Method rightClickMouse;

    public static void setup() {
        try {
            clickMouse = Minecraft.class.getDeclaredMethod("clickMouse", new Class[0]);
        }
        catch (NoSuchMethodException e) {
            try {
                clickMouse = Minecraft.class.getDeclaredMethod("func_147116_af", new Class[0]);
            }
            catch (NoSuchMethodException ex) {
                ex.printStackTrace();
            }
        }
        try {
            rightClickMouse = Minecraft.class.getDeclaredMethod("rightClickMouse", new Class[0]);
        }
        catch (NoSuchMethodException e) {
            try {
                rightClickMouse = Minecraft.class.getDeclaredMethod("func_147121_ag", new Class[0]);
            }
            catch (NoSuchMethodException e1) {
                e.printStackTrace();
            }
        }
        if (clickMouse != null) {
            clickMouse.setAccessible(true);
        }
        if (rightClickMouse != null) {
            rightClickMouse.setAccessible(true);
        }
    }

    public static void leftClick() {
        try {
            clickMouse.invoke((Object)Minecraft.func_71410_x(), new Object[0]);
        }
        catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void rightClick() {
        try {
            rightClickMouse.invoke((Object)Minecraft.func_71410_x(), new Object[0]);
        }
        catch (IllegalAccessException | InvocationTargetException reflectiveOperationException) {
            // empty catch block
        }
    }

    public static void stopMovement() {
        KeyBinding.func_74510_a((int)KeybindUtils.mc.field_71474_y.field_74370_x.func_151463_i(), (boolean)false);
        KeyBinding.func_74510_a((int)KeybindUtils.mc.field_71474_y.field_74366_z.func_151463_i(), (boolean)false);
        KeyBinding.func_74510_a((int)KeybindUtils.mc.field_71474_y.field_74351_w.func_151463_i(), (boolean)false);
        KeyBinding.func_74510_a((int)KeybindUtils.mc.field_71474_y.field_74368_y.func_151463_i(), (boolean)false);
    }
}

