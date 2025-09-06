package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.intrinsics.UndispatchedKt;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\u0012\u0010\u0000\u001A\u00020\u00012\n\b\u0002\u0010\u0002\u001A\u0004\u0018\u00010\u0003\u001A\u0019\u0010\u0004\u001A\u00020\u00032\n\b\u0002\u0010\u0002\u001A\u0004\u0018\u00010\u0003H\u0007¢\u0006\u0002\b\u0000\u001AM\u0010\u0005\u001A\u0002H\u0006\"\u0004\b\u0000\u0010\u00062\'\u0010\u0007\u001A#\b\u0001\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\n\u0012\u0006\u0012\u0004\u0018\u00010\u000B0\b¢\u0006\u0002\b\fH\u0086@ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000E"}, d2 = {"SupervisorJob", "Lkotlinx/coroutines/CompletableJob;", "parent", "Lkotlinx/coroutines/Job;", "SupervisorJob0", "supervisorScope", "R", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class SupervisorKt {
    public static final CompletableJob SupervisorJob(Job job0) {
        return new SupervisorJobImpl(job0);
    }

    public static CompletableJob SupervisorJob$default(Job job0, int v, Object object0) {
        if((v & 1) != 0) {
            job0 = null;
        }
        return SupervisorKt.SupervisorJob(job0);
    }

    public static Job SupervisorJob$default(Job job0, int v, Object object0) {
        if((v & 1) != 0) {
            job0 = null;
        }
        return SupervisorKt.SupervisorJob(job0);
    }

    public static final Object supervisorScope(Function2 function20, Continuation continuation0) {
        SupervisorCoroutine supervisorCoroutine0 = new SupervisorCoroutine(continuation0.getContext(), continuation0);
        Object object0 = UndispatchedKt.startUndispatchedOrReturn(supervisorCoroutine0, supervisorCoroutine0, function20);
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0;
    }
}

