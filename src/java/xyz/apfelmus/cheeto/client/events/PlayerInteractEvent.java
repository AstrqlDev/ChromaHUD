/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.BlockPos
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$Action
 */
package xyz.apfelmus.cheeto.client.events;

import net.minecraft.util.BlockPos;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import xyz.apfelmus.cf4m.event.Listener;

public class PlayerInteractEvent
extends Listener {
    public PlayerInteractEvent.Action action;
    public BlockPos pos;

    public PlayerInteractEvent(PlayerInteractEvent.Action action, BlockPos pos) {
        super(Listener.At.HEAD);
        this.action = action;
        this.pos = pos;
    }
}

