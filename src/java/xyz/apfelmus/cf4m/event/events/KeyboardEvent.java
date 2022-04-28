/*
 * Decompiled with CFR 0.150.
 */
package xyz.apfelmus.cf4m.event.events;

import xyz.apfelmus.cf4m.event.Listener;

public class KeyboardEvent
extends Listener {
    private final int key;

    public KeyboardEvent(int key) {
        super(Listener.At.NONE);
        this.key = key;
    }

    public int getKey() {
        return this.key;
    }
}

