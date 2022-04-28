/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityOtherPlayerMP
 *  net.minecraft.client.gui.inventory.GuiChest
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityArmorStand
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.BlockPos
 *  net.minecraft.util.StringUtils
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  org.apache.commons.lang3.StringUtils
 */
package xyz.apfelmus.cheeto.client.modules.player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
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
import xyz.apfelmus.cheeto.client.events.WorldUnloadEvent;
import xyz.apfelmus.cheeto.client.settings.BooleanSetting;
import xyz.apfelmus.cheeto.client.settings.IntegerSetting;
import xyz.apfelmus.cheeto.client.utils.client.ChadUtils;
import xyz.apfelmus.cheeto.client.utils.client.ChatUtils;
import xyz.apfelmus.cheeto.client.utils.client.JsonUtils;
import xyz.apfelmus.cheeto.client.utils.client.RotationUtils;
import xyz.apfelmus.cheeto.client.utils.math.RandomUtil;
import xyz.apfelmus.cheeto.client.utils.math.TimeHelper;
import xyz.apfelmus.cheeto.client.utils.mining.Location;
import xyz.apfelmus.cheeto.client.utils.mining.MiningJson;
import xyz.apfelmus.cheeto.client.utils.mining.PathPoint;
import xyz.apfelmus.cheeto.client.utils.skyblock.InventoryUtils;
import xyz.apfelmus.cheeto.client.utils.skyblock.SkyblockUtils;

@Module(name="CommMacro", category=Category.PLAYER)
public class CommMacro {
    @Setting(name="LookTime", description="Set higher if low mana or bad ping")
    private IntegerSetting lookTime = new IntegerSetting(500, 0, 2500);
    @Setting(name="WarpTime", description="Set higher if low mana or bad ping")
    private IntegerSetting warpTime = new IntegerSetting(250, 0, 1000);
    @Setting(name="MaxPlayerRange")
    private IntegerSetting maxPlayerRange = new IntegerSetting(5, 0, 10);
    @Setting(name="PickSlot")
    private IntegerSetting pickSlot = new IntegerSetting(0, 0, 8);
    @Setting(name="AotvSlot")
    private IntegerSetting aotvSlot = new IntegerSetting(0, 0, 8);
    @Setting(name="PigeonSlot")
    private IntegerSetting pigeonSlot = new IntegerSetting(0, 0, 8);
    @Setting(name="Ungrab", description="Automatically tabs out")
    private BooleanSetting ungrab = new BooleanSetting(true);
    private static Minecraft mc = Minecraft.func_71410_x();
    public static MiningJson miningJson = JsonUtils.getMiningJson();
    private static Quest currentQuest = null;
    private static Location currentLocation = null;
    private static List<PathPoint> path = null;
    private static TimeHelper pigeonTimer = new TimeHelper();
    private static TimeHelper warpTimer = new TimeHelper();
    private static TimeHelper recoverTimer = new TimeHelper();
    private static TimeHelper boostTimer = new TimeHelper();
    private static BlockPos oldPos = null;
    public static boolean hugeTits = false;
    public static boolean mithril = false;
    private static int oldDrillSlot = -1;
    private static CommState commState = CommState.CLICK_PIGEON;
    private static WarpState warpState = WarpState.SETUP;
    private static RefuelState refuelState = RefuelState.CLICK_MERCHANT;

    @Enable
    public void onEnable() {
        pigeonTimer.reset();
        warpTimer.reset();
        recoverTimer.reset();
        boostTimer.reset();
        commState = CommState.CLICK_PIGEON;
        warpState = WarpState.SETUP;
        refuelState = RefuelState.CLICK_MERCHANT;
        if (miningJson == null) {
            ChatUtils.send("An error occured while getting Mining Locations, reloading...", new String[0]);
            miningJson = JsonUtils.getMiningJson();
            CF4M.INSTANCE.moduleManager.toggle(this);
            return;
        }
        if (this.pickSlot.getCurrent() == 0 || this.aotvSlot.getCurrent() == 0 || this.pigeonSlot.getCurrent() == 0) {
            ChatUtils.send("Configure your fucking Item Slots retard", new String[0]);
            CF4M.INSTANCE.moduleManager.toggle(this);
        }
        if (this.ungrab.isEnabled()) {
            ChadUtils.ungrabMouse();
        }
    }

    @Disable
    public void onDisable() {
        hugeTits = false;
        mithril = false;
        KeyBinding.func_74506_a();
        if (CF4M.INSTANCE.moduleManager.isEnabled("AutoMithril")) {
            CF4M.INSTANCE.moduleManager.toggle("AutoMithril");
        }
        if (CF4M.INSTANCE.moduleManager.isEnabled("IceGoblinSlayer")) {
            CF4M.INSTANCE.moduleManager.toggle("IceGoblinSlayer");
        }
        if (this.ungrab.isEnabled()) {
            ChadUtils.regrabMouse();
        }
    }

    @Event
    public void onTick(ClientTickEvent event) {
        block47: {
            SkyblockUtils.Location curLoc;
            block48: {
                if (CommMacro.mc.field_71462_r instanceof GuiChest && currentLocation != null && CommMacro.currentLocation.name.equals("REFUEL") && "Drill Anvil".equals(InventoryUtils.getInventoryName())) {
                    switch (refuelState) {
                        case CLICK_DRILL_IN: {
                            oldDrillSlot = InventoryUtils.getSlotForItem("Drill", Items.field_179562_cC);
                            CommMacro.mc.field_71442_b.func_78753_a(CommMacro.mc.field_71439_g.field_71070_bA.field_75152_c, oldDrillSlot, 0, 1, (EntityPlayer)CommMacro.mc.field_71439_g);
                            refuelState = RefuelState.CLICK_FUEL_IN;
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
                                CommMacro.mc.field_71439_g.func_71053_j();
                                break;
                            }
                            CommMacro.mc.field_71442_b.func_78753_a(CommMacro.mc.field_71439_g.field_71070_bA.field_75152_c, fuelSlot, 0, 1, (EntityPlayer)CommMacro.mc.field_71439_g);
                            refuelState = RefuelState.REFUEL_DRILL;
                            break;
                        }
                        case REFUEL_DRILL: {
                            ItemStack hopper = InventoryUtils.getStackInOpenContainerSlot(22);
                            if (hopper == null || !hopper.func_77948_v()) break;
                            CommMacro.mc.field_71442_b.func_78753_a(CommMacro.mc.field_71439_g.field_71070_bA.field_75152_c, 22, 0, 0, (EntityPlayer)CommMacro.mc.field_71439_g);
                            refuelState = RefuelState.CLICK_DRILL_OUT;
                            break;
                        }
                        case CLICK_DRILL_OUT: {
                            ItemStack oldDrill = InventoryUtils.getStackInOpenContainerSlot(29);
                            if (oldDrill != null) break;
                            CommMacro.mc.field_71442_b.func_78753_a(CommMacro.mc.field_71439_g.field_71070_bA.field_75152_c, 13, 0, 1, (EntityPlayer)CommMacro.mc.field_71439_g);
                            refuelState = RefuelState.DONE_REFUELING;
                            break;
                        }
                        case DONE_REFUELING: {
                            CommMacro.mc.field_71439_g.func_71053_j();
                            commState = CommState.CLICK_PIGEON;
                            refuelState = RefuelState.CLICK_MERCHANT;
                            recoverTimer.reset();
                        }
                    }
                }
                if (!recoverTimer.hasReached(5000L)) break block47;
                curLoc = SkyblockUtils.getLocation();
                block7 : switch (curLoc) {
                    case SKYBLOCK: {
                        switch (commState) {
                            case CLICK_PIGEON: {
                                if (pigeonTimer.hasReached(5000L)) {
                                    SkyblockUtils.silentUse(0, this.pigeonSlot.getCurrent());
                                    commState = CommState.IN_PIGEON;
                                    pigeonTimer.reset();
                                    break;
                                }
                                break block48;
                            }
                            case IN_PIGEON: {
                                if (pigeonTimer.hasReached(1000L) && CommMacro.mc.field_71462_r instanceof GuiChest) {
                                    String lore;
                                    int i;
                                    ItemStack is;
                                    List<String> itemLore;
                                    int complSlot = this.getCompletedSlot();
                                    if (complSlot != -1) {
                                        InventoryUtils.clickOpenContainerSlot(complSlot);
                                        pigeonTimer.reset();
                                        return;
                                    }
                                    List<Integer> questSlots = this.getQuestSlots();
                                    if (questSlots.isEmpty()) {
                                        return;
                                    }
                                    Iterator<Integer> iterator = questSlots.iterator();
                                    while (iterator.hasNext() && ((itemLore = this.getLore(is = InventoryUtils.getStackInOpenContainerSlot(i = iterator.next().intValue()))).size() <= 4 || (currentQuest = CommMacro.getQuest(lore = itemLore.get(4))) == null || (currentLocation = CommMacro.getLocation(currentQuest)) == null)) {
                                    }
                                    CommMacro.mc.field_71439_g.func_71053_j();
                                    commState = CommState.WARP_FORGE;
                                    break;
                                }
                                if (pigeonTimer.hasReached(1000L)) {
                                    commState = CommState.CLICK_PIGEON;
                                    break;
                                }
                                break block48;
                            }
                            case WARP_FORGE: {
                                if (currentLocation == null) {
                                    ChatUtils.send("Couldn't determine Commission", new String[0]);
                                    pigeonTimer.reset();
                                    commState = CommState.CLICK_PIGEON;
                                    break;
                                }
                                ChatUtils.send("Navigating to: " + CommMacro.currentLocation.name, new String[0]);
                                CommMacro.mc.field_71439_g.func_71165_d("/warp forge");
                                path = null;
                                commState = CommState.NAVIGATE;
                                break;
                            }
                            case NAVIGATE: {
                                if (CommMacro.mc.field_71439_g.func_180425_c().equals((Object)new BlockPos(1, 149, -68)) && path == null) {
                                    path = new ArrayList<PathPoint>(CommMacro.currentLocation.path);
                                    warpTimer.reset();
                                    oldPos = null;
                                    KeyBinding.func_74510_a((int)CommMacro.mc.field_71474_y.field_74311_E.func_151463_i(), (boolean)true);
                                    warpState = WarpState.SETUP;
                                    break;
                                }
                                break block48;
                            }
                            case COMMIT: {
                                if (currentQuest == Quest.GOBLIN_SLAYER || currentQuest == Quest.ICE_WALKER_SLAYER) {
                                    CF4M.INSTANCE.moduleManager.toggle("IceGoblinSlayer");
                                    commState = CommState.COMMITTING;
                                    break;
                                }
                                if (currentLocation != null && CommMacro.currentLocation.name.equals("REFUEL") && refuelState == RefuelState.CLICK_MERCHANT) {
                                    List possible = CommMacro.mc.field_71441_e.func_175674_a((Entity)CommMacro.mc.field_71439_g, CommMacro.mc.field_71439_g.func_174813_aQ().func_72314_b(5.0, 3.0, 5.0), a -> a.func_70005_c_().contains("Jotraeline Greatforge"));
                                    if (possible.isEmpty()) break block7;
                                    CommMacro.mc.field_71442_b.func_78768_b((EntityPlayer)CommMacro.mc.field_71439_g, (Entity)possible.get(0));
                                    refuelState = RefuelState.CLICK_DRILL_IN;
                                    break;
                                }
                                if (this.pickSlot.getCurrent() > 0 && this.pickSlot.getCurrent() <= 8) {
                                    CommMacro.mc.field_71439_g.field_71071_by.field_70461_c = this.pickSlot.getCurrent() - 1;
                                }
                                hugeTits = CommMacro.currentQuest.questName.contains("Titanium");
                                mithril = CommMacro.currentQuest.questName.contains("Mithril");
                                CF4M.INSTANCE.moduleManager.toggle("AutoMithril");
                                commState = CommState.COMMITTING;
                                break;
                            }
                            case COMMITTING: {
                                boolean warpOut = false;
                                int rongo = this.maxPlayerRange.getCurrent();
                                if (currentQuest != Quest.GOBLIN_SLAYER && currentQuest != Quest.ICE_WALKER_SLAYER) {
                                    if (boostTimer.hasReached(125000L)) {
                                        CommMacro.mc.field_71442_b.func_78769_a((EntityPlayer)CommMacro.mc.field_71439_g, (World)CommMacro.mc.field_71441_e, CommMacro.mc.field_71439_g.func_70694_bm());
                                        boostTimer.reset();
                                    }
                                    if (rongo == 0) break;
                                    String warpName = "";
                                    for (Entity e : CommMacro.mc.field_71441_e.func_175674_a((Entity)CommMacro.mc.field_71439_g, CommMacro.mc.field_71439_g.func_174813_aQ().func_72314_b((double)rongo, (double)(rongo >> 1), (double)rongo), a -> a instanceof EntityOtherPlayerMP || a instanceof EntityArmorStand)) {
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
                                        ChatUtils.send("Switching lobbies cause a nice person is near you: " + warpName, new String[0]);
                                        CommMacro.mc.field_71439_g.func_71165_d("/warp home");
                                        if (CF4M.INSTANCE.moduleManager.isEnabled("AutoMithril")) {
                                            CF4M.INSTANCE.moduleManager.toggle("AutoMithril");
                                        }
                                        if (CF4M.INSTANCE.moduleManager.isEnabled("IceGoblinSlayer")) {
                                            CF4M.INSTANCE.moduleManager.toggle("IceGoblinSlayer");
                                        }
                                        KeyBinding.func_74506_a();
                                        recoverTimer.reset();
                                        commState = CommState.CLICK_PIGEON;
                                    } else {
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case ISLAND: {
                        ChatUtils.send("Detected player in Island, re-warping", new String[0]);
                        CommMacro.mc.field_71439_g.func_71165_d("/warp forge");
                        break;
                    }
                    case HUB: {
                        ChatUtils.send("Detected player in Hub, re-warping", new String[0]);
                        CommMacro.mc.field_71439_g.func_71165_d("/warp forge");
                        break;
                    }
                    case LIFT: {
                        ChatUtils.send("Detected player at Lift, re-warping", new String[0]);
                        CommMacro.mc.field_71439_g.func_71165_d("/warp forge");
                        break;
                    }
                    case LOBBY: {
                        ChatUtils.send("Detected player in Lobby, re-warping", new String[0]);
                        CommMacro.mc.field_71439_g.func_71165_d("/play skyblock");
                        break;
                    }
                    case LIMBO: {
                        ChatUtils.send("Detected player in Limbo, re-warping", new String[0]);
                        CommMacro.mc.field_71439_g.func_71165_d("/l");
                    }
                }
            }
            if (curLoc != SkyblockUtils.Location.SKYBLOCK) {
                if (CF4M.INSTANCE.moduleManager.isEnabled("AutoMithril")) {
                    CF4M.INSTANCE.moduleManager.toggle("AutoMithril");
                }
                if (CF4M.INSTANCE.moduleManager.isEnabled("IceGoblinSlayer")) {
                    CF4M.INSTANCE.moduleManager.toggle("IceGoblinSlayer");
                }
                commState = CommState.CLICK_PIGEON;
                KeyBinding.func_74506_a();
                recoverTimer.reset();
            }
        }
    }

    private int getCompletedSlot() {
        for (int i = 9; i < 18; ++i) {
            List<String> itemLore;
            ItemStack is = InventoryUtils.getStackInOpenContainerSlot(i);
            if (is == null || !SkyblockUtils.stripString(is.func_82833_r()).startsWith("Commission #") || !(itemLore = this.getLore(is)).stream().anyMatch(v -> v.toLowerCase().contains("completed"))) continue;
            return i;
        }
        return -1;
    }

    private List<Integer> getQuestSlots() {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        for (int i = 9; i < 18; ++i) {
            List<String> itemLore;
            ItemStack is = InventoryUtils.getStackInOpenContainerSlot(i);
            if (is == null || !SkyblockUtils.stripString(is.func_82833_r()).startsWith("Commission #") || !(itemLore = this.getLore(is)).stream().noneMatch(v -> v.toLowerCase().contains("completed"))) continue;
            ret.add(i);
        }
        return ret;
    }

    @Event
    public void onChatReceived(ClientChatReceivedEvent event) {
        String msg = event.message.func_150260_c();
        if (commState == CommState.COMMITTING) {
            if (msg.startsWith("Mining Speed Boost is now available!")) {
                CommMacro.mc.field_71442_b.func_78769_a((EntityPlayer)CommMacro.mc.field_71439_g, (World)CommMacro.mc.field_71441_e, CommMacro.mc.field_71439_g.func_70694_bm());
                boostTimer.reset();
            } else if (msg.contains("Commission Complete! Visit the King to claim your rewards!") && !msg.contains(":")) {
                if (CF4M.INSTANCE.moduleManager.isEnabled("AutoMithril")) {
                    CF4M.INSTANCE.moduleManager.toggle("AutoMithril");
                }
                if (CF4M.INSTANCE.moduleManager.isEnabled("IceGoblinSlayer")) {
                    CF4M.INSTANCE.moduleManager.toggle("IceGoblinSlayer");
                }
                commState = CommState.CLICK_PIGEON;
            } else if (msg.startsWith("Your") && msg.contains("is empty! Refuel it by talking to a Drill Mechanic!")) {
                if (CF4M.INSTANCE.moduleManager.isEnabled("AutoMithril")) {
                    CF4M.INSTANCE.moduleManager.toggle("AutoMithril");
                }
                if (CF4M.INSTANCE.moduleManager.isEnabled("IceGoblinSlayer")) {
                    CF4M.INSTANCE.moduleManager.toggle("IceGoblinSlayer");
                }
                currentLocation = CommMacro.miningJson.locations.get(CommMacro.miningJson.locations.size() - 1);
                commState = CommState.WARP_FORGE;
            }
        }
        if (msg.contains("You can't fast travel while in combat!")) {
            ChatUtils.send("Detected travel in combat, evacuating", new String[0]);
            CommMacro.mc.field_71439_g.func_71165_d("/l");
            recoverTimer.reset();
            commState = CommState.CLICK_PIGEON;
        }
    }

    @Event
    public void onRenderTick(Render3DEvent event) {
        if (commState == CommState.NAVIGATE && path != null) {
            switch (warpState) {
                case SETUP: {
                    if (path.size() > 0) {
                        if (warpTimer.hasReached(this.warpTime.getCurrent().intValue()) && !CommMacro.mc.field_71439_g.func_180425_c().equals((Object)oldPos)) {
                            PathPoint a = path.get(0);
                            path.remove(0);
                            RotationUtils.setup(RotationUtils.getRotation(new Vec3(a.x, a.y, a.z)), (long)this.lookTime.getCurrent());
                            oldPos = CommMacro.mc.field_71439_g.func_180425_c();
                            warpState = WarpState.LOOK;
                            break;
                        }
                        if (!warpTimer.hasReached(2500L)) break;
                        ChatUtils.send("Got stuck while tp'ing, re-navigating", new String[0]);
                        CommMacro.mc.field_71439_g.func_71165_d("/l");
                        recoverTimer.reset();
                        warpTimer.reset();
                        commState = CommState.CLICK_PIGEON;
                        break;
                    }
                    KeyBinding.func_74510_a((int)CommMacro.mc.field_71474_y.field_74311_E.func_151463_i(), (boolean)false);
                    commState = CommState.COMMIT;
                    break;
                }
                case LOOK: {
                    if (System.currentTimeMillis() <= RotationUtils.endTime) {
                        RotationUtils.update();
                        break;
                    }
                    RotationUtils.update();
                    warpTimer.reset();
                    warpState = WarpState.WARP;
                    break;
                }
                case WARP: {
                    if (!warpTimer.hasReached(this.warpTime.getCurrent().intValue())) break;
                    SkyblockUtils.silentUse(0, this.aotvSlot.getCurrent());
                    warpTimer.reset();
                    warpState = WarpState.SETUP;
                }
            }
        }
    }

    @Event
    public void onWorldUnload(WorldUnloadEvent event) {
    }

    public List<String> getLore(ItemStack is) {
        NBTTagCompound display;
        ArrayList<String> lore = new ArrayList<String>();
        if (is == null || !is.func_77942_o()) {
            return lore;
        }
        NBTTagCompound nbt = is.func_77978_p();
        if (nbt.func_74775_l("display") != null && (display = nbt.func_74775_l("display")).func_150295_c("Lore", 8) != null) {
            NBTTagList nbtLore = display.func_150295_c("Lore", 8);
            for (int i = 0; i < nbtLore.func_74745_c(); ++i) {
                lore.add(SkyblockUtils.stripString(nbtLore.func_179238_g(i).toString()).replaceAll("\"", ""));
            }
        }
        return lore;
    }

    public static Quest getQuest(String lore) {
        for (Quest q : Quest.values()) {
            if (!q.questName.equalsIgnoreCase(lore)) continue;
            return q;
        }
        return null;
    }

    public static Location getLocation(Quest quest) {
        switch (quest) {
            case MITHRIL_MINER: 
            case TITANIUM_MINER: {
                List<Location> sub = CommMacro.miningJson.locations.subList(0, CommMacro.miningJson.locations.size() - 5);
                if (sub.size() <= 0) break;
                return sub.get(RandomUtil.randBetween(0, sub.size() - 1));
            }
            default: {
                ArrayList<Location> possible = new ArrayList<Location>();
                for (Location loc : CommMacro.miningJson.locations) {
                    if (quest.questName.toLowerCase().contains(loc.name.toLowerCase())) {
                        possible.add(loc);
                        continue;
                    }
                    String name = null;
                    if (loc.name.contains("COMMISSION")) {
                        name = org.apache.commons.lang3.StringUtils.substringBefore((String)loc.name, (String)" COMMISSION");
                    } else if (loc.name.contains(" (")) {
                        name = org.apache.commons.lang3.StringUtils.substringBefore((String)loc.name, (String)" (");
                    }
                    if (name == null || !quest.questName.toLowerCase().contains(name.toLowerCase())) continue;
                    possible.add(loc);
                }
                if (possible.size() <= 0) break;
                return (Location)possible.get(RandomUtil.randBetween(0, possible.size() - 1));
            }
        }
        return null;
    }

    private static enum Quest {
        MITHRIL_MINER("Mithril Miner"),
        TITANIUM_MINER("Titanium Miner"),
        UPPER_MINES_MITHRIL("Upper Mines Mithril"),
        ROYAL_MINES_MITHRIL("Royal Mines Mithril"),
        LAVA_SPRINGS_MITHRIL("Lava Springs Mithril"),
        CLIFFSIDE_VEINS_MITHRIL("Cliffside Veins Mithril"),
        RAMPARTS_QUARRY_MITHRIL("Rampart's Quarry Mithril"),
        UPPER_MINES_TITANIUM("Upper Mines Titanium"),
        ROYAL_MINES_TITANIUM("Royal Mines Titanium"),
        LAVA_SPRINGS_TITANIUM("Lava Springs Titanium"),
        CLIFFSIDE_VEINS_TITANIUM("Cliffside Veins Titanium"),
        RAMPARTS_QUARRY_TITANIUM("Rampart's Quarry Titanium"),
        GOBLIN_SLAYER("Goblin Slayer"),
        ICE_WALKER_SLAYER("Ice Walker Slayer");

        String questName;

        private Quest(String questName) {
            this.questName = questName;
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

    private static enum CommState {
        CLICK_PIGEON,
        IN_PIGEON,
        WARP_FORGE,
        NAVIGATE,
        COMMIT,
        COMMITTING;

    }
}

