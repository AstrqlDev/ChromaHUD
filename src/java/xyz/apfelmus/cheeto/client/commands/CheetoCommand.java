/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 */
package xyz.apfelmus.cheeto.client.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import xyz.apfelmus.cheeto.client.utils.client.ChatUtils;

public class CheetoCommand
extends CommandBase {
    public static final String COMMAND_NAME = "cheeto";
    public static final String COMMAND_USAGE = "/cheeto";

    public String func_71517_b() {
        return COMMAND_NAME;
    }

    public String func_71518_a(ICommandSender sender) {
        return COMMAND_USAGE;
    }

    public void func_71515_b(ICommandSender sender, String[] args) {
        ChatUtils.send("================================", new String[0]);
        ChatUtils.send("There's no command such as /cheeto you retard", new String[0]);
        ChatUtils.send("To bring up the GUI, type \",t ClickGUI\" in chat, or press the Right Control Key on your keyboard", new String[0]);
        ChatUtils.send("The Command prefix is \",\" and you can type \",help\" to get a list of commands", new String[0]);
        ChatUtils.send("Yeah, that's a fucking comma you dumbass bitch", new String[0]);
        ChatUtils.send("================================", new String[0]);
    }

    public boolean func_71519_b(ICommandSender sender) {
        return true;
    }
}

