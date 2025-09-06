package androidx.work.impl;

import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0001¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\tH\u0016J\u0012\u0010\n\u001A\u0004\u0018\u00010\u000B2\u0006\u0010\b\u001A\u00020\tH\u0016J\u0016\u0010\n\u001A\b\u0012\u0004\u0012\u00020\u000B0\f2\u0006\u0010\r\u001A\u00020\u000EH\u0016J\u0010\u0010\u000F\u001A\u00020\u000B2\u0006\u0010\b\u001A\u00020\tH\u0016R\u000E\u0010\u0002\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/work/impl/SynchronizedStartStopTokensImpl;", "Landroidx/work/impl/StartStopTokens;", "delegate", "(Landroidx/work/impl/StartStopTokens;)V", "lock", "", "contains", "", "id", "Landroidx/work/impl/model/WorkGenerationalId;", "remove", "Landroidx/work/impl/StartStopToken;", "", "workSpecId", "", "tokenFor", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class SynchronizedStartStopTokensImpl implements StartStopTokens {
    private final StartStopTokens delegate;
    private final Object lock;

    public SynchronizedStartStopTokensImpl(StartStopTokens startStopTokens0) {
        Intrinsics.checkNotNullParameter(startStopTokens0, "delegate");
        super();
        this.delegate = startStopTokens0;
        this.lock = new Object();
    }

    @Override  // androidx.work.impl.StartStopTokens
    public boolean contains(WorkGenerationalId workGenerationalId0) {
        Intrinsics.checkNotNullParameter(workGenerationalId0, "id");
        synchronized(this.lock) {
            return this.delegate.contains(workGenerationalId0);
        }
    }

    @Override  // androidx.work.impl.StartStopTokens
    public StartStopToken remove(WorkGenerationalId workGenerationalId0) {
        Intrinsics.checkNotNullParameter(workGenerationalId0, "id");
        synchronized(this.lock) {
            return this.delegate.remove(workGenerationalId0);
        }
    }

    @Override  // androidx.work.impl.StartStopTokens
    public StartStopToken remove(WorkSpec workSpec0) {
        return StartStopTokens.-CC.$default$remove(this, workSpec0);
    }

    @Override  // androidx.work.impl.StartStopTokens
    public List remove(String s) {
        Intrinsics.checkNotNullParameter(s, "workSpecId");
        synchronized(this.lock) {
            return this.delegate.remove(s);
        }
    }

    @Override  // androidx.work.impl.StartStopTokens
    public StartStopToken tokenFor(WorkGenerationalId workGenerationalId0) {
        Intrinsics.checkNotNullParameter(workGenerationalId0, "id");
        synchronized(this.lock) {
            return this.delegate.tokenFor(workGenerationalId0);
        }
    }

    @Override  // androidx.work.impl.StartStopTokens
    public StartStopToken tokenFor(WorkSpec workSpec0) {
        return StartStopTokens.-CC.$default$tokenFor(this, workSpec0);
    }
}

