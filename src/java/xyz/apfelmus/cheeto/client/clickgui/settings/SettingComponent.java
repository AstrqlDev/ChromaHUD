/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  gg.essential.elementa.UIComponent
 *  gg.essential.elementa.UIConstraints
 *  gg.essential.elementa.components.UIContainer
 *  gg.essential.elementa.constraints.CenterConstraint
 *  gg.essential.elementa.constraints.XConstraint
 *  gg.essential.elementa.constraints.YConstraint
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package xyz.apfelmus.cheeto.client.clickgui.settings;

import gg.essential.elementa.UIComponent;
import gg.essential.elementa.UIConstraints;
import gg.essential.elementa.components.UIContainer;
import gg.essential.elementa.constraints.CenterConstraint;
import gg.essential.elementa.constraints.XConstraint;
import gg.essential.elementa.constraints.YConstraint;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.apfelmus.cheeto.client.clickgui.settings.SettingComponent;

@Metadata(mv={1, 6, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\b\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\n\u001a\u00020\u000bJ\u0012\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\u000bH\u0016J\u001c\u0010\u0005\u001a\u00020\u00072\u0014\u0010\u000e\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u00070\u0006J\u0010\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0005\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2={"Lxyz/apfelmus/cheeto/client/clickgui/settings/SettingComponent;", "Lgg/essential/elementa/components/UIContainer;", "()V", "lastValue", "", "onValueChange", "Lkotlin/Function1;", "", "changeValue", "newValue", "callListener", "", "closePopups", "instantly", "listener", "setupParentListeners", "parent", "Lgg/essential/elementa/UIComponent;", "Companion", "Cheeto"})
public abstract class SettingComponent
extends UIContainer {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private Function1<Object, Unit> onValueChange = onValueChange.1.INSTANCE;
    @Nullable
    private Object lastValue;
    @NotNull
    public static final String DOWN_ARROW_PNG = "/vigilance/arrow-down.png";
    @NotNull
    public static final String UP_ARROW_PNG = "/vigilance/arrow-up.png";

    public SettingComponent() {
        UIComponent uIComponent;
        UIComponent $this$constrain$iv = (UIComponent)this;
        boolean $i$f$constrain = false;
        UIComponent $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
        boolean bl = false;
        UIConstraints $this$_init__u24lambda_u2d0 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        boolean bl2 = false;
        $this$_init__u24lambda_u2d0.setX((XConstraint)new CenterConstraint());
        $this$_init__u24lambda_u2d0.setY((YConstraint)new CenterConstraint());
    }

    public final void onValueChange(@NotNull Function1<Object, Unit> listener2) {
        Intrinsics.checkNotNullParameter(listener2, (String)"listener");
        this.onValueChange = listener2;
    }

    public final void changeValue(@Nullable Object newValue, boolean callListener) {
        if (!Intrinsics.areEqual((Object)newValue, (Object)this.lastValue)) {
            this.lastValue = newValue;
            this.onValueChange.invoke(newValue);
        }
    }

    public static /* synthetic */ void changeValue$default(SettingComponent settingComponent, Object object, boolean bl, int n, Object object2) {
        if (object2 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: changeValue");
        }
        if ((n & 2) != 0) {
            bl = true;
        }
        settingComponent.changeValue(object, bl);
    }

    public void closePopups(boolean instantly) {
    }

    public static /* synthetic */ void closePopups$default(SettingComponent settingComponent, boolean bl, int n, Object object) {
        if (object != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: closePopups");
        }
        if ((n & 1) != 0) {
            bl = false;
        }
        settingComponent.closePopups(bl);
    }

    public void setupParentListeners(@NotNull UIComponent parent) {
        Intrinsics.checkNotNullParameter((Object)parent, (String)"parent");
    }

    @Metadata(mv={1, 6, 0}, k=1, xi=48, d1={"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2={"Lxyz/apfelmus/cheeto/client/clickgui/settings/SettingComponent$Companion;", "", "()V", "DOWN_ARROW_PNG", "", "UP_ARROW_PNG", "Cheeto"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

