package androidx.core.view;

import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.view.View;

public final class ViewPropertyAnimatorCompat..ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final ViewPropertyAnimatorUpdateListener f$0;
    public final View f$1;

    public ViewPropertyAnimatorCompat..ExternalSyntheticLambda0(ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener0, View view0) {
        this.f$0 = viewPropertyAnimatorUpdateListener0;
        this.f$1 = view0;
    }

    @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator0) {
        ViewPropertyAnimatorCompat.lambda$setUpdateListener$0(this.f$0, this.f$1, valueAnimator0);
    }
}

