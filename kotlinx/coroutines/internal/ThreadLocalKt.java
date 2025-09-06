package kotlinx.coroutines.internal;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A&\u0010\u0000\u001A\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0004\u001A\u00020\u0005H\u0000*\u001E\b\u0000\u0010\u0006\u001A\u0004\b\u0000\u0010\u0002\"\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\u0012\u0004\u0012\u0002H\u00020\u0001¨\u0006\u0007"}, d2 = {"commonThreadLocal", "Ljava/lang/ThreadLocal;", "T", "Lkotlinx/coroutines/internal/CommonThreadLocal;", "name", "Lkotlinx/coroutines/internal/Symbol;", "CommonThreadLocal", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ThreadLocalKt {
    public static void CommonThreadLocal$annotations() {
    }

    public static final ThreadLocal commonThreadLocal(Symbol symbol0) {
        return new ThreadLocal();
    }
}

