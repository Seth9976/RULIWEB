package androidx.work.impl.utils;

import androidx.core.util.Consumer;
import androidx.work.Logger;
import androidx.work.WorkerExceptionInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\u001A \u0010\u0000\u001A\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001A\u00020\u00032\u0006\u0010\u0005\u001A\u00020\u0006¨\u0006\u0007"}, d2 = {"safeAccept", "", "Landroidx/core/util/Consumer;", "Landroidx/work/WorkerExceptionInfo;", "info", "tag", "", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class WorkerExceptionUtilsKt {
    public static final void safeAccept(Consumer consumer0, WorkerExceptionInfo workerExceptionInfo0, String s) {
        Intrinsics.checkNotNullParameter(consumer0, "<this>");
        Intrinsics.checkNotNullParameter(workerExceptionInfo0, "info");
        Intrinsics.checkNotNullParameter(s, "tag");
        try {
            consumer0.accept(workerExceptionInfo0);
        }
        catch(Throwable throwable0) {
            Logger.get().error(s, "Exception handler threw an exception", throwable0);
        }
    }
}

