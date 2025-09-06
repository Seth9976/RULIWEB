package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.view.View;

class TranslationAnimationCreator {
    static class TransitionPositionListener extends AnimatorListenerAdapter implements TransitionListener {
        private boolean mIsTransitionCanceled;
        private final View mMovingView;
        private float mPausedX;
        private float mPausedY;
        private final float mTerminalX;
        private final float mTerminalY;
        private int[] mTransitionPosition;
        private final View mViewInHierarchy;

        TransitionPositionListener(View view0, View view1, float f, float f1) {
            this.mMovingView = view0;
            this.mViewInHierarchy = view1;
            this.mTerminalX = f;
            this.mTerminalY = f1;
            int[] arr_v = (int[])view1.getTag(id.transition_position);
            this.mTransitionPosition = arr_v;
            if(arr_v != null) {
                view1.setTag(id.transition_position, null);
            }
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationCancel(Animator animator0) {
            this.mIsTransitionCanceled = true;
            this.mMovingView.setTranslationX(this.mTerminalX);
            this.mMovingView.setTranslationY(this.mTerminalY);
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationEnd(Animator animator0) {
            this.onAnimationEnd(animator0, false);
        }

        @Override  // android.animation.Animator$AnimatorListener
        public void onAnimationEnd(Animator animator0, boolean z) {
            if(!z) {
                this.mMovingView.setTranslationX(this.mTerminalX);
                this.mMovingView.setTranslationY(this.mTerminalY);
            }
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionCancel(Transition transition0) {
            this.mIsTransitionCanceled = true;
            this.mMovingView.setTranslationX(this.mTerminalX);
            this.mMovingView.setTranslationY(this.mTerminalY);
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionEnd(Transition transition0) {
            this.onTransitionEnd(transition0, false);
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionEnd(Transition transition0, boolean z) {
            if(!this.mIsTransitionCanceled) {
                this.mViewInHierarchy.setTag(id.transition_position, null);
            }
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionPause(Transition transition0) {
            this.setInterruptedPosition();
            this.mPausedX = this.mMovingView.getTranslationX();
            this.mPausedY = this.mMovingView.getTranslationY();
            this.mMovingView.setTranslationX(this.mTerminalX);
            this.mMovingView.setTranslationY(this.mTerminalY);
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionResume(Transition transition0) {
            this.mMovingView.setTranslationX(this.mPausedX);
            this.mMovingView.setTranslationY(this.mPausedY);
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionStart(Transition transition0) {
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionStart(Transition transition0, boolean z) {
            Transition.TransitionListener.-CC.$default$onTransitionStart(this, transition0, z);
        }

        private void setInterruptedPosition() {
            if(this.mTransitionPosition == null) {
                this.mTransitionPosition = new int[2];
            }
            this.mMovingView.getLocationOnScreen(this.mTransitionPosition);
            this.mViewInHierarchy.setTag(id.transition_position, this.mTransitionPosition);
        }
    }

    static Animator createAnimation(View view0, TransitionValues transitionValues0, int v, int v1, float f, float f1, float f2, float f3, TimeInterpolator timeInterpolator0, Transition transition0) {
        float f4 = view0.getTranslationX();
        float f5 = view0.getTranslationY();
        int[] arr_v = (int[])transitionValues0.view.getTag(id.transition_position);
        if(arr_v != null) {
            f = ((float)(arr_v[0] - v)) + f4;
            f1 = ((float)(arr_v[1] - v1)) + f5;
        }
        view0.setTranslationX(f);
        view0.setTranslationY(f1);
        if(f == f2 && f1 == f3) {
            return null;
        }
        Animator animator0 = ObjectAnimator.ofPropertyValuesHolder(view0, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.TRANSLATION_X, new float[]{f, f2}), PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, new float[]{f1, f3})});
        TransitionPositionListener translationAnimationCreator$TransitionPositionListener0 = new TransitionPositionListener(view0, transitionValues0.view, f4, f5);
        transition0.addListener(translationAnimationCreator$TransitionPositionListener0);
        ((ObjectAnimator)animator0).addListener(translationAnimationCreator$TransitionPositionListener0);
        ((ObjectAnimator)animator0).setInterpolator(timeInterpolator0);
        return animator0;
    }
}

