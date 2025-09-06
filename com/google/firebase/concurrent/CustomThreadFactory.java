package com.google.firebase.concurrent;

import android.os.Process;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;

class CustomThreadFactory implements ThreadFactory {
    private static final ThreadFactory DEFAULT;
    private final String namePrefix;
    private final StrictMode.ThreadPolicy policy;
    private final int priority;
    private final AtomicLong threadCount;

    static {
        CustomThreadFactory.DEFAULT = Executors.defaultThreadFactory();
    }

    CustomThreadFactory(String s, int v, @Nullable StrictMode.ThreadPolicy strictMode$ThreadPolicy0) {
        this.threadCount = new AtomicLong();
        this.namePrefix = s;
        this.priority = v;
        this.policy = strictMode$ThreadPolicy0;
    }

    // 检测为 Lambda 实现
    void lambda$newThread$0$com-google-firebase-concurrent-CustomThreadFactory(Runnable runnable0) [...]

    @Override
    public Thread newThread(Runnable runnable0) {
        CustomThreadFactory..ExternalSyntheticLambda0 customThreadFactory$$ExternalSyntheticLambda00 = () -> {
            Process.setThreadPriority(this.priority);
            StrictMode.ThreadPolicy strictMode$ThreadPolicy0 = this.policy;
            if(strictMode$ThreadPolicy0 != null) {
                StrictMode.setThreadPolicy(strictMode$ThreadPolicy0);
            }
            runnable0.run();
        };
        Thread thread0 = CustomThreadFactory.DEFAULT.newThread(customThreadFactory$$ExternalSyntheticLambda00);
        Locale locale0 = Locale.ROOT;
        Long long0 = this.threadCount.getAndIncrement();
        thread0.setName(String.format(locale0, "%s Thread #%d", this.namePrefix, long0));
        return thread0;
    }
}

