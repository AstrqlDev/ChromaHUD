/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 */
package xyz.apfelmus.cheeto.client.events;

import net.minecraft.entity.EntityLivingBase;
import xyz.apfelmus.cf4m.event.Listener;

public class RenderLivingEventPre
extends Listener {
    public EntityLivingBase entity;

    public RenderLivingEventPre(EntityLivingBase entity) {
        super(Listener.At.HEAD);
        this.entity = entity;
    }
}

