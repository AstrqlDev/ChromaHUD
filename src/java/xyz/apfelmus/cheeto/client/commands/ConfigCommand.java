/*
 * Decompiled with CFR 0.150.
 */
package xyz.apfelmus.cheeto.client.commands;

import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cf4m.annotation.command.Command;
import xyz.apfelmus.cf4m.annotation.command.Exec;
import xyz.apfelmus.cf4m.annotation.command.Param;
import xyz.apfelmus.cheeto.client.configs.ClientConfig;
import xyz.apfelmus.cheeto.client.utils.client.ChatUtils;

@Command(name={"config", "cfg"}, description="Command to manage configs")
public class ConfigCommand {
    @Exec
    public void exec(@Param(value="Action") String action) {
        if (action.equalsIgnoreCase("list") || action.equalsIgnoreCase("ls")) {
            ChatUtils.send("Available configs are: &7" + String.join((CharSequence)", ", ClientConfig.getConfigs()), new String[0]);
        } else if (action.equalsIgnoreCase("current") || action.equalsIgnoreCase("cur")) {
            ChatUtils.send("Current config: &a" + ClientConfig.getActiveConfig(), new String[0]);
        } else if (action.equalsIgnoreCase("set") || action.equalsIgnoreCase("rename") || action.equalsIgnoreCase("delete") || action.equalsIgnoreCase("remove") || action.equalsIgnoreCase("rm") || action.equalsIgnoreCase("create") || action.equalsIgnoreCase("new")) {
            ChatUtils.send("Not enough arguments!", new String[0]);
        } else {
            ChatUtils.send("Not a valid action, try: &7<list/ls>, <current/cur>, <set/load>, <delete/remove/rm>, <create/new>, <rename>", new String[0]);
        }
    }

    @Exec
    public void exec(@Param(value="Action") String action, @Param(value="Config") String configName) {
        if (action.equalsIgnoreCase("set") || action.equalsIgnoreCase("load")) {
            boolean set = ClientConfig.setActiveConfig(configName);
            if (set) {
                ChatUtils.send("Switched to config: &a" + ClientConfig.getActiveConfig(), new String[0]);
                CF4M.INSTANCE.configManager.save();
            } else {
                ChatUtils.send("Config &7" + configName + "&f doesn't exist", new String[0]);
                this.exec("ls");
            }
        } else if (action.equalsIgnoreCase("rename")) {
            if (ClientConfig.renameConfig(configName)) {
                ChatUtils.send("Renamed current config to: &7" + ClientConfig.getActiveConfig(), new String[0]);
            } else {
                ChatUtils.send("Something went wrong! \u00af\\_(\u30c4)_/\u00af", new String[0]);
            }
        } else if (action.equalsIgnoreCase("delete") || action.equalsIgnoreCase("remove") || action.equalsIgnoreCase("rm")) {
            if (ClientConfig.removeConfig(configName)) {
                ChatUtils.send("Removed current config, now using: &a" + ClientConfig.getActiveConfig(), new String[0]);
            } else {
                ChatUtils.send("Something went wrong! \u00af\\_(\u30c4)_/\u00af", new String[0]);
            }
        } else if (action.equalsIgnoreCase("create") || action.equalsIgnoreCase("new")) {
            if (ClientConfig.createConfig(configName)) {
                ChatUtils.send("Created new config: &a" + ClientConfig.getActiveConfig(), new String[0]);
            } else {
                ChatUtils.send("Config &7" + configName + "&f already exists!", new String[0]);
            }
        }
    }
}

