package kotlinx.coroutines.debug.internal;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u001A\"\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0000\u001A\u0014\u0010\u0004\u001A\u00020\u00052\n\u0010\u0006\u001A\u0006\u0012\u0002\b\u00030\u0001H\u0000\u001A\u0014\u0010\u0007\u001A\u00020\u00052\n\u0010\u0006\u001A\u0006\u0012\u0002\b\u00030\u0001H\u0000¨\u0006\b"}, d2 = {"probeCoroutineCreated", "Lkotlin/coroutines/Continuation;", "T", "completion", "probeCoroutineResumed", "", "frame", "probeCoroutineSuspended", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class DebugProbesKt {
    public static final Continuation probeCoroutineCreated(Continuation continuation0) {
        return DebugProbesImpl.INSTANCE.probeCoroutineCreated$kotlinx_coroutines_core(continuation0);
    }

    public static final void probeCoroutineResumed(Continuation continuation0) {
        DebugProbesImpl.INSTANCE.probeCoroutineResumed$kotlinx_coroutines_core(continuation0);
    }

    public static final void probeCoroutineSuspended(Continuation continuation0) {
        DebugProbesImpl.INSTANCE.probeCoroutineSuspended$kotlinx_coroutines_core(continuation0);
    }
}

