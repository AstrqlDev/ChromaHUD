/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.SoundCategory
 *  net.minecraft.client.gui.GuiDisconnected
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.BlockPos
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.Session
 *  net.minecraft.util.StringUtils
 *  net.minecraft.util.Vec3i
 *  net.minecraftforge.fml.common.ObfuscationReflectionHelper
 */
package xyz.apfelmus.cheeto.client.modules.world;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Session;
import net.minecraft.util.StringUtils;
import net.minecraft.util.Vec3i;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import xyz.apfelmus.cf4m.annotation.Event;
import xyz.apfelmus.cf4m.annotation.Setting;
import xyz.apfelmus.cf4m.annotation.module.Disable;
import xyz.apfelmus.cf4m.annotation.module.Enable;
import xyz.apfelmus.cf4m.annotation.module.Module;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cheeto.client.events.ClientChatReceivedEvent;
import xyz.apfelmus.cheeto.client.events.ClientTickEvent;
import xyz.apfelmus.cheeto.client.events.Render3DEvent;
import xyz.apfelmus.cheeto.client.settings.BooleanSetting;
import xyz.apfelmus.cheeto.client.settings.IntegerSetting;
import xyz.apfelmus.cheeto.client.settings.ModeSetting;
import xyz.apfelmus.cheeto.client.settings.StringSetting;
import xyz.apfelmus.cheeto.client.utils.client.ChadUtils;
import xyz.apfelmus.cheeto.client.utils.client.ChatUtils;
import xyz.apfelmus.cheeto.client.utils.client.Rotation;
import xyz.apfelmus.cheeto.client.utils.client.RotationUtils;
import xyz.apfelmus.cheeto.client.utils.math.RandomUtil;
import xyz.apfelmus.cheeto.client.utils.math.TimeHelper;
import xyz.apfelmus.cheeto.client.utils.skyblock.InventoryUtils;
import xyz.apfelmus.cheeto.client.utils.skyblock.SkyblockUtils;

@Module(name="AutoFarm", category=Category.WORLD)
public class AutoFarm {
    @Setting(name="HoeSlot")
    private IntegerSetting hoeSlot = new IntegerSetting(0, 0, 8);
    @Setting(name="FailSafeDelay")
    private IntegerSetting failSafeDelay = new IntegerSetting(5000, 0, 10000);
    @Setting(name="CPUSaver")
    private BooleanSetting cpuSaver = new BooleanSetting(true);
    @Setting(name="AutoTab")
    private BooleanSetting autoTab = new BooleanSetting(true);
    @Setting(name="SoundAlerts")
    private BooleanSetting soundAlerts = new BooleanSetting(true);
    @Setting(name="WebhookUpdates")
    private BooleanSetting webhookUpdates = new BooleanSetting(false);
    @Setting(name="WebhookURL")
    private StringSetting webhookUrl = new StringSetting("");
    @Setting(name="Direction")
    private ModeSetting direction = new ModeSetting("NORTH", new ArrayList<String>(Arrays.asList("NORTH", "EAST", "SOUTH", "WEST")));
    private Minecraft mc = Minecraft.func_71410_x();
    private int stuckTicks = 0;
    private BlockPos oldPos;
    private BlockPos curPos;
    private boolean invFull = false;
    private List<ItemStack> oldInv;
    private int oldInvCount = 0;
    private final int GREEN = 3066993;
    private final int ORANGE = 15439360;
    private final int RED = 15158332;
    private final int BLUE = 1689596;
    private boolean banned;
    private FarmingState farmingState;
    private FarmingDirection farmingDirection;
    private AlertState alertState;
    private SameInvState sameInvState;
    private IsRebootState isRebootState;
    private TimeHelper alertTimer = new TimeHelper();
    private TimeHelper sameInvTimer = new TimeHelper();
    private TimeHelper recoverTimer = new TimeHelper();
    private Map<SoundCategory, Float> oldSounds = new HashMap<SoundCategory, Float>();
    private String recoverStr = " ";
    private boolean recoverBool = false;
    private boolean islandReboot;
    private List<String> msgs = new ArrayList<String>(Arrays.asList("hey?", "wtf?? why am i here", "What is this place!", "Hello?", "helpp where am i?"));

    @Enable
    public void onEnable() {
        this.farmingState = FarmingState.START_FARMING;
        this.farmingDirection = FarmingDirection.LEFT;
        this.alertState = AlertState.CHILLING;
        this.sameInvState = SameInvState.CHILLING;
        this.isRebootState = IsRebootState.ISLAND;
        this.islandReboot = false;
        this.banned = false;
        if (this.autoTab.isEnabled()) {
            ChadUtils.ungrabMouse();
        }
        if (this.cpuSaver.isEnabled()) {
            ChadUtils.improveCpuUsage();
        }
    }

    @Disable
    public void onDisable() {
        KeyBinding.func_74506_a();
        if (this.autoTab.isEnabled()) {
            ChadUtils.regrabMouse();
        }
        if (this.cpuSaver.isEnabled()) {
            ChadUtils.revertCpuUsage();
        }
        if (this.soundAlerts.isEnabled() && this.alertState != AlertState.CHILLING) {
            for (SoundCategory category : SoundCategory.values()) {
                this.mc.field_71474_y.func_151439_a(category, this.oldSounds.get((Object)category).floatValue());
            }
            this.alertState = AlertState.CHILLING;
        }
    }

    @Event
    public void onTick(ClientTickEvent event) {
        block0 : switch (this.farmingState) {
            case START_FARMING: {
                this.oldInv = null;
                Rotation rot = new Rotation(0.0f, 3.0f);
                switch (this.direction.getCurrent()) {
                    case "WEST": {
                        rot.setYaw(90.0f);
                        break;
                    }
                    case "NORTH": {
                        rot.setYaw(180.0f);
                        break;
                    }
                    case "EAST": {
                        rot.setYaw(-90.0f);
                    }
                }
                RotationUtils.setup(rot, 1000L);
                this.farmingState = FarmingState.SET_ANGLES;
                break;
            }
            case PRESS_KEYS: {
                if (this.hoeSlot.getCurrent() > 0 && this.hoeSlot.getCurrent() <= 8) {
                    this.mc.field_71439_g.field_71071_by.field_70461_c = this.hoeSlot.getCurrent() - 1;
                }
                this.pressKeys();
                this.farmingState = FarmingState.FARMING;
                break;
            }
            case FARMING: {
                if (!this.banned && this.mc.field_71462_r instanceof GuiDisconnected) {
                    GuiDisconnected gd = (GuiDisconnected)this.mc.field_71462_r;
                    IChatComponent message = (IChatComponent)ObfuscationReflectionHelper.getPrivateValue(GuiDisconnected.class, (Object)gd, (String[])new String[]{"message", "field_146304_f"});
                    StringBuilder reason = new StringBuilder();
                    for (IChatComponent cc : message.func_150253_a()) {
                        reason.append(cc.func_150260_c());
                    }
                    String re = reason.toString();
                    if ((re = re.replace("\r", "\\r").replace("\n", "\\n")).contains("banned")) {
                        this.banned = true;
                        this.sendWebhook("BEAMED", "You got beamed shitter!\\r\\n" + re, 15158332, true);
                    }
                }
                if (!this.recoverTimer.hasReached(2000L)) break;
                switch (SkyblockUtils.getLocation()) {
                    case NONE: {
                        KeyBinding.func_74506_a();
                        break block0;
                    }
                    case ISLAND: {
                        ItemStack heldItem;
                        if (this.recoverBool) {
                            ChatUtils.send("Recovered! Starting to farm again!", new String[0]);
                            this.sendWebhook("RECOVERED", "Recovered!\\r\\nStarting to farm again!", 3066993, false);
                            this.recoverBool = false;
                        }
                        BlockPos standing = new BlockPos(this.mc.field_71439_g.field_70165_t, this.mc.field_71439_g.field_70163_u - 1.0, this.mc.field_71439_g.field_70161_v);
                        IBlockState standBs = this.mc.field_71441_e.func_180495_p(standing);
                        int[] mods = this.getModifiers(this.direction.getCurrent());
                        BlockPos sideBlock = this.farmingDirection == FarmingDirection.LEFT ? new BlockPos(this.mc.field_71439_g.field_70165_t + (double)mods[0], this.mc.field_71439_g.field_70163_u, this.mc.field_71439_g.field_70161_v + (double)mods[1]) : new BlockPos(this.mc.field_71439_g.field_70165_t + (double)(mods[0] * -1), this.mc.field_71439_g.field_70163_u, this.mc.field_71439_g.field_70161_v + (double)(mods[1] * -1));
                        IBlockState sideBs = this.mc.field_71441_e.func_180495_p(sideBlock);
                        if (sideBs != null && sideBs.func_177230_c() != Blocks.field_150350_a && standBs != null && standBs.func_177230_c() != Blocks.field_150350_a) {
                            this.farmingDirection = this.farmingDirection == FarmingDirection.LEFT ? FarmingDirection.RIGHT : FarmingDirection.LEFT;
                            this.pressKeys();
                        }
                        if (standBs != null && standBs.func_177230_c() == Blocks.field_150357_h) {
                            this.sendWebhook("ADMIN CHECK", "Stopped Farming:\\r\\nBitch you are getting Admin checked - do something!", 15158332, true);
                            if (this.alertState == AlertState.CHILLING) {
                                this.alertState = AlertState.TURNUP;
                            }
                            new Thread(() -> {
                                try {
                                    Thread.sleep(1500L);
                                    this.farmingState = FarmingState.STOP_FARMING;
                                    Thread.sleep(2500L);
                                    this.mc.field_71439_g.func_71165_d(this.msgs.get(RandomUtil.randBetween(0, this.msgs.size() - 1)));
                                }
                                catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }).start();
                        }
                        if ((heldItem = this.mc.field_71439_g.func_70694_bm()) != null && (heldItem.func_77973_b() == Items.field_151098_aY || heldItem.func_77973_b() == Items.field_151148_bJ)) {
                            this.sendWebhook("MAPTCHA", "Stopped Farming:\\r\\nShitter you got a Maptcha, you should probably solve it!", 15158332, true);
                            if (this.alertState == AlertState.CHILLING) {
                                this.alertState = AlertState.TURNUP;
                            }
                            this.farmingState = FarmingState.STOP_FARMING;
                        }
                        if (++this.stuckTicks >= 100) {
                            this.curPos = this.mc.field_71439_g.func_180425_c();
                            if (this.oldPos != null && Math.sqrt(this.curPos.func_177951_i((Vec3i)this.oldPos)) <= 2.0 && !this.invFull) {
                                this.sendWebhook("I AM STUCK", "Oh no - I'm stuck, Step Bro come help me! >_<", 15158332, true);
                                if (this.alertState == AlertState.CHILLING) {
                                    this.alertState = AlertState.TURNUP;
                                }
                            }
                            this.oldPos = this.curPos;
                            this.stuckTicks = 0;
                        }
                        switch (this.sameInvState) {
                            case CHILLING: {
                                List<ItemStack> inv = InventoryUtils.getInventoryStacks();
                                if (inv.equals(this.oldInv)) {
                                    if (++this.oldInvCount < 240) break;
                                    if (this.alertState == AlertState.CHILLING) {
                                        this.alertState = AlertState.TURNUP;
                                    }
                                    this.sameInvState = SameInvState.UNPRESS;
                                    this.sameInvTimer.reset();
                                    this.oldInvCount = 0;
                                    break;
                                }
                                this.oldInv = InventoryUtils.getInventoryStacks();
                                this.oldInvCount = 0;
                                break;
                            }
                            case UNPRESS: {
                                KeyBinding.func_74506_a();
                                this.sameInvState = SameInvState.PRESS;
                                this.sameInvTimer.reset();
                                break;
                            }
                            case PRESS: {
                                if (!this.sameInvTimer.hasReached(30000L)) break;
                                this.pressKeys();
                                this.sameInvState = SameInvState.CHILLING;
                            }
                        }
                        if (this.mc.field_71439_g.field_71071_by.func_70447_i() == -1 && !this.invFull) {
                            this.sendWebhook("FULL INVENTORY", "Stopped Farming:\\r\\nInventory Full!", 15439360, false);
                            if (this.alertState == AlertState.CHILLING) {
                                this.alertState = AlertState.TURNUP;
                            }
                            this.invFull = true;
                            KeyBinding.func_74506_a();
                            break block0;
                        }
                        if (this.mc.field_71439_g.field_71071_by.func_70447_i() == -1 || !this.invFull) break;
                        this.sendWebhook("INVENTORY NOT FULL", "Continued Farming:\\r\\nInventory not full anymore!", 3066993, false);
                        this.invFull = false;
                        this.pressKeys();
                        break block0;
                    }
                    case SKYBLOCK: {
                        ChatUtils.send("Player isn't in Island!", new String[0]);
                        ChatUtils.send("Re-warping in " + this.failSafeDelay.getCurrent() + "ms", new String[0]);
                        this.sendWebhook("NOT IN ISLAND", "Player isn't in Island!\\r\\nRecovering...", 15439360, false);
                        this.recoverStr = "/warp home";
                        this.recoverBool = true;
                        this.farmingState = FarmingState.RECOVER;
                        this.recoverTimer.reset();
                        KeyBinding.func_74506_a();
                        break block0;
                    }
                    case LOBBY: {
                        ChatUtils.send("Player isn't in Skyblock!", new String[0]);
                        ChatUtils.send("Joining Skyblock in " + this.failSafeDelay.getCurrent() + "ms", new String[0]);
                        this.sendWebhook("NOT IN SKYBLOCK", "Player isn't in Island!\\r\\nRecovering...", 15439360, false);
                        this.recoverStr = "/play skyblock";
                        this.recoverBool = true;
                        this.farmingState = FarmingState.RECOVER;
                        this.recoverTimer.reset();
                        KeyBinding.func_74506_a();
                        break block0;
                    }
                    case LIMBO: {
                        ChatUtils.send("Player is in Limbo!", new String[0]);
                        ChatUtils.send("Escaping in " + this.failSafeDelay.getCurrent() + "ms", new String[0]);
                        this.sendWebhook("PLAYER IN LIMBO", "Player isn't in Island!\\r\\nRecovering...", 15439360, false);
                        this.recoverStr = "/l";
                        this.recoverBool = true;
                        this.farmingState = FarmingState.RECOVER;
                        this.recoverTimer.reset();
                        KeyBinding.func_74506_a();
                    }
                }
                break;
            }
            case RECOVER: {
                if (this.islandReboot) {
                    switch (this.isRebootState) {
                        case ISLAND: {
                            if (!this.recoverTimer.hasReached(this.failSafeDelay.getCurrent().intValue())) break;
                            this.mc.field_71439_g.func_71165_d(this.recoverStr);
                            this.recoverStr = "/warp home";
                            this.isRebootState = IsRebootState.HUB;
                            this.recoverTimer.reset();
                            break block0;
                        }
                        case HUB: {
                            if (!this.recoverTimer.hasReached(15000L)) break;
                            this.mc.field_71439_g.func_71165_d(this.recoverStr);
                            this.farmingState = FarmingState.FARMING;
                            this.recoverTimer.reset();
                            this.islandReboot = false;
                        }
                    }
                    break;
                }
                if (!this.recoverTimer.hasReached(this.failSafeDelay.getCurrent().intValue()) || SkyblockUtils.getLocation() == SkyblockUtils.Location.NONE) break;
                this.mc.field_71439_g.func_71165_d(this.recoverStr);
                this.farmingState = FarmingState.FARMING;
                this.recoverTimer.reset();
                break;
            }
            case STOP_FARMING: {
                if (this.autoTab.isEnabled()) {
                    ChadUtils.regrabMouse();
                }
                if (this.cpuSaver.isEnabled()) {
                    ChadUtils.revertCpuUsage();
                }
                KeyBinding.func_74506_a();
            }
        }
        if (this.soundAlerts.isEnabled()) {
            switch (this.alertState) {
                case TURNUP: {
                    for (SoundCategory category : SoundCategory.values()) {
                        this.oldSounds.put(category, Float.valueOf(this.mc.field_71474_y.func_151438_a(category)));
                        this.mc.field_71474_y.func_151439_a(category, 0.5f);
                    }
                    this.alertState = AlertState.PLAY;
                    this.alertTimer.reset();
                    break;
                }
                case PLAY: {
                    if (!this.alertTimer.hasReached(100L)) break;
                    this.mc.field_71439_g.func_85030_a("mob.enderdragon.growl", 1.0f, 1.0f);
                    this.alertState = AlertState.TURNDOWN;
                    this.alertTimer.reset();
                    break;
                }
                case TURNDOWN: {
                    if (!this.alertTimer.hasReached(2500L)) break;
                    for (SoundCategory category : SoundCategory.values()) {
                        this.mc.field_71474_y.func_151439_a(category, this.oldSounds.get((Object)category).floatValue());
                    }
                    this.alertState = AlertState.CHILLING;
                }
            }
        }
    }

    @Event
    public void onRenderWorld(Render3DEvent event) {
        if (this.farmingState == FarmingState.SET_ANGLES) {
            if (System.currentTimeMillis() <= RotationUtils.endTime) {
                RotationUtils.update();
            } else {
                RotationUtils.update();
                this.farmingDirection = this.determineDirection();
                this.farmingState = FarmingState.PRESS_KEYS;
            }
        }
    }

    @Event
    public void onChat(ClientChatReceivedEvent event) {
        if (event.type == 2) {
            return;
        }
        if (this.farmingState == FarmingState.FARMING) {
            String msg = StringUtils.func_76338_a((String)event.message.func_150260_c());
            if (msg.startsWith("From") || msg.matches("\\[SkyBlock] .*? is visiting Your Island!.*") || msg.contains("has invited you to join their party!")) {
                String[] split;
                this.alertState = AlertState.TURNUP;
                if (msg.startsWith("From")) {
                    this.sendWebhook("MESSAGE", "Received Message:\\r\\n" + msg, 1689596, true);
                } else if (msg.matches("\\[SkyBlock] .*? is visiting Your Island!.*")) {
                    Pattern pat = Pattern.compile("\\[SkyBlock] (.*?) is visiting Your Island!.*");
                    Matcher mat = pat.matcher(msg);
                    if (mat.matches()) {
                        this.sendWebhook("GETTING VISITED", "Player is visiting you:\\r\\n" + mat.group(1), 1689596, true);
                    }
                } else if (msg.contains("has invited you to join their party!") && (split = msg.split("\n")).length == 4) {
                    String mm = split[1];
                    Pattern pat = Pattern.compile("(.*?) has invited you to join their party!.*");
                    Matcher mat = pat.matcher(mm);
                    if (mat.matches()) {
                        this.sendWebhook("PARTY REQUEST", "Player partied you:\\r\\n" + mat.group(1), 1689596, true);
                    }
                }
            }
            if (msg.startsWith("[Important] This server will restart soon:")) {
                this.alertState = AlertState.TURNUP;
                this.mc.field_71439_g.func_71165_d("/setspawn");
                this.islandReboot = true;
                this.isRebootState = IsRebootState.ISLAND;
                ChatUtils.send("Server is rebooting!", new String[0]);
                ChatUtils.send("Escaping in " + this.failSafeDelay.getCurrent() + "ms", new String[0]);
                this.recoverStr = "/warp hub";
                this.farmingState = FarmingState.RECOVER;
                this.recoverTimer.reset();
                KeyBinding.func_74506_a();
            }
        }
    }

    private FarmingDirection determineDirection() {
        IBlockState downBs;
        IBlockState upBs;
        BlockPos down;
        BlockPos further;
        int i;
        int[] mod = this.getModifiers(this.direction.getCurrent());
        for (i = 0; i < 160; ++i) {
            further = new BlockPos(this.mc.field_71439_g.field_70165_t + (double)(mod[0] * (i + 1)), this.mc.field_71439_g.field_70163_u, this.mc.field_71439_g.field_70161_v + (double)(mod[1] * (i + 1)));
            down = new BlockPos(this.mc.field_71439_g.field_70165_t + (double)(mod[0] * i), this.mc.field_71439_g.field_70163_u - 1.0, this.mc.field_71439_g.field_70161_v + (double)(mod[1] * i));
            upBs = this.mc.field_71441_e.func_180495_p(further);
            downBs = this.mc.field_71441_e.func_180495_p(down);
            if (downBs == null || downBs.func_177230_c() != Blocks.field_150350_a || upBs == null || upBs.func_177230_c() == Blocks.field_150350_a) continue;
            return FarmingDirection.LEFT;
        }
        for (i = 0; i < 160; ++i) {
            further = new BlockPos(this.mc.field_71439_g.field_70165_t - (double)(mod[0] * (i + 1)), this.mc.field_71439_g.field_70163_u, this.mc.field_71439_g.field_70161_v - (double)(mod[1] * (i + 1)));
            down = new BlockPos(this.mc.field_71439_g.field_70165_t - (double)(mod[0] * i), this.mc.field_71439_g.field_70163_u - 1.0, this.mc.field_71439_g.field_70161_v - (double)(mod[1] * i));
            upBs = this.mc.field_71441_e.func_180495_p(further);
            downBs = this.mc.field_71441_e.func_180495_p(down);
            if (downBs == null || downBs.func_177230_c() != Blocks.field_150350_a || upBs == null || upBs.func_177230_c() == Blocks.field_150350_a) continue;
            return FarmingDirection.RIGHT;
        }
        return FarmingDirection.LEFT;
    }

    private int[] getModifiers(String direction) {
        int[] ret = new int[2];
        switch (direction) {
            case "SOUTH": {
                ret[0] = 1;
                break;
            }
            case "WEST": {
                ret[1] = 1;
                break;
            }
            case "NORTH": {
                ret[0] = -1;
                break;
            }
            case "EAST": {
                ret[1] = -1;
            }
        }
        return ret;
    }

    private void pressKeys() {
        this.mc.field_71462_r = null;
        this.mc.field_71415_G = true;
        KeyBinding.func_74510_a((int)this.mc.field_71474_y.field_74351_w.func_151463_i(), (boolean)true);
        KeyBinding.func_74510_a((int)this.mc.field_71474_y.field_74312_F.func_151463_i(), (boolean)true);
        switch (this.farmingDirection) {
            case LEFT: {
                KeyBinding.func_74510_a((int)this.mc.field_71474_y.field_74366_z.func_151463_i(), (boolean)false);
                KeyBinding.func_74510_a((int)this.mc.field_71474_y.field_74370_x.func_151463_i(), (boolean)true);
                break;
            }
            case RIGHT: {
                KeyBinding.func_74510_a((int)this.mc.field_71474_y.field_74370_x.func_151463_i(), (boolean)false);
                KeyBinding.func_74510_a((int)this.mc.field_71474_y.field_74366_z.func_151463_i(), (boolean)true);
            }
        }
    }

    private void sendWebhook(String title, String updateReason, int color, boolean ping) {
        if (this.webhookUpdates.isEnabled()) {
            try {
                HttpURLConnection con = (HttpURLConnection)new URL(this.webhookUrl.getCurrent()).openConnection();
                con.setDoOutput(true);
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("User-Agent", "Mozilla/5.0");
                Session session = this.mc.func_110432_I();
                String json = "{ \"content\": " + (ping ? "\"@everyone\"" : "null") + ", \"embeds\": [ { \"title\": \"" + title + "\", \"description\": \"**Account Info**\\nIGN: " + session.func_111285_a() + "\", \"color\": " + color + ", \"fields\": [ { \"name\": \"Update Reason:\", \"value\": \"" + updateReason + "\" } ], \"footer\": { \"text\": \"Made by Apfelsaft#0002\", \"icon_url\": \"https://visage.surgeplay.com/face/128/7c224caeaea249a49783053b9bcf4ed1\" } } ], \"username\": \"" + session.func_111285_a() + "\", \"avatar_url\": \"https://visage.surgeplay.com/face/128/" + session.func_148255_b() + "\" }";
                try (OutputStream output = con.getOutputStream();){
                    output.write(json.getBytes(StandardCharsets.UTF_8));
                }
                int resp = con.getResponseCode();
                if (resp == 200 || resp == 204) {
                    ChatUtils.send("Webhook sent successfully", new String[0]);
                } else {
                    ChatUtils.send("Error while sending Webhook", new String[0]);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static enum IsRebootState {
        ISLAND,
        HUB;

    }

    static enum SameInvState {
        CHILLING,
        UNPRESS,
        PRESS;

    }

    static enum AlertState {
        CHILLING,
        TURNUP,
        PLAY,
        TURNDOWN;

    }

    static enum FarmingDirection {
        LEFT,
        RIGHT;

    }

    static enum FarmingState {
        START_FARMING,
        SET_ANGLES,
        PRESS_KEYS,
        FARMING,
        STOP_FARMING,
        RECOVER;

    }
}

