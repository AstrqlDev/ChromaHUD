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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cf4m.annotation.command.Command;
import xyz.apfelmus.cf4m.annotation.command.Exec;
import xyz.apfelmus.cf4m.annotation.command.Param;

public class CommandManager {
    private final HashMap<Object, String[]> commands = Maps.newHashMap();
    private final String prefix;

    public CommandManager() {
        this.prefix = CF4M.INSTANCE.configuration.prefix();
        try {
            for (Class<?> type : CF4M.INSTANCE.classManager.getClasses()) {
                if (!type.isAnnotationPresent(Command.class)) continue;
                this.commands.put(type.newInstance(), type.getAnnotation(Command.class).name());
            }
        }
        catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public boolean isCommand(String rawMessage) {
        boolean safe;
        if (!rawMessage.startsWith(this.prefix)) {
            return false;
        }
        boolean bl = safe = rawMessage.split(this.prefix).length > 1;
        if (safe) {
            String beheaded = rawMessage.split(this.prefix)[1];
            ArrayList args = Lists.newArrayList((Object[])beheaded.split(" "));
            String key = (String)args.get(0);
            args.remove(key);
            Object command = this.getCommand(key);
            if (command != null) {
                if (!this.execCommand(command, args)) {
                    for (Method method : command.getClass().getDeclaredMethods()) {
                        if (!method.isAnnotationPresent(Exec.class)) continue;
                        Parameter[] parameters = method.getParameters();
                        ArrayList params = Lists.newArrayList();
                        for (Parameter parameter : parameters) {
                            params.add("<" + (parameter.isAnnotationPresent(Param.class) ? parameter.getAnnotation(Param.class).value() : "NULL") + "|" + parameter.getType().getSimpleName() + ">");
                        }
                        CF4M.INSTANCE.configuration.message(key + " " + params);
                    }
                }
            } else {
                this.help();
            }
        } else {
            this.help();
        }
        return true;
    }

    private boolean execCommand(Object command, List<String> args) {
        for (Method method : command.getClass().getDeclaredMethods()) {
            method.setAccessible(true);
            if (method.getParameterTypes().length != args.size() || !method.isAnnotationPresent(Exec.class)) continue;
            ArrayList params = Lists.newArrayList();
            for (int i = 0; i < method.getParameterTypes().length; ++i) {
                String arg = args.get(i);
                Class<?> paramType = method.getParameterTypes()[i];
                try {
                    if (paramType.equals(Boolean.class)) {
                        params.add(Boolean.parseBoolean(arg));
                        continue;
                    }
                    if (paramType.equals(Integer.class)) {
                        params.add(Integer.parseInt(arg));
                        continue;
                    }
                    if (paramType.equals(Float.class)) {
                        params.add(Float.valueOf(Float.parseFloat(arg)));
                        continue;
                    }
                    if (paramType.equals(Double.class)) {
                        params.add(Double.parseDouble(arg));
                        continue;
                    }
                    if (paramType.equals(Long.class)) {
                        params.add(Long.parseLong(arg));
                        continue;
                    }
                    if (paramType.equals(Short.class)) {
                        params.add(Short.parseShort(arg));
                        continue;
                    }
                    if (paramType.equals(Byte.class)) {
                        params.add(Byte.parseByte(arg));
                        continue;
                    }
                    if (!paramType.equals(String.class)) continue;
                    params.add(String.valueOf(arg));
                    continue;
                }
                catch (Exception e) {
                    CF4M.INSTANCE.configuration.message(e.getMessage());
                    e.printStackTrace();
                }
            }
            try {
                if (params.size() == 0) {
                    method.invoke(command, new Object[0]);
                } else {
                    method.invoke(command, params.toArray());
                }
                return true;
            }
            catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private void help() {
        for (Map.Entry<Object, String[]> entry : this.commands.entrySet()) {
            CF4M.INSTANCE.configuration.message(Arrays.toString(entry.getValue()) + " - " + this.getDescription(entry.getKey()));
        }
    }

    public String getDescription(Object object) {
        if (this.commands.containsKey(object)) {
            return object.getClass().getAnnotation(Command.class).description();
        }
        return null;
    }

    public String[] getKey(Object object) {
        return this.commands.get(object);
    }

    private Object getCommand(String key) {
        for (Map.Entry<Object, String[]> entry : this.commands.entrySet()) {
            for (String s : entry.getValue()) {
                if (!s.equalsIgnoreCase(key)) continue;
                return entry.getKey();
            }
        }
        return null;
    }
}

