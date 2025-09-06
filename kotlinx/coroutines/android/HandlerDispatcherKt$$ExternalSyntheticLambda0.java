package kotlinx.coroutines.android;

import android.view.Choreographer.FrameCallback;
import kotlinx.coroutines.CancellableContinuation;

public final class HandlerDispatcherKt..ExternalSyntheticLambda0 implements Choreographer.FrameCallback {
    public final CancellableContinuation f$0;

    public HandlerDispatcherKt..ExternalSyntheticLambda0(CancellableContinuation cancellableContinuation0) {
        this.f$0 = cancellableContinuation0;
    }

    @Override  // android.view.Choreographer$FrameCallback
    public final void doFrame(long v) {
        HandlerDispatcherKt.$r8$lambda$_-s4SOKmmdhN7PexQng1D-Olurw(this.f$0, v);
    }
}

