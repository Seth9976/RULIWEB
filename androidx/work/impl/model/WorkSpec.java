package androidx.work.impl.model;

import androidx.arch.core.util.Function;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.Logger;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo.PeriodicityInfo;
import androidx.work.WorkInfo.State;
import androidx.work.WorkInfo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.UByte..ExternalSyntheticBackport0;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b8\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0087\b\u0018\u0000 ]2\u00020\u0001:\u0003]^_B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u00A2\u0006\u0002\u0010\u0005B\u0017\b\u0016\u0012\u0006\u0010\u0006\u001A\u00020\u0003\u0012\u0006\u0010\u0007\u001A\u00020\u0000\u00A2\u0006\u0002\u0010\bB\u00F3\u0001\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\b\u0002\u0010\t\u001A\u00020\n\u0012\u0006\u0010\u000B\u001A\u00020\u0003\u0012\b\b\u0002\u0010\f\u001A\u00020\u0003\u0012\b\b\u0002\u0010\r\u001A\u00020\u000E\u0012\b\b\u0002\u0010\u000F\u001A\u00020\u000E\u0012\b\b\u0002\u0010\u0010\u001A\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001A\u00020\u0011\u0012\b\b\u0002\u0010\u0013\u001A\u00020\u0011\u0012\b\b\u0002\u0010\u0014\u001A\u00020\u0015\u0012\b\b\u0003\u0010\u0016\u001A\u00020\u0017\u0012\b\b\u0002\u0010\u0018\u001A\u00020\u0019\u0012\b\b\u0002\u0010\u001A\u001A\u00020\u0011\u0012\b\b\u0002\u0010\u001B\u001A\u00020\u0011\u0012\b\b\u0002\u0010\u001C\u001A\u00020\u0011\u0012\b\b\u0002\u0010\u001D\u001A\u00020\u0011\u0012\b\b\u0002\u0010\u001E\u001A\u00020\u001F\u0012\b\b\u0002\u0010 \u001A\u00020!\u0012\b\b\u0002\u0010\"\u001A\u00020\u0017\u0012\b\b\u0002\u0010#\u001A\u00020\u0017\u0012\b\b\u0002\u0010$\u001A\u00020\u0011\u0012\b\b\u0002\u0010%\u001A\u00020\u0017\u0012\b\b\u0002\u0010&\u001A\u00020\u0017\u0012\n\b\u0002\u0010\'\u001A\u0004\u0018\u00010\u0003\u00A2\u0006\u0002\u0010(J\u0006\u0010<\u001A\u00020\u0011J\t\u0010=\u001A\u00020\u0003H\u00C6\u0003J\t\u0010>\u001A\u00020\u0015H\u00C6\u0003J\t\u0010?\u001A\u00020\u0017H\u00C6\u0003J\t\u0010@\u001A\u00020\u0019H\u00C6\u0003J\t\u0010A\u001A\u00020\u0011H\u00C6\u0003J\t\u0010B\u001A\u00020\u0011H\u00C6\u0003J\t\u0010C\u001A\u00020\u0011H\u00C6\u0003J\t\u0010D\u001A\u00020\u0011H\u00C6\u0003J\t\u0010E\u001A\u00020\u001FH\u00C6\u0003J\t\u0010F\u001A\u00020!H\u00C6\u0003J\t\u0010G\u001A\u00020\u0017H\u00C6\u0003J\t\u0010H\u001A\u00020\nH\u00C6\u0003J\t\u0010I\u001A\u00020\u0017H\u00C6\u0003J\t\u0010J\u001A\u00020\u0011H\u00C6\u0003J\t\u0010K\u001A\u00020\u0017H\u00C6\u0003J\t\u0010L\u001A\u00020\u0017H\u00C6\u0003J\u000B\u0010M\u001A\u0004\u0018\u00010\u0003H\u00C6\u0003J\t\u0010N\u001A\u00020\u0003H\u00C6\u0003J\t\u0010O\u001A\u00020\u0003H\u00C6\u0003J\t\u0010P\u001A\u00020\u000EH\u00C6\u0003J\t\u0010Q\u001A\u00020\u000EH\u00C6\u0003J\t\u0010R\u001A\u00020\u0011H\u00C6\u0003J\t\u0010S\u001A\u00020\u0011H\u00C6\u0003J\t\u0010T\u001A\u00020\u0011H\u00C6\u0003J\u00FB\u0001\u0010U\u001A\u00020\u00002\b\b\u0002\u0010\u0002\u001A\u00020\u00032\b\b\u0002\u0010\t\u001A\u00020\n2\b\b\u0002\u0010\u000B\u001A\u00020\u00032\b\b\u0002\u0010\f\u001A\u00020\u00032\b\b\u0002\u0010\r\u001A\u00020\u000E2\b\b\u0002\u0010\u000F\u001A\u00020\u000E2\b\b\u0002\u0010\u0010\u001A\u00020\u00112\b\b\u0002\u0010\u0012\u001A\u00020\u00112\b\b\u0002\u0010\u0013\u001A\u00020\u00112\b\b\u0002\u0010\u0014\u001A\u00020\u00152\b\b\u0003\u0010\u0016\u001A\u00020\u00172\b\b\u0002\u0010\u0018\u001A\u00020\u00192\b\b\u0002\u0010\u001A\u001A\u00020\u00112\b\b\u0002\u0010\u001B\u001A\u00020\u00112\b\b\u0002\u0010\u001C\u001A\u00020\u00112\b\b\u0002\u0010\u001D\u001A\u00020\u00112\b\b\u0002\u0010\u001E\u001A\u00020\u001F2\b\b\u0002\u0010 \u001A\u00020!2\b\b\u0002\u0010\"\u001A\u00020\u00172\b\b\u0002\u0010#\u001A\u00020\u00172\b\b\u0002\u0010$\u001A\u00020\u00112\b\b\u0002\u0010%\u001A\u00020\u00172\b\b\u0002\u0010&\u001A\u00020\u00172\n\b\u0002\u0010\'\u001A\u0004\u0018\u00010\u0003H\u00C6\u0001J\u0013\u0010V\u001A\u00020\u001F2\b\u0010\u0007\u001A\u0004\u0018\u00010\u0001H\u00D6\u0003J\u0006\u0010W\u001A\u00020\u001FJ\t\u0010X\u001A\u00020\u0017H\u00D6\u0001J\u000E\u0010Y\u001A\u00020Z2\u0006\u0010\u001A\u001A\u00020\u0011J\u000E\u0010[\u001A\u00020Z2\u0006\u0010\u0012\u001A\u00020\u0011J\u0016\u0010[\u001A\u00020Z2\u0006\u0010\u0012\u001A\u00020\u00112\u0006\u0010\u0013\u001A\u00020\u0011J\b\u0010\\\u001A\u00020\u0003H\u0016R\u0012\u0010\u001A\u001A\u00020\u00118\u0006@\u0006X\u0087\u000E\u00A2\u0006\u0002\n\u0000R\u0012\u0010\u0018\u001A\u00020\u00198\u0006@\u0006X\u0087\u000E\u00A2\u0006\u0002\n\u0000R\u0012\u0010\u0014\u001A\u00020\u00158\u0006@\u0006X\u0087\u000E\u00A2\u0006\u0002\n\u0000R\u0012\u0010\u001E\u001A\u00020\u001F8\u0006@\u0006X\u0087\u000E\u00A2\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001A\u00020\u00118\u0006@\u0006X\u0087\u000E\u00A2\u0006\u0002\n\u0000R\u0016\u0010#\u001A\u00020\u00178\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b)\u0010*R\u0010\u0010\u0002\u001A\u00020\u00038\u0006X\u0087\u0004\u00A2\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001A\u00020\u00118\u0006@\u0006X\u0087\u000E\u00A2\u0006\u0002\n\u0000R\u0012\u0010\r\u001A\u00020\u000E8\u0006@\u0006X\u0087\u000E\u00A2\u0006\u0002\n\u0000R\u0012\u0010\f\u001A\u00020\u00038\u0006@\u0006X\u0087\u000E\u00A2\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001A\u00020\u00118\u0006@\u0006X\u0087\u000E\u00A2\u0006\u0002\n\u0000R\u0011\u0010+\u001A\u00020\u001F8F\u00A2\u0006\u0006\u001A\u0004\b+\u0010,R\u0011\u0010-\u001A\u00020\u001F8F\u00A2\u0006\u0006\u001A\u0004\b-\u0010,R\u0012\u0010\u001B\u001A\u00020\u00118\u0006@\u0006X\u0087\u000E\u00A2\u0006\u0002\n\u0000R\u0012\u0010\u001C\u001A\u00020\u00118\u0006@\u0006X\u0087\u000E\u00A2\u0006\u0002\n\u0000R\u001E\u0010$\u001A\u00020\u00118\u0006@\u0006X\u0087\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b.\u0010/\"\u0004\b0\u00101R\u001E\u0010%\u001A\u00020\u00178\u0006@\u0006X\u0087\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b2\u0010*\"\u0004\b3\u00104R\u0012\u0010 \u001A\u00020!8\u0006@\u0006X\u0087\u000E\u00A2\u0006\u0002\n\u0000R\u0012\u0010\u000F\u001A\u00020\u000E8\u0006@\u0006X\u0087\u000E\u00A2\u0006\u0002\n\u0000R\u001E\u0010\"\u001A\u00020\u00178\u0006@\u0006X\u0087\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b5\u0010*\"\u0004\b6\u00104R\u0012\u0010\u0016\u001A\u00020\u00178\u0006@\u0006X\u0087\u000E\u00A2\u0006\u0002\n\u0000R\u0012\u0010\u001D\u001A\u00020\u00118\u0006@\u0006X\u0087\u000E\u00A2\u0006\u0002\n\u0000R\u0012\u0010\t\u001A\u00020\n8\u0006@\u0006X\u0087\u000E\u00A2\u0006\u0002\n\u0000R\u0016\u0010&\u001A\u00020\u00178\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b7\u0010*R \u0010\'\u001A\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b8\u00109\"\u0004\b:\u0010;R\u0012\u0010\u000B\u001A\u00020\u00038\u0006@\u0006X\u0087\u000E\u00A2\u0006\u0002\n\u0000\u00A8\u0006`"}, d2 = {"Landroidx/work/impl/model/WorkSpec;", "", "id", "", "workerClassName_", "(Ljava/lang/String;Ljava/lang/String;)V", "newId", "other", "(Ljava/lang/String;Landroidx/work/impl/model/WorkSpec;)V", "state", "Landroidx/work/WorkInfo$State;", "workerClassName", "inputMergerClassName", "input", "Landroidx/work/Data;", "output", "initialDelay", "", "intervalDuration", "flexDuration", "constraints", "Landroidx/work/Constraints;", "runAttemptCount", "", "backoffPolicy", "Landroidx/work/BackoffPolicy;", "backoffDelayDuration", "lastEnqueueTime", "minimumRetentionDuration", "scheduleRequestedAt", "expedited", "", "outOfQuotaPolicy", "Landroidx/work/OutOfQuotaPolicy;", "periodCount", "generation", "nextScheduleTimeOverride", "nextScheduleTimeOverrideGeneration", "stopReason", "traceTag", "(Ljava/lang/String;Landroidx/work/WorkInfo$State;Ljava/lang/String;Ljava/lang/String;Landroidx/work/Data;Landroidx/work/Data;JJJLandroidx/work/Constraints;ILandroidx/work/BackoffPolicy;JJJJZLandroidx/work/OutOfQuotaPolicy;IIJIILjava/lang/String;)V", "getGeneration", "()I", "isBackedOff", "()Z", "isPeriodic", "getNextScheduleTimeOverride", "()J", "setNextScheduleTimeOverride", "(J)V", "getNextScheduleTimeOverrideGeneration", "setNextScheduleTimeOverrideGeneration", "(I)V", "getPeriodCount", "setPeriodCount", "getStopReason", "getTraceTag", "()Ljava/lang/String;", "setTraceTag", "(Ljava/lang/String;)V", "calculateNextRunTime", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "hasConstraints", "hashCode", "setBackoffDelayDuration", "", "setPeriodic", "toString", "Companion", "IdAndState", "WorkInfoPojo", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class WorkSpec {
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J^\u0010\f\u001A\u00020\u00042\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00020\u00042\u0006\u0010\u0014\u001A\u00020\u00042\u0006\u0010\u0015\u001A\u00020\u00102\u0006\u0010\u0016\u001A\u00020\u000E2\u0006\u0010\u0017\u001A\u00020\u00042\u0006\u0010\u0018\u001A\u00020\u00042\u0006\u0010\u0019\u001A\u00020\u00042\u0006\u0010\u001A\u001A\u00020\u0004R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\u0007\u001A\u001A\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000B0\t0\b8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u001B"}, d2 = {"Landroidx/work/impl/model/WorkSpec$Companion;", "", "()V", "SCHEDULE_NOT_REQUESTED_YET", "", "TAG", "", "WORK_INFO_MAPPER", "Landroidx/arch/core/util/Function;", "", "Landroidx/work/impl/model/WorkSpec$WorkInfoPojo;", "Landroidx/work/WorkInfo;", "calculateNextRunTime", "isBackedOff", "", "runAttemptCount", "", "backoffPolicy", "Landroidx/work/BackoffPolicy;", "backoffDelayDuration", "lastEnqueueTime", "periodCount", "isPeriodic", "initialDelay", "flexDuration", "intervalDuration", "nextScheduleTimeOverride", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final long calculateNextRunTime(boolean z, int v, BackoffPolicy backoffPolicy0, long v1, long v2, int v3, boolean z1, long v4, long v5, long v6, long v7) {
            Intrinsics.checkNotNullParameter(backoffPolicy0, "backoffPolicy");
            if(v7 != 0x7FFFFFFFFFFFFFFFL && z1) {
                return v3 == 0 ? v7 : RangesKt.coerceAtLeast(v7, v2 + 900000L);
            }
            if(z) {
                return backoffPolicy0 == BackoffPolicy.LINEAR ? RangesKt.coerceAtMost(v1 * ((long)v), 18000000L) + v2 : RangesKt.coerceAtMost(((long)Math.scalb(v1, v - 1)), 18000000L) + v2;
            }
            if(z1) {
                long v8 = v3 == 0 ? v2 + v4 : v2 + v6;
                return v5 == v6 || v3 != 0 ? v8 : v8 + (v6 - v5);
            }
            return v2 == -1L ? 0x7FFFFFFFFFFFFFFFL : v2 + v4;
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u0007\u001A\u00020\u0003HÆ\u0003J\t\u0010\b\u001A\u00020\u0005HÆ\u0003J\u001D\u0010\t\u001A\u00020\u00002\b\b\u0002\u0010\u0002\u001A\u00020\u00032\b\b\u0002\u0010\u0004\u001A\u00020\u0005HÆ\u0001J\u0013\u0010\n\u001A\u00020\u000B2\b\u0010\f\u001A\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001A\u00020\u000EHÖ\u0001J\t\u0010\u000F\u001A\u00020\u0003HÖ\u0001R\u0012\u0010\u0002\u001A\u00020\u00038\u0006@\u0006X\u0087\u000E¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001A\u00020\u00058\u0006@\u0006X\u0087\u000E¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/work/impl/model/WorkSpec$IdAndState;", "", "id", "", "state", "Landroidx/work/WorkInfo$State;", "(Ljava/lang/String;Landroidx/work/WorkInfo$State;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class IdAndState {
        public String id;
        public State state;

        public IdAndState(String s, State workInfo$State0) {
            Intrinsics.checkNotNullParameter(s, "id");
            Intrinsics.checkNotNullParameter(workInfo$State0, "state");
            super();
            this.id = s;
            this.state = workInfo$State0;
        }

        public final String component1() {
            return this.id;
        }

        public final State component2() {
            return this.state;
        }

        public final IdAndState copy(String s, State workInfo$State0) {
            Intrinsics.checkNotNullParameter(s, "id");
            Intrinsics.checkNotNullParameter(workInfo$State0, "state");
            return new IdAndState(s, workInfo$State0);
        }

        public static IdAndState copy$default(IdAndState workSpec$IdAndState0, String s, State workInfo$State0, int v, Object object0) {
            if((v & 1) != 0) {
                s = workSpec$IdAndState0.id;
            }
            if((v & 2) != 0) {
                workInfo$State0 = workSpec$IdAndState0.state;
            }
            return workSpec$IdAndState0.copy(s, workInfo$State0);
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            if(!(object0 instanceof IdAndState)) {
                return false;
            }
            return Intrinsics.areEqual(this.id, ((IdAndState)object0).id) ? this.state == ((IdAndState)object0).state : false;
        }

        @Override
        public int hashCode() {
            return this.id.hashCode() * 0x1F + this.state.hashCode();
        }

        @Override
        public String toString() {
            return "IdAndState(id=" + this.id + ", state=" + this.state + ')';
        }
    }

    @Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0014\n\u0002\u0010\u000B\n\u0002\b\'\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u00A7\u0001\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\b\b\u0002\u0010\b\u001A\u00020\t\u0012\b\b\u0002\u0010\n\u001A\u00020\t\u0012\b\b\u0002\u0010\u000B\u001A\u00020\t\u0012\u0006\u0010\f\u001A\u00020\r\u0012\u0006\u0010\u000E\u001A\u00020\u000F\u0012\b\b\u0002\u0010\u0010\u001A\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001A\u00020\t\u0012\b\b\u0002\u0010\u0013\u001A\u00020\t\u0012\b\b\u0002\u0010\u0014\u001A\u00020\u000F\u0012\u0006\u0010\u0015\u001A\u00020\u000F\u0012\u0006\u0010\u0016\u001A\u00020\t\u0012\u0006\u0010\u0017\u001A\u00020\u000F\u0012\f\u0010\u0018\u001A\b\u0012\u0004\u0012\u00020\u00030\u0019\u0012\f\u0010\u001A\u001A\b\u0012\u0004\u0012\u00020\u00070\u0019\u00A2\u0006\u0002\u0010\u001BJ\b\u0010@\u001A\u00020\tH\u0002J\t\u0010A\u001A\u00020\u0003H\u00C6\u0003J\t\u0010B\u001A\u00020\tH\u00C6\u0003J\t\u0010C\u001A\u00020\tH\u00C6\u0003J\t\u0010D\u001A\u00020\u000FH\u00C6\u0003J\t\u0010E\u001A\u00020\u000FH\u00C6\u0003J\t\u0010F\u001A\u00020\tH\u00C6\u0003J\t\u0010G\u001A\u00020\u000FH\u00C6\u0003J\u000F\u0010H\u001A\b\u0012\u0004\u0012\u00020\u00030\u0019H\u00C6\u0003J\u000F\u0010I\u001A\b\u0012\u0004\u0012\u00020\u00070\u0019H\u00C6\u0003J\t\u0010J\u001A\u00020\u0005H\u00C6\u0003J\t\u0010K\u001A\u00020\u0007H\u00C6\u0003J\t\u0010L\u001A\u00020\tH\u00C6\u0003J\t\u0010M\u001A\u00020\tH\u00C6\u0003J\t\u0010N\u001A\u00020\tH\u00C6\u0003J\t\u0010O\u001A\u00020\rH\u00C6\u0003J\t\u0010P\u001A\u00020\u000FH\u00C6\u0003J\t\u0010Q\u001A\u00020\u0011H\u00C6\u0003J\u00BF\u0001\u0010R\u001A\u00020\u00002\b\b\u0002\u0010\u0002\u001A\u00020\u00032\b\b\u0002\u0010\u0004\u001A\u00020\u00052\b\b\u0002\u0010\u0006\u001A\u00020\u00072\b\b\u0002\u0010\b\u001A\u00020\t2\b\b\u0002\u0010\n\u001A\u00020\t2\b\b\u0002\u0010\u000B\u001A\u00020\t2\b\b\u0002\u0010\f\u001A\u00020\r2\b\b\u0002\u0010\u000E\u001A\u00020\u000F2\b\b\u0002\u0010\u0010\u001A\u00020\u00112\b\b\u0002\u0010\u0012\u001A\u00020\t2\b\b\u0002\u0010\u0013\u001A\u00020\t2\b\b\u0002\u0010\u0014\u001A\u00020\u000F2\b\b\u0002\u0010\u0015\u001A\u00020\u000F2\b\b\u0002\u0010\u0016\u001A\u00020\t2\b\b\u0002\u0010\u0017\u001A\u00020\u000F2\u000E\b\u0002\u0010\u0018\u001A\b\u0012\u0004\u0012\u00020\u00030\u00192\u000E\b\u0002\u0010\u001A\u001A\b\u0012\u0004\u0012\u00020\u00070\u0019H\u00C6\u0001J\u0013\u0010S\u001A\u00020.2\b\u0010T\u001A\u0004\u0018\u00010\u0001H\u00D6\u0003J\n\u0010U\u001A\u0004\u0018\u00010VH\u0002J\t\u0010W\u001A\u00020\u000FH\u00D6\u0001J\t\u0010X\u001A\u00020\u0003H\u00D6\u0001J\u0006\u0010Y\u001A\u00020ZR\u001E\u0010\u0012\u001A\u00020\t8\u0006@\u0006X\u0087\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u001C\u0010\u001D\"\u0004\b\u001E\u0010\u001FR\u001E\u0010\u0010\u001A\u00020\u00118\u0006@\u0006X\u0087\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b \u0010!\"\u0004\b\"\u0010#R\u0016\u0010\f\u001A\u00020\r8\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b$\u0010%R\u0016\u0010\u000B\u001A\u00020\t8\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b&\u0010\u001DR\u0016\u0010\u0015\u001A\u00020\u000F8\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\'\u0010(R\u0016\u0010\u0002\u001A\u00020\u00038\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b)\u0010*R\u0016\u0010\b\u001A\u00020\t8\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b+\u0010\u001DR\u0016\u0010\n\u001A\u00020\t8\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b,\u0010\u001DR\u0011\u0010-\u001A\u00020.8F\u00A2\u0006\u0006\u001A\u0004\b-\u0010/R\u0011\u00100\u001A\u00020.8F\u00A2\u0006\u0006\u001A\u0004\b0\u0010/R\u001E\u0010\u0013\u001A\u00020\t8\u0006@\u0006X\u0087\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b1\u0010\u001D\"\u0004\b2\u0010\u001FR\u0016\u0010\u0016\u001A\u00020\t8\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b3\u0010\u001DR\u0016\u0010\u0006\u001A\u00020\u00078\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b4\u00105R\u001E\u0010\u0014\u001A\u00020\u000F8\u0006@\u0006X\u0087\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b6\u0010(\"\u0004\b7\u00108R\u001C\u0010\u001A\u001A\b\u0012\u0004\u0012\u00020\u00070\u00198\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b9\u0010:R\u0016\u0010\u000E\u001A\u00020\u000F8\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b;\u0010(R\u0016\u0010\u0004\u001A\u00020\u00058\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b<\u0010=R\u0016\u0010\u0017\u001A\u00020\u000F8\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b>\u0010(R\u001C\u0010\u0018\u001A\b\u0012\u0004\u0012\u00020\u00030\u00198\u0006X\u0087\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b?\u0010:\u00A8\u0006["}, d2 = {"Landroidx/work/impl/model/WorkSpec$WorkInfoPojo;", "", "id", "", "state", "Landroidx/work/WorkInfo$State;", "output", "Landroidx/work/Data;", "initialDelay", "", "intervalDuration", "flexDuration", "constraints", "Landroidx/work/Constraints;", "runAttemptCount", "", "backoffPolicy", "Landroidx/work/BackoffPolicy;", "backoffDelayDuration", "lastEnqueueTime", "periodCount", "generation", "nextScheduleTimeOverride", "stopReason", "tags", "", "progress", "(Ljava/lang/String;Landroidx/work/WorkInfo$State;Landroidx/work/Data;JJJLandroidx/work/Constraints;ILandroidx/work/BackoffPolicy;JJIIJILjava/util/List;Ljava/util/List;)V", "getBackoffDelayDuration", "()J", "setBackoffDelayDuration", "(J)V", "getBackoffPolicy", "()Landroidx/work/BackoffPolicy;", "setBackoffPolicy", "(Landroidx/work/BackoffPolicy;)V", "getConstraints", "()Landroidx/work/Constraints;", "getFlexDuration", "getGeneration", "()I", "getId", "()Ljava/lang/String;", "getInitialDelay", "getIntervalDuration", "isBackedOff", "", "()Z", "isPeriodic", "getLastEnqueueTime", "setLastEnqueueTime", "getNextScheduleTimeOverride", "getOutput", "()Landroidx/work/Data;", "getPeriodCount", "setPeriodCount", "(I)V", "getProgress", "()Ljava/util/List;", "getRunAttemptCount", "getState", "()Landroidx/work/WorkInfo$State;", "getStopReason", "getTags", "calculateNextRunTimeMillis", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "getPeriodicityOrNull", "Landroidx/work/WorkInfo$PeriodicityInfo;", "hashCode", "toString", "toWorkInfo", "Landroidx/work/WorkInfo;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class WorkInfoPojo {
        private long backoffDelayDuration;
        private BackoffPolicy backoffPolicy;
        private final Constraints constraints;
        private final long flexDuration;
        private final int generation;
        private final String id;
        private final long initialDelay;
        private final long intervalDuration;
        private long lastEnqueueTime;
        private final long nextScheduleTimeOverride;
        private final Data output;
        private int periodCount;
        private final List progress;
        private final int runAttemptCount;
        private final State state;
        private final int stopReason;
        private final List tags;

        public WorkInfoPojo(String s, State workInfo$State0, Data data0, long v, long v1, long v2, Constraints constraints0, int v3, BackoffPolicy backoffPolicy0, long v4, long v5, int v6, int v7, long v8, int v9, List list0, List list1) {
            Intrinsics.checkNotNullParameter(s, "id");
            Intrinsics.checkNotNullParameter(workInfo$State0, "state");
            Intrinsics.checkNotNullParameter(data0, "output");
            Intrinsics.checkNotNullParameter(constraints0, "constraints");
            Intrinsics.checkNotNullParameter(backoffPolicy0, "backoffPolicy");
            Intrinsics.checkNotNullParameter(list0, "tags");
            Intrinsics.checkNotNullParameter(list1, "progress");
            super();
            this.id = s;
            this.state = workInfo$State0;
            this.output = data0;
            this.initialDelay = v;
            this.intervalDuration = v1;
            this.flexDuration = v2;
            this.constraints = constraints0;
            this.runAttemptCount = v3;
            this.backoffPolicy = backoffPolicy0;
            this.backoffDelayDuration = v4;
            this.lastEnqueueTime = v5;
            this.periodCount = v6;
            this.generation = v7;
            this.nextScheduleTimeOverride = v8;
            this.stopReason = v9;
            this.tags = list0;
            this.progress = list1;
        }

        public WorkInfoPojo(String s, State workInfo$State0, Data data0, long v, long v1, long v2, Constraints constraints0, int v3, BackoffPolicy backoffPolicy0, long v4, long v5, int v6, int v7, long v8, int v9, List list0, List list1, int v10, DefaultConstructorMarker defaultConstructorMarker0) {
            this(s, workInfo$State0, data0, ((v10 & 8) == 0 ? v : 0L), ((v10 & 16) == 0 ? v1 : 0L), ((v10 & 0x20) == 0 ? v2 : 0L), constraints0, v3, ((v10 & 0x100) == 0 ? backoffPolicy0 : BackoffPolicy.EXPONENTIAL), ((v10 & 0x200) == 0 ? v4 : 30000L), ((v10 & 0x400) == 0 ? v5 : 0L), ((v10 & 0x800) == 0 ? v6 : 0), v7, v8, v9, list0, list1);
        }

        private final long calculateNextRunTimeMillis() {
            if(this.state == State.ENQUEUED) {
                boolean z = this.isBackedOff();
                BackoffPolicy backoffPolicy0 = this.backoffPolicy;
                long v = this.backoffDelayDuration;
                long v1 = this.lastEnqueueTime;
                int v2 = this.periodCount;
                boolean z1 = this.isPeriodic();
                return WorkSpec.Companion.calculateNextRunTime(z, this.runAttemptCount, backoffPolicy0, v, v1, v2, z1, this.initialDelay, this.flexDuration, this.intervalDuration, this.nextScheduleTimeOverride);
            }
            return 0x7FFFFFFFFFFFFFFFL;
        }

        public final String component1() {
            return this.id;
        }

        public final long component10() {
            return this.backoffDelayDuration;
        }

        public final long component11() {
            return this.lastEnqueueTime;
        }

        public final int component12() {
            return this.periodCount;
        }

        public final int component13() {
            return this.generation;
        }

        public final long component14() {
            return this.nextScheduleTimeOverride;
        }

        public final int component15() {
            return this.stopReason;
        }

        public final List component16() {
            return this.tags;
        }

        public final List component17() {
            return this.progress;
        }

        public final State component2() {
            return this.state;
        }

        public final Data component3() {
            return this.output;
        }

        public final long component4() {
            return this.initialDelay;
        }

        public final long component5() {
            return this.intervalDuration;
        }

        public final long component6() {
            return this.flexDuration;
        }

        public final Constraints component7() {
            return this.constraints;
        }

        public final int component8() {
            return this.runAttemptCount;
        }

        public final BackoffPolicy component9() {
            return this.backoffPolicy;
        }

        public final WorkInfoPojo copy(String s, State workInfo$State0, Data data0, long v, long v1, long v2, Constraints constraints0, int v3, BackoffPolicy backoffPolicy0, long v4, long v5, int v6, int v7, long v8, int v9, List list0, List list1) {
            Intrinsics.checkNotNullParameter(s, "id");
            Intrinsics.checkNotNullParameter(workInfo$State0, "state");
            Intrinsics.checkNotNullParameter(data0, "output");
            Intrinsics.checkNotNullParameter(constraints0, "constraints");
            Intrinsics.checkNotNullParameter(backoffPolicy0, "backoffPolicy");
            Intrinsics.checkNotNullParameter(list0, "tags");
            Intrinsics.checkNotNullParameter(list1, "progress");
            return new WorkInfoPojo(s, workInfo$State0, data0, v, v1, v2, constraints0, v3, backoffPolicy0, v4, v5, v6, v7, v8, v9, list0, list1);
        }

        public static WorkInfoPojo copy$default(WorkInfoPojo workSpec$WorkInfoPojo0, String s, State workInfo$State0, Data data0, long v, long v1, long v2, Constraints constraints0, int v3, BackoffPolicy backoffPolicy0, long v4, long v5, int v6, int v7, long v8, int v9, List list0, List list1, int v10, Object object0) {
            String s1 = (v10 & 1) == 0 ? s : workSpec$WorkInfoPojo0.id;
            State workInfo$State1 = (v10 & 2) == 0 ? workInfo$State0 : workSpec$WorkInfoPojo0.state;
            Data data1 = (v10 & 4) == 0 ? data0 : workSpec$WorkInfoPojo0.output;
            long v11 = (v10 & 8) == 0 ? v : workSpec$WorkInfoPojo0.initialDelay;
            long v12 = (v10 & 16) == 0 ? v1 : workSpec$WorkInfoPojo0.intervalDuration;
            long v13 = (v10 & 0x20) == 0 ? v2 : workSpec$WorkInfoPojo0.flexDuration;
            Constraints constraints1 = (v10 & 0x40) == 0 ? constraints0 : workSpec$WorkInfoPojo0.constraints;
            int v14 = (v10 & 0x80) == 0 ? v3 : workSpec$WorkInfoPojo0.runAttemptCount;
            BackoffPolicy backoffPolicy1 = (v10 & 0x100) == 0 ? backoffPolicy0 : workSpec$WorkInfoPojo0.backoffPolicy;
            long v15 = (v10 & 0x200) == 0 ? v4 : workSpec$WorkInfoPojo0.backoffDelayDuration;
            long v16 = (v10 & 0x400) == 0 ? v5 : workSpec$WorkInfoPojo0.lastEnqueueTime;
            int v17 = (v10 & 0x800) == 0 ? v6 : workSpec$WorkInfoPojo0.periodCount;
            int v18 = (v10 & 0x1000) == 0 ? v7 : workSpec$WorkInfoPojo0.generation;
            long v19 = (v10 & 0x2000) == 0 ? v8 : workSpec$WorkInfoPojo0.nextScheduleTimeOverride;
            int v20 = (v10 & 0x4000) == 0 ? v9 : workSpec$WorkInfoPojo0.stopReason;
            List list2 = (0x8000 & v10) == 0 ? list0 : workSpec$WorkInfoPojo0.tags;
            return (v10 & 0x10000) == 0 ? workSpec$WorkInfoPojo0.copy(s1, workInfo$State1, data1, v11, v12, v13, constraints1, v14, backoffPolicy1, v15, v16, v17, v18, v19, v20, list2, list1) : workSpec$WorkInfoPojo0.copy(s1, workInfo$State1, data1, v11, v12, v13, constraints1, v14, backoffPolicy1, v15, v16, v17, v18, v19, v20, list2, workSpec$WorkInfoPojo0.progress);
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            if(!(object0 instanceof WorkInfoPojo)) {
                return false;
            }
            if(!Intrinsics.areEqual(this.id, ((WorkInfoPojo)object0).id)) {
                return false;
            }
            if(this.state != ((WorkInfoPojo)object0).state) {
                return false;
            }
            if(!Intrinsics.areEqual(this.output, ((WorkInfoPojo)object0).output)) {
                return false;
            }
            if(this.initialDelay != ((WorkInfoPojo)object0).initialDelay) {
                return false;
            }
            if(this.intervalDuration != ((WorkInfoPojo)object0).intervalDuration) {
                return false;
            }
            if(this.flexDuration != ((WorkInfoPojo)object0).flexDuration) {
                return false;
            }
            if(!Intrinsics.areEqual(this.constraints, ((WorkInfoPojo)object0).constraints)) {
                return false;
            }
            if(this.runAttemptCount != ((WorkInfoPojo)object0).runAttemptCount) {
                return false;
            }
            if(this.backoffPolicy != ((WorkInfoPojo)object0).backoffPolicy) {
                return false;
            }
            if(this.backoffDelayDuration != ((WorkInfoPojo)object0).backoffDelayDuration) {
                return false;
            }
            if(this.lastEnqueueTime != ((WorkInfoPojo)object0).lastEnqueueTime) {
                return false;
            }
            if(this.periodCount != ((WorkInfoPojo)object0).periodCount) {
                return false;
            }
            if(this.generation != ((WorkInfoPojo)object0).generation) {
                return false;
            }
            if(this.nextScheduleTimeOverride != ((WorkInfoPojo)object0).nextScheduleTimeOverride) {
                return false;
            }
            if(this.stopReason != ((WorkInfoPojo)object0).stopReason) {
                return false;
            }
            return Intrinsics.areEqual(this.tags, ((WorkInfoPojo)object0).tags) ? Intrinsics.areEqual(this.progress, ((WorkInfoPojo)object0).progress) : false;
        }

        public final long getBackoffDelayDuration() {
            return this.backoffDelayDuration;
        }

        public final BackoffPolicy getBackoffPolicy() {
            return this.backoffPolicy;
        }

        public final Constraints getConstraints() {
            return this.constraints;
        }

        public final long getFlexDuration() {
            return this.flexDuration;
        }

        public final int getGeneration() {
            return this.generation;
        }

        public final String getId() {
            return this.id;
        }

        public final long getInitialDelay() {
            return this.initialDelay;
        }

        public final long getIntervalDuration() {
            return this.intervalDuration;
        }

        public final long getLastEnqueueTime() {
            return this.lastEnqueueTime;
        }

        public final long getNextScheduleTimeOverride() {
            return this.nextScheduleTimeOverride;
        }

        public final Data getOutput() {
            return this.output;
        }

        public final int getPeriodCount() {
            return this.periodCount;
        }

        private final PeriodicityInfo getPeriodicityOrNull() {
            return this.intervalDuration == 0L ? null : new PeriodicityInfo(this.intervalDuration, this.flexDuration);
        }

        public final List getProgress() {
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

        public final List getTags() {
            return this.tags;
        }

        @Override
        public int hashCode() {
            return (((((((((((((((this.id.hashCode() * 0x1F + this.state.hashCode()) * 0x1F + this.output.hashCode()) * 0x1F + ((int)(this.initialDelay ^ this.initialDelay >>> 0x20))) * 0x1F + ((int)(this.intervalDuration ^ this.intervalDuration >>> 0x20))) * 0x1F + ((int)(this.flexDuration ^ this.flexDuration >>> 0x20))) * 0x1F + this.constraints.hashCode()) * 0x1F + this.runAttemptCount) * 0x1F + this.backoffPolicy.hashCode()) * 0x1F + ((int)(this.backoffDelayDuration ^ this.backoffDelayDuration >>> 0x20))) * 0x1F + ((int)(this.lastEnqueueTime ^ this.lastEnqueueTime >>> 0x20))) * 0x1F + this.periodCount) * 0x1F + this.generation) * 0x1F + ((int)(this.nextScheduleTimeOverride ^ this.nextScheduleTimeOverride >>> 0x20))) * 0x1F + this.stopReason) * 0x1F + this.tags.hashCode()) * 0x1F + this.progress.hashCode();
        }

        public final boolean isBackedOff() {
            return this.state == State.ENQUEUED && this.runAttemptCount > 0;
        }

        public final boolean isPeriodic() {
            return this.intervalDuration != 0L;
        }

        public final void setBackoffDelayDuration(long v) {
            this.backoffDelayDuration = v;
        }

        public final void setBackoffPolicy(BackoffPolicy backoffPolicy0) {
            Intrinsics.checkNotNullParameter(backoffPolicy0, "<set-?>");
            this.backoffPolicy = backoffPolicy0;
        }

        public final void setLastEnqueueTime(long v) {
            this.lastEnqueueTime = v;
        }

        public final void setPeriodCount(int v) {
            this.periodCount = v;
        }

        @Override
        public String toString() {
            return "WorkInfoPojo(id=" + this.id + ", state=" + this.state + ", output=" + this.output + ", initialDelay=" + this.initialDelay + ", intervalDuration=" + this.intervalDuration + ", flexDuration=" + this.flexDuration + ", constraints=" + this.constraints + ", runAttemptCount=" + this.runAttemptCount + ", backoffPolicy=" + this.backoffPolicy + ", backoffDelayDuration=" + this.backoffDelayDuration + ", lastEnqueueTime=" + this.lastEnqueueTime + ", periodCount=" + this.periodCount + ", generation=" + this.generation + ", nextScheduleTimeOverride=" + this.nextScheduleTimeOverride + ", stopReason=" + this.stopReason + ", tags=" + this.tags + ", progress=" + this.progress + ')';
        }

        public final WorkInfo toWorkInfo() {
            Data data0 = this.progress.isEmpty() ? Data.EMPTY : ((Data)this.progress.get(0));
            UUID uUID0 = UUID.fromString(this.id);
            Intrinsics.checkNotNullExpressionValue(uUID0, "fromString(id)");
            HashSet hashSet0 = new HashSet(this.tags);
            PeriodicityInfo workInfo$PeriodicityInfo0 = this.getPeriodicityOrNull();
            long v = this.calculateNextRunTimeMillis();
            return new WorkInfo(uUID0, this.state, hashSet0, this.output, data0, this.runAttemptCount, this.generation, this.constraints, this.initialDelay, workInfo$PeriodicityInfo0, v, this.stopReason);
        }
    }

    public static final Companion Companion = null;
    public static final long SCHEDULE_NOT_REQUESTED_YET = -1L;
    private static final String TAG;
    public static final Function WORK_INFO_MAPPER;
    public long backoffDelayDuration;
    public BackoffPolicy backoffPolicy;
    public Constraints constraints;
    public boolean expedited;
    public long flexDuration;
    private final int generation;
    public final String id;
    public long initialDelay;
    public Data input;
    public String inputMergerClassName;
    public long intervalDuration;
    public long lastEnqueueTime;
    public long minimumRetentionDuration;
    private long nextScheduleTimeOverride;
    private int nextScheduleTimeOverrideGeneration;
    public OutOfQuotaPolicy outOfQuotaPolicy;
    public Data output;
    private int periodCount;
    public int runAttemptCount;
    public long scheduleRequestedAt;
    public State state;
    private final int stopReason;
    private String traceTag;
    public String workerClassName;

    // 检测为 Lambda 实现
    public static List $r8$lambda$kxU3gfKzoZdfGv8GSpkPecoJFFM(List list0) [...]

    static {
        WorkSpec.Companion = new Companion(null);
        Intrinsics.checkNotNullExpressionValue("WM-WorkSpec", "tagWithPrefix(\"WorkSpec\")");
        WorkSpec.TAG = "WM-WorkSpec";
        WorkSpec.WORK_INFO_MAPPER = (List list0) -> WorkSpec.WORK_INFO_MAPPER$lambda$1(list0);
    }

    public WorkSpec(String s, State workInfo$State0, String s1, String s2, Data data0, Data data1, long v, long v1, long v2, Constraints constraints0, int v3, BackoffPolicy backoffPolicy0, long v4, long v5, long v6, long v7, boolean z, OutOfQuotaPolicy outOfQuotaPolicy0, int v8, int v9, long v10, int v11, int v12, String s3) {
        Intrinsics.checkNotNullParameter(s, "id");
        Intrinsics.checkNotNullParameter(workInfo$State0, "state");
        Intrinsics.checkNotNullParameter(s1, "workerClassName");
        Intrinsics.checkNotNullParameter(s2, "inputMergerClassName");
        Intrinsics.checkNotNullParameter(data0, "input");
        Intrinsics.checkNotNullParameter(data1, "output");
        Intrinsics.checkNotNullParameter(constraints0, "constraints");
        Intrinsics.checkNotNullParameter(backoffPolicy0, "backoffPolicy");
        Intrinsics.checkNotNullParameter(outOfQuotaPolicy0, "outOfQuotaPolicy");
        super();
        this.id = s;
        this.state = workInfo$State0;
        this.workerClassName = s1;
        this.inputMergerClassName = s2;
        this.input = data0;
        this.output = data1;
        this.initialDelay = v;
        this.intervalDuration = v1;
        this.flexDuration = v2;
        this.constraints = constraints0;
        this.runAttemptCount = v3;
        this.backoffPolicy = backoffPolicy0;
        this.backoffDelayDuration = v4;
        this.lastEnqueueTime = v5;
        this.minimumRetentionDuration = v6;
        this.scheduleRequestedAt = v7;
        this.expedited = z;
        this.outOfQuotaPolicy = outOfQuotaPolicy0;
        this.periodCount = v8;
        this.generation = v9;
        this.nextScheduleTimeOverride = v10;
        this.nextScheduleTimeOverrideGeneration = v11;
        this.stopReason = v12;
        this.traceTag = s3;
    }

    public WorkSpec(String s, State workInfo$State0, String s1, String s2, Data data0, Data data1, long v, long v1, long v2, Constraints constraints0, int v3, BackoffPolicy backoffPolicy0, long v4, long v5, long v6, long v7, boolean z, OutOfQuotaPolicy outOfQuotaPolicy0, int v8, int v9, long v10, int v11, int v12, String s3, int v13, DefaultConstructorMarker defaultConstructorMarker0) {
        String s4;
        State workInfo$State1 = (v13 & 2) == 0 ? workInfo$State0 : State.ENQUEUED;
        if((v13 & 8) == 0) {
            s4 = s2;
        }
        else {
            Intrinsics.checkNotNullExpressionValue("androidx.work.OverwritingInputMerger", "OverwritingInputMerger::class.java.name");
            s4 = "androidx.work.OverwritingInputMerger";
        }
        Data data2 = (v13 & 16) == 0 ? data0 : Data.EMPTY;
        Data data3 = (v13 & 0x20) == 0 ? data1 : Data.EMPTY;
        long v14 = 0L;
        Constraints constraints1 = (v13 & 0x200) == 0 ? constraints0 : Constraints.NONE;
        BackoffPolicy backoffPolicy1 = (v13 & 0x800) == 0 ? backoffPolicy0 : BackoffPolicy.EXPONENTIAL;
        if((v13 & 0x4000) == 0) {
            v14 = v6;
        }
        this(s, workInfo$State1, s1, s4, data2, data3, ((v13 & 0x40) == 0 ? v : 0L), ((v13 & 0x80) == 0 ? v1 : 0L), ((v13 & 0x100) == 0 ? v2 : 0L), constraints1, ((v13 & 0x400) == 0 ? v3 : 0), backoffPolicy1, ((v13 & 0x1000) == 0 ? v4 : 30000L), ((v13 & 0x2000) == 0 ? v5 : -1L), v14, ((0x8000 & v13) == 0 ? v7 : -1L), ((0x10000 & v13) == 0 ? z : false), ((0x20000 & v13) == 0 ? outOfQuotaPolicy0 : OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST), ((0x40000 & v13) == 0 ? v8 : 0), ((0x80000 & v13) == 0 ? v9 : 0), ((0x100000 & v13) == 0 ? v10 : 0x7FFFFFFFFFFFFFFFL), ((0x200000 & v13) == 0 ? v11 : 0), ((0x400000 & v13) == 0 ? v12 : 0xFFFFFF00), ((v13 & 0x800000) == 0 ? s3 : null));
    }

    public WorkSpec(String s, WorkSpec workSpec0) {
        Intrinsics.checkNotNullParameter(s, "newId");
        Intrinsics.checkNotNullParameter(workSpec0, "other");
        this(s, workSpec0.state, workSpec0.workerClassName, workSpec0.inputMergerClassName, new Data(workSpec0.input), new Data(workSpec0.output), workSpec0.initialDelay, workSpec0.intervalDuration, workSpec0.flexDuration, new Constraints(workSpec0.constraints), workSpec0.runAttemptCount, workSpec0.backoffPolicy, workSpec0.backoffDelayDuration, workSpec0.lastEnqueueTime, workSpec0.minimumRetentionDuration, workSpec0.scheduleRequestedAt, workSpec0.expedited, workSpec0.outOfQuotaPolicy, workSpec0.periodCount, 0, workSpec0.nextScheduleTimeOverride, workSpec0.nextScheduleTimeOverrideGeneration, workSpec0.stopReason, workSpec0.traceTag, 0x80000, null);
    }

    public WorkSpec(String s, String s1) {
        Intrinsics.checkNotNullParameter(s, "id");
        Intrinsics.checkNotNullParameter(s1, "workerClassName_");
        this(s, null, s1, null, null, null, 0L, 0L, 0L, null, 0, null, 0L, 0L, 0L, 0L, false, null, 0, 0, 0L, 0, 0, null, 0xFFFFFA, null);
    }

    private static final List WORK_INFO_MAPPER$lambda$1(List list0) {
        if(list0 != null) {
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
            for(Object object0: list0) {
                arrayList0.add(((WorkInfoPojo)object0).toWorkInfo());
            }
            return arrayList0;
        }
        return null;
    }

    public final long calculateNextRunTime() {
        boolean z = this.isBackedOff();
        int v = this.runAttemptCount;
        BackoffPolicy backoffPolicy0 = this.backoffPolicy;
        long v1 = this.backoffDelayDuration;
        long v2 = this.lastEnqueueTime;
        int v3 = this.periodCount;
        boolean z1 = this.isPeriodic();
        return WorkSpec.Companion.calculateNextRunTime(z, v, backoffPolicy0, v1, v2, v3, z1, this.initialDelay, this.flexDuration, this.intervalDuration, this.nextScheduleTimeOverride);
    }

    public final String component1() {
        return this.id;
    }

    public final Constraints component10() {
        return this.constraints;
    }

    public final int component11() {
        return this.runAttemptCount;
    }

    public final BackoffPolicy component12() {
        return this.backoffPolicy;
    }

    public final long component13() {
        return this.backoffDelayDuration;
    }

    public final long component14() {
        return this.lastEnqueueTime;
    }

    public final long component15() {
        return this.minimumRetentionDuration;
    }

    public final long component16() {
        return this.scheduleRequestedAt;
    }

    public final boolean component17() {
        return this.expedited;
    }

    public final OutOfQuotaPolicy component18() {
        return this.outOfQuotaPolicy;
    }

    public final int component19() {
        return this.periodCount;
    }

    public final State component2() {
        return this.state;
    }

    public final int component20() {
        return this.generation;
    }

    public final long component21() {
        return this.nextScheduleTimeOverride;
    }

    public final int component22() {
        return this.nextScheduleTimeOverrideGeneration;
    }

    public final int component23() {
        return this.stopReason;
    }

    public final String component24() {
        return this.traceTag;
    }

    public final String component3() {
        return this.workerClassName;
    }

    public final String component4() {
        return this.inputMergerClassName;
    }

    public final Data component5() {
        return this.input;
    }

    public final Data component6() {
        return this.output;
    }

    public final long component7() {
        return this.initialDelay;
    }

    public final long component8() {
        return this.intervalDuration;
    }

    public final long component9() {
        return this.flexDuration;
    }

    public final WorkSpec copy(String s, State workInfo$State0, String s1, String s2, Data data0, Data data1, long v, long v1, long v2, Constraints constraints0, int v3, BackoffPolicy backoffPolicy0, long v4, long v5, long v6, long v7, boolean z, OutOfQuotaPolicy outOfQuotaPolicy0, int v8, int v9, long v10, int v11, int v12, String s3) {
        Intrinsics.checkNotNullParameter(s, "id");
        Intrinsics.checkNotNullParameter(workInfo$State0, "state");
        Intrinsics.checkNotNullParameter(s1, "workerClassName");
        Intrinsics.checkNotNullParameter(s2, "inputMergerClassName");
        Intrinsics.checkNotNullParameter(data0, "input");
        Intrinsics.checkNotNullParameter(data1, "output");
        Intrinsics.checkNotNullParameter(constraints0, "constraints");
        Intrinsics.checkNotNullParameter(backoffPolicy0, "backoffPolicy");
        Intrinsics.checkNotNullParameter(outOfQuotaPolicy0, "outOfQuotaPolicy");
        return new WorkSpec(s, workInfo$State0, s1, s2, data0, data1, v, v1, v2, constraints0, v3, backoffPolicy0, v4, v5, v6, v7, z, outOfQuotaPolicy0, v8, v9, v10, v11, v12, s3);
    }

    public static WorkSpec copy$default(WorkSpec workSpec0, String s, State workInfo$State0, String s1, String s2, Data data0, Data data1, long v, long v1, long v2, Constraints constraints0, int v3, BackoffPolicy backoffPolicy0, long v4, long v5, long v6, long v7, boolean z, OutOfQuotaPolicy outOfQuotaPolicy0, int v8, int v9, long v10, int v11, int v12, String s3, int v13, Object object0) {
        String s4 = (v13 & 1) == 0 ? s : workSpec0.id;
        State workInfo$State1 = (v13 & 2) == 0 ? workInfo$State0 : workSpec0.state;
        String s5 = (v13 & 4) == 0 ? s1 : workSpec0.workerClassName;
        String s6 = (v13 & 8) == 0 ? s2 : workSpec0.inputMergerClassName;
        Data data2 = (v13 & 16) == 0 ? data0 : workSpec0.input;
        Data data3 = (v13 & 0x20) == 0 ? data1 : workSpec0.output;
        long v14 = (v13 & 0x40) == 0 ? v : workSpec0.initialDelay;
        long v15 = (v13 & 0x80) == 0 ? v1 : workSpec0.intervalDuration;
        long v16 = (v13 & 0x100) == 0 ? v2 : workSpec0.flexDuration;
        Constraints constraints1 = (v13 & 0x200) == 0 ? constraints0 : workSpec0.constraints;
        int v17 = (v13 & 0x400) == 0 ? v3 : workSpec0.runAttemptCount;
        BackoffPolicy backoffPolicy1 = (v13 & 0x800) == 0 ? backoffPolicy0 : workSpec0.backoffPolicy;
        long v18 = (v13 & 0x1000) == 0 ? v4 : workSpec0.backoffDelayDuration;
        long v19 = (v13 & 0x2000) == 0 ? v5 : workSpec0.lastEnqueueTime;
        long v20 = (v13 & 0x4000) == 0 ? v6 : workSpec0.minimumRetentionDuration;
        long v21 = (v13 & 0x8000) == 0 ? v7 : workSpec0.scheduleRequestedAt;
        boolean z1 = (v13 & 0x10000) == 0 ? z : workSpec0.expedited;
        OutOfQuotaPolicy outOfQuotaPolicy1 = (v13 & 0x20000) == 0 ? outOfQuotaPolicy0 : workSpec0.outOfQuotaPolicy;
        int v22 = (v13 & 0x40000) == 0 ? v8 : workSpec0.periodCount;
        int v23 = (v13 & 0x80000) == 0 ? v9 : workSpec0.generation;
        long v24 = (v13 & 0x100000) == 0 ? v10 : workSpec0.nextScheduleTimeOverride;
        int v25 = (v13 & 0x200000) == 0 ? v11 : workSpec0.nextScheduleTimeOverrideGeneration;
        int v26 = (v13 & 0x400000) == 0 ? v12 : workSpec0.stopReason;
        return (v13 & 0x800000) == 0 ? workSpec0.copy(s4, workInfo$State1, s5, s6, data2, data3, v14, v15, v16, constraints1, v17, backoffPolicy1, v18, v19, v20, v21, z1, outOfQuotaPolicy1, v22, v23, v24, v25, v26, s3) : workSpec0.copy(s4, workInfo$State1, s5, s6, data2, data3, v14, v15, v16, constraints1, v17, backoffPolicy1, v18, v19, v20, v21, z1, outOfQuotaPolicy1, v22, v23, v24, v25, v26, workSpec0.traceTag);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof WorkSpec)) {
            return false;
        }
        WorkSpec workSpec0 = (WorkSpec)object0;
        if(!Intrinsics.areEqual(this.id, workSpec0.id)) {
            return false;
        }
        if(this.state != workSpec0.state) {
            return false;
        }
        if(!Intrinsics.areEqual(this.workerClassName, workSpec0.workerClassName)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.inputMergerClassName, workSpec0.inputMergerClassName)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.input, workSpec0.input)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.output, workSpec0.output)) {
            return false;
        }
        if(this.initialDelay != workSpec0.initialDelay) {
            return false;
        }
        if(this.intervalDuration != workSpec0.intervalDuration) {
            return false;
        }
        if(this.flexDuration != workSpec0.flexDuration) {
            return false;
        }
        if(!Intrinsics.areEqual(this.constraints, workSpec0.constraints)) {
            return false;
        }
        if(this.runAttemptCount != workSpec0.runAttemptCount) {
            return false;
        }
        if(this.backoffPolicy != workSpec0.backoffPolicy) {
            return false;
        }
        if(this.backoffDelayDuration != workSpec0.backoffDelayDuration) {
            return false;
        }
        if(this.lastEnqueueTime != workSpec0.lastEnqueueTime) {
            return false;
        }
        if(this.minimumRetentionDuration != workSpec0.minimumRetentionDuration) {
            return false;
        }
        if(this.scheduleRequestedAt != workSpec0.scheduleRequestedAt) {
            return false;
        }
        if(this.expedited != workSpec0.expedited) {
            return false;
        }
        if(this.outOfQuotaPolicy != workSpec0.outOfQuotaPolicy) {
            return false;
        }
        if(this.periodCount != workSpec0.periodCount) {
            return false;
        }
        if(this.generation != workSpec0.generation) {
            return false;
        }
        if(this.nextScheduleTimeOverride != workSpec0.nextScheduleTimeOverride) {
            return false;
        }
        if(this.nextScheduleTimeOverrideGeneration != workSpec0.nextScheduleTimeOverrideGeneration) {
            return false;
        }
        return this.stopReason == workSpec0.stopReason ? Intrinsics.areEqual(this.traceTag, workSpec0.traceTag) : false;
    }

    public final int getGeneration() {
        return this.generation;
    }

    public final long getNextScheduleTimeOverride() {
        return this.nextScheduleTimeOverride;
    }

    public final int getNextScheduleTimeOverrideGeneration() {
        return this.nextScheduleTimeOverrideGeneration;
    }

    public final int getPeriodCount() {
        return this.periodCount;
    }

    public final int getStopReason() {
        return this.stopReason;
    }

    public final String getTraceTag() {
        return this.traceTag;
    }

    public final boolean hasConstraints() {
        return !Intrinsics.areEqual(Constraints.NONE, this.constraints);
    }

    @Override
    public int hashCode() {
        int v = ((((((((((((((((((((((this.id.hashCode() * 0x1F + this.state.hashCode()) * 0x1F + this.workerClassName.hashCode()) * 0x1F + this.inputMergerClassName.hashCode()) * 0x1F + this.input.hashCode()) * 0x1F + this.output.hashCode()) * 0x1F + ((int)(this.initialDelay ^ this.initialDelay >>> 0x20))) * 0x1F + ((int)(this.intervalDuration ^ this.intervalDuration >>> 0x20))) * 0x1F + ((int)(this.flexDuration ^ this.flexDuration >>> 0x20))) * 0x1F + this.constraints.hashCode()) * 0x1F + this.runAttemptCount) * 0x1F + this.backoffPolicy.hashCode()) * 0x1F + ((int)(this.backoffDelayDuration ^ this.backoffDelayDuration >>> 0x20))) * 0x1F + ((int)(this.lastEnqueueTime ^ this.lastEnqueueTime >>> 0x20))) * 0x1F + ((int)(this.minimumRetentionDuration ^ this.minimumRetentionDuration >>> 0x20))) * 0x1F + ((int)(this.scheduleRequestedAt ^ this.scheduleRequestedAt >>> 0x20))) * 0x1F + UByte..ExternalSyntheticBackport0.m(this.expedited)) * 0x1F + this.outOfQuotaPolicy.hashCode()) * 0x1F + this.periodCount) * 0x1F + this.generation) * 0x1F + ((int)(this.nextScheduleTimeOverride ^ this.nextScheduleTimeOverride >>> 0x20))) * 0x1F + this.nextScheduleTimeOverrideGeneration) * 0x1F + this.stopReason) * 0x1F;
        return this.traceTag == null ? v : v + this.traceTag.hashCode();
    }

    public final boolean isBackedOff() {
        return this.state == State.ENQUEUED && this.runAttemptCount > 0;
    }

    public final boolean isPeriodic() {
        return this.intervalDuration != 0L;
    }

    public final void setBackoffDelayDuration(long v) {
        if(v > 18000000L) {
            Logger.get().warning("WM-WorkSpec", "Backoff delay duration exceeds maximum value");
        }
        if(v < 10000L) {
            Logger.get().warning("WM-WorkSpec", "Backoff delay duration less than minimum value");
        }
        this.backoffDelayDuration = RangesKt.coerceIn(v, 10000L, 18000000L);
    }

    public final void setNextScheduleTimeOverride(long v) {
        this.nextScheduleTimeOverride = v;
    }

    public final void setNextScheduleTimeOverrideGeneration(int v) {
        this.nextScheduleTimeOverrideGeneration = v;
    }

    public final void setPeriodCount(int v) {
        this.periodCount = v;
    }

    public final void setPeriodic(long v) {
        if(v < 900000L) {
            Logger.get().warning("WM-WorkSpec", "Interval duration lesser than minimum allowed value; Changed to 900000");
        }
        this.setPeriodic(RangesKt.coerceAtLeast(v, 900000L), RangesKt.coerceAtLeast(v, 900000L));
    }

    public final void setPeriodic(long v, long v1) {
        if(v < 900000L) {
            Logger.get().warning("WM-WorkSpec", "Interval duration lesser than minimum allowed value; Changed to 900000");
        }
        this.intervalDuration = RangesKt.coerceAtLeast(v, 900000L);
        if(v1 < 300000L) {
            Logger.get().warning("WM-WorkSpec", "Flex duration lesser than minimum allowed value; Changed to 300000");
        }
        if(v1 > this.intervalDuration) {
            Logger.get().warning("WM-WorkSpec", "Flex duration greater than interval duration; Changed to " + v);
        }
        this.flexDuration = RangesKt.coerceIn(v1, 300000L, this.intervalDuration);
    }

    public final void setTraceTag(String s) {
        this.traceTag = s;
    }

    @Override
    public String toString() {
        return "{WorkSpec: " + this.id + '}';
    }
}

