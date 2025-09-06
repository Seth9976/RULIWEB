package androidx.work.impl.utils;

import androidx.work.impl.WorkDatabase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001A\u00020\u0006J\u0016\u0010\u0007\u001A\u00020\u00062\u0006\u0010\b\u001A\u00020\u00062\u0006\u0010\t\u001A\u00020\u0006R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/work/impl/utils/IdGenerator;", "", "workDatabase", "Landroidx/work/impl/WorkDatabase;", "(Landroidx/work/impl/WorkDatabase;)V", "nextAlarmManagerId", "", "nextJobSchedulerIdWithRange", "minInclusive", "maxInclusive", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class IdGenerator {
    private final WorkDatabase workDatabase;

    // 检测为 Lambda 实现
    public static Integer $r8$lambda$JQr7FK7MZ2bSJyfHKiwoJ9vBWDc(IdGenerator idGenerator0, int v, int v1) [...]

    // 检测为 Lambda 实现
    public static Integer $r8$lambda$Or-Y_3PZnHqGqtjwdJP_AQtPLLY(IdGenerator idGenerator0) [...]

    public IdGenerator(WorkDatabase workDatabase0) {
        Intrinsics.checkNotNullParameter(workDatabase0, "workDatabase");
        super();
        this.workDatabase = workDatabase0;
    }

    public final int nextAlarmManagerId() {
        IdGenerator..ExternalSyntheticLambda0 idGenerator$$ExternalSyntheticLambda00 = () -> IdGenerator.nextAlarmManagerId$lambda$1(this);
        Object object0 = this.workDatabase.runInTransaction(idGenerator$$ExternalSyntheticLambda00);
        Intrinsics.checkNotNullExpressionValue(object0, "workDatabase.runInTransa…NAGER_ID_KEY) }\n        )");
        return ((Number)object0).intValue();
    }

    private static final Integer nextAlarmManagerId$lambda$1(IdGenerator idGenerator0) {
        return IdGeneratorKt.access$nextId(idGenerator0.workDatabase, "next_alarm_manager_id");
    }

    public final int nextJobSchedulerIdWithRange(int v, int v1) {
        IdGenerator..ExternalSyntheticLambda1 idGenerator$$ExternalSyntheticLambda10 = () -> IdGenerator.nextJobSchedulerIdWithRange$lambda$0(this, v, v1);
        Object object0 = this.workDatabase.runInTransaction(idGenerator$$ExternalSyntheticLambda10);
        Intrinsics.checkNotNullExpressionValue(object0, "workDatabase.runInTransa…d\n            }\n        )");
        return ((Number)object0).intValue();
    }

    private static final Integer nextJobSchedulerIdWithRange$lambda$0(IdGenerator idGenerator0, int v, int v1) {
        int v2 = IdGeneratorKt.access$nextId(idGenerator0.workDatabase, "next_job_scheduler_id");
        if(v <= v2 && v2 <= v1) {
            return v2;
        }
        IdGeneratorKt.access$updatePreference(idGenerator0.workDatabase, "next_job_scheduler_id", v + 1);
        return v;
    }
}

