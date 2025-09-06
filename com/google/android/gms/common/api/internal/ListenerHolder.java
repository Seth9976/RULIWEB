package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.concurrent.HandlerExecutor;
import java.util.concurrent.Executor;

public final class ListenerHolder {
    public static final class ListenerKey {
        private final Object zaa;
        private final String zab;

        ListenerKey(Object object0, String s) {
            this.zaa = object0;
            this.zab = s;
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            return object0 instanceof ListenerKey ? this.zaa == ((ListenerKey)object0).zaa && this.zab.equals(((ListenerKey)object0).zab) : false;
        }

        @Override
        public int hashCode() {
            return System.identityHashCode(this.zaa) * 0x1F + this.zab.hashCode();
        }

        public String toIdString() {
            return this.zab + "@" + System.identityHashCode(this.zaa);
        }
    }

    public interface Notifier {
        void notifyListener(Object arg1);

        void onNotifyListenerFailed();
    }

    private final Executor zaa;
    private volatile Object zab;
    private volatile ListenerKey zac;

    ListenerHolder(Looper looper0, Object object0, String s) {
        this.zaa = new HandlerExecutor(looper0);
        this.zab = Preconditions.checkNotNull(object0, "Listener must not be null");
        this.zac = new ListenerKey(object0, Preconditions.checkNotEmpty(s));
    }

    ListenerHolder(Executor executor0, Object object0, String s) {
        this.zaa = (Executor)Preconditions.checkNotNull(executor0, "Executor must not be null");
        this.zab = Preconditions.checkNotNull(object0, "Listener must not be null");
        this.zac = new ListenerKey(object0, Preconditions.checkNotEmpty(s));
    }

    public void clear() {
        this.zab = null;
        this.zac = null;
    }

    public ListenerKey getListenerKey() {
        return this.zac;
    }

    public boolean hasListener() {
        return this.zab != null;
    }

    public void notifyListener(Notifier listenerHolder$Notifier0) {
        Preconditions.checkNotNull(listenerHolder$Notifier0, "Notifier must not be null");
        zacb zacb0 = new zacb(this, listenerHolder$Notifier0);
        this.zaa.execute(zacb0);
    }

    final void zaa(Notifier listenerHolder$Notifier0) {
        Object object0 = this.zab;
        if(object0 == null) {
            listenerHolder$Notifier0.onNotifyListenerFailed();
            return;
        }
        try {
            listenerHolder$Notifier0.notifyListener(object0);
        }
        catch(RuntimeException runtimeException0) {
            listenerHolder$Notifier0.onNotifyListenerFailed();
            throw runtimeException0;
        }
    }
}

