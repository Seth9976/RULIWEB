package androidx.work;

import androidx.concurrent.futures.CallbackToFutureAdapter.Completer;
import androidx.concurrent.futures.CallbackToFutureAdapter.Resolver;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function0;

public final class ListenableFutureKt..ExternalSyntheticLambda0 implements Resolver {
    public final Executor f$0;
    public final String f$1;
    public final Function0 f$2;

    public ListenableFutureKt..ExternalSyntheticLambda0(Executor executor0, String s, Function0 function00) {
        this.f$0 = executor0;
        this.f$1 = s;
        this.f$2 = function00;
    }

    @Override  // androidx.concurrent.futures.CallbackToFutureAdapter$Resolver
    public final Object attachCompleter(Completer callbackToFutureAdapter$Completer0) {
        return ListenableFutureKt.$r8$lambda$X5HWWttRZ_Ir0xD9aqd6GXUt6fY(this.f$0, this.f$1, this.f$2, callbackToFutureAdapter$Completer0);
    }
}

