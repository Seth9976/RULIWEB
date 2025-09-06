package androidx.work;

import java.util.Set;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000B\n\u0002\b\u0007\u0018\u0000 12\u00020\u0001:\u0003123B\u0081\u0001\b\u0007\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0002\u0010\t\u001A\u00020\n\u0012\b\b\u0002\u0010\u000B\u001A\u00020\n\u0012\b\b\u0002\u0010\f\u001A\u00020\r\u0012\b\b\u0002\u0010\u000E\u001A\u00020\r\u0012\b\b\u0002\u0010\u000F\u001A\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001A\u00020\u0012\u0012\n\b\u0002\u0010\u0013\u001A\u0004\u0018\u00010\u0014\u0012\b\b\u0002\u0010\u0015\u001A\u00020\u0012\u0012\b\b\u0002\u0010\u0016\u001A\u00020\r\u00A2\u0006\u0002\u0010\u0017J\u0013\u0010,\u001A\u00020-2\b\u0010.\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010/\u001A\u00020\rH\u0016J\b\u00100\u001A\u00020\bH\u0016R\u0011\u0010\u000F\u001A\u00020\u0010\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0018\u0010\u0019R\u0011\u0010\u000E\u001A\u00020\r\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001A\u0010\u001BR\u0011\u0010\u0002\u001A\u00020\u0003\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001C\u0010\u001DR\u0011\u0010\u0011\u001A\u00020\u0012\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001E\u0010\u001FR\u0011\u0010\u0015\u001A\u00020\u0012\u00A2\u0006\b\n\u0000\u001A\u0004\b \u0010\u001FR\u0011\u0010\t\u001A\u00020\n\u00A2\u0006\b\n\u0000\u001A\u0004\b!\u0010\"R\u0013\u0010\u0013\u001A\u0004\u0018\u00010\u0014\u00A2\u0006\b\n\u0000\u001A\u0004\b#\u0010$R\u0011\u0010\u000B\u001A\u00020\n\u00A2\u0006\b\n\u0000\u001A\u0004\b%\u0010\"R\u0013\u0010\f\u001A\u00020\r8\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b&\u0010\u001BR\u0011\u0010\u0004\u001A\u00020\u0005\u00A2\u0006\b\n\u0000\u001A\u0004\b\'\u0010(R\u0013\u0010\u0016\u001A\u00020\r8\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b)\u0010\u001BR\u0017\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b*\u0010+\u00A8\u00064"}, d2 = {"Landroidx/work/WorkInfo;", "", "id", "Ljava/util/UUID;", "state", "Landroidx/work/WorkInfo$State;", "tags", "", "", "outputData", "Landroidx/work/Data;", "progress", "runAttemptCount", "", "generation", "constraints", "Landroidx/work/Constraints;", "initialDelayMillis", "", "periodicityInfo", "Landroidx/work/WorkInfo$PeriodicityInfo;", "nextScheduleTimeMillis", "stopReason", "(Ljava/util/UUID;Landroidx/work/WorkInfo$State;Ljava/util/Set;Landroidx/work/Data;Landroidx/work/Data;IILandroidx/work/Constraints;JLandroidx/work/WorkInfo$PeriodicityInfo;JI)V", "getConstraints", "()Landroidx/work/Constraints;", "getGeneration", "()I", "getId", "()Ljava/util/UUID;", "getInitialDelayMillis", "()J", "getNextScheduleTimeMillis", "getOutputData", "()Landroidx/work/Data;", "getPeriodicityInfo", "()Landroidx/work/WorkInfo$PeriodicityInfo;", "getProgress", "getRunAttemptCount", "getState", "()Landroidx/work/WorkInfo$State;", "getStopReason", "getTags", "()Ljava/util/Set;", "equals", "", "other", "hashCode", "toString", "Companion", "PeriodicityInfo", "State", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class WorkInfo {
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0012\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\r\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u000E\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u000F\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0010\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0011\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0012\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0013\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0014\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0015\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Landroidx/work/WorkInfo$Companion;", "", "()V", "STOP_REASON_APP_STANDBY", "", "STOP_REASON_BACKGROUND_RESTRICTION", "STOP_REASON_CANCELLED_BY_APP", "STOP_REASON_CONSTRAINT_BATTERY_NOT_LOW", "STOP_REASON_CONSTRAINT_CHARGING", "STOP_REASON_CONSTRAINT_CONNECTIVITY", "STOP_REASON_CONSTRAINT_DEVICE_IDLE", "STOP_REASON_CONSTRAINT_STORAGE_NOT_LOW", "STOP_REASON_DEVICE_STATE", "STOP_REASON_ESTIMATED_APP_LAUNCH_TIME_CHANGED", "STOP_REASON_FOREGROUND_SERVICE_TIMEOUT", "STOP_REASON_NOT_STOPPED", "STOP_REASON_PREEMPT", "STOP_REASON_QUOTA", "STOP_REASON_SYSTEM_PROCESSING", "STOP_REASON_TIMEOUT", "STOP_REASON_UNKNOWN", "STOP_REASON_USER", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0013\u0010\t\u001A\u00020\n2\b\u0010\u000B\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\f\u001A\u00020\rH\u0016J\b\u0010\u000E\u001A\u00020\u000FH\u0016R\u0011\u0010\u0004\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\u0007¨\u0006\u0010"}, d2 = {"Landroidx/work/WorkInfo$PeriodicityInfo;", "", "repeatIntervalMillis", "", "flexIntervalMillis", "(JJ)V", "getFlexIntervalMillis", "()J", "getRepeatIntervalMillis", "equals", "", "other", "hashCode", "", "toString", "", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class PeriodicityInfo {
        private final long flexIntervalMillis;
        private final long repeatIntervalMillis;

        public PeriodicityInfo(long v, long v1) {
            this.repeatIntervalMillis = v;
            this.flexIntervalMillis = v1;
        }

        // 去混淆评级： 低(20)
        @Override
        public boolean equals(Object object0) {
            return this == object0 ? true : object0 != null && Intrinsics.areEqual(this.getClass(), object0.getClass()) && ((PeriodicityInfo)object0).repeatIntervalMillis == this.repeatIntervalMillis && ((PeriodicityInfo)object0).flexIntervalMillis == this.flexIntervalMillis;
        }

        public final long getFlexIntervalMillis() {
            return this.flexIntervalMillis;
        }

        public final long getRepeatIntervalMillis() {
            return this.repeatIntervalMillis;
        }

        @Override
        public int hashCode() {
            return ((int)(this.repeatIntervalMillis ^ this.repeatIntervalMillis >>> 0x20)) * 0x1F + ((int)(this.flexIntervalMillis ^ this.flexIntervalMillis >>> 0x20));
        }

        @Override
        public String toString() {
            return "PeriodicityInfo{repeatIntervalMillis=" + this.repeatIntervalMillis + ", flexIntervalMillis=" + this.flexIntervalMillis + '}';
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001A\u00020\u00048F¢\u0006\u0006\u001A\u0004\b\u0003\u0010\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000B¨\u0006\f"}, d2 = {"Landroidx/work/WorkInfo$State;", "", "(Ljava/lang/String;I)V", "isFinished", "", "()Z", "ENQUEUED", "RUNNING", "SUCCEEDED", "FAILED", "BLOCKED", "CANCELLED", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static enum State {
        ENQUEUED,
        RUNNING,
        SUCCEEDED,
        FAILED,
        BLOCKED,
        CANCELLED;

        private static final State[] $values() [...] // Inlined contents

        public final boolean isFinished() [...] // 潜在的解密器
    }

    public static final Companion Companion = null;
    public static final int STOP_REASON_APP_STANDBY = 12;
    public static final int STOP_REASON_BACKGROUND_RESTRICTION = 11;
    public static final int STOP_REASON_CANCELLED_BY_APP = 1;
    public static final int STOP_REASON_CONSTRAINT_BATTERY_NOT_LOW = 5;
    public static final int STOP_REASON_CONSTRAINT_CHARGING = 6;
    public static final int STOP_REASON_CONSTRAINT_CONNECTIVITY = 7;
    public static final int STOP_REASON_CONSTRAINT_DEVICE_IDLE = 8;
    public static final int STOP_REASON_CONSTRAINT_STORAGE_NOT_LOW = 9;
    public static final int STOP_REASON_DEVICE_STATE = 4;
    public static final int STOP_REASON_ESTIMATED_APP_LAUNCH_TIME_CHANGED = 15;
    public static final int STOP_REASON_FOREGROUND_SERVICE_TIMEOUT = 0xFFFFFF80;
    public static final int STOP_REASON_NOT_STOPPED = 0xFFFFFF00;
    public static final int STOP_REASON_PREEMPT = 2;
    public static final int STOP_REASON_QUOTA = 10;
    public static final int STOP_REASON_SYSTEM_PROCESSING = 14;
    public static final int STOP_REASON_TIMEOUT = 3;
    public static final int STOP_REASON_UNKNOWN = 0xFFFFFE00;
    public static final int STOP_REASON_USER = 13;
    private final Constraints constraints;
    private final int generation;
    private final UUID id;
    private final long initialDelayMillis;
    private final long nextScheduleTimeMillis;
    private final Data outputData;
    private final PeriodicityInfo periodicityInfo;
    private final Data progress;
    private final int runAttemptCount;
    private final State state;
    private final int stopReason;
    private final Set tags;

    static {
        WorkInfo.Companion = new Companion(null);
    }

    public WorkInfo(UUID uUID0, State workInfo$State0, Set set0) {
        Intrinsics.checkNotNullParameter(uUID0, "id");
        Intrinsics.checkNotNullParameter(workInfo$State0, "state");
        Intrinsics.checkNotNullParameter(set0, "tags");
        this(uUID0, workInfo$State0, set0, null, null, 0, 0, null, 0L, null, 0L, 0, 0xFF8, null);
    }

    public WorkInfo(UUID uUID0, State workInfo$State0, Set set0, Data data0) {
        Intrinsics.checkNotNullParameter(uUID0, "id");
        Intrinsics.checkNotNullParameter(workInfo$State0, "state");
        Intrinsics.checkNotNullParameter(set0, "tags");
        Intrinsics.checkNotNullParameter(data0, "outputData");
        this(uUID0, workInfo$State0, set0, data0, null, 0, 0, null, 0L, null, 0L, 0, 0xFF0, null);
    }

    public WorkInfo(UUID uUID0, State workInfo$State0, Set set0, Data data0, Data data1) {
        Intrinsics.checkNotNullParameter(uUID0, "id");
        Intrinsics.checkNotNullParameter(workInfo$State0, "state");
        Intrinsics.checkNotNullParameter(set0, "tags");
        Intrinsics.checkNotNullParameter(data0, "outputData");
        Intrinsics.checkNotNullParameter(data1, "progress");
        this(uUID0, workInfo$State0, set0, data0, data1, 0, 0, null, 0L, null, 0L, 0, 0xFE0, null);
    }

    public WorkInfo(UUID uUID0, State workInfo$State0, Set set0, Data data0, Data data1, int v) {
        Intrinsics.checkNotNullParameter(uUID0, "id");
        Intrinsics.checkNotNullParameter(workInfo$State0, "state");
        Intrinsics.checkNotNullParameter(set0, "tags");
        Intrinsics.checkNotNullParameter(data0, "outputData");
        Intrinsics.checkNotNullParameter(data1, "progress");
        this(uUID0, workInfo$State0, set0, data0, data1, v, 0, null, 0L, null, 0L, 0, 0xFC0, null);
    }

    public WorkInfo(UUID uUID0, State workInfo$State0, Set set0, Data data0, Data data1, int v, int v1) {
        Intrinsics.checkNotNullParameter(uUID0, "id");
        Intrinsics.checkNotNullParameter(workInfo$State0, "state");
        Intrinsics.checkNotNullParameter(set0, "tags");
        Intrinsics.checkNotNullParameter(data0, "outputData");
        Intrinsics.checkNotNullParameter(data1, "progress");
        this(uUID0, workInfo$State0, set0, data0, data1, v, v1, null, 0L, null, 0L, 0, 0xF80, null);
    }

    public WorkInfo(UUID uUID0, State workInfo$State0, Set set0, Data data0, Data data1, int v, int v1, Constraints constraints0) {
        Intrinsics.checkNotNullParameter(uUID0, "id");
        Intrinsics.checkNotNullParameter(workInfo$State0, "state");
        Intrinsics.checkNotNullParameter(set0, "tags");
        Intrinsics.checkNotNullParameter(data0, "outputData");
        Intrinsics.checkNotNullParameter(data1, "progress");
        Intrinsics.checkNotNullParameter(constraints0, "constraints");
        this(uUID0, workInfo$State0, set0, data0, data1, v, v1, constraints0, 0L, null, 0L, 0, 0xF00, null);
    }

    public WorkInfo(UUID uUID0, State workInfo$State0, Set set0, Data data0, Data data1, int v, int v1, Constraints constraints0, long v2) {
        Intrinsics.checkNotNullParameter(uUID0, "id");
        Intrinsics.checkNotNullParameter(workInfo$State0, "state");
        Intrinsics.checkNotNullParameter(set0, "tags");
        Intrinsics.checkNotNullParameter(data0, "outputData");
        Intrinsics.checkNotNullParameter(data1, "progress");
        Intrinsics.checkNotNullParameter(constraints0, "constraints");
        this(uUID0, workInfo$State0, set0, data0, data1, v, v1, constraints0, v2, null, 0L, 0, 0xE00, null);
    }

    public WorkInfo(UUID uUID0, State workInfo$State0, Set set0, Data data0, Data data1, int v, int v1, Constraints constraints0, long v2, PeriodicityInfo workInfo$PeriodicityInfo0) {
        Intrinsics.checkNotNullParameter(uUID0, "id");
        Intrinsics.checkNotNullParameter(workInfo$State0, "state");
        Intrinsics.checkNotNullParameter(set0, "tags");
        Intrinsics.checkNotNullParameter(data0, "outputData");
        Intrinsics.checkNotNullParameter(data1, "progress");
        Intrinsics.checkNotNullParameter(constraints0, "constraints");
        this(uUID0, workInfo$State0, set0, data0, data1, v, v1, constraints0, v2, workInfo$PeriodicityInfo0, 0L, 0, 0xC00, null);
    }

    public WorkInfo(UUID uUID0, State workInfo$State0, Set set0, Data data0, Data data1, int v, int v1, Constraints constraints0, long v2, PeriodicityInfo workInfo$PeriodicityInfo0, long v3) {
        Intrinsics.checkNotNullParameter(uUID0, "id");
        Intrinsics.checkNotNullParameter(workInfo$State0, "state");
        Intrinsics.checkNotNullParameter(set0, "tags");
        Intrinsics.checkNotNullParameter(data0, "outputData");
        Intrinsics.checkNotNullParameter(data1, "progress");
        Intrinsics.checkNotNullParameter(constraints0, "constraints");
        this(uUID0, workInfo$State0, set0, data0, data1, v, v1, constraints0, v2, workInfo$PeriodicityInfo0, v3, 0, 0x800, null);
    }

    public WorkInfo(UUID uUID0, State workInfo$State0, Set set0, Data data0, Data data1, int v, int v1, Constraints constraints0, long v2, PeriodicityInfo workInfo$PeriodicityInfo0, long v3, int v4) {
        Intrinsics.checkNotNullParameter(uUID0, "id");
        Intrinsics.checkNotNullParameter(workInfo$State0, "state");
        Intrinsics.checkNotNullParameter(set0, "tags");
        Intrinsics.checkNotNullParameter(data0, "outputData");
        Intrinsics.checkNotNullParameter(data1, "progress");
        Intrinsics.checkNotNullParameter(constraints0, "constraints");
        super();
        this.id = uUID0;
        this.state = workInfo$State0;
        this.tags = set0;
        this.outputData = data0;
        this.progress = data1;
        this.runAttemptCount = v;
        this.generation = v1;
        this.constraints = constraints0;
        this.initialDelayMillis = v2;
        this.periodicityInfo = workInfo$PeriodicityInfo0;
        this.nextScheduleTimeMillis = v3;
        this.stopReason = v4;
    }

    public WorkInfo(UUID uUID0, State workInfo$State0, Set set0, Data data0, Data data1, int v, int v1, Constraints constraints0, long v2, PeriodicityInfo workInfo$PeriodicityInfo0, long v3, int v4, int v5, DefaultConstructorMarker defaultConstructorMarker0) {
        this(uUID0, workInfo$State0, set0, ((v5 & 8) == 0 ? data0 : Data.EMPTY), ((v5 & 16) == 0 ? data1 : Data.EMPTY), ((v5 & 0x20) == 0 ? v : 0), ((v5 & 0x40) == 0 ? v1 : 0), ((v5 & 0x80) == 0 ? constraints0 : Constraints.NONE), ((v5 & 0x100) == 0 ? v2 : 0L), ((v5 & 0x200) == 0 ? workInfo$PeriodicityInfo0 : null), ((v5 & 0x400) == 0 ? v3 : 0x7FFFFFFFFFFFFFFFL), ((v5 & 0x800) == 0 ? v4 : 0xFFFFFF00));
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(object0 == null || !Intrinsics.areEqual(this.getClass(), object0.getClass()) || this.runAttemptCount != ((WorkInfo)object0).runAttemptCount) {
            return false;
        }
        if(this.generation != ((WorkInfo)object0).generation) {
            return false;
        }
        if(!Intrinsics.areEqual(this.id, ((WorkInfo)object0).id)) {
            return false;
        }
        if(this.state != ((WorkInfo)object0).state) {
            return false;
        }
        if(!Intrinsics.areEqual(this.outputData, ((WorkInfo)object0).outputData)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.constraints, ((WorkInfo)object0).constraints)) {
            return false;
        }
        if(this.initialDelayMillis != ((WorkInfo)object0).initialDelayMillis) {
            return false;
        }
        if(!Intrinsics.areEqual(this.periodicityInfo, ((WorkInfo)object0).periodicityInfo)) {
            return false;
        }
        if(this.nextScheduleTimeMillis != ((WorkInfo)object0).nextScheduleTimeMillis) {
            return false;
        }
        if(this.stopReason != ((WorkInfo)object0).stopReason) {
            return false;
        }
        return Intrinsics.areEqual(this.tags, ((WorkInfo)object0).tags) ? Intrinsics.areEqual(this.progress, ((WorkInfo)object0).progress) : false;
    }

    public final Constraints getConstraints() {
        return this.constraints;
    }

    public final int getGeneration() {
        return this.generation;
    }

    public final UUID getId() {
        return this.id;
    }

    public final long getInitialDelayMillis() {
        return this.initialDelayMillis;
    }

    public final long getNextScheduleTimeMillis() {
        return this.nextScheduleTimeMillis;
    }

    public final Data getOutputData() {
        return this.outputData;
    }

    public final PeriodicityInfo getPeriodicityInfo() {
        return this.periodicityInfo;
    }

    public final Data getProgress() {
        return this.progress;
    }

    public final int getRunAttemptCount() {
        return this.runAttemptCount;
    }

    public final State getState() {
        return this.state;
    }

    public final int getStopReason() {
        return this.stopReason;
    }

    public final Set getTags() {
        return this.tags;
    }

    @Override
    public int hashCode() {
        int v = ((((((((this.id.hashCode() * 0x1F + this.state.hashCode()) * 0x1F + this.outputData.hashCode()) * 0x1F + this.tags.hashCode()) * 0x1F + this.progress.hashCode()) * 0x1F + this.runAttemptCount) * 0x1F + this.generation) * 0x1F + this.constraints.hashCode()) * 0x1F + ((int)(this.initialDelayMillis ^ this.initialDelayMillis >>> 0x20))) * 0x1F;
        return this.periodicityInfo == null ? (v * 0x1F + ((int)(this.nextScheduleTimeMillis ^ this.nextScheduleTimeMillis >>> 0x20))) * 0x1F + this.stopReason : ((v + this.periodicityInfo.hashCode()) * 0x1F + ((int)(this.nextScheduleTimeMillis ^ this.nextScheduleTimeMillis >>> 0x20))) * 0x1F + this.stopReason;
    }

    @Override
    public String toString() {
        return "WorkInfo{id=\'" + this.id + "\', state=" + this.state + ", outputData=" + this.outputData + ", tags=" + this.tags + ", progress=" + this.progress + ", runAttemptCount=" + this.runAttemptCount + ", generation=" + this.generation + ", constraints=" + this.constraints + ", initialDelayMillis=" + this.initialDelayMillis + ", periodicityInfo=" + this.periodicityInfo + ", nextScheduleTimeMillis=" + this.nextScheduleTimeMillis + "}, stopReason=" + this.stopReason;
    }
}

