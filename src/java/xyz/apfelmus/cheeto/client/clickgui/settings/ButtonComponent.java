/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  gg.essential.elementa.UIComponent
 *  gg.essential.elementa.UIConstraints
 *  gg.essential.elementa.components.UIRoundedRectangle
 *  gg.essential.elementa.components.UIWrappedText
 *  gg.essential.elementa.constraints.CenterConstraint
 *  gg.essential.elementa.constraints.ChildBasedSizeConstraint
 *  gg.essential.elementa.constraints.ColorConstraint
 *  gg.essential.elementa.constraints.HeightConstraint
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
 *  gg.essential.elementa.events.UIClickEvent
 *  gg.essential.elementa.font.DefaultFonts
 *  gg.essential.elementa.state.BasicState
 *  gg.essential.elementa.state.State
 *  gg.essential.elementa.utils.ExtensionsKt
 *  gg.essential.universal.USound
 *  gg.essential.vigilance.data.CallablePropertyValue
 *  gg.essential.vigilance.data.PropertyData
 *  gg.essential.vigilance.data.PropertyValue
 *  gg.essential.vigilance.gui.ExpandingClickEffect
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.functions.Function0
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
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
import gg.essential.elementa.components.UIRoundedRectangle;
import gg.essential.elementa.components.UIWrappedText;
import gg.essential.elementa.constraints.CenterConstraint;
import gg.essential.elementa.constraints.ChildBasedSizeConstraint;
import gg.essential.elementa.constraints.ColorConstraint;
import gg.essential.elementa.constraints.HeightConstraint;
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
import gg.essential.elementa.events.UIClickEvent;
import gg.essential.elementa.font.DefaultFonts;
import gg.essential.elementa.state.BasicState;
import gg.essential.elementa.state.State;
import gg.essential.elementa.utils.ExtensionsKt;
import gg.essential.universal.USound;
import gg.essential.vigilance.data.CallablePropertyValue;
import gg.essential.vigilance.data.PropertyData;
import gg.essential.vigilance.data.PropertyValue;
import gg.essential.vigilance.gui.ExpandingClickEffect;
import java.awt.Color;
import kotlin.Metadata;
import kotlin.Unit;
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
import xyz.apfelmus.cheeto.client.clickgui.settings.ButtonComponent;
import xyz.apfelmus.cheeto.client.clickgui.settings.SettingComponent;
import xyz.apfelmus.cheeto.client.utils.client.ColorUtils;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
@Metadata(mv={1, 6, 0}, k=1, xi=48, d1={"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u001b\b\u0016\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006B\u001f\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u001c\u001a\u00020\u00002\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u001bJ\u0006\u0010\u0017\u001a\u00020\u0003J\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0003R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0011\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0012\u0010\u000eR\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0019\u0010\u0010\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00030\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2={"Lxyz/apfelmus/cheeto/client/clickgui/settings/ButtonComponent;", "Lxyz/apfelmus/cheeto/client/clickgui/settings/SettingComponent;", "placeholder", "", "data", "Lgg/essential/vigilance/data/PropertyData;", "(Ljava/lang/String;Lgg/essential/vigilance/data/PropertyData;)V", "callback", "Lkotlin/Function0;", "", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;)V", "container", "Lgg/essential/elementa/components/UIRoundedRectangle;", "getContainer", "()Lgg/essential/elementa/components/UIRoundedRectangle;", "container$delegate", "Lkotlin/properties/ReadWriteProperty;", "contentContainer", "getContentContainer", "contentContainer$delegate", "listener", "text", "Lgg/essential/elementa/components/UIWrappedText;", "getText", "()Lgg/essential/elementa/components/UIWrappedText;", "text$delegate", "textState", "Lgg/essential/elementa/state/State;", "bindText", "newTextState", "setText", "Companion", "Cheeto"})
public final class ButtonComponent
extends SettingComponent {
    @NotNull
    public static final Companion Companion;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    @NotNull
    private final Function0<Unit> callback;
    @NotNull
    private State<String> textState;
    @NotNull
    private Function0<Unit> listener;
    @NotNull
    private final ReadWriteProperty container$delegate;
    @NotNull
    private final ReadWriteProperty contentContainer$delegate;
    @NotNull
    private final ReadWriteProperty text$delegate;

    /*
     * WARNING - void declaration
     */
    public ButtonComponent(@Nullable String placeholder, @NotNull Function0<Unit> callback) {
        void $this$text_delegate_u24lambda_u2d3;
        UIConstraints $this$contentContainer_delegate_u24lambda_u2d2;
        UIConstraints $this$container_delegate_u24lambda_u2d1;
        UIComponent uIComponent;
        CharSequence charSequence;
        ButtonComponent buttonComponent;
        CharSequence charSequence2;
        Intrinsics.checkNotNullParameter(callback, (String)"callback");
        this.callback = callback;
        ButtonComponent buttonComponent2 = this;
        String string = placeholder;
        if (string == null) {
            string = "";
        }
        if ((charSequence2 = (CharSequence)string).length() == 0) {
            buttonComponent = buttonComponent2;
            boolean bl = false;
            charSequence = "Activate";
            buttonComponent2 = buttonComponent;
        } else {
            charSequence = charSequence2;
        }
        CharSequence charSequence3 = charSequence;
        buttonComponent2.textState = (State)new BasicState((Object)charSequence3).map((Function1)textState.2.INSTANCE);
        this.listener = this.textState.onSetValue((Function1)new Function1<String, Unit>(this){
            final /* synthetic */ ButtonComponent this$0;
            {
                this.this$0 = $receiver;
                super(1);
            }

            public final void invoke(@NotNull String it) {
                Intrinsics.checkNotNullParameter((Object)it, (String)"it");
                ButtonComponent.access$getText(this.this$0).setText((String)ButtonComponent.access$getTextState$p(this.this$0).get());
            }
        });
        Object $this$constrain$iv = (UIComponent)new UIRoundedRectangle(2.5f);
        boolean $i$f$constrain = false;
        UIComponent $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
        boolean bl = false;
        UIConstraints uIConstraints = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        buttonComponent = this;
        boolean bl2 = false;
        $this$container_delegate_u24lambda_u2d1.setWidth((WidthConstraint)ConstraintsKt.plus((SuperConstraint)((SuperConstraint)new ChildBasedSizeConstraint(0.0f, 1, null)), (SuperConstraint)((SuperConstraint)UtilitiesKt.pixels$default((Number)2, (boolean)false, (boolean)false, (int)3, null))));
        $this$container_delegate_u24lambda_u2d1.setHeight((HeightConstraint)ConstraintsKt.plus((SuperConstraint)((SuperConstraint)new ChildBasedSizeConstraint(0.0f, 1, null)), (SuperConstraint)((SuperConstraint)UtilitiesKt.pixels$default((Number)2, (boolean)false, (boolean)false, (int)3, null))));
        Color color = ColorUtils.M_BORDER;
        Intrinsics.checkNotNullExpressionValue((Object)color, (String)"M_BORDER");
        $this$container_delegate_u24lambda_u2d1.setColor((ColorConstraint)UtilitiesKt.toConstraint((Color)color));
        buttonComponent.container$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.childOf((UIComponent)uIComponent, (UIComponent)((UIComponent)this)), (Object)((Object)this), $$delegatedProperties[0]);
        $this$constrain$iv = (UIComponent)new UIRoundedRectangle(2.5f);
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
        bl = false;
        $this$container_delegate_u24lambda_u2d1 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        buttonComponent = this;
        boolean bl3 = false;
        $this$contentContainer_delegate_u24lambda_u2d2.setX((XConstraint)UtilitiesKt.pixel$default((Number)1, (boolean)false, (boolean)false, (int)3, null));
        $this$contentContainer_delegate_u24lambda_u2d2.setY((YConstraint)UtilitiesKt.pixel$default((Number)1, (boolean)false, (boolean)false, (int)3, null));
        $this$contentContainer_delegate_u24lambda_u2d2.setWidth((WidthConstraint)ConstraintsKt.plus((SuperConstraint)((SuperConstraint)new ChildBasedSizeConstraint(0.0f, 1, null)), (SuperConstraint)((SuperConstraint)UtilitiesKt.pixels$default((Number)20, (boolean)false, (boolean)false, (int)3, null))));
        $this$contentContainer_delegate_u24lambda_u2d2.setHeight((HeightConstraint)ConstraintsKt.plus((SuperConstraint)((SuperConstraint)new ChildBasedSizeConstraint(0.0f, 1, null)), (SuperConstraint)((SuperConstraint)UtilitiesKt.pixels$default((Number)10, (boolean)false, (boolean)false, (int)3, null))));
        color = ColorUtils.MENU_BG;
        Intrinsics.checkNotNullExpressionValue((Object)color, (String)"MENU_BG");
        $this$contentContainer_delegate_u24lambda_u2d2.setColor((ColorConstraint)UtilitiesKt.toConstraint((Color)color));
        buttonComponent.contentContainer$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.childOf((UIComponent)uIComponent, (UIComponent)((UIComponent)this.getContainer())), (Object)((Object)this), $$delegatedProperties[1]);
        $this$constrain$iv = (UIComponent)new UIWrappedText((String)this.textState.get(), false, null, false, true, 0.0f, null, 110, null);
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
        bl = false;
        $this$contentContainer_delegate_u24lambda_u2d2 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        buttonComponent = this;
        boolean bl4 = false;
        $this$text_delegate_u24lambda_u2d3.setX((XConstraint)new CenterConstraint());
        $this$text_delegate_u24lambda_u2d3.setY((YConstraint)new CenterConstraint());
        $this$text_delegate_u24lambda_u2d3.setWidth((WidthConstraint)ConstraintsKt.coerceAtMost((SuperConstraint)((SuperConstraint)$this$text_delegate_u24lambda_u2d3.getWidth()), (SuperConstraint)((SuperConstraint)UtilitiesKt.pixels$default((Number)300, (boolean)false, (boolean)false, (int)3, null))));
        $this$text_delegate_u24lambda_u2d3.setHeight((HeightConstraint)UtilitiesKt.pixels$default((Number)10, (boolean)false, (boolean)false, (int)3, null));
        color = ColorUtils.LABEL;
        Intrinsics.checkNotNullExpressionValue((Object)color, (String)"LABEL");
        $this$text_delegate_u24lambda_u2d3.setColor((ColorConstraint)UtilitiesKt.toConstraint((Color)color));
        $this$text_delegate_u24lambda_u2d3.setFontProvider(DefaultFonts.getVANILLA_FONT_RENDERER());
        buttonComponent.text$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.childOf((UIComponent)uIComponent, (UIComponent)((UIComponent)this.getContentContainer())), (Object)((Object)this), $$delegatedProperties[2]);
        $this$constrain$iv = (UIComponent)this;
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
        bl = false;
        UIConstraints $this$_init__u24lambda_u2d4 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        boolean bl5 = false;
        $this$_init__u24lambda_u2d4.setWidth((WidthConstraint)new ChildBasedSizeConstraint(0.0f, 1, null));
        $this$_init__u24lambda_u2d4.setHeight((HeightConstraint)new ChildBasedSizeConstraint(0.0f, 1, null));
        $this$constrain$iv = ColorUtils.SELECTED;
        Intrinsics.checkNotNullExpressionValue((Object)$this$constrain$iv, (String)"SELECTED");
        this.enableEffect((Effect)new ExpandingClickEffect(ExtensionsKt.withAlpha((Color)$this$constrain$iv, (float)0.5f), 0.0f, (UIComponent)this.getContentContainer(), 2, null));
        UIComponent $this$onLeftClick$iv = this.getContainer().onMouseEnter((Function1)new Function1<UIComponent, Unit>(){

            public final void invoke(@NotNull UIComponent $this$onMouseEnter) {
                AnimatingConstraints anim$iv;
                UIComponent uIComponent;
                Intrinsics.checkNotNullParameter((Object)$this$onMouseEnter, (String)"$this$onMouseEnter");
                UIComponent $this$animate$iv = (UIComponent)this.getContainer();
                boolean $i$f$animate = false;
                UIComponent $this$animate_u24lambda_u2d0$iv = uIComponent = $this$animate$iv;
                boolean bl = false;
                AnimatingConstraints $this$invoke_u24lambda_u2d0 = anim$iv = $this$animate_u24lambda_u2d0$iv.makeAnimation();
                boolean bl2 = false;
                AnimationStrategy animationStrategy = (AnimationStrategy)Animations.OUT_EXP;
                Color color = ColorUtils.SELECTED;
                Intrinsics.checkNotNullExpressionValue((Object)color, (String)"SELECTED");
                AnimatingConstraints.setColorAnimation$default((AnimatingConstraints)$this$invoke_u24lambda_u2d0, (AnimationStrategy)animationStrategy, (float)0.5f, (ColorConstraint)((ColorConstraint)UtilitiesKt.toConstraint((Color)color)), (float)0.0f, (int)8, null);
                $this$animate_u24lambda_u2d0$iv.animateTo(anim$iv);
            }
        }).onMouseLeave((Function1)new Function1<UIComponent, Unit>(){

            public final void invoke(@NotNull UIComponent $this$onMouseLeave) {
                AnimatingConstraints anim$iv;
                UIComponent uIComponent;
                Intrinsics.checkNotNullParameter((Object)$this$onMouseLeave, (String)"$this$onMouseLeave");
                UIComponent $this$animate$iv = (UIComponent)this.getContainer();
                boolean $i$f$animate = false;
                UIComponent $this$animate_u24lambda_u2d0$iv = uIComponent = $this$animate$iv;
                boolean bl = false;
                AnimatingConstraints $this$invoke_u24lambda_u2d0 = anim$iv = $this$animate_u24lambda_u2d0$iv.makeAnimation();
                boolean bl2 = false;
                AnimationStrategy animationStrategy = (AnimationStrategy)Animations.OUT_EXP;
                Color color = ColorUtils.M_BORDER;
                Intrinsics.checkNotNullExpressionValue((Object)color, (String)"M_BORDER");
                AnimatingConstraints.setColorAnimation$default((AnimatingConstraints)$this$invoke_u24lambda_u2d0, (AnimationStrategy)animationStrategy, (float)0.5f, (ColorConstraint)((ColorConstraint)UtilitiesKt.toConstraint((Color)color)), (float)0.0f, (int)8, null);
                $this$animate_u24lambda_u2d0$iv.animateTo(anim$iv);
            }
        });
        boolean $i$f$onLeftClick = false;
        $this$onLeftClick$iv.onMouseClick((Function2)new Function2<UIComponent, UIClickEvent, Unit>(this){
            final /* synthetic */ ButtonComponent this$0;
            {
                this.this$0 = buttonComponent;
                super(2);
            }

            public final void invoke(@NotNull UIComponent $this$onMouseClick, @NotNull UIClickEvent it) {
                Intrinsics.checkNotNullParameter((Object)$this$onMouseClick, (String)"$this$onMouseClick");
                Intrinsics.checkNotNullParameter((Object)it, (String)"it");
                if (it.getMouseButton() == 0) {
                    UIClickEvent uIClickEvent = it;
                    UIComponent $this$_init__u24lambda_u2d5 = $this$onMouseClick;
                    boolean bl = false;
                    USound.playButtonPress$default((USound)USound.INSTANCE, (float)0.0f, (int)1, null);
                    ButtonComponent.access$getCallback$p(this.this$0).invoke();
                }
            }
        });
    }

    public /* synthetic */ ButtonComponent(String string, Function0 function0, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 1) != 0) {
            string = null;
        }
        this(string, (Function0<Unit>)function0);
    }

    private final UIRoundedRectangle getContainer() {
        return (UIRoundedRectangle)this.container$delegate.getValue((Object)this, $$delegatedProperties[0]);
    }

    private final UIRoundedRectangle getContentContainer() {
        return (UIRoundedRectangle)this.contentContainer$delegate.getValue((Object)this, $$delegatedProperties[1]);
    }

    private final UIWrappedText getText() {
        return (UIWrappedText)this.text$delegate.getValue((Object)this, $$delegatedProperties[2]);
    }

    @NotNull
    public final ButtonComponent bindText(@NotNull State<String> newTextState) {
        ButtonComponent buttonComponent;
        Intrinsics.checkNotNullParameter(newTextState, (String)"newTextState");
        ButtonComponent $this$bindText_u24lambda_u2d6 = buttonComponent = this;
        boolean bl = false;
        $this$bindText_u24lambda_u2d6.listener.invoke();
        $this$bindText_u24lambda_u2d6.textState = newTextState;
        $this$bindText_u24lambda_u2d6.getText().bindText((State)$this$bindText_u24lambda_u2d6.textState.map((Function1)bindText.1.1.INSTANCE));
        $this$bindText_u24lambda_u2d6.listener = $this$bindText_u24lambda_u2d6.textState.onSetValue((Function1)new Function1<String, Unit>($this$bindText_u24lambda_u2d6){
            final /* synthetic */ ButtonComponent $this_apply;
            {
                this.$this_apply = $receiver;
                super(1);
            }

            public final void invoke(@NotNull String it) {
                Intrinsics.checkNotNullParameter((Object)it, (String)"it");
                ButtonComponent.access$getText(this.$this_apply).setText((String)ButtonComponent.access$getTextState$p(this.$this_apply).get());
            }
        });
        return buttonComponent;
    }

    @NotNull
    public final String getText() {
        return (String)this.textState.get();
    }

    @NotNull
    public final ButtonComponent setText(@NotNull String text) {
        ButtonComponent buttonComponent;
        Intrinsics.checkNotNullParameter((Object)text, (String)"text");
        ButtonComponent $this$setText_u24lambda_u2d7 = buttonComponent = this;
        boolean bl = false;
        $this$setText_u24lambda_u2d7.textState.set((Object)text);
        return buttonComponent;
    }

    public ButtonComponent(@Nullable String placeholder, @NotNull PropertyData data) {
        Intrinsics.checkNotNullParameter((Object)data, (String)"data");
        this(placeholder, (Function0<Unit>)ButtonComponent.Companion.callbackFromPropertyData(data));
    }

    public /* synthetic */ ButtonComponent(String string, PropertyData propertyData, int n, DefaultConstructorMarker defaultConstructorMarker) {
        if ((n & 1) != 0) {
            string = null;
        }
        this(string, propertyData);
    }

    public static final /* synthetic */ Function0 access$getCallback$p(ButtonComponent $this) {
        return $this.callback;
    }

    public static final /* synthetic */ UIWrappedText access$getText(ButtonComponent $this) {
        return $this.getText();
    }

    public static final /* synthetic */ State access$getTextState$p(ButtonComponent $this) {
        return $this.textState;
    }

    static {
        KProperty[] arrkProperty = new KProperty[]{(KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(ButtonComponent.class, "container", "getContainer()Lgg/essential/elementa/components/UIRoundedRectangle;", 0))), (KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(ButtonComponent.class, "contentContainer", "getContentContainer()Lgg/essential/elementa/components/UIRoundedRectangle;", 0))), (KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(ButtonComponent.class, "text", "getText()Lgg/essential/elementa/components/UIWrappedText;", 0)))};
        $$delegatedProperties = arrkProperty;
        Companion = new Companion(null);
    }

    @Metadata(mv={1, 6, 0}, k=1, xi=48, d1={"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u00a8\u0006\b"}, d2={"Lxyz/apfelmus/cheeto/client/clickgui/settings/ButtonComponent$Companion;", "", "()V", "callbackFromPropertyData", "Lkotlin/Function0;", "", "data", "Lgg/essential/vigilance/data/PropertyData;", "Cheeto"})
    public static final class Companion {
        private Companion() {
        }

        private final Function0<Unit> callbackFromPropertyData(PropertyData data) {
            PropertyValue value = data.getValue();
            if (!(value instanceof CallablePropertyValue)) {
                throw new IllegalStateException();
            }
            return (Function0)new Function0<Unit>(value, data){
                final /* synthetic */ PropertyValue $value;
                final /* synthetic */ PropertyData $data;
                {
                    this.$value = $value;
                    this.$data = $data;
                    super(0);
                }

                public final void invoke() {
                    ((CallablePropertyValue)this.$value).invoke(this.$data.getInstance());
                }
            };
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}

