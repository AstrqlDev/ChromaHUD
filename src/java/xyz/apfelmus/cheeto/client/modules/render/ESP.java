/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.boss.EntityDragon
 *  net.minecraft.entity.item.EntityArmorStand
 *  net.minecraft.entity.monster.EntityEnderman
 *  net.minecraft.entity.monster.EntitySpider
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.passive.EntityBat
 *  net.minecraft.entity.passive.EntityWolf
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityChest
 */
package xyz.apfelmus.cheeto.client.modules.render;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import xyz.apfelmus.cf4m.annotation.Event;
import xyz.apfelmus.cf4m.annotation.Setting;
import xyz.apfelmus.cf4m.annotation.module.Module;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cheeto.client.events.Render3DEvent;
import xyz.apfelmus.cheeto.client.settings.BooleanSetting;
import xyz.apfelmus.cheeto.client.settings.FloatSetting;
import xyz.apfelmus.cheeto.client.utils.client.ColorUtils;
import xyz.apfelmus.cheeto.client.utils.render.Render3DUtils;
import xyz.apfelmus.cheeto.client.utils.skyblock.SkyblockUtils;

@Module(name="ESP", category=Category.RENDER)
public class ESP {
    @Setting(name="StarredMobs")
    private BooleanSetting starredMobs = new BooleanSetting(true);
    @Setting(name="Bats")
    private BooleanSetting bats = new BooleanSetting(true);
    @Setting(name="Dragon")
    private BooleanSetting dragon = new BooleanSetting(true);
    @Setting(name="Arrows")
    private BooleanSetting arrows = new BooleanSetting(true);
    @Setting(name="CrystalHollows")
    private BooleanSetting crystalHollows = new BooleanSetting(true);
    @Setting(name="Voidgloom")
    private BooleanSetting voidgloom = new BooleanSetting(true);
    @Setting(name="Sven")
    private BooleanSetting sven = new BooleanSetting(true);
    @Setting(name="Tarantula")
    private BooleanSetting tarantula = new BooleanSetting(true);
    @Setting(name="Revenant")
    private BooleanSetting revenant = new BooleanSetting(true);
    @Setting(name="BoxOpacity")
    public FloatSetting boxOpacity = new FloatSetting(Float.valueOf(0.3f), Float.valueOf(0.0f), Float.valueOf(1.0f));
    private static Minecraft mc = Minecraft.func_71410_x();
    private static Map<String, Integer> voidglooms = new HashMap<String, Integer>(){
        {
            this.put("Voidling Devotee", -26975);
            this.put("Voidling Radical", -4587356);
            this.put("Voidcrazed Maniac", -4587520);
        }
    };
    private static Map<String, Integer> svens = new HashMap<String, Integer>(){
        {
            this.put("Pack Enforcer", -26975);
            this.put("Sven Follower", -65502);
            this.put("Sven Alpha", -4587520);
        }
    };
    private static Map<String, Integer> tarantulas = new HashMap<String, Integer>(){
        {
            this.put("Tarantula Vermin", -26975);
            this.put("Tarantula Beast", -65502);
            this.put("Mutant Tarantula", -4587520);
        }
    };
    private static Map<String, Integer> revenants = new HashMap<String, Integer>(){
        {
            this.put("Revenant Sycophant", -26975);
            this.put("Revenant Champion", -65502);
            this.put("Deformed Revenant", -4587520);
            this.put("Atoned Champion", -65502);
            this.put("Atoned Revenant", -4587520);
        }
    };

    @Event
    public void onESP(Render3DEvent event) {
        if (this.starredMobs.isEnabled() || this.bats.isEnabled() || this.dragon.isEnabled() || this.arrows.isEnabled()) {
            for (Entity e : ESP.mc.field_71441_e.field_72996_f) {
                if (this.starredMobs.isEnabled() && e instanceof EntityArmorStand && !e.field_70128_L && e.func_95999_t().contains("\u272f")) {
                    Render3DUtils.renderStarredMobBoundingBox(e, event.partialTicks);
                }
                if (this.bats.isEnabled() && e instanceof EntityBat && !e.field_70128_L) {
                    Render3DUtils.renderBoundingBox(e, event.partialTicks, -10335698);
                }
                if (this.dragon.isEnabled() && e instanceof EntityDragon) {
                    for (Entity part : e.func_70021_al()) {
                        Render3DUtils.renderBoundingBox(part, event.partialTicks, ColorUtils.getChroma(3000.0f, 0));
                    }
                }
                if (this.arrows.isEnabled() && e instanceof EntityArrow) {
                    Render3DUtils.renderBoundingBox(e, event.partialTicks, -1);
                }
                if (!(e instanceof EntityArmorStand)) continue;
                if (this.voidgloom.isEnabled()) {
                    voidglooms.forEach((k, v) -> {
                        Entity yep;
                        if (!e.field_70128_L && e.func_95999_t().contains((CharSequence)k) && (yep = SkyblockUtils.getEntityCuttingOtherEntity(e, EntityEnderman.class)) != null) {
                            Render3DUtils.renderMiniBoundingBox(yep, event.partialTicks, v);
                        }
                    });
                }
                if (this.sven.isEnabled()) {
                    svens.forEach((k, v) -> {
                        Entity yep;
                        if (!e.field_70128_L && e.func_95999_t().contains((CharSequence)k) && (yep = SkyblockUtils.getEntityCuttingOtherEntity(e, EntityWolf.class)) != null) {
                            Render3DUtils.renderMiniBoundingBox(yep, event.partialTicks, v);
                        }
                    });
                }
                if (this.tarantula.isEnabled()) {
                    tarantulas.forEach((k, v) -> {
                        Entity yep;
                        if (!e.field_70128_L && e.func_95999_t().contains((CharSequence)k) && (yep = SkyblockUtils.getEntityCuttingOtherEntity(e, EntitySpider.class)) != null) {
                            Render3DUtils.renderMiniBoundingBox(yep, event.partialTicks, v);
                        }
                    });
                }
                if (!this.revenant.isEnabled()) continue;
                revenants.forEach((k, v) -> {
                    Entity yep;
                    if (!e.field_70128_L && e.func_95999_t().contains((CharSequence)k) && (yep = SkyblockUtils.getEntityCuttingOtherEntity(e, EntityZombie.class)) != null) {
                        Render3DUtils.renderMiniBoundingBox(yep, event.partialTicks, v);
                    }
                });
            }
        }
        if (this.crystalHollows.isEnabled()) {
            for (TileEntity te : Minecraft.func_71410_x().field_71441_e.field_147482_g) {
                if (!(te instanceof TileEntityChest)) continue;
                Render3DUtils.drawChestOutline(te.func_174877_v());
            }
        }
    }
}

