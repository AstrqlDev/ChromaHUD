/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.MouseHelper
 *  org.lwjgl.input.Mouse
 */
package xyz.apfelmus.cheeto.client.utils.client;

import net.minecraft.client.Minecraft;
import net.minecraft.util.MouseHelper;
import org.lwjgl.input.Mouse;

public class ChadUtils {
    private static Minecraft mc = Minecraft.func_71410_x();
    public static boolean isUngrabbed = false;
    private static MouseHelper oldMouseHelper;
    private static boolean doesGameWantUngrab;
    private static int oldRenderDist;
    private static int oldFpsCap;
    private static boolean improving;

    public static void ungrabMouse() {
        if (!ChadUtils.mc.field_71415_G || isUngrabbed) {
            return;
        }
        if (oldMouseHelper == null) {
            oldMouseHelper = ChadUtils.mc.field_71417_B;
        }
        ChadUtils.mc.field_71474_y.field_82881_y = false;
        doesGameWantUngrab = !Mouse.isGrabbed();
        oldMouseHelper.func_74373_b();
        ChadUtils.mc.field_71415_G = true;
        ChadUtils.mc.field_71417_B = new MouseHelper(){

            public void func_74374_c() {
            }

            public void func_74372_a() {
                doesGameWantUngrab = false;
            }

            public void func_74373_b() {
                doesGameWantUngrab = true;
            }
        };
        isUngrabbed = true;
    }

    public static void regrabMouse() {
        if (!isUngrabbed) {
            return;
        }
        ChadUtils.mc.field_71417_B = oldMouseHelper;
        if (!doesGameWantUngrab) {
            ChadUtils.mc.field_71417_B.func_74372_a();
        }
        oldMouseHelper = null;
        isUngrabbed = false;
    }

    public static void improveCpuUsage() {
        if (!improving) {
            oldRenderDist = ChadUtils.mc.field_71474_y.field_151451_c;
            oldFpsCap = ChadUtils.mc.field_71474_y.field_74350_i;
            ChadUtils.mc.field_71474_y.field_151451_c = 2;
            ChadUtils.mc.field_71474_y.field_74350_i = 30;
            improving = true;
        }
    }

    public static void revertCpuUsage() {
        ChadUtils.mc.field_71474_y.field_151451_c = oldRenderDist;
        ChadUtils.mc.field_71474_y.field_74350_i = oldFpsCap;
        improving = false;
    }

    static {
        doesGameWantUngrab = true;
        oldRenderDist = 0;
        oldFpsCap = 0;
        improving = false;
    }
}

