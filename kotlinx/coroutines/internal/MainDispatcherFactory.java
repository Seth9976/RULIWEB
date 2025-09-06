package kotlinx.coroutines.internal;

import java.util.List;
import kotlin.Metadata;
import kotlinx.coroutines.MainCoroutineDispatcher;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000E\n\u0000\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0006\u001A\u00020\u00072\f\u0010\b\u001A\b\u0012\u0004\u0012\u00020\u00000\tH&J\n\u0010\n\u001A\u0004\u0018\u00010\u000BH\u0016R\u0012\u0010\u0002\u001A\u00020\u0003X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0004\u0010\u0005¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/internal/MainDispatcherFactory;", "", "loadPriority", "", "getLoadPriority", "()I", "createDispatcher", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "allFactories", "", "hintOnError", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface MainDispatcherFactory {
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    public static final class DefaultImpls {
        public static String hintOnError(MainDispatcherFactory mainDispatcherFactory0) [...] // Inlined contents
    }

    MainCoroutineDispatcher createDispatcher(List arg1);

    int getLoadPriority();

    String hintOnError();
}

