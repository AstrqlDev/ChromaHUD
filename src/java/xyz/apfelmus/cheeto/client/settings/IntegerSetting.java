/*
 * Decompiled with CFR 0.150.
 */
package xyz.apfelmus.cheeto.client.settings;

public class IntegerSetting {
    private Integer current;
    private Integer min;
    private Integer max;

    public IntegerSetting(Integer current, Integer min, Integer max) {
        this.current = current;
        this.min = min;
        this.max = max;
    }

    public Integer getCurrent() {
        return this.current;
    }

    public void setCurrent(Integer current) {
        this.current = current < this.min ? this.min : (current > this.max ? this.max : current);
    }

    public Integer getMin() {
        return this.min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return this.max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
}

