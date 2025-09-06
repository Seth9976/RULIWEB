package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public class PendingResultUtil {
    public interface ResultConverter {
        Object convert(Result arg1);
    }

    private static final zas zaa;

    static {
        PendingResultUtil.zaa = new zao();
    }

    public static Task toResponseTask(PendingResult pendingResult0, Response response0) {
        return PendingResultUtil.toTask(pendingResult0, new zaq(response0));
    }

    public static Task toTask(PendingResult pendingResult0, ResultConverter pendingResultUtil$ResultConverter0) {
        TaskCompletionSource taskCompletionSource0 = new TaskCompletionSource();
        pendingResult0.addStatusListener(new zap(pendingResult0, taskCompletionSource0, pendingResultUtil$ResultConverter0, PendingResultUtil.zaa));
        return taskCompletionSource0.getTask();
    }

    public static Task toVoidTask(PendingResult pendingResult0) {
        return PendingResultUtil.toTask(pendingResult0, new zar());
    }
}

