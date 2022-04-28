/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockCactus
 *  net.minecraft.block.BlockChest
 *  net.minecraft.block.BlockEnderChest
 *  net.minecraft.block.BlockFence
 *  net.minecraft.block.BlockGlass
 *  net.minecraft.block.BlockPane
 *  net.minecraft.block.BlockPistonBase
 *  net.minecraft.block.BlockPistonExtension
 *  net.minecraft.block.BlockPistonMoving
 *  net.minecraft.block.BlockSkull
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.block.BlockSlab$EnumBlockHalf
 *  net.minecraft.block.BlockStainedGlass
 *  net.minecraft.block.BlockStairs
 *  net.minecraft.block.BlockTrapDoor
 *  net.minecraft.block.BlockWall
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.BlockPos
 *  net.minecraft.util.Vec3
 */
package xyz.apfelmus.cheeto.client.utils.pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.BlockPistonExtension;
import net.minecraft.block.BlockPistonMoving;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.BlockWall;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;
import xyz.apfelmus.cheeto.client.utils.math.VecUtils;

public class AStarCustomPathfinder {
    private Vec3 startVec3;
    private Vec3 endVec3;
    private ArrayList<Vec3> path = new ArrayList();
    private ArrayList<Hub> hubs = new ArrayList();
    private ArrayList<Hub> hubsToWork = new ArrayList();
    private double minDistanceSquared;
    private boolean nearest = true;
    private static Vec3[] flatCardinalDirections = new Vec3[]{new Vec3(1.0, 0.0, 0.0), new Vec3(-1.0, 0.0, 0.0), new Vec3(0.0, 0.0, 1.0), new Vec3(0.0, 0.0, -1.0)};
    private static Minecraft mc = Minecraft.func_71410_x();

    public AStarCustomPathfinder(Vec3 startVec3, Vec3 endVec3, double minDistanceSquared) {
        this.startVec3 = VecUtils.floorVec(startVec3);
        this.endVec3 = VecUtils.floorVec(endVec3);
        this.minDistanceSquared = minDistanceSquared;
    }

    public ArrayList<Vec3> getPath() {
        return this.path;
    }

    public void compute() {
        this.compute(1000, 4);
    }

    public void compute(int loops, int depth) {
        this.path.clear();
        this.hubsToWork.clear();
        ArrayList<Vec3> initPath = new ArrayList<Vec3>();
        initPath.add(this.startVec3);
        this.hubsToWork.add(new Hub(this.startVec3, null, initPath, this.startVec3.func_72436_e(this.endVec3), 0.0, 0.0));
        block0: for (int i = 0; i < loops; ++i) {
            Collections.sort(this.hubsToWork, new CompareHub());
            int j = 0;
            if (this.hubsToWork.size() == 0) break;
            for (Hub hub : new ArrayList<Hub>(this.hubsToWork)) {
                Vec3 loc;
                if (++j > depth) continue block0;
                this.hubsToWork.remove(hub);
                this.hubs.add(hub);
                for (Vec3 direction : flatCardinalDirections) {
                    loc = VecUtils.ceilVec(hub.getLoc().func_178787_e(direction));
                    if (AStarCustomPathfinder.checkPositionValidity(loc, true) && (AStarCustomPathfinder.isSlab(loc.func_72441_c(0.0, -1.0, 0.0), BlockSlab.EnumBlockHalf.BOTTOM) ? this.addHub(hub, loc.func_72441_c(0.0, -0.5, 0.0), 0.0) : this.addHub(hub, loc, 0.0))) break block0;
                }
                for (Vec3 direction : flatCardinalDirections) {
                    loc = VecUtils.ceilVec(hub.getLoc().func_178787_e(direction).func_72441_c(0.0, 1.0, 0.0));
                    if (AStarCustomPathfinder.checkPositionValidity(loc, true) && AStarCustomPathfinder.checkPositionValidity(hub.getLoc().func_72441_c(0.0, 1.0, 0.0), false) && (AStarCustomPathfinder.isSlab(loc.func_72441_c(0.0, -1.0, 0.0), BlockSlab.EnumBlockHalf.BOTTOM) ? this.addHub(hub, loc.func_72441_c(0.0, -0.5, 0.0), 0.0) : !AStarCustomPathfinder.isSlab(hub.getLoc(), BlockSlab.EnumBlockHalf.BOTTOM) && this.addHub(hub, loc, 0.0))) break block0;
                }
                for (Vec3 direction : flatCardinalDirections) {
                    loc = VecUtils.ceilVec(hub.getLoc().func_178787_e(direction).func_72441_c(0.0, -1.0, 0.0));
                    if (AStarCustomPathfinder.checkPositionValidity(loc, true) && AStarCustomPathfinder.checkPositionValidity(loc.func_72441_c(0.0, 1.0, 0.0), false) && (AStarCustomPathfinder.isSlab(loc, BlockSlab.EnumBlockHalf.BOTTOM) ? this.addHub(hub, loc.func_72441_c(0.0, 0.5, 0.0), 0.0) : this.addHub(hub, loc, 0.0))) break block0;
                }
            }
        }
        if (this.nearest) {
            this.hubs.sort(new CompareHub());
            this.path = this.hubs.get(0).getPath();
        }
    }

    public static boolean checkPositionValidity(Vec3 loc, boolean checkGround) {
        return AStarCustomPathfinder.checkPositionValidity((int)loc.field_72450_a, (int)loc.field_72448_b, (int)loc.field_72449_c, checkGround);
    }

    public static boolean checkPositionValidity(int x, int y, int z, boolean checkGround) {
        BlockPos block1 = new BlockPos(x, y, z);
        BlockPos block2 = new BlockPos(x, y + 1, z);
        BlockPos block3 = new BlockPos(x, y - 1, z);
        return !AStarCustomPathfinder.isBlockSolid(block1) && !AStarCustomPathfinder.isBlockSolid(block2) && (AStarCustomPathfinder.isBlockSolid(block3) || !checkGround) && AStarCustomPathfinder.isSafeToWalkOn(block3);
    }

    public static boolean isSlab(Vec3 loc, BlockSlab.EnumBlockHalf slabType) {
        IBlockState bs = AStarCustomPathfinder.mc.field_71441_e.func_180495_p(new BlockPos(loc));
        return bs.func_177230_c() instanceof BlockSlab && bs.func_177229_b((IProperty)BlockSlab.field_176554_a) == slabType;
    }

    private static boolean isBlockSolid(BlockPos block) {
        IBlockState bs = AStarCustomPathfinder.mc.field_71441_e.func_180495_p(block);
        if (bs != null) {
            Block b = bs.func_177230_c();
            return AStarCustomPathfinder.mc.field_71441_e.func_175665_u(block) || b instanceof BlockSlab || b instanceof BlockStairs || b instanceof BlockCactus || b instanceof BlockChest || b instanceof BlockEnderChest || b instanceof BlockSkull || b instanceof BlockPane || b instanceof BlockFence || b instanceof BlockWall || b instanceof BlockGlass || b instanceof BlockPistonBase || b instanceof BlockPistonExtension || b instanceof BlockPistonMoving || b instanceof BlockStainedGlass || b instanceof BlockTrapDoor;
        }
        return false;
    }

    private static boolean isSafeToWalkOn(BlockPos block) {
        IBlockState bs = AStarCustomPathfinder.mc.field_71441_e.func_180495_p(block);
        if (bs != null) {
            Block b = bs.func_177230_c();
            return !(b instanceof BlockFence) && !(b instanceof BlockWall);
        }
        return false;
    }

    public Hub isHubExisting(Vec3 loc) {
        for (Hub hub : this.hubs) {
            if (hub.getLoc().field_72450_a != loc.field_72450_a || hub.getLoc().field_72448_b != loc.field_72448_b || hub.getLoc().field_72449_c != loc.field_72449_c) continue;
            return hub;
        }
        for (Hub hub : this.hubsToWork) {
            if (hub.getLoc().field_72450_a != loc.field_72450_a || hub.getLoc().field_72448_b != loc.field_72448_b || hub.getLoc().field_72449_c != loc.field_72449_c) continue;
            return hub;
        }
        return null;
    }

    public boolean addHub(Hub parent, Vec3 loc, double cost) {
        Hub existingHub = this.isHubExisting(loc);
        double totalCost = cost;
        if (parent != null) {
            totalCost += parent.getTotalCost();
        }
        if (existingHub == null) {
            if (loc.field_72450_a == this.endVec3.field_72450_a && loc.field_72448_b == this.endVec3.field_72448_b && loc.field_72449_c == this.endVec3.field_72449_c || this.minDistanceSquared != 0.0 && loc.func_72436_e(this.endVec3) <= this.minDistanceSquared) {
                this.path.clear();
                this.path = parent.getPath();
                this.path.add(loc);
                return true;
            }
            ArrayList<Vec3> path = new ArrayList<Vec3>(parent.getPath());
            path.add(loc);
            this.hubsToWork.add(new Hub(loc, parent, path, loc.func_72436_e(this.endVec3), cost, totalCost));
        } else if (existingHub.getCost() > cost) {
            ArrayList<Vec3> path = new ArrayList<Vec3>(parent.getPath());
            path.add(loc);
            existingHub.setLoc(loc);
            existingHub.setParent(parent);
            existingHub.setPath(path);
            existingHub.setSquareDistanceToFromTarget(loc.func_72436_e(this.endVec3));
            existingHub.setCost(cost);
            existingHub.setTotalCost(totalCost);
        }
        return false;
    }

    public class CompareHub
    implements Comparator<Hub> {
        @Override
        public int compare(Hub o1, Hub o2) {
            return (int)(o1.getSquareDistanceToFromTarget() + o1.getTotalCost() - (o2.getSquareDistanceToFromTarget() + o2.getTotalCost()));
        }
    }

    private class Hub {
        private Vec3 loc = null;
        private Hub parent = null;
        private ArrayList<Vec3> path;
        private double squareDistanceToFromTarget;
        private double cost;
        private double totalCost;

        public Hub(Vec3 loc, Hub parent, ArrayList<Vec3> path, double squareDistanceToFromTarget, double cost, double totalCost) {
            this.loc = loc;
            this.parent = parent;
            this.path = path;
            this.squareDistanceToFromTarget = squareDistanceToFromTarget;
            this.cost = cost;
            this.totalCost = totalCost;
        }

        public Vec3 getLoc() {
            return this.loc;
        }

        public Hub getParent() {
            return this.parent;
        }

        public ArrayList<Vec3> getPath() {
            return this.path;
        }

        public double getSquareDistanceToFromTarget() {
            return this.squareDistanceToFromTarget;
        }

        public double getCost() {
            return this.cost;
        }

        public void setLoc(Vec3 loc) {
            this.loc = loc;
        }

        public void setParent(Hub parent) {
            this.parent = parent;
        }

        public void setPath(ArrayList<Vec3> path) {
            this.path = path;
        }

        public void setSquareDistanceToFromTarget(double squareDistanceToFromTarget) {
            this.squareDistanceToFromTarget = squareDistanceToFromTarget;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public double getTotalCost() {
            return this.totalCost;
        }

        public void setTotalCost(double totalCost) {
            this.totalCost = totalCost;
        }
    }
}

