/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockChest
 *  net.minecraft.block.BlockLever
 *  net.minecraft.block.BlockSkull
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.inventory.GuiChest
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntityChest
 *  net.minecraft.tileentity.TileEntitySkull
 *  net.minecraft.util.BlockPos
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.Vec3
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$Action
 */
package xyz.apfelmus.cheeto.client.modules.world;

import com.mojang.authlib.GameProfile;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cf4m.annotation.Event;
import xyz.apfelmus.cf4m.annotation.Setting;
import xyz.apfelmus.cf4m.annotation.module.Enable;
import xyz.apfelmus.cf4m.annotation.module.Module;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cheeto.client.events.ClientChatReceivedEvent;
import xyz.apfelmus.cheeto.client.events.ClientTickEvent;
import xyz.apfelmus.cheeto.client.events.PlayerInteractEvent;
import xyz.apfelmus.cheeto.client.events.Render3DEvent;
import xyz.apfelmus.cheeto.client.events.WorldUnloadEvent;
import xyz.apfelmus.cheeto.client.settings.BooleanSetting;
import xyz.apfelmus.cheeto.client.settings.FloatSetting;
import xyz.apfelmus.cheeto.client.settings.IntegerSetting;
import xyz.apfelmus.cheeto.client.utils.client.RotationUtils;
import xyz.apfelmus.cheeto.client.utils.render.Render3DUtils;
import xyz.apfelmus.cheeto.client.utils.skyblock.SkyblockUtils;

@Module(name="SecretAura", category=Category.WORLD)
public class SecretAura
implements Runnable {
    @Setting(name="ScanRange")
    private IntegerSetting scanRange = new IntegerSetting(7, 0, 8);
    @Setting(name="ClickRange")
    private FloatSetting clickRange = new FloatSetting(Float.valueOf(5.0f), Float.valueOf(0.0f), Float.valueOf(8.0f));
    @Setting(name="ClickSlot")
    private IntegerSetting clickSlot = new IntegerSetting(0, 0, 8);
    @Setting(name="Chests")
    private BooleanSetting chests = new BooleanSetting(true);
    @Setting(name="ChestClose")
    private BooleanSetting chestClose = new BooleanSetting(true);
    @Setting(name="Levers")
    private BooleanSetting levers = new BooleanSetting(true);
    @Setting(name="Essences")
    private BooleanSetting essences = new BooleanSetting(true);
    @Setting(name="StonklessStonk")
    private BooleanSetting stonklessStonk = new BooleanSetting(true);
    private static Minecraft mc = Minecraft.func_71410_x();
    private static List<BlockPos> clicked = new ArrayList<BlockPos>();
    private int delayMs = 500;
    private Thread thread;
    private List<BlockPos> blocksNear = new ArrayList<BlockPos>();
    private boolean inChest = false;
    private BlockPos selected;
    private static BlockPos lastPos;

    @Enable
    public void onEnable() {
        this.blocksNear.clear();
        clicked.clear();
        this.inChest = false;
        this.selected = null;
        lastPos = null;
    }

    @Event
    public void onTick(ClientTickEvent event) {
        if ((this.thread == null || !this.thread.isAlive()) && SecretAura.mc.field_71441_e != null && SecretAura.mc.field_71439_g != null && SkyblockUtils.isInDungeon()) {
            this.thread = new Thread(this);
            this.thread.setDaemon(false);
            this.thread.setPriority(1);
            this.thread.start();
        }
        if (SecretAura.mc.field_71441_e != null && SecretAura.mc.field_71439_g != null && this.thread != null) {
            if (!this.stonklessStonk.isEnabled()) {
                if (!this.inChest) {
                    Vec3 eyes = SecretAura.mc.field_71439_g.func_174824_e(1.0f);
                    for (BlockPos bp : new ArrayList<BlockPos>(this.blocksNear)) {
                        if (!(Math.sqrt(bp.func_177957_d(eyes.field_72450_a, eyes.field_72448_b, eyes.field_72449_c)) < (double)this.clickRange.getCurrent().floatValue())) continue;
                        IBlockState bs = SecretAura.mc.field_71441_e.func_180495_p(bp);
                        if (bs != null) {
                            if (bs.func_177230_c() == Blocks.field_150486_ae) {
                                this.inChest = true;
                            }
                            this.handleClick(bp);
                        } else {
                            this.blocksNear.remove((Object)bp);
                            clicked.add(bp);
                        }
                        break;
                    }
                }
            } else {
                clicked.clear();
            }
            if (SkyblockUtils.isInDungeon() && this.inChest && this.chestClose.isEnabled()) {
                if (SecretAura.mc.field_71462_r instanceof GuiChest) {
                    SecretAura.mc.field_71439_g.func_71053_j();
                    this.inChest = false;
                }
            } else {
                this.inChest = false;
            }
        }
    }

    @Event
    public void onRender(Render3DEvent event) {
        this.selected = null;
        for (BlockPos bp : new ArrayList<BlockPos>(this.blocksNear)) {
            if (this.selected == null && RotationUtils.lookingAt(bp, this.clickRange.getCurrent().floatValue())) {
                this.selected = bp;
                continue;
            }
            Render3DUtils.renderEspBox(bp, event.partialTicks, -1754827);
        }
        if (this.selected != null) {
            Render3DUtils.renderEspBox(this.selected, event.partialTicks, -19712);
        }
    }

    @Event
    public void onInteract(PlayerInteractEvent event) {
        if (this.stonklessStonk.isEnabled() && this.selected != null && !clicked.contains((Object)this.selected)) {
            MovingObjectPosition omo = SecretAura.mc.field_71476_x;
            if (omo != null && omo.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK && omo.func_178782_a().equals((Object)this.selected)) {
                return;
            }
            if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR || event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
                clicked.add(this.selected);
                this.handleClick(this.selected);
                SecretAura.mc.field_71439_g.func_71038_i();
            }
        }
    }

    @Event
    public void onWorldLoad(WorldUnloadEvent event) {
        clicked.clear();
        this.inChest = false;
    }

    @Event
    public void onChat(ClientChatReceivedEvent event) {
        if (event.message.func_150260_c().contains("locked")) {
            clicked.clear();
            this.inChest = false;
        }
    }

    @Override
    public void run() {
        while (!this.thread.isInterrupted()) {
            BlockPos curPos = SecretAura.mc.field_71439_g.func_180425_c();
            if (CF4M.INSTANCE.moduleManager.isEnabled(this)) {
                if (SkyblockUtils.isInDungeon() && !curPos.equals((Object)lastPos)) {
                    lastPos = curPos;
                    int radius = this.scanRange.getCurrent();
                    int px = MathHelper.func_76128_c((double)SecretAura.mc.field_71439_g.field_70165_t);
                    int py = MathHelper.func_76128_c((double)(SecretAura.mc.field_71439_g.field_70163_u + 1.0));
                    int pz = MathHelper.func_76128_c((double)SecretAura.mc.field_71439_g.field_70161_v);
                    Vec3 eyes = SecretAura.mc.field_71439_g.func_174824_e(1.0f);
                    for (int x = px - radius; x < px + radius + 1; ++x) {
                        for (int y = py - radius; y < py + radius + 1; ++y) {
                            for (int z = pz - radius; z < pz + radius + 1; ++z) {
                                BlockPos bp;
                                GameProfile gp;
                                TileEntityChest te;
                                BlockPos xyz = new BlockPos(x, y, z);
                                IBlockState bs = SecretAura.mc.field_71441_e.func_180495_p(xyz);
                                Block block = bs.func_177230_c();
                                if (clicked.contains((Object)xyz) || this.blocksNear.contains((Object)xyz) || !(Math.sqrt(xyz.func_177957_d(eyes.field_72450_a, eyes.field_72448_b, eyes.field_72449_c)) <= (double)this.scanRange.getCurrent().intValue())) continue;
                                if (this.chests.isEnabled() && block instanceof BlockChest) {
                                    te = (TileEntityChest)SecretAura.mc.field_71441_e.func_175625_s(xyz);
                                    if (te.field_145989_m == 0.0f) {
                                        this.blocksNear.add(xyz);
                                    }
                                }
                                if (this.levers.isEnabled() && block instanceof BlockLever) {
                                    this.blocksNear.add(xyz);
                                }
                                if (!this.essences.isEnabled() || !(block instanceof BlockSkull) || (gp = (te = (TileEntitySkull)SecretAura.mc.field_71441_e.func_175625_s(xyz)).func_152108_a()) == null || (bp = te.func_174877_v()) == null || !gp.getId().toString().equals("26bb1a8d-7c66-31c6-82d5-a9c04c94fb02")) continue;
                                this.blocksNear.add(xyz);
                            }
                        }
                    }
                    ArrayList<BlockPos> blocksToRemove = new ArrayList<BlockPos>();
                    for (BlockPos bp : this.blocksNear) {
                        IBlockState state = SecretAura.mc.field_71441_e.func_180495_p(bp);
                        Block block = null;
                        if (state != null) {
                            block = state.func_177230_c();
                        }
                        if (!(Math.sqrt(bp.func_177957_d(eyes.field_72450_a, eyes.field_72448_b, eyes.field_72449_c)) > (double)this.scanRange.getCurrent().intValue()) && block != Blocks.field_150350_a) continue;
                        blocksToRemove.add(bp);
                    }
                    this.blocksNear.removeAll(blocksToRemove);
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

    private void handleClick(BlockPos xyz) {
        if (this.clickSlot.getCurrent() != 0) {
            int holding = SecretAura.mc.field_71439_g.field_71071_by.field_70461_c;
            SecretAura.mc.field_71439_g.field_71071_by.field_70461_c = this.clickSlot.getCurrent();
            SecretAura.mc.field_71442_b.func_178890_a(SecretAura.mc.field_71439_g, SecretAura.mc.field_71441_e, SecretAura.mc.field_71439_g.field_71071_by.func_70448_g(), xyz, EnumFacing.func_176733_a((double)RotationUtils.getRotation(xyz).getYaw()), SecretAura.mc.field_71476_x.field_72307_f);
            SecretAura.mc.field_71439_g.field_71071_by.field_70461_c = holding;
        } else {
            SecretAura.mc.field_71442_b.func_178890_a(SecretAura.mc.field_71439_g, SecretAura.mc.field_71441_e, SecretAura.mc.field_71439_g.field_71071_by.func_70448_g(), xyz, EnumFacing.func_176733_a((double)RotationUtils.getRotation(xyz).getYaw()), SecretAura.mc.field_71476_x.field_72307_f);
        }
        if (!this.stonklessStonk.isEnabled()) {
            this.blocksNear.remove((Object)xyz);
            clicked.add(xyz);
        }
    }
}

