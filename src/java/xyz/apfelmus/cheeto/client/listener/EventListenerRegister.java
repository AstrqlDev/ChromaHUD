/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiChat
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraftforge.client.event.ClientChatReceivedEvent
 *  net.minecraftforge.client.event.GuiOpenEvent
 *  net.minecraftforge.client.event.GuiScreenEvent$BackgroundDrawnEvent
 *  net.minecraftforge.client.event.RenderLivingEvent$Pre
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.event.entity.player.EntityInteractEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent
 *  net.minecraftforge.event.world.WorldEvent$Unload
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.InputEvent$MouseInputEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.gameevent.TickEvent$RenderTickEvent
 */
package xyz.apfelmus.cheeto.client.listener;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import xyz.apfelmus.cheeto.client.clickgui.ConfigGUI;
import xyz.apfelmus.cheeto.client.events.BackgroundDrawnEvent;
import xyz.apfelmus.cheeto.client.events.ClientTickEvent;
import xyz.apfelmus.cheeto.client.events.EntityInteractEvent;
import xyz.apfelmus.cheeto.client.events.MouseInputEvent;
import xyz.apfelmus.cheeto.client.events.PlayerInteractEvent;
import xyz.apfelmus.cheeto.client.events.Render2DEvent;
import xyz.apfelmus.cheeto.client.events.Render3DEvent;
import xyz.apfelmus.cheeto.client.events.RenderLivingEventPre;
import xyz.apfelmus.cheeto.client.events.WorldUnloadEvent;

public class EventListenerRegister {
    private static Minecraft mc = Minecraft.func_71410_x();

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END || EventListenerRegister.mc.field_71439_g == null || EventListenerRegister.mc.field_71441_e == null) {
            return;
        }
        new ClientTickEvent().call();
    }

    @SubscribeEvent
    public void onRenderGui(TickEvent.RenderTickEvent event) {
        if (EventListenerRegister.mc.field_71439_g == null || EventListenerRegister.mc.field_71441_e == null || EventListenerRegister.mc.field_71474_y.field_74330_P || EventListenerRegister.mc.field_71462_r != null && !(EventListenerRegister.mc.field_71462_r instanceof GuiChat) && !(EventListenerRegister.mc.field_71462_r instanceof ConfigGUI)) {
            return;
        }
        new Render2DEvent().call();
    }

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        new Render3DEvent(event.partialTicks).call();
    }

    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent event) {
        new xyz.apfelmus.cheeto.client.events.ClientChatReceivedEvent(event.type, event.message).call();
    }

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event) {
        new WorldUnloadEvent().call();
    }

    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event) {
        new xyz.apfelmus.cheeto.client.events.GuiOpenEvent(event.gui).call();
    }

    @SubscribeEvent
    public void onBackgroundDrawn(GuiScreenEvent.BackgroundDrawnEvent event) {
        new BackgroundDrawnEvent(event.gui).call();
    }

    @SubscribeEvent
    public void onBeforeRenderEntity(RenderLivingEvent.Pre<EntityLivingBase> event) {
        new RenderLivingEventPre(event.entity).call();
    }

    @SubscribeEvent
    public void onInteract(net.minecraftforge.event.entity.player.PlayerInteractEvent event) {
        new PlayerInteractEvent(event.action, event.pos).call();
    }

    @SubscribeEvent
    public void onEntityInteract(net.minecraftforge.event.entity.player.EntityInteractEvent event) {
        new EntityInteractEvent(event).call();
    }

    @SubscribeEvent
    public void onMouseInput(InputEvent.MouseInputEvent event) {
        new MouseInputEvent(event).call();
    }
}

