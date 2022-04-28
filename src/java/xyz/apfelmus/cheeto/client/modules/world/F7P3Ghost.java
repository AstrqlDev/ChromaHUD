/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.BlockPos
 *  net.minecraft.util.StringUtils
 */
package xyz.apfelmus.cheeto.client.modules.world;

import java.util.Arrays;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.util.StringUtils;
import xyz.apfelmus.cf4m.annotation.Event;
import xyz.apfelmus.cf4m.annotation.module.Module;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cheeto.client.events.ClientChatReceivedEvent;

@Module(name="F7P3Ghost", category=Category.WORLD)
public class F7P3Ghost {
    private static final int[][] BLOCK_POS_LIST = new int[][]{{275, 220, 231}, {275, 220, 232}, {299, 168, 243}, {299, 168, 244}, {299, 168, 246}, {299, 168, 247}, {299, 168, 247}, {300, 168, 247}, {300, 168, 246}, {300, 168, 244}, {300, 168, 243}, {298, 168, 247}, {298, 168, 246}, {298, 168, 244}, {298, 168, 243}, {287, 167, 240}, {288, 167, 240}, {289, 167, 240}, {290, 167, 240}, {291, 167, 240}, {292, 167, 240}, {293, 167, 240}, {294, 167, 240}, {295, 167, 240}, {290, 167, 239}, {291, 167, 239}, {292, 167, 239}, {293, 167, 239}, {294, 167, 239}, {295, 167, 239}, {290, 166, 239}, {291, 166, 239}, {292, 166, 239}, {293, 166, 239}, {294, 166, 239}, {295, 166, 239}, {290, 166, 240}, {291, 166, 240}, {292, 166, 240}, {293, 166, 240}, {294, 166, 240}, {295, 166, 240}};
    private static Minecraft mc = Minecraft.func_71410_x();

    @Event
    public void onChat(ClientChatReceivedEvent event) {
        String message = StringUtils.func_76338_a((String)event.message.func_150260_c());
        if (message.contains("test") || message.equals("[BOSS] Necron: You caused me many troubles, your journey ends here adventurers!")) {
            Arrays.stream(BLOCK_POS_LIST).forEach(v -> F7P3Ghost.mc.field_71441_e.func_175698_g(new BlockPos(v[0], v[1], v[2])));
        }
    }
}

