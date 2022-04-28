/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  gg.essential.elementa.UIComponent
 *  gg.essential.elementa.UIConstraints
 *  gg.essential.elementa.components.ScrollComponent
 *  gg.essential.elementa.components.UIBlock
 *  gg.essential.elementa.components.UIContainer
 *  gg.essential.elementa.components.UIImage
 *  gg.essential.elementa.components.UIText
 *  gg.essential.elementa.constraints.AdditiveConstraint
 *  gg.essential.elementa.constraints.ChildBasedMaxSizeConstraint
 *  gg.essential.elementa.constraints.ChildBasedSizeConstraint
 *  gg.essential.elementa.constraints.ColorConstraint
 *  gg.essential.elementa.constraints.CopyConstraintFloat
 *  gg.essential.elementa.constraints.HeightConstraint
 *  gg.essential.elementa.constraints.RelativeConstraint
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
 *  gg.essential.elementa.effects.Effect
 *  gg.essential.elementa.effects.OutlineEffect
 *  gg.essential.elementa.effects.ScissorEffect
 *  gg.essential.elementa.events.UIClickEvent
 *  gg.essential.universal.USound
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.functions.Function0
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.Intrinsics$Kotlin
 *  kotlin.jvm.internal.PropertyReference1
 *  kotlin.jvm.internal.PropertyReference1Impl
 *  kotlin.jvm.internal.Reflection
 *  kotlin.properties.ReadWriteProperty
 *  kotlin.reflect.KProperty
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package xyz.apfelmus.cheeto.client.clickgui.settings;

import gg.essential.elementa.UIComponent;
import gg.essential.elementa.UIConstraints;
import gg.essential.elementa.components.ScrollComponent;
import gg.essential.elementa.components.UIBlock;
import gg.essential.elementa.components.UIContainer;
import gg.essential.elementa.components.UIImage;
import gg.essential.elementa.components.UIText;
import gg.essential.elementa.constraints.AdditiveConstraint;
import gg.essential.elementa.constraints.ChildBasedMaxSizeConstraint;
import gg.essential.elementa.constraints.ChildBasedSizeConstraint;
import gg.essential.elementa.constraints.ColorConstraint;
import gg.essential.elementa.constraints.CopyConstraintFloat;
import gg.essential.elementa.constraints.HeightConstraint;
import gg.essential.elementa.constraints.RelativeConstraint;
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
import gg.essential.elementa.effects.Effect;
import gg.essential.elementa.effects.OutlineEffect;
import gg.essential.elementa.effects.ScissorEffect;
import gg.essential.elementa.events.UIClickEvent;
import gg.essential.universal.USound;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.apfelmus.cheeto.client.clickgui.settings.DropDown;
import xyz.apfelmus.cheeto.client.utils.client.ColorUtils;

@Metadata(mv={1, 6, 0}, k=1, xi=48, d1={"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0014\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\u001a\u0010/\u001a\u00020 2\b\b\u0002\u00100\u001a\u00020\r2\b\b\u0002\u00101\u001a\u00020\rJ\b\u00102\u001a\u00020 H\u0002J\u0006\u00103\u001a\u00020\u0003J\u0010\u00104\u001a\u00020 2\u0006\u00105\u001a\u00020\u001dH\u0002J\u001a\u0010\u001e\u001a\u00020 2\u0012\u00106\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020 0\u001fJ\b\u00107\u001a\u00020 H\u0002J\u000e\u00108\u001a\u00020 2\u0006\u00109\u001a\u00020\u0003J\u0010\u0010:\u001a\u00020 2\u0006\u00105\u001a\u00020\u001dH\u0002R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001a\u0010\u0015\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001b\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020 0\u001fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010!\u001a\u00020\"8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b%\u0010\u0015\u001a\u0004\b#\u0010$R\u001b\u0010&\u001a\u00020'8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b*\u0010\u0015\u001a\u0004\b(\u0010)R\u000e\u0010+\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010,\u001a\u00020\u00178BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b.\u0010\u0015\u001a\u0004\b-\u0010\u0019\u00a8\u0006;"}, d2={"Lxyz/apfelmus/cheeto/client/clickgui/settings/DropDown;", "Lgg/essential/elementa/components/UIBlock;", "initialSelection", "", "options", "", "", "outlineEffect", "Lgg/essential/elementa/effects/OutlineEffect;", "optionPadding", "", "(ILjava/util/List;Lgg/essential/elementa/effects/OutlineEffect;F)V", "active", "", "collapsedWidth", "Lgg/essential/elementa/constraints/AdditiveConstraint;", "currentSelectionText", "Lgg/essential/elementa/components/UIText;", "getCurrentSelectionText", "()Lgg/essential/elementa/components/UIText;", "currentSelectionText$delegate", "Lkotlin/properties/ReadWriteProperty;", "downArrow", "Lgg/essential/elementa/components/UIImage;", "getDownArrow", "()Lgg/essential/elementa/components/UIImage;", "downArrow$delegate", "expandedWidth", "mappedOptions", "Lgg/essential/elementa/UIComponent;", "onValueChange", "Lkotlin/Function1;", "", "optionsHolder", "Lgg/essential/elementa/components/ScrollComponent;", "getOptionsHolder", "()Lgg/essential/elementa/components/ScrollComponent;", "optionsHolder$delegate", "scrollContainer", "Lgg/essential/elementa/components/UIContainer;", "getScrollContainer", "()Lgg/essential/elementa/components/UIContainer;", "scrollContainer$delegate", "selected", "upArrow", "getUpArrow", "upArrow$delegate", "collapse", "unHover", "instantly", "expand", "getValue", "hoverText", "text", "listener", "readOptionComponents", "select", "index", "unHoverText", "Cheeto"})
public final class DropDown
extends UIBlock {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    @NotNull
    private final List<String> options;
    private int selected;
    @NotNull
    private Function1<? super Integer, Unit> onValueChange;
    private boolean active;
    @NotNull
    private final ReadWriteProperty currentSelectionText$delegate;
    @NotNull
    private final ReadWriteProperty downArrow$delegate;
    @NotNull
    private final ReadWriteProperty upArrow$delegate;
    @NotNull
    private final ReadWriteProperty scrollContainer$delegate;
    @NotNull
    private final ReadWriteProperty optionsHolder$delegate;
    @NotNull
    private final List<UIComponent> mappedOptions;
    @NotNull
    private final AdditiveConstraint collapsedWidth;
    @NotNull
    private final AdditiveConstraint expandedWidth;

    /*
     * WARNING - void declaration
     */
    public DropDown(int initialSelection, @NotNull List<String> options, @Nullable OutlineEffect outlineEffect, float optionPadding) {
        UIComponent uIComponent;
        UIComponent uIComponent2;
        Color option;
        Object $this$mapIndexedTo$iv$iv;
        void $this$mapIndexed$iv;
        void $this$optionsHolder_delegate_u24lambda_u2d4;
        UIConstraints $this$scrollContainer_delegate_u24lambda_u2d3;
        UIConstraints $this$upArrow_delegate_u24lambda_u2d2;
        UIConstraints $this$downArrow_delegate_u24lambda_u2d1;
        UIConstraints $this$currentSelectionText_delegate_u24lambda_u2d0;
        UIComponent uIComponent3;
        Intrinsics.checkNotNullParameter(options, (String)"options");
        super(null, 1, null);
        this.options = options;
        this.selected = initialSelection;
        this.onValueChange = onValueChange.1.INSTANCE;
        Object $this$constrain$iv = (UIComponent)new UIText(this.options.get(this.selected), false, null, 6, null);
        boolean $i$f$constrain = false;
        UIComponent $this$constrain_u24lambda_u2d0$iv = uIComponent3 = $this$constrain$iv;
        boolean bl = false;
        UIConstraints uIConstraints = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        DropDown dropDown = this;
        boolean bl2 = false;
        $this$currentSelectionText_delegate_u24lambda_u2d0.setX((XConstraint)UtilitiesKt.pixels$default((Number)5, (boolean)false, (boolean)false, (int)3, null));
        $this$currentSelectionText_delegate_u24lambda_u2d0.setY((YConstraint)UtilitiesKt.pixels$default((Number)6, (boolean)false, (boolean)false, (int)3, null));
        Object object = ColorUtils.LABEL;
        Intrinsics.checkNotNullExpressionValue((Object)object, (String)"LABEL");
        $this$currentSelectionText_delegate_u24lambda_u2d0.setColor((ColorConstraint)UtilitiesKt.toConstraint((Color)object));
        $this$currentSelectionText_delegate_u24lambda_u2d0.setFontProvider(this.getFontProvider());
        dropDown.currentSelectionText$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.childOf((UIComponent)uIComponent3, (UIComponent)((UIComponent)this)), (Object)((Object)this), $$delegatedProperties[0]);
        $this$constrain$iv = (UIComponent)UIImage.Companion.ofResourceCached("/vigilance/arrow-down.png");
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = uIComponent3 = $this$constrain$iv;
        bl = false;
        $this$currentSelectionText_delegate_u24lambda_u2d0 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        dropDown = this;
        boolean bl3 = false;
        $this$downArrow_delegate_u24lambda_u2d1.setX((XConstraint)UtilitiesKt.pixels$default((Number)5, (boolean)true, (boolean)false, (int)2, null));
        $this$downArrow_delegate_u24lambda_u2d1.setY((YConstraint)UtilitiesKt.pixels$default((Number)7.5, (boolean)false, (boolean)false, (int)3, null));
        $this$downArrow_delegate_u24lambda_u2d1.setWidth((WidthConstraint)UtilitiesKt.pixels$default((Number)9, (boolean)false, (boolean)false, (int)3, null));
        $this$downArrow_delegate_u24lambda_u2d1.setHeight((HeightConstraint)UtilitiesKt.pixels$default((Number)5, (boolean)false, (boolean)false, (int)3, null));
        dropDown.downArrow$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.childOf((UIComponent)uIComponent3, (UIComponent)((UIComponent)this)), (Object)((Object)this), $$delegatedProperties[1]);
        $this$constrain$iv = (UIComponent)UIImage.Companion.ofResourceCached("/vigilance/arrow-up.png");
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = uIComponent3 = $this$constrain$iv;
        bl = false;
        $this$downArrow_delegate_u24lambda_u2d1 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        dropDown = this;
        boolean bl4 = false;
        $this$upArrow_delegate_u24lambda_u2d2.setX((XConstraint)UtilitiesKt.pixels$default((Number)5, (boolean)true, (boolean)false, (int)2, null));
        $this$upArrow_delegate_u24lambda_u2d2.setY((YConstraint)UtilitiesKt.pixels$default((Number)7.5, (boolean)false, (boolean)false, (int)3, null));
        $this$upArrow_delegate_u24lambda_u2d2.setWidth((WidthConstraint)UtilitiesKt.pixels$default((Number)9, (boolean)false, (boolean)false, (int)3, null));
        $this$upArrow_delegate_u24lambda_u2d2.setHeight((HeightConstraint)UtilitiesKt.pixels$default((Number)5, (boolean)false, (boolean)false, (int)3, null));
        dropDown.upArrow$delegate = ComponentsKt.provideDelegate((UIComponent)uIComponent3, (Object)((Object)this), $$delegatedProperties[2]);
        $this$constrain$iv = (UIComponent)new UIContainer();
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = uIComponent3 = $this$constrain$iv;
        bl = false;
        $this$upArrow_delegate_u24lambda_u2d2 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        dropDown = this;
        boolean bl5 = false;
        $this$scrollContainer_delegate_u24lambda_u2d3.setX((XConstraint)UtilitiesKt.pixels$default((Number)5, (boolean)false, (boolean)false, (int)3, null));
        $this$scrollContainer_delegate_u24lambda_u2d3.setY((YConstraint)ConstraintsKt.boundTo((SuperConstraint)((SuperConstraint)new SiblingConstraint(optionPadding, false, 2, null)), (UIComponent)((UIComponent)this.getCurrentSelectionText())));
        $this$scrollContainer_delegate_u24lambda_u2d3.setWidth((WidthConstraint)new ChildBasedMaxSizeConstraint());
        $this$scrollContainer_delegate_u24lambda_u2d3.setHeight((HeightConstraint)ConstraintsKt.plus((SuperConstraint)((SuperConstraint)new ChildBasedSizeConstraint(0.0f, 1, null)), (SuperConstraint)((SuperConstraint)UtilitiesKt.pixels$default((Number)Float.valueOf(optionPadding), (boolean)false, (boolean)false, (int)3, null))));
        dropDown.scrollContainer$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.childOf((UIComponent)uIComponent3, (UIComponent)((UIComponent)this)), (Object)((Object)this), $$delegatedProperties[3]);
        $this$constrain$iv = (UIComponent)new ScrollComponent(null, 0.0f, null, false, false, false, false, 0.0f, 0.0f, (UIComponent)this.getScrollContainer(), 511, null);
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = uIComponent3 = $this$constrain$iv;
        bl = false;
        $this$scrollContainer_delegate_u24lambda_u2d3 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        dropDown = this;
        int n = 0;
        $this$optionsHolder_delegate_u24lambda_u2d4.setX((XConstraint)UtilitiesKt.pixels$default((Number)0, (boolean)false, (boolean)false, (int)3, null));
        $this$optionsHolder_delegate_u24lambda_u2d4.setY((YConstraint)UtilitiesKt.pixels$default((Number)0, (boolean)false, (boolean)false, (int)3, null));
        $this$optionsHolder_delegate_u24lambda_u2d4.setHeight((HeightConstraint)UtilitiesKt.pixels$default((Number)Float.valueOf((float)(this.options.size() - 1) * (this.getFontProvider().getStringHeight("Text", $this$optionsHolder_delegate_u24lambda_u2d4.getTextScale()) + optionPadding) - optionPadding), (boolean)false, (boolean)false, (int)3, null));
        dropDown.optionsHolder$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.childOf((UIComponent)uIComponent3, (UIComponent)((UIComponent)this.getScrollContainer())), (Object)((Object)this), $$delegatedProperties[4]);
        $this$constrain$iv = this.options;
        dropDown = this;
        boolean $i$f$mapIndexed = false;
        uIComponent3 = $this$mapIndexed$iv;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)$this$mapIndexed$iv, (int)10));
        boolean $i$f$mapIndexedTo = false;
        int index$iv$iv = 0;
        for (Object item$iv$iv : $this$mapIndexedTo$iv$iv) {
            void index;
            UIComponent uIComponent4;
            n = index$iv$iv;
            index$iv$iv = n + 1;
            if (n < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            object = (String)item$iv$iv;
            int n2 = n;
            Collection collection = destination$iv$iv;
            boolean bl6 = false;
            UIComponent $this$constrain$iv2 = (UIComponent)new UIText((String)((Object)option), false, null, 6, null);
            boolean $i$f$constrain2 = false;
            UIComponent $this$constrain_u24lambda_u2d0$iv2 = uIComponent4 = $this$constrain$iv2;
            $i$a$-apply-ComponentsKt$constrain$1$iv = false;
            UIConstraints $this$mappedOptions_u24lambda_u2d6_u24lambda_u2d5 = $this$constrain_u24lambda_u2d0$iv2.getConstraints();
            boolean bl7 = false;
            $this$mappedOptions_u24lambda_u2d6_u24lambda_u2d5.setY((YConstraint)new SiblingConstraint(optionPadding, false, 2, null));
            $this$mappedOptions_u24lambda_u2d6_u24lambda_u2d5.setColor((ColorConstraint)UtilitiesKt.toConstraint((Color)new Color(0, 0, 0, 0)));
            $this$mappedOptions_u24lambda_u2d6_u24lambda_u2d5.setFontProvider(this.getFontProvider());
            collection.add(((UIText)uIComponent4).onMouseEnter((Function1)new Function1<UIComponent, Unit>(this){
                final /* synthetic */ DropDown this$0;
                {
                    this.this$0 = $receiver;
                    super(1);
                }

                public final void invoke(@NotNull UIComponent $this$onMouseEnter) {
                    Intrinsics.checkNotNullParameter((Object)$this$onMouseEnter, (String)"$this$onMouseEnter");
                    DropDown.access$hoverText(this.this$0, $this$onMouseEnter);
                }
            }).onMouseLeave((Function1)new Function1<UIComponent, Unit>(this){
                final /* synthetic */ DropDown this$0;
                {
                    this.this$0 = $receiver;
                    super(1);
                }

                public final void invoke(@NotNull UIComponent $this$onMouseLeave) {
                    Intrinsics.checkNotNullParameter((Object)$this$onMouseLeave, (String)"$this$onMouseLeave");
                    DropDown.access$unHoverText(this.this$0, $this$onMouseLeave);
                }
            }).onMouseClick((Function2)new Function2<UIComponent, UIClickEvent, Unit>(this, (int)index){
                final /* synthetic */ DropDown this$0;
                final /* synthetic */ int $index;
                {
                    this.this$0 = $receiver;
                    this.$index = $index;
                    super(2);
                }

                public final void invoke(@NotNull UIComponent $this$onMouseClick, @NotNull UIClickEvent event) {
                    Intrinsics.checkNotNullParameter((Object)$this$onMouseClick, (String)"$this$onMouseClick");
                    Intrinsics.checkNotNullParameter((Object)event, (String)"event");
                    event.stopPropagation();
                    this.this$0.select(this.$index);
                }
            }));
        }
        dropDown.mappedOptions = (List)destination$iv$iv;
        this.collapsedWidth = ConstraintsKt.plus((SuperConstraint)((SuperConstraint)UtilitiesKt.pixels$default((Number)22, (boolean)false, (boolean)false, (int)3, null)), (SuperConstraint)new CopyConstraintFloat(false, 1, null).to((UIComponent)this.getCurrentSelectionText()));
        this.expandedWidth = ConstraintsKt.plus((SuperConstraint)((SuperConstraint)UtilitiesKt.pixels$default((Number)22, (boolean)false, (boolean)false, (int)3, null)), (SuperConstraint)((SuperConstraint)ConstraintsKt.coerceAtLeast((SuperConstraint)new ChildBasedMaxSizeConstraint().to((UIComponent)this.getOptionsHolder()), (SuperConstraint)new CopyConstraintFloat(false, 1, null).to((UIComponent)this.getCurrentSelectionText()))));
        $this$constrain$iv = (UIComponent)this;
        $i$f$constrain = false;
        Object $this$constrain_u24lambda_u2d0$iv2 = $this$mapIndexedTo$iv$iv = $this$constrain$iv;
        $i$a$-apply-ComponentsKt$constrain$1$iv = false;
        UIConstraints $this$_init__u24lambda_u2d7 = $this$constrain_u24lambda_u2d0$iv2.getConstraints();
        boolean bl8 = false;
        $this$_init__u24lambda_u2d7.setWidth((WidthConstraint)this.collapsedWidth);
        $this$_init__u24lambda_u2d7.setHeight((HeightConstraint)UtilitiesKt.pixels$default((Number)20, (boolean)false, (boolean)false, (int)3, null));
        option = ColorUtils.MENU_BG;
        Intrinsics.checkNotNullExpressionValue((Object)option, (String)"MENU_BG");
        $this$_init__u24lambda_u2d7.setColor((ColorConstraint)UtilitiesKt.toConstraint((Color)option));
        this.readOptionComponents();
        this.getOptionsHolder().hide(true);
        OutlineEffect outlineEffect2 = outlineEffect;
        if (outlineEffect2 == null) {
            uIComponent2 = null;
        } else {
            Effect p0 = (Effect)outlineEffect2;
            boolean bl9 = false;
            uIComponent2 = this.enableEffect(p0);
        }
        UIComponent $this$constrain$iv3 = (UIComponent)new UIContainer();
        boolean $i$f$constrain3 = false;
        UIComponent $this$constrain_u24lambda_u2d0$iv3 = uIComponent = $this$constrain$iv3;
        $i$a$-apply-ComponentsKt$constrain$1$iv = false;
        UIConstraints $this$_init__u24lambda_u2d8 = $this$constrain_u24lambda_u2d0$iv3.getConstraints();
        boolean bl10 = false;
        $this$_init__u24lambda_u2d8.setX((XConstraint)UtilitiesKt.pixels$default((Number)-1, (boolean)false, (boolean)false, (int)3, null));
        $this$_init__u24lambda_u2d8.setY((YConstraint)UtilitiesKt.pixels$default((Number)-1, (boolean)false, (boolean)false, (int)3, null));
        $this$_init__u24lambda_u2d8.setWidth((WidthConstraint)ConstraintsKt.plus((SuperConstraint)((SuperConstraint)new RelativeConstraint(1.0f)), (SuperConstraint)((SuperConstraint)UtilitiesKt.pixels$default((Number)2, (boolean)false, (boolean)false, (int)3, null))));
        $this$_init__u24lambda_u2d8.setHeight((HeightConstraint)ConstraintsKt.plus((SuperConstraint)((SuperConstraint)new RelativeConstraint(1.0f)), (SuperConstraint)((SuperConstraint)UtilitiesKt.pixels$default((Number)Float.valueOf(3.0f), (boolean)false, (boolean)false, (int)3, null))));
        UIContainer outlineContainer = (UIContainer)uIComponent;
        outlineContainer.setParent((UIComponent)this);
        this.getChildren().add(0, (Object)outlineContainer);
        this.enableEffect((Effect)new ScissorEffect((UIComponent)outlineContainer, false, 2, null));
        this.onMouseEnter((Function1)new Function1<UIComponent, Unit>(){

            public final void invoke(@NotNull UIComponent $this$onMouseEnter) {
                Intrinsics.checkNotNullParameter((Object)$this$onMouseEnter, (String)"$this$onMouseEnter");
                this.hoverText((UIComponent)this.getCurrentSelectionText());
            }
        });
        this.onMouseLeave((Function1)new Function1<UIComponent, Unit>(){

            public final void invoke(@NotNull UIComponent $this$onMouseLeave) {
                Intrinsics.checkNotNullParameter((Object)$this$onMouseLeave, (String)"$this$onMouseLeave");
                if (active) {
                    return;
                }
                this.unHoverText((UIComponent)this.getCurrentSelectionText());
            }
        });
        UIComponent $this$onLeftClick$iv = (UIComponent)this;
        boolean $i$f$onLeftClick = false;
        $this$onLeftClick$iv.onMouseClick((Function2)new Function2<UIComponent, UIClickEvent, Unit>(this){
            final /* synthetic */ DropDown this$0;
            {
                this.this$0 = dropDown;
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
                    UIComponent $this$_init__u24lambda_u2d9 = $this$onMouseClick;
                    boolean bl = false;
                    USound.playButtonPress$default((USound)USound.INSTANCE, (float)0.0f, (int)1, null);
                    event.stopPropagation();
                    if (DropDown.access$getActive$p(this.this$0)) {
                        DropDown.collapse$default(this.this$0, false, false, 3, null);
                    } else {
                        DropDown.access$expand(this.this$0);
                    }
                }
            }
        });
    }

    public /* synthetic */ DropDown(int n, List list, OutlineEffect outlineEffect, float f, int n2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n2 & 4) != 0) {
            Color color = ColorUtils.M_BORDER;
            Intrinsics.checkNotNullExpressionValue((Object)color, (String)"M_BORDER");
            outlineEffect = new OutlineEffect(color, 1.0f, false, false, null, 28, null);
        }
        if ((n2 & 8) != 0) {
            f = 6.0f;
        }
        this(n, list, outlineEffect, f);
    }

    private final UIText getCurrentSelectionText() {
        return (UIText)this.currentSelectionText$delegate.getValue((Object)this, $$delegatedProperties[0]);
    }

    private final UIImage getDownArrow() {
        return (UIImage)this.downArrow$delegate.getValue((Object)this, $$delegatedProperties[1]);
    }

    private final UIImage getUpArrow() {
        return (UIImage)this.upArrow$delegate.getValue((Object)this, $$delegatedProperties[2]);
    }

    private final UIContainer getScrollContainer() {
        return (UIContainer)this.scrollContainer$delegate.getValue((Object)this, $$delegatedProperties[3]);
    }

    private final ScrollComponent getOptionsHolder() {
        return (ScrollComponent)this.optionsHolder$delegate.getValue((Object)this, $$delegatedProperties[4]);
    }

    public final void select(int index) {
        boolean bl = 0 <= index ? index < this.options.size() : false;
        if (bl) {
            this.selected = index;
            this.onValueChange.invoke((Object)index);
            this.getCurrentSelectionText().setText(this.options.get(index));
            DropDown.collapse$default(this, false, false, 3, null);
            this.readOptionComponents();
        }
    }

    public final void onValueChange(@NotNull Function1<? super Integer, Unit> listener2) {
        Intrinsics.checkNotNullParameter(listener2, (String)"listener");
        this.onValueChange = listener2;
    }

    public final int getValue() {
        return this.selected;
    }

    private final void expand() {
        AnimatingConstraints anim$iv;
        this.active = true;
        Iterable $this$forEach$iv = this.mappedOptions;
        boolean $i$f$forEach = false;
        UIComponent uIComponent = $this$forEach$iv.iterator();
        while (uIComponent.hasNext()) {
            Object element$iv = uIComponent.next();
            UIComponent it = (UIComponent)element$iv;
            boolean bl = false;
            Color color = ColorUtils.LABEL;
            Intrinsics.checkNotNullExpressionValue((Object)color, (String)"LABEL");
            it.setColor((ColorConstraint)UtilitiesKt.toConstraint((Color)color));
        }
        UIComponent $this$animate$iv = (UIComponent)this;
        boolean $i$f$animate = false;
        UIComponent $this$animate_u24lambda_u2d0$iv = uIComponent = $this$animate$iv;
        boolean bl = false;
        AnimatingConstraints $this$expand_u24lambda_u2d11 = anim$iv = $this$animate_u24lambda_u2d0$iv.makeAnimation();
        boolean bl2 = false;
        AnimatingConstraints.setHeightAnimation$default((AnimatingConstraints)$this$expand_u24lambda_u2d11, (AnimationStrategy)((AnimationStrategy)Animations.IN_SIN), (float)0.25f, (HeightConstraint)((HeightConstraint)ConstraintsKt.plus((SuperConstraint)((SuperConstraint)UtilitiesKt.pixels$default((Number)20, (boolean)false, (boolean)false, (int)3, null)), (SuperConstraint)ConstraintsKt.boundTo((SuperConstraint)((SuperConstraint)new RelativeConstraint(1.0f)), (UIComponent)((UIComponent)this.getScrollContainer())))), (float)0.0f, (int)8, null);
        $this$animate_u24lambda_u2d0$iv.animateTo(anim$iv);
        this.getOptionsHolder().scrollToTop(false);
        this.replaceChild((UIComponent)this.getUpArrow(), (UIComponent)this.getDownArrow());
        this.setFloating(true);
        this.getOptionsHolder().unhide(true);
        this.setWidth((WidthConstraint)this.expandedWidth);
    }

    public final void collapse(boolean unHover, boolean instantly) {
        if (this.active) {
            this.replaceChild((UIComponent)this.getDownArrow(), (UIComponent)this.getUpArrow());
        }
        this.active = false;
        if (instantly) {
            this.setHeight((HeightConstraint)UtilitiesKt.pixels$default((Number)20, (boolean)false, (boolean)false, (int)3, null));
            DropDown.collapse$animationComplete(this);
        } else {
            AnimatingConstraints anim$iv;
            UIComponent uIComponent;
            UIComponent $this$animate$iv = (UIComponent)this;
            boolean $i$f$animate = false;
            UIComponent $this$animate_u24lambda_u2d0$iv = uIComponent = $this$animate$iv;
            boolean bl = false;
            AnimatingConstraints $this$collapse_u24lambda_u2d13 = anim$iv = $this$animate_u24lambda_u2d0$iv.makeAnimation();
            boolean bl2 = false;
            AnimatingConstraints.setHeightAnimation$default((AnimatingConstraints)$this$collapse_u24lambda_u2d13, (AnimationStrategy)((AnimationStrategy)Animations.OUT_SIN), (float)0.35f, (HeightConstraint)((HeightConstraint)UtilitiesKt.pixels$default((Number)20, (boolean)false, (boolean)false, (int)3, null)), (float)0.0f, (int)8, null);
            $this$collapse_u24lambda_u2d13.onComplete((Function0)new Function0<Unit>(this){
                final /* synthetic */ DropDown this$0;
                {
                    this.this$0 = $receiver;
                    super(0, Intrinsics.Kotlin.class, "animationComplete", "collapse$animationComplete(Lxyz/apfelmus/cheeto/client/clickgui/settings/DropDown;)V", 0);
                }

                public final void invoke() {
                    DropDown.access$collapse$animationComplete(this.this$0);
                }
            });
            $this$animate_u24lambda_u2d0$iv.animateTo(anim$iv);
        }
        if (unHover) {
            this.unHoverText((UIComponent)this.getCurrentSelectionText());
        }
        this.setWidth((WidthConstraint)this.collapsedWidth);
    }

    public static /* synthetic */ void collapse$default(DropDown dropDown, boolean bl, boolean bl2, int n, Object object) {
        if ((n & 1) != 0) {
            bl = false;
        }
        if ((n & 2) != 0) {
            bl2 = false;
        }
        dropDown.collapse(bl, bl2);
    }

    private final void hoverText(UIComponent text) {
        AnimatingConstraints anim$iv;
        UIComponent uIComponent;
        UIComponent $this$animate$iv = text;
        boolean $i$f$animate = false;
        UIComponent $this$animate_u24lambda_u2d0$iv = uIComponent = $this$animate$iv;
        boolean bl = false;
        AnimatingConstraints $this$hoverText_u24lambda_u2d14 = anim$iv = $this$animate_u24lambda_u2d0$iv.makeAnimation();
        boolean bl2 = false;
        AnimationStrategy animationStrategy = (AnimationStrategy)Animations.OUT_EXP;
        Color color = Color.WHITE;
        Intrinsics.checkNotNullExpressionValue((Object)color, (String)"WHITE");
        AnimatingConstraints.setColorAnimation$default((AnimatingConstraints)$this$hoverText_u24lambda_u2d14, (AnimationStrategy)animationStrategy, (float)0.25f, (ColorConstraint)((ColorConstraint)UtilitiesKt.toConstraint((Color)color)), (float)0.0f, (int)8, null);
        $this$animate_u24lambda_u2d0$iv.animateTo(anim$iv);
    }

    private final void unHoverText(UIComponent text) {
        AnimatingConstraints anim$iv;
        UIComponent uIComponent;
        UIComponent $this$animate$iv = text;
        boolean $i$f$animate = false;
        UIComponent $this$animate_u24lambda_u2d0$iv = uIComponent = $this$animate$iv;
        boolean bl = false;
        AnimatingConstraints $this$unHoverText_u24lambda_u2d15 = anim$iv = $this$animate_u24lambda_u2d0$iv.makeAnimation();
        boolean bl2 = false;
        AnimationStrategy animationStrategy = (AnimationStrategy)Animations.OUT_EXP;
        Color color = ColorUtils.LABEL;
        Intrinsics.checkNotNullExpressionValue((Object)color, (String)"LABEL");
        AnimatingConstraints.setColorAnimation$default((AnimatingConstraints)$this$unHoverText_u24lambda_u2d15, (AnimationStrategy)animationStrategy, (float)0.25f, (ColorConstraint)((ColorConstraint)UtilitiesKt.toConstraint((Color)color)), (float)0.0f, (int)8, null);
        $this$animate_u24lambda_u2d0$iv.animateTo(anim$iv);
    }

    /*
     * WARNING - void declaration
     */
    private final void readOptionComponents() {
        this.getOptionsHolder().clearChildren();
        Iterable $this$forEachIndexed$iv = this.mappedOptions;
        boolean $i$f$forEachIndexed = false;
        int index$iv = 0;
        for (Object item$iv : $this$forEachIndexed$iv) {
            void component;
            int n = index$iv;
            index$iv = n + 1;
            if (n < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            UIComponent uIComponent = (UIComponent)item$iv;
            int index = n;
            boolean bl = false;
            if (index == this.selected) continue;
            ComponentsKt.childOf((UIComponent)component, (UIComponent)((UIComponent)this.getOptionsHolder()));
        }
    }

    private static final void collapse$animationComplete(DropDown this$0) {
        Iterable $this$forEach$iv = this$0.mappedOptions;
        boolean $i$f$forEach = false;
        for (Object element$iv : $this$forEach$iv) {
            UIComponent it = (UIComponent)element$iv;
            boolean bl = false;
            it.setColor((ColorConstraint)UtilitiesKt.toConstraint((Color)new Color(0, 0, 0, 0)));
        }
        this$0.setFloating(false);
        this$0.getOptionsHolder().hide(true);
    }

    public static final /* synthetic */ void access$expand(DropDown $this) {
        $this.expand();
    }

    public static final /* synthetic */ void access$collapse$animationComplete(DropDown this$0) {
        DropDown.collapse$animationComplete(this$0);
    }

    static {
        KProperty[] arrkProperty = new KProperty[]{(KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(DropDown.class, "currentSelectionText", "getCurrentSelectionText()Lgg/essential/elementa/components/UIText;", 0))), (KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(DropDown.class, "downArrow", "getDownArrow()Lgg/essential/elementa/components/UIImage;", 0))), (KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(DropDown.class, "upArrow", "getUpArrow()Lgg/essential/elementa/components/UIImage;", 0))), (KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(DropDown.class, "scrollContainer", "getScrollContainer()Lgg/essential/elementa/components/UIContainer;", 0))), (KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(DropDown.class, "optionsHolder", "getOptionsHolder()Lgg/essential/elementa/components/ScrollComponent;", 0)))};
        $$delegatedProperties = arrkProperty;
    }
}

