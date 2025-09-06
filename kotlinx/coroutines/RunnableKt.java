package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u001A\u001D\u0010\u0000\u001A\u00060\u0001j\u0002`\u00022\u000E\b\u0004\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0086\b*\n\u0010\u0000\"\u00020\u00012\u00020\u0001¨\u0006\u0006"}, d2 = {"Runnable", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "block", "Lkotlin/Function0;", "", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class RunnableKt {
    public static final Runnable Runnable(Function0 function00) {
        return new Runnable() {
            @Override
            public final void run() {
                function00.invoke();
            }
        };
    }
}

