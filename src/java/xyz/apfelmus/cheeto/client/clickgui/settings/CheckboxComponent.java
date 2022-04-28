/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  gg.essential.elementa.UIComponent
 *  gg.essential.elementa.UIConstraints
 *  gg.essential.elementa.components.UIImage
 *  gg.essential.elementa.constraints.AspectConstraint
 *  gg.essential.elementa.constraints.CenterConstraint
 *  gg.essential.elementa.constraints.ColorConstraint
 *  gg.essential.elementa.constraints.HeightConstraint
 *  gg.essential.elementa.constraints.WidthConstraint
 *  gg.essential.elementa.constraints.XConstraint
 *  gg.essential.elementa.constraints.YConstraint
 *  gg.essential.elementa.dsl.ComponentsKt
 *  gg.essential.elementa.dsl.UtilitiesKt
 *  gg.essential.elementa.effects.Effect
 *  gg.essential.elementa.effects.OutlineEffect
 *  gg.essential.elementa.events.UIClickEvent
 *  gg.essential.universal.USound
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package xyz.apfelmus.cheeto.client.clickgui.settings;

import gg.essential.elementa.UIComponent;
import gg.essential.elementa.UIConstraints;
import gg.essential.elementa.components.UIImage;
import gg.essential.elementa.constraints.AspectConstraint;
import gg.essential.elementa.constraints.CenterConstraint;
import gg.essential.elementa.constraints.ColorConstraint;
import gg.essential.elementa.constraints.HeightConstraint;
import gg.essential.elementa.constraints.WidthConstraint;
import gg.essential.elementa.constraints.XConstraint;
import gg.essential.elementa.constraints.YConstraint;
import gg.essential.elementa.dsl.ComponentsKt;
import gg.essential.elementa.dsl.UtilitiesKt;
import gg.essential.elementa.effects.Effect;
import gg.essential.elementa.effects.OutlineEffect;
import gg.essential.elementa.events.UIClickEvent;
import gg.essential.universal.USound;
import java.awt.Color;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import xyz.apfelmus.cheeto.client.clickgui.settings.SettingComponent;
import xyz.apfelmus.cheeto.client.utils.client.ColorUtils;

@Metadata(mv={1, 6, 0}, k=1, xi=48, d1={"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u000e\u001a\n \u0010*\u0004\u0018\u00010\u000f0\u000fH\u0002R$\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0004R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2={"Lxyz/apfelmus/cheeto/client/clickgui/settings/CheckboxComponent;", "Lxyz/apfelmus/cheeto/client/clickgui/settings/SettingComponent;", "initialValue", "", "(Z)V", "value", "checked", "getChecked", "()Z", "setChecked", "checkmark", "Lgg/essential/elementa/components/UIImage;", "getOutlineEffect", "Lgg/essential/elementa/effects/OutlineEffect;", "getSettingColor", "Ljava/awt/Color;", "kotlin.jvm.PlatformType", "Cheeto"})
public final class CheckboxComponent
extends SettingComponent {
    private boolean checked;
    @NotNull
    private final UIImage checkmark;

    /*
     * WARNING - void declaration
     */
    public CheckboxComponent(boolean initialValue) {
        void $this$checkmark_u24lambda_u2d0;
        UIComponent uIComponent;
        this.checked = initialValue;
        UIComponent $this$constrain$iv = (UIComponent)UIImage.Companion.ofResourceCached("/vigilance/check.png");
        boolean $i$f$constrain = false;
        UIComponent $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
        boolean bl = false;
        UIConstraints uIConstraints = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        CheckboxComponent checkboxComponent = this;
        boolean bl2 = false;
        $this$checkmark_u24lambda_u2d0.setX((XConstraint)new CenterConstraint());
        $this$checkmark_u24lambda_u2d0.setY((YConstraint)new CenterConstraint());
        $this$checkmark_u24lambda_u2d0.setWidth((WidthConstraint)UtilitiesKt.pixels$default((Number)16, (boolean)false, (boolean)false, (int)3, null));
        $this$checkmark_u24lambda_u2d0.setHeight((HeightConstraint)UtilitiesKt.pixels$default((Number)12, (boolean)false, (boolean)false, (int)3, null));
        Color color = ColorUtils.SELECTED;
        Intrinsics.checkNotNullExpressionValue((Object)color, (String)"SELECTED");
        $this$checkmark_u24lambda_u2d0.setColor((ColorConstraint)UtilitiesKt.toConstraint((Color)color));
        checkboxComponent.checkmark = (UIImage)ComponentsKt.childOf((UIComponent)uIComponent, (UIComponent)((UIComponent)this));
        $this$constrain$iv = (UIComponent)this;
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
        bl = false;
        UIConstraints $this$_init__u24lambda_u2d1 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        boolean bl3 = false;
        $this$_init__u24lambda_u2d1.setWidth((WidthConstraint)UtilitiesKt.pixels$default((Number)20, (boolean)false, (boolean)false, (int)3, null));
        $this$_init__u24lambda_u2d1.setHeight((HeightConstraint)new AspectConstraint(0.0f, 1, null));
        ComponentsKt.effect((UIComponent)((UIComponent)this), (Effect)((Effect)this.getOutlineEffect()));
        if (!this.checked) {
            this.checkmark.hide(true);
        }
        UIComponent $this$onLeftClick$iv = (UIComponent)this;
        boolean $i$f$onLeftClick = false;
        $this$onLeftClick$iv.onMouseClick((Function2)new Function2<UIComponent, UIClickEvent, Unit>(this){
            final /* synthetic */ CheckboxComponent this$0;
            {
                this.this$0 = checkboxComponent;
                super(2);
            }

            public final void invoke(@NotNull UIComponent $this$onMouseClick, @NotNull UIClickEvent it) {
                Intrinsics.checkNotNullParameter((Object)$this$onMouseClick, (String)"$this$onMouseClick");
                Intrinsics.checkNotNullParameter((Object)it, (String)"it");
                if (it.getMouseButton() == 0) {
                    UIClickEvent uIClickEvent = it;
                    UIComponent $this$_init__u24lambda_u2d2 = $this$onMouseClick;
                    boolean bl = false;
                    USound.playButtonPress$default((USound)USound.INSTANCE, (float)0.0f, (int)1, null);
                    this.this$0.setChecked(!this.this$0.getChecked());
                    UIComponent this_$iv = $this$_init__u24lambda_u2d2;
                    boolean $i$f$removeEffect = false;
                    this_$iv.getEffects().removeIf(new Predicate(){

                        public final boolean test(@NotNull Effect it) {
                            Intrinsics.checkNotNullParameter((Object)it, (String)"it");
                            return it instanceof OutlineEffect;
                        }
                    });
                    ComponentsKt.effect((UIComponent)$this$_init__u24lambda_u2d2, (Effect)((Effect)CheckboxComponent.access$getOutlineEffect(this.this$0)));
                    if (this.this$0.getChecked()) {
                        UIComponent.unhide$default((UIComponent)((UIComponent)CheckboxComponent.access$getCheckmark$p(this.this$0)), (boolean)false, (int)1, null);
                    } else {
                        UIComponent.hide$default((UIComponent)((UIComponent)CheckboxComponent.access$getCheckmark$p(this.this$0)), (boolean)false, (int)1, null);
                    }
                }
            }
        });
    }

    public final boolean getChecked() {
        return this.checked;
    }

    public final void setChecked(boolean value) {
        SettingComponent.changeValue$default(this, value, false, 2, null);
        this.checked = value;
    }

    private final OutlineEffect getOutlineEffect() {
        Color color = this.getSettingColor();
        Intrinsics.checkNotNullExpressionValue((Object)color, (String)"getSettingColor()");
        return new OutlineEffect(color, 1.0f, false, false, null, 28, null);
    }

    private final Color getSettingColor() {
        return this.checked ? ColorUtils.SELECTED : ColorUtils.SELECT;
    }

    public static final /* synthetic */ OutlineEffect access$getOutlineEffect(CheckboxComponent $this) {
        return $this.getOutlineEffect();
    }

    public static final /* synthetic */ UIImage access$getCheckmark$p(CheckboxComponent $this) {
        return $this.checkmark;
    }
}

