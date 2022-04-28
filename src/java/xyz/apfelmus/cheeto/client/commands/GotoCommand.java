/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.BlockPos
 */
package xyz.apfelmus.cheeto.client.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cf4m.annotation.command.Command;
import xyz.apfelmus.cf4m.annotation.command.Exec;
import xyz.apfelmus.cf4m.annotation.command.Param;
import xyz.apfelmus.cheeto.client.utils.client.ChatUtils;
import xyz.apfelmus.cheeto.client.utils.math.VecUtils;
import xyz.apfelmus.cheeto.client.utils.pathfinding.Pathfinder;

@Command(name={"goto", "gt"}, description="Pathfinds towards a block")
public class GotoCommand {
    private static Minecraft mc = Minecraft.func_71410_x();

    @Exec
    public void exec(@Param(value="X") String x, @Param(value="Y") String y, @Param(value="Z") String z) {
        try {
            Pathfinder.setup(new BlockPos(VecUtils.floorVec(GotoCommand.mc.field_71439_g.func_174791_d())), new BlockPos(Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(z)), 0.0);
            if (Pathfinder.hasPath()) {
                CF4M.INSTANCE.moduleManager.toggle("Pathfinding");
            } else {
                ChatUtils.send(String.format("Could not find a path for X: %s, Y: %s, Z: %s", x, y, z), new String[0]);
            }
        }
        catch (NumberFormatException numberFormatException) {
            // empty catch block
        }
    }
}

