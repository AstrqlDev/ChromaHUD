/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.inventory.GuiChest
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.ContainerChest
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StringUtils
 *  net.minecraft.world.World
 */
package xyz.apfelmus.cheeto.client.utils.skyblock;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;

public class InventoryUtils {
    private static Minecraft mc = Minecraft.func_71410_x();

    public static String getInventoryName() {
        if (InventoryUtils.mc.field_71462_r instanceof GuiChest) {
            ContainerChest chest = (ContainerChest)InventoryUtils.mc.field_71439_g.field_71070_bA;
            IInventory inv = chest.func_85151_d();
            return inv.func_145818_k_() ? inv.func_70005_c_() : null;
        }
        return null;
    }

    public static ItemStack getStackInSlot(int slot) {
        return InventoryUtils.mc.field_71439_g.field_71071_by.func_70301_a(slot);
    }

    public static ItemStack getStackInOpenContainerSlot(int slot) {
        if (((Slot)InventoryUtils.mc.field_71439_g.field_71070_bA.field_75151_b.get(slot)).func_75216_d()) {
            return ((Slot)InventoryUtils.mc.field_71439_g.field_71070_bA.field_75151_b.get(slot)).func_75211_c();
        }
        return null;
    }

    public static int getSlotForItem(String itemName, Item item) {
        for (Slot slot : InventoryUtils.mc.field_71439_g.field_71070_bA.field_75151_b) {
            ItemStack is;
            if (!slot.func_75216_d() || (is = slot.func_75211_c()).func_77973_b() != item || !is.func_82833_r().contains(itemName)) continue;
            return slot.field_75222_d;
        }
        return -1;
    }

    public static void clickOpenContainerSlot(int slot, int nextWindow) {
        InventoryUtils.mc.field_71442_b.func_78753_a(InventoryUtils.mc.field_71439_g.field_71070_bA.field_75152_c + nextWindow, slot, 0, 0, (EntityPlayer)InventoryUtils.mc.field_71439_g);
    }

    public static void clickOpenContainerSlot(int slot) {
        InventoryUtils.mc.field_71442_b.func_78753_a(InventoryUtils.mc.field_71439_g.field_71070_bA.field_75152_c, slot, 0, 0, (EntityPlayer)InventoryUtils.mc.field_71439_g);
    }

    public static int getAvailableHotbarSlot(String name) {
        for (int i = 0; i < 8; ++i) {
            ItemStack is = InventoryUtils.mc.field_71439_g.field_71071_by.func_70301_a(i);
            if (is != null && !is.func_82833_r().contains(name)) continue;
            return i;
        }
        return -1;
    }

    public static List<Integer> getAllSlots(int throwSlot, String name) {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        for (int i = 9; i < 44; ++i) {
            ItemStack is = ((Slot)InventoryUtils.mc.field_71439_g.field_71069_bz.field_75151_b.get(i)).func_75211_c();
            if (is == null || !is.func_82833_r().contains(name) || i - 36 == throwSlot) continue;
            ret.add(i);
        }
        return ret;
    }

    public static void throwSlot(int slot) {
        ItemStack curInSlot = InventoryUtils.mc.field_71439_g.field_71071_by.func_70301_a(slot);
        if (curInSlot != null) {
            if (curInSlot.func_82833_r().contains("Snowball")) {
                int ss = curInSlot.field_77994_a;
                for (int i = 0; i < ss; ++i) {
                    InventoryUtils.mc.field_71439_g.field_71071_by.field_70461_c = slot;
                    InventoryUtils.mc.field_71442_b.func_78769_a((EntityPlayer)InventoryUtils.mc.field_71439_g, (World)InventoryUtils.mc.field_71441_e, InventoryUtils.mc.field_71439_g.field_71071_by.func_70301_a(slot));
                }
            } else {
                InventoryUtils.mc.field_71439_g.field_71071_by.field_70461_c = slot;
                InventoryUtils.mc.field_71442_b.func_78769_a((EntityPlayer)InventoryUtils.mc.field_71439_g, (World)InventoryUtils.mc.field_71441_e, InventoryUtils.mc.field_71439_g.field_71071_by.func_70301_a(slot));
            }
        }
    }

    public static int getAmountInHotbar(String item) {
        for (int i = 0; i < 8; ++i) {
            ItemStack is = InventoryUtils.mc.field_71439_g.field_71071_by.func_70301_a(i);
            if (is == null || !StringUtils.func_76338_a((String)is.func_82833_r()).equals(item)) continue;
            return is.field_77994_a;
        }
        return 0;
    }

    public static int getItemInHotbar(String itemName) {
        for (int i = 0; i < 8; ++i) {
            ItemStack is = InventoryUtils.mc.field_71439_g.field_71071_by.func_70301_a(i);
            if (is == null || !StringUtils.func_76338_a((String)is.func_82833_r()).contains(itemName)) continue;
            return i;
        }
        return -1;
    }

    public static List<ItemStack> getInventoryStacks() {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        for (int i = 9; i < 44; ++i) {
            ItemStack stack;
            Slot slot = InventoryUtils.mc.field_71439_g.field_71069_bz.func_75139_a(i);
            if (slot == null || (stack = slot.func_75211_c()) == null) continue;
            ret.add(stack);
        }
        return ret;
    }
}

