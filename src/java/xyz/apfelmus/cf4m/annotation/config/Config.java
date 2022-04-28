/*
 * Decompiled with CFR 0.150.
 */
package xyz.apfelmus.cf4m.annotation.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value={ElementType.TYPE})
@Retention(value=RetentionPolicy.RUNTIME)
public @interface Config {
    public String name();

    public String description() default "";
}

