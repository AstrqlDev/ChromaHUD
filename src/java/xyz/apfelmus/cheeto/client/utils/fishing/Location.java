/*
 * Decompiled with CFR 0.150.
 */
package xyz.apfelmus.cheeto.client.utils.fishing;

import java.util.List;
import xyz.apfelmus.cheeto.client.utils.client.Rotation;
import xyz.apfelmus.cheeto.client.utils.fishing.PathPoint;

public class Location {
    public String name;
    public List<PathPoint> path;
    public Rotation rotation;

    public String toString() {
        return this.name;
    }
}

