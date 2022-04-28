/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.inventory.GuiChest
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ContainerChest
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.EnumDyeColor
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StringUtils
 */
package xyz.apfelmus.cheeto.client.modules.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.Slot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringUtils;
import xyz.apfelmus.cf4m.annotation.Event;
import xyz.apfelmus.cf4m.annotation.Setting;
import xyz.apfelmus.cf4m.annotation.module.Module;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cheeto.client.events.BackgroundDrawnEvent;
import xyz.apfelmus.cheeto.client.events.ClientTickEvent;
import xyz.apfelmus.cheeto.client.settings.BooleanSetting;
import xyz.apfelmus.cheeto.client.settings.IntegerSetting;
import xyz.apfelmus.cheeto.client.utils.skyblock.SkyblockUtils;

@Module(name="ApfelTerms", category=Category.MISC)
public class ApfelTerms {
    @Setting(name="Delay")
    private IntegerSetting delay = new IntegerSetting(150, 0, 1000);
    @Setting(name="RandomRange")
    private IntegerSetting randomRange = new IntegerSetting(100, 0, 1000);
    @Setting(name="MaxLag", description="Set to something slightly above your ping")
    private IntegerSetting maxLag = new IntegerSetting(250, 0, 1000);
    @Setting(name="Maze")
    BooleanSetting maze = new BooleanSetting(true);
    @Setting(name="Order")
    BooleanSetting order = new BooleanSetting(true);
    @Setting(name="Panes")
    BooleanSetting panes = new BooleanSetting(true);
    @Setting(name="Name")
    BooleanSetting name = new BooleanSetting(true);
    @Setting(name="Color")
    BooleanSetting color = new BooleanSetting(true);
    @Setting(name="Pingless")
    BooleanSetting pingless = new BooleanSetting(true);
    private static Terminal currentTerm = Terminal.NONE;
    private static List<TermSlot> clickQueue = new ArrayList<TermSlot>();
    private static boolean recalculate = false;
    private static final int[] mazeDirection = new int[]{-9, -1, 1, 9};
    private static char letterNeeded = '\u0000';
    private static String colorNeeded = null;
    private static int windowId;
    private static long lastClickTime;
    private static int windowClicks;
    private static int currentSlot;
    private static Minecraft mc;

    @Event
    public void onGuiDraw(BackgroundDrawnEvent event) {
        Container container;
        if (!SkyblockUtils.isInDungeon()) {
            return;
        }
        if (event.gui instanceof GuiChest && (container = ((GuiChest)event.gui).field_147002_h) instanceof ContainerChest) {
            List invSlots = container.field_75151_b;
            if (currentTerm == Terminal.NONE) {
                String chestName = ((ContainerChest)container).func_85151_d().func_145748_c_().func_150260_c();
                if (chestName.equals("Navigate the maze!")) {
                    currentTerm = Terminal.MAZE;
                } else if (chestName.equals("Click in order!")) {
                    currentTerm = Terminal.ORDER;
                } else if (chestName.equals("Correct all the panes!")) {
                    currentTerm = Terminal.PANES;
                } else if (chestName.startsWith("What starts with:")) {
                    currentTerm = Terminal.NAME;
                } else if (chestName.startsWith("Select all the")) {
                    currentTerm = Terminal.COLOR;
                }
            } else {
                if (clickQueue.isEmpty() || recalculate) {
                    recalculate = ApfelTerms.getClicks((ContainerChest)container);
                } else {
                    block0 : switch (currentTerm) {
                        case MAZE: 
                        case ORDER: 
                        case PANES: {
                            for (TermSlot slot : clickQueue) {
                                Slot s;
                                if (slot.clicked <= 0L || System.currentTimeMillis() - slot.clicked < (long)this.maxLag.getCurrent().intValue() || !(s = (Slot)invSlots.get(slot.slot.field_75222_d)).func_75216_d() || s.func_75211_c().func_77952_i() == 5) continue;
                                windowClicks = currentSlot = clickQueue.indexOf(slot);
                                break block0;
                            }
                            break;
                        }
                        case NAME: 
                        case COLOR: {
                            for (TermSlot slot : clickQueue) {
                                Slot s;
                                if (slot.clicked <= 0L || System.currentTimeMillis() - slot.clicked < (long)this.maxLag.getCurrent().intValue() || !(s = (Slot)invSlots.get(slot.slot.field_75222_d)).func_75216_d() || s.func_75211_c().func_77948_v()) continue;
                                windowClicks = currentSlot = clickQueue.indexOf(slot);
                                break block0;
                            }
                            break;
                        }
                    }
                }
                if (!clickQueue.isEmpty() && currentSlot < clickQueue.size() && (double)(System.currentTimeMillis() - lastClickTime) >= (double)this.randomRange.getCurrent().intValue() / 2.0 - Math.random() * (double)this.randomRange.getCurrent().intValue() + (double)this.delay.getCurrent().intValue()) {
                    switch (currentTerm) {
                        case MAZE: {
                            if (!this.maze.isEnabled()) break;
                            this.clickSlot(clickQueue.get(currentSlot));
                            break;
                        }
                        case ORDER: {
                            if (!this.order.isEnabled()) break;
                            this.clickSlot(clickQueue.get(currentSlot));
                            break;
                        }
                        case PANES: {
                            if (!this.panes.isEnabled()) break;
                            this.clickSlot(clickQueue.get(currentSlot));
                            break;
                        }
                        case NAME: {
                            if (!this.name.isEnabled()) break;
                            this.clickSlot(clickQueue.get(currentSlot));
                            break;
                        }
                        case COLOR: {
                            if (!this.color.isEnabled()) break;
                            this.clickSlot(clickQueue.get(currentSlot));
                        }
                    }
                }
            }
        }
    }

    @Event
    public void onTick(ClientTickEvent event) {
        if (!SkyblockUtils.isInDungeon()) {
            return;
        }
        if (!(ApfelTerms.mc.field_71462_r instanceof GuiChest)) {
            currentTerm = Terminal.NONE;
            clickQueue.clear();
            letterNeeded = '\u0000';
            colorNeeded = null;
            windowClicks = 0;
            currentSlot = 0;
        }
    }

    private static boolean getClicks(ContainerChest cc) {
        List invSlots = cc.field_75151_b;
        String chestName = cc.func_85151_d().func_145748_c_().func_150260_c();
        clickQueue.clear();
        switch (currentTerm) {
            case MAZE: {
                int startSlot = -1;
                int endSlot = -1;
                boolean[] mazeVisited = new boolean[54];
                for (Slot slot : invSlots) {
                    ItemStack stack;
                    if (startSlot >= 0 && endSlot >= 0) break;
                    if (slot.field_75224_c == ApfelTerms.mc.field_71439_g.field_71071_by || (stack = slot.func_75211_c()) == null || stack.func_77973_b() != Item.func_150898_a((Block)Blocks.field_150397_co)) continue;
                    if (stack.func_77952_i() == 5) {
                        startSlot = slot.field_75222_d;
                        continue;
                    }
                    if (stack.func_77952_i() != 14) continue;
                    endSlot = slot.field_75222_d;
                }
                while (startSlot != endSlot) {
                    boolean newSlotChosen = false;
                    for (int i = 0; i < 4; ++i) {
                        ItemStack stack;
                        int slotNumber = startSlot + mazeDirection[i];
                        if (slotNumber == endSlot) {
                            return false;
                        }
                        if (slotNumber < 0 || slotNumber > 53 || i == 1 && slotNumber % 9 == 8 || i == 2 && slotNumber % 9 == 0 || mazeVisited[slotNumber] || (stack = ((Slot)invSlots.get(slotNumber)).func_75211_c()) == null || stack.func_77973_b() != Item.func_150898_a((Block)Blocks.field_150397_co) || stack.func_77952_i() != 0) continue;
                        clickQueue.add(new TermSlot((Slot)invSlots.get(slotNumber)));
                        startSlot = slotNumber;
                        mazeVisited[slotNumber] = true;
                        newSlotChosen = true;
                        break;
                    }
                    if (newSlotChosen) continue;
                    return true;
                }
                break;
            }
            case ORDER: {
                while (clickQueue.size() < 14) {
                    clickQueue.add(null);
                }
                for (int j = 10; j <= 25; ++j) {
                    ItemStack itemStack3;
                    if (j == 17 || j == 18 || (itemStack3 = ((Slot)invSlots.get(j)).func_75211_c()) == null || itemStack3.func_77973_b() != Item.func_150898_a((Block)Blocks.field_150397_co) || itemStack3.func_77952_i() != 14 || itemStack3.field_77994_a >= 15) continue;
                    clickQueue.set(itemStack3.field_77994_a - 1, new TermSlot((Slot)invSlots.get(j)));
                }
                if (!clickQueue.removeIf(Objects::isNull)) break;
                return true;
            }
            case PANES: {
                for (Slot slot : invSlots) {
                    if (slot.field_75224_c == ApfelTerms.mc.field_71439_g.field_71071_by || slot.field_75222_d < 9 || slot.field_75222_d > 35 || slot.field_75222_d % 9 <= 1 || slot.field_75222_d % 9 >= 7) continue;
                    ItemStack itemStack = slot.func_75211_c();
                    if (itemStack == null) {
                        return true;
                    }
                    if (itemStack.func_77973_b() != Item.func_150898_a((Block)Blocks.field_150397_co) || itemStack.func_77952_i() != 14) continue;
                    clickQueue.add(new TermSlot(slot));
                }
                break;
            }
            case NAME: {
                letterNeeded = chestName.charAt(chestName.indexOf("'") + 1);
                if (letterNeeded == '\u0000') break;
                for (Slot slot : invSlots) {
                    if (slot.field_75224_c == ApfelTerms.mc.field_71439_g.field_71071_by || slot.field_75222_d < 9 || slot.field_75222_d > 44 || slot.field_75222_d % 9 == 0 || slot.field_75222_d % 9 == 8) continue;
                    ItemStack itemStack = slot.func_75211_c();
                    if (itemStack == null) {
                        return true;
                    }
                    if (itemStack.func_77948_v() || StringUtils.func_76338_a((String)itemStack.func_82833_r()).charAt(0) != letterNeeded) continue;
                    clickQueue.add(new TermSlot(slot));
                }
                break;
            }
            case COLOR: {
                for (EnumDyeColor color : EnumDyeColor.values()) {
                    String colorName = color.func_176610_l().replaceAll("_", " ").toUpperCase();
                    if (!chestName.contains(colorName)) continue;
                    colorNeeded = color.func_176762_d();
                    break;
                }
                if (colorNeeded == null) break;
                for (Slot slot : invSlots) {
                    if (slot.field_75224_c == ApfelTerms.mc.field_71439_g.field_71071_by || slot.field_75222_d < 9 || slot.field_75222_d > 44 || slot.field_75222_d % 9 == 0 || slot.field_75222_d % 9 == 8) continue;
                    ItemStack itemStack = slot.func_75211_c();
                    if (itemStack == null) {
                        return true;
                    }
                    if (itemStack.func_77948_v() || !itemStack.func_77977_a().contains(colorNeeded)) continue;
                    clickQueue.add(new TermSlot(slot));
                }
                break;
            }
        }
        return false;
    }

    private void clickSlot(TermSlot slot) {
        if (windowClicks == 0) {
            windowId = ApfelTerms.mc.field_71439_g.field_71070_bA.field_75152_c;
        }
        ApfelTerms.mc.field_71442_b.func_78753_a(windowId + (this.pingless.isEnabled() ? windowClicks : 0), slot.slot.field_75222_d, 2, 0, (EntityPlayer)ApfelTerms.mc.field_71439_g);
        slot.clicked = System.currentTimeMillis();
        lastClickTime = System.currentTimeMillis();
        ++currentSlot;
        if (this.pingless.isEnabled()) {
            ++windowClicks;
        }
    }

    static {
        windowClicks = 0;
        currentSlot = 0;
        mc = Minecraft.func_71410_x();
    }

    private static class TermSlot {
        public Slot slot;
        public long clicked;

        public TermSlot(Slot slot) {
            this.slot = slot;
            this.clicked = -1L;
        }

        public String toString() {
            return this.slot.field_75222_d + " - " + this.clicked;
        }
    }

    private static enum Terminal {
        NONE,
        MAZE,
        ORDER,
        PANES,
        NAME,
        COLOR;

    }
}

