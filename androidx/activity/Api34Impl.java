package androidx.activity;

import android.window.BackEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u00062\u0006\u0010\b\u001A\u00020\u00062\u0006\u0010\t\u001A\u00020\nJ\u000E\u0010\b\u001A\u00020\u00062\u0006\u0010\u000B\u001A\u00020\u0004J\u000E\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\u0004J\u000E\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u000B\u001A\u00020\u0004J\u000E\u0010\u0007\u001A\u00020\u00062\u0006\u0010\u000B\u001A\u00020\u0004¨\u0006\f"}, d2 = {"Landroidx/activity/Api34Impl;", "", "()V", "createOnBackEvent", "Landroid/window/BackEvent;", "touchX", "", "touchY", "progress", "swipeEdge", "", "backEvent", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class Api34Impl {
    public static final Api34Impl INSTANCE;

    static {
        Api34Impl.INSTANCE = new Api34Impl();
    }

    public final BackEvent createOnBackEvent(float f, float f1, float f2, int v) {
        return new BackEvent(f, f1, f2, v);
    }

    public final float progress(BackEvent backEvent0) {
        Intrinsics.checkNotNullParameter(backEvent0, "backEvent");
        return backEvent0.getProgress();
    }

    public final int swipeEdge(BackEvent backEvent0) {
        Intrinsics.checkNotNullParameter(backEvent0, "backEvent");
        return backEvent0.getSwipeEdge();
    }

    public final float touchX(BackEvent backEvent0) {
        Intrinsics.checkNotNullParameter(backEvent0, "backEvent");
        return backEvent0.getTouchX();
    }

    public final float touchY(BackEvent backEvent0) {
        Intrinsics.checkNotNullParameter(backEvent0, "backEvent");
        return backEvent0.getTouchY();
    }
}

