package androidx.work;

import android.os.Build.VERSION;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.DurationApi26Impl;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000E\n\u0002\b\r\b&\u0018\u0000 \u00142\u00020\u0001:\u0002\u0013\u0014B%\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tR\u0014\u0010\u0002\u001A\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000BR\u0011\u0010\f\u001A\u00020\b8G¢\u0006\u0006\u001A\u0004\b\r\u0010\u000ER\u0019\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u00078\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u000F\u0010\u0010R\u0013\u0010\u0004\u001A\u00020\u00058\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Landroidx/work/WorkRequest;", "", "id", "Ljava/util/UUID;", "workSpec", "Landroidx/work/impl/model/WorkSpec;", "tags", "", "", "(Ljava/util/UUID;Landroidx/work/impl/model/WorkSpec;Ljava/util/Set;)V", "getId", "()Ljava/util/UUID;", "stringId", "getStringId", "()Ljava/lang/String;", "getTags", "()Ljava/util/Set;", "getWorkSpec", "()Landroidx/work/impl/model/WorkSpec;", "Builder", "Companion", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class WorkRequest {
    @Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\u0010\u000E\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u0000*\u0012\b\u0000\u0010\u0001*\f\u0012\u0004\u0012\u0002H\u0001\u0012\u0002\b\u00030\u0000*\b\b\u0001\u0010\u0002*\u00020\u00032\u00020\u0004B\u0017\b\u0000\u0012\u000E\u0010\u0005\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006\u00A2\u0006\u0002\u0010\bJ\u0013\u0010%\u001A\u00028\u00002\u0006\u0010&\u001A\u00020\u0017\u00A2\u0006\u0002\u0010\'J\u000B\u0010(\u001A\u00028\u0001\u00A2\u0006\u0002\u0010)J\u000F\u0010*\u001A\u00028\u0001H \u00A2\u0006\u0004\b+\u0010)J\u0015\u0010,\u001A\u00028\u00002\u0006\u0010-\u001A\u00020.H\u0007\u00A2\u0006\u0002\u0010/J\u001B\u0010,\u001A\u00028\u00002\u0006\u0010-\u001A\u0002002\u0006\u00101\u001A\u000202\u00A2\u0006\u0002\u00103J\u001D\u00104\u001A\u00028\u00002\u0006\u00105\u001A\u0002062\u0006\u0010-\u001A\u00020.H\u0007\u00A2\u0006\u0002\u00107J#\u00104\u001A\u00028\u00002\u0006\u00105\u001A\u0002062\u0006\u00108\u001A\u0002002\u0006\u00101\u001A\u000202\u00A2\u0006\u0002\u00109J\u0013\u0010:\u001A\u00028\u00002\u0006\u0010;\u001A\u00020<\u00A2\u0006\u0002\u0010=J\u0015\u0010>\u001A\u00028\u00002\u0006\u0010?\u001A\u00020@H\u0017\u00A2\u0006\u0002\u0010AJ\u0013\u0010B\u001A\u00028\u00002\u0006\u0010\u000F\u001A\u00020\u0010\u00A2\u0006\u0002\u0010CJ\u0015\u0010D\u001A\u00028\u00002\u0006\u0010-\u001A\u00020.H\u0017\u00A2\u0006\u0002\u0010/J\u001D\u0010D\u001A\u00028\u00002\u0006\u0010-\u001A\u0002002\u0006\u00101\u001A\u000202H\u0016\u00A2\u0006\u0002\u00103J\u0015\u0010E\u001A\u00028\u00002\u0006\u0010F\u001A\u00020GH\u0007\u00A2\u0006\u0002\u0010HJ\u0015\u0010I\u001A\u00028\u00002\u0006\u0010J\u001A\u00020KH\u0007\u00A2\u0006\u0002\u0010LJ\u0013\u0010M\u001A\u00028\u00002\u0006\u0010N\u001A\u00020O\u00A2\u0006\u0002\u0010PJ\u001D\u0010Q\u001A\u00028\u00002\u0006\u0010R\u001A\u0002002\u0006\u00101\u001A\u000202H\u0007\u00A2\u0006\u0002\u00103J\u001D\u0010S\u001A\u00028\u00002\u0006\u0010T\u001A\u0002002\u0006\u00101\u001A\u000202H\u0007\u00A2\u0006\u0002\u00103J\u0013\u0010U\u001A\u00028\u00002\u0006\u0010V\u001A\u00020\u0017\u00A2\u0006\u0002\u0010\'R\u001A\u0010\t\u001A\u00020\nX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u000B\u0010\f\"\u0004\b\r\u0010\u000ER\u001A\u0010\u000F\u001A\u00020\u0010X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001A\u0010\u0015\u001A\b\u0012\u0004\u0012\u00020\u00170\u0016X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0018\u0010\u0019R\u0012\u0010\u001A\u001A\u00028\u0000X\u00A0\u0004\u00A2\u0006\u0006\u001A\u0004\b\u001B\u0010\u001CR\u001A\u0010\u001D\u001A\u00020\u001EX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u001F\u0010 \"\u0004\b!\u0010\"R\u001C\u0010\u0005\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b#\u0010$\u00A8\u0006W"}, d2 = {"Landroidx/work/WorkRequest$Builder;", "B", "W", "Landroidx/work/WorkRequest;", "", "workerClass", "Ljava/lang/Class;", "Landroidx/work/ListenableWorker;", "(Ljava/lang/Class;)V", "backoffCriteriaSet", "", "getBackoffCriteriaSet$work_runtime_release", "()Z", "setBackoffCriteriaSet$work_runtime_release", "(Z)V", "id", "Ljava/util/UUID;", "getId$work_runtime_release", "()Ljava/util/UUID;", "setId$work_runtime_release", "(Ljava/util/UUID;)V", "tags", "", "", "getTags$work_runtime_release", "()Ljava/util/Set;", "thisObject", "getThisObject$work_runtime_release", "()Landroidx/work/WorkRequest$Builder;", "workSpec", "Landroidx/work/impl/model/WorkSpec;", "getWorkSpec$work_runtime_release", "()Landroidx/work/impl/model/WorkSpec;", "setWorkSpec$work_runtime_release", "(Landroidx/work/impl/model/WorkSpec;)V", "getWorkerClass$work_runtime_release", "()Ljava/lang/Class;", "addTag", "tag", "(Ljava/lang/String;)Landroidx/work/WorkRequest$Builder;", "build", "()Landroidx/work/WorkRequest;", "buildInternal", "buildInternal$work_runtime_release", "keepResultsForAtLeast", "duration", "Ljava/time/Duration;", "(Ljava/time/Duration;)Landroidx/work/WorkRequest$Builder;", "", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "(JLjava/util/concurrent/TimeUnit;)Landroidx/work/WorkRequest$Builder;", "setBackoffCriteria", "backoffPolicy", "Landroidx/work/BackoffPolicy;", "(Landroidx/work/BackoffPolicy;Ljava/time/Duration;)Landroidx/work/WorkRequest$Builder;", "backoffDelay", "(Landroidx/work/BackoffPolicy;JLjava/util/concurrent/TimeUnit;)Landroidx/work/WorkRequest$Builder;", "setConstraints", "constraints", "Landroidx/work/Constraints;", "(Landroidx/work/Constraints;)Landroidx/work/WorkRequest$Builder;", "setExpedited", "policy", "Landroidx/work/OutOfQuotaPolicy;", "(Landroidx/work/OutOfQuotaPolicy;)Landroidx/work/WorkRequest$Builder;", "setId", "(Ljava/util/UUID;)Landroidx/work/WorkRequest$Builder;", "setInitialDelay", "setInitialRunAttemptCount", "runAttemptCount", "", "(I)Landroidx/work/WorkRequest$Builder;", "setInitialState", "state", "Landroidx/work/WorkInfo$State;", "(Landroidx/work/WorkInfo$State;)Landroidx/work/WorkRequest$Builder;", "setInputData", "inputData", "Landroidx/work/Data;", "(Landroidx/work/Data;)Landroidx/work/WorkRequest$Builder;", "setLastEnqueueTime", "lastEnqueueTime", "setScheduleRequestedAt", "scheduleRequestedAt", "setTraceTag", "traceTag", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static abstract class Builder {
        private boolean backoffCriteriaSet;
        private UUID id;
        private final Set tags;
        private WorkSpec workSpec;
        private final Class workerClass;

        public Builder(Class class0) {
            Intrinsics.checkNotNullParameter(class0, "workerClass");
            super();
            this.workerClass = class0;
            UUID uUID0 = UUID.randomUUID();
            Intrinsics.checkNotNullExpressionValue(uUID0, "randomUUID()");
            this.id = uUID0;
            String s = this.id.toString();
            Intrinsics.checkNotNullExpressionValue(s, "id.toString()");
            String s1 = class0.getName();
            Intrinsics.checkNotNullExpressionValue(s1, "workerClass.name");
            this.workSpec = new WorkSpec(s, s1);
            String s2 = class0.getName();
            Intrinsics.checkNotNullExpressionValue(s2, "workerClass.name");
            this.tags = SetsKt.mutableSetOf(new String[]{s2});
        }

        public final Builder addTag(String s) {
            Intrinsics.checkNotNullParameter(s, "tag");
            this.tags.add(s);
            return this.getThisObject$work_runtime_release();
        }

        public final WorkRequest build() {
            WorkRequest workRequest0 = this.buildInternal$work_runtime_release();
            Constraints constraints0 = this.workSpec.constraints;
            boolean z = Build.VERSION.SDK_INT >= 24 && constraints0.hasContentUriTriggers() || constraints0.requiresBatteryNotLow() || constraints0.requiresCharging() || Build.VERSION.SDK_INT >= 23 && constraints0.requiresDeviceIdle();
            if(this.workSpec.expedited) {
                if(z) {
                    throw new IllegalArgumentException("Expedited jobs only support network and storage constraints");
                }
                if(this.workSpec.initialDelay > 0L) {
                    throw new IllegalArgumentException("Expedited jobs cannot be delayed");
                }
            }
            if(this.workSpec.getTraceTag() == null) {
                this.workSpec.setTraceTag(Companion.access$deriveTraceTagFromClassName(WorkRequest.Companion, this.workSpec.workerClassName));
            }
            UUID uUID0 = UUID.randomUUID();
            Intrinsics.checkNotNullExpressionValue(uUID0, "randomUUID()");
            this.setId(uUID0);
            return workRequest0;
        }

        public abstract WorkRequest buildInternal$work_runtime_release();

        public final boolean getBackoffCriteriaSet$work_runtime_release() {
            return this.backoffCriteriaSet;
        }

        public final UUID getId$work_runtime_release() {
            return this.id;
        }

        public final Set getTags$work_runtime_release() {
            return this.tags;
        }

        public abstract Builder getThisObject$work_runtime_release();

        public final WorkSpec getWorkSpec$work_runtime_release() {
            return this.workSpec;
        }

        public final Class getWorkerClass$work_runtime_release() {
            return this.workerClass;
        }

        public final Builder keepResultsForAtLeast(long v, TimeUnit timeUnit0) {
            Intrinsics.checkNotNullParameter(timeUnit0, "timeUnit");
            WorkSpec workSpec0 = this.workSpec;
            workSpec0.minimumRetentionDuration = timeUnit0.toMillis(v);
            return this.getThisObject$work_runtime_release();
        }

        public final Builder keepResultsForAtLeast(Duration duration0) {
            Intrinsics.checkNotNullParameter(duration0, "duration");
            WorkSpec workSpec0 = this.workSpec;
            workSpec0.minimumRetentionDuration = DurationApi26Impl.toMillisCompat(duration0);
            return this.getThisObject$work_runtime_release();
        }

        public final Builder setBackoffCriteria(BackoffPolicy backoffPolicy0, long v, TimeUnit timeUnit0) {
            Intrinsics.checkNotNullParameter(backoffPolicy0, "backoffPolicy");
            Intrinsics.checkNotNullParameter(timeUnit0, "timeUnit");
            this.backoffCriteriaSet = true;
            this.workSpec.backoffPolicy = backoffPolicy0;
            this.workSpec.setBackoffDelayDuration(timeUnit0.toMillis(v));
            return this.getThisObject$work_runtime_release();
        }

        public final Builder setBackoffCriteria(BackoffPolicy backoffPolicy0, Duration duration0) {
            Intrinsics.checkNotNullParameter(backoffPolicy0, "backoffPolicy");
            Intrinsics.checkNotNullParameter(duration0, "duration");
            this.backoffCriteriaSet = true;
            this.workSpec.backoffPolicy = backoffPolicy0;
            this.workSpec.setBackoffDelayDuration(DurationApi26Impl.toMillisCompat(duration0));
            return this.getThisObject$work_runtime_release();
        }

        public final void setBackoffCriteriaSet$work_runtime_release(boolean z) {
            this.backoffCriteriaSet = z;
        }

        public final Builder setConstraints(Constraints constraints0) {
            Intrinsics.checkNotNullParameter(constraints0, "constraints");
            this.workSpec.constraints = constraints0;
            return this.getThisObject$work_runtime_release();
        }

        public Builder setExpedited(OutOfQuotaPolicy outOfQuotaPolicy0) {
            Intrinsics.checkNotNullParameter(outOfQuotaPolicy0, "policy");
            this.workSpec.expedited = true;
            this.workSpec.outOfQuotaPolicy = outOfQuotaPolicy0;
            return this.getThisObject$work_runtime_release();
        }

        public final Builder setId(UUID uUID0) {
            Intrinsics.checkNotNullParameter(uUID0, "id");
            this.id = uUID0;
            String s = uUID0.toString();
            Intrinsics.checkNotNullExpressionValue(s, "id.toString()");
            this.workSpec = new WorkSpec(s, this.workSpec);
            return this.getThisObject$work_runtime_release();
        }

        public final void setId$work_runtime_release(UUID uUID0) {
            Intrinsics.checkNotNullParameter(uUID0, "<set-?>");
            this.id = uUID0;
        }

        public Builder setInitialDelay(long v, TimeUnit timeUnit0) {
            Intrinsics.checkNotNullParameter(timeUnit0, "timeUnit");
            WorkSpec workSpec0 = this.workSpec;
            workSpec0.initialDelay = timeUnit0.toMillis(v);
            if(0x7FFFFFFFFFFFFFFFL - System.currentTimeMillis() <= this.workSpec.initialDelay) {
                throw new IllegalArgumentException("The given initial delay is too large and will cause an overflow!");
            }
            return this.getThisObject$work_runtime_release();
        }

        public Builder setInitialDelay(Duration duration0) {
            Intrinsics.checkNotNullParameter(duration0, "duration");
            WorkSpec workSpec0 = this.workSpec;
            workSpec0.initialDelay = DurationApi26Impl.toMillisCompat(duration0);
            if(0x7FFFFFFFFFFFFFFFL - System.currentTimeMillis() <= this.workSpec.initialDelay) {
                throw new IllegalArgumentException("The given initial delay is too large and will cause an overflow!");
            }
            return this.getThisObject$work_runtime_release();
        }

        public final Builder setInitialRunAttemptCount(int v) {
            this.workSpec.runAttemptCount = v;
            return this.getThisObject$work_runtime_release();
        }

        public final Builder setInitialState(State workInfo$State0) {
            Intrinsics.checkNotNullParameter(workInfo$State0, "state");
            this.workSpec.state = workInfo$State0;
            return this.getThisObject$work_runtime_release();
        }

        public final Builder setInputData(Data data0) {
            Intrinsics.checkNotNullParameter(data0, "inputData");
            this.workSpec.input = data0;
            return this.getThisObject$work_runtime_release();
        }

        public final Builder setLastEnqueueTime(long v, TimeUnit timeUnit0) {
            Intrinsics.checkNotNullParameter(timeUnit0, "timeUnit");
            WorkSpec workSpec0 = this.workSpec;
            workSpec0.lastEnqueueTime = timeUnit0.toMillis(v);
            return this.getThisObject$work_runtime_release();
        }

        public final Builder setScheduleRequestedAt(long v, TimeUnit timeUnit0) {
            Intrinsics.checkNotNullParameter(timeUnit0, "timeUnit");
            WorkSpec workSpec0 = this.workSpec;
            workSpec0.scheduleRequestedAt = timeUnit0.toMillis(v);
            return this.getThisObject$work_runtime_release();
        }

        public final Builder setTraceTag(String s) {
            Intrinsics.checkNotNullParameter(s, "traceTag");
            this.workSpec.setTraceTag(s);
            return this.getThisObject$work_runtime_release();
        }

        public final void setWorkSpec$work_runtime_release(WorkSpec workSpec0) {
            Intrinsics.checkNotNullParameter(workSpec0, "<set-?>");
            this.workSpec = workSpec0;
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\nH\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001A\u00020\u00048\u0006X\u0087T¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001A\u00020\u00048\u0006X\u0087T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/work/WorkRequest$Companion;", "", "()V", "DEFAULT_BACKOFF_DELAY_MILLIS", "", "MAX_BACKOFF_MILLIS", "MAX_TRACE_SPAN_LENGTH", "", "MIN_BACKOFF_MILLIS", "deriveTraceTagFromClassName", "", "workerClassName", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public static final String access$deriveTraceTagFromClassName(Companion workRequest$Companion0, String s) {
            return workRequest$Companion0.deriveTraceTagFromClassName(s);
        }

        private final String deriveTraceTagFromClassName(String s) {
            List list0 = StringsKt.split$default(s, new String[]{"."}, false, 0, 6, null);
            String s1 = list0.size() == 1 ? ((String)list0.get(0)) : ((String)CollectionsKt.last(list0));
            return s1.length() > 0x7F ? StringsKt.take(s1, 0x7F) : s1;
        }
    }

    public static final Companion Companion = null;
    public static final long DEFAULT_BACKOFF_DELAY_MILLIS = 30000L;
    public static final long MAX_BACKOFF_MILLIS = 18000000L;
    private static final int MAX_TRACE_SPAN_LENGTH = 0x7F;
    public static final long MIN_BACKOFF_MILLIS = 10000L;
    private final UUID id;
    private final Set tags;
    private final WorkSpec workSpec;

    static {
        WorkRequest.Companion = new Companion(null);
    }

    public WorkRequest(UUID uUID0, WorkSpec workSpec0, Set set0) {
        Intrinsics.checkNotNullParameter(uUID0, "id");
        Intrinsics.checkNotNullParameter(workSpec0, "workSpec");
        Intrinsics.checkNotNullParameter(set0, "tags");
        super();
        this.id = uUID0;
        this.workSpec = workSpec0;
        this.tags = set0;
    }

    public UUID getId() {
        return this.id;
    }

    public final String getStringId() {
        String s = this.getId().toString();
        Intrinsics.checkNotNullExpressionValue(s, "id.toString()");
        return s;
    }

    public final Set getTags() {
        return this.tags;
    }

    public final WorkSpec getWorkSpec() {
        return this.workSpec;
    }
}

