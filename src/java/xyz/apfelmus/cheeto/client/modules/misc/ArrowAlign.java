/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.item.EntityItemFrame
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.BlockPos
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.Vec3
 *  net.minecraft.util.Vec3i
 */
package xyz.apfelmus.cheeto.client.modules.misc;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;
import xyz.apfelmus.cf4m.annotation.Event;
import xyz.apfelmus.cf4m.annotation.Setting;
import xyz.apfelmus.cf4m.annotation.module.Enable;
import xyz.apfelmus.cf4m.annotation.module.Module;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cheeto.client.events.ClientTickEvent;
import xyz.apfelmus.cheeto.client.events.EntityInteractEvent;
import xyz.apfelmus.cheeto.client.events.Render3DEvent;
import xyz.apfelmus.cheeto.client.events.WorldUnloadEvent;
import xyz.apfelmus.cheeto.client.settings.BooleanSetting;
import xyz.apfelmus.cheeto.client.utils.client.ColorUtils;
import xyz.apfelmus.cheeto.client.utils.client.KeybindUtils;
import xyz.apfelmus.cheeto.client.utils.render.Render3DUtils;
import xyz.apfelmus.cheeto.client.utils.skyblock.SkyblockUtils;

@Module(name="ArrowAlign", category=Category.MISC)
public class ArrowAlign {
    @Setting(name="ShowClicks")
    private static BooleanSetting showClicks = new BooleanSetting(true);
    @Setting(name="ClickNeeded")
    private static BooleanSetting clickNeeded = new BooleanSetting(true);
    private static BlockPos topLeft = new BlockPos(197, 124, 278).func_177984_a();
    private static BlockPos botRight = new BlockPos(197, 120, 274).func_177984_a();
    private static List<BlockPos> box = StreamSupport.stream(BlockPos.func_177980_a((BlockPos)topLeft, (BlockPos)botRight).spliterator(), false).sorted((a, b) -> {
        if (a.func_177956_o() == b.func_177956_o()) {
            return b.func_177952_p() - a.func_177952_p();
        }
        return Integer.compare(b.func_177956_o(), a.func_177956_o());
    }).collect(Collectors.toList());
    private static LinkedHashSet<MazeSpace> grid = new LinkedHashSet();
    private static HashMap<Point, Integer> directionSet = new HashMap();
    private static int ticks = 0;
    private static EnumFacing[] directions = ArrowAlign.reverse((EnumFacing[])EnumFacing.field_176754_o.clone());
    private static boolean clicking = false;
    private static Minecraft mc = Minecraft.func_71410_x();

    @Enable
    public void onEnable() {
        grid.clear();
        directionSet.clear();
    }

    @Event
    public void onTick(ClientTickEvent event) {
        if (!SkyblockUtils.isInDungeon()) {
            return;
        }
        if (ticks % 20 == 0) {
            if (ArrowAlign.mc.field_71439_g.func_174831_c(topLeft) <= 625.0) {
                if (grid.size() < 25) {
                    List frames = ArrowAlign.mc.field_71441_e.func_175644_a(EntityItemFrame.class, it -> it != null && box.contains((Object)it.func_180425_c()) && it.func_82335_i() != null && (it.func_82335_i().func_77973_b().equals((Object)Items.field_151032_g) || it.func_82335_i().func_77973_b().equals((Object)Item.func_150898_a((Block)Blocks.field_150325_L))));
                    if (!frames.isEmpty()) {
                        for (int i = 0; i < box.size(); ++i) {
                            int row = i % 5;
                            int column = (int)Math.floor((float)i / 5.0f);
                            Point coords = new Point(row, column);
                            int finalI = i;
                            EntityItemFrame frame = frames.stream().filter(v -> v.func_180425_c().equals((Object)box.get(finalI))).findFirst().orElse(null);
                            if (frame != null) {
                                ItemStack item = frame.func_82335_i();
                                SpaceType type = SpaceType.EMPTY;
                                if (item.func_77973_b() == Items.field_151032_g) {
                                    type = SpaceType.PATH;
                                } else if (item.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150325_L)) {
                                    type = item.func_77952_i() == 5 ? SpaceType.START : (item.func_77952_i() == 14 ? SpaceType.END : SpaceType.PATH);
                                }
                                grid.add(new MazeSpace(frame.func_174857_n(), type, coords));
                                continue;
                            }
                            grid.add(new MazeSpace(null, SpaceType.EMPTY, coords));
                        }
                    }
                } else if (directionSet.isEmpty()) {
                    List startPositions = grid.stream().filter(it -> it.type == SpaceType.START).collect(Collectors.toList());
                    List endPositions = grid.stream().filter(it -> it.type == SpaceType.END).collect(Collectors.toList());
                    int[][] layout = ArrowAlign.getLayout();
                    for (MazeSpace start : startPositions) {
                        for (MazeSpace endPosition : endPositions) {
                            List<Point> pointMap = ArrowAlign.solve(layout, start.coords, endPosition.coords);
                            if (pointMap.size() == 0) continue;
                            List<GridMove> moveSet = this.convertPointMapToMoves(pointMap);
                            for (GridMove move : moveSet) {
                                directionSet.put(move.point, move.directionNum);
                            }
                        }
                    }
                }
            }
            ticks = 0;
        }
        ++ticks;
    }

    @Event
    public void onRightClick(EntityInteractEvent event) {
        if (clicking || !SkyblockUtils.isInDungeon()) {
            return;
        }
        if (event.event.target instanceof EntityItemFrame) {
            EntityItemFrame frame = (EntityItemFrame)event.event.target;
            for (MazeSpace space : grid) {
                if (!frame.func_174857_n().equals((Object)space.framePos) || space.type != SpaceType.PATH || space.framePos == null) continue;
                int neededClicks = directionSet.getOrDefault(space.coords, 0) - frame.func_82333_j();
                if (neededClicks == 0) {
                    event.event.setCanceled(true);
                }
                if (neededClicks < 0) {
                    neededClicks += 8;
                }
                if (neededClicks > 1 && clickNeeded.isEnabled()) {
                    clicking = true;
                    for (int i = 0; i < neededClicks - 1; ++i) {
                        KeybindUtils.rightClick();
                    }
                    clicking = false;
                }
                return;
            }
        }
    }

    @Event
    public void onRenderWorld(Render3DEvent event) {
        if (!showClicks.isEnabled()) {
            return;
        }
        for (MazeSpace space : grid) {
            int neededClicks;
            EntityItemFrame frame;
            if (space.type != SpaceType.PATH || space.framePos == null || (frame = (EntityItemFrame)ArrowAlign.mc.field_71441_e.field_72996_f.stream().filter(it -> it instanceof EntityItemFrame && ((EntityItemFrame)it).func_174857_n().equals((Object)space.framePos)).findFirst().orElse(null)) == null || (neededClicks = directionSet.getOrDefault(space.coords, 0) - frame.func_82333_j()) == 0) continue;
            if (neededClicks < 0) {
                neededClicks += 8;
            }
            Render3DUtils.draw3DString(this.getVec3RelativeToGrid(space.coords.x, space.coords.y).func_72441_c(0.1, 0.6, 0.5), "" + neededClicks, ColorUtils.getChroma(3000.0f, 0), event.partialTicks);
        }
    }

    @Event
    public void onWorldLoad(WorldUnloadEvent event) {
        grid.clear();
        directionSet.clear();
    }

    private Vec3 getVec3RelativeToGrid(int row, int column) {
        return new Vec3((Vec3i)topLeft.func_177977_b().func_177964_d(row).func_177979_c(column));
    }

    private List<GridMove> convertPointMapToMoves(List<Point> solution) {
        Collections.reverse(solution);
        ArrayList<GridMove> moves = new ArrayList<GridMove>();
        block6: for (int i = 0; i < solution.size() - 1; ++i) {
            Point current = solution.get(i);
            Point next = solution.get(i + 1);
            int diffX = current.x - next.x;
            int diffY = current.y - next.y;
            for (EnumFacing dir : EnumFacing.field_176754_o) {
                int dirX = dir.func_176730_m().func_177958_n();
                int dirY = dir.func_176730_m().func_177952_p();
                if (dirX != diffX || dirY != diffY) continue;
                int rotation = 0;
                switch (dir.func_176734_d()) {
                    case NORTH: {
                        rotation = 7;
                        break;
                    }
                    case SOUTH: {
                        rotation = 3;
                        break;
                    }
                    case WEST: {
                        rotation = 5;
                        break;
                    }
                    case EAST: {
                        rotation = 1;
                    }
                }
                moves.add(new GridMove(current, rotation));
                continue block6;
            }
        }
        Collections.reverse(solution);
        return moves;
    }

    private static List<Point> solve(int[][] grid, Point start, Point end) {
        LinkedList<Point> queue = new LinkedList<Point>();
        Point[][] gridCopy = new Point[grid.length][grid[0].length];
        queue.addLast(start);
        gridCopy[start.y][start.x] = start;
        while (queue.size() != 0) {
            Point currPos = (Point)queue.pollFirst();
            for (EnumFacing dir : directions) {
                Point nextPos = ArrowAlign.move(grid, gridCopy, currPos, dir);
                if (nextPos == null) continue;
                queue.addLast(nextPos);
                gridCopy[nextPos.y][nextPos.x] = new Point(currPos.x, currPos.y);
                if (!end.equals(new Point(nextPos.x, nextPos.y))) continue;
                ArrayList<Point> steps = new ArrayList<Point>();
                Point tmp = currPos;
                int count = 0;
                steps.add(nextPos);
                steps.add(currPos);
                while (tmp != start) {
                    ++count;
                    tmp = gridCopy[tmp.y][tmp.x];
                    steps.add(tmp);
                }
                return steps;
            }
        }
        return new ArrayList<Point>();
    }

    private static Point move(int[][] grid, Point[][] gridCopy, Point currPos, EnumFacing dir) {
        int i;
        int x = currPos.x;
        int y = currPos.y;
        int diffX = dir.func_176730_m().func_177958_n();
        int diffY = dir.func_176730_m().func_177952_p();
        int n = i = x + diffX >= 0 && x + diffX < grid[0].length && y + diffY >= 0 && y + diffY < grid.length && grid[y + diffY][x + diffX] != 1 ? 1 : 0;
        if (gridCopy[y + i * diffY][x + i * diffX] != null) {
            return null;
        }
        return new Point(x + i * diffX, y + i * diffY);
    }

    private static int[][] getLayout() {
        int[][] ret = new int[5][5];
        for (int row = 0; row < 5; ++row) {
            for (int col = 0; col < 5; ++col) {
                int finalRow = row;
                int finalCol = col;
                MazeSpace space = grid.stream().filter(it -> it.coords.equals(new Point(finalRow, finalCol))).findFirst().orElse(null);
                ret[col][row] = space != null ? (space.framePos != null ? 0 : 1) : 1;
            }
        }
        return ret;
    }

    private static EnumFacing[] reverse(EnumFacing[] array) {
        for (int i = 0; i < array.length / 2; ++i) {
            EnumFacing temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        return array;
    }

    private static class MazeSpace {
        BlockPos framePos;
        SpaceType type;
        Point coords;

        public MazeSpace(BlockPos framePos, SpaceType type, Point coords) {
            this.framePos = framePos;
            this.type = type;
            this.coords = coords;
        }

        public String toString() {
            return (Object)this.framePos + " - " + (Object)((Object)this.type) + " - " + this.coords;
        }
    }

    private static class GridMove {
        Point point;
        int directionNum;

        public GridMove(Point point, int directionNum) {
            this.point = point;
            this.directionNum = directionNum;
        }
    }

    private static enum SpaceType {
        EMPTY,
        PATH,
        START,
        END;

    }
}

