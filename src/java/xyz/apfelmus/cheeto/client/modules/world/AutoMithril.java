/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockColored
 *  net.minecraft.block.BlockStone
 *  net.minecraft.block.BlockStone$EnumType
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityOtherPlayerMP
 *  net.minecraft.client.gui.inventory.GuiChest
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityArmorStand
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumDyeColor
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.BlockPos
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.StringUtils
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package xyz.apfelmus.cheeto.client.modules.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.BlockStone;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StringUtils;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cf4m.annotation.Event;
import xyz.apfelmus.cf4m.annotation.Setting;
import xyz.apfelmus.cf4m.annotation.module.Disable;
import xyz.apfelmus.cf4m.annotation.module.Enable;
import xyz.apfelmus.cf4m.annotation.module.Module;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cheeto.client.events.ClientChatReceivedEvent;
import xyz.apfelmus.cheeto.client.events.ClientTickEvent;
import xyz.apfelmus.cheeto.client.events.Render3DEvent;
import xyz.apfelmus.cheeto.client.modules.player.CommMacro;
import xyz.apfelmus.cheeto.client.settings.BooleanSetting;
import xyz.apfelmus.cheeto.client.settings.IntegerSetting;
import xyz.apfelmus.cheeto.client.settings.ModeSetting;
import xyz.apfelmus.cheeto.client.utils.client.ChadUtils;
import xyz.apfelmus.cheeto.client.utils.client.ChatUtils;
import xyz.apfelmus.cheeto.client.utils.client.Rotation;
import xyz.apfelmus.cheeto.client.utils.client.RotationUtils;
import xyz.apfelmus.cheeto.client.utils.math.RandomUtil;
import xyz.apfelmus.cheeto.client.utils.math.TimeHelper;
import xyz.apfelmus.cheeto.client.utils.mining.Location;
import xyz.apfelmus.cheeto.client.utils.mining.PathPoint;
import xyz.apfelmus.cheeto.client.utils.render.Render3DUtils;
import xyz.apfelmus.cheeto.client.utils.skyblock.InventoryUtils;
import xyz.apfelmus.cheeto.client.utils.skyblock.SkyblockUtils;

@Module(name="AutoMithril", category=Category.WORLD)
public class AutoMithril
implements Runnable {
    @Setting(name="MiningSpot", description="Spot to mine at, requires Etherwarp")
    private ModeSetting miningSpot = new ModeSetting("None", this.getMiningSpotNames());
    @Setting(name="PickSlot")
    private IntegerSetting pickSlot = new IntegerSetting(0, 0, 8);
    @Setting(name="Sneak", description="Makes the player sneak while mining")
    private BooleanSetting sneak = new BooleanSetting(false);
    @Setting(name="BlueWool")
    private BooleanSetting blueWool = new BooleanSetting(true);
    @Setting(name="Prismarine")
    private BooleanSetting prismarine = new BooleanSetting(true);
    @Setting(name="Titanium")
    private BooleanSetting titanium = new BooleanSetting(true);
    @Setting(name="GrayShit")
    private BooleanSetting grayShit = new BooleanSetting(true);
    @Setting(name="LookTime")
    private IntegerSetting lookTime = new IntegerSetting(500, 0, 2500);
    @Setting(name="MaxMineTime", description="Set to slightly more than it takes to mine")
    private IntegerSetting maxMineTime = new IntegerSetting(5000, 0, 10000);
    @Setting(name="AotvSlot")
    private IntegerSetting aotvSlot = new IntegerSetting(0, 0, 8);
    @Setting(name="WarpLookTime", description="Set higher if low mana or bad ping")
    private IntegerSetting warpLookTime = new IntegerSetting(500, 0, 2500);
    @Setting(name="WarpTime", description="Set higher if low mana or bad ping")
    private IntegerSetting warpTime = new IntegerSetting(250, 0, 1000);
    @Setting(name="MaxPlayerRange", description="Range the bot will warp out at")
    private IntegerSetting maxPlayerRange = new IntegerSetting(5, 0, 10);
    @Setting(name="Ungrab", description="Automatically tabs out")
    private BooleanSetting ungrab = new BooleanSetting(true);
    private static Minecraft mc = Minecraft.func_71410_x();
    private static Location currentLocation = null;
    private static List<PathPoint> path = null;
    private Thread thread;
    private List<BetterBlockPos> blocksNear = new ArrayList<BetterBlockPos>();
    private List<BlockPos> blacklist = new ArrayList<BlockPos>();
    private int delayMs = 500;
    private BlockPos curBlockPos;
    private Block curBlock;
    private TimeHelper mineTimer;
    private Vec3 startRot;
    private Vec3 endRot;
    private static TimeHelper warpTimer = new TimeHelper();
    private static TimeHelper recoverTimer = new TimeHelper();
    private static TimeHelper boostTimer = new TimeHelper();
    private static BlockPos oldPos = null;
    private static int oldDrillSlot = -1;
    private static String fsMsg = "";
    private MineState mineState = MineState.CHOOSE;
    private WarpState warpState = WarpState.SETUP;
    private RefuelState refuelState = RefuelState.CLICK_MERCHANT;

    @Enable
    public void onEnable() {
        if (this.miningSpot.getCurrent().equals("None") || CF4M.INSTANCE.moduleManager.isEnabled("CommMacro")) {
            this.mineState = MineState.CHOOSE;
        } else if (this.aotvSlot.getCurrent() > 0 && this.pickSlot.getCurrent() > 0) {
            this.mineState = MineState.WARP_FORGE;
            this.refuelState = RefuelState.CLICK_MERCHANT;
            currentLocation = null;
            warpTimer.reset();
            recoverTimer.reset();
            boostTimer.reset();
        } else {
            ChatUtils.send("Configure your fucking Slots if you want FailSafes retard", new String[0]);
            CF4M.INSTANCE.moduleManager.toggle(this);
        }
        this.warpState = WarpState.SETUP;
        this.blocksNear.clear();
        this.blacklist.clear();
        this.mineTimer = new TimeHelper();
        this.curBlockPos = null;
        this.curBlock = null;
        this.startRot = null;
        this.endRot = null;
        KeyBinding.func_74510_a((int)AutoMithril.mc.field_71474_y.field_74311_E.func_151463_i(), (boolean)this.sneak.isEnabled());
        if (this.ungrab.isEnabled()) {
            ChadUtils.ungrabMouse();
        }
    }

    @Disable
    public void onDisable() {
        KeyBinding.func_74510_a((int)AutoMithril.mc.field_71474_y.field_74312_F.func_151463_i(), (boolean)false);
        KeyBinding.func_74510_a((int)AutoMithril.mc.field_71474_y.field_74311_E.func_151463_i(), (boolean)false);
        AutoMithril.mc.field_71442_b.func_78767_c();
        if (this.ungrab.isEnabled()) {
            ChadUtils.regrabMouse();
        }
    }

    @Event
    public void onTick(ClientTickEvent event) {
        if (this.mineState == MineState.REFUEL && AutoMithril.mc.field_71462_r instanceof GuiChest) {
            if (currentLocation != null && AutoMithril.currentLocation.name.equals("REFUEL") && "Drill Anvil".equals(InventoryUtils.getInventoryName())) {
                switch (this.refuelState) {
                    case CLICK_DRILL_IN: {
                        oldDrillSlot = InventoryUtils.getSlotForItem("Drill", Items.field_179562_cC);
                        AutoMithril.mc.field_71442_b.func_78753_a(AutoMithril.mc.field_71439_g.field_71070_bA.field_75152_c, oldDrillSlot, 0, 1, (EntityPlayer)AutoMithril.mc.field_71439_g);
                        this.refuelState = RefuelState.CLICK_FUEL_IN;
                        break;
                    }
                    case CLICK_FUEL_IN: {
                        ItemStack aboveDrill = InventoryUtils.getStackInOpenContainerSlot(20);
                        if (aboveDrill == null || aboveDrill.func_77952_i() != 5) break;
                        int fuelSlot = InventoryUtils.getSlotForItem("Volta", Items.field_151144_bL);
                        if (fuelSlot == -1) {
                            fuelSlot = InventoryUtils.getSlotForItem("Oil Barrel", Items.field_151144_bL);
                        }
                        if (fuelSlot == -1) {
                            ChatUtils.send("Bozo you don't have any fuel", new String[0]);
                            CF4M.INSTANCE.moduleManager.toggle(this);
                            AutoMithril.mc.field_71439_g.func_71053_j();
                            break;
                        }
                        AutoMithril.mc.field_71442_b.func_78753_a(AutoMithril.mc.field_71439_g.field_71070_bA.field_75152_c, fuelSlot, 0, 1, (EntityPlayer)AutoMithril.mc.field_71439_g);
                        this.refuelState = RefuelState.REFUEL_DRILL;
                        break;
                    }
                    case REFUEL_DRILL: {
                        ItemStack hopper = InventoryUtils.getStackInOpenContainerSlot(22);
                        if (hopper == null || !hopper.func_77948_v()) break;
                        AutoMithril.mc.field_71442_b.func_78753_a(AutoMithril.mc.field_71439_g.field_71070_bA.field_75152_c, 22, 0, 0, (EntityPlayer)AutoMithril.mc.field_71439_g);
                        this.refuelState = RefuelState.CLICK_DRILL_OUT;
                        break;
                    }
                    case CLICK_DRILL_OUT: {
                        ItemStack oldDrill = InventoryUtils.getStackInOpenContainerSlot(29);
                        if (oldDrill != null) break;
                        AutoMithril.mc.field_71442_b.func_78753_a(AutoMithril.mc.field_71439_g.field_71070_bA.field_75152_c, 13, 0, 1, (EntityPlayer)AutoMithril.mc.field_71439_g);
                        this.refuelState = RefuelState.DONE_REFUELING;
                        break;
                    }
                    case DONE_REFUELING: {
                        AutoMithril.mc.field_71439_g.func_71053_j();
                        this.mineState = MineState.WARP_FORGE;
                        this.refuelState = RefuelState.CLICK_MERCHANT;
                        recoverTimer.reset();
                    }
                }
            }
            return;
        }
        if (this.thread == null || !this.thread.isAlive()) {
            this.thread = new Thread(this);
            this.thread.setDaemon(false);
            this.thread.setPriority(1);
            this.thread.start();
        }
        if (this.mineState == MineState.CHOOSE || this.mineState == MineState.LOOK || this.mineState == MineState.MINE) {
            if (!CF4M.INSTANCE.moduleManager.isEnabled("CommMacro")) {
                boolean warpOut = false;
                int rongo = this.maxPlayerRange.getCurrent();
                if (currentLocation != null && !AutoMithril.currentLocation.name.equals("REFUEL")) {
                    if (boostTimer.hasReached(125000L)) {
                        AutoMithril.mc.field_71442_b.func_78769_a((EntityPlayer)AutoMithril.mc.field_71439_g, (World)AutoMithril.mc.field_71441_e, AutoMithril.mc.field_71439_g.func_70694_bm());
                        boostTimer.reset();
                    }
                    if (rongo != 0) {
                        String warpName = "";
                        for (Entity e : AutoMithril.mc.field_71441_e.func_175674_a((Entity)AutoMithril.mc.field_71439_g, AutoMithril.mc.field_71439_g.func_174813_aQ().func_72314_b((double)rongo, (double)(rongo >> 1), (double)rongo), a -> a instanceof EntityOtherPlayerMP || a instanceof EntityArmorStand)) {
                            String formatted;
                            if (e instanceof EntityArmorStand) {
                                ItemStack bushSlot = ((EntityArmorStand)e).func_71124_b(4);
                                if (bushSlot == null || Item.func_150898_a((Block)Blocks.field_150330_I) != bushSlot.func_77973_b()) continue;
                                warpOut = true;
                                warpName = "Dead Bush";
                                break;
                            }
                            if (!(e instanceof EntityOtherPlayerMP) || e.func_70005_c_().equals("Goblin ") || e.func_70005_c_().contains("Treasuer Hunter") || e.func_70005_c_().contains("Crystal Sentry") || StringUtils.func_76338_a((String)(formatted = e.func_145748_c_().func_150254_d())).equals(formatted) || formatted.startsWith("\u00a7r") && !formatted.startsWith("\u00a7r\u00a7")) continue;
                            warpOut = true;
                            warpName = e.func_70005_c_();
                        }
                        if (warpOut) {
                            if (!this.miningSpot.getCurrent().equals("None")) {
                                ChatUtils.send("Switching lobbies cause a nice person is near you: " + warpName, new String[0]);
                                AutoMithril.mc.field_71439_g.func_71165_d("/warp home");
                                this.mineState = MineState.WARP_FORGE;
                                KeyBinding.func_74506_a();
                                recoverTimer.reset();
                            } else {
                                ChatUtils.send("A person was near you, but you didn't configure a Mining Spot, sending to your island: " + warpName, new String[0]);
                                AutoMithril.mc.field_71439_g.func_71165_d("/warp home");
                                KeyBinding.func_74506_a();
                                if (CF4M.INSTANCE.moduleManager.isEnabled(this)) {
                                    CF4M.INSTANCE.moduleManager.toggle(this);
                                }
                            }
                        }
                    }
                }
            }
            this.blocksNear.removeIf(v -> {
                Vec3 randPoint = v.points.get(RandomUtil.randBetween(0, v.points.size() - 1));
                MovingObjectPosition mop = AutoMithril.mc.field_71441_e.func_72933_a(AutoMithril.mc.field_71439_g.func_174824_e(1.0f), randPoint);
                if (mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
                    return !mop.func_178782_a().equals((Object)v.blockPos) || !(randPoint.func_72438_d(AutoMithril.mc.field_71439_g.func_174824_e(1.0f)) < (double)AutoMithril.mc.field_71442_b.func_78757_d());
                }
                return true;
            });
            if (this.blocksNear.stream().noneMatch(v -> v.blockPos.equals((Object)this.curBlockPos))) {
                this.mineState = MineState.CHOOSE;
            }
        }
        if (recoverTimer.hasReached(5000L)) {
            SkyblockUtils.Location curLoc = SkyblockUtils.getLocation();
            block7 : switch (curLoc) {
                case SKYBLOCK: {
                    switch (this.mineState) {
                        case WARP_FORGE: {
                            Optional<Location> loc = CommMacro.miningJson.locations.stream().filter(v -> v.name.equals(this.miningSpot.getCurrent())).findFirst();
                            loc.ifPresent(location -> {
                                currentLocation = location;
                            });
                            if (currentLocation != null) {
                                ChatUtils.send("Navigating to: " + AutoMithril.currentLocation.name, new String[0]);
                                AutoMithril.mc.field_71439_g.func_71165_d("/warp forge");
                                path = null;
                                this.mineState = MineState.NAVIGATING;
                                break block7;
                            }
                            ChatUtils.send("Couldn't determine location, very weird", new String[0]);
                            CF4M.INSTANCE.moduleManager.toggle(this);
                            break block7;
                        }
                        case NAVIGATING: {
                            if (!AutoMithril.mc.field_71439_g.func_180425_c().equals((Object)new BlockPos(1, 149, -68)) || path != null) break;
                            path = new ArrayList<PathPoint>(AutoMithril.currentLocation.path);
                            oldPos = null;
                            KeyBinding.func_74510_a((int)AutoMithril.mc.field_71474_y.field_74311_E.func_151463_i(), (boolean)true);
                            warpTimer.reset();
                            this.warpState = WarpState.SETUP;
                            break block7;
                        }
                        case CHOOSE: {
                            IBlockState ibs;
                            BlockPosWithVec closest;
                            if (boostTimer.hasReached(125000L)) {
                                AutoMithril.mc.field_71442_b.func_78769_a((EntityPlayer)AutoMithril.mc.field_71439_g, (World)AutoMithril.mc.field_71441_e, AutoMithril.mc.field_71439_g.func_70694_bm());
                                boostTimer.reset();
                            }
                            if ((closest = this.getClosestBlock(null)) != null) {
                                this.curBlockPos = closest.getBlockPos();
                                ibs = AutoMithril.mc.field_71441_e.func_180495_p(this.curBlockPos);
                                if (ibs != null) {
                                    this.curBlock = ibs.func_177230_c();
                                }
                                this.startRot = closest.getVec3();
                                this.endRot = null;
                                RotationUtils.setup(RotationUtils.getRotation(closest.getVec3()), (long)this.lookTime.getCurrent());
                                this.mineState = MineState.LOOK;
                                break block7;
                            }
                            KeyBinding.func_74510_a((int)AutoMithril.mc.field_71474_y.field_74312_F.func_151463_i(), (boolean)false);
                            break block7;
                        }
                        case MINE: {
                            if (this.mineTimer.hasReached(this.maxMineTime.getCurrent().intValue())) {
                                this.blocksNear.removeIf(v -> v.blockPos.equals((Object)this.curBlockPos));
                                this.mineState = MineState.CHOOSE;
                                break block7;
                            }
                            IBlockState ibs = AutoMithril.mc.field_71441_e.func_180495_p(this.curBlockPos);
                            if (ibs == null) break;
                            if (this.curBlock != null && ibs.func_177230_c() != this.curBlock || ibs.func_177230_c() == Blocks.field_150357_h || ibs.func_177230_c() == Blocks.field_150350_a) {
                                this.blocksNear.removeIf(v -> v.blockPos.equals((Object)this.curBlockPos));
                                this.mineState = MineState.CHOOSE;
                                this.curBlockPos = null;
                                this.curBlock = null;
                                this.startRot = null;
                                this.endRot = null;
                                break block7;
                            }
                            if (AutoMithril.mc.field_71474_y.field_74312_F.func_151470_d() || AutoMithril.mc.field_71462_r != null) break;
                            AutoMithril.mc.field_71415_G = true;
                            KeyBinding.func_74507_a((int)AutoMithril.mc.field_71474_y.field_74312_F.func_151463_i());
                            KeyBinding.func_74510_a((int)AutoMithril.mc.field_71474_y.field_74312_F.func_151463_i(), (boolean)true);
                        }
                    }
                    break;
                }
                case ISLAND: {
                    ChatUtils.send("Detected player in Island, re-warping", new String[0]);
                    fsMsg = "/warp forge";
                    break;
                }
                case HUB: {
                    ChatUtils.send("Detected player in Hub, re-warping", new String[0]);
                    fsMsg = "/warp forge";
                    break;
                }
                case LIFT: {
                    ChatUtils.send("Detected player at Lift, re-warping", new String[0]);
                    fsMsg = "/warp forge";
                    break;
                }
                case LOBBY: {
                    ChatUtils.send("Detected player in Lobby, re-warping", new String[0]);
                    fsMsg = "/play skyblock";
                    break;
                }
                case LIMBO: {
                    ChatUtils.send("Detected player in Limbo, re-warping", new String[0]);
                    fsMsg = "/l";
                }
            }
            if (curLoc != SkyblockUtils.Location.SKYBLOCK && !this.miningSpot.getCurrent().equals("None") && !CF4M.INSTANCE.moduleManager.isEnabled("CommMacro")) {
                this.mineState = MineState.WARP_FORGE;
                AutoMithril.mc.field_71439_g.func_71165_d(fsMsg);
                KeyBinding.func_74506_a();
                recoverTimer.reset();
            }
        }
    }

    @Event
    public void onRender(Render3DEvent event) {
        for (BetterBlockPos betterBlockPos : new ArrayList<BetterBlockPos>(this.blocksNear)) {
            if (betterBlockPos.blockPos.equals((Object)this.curBlockPos)) continue;
            Render3DUtils.renderEspBox(betterBlockPos.blockPos, event.partialTicks, -16711681);
        }
        if (this.blocksNear.stream().anyMatch(v -> v.blockPos.equals((Object)this.curBlockPos))) {
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
        if (AutoMithril.mc.field_71462_r != null) {
            return;
        }
        if (this.mineState == MineState.NAVIGATING && path != null) {
            switch (this.warpState) {
                case SETUP: {
                    if (path.size() > 0) {
                        if (warpTimer.hasReached(this.warpTime.getCurrent().intValue()) && !AutoMithril.mc.field_71439_g.func_180425_c().equals((Object)oldPos)) {
                            PathPoint a = path.get(0);
                            path.remove(0);
                            RotationUtils.setup(RotationUtils.getRotation(new Vec3(a.x, a.y, a.z)), (long)this.warpLookTime.getCurrent());
                            oldPos = AutoMithril.mc.field_71439_g.func_180425_c();
                            this.warpState = WarpState.LOOK;
                            break;
                        }
                        if (!warpTimer.hasReached(2500L)) break;
                        ChatUtils.send("Got stuck while tp'ing, re-navigating", new String[0]);
                        AutoMithril.mc.field_71439_g.func_71165_d("/l");
                        recoverTimer.reset();
                        warpTimer.reset();
                        break;
                    }
                    if (!this.sneak.isEnabled()) {
                        KeyBinding.func_74510_a((int)AutoMithril.mc.field_71474_y.field_74311_E.func_151463_i(), (boolean)false);
                    }
                    if (this.pickSlot.getCurrent() > 0 && this.pickSlot.getCurrent() <= 8) {
                        AutoMithril.mc.field_71439_g.field_71071_by.field_70461_c = this.pickSlot.getCurrent() - 1;
                    }
                    if (AutoMithril.currentLocation.name.equals("REFUEL")) {
                        this.mineState = MineState.REFUEL;
                        break;
                    }
                    this.mineState = MineState.CHOOSE;
                    break;
                }
                case LOOK: {
                    if (System.currentTimeMillis() <= RotationUtils.endTime) {
                        RotationUtils.update();
                        break;
                    }
                    RotationUtils.update();
                    warpTimer.reset();
                    this.warpState = WarpState.WARP;
                    break;
                }
                case WARP: {
                    if (!warpTimer.hasReached(this.warpTime.getCurrent().intValue())) break;
                    SkyblockUtils.silentUse(0, this.aotvSlot.getCurrent());
                    warpTimer.reset();
                    this.warpState = WarpState.SETUP;
                }
            }
        }
        switch (this.mineState) {
            case LOOK: {
                Optional<BetterBlockPos> optional;
                if (System.currentTimeMillis() <= RotationUtils.endTime) {
                    RotationUtils.update();
                    break;
                }
                if (!RotationUtils.done) {
                    RotationUtils.update();
                }
                this.mineTimer.reset();
                BlockPosWithVec next = this.getClosestBlock(this.curBlockPos);
                if (next != null && (optional = this.blocksNear.stream().filter(v -> v.blockPos.equals((Object)this.curBlockPos)).findFirst()).isPresent()) {
                    List<Vec3> points = optional.get().points;
                    points.stream().min(Comparator.comparing(v -> Float.valueOf(RotationUtils.getNeededChange(RotationUtils.getRotation(next.getVec3()), RotationUtils.getRotation(v)).getValue()))).ifPresent(nextPointOnSameBlock -> {
                        this.curBlock = AutoMithril.mc.field_71441_e.func_180495_p(this.curBlockPos).func_177230_c();
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

    @Event
    public void onChatReceived(ClientChatReceivedEvent event) {
        if (!CF4M.INSTANCE.moduleManager.isEnabled("CommMacro")) {
            String msg = event.message.func_150260_c();
            if (this.mineState == MineState.CHOOSE || this.mineState == MineState.LOOK || this.mineState == MineState.MINE) {
                if (msg.startsWith("Mining Speed Boost is now available!")) {
                    AutoMithril.mc.field_71442_b.func_78769_a((EntityPlayer)AutoMithril.mc.field_71439_g, (World)AutoMithril.mc.field_71441_e, AutoMithril.mc.field_71439_g.func_70694_bm());
                    boostTimer.reset();
                } else if (msg.startsWith("Your") && msg.contains("is empty! Refuel it by talking to a Drill Mechanic!")) {
                    if (!this.miningSpot.getCurrent().equals("None")) {
                        currentLocation = CommMacro.miningJson.locations.get(CommMacro.miningJson.locations.size() - 1);
                        this.mineState = MineState.WARP_FORGE;
                    } else {
                        ChatUtils.send("Can't use Drill Refuel without a Mining Spot", new String[0]);
                        CF4M.INSTANCE.moduleManager.toggle(this);
                    }
                }
            }
            if (msg.contains("You can't fast travel while in combat!")) {
                AutoMithril.mc.field_71439_g.func_71165_d("/l");
                recoverTimer.reset();
            }
        }
    }

    private List<String> getMiningSpotNames() {
        ArrayList<String> ret = new ArrayList<String>();
        ret.add("None");
        ret.addAll(CommMacro.miningJson.locations.subList(0, CommMacro.miningJson.locations.size() - 5).stream().map(v -> v.name).collect(Collectors.toList()));
        return ret;
    }

    private void addBlockIfHittable(BlockPos xyz) {
        List<Vec3> pointsOnBlock = RotationUtils.getPointsOnBlock(xyz);
        for (Vec3 point : pointsOnBlock) {
            MovingObjectPosition mop = AutoMithril.mc.field_71441_e.func_72933_a(AutoMithril.mc.field_71439_g.func_174824_e(1.0f), point);
            if (mop == null || mop.field_72313_a != MovingObjectPosition.MovingObjectType.BLOCK || !mop.func_178782_a().equals((Object)xyz) || !(point.func_72438_d(AutoMithril.mc.field_71439_g.func_174824_e(1.0f)) < (double)AutoMithril.mc.field_71442_b.func_78757_d())) continue;
            if (this.blocksNear.stream().noneMatch(v -> v.blockPos.equals((Object)xyz))) {
                this.blocksNear.add(new BetterBlockPos(xyz, new ArrayList<Vec3>(Collections.singletonList(point))));
                continue;
            }
            this.blocksNear.stream().filter(v -> v.blockPos.equals((Object)xyz)).findFirst().ifPresent(v -> v.points.add(point));
        }
    }

    private BlockPosWithVec getClosestBlock(BlockPos excluding) {
        BlockPos closest = null;
        Rotation closestRot = null;
        Vec3 closestPoint = null;
        ArrayList<BetterBlockPos> asd = new ArrayList<BetterBlockPos>(this.blocksNear);
        asd.removeIf(v -> v.blockPos.equals((Object)excluding));
        ArrayList tits = new ArrayList();
        if (CommMacro.hugeTits) {
            asd.forEach(bbp -> {
                IBlockState bs = AutoMithril.mc.field_71441_e.func_180495_p(bbp.blockPos);
                Block b = bs.func_177230_c();
                if (b == Blocks.field_150348_b && bs.func_177229_b((IProperty)BlockStone.field_176247_a) == BlockStone.EnumType.DIORITE_SMOOTH) {
                    tits.add(bbp);
                }
            });
            if (!tits.isEmpty()) {
                asd = tits;
            }
        }
        ArrayList miths = new ArrayList();
        if (CommMacro.mithril) {
            asd.forEach(bbp -> {
                IBlockState bs = AutoMithril.mc.field_71441_e.func_180495_p(bbp.blockPos);
                Block b = bs.func_177230_c();
                if (b == Blocks.field_150325_L && bs.func_177229_b((IProperty)BlockColored.field_176581_a) == EnumDyeColor.GRAY) {
                    miths.add(bbp);
                } else if (b == Blocks.field_150406_ce && bs.func_177229_b((IProperty)BlockColored.field_176581_a) == EnumDyeColor.CYAN) {
                    miths.add(bbp);
                }
            });
            if (!miths.isEmpty()) {
                asd = miths;
            }
        }
        for (BetterBlockPos bbp2 : asd) {
            for (Vec3 point : bbp2.points) {
                Rotation endRot = RotationUtils.getRotation(point);
                Rotation needed = RotationUtils.getNeededChange(endRot);
                if (closestRot != null && needed.getValue() >= closestRot.getValue()) continue;
                closest = bbp2.blockPos;
                closestRot = needed;
                closestPoint = point;
            }
        }
        if (closest != null) {
            return new BlockPosWithVec(closest, closestPoint);
        }
        return null;
    }

    @Override
    public void run() {
        while (!this.thread.isInterrupted() && AutoMithril.mc.field_71439_g != null && AutoMithril.mc.field_71441_e != null) {
            if (CF4M.INSTANCE.moduleManager.isEnabled(this)) {
                int radius = 6;
                int px = MathHelper.func_76128_c((double)AutoMithril.mc.field_71439_g.field_70165_t);
                int py = MathHelper.func_76128_c((double)(AutoMithril.mc.field_71439_g.field_70163_u + 1.0));
                int pz = MathHelper.func_76128_c((double)AutoMithril.mc.field_71439_g.field_70161_v);
                Vec3 eyes = AutoMithril.mc.field_71439_g.func_174824_e(1.0f);
                for (int x = px - radius; x < px + radius + 1; ++x) {
                    for (int y = py - radius; y < py + radius + 1; ++y) {
                        for (int z = pz - radius; z < pz + radius + 1; ++z) {
                            BlockPos xyz = new BlockPos(x, y, z);
                            IBlockState bs = AutoMithril.mc.field_71441_e.func_180495_p(xyz);
                            if (!this.blocksNear.stream().noneMatch(v -> v.blockPos.equals((Object)xyz)) || this.blacklist.contains((Object)xyz)) continue;
                            Block block = bs.func_177230_c();
                            if (!(Math.sqrt(xyz.func_177957_d(eyes.field_72450_a, eyes.field_72448_b, eyes.field_72449_c)) <= 6.0)) continue;
                            if (block == Blocks.field_150325_L) {
                                if (bs.func_177229_b((IProperty)BlockColored.field_176581_a) == EnumDyeColor.LIGHT_BLUE) {
                                    if (!this.blueWool.isEnabled()) continue;
                                    this.addBlockIfHittable(xyz);
                                    continue;
                                }
                                if (bs.func_177229_b((IProperty)BlockColored.field_176581_a) != EnumDyeColor.GRAY || !this.grayShit.isEnabled()) continue;
                                this.addBlockIfHittable(xyz);
                                continue;
                            }
                            if (block == Blocks.field_180397_cI) {
                                if (!this.prismarine.isEnabled()) continue;
                                this.addBlockIfHittable(xyz);
                                continue;
                            }
                            if (block == Blocks.field_150348_b) {
                                if (bs.func_177229_b((IProperty)BlockStone.field_176247_a) != BlockStone.EnumType.DIORITE_SMOOTH || !this.titanium.isEnabled()) continue;
                                this.addBlockIfHittable(xyz);
                                continue;
                            }
                            if (block != Blocks.field_150406_ce || bs.func_177229_b((IProperty)BlockColored.field_176581_a) != EnumDyeColor.CYAN || !this.grayShit.isEnabled()) continue;
                            this.addBlockIfHittable(xyz);
                        }
                    }
                }
                for (BetterBlockPos bbp : new ArrayList<BetterBlockPos>(this.blocksNear)) {
                    IBlockState state = AutoMithril.mc.field_71441_e.func_180495_p(bbp.blockPos);
                    Block block = null;
                    if (state != null) {
                        block = state.func_177230_c();
                    }
                    if (!(Math.sqrt(bbp.blockPos.func_177957_d(eyes.field_72450_a, eyes.field_72448_b, eyes.field_72449_c)) > 5.0) && block != Blocks.field_150357_h && block != Blocks.field_150350_a) continue;
                    this.blocksNear.remove(bbp);
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

    public static class BlockPosWithVec {
        private BlockPos blockPos;
        private Vec3 vec3;

        public BlockPosWithVec(BlockPos blockPos, Vec3 vec3) {
            this.blockPos = blockPos;
            this.vec3 = vec3;
        }

        public BlockPos getBlockPos() {
            return this.blockPos;
        }

        public Vec3 getVec3() {
            return this.vec3;
        }
    }

    private static class BetterBlockPos {
        BlockPos blockPos;
        List<Vec3> points;

        public BetterBlockPos(BlockPos blockPos, List<Vec3> points) {
            this.blockPos = blockPos;
            this.points = points;
        }
    }

    private static enum RefuelState {
        CLICK_MERCHANT,
        CLICK_DRILL_IN,
        CLICK_FUEL_IN,
        REFUEL_DRILL,
        CLICK_DRILL_OUT,
        DONE_REFUELING;

    }

    private static enum WarpState {
        SETUP,
        LOOK,
        WARP;

    }

    static enum MineState {
        WARP_FORGE,
        NAVIGATING,
        REFUEL,
        CHOOSE,
        LOOK,
        MINE;

    }
}

