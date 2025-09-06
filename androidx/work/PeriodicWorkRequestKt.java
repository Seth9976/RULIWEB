package androidx.work;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\u001D\u0010\u0000\u001A\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H\u0087\b\u001A%\u0010\u0000\u001A\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0005H\u0087\b\u001A%\u0010\u0000\u001A\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\tH\u0086\b\u001A5\u0010\u0000\u001A\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010\n\u001A\u00020\tH\u0086\b¨\u0006\u000B"}, d2 = {"PeriodicWorkRequestBuilder", "Landroidx/work/PeriodicWorkRequest$Builder;", "W", "Landroidx/work/ListenableWorker;", "repeatInterval", "Ljava/time/Duration;", "flexTimeInterval", "", "repeatIntervalTimeUnit", "Ljava/util/concurrent/TimeUnit;", "flexTimeIntervalUnit", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class PeriodicWorkRequestKt {
    public static final Builder PeriodicWorkRequestBuilder(long v, TimeUnit timeUnit0) {
        Intrinsics.checkNotNullParameter(timeUnit0, "repeatIntervalTimeUnit");
        Intrinsics.reifiedOperationMarker(4, "W");
        return new Builder(ListenableWorker.class, v, timeUnit0);
    }

    public static final Builder PeriodicWorkRequestBuilder(long v, TimeUnit timeUnit0, long v1, TimeUnit timeUnit1) {
        Intrinsics.checkNotNullParameter(timeUnit0, "repeatIntervalTimeUnit");
        Intrinsics.checkNotNullParameter(timeUnit1, "flexTimeIntervalUnit");
        Intrinsics.reifiedOperationMarker(4, "W");
        return new Builder(ListenableWorker.class, v, timeUnit0, v1, timeUnit1);
    }

    public static final Builder PeriodicWorkRequestBuilder(Duration duration0) {
        Intrinsics.checkNotNullParameter(duration0, "repeatInterval");
        Intrinsics.reifiedOperationMarker(4, "W");
        return new Builder(ListenableWorker.class, duration0);
    }

    public static final Builder PeriodicWorkRequestBuilder(Duration duration0, Duration duration1) {
        Intrinsics.checkNotNullParameter(duration0, "repeatInterval");
        Intrinsics.checkNotNullParameter(duration1, "flexTimeInterval");
        Intrinsics.reifiedOperationMarker(4, "W");
        return new Builder(ListenableWorker.class, duration0, duration1);
    }
}

