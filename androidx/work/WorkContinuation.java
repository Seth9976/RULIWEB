package androidx.work;

import androidx.lifecycle.LiveData;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collections;
import java.util.List;

public abstract class WorkContinuation {
    public static WorkContinuation combine(List list0) {
        return ((WorkContinuation)list0.get(0)).combineInternal(list0);
    }

    protected abstract WorkContinuation combineInternal(List arg1);

    public abstract Operation enqueue();

    public abstract ListenableFuture getWorkInfos();

    public abstract LiveData getWorkInfosLiveData();

    public final WorkContinuation then(OneTimeWorkRequest oneTimeWorkRequest0) {
        return this.then(Collections.singletonList(oneTimeWorkRequest0));
    }

    public abstract WorkContinuation then(List arg1);
}

