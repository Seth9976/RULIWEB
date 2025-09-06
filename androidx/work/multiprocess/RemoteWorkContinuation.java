package androidx.work.multiprocess;

import androidx.work.OneTimeWorkRequest;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collections;
import java.util.List;

public abstract class RemoteWorkContinuation {
    public static RemoteWorkContinuation combine(List list0) {
        return ((RemoteWorkContinuation)list0.get(0)).combineInternal(list0);
    }

    protected abstract RemoteWorkContinuation combineInternal(List arg1);

    public abstract ListenableFuture enqueue();

    public final RemoteWorkContinuation then(OneTimeWorkRequest oneTimeWorkRequest0) {
        return this.then(Collections.singletonList(oneTimeWorkRequest0));
    }

    public abstract RemoteWorkContinuation then(List arg1);
}

