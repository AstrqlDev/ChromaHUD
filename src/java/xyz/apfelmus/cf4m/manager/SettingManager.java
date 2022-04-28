/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.LinkedHashMultimap
 */
package xyz.apfelmus.cf4m.manager;

import com.google.common.collect.LinkedHashMultimap;
import java.lang.reflect.Field;
import java.util.ArrayList;
import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cf4m.annotation.Setting;

public class SettingManager {
    private final LinkedHashMultimap<Object, Field> settings = LinkedHashMultimap.create();

    public SettingManager() {
        for (Object module : CF4M.INSTANCE.moduleManager.getModules()) {
            for (Field field : module.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (!field.isAnnotationPresent(Setting.class)) continue;
                this.settings.put(module, (Object)field);
            }
        }
    }

    public String getName(Object module, Object setting) {
        if (this.settings.containsKey(module)) {
            for (Field field : this.settings.get(module)) {
                try {
                    if (!field.get(module).equals(setting)) continue;
                    return field.getAnnotation(Setting.class).name();
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public String getDescription(Object module, Object setting) {
        if (this.settings.containsKey(module)) {
            for (Field field : this.settings.get(module)) {
                try {
                    if (!field.get(module).equals(setting)) continue;
                    return field.getAnnotation(Setting.class).description();
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public Object getSetting(Object module, String name) {
        for (Object setting : this.getSettings(module)) {
            if (!this.getName(module, setting).equalsIgnoreCase(name)) continue;
            return setting;
        }
        return null;
    }

    public ArrayList<Object> getSettings(Object module) {
        if (this.settings.containsKey(module)) {
            ArrayList<Object> setting = new ArrayList<Object>();
            this.settings.get(module).forEach(field -> {
                try {
                    setting.add(field.get(module));
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
            return setting;
        }
        return null;
    }
}

