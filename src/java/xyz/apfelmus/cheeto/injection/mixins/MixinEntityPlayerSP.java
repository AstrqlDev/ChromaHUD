/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.EntityPlayerSP
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package xyz.apfelmus.cheeto.injection.mixins;

import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.apfelmus.cf4m.CF4M;

@Mixin(value={EntityPlayerSP.class})
public class MixinEntityPlayerSP {
    @Inject(at={@At(value="HEAD")}, method={"sendChatMessage"}, cancellable=true)
    private void sendChatMessage(String message, CallbackInfo ci) {
        if (CF4M.INSTANCE.commandManager.isCommand(message)) {
            ci.cancel();
        }
    }
}

