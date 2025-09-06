package androidx.work;

import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\b\u0087\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0007H\u0016J\b\u0010\b\u001A\u00020\tH\u0016j\u0002\b\n¨\u0006\u000B"}, d2 = {"Landroidx/work/DirectExecutor;", "", "Ljava/util/concurrent/Executor;", "(Ljava/lang/String;I)V", "execute", "", "command", "Ljava/lang/Runnable;", "toString", "", "INSTANCE", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public enum DirectExecutor implements Executor {
    INSTANCE;

    private static final DirectExecutor[] $values() [...] // Inlined contents

    @Override
    public void execute(Runnable runnable0) {
        Intrinsics.checkNotNullParameter(runnable0, "command");
        runnable0.run();
    }

    @Override
    public String toString() {
        return "DirectExecutor";
    }
}

