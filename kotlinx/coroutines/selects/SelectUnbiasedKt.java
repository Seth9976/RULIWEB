package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000\u0018\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001AE\u0010\u0000\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u001F\b\u0004\u0010\u0002\u001A\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086Hø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, d2 = {"selectUnbiased", "R", "builder", "Lkotlin/Function1;", "Lkotlinx/coroutines/selects/SelectBuilder;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class SelectUnbiasedKt {
    public static final Object selectUnbiased(Function1 function10, Continuation continuation0) {
        UnbiasedSelectImplementation unbiasedSelectImplementation0 = new UnbiasedSelectImplementation(continuation0.getContext());
        function10.invoke(unbiasedSelectImplementation0);
        return unbiasedSelectImplementation0.doSelect(continuation0);
    }

    private static final Object selectUnbiased$$forInline(Function1 function10, Continuation continuation0) {
        throw new NullPointerException();
    }
}

