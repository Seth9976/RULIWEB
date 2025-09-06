package androidx.work;

import androidx.tracing.Trace;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorsKt;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A\u0010\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H\u0002\u001A\b\u0010\u0006\u001A\u00020\u0007H\u0002\u001A\u0010\u0010\b\u001A\u0004\u0018\u00010\u0003*\u0004\u0018\u00010\tH\u0002\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"DEFAULT_CONTENT_URI_TRIGGERS_WORKERS_LIMIT", "", "createDefaultExecutor", "Ljava/util/concurrent/Executor;", "isTaskExecutor", "", "createDefaultTracer", "Landroidx/work/Tracer;", "asExecutor", "Lkotlin/coroutines/CoroutineContext;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ConfigurationKt {
    public static final int DEFAULT_CONTENT_URI_TRIGGERS_WORKERS_LIMIT = 8;

    public static final Executor access$asExecutor(CoroutineContext coroutineContext0) {
        return ConfigurationKt.asExecutor(coroutineContext0);
    }

    public static final Executor access$createDefaultExecutor(boolean z) {
        return ConfigurationKt.createDefaultExecutor(z);
    }

    public static final Tracer access$createDefaultTracer() {
        return ConfigurationKt.createDefaultTracer();
    }

    private static final Executor asExecutor(CoroutineContext coroutineContext0) {
        ContinuationInterceptor continuationInterceptor0 = coroutineContext0 == null ? null : ((ContinuationInterceptor)coroutineContext0.get(ContinuationInterceptor.Key));
        CoroutineDispatcher coroutineDispatcher0 = continuationInterceptor0 instanceof CoroutineDispatcher ? ((CoroutineDispatcher)continuationInterceptor0) : null;
        return coroutineDispatcher0 == null ? null : ExecutorsKt.asExecutor(coroutineDispatcher0);
    }

    private static final Executor createDefaultExecutor(boolean z) {
        ExecutorService executorService0 = Executors.newFixedThreadPool(Math.max(2, Math.min(Runtime.getRuntime().availableProcessors() - 1, 4)), new ThreadFactory() {
            private final AtomicInteger threadCount;

            {
                boolean z = z;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                this.threadCount = new AtomicInteger(0);
            }

            @Override
            public Thread newThread(Runnable runnable0) {
                Intrinsics.checkNotNullParameter(runnable0, "runnable");
                return z ? new Thread(runnable0, "WM.task-" + this.threadCount.incrementAndGet()) : new Thread(runnable0, "androidx.work-" + this.threadCount.incrementAndGet());
            }
        });
        Intrinsics.checkNotNullExpressionValue(executorService0, "newFixedThreadPool(\n    …)),\n        factory\n    )");
        return executorService0;
    }

    private static final Tracer createDefaultTracer() {
        return new Tracer() {
            @Override  // androidx.work.Tracer
            public void beginAsyncSection(String s, int v) {
                Intrinsics.checkNotNullParameter(s, "methodName");
                Trace.beginAsyncSection(s, v);
            }

            @Override  // androidx.work.Tracer
            public void beginSection(String s) {
                Intrinsics.checkNotNullParameter(s, "label");
                Trace.beginSection(s);
            }

            @Override  // androidx.work.Tracer
            public void endAsyncSection(String s, int v) {
                Intrinsics.checkNotNullParameter(s, "methodName");
                Trace.endAsyncSection(s, v);
            }

            @Override  // androidx.work.Tracer
            public void endSection() {
                Trace.endSection();
            }

            @Override  // androidx.work.Tracer
            public boolean isEnabled() {
                return Trace.isEnabled();
            }
        };
    }
}

