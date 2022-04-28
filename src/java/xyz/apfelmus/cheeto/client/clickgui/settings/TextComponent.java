/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  gg.essential.elementa.UIComponent
 *  gg.essential.elementa.UIConstraints
 *  gg.essential.elementa.components.UIBlock
 *  gg.essential.elementa.components.UIImage
 *  gg.essential.elementa.components.input.AbstractTextInput
 *  gg.essential.elementa.components.input.UIMultilineTextInput
 *  gg.essential.elementa.components.input.UIPasswordInput
 *  gg.essential.elementa.components.input.UITextInput
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
 *  gg.essential.elementa.dsl.BasicConstraintsKt
 *  gg.essential.elementa.dsl.ComponentsKt
 *  gg.essential.elementa.dsl.ConstraintsKt
 *  gg.essential.elementa.dsl.UtilitiesKt
 *  gg.essential.elementa.effects.Effect
 *  gg.essential.elementa.effects.OutlineEffect
 *  gg.essential.elementa.events.UIClickEvent
 *  gg.essential.elementa.state.BasicState
 *  gg.essential.elementa.state.ExtensionsKt
 *  gg.essential.elementa.state.State
 *  kotlin.Metadata
 *  kotlin.Unit
 *  kotlin.jvm.functions.Function1
 *  kotlin.jvm.functions.Function2
 *  kotlin.jvm.internal.Intrinsics
 *  kotlin.jvm.internal.Ref$BooleanRef
 *  org.jetbrains.annotations.NotNull
 */
package xyz.apfelmus.cheeto.client.clickgui.settings;

import gg.essential.elementa.UIComponent;
import gg.essential.elementa.UIConstraints;
import gg.essential.elementa.components.UIBlock;
import gg.essential.elementa.components.UIImage;
import gg.essential.elementa.components.input.AbstractTextInput;
import gg.essential.elementa.components.input.UIMultilineTextInput;
import gg.essential.elementa.components.input.UIPasswordInput;
import gg.essential.elementa.components.input.UITextInput;
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
import gg.essential.elementa.dsl.BasicConstraintsKt;
import gg.essential.elementa.dsl.ComponentsKt;
import gg.essential.elementa.dsl.ConstraintsKt;
import gg.essential.elementa.dsl.UtilitiesKt;
import gg.essential.elementa.effects.Effect;
import gg.essential.elementa.effects.OutlineEffect;
import gg.essential.elementa.events.UIClickEvent;
import gg.essential.elementa.state.BasicState;
import gg.essential.elementa.state.ExtensionsKt;
import gg.essential.elementa.state.State;
import java.awt.Color;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import xyz.apfelmus.cheeto.client.clickgui.settings.SettingComponent;
import xyz.apfelmus.cheeto.client.utils.client.ColorUtils;

@Metadata(mv={1, 6, 0}, k=1, xi=48, d1={"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\bJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0006H\u0016R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2={"Lxyz/apfelmus/cheeto/client/clickgui/settings/TextComponent;", "Lxyz/apfelmus/cheeto/client/clickgui/settings/SettingComponent;", "initial", "", "placeholder", "wrap", "", "protected", "(Ljava/lang/String;Ljava/lang/String;ZZ)V", "hasSetInitialText", "textHolder", "Lgg/essential/elementa/components/UIBlock;", "textInput", "Lgg/essential/elementa/components/input/AbstractTextInput;", "animationFrame", "", "closePopups", "instantly", "Cheeto"})
public final class TextComponent
extends SettingComponent {
    @NotNull
    private final String initial;
    @NotNull
    private final UIBlock textHolder;
    @NotNull
    private final AbstractTextInput textInput;
    private boolean hasSetInitialText;

    /*
     * WARNING - void declaration
     */
    public TextComponent(@NotNull String initial, @NotNull String placeholder, boolean wrap, boolean bl) {
        Object object;
        Color $this$onLeftClick$iv;
        UIConstraints $this$textInput_u24lambda_u2d2;
        AbstractTextInput abstractTextInput;
        TextComponent textComponent;
        UIConstraints $this$textInput_u24lambda_u2d1;
        UIConstraints $this$textHolder_u24lambda_u2d0;
        UIComponent uIComponent;
        Intrinsics.checkNotNullParameter((Object)initial, (String)"initial");
        Intrinsics.checkNotNullParameter((Object)placeholder, (String)"placeholder");
        this.initial = initial;
        Object $this$constrain$iv = (UIComponent)new UIBlock(null, 1, null);
        boolean $i$f$constrain = false;
        UIComponent $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
        boolean bl2 = false;
        UIConstraints uIConstraints = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        TextComponent textComponent2 = this;
        boolean bl3 = false;
        $this$textHolder_u24lambda_u2d0.setWidth((WidthConstraint)ConstraintsKt.plus((SuperConstraint)((SuperConstraint)new ChildBasedSizeConstraint(0.0f, 1, null)), (SuperConstraint)((SuperConstraint)UtilitiesKt.pixels$default((Number)(bl ? 14 : 6), (boolean)false, (boolean)false, (int)3, null))));
        $this$textHolder_u24lambda_u2d0.setHeight((HeightConstraint)ConstraintsKt.plus((SuperConstraint)((SuperConstraint)new ChildBasedSizeConstraint(0.0f, 1, null)), (SuperConstraint)((SuperConstraint)UtilitiesKt.pixels$default((Number)6, (boolean)false, (boolean)false, (int)3, null))));
        Color color = ColorUtils.MENU_BG;
        Intrinsics.checkNotNullExpressionValue((Object)color, (String)"MENU_BG");
        $this$textHolder_u24lambda_u2d0.setColor((ColorConstraint)UtilitiesKt.toConstraint((Color)color));
        UIComponent uIComponent2 = ComponentsKt.childOf((UIComponent)uIComponent, (UIComponent)((UIComponent)this));
        $this$constrain$iv = ColorUtils.M_BORDER;
        Intrinsics.checkNotNullExpressionValue((Object)$this$constrain$iv, (String)"M_BORDER");
        textComponent2.textHolder = (UIBlock)ComponentsKt.effect((UIComponent)uIComponent2, (Effect)((Effect)new OutlineEffect((Color)$this$constrain$iv, 1.0f, false, false, null, 28, null)));
        TextComponent textComponent3 = this;
        if (wrap) {
            $this$constrain$iv = (UIComponent)new UIMultilineTextInput(placeholder, false, null, null, false, null, null, null, 254, null);
            $i$f$constrain = false;
            $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
            bl2 = false;
            $this$textHolder_u24lambda_u2d0 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
            textComponent2 = textComponent3;
            boolean bl4 = false;
            $this$textInput_u24lambda_u2d1.setX((XConstraint)UtilitiesKt.pixels$default((Number)3, (boolean)false, (boolean)false, (int)3, null));
            $this$textInput_u24lambda_u2d1.setY((YConstraint)UtilitiesKt.pixels$default((Number)3, (boolean)false, (boolean)false, (int)3, null));
            $this$textInput_u24lambda_u2d1.setWidth(BasicConstraintsKt.basicWidthConstraint((Function1)((Function1)new Function1<UIComponent, Float>(this){
                final /* synthetic */ TextComponent this$0;
                {
                    this.this$0 = $receiver;
                    super(1);
                }

                @NotNull
                public final Float invoke(@NotNull UIComponent it) {
                    Intrinsics.checkNotNullParameter((Object)it, (String)"it");
                    return Float.valueOf(this.this$0.getParent().getWidth() * 0.4f);
                }
            })));
            textComponent = textComponent2;
            abstractTextInput = (AbstractTextInput)((UIMultilineTextInput)uIComponent).setMaxLines(10);
        } else if (bl) {
            $this$constrain$iv = (UIComponent)new UIPasswordInput('\u0000', placeholder, false, null, null, false, null, null, null, 509, null);
            $i$f$constrain = false;
            $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
            bl2 = false;
            $this$textInput_u24lambda_u2d1 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
            textComponent2 = textComponent3;
            boolean bl5 = false;
            $this$textInput_u24lambda_u2d2.setX((XConstraint)UtilitiesKt.pixels$default((Number)3, (boolean)false, (boolean)false, (int)3, null));
            $this$textInput_u24lambda_u2d2.setY((YConstraint)UtilitiesKt.pixels$default((Number)3, (boolean)false, (boolean)false, (int)3, null));
            textComponent = textComponent2;
            abstractTextInput = (AbstractTextInput)((UIPasswordInput)uIComponent).setMinWidth((WidthConstraint)UtilitiesKt.pixels$default((Number)50, (boolean)false, (boolean)false, (int)3, null)).setMaxWidth(BasicConstraintsKt.basicWidthConstraint((Function1)((Function1)new Function1<UIComponent, Float>(this){
                final /* synthetic */ TextComponent this$0;
                {
                    this.this$0 = $receiver;
                    super(1);
                }

                @NotNull
                public final Float invoke(@NotNull UIComponent it) {
                    Intrinsics.checkNotNullParameter((Object)it, (String)"it");
                    return Float.valueOf(this.this$0.getParent().getWidth() * 0.5f);
                }
            })));
        } else {
            void $this$textInput_u24lambda_u2d3;
            $this$constrain$iv = (UIComponent)new UITextInput(placeholder, false, null, null, false, null, null, null, 254, null);
            $i$f$constrain = false;
            $this$constrain_u24lambda_u2d0$iv = uIComponent = $this$constrain$iv;
            bl2 = false;
            $this$textInput_u24lambda_u2d2 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
            textComponent2 = textComponent3;
            boolean bl6 = false;
            $this$textInput_u24lambda_u2d3.setX((XConstraint)UtilitiesKt.pixels$default((Number)3, (boolean)false, (boolean)false, (int)3, null));
            $this$textInput_u24lambda_u2d3.setY((YConstraint)UtilitiesKt.pixels$default((Number)3, (boolean)false, (boolean)false, (int)3, null));
            textComponent = textComponent2;
            abstractTextInput = (AbstractTextInput)((UITextInput)uIComponent).setMinWidth((WidthConstraint)UtilitiesKt.pixels$default((Number)50, (boolean)false, (boolean)false, (int)3, null)).setMaxWidth(BasicConstraintsKt.basicWidthConstraint((Function1)((Function1)new Function1<UIComponent, Float>(this){
                final /* synthetic */ TextComponent this$0;
                {
                    this.this$0 = $receiver;
                    super(1);
                }

                @NotNull
                public final Float invoke(@NotNull UIComponent it) {
                    Intrinsics.checkNotNullParameter((Object)it, (String)"it");
                    return Float.valueOf(this.this$0.getParent().getWidth() * 0.5f);
                }
            })));
        }
        textComponent.textInput = abstractTextInput;
        ComponentsKt.childOf((UIComponent)((UIComponent)this.textInput), (UIComponent)((UIComponent)this.textHolder));
        $this$constrain$iv = (UIComponent)this.textInput.onUpdate((Function1)new Function1<String, Unit>(){

            public final void invoke(@NotNull String newText) {
                Intrinsics.checkNotNullParameter((Object)newText, (String)"newText");
                SettingComponent.changeValue$default(this, newText, false, 2, null);
            }
        });
        boolean $i$f$onLeftClick = false;
        $this$onLeftClick$iv.onMouseClick((Function2)new Function2<UIComponent, UIClickEvent, Unit>(this){
            final /* synthetic */ TextComponent this$0;
            {
                this.this$0 = textComponent;
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
                    event.stopPropagation();
                    TextComponent.access$getTextInput$p(this.this$0).grabWindowFocus();
                }
            }
        }).onFocus((Function1)new Function1<UIComponent, Unit>(){

            public final void invoke(@NotNull UIComponent $this$onFocus) {
                Intrinsics.checkNotNullParameter((Object)$this$onFocus, (String)"$this$onFocus");
                textInput.setActive(true);
            }
        }).onFocusLost((Function1)new Function1<UIComponent, Unit>(){

            public final void invoke(@NotNull UIComponent $this$onFocusLost) {
                Intrinsics.checkNotNullParameter((Object)$this$onFocusLost, (String)"$this$onFocusLost");
                textInput.setActive(false);
            }
        });
        $this$onLeftClick$iv = ColorUtils.LABEL;
        Intrinsics.checkNotNullExpressionValue((Object)$this$onLeftClick$iv, (String)"LABEL");
        this.textInput.setColor($this$onLeftClick$iv);
        if (bl) {
            void $this$onLeftClick$iv2;
            UIComponent uIComponent3;
            final Ref.BooleanRef toggle = new Ref.BooleanRef();
            UIComponent $this$constrain$iv2 = (UIComponent)UIImage.Companion.ofResourceCached("/vigilance/eye.png");
            boolean $i$f$constrain2 = false;
            UIComponent $this$constrain_u24lambda_u2d0$iv2 = uIComponent3 = $this$constrain$iv2;
            $i$a$-apply-ComponentsKt$constrain$1$iv = false;
            UIConstraints $this$_init__u24lambda_u2d5 = $this$constrain_u24lambda_u2d0$iv2.getConstraints();
            boolean bl7 = false;
            $this$_init__u24lambda_u2d5.setY((YConstraint)UtilitiesKt.pixels$default((Number)3, (boolean)false, (boolean)false, (int)3, null));
            $this$_init__u24lambda_u2d5.setX((XConstraint)UtilitiesKt.pixels$default((Number)3, (boolean)true, (boolean)false, (int)2, null));
            $this$_init__u24lambda_u2d5.setWidth((WidthConstraint)UtilitiesKt.pixels$default((Number)12, (boolean)false, (boolean)false, (int)3, null));
            $this$_init__u24lambda_u2d5.setHeight(BasicConstraintsKt.basicHeightConstraint((Function1)((Function1)new Function1<UIComponent, Float>(this){
                final /* synthetic */ TextComponent this$0;
                {
                    this.this$0 = $receiver;
                    super(1);
                }

                @NotNull
                public final Float invoke(@NotNull UIComponent it) {
                    Intrinsics.checkNotNullParameter((Object)it, (String)"it");
                    return Float.valueOf(TextComponent.access$getTextInput$p(this.this$0).getHeight());
                }
            })));
            Color color2 = ColorUtils.SUB_LABEL;
            Intrinsics.checkNotNullExpressionValue((Object)color2, (String)"SUB_LABEL");
            $this$_init__u24lambda_u2d5.setColor((ColorConstraint)UtilitiesKt.toConstraint((Color)color2));
            $this$constrain$iv2 = ((UIImage)uIComponent3).onMouseEnter((Function1)new Function1<UIComponent, Unit>(){

                public final void invoke(@NotNull UIComponent $this$onMouseEnter) {
                    Intrinsics.checkNotNullParameter((Object)$this$onMouseEnter, (String)"$this$onMouseEnter");
                    if (!toggle.element) {
                        AnimatingConstraints anim$iv;
                        UIComponent uIComponent;
                        UIComponent $this$animate$iv = $this$onMouseEnter;
                        boolean $i$f$animate = false;
                        UIComponent $this$animate_u24lambda_u2d0$iv = uIComponent = $this$animate$iv;
                        boolean bl = false;
                        AnimatingConstraints $this$invoke_u24lambda_u2d0 = anim$iv = $this$animate_u24lambda_u2d0$iv.makeAnimation();
                        boolean bl2 = false;
                        AnimationStrategy animationStrategy = (AnimationStrategy)Animations.OUT_EXP;
                        Color color = ColorUtils.LABEL;
                        Intrinsics.checkNotNullExpressionValue((Object)color, (String)"LABEL");
                        AnimatingConstraints.setColorAnimation$default((AnimatingConstraints)$this$invoke_u24lambda_u2d0, (AnimationStrategy)animationStrategy, (float)0.2f, (ColorConstraint)((ColorConstraint)UtilitiesKt.toConstraint((Color)color)), (float)0.0f, (int)8, null);
                        $this$animate_u24lambda_u2d0$iv.animateTo(anim$iv);
                    }
                }
            }).onMouseLeave((Function1)new Function1<UIComponent, Unit>(){

                public final void invoke(@NotNull UIComponent $this$onMouseLeave) {
                    Intrinsics.checkNotNullParameter((Object)$this$onMouseLeave, (String)"$this$onMouseLeave");
                    if (!toggle.element) {
                        AnimatingConstraints anim$iv;
                        UIComponent uIComponent;
                        UIComponent $this$animate$iv = $this$onMouseLeave;
                        boolean $i$f$animate = false;
                        UIComponent $this$animate_u24lambda_u2d0$iv = uIComponent = $this$animate$iv;
                        boolean bl = false;
                        AnimatingConstraints $this$invoke_u24lambda_u2d0 = anim$iv = $this$animate_u24lambda_u2d0$iv.makeAnimation();
                        boolean bl2 = false;
                        AnimationStrategy animationStrategy = (AnimationStrategy)Animations.OUT_EXP;
                        Color color = ColorUtils.SUB_LABEL;
                        Intrinsics.checkNotNullExpressionValue((Object)color, (String)"SUB_LABEL");
                        AnimatingConstraints.setColorAnimation$default((AnimatingConstraints)$this$invoke_u24lambda_u2d0, (AnimationStrategy)animationStrategy, (float)0.2f, (ColorConstraint)((ColorConstraint)UtilitiesKt.toConstraint((Color)color)), (float)0.0f, (int)8, null);
                        $this$animate_u24lambda_u2d0$iv.animateTo(anim$iv);
                    }
                }
            });
            boolean $i$f$onLeftClick2 = false;
            ComponentsKt.childOf((UIComponent)$this$onLeftClick$iv2.onMouseClick((Function2)new Function2<UIComponent, UIClickEvent, Unit>(toggle, this){
                final /* synthetic */ Ref.BooleanRef $toggle$inlined;
                final /* synthetic */ TextComponent this$0;
                {
                    this.$toggle$inlined = booleanRef;
                    this.this$0 = textComponent;
                    super(2);
                }

                public final void invoke(@NotNull UIComponent $this$onMouseClick, @NotNull UIClickEvent it) {
                    Intrinsics.checkNotNullParameter((Object)$this$onMouseClick, (String)"$this$onMouseClick");
                    Intrinsics.checkNotNullParameter((Object)it, (String)"it");
                    if (it.getMouseButton() == 0) {
                        AnimatingConstraints anim$iv;
                        UIComponent uIComponent;
                        UIClickEvent uIClickEvent = it;
                        UIComponent $this$_init__u24lambda_u2d7 = $this$onMouseClick;
                        boolean bl = false;
                        this.$toggle$inlined.element = !this.$toggle$inlined.element;
                        ((UIPasswordInput)TextComponent.access$getTextInput$p(this.this$0)).setProtection(!this.$toggle$inlined.element);
                        UIComponent $this$animate$iv = $this$_init__u24lambda_u2d7;
                        boolean $i$f$animate = false;
                        UIComponent $this$animate_u24lambda_u2d0$iv = uIComponent = $this$animate$iv;
                        boolean bl2 = false;
                        AnimatingConstraints $this$lambda_u2d7_u24lambda_u2d6 = anim$iv = $this$animate_u24lambda_u2d0$iv.makeAnimation();
                        boolean bl3 = false;
                        AnimatingConstraints.setColorAnimation$default((AnimatingConstraints)$this$lambda_u2d7_u24lambda_u2d6, (AnimationStrategy)((AnimationStrategy)Animations.OUT_EXP), (float)0.2f, (ColorConstraint)((ColorConstraint)ExtensionsKt.toConstraint((State)((State)(this.$toggle$inlined.element ? new BasicState((Object)Color.RED) : new BasicState((Object)Color.GRAY))))), (float)0.0f, (int)8, null);
                        $this$animate_u24lambda_u2d0$iv.animateTo(anim$iv);
                    }
                }
            }), (UIComponent)((UIComponent)this.textHolder));
            this.textHolder.setHeight(BasicConstraintsKt.basicHeightConstraint((Function1)((Function1)new Function1<UIComponent, Float>(){

                @NotNull
                public final Float invoke(@NotNull UIComponent it) {
                    Intrinsics.checkNotNullParameter((Object)it, (String)"it");
                    return Float.valueOf(textInput.getHeight() + 6.0f);
                }
            })));
        }
        $this$constrain$iv = (UIComponent)this;
        $i$f$constrain = false;
        $this$constrain_u24lambda_u2d0$iv = object = $this$constrain$iv;
        $i$a$-apply-ComponentsKt$constrain$1$iv = false;
        UIConstraints $this$_init__u24lambda_u2d8 = $this$constrain_u24lambda_u2d0$iv.getConstraints();
        boolean bl8 = false;
        $this$_init__u24lambda_u2d8.setWidth((WidthConstraint)new ChildBasedSizeConstraint(0.0f, 1, null));
        $this$_init__u24lambda_u2d8.setHeight((HeightConstraint)new ChildBasedSizeConstraint(0.0f, 1, null));
    }

    public void animationFrame() {
        super.animationFrame();
        if (!this.hasSetInitialText) {
            this.textInput.setText(this.initial);
            this.hasSetInitialText = true;
        }
    }

    @Override
    public void closePopups(boolean instantly) {
        this.textInput.setActive(false);
    }
}

