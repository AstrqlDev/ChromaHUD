/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  gg.essential.elementa.UIComponent
 *  gg.essential.elementa.UIConstraints
 *  gg.essential.elementa.components.UIBlock
 *  gg.essential.elementa.components.UIContainer
 *  gg.essential.elementa.constraints.AspectConstraint
 *  gg.essential.elementa.constraints.CenterConstraint
 *  gg.essential.elementa.constraints.ColorConstraint
 *  gg.essential.elementa.constraints.HeightConstraint
 *  gg.essential.elementa.constraints.RelativeConstraint
 *  gg.essential.elementa.constraints.SuperConstraint
 *  gg.essential.elementa.constraints.WidthConstraint
 *  gg.essential.elementa.constraints.YConstraint
 *  gg.essential.elementa.dsl.BasicConstraintsKt
 *  gg.essential.elementa.dsl.ComponentsKt
 *  gg.essential.elementa.dsl.ConstraintsKt
 *  gg.essential.elementa.dsl.UtilitiesKt
 *  gg.essential.elementa.effects.Effect
 *  gg.essential.elementa.effects.OutlineEffect
 *  gg.essential.elementa.events.UIClickEvent
 *  gg.essential.universal.USound
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.functions.Function4
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.ranges.ClosedFloatingPointRange
 *  kotlin.ranges.RangesKt
 *  org.jetbrains.annotations.NotNull
 */
package xyz.apfelmus.cheeto.client.clickgui.settings;

import gg.essential.elementa.UIComponent;
import gg.essential.elementa.UIConstraints;
import gg.essential.elementa.components.UIBlock;
import gg.essential.elementa.components.UIContainer;
import gg.essential.elementa.constraints.AspectConstraint;
import gg.essential.elementa.constraints.CenterConstraint;
import gg.essential.elementa.constraints.ColorConstraint;
import gg.essential.elementa.constraints.HeightConstraint;
import gg.essential.elementa.constraints.RelativeConstraint;
import gg.essential.elementa.constraints.SuperConstraint;
import gg.essential.elementa.constraints.WidthConstraint;
import gg.essential.elementa.constraints.YConstraint;
import gg.essential.elementa.dsl.BasicConstraintsKt;
import gg.essential.elementa.dsl.ComponentsKt;
import gg.essential.elementa.dsl.ConstraintsKt;
import gg.essential.elementa.dsl.UtilitiesKt;
import gg.essential.elementa.effects.Effect;
import gg.essential.elementa.effects.OutlineEffect;
import gg.essential.elementa.events.UIClickEvent;
import gg.essential.universal.USound;
import java.awt.Color;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import xyz.apfelmus.cheeto.client.clickgui.settings.Slider;
import xyz.apfelmus.cheeto.client.utils.client.ColorUtils;

@Metadata(mv={1, 6, 0}, k=1, xi=48, d1={"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0012\u001a\u00020\u0003J\u001a\u0010\r\u001a\u00020\u000f2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000f0\u000eJ\u0018\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2={"Lxyz/apfelmus/cheeto/client/clickgui/settings/Slider;", "Lgg/essential/elementa/components/UIContainer;", "initialValue", "", "(F)V", "completionBox", "Lgg/essential/elementa/components/UIBlock;", "dragging", "", "grabBox", "getGrabBox", "()Lgg/essential/elementa/components/UIBlock;", "grabOffset", "onValueChange", "Lkotlin/Function1;", "", "outerBox", "percentage", "getCurrentPercentage", "listener", "setCurrentPercentage", "newPercentage", "callListener", "Cheeto"})
public final class Slider
extends UIContainer {
    private float percentage;
    @NotNull
    private Function1<? super Float, Unit> onValueChange;
    private boolean dragging;
    private float grabOffset;
    @NotNull
    private final UIContainer outerBox;
    @NotNull
    private final UIBlock completionBox;
    @NotNull
    private final UIBlock grabBox;

    /*
     * WARNING - void declaration
     */
    public Slider(float initialValue) {
        void $this$grabBox_u24lambda_u2d2;
        UIConstraints $this$completionBox_u24lambda_u2d1;
        UIConstraints $this$outerBox_u24lambda_u2d0;
        UIComponent uIComponent;
        this.percentage = initialValue;
        this.onValueChange = onValueChange.1.INSTANCE;
        Object $this$constrain$iv = (UIComponent)new UIContainer();
        boolean $i$f$constrain = false;
        UIComponent $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
        boolean bl = false;
        UIConstraints uIConstraints = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        Slider slider = this;
        boolean bl2 = false;
        $this$outerBox_u24lambda_u2d0.setX(BasicConstraintsKt.basicXConstraint((Function1)((Function1)new Function1<UIComponent, Float>(this){
            final /* synthetic */ Slider this$0;
            {
                this.this$0 = $receiver;
                super(1);
            }

            @NotNull
            public final Float invoke(@NotNull UIComponent it) {
                Intrinsics.checkNotNullParameter((Object)it, (String)"it");
                return Float.valueOf(this.this$0.getLeft() + 1.0f + this.this$0.getHeight() * 0.75f);
            }
        })));
        $this$outerBox_u24lambda_u2d0.setY((YConstraint)new CenterConstraint());
        $this$outerBox_u24lambda_u2d0.setWidth(BasicConstraintsKt.basicWidthConstraint((Function1)((Function1)new Function1<UIComponent, Float>(this){
            final /* synthetic */ Slider this$0;
            {
                this.this$0 = $receiver;
                super(1);
            }

            @NotNull
            public final Float invoke(@NotNull UIComponent it) {
                Intrinsics.checkNotNullParameter((Object)it, (String)"it");
                return Float.valueOf(this.this$0.getWidth() - 2.0f - this.this$0.getHeight() * 1.5f);
            }
        })));
        $this$outerBox_u24lambda_u2d0.setHeight((HeightConstraint)new RelativeConstraint(0.5f));
        UIComponent uIComponent2 = ComponentsKt.childOf((UIComponent)uIComponent, (UIComponent)((UIComponent)this));
        $this$constrain$iv = ColorUtils.C_BORDER;
        Intrinsics.checkNotNullExpressionValue((Object)$this$constrain$iv, (String)"C_BORDER");
        slider.outerBox = (UIContainer)ComponentsKt.effect((UIComponent)uIComponent2, (Effect)((Effect)new OutlineEffect((Color)$this$constrain$iv, 1.0f, false, false, null, 28, null)));
        $this$constrain$iv = (UIComponent)new UIBlock(null, 1, null);
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
        bl = false;
        $this$outerBox_u24lambda_u2d0 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        slider = this;
        boolean bl3 = false;
        $this$completionBox_u24lambda_u2d1.setWidth((WidthConstraint)new RelativeConstraint(this.percentage));
        $this$completionBox_u24lambda_u2d1.setHeight((HeightConstraint)new RelativeConstraint(1.0f));
        Color color = ColorUtils.SELECTED;
        Intrinsics.checkNotNullExpressionValue((Object)color, (String)"SELECTED");
        $this$completionBox_u24lambda_u2d1.setColor((ColorConstraint)UtilitiesKt.toConstraint((Color)color));
        slider.completionBox = (UIBlock)ComponentsKt.childOf((UIComponent)uIComponent, (UIComponent)((UIComponent)this.outerBox));
        $this$constrain$iv = (UIComponent)new UIBlock(null, 1, null);
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
        bl = false;
        $this$completionBox_u24lambda_u2d1 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        slider = this;
        boolean bl4 = false;
        $this$grabBox_u24lambda_u2d2.setX(BasicConstraintsKt.basicXConstraint((Function1)((Function1)new Function1<UIComponent, Float>(this){
            final /* synthetic */ Slider this$0;
            {
                this.this$0 = $receiver;
                super(1);
            }

            @NotNull
            public final Float invoke(@NotNull UIComponent it) {
                Intrinsics.checkNotNullParameter((Object)it, (String)"it");
                return Float.valueOf(Slider.access$getCompletionBox$p(this.this$0).getRight() - it.getWidth() / 2.0f);
            }
        })));
        $this$grabBox_u24lambda_u2d2.setY((YConstraint)ConstraintsKt.boundTo((SuperConstraint)((SuperConstraint)new CenterConstraint()), (UIComponent)((UIComponent)this.outerBox)));
        $this$grabBox_u24lambda_u2d2.setWidth((WidthConstraint)new AspectConstraint(1.0f));
        $this$grabBox_u24lambda_u2d2.setHeight((HeightConstraint)UtilitiesKt.percent((Number)100));
        color = ColorUtils.SELECTED;
        Intrinsics.checkNotNullExpressionValue((Object)color, (String)"SELECTED");
        $this$grabBox_u24lambda_u2d2.setColor((ColorConstraint)UtilitiesKt.toConstraint((Color)color));
        UIComponent uIComponent3 = ComponentsKt.childOf((UIComponent)uIComponent, (UIComponent)((UIComponent)this));
        $this$constrain$iv = ColorUtils.M_BORDER;
        Intrinsics.checkNotNullExpressionValue((Object)$this$constrain$iv, (String)"M_BORDER");
        slider.grabBox = (UIBlock)ComponentsKt.effect((UIComponent)uIComponent3, (Effect)((Effect)new OutlineEffect((Color)$this$constrain$iv, 1.0f, false, false, null, 28, null)));
        UIComponent $this$onLeftClick$iv = (UIComponent)this.grabBox;
        boolean $i$f$onLeftClick = false;
        $this$onLeftClick$iv.onMouseClick((Function2)new Function2<UIComponent, UIClickEvent, Unit>(this){
            final /* synthetic */ Slider this$0;
            {
                this.this$0 = slider;
                super(2);
            }

            /*
             * WARNING - void declaration
             */
            public final void invoke(@NotNull UIComponent $this$onMouseClick, @NotNull UIClickEvent it) {
                Intrinsics.checkNotNullParameter((Object)$this$onMouseClick, (String)"$this$onMouseClick");
                Intrinsics.checkNotNullParameter((Object)it, (String)"it");
                if (it.getMouseButton() == 0) {
                    void event;
                    UIClickEvent uIClickEvent = it;
                    UIComponent $this$_init__u24lambda_u2d3 = $this$onMouseClick;
                    boolean bl = false;
                    USound.playButtonPress$default((USound)USound.INSTANCE, (float)0.0f, (int)1, null);
                    Slider.access$setDragging$p(this.this$0, true);
                    Slider.access$setGrabOffset$p(this.this$0, event.getRelativeX() - this.this$0.getGrabBox().getWidth() / (float)2);
                    event.stopPropagation();
                }
            }
        }).onMouseRelease((Function1)new Function1<UIComponent, Unit>(){

            public final void invoke(@NotNull UIComponent $this$onMouseRelease) {
                Intrinsics.checkNotNullParameter((Object)$this$onMouseRelease, (String)"$this$onMouseRelease");
                dragging = false;
                grabOffset = 0.0f;
            }
        }).onMouseDrag((Function4)new Function4<UIComponent, Float, Float, Integer, Unit>(){

            public final void invoke(@NotNull UIComponent $this$onMouseDrag, float mouseX, float $noName_1, int $noName_2) {
                Intrinsics.checkNotNullParameter((Object)$this$onMouseDrag, (String)"$this$onMouseDrag");
                if (!dragging) {
                    return;
                }
                float clamped = ((Number)((Object)RangesKt.coerceIn((Comparable)Float.valueOf(mouseX + this.getGrabBox().getLeft() - grabOffset), (ClosedFloatingPointRange)RangesKt.rangeTo((float)outerBox.getLeft(), (float)outerBox.getRight())))).floatValue();
                float percentage = (clamped - outerBox.getLeft()) / outerBox.getWidth();
                Slider.setCurrentPercentage$default(this, percentage, false, 2, null);
            }
        });
        $this$onLeftClick$iv = (UIComponent)this.outerBox;
        $i$f$onLeftClick = false;
        $this$onLeftClick$iv.onMouseClick((Function2)new Function2<UIComponent, UIClickEvent, Unit>(this){
            final /* synthetic */ Slider this$0;
            {
                this.this$0 = slider;
                super(2);
            }

            /*
             * WARNING - void declaration
             */
            public final void invoke(@NotNull UIComponent $this$onMouseClick, @NotNull UIClickEvent it) {
                Intrinsics.checkNotNullParameter((Object)$this$onMouseClick, (String)"$this$onMouseClick");
                Intrinsics.checkNotNullParameter((Object)it, (String)"it");
                if (it.getMouseButton() == 0) {
                    void event;
                    UIClickEvent uIClickEvent = it;
                    UIComponent $this$_init__u24lambda_u2d4 = $this$onMouseClick;
                    boolean bl = false;
                    USound.playButtonPress$default((USound)USound.INSTANCE, (float)0.0f, (int)1, null);
                    float percentage = event.getRelativeX() / Slider.access$getOuterBox$p(this.this$0).getWidth();
                    Slider.setCurrentPercentage$default(this.this$0, percentage, false, 2, null);
                    Slider.access$setDragging$p(this.this$0, true);
                }
            }
        });
    }

    @NotNull
    public final UIBlock getGrabBox() {
        return this.grabBox;
    }

    public final float getCurrentPercentage() {
        return this.percentage;
    }

    public final void setCurrentPercentage(float newPercentage, boolean callListener) {
        this.percentage = ((Number)((Object)RangesKt.coerceIn((Comparable)Float.valueOf(newPercentage), (ClosedFloatingPointRange)RangesKt.rangeTo((float)0.0f, (float)1.0f)))).floatValue();
        this.completionBox.setWidth((WidthConstraint)new RelativeConstraint(this.percentage));
        if (callListener) {
            this.onValueChange.invoke((Object)Float.valueOf(this.percentage));
        }
    }

    public static /* synthetic */ void setCurrentPercentage$default(Slider slider, float f, boolean bl, int n, Object object) {
        if ((n & 2) != 0) {
            bl = true;
        }
        slider.setCurrentPercentage(f, bl);
    }

    public final void onValueChange(@NotNull Function1<? super Float, Unit> listener2) {
        Intrinsics.checkNotNullParameter(listener2, (String)"listener");
        this.onValueChange = listener2;
    }

    public static final /* synthetic */ UIBlock access$getCompletionBox$p(Slider $this) {
        return $this.completionBox;
    }
}

