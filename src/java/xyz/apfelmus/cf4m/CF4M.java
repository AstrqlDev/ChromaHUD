/*
 * Decompiled with CFR 0.150.
 */
package xyz.apfelmus.cf4m;

import java.io.File;
import xyz.apfelmus.cf4m.configuration.IConfiguration;
import xyz.apfelmus.cf4m.manager.ClassManager;
import xyz.apfelmus.cf4m.manager.CommandManager;
import xyz.apfelmus.cf4m.manager.ConfigManager;
import xyz.apfelmus.cf4m.manager.EventManager;
import xyz.apfelmus.cf4m.manager.ModuleManager;
import xyz.apfelmus.cf4m.manager.SettingManager;

public enum CF4M {
    INSTANCE;

    public String packName;
    public String dir;
    public IConfiguration configuration;
    public ClassManager classManager;
    public EventManager eventManager;
    public ModuleManager moduleManager;
    public SettingManager settingManager;
    public CommandManager commandManager;
    public ConfigManager configManager;

    public void run(Class<?> mainClass) {
        this.packName = mainClass.getPackage().getName();
        this.dir = new File(".", mainClass.getSimpleName()).toString();
        this.configuration = new IConfiguration(){};
        this.classManager = new ClassManager(mainClass.getClassLoader());
        this.eventManager = new EventManager();
        this.moduleManager = new ModuleManager();
        this.settingManager = new SettingManager();
        this.commandManager = new CommandManager();
        this.configManager = new ConfigManager();
        if (this.configuration.config()) {
            this.configManager.load();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> this.configManager.save()));
        }
    }

    public void run(Object o, String dir) {
        this.dir = dir;
        this.run(o.getClass());
    }
}

