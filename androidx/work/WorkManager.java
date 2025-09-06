package androidx.work;

import android.app.PendingIntent;
import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.work.impl.WorkManagerImpl;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.UUID;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;

@Metadata(d1 = {"\u0000\u008A\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\b\'\u0018\u0000 ;2\u00020\u0001:\u0002;<B\u0007\b\u0000\u00A2\u0006\u0002\u0010\u0002J\u001E\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000EJ&\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\f2\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u00020\u000E0\u0010H&J\u000E\u0010\u0011\u001A\u00020\b2\u0006\u0010\r\u001A\u00020\u000EJ\u0016\u0010\u0011\u001A\u00020\b2\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u00020\u000E0\u0010H&J\b\u0010\u0012\u001A\u00020\u0013H&J\u0010\u0010\u0014\u001A\u00020\u00132\u0006\u0010\u0015\u001A\u00020\nH&J\u0010\u0010\u0016\u001A\u00020\u00132\u0006\u0010\t\u001A\u00020\nH&J\u0010\u0010\u0017\u001A\u00020\u00132\u0006\u0010\u0018\u001A\u00020\u0019H&J\u0010\u0010\u001A\u001A\u00020\u001B2\u0006\u0010\u0018\u001A\u00020\u0019H&J\u000E\u0010\u001C\u001A\u00020\u00132\u0006\u0010\r\u001A\u00020\u001DJ\u0016\u0010\u001C\u001A\u00020\u00132\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u00020\u001D0\u0010H&J \u0010\u001E\u001A\u00020\u00132\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u001F\u001A\u00020 2\u0006\u0010\r\u001A\u00020!H&J \u0010\"\u001A\u00020\u00132\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000EH\u0016J&\u0010\"\u001A\u00020\u00132\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\f2\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u00020\u000E0\u0010H&J\u000E\u0010#\u001A\b\u0012\u0004\u0012\u00020%0$H&J\u000E\u0010&\u001A\b\u0012\u0004\u0012\u00020%0\'H&J\u0018\u0010(\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010)0$2\u0006\u0010\u0018\u001A\u00020\u0019H&J\u0018\u0010*\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010)0+2\u0006\u0010\u0018\u001A\u00020\u0019H&J\u0018\u0010,\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010)0\'2\u0006\u0010\u0018\u001A\u00020\u0019H&J\u001C\u0010-\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u00100$2\u0006\u0010.\u001A\u00020/H&J\u001C\u00100\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u00100$2\u0006\u0010\u0015\u001A\u00020\nH&J\u001C\u00101\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u00100+2\u0006\u0010\u0015\u001A\u00020\nH&J\u001C\u00102\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u00100\'2\u0006\u0010\u0015\u001A\u00020\nH&J\u001C\u00103\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u00100+2\u0006\u0010.\u001A\u00020/H&J\u001C\u00104\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u00100$2\u0006\u0010\t\u001A\u00020\nH&J\u001C\u00105\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u00100+2\u0006\u0010\t\u001A\u00020\nH&J\u001C\u00106\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u00100\'2\u0006\u0010\t\u001A\u00020\nH&J\u001C\u00107\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u00100\'2\u0006\u0010.\u001A\u00020/H&J\b\u00108\u001A\u00020\u0013H&J\u0016\u00109\u001A\b\u0012\u0004\u0012\u00020:0$2\u0006\u0010\r\u001A\u00020\u001DH&R\u0012\u0010\u0003\u001A\u00020\u0004X\u00A6\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0005\u0010\u0006\u00A8\u0006="}, d2 = {"Landroidx/work/WorkManager;", "", "()V", "configuration", "Landroidx/work/Configuration;", "getConfiguration", "()Landroidx/work/Configuration;", "beginUniqueWork", "Landroidx/work/WorkContinuation;", "uniqueWorkName", "", "existingWorkPolicy", "Landroidx/work/ExistingWorkPolicy;", "request", "Landroidx/work/OneTimeWorkRequest;", "requests", "", "beginWith", "cancelAllWork", "Landroidx/work/Operation;", "cancelAllWorkByTag", "tag", "cancelUniqueWork", "cancelWorkById", "id", "Ljava/util/UUID;", "createCancelPendingIntent", "Landroid/app/PendingIntent;", "enqueue", "Landroidx/work/WorkRequest;", "enqueueUniquePeriodicWork", "existingPeriodicWorkPolicy", "Landroidx/work/ExistingPeriodicWorkPolicy;", "Landroidx/work/PeriodicWorkRequest;", "enqueueUniqueWork", "getLastCancelAllTimeMillis", "Lcom/google/common/util/concurrent/ListenableFuture;", "", "getLastCancelAllTimeMillisLiveData", "Landroidx/lifecycle/LiveData;", "getWorkInfoById", "Landroidx/work/WorkInfo;", "getWorkInfoByIdFlow", "Lkotlinx/coroutines/flow/Flow;", "getWorkInfoByIdLiveData", "getWorkInfos", "workQuery", "Landroidx/work/WorkQuery;", "getWorkInfosByTag", "getWorkInfosByTagFlow", "getWorkInfosByTagLiveData", "getWorkInfosFlow", "getWorkInfosForUniqueWork", "getWorkInfosForUniqueWorkFlow", "getWorkInfosForUniqueWorkLiveData", "getWorkInfosLiveData", "pruneWork", "updateWork", "Landroidx/work/WorkManager$UpdateResult;", "Companion", "UpdateResult", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class WorkManager {
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001A\u00020\u0004H\u0017J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0017J\u0018\u0010\u0007\u001A\u00020\b2\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\t\u001A\u00020\nH\u0017J\b\u0010\u000B\u001A\u00020\fH\u0017¨\u0006\r"}, d2 = {"Landroidx/work/WorkManager$Companion;", "", "()V", "getInstance", "Landroidx/work/WorkManager;", "context", "Landroid/content/Context;", "initialize", "", "configuration", "Landroidx/work/Configuration;", "isInitialized", "", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @Deprecated(message = "Use the overload receiving Context", replaceWith = @ReplaceWith(expression = "WorkManager.getContext(context)", imports = {}))
        @JvmStatic
        public WorkManager getInstance() {
            WorkManager workManager0 = WorkManagerImpl.getInstance();
            if(workManager0 == null) {
                throw new IllegalStateException("WorkManager is not initialized properly.  The most likely cause is that you disabled WorkManagerInitializer in your manifest but forgot to call WorkManager#initialize in your Application#onCreate or a ContentProvider.");
            }
            return workManager0;
        }

        @JvmStatic
        public WorkManager getInstance(Context context0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            WorkManagerImpl workManagerImpl0 = WorkManagerImpl.getInstance(context0);
            Intrinsics.checkNotNullExpressionValue(workManagerImpl0, "getInstance(context)");
            return workManagerImpl0;
        }

        @JvmStatic
        public void initialize(Context context0, Configuration configuration0) {
            Intrinsics.checkNotNullParameter(context0, "context");
            Intrinsics.checkNotNullParameter(configuration0, "configuration");
            WorkManagerImpl.initialize(context0, configuration0);
        }

        @JvmStatic
        public boolean isInitialized() {
            return WorkManagerImpl.isInitialized();
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Landroidx/work/WorkManager$UpdateResult;", "", "(Ljava/lang/String;I)V", "NOT_APPLIED", "APPLIED_IMMEDIATELY", "APPLIED_FOR_NEXT_RUN", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static enum UpdateResult {
        NOT_APPLIED,
        APPLIED_IMMEDIATELY,
        APPLIED_FOR_NEXT_RUN;

        private static final UpdateResult[] $values() [...] // Inlined contents
    }

    public static final Companion Companion;

    static {
        WorkManager.Companion = new Companion(null);
    }

    public final WorkContinuation beginUniqueWork(String s, ExistingWorkPolicy existingWorkPolicy0, OneTimeWorkRequest oneTimeWorkRequest0) {
        Intrinsics.checkNotNullParameter(s, "uniqueWorkName");
        Intrinsics.checkNotNullParameter(existingWorkPolicy0, "existingWorkPolicy");
        Intrinsics.checkNotNullParameter(oneTimeWorkRequest0, "request");
        return this.beginUniqueWork(s, existingWorkPolicy0, CollectionsKt.listOf(oneTimeWorkRequest0));
    }

    public abstract WorkContinuation beginUniqueWork(String arg1, ExistingWorkPolicy arg2, List arg3);

    public final WorkContinuation beginWith(OneTimeWorkRequest oneTimeWorkRequest0) {
        Intrinsics.checkNotNullParameter(oneTimeWorkRequest0, "request");
        return this.beginWith(CollectionsKt.listOf(oneTimeWorkRequest0));
    }

    public abstract WorkContinuation beginWith(List arg1);

    public abstract Operation cancelAllWork();

    public abstract Operation cancelAllWorkByTag(String arg1);

    public abstract Operation cancelUniqueWork(String arg1);

    public abstract Operation cancelWorkById(UUID arg1);

    public abstract PendingIntent createCancelPendingIntent(UUID arg1);

    public final Operation enqueue(WorkRequest workRequest0) {
        Intrinsics.checkNotNullParameter(workRequest0, "request");
        return this.enqueue(CollectionsKt.listOf(workRequest0));
    }

    public abstract Operation enqueue(List arg1);

    public abstract Operation enqueueUniquePeriodicWork(String arg1, ExistingPeriodicWorkPolicy arg2, PeriodicWorkRequest arg3);

    public Operation enqueueUniqueWork(String s, ExistingWorkPolicy existingWorkPolicy0, OneTimeWorkRequest oneTimeWorkRequest0) {
        Intrinsics.checkNotNullParameter(s, "uniqueWorkName");
        Intrinsics.checkNotNullParameter(existingWorkPolicy0, "existingWorkPolicy");
        Intrinsics.checkNotNullParameter(oneTimeWorkRequest0, "request");
        return this.enqueueUniqueWork(s, existingWorkPolicy0, CollectionsKt.listOf(oneTimeWorkRequest0));
    }

    public abstract Operation enqueueUniqueWork(String arg1, ExistingWorkPolicy arg2, List arg3);

    public abstract Configuration getConfiguration();

    @Deprecated(message = "Use the overload receiving Context", replaceWith = @ReplaceWith(expression = "WorkManager.getContext(context)", imports = {}))
    @JvmStatic
    public static WorkManager getInstance() {
        return WorkManager.Companion.getInstance();
    }

    @JvmStatic
    public static WorkManager getInstance(Context context0) {
        return WorkManager.Companion.getInstance(context0);
    }

    public abstract ListenableFuture getLastCancelAllTimeMillis();

    public abstract LiveData getLastCancelAllTimeMillisLiveData();

    public abstract ListenableFuture getWorkInfoById(UUID arg1);

    public abstract Flow getWorkInfoByIdFlow(UUID arg1);

    public abstract LiveData getWorkInfoByIdLiveData(UUID arg1);

    public abstract ListenableFuture getWorkInfos(WorkQuery arg1);

    public abstract ListenableFuture getWorkInfosByTag(String arg1);

    public abstract Flow getWorkInfosByTagFlow(String arg1);

    public abstract LiveData getWorkInfosByTagLiveData(String arg1);

    public abstract Flow getWorkInfosFlow(WorkQuery arg1);

    public abstract ListenableFuture getWorkInfosForUniqueWork(String arg1);

    public abstract Flow getWorkInfosForUniqueWorkFlow(String arg1);

    public abstract LiveData getWorkInfosForUniqueWorkLiveData(String arg1);

    public abstract LiveData getWorkInfosLiveData(WorkQuery arg1);

    @JvmStatic
    public static void initialize(Context context0, Configuration configuration0) {
        WorkManager.Companion.initialize(context0, configuration0);
    }

    @JvmStatic
    public static boolean isInitialized() {
        return WorkManager.Companion.isInitialized();
    }

    public abstract Operation pruneWork();

    public abstract ListenableFuture updateWork(WorkRequest arg1);
}

