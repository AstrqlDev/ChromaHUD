/*
 * Decompiled with CFR 0.150.
 */
package xyz.apfelmus.cheeto.client.settings;

public class BooleanSetting {
    private boolean enabled;

    public BooleanSetting(boolean enable) {
        this.enabled = enable;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setState(boolean enable) {
        this.enabled = enable;
    }
}

