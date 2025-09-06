package androidx.work;

import androidx.concurrent.futures.CallbackToFutureAdapter.Completer;
import androidx.concurrent.futures.CallbackToFutureAdapter.Resolver;
import androidx.lifecycle.MutableLiveData;
import java.util.concurrent.Executor;
import kotlin.jvm.functions.Function0;

public final class OperationKt..ExternalSyntheticLambda0 implements Resolver {
    public final Executor f$0;
    public final Tracer f$1;
    public final String f$2;
    public final Function0 f$3;
    public final MutableLiveData f$4;

    public OperationKt..ExternalSyntheticLambda0(Executor executor0, Tracer tracer0, String s, Function0 function00, MutableLiveData mutableLiveData0) {
        this.f$0 = executor0;
        this.f$1 = tracer0;
        this.f$2 = s;
        this.f$3 = function00;
        this.f$4 = mutableLiveData0;
    }

    @Override  // androidx.concurrent.futures.CallbackToFutureAdapter$Resolver
    public final Object attachCompleter(Completer callbackToFutureAdapter$Completer0) {
        return OperationKt.$r8$lambda$4AmAQmnwY87AwH_dAIVR-wuDub0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, callbackToFutureAdapter$Completer0);
    }
}

