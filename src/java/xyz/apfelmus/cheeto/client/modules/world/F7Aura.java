/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityArmorStand
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.BlockPos
 */
package xyz.apfelmus.cheeto.client.modules.world;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import xyz.apfelmus.cf4m.annotation.Event;
import xyz.apfelmus.cf4m.annotation.Setting;
import xyz.apfelmus.cf4m.annotation.module.Enable;
import xyz.apfelmus.cf4m.annotation.module.Module;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cheeto.client.events.ClientTickEvent;
import xyz.apfelmus.cheeto.client.events.GuiOpenEvent;
import xyz.apfelmus.cheeto.client.events.WorldUnloadEvent;
import xyz.apfelmus.cheeto.client.settings.FloatSetting;
import xyz.apfelmus.cheeto.client.utils.skyblock.SkyblockUtils;

@Module(name="F7Aura", category=Category.WORLD)
public class F7Aura {
    @Setting(name="Range")
    FloatSetting range = new FloatSetting(Float.valueOf(5.0f), Float.valueOf(0.0f), Float.valueOf(32.0f));
    private static Minecraft mc = Minecraft.func_71410_x();
    private static boolean clicked = false;
    private static List<Entity> terms = new ArrayList<Entity>();

    @Enable
    public void onEnable() {
        terms.clear();
        clicked = false;
    }

    @Event
    public void onTick(ClientTickEvent event) {
        try {
            if (!SkyblockUtils.isInDungeon()) {
                return;
            }
            BlockPos pp = F7Aura.mc.field_71439_g.func_180425_c();
            for (int i = pp.func_177956_o(); i > 0; --i) {
                Block b;
                IBlockState bs = F7Aura.mc.field_71441_e.func_180495_p(new BlockPos(pp.func_177958_n(), i, pp.func_177952_p()));
                if (bs == null || (b = bs.func_177230_c()) == Blocks.field_150350_a) continue;
                if (b != Blocks.field_150353_l && b != Blocks.field_150356_k) break;
                return;
            }
            for (Entity e : F7Aura.mc.field_71441_e.field_72996_f) {
                if (!(e instanceof EntityArmorStand) || !e.func_70005_c_().contains("CLICK HERE") || clicked || F7Aura.mc.field_71439_g.field_71070_bA.field_75152_c != 0 || terms.contains((Object)e) || !(e.func_70032_d((Entity)F7Aura.mc.field_71439_g) < this.range.getCurrent().floatValue())) continue;
                F7Aura.mc.field_71442_b.func_78768_b((EntityPlayer)F7Aura.mc.field_71439_g, e);
                clicked = true;
                terms.add(e);
                break;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @Event
    public void onGui(GuiOpenEvent event) {
        clicked = false;
    }

    @Event
    public void onWorldLoad(WorldUnloadEvent event) {
        terms.clear();
        clicked = false;
    }
}

