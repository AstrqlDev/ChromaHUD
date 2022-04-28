/*
 * Decompiled with CFR 0.150.
 */
package xyz.apfelmus.cheeto.client.commands;

import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cf4m.annotation.command.Command;
import xyz.apfelmus.cf4m.annotation.command.Exec;
import xyz.apfelmus.cf4m.annotation.command.Param;

@Command(name={"toggle", "t"}, description="Toggles a module")
public class ToggleCommand {
    @Exec
    private void exec(@Param(value="module") String name) {
        Object module = CF4M.INSTANCE.moduleManager.getModule(name);
        if (module == null) {
            CF4M.INSTANCE.configuration.message("The module &l" + name + "&r does not exist.");
            return;
        }
        CF4M.INSTANCE.moduleManager.toggle(module);
        CF4M.INSTANCE.configManager.save();
    }
}

