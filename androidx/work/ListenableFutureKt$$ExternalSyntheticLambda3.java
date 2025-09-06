package androidx.work;

import androidx.concurrent.futures.CallbackToFutureAdapter.Completer;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.functions.Function0;

public final class ListenableFutureKt..ExternalSyntheticLambda3 implements Runnable {
    public final AtomicBoolean f$0;
    public final Completer f$1;
    public final Function0 f$2;

    public ListenableFutureKt..ExternalSyntheticLambda3(AtomicBoolean atomicBoolean0, Completer callbackToFutureAdapter$Completer0, Function0 function00) {
        this.f$0 = atomicBoolean0;
        this.f$1 = callbackToFutureAdapter$Completer0;
        this.f$2 = function00;
    }

    @Override
    public final void run() {
        ListenableFutureKt.$r8$lambda$2J7WTnmfyHeSyx3GGU57K1DCNjw(this.f$0, this.f$1, this.f$2);
    }
}

