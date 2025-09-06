package androidx.emoji2.text;

import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class ConcurrencyHelpers {
    static class Handler28Impl {
        public static Handler createAsync(Looper looper0) {
            return Handler.createAsync(looper0);
        }
    }

    private static final int FONT_LOAD_TIMEOUT_SECONDS = 15;

    @Deprecated
    static Executor convertHandlerToExecutor(Handler handler0) {
        Objects.requireNonNull(handler0);
        return new ConcurrencyHelpers..ExternalSyntheticLambda0(handler0);
    }

    static ThreadPoolExecutor createBackgroundPriorityExecutor(String s) {
        ConcurrencyHelpers..ExternalSyntheticLambda1 concurrencyHelpers$$ExternalSyntheticLambda10 = (Runnable runnable0) -> {
            Thread thread0 = new Thread(runnable0, s);
            thread0.setPriority(10);
            return thread0;
        };
        ThreadPoolExecutor threadPoolExecutor0 = new ThreadPoolExecutor(0, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), concurrencyHelpers$$ExternalSyntheticLambda10);
        threadPoolExecutor0.allowCoreThreadTimeOut(true);
        return threadPoolExecutor0;
    }

    // 检测为 Lambda 实现
    static Thread lambda$createBackgroundPriorityExecutor$0(String s, Runnable runnable0) [...]

    static Handler mainHandlerAsync() {
        return Build.VERSION.SDK_INT < 28 ? new Handler(Looper.getMainLooper()) : Handler28Impl.createAsync(Looper.getMainLooper());
    }
}

