/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  gg.essential.elementa.UIComponent
 *  gg.essential.elementa.UIConstraints
 *  gg.essential.elementa.components.UIBlock
 *  gg.essential.elementa.constraints.ColorConstraint
 *  gg.essential.elementa.constraints.HeightConstraint
 *  gg.essential.elementa.constraints.PixelConstraint
 *  gg.essential.elementa.constraints.RelativeConstraint
 *  gg.essential.elementa.constraints.WidthConstraint
 *  gg.essential.elementa.constraints.XConstraint
 *  gg.essential.elementa.constraints.animation.AnimatingConstraints
 *  gg.essential.elementa.constraints.animation.AnimationStrategy
 *  gg.essential.elementa.constraints.animation.Animations
 *  gg.essential.elementa.dsl.ComponentsKt
 *  gg.essential.elementa.dsl.UtilitiesKt
 *  gg.essential.elementa.effects.Effect
 *  gg.essential.elementa.effects.OutlineEffect
 *  gg.essential.elementa.events.UIClickEvent
 *  gg.essential.elementa.state.BasicState
 *  gg.essential.elementa.state.ExtensionsKt
 *  gg.essential.elementa.state.State
 *  gg.essential.universal.USound
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.internal.Intrinsics
 *  org.jetbrains.annotations.NotNull
 */
package xyz.apfelmus.cheeto.client.clickgui.settings;

import gg.essential.elementa.UIComponent;
import gg.essential.elementa.UIConstraints;
import gg.essential.elementa.components.UIBlock;
import gg.essential.elementa.constraints.ColorConstraint;
import gg.essential.elementa.constraints.HeightConstraint;
import gg.essential.elementa.constraints.PixelConstraint;
import gg.essential.elementa.constraints.RelativeConstraint;
import gg.essential.elementa.constraints.WidthConstraint;
import gg.essential.elementa.constraints.XConstraint;
import gg.essential.elementa.constraints.animation.AnimatingConstraints;
import gg.essential.elementa.constraints.animation.AnimationStrategy;
import gg.essential.elementa.constraints.animation.Animations;
import gg.essential.elementa.dsl.ComponentsKt;
import gg.essential.elementa.dsl.UtilitiesKt;
import gg.essential.elementa.effects.Effect;
import gg.essential.elementa.effects.OutlineEffect;
import gg.essential.elementa.events.UIClickEvent;
import gg.essential.elementa.state.BasicState;
import gg.essential.elementa.state.ExtensionsKt;
import gg.essential.elementa.state.State;
import gg.essential.universal.USound;
import java.awt.Color;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import xyz.apfelmus.cheeto.client.clickgui.settings.SettingComponent;
import xyz.apfelmus.cheeto.client.clickgui.settings.SwitchComponent;
import xyz.apfelmus.cheeto.client.utils.client.ColorUtils;

@Metadata(mv={1, 6, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0002J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0002R\u001a\u0010\u0005\u001a\u00020\u0003X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0004R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2={"Lxyz/apfelmus/cheeto/client/clickgui/settings/SwitchComponent;", "Lxyz/apfelmus/cheeto/client/clickgui/settings/SettingComponent;", "initialState", "", "(Z)V", "enabled", "getEnabled$Cheeto", "()Z", "setEnabled$Cheeto", "switchBox", "Lgg/essential/elementa/components/UIBlock;", "getOutlineEffect", "Lgg/essential/elementa/effects/OutlineEffect;", "getSwitchColor", "Lgg/essential/elementa/state/BasicState;", "Ljava/awt/Color;", "getSwitchPosition", "Lgg/essential/elementa/constraints/PixelConstraint;", "Cheeto"})
public final class SwitchComponent
extends SettingComponent {
    private boolean enabled;
    @NotNull
    private final UIBlock switchBox;

    /*
     * WARNING - void declaration
     */
    public SwitchComponent(boolean initialState) {
        void $this$switchBox_u24lambda_u2d0;
        UIComponent uIComponent;
        this.enabled = initialState;
        UIComponent $this$constrain$iv = (UIComponent)new UIBlock((State)this.getSwitchColor());
        boolean $i$f$constrain = false;
        UIComponent $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
        boolean bl = false;
        UIConstraints uIConstraints = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        SwitchComponent switchComponent = this;
        boolean bl2 = false;
        $this$switchBox_u24lambda_u2d0.setX((XConstraint)this.getSwitchPosition());
        $this$switchBox_u24lambda_u2d0.setWidth((WidthConstraint)new RelativeConstraint(0.5f));
        $this$switchBox_u24lambda_u2d0.setHeight((HeightConstraint)new RelativeConstraint(1.0f));
        switchComponent.switchBox = (UIBlock)ComponentsKt.childOf((UIComponent)uIComponent, (UIComponent)((UIComponent)this));
        $this$constrain$iv = (UIComponent)this;
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
        bl = false;
        UIConstraints $this$_init__u24lambda_u2d1 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        boolean bl3 = false;
        $this$_init__u24lambda_u2d1.setWidth((WidthConstraint)UtilitiesKt.pixels$default((Number)20, (boolean)false, (boolean)false, (int)3, null));
        $this$_init__u24lambda_u2d1.setHeight((HeightConstraint)UtilitiesKt.pixels$default((Number)10, (boolean)false, (boolean)false, (int)3, null));
        ComponentsKt.effect((UIComponent)((UIComponent)this), (Effect)((Effect)this.getOutlineEffect()));
        UIComponent $this$onLeftClick$iv = (UIComponent)this;
        boolean $i$f$onLeftClick = false;
        $this$onLeftClick$iv.onMouseClick((Function2)new Function2<UIComponent, UIClickEvent, Unit>(this){
            final /* synthetic */ SwitchComponent this$0;
            {
                this.this$0 = switchComponent;
                super(2);
            }

            public final void invoke(@NotNull UIComponent $this$onMouseClick, @NotNull UIClickEvent it) {
                Intrinsics.checkNotNullParameter((Object)$this$onMouseClick, (String)"$this$onMouseClick");
                Intrinsics.checkNotNullParameter((Object)it, (String)"it");
                if (it.getMouseButton() == 0) {
                    AnimatingConstraints anim$iv;
                    UIComponent uIComponent;
                    UIClickEvent uIClickEvent = it;
                    UIComponent $this$_init__u24lambda_u2d3 = $this$onMouseClick;
                    boolean bl = false;
                    USound.playButtonPress$default((USound)USound.INSTANCE, (float)0.0f, (int)1, null);
                    this.this$0.setEnabled$Cheeto(!this.this$0.getEnabled$Cheeto());
                    SettingComponent.changeValue$default(this.this$0, this.this$0.getEnabled$Cheeto(), false, 2, null);
                    UIComponent this_$iv = $this$_init__u24lambda_u2d3;
                    boolean $i$f$removeEffect = false;
                    this_$iv.getEffects().removeIf(new Predicate(){

                        public final boolean test(@NotNull Effect it) {
                            Intrinsics.checkNotNullParameter((Object)it, (String)"it");
                            return it instanceof OutlineEffect;
                        }
                    });
                    $this$_init__u24lambda_u2d3.enableEffect((Effect)SwitchComponent.access$getOutlineEffect(this.this$0));
                    SwitchComponent.access$getSwitchBox$p(this.this$0).setColor((ColorConstraint)ExtensionsKt.toConstraint((State)((State)($this$_init__u24lambda_u2d3.isHovered() ? (BasicState)SwitchComponent.access$getSwitchColor(this.this$0).map((Function1)2.1.INSTANCE) : SwitchComponent.access$getSwitchColor(this.this$0)))));
                    UIComponent $this$animate$iv = (UIComponent)SwitchComponent.access$getSwitchBox$p(this.this$0);
                    boolean $i$f$animate = false;
                    UIComponent $this$animate_u24lambda_u2d0$iv = uIComponent = $this$animate$iv;
                    boolean bl2 = false;
                    AnimatingConstraints $this$lambda_u2d3_u24lambda_u2d2 = anim$iv = $this$animate_u24lambda_u2d0$iv.makeAnimation();
                    boolean bl3 = false;
                    AnimatingConstraints.setXAnimation$default((AnimatingConstraints)$this$lambda_u2d3_u24lambda_u2d2, (AnimationStrategy)((AnimationStrategy)Animations.OUT_EXP), (float)0.5f, (XConstraint)((XConstraint)SwitchComponent.access$getSwitchPosition(this.this$0)), (float)0.0f, (int)8, null);
                    $this$animate_u24lambda_u2d0$iv.animateTo(anim$iv);
                }
            }
        });
        this.onMouseEnter((Function1)new Function1<UIComponent, Unit>(){

            /*
             * WARNING - void declaration
             */
            public final void invoke(@NotNull UIComponent $this$onMouseEnter) {
                AnimatingConstraints anim$iv;
                void $this$animate$iv;
                void var5_5;
                Intrinsics.checkNotNullParameter((Object)$this$onMouseEnter, (String)"$this$onMouseEnter");
                UIComponent uIComponent = (UIComponent)switchBox;
                SwitchComponent switchComponent = this;
                boolean $i$f$animate = false;
                void $this$animate_u24lambda_u2d0$iv = var5_5 = $this$animate$iv;
                boolean bl = false;
                AnimatingConstraints $this$invoke_u24lambda_u2d0 = anim$iv = $this$animate_u24lambda_u2d0$iv.makeAnimation();
                boolean bl2 = false;
                AnimatingConstraints.setColorAnimation$default((AnimatingConstraints)$this$invoke_u24lambda_u2d0, (AnimationStrategy)((AnimationStrategy)Animations.OUT_EXP), (float)0.25f, (ColorConstraint)((ColorConstraint)ExtensionsKt.toConstraint((State)((State)switchComponent.getSwitchColor().map((Function1)1.1.INSTANCE)))), (float)0.0f, (int)8, null);
                $this$animate_u24lambda_u2d0$iv.animateTo(anim$iv);
            }
        });
        this.onMouseLeave((Function1)new Function1<UIComponent, Unit>(){

            /*
             * WARNING - void declaration
             */
            public final void invoke(@NotNull UIComponent $this$onMouseLeave) {
                AnimatingConstraints anim$iv;
                void $this$animate$iv;
                void var5_5;
                Intrinsics.checkNotNullParameter((Object)$this$onMouseLeave, (String)"$this$onMouseLeave");
                UIComponent uIComponent = (UIComponent)switchBox;
                SwitchComponent switchComponent = this;
                boolean $i$f$animate = false;
                void $this$animate_u24lambda_u2d0$iv = var5_5 = $this$animate$iv;
                boolean bl = false;
                AnimatingConstraints $this$invoke_u24lambda_u2d0 = anim$iv = $this$animate_u24lambda_u2d0$iv.makeAnimation();
                boolean bl2 = false;
                AnimatingConstraints.setColorAnimation$default((AnimatingConstraints)$this$invoke_u24lambda_u2d0, (AnimationStrategy)((AnimationStrategy)Animations.OUT_EXP), (float)0.25f, (ColorConstraint)((ColorConstraint)ExtensionsKt.toConstraint((State)((State)switchComponent.getSwitchColor()))), (float)0.0f, (int)8, null);
                $this$animate_u24lambda_u2d0$iv.animateTo(anim$iv);
            }
        });
    }

    public final boolean getEnabled$Cheeto() {
        return this.enabled;
    }

    public final void setEnabled$Cheeto(boolean bl) {
        this.enabled = bl;
    }

    private final OutlineEffect getOutlineEffect() {
        return new OutlineEffect((Color)this.getSwitchColor().get(), 1.0f, false, false, null, 28, null).bindColor((State)this.getSwitchColor());
    }

    private final BasicState<Color> getSwitchColor() {
        BasicState basicState;
        if (this.enabled) {
            Color color = ColorUtils.SELECTED;
            Intrinsics.checkNotNullExpressionValue((Object)color, (String)"SELECTED");
            BasicState basicState2 = new BasicState((Object)color);
            basicState = basicState2;
        } else {
            Color color = ColorUtils.SELECT;
            Intrinsics.checkNotNullExpressionValue((Object)color, (String)"SELECT");
            BasicState basicState3 = new BasicState((Object)color);
            basicState = basicState3;
        }
        return basicState;
    }

    private final PixelConstraint getSwitchPosition() {
        return this.enabled ? UtilitiesKt.pixels$default((Number)0, (boolean)true, (boolean)false, (int)2, null) : UtilitiesKt.pixels$default((Number)0, (boolean)false, (boolean)false, (int)3, null);
    }

    public static final /* synthetic */ PixelConstraint access$getSwitchPosition(SwitchComponent $this) {
        return $this.getSwitchPosition();
    }

    public static final /* synthetic */ OutlineEffect access$getOutlineEffect(SwitchComponent $this) {
        return $this.getOutlineEffect();
    }
}

