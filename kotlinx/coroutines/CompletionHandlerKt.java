package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001A8\u0010\r\u001A\u00020\u0006*#\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u00072\b\u0010\u0005\u001A\u0004\u0018\u00010\u0002H\u0080\b\":\u0010\u0000\u001A#\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u0007*\u00020\b8À\u0002X\u0080\u0004¢\u0006\u0006\u001A\u0004\b\t\u0010\n\":\u0010\u0000\u001A#\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u0007*\u00020\u000B8À\u0002X\u0080\u0004¢\u0006\u0006\u001A\u0004\b\t\u0010\f¨\u0006\u000E"}, d2 = {"asHandler", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "Lkotlinx/coroutines/CompletionHandler;", "Lkotlinx/coroutines/CancelHandlerBase;", "getAsHandler", "(Lkotlinx/coroutines/CancelHandlerBase;)Lkotlin/jvm/functions/Function1;", "Lkotlinx/coroutines/CompletionHandlerBase;", "(Lkotlinx/coroutines/CompletionHandlerBase;)Lkotlin/jvm/functions/Function1;", "invokeIt", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class CompletionHandlerKt {
    public static final Function1 getAsHandler(CancelHandlerBase cancelHandlerBase0) {
        return cancelHandlerBase0;
    }

    public static final Function1 getAsHandler(CompletionHandlerBase completionHandlerBase0) {
        return completionHandlerBase0;
    }

    public static final void invokeIt(Function1 function10, Throwable throwable0) {
        function10.invoke(throwable0);
    }
}

