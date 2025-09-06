package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import androidx.core.view.OneShotPreDrawListener;
import androidx.fragment.R.animator;
import androidx.fragment.R.id;

class FragmentAnim {
    static class AnimationOrAnimator {
        public final Animation animation;
        public final Animator animator;

        AnimationOrAnimator(Animator animator0) {
            this.animation = null;
            this.animator = animator0;
            if(animator0 == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }

        AnimationOrAnimator(Animation animation0) {
            this.animation = animation0;
            this.animator = null;
            if(animation0 == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }
    }

    static class EndViewTransitionAnimation extends AnimationSet implements Runnable {
        private boolean mAnimating;
        private final View mChild;
        private boolean mEnded;
        private final ViewGroup mParent;
        private boolean mTransitionEnded;

        EndViewTransitionAnimation(Animation animation0, ViewGroup viewGroup0, View view0) {
            super(false);
            this.mAnimating = true;
            this.mParent = viewGroup0;
            this.mChild = view0;
            this.addAnimation(animation0);
            viewGroup0.post(this);
        }

        @Override  // android.view.animation.AnimationSet
        public boolean getTransformation(long v, Transformation transformation0) {
            this.mAnimating = true;
            if(this.mEnded) {
                return !this.mTransitionEnded;
            }
            if(!super.getTransformation(v, transformation0)) {
                this.mEnded = true;
                OneShotPreDrawListener.add(this.mParent, this);
            }
            return true;
        }

        @Override  // android.view.animation.Animation
        public boolean getTransformation(long v, Transformation transformation0, float f) {
            this.mAnimating = true;
            if(this.mEnded) {
                return !this.mTransitionEnded;
            }
            if(!super.getTransformation(v, transformation0, f)) {
                this.mEnded = true;
                OneShotPreDrawListener.add(this.mParent, this);
            }
            return true;
        }

        @Override
        public void run() {
            if(!this.mEnded && this.mAnimating) {
                this.mAnimating = false;
                this.mParent.post(this);
                return;
            }
            this.mParent.endViewTransition(this.mChild);
            this.mTransitionEnded = true;
        }
    }

    private static int getNextAnim(Fragment fragment0, boolean z, boolean z1) {
        if(z1) {
            return z ? fragment0.getPopEnterAnim() : fragment0.getPopExitAnim();
        }
        return z ? fragment0.getEnterAnim() : fragment0.getExitAnim();
    }

    static AnimationOrAnimator loadAnimation(Context context0, Fragment fragment0, boolean z, boolean z1) {
        int v = fragment0.getNextTransition();
        int v1 = FragmentAnim.getNextAnim(fragment0, z, z1);
        fragment0.setAnimations(0, 0, 0, 0);
        if(fragment0.mContainer != null && fragment0.mContainer.getTag(id.visible_removing_fragment_view_tag) != null) {
            fragment0.mContainer.setTag(id.visible_removing_fragment_view_tag, null);
        }
        if(fragment0.mContainer != null && fragment0.mContainer.getLayoutTransition() != null) {
            return null;
        }
        if(v1 == 0 && v != 0) {
            v1 = FragmentAnim.transitToAnimResourceId(context0, v, z);
        }
        if(v1 == 0) {
            return null;
        }
        else {
            boolean z2 = "anim".equals(context0.getResources().getResourceTypeName(v1));
            if(!z2) {
                goto label_17;
            }
            try {
                Animation animation0 = AnimationUtils.loadAnimation(context0, v1);
                return animation0 != null ? new AnimationOrAnimator(animation0) : null;
            }
            catch(Resources.NotFoundException resources$NotFoundException0) {
                throw resources$NotFoundException0;
            }
            catch(RuntimeException unused_ex) {
            }
            try {
            label_17:
                Animator animator0 = AnimatorInflater.loadAnimator(context0, v1);
                return animator0 == null ? null : new AnimationOrAnimator(animator0);
            }
            catch(RuntimeException runtimeException0) {
            }
        }
        if(z2) {
            throw runtimeException0;
        }
        Animation animation1 = AnimationUtils.loadAnimation(context0, v1);
        return animation1 == null ? null : new AnimationOrAnimator(animation1);
    }

    private static int toActivityTransitResId(Context context0, int v) {
        TypedArray typedArray0 = context0.obtainStyledAttributes(0x1030001, new int[]{v});
        int v1 = typedArray0.getResourceId(0, -1);
        typedArray0.recycle();
        return v1;
    }

    private static int transitToAnimResourceId(Context context0, int v, boolean z) {
        switch(v) {
            case 0x1001: {
                return z ? animator.fragment_open_enter : animator.fragment_open_exit;
            }
            case 0x1003: {
                return z ? animator.fragment_fade_enter : animator.fragment_fade_exit;
            }
            case 4100: {
                return z ? FragmentAnim.toActivityTransitResId(context0, 0x10100B8) : FragmentAnim.toActivityTransitResId(context0, 0x10100B9);
            }
            case 0x2002: {
                return z ? animator.fragment_close_enter : animator.fragment_close_exit;
            }
            case 0x2005: {
                return z ? FragmentAnim.toActivityTransitResId(context0, 0x10100BA) : FragmentAnim.toActivityTransitResId(context0, 0x10100BB);
            }
            default: {
                return -1;
            }
        }
    }
}

