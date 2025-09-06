package androidx.work;

import androidx.concurrent.futures.CallbackToFutureAdapter.Completer;
import androidx.lifecycle.MutableLiveData;
import kotlin.jvm.functions.Function0;

public final class OperationKt..ExternalSyntheticLambda1 implements Runnable {
    public final Tracer f$0;
    public final String f$1;
    public final Function0 f$2;
    public final MutableLiveData f$3;
    public final Completer f$4;

    public OperationKt..ExternalSyntheticLambda1(Tracer tracer0, String s, Function0 function00, MutableLiveData mutableLiveData0, Completer callbackToFutureAdapter$Completer0) {
        this.f$0 = tracer0;
        this.f$1 = s;
        this.f$2 = function00;
        this.f$3 = mutableLiveData0;
        this.f$4 = callbackToFutureAdapter$Completer0;
    }

    @Override
    public final void run() {
        OperationKt.$r8$lambda$XKAkIiEN7OgIvwuLUZRQpJhjmyE(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}

