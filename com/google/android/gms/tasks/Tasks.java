package com.google.android.gms.tasks;

import android.os.Looper;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.tasks.zza;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class Tasks {
    public static Object await(Task task0) throws ExecutionException, InterruptedException {
        Preconditions.checkNotMainThread();
        Preconditions.checkNotGoogleApiHandlerThread();
        Preconditions.checkNotNull(task0, "Task must not be null");
        if(task0.isComplete()) {
            return Tasks.zza(task0);
        }
        zzad zzad0 = new zzad(null);
        Tasks.zzb(task0, zzad0);
        zzad0.zza();
        return Tasks.zza(task0);
    }

    public static Object await(Task task0, long v, TimeUnit timeUnit0) throws ExecutionException, InterruptedException, TimeoutException {
        Preconditions.checkNotMainThread();
        Preconditions.checkNotGoogleApiHandlerThread();
        Preconditions.checkNotNull(task0, "Task must not be null");
        Preconditions.checkNotNull(timeUnit0, "TimeUnit must not be null");
        if(task0.isComplete()) {
            return Tasks.zza(task0);
        }
        zzad zzad0 = new zzad(null);
        Tasks.zzb(task0, zzad0);
        if(!zzad0.zzb(v, timeUnit0)) {
            throw new TimeoutException("Timed out waiting for Task");
        }
        return Tasks.zza(task0);
    }

    @Deprecated
    public static Task call(Callable callable0) {
        return Tasks.call(TaskExecutors.MAIN_THREAD, callable0);
    }

    @Deprecated
    public static Task call(Executor executor0, Callable callable0) {
        Preconditions.checkNotNull(executor0, "Executor must not be null");
        Preconditions.checkNotNull(callable0, "Callback must not be null");
        Task task0 = new zzw();
        executor0.execute(new zzz(((zzw)task0), callable0));
        return task0;
    }

    public static Task forCanceled() {
        Task task0 = new zzw();
        ((zzw)task0).zzc();
        return task0;
    }

    public static Task forException(Exception exception0) {
        Task task0 = new zzw();
        ((zzw)task0).zza(exception0);
        return task0;
    }

    public static Task forResult(Object object0) {
        Task task0 = new zzw();
        ((zzw)task0).zzb(object0);
        return task0;
    }

    public static Task whenAll(Collection collection0) {
        if(collection0 != null && !collection0.isEmpty()) {
            for(Object object0: collection0) {
                if(((Task)object0) == null) {
                    throw new NullPointerException("null tasks are not accepted");
                }
                if(false) {
                    break;
                }
            }
            Task task0 = new zzw();
            zzaf zzaf0 = new zzaf(collection0.size(), ((zzw)task0));
            for(Object object1: collection0) {
                Tasks.zzb(((Task)object1), zzaf0);
            }
            return task0;
        }
        return Tasks.forResult(null);
    }

    public static Task whenAll(Task[] arr_task) {
        return arr_task == null || arr_task.length == 0 ? Tasks.forResult(null) : Tasks.whenAll(Arrays.asList(arr_task));
    }

    public static Task whenAllComplete(Collection collection0) {
        return Tasks.whenAllComplete(TaskExecutors.MAIN_THREAD, collection0);
    }

    public static Task whenAllComplete(Executor executor0, Collection collection0) {
        return collection0 == null || collection0.isEmpty() ? Tasks.forResult(Collections.EMPTY_LIST) : Tasks.whenAll(collection0).continueWithTask(executor0, new zzab(collection0));
    }

    public static Task whenAllComplete(Executor executor0, Task[] arr_task) {
        return arr_task == null || arr_task.length == 0 ? Tasks.forResult(Collections.EMPTY_LIST) : Tasks.whenAllComplete(executor0, Arrays.asList(arr_task));
    }

    public static Task whenAllComplete(Task[] arr_task) {
        return arr_task == null || arr_task.length == 0 ? Tasks.forResult(Collections.EMPTY_LIST) : Tasks.whenAllComplete(Arrays.asList(arr_task));
    }

    public static Task whenAllSuccess(Collection collection0) {
        return Tasks.whenAllSuccess(TaskExecutors.MAIN_THREAD, collection0);
    }

    public static Task whenAllSuccess(Executor executor0, Collection collection0) {
        return collection0 == null || collection0.isEmpty() ? Tasks.forResult(Collections.EMPTY_LIST) : Tasks.whenAll(collection0).continueWith(executor0, new zzaa(collection0));
    }

    public static Task whenAllSuccess(Executor executor0, Task[] arr_task) {
        return arr_task == null || arr_task.length == 0 ? Tasks.forResult(Collections.EMPTY_LIST) : Tasks.whenAllSuccess(executor0, Arrays.asList(arr_task));
    }

    public static Task whenAllSuccess(Task[] arr_task) {
        return arr_task == null || arr_task.length == 0 ? Tasks.forResult(Collections.EMPTY_LIST) : Tasks.whenAllSuccess(Arrays.asList(arr_task));
    }

    public static Task withTimeout(Task task0, long v, TimeUnit timeUnit0) {
        Preconditions.checkNotNull(task0, "Task must not be null");
        Preconditions.checkArgument(v > 0L, "Timeout must be positive");
        Preconditions.checkNotNull(timeUnit0, "TimeUnit must not be null");
        zzb zzb0 = new zzb();
        TaskCompletionSource taskCompletionSource0 = new TaskCompletionSource(zzb0);
        zza zza0 = new zza(Looper.getMainLooper());
        zza0.postDelayed(new zzx(taskCompletionSource0), timeUnit0.toMillis(v));
        task0.addOnCompleteListener(new zzy(zza0, taskCompletionSource0, zzb0));
        return taskCompletionSource0.getTask();
    }

    private static Object zza(Task task0) throws ExecutionException {
        if(task0.isSuccessful()) {
            return task0.getResult();
        }
        if(!task0.isCanceled()) {
            throw new ExecutionException(task0.getException());
        }
        throw new CancellationException("Task is already canceled");
    }

    private static void zzb(Task task0, zzae zzae0) {
        task0.addOnSuccessListener(TaskExecutors.zza, zzae0);
        task0.addOnFailureListener(TaskExecutors.zza, zzae0);
        task0.addOnCanceledListener(TaskExecutors.zza, zzae0);
    }
}

