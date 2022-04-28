/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.PlayerControllerMP
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityArmorStand
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.C02PacketUseEntity
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.StringUtils
 *  net.minecraft.util.Vec3
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$Action
 */
package xyz.apfelmus.cheeto.client.modules.world;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StringUtils;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
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

@Module(name="FairySoulAura", category=Category.WORLD)
public class FairySoulAura {
    @Setting(name="ClickRange")
    private FloatSetting clickRange = new FloatSetting(Float.valueOf(2.75f), Float.valueOf(0.0f), Float.valueOf(5.0f));
    @Setting(name="ClickSlot")
    private IntegerSetting clickSlot = new IntegerSetting(0, 0, 8);
    @Setting(name="ClickDelay")
    private FloatSetting clickDelay = new FloatSetting(Float.valueOf(500.0f), Float.valueOf(0.0f), Float.valueOf(2500.0f));
    @Setting(name="Stonkless")
    private BooleanSetting stonkless = new BooleanSetting(true);
    private static Minecraft mc = Minecraft.func_71410_x();
    private static List<Entity> clicked = new ArrayList<Entity>();
    private List<Entity> soulsNear = new ArrayList<Entity>();
    private List<Entity> foundSouls = new ArrayList<Entity>();
    private Entity selected;
    private Method syncCurrentPlayItem = null;
    private long lastClickTime;

    @Enable
    public void onEnable() {
        if (this.syncCurrentPlayItem == null) {
            try {
                this.syncCurrentPlayItem = PlayerControllerMP.class.getDeclaredMethod("syncCurrentPlayItem", new Class[0]);
            }
            catch (NoSuchMethodException e) {
                try {
                    this.syncCurrentPlayItem = PlayerControllerMP.class.getDeclaredMethod("func_78750_j", new Class[0]);
                }
                catch (NoSuchMethodException noSuchMethodException) {
                    // empty catch block
                }
            }
        }
        if (this.syncCurrentPlayItem != null) {
            this.syncCurrentPlayItem.setAccessible(true);
        }
        this.soulsNear.clear();
        this.foundSouls.clear();
        clicked.clear();
        this.selected = null;
    }

    @Event
    public void onTick(ClientTickEvent event) {
        if (FairySoulAura.mc.field_71441_e != null && FairySoulAura.mc.field_71439_g != null) {
            for (Entity e : FairySoulAura.mc.field_71441_e.field_72996_f) {
                NBTTagCompound skullOwner;
                ItemStack stack;
                if (this.soulsNear.contains((Object)e) || !(e instanceof EntityArmorStand) || (stack = ((EntityArmorStand)e).func_82169_q(3)) == null || !stack.func_82833_r().equals("Head") || (skullOwner = stack.func_179543_a("SkullOwner", false)) == null || !skullOwner.func_74779_i("Id").equals("57a4c8dc-9b8e-3d41-80da-a608901a6147") || this.foundSouls.contains((Object)e)) continue;
                this.soulsNear.add(e);
            }
            if (!this.stonkless.isEnabled()) {
                for (Entity e : new ArrayList<Entity>(this.soulsNear)) {
                    Vec3 eyes = FairySoulAura.mc.field_71439_g.func_174824_e(1.0f);
                    if (clicked.contains((Object)e) || !(e.func_70011_f(eyes.field_72450_a, eyes.field_72448_b - 2.0, eyes.field_72449_c) < (double)this.clickRange.getCurrent().floatValue())) continue;
                    if ((float)(System.currentTimeMillis() - this.lastClickTime) >= this.clickDelay.getCurrent().floatValue()) {
                        this.handleClick(e);
                        clicked.add(e);
                    }
                    break;
                }
            } else {
                clicked.clear();
            }
        }
    }

    @Event
    public void onRender(Render3DEvent event) {
        this.selected = null;
        for (Entity e : new ArrayList<Entity>(this.soulsNear)) {
            if (this.selected == null && RotationUtils.lookingAt(e.func_180425_c().func_177982_a(0, 1, 0), this.clickRange.getCurrent().floatValue())) {
                this.selected = e;
                continue;
            }
            if (!clicked.contains((Object)e)) {
                Render3DUtils.drawFairySoulOutline(e, event.partialTicks, -1);
                continue;
            }
            Render3DUtils.drawFairySoulOutline(e, event.partialTicks, -12345273);
        }
        if (this.selected != null) {
            Render3DUtils.drawFairySoulOutline(this.selected, event.partialTicks, -19712);
        }
    }

    @Event
    public void onInteract(PlayerInteractEvent event) {
        if (this.stonkless.isEnabled() && this.selected != null && !clicked.contains((Object)this.selected)) {
            MovingObjectPosition omo = FairySoulAura.mc.field_71476_x;
            if (omo != null && omo.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY && omo.field_72308_g.equals((Object)this.selected)) {
                return;
            }
            if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR || event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
                clicked.add(this.selected);
                this.handleClick(this.selected);
            }
        }
    }

    @Event
    public void onWorldLoad(WorldUnloadEvent event) {
        clicked.clear();
    }

    @Event
    public void onChat(ClientChatReceivedEvent event) {
        String msg = StringUtils.func_76338_a((String)event.message.func_150260_c());
        if (msg.equals("SOUL! You found a Fairy Soul!") || msg.equals("You have already found that Fairy Soul!")) {
            Optional<Entity> nearest = this.soulsNear.stream().min(Comparator.comparing(a -> Float.valueOf(a.func_70032_d((Entity)FairySoulAura.mc.field_71439_g))));
            nearest.ifPresent(e -> {
                this.foundSouls.add((Entity)e);
                this.soulsNear.remove(e);
            });
        }
    }

    private void handleClick(Entity e) {
        this.lastClickTime = System.currentTimeMillis();
        if (FairySoulAura.mc.field_71476_x == null) {
            return;
        }
        MovingObjectPosition movingObject = FairySoulAura.mc.field_71476_x;
        if (this.clickSlot.getCurrent() != 0) {
            int holding = FairySoulAura.mc.field_71439_g.field_71071_by.field_70461_c;
            FairySoulAura.mc.field_71439_g.field_71071_by.field_70461_c = this.clickSlot.getCurrent();
            this.syncItem();
            Vec3 vec3 = new Vec3(movingObject.field_72307_f.field_72450_a - e.field_70165_t, movingObject.field_72307_f.field_72448_b - e.field_70163_u, movingObject.field_72307_f.field_72449_c - e.field_70161_v);
            mc.func_147114_u().func_147297_a((Packet)new C02PacketUseEntity(e, vec3));
            e.func_174825_a((EntityPlayer)FairySoulAura.mc.field_71439_g, vec3);
            FairySoulAura.mc.field_71439_g.field_71071_by.field_70461_c = holding;
        } else {
            this.syncItem();
            Vec3 vec3 = new Vec3(movingObject.field_72307_f.field_72450_a - e.field_70165_t, movingObject.field_72307_f.field_72448_b - e.field_70163_u, movingObject.field_72307_f.field_72449_c - e.field_70161_v);
            mc.func_147114_u().func_147297_a((Packet)new C02PacketUseEntity(e, vec3));
        }
        if (!this.stonkless.isEnabled()) {
            this.soulsNear.remove((Object)e);
            clicked.add(e);
        }
    }

    private void syncItem() {
        if (this.syncCurrentPlayItem != null) {
            try {
                this.syncCurrentPlayItem.invoke((Object)FairySoulAura.mc.field_71442_b, new Object[0]);
            }
            catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}

