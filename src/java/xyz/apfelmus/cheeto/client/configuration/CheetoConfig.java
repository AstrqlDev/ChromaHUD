/*
 * Decompiled with CFR 0.150.
 */
package xyz.apfelmus.cheeto.client.configuration;

import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cf4m.annotation.Configuration;
import xyz.apfelmus.cf4m.configuration.IConfiguration;
import xyz.apfelmus.cheeto.client.configs.ClientConfig;
import xyz.apfelmus.cheeto.client.utils.client.ChatUtils;

@Configuration
public class CheetoConfig
implements IConfiguration {
    @Override
    public void message(String message) {
        ChatUtils.send(message, new String[0]);
    }

    @Override
    public void enable(Object module) {
        if (!(CF4M.INSTANCE.moduleManager.isSilent(module) || CF4M.INSTANCE.moduleManager.isEnabled("MuteNotifs") || ClientConfig.swapping)) {
            ChatUtils.send("&aEnabled &f&l%s", CF4M.INSTANCE.moduleManager.getName(module));
        }
    }

    @Override
    public void disable(Object module) {
        if (!(CF4M.INSTANCE.moduleManager.isSilent(module) || CF4M.INSTANCE.moduleManager.isEnabled("MuteNotifs") || ClientConfig.swapping)) {
            ChatUtils.send("&cDisabled &f&l%s", CF4M.INSTANCE.moduleManager.getName(module));
        }
    }
}

