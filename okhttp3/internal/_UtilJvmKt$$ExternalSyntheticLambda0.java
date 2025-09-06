package okhttp3.internal;

import okhttp3.Call;
import okhttp3.EventListener.Factory;
import okhttp3.EventListener;

public final class _UtilJvmKt..ExternalSyntheticLambda0 implements Factory {
    public final EventListener f$0;

    public _UtilJvmKt..ExternalSyntheticLambda0(EventListener eventListener0) {
        this.f$0 = eventListener0;
    }

    @Override  // okhttp3.EventListener$Factory
    public final EventListener create(Call call0) {
        return _UtilJvmKt.$r8$lambda$fH14ds-wEUmL5I-DC0Q1j9BfXO8(this.f$0, call0);
    }
}

