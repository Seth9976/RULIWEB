package androidx.work;

import androidx.concurrent.futures.CallbackToFutureAdapter.Completer;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.functions.Function0;

public final class WorkerKt..ExternalSyntheticLambda1 implements Runnable {
    public final AtomicBoolean f$0;
    public final Completer f$1;
    public final Function0 f$2;

    public WorkerKt..ExternalSyntheticLambda1(AtomicBoolean atomicBoolean0, Completer callbackToFutureAdapter$Completer0, Function0 function00) {
        this.f$0 = atomicBoolean0;
        this.f$1 = callbackToFutureAdapter$Completer0;
        this.f$2 = function00;
    }

    @Override
    public final void run() {
        WorkerKt.$r8$lambda$06LNzu7McnKR6G06fSbfQ2BCegc(this.f$0, this.f$1, this.f$2);
    }
}

