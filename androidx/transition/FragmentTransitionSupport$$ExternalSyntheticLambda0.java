package androidx.transition;

import androidx.core.os.CancellationSignal.OnCancelListener;

public final class FragmentTransitionSupport..ExternalSyntheticLambda0 implements OnCancelListener {
    public final Runnable f$0;
    public final Transition f$1;
    public final Runnable f$2;

    public FragmentTransitionSupport..ExternalSyntheticLambda0(Runnable runnable0, Transition transition0, Runnable runnable1) {
        this.f$0 = runnable0;
        this.f$1 = transition0;
        this.f$2 = runnable1;
    }

    @Override  // androidx.core.os.CancellationSignal$OnCancelListener
    public final void onCancel() {
        FragmentTransitionSupport.lambda$setListenerForTransitionEnd$0(this.f$0, this.f$1, this.f$2);
    }
}

