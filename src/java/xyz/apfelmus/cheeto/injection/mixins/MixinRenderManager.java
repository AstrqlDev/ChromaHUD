/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.EntityOtherPlayerMP
 *  net.minecraft.client.renderer.culling.ICamera
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.projectile.EntitySnowball
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package xyz.apfelmus.cheeto.injection.mixins;

import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.projectile.EntitySnowball;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cheeto.client.modules.combat.GhostArm;

@Mixin(value={RenderManager.class})
public class MixinRenderManager {
    @Inject(method={"shouldRender"}, at={@At(value="HEAD")}, cancellable=true)
    private void shouldRender(Entity entityIn, ICamera camera, double camX, double camY, double camZ, CallbackInfoReturnable<Boolean> cir) {
        if (CF4M.INSTANCE.moduleManager.isEnabled("GhostArm")) {
            GhostArm ga = (GhostArm)CF4M.INSTANCE.moduleManager.getModule("GhostArm");
            if (ga.HideMobs.isEnabled()) {
                if (ga.Zombies.isEnabled() && entityIn instanceof EntityZombie) {
                    cir.setReturnValue((Object)false);
                }
                if (ga.Players.isEnabled() && entityIn instanceof EntityOtherPlayerMP) {
                    cir.setReturnValue((Object)false);
                }
            }
        }
        if (CF4M.INSTANCE.moduleManager.isEnabled("SnowballHider") && entityIn instanceof EntitySnowball) {
            cir.setReturnValue((Object)false);
        }
    }
}

