package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;

public class TaskUtil {
    public static void setResultOrApiException(Status status0, TaskCompletionSource taskCompletionSource0) {
        TaskUtil.setResultOrApiException(status0, null, taskCompletionSource0);
    }

    public static void setResultOrApiException(Status status0, Object object0, TaskCompletionSource taskCompletionSource0) {
        if(status0.isSuccess()) {
            taskCompletionSource0.setResult(object0);
            return;
        }
        taskCompletionSource0.setException(ApiExceptionUtil.fromStatus(status0));
    }

    @Deprecated
    public static Task toVoidTaskThatFailsOnFalse(Task task0) {
        return task0.continueWith(new zacx());
    }

    // 去混淆评级： 低(20)
    @ResultIgnorabilityUnspecified
    public static boolean trySetResultOrApiException(Status status0, Object object0, TaskCompletionSource taskCompletionSource0) {
        return status0.isSuccess() ? taskCompletionSource0.trySetResult(object0) : taskCompletionSource0.trySetException(ApiExceptionUtil.fromStatus(status0));
    }
}

