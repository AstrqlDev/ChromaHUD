/*
 * Decompiled with CFR 0.150.
 */
package xyz.apfelmus.cf4m.configuration;

public interface IConfiguration {
    default public void message(String message) {
        System.out.println(message);
    }

    default public void enable(Object module) {
    }

    default public void disable(Object module) {
    }

    default public String prefix() {
        return ",";
    }

    default public boolean config() {
        return true;
    }
}

