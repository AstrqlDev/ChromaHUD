/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.IChatComponent
 */
package xyz.apfelmus.cheeto.client.events;

import net.minecraft.util.IChatComponent;
import xyz.apfelmus.cf4m.event.Listener;

public class ClientChatReceivedEvent
extends Listener {
    public IChatComponent message;
    public final byte type;

    public ClientChatReceivedEvent(byte type, IChatComponent message) {
        super(Listener.At.HEAD);
        this.type = type;
        this.message = message;
    }
}

