/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.BlockPos
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.Vec3
 */
package xyz.apfelmus.cheeto.client.modules.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cf4m.annotation.Event;
import xyz.apfelmus.cf4m.annotation.Setting;
import xyz.apfelmus.cf4m.annotation.module.Disable;
import xyz.apfelmus.cf4m.annotation.module.Enable;
import xyz.apfelmus.cf4m.annotation.module.Module;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cheeto.client.events.ClientTickEvent;
import xyz.apfelmus.cheeto.client.events.Render3DEvent;
import xyz.apfelmus.cheeto.client.modules.world.AutoMithril;
import xyz.apfelmus.cheeto.client.settings.BooleanSetting;
import xyz.apfelmus.cheeto.client.settings.IntegerSetting;
import xyz.apfelmus.cheeto.client.utils.client.Rotation;
import xyz.apfelmus.cheeto.client.utils.client.RotationUtils;
import xyz.apfelmus.cheeto.client.utils.math.RandomUtil;
import xyz.apfelmus.cheeto.client.utils.math.TimeHelper;
import xyz.apfelmus.cheeto.client.utils.render.Render3DUtils;

@Module(name="AutoMine", category=Category.WORLD)
public class AutoMine
implements Runnable {
    @Setting(name="Sneak", description="Makes the player sneak while mining")
    private BooleanSetting sneak = new BooleanSetting(false);
    @Setting(name="CoalOre")
    private BooleanSetting coalOre = new BooleanSetting(true);
    @Setting(name="LapisOre")
    private BooleanSetting lapisOre = new BooleanSetting(true);
    @Setting(name="IronOre")
    private BooleanSetting ironOre = new BooleanSetting(true);
    @Setting(name="GoldOre")
    private BooleanSetting goldOre = new BooleanSetting(true);
    @Setting(name="RedstoneOre")
    private BooleanSetting redstoneOre = new BooleanSetting(true);
    @Setting(name="DiamondOre")
    private BooleanSetting diamondOre = new BooleanSetting(true);
    @Setting(name="EmeraldOre")
    private BooleanSetting emeraldOre = new BooleanSetting(true);
    @Setting(name="GoldBlocks")
    private BooleanSetting goldBlocks = new BooleanSetting(true);
    @Setting(name="LookTime")
    private IntegerSetting lookTime = new IntegerSetting(500, 0, 2500);
    @Setting(name="MaxMineTime", description="Set to slightly more than it takes to mine")
    private IntegerSetting maxMineTime = new IntegerSetting(5000, 0, 10000);
    private static Minecraft mc = Minecraft.func_71410_x();
    private Thread thread;
    private Map<BlockPos, List<Vec3>> blocksNear = new HashMap<BlockPos, List<Vec3>>();
    private List<BlockPos> blacklist = new ArrayList<BlockPos>();
    private int delayMs = 500;
    private BlockPos curBlockPos;
    private Block curBlock;
    private TimeHelper mineTimer;
    private Vec3 startRot;
    private Vec3 endRot;
    private MineState mineState = MineState.CHOOSE;

    @Enable
    public void onEnable() {
        this.mineState = MineState.CHOOSE;
        this.blocksNear.clear();
        this.blacklist.clear();
        this.mineTimer = new TimeHelper();
        this.curBlockPos = null;
        this.curBlock = null;
        this.startRot = null;
        this.endRot = null;
        KeyBinding.func_74510_a((int)AutoMine.mc.field_71474_y.field_74311_E.func_151463_i(), (boolean)this.sneak.isEnabled());
    }

    @Disable
    public void onDisable() {
        KeyBinding.func_74510_a((int)AutoMine.mc.field_71474_y.field_74312_F.func_151463_i(), (boolean)false);
        KeyBinding.func_74510_a((int)AutoMine.mc.field_71474_y.field_74311_E.func_151463_i(), (boolean)false);
        AutoMine.mc.field_71442_b.func_78767_c();
    }

    @Event
    public void onTick(ClientTickEvent event) {
        if (AutoMine.mc.field_71439_g == null || AutoMine.mc.field_71441_e == null) {
            return;
        }
        if (this.thread == null || !this.thread.isAlive()) {
            this.thread = new Thread(this);
            this.thread.setDaemon(false);
            this.thread.setPriority(1);
            this.thread.start();
        }
        Iterator<Map.Entry<BlockPos, List<Vec3>>> it = this.blocksNear.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<BlockPos, List<Vec3>> entry = it.next();
            Vec3 randPoint = entry.getValue().get(RandomUtil.randBetween(0, entry.getValue().size() - 1));
            MovingObjectPosition mop = AutoMine.mc.field_71441_e.func_72933_a(AutoMine.mc.field_71439_g.func_174824_e(1.0f), randPoint);
            if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
                if (mop.func_178782_a().equals((Object)entry.getKey()) && randPoint.func_72438_d(AutoMine.mc.field_71439_g.func_174824_e(1.0f)) < (double)AutoMine.mc.field_71442_b.func_78757_d()) continue;
                it.remove();
                continue;
            }
            it.remove();
        }
        if (!this.blocksNear.containsKey((Object)this.curBlockPos)) {
            this.mineState = MineState.CHOOSE;
        }
        switch (this.mineState) {
            case CHOOSE: {
                IBlockState ibs;
                AutoMithril.BlockPosWithVec closest = this.getClosestBlock(null);
                if (closest != null) {
                    this.curBlockPos = closest.getBlockPos();
                    ibs = AutoMine.mc.field_71441_e.func_180495_p(this.curBlockPos);
                    if (ibs != null) {
                        this.curBlock = ibs.func_177230_c();
                    }
                    this.startRot = closest.getVec3();
                    this.endRot = null;
                    RotationUtils.setup(RotationUtils.getRotation(closest.getVec3()), (long)this.lookTime.getCurrent());
                    this.mineState = MineState.LOOK;
                    break;
                }
                KeyBinding.func_74510_a((int)AutoMine.mc.field_71474_y.field_74312_F.func_151463_i(), (boolean)false);
                break;
            }
            case MINE: {
                if (this.mineTimer.hasReached(this.maxMineTime.getCurrent().intValue())) {
                    this.blocksNear.remove((Object)this.curBlockPos);
                    this.mineState = MineState.CHOOSE;
                    break;
                }
                IBlockState ibs = AutoMine.mc.field_71441_e.func_180495_p(this.curBlockPos);
                if (ibs == null) break;
                if (this.curBlock != null && ibs.func_177230_c() != this.curBlock || ibs.func_177230_c() == Blocks.field_150357_h || ibs.func_177230_c() == Blocks.field_150350_a) {
                    this.blocksNear.remove((Object)this.curBlockPos);
                    this.mineState = MineState.CHOOSE;
                    this.curBlock = null;
                    this.startRot = null;
                    this.endRot = null;
                    break;
                }
                if (AutoMine.mc.field_71474_y.field_74312_F.func_151470_d() || AutoMine.mc.field_71462_r != null) break;
                AutoMine.mc.field_71415_G = true;
                KeyBinding.func_74507_a((int)AutoMine.mc.field_71474_y.field_74312_F.func_151463_i());
                KeyBinding.func_74510_a((int)AutoMine.mc.field_71474_y.field_74312_F.func_151463_i(), (boolean)true);
            }
        }
    }

    @Event
    public void onRender(Render3DEvent event) {
        for (BlockPos bp : new ArrayList(((Map)new HashMap<BlockPos, List<Vec3>>(this.blocksNear).clone()).keySet())) {
            if (bp.equals((Object)this.curBlockPos)) continue;
            Render3DUtils.renderEspBox(bp, event.partialTicks, -16711681);
        }
        if (this.blocksNear.containsKey((Object)this.curBlockPos)) {
            Render3DUtils.renderEspBox(this.curBlockPos, event.partialTicks, -65536);
        }
        if (this.startRot != null && this.endRot != null) {
            Render3DUtils.drawLine(this.startRot, this.endRot, 1.0f, event.partialTicks);
        }
        if (this.startRot != null) {
            Render3DUtils.renderSmallBox(this.startRot, -16711936);
        }
        if (this.endRot != null) {
            Render3DUtils.renderSmallBox(this.endRot, -65536);
        }
        switch (this.mineState) {
            case LOOK: {
                if (System.currentTimeMillis() <= RotationUtils.endTime) {
                    RotationUtils.update();
                    break;
                }
                if (!RotationUtils.done) {
                    RotationUtils.update();
                }
                this.mineTimer.reset();
                AutoMithril.BlockPosWithVec next = this.getClosestBlock(this.curBlockPos);
                if (next != null && this.blocksNear.containsKey((Object)this.curBlockPos)) {
                    this.blocksNear.get((Object)this.curBlockPos).stream().min(Comparator.comparing(v -> Float.valueOf(RotationUtils.getNeededChange(RotationUtils.getRotation(next.getVec3()), RotationUtils.getRotation(v)).getValue()))).ifPresent(nextPointOnSameBlock -> {
                        this.curBlock = AutoMine.mc.field_71441_e.func_180495_p(this.curBlockPos).func_177230_c();
                        this.endRot = nextPointOnSameBlock;
                        RotationUtils.setup(RotationUtils.getRotation(nextPointOnSameBlock), (long)this.maxMineTime.getCurrent());
                    });
                }
                this.mineState = MineState.MINE;
                break;
            }
            case MINE: {
                if (System.currentTimeMillis() <= RotationUtils.endTime) {
                    RotationUtils.update();
                    break;
                }
                this.startRot = null;
                this.endRot = null;
                if (RotationUtils.done) break;
                RotationUtils.update();
            }
        }
    }

    private void addBlockIfHittable(BlockPos xyz) {
        List<Vec3> pointsOnBlock = RotationUtils.getPointsOnBlock(xyz);
        for (Vec3 point : pointsOnBlock) {
            MovingObjectPosition mop = AutoMine.mc.field_71441_e.func_72933_a(AutoMine.mc.field_71439_g.func_174824_e(1.0f), point);
            if (mop == null || mop.field_72313_a != MovingObjectPosition.MovingObjectType.BLOCK || !mop.func_178782_a().equals((Object)xyz) || !(point.func_72438_d(AutoMine.mc.field_71439_g.func_174824_e(1.0f)) < (double)AutoMine.mc.field_71442_b.func_78757_d())) continue;
            if (!this.blocksNear.containsKey((Object)xyz)) {
                this.blocksNear.put(xyz, new ArrayList<Vec3>(Collections.singletonList(point)));
                continue;
            }
            this.blocksNear.get((Object)xyz).add(point);
        }
    }

    private AutoMithril.BlockPosWithVec getClosestBlock(BlockPos excluding) {
        BlockPos closest = null;
        Rotation closestRot = null;
        Vec3 closestPoint = null;
        ArrayList<BlockPos> asd = new ArrayList<BlockPos>(this.blocksNear.keySet());
        asd.remove((Object)excluding);
        for (BlockPos bp : asd) {
            for (Vec3 point : this.blocksNear.get((Object)bp)) {
                Rotation endRot = RotationUtils.getRotation(point);
                Rotation needed = RotationUtils.getNeededChange(endRot);
                if (closestRot != null && needed.getValue() >= closestRot.getValue()) continue;
                closest = bp;
                closestRot = needed;
                closestPoint = point;
            }
        }
        if (closest != null) {
            return new AutoMithril.BlockPosWithVec(closest, closestPoint);
        }
        return null;
    }

    @Override
    public void run() {
        while (!this.thread.isInterrupted() && AutoMine.mc.field_71439_g != null && AutoMine.mc.field_71441_e != null) {
            if (CF4M.INSTANCE.moduleManager.isEnabled(this)) {
                int radius = 6;
                int px = MathHelper.func_76128_c((double)AutoMine.mc.field_71439_g.field_70165_t);
                int py = MathHelper.func_76128_c((double)(AutoMine.mc.field_71439_g.field_70163_u + 1.0));
                int pz = MathHelper.func_76128_c((double)AutoMine.mc.field_71439_g.field_70161_v);
                Vec3 eyes = AutoMine.mc.field_71439_g.func_174824_e(1.0f);
                for (int x = px - radius; x < px + radius + 1; ++x) {
                    for (int y = py - radius; y < py + radius + 1; ++y) {
                        for (int z = pz - radius; z < pz + radius + 1; ++z) {
                            BlockPos xyz = new BlockPos(x, y, z);
                            IBlockState bs = AutoMine.mc.field_71441_e.func_180495_p(xyz);
                            if (this.blocksNear.containsKey((Object)xyz) || this.blacklist.contains((Object)xyz)) continue;
                            Block block = bs.func_177230_c();
                            if (!(Math.sqrt(xyz.func_177957_d(eyes.field_72450_a, eyes.field_72448_b, eyes.field_72449_c)) <= 6.0)) continue;
                            if (block == Blocks.field_150365_q && this.coalOre.isEnabled()) {
                                this.addBlockIfHittable(xyz);
                                continue;
                            }
                            if (block == Blocks.field_150369_x && this.lapisOre.isEnabled()) {
                                this.addBlockIfHittable(xyz);
                                continue;
                            }
                            if (block == Blocks.field_150366_p && this.ironOre.isEnabled()) {
                                this.addBlockIfHittable(xyz);
                                continue;
                            }
                            if (block == Blocks.field_150352_o && this.goldOre.isEnabled()) {
                                this.addBlockIfHittable(xyz);
                                continue;
                            }
                            if ((block == Blocks.field_150450_ax || block == Blocks.field_150439_ay) && this.redstoneOre.isEnabled()) {
                                this.addBlockIfHittable(xyz);
                                continue;
                            }
                            if (block == Blocks.field_150482_ag && this.diamondOre.isEnabled()) {
                                this.addBlockIfHittable(xyz);
                                continue;
                            }
                            if (block == Blocks.field_150412_bA && this.emeraldOre.isEnabled()) {
                                this.addBlockIfHittable(xyz);
                                continue;
                            }
                            if (block != Blocks.field_150340_R || !this.goldBlocks.isEnabled()) continue;
                            this.addBlockIfHittable(xyz);
                        }
                    }
                }
                for (BlockPos bp : new HashMap<BlockPos, List<Vec3>>(this.blocksNear).keySet()) {
                    IBlockState state = AutoMine.mc.field_71441_e.func_180495_p(bp);
                    Block block = null;
                    if (state != null) {
                        block = state.func_177230_c();
                    }
                    if (!(Math.sqrt(bp.func_177957_d(eyes.field_72450_a, eyes.field_72448_b, eyes.field_72449_c)) > 5.0) && block != Blocks.field_150357_h && block != Blocks.field_150350_a) continue;
                    this.blocksNear.remove((Object)bp);
                }
                try {
                    Thread.sleep(this.delayMs);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            this.thread.interrupt();
        }
        this.thread = null;
    }

    static enum MineState {
        CHOOSE,
        LOOK,
        MINE;

    }
}

