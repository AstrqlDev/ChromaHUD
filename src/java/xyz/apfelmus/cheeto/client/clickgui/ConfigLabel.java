/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  gg.essential.elementa.UIComponent
 *  gg.essential.elementa.UIConstraints
 *  gg.essential.elementa.components.UIContainer
 *  gg.essential.elementa.components.UIText
 *  gg.essential.elementa.constraints.CenterConstraint
 *  gg.essential.elementa.constraints.ChildBasedMaxSizeConstraint
 *  gg.essential.elementa.constraints.ColorConstraint
 *  gg.essential.elementa.constraints.HeightConstraint
 *  gg.essential.elementa.constraints.SiblingConstraint
 *  gg.essential.elementa.constraints.WidthConstraint
 *  gg.essential.elementa.constraints.XConstraint
 *  gg.essential.elementa.constraints.YConstraint
 *  gg.essential.elementa.constraints.animation.AnimatingConstraints
 *  gg.essential.elementa.constraints.animation.AnimationStrategy
 *  gg.essential.elementa.constraints.animation.Animations
 *  gg.essential.elementa.dsl.ComponentsKt
 *  gg.essential.elementa.dsl.UtilitiesKt
 *  gg.essential.elementa.events.UIClickEvent
 *  gg.essential.elementa.font.DefaultFonts
 *  gg.essential.universal.USound
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.PropertyReference1
 *  kotlin.jvm.internal.PropertyReference1Impl
 *  kotlin.jvm.internal.Reflection
 *  kotlin.properties.ReadWriteProperty
 *  kotlin.reflect.KProperty
 *  org.jetbrains.annotations.NotNull
 */
package xyz.apfelmus.cheeto.client.clickgui;

import gg.essential.elementa.UIComponent;
import gg.essential.elementa.UIConstraints;
import gg.essential.elementa.components.UIContainer;
import gg.essential.elementa.components.UIText;
import gg.essential.elementa.constraints.CenterConstraint;
import gg.essential.elementa.constraints.ChildBasedMaxSizeConstraint;
import gg.essential.elementa.constraints.ColorConstraint;
import gg.essential.elementa.constraints.HeightConstraint;
import gg.essential.elementa.constraints.SiblingConstraint;
import gg.essential.elementa.constraints.WidthConstraint;
import gg.essential.elementa.constraints.XConstraint;
import gg.essential.elementa.constraints.YConstraint;
import gg.essential.elementa.constraints.animation.AnimatingConstraints;
import gg.essential.elementa.constraints.animation.AnimationStrategy;
import gg.essential.elementa.constraints.animation.Animations;
import gg.essential.elementa.dsl.ComponentsKt;
import gg.essential.elementa.dsl.UtilitiesKt;
import gg.essential.elementa.events.UIClickEvent;
import gg.essential.elementa.font.DefaultFonts;
import gg.essential.universal.USound;
import java.awt.Color;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import xyz.apfelmus.cheeto.client.clickgui.ConfigGUI;
import xyz.apfelmus.cheeto.client.utils.client.ColorUtils;

@Metadata(mv={1, 6, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\t\"\u0004\b\n\u0010\u000bR\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0015"}, d2={"Lxyz/apfelmus/cheeto/client/clickgui/ConfigLabel;", "Lgg/essential/elementa/components/UIContainer;", "gui", "Lxyz/apfelmus/cheeto/client/clickgui/ConfigGUI;", "config", "", "(Lxyz/apfelmus/cheeto/client/clickgui/ConfigGUI;Ljava/lang/String;)V", "isSelected", "", "()Z", "setSelected", "(Z)V", "text", "Lgg/essential/elementa/components/UIText;", "getText", "()Lgg/essential/elementa/components/UIText;", "text$delegate", "Lkotlin/properties/ReadWriteProperty;", "deselect", "", "select", "Cheeto"})
public final class ConfigLabel
extends UIContainer {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    @NotNull
    private final ConfigGUI gui;
    @NotNull
    private final String config;
    @NotNull
    private final ReadWriteProperty text$delegate;
    private boolean isSelected;

    /*
     * WARNING - void declaration
     */
    public ConfigLabel(@NotNull ConfigGUI gui, @NotNull String config) {
        void $this$text_delegate_u24lambda_u2d0;
        UIComponent uIComponent;
        Intrinsics.checkNotNullParameter((Object)((Object)gui), (String)"gui");
        Intrinsics.checkNotNullParameter((Object)config, (String)"config");
        this.gui = gui;
        this.config = config;
        UIComponent $this$constrain$iv = (UIComponent)new UIText(this.config, false, null, 6, null);
        boolean $i$f$constrain = false;
        UIComponent $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
        boolean bl = false;
        UIConstraints uIConstraints = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        ConfigLabel configLabel = this;
        boolean bl2 = false;
        $this$text_delegate_u24lambda_u2d0.setX((XConstraint)new CenterConstraint());
        $this$text_delegate_u24lambda_u2d0.setY((YConstraint)new CenterConstraint());
        $this$text_delegate_u24lambda_u2d0.setTextScale((HeightConstraint)UtilitiesKt.pixels$default((Number)1, (boolean)false, (boolean)false, (int)3, null));
        Color color = ColorUtils.LABEL;
        Intrinsics.checkNotNullExpressionValue((Object)color, (String)"LABEL");
        $this$text_delegate_u24lambda_u2d0.setColor((ColorConstraint)UtilitiesKt.toConstraint((Color)color));
        $this$text_delegate_u24lambda_u2d0.setFontProvider(DefaultFonts.getVANILLA_FONT_RENDERER());
        configLabel.text$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.childOf((UIComponent)uIComponent, (UIComponent)((UIComponent)this)), (Object)((Object)this), $$delegatedProperties[0]);
        $this$constrain$iv = (UIComponent)this;
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
        bl = false;
        UIConstraints $this$_init__u24lambda_u2d1 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        boolean bl3 = false;
        $this$_init__u24lambda_u2d1.setX((XConstraint)UtilitiesKt.pixels$default((Number)0, (boolean)false, (boolean)false, (int)3, null));
        $this$_init__u24lambda_u2d1.setY((YConstraint)new SiblingConstraint(0.0f, false, 3, null));
        $this$_init__u24lambda_u2d1.setWidth((WidthConstraint)new ChildBasedMaxSizeConstraint());
        $this$_init__u24lambda_u2d1.setHeight((HeightConstraint)UtilitiesKt.pixels$default((Number)20, (boolean)false, (boolean)false, (int)3, null));
        UIComponent $this$onLeftClick$iv = (UIComponent)this;
        boolean $i$f$onLeftClick = false;
        $this$onLeftClick$iv.onMouseClick((Function2)new Function2<UIComponent, UIClickEvent, Unit>(this){
            final /* synthetic */ ConfigLabel this$0;
            {
                this.this$0 = configLabel;
                super(2);
            }

            public final void invoke(@NotNull UIComponent $this$onMouseClick, @NotNull UIClickEvent it) {
                Intrinsics.checkNotNullParameter((Object)$this$onMouseClick, (String)"$this$onMouseClick");
                Intrinsics.checkNotNullParameter((Object)it, (String)"it");
                if (it.getMouseButton() == 0) {
                    UIClickEvent uIClickEvent = it;
                    UIComponent $this$_init__u24lambda_u2d2 = $this$onMouseClick;
                    boolean bl = false;
                    if (!this.this$0.isSelected()) {
                        USound.playButtonPress$default((USound)USound.INSTANCE, (float)0.0f, (int)1, null);
                        this.this$0.select();
                    }
                }
            }
        });
        this.onMouseEnter((Function1)new Function1<UIComponent, Unit>(){

            public final void invoke(@NotNull UIComponent $this$onMouseEnter) {
                Intrinsics.checkNotNullParameter((Object)$this$onMouseEnter, (String)"$this$onMouseEnter");
                if (!this.isSelected()) {
                    AnimatingConstraints anim$iv;
                    UIComponent uIComponent;
                    UIComponent $this$animate$iv = (UIComponent)this.getText();
                    boolean $i$f$animate = false;
                    UIComponent $this$animate_u24lambda_u2d0$iv = uIComponent = $this$animate$iv;
                    boolean bl = false;
                    AnimatingConstraints $this$invoke_u24lambda_u2d0 = anim$iv = $this$animate_u24lambda_u2d0$iv.makeAnimation();
                    boolean bl2 = false;
                    AnimationStrategy animationStrategy = (AnimationStrategy)Animations.OUT_EXP;
                    Color color = ColorUtils.TEXT_HOVERED;
                    Intrinsics.checkNotNullExpressionValue((Object)color, (String)"TEXT_HOVERED");
                    AnimatingConstraints.setColorAnimation$default((AnimatingConstraints)$this$invoke_u24lambda_u2d0, (AnimationStrategy)animationStrategy, (float)0.5f, (ColorConstraint)((ColorConstraint)UtilitiesKt.toConstraint((Color)color)), (float)0.0f, (int)8, null);
                    $this$animate_u24lambda_u2d0$iv.animateTo(anim$iv);
                }
            }
        });
        this.onMouseLeave((Function1)new Function1<UIComponent, Unit>(){

            public final void invoke(@NotNull UIComponent $this$onMouseLeave) {
                Intrinsics.checkNotNullParameter((Object)$this$onMouseLeave, (String)"$this$onMouseLeave");
                if (!this.isSelected()) {
                    AnimatingConstraints anim$iv;
                    UIComponent uIComponent;
                    UIComponent $this$animate$iv = (UIComponent)this.getText();
                    boolean $i$f$animate = false;
                    UIComponent $this$animate_u24lambda_u2d0$iv = uIComponent = $this$animate$iv;
                    boolean bl = false;
                    AnimatingConstraints $this$invoke_u24lambda_u2d0 = anim$iv = $this$animate_u24lambda_u2d0$iv.makeAnimation();
                    boolean bl2 = false;
                    AnimationStrategy animationStrategy = (AnimationStrategy)Animations.OUT_EXP;
                    Color color = ColorUtils.LABEL;
                    Intrinsics.checkNotNullExpressionValue((Object)color, (String)"LABEL");
                    AnimatingConstraints.setColorAnimation$default((AnimatingConstraints)$this$invoke_u24lambda_u2d0, (AnimationStrategy)animationStrategy, (float)0.5f, (ColorConstraint)((ColorConstraint)UtilitiesKt.toConstraint((Color)color)), (float)0.0f, (int)8, null);
                    $this$animate_u24lambda_u2d0$iv.animateTo(anim$iv);
                }
            }
        });
    }

    private final UIText getText() {
        return (UIText)this.text$delegate.getValue((Object)this, $$delegatedProperties[0]);
    }

    public final boolean isSelected() {
        return this.isSelected;
    }

    public final void setSelected(boolean bl) {
        this.isSelected = bl;
    }

    public final void select() {
        AnimatingConstraints anim$iv;
        UIComponent uIComponent;
        this.gui.selectConfig(this.config);
        this.isSelected = true;
        UIComponent $this$animate$iv = (UIComponent)this.getText();
        boolean $i$f$animate = false;
        UIComponent $this$animate_u24lambda_u2d0$iv = uIComponent = $this$animate$iv;
        boolean bl = false;
        AnimatingConstraints $this$select_u24lambda_u2d3 = anim$iv = $this$animate_u24lambda_u2d0$iv.makeAnimation();
        boolean bl2 = false;
        AnimationStrategy animationStrategy = (AnimationStrategy)Animations.OUT_EXP;
        Color color = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue((Object)color, (String)"WHITE");
        AnimatingConstraints.setColorAnimation$default((AnimatingConstraints)$this$select_u24lambda_u2d3, (AnimationStrategy)animationStrategy, (float)0.5f, (ColorConstraint)((ColorConstraint)UtilitiesKt.toConstraint((Color)color)), (float)0.0f, (int)8, null);
        $this$animate_u24lambda_u2d0$iv.animateTo(anim$iv);
    }

    public final void deselect() {
        AnimatingConstraints anim$iv;
        UIComponent uIComponent;
        this.isSelected = false;
        UIComponent $this$animate$iv = (UIComponent)this.getText();
        boolean $i$f$animate = false;
        UIComponent $this$animate_u24lambda_u2d0$iv = uIComponent = $this$animate$iv;
        boolean bl = false;
        AnimatingConstraints $this$deselect_u24lambda_u2d4 = anim$iv = $this$animate_u24lambda_u2d0$iv.makeAnimation();
        boolean bl2 = false;
        AnimationStrategy animationStrategy = (AnimationStrategy)Animations.OUT_EXP;
        Color color = ColorUtils.LABEL;
        Intrinsics.checkNotNullExpressionValue((Object)color, (String)"LABEL");
        AnimatingConstraints.setColorAnimation$default((AnimatingConstraints)$this$deselect_u24lambda_u2d4, (AnimationStrategy)animationStrategy, (float)0.5f, (ColorConstraint)((ColorConstraint)UtilitiesKt.toConstraint((Color)color)), (float)0.0f, (int)8, null);
        $this$animate_u24lambda_u2d0$iv.animateTo(anim$iv);
    }

    static {
        KProperty[] arrkProperty = new KProperty[]{(KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(ConfigLabel.class, "text", "getText()Lgg/essential/elementa/components/UIText;", 0)))};
        $$delegatedProperties = arrkProperty;
    }
}

