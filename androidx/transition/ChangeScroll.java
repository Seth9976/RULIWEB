package androidx.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class ChangeScroll extends Transition {
    private static final String[] PROPERTIES = null;
    private static final String PROPNAME_SCROLL_X = "android:changeScroll:x";
    private static final String PROPNAME_SCROLL_Y = "android:changeScroll:y";

    static {
        ChangeScroll.PROPERTIES = new String[]{"android:changeScroll:x", "android:changeScroll:y"};
    }

    public ChangeScroll() {
    }

    public ChangeScroll(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
    }

    @Override  // androidx.transition.Transition
    public void captureEndValues(TransitionValues transitionValues0) {
        this.captureValues(transitionValues0);
    }

    @Override  // androidx.transition.Transition
    public void captureStartValues(TransitionValues transitionValues0) {
        this.captureValues(transitionValues0);
    }

    private void captureValues(TransitionValues transitionValues0) {
        Integer integer0 = transitionValues0.view.getScrollX();
        transitionValues0.values.put("android:changeScroll:x", integer0);
        Integer integer1 = transitionValues0.view.getScrollY();
        transitionValues0.values.put("android:changeScroll:y", integer1);
    }

    @Override  // androidx.transition.Transition
    public Animator createAnimator(ViewGroup viewGroup0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        ObjectAnimator objectAnimator0;
        Animator animator0 = null;
        if(transitionValues0 != null && transitionValues1 != null) {
            View view0 = transitionValues1.view;
            int v = (int)(((Integer)transitionValues0.values.get("android:changeScroll:x")));
            int v1 = (int)(((Integer)transitionValues1.values.get("android:changeScroll:x")));
            int v2 = (int)(((Integer)transitionValues0.values.get("android:changeScroll:y")));
            int v3 = (int)(((Integer)transitionValues1.values.get("android:changeScroll:y")));
            if(v == v1) {
                objectAnimator0 = null;
            }
            else {
                view0.setScrollX(v);
                objectAnimator0 = ObjectAnimator.ofInt(view0, "scrollX", new int[]{v, v1});
            }
            if(v2 != v3) {
                view0.setScrollY(v2);
                animator0 = ObjectAnimator.ofInt(view0, "scrollY", new int[]{v2, v3});
            }
            return TransitionUtils.mergeAnimators(objectAnimator0, animator0);
        }
        return null;
    }

    @Override  // androidx.transition.Transition
    public String[] getTransitionProperties() {
        return ChangeScroll.PROPERTIES;
    }

    @Override  // androidx.transition.Transition
    public boolean isSeekingSupported() {
        return true;
    }
}

