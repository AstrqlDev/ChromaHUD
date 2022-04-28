/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 */
package xyz.apfelmus.cf4m.manager;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.stream.Collectors;
import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cf4m.annotation.Event;
import xyz.apfelmus.cf4m.annotation.module.Disable;
import xyz.apfelmus.cf4m.annotation.module.Enable;
import xyz.apfelmus.cf4m.annotation.module.Module;
import xyz.apfelmus.cf4m.annotation.module.extend.Extend;
import xyz.apfelmus.cf4m.annotation.module.extend.Name;
import xyz.apfelmus.cf4m.event.events.KeyboardEvent;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cf4m.module.ValueBean;

public class ModuleManager {
    private final LinkedHashMap<Object, LinkedHashSet<ValueBean>> modules = Maps.newLinkedHashMap();

    public ModuleManager() {
        CF4M.INSTANCE.eventManager.register(this);
        try {
            Class<?> extend = null;
            HashMap findFields = Maps.newHashMap();
            for (Class<?> type : CF4M.INSTANCE.classManager.getClasses()) {
                if (!type.isAnnotationPresent(Extend.class)) continue;
                extend = type;
                for (Field field : type.getDeclaredFields()) {
                    field.setAccessible(true);
                    if (!field.isAnnotationPresent(Name.class)) continue;
                    Name name = field.getAnnotation(Name.class);
                    findFields.put(name.name(), field);
                }
            }
            for (Class<?> type : CF4M.INSTANCE.classManager.getClasses()) {
                if (!type.isAnnotationPresent(Module.class)) continue;
                Object extendObject = extend != null ? extend.newInstance() : null;
                Object moduleObject = type.newInstance();
                LinkedHashSet valueBeans = Sets.newLinkedHashSet();
                for (Map.Entry entry : findFields.entrySet()) {
                    valueBeans.add(new ValueBean((String)entry.getKey(), (Field)entry.getValue(), extendObject));
                }
                this.modules.put(moduleObject, valueBeans);
            }
        }
        catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public String getName(Object module) {
        if (this.modules.containsKey(module)) {
            return module.getClass().getAnnotation(Module.class).name();
        }
        return null;
    }

    public long getActivatedTime(Object module) {
        if (this.modules.containsKey(module)) {
            return module.getClass().getAnnotation(Module.class).time();
        }
        return -1L;
    }

    public void setActivatedTime(Object module, long value) {
        if (this.modules.containsKey(module)) {
            try {
                this.TypeAnnotation(Proxy.getInvocationHandler(module.getClass().getAnnotation(Module.class)), "time", value);
            }
            catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isEnabled(Object module) {
        if (this.modules.containsKey(module)) {
            return module.getClass().getAnnotation(Module.class).enable();
        }
        return false;
    }

    public boolean isEnabled(String module) {
        Object mod = CF4M.INSTANCE.moduleManager.getModule(module);
        return this.isEnabled(mod);
    }

    private void setEnable(Object module, boolean value) {
        if (this.modules.containsKey(module)) {
            if (value) {
                this.setActivatedTime(module, System.currentTimeMillis());
            }
            try {
                this.TypeAnnotation(Proxy.getInvocationHandler(module.getClass().getAnnotation(Module.class)), "enable", value);
            }
            catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    public int getKey(Object module) {
        if (this.modules.containsKey(module)) {
            return module.getClass().getAnnotation(Module.class).key();
        }
        return 0;
    }

    public void setKey(Object module, int value) {
        if (this.modules.containsKey(module)) {
            try {
                this.TypeAnnotation(Proxy.getInvocationHandler(module.getClass().getAnnotation(Module.class)), "key", value);
            }
            catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    public Category getCategory(Object module) {
        if (this.modules.containsKey(module)) {
            return module.getClass().getAnnotation(Module.class).category();
        }
        return Category.NONE;
    }

    public String getDescription(Object module) {
        if (this.modules.containsKey(module)) {
            return module.getClass().getAnnotation(Module.class).description();
        }
        return "";
    }

    public boolean isSilent(Object module) {
        if (this.modules.containsKey(module)) {
            return module.getClass().getAnnotation(Module.class).silent();
        }
        return false;
    }

    public <T> T getValue(Object module, String name) {
        try {
            if (this.modules.containsKey(module)) {
                for (ValueBean valueBean : this.modules.get(module)) {
                    if (!valueBean.getName().equals(name)) continue;
                    return (T)valueBean.getField().get(valueBean.getObject());
                }
            }
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> void setValue(Object module, String name, T value) {
        try {
            if (this.modules.containsKey(module)) {
                for (ValueBean valueBean : this.modules.get(module)) {
                    if (!valueBean.getName().equals(name)) continue;
                    valueBean.getField().set(valueBean.getObject(), value);
                }
            }
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setEnabled(Object module, boolean enabled) {
        if (this.modules.containsKey(module)) {
            Class<?> type = module.getClass();
            if (enabled && this.isEnabled(module) || !enabled && !this.isEnabled(module)) {
                return;
            }
            this.setEnable(module, enabled);
            if (this.isEnabled(module)) {
                CF4M.INSTANCE.configuration.enable(module);
                CF4M.INSTANCE.eventManager.register(module);
            } else {
                CF4M.INSTANCE.configuration.disable(module);
                CF4M.INSTANCE.eventManager.unregister(module);
            }
            for (Method method : type.getDeclaredMethods()) {
                method.setAccessible(true);
                try {
                    if (this.isEnabled(module)) {
                        if (!method.isAnnotationPresent(Enable.class)) continue;
                        method.invoke(module, new Object[0]);
                        continue;
                    }
                    if (!method.isAnnotationPresent(Disable.class)) continue;
                    method.invoke(module, new Object[0]);
                }
                catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setEnabled(String module, boolean enabled) {
        this.setEnabled(this.getModule(module), enabled);
    }

    public void toggle(Object module) {
        if (this.modules.containsKey(module)) {
            Class<?> type = module.getClass();
            this.setEnable(module, !this.isEnabled(module));
            if (this.isEnabled(module)) {
                CF4M.INSTANCE.configuration.enable(module);
                CF4M.INSTANCE.eventManager.register(module);
            } else {
                CF4M.INSTANCE.configuration.disable(module);
                CF4M.INSTANCE.eventManager.unregister(module);
            }
            for (Method method : type.getDeclaredMethods()) {
                method.setAccessible(true);
                try {
                    if (this.isEnabled(module)) {
                        if (!method.isAnnotationPresent(Enable.class)) continue;
                        method.invoke(module, new Object[0]);
                        continue;
                    }
                    if (!method.isAnnotationPresent(Disable.class)) continue;
                    method.invoke(module, new Object[0]);
                }
                catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void toggle(String module) {
        this.toggle(this.getModule(module));
    }

    @Event
    private void onKey(KeyboardEvent e) {
        for (Object module : this.getModules()) {
            if (this.getKey(module) != e.getKey()) continue;
            this.toggle(module);
            CF4M.INSTANCE.configManager.save();
        }
    }

    private void TypeAnnotation(InvocationHandler invocationHandler, String name, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field memberValues = invocationHandler.getClass().getDeclaredField("memberValues");
        memberValues.setAccessible(true);
        Map map = (Map)memberValues.get(invocationHandler);
        map.put(name, value);
    }

    public ArrayList<Object> getModules() {
        return Lists.newArrayList(this.modules.keySet());
    }

    public ArrayList<Object> getModules(Category category) {
        return this.getModules().stream().filter(module -> this.getCategory(module).equals((Object)category)).collect(Collectors.toCollection(Lists::newArrayList));
    }

    public Object getModule(String name) {
        for (Object module : this.getModules()) {
            if (!this.getName(module).equalsIgnoreCase(name)) continue;
            return module;
        }
        return null;
    }
}

