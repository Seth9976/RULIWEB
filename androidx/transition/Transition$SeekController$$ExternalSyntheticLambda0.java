package androidx.transition;

import androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener;
import androidx.dynamicanimation.animation.DynamicAnimation;

public final class Transition.SeekController..ExternalSyntheticLambda0 implements OnAnimationEndListener {
    public final SeekController f$0;

    public Transition.SeekController..ExternalSyntheticLambda0(SeekController transition$SeekController0) {
        this.f$0 = transition$SeekController0;
    }

    @Override  // androidx.dynamicanimation.animation.DynamicAnimation$OnAnimationEndListener
    public final void onAnimationEnd(DynamicAnimation dynamicAnimation0, boolean z, float f, float f1) {
        this.f$0.lambda$ensureAnimation$0$androidx-transition-Transition$SeekController(dynamicAnimation0, z, f, f1);
    }
}

