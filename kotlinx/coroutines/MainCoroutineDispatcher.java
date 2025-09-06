package kotlinx.coroutines;

import kotlin.Metadata;
import kotlinx.coroutines.internal.LimitedDispatcherKt;

@Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001A\u00020\u00012\u0006\u0010\u0007\u001A\u00020\bH\u0016J\b\u0010\t\u001A\u00020\nH\u0016J\n\u0010\u000B\u001A\u0004\u0018\u00010\nH\u0005R\u0012\u0010\u0003\u001A\u00020\u0000X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0004\u0010\u0005¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/MainCoroutineDispatcher;", "Lkotlinx/coroutines/CoroutineDispatcher;", "()V", "immediate", "getImmediate", "()Lkotlinx/coroutines/MainCoroutineDispatcher;", "limitedParallelism", "parallelism", "", "toString", "", "toStringInternalImpl", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class MainCoroutineDispatcher extends CoroutineDispatcher {
    public abstract MainCoroutineDispatcher getImmediate();

    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public CoroutineDispatcher limitedParallelism(int v) {
        LimitedDispatcherKt.checkParallelism(v);
        return this;
    }

    @Override  // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        String s = this.toStringInternalImpl();
        return s == null ? DebugStringsKt.getClassSimpleName(this) + '@' + DebugStringsKt.getHexAddress(this) : s;
    }

    protected final String toStringInternalImpl() {
        MainCoroutineDispatcher mainCoroutineDispatcher1;
        MainCoroutineDispatcher mainCoroutineDispatcher0 = Dispatchers.getMain();
        if(this == mainCoroutineDispatcher0) {
            return "Dispatchers.Main";
        }
        try {
            mainCoroutineDispatcher1 = null;
            mainCoroutineDispatcher1 = mainCoroutineDispatcher0.getImmediate();
        }
        catch(UnsupportedOperationException unused_ex) {
        }
        return this == mainCoroutineDispatcher1 ? "Dispatchers.Main.immediate" : null;
    }
}

