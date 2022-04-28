/*
 * Decompiled with CFR 0.150.
 */
package xyz.apfelmus.cf4m.event;

import java.lang.reflect.Method;

public class EventBean {
    private final Object object;
    private final Method method;
    private final int priority;

    public EventBean(Object object, Method method, int priority) {
        this.object = object;
        this.method = method;
        this.priority = priority;
    }

    public Object getObject() {
        return this.object;
    }

    public Method getMethod() {
        return this.method;
    }

    public int getPriority() {
        return this.priority;
    }
}

