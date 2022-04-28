/*
 * Decompiled with CFR 0.150.
 */
package xyz.apfelmus.cheeto.client.modules.combat;

import xyz.apfelmus.cf4m.annotation.Setting;
import xyz.apfelmus.cf4m.annotation.module.Module;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cheeto.client.settings.BooleanSetting;

@Module(name="GhostArm", category=Category.COMBAT)
public class GhostArm {
    @Setting(name="Zombies")
    public BooleanSetting Zombies = new BooleanSetting(true);
    @Setting(name="Players")
    public BooleanSetting Players = new BooleanSetting(true);
    @Setting(name="HideMobs")
    public BooleanSetting HideMobs = new BooleanSetting(true);
}

