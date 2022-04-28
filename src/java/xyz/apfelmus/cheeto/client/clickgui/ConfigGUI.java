/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  gg.essential.elementa.ElementaVersion
 *  gg.essential.elementa.UIComponent
 *  gg.essential.elementa.UIConstraints
 *  gg.essential.elementa.WindowScreen
 *  gg.essential.elementa.components.ScrollComponent
 *  gg.essential.elementa.components.UIBlock
 *  gg.essential.elementa.components.UIContainer
 *  gg.essential.elementa.components.UIText
 *  gg.essential.elementa.constraints.CenterConstraint
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
 *  gg.essential.elementa.state.BasicState
 *  gg.essential.elementa.state.State
 *  gg.essential.universal.GuiScale
 *  gg.essential.universal.GuiScale$Companion
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.PropertyReference1
 *  kotlin.jvm.internal.PropertyReference1Impl
 *  kotlin.jvm.internal.Reflection
 *  kotlin.properties.ReadWriteProperty
 *  kotlin.reflect.KProperty
 *  kotlin.text.StringsKt
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package xyz.apfelmus.cheeto.client.clickgui;

import gg.essential.elementa.ElementaVersion;
import gg.essential.elementa.UIComponent;
import gg.essential.elementa.UIConstraints;
import gg.essential.elementa.WindowScreen;
import gg.essential.elementa.components.ScrollComponent;
import gg.essential.elementa.components.UIBlock;
import gg.essential.elementa.components.UIContainer;
import gg.essential.elementa.components.UIText;
import gg.essential.elementa.constraints.CenterConstraint;
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
import gg.essential.elementa.state.BasicState;
import gg.essential.elementa.state.State;
import gg.essential.universal.GuiScale;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cheeto.client.clickgui.CategoryLabel;
import xyz.apfelmus.cheeto.client.clickgui.ConfigLabel;
import xyz.apfelmus.cheeto.client.clickgui.KeybindLabel;
import xyz.apfelmus.cheeto.client.clickgui.ModuleLabel;
import xyz.apfelmus.cheeto.client.clickgui.SettingLabel;
import xyz.apfelmus.cheeto.client.clickgui.settings.TextComponent;
import xyz.apfelmus.cheeto.client.configs.ClientConfig;
import xyz.apfelmus.cheeto.client.utils.client.ColorUtils;

@Metadata(mv={1, 6, 0}, k=1, xi=48, d1={"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010>\u001a\u00020?H\u0016J\b\u0010@\u001a\u00020AH\u0016J\u000e\u0010B\u001a\u00020A2\u0006\u0010C\u001a\u00020DJ\u000e\u0010E\u001a\u00020A2\u0006\u0010\f\u001a\u00020FJ\u000e\u0010G\u001a\u00020A2\u0006\u0010H\u001a\u00020IJ\b\u0010J\u001a\u00020AH\u0016R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\b\u001a\u0004\b\n\u0010\u0006R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\b\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0011\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\b\u001a\u0004\b\u0012\u0010\u0006R\u001b\u0010\u0014\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\b\u001a\u0004\b\u0015\u0010\u000fR\u001b\u0010\u0017\u001a\u00020\u00188BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001b\u0010\b\u001a\u0004\b\u0019\u0010\u001aR\u001b\u0010\u001c\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001e\u0010\b\u001a\u0004\b\u001d\u0010\u000fR\u001b\u0010\u001f\u001a\u00020 8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b#\u0010\b\u001a\u0004\b!\u0010\"R\u001b\u0010$\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b&\u0010\b\u001a\u0004\b%\u0010\u000fR\u001b\u0010'\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b)\u0010\b\u001a\u0004\b(\u0010\u000fR\u001b\u0010*\u001a\u00020\u00188BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b,\u0010\b\u001a\u0004\b+\u0010\u001aR\u001b\u0010-\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b/\u0010\b\u001a\u0004\b.\u0010\u0006R\u001b\u00100\u001a\u0002018BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b4\u0010\b\u001a\u0004\b2\u00103R\u001b\u00105\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b7\u0010\b\u001a\u0004\b6\u0010\u0006R\u001b\u00108\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b:\u0010\b\u001a\u0004\b9\u0010\u000fR\u001b\u0010;\u001a\u00020\u00188BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b=\u0010\b\u001a\u0004\b<\u0010\u001a\u00a8\u0006K"}, d2={"Lxyz/apfelmus/cheeto/client/clickgui/ConfigGUI;", "Lgg/essential/elementa/WindowScreen;", "()V", "bottomContent", "Lgg/essential/elementa/components/UIContainer;", "getBottomContent", "()Lgg/essential/elementa/components/UIContainer;", "bottomContent$delegate", "Lkotlin/properties/ReadWriteProperty;", "categoryContainer", "getCategoryContainer", "categoryContainer$delegate", "config", "Lgg/essential/elementa/components/UIBlock;", "getConfig", "()Lgg/essential/elementa/components/UIBlock;", "config$delegate", "configContainer", "getConfigContainer", "configContainer$delegate", "configScrollBar", "getConfigScrollBar", "configScrollBar$delegate", "configScroller", "Lgg/essential/elementa/components/ScrollComponent;", "getConfigScroller", "()Lgg/essential/elementa/components/ScrollComponent;", "configScroller$delegate", "configSpacer", "getConfigSpacer", "configSpacer$delegate", "configText", "Lgg/essential/elementa/components/UIText;", "getConfigText", "()Lgg/essential/elementa/components/UIText;", "configText$delegate", "content", "getContent", "content$delegate", "moduleScrollBar", "getModuleScrollBar", "moduleScrollBar$delegate", "moduleScroller", "getModuleScroller", "moduleScroller$delegate", "scrollContainer", "getScrollContainer", "scrollContainer$delegate", "searchBar", "Lxyz/apfelmus/cheeto/client/clickgui/settings/TextComponent;", "getSearchBar", "()Lxyz/apfelmus/cheeto/client/clickgui/settings/TextComponent;", "searchBar$delegate", "settingContainer", "getSettingContainer", "settingContainer$delegate", "settingScrollBar", "getSettingScrollBar", "settingScrollBar$delegate", "settingScroller", "getSettingScroller", "settingScroller$delegate", "doesGuiPauseGame", "", "onScreenClose", "", "selectCategory", "category", "Lxyz/apfelmus/cf4m/module/Category;", "selectConfig", "", "selectModule", "module", "", "updateGuiScale", "Cheeto"})
public final class ConfigGUI
extends WindowScreen {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    @NotNull
    private final ReadWriteProperty content$delegate;
    @NotNull
    private final ReadWriteProperty config$delegate;
    @NotNull
    private final ReadWriteProperty configText$delegate;
    @NotNull
    private final ReadWriteProperty configSpacer$delegate;
    @NotNull
    private final ReadWriteProperty configContainer$delegate;
    @NotNull
    private final ReadWriteProperty configScroller$delegate;
    @NotNull
    private final ReadWriteProperty configScrollBar$delegate;
    @NotNull
    private final ReadWriteProperty categoryContainer$delegate;
    @NotNull
    private final ReadWriteProperty bottomContent$delegate;
    @NotNull
    private final ReadWriteProperty scrollContainer$delegate;
    @NotNull
    private final ReadWriteProperty moduleScroller$delegate;
    @NotNull
    private final ReadWriteProperty moduleScrollBar$delegate;
    @NotNull
    private final ReadWriteProperty settingContainer$delegate;
    @NotNull
    private final ReadWriteProperty settingScroller$delegate;
    @NotNull
    private final ReadWriteProperty settingScrollBar$delegate;
    @NotNull
    private final ReadWriteProperty searchBar$delegate;

    /*
     * WARNING - void declaration
     */
    public ConfigGUI() {
        void $this$searchBar_delegate_u24lambda_u2d18;
        Object object;
        Unit unit;
        Unit unit2;
        Object element$iv$iv2;
        Iterable $this$filterIsInstanceTo$iv$iv;
        void $this$settingScrollBar_delegate_u24lambda_u2d17;
        UIConstraints $this$settingScroller_delegate_u24lambda_u2d16;
        UIConstraints $this$settingContainer_delegate_u24lambda_u2d15;
        UIConstraints $this$moduleScrollBar_delegate_u24lambda_u2d14;
        UIConstraints $this$moduleScroller_delegate_u24lambda_u2d13;
        UIConstraints $this$scrollContainer_delegate_u24lambda_u2d12;
        UIConstraints $this$bottomContent_delegate_u24lambda_u2d11;
        void $this$categoryContainer_delegate_u24lambda_u2d8;
        Object label;
        void $this$configScrollBar_delegate_u24lambda_u2d6;
        UIConstraints $this$configScroller_delegate_u24lambda_u2d5;
        UIConstraints $this$configContainer_delegate_u24lambda_u2d4;
        UIConstraints $this$configSpacer_delegate_u24lambda_u2d3;
        UIConstraints $this$configText_delegate_u24lambda_u2d2;
        UIConstraints $this$config_delegate_u24lambda_u2d1;
        UIConstraints $this$content_delegate_u24lambda_u2d0;
        UIComponent uIComponent;
        Object object2 = ElementaVersion.V1;
        int n = GuiScale.Companion.scaleForScreenSize$default((GuiScale.Companion)GuiScale.Companion, (int)0, (int)1, null).ordinal();
        super(object2, false, false, true, n, 6, null);
        object2 = ColorUtils.MENU_BG;
        Intrinsics.checkNotNullExpressionValue((Object)object2, (String)"MENU_BG");
        Object $this$constrain$iv = (UIComponent)new UIBlock((Color)object2);
        boolean $i$f$constrain = false;
        UIComponent $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
        boolean bl = false;
        UIConstraints uIConstraints = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        ConfigGUI configGUI = this;
        boolean bl2 = false;
        $this$content_delegate_u24lambda_u2d0.setX((XConstraint)new CenterConstraint());
        $this$content_delegate_u24lambda_u2d0.setY((YConstraint)new CenterConstraint());
        $this$content_delegate_u24lambda_u2d0.setWidth((WidthConstraint)UtilitiesKt.percent((Number)60));
        $this$content_delegate_u24lambda_u2d0.setHeight((HeightConstraint)UtilitiesKt.percent((Number)75));
        UIComponent uIComponent2 = ComponentsKt.childOf((UIComponent)uIComponent, (UIComponent)((UIComponent)this.getWindow()));
        $this$constrain$iv = ColorUtils.M_BORDER;
        Intrinsics.checkNotNullExpressionValue((Object)$this$constrain$iv, (String)"M_BORDER");
        OutlineEffect outlineEffect = new OutlineEffect((Color)$this$constrain$iv, 1.0f, false, false, null, 28, null);
        $this$constrain$iv = ColorUtils.M_BORDER;
        Intrinsics.checkNotNullExpressionValue((Object)$this$constrain$iv, (String)"M_BORDER");
        configGUI.content$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.effect((UIComponent)uIComponent2, (Effect)((Effect)outlineEffect.bindColor((State)new BasicState($this$constrain$iv)))), (Object)((Object)this), $$delegatedProperties[0]);
        $this$constrain$iv = ColorUtils.MENU_BG;
        Intrinsics.checkNotNullExpressionValue((Object)$this$constrain$iv, (String)"MENU_BG");
        $this$constrain$iv = (UIComponent)new UIBlock((Color)$this$constrain$iv);
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
        bl = false;
        $this$content_delegate_u24lambda_u2d0 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        configGUI = this;
        boolean bl3 = false;
        $this$config_delegate_u24lambda_u2d1.setX((XConstraint)ConstraintsKt.plus((SuperConstraint)((SuperConstraint)new SiblingConstraint(0.0f, false, 3, null)), (SuperConstraint)((SuperConstraint)UtilitiesKt.pixels$default((Number)5, (boolean)false, (boolean)false, (int)3, null))));
        $this$config_delegate_u24lambda_u2d1.setY((YConstraint)new CenterConstraint());
        $this$config_delegate_u24lambda_u2d1.setWidth((WidthConstraint)UtilitiesKt.percent((Number)13));
        $this$config_delegate_u24lambda_u2d1.setHeight((HeightConstraint)UtilitiesKt.percent((Number)40));
        UIComponent uIComponent3 = ComponentsKt.childOf((UIComponent)uIComponent, (UIComponent)((UIComponent)this.getWindow()));
        $this$constrain$iv = ColorUtils.M_BORDER;
        Intrinsics.checkNotNullExpressionValue((Object)$this$constrain$iv, (String)"M_BORDER");
        configGUI.config$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.effect((UIComponent)uIComponent3, (Effect)((Effect)new OutlineEffect((Color)$this$constrain$iv, 1.0f, false, false, null, 28, null))), (Object)((Object)this), $$delegatedProperties[1]);
        $this$constrain$iv = (UIComponent)new UIText("Configs", false, null, 6, null);
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
        bl = false;
        $this$config_delegate_u24lambda_u2d1 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        configGUI = this;
        boolean bl4 = false;
        $this$configText_delegate_u24lambda_u2d2.setX((XConstraint)new CenterConstraint());
        $this$configText_delegate_u24lambda_u2d2.setY((YConstraint)UtilitiesKt.pixels$default((Number)10, (boolean)false, (boolean)false, (int)3, null));
        $this$configText_delegate_u24lambda_u2d2.setTextScale((HeightConstraint)UtilitiesKt.pixels$default((Number)Float.valueOf(1.5f), (boolean)false, (boolean)false, (int)3, null));
        configGUI.configText$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.childOf((UIComponent)uIComponent, (UIComponent)((UIComponent)this.getConfig())), (Object)((Object)this), $$delegatedProperties[2]);
        $this$constrain$iv = ColorUtils.M_BORDER;
        Intrinsics.checkNotNullExpressionValue((Object)$this$constrain$iv, (String)"M_BORDER");
        $this$constrain$iv = (UIComponent)new UIBlock((Color)$this$constrain$iv);
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
        bl = false;
        $this$configText_delegate_u24lambda_u2d2 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        configGUI = this;
        boolean bl5 = false;
        $this$configSpacer_delegate_u24lambda_u2d3.setX((XConstraint)new CenterConstraint());
        $this$configSpacer_delegate_u24lambda_u2d3.setY((YConstraint)new SiblingConstraint(5.0f, false, 2, null));
        $this$configSpacer_delegate_u24lambda_u2d3.setWidth((WidthConstraint)ConstraintsKt.minus((SuperConstraint)((SuperConstraint)UtilitiesKt.percent((Number)100)), (SuperConstraint)((SuperConstraint)UtilitiesKt.pixels$default((Number)20, (boolean)false, (boolean)false, (int)3, null))));
        $this$configSpacer_delegate_u24lambda_u2d3.setHeight((HeightConstraint)UtilitiesKt.pixel$default((Number)1, (boolean)false, (boolean)false, (int)3, null));
        configGUI.configSpacer$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.childOf((UIComponent)uIComponent, (UIComponent)((UIComponent)this.getConfig())), (Object)((Object)this), $$delegatedProperties[3]);
        $this$constrain$iv = (UIComponent)new UIContainer();
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
        bl = false;
        $this$configSpacer_delegate_u24lambda_u2d3 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        configGUI = this;
        boolean bl6 = false;
        $this$configContainer_delegate_u24lambda_u2d4.setX((XConstraint)UtilitiesKt.pixels$default((Number)10, (boolean)false, (boolean)false, (int)3, null));
        $this$configContainer_delegate_u24lambda_u2d4.setY((YConstraint)new SiblingConstraint(5.0f, false, 2, null));
        $this$configContainer_delegate_u24lambda_u2d4.setWidth((WidthConstraint)ConstraintsKt.minus((SuperConstraint)((SuperConstraint)new FillConstraint(false)), (SuperConstraint)((SuperConstraint)UtilitiesKt.pixels$default((Number)10, (boolean)false, (boolean)false, (int)3, null))));
        $this$configContainer_delegate_u24lambda_u2d4.setHeight((HeightConstraint)new FillConstraint(false));
        configGUI.configContainer$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.childOf((UIComponent)uIComponent, (UIComponent)((UIComponent)this.getConfig())), (Object)((Object)this), $$delegatedProperties[4]);
        $this$constrain$iv = (UIComponent)new ScrollComponent(null, 0.0f, null, false, false, false, false, 25.0f, 0.0f, null, 895, null);
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
        bl = false;
        $this$configContainer_delegate_u24lambda_u2d4 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        configGUI = this;
        boolean bl7 = false;
        $this$configScroller_delegate_u24lambda_u2d5.setWidth((WidthConstraint)UtilitiesKt.percent((Number)100));
        $this$configScroller_delegate_u24lambda_u2d5.setHeight((HeightConstraint)UtilitiesKt.percent((Number)100));
        configGUI.configScroller$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.childOf((UIComponent)uIComponent, (UIComponent)((UIComponent)this.getConfigContainer())), (Object)((Object)this), $$delegatedProperties[5]);
        $this$constrain$iv = (UIComponent)new UIBlock(null, 1, null);
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
        bl = false;
        $this$configScroller_delegate_u24lambda_u2d5 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        configGUI = this;
        boolean bl8 = false;
        $this$configScrollBar_delegate_u24lambda_u2d6.setX((XConstraint)UtilitiesKt.percent((Number)106));
        $this$configScrollBar_delegate_u24lambda_u2d6.setWidth((WidthConstraint)UtilitiesKt.pixels$default((Number)3, (boolean)false, (boolean)false, (int)3, null));
        Color color = ColorUtils.M_BORDER;
        Intrinsics.checkNotNullExpressionValue((Object)color, (String)"M_BORDER");
        $this$configScrollBar_delegate_u24lambda_u2d6.setColor((ColorConstraint)UtilitiesKt.toConstraint((Color)color));
        configGUI.configScrollBar$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.childOf((UIComponent)uIComponent, (UIComponent)((UIComponent)this.getConfigContainer())), (Object)((Object)this), $$delegatedProperties[6]);
        this.getConfigScroller().setVerticalScrollBarComponent((UIComponent)this.getConfigScrollBar(), true);
        String active = ClientConfig.getActiveConfig();
        List<String> $i$f$constrain2 = ClientConfig.getConfigs();
        Intrinsics.checkNotNullExpressionValue($i$f$constrain2, (String)"getConfigs()");
        Iterable $this$forEach$iv = $i$f$constrain2;
        boolean $i$f$forEach22 = false;
        for (Object element$iv : $this$forEach$iv) {
            String cfg = (String)element$iv;
            boolean bl9 = false;
            Intrinsics.checkNotNullExpressionValue((Object)cfg, (String)"cfg");
            label = (ConfigLabel)ComponentsKt.childOf((UIComponent)((UIComponent)new ConfigLabel(this, cfg)), (UIComponent)((UIComponent)this.getConfigScroller()));
            if (!Intrinsics.areEqual((Object)active, (Object)cfg)) continue;
            ((ConfigLabel)((Object)label)).select();
        }
        $this$constrain$iv = (UIComponent)new UIContainer();
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = $i$f$forEach22 = $this$constrain$iv;
        bl = false;
        label = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        configGUI = this;
        boolean bl10 = false;
        $this$categoryContainer_delegate_u24lambda_u2d8.setX((XConstraint)new CenterConstraint());
        $this$categoryContainer_delegate_u24lambda_u2d8.setY((YConstraint)UtilitiesKt.pixels$default((Number)5, (boolean)false, (boolean)false, (int)3, null));
        $this$categoryContainer_delegate_u24lambda_u2d8.setWidth((WidthConstraint)UtilitiesKt.percent((Number)95));
        $this$categoryContainer_delegate_u24lambda_u2d8.setHeight((HeightConstraint)UtilitiesKt.percent((Number)12));
        configGUI.categoryContainer$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.childOf((UIComponent)$i$f$forEach22, (UIComponent)((UIComponent)this.getContent())), (Object)((Object)this), $$delegatedProperties[7]);
        Object $this$forEach$iv2 = Category.values();
        boolean $i$f$forEach = false;
        Object $i$f$forEach22 = $this$forEach$iv2;
        int element$iv = ((Category[])$i$f$forEach22).length;
        for (int i = 0; i < element$iv; ++i) {
            Category element$iv2 = $i$f$forEach22[i];
            Category category = element$iv2;
            boolean bl11 = false;
            ComponentsKt.childOf((UIComponent)((UIComponent)new CategoryLabel(this, category)), (UIComponent)((UIComponent)this.getCategoryContainer()));
        }
        $this$forEach$iv2 = ColorUtils.M_BORDER;
        Intrinsics.checkNotNullExpressionValue((Object)$this$forEach$iv2, (String)"M_BORDER");
        $this$constrain$iv = (UIComponent)new UIBlock((Color)$this$forEach$iv2);
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = $i$f$forEach22 = $this$constrain$iv;
        bl = false;
        UIConstraints $this$_init__u24lambda_u2d10 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        boolean bl12 = false;
        $this$_init__u24lambda_u2d10.setX((XConstraint)UtilitiesKt.pixels$default((Number)0, (boolean)false, (boolean)false, (int)3, null));
        $this$_init__u24lambda_u2d10.setY((YConstraint)new SiblingConstraint(0.0f, false, 3, null));
        $this$_init__u24lambda_u2d10.setWidth((WidthConstraint)new FillConstraint(false));
        $this$_init__u24lambda_u2d10.setHeight((HeightConstraint)UtilitiesKt.pixel$default((Number)1, (boolean)false, (boolean)false, (int)3, null));
        ComponentsKt.childOf((UIComponent)$i$f$forEach22, (UIComponent)((UIComponent)this.getContent()));
        $this$constrain$iv = (UIComponent)new UIContainer();
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = $i$f$forEach22 = $this$constrain$iv;
        bl = false;
        $this$_init__u24lambda_u2d10 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        configGUI = this;
        boolean bl13 = false;
        $this$bottomContent_delegate_u24lambda_u2d11.setX((XConstraint)UtilitiesKt.pixels$default((Number)10, (boolean)false, (boolean)false, (int)3, null));
        $this$bottomContent_delegate_u24lambda_u2d11.setY((YConstraint)new SiblingConstraint(10.0f, false, 2, null));
        $this$bottomContent_delegate_u24lambda_u2d11.setWidth((WidthConstraint)ConstraintsKt.minus((SuperConstraint)((SuperConstraint)new FillConstraint(false)), (SuperConstraint)((SuperConstraint)UtilitiesKt.pixels$default((Number)10, (boolean)false, (boolean)false, (int)3, null))));
        $this$bottomContent_delegate_u24lambda_u2d11.setHeight((HeightConstraint)ConstraintsKt.minus((SuperConstraint)((SuperConstraint)new FillConstraint(false)), (SuperConstraint)((SuperConstraint)UtilitiesKt.pixels$default((Number)10, (boolean)false, (boolean)false, (int)3, null))));
        configGUI.bottomContent$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.childOf((UIComponent)$i$f$forEach22, (UIComponent)((UIComponent)this.getContent())), (Object)((Object)this), $$delegatedProperties[8]);
        $this$constrain$iv = (UIComponent)new UIContainer();
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = $i$f$forEach22 = $this$constrain$iv;
        bl = false;
        $this$bottomContent_delegate_u24lambda_u2d11 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        configGUI = this;
        boolean bl14 = false;
        $this$scrollContainer_delegate_u24lambda_u2d12.setX((XConstraint)UtilitiesKt.pixels$default((Number)10, (boolean)false, (boolean)false, (int)3, null));
        $this$scrollContainer_delegate_u24lambda_u2d12.setY((YConstraint)new SiblingConstraint(10.0f, false, 2, null));
        $this$scrollContainer_delegate_u24lambda_u2d12.setWidth((WidthConstraint)UtilitiesKt.percent((Number)27.5));
        $this$scrollContainer_delegate_u24lambda_u2d12.setHeight((HeightConstraint)ConstraintsKt.minus((SuperConstraint)((SuperConstraint)new FillConstraint(false)), (SuperConstraint)((SuperConstraint)UtilitiesKt.pixels$default((Number)10, (boolean)false, (boolean)false, (int)3, null))));
        configGUI.scrollContainer$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.childOf((UIComponent)$i$f$forEach22, (UIComponent)((UIComponent)this.getBottomContent())), (Object)((Object)this), $$delegatedProperties[9]);
        $this$constrain$iv = (UIComponent)new ScrollComponent(null, 0.0f, null, false, false, false, false, 25.0f, 0.0f, null, 895, null);
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = $i$f$forEach22 = $this$constrain$iv;
        bl = false;
        $this$scrollContainer_delegate_u24lambda_u2d12 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        configGUI = this;
        boolean bl15 = false;
        $this$moduleScroller_delegate_u24lambda_u2d13.setWidth((WidthConstraint)UtilitiesKt.percent((Number)100));
        $this$moduleScroller_delegate_u24lambda_u2d13.setHeight((HeightConstraint)UtilitiesKt.percent((Number)100));
        configGUI.moduleScroller$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.childOf((UIComponent)$i$f$forEach22, (UIComponent)((UIComponent)this.getScrollContainer())), (Object)((Object)this), $$delegatedProperties[10]);
        $this$constrain$iv = (UIComponent)new UIBlock(null, 1, null);
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = $i$f$forEach22 = $this$constrain$iv;
        bl = false;
        $this$moduleScroller_delegate_u24lambda_u2d13 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        configGUI = this;
        boolean bl16 = false;
        $this$moduleScrollBar_delegate_u24lambda_u2d14.setX((XConstraint)UtilitiesKt.pixels$default((Number)7.5, (boolean)true, (boolean)false, (int)2, null));
        $this$moduleScrollBar_delegate_u24lambda_u2d14.setWidth((WidthConstraint)UtilitiesKt.pixels$default((Number)3, (boolean)false, (boolean)false, (int)3, null));
        color = ColorUtils.M_BORDER;
        Intrinsics.checkNotNullExpressionValue((Object)color, (String)"M_BORDER");
        $this$moduleScrollBar_delegate_u24lambda_u2d14.setColor((ColorConstraint)UtilitiesKt.toConstraint((Color)color));
        configGUI.moduleScrollBar$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.childOf((UIComponent)$i$f$forEach22, (UIComponent)((UIComponent)this.getScrollContainer())), (Object)((Object)this), $$delegatedProperties[11]);
        $this$constrain$iv = (UIComponent)new UIContainer();
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = $i$f$forEach22 = $this$constrain$iv;
        bl = false;
        $this$moduleScrollBar_delegate_u24lambda_u2d14 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        configGUI = this;
        boolean bl17 = false;
        $this$settingContainer_delegate_u24lambda_u2d15.setX((XConstraint)new SiblingConstraint(0.0f, false, 3, null));
        $this$settingContainer_delegate_u24lambda_u2d15.setWidth((WidthConstraint)new FillConstraint(false));
        $this$settingContainer_delegate_u24lambda_u2d15.setHeight((HeightConstraint)new FillConstraint(false));
        UIComponent uIComponent4 = ComponentsKt.childOf((UIComponent)$i$f$forEach22, (UIComponent)((UIComponent)this.getBottomContent()));
        $this$constrain$iv = ColorUtils.C_BORDER;
        Intrinsics.checkNotNullExpressionValue((Object)$this$constrain$iv, (String)"C_BORDER");
        configGUI.settingContainer$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.effect((UIComponent)uIComponent4, (Effect)((Effect)new OutlineEffect((Color)$this$constrain$iv, 1.0f, false, false, null, 28, null))), (Object)((Object)this), $$delegatedProperties[12]);
        $this$constrain$iv = (UIComponent)new ScrollComponent(null, 0.0f, null, false, false, false, false, 25.0f, 0.0f, null, 895, null);
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = $i$f$forEach22 = $this$constrain$iv;
        bl = false;
        $this$settingContainer_delegate_u24lambda_u2d15 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        configGUI = this;
        boolean bl18 = false;
        $this$settingScroller_delegate_u24lambda_u2d16.setWidth((WidthConstraint)UtilitiesKt.percent((Number)100));
        $this$settingScroller_delegate_u24lambda_u2d16.setHeight((HeightConstraint)UtilitiesKt.percent((Number)100));
        configGUI.settingScroller$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.childOf((UIComponent)$i$f$forEach22, (UIComponent)((UIComponent)this.getSettingContainer())), (Object)((Object)this), $$delegatedProperties[13]);
        $this$constrain$iv = (UIComponent)new UIBlock(null, 1, null);
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = $i$f$forEach22 = $this$constrain$iv;
        bl = false;
        $this$settingScroller_delegate_u24lambda_u2d16 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        configGUI = this;
        boolean bl19 = false;
        $this$settingScrollBar_delegate_u24lambda_u2d17.setX((XConstraint)UtilitiesKt.pixels$default((Number)2.5, (boolean)true, (boolean)false, (int)2, null));
        $this$settingScrollBar_delegate_u24lambda_u2d17.setWidth((WidthConstraint)UtilitiesKt.pixels$default((Number)3, (boolean)false, (boolean)false, (int)3, null));
        color = ColorUtils.M_BORDER;
        Intrinsics.checkNotNullExpressionValue((Object)color, (String)"M_BORDER");
        $this$settingScrollBar_delegate_u24lambda_u2d17.setColor((ColorConstraint)UtilitiesKt.toConstraint((Color)color));
        configGUI.settingScrollBar$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.childOf((UIComponent)$i$f$forEach22, (UIComponent)((UIComponent)this.getSettingContainer())), (Object)((Object)this), $$delegatedProperties[14]);
        this.getModuleScroller().setVerticalScrollBarComponent((UIComponent)this.getModuleScrollBar(), true);
        Iterable $this$filterIsInstance$iv = (Iterable)this.getCategoryContainer().getChildren();
        boolean $i$f$filterIsInstance = false;
        Iterable iterable = $this$filterIsInstance$iv;
        Collection destination$iv$iv = new ArrayList();
        boolean $i$f$filterIsInstanceTo = false;
        for (Object element$iv$iv2 : $this$filterIsInstanceTo$iv$iv) {
            if (!(element$iv$iv2 instanceof CategoryLabel)) continue;
            destination$iv$iv.add(element$iv$iv2);
        }
        CategoryLabel categoryLabel = (CategoryLabel)((Object)CollectionsKt.firstOrNull((List)((List)destination$iv$iv)));
        if (categoryLabel == null) {
            unit2 = null;
        } else {
            categoryLabel.select();
            unit2 = Unit.INSTANCE;
        }
        this.getSettingScroller().setVerticalScrollBarComponent((UIComponent)this.getSettingScrollBar(), true);
        $this$filterIsInstance$iv = this.getModuleScroller().getAllChildren();
        $i$f$filterIsInstance = false;
        $this$filterIsInstanceTo$iv$iv = $this$filterIsInstance$iv;
        destination$iv$iv = new ArrayList();
        $i$f$filterIsInstanceTo = false;
        for (Object element$iv$iv2 : $this$filterIsInstanceTo$iv$iv) {
            if (!(element$iv$iv2 instanceof ModuleLabel)) continue;
            destination$iv$iv.add(element$iv$iv2);
        }
        ModuleLabel moduleLabel = (ModuleLabel)((Object)CollectionsKt.firstOrNull((List)((List)destination$iv$iv)));
        if (moduleLabel == null) {
            unit = null;
        } else {
            moduleLabel.select();
            unit = Unit.INSTANCE;
        }
        $this$constrain$iv = (UIComponent)new TextComponent("", "Search...", false, false);
        $i$f$constrain = false;
        Object $this$constrain_u24lambda_u2d0$iv2 = object = $this$constrain$iv;
        $i$a$-apply-ComponentsKt$constrain$1$iv = false;
        element$iv$iv2 = $this$constrain_u24lambda_u2d0$iv2.getConstraints();
        configGUI = this;
        boolean bl20 = false;
        $this$searchBar_delegate_u24lambda_u2d18.setX((XConstraint)new CenterConstraint());
        $this$searchBar_delegate_u24lambda_u2d18.setY((YConstraint)UtilitiesKt.percent((Number)7.5));
        configGUI.searchBar$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.childOf((UIComponent)object, (UIComponent)((UIComponent)this.getWindow())), (Object)((Object)this), $$delegatedProperties[15]);
        this.getSearchBar().onValueChange((Function1<Object, Unit>)((Function1)new Function1<Object, Unit>(){

            /*
             * WARNING - void declaration
             */
            public final void invoke(@Nullable Object it) {
                Object object = it;
                if (object == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
                }
                if (((String)object).equals("")) {
                    Object v1;
                    Object element$iv$iv;
                    Iterable $this$filterIsInstanceTo$iv$iv;
                    Collection destination$iv$iv;
                    boolean $i$f$filterIsInstance;
                    Iterable $this$filterIsInstance$iv;
                    block15: {
                        Iterator element$iv2;
                        void $this$forEach$iv;
                        this.getCategoryContainer().clearChildren();
                        Category[] arrcategory = Category.values();
                        ConfigGUI configGUI = this;
                        boolean $i$f$forEach = false;
                        for (Iterator element$iv2 : $this$forEach$iv) {
                            void category = element$iv2;
                            boolean bl2 = false;
                            ComponentsKt.childOf((UIComponent)((UIComponent)new CategoryLabel(configGUI, (Category)category)), (UIComponent)((UIComponent)configGUI.getCategoryContainer()));
                        }
                        $this$filterIsInstance$iv = (Iterable)this.getCategoryContainer().getChildren();
                        $i$f$filterIsInstance = false;
                        Iterable iterable = $this$filterIsInstance$iv;
                        destination$iv$iv = new ArrayList();
                        boolean $i$f$filterIsInstanceTo = false;
                        element$iv2 = $this$filterIsInstanceTo$iv$iv.iterator();
                        while (element$iv2.hasNext()) {
                            element$iv$iv = element$iv2.next();
                            if (!(element$iv$iv instanceof CategoryLabel)) continue;
                            destination$iv$iv.add(element$iv$iv);
                        }
                        Iterable $this$firstOrNull$iv = (List)destination$iv$iv;
                        boolean $i$f$firstOrNull = false;
                        for (Object element$iv3 : $this$firstOrNull$iv) {
                            CategoryLabel it2 = (CategoryLabel)((Object)element$iv3);
                            boolean bl = false;
                            if (!it2.isSelected()) continue;
                            v1 = element$iv3;
                            break block15;
                        }
                        v1 = null;
                    }
                    CategoryLabel categoryLabel = v1;
                    if (categoryLabel != null) {
                        categoryLabel.deselect();
                    }
                    $this$filterIsInstance$iv = (Iterable)this.getCategoryContainer().getChildren();
                    $i$f$filterIsInstance = false;
                    $this$filterIsInstanceTo$iv$iv = $this$filterIsInstance$iv;
                    destination$iv$iv = new ArrayList();
                    boolean $i$f$filterIsInstanceTo = false;
                    Iterator bl = $this$filterIsInstanceTo$iv$iv.iterator();
                    while (bl.hasNext()) {
                        element$iv$iv = bl.next();
                        if (!(element$iv$iv instanceof CategoryLabel)) continue;
                        destination$iv$iv.add(element$iv$iv);
                    }
                    CategoryLabel categoryLabel2 = (CategoryLabel)((Object)CollectionsKt.firstOrNull((List)((List)destination$iv$iv)));
                    if (categoryLabel2 != null) {
                        categoryLabel2.select();
                    }
                } else {
                    Object v5;
                    Object element$iv$iv;
                    Iterable $this$filterIsInstanceTo$iv$iv;
                    Collection destination$iv$iv;
                    boolean $i$f$filterIsInstance;
                    Iterable $this$filterIsInstance$iv;
                    block16: {
                        void $this$firstOrNull$iv;
                        void $this$forEach$iv;
                        this.getCategoryContainer().clearChildren();
                        CategoryLabel cat = (CategoryLabel)ComponentsKt.childOf((UIComponent)((UIComponent)new CategoryLabel(this, Category.NONE)), (UIComponent)((UIComponent)this.getCategoryContainer()));
                        cat.select();
                        this.getModuleScroller().clearChildren();
                        Iterable<Object> $this$filterIsInstance$iv2 = CF4M.INSTANCE.moduleManager.getModules();
                        Intrinsics.checkNotNullExpressionValue($this$filterIsInstance$iv2, (String)"INSTANCE.moduleManager.modules");
                        $this$filterIsInstance$iv2 = $this$filterIsInstance$iv2;
                        ConfigGUI $i$f$filterIsInstance2 = this;
                        boolean $i$f$forEach = false;
                        Object destination$iv$iv2 = $this$forEach$iv.iterator();
                        while (destination$iv$iv2.hasNext()) {
                            Object element$iv;
                            Object mod = element$iv = destination$iv$iv2.next();
                            boolean bl = false;
                            String bl2 = CF4M.INSTANCE.moduleManager.getName(mod);
                            Intrinsics.checkNotNullExpressionValue((Object)bl2, (String)"INSTANCE.moduleManager.getName(mod)");
                            String string = bl2.toLowerCase(Locale.ROOT);
                            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"this as java.lang.String).toLowerCase(Locale.ROOT)");
                            CharSequence charSequence = string;
                            string = ((String)it).toLowerCase(Locale.ROOT);
                            Intrinsics.checkNotNullExpressionValue((Object)string, (String)"this as java.lang.String).toLowerCase(Locale.ROOT)");
                            if (!StringsKt.contains$default((CharSequence)charSequence, (CharSequence)string, (boolean)false, (int)2, null)) continue;
                            Intrinsics.checkNotNullExpressionValue(mod, (String)"mod");
                            ComponentsKt.childOf((UIComponent)((UIComponent)new ModuleLabel($i$f$filterIsInstance2, mod)), (UIComponent)((UIComponent)$i$f$filterIsInstance2.getModuleScroller()));
                        }
                        this.getSettingScroller().clearChildren();
                        $this$filterIsInstance$iv = this.getModuleScroller().getAllChildren();
                        $i$f$filterIsInstance = false;
                        destination$iv$iv2 = $this$filterIsInstance$iv;
                        destination$iv$iv = new ArrayList();
                        boolean $i$f$filterIsInstanceTo = false;
                        Iterator bl = $this$filterIsInstanceTo$iv$iv.iterator();
                        while (bl.hasNext()) {
                            element$iv$iv = bl.next();
                            if (!(element$iv$iv instanceof ModuleLabel)) continue;
                            destination$iv$iv.add(element$iv$iv);
                        }
                        $this$filterIsInstance$iv = (List)destination$iv$iv;
                        boolean $i$f$firstOrNull = false;
                        for (Object element$iv : $this$firstOrNull$iv) {
                            ModuleLabel it3 = (ModuleLabel)((Object)element$iv);
                            boolean bl3 = false;
                            if (!it3.isSelected()) continue;
                            v5 = element$iv;
                            break block16;
                        }
                        v5 = null;
                    }
                    ModuleLabel moduleLabel = v5;
                    if (moduleLabel != null) {
                        moduleLabel.deselect();
                    }
                    $this$filterIsInstance$iv = this.getModuleScroller().getAllChildren();
                    $i$f$filterIsInstance = false;
                    $this$filterIsInstanceTo$iv$iv = $this$filterIsInstance$iv;
                    destination$iv$iv = new ArrayList();
                    boolean $i$f$filterIsInstanceTo = false;
                    Iterator iterator = $this$filterIsInstanceTo$iv$iv.iterator();
                    while (iterator.hasNext()) {
                        element$iv$iv = iterator.next();
                        if (!(element$iv$iv instanceof ModuleLabel)) continue;
                        destination$iv$iv.add(element$iv$iv);
                    }
                    ModuleLabel moduleLabel2 = (ModuleLabel)((Object)CollectionsKt.firstOrNull((List)((List)destination$iv$iv)));
                    if (moduleLabel2 != null) {
                        moduleLabel2.select();
                    }
                }
            }
        }));
    }

    private final UIBlock getContent() {
        return (UIBlock)this.content$delegate.getValue((Object)this, $$delegatedProperties[0]);
    }

    private final UIBlock getConfig() {
        return (UIBlock)this.config$delegate.getValue((Object)this, $$delegatedProperties[1]);
    }

    private final UIText getConfigText() {
        return (UIText)this.configText$delegate.getValue((Object)this, $$delegatedProperties[2]);
    }

    private final UIBlock getConfigSpacer() {
        return (UIBlock)this.configSpacer$delegate.getValue((Object)this, $$delegatedProperties[3]);
    }

    private final UIContainer getConfigContainer() {
        return (UIContainer)this.configContainer$delegate.getValue((Object)this, $$delegatedProperties[4]);
    }

    private final ScrollComponent getConfigScroller() {
        return (ScrollComponent)this.configScroller$delegate.getValue((Object)this, $$delegatedProperties[5]);
    }

    private final UIBlock getConfigScrollBar() {
        return (UIBlock)this.configScrollBar$delegate.getValue((Object)this, $$delegatedProperties[6]);
    }

    private final UIContainer getCategoryContainer() {
        return (UIContainer)this.categoryContainer$delegate.getValue((Object)this, $$delegatedProperties[7]);
    }

    private final UIContainer getBottomContent() {
        return (UIContainer)this.bottomContent$delegate.getValue((Object)this, $$delegatedProperties[8]);
    }

    private final UIContainer getScrollContainer() {
        return (UIContainer)this.scrollContainer$delegate.getValue((Object)this, $$delegatedProperties[9]);
    }

    private final ScrollComponent getModuleScroller() {
        return (ScrollComponent)this.moduleScroller$delegate.getValue((Object)this, $$delegatedProperties[10]);
    }

    private final UIBlock getModuleScrollBar() {
        return (UIBlock)this.moduleScrollBar$delegate.getValue((Object)this, $$delegatedProperties[11]);
    }

    private final UIContainer getSettingContainer() {
        return (UIContainer)this.settingContainer$delegate.getValue((Object)this, $$delegatedProperties[12]);
    }

    private final ScrollComponent getSettingScroller() {
        return (ScrollComponent)this.settingScroller$delegate.getValue((Object)this, $$delegatedProperties[13]);
    }

    private final UIBlock getSettingScrollBar() {
        return (UIBlock)this.settingScrollBar$delegate.getValue((Object)this, $$delegatedProperties[14]);
    }

    private final TextComponent getSearchBar() {
        return (TextComponent)((Object)this.searchBar$delegate.getValue((Object)this, $$delegatedProperties[15]));
    }

    /*
     * WARNING - void declaration
     */
    public final void selectCategory(@NotNull Category category) {
        Object v0;
        block8: {
            void $this$filterIsInstanceTo$iv$iv;
            Iterable $this$filterIsInstanceTo$iv$iv2;
            Intrinsics.checkNotNullParameter((Object)((Object)category), (String)"category");
            this.getModuleScroller().clearChildren();
            ArrayList<Object> arrayList = CF4M.INSTANCE.moduleManager.getModules(category);
            Intrinsics.checkNotNullExpressionValue(arrayList, (String)"INSTANCE.moduleManager.getModules(category)");
            Iterable $this$forEach$iv = arrayList;
            boolean $i$f$forEach = false;
            Object object = $this$forEach$iv.iterator();
            while (object.hasNext()) {
                Object element$iv;
                Object module = element$iv = object.next();
                boolean bl = false;
                Intrinsics.checkNotNullExpressionValue(module, (String)"module");
                ComponentsKt.childOf((UIComponent)((UIComponent)new ModuleLabel(this, module)), (UIComponent)((UIComponent)this.getModuleScroller()));
            }
            Iterable $this$filterIsInstance$iv = this.getModuleScroller().getAllChildren();
            boolean $i$f$filterIsInstance = false;
            object = $this$filterIsInstance$iv;
            Iterable<Object> destination$iv$iv = new ArrayList();
            boolean $i$f$filterIsInstanceTo = false;
            for (Object element$iv$iv : $this$filterIsInstanceTo$iv$iv2) {
                if (!(element$iv$iv instanceof ModuleLabel)) continue;
                destination$iv$iv.add(element$iv$iv);
            }
            if (!((Collection)((List)destination$iv$iv)).isEmpty()) {
                $this$filterIsInstance$iv = this.getModuleScroller().getAllChildren();
                $i$f$filterIsInstance = false;
                $this$filterIsInstanceTo$iv$iv2 = $this$filterIsInstance$iv;
                destination$iv$iv = new ArrayList();
                $i$f$filterIsInstanceTo = false;
                for (Object element$iv$iv : $this$filterIsInstanceTo$iv$iv2) {
                    if (!(element$iv$iv instanceof ModuleLabel)) continue;
                    destination$iv$iv.add(element$iv$iv);
                }
                ((ModuleLabel)((Object)CollectionsKt.first((List)((List)destination$iv$iv)))).select();
            } else {
                this.getSettingScroller().clearChildren();
            }
            Iterable $this$filterIsInstance$iv2 = (Iterable)this.getCategoryContainer().getChildren();
            boolean $i$f$filterIsInstance2 = false;
            destination$iv$iv = $this$filterIsInstance$iv2;
            Collection destination$iv$iv2 = new ArrayList();
            boolean $i$f$filterIsInstanceTo2 = false;
            for (Object element$iv$iv : $this$filterIsInstanceTo$iv$iv) {
                if (!(element$iv$iv instanceof CategoryLabel)) continue;
                destination$iv$iv2.add(element$iv$iv);
            }
            Iterable $this$firstOrNull$iv = (List)destination$iv$iv2;
            boolean $i$f$firstOrNull = false;
            for (Object element$iv : $this$firstOrNull$iv) {
                CategoryLabel it = (CategoryLabel)((Object)element$iv);
                boolean bl = false;
                if (!it.isSelected()) continue;
                v0 = element$iv;
                break block8;
            }
            v0 = null;
        }
        CategoryLabel categoryLabel = v0;
        if (categoryLabel != null) {
            categoryLabel.deselect();
        }
    }

    /*
     * WARNING - void declaration
     */
    public final void selectModule(@NotNull Object module) {
        Object v0;
        block5: {
            void $this$firstOrNull$iv;
            void $this$filterIsInstanceTo$iv$iv;
            Iterable element$iv;
            Intrinsics.checkNotNullParameter((Object)module, (String)"module");
            this.getSettingScroller().clearChildren();
            ComponentsKt.childOf((UIComponent)((UIComponent)new KeybindLabel(module, this.getWindow())), (UIComponent)((UIComponent)this.getSettingScroller()));
            ArrayList<Object> settings = CF4M.INSTANCE.settingManager.getSettings(module);
            if (settings != null) {
                ArrayList<Object> arrayList = CF4M.INSTANCE.settingManager.getSettings(module);
                Intrinsics.checkNotNullExpressionValue(arrayList, (String)"INSTANCE.settingManager.getSettings(module)");
                Iterable $this$forEach$iv = arrayList;
                boolean $i$f$forEach = false;
                Iterator iterator = $this$forEach$iv.iterator();
                while (iterator.hasNext()) {
                    Iterable setting = element$iv = iterator.next();
                    boolean bl = false;
                    Intrinsics.checkNotNullExpressionValue((Object)setting, (String)"setting");
                    ComponentsKt.childOf((UIComponent)((UIComponent)new SettingLabel(module, setting)), (UIComponent)((UIComponent)this.getSettingScroller()));
                }
                ComponentsKt.childOf((UIComponent)new UIContainer().setY((YConstraint)new SiblingConstraint(0.0f, false, 3, null)).setHeight((HeightConstraint)UtilitiesKt.pixels$default((Number)20, (boolean)false, (boolean)false, (int)3, null)), (UIComponent)((UIComponent)this.getSettingScroller()));
            }
            Iterable $this$filterIsInstance$iv = this.getModuleScroller().getAllChildren();
            boolean $i$f$filterIsInstance = false;
            element$iv = $this$filterIsInstance$iv;
            Collection destination$iv$iv = new ArrayList();
            boolean $i$f$filterIsInstanceTo = false;
            for (Object element$iv$iv : $this$filterIsInstanceTo$iv$iv) {
                if (!(element$iv$iv instanceof ModuleLabel)) continue;
                destination$iv$iv.add(element$iv$iv);
            }
            $this$filterIsInstance$iv = (List)destination$iv$iv;
            boolean $i$f$firstOrNull = false;
            for (Object element$iv2 : $this$firstOrNull$iv) {
                ModuleLabel it = (ModuleLabel)((Object)element$iv2);
                boolean bl = false;
                if (!it.isSelected()) continue;
                v0 = element$iv2;
                break block5;
            }
            v0 = null;
        }
        ModuleLabel moduleLabel = v0;
        if (moduleLabel != null) {
            moduleLabel.deselect();
        }
    }

    /*
     * WARNING - void declaration
     */
    public final void selectConfig(@NotNull String config) {
        Object v0;
        block3: {
            void $this$filterIsInstanceTo$iv$iv;
            Intrinsics.checkNotNullParameter((Object)config, (String)"config");
            ClientConfig.setActiveConfig(config);
            Iterable $this$filterIsInstance$iv = this.getConfigScroller().getAllChildren();
            boolean $i$f$filterIsInstance = false;
            Iterable iterable = $this$filterIsInstance$iv;
            Collection destination$iv$iv = new ArrayList();
            boolean $i$f$filterIsInstanceTo = false;
            for (Object element$iv$iv : $this$filterIsInstanceTo$iv$iv) {
                if (!(element$iv$iv instanceof ConfigLabel)) continue;
                destination$iv$iv.add(element$iv$iv);
            }
            Iterable $this$firstOrNull$iv = (List)destination$iv$iv;
            boolean $i$f$firstOrNull = false;
            for (Object element$iv : $this$firstOrNull$iv) {
                ConfigLabel it = (ConfigLabel)((Object)element$iv);
                boolean bl = false;
                if (!it.isSelected()) continue;
                v0 = element$iv;
                break block3;
            }
            v0 = null;
        }
        ConfigLabel configLabel = v0;
        if (configLabel != null) {
            configLabel.deselect();
        }
    }

    public void updateGuiScale() {
        this.setNewGuiScale(GuiScale.Companion.scaleForScreenSize$default((GuiScale.Companion)GuiScale.Companion, (int)0, (int)1, null).ordinal());
        super.updateGuiScale();
    }

    public boolean func_73868_f() {
        return false;
    }

    public void onScreenClose() {
        super.onScreenClose();
        if (CF4M.INSTANCE.moduleManager.isEnabled("ClickGUI")) {
            CF4M.INSTANCE.moduleManager.toggle("ClickGUI");
        }
        CF4M.INSTANCE.configManager.save();
    }

    static {
        KProperty[] arrkProperty = new KProperty[]{(KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(ConfigGUI.class, "content", "getContent()Lgg/essential/elementa/components/UIBlock;", 0))), (KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(ConfigGUI.class, "config", "getConfig()Lgg/essential/elementa/components/UIBlock;", 0))), (KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(ConfigGUI.class, "configText", "getConfigText()Lgg/essential/elementa/components/UIText;", 0))), (KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(ConfigGUI.class, "configSpacer", "getConfigSpacer()Lgg/essential/elementa/components/UIBlock;", 0))), (KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(ConfigGUI.class, "configContainer", "getConfigContainer()Lgg/essential/elementa/components/UIContainer;", 0))), (KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(ConfigGUI.class, "configScroller", "getConfigScroller()Lgg/essential/elementa/components/ScrollComponent;", 0))), (KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(ConfigGUI.class, "configScrollBar", "getConfigScrollBar()Lgg/essential/elementa/components/UIBlock;", 0))), (KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(ConfigGUI.class, "categoryContainer", "getCategoryContainer()Lgg/essential/elementa/components/UIContainer;", 0))), (KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(ConfigGUI.class, "bottomContent", "getBottomContent()Lgg/essential/elementa/components/UIContainer;", 0))), (KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(ConfigGUI.class, "scrollContainer", "getScrollContainer()Lgg/essential/elementa/components/UIContainer;", 0))), (KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(ConfigGUI.class, "moduleScroller", "getModuleScroller()Lgg/essential/elementa/components/ScrollComponent;", 0))), (KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(ConfigGUI.class, "moduleScrollBar", "getModuleScrollBar()Lgg/essential/elementa/components/UIBlock;", 0))), (KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(ConfigGUI.class, "settingContainer", "getSettingContainer()Lgg/essential/elementa/components/UIContainer;", 0))), (KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(ConfigGUI.class, "settingScroller", "getSettingScroller()Lgg/essential/elementa/components/ScrollComponent;", 0))), (KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(ConfigGUI.class, "settingScrollBar", "getSettingScrollBar()Lgg/essential/elementa/components/UIBlock;", 0))), (KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(ConfigGUI.class, "searchBar", "getSearchBar()Lxyz/apfelmus/cheeto/client/clickgui/settings/TextComponent;", 0)))};
        $$delegatedProperties = arrkProperty;
    }
}

