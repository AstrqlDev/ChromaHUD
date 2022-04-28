/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.gameevent.InputEvent$MouseInputEvent
 */
package xyz.apfelmus.cheeto.client.events;

import net.minecraftforge.fml.common.gameevent.InputEvent;
import xyz.apfelmus.cf4m.event.Listener;

public class MouseInputEvent
extends Listener {
    public InputEvent.MouseInputEvent event;

    public MouseInputEvent(InputEvent.MouseInputEvent event) {
        super(Listener.At.HEAD);
        this.event = event;
    }
}

