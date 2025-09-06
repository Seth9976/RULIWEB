package kotlinx.coroutines.internal;

import java.util.List;
import kotlin.Metadata;
import kotlinx.coroutines.MainCoroutineDispatcher;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001A\u00020\b2\f\u0010\t\u001A\b\u0012\u0004\u0012\u00020\u00010\nH\u0016R\u0014\u0010\u0003\u001A\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u000B"}, d2 = {"Lkotlinx/coroutines/internal/MissingMainCoroutineDispatcherFactory;", "Lkotlinx/coroutines/internal/MainDispatcherFactory;", "()V", "loadPriority", "", "getLoadPriority", "()I", "createDispatcher", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "allFactories", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class MissingMainCoroutineDispatcherFactory implements MainDispatcherFactory {
    public static final MissingMainCoroutineDispatcherFactory INSTANCE;

    static {
        MissingMainCoroutineDispatcherFactory.INSTANCE = new MissingMainCoroutineDispatcherFactory();
    }

    @Override  // kotlinx.coroutines.internal.MainDispatcherFactory
    public MainCoroutineDispatcher createDispatcher(List list0) {
        return new MissingMainCoroutineDispatcher(null, null, 2, null);
    }

    @Override  // kotlinx.coroutines.internal.MainDispatcherFactory
    public int getLoadPriority() {
        return -1;
    }

    @Override  // kotlinx.coroutines.internal.MainDispatcherFactory
    public String hintOnError() {
        return null;
    }
}

