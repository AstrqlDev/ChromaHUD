/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 */
package xyz.apfelmus.cheeto.client.commands;

import org.lwjgl.input.Keyboard;
import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cf4m.annotation.command.Command;
import xyz.apfelmus.cf4m.annotation.command.Exec;
import xyz.apfelmus.cf4m.annotation.command.Param;

@Command(name={"bind", "b"}, description="Binds a module to a key")
public class BindCommand {
    @Exec
    public void exec(@Param(value="Module") String moduleName, @Param(value="Key") String key) {
        Object m = CF4M.INSTANCE.moduleManager.getModule(moduleName);
        if (m == null) {
            CF4M.INSTANCE.configuration.message("The module &l" + moduleName + "&r does not exist");
            return;
        }
        int bk = Keyboard.getKeyIndex((String)key.toUpperCase());
        if (key.equalsIgnoreCase("NONE")) {
            CF4M.INSTANCE.moduleManager.setKey(m, 0);
            CF4M.INSTANCE.configuration.message("&l" + CF4M.INSTANCE.moduleManager.getName(m) + "&r has been unbound");
            CF4M.INSTANCE.configManager.save();
        } else if (bk != 0) {
            CF4M.INSTANCE.moduleManager.setKey(m, bk);
            CF4M.INSTANCE.configuration.message("&l" + CF4M.INSTANCE.moduleManager.getName(m) + "&r has been bound to &7" + key.toUpperCase());
            CF4M.INSTANCE.configManager.save();
        } else {
            CF4M.INSTANCE.configuration.message("Invalid Key: &7" + key.toUpperCase());
        }
    }
}

