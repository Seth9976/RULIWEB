package androidx.work.impl.utils;

import androidx.work.ListenableFutureKt;
import androidx.work.WorkInfo;
import androidx.work.WorkQuery;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.WorkSpec.WorkInfoPojo;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.utils.taskexecutor.SerialExecutor;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001A.\u0010\u0000\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001*\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\b0\u0002H\u0000\u001A(\u0010\t\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001*\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\n\u001A\u00020\bH\u0000\u001A$\u0010\u000B\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001*\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\f\u001A\u00020\rH\u0000\u001A(\u0010\u000E\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001*\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u000F\u001A\u00020\bH\u0000\u001A(\u0010\u0010\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001*\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0011\u001A\u00020\u0012H\u0000\u001A4\u0010\u0013\u001A\b\u0012\u0004\u0012\u0002H\u00140\u0001\"\u0004\b\u0000\u0010\u0014*\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0012\u0010\u0015\u001A\u000E\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002H\u00140\u0016H\u0002¨\u0006\u0017"}, d2 = {"forStringIds", "Lcom/google/common/util/concurrent/ListenableFuture;", "", "Landroidx/work/WorkInfo;", "Landroidx/work/impl/WorkDatabase;", "executor", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "ids", "", "forTag", "tag", "forUUID", "id", "Ljava/util/UUID;", "forUniqueWork", "name", "forWorkQuerySpec", "querySpec", "Landroidx/work/WorkQuery;", "loadStatusFuture", "T", "block", "Lkotlin/Function1;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class StatusRunnable {
    public static final ListenableFuture forStringIds(WorkDatabase workDatabase0, TaskExecutor taskExecutor0, List list0) {
        Intrinsics.checkNotNullParameter(workDatabase0, "<this>");
        Intrinsics.checkNotNullParameter(taskExecutor0, "executor");
        Intrinsics.checkNotNullParameter(list0, "ids");
        return StatusRunnable.loadStatusFuture(workDatabase0, taskExecutor0, new Function1(list0) {
            final List $ids;

            {
                this.$ids = list0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((WorkDatabase)object0));
            }

            public final List invoke(WorkDatabase workDatabase0) {
                Intrinsics.checkNotNullParameter(workDatabase0, "db");
                List list0 = workDatabase0.workSpecDao().getWorkStatusPojoForIds(this.$ids);
                Object object0 = WorkSpec.WORK_INFO_MAPPER.apply(list0);
                Intrinsics.checkNotNullExpressionValue(object0, "WORK_INFO_MAPPER.apply(d…orkStatusPojoForIds(ids))");
                return (List)object0;
            }
        });
    }

    public static final ListenableFuture forTag(WorkDatabase workDatabase0, TaskExecutor taskExecutor0, String s) {
        Intrinsics.checkNotNullParameter(workDatabase0, "<this>");
        Intrinsics.checkNotNullParameter(taskExecutor0, "executor");
        Intrinsics.checkNotNullParameter(s, "tag");
        return StatusRunnable.loadStatusFuture(workDatabase0, taskExecutor0, new Function1(s) {
            final String $tag;

            {
                this.$tag = s;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((WorkDatabase)object0));
            }

            public final List invoke(WorkDatabase workDatabase0) {
                Intrinsics.checkNotNullParameter(workDatabase0, "db");
                List list0 = workDatabase0.workSpecDao().getWorkStatusPojoForTag(this.$tag);
                Object object0 = WorkSpec.WORK_INFO_MAPPER.apply(list0);
                Intrinsics.checkNotNullExpressionValue(object0, "WORK_INFO_MAPPER.apply(d…orkStatusPojoForTag(tag))");
                return (List)object0;
            }
        });
    }

    public static final ListenableFuture forUUID(WorkDatabase workDatabase0, TaskExecutor taskExecutor0, UUID uUID0) {
        Intrinsics.checkNotNullParameter(workDatabase0, "<this>");
        Intrinsics.checkNotNullParameter(taskExecutor0, "executor");
        Intrinsics.checkNotNullParameter(uUID0, "id");
        return StatusRunnable.loadStatusFuture(workDatabase0, taskExecutor0, new Function1(uUID0) {
            final UUID $id;

            {
                this.$id = uUID0;
                super(1);
            }

            public final WorkInfo invoke(WorkDatabase workDatabase0) {
                Intrinsics.checkNotNullParameter(workDatabase0, "db");
                WorkSpecDao workSpecDao0 = workDatabase0.workSpecDao();
                String s = this.$id.toString();
                Intrinsics.checkNotNullExpressionValue(s, "id.toString()");
                WorkInfoPojo workSpec$WorkInfoPojo0 = workSpecDao0.getWorkStatusPojoForId(s);
                return workSpec$WorkInfoPojo0 == null ? null : workSpec$WorkInfoPojo0.toWorkInfo();
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((WorkDatabase)object0));
            }
        });
    }

    public static final ListenableFuture forUniqueWork(WorkDatabase workDatabase0, TaskExecutor taskExecutor0, String s) {
        Intrinsics.checkNotNullParameter(workDatabase0, "<this>");
        Intrinsics.checkNotNullParameter(taskExecutor0, "executor");
        Intrinsics.checkNotNullParameter(s, "name");
        return StatusRunnable.loadStatusFuture(workDatabase0, taskExecutor0, new Function1(s) {
            final String $name;

            {
                this.$name = s;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((WorkDatabase)object0));
            }

            public final List invoke(WorkDatabase workDatabase0) {
                Intrinsics.checkNotNullParameter(workDatabase0, "db");
                List list0 = workDatabase0.workSpecDao().getWorkStatusPojoForName(this.$name);
                Object object0 = WorkSpec.WORK_INFO_MAPPER.apply(list0);
                Intrinsics.checkNotNullExpressionValue(object0, "WORK_INFO_MAPPER.apply(d…kStatusPojoForName(name))");
                return (List)object0;
            }
        });
    }

    public static final ListenableFuture forWorkQuerySpec(WorkDatabase workDatabase0, TaskExecutor taskExecutor0, WorkQuery workQuery0) {
        Intrinsics.checkNotNullParameter(workDatabase0, "<this>");
        Intrinsics.checkNotNullParameter(taskExecutor0, "executor");
        Intrinsics.checkNotNullParameter(workQuery0, "querySpec");
        return StatusRunnable.loadStatusFuture(workDatabase0, taskExecutor0, new Function1(workQuery0) {
            final WorkQuery $querySpec;

            {
                this.$querySpec = workQuery0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((WorkDatabase)object0));
            }

            public final List invoke(WorkDatabase workDatabase0) {
                Intrinsics.checkNotNullParameter(workDatabase0, "db");
                List list0 = workDatabase0.rawWorkInfoDao().getWorkInfoPojos(RawQueries.toRawQuery(this.$querySpec));
                Object object0 = WorkSpec.WORK_INFO_MAPPER.apply(list0);
                Intrinsics.checkNotNullExpressionValue(object0, "WORK_INFO_MAPPER.apply(d…(querySpec.toRawQuery()))");
                return (List)object0;
            }
        });
    }

    private static final ListenableFuture loadStatusFuture(WorkDatabase workDatabase0, TaskExecutor taskExecutor0, Function1 function10) {
        SerialExecutor serialExecutor0 = taskExecutor0.getSerialTaskExecutor();
        Intrinsics.checkNotNullExpressionValue(serialExecutor0, "executor.serialTaskExecutor");
        return ListenableFutureKt.executeAsync(serialExecutor0, "loadStatusFuture", new Function0(function10, workDatabase0) {
            final Function1 $block;
            final WorkDatabase $this_loadStatusFuture;

            {
                this.$block = function10;
                this.$this_loadStatusFuture = workDatabase0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.$block.invoke(this.$this_loadStatusFuture);
            }
        });
    }
}

