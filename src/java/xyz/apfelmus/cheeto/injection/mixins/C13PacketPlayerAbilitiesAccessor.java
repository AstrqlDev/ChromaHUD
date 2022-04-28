/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.play.client.C13PacketPlayerAbilities
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package xyz.apfelmus.cheeto.injection.mixins;

import net.minecraft.network.play.client.C13PacketPlayerAbilities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={C13PacketPlayerAbilities.class})
public interface C13PacketPlayerAbilitiesAccessor {
    @Accessor
    public float getFlySpeed();
}

