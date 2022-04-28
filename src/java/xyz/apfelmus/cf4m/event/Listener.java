/*
 * Decompiled with CFR 0.150.
 */
package xyz.apfelmus.cf4m.event;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.CopyOnWriteArrayList;
import xyz.apfelmus.cf4m.CF4M;
import xyz.apfelmus.cf4m.event.EventBean;

public class Listener {
    private final At at;
    private boolean cancel;

    public Listener(At at) {
        this.at = at;
        this.cancel = false;
    }

    public void call() {
        this.cancel = false;
        if (CF4M.INSTANCE.eventManager == null) {
            return;
        }
        CopyOnWriteArrayList<EventBean> eventBeans = CF4M.INSTANCE.eventManager.getEvent(this.getClass());
        if (eventBeans == null) {
            return;
        }
        for (EventBean event : eventBeans) {
            try {
                event.getMethod().invoke(event.getObject(), this);
            }
            catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean getCancel() {
        return this.cancel;
    }

    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    public At getAt() {
        return this.at;
    }

    public static enum At {
        HEAD,
        TAIL,
        NONE;

    }
}

