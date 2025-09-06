package androidx.customview.poolingcontainer;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000E\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u0005J\u0006\u0010\n\u001A\u00020\bJ\u000E\u0010\u000B\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u0005R\u001E\u0010\u0003\u001A\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/customview/poolingcontainer/PoolingContainerListenerHolder;", "", "()V", "listeners", "Ljava/util/ArrayList;", "Landroidx/customview/poolingcontainer/PoolingContainerListener;", "Lkotlin/collections/ArrayList;", "addListener", "", "listener", "onRelease", "removeListener", "customview-poolingcontainer_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
final class PoolingContainerListenerHolder {
    private final ArrayList listeners;

    public PoolingContainerListenerHolder() {
        this.listeners = new ArrayList();
    }

    public final void addListener(PoolingContainerListener poolingContainerListener0) {
        Intrinsics.checkNotNullParameter(poolingContainerListener0, "listener");
        this.listeners.add(poolingContainerListener0);
    }

    public final void onRelease() {
        for(int v = CollectionsKt.getLastIndex(this.listeners); -1 < v; --v) {
            ((PoolingContainerListener)this.listeners.get(v)).onRelease();
        }
    }

    public final void removeListener(PoolingContainerListener poolingContainerListener0) {
        Intrinsics.checkNotNullParameter(poolingContainerListener0, "listener");
        this.listeners.remove(poolingContainerListener0);
    }
}

