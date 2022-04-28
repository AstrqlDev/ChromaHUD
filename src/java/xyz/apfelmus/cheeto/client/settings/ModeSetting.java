/*
 * Decompiled with CFR 0.150.
 */
package xyz.apfelmus.cheeto.client.settings;

import java.util.List;

public class ModeSetting {
    private String current;
    private final List<String> modes;

    public ModeSetting(String current, List<String> modes) {
        this.current = current;
        this.modes = modes;
    }

    public String getCurrent() {
        return this.current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public List<String> getModes() {
        return this.modes;
    }
}

