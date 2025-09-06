package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"kotlinx/coroutines/BuildersKt__BuildersKt", "kotlinx/coroutines/BuildersKt__Builders_commonKt"}, k = 4, mv = {1, 8, 0}, xi = 0x30)
public final class BuildersKt {
    public static final Deferred async(CoroutineScope coroutineScope0, CoroutineContext coroutineContext0, CoroutineStart coroutineStart0, Function2 function20) {
        return BuildersKt__Builders_commonKt.async(coroutineScope0, coroutineContext0, coroutineStart0, function20);
    }

    public static Deferred async$default(CoroutineScope coroutineScope0, CoroutineContext coroutineContext0, CoroutineStart coroutineStart0, Function2 function20, int v, Object object0) {
        return BuildersKt__Builders_commonKt.async$default(coroutineScope0, coroutineContext0, coroutineStart0, function20, v, object0);
    }

    public static final Object invoke(CoroutineDispatcher coroutineDispatcher0, Function2 function20, Continuation continuation0) {
        return BuildersKt__Builders_commonKt.invoke(coroutineDispatcher0, function20, continuation0);
    }

    public static final Job launch(CoroutineScope coroutineScope0, CoroutineContext coroutineContext0, CoroutineStart coroutineStart0, Function2 function20) {
        return BuildersKt__Builders_commonKt.launch(coroutineScope0, coroutineContext0, coroutineStart0, function20);
    }

    public static final Object runBlocking(CoroutineContext coroutineContext0, Function2 function20) throws InterruptedException {
        return BuildersKt__BuildersKt.runBlocking(coroutineContext0, function20);
    }

    public static final Object withContext(CoroutineContext coroutineContext0, Function2 function20, Continuation continuation0) {
        return BuildersKt__Builders_commonKt.withContext(coroutineContext0, function20, continuation0);
    }
}

