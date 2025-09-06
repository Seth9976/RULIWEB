package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.res.TypedArrayUtils;

public class Fade extends Visibility {
    static class FadeAnimatorListener extends AnimatorListenerAdapter implements TransitionListener {
        private boolean mLayerTypeChanged;
        private final View mView;

        FadeAnimatorListener(View view0) {
            this.mLayerTypeChanged = false;
            this.mView = view0;
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationCancel(Animator animator0) {
            ViewUtils.setTransitionAlpha(this.mView, 1.0f);
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationEnd(Animator animator0) {
            this.onAnimationEnd(animator0, false);
        }

        @Override  // android.animation.Animator$AnimatorListener
        public void onAnimationEnd(Animator animator0, boolean z) {
            if(this.mLayerTypeChanged) {
                this.mView.setLayerType(0, null);
            }
            if(!z) {
                ViewUtils.setTransitionAlpha(this.mView, 1.0f);
            }
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationStart(Animator animator0) {
            if(this.mView.hasOverlappingRendering() && this.mView.getLayerType() == 0) {
                this.mLayerTypeChanged = true;
                this.mView.setLayerType(2, null);
            }
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionCancel(Transition transition0) {
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionEnd(Transition transition0) {
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionEnd(Transition transition0, boolean z) {
            Transition.TransitionListener.-CC.$default$onTransitionEnd(this, transition0, z);
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionPause(Transition transition0) {
            float f = this.mView.getVisibility() == 0 ? ViewUtils.getTransitionAlpha(this.mView) : 0.0f;
            this.mView.setTag(id.transition_pause_alpha, f);
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionResume(Transition transition0) {
            this.mView.setTag(id.transition_pause_alpha, null);
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionStart(Transition transition0) {
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionStart(Transition transition0, boolean z) {
        }
    }

    public static final int IN = 1;
    private static final String LOG_TAG = "Fade";
    public static final int OUT = 2;
    private static final String PROPNAME_TRANSITION_ALPHA = "android:fade:transitionAlpha";

    public Fade() {
    }

    public Fade(int v) {
        this.setMode(v);
    }

    public Fade(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, Styleable.FADE);
        this.setMode(TypedArrayUtils.getNamedInt(typedArray0, ((XmlResourceParser)attributeSet0), "fadingMode", 0, this.getMode()));
        typedArray0.recycle();
    }

    @Override  // androidx.transition.Visibility
    public void captureStartValues(TransitionValues transitionValues0) {
        super.captureStartValues(transitionValues0);
        Float float0 = (Float)transitionValues0.view.getTag(id.transition_pause_alpha);
        if(float0 == null) {
            float0 = transitionValues0.view.getVisibility() == 0 ? ViewUtils.getTransitionAlpha(transitionValues0.view) : 0.0f;
        }
        transitionValues0.values.put("android:fade:transitionAlpha", float0);
    }

    private Animator createAnimation(View view0, float f, float f1) {
        if(f == f1) {
            return null;
        }
        ViewUtils.setTransitionAlpha(view0, f);
        Animator animator0 = ObjectAnimator.ofFloat(view0, ViewUtils.TRANSITION_ALPHA, new float[]{f1});
        FadeAnimatorListener fade$FadeAnimatorListener0 = new FadeAnimatorListener(view0);
        ((ObjectAnimator)animator0).addListener(fade$FadeAnimatorListener0);
        this.getRootTransition().addListener(fade$FadeAnimatorListener0);
        return animator0;
    }

    private static float getStartAlpha(TransitionValues transitionValues0, float f) {
        if(transitionValues0 != null) {
            Float float0 = (Float)transitionValues0.values.get("android:fade:transitionAlpha");
            return float0 == null ? f : ((float)float0);
        }
        return f;
    }

    @Override  // androidx.transition.Transition
    public boolean isSeekingSupported() {
        return true;
    }

    @Override  // androidx.transition.Visibility
    public Animator onAppear(ViewGroup viewGroup0, View view0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        return this.createAnimation(view0, Fade.getStartAlpha(transitionValues0, 0.0f), 1.0f);
    }

    @Override  // androidx.transition.Visibility
    public Animator onDisappear(ViewGroup viewGroup0, View view0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        Animator animator0 = this.createAnimation(view0, Fade.getStartAlpha(transitionValues0, 1.0f), 0.0f);
        if(animator0 == null) {
            ViewUtils.setTransitionAlpha(view0, Fade.getStartAlpha(transitionValues1, 1.0f));
        }
        return animator0;
    }
}

