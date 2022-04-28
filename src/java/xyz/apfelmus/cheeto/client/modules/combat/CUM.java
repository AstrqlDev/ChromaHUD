/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package xyz.apfelmus.cheeto.client.modules.combat;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cf4m.annotation.Setting;
import xyz.apfelmus.cf4m.annotation.module.Enable;
import xyz.apfelmus.cf4m.annotation.module.Module;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cheeto.client.settings.BooleanSetting;
import xyz.apfelmus.cheeto.client.settings.IntegerSetting;
import xyz.apfelmus.cheeto.client.utils.skyblock.InventoryUtils;

@Module(name="CUM", category=Category.COMBAT)
public class CUM {
    private static Minecraft mc = Minecraft.func_71410_x();
    @Setting(name="MainSlot", description="Slot of the weapon you want held")
    private IntegerSetting mainSlot = new IntegerSetting(0, 0, 8);
    @Setting(name="PickupStash")
    private BooleanSetting pickupStash = new BooleanSetting(true);
    @Setting(name="InvMode", description="A bit bannable")
    private BooleanSetting invMode = new BooleanSetting(false);

    @Enable
    public void onEnable() {
        int oldSlot = CUM.mc.field_71439_g.field_71071_by.field_70461_c;
        if (!this.invMode.isEnabled()) {
            for (int i = 0; i < 8; ++i) {
                ItemStack a = CUM.mc.field_71439_g.field_71071_by.func_70301_a(i);
                if (a == null || !a.func_82833_r().contains("Snowball")) continue;
                InventoryUtils.throwSlot(i);
            }
        } else {
            int snowballSlot = InventoryUtils.getAvailableHotbarSlot("Snowball");
            if (snowballSlot == -1 || InventoryUtils.getAllSlots(snowballSlot, "Snowball").size() == 0) {
                CF4M.INSTANCE.moduleManager.toggle(this);
                return;
            }
            InventoryUtils.throwSlot(snowballSlot);
            for (int slotNum : InventoryUtils.getAllSlots(snowballSlot, "Snowball")) {
                ItemStack curInSlot = CUM.mc.field_71439_g.field_71071_by.func_70301_a(snowballSlot);
                if (curInSlot == null) {
                    CUM.mc.field_71442_b.func_78753_a(CUM.mc.field_71439_g.field_71069_bz.field_75152_c, slotNum, snowballSlot, 2, (EntityPlayer)CUM.mc.field_71439_g);
                }
                InventoryUtils.throwSlot(snowballSlot);
            }
        }
        CUM.mc.field_71439_g.field_71071_by.field_70461_c = this.mainSlot.getCurrent() > 0 && this.mainSlot.getCurrent() <= 8 ? this.mainSlot.getCurrent() - 1 : oldSlot;
        if (this.pickupStash.isEnabled()) {
            CUM.mc.field_71439_g.func_71165_d("/pickupstash");
        }
        CF4M.INSTANCE.moduleManager.toggle(this);
    }
}

