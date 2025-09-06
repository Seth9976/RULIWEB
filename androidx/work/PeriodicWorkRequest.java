package androidx.work;

import android.os.Build.VERSION;
import androidx.work.impl.utils.DurationApi26Impl;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00062\u00020\u0001:\u0002\u0005\u0006B\u000F\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0007"}, d2 = {"Landroidx/work/PeriodicWorkRequest;", "Landroidx/work/WorkRequest;", "builder", "Landroidx/work/PeriodicWorkRequest$Builder;", "(Landroidx/work/PeriodicWorkRequest$Builder;)V", "Builder", "Companion", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class PeriodicWorkRequest extends WorkRequest {
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u00002\u000E\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00020\u0001B)\b\u0016\u0012\u0010\u0010\u0003\u001A\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00050\u0004\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t¢\u0006\u0002\u0010\nB\'\b\u0016\u0012\u000E\u0010\u0003\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u000B\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t¢\u0006\u0002\u0010\fB\u001F\b\u0017\u0012\u000E\u0010\u0003\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001A\u00020\r¢\u0006\u0002\u0010\u000EB\u001F\b\u0017\u0012\u000E\u0010\u0003\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u000B\u0012\u0006\u0010\u0006\u001A\u00020\r¢\u0006\u0002\u0010\u000FB9\b\u0016\u0012\u0010\u0010\u0003\u001A\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00050\u0004\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u0012\u0006\u0010\u0010\u001A\u00020\u0007\u0012\u0006\u0010\u0011\u001A\u00020\t¢\u0006\u0002\u0010\u0012B7\b\u0016\u0012\u000E\u0010\u0003\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u000B\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u0012\u0006\u0010\u0010\u001A\u00020\u0007\u0012\u0006\u0010\u0011\u001A\u00020\t¢\u0006\u0002\u0010\u0013B)\b\u0017\u0012\u0010\u0010\u0003\u001A\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00050\u0004\u0012\u0006\u0010\u0006\u001A\u00020\r\u0012\u0006\u0010\u0010\u001A\u00020\r¢\u0006\u0002\u0010\u0014B\'\b\u0017\u0012\u000E\u0010\u0003\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u000B\u0012\u0006\u0010\u0006\u001A\u00020\r\u0012\u0006\u0010\u0010\u001A\u00020\r¢\u0006\u0002\u0010\u0015J\r\u0010\u0019\u001A\u00020\u0002H\u0010¢\u0006\u0002\b\u001AJ\u0006\u0010\u001B\u001A\u00020\u0000J\u000E\u0010\u001C\u001A\u00020\u00002\u0006\u0010\u001D\u001A\u00020\u0007R\u0014\u0010\u0016\u001A\u00020\u00008PX\u0090\u0004¢\u0006\u0006\u001A\u0004\b\u0017\u0010\u0018¨\u0006\u001E"}, d2 = {"Landroidx/work/PeriodicWorkRequest$Builder;", "Landroidx/work/WorkRequest$Builder;", "Landroidx/work/PeriodicWorkRequest;", "workerClass", "Ljava/lang/Class;", "Landroidx/work/ListenableWorker;", "repeatInterval", "", "repeatIntervalTimeUnit", "Ljava/util/concurrent/TimeUnit;", "(Ljava/lang/Class;JLjava/util/concurrent/TimeUnit;)V", "Lkotlin/reflect/KClass;", "(Lkotlin/reflect/KClass;JLjava/util/concurrent/TimeUnit;)V", "Ljava/time/Duration;", "(Ljava/lang/Class;Ljava/time/Duration;)V", "(Lkotlin/reflect/KClass;Ljava/time/Duration;)V", "flexInterval", "flexIntervalTimeUnit", "(Ljava/lang/Class;JLjava/util/concurrent/TimeUnit;JLjava/util/concurrent/TimeUnit;)V", "(Lkotlin/reflect/KClass;JLjava/util/concurrent/TimeUnit;JLjava/util/concurrent/TimeUnit;)V", "(Ljava/lang/Class;Ljava/time/Duration;Ljava/time/Duration;)V", "(Lkotlin/reflect/KClass;Ljava/time/Duration;Ljava/time/Duration;)V", "thisObject", "getThisObject$work_runtime_release", "()Landroidx/work/PeriodicWorkRequest$Builder;", "buildInternal", "buildInternal$work_runtime_release", "clearNextScheduleTimeOverride", "setNextScheduleTimeOverride", "nextScheduleTimeOverrideMillis", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Builder extends androidx.work.WorkRequest.Builder {
        public Builder(Class class0, long v, TimeUnit timeUnit0) {
            Intrinsics.checkNotNullParameter(class0, "workerClass");
            Intrinsics.checkNotNullParameter(timeUnit0, "repeatIntervalTimeUnit");
            super(class0);
            this.getWorkSpec$work_runtime_release().setPeriodic(timeUnit0.toMillis(v));
        }

        public Builder(Class class0, long v, TimeUnit timeUnit0, long v1, TimeUnit timeUnit1) {
            Intrinsics.checkNotNullParameter(class0, "workerClass");
            Intrinsics.checkNotNullParameter(timeUnit0, "repeatIntervalTimeUnit");
            Intrinsics.checkNotNullParameter(timeUnit1, "flexIntervalTimeUnit");
            super(class0);
            this.getWorkSpec$work_runtime_release().setPeriodic(timeUnit0.toMillis(v), timeUnit1.toMillis(v1));
        }

        public Builder(Class class0, Duration duration0) {
            Intrinsics.checkNotNullParameter(class0, "workerClass");
            Intrinsics.checkNotNullParameter(duration0, "repeatInterval");
            super(class0);
            this.getWorkSpec$work_runtime_release().setPeriodic(DurationApi26Impl.toMillisCompat(duration0));
        }

        public Builder(Class class0, Duration duration0, Duration duration1) {
            Intrinsics.checkNotNullParameter(class0, "workerClass");
            Intrinsics.checkNotNullParameter(duration0, "repeatInterval");
            Intrinsics.checkNotNullParameter(duration1, "flexInterval");
            super(class0);
            this.getWorkSpec$work_runtime_release().setPeriodic(DurationApi26Impl.toMillisCompat(duration0), DurationApi26Impl.toMillisCompat(duration1));
        }

        public Builder(KClass kClass0, long v, TimeUnit timeUnit0) {
            Intrinsics.checkNotNullParameter(kClass0, "workerClass");
            Intrinsics.checkNotNullParameter(timeUnit0, "repeatIntervalTimeUnit");
            super(JvmClassMappingKt.getJavaClass(kClass0));
            this.getWorkSpec$work_runtime_release().setPeriodic(timeUnit0.toMillis(v));
        }

        public Builder(KClass kClass0, long v, TimeUnit timeUnit0, long v1, TimeUnit timeUnit1) {
            Intrinsics.checkNotNullParameter(kClass0, "workerClass");
            Intrinsics.checkNotNullParameter(timeUnit0, "repeatIntervalTimeUnit");
            Intrinsics.checkNotNullParameter(timeUnit1, "flexIntervalTimeUnit");
            super(JvmClassMappingKt.getJavaClass(kClass0));
            this.getWorkSpec$work_runtime_release().setPeriodic(timeUnit0.toMillis(v), timeUnit1.toMillis(v1));
        }

        public Builder(KClass kClass0, Duration duration0) {
            Intrinsics.checkNotNullParameter(kClass0, "workerClass");
            Intrinsics.checkNotNullParameter(duration0, "repeatInterval");
            super(JvmClassMappingKt.getJavaClass(kClass0));
            this.getWorkSpec$work_runtime_release().setPeriodic(DurationApi26Impl.toMillisCompat(duration0));
        }

        public Builder(KClass kClass0, Duration duration0, Duration duration1) {
            Intrinsics.checkNotNullParameter(kClass0, "workerClass");
            Intrinsics.checkNotNullParameter(duration0, "repeatInterval");
            Intrinsics.checkNotNullParameter(duration1, "flexInterval");
            super(JvmClassMappingKt.getJavaClass(kClass0));
            this.getWorkSpec$work_runtime_release().setPeriodic(DurationApi26Impl.toMillisCompat(duration0), DurationApi26Impl.toMillisCompat(duration1));
        }

        public PeriodicWorkRequest buildInternal$work_runtime_release() {
            if(this.getBackoffCriteriaSet$work_runtime_release() && Build.VERSION.SDK_INT >= 23 && this.getWorkSpec$work_runtime_release().constraints.requiresDeviceIdle()) {
                throw new IllegalArgumentException("Cannot set backoff criteria on an idle mode job");
            }
            if(this.getWorkSpec$work_runtime_release().expedited) {
                throw new IllegalArgumentException("PeriodicWorkRequests cannot be expedited");
            }
            return new PeriodicWorkRequest(this);
        }

        @Override  // androidx.work.WorkRequest$Builder
        public WorkRequest buildInternal$work_runtime_release() {
            return this.buildInternal$work_runtime_release();
        }

        public final Builder clearNextScheduleTimeOverride() {
            this.getWorkSpec$work_runtime_release().setNextScheduleTimeOverride(0x7FFFFFFFFFFFFFFFL);
            this.getWorkSpec$work_runtime_release().setNextScheduleTimeOverrideGeneration(1);
            return this;
        }

        public Builder getThisObject$work_runtime_release() [...] // Inlined contents

        @Override  // androidx.work.WorkRequest$Builder
        public androidx.work.WorkRequest.Builder getThisObject$work_runtime_release() {
            return this;
        }

        public final Builder setNextScheduleTimeOverride(long v) {
            if(v == 0x7FFFFFFFFFFFFFFFL) {
                throw new IllegalArgumentException("Cannot set Long.MAX_VALUE as the schedule override time");
            }
            this.getWorkSpec$work_runtime_release().setNextScheduleTimeOverride(v);
            this.getWorkSpec$work_runtime_release().setNextScheduleTimeOverrideGeneration(1);
            return this;
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001A\u00020\u00048\u0006X\u0087T¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001A\u00020\u00048\u0006X\u0087T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/work/PeriodicWorkRequest$Companion;", "", "()V", "MIN_PERIODIC_FLEX_MILLIS", "", "MIN_PERIODIC_INTERVAL_MILLIS", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Companion Companion = null;
    public static final long MIN_PERIODIC_FLEX_MILLIS = 300000L;
    public static final long MIN_PERIODIC_INTERVAL_MILLIS = 900000L;

    static {
        PeriodicWorkRequest.Companion = new Companion(null);
    }

    public PeriodicWorkRequest(Builder periodicWorkRequest$Builder0) {
        Intrinsics.checkNotNullParameter(periodicWorkRequest$Builder0, "builder");
        super(periodicWorkRequest$Builder0.getId$work_runtime_release(), periodicWorkRequest$Builder0.getWorkSpec$work_runtime_release(), periodicWorkRequest$Builder0.getTags$work_runtime_release());
    }
}

