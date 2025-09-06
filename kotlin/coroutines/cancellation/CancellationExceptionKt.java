package kotlin.coroutines.cancellation;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001E\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001A!\u0010\u0000\u001A\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001A\u0004\u0018\u00010\u00042\b\u0010\u0005\u001A\u0004\u0018\u00010\u0006H\u0087\b\u001A\u0017\u0010\u0000\u001A\u00060\u0001j\u0002`\u00022\b\u0010\u0005\u001A\u0004\u0018\u00010\u0006H\u0087\b*\u001A\b\u0007\u0010\u0000\"\u00020\u00012\u00020\u0001B\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t¨\u0006\n"}, d2 = {"CancellationException", "Ljava/util/concurrent/CancellationException;", "Lkotlin/coroutines/cancellation/CancellationException;", "message", "", "cause", "", "Lkotlin/SinceKotlin;", "version", "1.4", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class CancellationExceptionKt {
    private static final CancellationException CancellationException(String s, Throwable throwable0) {
        CancellationException cancellationException0 = new CancellationException(s);
        cancellationException0.initCause(throwable0);
        return cancellationException0;
    }

    private static final CancellationException CancellationException(Throwable throwable0) {
        CancellationException cancellationException0 = new CancellationException((throwable0 == null ? null : throwable0.toString()));
        cancellationException0.initCause(throwable0);
        return cancellationException0;
    }

    public static void CancellationException$annotations() {
    }
}

