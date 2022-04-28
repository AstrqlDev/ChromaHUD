/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  gg.essential.elementa.UIComponent
 *  gg.essential.elementa.UIConstraints
 *  gg.essential.elementa.constraints.ChildBasedSizeConstraint
 *  gg.essential.elementa.constraints.HeightConstraint
 *  gg.essential.elementa.constraints.WidthConstraint
 *  gg.essential.elementa.constraints.YConstraint
 *  gg.essential.elementa.dsl.ComponentsKt
 *  gg.essential.elementa.dsl.UtilitiesKt
 *  gg.essential.elementa.effects.OutlineEffect
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.collections.CollectionsKt
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.internal.DefaultConstructorMarker
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.PropertyReference1
 *  kotlin.jvm.internal.PropertyReference1Impl
 *  kotlin.jvm.internal.Reflection
 *  kotlin.properties.ReadWriteProperty
 *  kotlin.reflect.KProperty
 *  net.minecraft.client.resources.I18n
 *  org.jetbrains.annotations.NotNull
 */
package xyz.apfelmus.cheeto.client.clickgui.settings;

import gg.essential.elementa.UIComponent;
import gg.essential.elementa.UIConstraints;
import gg.essential.elementa.constraints.ChildBasedSizeConstraint;
import gg.essential.elementa.constraints.HeightConstraint;
import gg.essential.elementa.constraints.WidthConstraint;
import gg.essential.elementa.constraints.YConstraint;
import gg.essential.elementa.dsl.ComponentsKt;
import gg.essential.elementa.dsl.UtilitiesKt;
import gg.essential.elementa.effects.OutlineEffect;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import net.minecraft.client.resources.I18n;
import org.jetbrains.annotations.NotNull;
import xyz.apfelmus.cheeto.client.clickgui.settings.DropDown;
import xyz.apfelmus.cheeto.client.clickgui.settings.SettingComponent;

@Metadata(mv={1, 6, 0}, k=1, xi=48, d1={"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u001b\u0010\b\u001a\u00020\t8@X\u0080\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0012"}, d2={"Lxyz/apfelmus/cheeto/client/clickgui/settings/SelectorComponent;", "Lxyz/apfelmus/cheeto/client/clickgui/settings/SettingComponent;", "initialSelection", "", "options", "", "", "(ILjava/util/List;)V", "dropDown", "Lxyz/apfelmus/cheeto/client/clickgui/settings/DropDown;", "getDropDown$Cheeto", "()Lxyz/apfelmus/cheeto/client/clickgui/settings/DropDown;", "dropDown$delegate", "Lkotlin/properties/ReadWriteProperty;", "closePopups", "", "instantly", "", "Cheeto"})
public final class SelectorComponent
extends SettingComponent {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    @NotNull
    private final ReadWriteProperty dropDown$delegate;

    /*
     * WARNING - void declaration
     */
    public SelectorComponent(int initialSelection, @NotNull List<String> options) {
        Collection<String> collection;
        void $this$mapTo$iv$iv;
        void $this$map$iv;
        Intrinsics.checkNotNullParameter(options, (String)"options");
        Iterable iterable = options;
        int n = initialSelection;
        SelectorComponent selectorComponent = this;
        boolean $i$f$map = false;
        UIComponent uIComponent = $this$map$iv;
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)$this$map$iv, (int)10));
        boolean $i$f$mapTo = false;
        for (Object item$iv$iv : $this$mapTo$iv$iv) {
            void it;
            String string = (String)item$iv$iv;
            collection = destination$iv$iv;
            boolean bl = false;
            collection.add(I18n.func_135052_a((String)it, (Object[])new Object[0]));
        }
        collection = (List)destination$iv$iv;
        DefaultConstructorMarker defaultConstructorMarker = null;
        int n2 = 12;
        float f = 0.0f;
        OutlineEffect outlineEffect = null;
        List list = collection;
        int n3 = n;
        selectorComponent.dropDown$delegate = ComponentsKt.provideDelegate((UIComponent)ComponentsKt.childOf((UIComponent)((UIComponent)new DropDown(n3, list, outlineEffect, f, n2, defaultConstructorMarker)), (UIComponent)((UIComponent)this)), (Object)((Object)this), $$delegatedProperties[0]);
        UIComponent $this$constrain$iv = (UIComponent)this;
        boolean $i$f$constrain = false;
        UIComponent $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
        boolean bl = false;
        UIConstraints $this$_init__u24lambda_u2d1 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        boolean bl2 = false;
        $this$_init__u24lambda_u2d1.setY((YConstraint)UtilitiesKt.pixels$default((Number)18, (boolean)false, (boolean)false, (int)3, null));
        $this$_init__u24lambda_u2d1.setWidth((WidthConstraint)new ChildBasedSizeConstraint(0.0f, 1, null));
        $this$_init__u24lambda_u2d1.setHeight((HeightConstraint)new ChildBasedSizeConstraint(0.0f, 1, null));
        this.getDropDown$Cheeto().onValueChange((Function1<? super Integer, Unit>)((Function1)new Function1<Integer, Unit>(){

            public final void invoke(int newValue) {
                SettingComponent.changeValue$default(this, newValue, false, 2, null);
            }
        }));
    }

    @NotNull
    public final DropDown getDropDown$Cheeto() {
        return (DropDown)((Object)this.dropDown$delegate.getValue((Object)this, $$delegatedProperties[0]));
    }

    @Override
    public void closePopups(boolean instantly) {
        this.getDropDown$Cheeto().collapse(true, instantly);
    }

    static {
        KProperty[] arrkProperty = new KProperty[]{(KProperty)Reflection.property1((PropertyReference1)((PropertyReference1)new PropertyReference1Impl(SelectorComponent.class, "dropDown", "getDropDown$Cheeto()Lxyz/apfelmus/cheeto/client/clickgui/settings/DropDown;", 0)))};
        $$delegatedProperties = arrkProperty;
    }
}

