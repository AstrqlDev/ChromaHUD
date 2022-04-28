/*
 * Decompiled with CFR 0.150.
 */
package xyz.apfelmus.cf4m.annotation.module;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import xyz.apfelmus.cf4m.module.Category;

@Target(value={ElementType.TYPE})
@Retention(value=RetentionPolicy.RUNTIME)
public @interface Module {
    public String name();

    public boolean enable() default false;

    public int key() default 0;

    public Category category() default Category.NONE;

    public String description() default "";

    public long time() default -1L;

    public boolean silent() default false;
}

