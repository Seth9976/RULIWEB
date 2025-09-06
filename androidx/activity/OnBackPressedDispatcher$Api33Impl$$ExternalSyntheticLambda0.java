package androidx.activity;

import android.window.OnBackInvokedCallback;
import kotlin.jvm.functions.Function0;

public final class OnBackPressedDispatcher.Api33Impl..ExternalSyntheticLambda0 implements OnBackInvokedCallback {
    public final Function0 f$0;

    public OnBackPressedDispatcher.Api33Impl..ExternalSyntheticLambda0(Function0 function00) {
        this.f$0 = function00;
    }

    @Override  // android.window.OnBackInvokedCallback
    public final void onBackInvoked() {
        Api33Impl.createOnBackInvokedCallback$lambda$0(this.f$0);
    }
}

