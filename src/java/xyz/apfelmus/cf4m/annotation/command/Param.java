/*
 * Decompiled with CFR 0.150.
 */
package xyz.apfelmus.cf4m.annotation.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value={ElementType.PARAMETER})
@Retention(value=RetentionPolicy.RUNTIME)
public @interface Param {
    public String value();
}

