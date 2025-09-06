package androidx.lifecycle;

import java.io.Closeable;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.JobKt__JobKt;

@Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\t\u001A\u00020\nH\u0016R\u0014\u0010\u0006\u001A\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\b¨\u0006\u000B"}, d2 = {"Landroidx/lifecycle/CloseableCoroutineScope;", "Ljava/io/Closeable;", "Lkotlinx/coroutines/CoroutineScope;", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "coroutineContext", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "close", "", "lifecycle-viewmodel-ktx_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class CloseableCoroutineScope implements Closeable, AutoCloseable, CoroutineScope {
    private final CoroutineContext coroutineContext;

    public CloseableCoroutineScope(CoroutineContext coroutineContext0) {
        Intrinsics.checkNotNullParameter(coroutineContext0, "context");
        super();
        this.coroutineContext = coroutineContext0;
    }

    @Override
    public void close() {
        JobKt__JobKt.cancel$default(this.getCoroutineContext(), null, 1, null);
    }

    @Override  // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }
}

