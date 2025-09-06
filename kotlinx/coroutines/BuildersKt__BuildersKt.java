package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001AT\u0010\u0000\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\b\b\u0002\u0010\u0002\u001A\u00020\u00032\'\u0010\u0004\u001A#\b\u0001\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005¢\u0006\u0002\b\tø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000B"}, d2 = {"runBlocking", "T", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 5, mv = {1, 8, 0}, xi = 0x30, xs = "kotlinx/coroutines/BuildersKt")
final class BuildersKt__BuildersKt {
    public static final Object runBlocking(CoroutineContext coroutineContext0, Function2 function20) throws InterruptedException {
        CoroutineContext coroutineContext2;
        EventLoop eventLoop0;
        Thread thread0 = Thread.currentThread();
        ContinuationInterceptor continuationInterceptor0 = (ContinuationInterceptor)coroutineContext0.get(ContinuationInterceptor.Key);
        if(continuationInterceptor0 == null) {
            eventLoop0 = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
            CoroutineContext coroutineContext1 = coroutineContext0.plus(eventLoop0);
            coroutineContext2 = CoroutineContextKt.newCoroutineContext(GlobalScope.INSTANCE, coroutineContext1);
        }
        else {
            if(continuationInterceptor0 instanceof EventLoop) {
                EventLoop eventLoop1 = (EventLoop)continuationInterceptor0;
            }
            eventLoop0 = ThreadLocalEventLoop.INSTANCE.currentOrNull$kotlinx_coroutines_core();
            coroutineContext2 = CoroutineContextKt.newCoroutineContext(GlobalScope.INSTANCE, coroutineContext0);
        }
        BlockingCoroutine blockingCoroutine0 = new BlockingCoroutine(coroutineContext2, thread0, eventLoop0);
        blockingCoroutine0.start(CoroutineStart.DEFAULT, blockingCoroutine0, function20);
        return blockingCoroutine0.joinBlocking();
    }

    public static Object runBlocking$default(CoroutineContext coroutineContext0, Function2 function20, int v, Object object0) throws InterruptedException {
        if((v & 1) != 0) {
            coroutineContext0 = EmptyCoroutineContext.INSTANCE;
        }
        return BuildersKt.runBlocking(coroutineContext0, function20);
    }
}

