/*
 * Decompiled with CFR 0.150.
 */
package xyz.apfelmus.cheeto.client.settings;

public class StringSetting {
    private String value;

    public StringSetting(String value) {
        this.value = value;
    }

    public String getCurrent() {
        return this.value;
    }

    public void setCurrent(String value) {
        this.value = value;
    }
}

