package androidx.activity.contextaware;

import android.content.Context;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000E\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u0007J\u0006\u0010\u000B\u001A\u00020\tJ\u000E\u0010\f\u001A\u00020\t2\u0006\u0010\u0003\u001A\u00020\u0004J\b\u0010\r\u001A\u0004\u0018\u00010\u0004J\u000E\u0010\u000E\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u0007R\u0010\u0010\u0003\u001A\u0004\u0018\u00010\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000F"}, d2 = {"Landroidx/activity/contextaware/ContextAwareHelper;", "", "()V", "context", "Landroid/content/Context;", "listeners", "", "Landroidx/activity/contextaware/OnContextAvailableListener;", "addOnContextAvailableListener", "", "listener", "clearAvailableContext", "dispatchOnContextAvailable", "peekAvailableContext", "removeOnContextAvailableListener", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class ContextAwareHelper {
    private volatile Context context;
    private final Set listeners;

    public ContextAwareHelper() {
        this.listeners = new CopyOnWriteArraySet();
    }

    public final void addOnContextAvailableListener(OnContextAvailableListener onContextAvailableListener0) {
        Intrinsics.checkNotNullParameter(onContextAvailableListener0, "listener");
        Context context0 = this.context;
        if(context0 != null) {
            onContextAvailableListener0.onContextAvailable(context0);
        }
        this.listeners.add(onContextAvailableListener0);
    }

    public final void clearAvailableContext() {
        this.context = null;
    }

    public final void dispatchOnContextAvailable(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        this.context = context0;
        for(Object object0: this.listeners) {
            ((OnContextAvailableListener)object0).onContextAvailable(context0);
        }
    }

    public final Context peekAvailableContext() {
        return this.context;
    }

    public final void removeOnContextAvailableListener(OnContextAvailableListener onContextAvailableListener0) {
        Intrinsics.checkNotNullParameter(onContextAvailableListener0, "listener");
        this.listeners.remove(onContextAvailableListener0);
    }
}

