package androidx.room;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0001¢\u0006\u0002\u0010\u0003J\u0010\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\u0005H\u0016J\u0006\u0010\r\u001A\u00020\u000BR\u0010\u0010\u0004\u001A\u0004\u0018\u00010\u0005X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001A\b\u0012\u0004\u0012\u00020\u00050\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000E"}, d2 = {"Landroidx/room/TransactionExecutor;", "Ljava/util/concurrent/Executor;", "executor", "(Ljava/util/concurrent/Executor;)V", "active", "Ljava/lang/Runnable;", "syncLock", "", "tasks", "Ljava/util/ArrayDeque;", "execute", "", "command", "scheduleNext", "room-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class TransactionExecutor implements Executor {
    private Runnable active;
    private final Executor executor;
    private final Object syncLock;
    private final ArrayDeque tasks;

    // 检测为 Lambda 实现
    public static void $r8$lambda$FZWr2PGmP3sgXLCiri-DCcePXSs(Runnable runnable0, TransactionExecutor transactionExecutor0) [...]

    public TransactionExecutor(Executor executor0) {
        Intrinsics.checkNotNullParameter(executor0, "executor");
        super();
        this.executor = executor0;
        this.tasks = new ArrayDeque();
        this.syncLock = new Object();
    }

    @Override
    public void execute(Runnable runnable0) {
        Intrinsics.checkNotNullParameter(runnable0, "command");
        synchronized(this.syncLock) {
            TransactionExecutor..ExternalSyntheticLambda0 transactionExecutor$$ExternalSyntheticLambda00 = () -> TransactionExecutor.execute$lambda$1$lambda$0(runnable0, this);
            this.tasks.offer(transactionExecutor$$ExternalSyntheticLambda00);
            if(this.active == null) {
                this.scheduleNext();
            }
        }
    }

    private static final void execute$lambda$1$lambda$0(Runnable runnable0, TransactionExecutor transactionExecutor0) {
        Intrinsics.checkNotNullParameter(runnable0, "$command");
        Intrinsics.checkNotNullParameter(transactionExecutor0, "this$0");
        try {
            runnable0.run();
        }
        finally {
            transactionExecutor0.scheduleNext();
        }
    }

    public final void scheduleNext() {
        synchronized(this.syncLock) {
            Object object1 = this.tasks.poll();
            this.active = (Runnable)object1;
            if(object1 != null) {
                this.executor.execute(((Runnable)object1));
            }
        }
    }
}

