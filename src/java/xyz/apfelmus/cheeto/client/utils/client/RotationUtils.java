/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.BlockPos
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.util.Vec3i
 *  org.lwjgl.util.vector.Vector3f
 */
package xyz.apfelmus.cheeto.client.utils.client;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3i;
import org.lwjgl.util.vector.Vector3f;
import xyz.apfelmus.cheeto.client.modules.combat.BloodCamp;
import xyz.apfelmus.cheeto.client.utils.client.Rotation;
import xyz.apfelmus.cheeto.client.utils.math.RandomUtil;

public class RotationUtils {
    private static final Minecraft mc = Minecraft.func_71410_x();
    public static Rotation startRot;
    public static Rotation neededChange;
    public static Rotation endRot;
    public static long startTime;
    public static long endTime;
    public static boolean done;
    private static final float[][] BLOCK_SIDES;

    public static Rotation getRotation(Vec3 vec) {
        Vec3 eyes = RotationUtils.mc.field_71439_g.func_174824_e(1.0f);
        return RotationUtils.getRotation(eyes, vec);
    }

    public static Rotation getRotation(Vec3 from, Vec3 to) {
        double diffX = to.field_72450_a - from.field_72450_a;
        double diffY = to.field_72448_b - from.field_72448_b;
        double diffZ = to.field_72449_c - from.field_72449_c;
        return new Rotation(MathHelper.func_76142_g((float)((float)(Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0))), (float)(-Math.toDegrees(Math.atan2(diffY, Math.sqrt(diffX * diffX + diffZ * diffZ)))));
    }

    public static Rotation getRotation(BlockPos bp) {
        Vec3 vec = new Vec3((double)bp.func_177958_n() + 0.5, (double)bp.func_177956_o() + 0.5, (double)bp.func_177952_p() + 0.5);
        return RotationUtils.getRotation(vec);
    }

    public static void setup(Rotation rot, Long aimTime) {
        done = false;
        startRot = new Rotation(RotationUtils.mc.field_71439_g.field_70177_z, RotationUtils.mc.field_71439_g.field_70125_A);
        neededChange = RotationUtils.getNeededChange(startRot, rot);
        endRot = new Rotation(startRot.getYaw() + neededChange.getYaw(), startRot.getPitch() + neededChange.getPitch());
        startTime = System.currentTimeMillis();
        endTime = System.currentTimeMillis() + aimTime;
    }

    public static void reset() {
        done = true;
        startRot = null;
        neededChange = null;
        endRot = null;
        startTime = 0L;
        endTime = 0L;
    }

    public static void update() {
        if (System.currentTimeMillis() <= endTime) {
            RotationUtils.mc.field_71439_g.field_70177_z = RotationUtils.interpolate(startRot.getYaw(), endRot.getYaw());
            RotationUtils.mc.field_71439_g.field_70125_A = RotationUtils.interpolate(startRot.getPitch(), endRot.getPitch());
        } else if (!done) {
            RotationUtils.mc.field_71439_g.field_70177_z = endRot.getYaw();
            RotationUtils.mc.field_71439_g.field_70125_A = endRot.getPitch();
            RotationUtils.reset();
        }
    }

    public static void snapAngles(Rotation rot) {
        RotationUtils.mc.field_71439_g.field_70177_z = rot.getYaw();
        RotationUtils.mc.field_71439_g.field_70125_A = rot.getPitch();
    }

    private static float interpolate(float start, float end) {
        float spentMillis = System.currentTimeMillis() - startTime;
        float relativeProgress = spentMillis / (float)(endTime - startTime);
        return (end - start) * RotationUtils.easeOutCubic(relativeProgress) + start;
    }

    public static float easeOutCubic(double number) {
        return (float)(1.0 - Math.pow(1.0 - number, 3.0));
    }

    public static Rotation getNeededChange(Rotation startRot, Rotation endRot) {
        float yawChng = MathHelper.func_76142_g((float)endRot.getYaw()) - MathHelper.func_76142_g((float)startRot.getYaw());
        if (yawChng <= -180.0f) {
            yawChng = 360.0f + yawChng;
        } else if (yawChng > 180.0f) {
            yawChng = -360.0f + yawChng;
        }
        if (BloodCamp.godGamerMode.isEnabled()) {
            yawChng = yawChng < 0.0f ? (yawChng += 360.0f) : (yawChng -= 360.0f);
        }
        return new Rotation(yawChng, endRot.getPitch() - startRot.getPitch());
    }

    public static double fovFromEntity(Entity en) {
        return ((double)(RotationUtils.mc.field_71439_g.field_70177_z - RotationUtils.fovToEntity(en)) % 360.0 + 540.0) % 360.0 - 180.0;
    }

    public static float fovToEntity(Entity ent) {
        double x = ent.field_70165_t - RotationUtils.mc.field_71439_g.field_70165_t;
        double z = ent.field_70161_v - RotationUtils.mc.field_71439_g.field_70161_v;
        double yaw = Math.atan2(x, z) * 57.2957795;
        return (float)(yaw * -1.0);
    }

    public static Rotation getNeededChange(Rotation endRot) {
        Rotation startRot = new Rotation(RotationUtils.mc.field_71439_g.field_70177_z, RotationUtils.mc.field_71439_g.field_70125_A);
        return RotationUtils.getNeededChange(startRot, endRot);
    }

    public static List<Vec3> getBlockSides(BlockPos bp) {
        ArrayList<Vec3> ret = new ArrayList<Vec3>();
        for (float[] side : BLOCK_SIDES) {
            ret.add(new Vec3((Vec3i)bp).func_72441_c((double)side[0], (double)side[1], (double)side[2]));
        }
        return ret;
    }

    public static boolean lookingAt(BlockPos blockPos, float range) {
        float stepSize = 0.15f;
        Vec3 position = new Vec3(RotationUtils.mc.field_71439_g.field_70165_t, RotationUtils.mc.field_71439_g.field_70163_u + (double)RotationUtils.mc.field_71439_g.func_70047_e(), RotationUtils.mc.field_71439_g.field_70161_v);
        Vec3 look = RotationUtils.mc.field_71439_g.func_70676_i(0.0f);
        Vector3f step = new Vector3f((float)look.field_72450_a, (float)look.field_72448_b, (float)look.field_72449_c);
        step.scale(stepSize / step.length());
        int i = 0;
        while ((double)i < Math.floor(range / stepSize) - 2.0) {
            BlockPos blockAtPos = new BlockPos(position.field_72450_a, position.field_72448_b, position.field_72449_c);
            if (blockAtPos.equals((Object)blockPos)) {
                return true;
            }
            position = position.func_178787_e(new Vec3((double)step.x, (double)step.y, (double)step.z));
            ++i;
        }
        return false;
    }

    public static Vec3 getVectorForRotation(float pitch, float yaw) {
        float f2 = -MathHelper.func_76134_b((float)(-pitch * ((float)Math.PI / 180)));
        return new Vec3((double)(MathHelper.func_76126_a((float)(-yaw * ((float)Math.PI / 180) - (float)Math.PI)) * f2), (double)MathHelper.func_76126_a((float)(-pitch * ((float)Math.PI / 180))), (double)(MathHelper.func_76134_b((float)(-yaw * ((float)Math.PI / 180) - (float)Math.PI)) * f2));
    }

    public static Vec3 getLook(Vec3 vec) {
        double diffX = vec.field_72450_a - RotationUtils.mc.field_71439_g.field_70165_t;
        double diffY = vec.field_72448_b - (RotationUtils.mc.field_71439_g.field_70163_u + (double)RotationUtils.mc.field_71439_g.func_70047_e());
        double diffZ = vec.field_72449_c - RotationUtils.mc.field_71439_g.field_70161_v;
        double dist = MathHelper.func_76133_a((double)(diffX * diffX + diffZ * diffZ));
        return RotationUtils.getVectorForRotation((float)(-(MathHelper.func_181159_b((double)diffY, (double)dist) * 180.0 / Math.PI)), (float)(MathHelper.func_181159_b((double)diffZ, (double)diffX) * 180.0 / Math.PI - 90.0));
    }

    public static EnumFacing calculateEnumfacing(Vec3 pos) {
        int z;
        int y;
        int x = MathHelper.func_76128_c((double)pos.field_72450_a);
        MovingObjectPosition position = RotationUtils.calculateIntercept(new AxisAlignedBB((double)x, (double)(y = MathHelper.func_76128_c((double)pos.field_72448_b)), (double)(z = MathHelper.func_76128_c((double)pos.field_72449_c)), (double)(x + 1), (double)(y + 1), (double)(z + 1)), pos, 50.0f);
        return position != null ? position.field_178784_b : null;
    }

    public static MovingObjectPosition calculateIntercept(AxisAlignedBB aabb, Vec3 block, float range) {
        Vec3 vec3 = RotationUtils.mc.field_71439_g.func_174824_e(1.0f);
        Vec3 vec4 = RotationUtils.getLook(block);
        return aabb.func_72327_a(vec3, vec3.func_72441_c(vec4.field_72450_a * (double)range, vec4.field_72448_b * (double)range, vec4.field_72449_c * (double)range));
    }

    public static List<Vec3> getPointsOnBlock(BlockPos bp) {
        ArrayList<Vec3> ret = new ArrayList<Vec3>();
        for (float[] side : BLOCK_SIDES) {
            for (int i = 0; i < 20; ++i) {
                float x = side[0];
                float y = side[1];
                float z = side[2];
                if ((double)x == 0.5) {
                    x = RandomUtil.randBetween(0.1f, 0.9f);
                }
                if ((double)y == 0.5) {
                    y = RandomUtil.randBetween(0.1f, 0.9f);
                }
                if ((double)z == 0.5) {
                    z = RandomUtil.randBetween(0.1f, 0.9f);
                }
                ret.add(new Vec3((Vec3i)bp).func_72441_c((double)x, (double)y, (double)z));
            }
        }
        return ret;
    }

    static {
        done = true;
        BLOCK_SIDES = new float[][]{{0.5f, 0.01f, 0.5f}, {0.5f, 0.99f, 0.5f}, {0.01f, 0.5f, 0.5f}, {0.99f, 0.5f, 0.5f}, {0.5f, 0.5f, 0.01f}, {0.5f, 0.5f, 0.99f}};
    }
}

