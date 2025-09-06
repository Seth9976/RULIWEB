package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;

public class ListenerHolders {
    private final Set zaa;

    public ListenerHolders() {
        this.zaa = Collections.newSetFromMap(new WeakHashMap());
    }

    public static ListenerHolder createListenerHolder(Object object0, Looper looper0, String s) {
        Preconditions.checkNotNull(object0, "Listener must not be null");
        Preconditions.checkNotNull(looper0, "Looper must not be null");
        Preconditions.checkNotNull(s, "Listener type must not be null");
        return new ListenerHolder(looper0, object0, s);
    }

    public static ListenerHolder createListenerHolder(Object object0, Executor executor0, String s) {
        Preconditions.checkNotNull(object0, "Listener must not be null");
        Preconditions.checkNotNull(executor0, "Executor must not be null");
        Preconditions.checkNotNull(s, "Listener type must not be null");
        return new ListenerHolder(executor0, object0, s);
    }

    public static ListenerKey createListenerKey(Object object0, String s) {
        Preconditions.checkNotNull(object0, "Listener must not be null");
        Preconditions.checkNotNull(s, "Listener type must not be null");
        Preconditions.checkNotEmpty(s, "Listener type must not be empty");
        return new ListenerKey(object0, s);
    }

    public final ListenerHolder zaa(Object object0, Looper looper0, String s) {
        ListenerHolder listenerHolder0 = ListenerHolders.createListenerHolder(object0, looper0, "NO_TYPE");
        this.zaa.add(listenerHolder0);
        return listenerHolder0;
    }

    public final void zab() {
        for(Object object0: this.zaa) {
            ((ListenerHolder)object0).clear();
        }
        this.zaa.clear();
    }
}

