package androidx.work.impl.utils;

import java.time.Duration;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\u001A\f\u0010\u0000\u001A\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"toMillisCompat", "", "Ljava/time/Duration;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class DurationApi26Impl {
    public static final long toMillisCompat(Duration duration0) {
        Intrinsics.checkNotNullParameter(duration0, "<this>");
        return duration0.toMillis();
    }
}

