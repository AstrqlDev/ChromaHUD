/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.monster.EntityEnderman
 *  net.minecraft.entity.player.EntityPlayer
 */
package xyz.apfelmus.cheeto.client.modules.render;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import xyz.apfelmus.cf4m.annotation.Event;
import xyz.apfelmus.cf4m.annotation.Setting;
import xyz.apfelmus.cf4m.annotation.module.Module;
import xyz.apfelmus.cf4m.module.Category;
import xyz.apfelmus.cheeto.client.events.RenderLivingEventPre;
import xyz.apfelmus.cheeto.client.settings.BooleanSetting;
import xyz.apfelmus.cheeto.client.utils.skyblock.SkyblockUtils;

@Module(name="ShowHiddenMobs", category=Category.RENDER)
public class ShowHiddenMobs {
    @Setting(name="ShowFels")
    private BooleanSetting showFels = new BooleanSetting(true);
    @Setting(name="ShowSA")
    private BooleanSetting showSA = new BooleanSetting(true);
    @Setting(name="ShowBloods")
    private BooleanSetting showBloods = new BooleanSetting(true);
    @Setting(name="ShowGhosts")
    private BooleanSetting showGhosts = new BooleanSetting(true);

    @Event
    public void onBeforeRenderEntity(RenderLivingEventPre event) {
        if (event.entity.func_82150_aj()) {
            if (this.showFels.isEnabled() && event.entity instanceof EntityEnderman) {
                event.entity.func_82142_c(false);
            }
            if (SkyblockUtils.isInDungeon() && event.entity instanceof EntityPlayer) {
                if (this.showSA.isEnabled() && event.entity.func_70005_c_().contains("Shadow Assassin")) {
                    event.entity.func_82142_c(false);
                }
                if (this.showBloods.isEnabled()) {
                    for (String name : new String[]{"Revoker", "Psycho", "Reaper", "Cannibal", "Mute", "Ooze", "Putrid", "Freak", "Leech", "Tear", "Parasite", "Flamer", "Skull", "Mr. Dead", "Vader", "Frost", "Walker", "Wandering Soul", "Bonzo", "Scarf", "Livid"}) {
                        if (!event.entity.func_70005_c_().contains(name)) continue;
                        event.entity.func_82142_c(false);
                        break;
                    }
                }
            }
            if (this.showGhosts.isEnabled() && event.entity instanceof EntityCreeper && SkyblockUtils.hasLine("The Mist")) {
                event.entity.func_82142_c(false);
            }
        }
    }
}

