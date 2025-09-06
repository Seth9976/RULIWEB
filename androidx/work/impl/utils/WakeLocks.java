package androidx.work.impl.utils;

import android.content.Context;
import android.os.PowerManager.WakeLock;
import android.os.PowerManager;
import androidx.work.Logger;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\u0006\u0010\u0002\u001A\u00020\u0003\u001A\u001C\u0010\u0004\u001A\u00060\u0005R\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u0001H\u0000\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"TAG", "", "checkWakeLocks", "", "newWakeLock", "Landroid/os/PowerManager$WakeLock;", "Landroid/os/PowerManager;", "context", "Landroid/content/Context;", "tag", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class WakeLocks {
    private static final String TAG;

    // 去混淆评级： 低(20)
    static {
        Intrinsics.checkNotNullExpressionValue("WM-WakeLocks", "tagWithPrefix(\"WakeLocks\")");
        WakeLocks.TAG = "WM-WakeLocks";
    }

    public static final void checkWakeLocks() {
        Map map0 = new LinkedHashMap();
        synchronized(WakeLocksHolder.INSTANCE) {
            map0.putAll(WakeLocksHolder.INSTANCE.getWakeLocks());
        }
        for(Object object0: map0.entrySet()) {
            PowerManager.WakeLock powerManager$WakeLock0 = (PowerManager.WakeLock)((Map.Entry)object0).getKey();
            String s = (String)((Map.Entry)object0).getValue();
            if(powerManager$WakeLock0 != null && powerManager$WakeLock0.isHeld()) {
                Logger.get().warning("WM-WakeLocks", "WakeLock held for " + s);
            }
        }
    }

    public static final PowerManager.WakeLock newWakeLock(Context context0, String s) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(s, "tag");
        Object object0 = context0.getApplicationContext().getSystemService("power");
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type android.os.PowerManager");
        PowerManager.WakeLock powerManager$WakeLock0 = ((PowerManager)object0).newWakeLock(1, "WorkManager: " + s);
        synchronized(WakeLocksHolder.INSTANCE) {
            String s1 = (String)WakeLocksHolder.INSTANCE.getWakeLocks().put(powerManager$WakeLock0, "WorkManager: " + s);
        }
        Intrinsics.checkNotNullExpressionValue(powerManager$WakeLock0, "wakeLock");
        return powerManager$WakeLock0;
    }
}

