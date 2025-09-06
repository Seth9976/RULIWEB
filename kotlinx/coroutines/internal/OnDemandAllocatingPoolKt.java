package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u001A\u0017\u0010\u0002\u001A\u00020\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082\b\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"IS_CLOSED_MASK", "", "loop", "", "block", "Lkotlin/Function0;", "", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class OnDemandAllocatingPoolKt {
    private static final int IS_CLOSED_MASK = 0x80000000;

    private static final Void loop(Function0 function00) {
        while(true) {
            function00.invoke();
        }
    }
}

