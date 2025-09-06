package androidx.work.impl.constraints.trackers;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000E\n\u0000\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0004"}, d2 = {"BATTERY_LOW_THRESHOLD", "", "TAG", "", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class BatteryNotLowTrackerKt {
    public static final float BATTERY_LOW_THRESHOLD = 0.15f;
    private static final String TAG;

    // 去混淆评级： 低(20)
    static {
        Intrinsics.checkNotNullExpressionValue("WM-BatteryNotLowTracker", "tagWithPrefix(\"BatteryNotLowTracker\")");
        BatteryNotLowTrackerKt.TAG = "WM-BatteryNotLowTracker";
    }

    // 去混淆评级： 低(20)
    public static final String access$getTAG$p() [...] // 潜在的解密器
}

