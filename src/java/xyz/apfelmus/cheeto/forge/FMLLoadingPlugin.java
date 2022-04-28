/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  kotlin.Metadata
 *  kotlin.jvm.internal.Intrinsics
 *  net.minecraftforge.fml.relauncher.IFMLLoadingPlugin
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.spongepowered.asm.launch.MixinBootstrap
 *  org.spongepowered.asm.mixin.MixinEnvironment
 *  org.spongepowered.asm.mixin.Mixins
 */
package xyz.apfelmus.cheeto.forge;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

@Metadata(mv={1, 6, 0}, k=1, xi=48, d1={"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0013\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0016\u00a2\u0006\u0002\u0010\u0006J\n\u0010\u0007\u001a\u0004\u0018\u00010\u0005H\u0016J\n\u0010\b\u001a\u0004\u0018\u00010\u0005H\u0016J\n\u0010\t\u001a\u0004\u0018\u00010\u0005H\u0016J\u001c\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000e0\rH\u0016\u00a8\u0006\u000f"}, d2={"Lxyz/apfelmus/cheeto/forge/FMLLoadingPlugin;", "Lnet/minecraftforge/fml/relauncher/IFMLLoadingPlugin;", "()V", "getASMTransformerClass", "", "", "()[Ljava/lang/String;", "getAccessTransformerClass", "getModContainerClass", "getSetupClass", "injectData", "", "data", "", "", "Cheeto"})
public final class FMLLoadingPlugin
implements IFMLLoadingPlugin {
    public FMLLoadingPlugin() {
        MixinBootstrap.init();
        Mixins.addConfiguration((String)"mixins.cheeto.json");
        MixinEnvironment.getCurrentEnvironment().setObfuscationContext("searge");
    }

    @NotNull
    public String[] getASMTransformerClass() {
        boolean $i$f$emptyArray = false;
        return new String[0];
    }

    @Nullable
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    public String getSetupClass() {
        return null;
    }

    public void injectData(@NotNull Map<String, ? extends Object> data) {
        Intrinsics.checkNotNullParameter(data, (String)"data");
    }

    @Nullable
    public String getAccessTransformerClass() {
        return null;
    }
}

