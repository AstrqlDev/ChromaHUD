/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 */
package xyz.apfelmus.cheeto.client.modules.combat;

import org.lwjgl.input.Keyboard;
import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cf4m.annotation.Event;
import xyz.apfelmus.cf4m.annotation.Setting;
import xyz.apfelmus.cf4m.annotation.module.Enable;
import xyz.apfelmus.cf4m.annotation.module.Module;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cheeto.client.events.Render3DEvent;
import xyz.apfelmus.cheeto.client.settings.BooleanSetting;
import xyz.apfelmus.cheeto.client.settings.IntegerSetting;
import xyz.apfelmus.cheeto.client.utils.client.KeybindUtils;

@Module(name="AutoClicker", category=Category.COMBAT)
public class AutoClicker {
    @Setting(name="BurstMode")
    private BooleanSetting burstMode = new BooleanSetting(false);
    @Setting(name="BurstAmount")
    private IntegerSetting burstAmount = new IntegerSetting(10, 0, 25);
    @Setting(name="CPS")
    private IntegerSetting cps = new IntegerSetting(10, 0, 50);
    private static boolean toggled = false;
    private static long lastClickTime = 0L;
    private static int clickedAmount = 0;

    @Enable
    public void onEnable() {
        if (toggled) {
            toggled = false;
        }
        clickedAmount = 0;
    }

    @Event
    public void onTick(Render3DEvent event) {
        if (this.burstMode.isEnabled()) {
            if (toggled) {
                float acTime = 1000.0f / (float)this.cps.getCurrent().intValue();
                if (clickedAmount < this.burstAmount.getCurrent()) {
                    if ((float)(System.currentTimeMillis() - lastClickTime) >= acTime) {
                        KeybindUtils.rightClick();
                        lastClickTime = System.currentTimeMillis();
                        ++clickedAmount;
                    }
                } else {
                    toggled = false;
                    CF4M.INSTANCE.moduleManager.toggle(this);
                }
            } else {
                toggled = true;
            }
        } else if (Keyboard.isKeyDown((int)CF4M.INSTANCE.moduleManager.getKey(this))) {
            float acTime = 1000.0f / (float)this.cps.getCurrent().intValue();
            if ((float)(System.currentTimeMillis() - lastClickTime) >= acTime) {
                KeybindUtils.rightClick();
                lastClickTime = System.currentTimeMillis();
            }
        } else {
            CF4M.INSTANCE.moduleManager.toggle(this);
        }
    }
}

