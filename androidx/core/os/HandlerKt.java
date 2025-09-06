package androidx.core.os;

import android.os.Handler;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u001A1\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u00042\n\b\u0002\u0010\u0005\u001A\u0004\u0018\u00010\u00062\u000E\b\u0004\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\t0\bH\u0086\b\u001A1\u0010\n\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u000B\u001A\u00020\u00042\n\b\u0002\u0010\u0005\u001A\u0004\u0018\u00010\u00062\u000E\b\u0004\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\t0\bH\u0086\b¨\u0006\f"}, d2 = {"postAtTime", "Ljava/lang/Runnable;", "Landroid/os/Handler;", "uptimeMillis", "", "token", "", "action", "Lkotlin/Function0;", "", "postDelayed", "delayInMillis", "core-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class HandlerKt {
    public static final Runnable postAtTime(Handler handler0, long v, Object object0, Function0 function00) {
        Runnable runnable0 = new Runnable() {
            @Override
            public final void run() {
                function00.invoke();
            }
        };
        handler0.postAtTime(runnable0, object0, v);
        return runnable0;
    }

    public static Runnable postAtTime$default(Handler handler0, long v, Object object0, Function0 function00, int v1, Object object1) {
        if((v1 & 2) != 0) {
            object0 = null;
        }
        Runnable runnable0 = new androidx.core.os.HandlerKt.postAtTime.runnable.1(function00);
        handler0.postAtTime(runnable0, object0, v);
        return runnable0;
    }

    public static final Runnable postDelayed(Handler handler0, long v, Object object0, Function0 function00) {
        Runnable runnable0 = new Runnable() {
            @Override
            public final void run() {
                function00.invoke();
            }
        };
        if(object0 == null) {
            handler0.postDelayed(runnable0, v);
            return runnable0;
        }
        HandlerCompat.postDelayed(handler0, runnable0, object0, v);
        return runnable0;
    }

    public static Runnable postDelayed$default(Handler handler0, long v, Object object0, Function0 function00, int v1, Object object1) {
        if((v1 & 2) != 0) {
            object0 = null;
        }
        Runnable runnable0 = new androidx.core.os.HandlerKt.postDelayed.runnable.1(function00);
        if(object0 == null) {
            handler0.postDelayed(runnable0, v);
            return runnable0;
        }
        HandlerCompat.postDelayed(handler0, runnable0, object0, v);
        return runnable0;
    }
}

