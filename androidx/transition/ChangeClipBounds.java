package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class ChangeClipBounds extends Transition {
    static class Listener extends AnimatorListenerAdapter implements TransitionListener {
        private final Rect mEnd;
        private final Rect mStart;
        private final View mView;

        Listener(View view0, Rect rect0, Rect rect1) {
            this.mView = view0;
            this.mStart = rect0;
            this.mEnd = rect1;
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationEnd(Animator animator0) {
            this.onAnimationEnd(animator0, false);
        }

        @Override  // android.animation.Animator$AnimatorListener
        public void onAnimationEnd(Animator animator0, boolean z) {
            if(!z) {
                this.mView.setClipBounds(this.mEnd);
                return;
            }
            this.mView.setClipBounds(this.mStart);
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
            Rect rect0 = this.mView.getClipBounds();
            if(rect0 == null) {
                rect0 = ChangeClipBounds.NULL_SENTINEL;
            }
            this.mView.setTag(id.transition_clip, rect0);
            this.mView.setClipBounds(this.mEnd);
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionResume(Transition transition0) {
            Rect rect0 = (Rect)this.mView.getTag(id.transition_clip);
            this.mView.setClipBounds(rect0);
            this.mView.setTag(id.transition_clip, null);
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionStart(Transition transition0) {
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionStart(Transition transition0, boolean z) {
            Transition.TransitionListener.-CC.$default$onTransitionStart(this, transition0, z);
        }
    }

    static final Rect NULL_SENTINEL = null;
    private static final String PROPNAME_BOUNDS = "android:clipBounds:bounds";
    private static final String PROPNAME_CLIP = "android:clipBounds:clip";
    private static final String[] sTransitionProperties;

    static {
        ChangeClipBounds.sTransitionProperties = new String[]{"android:clipBounds:clip"};
        ChangeClipBounds.NULL_SENTINEL = new Rect();
    }

    public ChangeClipBounds() {
    }

    public ChangeClipBounds(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
    }

    @Override  // androidx.transition.Transition
    public void captureEndValues(TransitionValues transitionValues0) {
        this.captureValues(transitionValues0, false);
    }

    @Override  // androidx.transition.Transition
    public void captureStartValues(TransitionValues transitionValues0) {
        this.captureValues(transitionValues0, true);
    }

    private void captureValues(TransitionValues transitionValues0, boolean z) {
        Rect rect0 = null;
        View view0 = transitionValues0.view;
        if(view0.getVisibility() != 8) {
            Rect rect1 = z ? ((Rect)view0.getTag(id.transition_clip)) : null;
            if(rect1 == null) {
                rect1 = view0.getClipBounds();
            }
            if(rect1 != ChangeClipBounds.NULL_SENTINEL) {
                rect0 = rect1;
            }
            transitionValues0.values.put("android:clipBounds:clip", rect0);
            if(rect0 == null) {
                Rect rect2 = new Rect(0, 0, view0.getWidth(), view0.getHeight());
                transitionValues0.values.put("android:clipBounds:bounds", rect2);
            }
        }
    }

    @Override  // androidx.transition.Transition
    public Animator createAnimator(ViewGroup viewGroup0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        Animator animator0 = null;
        if(transitionValues0 != null && transitionValues1 != null && transitionValues0.values.containsKey("android:clipBounds:clip") && transitionValues1.values.containsKey("android:clipBounds:clip")) {
            Rect rect0 = (Rect)transitionValues0.values.get("android:clipBounds:clip");
            Rect rect1 = (Rect)transitionValues1.values.get("android:clipBounds:clip");
            if(rect0 == null && rect1 == null) {
                return null;
            }
            Rect rect2 = rect0 == null ? ((Rect)transitionValues0.values.get("android:clipBounds:bounds")) : rect0;
            Rect rect3 = rect1 == null ? ((Rect)transitionValues1.values.get("android:clipBounds:bounds")) : rect1;
            if(rect2.equals(rect3)) {
                return null;
            }
            transitionValues1.view.setClipBounds(rect0);
            RectEvaluator rectEvaluator0 = new RectEvaluator(new Rect());
            animator0 = ObjectAnimator.ofObject(transitionValues1.view, ViewUtils.CLIP_BOUNDS, rectEvaluator0, new Rect[]{rect2, rect3});
            Listener changeClipBounds$Listener0 = new Listener(transitionValues1.view, rect0, rect1);
            ((ObjectAnimator)animator0).addListener(changeClipBounds$Listener0);
            this.addListener(changeClipBounds$Listener0);
        }
        return animator0;
    }

    @Override  // androidx.transition.Transition
    public String[] getTransitionProperties() {
        return ChangeClipBounds.sTransitionProperties;
    }

    @Override  // androidx.transition.Transition
    public boolean isSeekingSupported() {
        return true;
    }
}

