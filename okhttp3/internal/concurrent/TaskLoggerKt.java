package okhttp3.internal.concurrent;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001A\u000E\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003\u001A$\u0010\u0004\u001A\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\u0001H\u0002\u001A9\u0010\f\u001A\u0002H\r\"\u0004\b\u0000\u0010\r*\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\f\u0010\u000E\u001A\b\u0012\u0004\u0012\u0002H\r0\u000FH\u0080\bø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001A.\u0010\u0011\u001A\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u00020\u00010\u000FH\u0080\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0013"}, d2 = {"formatDuration", "", "ns", "", "log", "", "Ljava/util/logging/Logger;", "task", "Lokhttp3/internal/concurrent/Task;", "queue", "Lokhttp3/internal/concurrent/TaskQueue;", "message", "logElapsed", "T", "block", "Lkotlin/Function0;", "(Ljava/util/logging/Logger;Lokhttp3/internal/concurrent/Task;Lokhttp3/internal/concurrent/TaskQueue;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "taskLog", "messageBlock", "okhttp"}, k = 2, mv = {1, 7, 1}, xi = 0x30)
public final class TaskLoggerKt {
    public static final String formatDuration(long v) {
        String s;
        if(v <= -999500000L) {
            s = (v - 500000000L) / 1000000000L + " s ";
        }
        else if(v <= 0xFFFFFFFFFFF0BFB4L) {
            s = (v - 500000L) / 1000000L + " ms";
        }
        else if(v <= 0L) {
            s = (v - 500L) / 1000L + " µs";
        }
        else if(v < 0xF404CL) {
            s = (v + 500L) / 1000L + " µs";
        }
        else {
            s = v >= 999500000L ? (v + 500000000L) / 1000000000L + " s " : (v + 500000L) / 1000000L + " ms";
        }
        String s1 = String.format("%6s", Arrays.copyOf(new Object[]{s}, 1));
        Intrinsics.checkNotNullExpressionValue(s1, "format(format, *args)");
        return s1;
    }

    private static final void log(Logger logger0, Task task0, TaskQueue taskQueue0, String s) {
        String s1 = String.format("%-22s", Arrays.copyOf(new Object[]{s}, 1));
        Intrinsics.checkNotNullExpressionValue(s1, "format(format, *args)");
        logger0.fine(taskQueue0.getName$okhttp() + ' ' + s1 + ": " + task0.getName());
    }

    public static final Object logElapsed(Logger logger0, Task task0, TaskQueue taskQueue0, Function0 function00) {
        Object object0;
        long v;
        Intrinsics.checkNotNullParameter(logger0, "<this>");
        Intrinsics.checkNotNullParameter(task0, "task");
        Intrinsics.checkNotNullParameter(taskQueue0, "queue");
        Intrinsics.checkNotNullParameter(function00, "block");
        boolean z = logger0.isLoggable(Level.FINE);
        if(z) {
            v = taskQueue0.getTaskRunner$okhttp().getBackend().nanoTime();
            TaskLoggerKt.log(logger0, task0, taskQueue0, "starting");
        }
        else {
            v = -1L;
        }
        try {
            object0 = function00.invoke();
        }
        catch(Throwable throwable0) {
            if(z) {
                TaskLoggerKt.log(logger0, task0, taskQueue0, "failed a run in " + TaskLoggerKt.formatDuration(taskQueue0.getTaskRunner$okhttp().getBackend().nanoTime() - v));
            }
            throw throwable0;
        }
        if(z) {
            TaskLoggerKt.log(logger0, task0, taskQueue0, "finished run in " + TaskLoggerKt.formatDuration(taskQueue0.getTaskRunner$okhttp().getBackend().nanoTime() - v));
        }
        return object0;
    }

    public static final void taskLog(Logger logger0, Task task0, TaskQueue taskQueue0, Function0 function00) {
        Intrinsics.checkNotNullParameter(logger0, "<this>");
        Intrinsics.checkNotNullParameter(task0, "task");
        Intrinsics.checkNotNullParameter(taskQueue0, "queue");
        Intrinsics.checkNotNullParameter(function00, "messageBlock");
        if(logger0.isLoggable(Level.FINE)) {
            TaskLoggerKt.log(logger0, task0, taskQueue0, ((String)function00.invoke()));
        }
    }
}

