package androidx.customview.poolingcontainer;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.core.view.ViewGroupKt;
import androidx.core.view.ViewKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\u0014\u0010\u000F\u001A\u00020\u0010*\u00020\u00062\u0006\u0010\u0011\u001A\u00020\u0012H\u0007\u001A\n\u0010\u0013\u001A\u00020\u0010*\u00020\u0006\u001A\n\u0010\u0014\u001A\u00020\u0010*\u00020\u0015\u001A\u0014\u0010\u0016\u001A\u00020\u0010*\u00020\u00062\u0006\u0010\u0011\u001A\u00020\u0012H\u0007\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000E\u0010\u0002\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"(\u0010\u0005\u001A\u00020\u0004*\u00020\u00062\u0006\u0010\u0003\u001A\u00020\u00048F@FX\u0086\u000E¢\u0006\f\u001A\u0004\b\u0005\u0010\u0007\"\u0004\b\b\u0010\t\"\u0015\u0010\n\u001A\u00020\u0004*\u00020\u00068F¢\u0006\u0006\u001A\u0004\b\n\u0010\u0007\"\u0018\u0010\u000B\u001A\u00020\f*\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001A\u0004\b\r\u0010\u000E¨\u0006\u0017"}, d2 = {"IsPoolingContainerTag", "", "PoolingContainerListenerHolderTag", "value", "", "isPoolingContainer", "Landroid/view/View;", "(Landroid/view/View;)Z", "setPoolingContainer", "(Landroid/view/View;Z)V", "isWithinPoolingContainer", "poolingContainerListenerHolder", "Landroidx/customview/poolingcontainer/PoolingContainerListenerHolder;", "getPoolingContainerListenerHolder", "(Landroid/view/View;)Landroidx/customview/poolingcontainer/PoolingContainerListenerHolder;", "addPoolingContainerListener", "", "listener", "Landroidx/customview/poolingcontainer/PoolingContainerListener;", "callPoolingContainerOnRelease", "callPoolingContainerOnReleaseForChildren", "Landroid/view/ViewGroup;", "removePoolingContainerListener", "customview-poolingcontainer_release"}, k = 2, mv = {1, 6, 0}, xi = 0x30)
public final class PoolingContainer {
    private static final int IsPoolingContainerTag;
    private static final int PoolingContainerListenerHolderTag;

    static {
        PoolingContainer.PoolingContainerListenerHolderTag = id.pooling_container_listener_holder_tag;
        PoolingContainer.IsPoolingContainerTag = id.is_pooling_container_tag;
    }

    public static final void addPoolingContainerListener(View view0, PoolingContainerListener poolingContainerListener0) {
        Intrinsics.checkNotNullParameter(view0, "<this>");
        Intrinsics.checkNotNullParameter(poolingContainerListener0, "listener");
        PoolingContainer.getPoolingContainerListenerHolder(view0).addListener(poolingContainerListener0);
    }

    public static final void callPoolingContainerOnRelease(View view0) {
        Intrinsics.checkNotNullParameter(view0, "<this>");
        for(Object object0: ViewKt.getAllViews(view0)) {
            PoolingContainer.getPoolingContainerListenerHolder(((View)object0)).onRelease();
        }
    }

    public static final void callPoolingContainerOnReleaseForChildren(ViewGroup viewGroup0) {
        Intrinsics.checkNotNullParameter(viewGroup0, "<this>");
        for(Object object0: ViewGroupKt.getChildren(viewGroup0)) {
            PoolingContainer.getPoolingContainerListenerHolder(((View)object0)).onRelease();
        }
    }

    private static final PoolingContainerListenerHolder getPoolingContainerListenerHolder(View view0) {
        int v = PoolingContainer.PoolingContainerListenerHolderTag;
        PoolingContainerListenerHolder poolingContainerListenerHolder0 = (PoolingContainerListenerHolder)view0.getTag(v);
        if(poolingContainerListenerHolder0 == null) {
            poolingContainerListenerHolder0 = new PoolingContainerListenerHolder();
            view0.setTag(v, poolingContainerListenerHolder0);
        }
        return poolingContainerListenerHolder0;
    }

    public static final boolean isPoolingContainer(View view0) {
        Intrinsics.checkNotNullParameter(view0, "<this>");
        Object object0 = view0.getTag(PoolingContainer.IsPoolingContainerTag);
        Boolean boolean0 = object0 instanceof Boolean ? ((Boolean)object0) : null;
        return boolean0 == null ? false : boolean0.booleanValue();
    }

    public static final boolean isWithinPoolingContainer(View view0) {
        Intrinsics.checkNotNullParameter(view0, "<this>");
        for(Object object0: ViewKt.getAncestors(view0)) {
            if(((ViewParent)object0) instanceof View && PoolingContainer.isPoolingContainer(((View)(((ViewParent)object0))))) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    public static final void removePoolingContainerListener(View view0, PoolingContainerListener poolingContainerListener0) {
        Intrinsics.checkNotNullParameter(view0, "<this>");
        Intrinsics.checkNotNullParameter(poolingContainerListener0, "listener");
        PoolingContainer.getPoolingContainerListenerHolder(view0).removeListener(poolingContainerListener0);
    }

    public static final void setPoolingContainer(View view0, boolean z) {
        Intrinsics.checkNotNullParameter(view0, "<this>");
        view0.setTag(PoolingContainer.IsPoolingContainerTag, Boolean.valueOf(z));
    }
}

