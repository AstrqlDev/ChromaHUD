/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  gg.essential.elementa.UIComponent
 *  gg.essential.elementa.UIConstraints
 *  gg.essential.elementa.constraints.ChildBasedMaxSizeConstraint
 *  gg.essential.elementa.constraints.ChildBasedSizeConstraint
 *  gg.essential.elementa.constraints.HeightConstraint
 *  gg.essential.elementa.constraints.WidthConstraint
 *  gg.essential.elementa.constraints.animation.AnimatingConstraints
 *  gg.essential.elementa.constraints.animation.AnimationStrategy
 *  gg.essential.elementa.constraints.animation.Animations
 *  gg.essential.elementa.dsl.UtilitiesKt
 *  gg.essential.elementa.events.UIClickEvent
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
import gg.essential.elementa.constraints.ChildBasedMaxSizeConstraint;
import gg.essential.elementa.constraints.ChildBasedSizeConstraint;
import gg.essential.elementa.constraints.HeightConstraint;
import gg.essential.elementa.constraints.WidthConstraint;
import gg.essential.elementa.constraints.animation.AnimatingConstraints;
import gg.essential.elementa.constraints.animation.AnimationStrategy;
import gg.essential.elementa.constraints.animation.Animations;
import gg.essential.elementa.dsl.UtilitiesKt;
import gg.essential.elementa.events.UIClickEvent;
import gg.essential.universal.USound;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import xyz.apfelmus.cheeto.client.clickgui.settings.SettingComponent;
import xyz.apfelmus.cheeto.client.clickgui.settings.Slider;

@Metadata(mv={1, 6, 0}, k=1, xi=48, d1={"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u000bH\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u00020\u0007X\u00a4\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\t\u00a8\u0006\u0012"}, d2={"Lxyz/apfelmus/cheeto/client/clickgui/settings/AbstractSliderComponent;", "Lxyz/apfelmus/cheeto/client/clickgui/settings/SettingComponent;", "()V", "expanded", "", "mouseHeld", "slider", "Lxyz/apfelmus/cheeto/client/clickgui/settings/Slider;", "getSlider", "()Lxyz/apfelmus/cheeto/client/clickgui/settings/Slider;", "incrementBy", "", "inc", "", "setupParentListeners", "parent", "Lgg/essential/elementa/UIComponent;", "sliderInit", "Cheeto"})
public abstract class AbstractSliderComponent
extends SettingComponent {
    private boolean expanded;
    private boolean mouseHeld;

    public AbstractSliderComponent() {
        UIComponent uIComponent;
        UIComponent $this$constrain$iv = (UIComponent)this;
        boolean $i$f$constrain = false;
        UIComponent $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
        boolean bl = false;
        UIConstraints $this$_init__u24lambda_u2d0 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        boolean bl2 = false;
        $this$_init__u24lambda_u2d0.setWidth((WidthConstraint)new ChildBasedSizeConstraint(0.0f, 1, null));
        $this$_init__u24lambda_u2d0.setHeight((HeightConstraint)new ChildBasedMaxSizeConstraint());
    }

    @NotNull
    protected abstract Slider getSlider();

    @Override
    public void setupParentListeners(@NotNull UIComponent parent) {
        Intrinsics.checkNotNullParameter((Object)parent, (String)"parent");
        parent.onMouseEnter((Function1)new Function1<UIComponent, Unit>(this){
            final /* synthetic */ AbstractSliderComponent this$0;
            {
                this.this$0 = $receiver;
                super(1);
            }

            public final void invoke(@NotNull UIComponent $this$onMouseEnter) {
                AnimatingConstraints anim$iv;
                UIComponent uIComponent;
                Intrinsics.checkNotNullParameter((Object)$this$onMouseEnter, (String)"$this$onMouseEnter");
                UIComponent $this$animate$iv = (UIComponent)this.this$0.getSlider();
                boolean $i$f$animate = false;
                UIComponent $this$animate_u24lambda_u2d0$iv = uIComponent = $this$animate$iv;
                boolean bl = false;
                AnimatingConstraints $this$invoke_u24lambda_u2d0 = anim$iv = $this$animate_u24lambda_u2d0$iv.makeAnimation();
                boolean bl2 = false;
                AnimatingConstraints.setWidthAnimation$default((AnimatingConstraints)$this$invoke_u24lambda_u2d0, (AnimationStrategy)((AnimationStrategy)Animations.OUT_EXP), (float)0.25f, (WidthConstraint)((WidthConstraint)UtilitiesKt.pixels$default((Number)100, (boolean)false, (boolean)false, (int)3, null)), (float)0.0f, (int)8, null);
                $this$animate_u24lambda_u2d0$iv.animateTo(anim$iv);
                AbstractSliderComponent.access$setExpanded$p(this.this$0, true);
            }
        }).onMouseLeave((Function1)new Function1<UIComponent, Unit>(this){
            final /* synthetic */ AbstractSliderComponent this$0;
            {
                this.this$0 = $receiver;
                super(1);
            }

            public final void invoke(@NotNull UIComponent $this$onMouseLeave) {
                Intrinsics.checkNotNullParameter((Object)$this$onMouseLeave, (String)"$this$onMouseLeave");
                if (!AbstractSliderComponent.access$getMouseHeld$p(this.this$0)) {
                    AnimatingConstraints anim$iv;
                    UIComponent uIComponent;
                    UIComponent $this$animate$iv = (UIComponent)this.this$0.getSlider();
                    boolean $i$f$animate = false;
                    UIComponent $this$animate_u24lambda_u2d0$iv = uIComponent = $this$animate$iv;
                    boolean bl = false;
                    AnimatingConstraints $this$invoke_u24lambda_u2d0 = anim$iv = $this$animate_u24lambda_u2d0$iv.makeAnimation();
                    boolean bl2 = false;
                    AnimatingConstraints.setWidthAnimation$default((AnimatingConstraints)$this$invoke_u24lambda_u2d0, (AnimationStrategy)((AnimationStrategy)Animations.OUT_EXP), (float)0.25f, (WidthConstraint)((WidthConstraint)UtilitiesKt.pixels$default((Number)60, (boolean)false, (boolean)false, (int)3, null)), (float)0.0f, (int)8, null);
                    $this$animate_u24lambda_u2d0$iv.animateTo(anim$iv);
                    AbstractSliderComponent.access$setExpanded$p(this.this$0, false);
                }
            }
        });
    }

    protected final void sliderInit() {
        UIComponent $this$onLeftClick$iv = (UIComponent)this;
        boolean $i$f$onLeftClick = false;
        $this$onLeftClick$iv.onMouseClick((Function2)new Function2<UIComponent, UIClickEvent, Unit>(this){
            final /* synthetic */ AbstractSliderComponent this$0;
            {
                this.this$0 = abstractSliderComponent;
                super(2);
            }

            public final void invoke(@NotNull UIComponent $this$onMouseClick, @NotNull UIClickEvent it) {
                Intrinsics.checkNotNullParameter((Object)$this$onMouseClick, (String)"$this$onMouseClick");
                Intrinsics.checkNotNullParameter((Object)it, (String)"it");
                if (it.getMouseButton() == 0) {
                    UIClickEvent uIClickEvent = it;
                    UIComponent $this$sliderInit_u24lambda_u2d1 = $this$onMouseClick;
                    boolean bl = false;
                    USound.playButtonPress$default((USound)USound.INSTANCE, (float)0.0f, (int)1, null);
                    AbstractSliderComponent.access$setMouseHeld$p(this.this$0, true);
                }
            }
        });
        this.onMouseRelease((Function1)new Function1<UIComponent, Unit>(this){
            final /* synthetic */ AbstractSliderComponent this$0;
            {
                this.this$0 = $receiver;
                super(1);
            }

            public final void invoke(@NotNull UIComponent $this$onMouseRelease) {
                Intrinsics.checkNotNullParameter((Object)$this$onMouseRelease, (String)"$this$onMouseRelease");
                AbstractSliderComponent.access$setMouseHeld$p(this.this$0, false);
                if (AbstractSliderComponent.access$getExpanded$p(this.this$0) && !this.this$0.getSlider().isHovered()) {
                    AnimatingConstraints anim$iv;
                    UIComponent uIComponent;
                    UIComponent $this$animate$iv = (UIComponent)this.this$0.getSlider();
                    boolean $i$f$animate = false;
                    UIComponent $this$animate_u24lambda_u2d0$iv = uIComponent = $this$animate$iv;
                    boolean bl = false;
                    AnimatingConstraints $this$invoke_u24lambda_u2d0 = anim$iv = $this$animate_u24lambda_u2d0$iv.makeAnimation();
                    boolean bl2 = false;
                    AnimatingConstraints.setWidthAnimation$default((AnimatingConstraints)$this$invoke_u24lambda_u2d0, (AnimationStrategy)((AnimationStrategy)Animations.OUT_EXP), (float)0.25f, (WidthConstraint)((WidthConstraint)UtilitiesKt.pixels$default((Number)60, (boolean)false, (boolean)false, (int)3, null)), (float)0.0f, (int)8, null);
                    $this$animate_u24lambda_u2d0$iv.animateTo(anim$iv);
                    AbstractSliderComponent.access$setExpanded$p(this.this$0, false);
                }
            }
        });
    }

    public final void incrementBy(float inc) {
        Slider.setCurrentPercentage$default(this.getSlider(), this.getSlider().getCurrentPercentage() + inc, false, 2, null);
    }

    public static final /* synthetic */ void access$setExpanded$p(AbstractSliderComponent $this, boolean bl) {
        $this.expanded = bl;
    }

    public static final /* synthetic */ boolean access$getMouseHeld$p(AbstractSliderComponent $this) {
        return $this.mouseHeld;
    }

    public static final /* synthetic */ void access$setMouseHeld$p(AbstractSliderComponent $this, boolean bl) {
        $this.mouseHeld = bl;
    }

    public static final /* synthetic */ boolean access$getExpanded$p(AbstractSliderComponent $this) {
        return $this.expanded;
    }
}

