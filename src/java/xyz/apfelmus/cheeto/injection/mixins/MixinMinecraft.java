/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  org.lwjgl.input.Keyboard
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package xyz.apfelmus.cheeto.injection.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.apfelmus.cf4m.event.events.KeyboardEvent;
import xyz.apfelmus.cheeto.Cheeto;
import xyz.apfelmus.cheeto.client.events.LeftClickEvent;

@Mixin(value={Minecraft.class})
public class MixinMinecraft {
    @Shadow
    public GuiScreen field_71462_r;

    @Inject(method={"startGame"}, at={@At(value="RETURN")})
    private void startGame(CallbackInfo ci) {
        Cheeto.Start();
    }

    @Inject(method={"runTick"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/Minecraft;dispatchKeypresses()V")})
    private void dispatchKeypresses(CallbackInfo ci) {
        if (Keyboard.getEventKeyState() && this.field_71462_r == null) {
            new KeyboardEvent(Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey()).call();
        }
    }

    @Inject(method={"clickMouse"}, at={@At(value="HEAD")})
    private void onLeftClick(CallbackInfo ci) {
        new LeftClickEvent().call();
    }
}

