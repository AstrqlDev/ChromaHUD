/*
 * Decompiled with CFR 0.150.
 */
package xyz.apfelmus.cheeto.client.events;

import xyz.apfelmus.cf4m.event.Listener;

public class Render3DEvent
extends Listener {
    public float partialTicks;

    public Render3DEvent(float partialTicks) {
        super(Listener.At.HEAD);
        this.partialTicks = partialTicks;
    }
}

