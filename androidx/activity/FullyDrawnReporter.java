package androidx.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001B\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0014\u0010\u0014\u001A\u00020\u00062\f\u0010\u0015\u001A\b\u0012\u0004\u0012\u00020\u00060\u0005J\u0006\u0010\u0016\u001A\u00020\u0006J\b\u0010\u0017\u001A\u00020\u0006H\u0007J\b\u0010\u0018\u001A\u00020\u0006H\u0002J\u0014\u0010\u0019\u001A\u00020\u00062\f\u0010\u0015\u001A\b\u0012\u0004\u0012\u00020\u00060\u0005J\u0006\u0010\u001A\u001A\u00020\u0006R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001A\u00020\t8F¢\u0006\u0006\u001A\u0004\b\b\u0010\nR\u000E\u0010\u000B\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001C\u0010\f\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\r8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000E\u001A\u00020\t8\u0002@\u0002X\u0083\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u000F\u001A\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001A\u00020\t8\u0002@\u0002X\u0083\u000E¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001A\u00020\u00138\u0002@\u0002X\u0083\u000E¢\u0006\u0002\n\u0000¨\u0006\u001B"}, d2 = {"Landroidx/activity/FullyDrawnReporter;", "", "executor", "Ljava/util/concurrent/Executor;", "reportFullyDrawn", "Lkotlin/Function0;", "", "(Ljava/util/concurrent/Executor;Lkotlin/jvm/functions/Function0;)V", "isFullyDrawnReported", "", "()Z", "lock", "onReportCallbacks", "", "reportPosted", "reportRunnable", "Ljava/lang/Runnable;", "reportedFullyDrawn", "reporterCount", "", "addOnReportDrawnListener", "callback", "addReporter", "fullyDrawnReported", "postWhenReportersAreDone", "removeOnReportDrawnListener", "removeReporter", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class FullyDrawnReporter {
    private final Executor executor;
    private final Object lock;
    private final List onReportCallbacks;
    private final Function0 reportFullyDrawn;
    private boolean reportPosted;
    private final Runnable reportRunnable;
    private boolean reportedFullyDrawn;
    private int reporterCount;

    public FullyDrawnReporter(Executor executor0, Function0 function00) {
        Intrinsics.checkNotNullParameter(executor0, "executor");
        Intrinsics.checkNotNullParameter(function00, "reportFullyDrawn");
        super();
        this.executor = executor0;
        this.reportFullyDrawn = function00;
        this.lock = new Object();
        this.onReportCallbacks = new ArrayList();
        this.reportRunnable = () -> synchronized(this.lock) {
            this.reportPosted = false;
            if(this.reporterCount == 0 && !this.reportedFullyDrawn) {
                this.reportFullyDrawn.invoke();
                this.fullyDrawnReported();
            }
        };
    }

    public final void addOnReportDrawnListener(Function0 function00) {
        boolean z;
        Intrinsics.checkNotNullParameter(function00, "callback");
        synchronized(this.lock) {
            if(this.reportedFullyDrawn) {
                z = true;
            }
            else {
                this.onReportCallbacks.add(function00);
                z = false;
            }
        }
        if(z) {
            function00.invoke();
        }
    }

    public final void addReporter() {
        synchronized(this.lock) {
            if(!this.reportedFullyDrawn) {
                ++this.reporterCount;
            }
        }
    }

    public final void fullyDrawnReported() {
        synchronized(this.lock) {
            this.reportedFullyDrawn = true;
            for(Object object1: this.onReportCallbacks) {
                ((Function0)object1).invoke();
            }
            this.onReportCallbacks.clear();
        }
    }

    public final boolean isFullyDrawnReported() {
        synchronized(this.lock) {
        }
        return this.reportedFullyDrawn;
    }

    private final void postWhenReportersAreDone() {
        if(!this.reportPosted && this.reporterCount == 0) {
            this.reportPosted = true;
            this.executor.execute(this.reportRunnable);
        }
    }

    public final void removeOnReportDrawnListener(Function0 function00) {
        Intrinsics.checkNotNullParameter(function00, "callback");
        synchronized(this.lock) {
            this.onReportCallbacks.remove(function00);
        }
    }

    public final void removeReporter() {
        synchronized(this.lock) {
            if(!this.reportedFullyDrawn) {
                int v1 = this.reporterCount;
                if(v1 > 0) {
                    this.reporterCount = v1 - 1;
                    this.postWhenReportersAreDone();
                }
            }
        }
    }

    // 检测为 Lambda 实现
    private static final void reportRunnable$lambda$2(FullyDrawnReporter fullyDrawnReporter0) [...]
}

