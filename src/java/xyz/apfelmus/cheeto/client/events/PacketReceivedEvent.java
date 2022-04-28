/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 */
package xyz.apfelmus.cheeto.client.events;

import net.minecraft.network.Packet;
import xyz.apfelmus.cf4m.event.Listener;

public class PacketReceivedEvent
extends Listener {
    public Packet<?> packet;

    public PacketReceivedEvent(Packet<?> packet) {
        super(Listener.At.HEAD);
        this.packet = packet;
    }
}

