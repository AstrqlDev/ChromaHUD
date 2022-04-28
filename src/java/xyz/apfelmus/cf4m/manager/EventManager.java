/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 */
package xyz.apfelmus.cf4m.manager;

import com.google.common.collect.Maps;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import xyz.apfelmus.cf4m.annotation.Event;
import xyz.apfelmus.cf4m.event.EventBean;
import xyz.apfelmus.cf4m.event.Listener;

public class EventManager {
    private final LinkedHashMap<Class<? extends Listener>, CopyOnWriteArrayList<EventBean>> events = Maps.newLinkedHashMap();

    public void register(Object o) {
        Class<?> type = o.getClass();
        for (Method method : type.getDeclaredMethods()) {
            if (method.getParameterTypes().length != 1 || !method.isAnnotationPresent(Event.class)) continue;
            method.setAccessible(true);
            Class<?> listener2 = method.getParameterTypes()[0];
            EventBean eventBean = new EventBean(o, method, method.getAnnotation(Event.class).priority());
            if (this.events.containsKey(listener2)) {
                if (!this.events.get(listener2).contains(eventBean)) {
                    this.events.get(listener2).add(eventBean);
                }
            } else {
                this.events.put(listener2, new CopyOnWriteArrayList<EventBean>(Collections.singletonList(eventBean)));
            }
            this.events.values().forEach(methodBeans -> methodBeans.sort(Comparator.comparingInt(EventBean::getPriority)));
        }
    }

    public void unregister(Object o) {
        this.events.values().forEach(methodBeans -> methodBeans.removeIf(methodEventBean -> methodEventBean.getObject().equals(o)));
        this.events.entrySet().removeIf(event -> ((CopyOnWriteArrayList)event.getValue()).isEmpty());
    }

    public CopyOnWriteArrayList<EventBean> getEvent(Class<? extends Listener> type) {
        return this.events.get(type);
    }
}

