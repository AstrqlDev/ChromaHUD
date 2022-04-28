/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  gg.essential.elementa.UIComponent
 *  gg.essential.elementa.UIConstraints
 *  gg.essential.elementa.components.UIContainer
 *  gg.essential.elementa.components.UIText
 *  gg.essential.elementa.components.Window
 *  gg.essential.elementa.constraints.CenterConstraint
 *  gg.essential.elementa.constraints.ChildBasedSizeConstraint
 *  gg.essential.elementa.constraints.ColorConstraint
 *  gg.essential.elementa.constraints.FillConstraint
 *  gg.essential.elementa.constraints.HeightConstraint
 *  gg.essential.elementa.constraints.SiblingConstraint
 *  gg.essential.elementa.constraints.SuperConstraint
 *  gg.essential.elementa.constraints.WidthConstraint
 *  gg.essential.elementa.constraints.XConstraint
 *  gg.essential.elementa.constraints.YConstraint
 *  gg.essential.elementa.dsl.ComponentsKt
 *  gg.essential.elementa.dsl.ConstraintsKt
 *  gg.essential.elementa.dsl.UtilitiesKt
 *  gg.essential.elementa.effects.Effect
 *  gg.essential.elementa.effects.OutlineEffect
 *  gg.essential.elementa.events.UIClickEvent
 *  gg.essential.elementa.font.DefaultFonts
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.functions.Function0
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.functions.Function3
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.PropertyReference1
 *  kotlin.jvm.internal.PropertyReference1Impl
 *  kotlin.jvm.internal.Ref$BooleanRef
 *  kotlin.jvm.internal.Reflection
 *  kotlin.properties.ReadWriteProperty
 *  kotlin.reflect.KProperty
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 *  org.lwjgl.input.Keyboard
 */
package xyz.apfelmus.cheeto.client.clickgui;

import gg.essential.elementa.UIComponent;
import gg.essential.elementa.UIConstraints;
import gg.essential.elementa.components.UIContainer;
import gg.essential.elementa.components.UIText;
import gg.essential.elementa.components.Window;
import gg.essential.elementa.constraints.CenterConstraint;
import gg.essential.elementa.constraints.ChildBasedSizeConstraint;
import gg.essential.elementa.constraints.ColorConstraint;
import gg.essential.elementa.constraints.FillConstraint;
import gg.essential.elementa.constraints.HeightConstraint;
import gg.essential.elementa.constraints.SiblingConstraint;
import gg.essential.elementa.constraints.SuperConstraint;
import gg.essential.elementa.constraints.WidthConstraint;
import gg.essential.elementa.constraints.XConstraint;
import gg.essential.elementa.constraints.YConstraint;
import gg.essential.elementa.dsl.ComponentsKt;
import gg.essential.elementa.dsl.ConstraintsKt;
import gg.essential.elementa.dsl.UtilitiesKt;
import gg.essential.elementa.effects.Effect;
import gg.essential.elementa.effects.OutlineEffect;
import gg.essential.elementa.events.UIClickEvent;
import gg.essential.elementa.font.DefaultFonts;
import java.awt.Color;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.input.Keyboard;
import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cheeto.client.clickgui.settings.ButtonComponent;
import xyz.apfelmus.cheeto.client.clickgui.settings.CheckboxComponent;
import xyz.apfelmus.cheeto.client.utils.client.ColorUtils;

@Metadata(mv={1, 6, 0}, k=1, xi=48, d1={"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\u00018BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t\u00a8\u0006\f"}, d2={"Lxyz/apfelmus/cheeto/client/clickgui/KeybindLabel;", "Lgg/essential/elementa/components/UIContainer;", "module", "", "window", "Lgg/essential/elementa/components/Window;", "(Ljava/lang/Object;Lgg/essential/elementa/components/Window;)V", "textContainer", "getTextContainer", "()Lgg/essential/elementa/components/UIContainer;", "textContainer$delegate", "Lkotlin/properties/ReadWriteProperty;", "Cheeto"})
public final class KeybindLabel
extends UIContainer {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    @NotNull
    private final Object module;
    @NotNull
    private final ReadWriteProperty textContainer$delegate;

    /*
     * WARNING - void declaration
     */
    public KeybindLabel(@NotNull Object module, @NotNull Window window) {
        void $this$constrain$iv;
        void var9_12;
        UIComponent uIComponent;
        void $this$textContainer_delegate_u24lambda_u2d0;
        UIComponent uIComponent2;
        Intrinsics.checkNotNullParameter((Object)module, (String)"module");
        Intrinsics.checkNotNullParameter((Object)window, (String)"window");
        this.module = module;
        Object $this$constrain$iv2 = (UIComponent)new UIContainer();
        boolean $i$f$constrain = false;
        UIComponent $this$constrain_u24lambda_u2d0$iv = uIComponent2 = $this$constrain$iv2;
        boolean bl = false;
        UIConstraints uIConstraints = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        KeybindLabel keybindLabel = this;
        boolean bl2 = false;
        $this$textContainer_delegate_u24lambda_u2d0.setY((YConstraint)new CenterConstraint());
        $this$textContainer_delegate_u24lambda_u2d0.setWidth((WidthConstraint)new ChildBasedSizeConstraint(0.0f, 1, null));
        $this$textContainer_delegate_u24lambda_u2d0.setHeight((HeightConstraint)new ChildBasedSizeConstraint(0.0f, 1, null));
        keybindLabel.textContainer$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.childOf((UIComponent)uIComponent2, (UIComponent)((UIComponent)this)), (Object)((Object)this), $$delegatedProperties[0]);
        $this$constrain$iv2 = (UIComponent)new UIText("Enable", false, null, 6, null);
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = uIComponent2 = $this$constrain$iv2;
        bl = false;
        UIConstraints $this$_init__u24lambda_u2d1 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        boolean bl3 = false;
        $this$_init__u24lambda_u2d1.setX((XConstraint)UtilitiesKt.pixels$default((Number)10, (boolean)false, (boolean)false, (int)3, null));
        $this$_init__u24lambda_u2d1.setY((YConstraint)new CenterConstraint());
        $this$_init__u24lambda_u2d1.setTextScale((HeightConstraint)UtilitiesKt.pixels$default((Number)Float.valueOf(1.5f), (boolean)false, (boolean)false, (int)3, null));
        Color color = ColorUtils.LABEL;
        Intrinsics.checkNotNullExpressionValue((Object)color, (String)"LABEL");
        $this$_init__u24lambda_u2d1.setColor((ColorConstraint)UtilitiesKt.toConstraint((Color)color));
        $this$_init__u24lambda_u2d1.setFontProvider(DefaultFonts.getVANILLA_FONT_RENDERER());
        ComponentsKt.childOf((UIComponent)uIComponent2, (UIComponent)((UIComponent)this.getTextContainer()));
        $this$constrain$iv2 = (UIComponent)this;
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = uIComponent2 = $this$constrain$iv2;
        bl = false;
        UIConstraints $this$_init__u24lambda_u2d2 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        boolean bl4 = false;
        $this$_init__u24lambda_u2d2.setX((XConstraint)UtilitiesKt.pixels$default((Number)10, (boolean)false, (boolean)false, (int)3, null));
        $this$_init__u24lambda_u2d2.setY((YConstraint)ConstraintsKt.plus((SuperConstraint)((SuperConstraint)new SiblingConstraint(0.0f, false, 3, null)), (SuperConstraint)((SuperConstraint)UtilitiesKt.pixels$default((Number)10, (boolean)false, (boolean)false, (int)3, null))));
        $this$_init__u24lambda_u2d2.setWidth((WidthConstraint)ConstraintsKt.minus((SuperConstraint)((SuperConstraint)new FillConstraint(false)), (SuperConstraint)((SuperConstraint)UtilitiesKt.pixels$default((Number)10, (boolean)false, (boolean)false, (int)3, null))));
        $this$_init__u24lambda_u2d2.setHeight((HeightConstraint)UtilitiesKt.pixels$default((Number)45, (boolean)false, (boolean)false, (int)3, null));
        $this$constrain$iv2 = ColorUtils.M_BORDER;
        Intrinsics.checkNotNullExpressionValue((Object)$this$constrain$iv2, (String)"M_BORDER");
        ComponentsKt.effect((UIComponent)uIComponent2, (Effect)((Effect)new OutlineEffect((Color)$this$constrain$iv2, 1.0f, false, false, null, 28, null)));
        CheckboxComponent enable = new CheckboxComponent(CF4M.INSTANCE.moduleManager.isEnabled(this.module));
        UIComponent $this$constrain$iv3 = (UIComponent)enable;
        boolean $i$f$constrain2 = false;
        UIComponent $this$constrain_u24lambda_u2d0$iv2 = uIComponent = $this$constrain$iv3;
        $i$a$-apply-ComponentsKt$constrain$1$iv = false;
        UIConstraints $this$_init__u24lambda_u2d3 = $this$constrain_u24lambda_u2d0$iv2.getConstraints();
        boolean bl5 = false;
        $this$_init__u24lambda_u2d3.setX((XConstraint)new SiblingConstraint(20.0f, false, 2, null));
        $this$_init__u24lambda_u2d3.setY((YConstraint)UtilitiesKt.pixels$default((Number)12, (boolean)false, (boolean)false, (int)3, null));
        ComponentsKt.childOf((UIComponent)uIComponent, (UIComponent)((UIComponent)this));
        enable.onValueChange((Function1<Object, Unit>)((Function1)new Function1<Object, Unit>(){

            public final void invoke(@Nullable Object it) {
                Object object = it;
                if (object == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
                }
                CF4M.INSTANCE.moduleManager.setEnabled(module, (boolean)((Boolean)object));
            }
        }));
        String key = Keyboard.getKeyName((int)CF4M.INSTANCE.moduleManager.getKey(this.module));
        final Ref.BooleanRef changing = new Ref.BooleanRef();
        UIComponent uIComponent3 = (UIComponent)new ButtonComponent(Intrinsics.stringPlus((String)"Keybind: ", (Object)key), (Function0<Unit>)((Function0)new Function0<Unit>(changing){
            final /* synthetic */ Ref.BooleanRef $changing;
            {
                this.$changing = $changing;
                super(0);
            }

            public final void invoke() {
                this.$changing.element = !this.$changing.element;
            }
        }));
        boolean $i$f$constrain3 = false;
        void $this$constrain_u24lambda_u2d0$iv3 = var9_12 = $this$constrain$iv;
        $i$a$-apply-ComponentsKt$constrain$1$iv = false;
        UIConstraints $this$_init__u24lambda_u2d4 = $this$constrain_u24lambda_u2d0$iv3.getConstraints();
        boolean bl6 = false;
        $this$_init__u24lambda_u2d4.setX((XConstraint)UtilitiesKt.pixels$default((Number)12, (boolean)true, (boolean)false, (int)2, null));
        $this$_init__u24lambda_u2d4.setY((YConstraint)UtilitiesKt.pixels$default((Number)12, (boolean)false, (boolean)false, (int)3, null));
        final ButtonComponent keybind2 = (ButtonComponent)ComponentsKt.childOf((UIComponent)var9_12, (UIComponent)((UIComponent)this));
        UIComponent $this$onLeftClick$iv = (UIComponent)keybind2;
        boolean $i$f$onLeftClick = false;
        $this$onLeftClick$iv.onMouseClick((Function2)new Function2<UIComponent, UIClickEvent, Unit>(changing, keybind2){
            final /* synthetic */ Ref.BooleanRef $changing$inlined;
            final /* synthetic */ ButtonComponent $keybind$inlined;
            {
                this.$changing$inlined = booleanRef;
                this.$keybind$inlined = buttonComponent;
                super(2);
            }

            public final void invoke(@NotNull UIComponent $this$onMouseClick, @NotNull UIClickEvent it) {
                Intrinsics.checkNotNullParameter((Object)$this$onMouseClick, (String)"$this$onMouseClick");
                Intrinsics.checkNotNullParameter((Object)it, (String)"it");
                if (it.getMouseButton() == 0) {
                    UIClickEvent uIClickEvent = it;
                    UIComponent $this$_init__u24lambda_u2d5 = $this$onMouseClick;
                    boolean bl = false;
                    if (this.$changing$inlined.element) {
                        this.$keybind$inlined.setText("<Press a Key>");
                    }
                }
            }
        });
        window.onKeyType((Function3)new Function3<UIComponent, Character, Integer, Unit>(){

            public final void invoke(@NotNull UIComponent $this$onKeyType, char typedChar, int keyCode) {
                Intrinsics.checkNotNullParameter((Object)$this$onKeyType, (String)"$this$onKeyType");
                if (changing.element) {
                    if (keyCode == 14) {
                        keybind2.setText("Keybind: NONE");
                        CF4M.INSTANCE.moduleManager.setKey(module, 0);
                    } else if (keyCode != 1) {
                        keybind2.setText(Intrinsics.stringPlus((String)"Keybind: ", (Object)Keyboard.getKeyName((int)keyCode)));
                        CF4M.INSTANCE.moduleManager.setKey(module, keyCode);
                    }
                    changing.element = false;
                }
            }
        });
    }

    private final UIContainer getTextContainer() {
        return (UIContainer)this.textContainer$delegate.getValue((Object)this, $$delegatedProperties[0]);
    }

    static {
        KProperty[] arrkProperty = new KProperty[]{(KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(KeybindLabel.class, "textContainer", "getTextContainer()Lgg/essential/elementa/components/UIContainer;", 0)))};
        $$delegatedProperties = arrkProperty;
    }
}

