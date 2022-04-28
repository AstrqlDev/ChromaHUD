/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 */
package xyz.apfelmus.cf4m.manager;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cf4m.annotation.config.Config;
import xyz.apfelmus.cf4m.annotation.config.Load;
import xyz.apfelmus.cf4m.annotation.config.Save;

public class ConfigManager {
    private HashMap<String, Object> configs = Maps.newHashMap();

    public ConfigManager() {
        new File(CF4M.INSTANCE.dir).mkdir();
        new File(CF4M.INSTANCE.dir, "configs").mkdir();
        try {
            for (Class<?> type : CF4M.INSTANCE.classManager.getClasses()) {
                if (!type.isAnnotationPresent(Config.class)) continue;
                this.configs.put(type.getAnnotation(Config.class).name(), type.newInstance());
            }
        }
        catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public Object getConfig(String name) {
        return this.configs.getOrDefault(name, null);
    }

    public String getName(Object object) {
        for (Map.Entry<String, Object> e : this.configs.entrySet()) {
            if (!e.getValue().equals(object)) continue;
            return e.getKey();
        }
        return null;
    }

    public String getPath(Object object) {
        if (this.configs.containsValue(object)) {
            return CF4M.INSTANCE.dir + File.separator + "configs" + File.separator + this.getName(object) + ".json";
        }
        return null;
    }

    public ArrayList<String> getConfigs() {
        return Lists.newArrayList(this.configs.keySet());
    }

    public void load() {
        this.configs.values().forEach(config -> {
            for (Method method : config.getClass().getMethods()) {
                method.setAccessible(true);
                if (!method.isAnnotationPresent(Load.class)) continue;
                try {
                    method.invoke(config, new Object[0]);
                }
                catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void save() {
        if (CF4M.INSTANCE.moduleManager.isEnabled("ClickGUI")) {
            CF4M.INSTANCE.moduleManager.toggle("ClickGUI");
        }
        this.configs.values().forEach(config -> {
            for (Method method : config.getClass().getMethods()) {
                method.setAccessible(true);
                if (!method.isAnnotationPresent(Save.class)) continue;
                try {
                    method.invoke(config, new Object[0]);
                }
                catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

