package com.google.firebase.concurrent;

import android.os.Build.VERSION;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.os.StrictMode.ThreadPolicy;
import com.google.firebase.annotations.concurrent.Background;
import com.google.firebase.annotations.concurrent.Blocking;
import com.google.firebase.annotations.concurrent.Lightweight;
import com.google.firebase.annotations.concurrent.UiThread;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Lazy;
import com.google.firebase.components.Qualified;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

public class ExecutorsRegistrar implements ComponentRegistrar {
    static final Lazy BG_EXECUTOR;
    static final Lazy BLOCKING_EXECUTOR;
    static final Lazy LITE_EXECUTOR;
    static final Lazy SCHEDULER;

    static {
        ExecutorsRegistrar.BG_EXECUTOR = new Lazy(() -> ExecutorsRegistrar.scheduled(Executors.newFixedThreadPool(4, ExecutorsRegistrar.factory("Firebase Background", 10, ExecutorsRegistrar.bgPolicy()))));
        ExecutorsRegistrar.LITE_EXECUTOR = new Lazy(() -> ExecutorsRegistrar.scheduled(Executors.newFixedThreadPool(Math.max(2, Runtime.getRuntime().availableProcessors()), ExecutorsRegistrar.factory("Firebase Lite", 0, ExecutorsRegistrar.litePolicy()))));
        ExecutorsRegistrar.BLOCKING_EXECUTOR = new Lazy(() -> ExecutorsRegistrar.scheduled(Executors.newCachedThreadPool(ExecutorsRegistrar.factory("Firebase Blocking", 11))));
        ExecutorsRegistrar.SCHEDULER = new Lazy(() -> Executors.newSingleThreadScheduledExecutor(ExecutorsRegistrar.factory("Firebase Scheduler", 0)));
    }

    private static StrictMode.ThreadPolicy bgPolicy() {
        StrictMode.ThreadPolicy.Builder strictMode$ThreadPolicy$Builder0 = new StrictMode.ThreadPolicy.Builder().detectNetwork();
        if(Build.VERSION.SDK_INT >= 23) {
            LinkFollowing..ExternalSyntheticApiModelOutline0.m(strictMode$ThreadPolicy$Builder0);
            if(Build.VERSION.SDK_INT >= 26) {
                LinkFollowing..ExternalSyntheticApiModelOutline0.m$1(strictMode$ThreadPolicy$Builder0);
            }
        }
        return strictMode$ThreadPolicy$Builder0.penaltyLog().build();
    }

    private static ThreadFactory factory(String s, int v) {
        return new CustomThreadFactory(s, v, null);
    }

    private static ThreadFactory factory(String s, int v, StrictMode.ThreadPolicy strictMode$ThreadPolicy0) {
        return new CustomThreadFactory(s, v, strictMode$ThreadPolicy0);
    }

    @Override  // com.google.firebase.components.ComponentRegistrar
    public List getComponents() {
        return Arrays.asList(new Component[]{Component.builder(Qualified.qualified(Background.class, ScheduledExecutorService.class), new Qualified[]{Qualified.qualified(Background.class, ExecutorService.class), Qualified.qualified(Background.class, Executor.class)}).factory((ComponentContainer componentContainer0) -> ((ScheduledExecutorService)ExecutorsRegistrar.BG_EXECUTOR.get())).build(), Component.builder(Qualified.qualified(Blocking.class, ScheduledExecutorService.class), new Qualified[]{Qualified.qualified(Blocking.class, ExecutorService.class), Qualified.qualified(Blocking.class, Executor.class)}).factory((ComponentContainer componentContainer0) -> ((ScheduledExecutorService)ExecutorsRegistrar.BLOCKING_EXECUTOR.get())).build(), Component.builder(Qualified.qualified(Lightweight.class, ScheduledExecutorService.class), new Qualified[]{Qualified.qualified(Lightweight.class, ExecutorService.class), Qualified.qualified(Lightweight.class, Executor.class)}).factory((ComponentContainer componentContainer0) -> ((ScheduledExecutorService)ExecutorsRegistrar.LITE_EXECUTOR.get())).build(), Component.builder(Qualified.qualified(UiThread.class, Executor.class)).factory((ComponentContainer componentContainer0) -> UiExecutor.INSTANCE).build()});
    }

    // 检测为 Lambda 实现
    static ScheduledExecutorService lambda$getComponents$4(ComponentContainer componentContainer0) [...]

    // 检测为 Lambda 实现
    static ScheduledExecutorService lambda$getComponents$5(ComponentContainer componentContainer0) [...]

    // 检测为 Lambda 实现
    static ScheduledExecutorService lambda$getComponents$6(ComponentContainer componentContainer0) [...]

    // 检测为 Lambda 实现
    static Executor lambda$getComponents$7(ComponentContainer componentContainer0) [...]

    // 检测为 Lambda 实现
    static ScheduledExecutorService lambda$static$0() [...]

    // 检测为 Lambda 实现
    static ScheduledExecutorService lambda$static$1() [...]

    // 检测为 Lambda 实现
    static ScheduledExecutorService lambda$static$2() [...]

    // 检测为 Lambda 实现
    static ScheduledExecutorService lambda$static$3() [...]

    private static StrictMode.ThreadPolicy litePolicy() {
        return new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build();
    }

    private static ScheduledExecutorService scheduled(ExecutorService executorService0) {
        return new DelegatingScheduledExecutorService(executorService0, ((ScheduledExecutorService)ExecutorsRegistrar.SCHEDULER.get()));
    }
}

