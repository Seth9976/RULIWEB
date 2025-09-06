package androidx.work;

import androidx.concurrent.futures.CallbackToFutureAdapter.Completer;
import androidx.concurrent.futures.CallbackToFutureAdapter.Resolver;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function0;

public final class WorkerKt..ExternalSyntheticLambda2 implements Resolver {
    public final Executor f$0;
    public final Function0 f$1;

    public WorkerKt..ExternalSyntheticLambda2(Executor executor0, Function0 function00) {
        this.f$0 = executor0;
        this.f$1 = function00;
    }

    @Override  // androidx.concurrent.futures.CallbackToFutureAdapter$Resolver
    public final Object attachCompleter(Completer callbackToFutureAdapter$Completer0) {
        return WorkerKt.$r8$lambda$stbDiVrUcYsUSVFbJy_5-j0wuK4(this.f$0, this.f$1, callbackToFutureAdapter$Completer0);
    }
}

