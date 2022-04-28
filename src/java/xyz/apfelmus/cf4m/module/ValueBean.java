/*
 * Decompiled with CFR 0.150.
 */
package xyz.apfelmus.cf4m.module;

import java.lang.reflect.Field;

public class ValueBean {
    private final String name;
    private final Field field;
    private final Object object;

    public ValueBean(String name, Field field, Object object) {
        this.name = name;
        this.field = field;
        this.object = object;
    }

    public String getName() {
        return this.name;
    }

    public Field getField() {
        return this.field;
    }

    public Object getObject() {
        return this.object;
    }
}

