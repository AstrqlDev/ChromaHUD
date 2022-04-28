/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  gg.essential.elementa.UIComponent
 *  gg.essential.elementa.UIConstraints
 *  gg.essential.elementa.components.UIBlock
 *  gg.essential.elementa.components.UIContainer
 *  gg.essential.elementa.components.UIText
 *  gg.essential.elementa.constraints.CenterConstraint
 *  gg.essential.elementa.constraints.ChildBasedMaxSizeConstraint
 *  gg.essential.elementa.constraints.ColorConstraint
 *  gg.essential.elementa.constraints.HeightConstraint
 *  gg.essential.elementa.constraints.SiblingConstraint
 *  gg.essential.elementa.constraints.SuperConstraint
 *  gg.essential.elementa.constraints.WidthConstraint
 *  gg.essential.elementa.constraints.XConstraint
 *  gg.essential.elementa.constraints.YConstraint
 *  gg.essential.elementa.constraints.animation.AnimatingConstraints
 *  gg.essential.elementa.constraints.animation.AnimationStrategy
 *  gg.essential.elementa.constraints.animation.Animations
 *  gg.essential.elementa.dsl.ComponentsKt
 *  gg.essential.elementa.dsl.ConstraintsKt
 *  gg.essential.elementa.dsl.UtilitiesKt
 *  gg.essential.elementa.events.UIClickEvent
 *  gg.essential.elementa.font.DefaultFonts
 *  gg.essential.universal.USound
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.PropertyReference1
 *  kotlin.jvm.internal.PropertyReference1Impl
 *  kotlin.jvm.internal.Reflection
 *  kotlin.properties.ReadWriteProperty
 *  kotlin.reflect.KProperty
 *  kotlin.text.CharsKt
 *  org.jetbrains.annotations.NotNull
 */
package xyz.apfelmus.cheeto.client.clickgui;

import gg.essential.elementa.UIComponent;
import gg.essential.elementa.UIConstraints;
import gg.essential.elementa.components.UIBlock;
import gg.essential.elementa.components.UIContainer;
import gg.essential.elementa.components.UIText;
import gg.essential.elementa.constraints.CenterConstraint;
import gg.essential.elementa.constraints.ChildBasedMaxSizeConstraint;
import gg.essential.elementa.constraints.ColorConstraint;
import gg.essential.elementa.constraints.HeightConstraint;
import gg.essential.elementa.constraints.SiblingConstraint;
import gg.essential.elementa.constraints.SuperConstraint;
import gg.essential.elementa.constraints.WidthConstraint;
import gg.essential.elementa.constraints.XConstraint;
import gg.essential.elementa.constraints.YConstraint;
import gg.essential.elementa.constraints.animation.AnimatingConstraints;
import gg.essential.elementa.constraints.animation.AnimationStrategy;
import gg.essential.elementa.constraints.animation.Animations;
import gg.essential.elementa.dsl.ComponentsKt;
import gg.essential.elementa.dsl.ConstraintsKt;
import gg.essential.elementa.dsl.UtilitiesKt;
import gg.essential.elementa.events.UIClickEvent;
import gg.essential.elementa.font.DefaultFonts;
import gg.essential.universal.USound;
import java.awt.Color;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlin.text.CharsKt;
import org.jetbrains.annotations.NotNull;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cheeto.client.clickgui.ConfigGUI;
import xyz.apfelmus.cheeto.client.utils.client.ColorUtils;

@Metadata(mv={1, 6, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u001cR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0017\u001a\u00020\u00018BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001a\u0010\u0016\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001e"}, d2={"Lxyz/apfelmus/cheeto/client/clickgui/CategoryLabel;", "Lgg/essential/elementa/components/UIContainer;", "gui", "Lxyz/apfelmus/cheeto/client/clickgui/ConfigGUI;", "category", "Lxyz/apfelmus/cf4m/module/Category;", "(Lxyz/apfelmus/cheeto/client/clickgui/ConfigGUI;Lxyz/apfelmus/cf4m/module/Category;)V", "categoryName", "getCategoryName", "()Lxyz/apfelmus/cf4m/module/Category;", "setCategoryName", "(Lxyz/apfelmus/cf4m/module/Category;)V", "isSelected", "", "()Z", "setSelected", "(Z)V", "text", "Lgg/essential/elementa/components/UIText;", "getText", "()Lgg/essential/elementa/components/UIText;", "text$delegate", "Lkotlin/properties/ReadWriteProperty;", "textContainer", "getTextContainer", "()Lgg/essential/elementa/components/UIContainer;", "textContainer$delegate", "deselect", "", "select", "Cheeto"})
public final class CategoryLabel
extends UIContainer {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    @NotNull
    private final ConfigGUI gui;
    @NotNull
    private final Category category;
    @NotNull
    private Category categoryName;
    @NotNull
    private final ReadWriteProperty textContainer$delegate;
    @NotNull
    private final ReadWriteProperty text$delegate;
    private boolean isSelected;

    /*
     * WARNING - void declaration
     */
    public CategoryLabel(@NotNull ConfigGUI gui, @NotNull Category category) {
        void $this$text_delegate_u24lambda_u2d2;
        Object object;
        UIConstraints $this$textContainer_delegate_u24lambda_u2d1;
        UIComponent uIComponent;
        Intrinsics.checkNotNullParameter((Object)((Object)gui), (String)"gui");
        Intrinsics.checkNotNullParameter((Object)((Object)category), (String)"category");
        this.gui = gui;
        this.categoryName = this.category = category;
        Color color = ColorUtils.M_BORDER;
        Intrinsics.checkNotNullExpressionValue((Object)color, (String)"M_BORDER");
        Object $this$constrain$iv = (UIComponent)new UIBlock(color);
        boolean $i$f$constrain = false;
        Object $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
        boolean bl = false;
        UIConstraints $this$_init__u24lambda_u2d0 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        boolean bl2 = false;
        $this$_init__u24lambda_u2d0.setX((XConstraint)new SiblingConstraint(0.0f, false, 3, null));
        $this$_init__u24lambda_u2d0.setWidth((WidthConstraint)UtilitiesKt.pixel$default((Number)1, (boolean)false, (boolean)false, (int)3, null));
        $this$_init__u24lambda_u2d0.setHeight((HeightConstraint)UtilitiesKt.percent((Number)100));
        ComponentsKt.childOf((UIComponent)uIComponent, (UIComponent)((UIComponent)this));
        $this$constrain$iv = (UIComponent)new UIContainer();
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
        bl = false;
        $this$_init__u24lambda_u2d0 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        CategoryLabel categoryLabel = this;
        boolean bl3 = false;
        $this$textContainer_delegate_u24lambda_u2d1.setX((XConstraint)new SiblingConstraint(0.0f, false, 3, null));
        $this$textContainer_delegate_u24lambda_u2d1.setY((YConstraint)new CenterConstraint());
        $this$textContainer_delegate_u24lambda_u2d1.setWidth((WidthConstraint)ConstraintsKt.plus((SuperConstraint)((SuperConstraint)new ChildBasedMaxSizeConstraint()), (SuperConstraint)((SuperConstraint)UtilitiesKt.pixels$default((Number)20, (boolean)false, (boolean)false, (int)3, null))));
        categoryLabel.textContainer$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.childOf((UIComponent)uIComponent, (UIComponent)((UIComponent)this)), (Object)((Object)this), $$delegatedProperties[0]);
        CategoryLabel categoryLabel2 = this;
        String $i$f$constrain22 = this.category.name().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue((Object)$i$f$constrain22, (String)"this as java.lang.String).toLowerCase(Locale.ROOT)");
        $this$constrain$iv = $i$f$constrain22;
        if (((CharSequence)$this$constrain$iv).length() > 0) {
            void p12;
            char $i$f$constrain22 = ((String)$this$constrain$iv).charAt(0);
            StringBuilder stringBuilder = new StringBuilder();
            categoryLabel = categoryLabel2;
            boolean bl4 = false;
            String string = CharsKt.titlecase((char)p12);
            categoryLabel2 = categoryLabel;
            StringBuilder stringBuilder2 = stringBuilder.append((Object)string);
            Object p12 = $this$constrain$iv;
            int n = 1;
            String string2 = ((String)p12).substring(n);
            Intrinsics.checkNotNullExpressionValue((Object)string2, (String)"this as java.lang.String).substring(startIndex)");
            object = stringBuilder2.append(string2).toString();
        } else {
            object = $this$constrain$iv;
        }
        DefaultConstructorMarker defaultConstructorMarker = null;
        int n = 6;
        Color color2 = null;
        boolean bl5 = false;
        Object object2 = object;
        $this$constrain$iv = (UIComponent)new UIText((String)object2, bl5, color2, n, defaultConstructorMarker);
        $i$f$constrain = false;
        Object object3 = $this$constrain$iv;
        $this$constrain_u24lambda_u2d0$iv = object3;
        bl = false;
        $this$textContainer_delegate_u24lambda_u2d1 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        categoryLabel = categoryLabel2;
        boolean bl6 = false;
        $this$text_delegate_u24lambda_u2d2.setX((XConstraint)new CenterConstraint());
        $this$text_delegate_u24lambda_u2d2.setY((YConstraint)new CenterConstraint());
        Color color3 = ColorUtils.LABEL;
        Intrinsics.checkNotNullExpressionValue((Object)color3, (String)"LABEL");
        $this$text_delegate_u24lambda_u2d2.setColor((ColorConstraint)UtilitiesKt.toConstraint((Color)color3));
        $this$text_delegate_u24lambda_u2d2.setTextScale((HeightConstraint)UtilitiesKt.pixels$default((Number)Float.valueOf(1.5f), (boolean)false, (boolean)false, (int)3, null));
        $this$text_delegate_u24lambda_u2d2.setFontProvider(DefaultFonts.getVANILLA_FONT_RENDERER());
        categoryLabel.text$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.childOf((UIComponent)object3, (UIComponent)((UIComponent)this.getTextContainer())), (Object)((Object)this), $$delegatedProperties[1]);
        $this$constrain$iv = ColorUtils.M_BORDER;
        Intrinsics.checkNotNullExpressionValue((Object)$this$constrain$iv, (String)"M_BORDER");
        $this$constrain$iv = (UIComponent)new UIBlock((Color)$this$constrain$iv);
        $i$f$constrain = false;
        object3 = $this$constrain$iv;
        $this$constrain_u24lambda_u2d0$iv = object3;
        bl = false;
        UIConstraints $this$_init__u24lambda_u2d3 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        boolean bl7 = false;
        $this$_init__u24lambda_u2d3.setX((XConstraint)ConstraintsKt.minus((SuperConstraint)((SuperConstraint)new SiblingConstraint(0.0f, false, 3, null)), (SuperConstraint)((SuperConstraint)UtilitiesKt.pixel$default((Number)1, (boolean)false, (boolean)false, (int)3, null))));
        $this$_init__u24lambda_u2d3.setWidth((WidthConstraint)UtilitiesKt.pixel$default((Number)1, (boolean)false, (boolean)false, (int)3, null));
        $this$_init__u24lambda_u2d3.setHeight((HeightConstraint)UtilitiesKt.percent((Number)100));
        ComponentsKt.childOf((UIComponent)object3, (UIComponent)((UIComponent)this));
        $this$constrain$iv = (UIComponent)this;
        $i$f$constrain = false;
        object3 = $this$constrain$iv;
        $this$constrain_u24lambda_u2d0$iv = object3;
        bl = false;
        UIConstraints $this$_init__u24lambda_u2d4 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        boolean bl8 = false;
        $this$_init__u24lambda_u2d4.setX((XConstraint)new SiblingConstraint(0.0f, false, 3, null));
        $this$_init__u24lambda_u2d4.setY((YConstraint)new CenterConstraint());
        $this$_init__u24lambda_u2d4.setWidth((WidthConstraint)new ChildBasedMaxSizeConstraint());
        $this$_init__u24lambda_u2d4.setHeight((HeightConstraint)UtilitiesKt.pixels$default((Number)14, (boolean)false, (boolean)false, (int)3, null));
        UIComponent $this$onLeftClick$iv = (UIComponent)this;
        boolean $i$f$onLeftClick = false;
        $this$onLeftClick$iv.onMouseClick((Function2)new Function2<UIComponent, UIClickEvent, Unit>(this){
            final /* synthetic */ CategoryLabel this$0;
            {
                this.this$0 = categoryLabel;
                super(2);
            }

            public final void invoke(@NotNull UIComponent $this$onMouseClick, @NotNull UIClickEvent it) {
                Intrinsics.checkNotNullParameter((Object)$this$onMouseClick, (String)"$this$onMouseClick");
                Intrinsics.checkNotNullParameter((Object)it, (String)"it");
                if (it.getMouseButton() == 0) {
                    UIClickEvent uIClickEvent = it;
                    UIComponent $this$_init__u24lambda_u2d5 = $this$onMouseClick;
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

    @NotNull
    public final Category getCategoryName() {
        return this.categoryName;
    }

    public final void setCategoryName(@NotNull Category category) {
        Intrinsics.checkNotNullParameter((Object)((Object)category), (String)"<set-?>");
        this.categoryName = category;
    }

    private final UIContainer getTextContainer() {
        return (UIContainer)this.textContainer$delegate.getValue((Object)this, $$delegatedProperties[0]);
    }

    private final UIText getText() {
        return (UIText)this.text$delegate.getValue((Object)this, $$delegatedProperties[1]);
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
        this.gui.selectCategory(this.category);
        this.isSelected = true;
        UIComponent $this$animate$iv = (UIComponent)this.getText();
        boolean $i$f$animate = false;
        UIComponent $this$animate_u24lambda_u2d0$iv = uIComponent = $this$animate$iv;
        boolean bl = false;
        AnimatingConstraints $this$select_u24lambda_u2d6 = anim$iv = $this$animate_u24lambda_u2d0$iv.makeAnimation();
        boolean bl2 = false;
        AnimationStrategy animationStrategy = (AnimationStrategy)Animations.OUT_EXP;
        Color color = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue((Object)color, (String)"WHITE");
        AnimatingConstraints.setColorAnimation$default((AnimatingConstraints)$this$select_u24lambda_u2d6, (AnimationStrategy)animationStrategy, (float)0.5f, (ColorConstraint)((ColorConstraint)UtilitiesKt.toConstraint((Color)color)), (float)0.0f, (int)8, null);
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
        AnimatingConstraints $this$deselect_u24lambda_u2d7 = anim$iv = $this$animate_u24lambda_u2d0$iv.makeAnimation();
        boolean bl2 = false;
        AnimationStrategy animationStrategy = (AnimationStrategy)Animations.OUT_EXP;
        Color color = ColorUtils.LABEL;
        Intrinsics.checkNotNullExpressionValue((Object)color, (String)"LABEL");
        AnimatingConstraints.setColorAnimation$default((AnimatingConstraints)$this$deselect_u24lambda_u2d7, (AnimationStrategy)animationStrategy, (float)0.5f, (ColorConstraint)((ColorConstraint)UtilitiesKt.toConstraint((Color)color)), (float)0.0f, (int)8, null);
        $this$animate_u24lambda_u2d0$iv.animateTo(anim$iv);
    }

    static {
        KProperty[] arrkProperty = new KProperty[]{(KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(CategoryLabel.class, "textContainer", "getTextContainer()Lgg/essential/elementa/components/UIContainer;", 0))), (KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(CategoryLabel.class, "text", "getText()Lgg/essential/elementa/components/UIText;", 0)))};
        $$delegatedProperties = arrkProperty;
    }
}

