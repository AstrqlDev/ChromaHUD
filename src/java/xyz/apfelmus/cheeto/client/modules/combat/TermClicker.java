/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  org.lwjgl.input.Keyboard
 */
package xyz.apfelmus.cheeto.client.modules.combat;

import java.util.Arrays;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cf4m.annotation.Event;
import xyz.apfelmus.cf4m.annotation.Setting;
import xyz.apfelmus.cf4m.annotation.module.Module;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cheeto.client.events.Render3DEvent;
import xyz.apfelmus.cheeto.client.settings.BooleanSetting;
import xyz.apfelmus.cheeto.client.settings.IntegerSetting;
import xyz.apfelmus.cheeto.client.settings.ModeSetting;
import xyz.apfelmus.cheeto.client.utils.skyblock.InventoryUtils;
import xyz.apfelmus.cheeto.client.utils.skyblock.SkyblockUtils;

@Module(name="TermClicker", category=Category.COMBAT)
public class TermClicker {
    @Setting(name="CPS")
    private IntegerSetting cps = new IntegerSetting(20, 0, 50);
    @Setting(name="Silent")
    private BooleanSetting silent = new BooleanSetting(true);
    @Setting(name="Mode")
    private ModeSetting mode = new ModeSetting("Hold", Arrays.asList("Hold", "Toggle"));
    @Setting(name="Weapon")
    private ModeSetting weapon = new ModeSetting("Terminator", Arrays.asList("Terminator", "Juju Shortbow"));
    private static long lastClickTime = 0L;
    private static Minecraft mc = Minecraft.func_71410_x();

    @Event
    public void onRenderTick(Render3DEvent event) {
        if (this.mode.getCurrent().equals("Hold")) {
            if (Keyboard.isKeyDown((int)CF4M.INSTANCE.moduleManager.getKey(this))) {
                this.clickTermIfNeeded();
            } else {
                CF4M.INSTANCE.moduleManager.toggle(this);
            }
        } else if (this.mode.getCurrent().equals("Toggle")) {
            this.clickTermIfNeeded();
        }
    }

    private void clickTermIfNeeded() {
        float acTime = 1000.0f / (float)this.cps.getCurrent().intValue();
        if ((float)(System.currentTimeMillis() - lastClickTime) >= acTime) {
            int termSlot = InventoryUtils.getItemInHotbar(this.weapon.getCurrent());
            if (termSlot != -1) {
                SkyblockUtils.silentUse(this.silent.isEnabled() ? TermClicker.mc.field_71439_g.field_71071_by.field_70461_c + 1 : termSlot + 1, termSlot + 1);
            }
            lastClickTime = System.currentTimeMillis();
        }
    }
}

