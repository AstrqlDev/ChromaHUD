/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Iterables
 *  com.google.common.collect.Lists
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityArmorStand
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityFireball
 *  net.minecraft.entity.projectile.EntityFishHook
 *  net.minecraft.init.Blocks
 *  net.minecraft.scoreboard.Score
 *  net.minecraft.scoreboard.ScoreObjective
 *  net.minecraft.scoreboard.ScorePlayerTeam
 *  net.minecraft.scoreboard.Scoreboard
 *  net.minecraft.scoreboard.Team
 *  net.minecraft.util.StringUtils
 *  net.minecraft.world.World
 */
package xyz.apfelmus.cheeto.client.utils.skyblock;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Blocks;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;

public class SkyblockUtils {
    private static Minecraft mc = Minecraft.func_71410_x();
    private static final ArrayList<Block> interactables = new ArrayList<Block>(Arrays.asList(new Block[]{Blocks.field_180410_as, Blocks.field_150467_bQ, Blocks.field_150461_bJ, Blocks.field_150324_C, Blocks.field_180412_aq, Blocks.field_150382_bo, Blocks.field_150483_bI, Blocks.field_150462_ai, Blocks.field_150486_ae, Blocks.field_180409_at, Blocks.field_150453_bW, Blocks.field_180402_cm, Blocks.field_150367_z, Blocks.field_150409_cd, Blocks.field_150381_bn, Blocks.field_150477_bB, Blocks.field_150460_al, Blocks.field_150438_bZ, Blocks.field_180411_ar, Blocks.field_150442_at, Blocks.field_150323_B, Blocks.field_150455_bV, Blocks.field_150441_bU, Blocks.field_150416_aS, Blocks.field_150413_aR, Blocks.field_150472_an, Blocks.field_150444_as, Blocks.field_150415_aT, Blocks.field_150447_bR, Blocks.field_150471_bO, Blocks.field_150430_aB, Blocks.field_180413_ao, Blocks.field_150465_bP}));

    public static void ghostBlock() {
        if (SkyblockUtils.mc.field_71476_x.func_178782_a() == null) {
            return;
        }
        Block block = Minecraft.func_71410_x().field_71441_e.func_180495_p(SkyblockUtils.mc.field_71476_x.func_178782_a()).func_177230_c();
        if (!interactables.contains((Object)block)) {
            SkyblockUtils.mc.field_71441_e.func_175698_g(SkyblockUtils.mc.field_71476_x.func_178782_a());
        }
    }

    public static int getMobHp(EntityArmorStand aStand) {
        String stripped;
        double mobHp = -1.0;
        Pattern pattern = Pattern.compile(".+? ([.\\d]+)[Mk]?/[.\\d]+[Mk]?");
        Matcher mat = pattern.matcher(stripped = SkyblockUtils.stripString(aStand.func_70005_c_()));
        if (mat.matches()) {
            try {
                mobHp = Double.parseDouble(mat.group(1));
            }
            catch (NumberFormatException numberFormatException) {
                // empty catch block
            }
        }
        return (int)Math.ceil(mobHp);
    }

    public static Entity getEntityCuttingOtherEntity(Entity e, Class<?> entityType) {
        List possible = SkyblockUtils.mc.field_71441_e.func_175674_a(e, e.func_174813_aQ().func_72314_b(0.3, 2.0, 0.3), a -> !a.field_70128_L && !a.equals((Object)SkyblockUtils.mc.field_71439_g) && !(a instanceof EntityArmorStand) && !(a instanceof EntityFireball) && !(a instanceof EntityFishHook) && (entityType == null || entityType.isInstance(a)));
        if (!possible.isEmpty()) {
            return Collections.min(possible, Comparator.comparing(e2 -> Float.valueOf(e2.func_70032_d(e))));
        }
        return null;
    }

    public static Location getLocation() {
        if (SkyblockUtils.isInIsland()) {
            return Location.ISLAND;
        }
        if (SkyblockUtils.isInHub()) {
            return Location.HUB;
        }
        if (SkyblockUtils.isAtLift()) {
            return Location.LIFT;
        }
        if (SkyblockUtils.isInSkyblock()) {
            return Location.SKYBLOCK;
        }
        if (SkyblockUtils.isInLobby()) {
            return Location.LOBBY;
        }
        IBlockState ibs = SkyblockUtils.mc.field_71441_e.func_180495_p(SkyblockUtils.mc.field_71439_g.func_180425_c().func_177977_b());
        if (ibs != null && ibs.func_177230_c() == Blocks.field_150344_f) {
            return Location.LIMBO;
        }
        return Location.NONE;
    }

    public static boolean isInIsland() {
        return SkyblockUtils.hasLine("Your Island");
    }

    public static boolean isInHub() {
        return SkyblockUtils.hasLine("Village") && !SkyblockUtils.hasLine("Dwarven");
    }

    public static boolean isAtLift() {
        return SkyblockUtils.hasLine("The Lift");
    }

    public static boolean isInDungeon() {
        return SkyblockUtils.hasLine("Dungeon Cleared:") || SkyblockUtils.hasLine("Start");
    }

    public static boolean isInFloor(String floor) {
        return SkyblockUtils.hasLine("The Catacombs (" + floor + ")");
    }

    public static boolean isInSkyblock() {
        return SkyblockUtils.hasLine("SKYBLOCK");
    }

    public static boolean isInLobby() {
        return SkyblockUtils.hasLine("HYPIXEL") || SkyblockUtils.hasLine("PROTOTYPE");
    }

    public static boolean hasLine(String sbString) {
        ScoreObjective sbo;
        if (mc != null && SkyblockUtils.mc.field_71439_g != null && (sbo = SkyblockUtils.mc.field_71441_e.func_96441_U().func_96539_a(1)) != null) {
            List<String> scoreboard = SkyblockUtils.getSidebarLines();
            scoreboard.add(StringUtils.func_76338_a((String)sbo.func_96678_d()));
            for (String s : scoreboard) {
                String validated = SkyblockUtils.stripString(s);
                if (!validated.contains(sbString)) continue;
                return true;
            }
        }
        return false;
    }

    public static String stripString(String s) {
        char[] nonValidatedString = StringUtils.func_76338_a((String)s).toCharArray();
        StringBuilder validated = new StringBuilder();
        for (char a : nonValidatedString) {
            if (a >= '\u007f' || a <= '\u0014') continue;
            validated.append(a);
        }
        return validated.toString();
    }

    private static List<String> getSidebarLines() {
        ArrayList<String> lines = new ArrayList<String>();
        Scoreboard scoreboard = Minecraft.func_71410_x().field_71441_e.func_96441_U();
        if (scoreboard == null) {
            return lines;
        }
        ScoreObjective objective = scoreboard.func_96539_a(1);
        if (objective == null) {
            return lines;
        }
        ArrayList scores = scoreboard.func_96534_i(objective);
        ArrayList list = new ArrayList();
        for (Score s : scores) {
            if (s == null || s.func_96653_e() == null || s.func_96653_e().startsWith("#")) continue;
            list.add(s);
        }
        scores = list.size() > 15 ? Lists.newArrayList((Iterable)Iterables.skip(list, (int)(scores.size() - 15))) : list;
        for (Score score : scores) {
            ScorePlayerTeam team = scoreboard.func_96509_i(score.func_96653_e());
            lines.add(ScorePlayerTeam.func_96667_a((Team)team, (String)score.func_96653_e()));
        }
        return lines;
    }

    public static void silentUse(int mainSlot, int useSlot) {
        int oldSlot = SkyblockUtils.mc.field_71439_g.field_71071_by.field_70461_c;
        if (useSlot > 0 && useSlot <= 9) {
            SkyblockUtils.mc.field_71439_g.field_71071_by.field_70461_c = useSlot - 1;
            SkyblockUtils.mc.field_71442_b.func_78769_a((EntityPlayer)SkyblockUtils.mc.field_71439_g, (World)SkyblockUtils.mc.field_71441_e, SkyblockUtils.mc.field_71439_g.func_70694_bm());
        }
        if (mainSlot > 0 && mainSlot <= 9) {
            SkyblockUtils.mc.field_71439_g.field_71071_by.field_70461_c = mainSlot - 1;
        } else if (mainSlot == 0) {
            SkyblockUtils.mc.field_71439_g.field_71071_by.field_70461_c = oldSlot;
        }
    }

    public static enum Location {
        ISLAND,
        HUB,
        LIFT,
        SKYBLOCK,
        LOBBY,
        LIMBO,
        NONE;

    }
}

