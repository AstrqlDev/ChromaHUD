/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.play.server.S03PacketTimeUpdate
 */
package xyz.apfelmus.cheeto.client.modules.render;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import xyz.apfelmus.cf4m.annotation.Event;
import xyz.apfelmus.cf4m.annotation.Setting;
import xyz.apfelmus.cf4m.annotation.module.Enable;
import xyz.apfelmus.cf4m.annotation.module.Module;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cheeto.client.events.PacketReceivedEvent;
import xyz.apfelmus.cheeto.client.events.Render2DEvent;
import xyz.apfelmus.cheeto.client.events.WorldUnloadEvent;
import xyz.apfelmus.cheeto.client.settings.BooleanSetting;
import xyz.apfelmus.cheeto.client.settings.IntegerSetting;
import xyz.apfelmus.cheeto.client.utils.client.FontUtils;

@Module(name="TPSViewer", category=Category.RENDER)
public class TPSViewer {
    @Setting(name="xPos")
    private IntegerSetting xPos = new IntegerSetting(0, 0, 1000);
    @Setting(name="yPos")
    private IntegerSetting yPos = new IntegerSetting(0, 0, 1000);
    @Setting(name="RGB")
    private BooleanSetting rgb = new BooleanSetting(true);
    public static List<Float> serverTPS = new ArrayList<Float>();
    private static long systemTime = 0L;
    private static long serverTime = 0L;

    @Enable
    public void onEnable() {
        serverTPS.clear();
        systemTime = 0L;
        serverTime = 0L;
    }

    @Event
    public void onRender(Render2DEvent event) {
        if (this.rgb.isEnabled()) {
            FontUtils.drawHVCenteredChromaString(String.format("TPS: %.1f", this.calcTps()), this.xPos.getCurrent(), this.yPos.getCurrent(), 0);
        } else {
            FontUtils.drawHVCenteredString(String.format("TPS: %.1f", this.calcTps()), this.xPos.getCurrent(), this.yPos.getCurrent(), -1);
        }
    }

    @Event
    public void onPacket(PacketReceivedEvent event) {
        if (event.packet instanceof S03PacketTimeUpdate) {
            S03PacketTimeUpdate s03packet = (S03PacketTimeUpdate)event.packet;
            if (systemTime == 0L) {
                systemTime = System.currentTimeMillis();
                serverTime = s03packet.func_149366_c();
            } else {
                long newSystemTime = System.currentTimeMillis();
                long newServerTime = s03packet.func_149366_c();
                float tps = (float)(serverTime - newServerTime) / ((float)(systemTime - newSystemTime) / 50.0f) * 20.0f;
                if (tps <= 20.0f) {
                    serverTPS.add(Float.valueOf(tps));
                }
                systemTime = newSystemTime;
                serverTime = newServerTime;
            }
        }
    }

    @Event
    public void onServerJoin(WorldUnloadEvent event) {
        serverTPS.clear();
        systemTime = 0L;
        serverTime = 0L;
    }

    private double calcTps() {
        while (serverTPS.size() > 10) {
            serverTPS.remove(0);
        }
        return new ArrayList<Float>(serverTPS).stream().mapToDouble(x -> x.floatValue()).sum() / (double)serverTPS.size();
    }
}

