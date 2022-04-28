/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.BlockPos
 *  net.minecraft.util.Vec3
 *  net.minecraft.util.Vec3i
 */
package xyz.apfelmus.cheeto.client.utils.pathfinding;

import java.util.List;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;
import xyz.apfelmus.cheeto.client.utils.pathfinding.AStarCustomPathfinder;

public class Pathfinder {
    private static AStarCustomPathfinder pathfinder;
    public static List<Vec3> path;

    public static void setup(BlockPos from, BlockPos to, double minDistance) {
        pathfinder = new AStarCustomPathfinder(new Vec3((Vec3i)from), new Vec3((Vec3i)to), minDistance);
        pathfinder.compute();
        path = pathfinder.getPath();
    }

    public static Vec3 getCurrent() {
        if (path != null && !path.isEmpty()) {
            return path.get(0);
        }
        return null;
    }

    public static boolean hasNext() {
        return path != null && path.size() > 1;
    }

    public static Vec3 getNext() {
        return path.get(1);
    }

    public static boolean goNext() {
        if (path != null && path.size() > 1) {
            path.remove(0);
            return true;
        }
        path = null;
        return false;
    }

    public static boolean hasPath() {
        return path != null && !path.isEmpty();
    }

    public static Vec3 getGoal() {
        return path.get(path.size() - 1);
    }
}

