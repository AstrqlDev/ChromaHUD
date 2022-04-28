/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.reflect.ClassPath
 *  com.google.common.reflect.ClassPath$ClassInfo
 */
package xyz.apfelmus.cf4m.manager;

import com.google.common.collect.Lists;
import com.google.common.reflect.ClassPath;
import java.io.IOException;
import java.util.ArrayList;
import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cf4m.annotation.Configuration;
import xyz.apfelmus.cf4m.configuration.IConfiguration;

public class ClassManager {
    private final ArrayList<Class<?>> classes = Lists.newArrayList();

    public ClassManager(ClassLoader classLoader) {
        try {
            for (ClassPath.ClassInfo info : ClassPath.from((ClassLoader)Thread.currentThread().getContextClassLoader()).getTopLevelClasses()) {
                if (!info.getName().startsWith(CF4M.INSTANCE.packName) || info.getName().contains("injection")) continue;
                Class<?> type = classLoader.loadClass(info.getName());
                if (type.isAnnotationPresent(Configuration.class)) {
                    CF4M.INSTANCE.configuration = (IConfiguration)type.newInstance();
                }
                this.classes.add(type);
            }
        }
        catch (IOException | ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Class<?>> getClasses() {
        return this.classes;
    }
}

