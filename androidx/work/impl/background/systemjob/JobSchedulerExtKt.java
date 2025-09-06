package androidx.work.impl.background.systemjob;

import android.app.job.JobScheduler;
import android.content.Context;
import android.os.Build.VERSION;
import androidx.work.Configuration;
import androidx.work.Logger;
import androidx.work.impl.WorkDatabase;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A \u0010\r\u001A\u00020\u00012\u0006\u0010\u000E\u001A\u00020\n2\u0006\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u0012H\u0001\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000E\u0010\u0002\u001A\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u001D\u0010\u0003\u001A\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004*\u00020\u00068G¢\u0006\u0006\u001A\u0004\b\u0007\u0010\b\"\u0018\u0010\t\u001A\u00020\u0006*\u00020\n8AX\u0080\u0004¢\u0006\u0006\u001A\u0004\b\u000B\u0010\f¨\u0006\u0013"}, d2 = {"TAG", "", "WORKMANAGER_NAMESPACE", "safePendingJobs", "", "Landroid/app/job/JobInfo;", "Landroid/app/job/JobScheduler;", "getSafePendingJobs", "(Landroid/app/job/JobScheduler;)Ljava/util/List;", "wmJobScheduler", "Landroid/content/Context;", "getWmJobScheduler", "(Landroid/content/Context;)Landroid/app/job/JobScheduler;", "createErrorMessage", "context", "workDatabase", "Landroidx/work/impl/WorkDatabase;", "configuration", "Landroidx/work/Configuration;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class JobSchedulerExtKt {
    private static final String TAG = null;
    public static final String WORKMANAGER_NAMESPACE = "androidx.work.systemjobscheduler";

    // 去混淆评级： 低(20)
    static {
        Intrinsics.checkNotNullExpressionValue("WM-SystemJobScheduler", "tagWithPrefix(\"SystemJobScheduler\")");
        JobSchedulerExtKt.TAG = "WM-SystemJobScheduler";
    }

    public static final String createErrorMessage(Context context0, WorkDatabase workDatabase0, Configuration configuration0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(workDatabase0, "workDatabase");
        Intrinsics.checkNotNullParameter(configuration0, "configuration");
        int v = Build.VERSION.SDK_INT < 0x1F ? 100 : 150;
        int v1 = workDatabase0.workSpecDao().getScheduledWork().size();
        String s = "<faulty JobScheduler failed to getPendingJobs>";
        if(Build.VERSION.SDK_INT >= 34) {
            JobScheduler jobScheduler0 = JobSchedulerExtKt.getWmJobScheduler(context0);
            List list0 = JobSchedulerExtKt.getSafePendingJobs(jobScheduler0);
            if(list0 != null) {
                List list1 = SystemJobScheduler.getPendingJobs(context0, jobScheduler0);
                int v2 = 0;
                int v3 = list1 == null ? 0 : list0.size() - list1.size();
                String s1 = null;
                Object object0 = context0.getSystemService("jobscheduler");
                Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type android.app.job.JobScheduler");
                List list2 = SystemJobScheduler.getPendingJobs(context0, ((JobScheduler)object0));
                if(list2 != null) {
                    v2 = list2.size();
                }
                if(v2 != 0) {
                    s1 = v2 + " from WorkManager in the default namespace";
                }
                return "JobScheduler " + v + " job limit exceeded.\nIn JobScheduler there are " + CollectionsKt.joinToString$default(CollectionsKt.listOfNotNull(new String[]{list0.size() + " jobs in \"androidx.work.systemjobscheduler\" namespace", (v3 == 0 ? null : v3 + " of which are not owned by WorkManager"), s1}), ",\n", null, null, 0, null, null, 62, null) + ".\nThere are " + v1 + " jobs tracked by WorkManager\'s database;\nthe Configuration limit is " + configuration0.getMaxSchedulerLimit() + '.';
            }
        }
        else {
            List list3 = SystemJobScheduler.getPendingJobs(context0, JobSchedulerExtKt.getWmJobScheduler(context0));
            if(list3 != null) {
                s = list3.size() + " jobs from WorkManager";
            }
        }
        return "JobScheduler " + v + " job limit exceeded.\nIn JobScheduler there are " + s + ".\nThere are " + v1 + " jobs tracked by WorkManager\'s database;\nthe Configuration limit is " + configuration0.getMaxSchedulerLimit() + '.';
    }

    public static final List getSafePendingJobs(JobScheduler jobScheduler0) {
        Intrinsics.checkNotNullParameter(jobScheduler0, "<this>");
        try {
            return JobScheduler21.INSTANCE.getAllPendingJobs(jobScheduler0);
        }
        catch(Throwable throwable0) {
            Logger.get().error("WM-SystemJobScheduler", "getAllPendingJobs() is not reliable on this device.", throwable0);
            return null;
        }
    }

    public static final JobScheduler getWmJobScheduler(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "<this>");
        Object object0 = context0.getSystemService("jobscheduler");
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type android.app.job.JobScheduler");
        return Build.VERSION.SDK_INT < 34 ? ((JobScheduler)object0) : JobScheduler34.INSTANCE.forNamespace(((JobScheduler)object0));
    }
}

