package androidx.browser.trusted;

import androidx.concurrent.futures.CallbackToFutureAdapter.Completer;
import androidx.concurrent.futures.CallbackToFutureAdapter.Resolver;

public final class ConnectionHolder..ExternalSyntheticLambda0 implements Resolver {
    public final ConnectionHolder f$0;

    public ConnectionHolder..ExternalSyntheticLambda0(ConnectionHolder connectionHolder0) {
        this.f$0 = connectionHolder0;
    }

    @Override  // androidx.concurrent.futures.CallbackToFutureAdapter$Resolver
    public final Object attachCompleter(Completer callbackToFutureAdapter$Completer0) {
        return this.f$0.lambda$getServiceWrapper$0$androidx-browser-trusted-ConnectionHolder(callbackToFutureAdapter$Completer0);
    }
}

