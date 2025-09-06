package androidx.work.impl.utils;

import java.util.WeakHashMap;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R!\u0010\u0003\u001A\u0012\u0012\b\u0012\u00060\u0005R\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0004¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Landroidx/work/impl/utils/WakeLocksHolder;", "", "()V", "wakeLocks", "Ljava/util/WeakHashMap;", "Landroid/os/PowerManager$WakeLock;", "Landroid/os/PowerManager;", "", "getWakeLocks", "()Ljava/util/WeakHashMap;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class WakeLocksHolder {
    public static final WakeLocksHolder INSTANCE;
    private static final WeakHashMap wakeLocks;

    static {
        WakeLocksHolder.INSTANCE = new WakeLocksHolder();
        WakeLocksHolder.wakeLocks = new WeakHashMap();
    }

    public final WeakHashMap getWakeLocks() {
        return WakeLocksHolder.wakeLocks;
    }
}

