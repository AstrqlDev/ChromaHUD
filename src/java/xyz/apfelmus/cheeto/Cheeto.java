/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.command.ICommand
 *  net.minecraftforge.client.ClientCommandHandler
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  org.lwjgl.opengl.Display
 */
package xyz.apfelmus.cheeto;

import java.awt.Color;
import javax.swing.JOptionPane;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.lwjgl.opengl.Display;
import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cheeto.client.commands.CheetoCommand;
import xyz.apfelmus.cheeto.client.listener.EventListenerRegister;
import xyz.apfelmus.cheeto.client.modules.render.ClickGUI;
import xyz.apfelmus.cheeto.client.utils.client.ColorUtils;
import xyz.apfelmus.cheeto.client.utils.client.KeybindUtils;
import xyz.apfelmus.cheeto.client.utils.client.VersionCheck;

@Mod(modid="ChromaHUD", version="3.0", acceptedMinecraftVersions="[1.8.9]")
public class Cheeto {
    public static final String MODID = "ChromaHUD";
    public static final String VERSION = "3.0";
    public static String name = "Cheeto";
    public static String author = "Apfelsaft";
    public static String version = "6.9";
    public static String game = "1.8.9";
    public static double clientVersion = 1.2;
    private EventListenerRegister eventListenerRegister;

    public static void Start() {
        CF4M.INSTANCE.run(new Cheeto(), Minecraft.func_71410_x().field_71412_D + "/" + name);
        Display.setTitle((String)(name + " v" + version + " | Author: " + author + " | Minecraft " + game));
        KeybindUtils.setup();
        if (((ClickGUI)CF4M.INSTANCE.moduleManager.getModule((String)"ClickGUI")).theme.getCurrent().equals("Classic")) {
            ColorUtils.MENU_BG = new Color(22, 22, 22);
            ColorUtils.TEXT_HOVERED = new Color(200, 200, 200);
            ColorUtils.HUD_BG = new Color(0, 0, 0, 150);
            ColorUtils.SELECT = new Color(132, 132, 132);
            ColorUtils.LABEL = new Color(150, 150, 150);
            ColorUtils.SUB_LABEL = new Color(100, 100, 100);
            ColorUtils.SELECTED = new Color(55, 174, 160);
            ColorUtils.M_BORDER = new Color(42, 42, 42);
            ColorUtils.C_BORDER = new Color(55, 174, 160, 100);
        } else {
            ColorUtils.MENU_BG = new Color(22, 22, 22);
            ColorUtils.TEXT_HOVERED = new Color(200, 200, 200);
            ColorUtils.HUD_BG = new Color(0, 0, 0, 150);
            ColorUtils.SELECT = new Color(215, 173, 255, 100);
            ColorUtils.LABEL = new Color(150, 150, 150);
            ColorUtils.SUB_LABEL = new Color(100, 100, 100);
            ColorUtils.SELECTED = new Color(211, 165, 255);
            ColorUtils.M_BORDER = new Color(181, 140, 236);
            ColorUtils.C_BORDER = new Color(215, 173, 255, 100);
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        VersionCheck.check();
        if (VersionCheck.fuckNiggers) {
            String SENDING_SESSION_ID_TO_APFELSAFT = "Pussy bitch nigga go kill yourself";
            JOptionPane.showMessageDialog(null, SENDING_SESSION_ID_TO_APFELSAFT);
            long[][][][][][][] array = new long[1337][1337][1337][1337][1337][1337][1337];
            System.exit(-1);
            throw new Error();
        }
        ClientCommandHandler.instance.func_71560_a((ICommand)new CheetoCommand());
        this.eventListenerRegister = new EventListenerRegister();
        MinecraftForge.EVENT_BUS.register((Object)this.eventListenerRegister);
    }
}

