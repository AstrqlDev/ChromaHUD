/*
 * Decompiled with CFR 0.150.
 */
package xyz.apfelmus.cheeto.client.commands;

import java.util.ArrayList;
import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cf4m.annotation.command.Command;
import xyz.apfelmus.cf4m.annotation.command.Exec;
import xyz.apfelmus.cf4m.annotation.command.Param;
import xyz.apfelmus.cheeto.client.settings.BooleanSetting;
import xyz.apfelmus.cheeto.client.settings.FloatSetting;
import xyz.apfelmus.cheeto.client.settings.IntegerSetting;
import xyz.apfelmus.cheeto.client.settings.ModeSetting;

@Command(name={"set", "s"}, description="Sets a setting of a module")
public class SetCommand {
    private Object currentModule;
    private ArrayList<Object> settings;
    private Object currentSetting;

    @Exec
    public void exec(@Param(value="Module") String moduleName) {
        this.printModule(moduleName);
        CF4M.INSTANCE.configuration.message("No setting for &l" + moduleName + "&r specified.");
        this.printSettings();
    }

    private void printModule(@Param(value="Module") String moduleName) {
        this.currentModule = CF4M.INSTANCE.moduleManager.getModule(moduleName);
        if (this.currentModule == null) {
            CF4M.INSTANCE.configuration.message("The module &l" + moduleName + "&r does not exist");
            return;
        }
        this.settings = CF4M.INSTANCE.settingManager.getSettings(this.currentModule);
        if (this.settings == null) {
            CF4M.INSTANCE.configuration.message("The module &l" + moduleName + "&r has no settings");
        }
    }

    private void printSettings() {
        CF4M.INSTANCE.configuration.message("Here is the list of settings:");
        for (Object s : this.settings) {
            CF4M.INSTANCE.configuration.message(CF4M.INSTANCE.settingManager.getName(this.currentModule, s) + "(" + s.getClass().getSimpleName() + ")" + CF4M.INSTANCE.settingManager.getDescription(this.currentModule, s));
            if (!(s instanceof ModeSetting)) continue;
            ((ModeSetting)s).getModes().forEach(CF4M.INSTANCE.configuration::message);
        }
    }

    private void printModuleSettings(@Param(value="Module") String moduleName, @Param(value="Setting") String settingName) {
        this.printModule(moduleName);
        Object setting = CF4M.INSTANCE.settingManager.getSetting(this.currentModule, settingName);
        if (setting != null) {
            this.currentSetting = setting;
            CF4M.INSTANCE.configuration.message(CF4M.INSTANCE.settingManager.getName(this.currentModule, this.currentSetting) + " > " + this.currentSetting.getClass().getSimpleName());
        } else {
            CF4M.INSTANCE.configuration.message("The setting &7" + settingName + "&f does not exist");
            this.printSettings();
        }
    }

    @Exec
    public void exec(@Param(value="Module") String moduleName, @Param(value="Setting") String settingName) {
        this.printModuleSettings(moduleName, settingName);
    }

    @Exec
    public void exec(@Param(value="Module") String moduleName, @Param(value="Setting") String settingName, @Param(value="SettingValue") String settingValue) {
        this.printModuleSettings(moduleName, settingName);
        try {
            if (this.currentSetting instanceof BooleanSetting) {
                ((BooleanSetting)this.currentSetting).setState(Boolean.parseBoolean(settingValue));
            } else if (this.currentSetting instanceof FloatSetting) {
                ((FloatSetting)this.currentSetting).setCurrent(Float.valueOf(Float.parseFloat(settingValue)));
            } else if (this.currentSetting instanceof IntegerSetting) {
                ((IntegerSetting)this.currentSetting).setCurrent(Integer.parseInt(settingValue));
            } else if (this.currentSetting instanceof ModeSetting) {
                ((ModeSetting)this.currentSetting).setCurrent(settingValue);
            }
            CF4M.INSTANCE.configuration.message("&7" + CF4M.INSTANCE.settingManager.getName(this.currentModule, this.currentSetting) + "&f was set to &7" + settingValue);
            CF4M.INSTANCE.configManager.save();
        }
        catch (NumberFormatException e) {
            CF4M.INSTANCE.configuration.message("&cAn Error occured while trying to set &l" + settingName + " &r&cto &l" + settingValue);
        }
    }
}

