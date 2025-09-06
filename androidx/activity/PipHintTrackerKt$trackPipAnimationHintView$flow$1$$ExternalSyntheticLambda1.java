package androidx.activity;

import android.view.View;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import kotlinx.coroutines.channels.ProducerScope;

public final class PipHintTrackerKt.trackPipAnimationHintView.flow.1..ExternalSyntheticLambda1 implements ViewTreeObserver.OnScrollChangedListener {
    public final ProducerScope f$0;
    public final View f$1;

    public PipHintTrackerKt.trackPipAnimationHintView.flow.1..ExternalSyntheticLambda1(ProducerScope producerScope0, View view0) {
        this.f$0 = producerScope0;
        this.f$1 = view0;
    }

    @Override  // android.view.ViewTreeObserver$OnScrollChangedListener
    public final void onScrollChanged() {
        androidx.activity.PipHintTrackerKt.trackPipAnimationHintView.flow.1.invokeSuspend$lambda$1(this.f$0, this.f$1);
    }
}

