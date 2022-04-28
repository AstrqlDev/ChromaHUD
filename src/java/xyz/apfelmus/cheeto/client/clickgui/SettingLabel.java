/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  gg.essential.elementa.UIComponent
 *  gg.essential.elementa.UIConstraints
 *  gg.essential.elementa.components.UIContainer
 *  gg.essential.elementa.components.UIText
 *  gg.essential.elementa.components.UIWrappedText
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
 *  gg.essential.elementa.font.DefaultFonts
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.PropertyReference1
 *  kotlin.jvm.internal.PropertyReference1Impl
 *  kotlin.jvm.internal.Reflection
 *  kotlin.properties.ReadWriteProperty
 *  kotlin.reflect.KProperty
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package xyz.apfelmus.cheeto.client.clickgui;

import gg.essential.elementa.UIComponent;
import gg.essential.elementa.UIConstraints;
import gg.essential.elementa.components.UIContainer;
import gg.essential.elementa.components.UIText;
import gg.essential.elementa.components.UIWrappedText;
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
import gg.essential.elementa.font.DefaultFonts;
import java.awt.Color;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cheeto.client.clickgui.settings.DecimalSliderComponent;
import xyz.apfelmus.cheeto.client.clickgui.settings.SelectorComponent;
import xyz.apfelmus.cheeto.client.clickgui.settings.SettingComponent;
import xyz.apfelmus.cheeto.client.clickgui.settings.SliderComponent;
import xyz.apfelmus.cheeto.client.clickgui.settings.SwitchComponent;
import xyz.apfelmus.cheeto.client.clickgui.settings.TextComponent;
import xyz.apfelmus.cheeto.client.settings.BooleanSetting;
import xyz.apfelmus.cheeto.client.settings.FloatSetting;
import xyz.apfelmus.cheeto.client.settings.IntegerSetting;
import xyz.apfelmus.cheeto.client.settings.ModeSetting;
import xyz.apfelmus.cheeto.client.settings.StringSetting;
import xyz.apfelmus.cheeto.client.utils.client.ColorUtils;

@Metadata(mv={1, 6, 0}, k=1, xi=48, d1={"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00018BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u000b"}, d2={"Lxyz/apfelmus/cheeto/client/clickgui/SettingLabel;", "Lgg/essential/elementa/components/UIContainer;", "module", "", "setting", "(Ljava/lang/Object;Ljava/lang/Object;)V", "textContainer", "getTextContainer", "()Lgg/essential/elementa/components/UIContainer;", "textContainer$delegate", "Lkotlin/properties/ReadWriteProperty;", "Cheeto"})
public final class SettingLabel
extends UIContainer {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    @NotNull
    private final Object module;
    @NotNull
    private final Object setting;
    @NotNull
    private final ReadWriteProperty textContainer$delegate;

    /*
     * WARNING - void declaration
     */
    public SettingLabel(@NotNull Object module, @NotNull Object setting) {
        UIComponent uIComponent;
        SettingComponent settingComponent;
        void $this$textContainer_delegate_u24lambda_u2d0;
        Object object;
        Intrinsics.checkNotNullParameter((Object)module, (String)"module");
        Intrinsics.checkNotNullParameter((Object)setting, (String)"setting");
        this.module = module;
        this.setting = setting;
        Object $this$constrain$iv = (UIComponent)new UIContainer();
        boolean $i$f$constrain = false;
        UIComponent $this$constrain_u24lambda_u2d0$iv = object = $this$constrain$iv;
        boolean bl = false;
        UIConstraints uIConstraints = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        SettingLabel settingLabel = this;
        boolean bl2 = false;
        $this$textContainer_delegate_u24lambda_u2d0.setY((YConstraint)new CenterConstraint());
        $this$textContainer_delegate_u24lambda_u2d0.setWidth((WidthConstraint)UtilitiesKt.percent((Number)75));
        $this$textContainer_delegate_u24lambda_u2d0.setHeight((HeightConstraint)new ChildBasedSizeConstraint(0.0f, 1, null));
        settingLabel.textContainer$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.childOf((UIComponent)object, (UIComponent)((UIComponent)this)), (Object)((Object)this), $$delegatedProperties[0]);
        $this$constrain$iv = CF4M.INSTANCE.settingManager.getName(this.module, this.setting);
        Intrinsics.checkNotNullExpressionValue((Object)$this$constrain$iv, (String)"INSTANCE.settingManager.getName(module, setting)");
        $this$constrain$iv = (UIComponent)new UIText((String)$this$constrain$iv, false, null, 6, null);
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = object = $this$constrain$iv;
        bl = false;
        UIConstraints $this$_init__u24lambda_u2d1 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        boolean bl3 = false;
        $this$_init__u24lambda_u2d1.setX((XConstraint)UtilitiesKt.pixels$default((Number)10, (boolean)false, (boolean)false, (int)3, null));
        Object object2 = CF4M.INSTANCE.settingManager.getDescription(this.module, this.setting);
        Intrinsics.checkNotNullExpressionValue((Object)object2, (String)"INSTANCE.settingManager.\u2026cription(module, setting)");
        $this$_init__u24lambda_u2d1.setY(((CharSequence)object2).length() == 0 ? (YConstraint)new CenterConstraint() : (YConstraint)UtilitiesKt.pixels$default((Number)0, (boolean)false, (boolean)false, (int)3, null));
        $this$_init__u24lambda_u2d1.setTextScale((HeightConstraint)UtilitiesKt.pixels$default((Number)Float.valueOf(1.5f), (boolean)false, (boolean)false, (int)3, null));
        object2 = ColorUtils.LABEL;
        Intrinsics.checkNotNullExpressionValue((Object)object2, (String)"LABEL");
        $this$_init__u24lambda_u2d1.setColor((ColorConstraint)UtilitiesKt.toConstraint((Color)object2));
        $this$_init__u24lambda_u2d1.setFontProvider(DefaultFonts.getVANILLA_FONT_RENDERER());
        ComponentsKt.childOf((UIComponent)object, (UIComponent)((UIComponent)this.getTextContainer()));
        $this$constrain$iv = CF4M.INSTANCE.settingManager.getDescription(this.module, this.setting);
        Intrinsics.checkNotNullExpressionValue((Object)$this$constrain$iv, (String)"INSTANCE.settingManager.\u2026cription(module, setting)");
        $this$constrain$iv = (UIComponent)new UIWrappedText((String)$this$constrain$iv, false, null, false, false, 0.0f, null, 126, null);
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = object = $this$constrain$iv;
        bl = false;
        UIConstraints $this$_init__u24lambda_u2d2 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        boolean bl4 = false;
        $this$_init__u24lambda_u2d2.setX((XConstraint)UtilitiesKt.pixels$default((Number)10, (boolean)false, (boolean)false, (int)3, null));
        $this$_init__u24lambda_u2d2.setY((YConstraint)ConstraintsKt.plus((SuperConstraint)((SuperConstraint)new SiblingConstraint(0.0f, false, 3, null)), (SuperConstraint)((SuperConstraint)UtilitiesKt.pixels$default((Number)2, (boolean)false, (boolean)false, (int)3, null))));
        object2 = ColorUtils.SUB_LABEL;
        Intrinsics.checkNotNullExpressionValue((Object)object2, (String)"SUB_LABEL");
        $this$_init__u24lambda_u2d2.setColor((ColorConstraint)UtilitiesKt.toConstraint((Color)object2));
        $this$_init__u24lambda_u2d2.setFontProvider(DefaultFonts.getVANILLA_FONT_RENDERER());
        ComponentsKt.childOf((UIComponent)object, (UIComponent)((UIComponent)this.getTextContainer()));
        $this$constrain$iv = (UIComponent)this;
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = object = $this$constrain$iv;
        bl = false;
        UIConstraints $this$_init__u24lambda_u2d3 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        boolean bl5 = false;
        $this$_init__u24lambda_u2d3.setX((XConstraint)UtilitiesKt.pixels$default((Number)10, (boolean)false, (boolean)false, (int)3, null));
        $this$_init__u24lambda_u2d3.setY((YConstraint)ConstraintsKt.plus((SuperConstraint)((SuperConstraint)new SiblingConstraint(0.0f, false, 3, null)), (SuperConstraint)((SuperConstraint)UtilitiesKt.pixels$default((Number)10, (boolean)false, (boolean)false, (int)3, null))));
        $this$_init__u24lambda_u2d3.setWidth((WidthConstraint)ConstraintsKt.minus((SuperConstraint)((SuperConstraint)new FillConstraint(false)), (SuperConstraint)((SuperConstraint)UtilitiesKt.pixels$default((Number)10, (boolean)false, (boolean)false, (int)3, null))));
        $this$_init__u24lambda_u2d3.setHeight((HeightConstraint)UtilitiesKt.pixels$default((Number)45, (boolean)false, (boolean)false, (int)3, null));
        $this$constrain$iv = ColorUtils.M_BORDER;
        Intrinsics.checkNotNullExpressionValue((Object)$this$constrain$iv, (String)"M_BORDER");
        ComponentsKt.effect((UIComponent)object, (Effect)((Effect)new OutlineEffect((Color)$this$constrain$iv, 1.0f, false, false, null, 28, null)));
        Object $i$f$constrain2 = this.setting;
        if ($i$f$constrain2 instanceof BooleanSetting) {
            settingComponent = new SwitchComponent(((BooleanSetting)this.setting).isEnabled());
        } else if ($i$f$constrain2 instanceof IntegerSetting) {
            object = ((IntegerSetting)this.setting).getCurrent();
            Intrinsics.checkNotNullExpressionValue((Object)object, (String)"setting.current");
            int n = ((Number)object).intValue();
            object = ((IntegerSetting)this.setting).getMin();
            Intrinsics.checkNotNullExpressionValue((Object)object, (String)"setting.min");
            int n2 = ((Number)object).intValue();
            object = ((IntegerSetting)this.setting).getMax();
            Intrinsics.checkNotNullExpressionValue((Object)object, (String)"setting.max");
            settingComponent = new SliderComponent(n, n2, ((Number)object).intValue());
        } else if ($i$f$constrain2 instanceof FloatSetting) {
            object = ((FloatSetting)this.setting).getCurrent();
            Intrinsics.checkNotNullExpressionValue((Object)object, (String)"setting.current");
            float f = ((Number)object).floatValue();
            object = ((FloatSetting)this.setting).getMin();
            Intrinsics.checkNotNullExpressionValue((Object)object, (String)"setting.min");
            float f2 = ((Number)object).floatValue();
            object = ((FloatSetting)this.setting).getMax();
            Intrinsics.checkNotNullExpressionValue((Object)object, (String)"setting.max");
            settingComponent = new DecimalSliderComponent(f, f2, ((Number)object).floatValue(), 1);
        } else if ($i$f$constrain2 instanceof ModeSetting) {
            int n = ((ModeSetting)this.setting).getModes().indexOf(((ModeSetting)this.setting).getCurrent());
            object = ((ModeSetting)this.setting).getModes();
            Intrinsics.checkNotNullExpressionValue((Object)object, (String)"setting.modes");
            settingComponent = new SelectorComponent(n, (List<String>)object);
        } else if ($i$f$constrain2 instanceof StringSetting) {
            object = ((StringSetting)this.setting).getCurrent();
            Intrinsics.checkNotNullExpressionValue((Object)object, (String)"setting.current");
            settingComponent = new TextComponent((String)object, "<empty>", false, false);
        } else {
            settingComponent = new SwitchComponent(true);
        }
        SettingComponent comp = settingComponent;
        UIComponent $this$constrain$iv2 = (UIComponent)comp;
        boolean $i$f$constrain3 = false;
        UIComponent $this$constrain_u24lambda_u2d0$iv2 = uIComponent = $this$constrain$iv2;
        $i$a$-apply-ComponentsKt$constrain$1$iv = false;
        UIConstraints $this$_init__u24lambda_u2d4 = $this$constrain_u24lambda_u2d0$iv2.getConstraints();
        boolean bl6 = false;
        $this$_init__u24lambda_u2d4.setX((XConstraint)UtilitiesKt.pixels$default((Number)12, (boolean)true, (boolean)false, (int)2, null));
        $this$_init__u24lambda_u2d4.setY(comp instanceof SwitchComponent ? (YConstraint)UtilitiesKt.pixels$default((Number)18, (boolean)false, (boolean)false, (int)3, null) : (YConstraint)UtilitiesKt.pixels$default((Number)12, (boolean)false, (boolean)false, (int)3, null));
        ComponentsKt.childOf((UIComponent)uIComponent, (UIComponent)((UIComponent)this));
        comp.onValueChange((Function1<Object, Unit>)((Function1)new Function1<Object, Unit>(){

            public final void invoke(@Nullable Object it) {
                Object object = setting;
                if (object instanceof BooleanSetting) {
                    Object object2 = it;
                    if (object2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
                    }
                    ((BooleanSetting)setting).setState((Boolean)object2);
                } else if (object instanceof IntegerSetting) {
                    Object object3 = it;
                    if (object3 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
                    }
                    ((IntegerSetting)setting).setCurrent((int)((Integer)object3));
                } else if (object instanceof FloatSetting) {
                    Object object4 = it;
                    if (object4 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Float");
                    }
                    ((FloatSetting)setting).setCurrent(Float.valueOf(((Float)object4).floatValue()));
                } else if (object instanceof ModeSetting) {
                    Object object5 = it;
                    if (object5 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
                    }
                    ((ModeSetting)setting).setCurrent(((ModeSetting)setting).getModes().get((Integer)object5));
                } else if (object instanceof StringSetting) {
                    Object object6 = it;
                    if (object6 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                    }
                    ((StringSetting)setting).setCurrent((String)object6);
                }
            }
        }));
    }

    private final UIContainer getTextContainer() {
        return (UIContainer)this.textContainer$delegate.getValue((Object)this, $$delegatedProperties[0]);
    }

    static {
        KProperty[] arrkProperty = new KProperty[]{(KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(SettingLabel.class, "textContainer", "getTextContainer()Lgg/essential/elementa/components/UIContainer;", 0)))};
        $$delegatedProperties = arrkProperty;
    }
}

