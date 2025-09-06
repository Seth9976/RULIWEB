package androidx.work;

import androidx.concurrent.futures.CallbackToFutureAdapter.Completer;
import androidx.concurrent.futures.CallbackToFutureAdapter.Resolver;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineStart;

public final class ListenableFutureKt..ExternalSyntheticLambda1 implements Resolver {
    public final CoroutineContext f$0;
    public final CoroutineStart f$1;
    public final Function2 f$2;

    public ListenableFutureKt..ExternalSyntheticLambda1(CoroutineContext coroutineContext0, CoroutineStart coroutineStart0, Function2 function20) {
        this.f$0 = coroutineContext0;
        this.f$1 = coroutineStart0;
        this.f$2 = function20;
    }

    @Override  // androidx.concurrent.futures.CallbackToFutureAdapter$Resolver
    public final Object attachCompleter(Completer callbackToFutureAdapter$Completer0) {
        return ListenableFutureKt.$r8$lambda$GaXsP0J9ZiqT-NKOjjWkBCoRah8(this.f$0, this.f$1, this.f$2, callbackToFutureAdapter$Completer0);
    }
}

