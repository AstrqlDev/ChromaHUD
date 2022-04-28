/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.gui.inventory.GuiChest
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ContainerChest
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StringUtils
 */
package xyz.apfelmus.cheeto.client.modules.misc;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringUtils;
import xyz.apfelmus.cf4m.annotation.Event;
import xyz.apfelmus.cf4m.annotation.Setting;
import xyz.apfelmus.cf4m.annotation.module.Module;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cheeto.client.events.BackgroundDrawnEvent;
import xyz.apfelmus.cheeto.client.events.ClientTickEvent;
import xyz.apfelmus.cheeto.client.events.GuiOpenEvent;
import xyz.apfelmus.cheeto.client.settings.BooleanSetting;

@Module(name="ExperimentSolver", category=Category.MISC)
public class ExperimentSolver {
    @Setting(name="Chronomatron")
    BooleanSetting chronomatron = new BooleanSetting(true);
    @Setting(name="Ultrasequencer")
    BooleanSetting ultrasequencer = new BooleanSetting(true);
    private static int until = 0;
    private static int tickAmount = 0;
    private static Minecraft mc = Minecraft.func_71410_x();
    private static Slot[] clickInOrderSlots = new Slot[36];
    private static int lastChronomatronRound = 0;
    private static List<String> chronomatronPattern = new ArrayList<String>();
    private static int chronomatronMouseClicks = 0;
    private static int lastUltraSequencerClicked = 0;
    private static long lastInteractTime = 0L;

    @Event
    public void onTick(BackgroundDrawnEvent event) {
        if (ExperimentSolver.mc.field_71462_r instanceof GuiChest) {
            GuiChest inventory = (GuiChest)event.gui;
            Container containerChest = inventory.field_147002_h;
            if (containerChest instanceof ContainerChest) {
                EntityPlayerSP player;
                List invSlots = containerChest.field_75151_b;
                String invName = ((ContainerChest)containerChest).func_85151_d().func_145748_c_().func_150260_c().trim();
                if (this.chronomatron.isEnabled() && invName.startsWith("Chronomatron (")) {
                    player = ExperimentSolver.mc.field_71439_g;
                    if (player.field_71071_by.func_70445_o() == null && invSlots.size() > 48 && ((Slot)invSlots.get(49)).func_75211_c() != null) {
                        if (((Slot)invSlots.get(49)).func_75211_c().func_82833_r().startsWith("\u00a77Timer: \u00a7a") && ((Slot)invSlots.get(4)).func_75211_c() != null) {
                            int i;
                            int round = ((Slot)invSlots.get((int)4)).func_75211_c().field_77994_a;
                            int timerSeconds = Integer.parseInt(StringUtils.func_76338_a((String)((Slot)invSlots.get(49)).func_75211_c().func_82833_r()).replaceAll("[^\\d]", ""));
                            if (round != lastChronomatronRound && timerSeconds == round + 2) {
                                lastChronomatronRound = round;
                                for (i = 10; i <= 43; ++i) {
                                    ItemStack stack = ((Slot)invSlots.get(i)).func_75211_c();
                                    if (stack == null || stack.func_77973_b() != Item.func_150898_a((Block)Blocks.field_150406_ce)) continue;
                                    chronomatronPattern.add(stack.func_82833_r());
                                    break;
                                }
                            }
                            if (chronomatronMouseClicks < chronomatronPattern.size() && player.field_71071_by.func_70445_o() == null) {
                                for (i = 10; i <= 43; ++i) {
                                    ItemStack glass = ((Slot)invSlots.get(i)).func_75211_c();
                                    if (glass == null || player.field_71071_by.func_70445_o() != null || tickAmount % 5 != 0) continue;
                                    Slot glassSlot = (Slot)invSlots.get(i);
                                    if (!glass.func_82833_r().equals(chronomatronPattern.get(chronomatronMouseClicks))) continue;
                                    ExperimentSolver.mc.field_71442_b.func_78753_a(ExperimentSolver.mc.field_71439_g.field_71070_bA.field_75152_c, glassSlot.field_75222_d, 0, 0, (EntityPlayer)ExperimentSolver.mc.field_71439_g);
                                    lastInteractTime = 0L;
                                    ++chronomatronMouseClicks;
                                    break;
                                }
                            }
                        } else if (((Slot)invSlots.get(49)).func_75211_c().func_82833_r().equals("\u00a7aRemember the pattern!")) {
                            chronomatronMouseClicks = 0;
                        }
                    }
                }
                if (this.ultrasequencer.isEnabled() && invName.startsWith("Ultrasequencer (")) {
                    player = ExperimentSolver.mc.field_71439_g;
                    if (invSlots.size() > 48 && ((Slot)invSlots.get(49)).func_75211_c() != null && player.field_71071_by.func_70445_o() == null && ((Slot)invSlots.get(49)).func_75211_c().func_82833_r().startsWith("\u00a77Timer: \u00a7a")) {
                        Slot nextSlot;
                        lastUltraSequencerClicked = 0;
                        for (Slot slot4 : clickInOrderSlots) {
                            int number;
                            if (slot4 == null || slot4.func_75211_c() == null || !StringUtils.func_76338_a((String)slot4.func_75211_c().func_82833_r()).matches("\\d+") || (number = Integer.parseInt(StringUtils.func_76338_a((String)slot4.func_75211_c().func_82833_r()))) <= lastUltraSequencerClicked) continue;
                            lastUltraSequencerClicked = number;
                        }
                        if (clickInOrderSlots[lastUltraSequencerClicked] != null && player.field_71071_by.func_70445_o() == null && tickAmount % 2 == 0 && lastUltraSequencerClicked != 0 && until == lastUltraSequencerClicked) {
                            nextSlot = clickInOrderSlots[lastUltraSequencerClicked];
                            ExperimentSolver.mc.field_71442_b.func_78753_a(ExperimentSolver.mc.field_71439_g.field_71070_bA.field_75152_c, nextSlot.field_75222_d, 0, 0, (EntityPlayer)ExperimentSolver.mc.field_71439_g);
                            until = lastUltraSequencerClicked + 1;
                            tickAmount = 0;
                        }
                        if (clickInOrderSlots[lastUltraSequencerClicked] != null && player.field_71071_by.func_70445_o() == null && tickAmount == 18 && lastUltraSequencerClicked < 1) {
                            nextSlot = clickInOrderSlots[lastUltraSequencerClicked];
                            ExperimentSolver.mc.field_71442_b.func_78753_a(ExperimentSolver.mc.field_71439_g.field_71070_bA.field_75152_c, nextSlot.field_75222_d, 0, 0, (EntityPlayer)ExperimentSolver.mc.field_71439_g);
                            tickAmount = 0;
                            until = 1;
                        }
                    }
                }
            }
        }
    }

    @Event
    public void onGuiOpen(GuiOpenEvent event) {
        clickInOrderSlots = new Slot[36];
        lastChronomatronRound = 0;
        chronomatronPattern.clear();
        chronomatronMouseClicks = 0;
    }

    @Event
    public void onTick(ClientTickEvent event) {
        if (++tickAmount % 20 == 0) {
            tickAmount = 0;
        }
        if (ExperimentSolver.mc.field_71462_r instanceof GuiChest && this.ultrasequencer.isEnabled()) {
            ContainerChest chest = (ContainerChest)ExperimentSolver.mc.field_71439_g.field_71070_bA;
            List invSlots = ((GuiChest)ExperimentSolver.mc.field_71462_r).field_147002_h.field_75151_b;
            String chestName = chest.func_85151_d().func_145748_c_().func_150260_c().trim();
            if (chestName.startsWith("Ultrasequencer (") && ((Slot)invSlots.get(49)).func_75211_c() != null && ((Slot)invSlots.get(49)).func_75211_c().func_82833_r().equals("\u00a7aRemember the pattern!")) {
                for (int l = 9; l <= 44; ++l) {
                    String itemName;
                    if (invSlots.get(l) == null || ((Slot)invSlots.get(l)).func_75211_c() == null || !(itemName = StringUtils.func_76338_a((String)((Slot)invSlots.get(l)).func_75211_c().func_82833_r())).matches("\\d+")) continue;
                    int number = Integer.parseInt(itemName);
                    ExperimentSolver.clickInOrderSlots[number - 1] = (Slot)invSlots.get(l);
                }
            }
        }
    }
}

