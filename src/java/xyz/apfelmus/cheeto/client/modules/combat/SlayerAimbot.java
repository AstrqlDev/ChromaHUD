/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityArmorStand
 *  net.minecraft.entity.monster.EntityEnderman
 *  net.minecraft.entity.monster.EntitySpider
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.passive.EntityWolf
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 */
package xyz.apfelmus.cheeto.client.modules.combat;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import xyz.apfelmus.cf4m.annotation.Event;
import xyz.apfelmus.cf4m.annotation.Setting;
import xyz.apfelmus.cf4m.annotation.module.Enable;
import xyz.apfelmus.cf4m.annotation.module.Module;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cheeto.client.events.ClientTickEvent;
import xyz.apfelmus.cheeto.client.events.Render3DEvent;
import xyz.apfelmus.cheeto.client.events.WorldUnloadEvent;
import xyz.apfelmus.cheeto.client.settings.BooleanSetting;
import xyz.apfelmus.cheeto.client.settings.IntegerSetting;
import xyz.apfelmus.cheeto.client.utils.client.Rotation;
import xyz.apfelmus.cheeto.client.utils.client.RotationUtils;
import xyz.apfelmus.cheeto.client.utils.skyblock.SkyblockUtils;

@Module(name="SlayerAimbot", category=Category.COMBAT)
public class SlayerAimbot {
    @Setting(name="AimSpeed")
    private IntegerSetting aimSpeed = new IntegerSetting(45, 5, 100);
    @Setting(name="Range")
    private IntegerSetting range = new IntegerSetting(4, 3, 7);
    @Setting(name="Blatant")
    private BooleanSetting blatant = new BooleanSetting(false);
    @Setting(name="Minibosses")
    private BooleanSetting miniBosses = new BooleanSetting(true);
    @Setting(name="DisableForTP")
    private BooleanSetting disable = new BooleanSetting(false);
    private static Minecraft mc = Minecraft.func_71410_x();
    private static EntityArmorStand slayerStand;
    private static Entity slayerMob;
    private static boolean isMini;
    private static final Map<String, Class<?>> slayerMap;
    private static final Map<String, Class<?>> miniMap;

    @Enable
    public void onEnable() {
        slayerMob = null;
        slayerStand = null;
        isMini = false;
    }

    @Event
    public void onTick(ClientTickEvent event) {
        if (slayerMob == null) {
            HashMap allPossibleSlayers = new HashMap();
            HashMap allPossibleMinis = new HashMap();
            int rongo = this.range.getCurrent();
            for (Entity e : SlayerAimbot.mc.field_71441_e.func_175674_a((Entity)SlayerAimbot.mc.field_71439_g, SlayerAimbot.mc.field_71439_g.func_174813_aQ().func_72314_b((double)rongo, (double)rongo, (double)rongo), null)) {
                if (!(e instanceof EntityArmorStand)) continue;
                slayerMap.forEach((k, v) -> {
                    Entity slay;
                    if (e.func_95999_t().contains((CharSequence)k) && e.func_70032_d((Entity)SlayerAimbot.mc.field_71439_g) <= (float)this.range.getCurrent().intValue() && (slay = SkyblockUtils.getEntityCuttingOtherEntity(e, v)) != null) {
                        allPossibleSlayers.put(slay, (EntityArmorStand)e);
                    }
                });
                if (!this.miniBosses.isEnabled()) continue;
                miniMap.forEach((k, v) -> {
                    Entity mini;
                    if (e.func_95999_t().contains((CharSequence)k) && e.func_70032_d((Entity)SlayerAimbot.mc.field_71439_g) <= (float)this.range.getCurrent().intValue() && (mini = SkyblockUtils.getEntityCuttingOtherEntity(e, v)) != null) {
                        allPossibleMinis.put(mini, (EntityArmorStand)e);
                    }
                });
            }
            if (!allPossibleSlayers.isEmpty()) {
                slayerMob = Collections.min(allPossibleSlayers.keySet(), Comparator.comparing(e2 -> Float.valueOf(e2.func_70032_d((Entity)SlayerAimbot.mc.field_71439_g))));
                slayerStand = (EntityArmorStand)allPossibleSlayers.get((Object)slayerMob);
            } else if (!allPossibleMinis.isEmpty()) {
                slayerMob = Collections.min(allPossibleMinis.keySet(), Comparator.comparing(e2 -> Float.valueOf(e2.func_70032_d((Entity)SlayerAimbot.mc.field_71439_g))));
                slayerStand = (EntityArmorStand)allPossibleMinis.get((Object)slayerMob);
                isMini = true;
            }
        } else if (this.miniBosses.isEnabled() && isMini) {
            HashMap allPossibleSlayers = new HashMap();
            int rongo = this.range.getCurrent();
            for (Entity e : SlayerAimbot.mc.field_71441_e.func_175674_a((Entity)SlayerAimbot.mc.field_71439_g, SlayerAimbot.mc.field_71439_g.func_174813_aQ().func_72314_b((double)rongo, (double)rongo, (double)rongo), null)) {
                if (!(e instanceof EntityArmorStand)) continue;
                slayerMap.forEach((k, v) -> {
                    Entity slay;
                    if (e.func_95999_t().contains((CharSequence)k) && e.func_70032_d((Entity)SlayerAimbot.mc.field_71439_g) <= (float)this.range.getCurrent().intValue() && (slay = SkyblockUtils.getEntityCuttingOtherEntity(e, v)) != null) {
                        allPossibleSlayers.put(slay, (EntityArmorStand)e);
                    }
                });
            }
            if (!allPossibleSlayers.isEmpty()) {
                slayerMob = Collections.min(allPossibleSlayers.keySet(), Comparator.comparing(e2 -> Float.valueOf(e2.func_70032_d((Entity)SlayerAimbot.mc.field_71439_g))));
                slayerStand = (EntityArmorStand)allPossibleSlayers.get((Object)slayerMob);
                isMini = false;
            }
        }
    }

    @Event
    public void onRender(Render3DEvent event) {
        ItemStack currentHeld;
        if (this.disable.isEnabled() && ((currentHeld = SlayerAimbot.mc.field_71439_g.func_70694_bm()) == null || currentHeld.func_82833_r().contains("Aspect of the "))) {
            return;
        }
        if (slayerMob != null) {
            int mobHp = this.getSlayerHp();
            if (slayerMob.func_70032_d((Entity)SlayerAimbot.mc.field_71439_g) > 5.0f || SlayerAimbot.slayerMob.field_70128_L || mobHp == 0) {
                slayerMob = null;
                slayerStand = null;
                return;
            }
            if (!(this.blatant.isEnabled() || SlayerAimbot.mc.field_71476_x != null && SlayerAimbot.mc.field_71476_x.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY && SlayerAimbot.mc.field_71476_x.field_72308_g.equals((Object)slayerMob))) {
                double n = RotationUtils.fovFromEntity(slayerMob);
                double complimentSpeed = n * ThreadLocalRandom.current().nextDouble(-1.47328, 2.48293) / 100.0;
                float val = (float)(-(complimentSpeed + n / (101.0 - (double)((float)ThreadLocalRandom.current().nextDouble((double)this.aimSpeed.getCurrent().intValue() - 4.723847, this.aimSpeed.getCurrent().intValue())))));
                SlayerAimbot.mc.field_71439_g.field_70177_z += val;
            } else if (this.blatant.isEnabled()) {
                Rotation needed = RotationUtils.getNeededChange(RotationUtils.getRotation(slayerMob.func_174791_d()));
                SlayerAimbot.mc.field_71439_g.field_70177_z += needed.getYaw();
            }
        }
    }

    @Event
    public void onWorldUnload(WorldUnloadEvent event) {
        slayerMob = null;
    }

    private int getSlayerHp() {
        String stripped;
        int mobHp = -1;
        Pattern pattern = Pattern.compile(".+? (\\d+)[Mk]?");
        Matcher mat = pattern.matcher(stripped = SkyblockUtils.stripString(slayerStand.func_70005_c_()));
        if (mat.matches()) {
            try {
                mobHp = Integer.parseInt(mat.group(1));
            }
            catch (NumberFormatException numberFormatException) {
                // empty catch block
            }
        }
        return mobHp;
    }

    static {
        slayerMap = new HashMap<String, Class<?>>(){
            {
                this.put("Revenant Horror", EntityZombie.class);
                this.put("Atoned Horror", EntityZombie.class);
                this.put("Tarantula Broodfather", EntitySpider.class);
                this.put("Sven Packmaster", EntityWolf.class);
                this.put("Voidgloom Seraph", EntityEnderman.class);
            }
        };
        miniMap = new HashMap<String, Class<?>>(){
            {
                this.put("Revenant Champion", EntityZombie.class);
                this.put("Deformed Revenant", EntityZombie.class);
                this.put("Atoned Champion", EntityZombie.class);
                this.put("Atoned Revenant", EntityZombie.class);
                this.put("Tarantula Beast", EntitySpider.class);
                this.put("Mutant Tarantula", EntitySpider.class);
                this.put("Sven Follower", EntityWolf.class);
                this.put("Sven Alpha", EntityWolf.class);
                this.put("Voidling Devotee", EntityEnderman.class);
                this.put("Voidling Radical", EntityEnderman.class);
                this.put("Voidcrazed Maniac", EntityEnderman.class);
            }
        };
    }
}

