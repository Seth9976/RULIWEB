package androidx.work.impl.background.systemjob;

import android.app.job.JobScheduler;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001A\u00020\u0007¨\u0006\b"}, d2 = {"Landroidx/work/impl/background/systemjob/JobScheduler21;", "", "()V", "getAllPendingJobs", "", "Landroid/app/job/JobInfo;", "jobScheduler", "Landroid/app/job/JobScheduler;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class JobScheduler21 {
    public static final JobScheduler21 INSTANCE;

    static {
        JobScheduler21.INSTANCE = new JobScheduler21();
    }

    public final List getAllPendingJobs(JobScheduler jobScheduler0) {
        Intrinsics.checkNotNullParameter(jobScheduler0, "jobScheduler");
        List list0 = jobScheduler0.getAllPendingJobs();
        Intrinsics.checkNotNullExpressionValue(list0, "jobScheduler.allPendingJobs");
        return list0;
    }
}

