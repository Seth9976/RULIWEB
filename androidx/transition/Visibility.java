package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.core.content.res.TypedArrayUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class Visibility extends Transition {
    static class DisappearListener extends AnimatorListenerAdapter implements TransitionListener {
        boolean mCanceled;
        private final int mFinalVisibility;
        private boolean mLayoutSuppressed;
        private final ViewGroup mParent;
        private final boolean mSuppressLayout;
        private final View mView;

        DisappearListener(View view0, int v, boolean z) {
            this.mCanceled = false;
            this.mView = view0;
            this.mFinalVisibility = v;
            this.mParent = (ViewGroup)view0.getParent();
            this.mSuppressLayout = z;
            this.suppressLayout(true);
        }

        private void hideViewWhenNotCanceled() {
            if(!this.mCanceled) {
                ViewUtils.setTransitionVisibility(this.mView, this.mFinalVisibility);
                ViewGroup viewGroup0 = this.mParent;
                if(viewGroup0 != null) {
                    viewGroup0.invalidate();
                }
            }
            this.suppressLayout(false);
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationCancel(Animator animator0) {
            this.mCanceled = true;
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationEnd(Animator animator0) {
            this.hideViewWhenNotCanceled();
        }

        @Override  // android.animation.Animator$AnimatorListener
        public void onAnimationEnd(Animator animator0, boolean z) {
            if(!z) {
                this.hideViewWhenNotCanceled();
            }
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationRepeat(Animator animator0) {
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationStart(Animator animator0) {
        }

        @Override  // android.animation.Animator$AnimatorListener
        public void onAnimationStart(Animator animator0, boolean z) {
            if(z) {
                ViewUtils.setTransitionVisibility(this.mView, 0);
                ViewGroup viewGroup0 = this.mParent;
                if(viewGroup0 != null) {
                    viewGroup0.invalidate();
                }
            }
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionCancel(Transition transition0) {
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionEnd(Transition transition0) {
            transition0.removeListener(this);
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionEnd(Transition transition0, boolean z) {
            Transition.TransitionListener.-CC.$default$onTransitionEnd(this, transition0, z);
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionPause(Transition transition0) {
            this.suppressLayout(false);
            if(!this.mCanceled) {
                ViewUtils.setTransitionVisibility(this.mView, this.mFinalVisibility);
            }
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionResume(Transition transition0) {
            this.suppressLayout(true);
            if(!this.mCanceled) {
                ViewUtils.setTransitionVisibility(this.mView, 0);
            }
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionStart(Transition transition0) {
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionStart(Transition transition0, boolean z) {
            Transition.TransitionListener.-CC.$default$onTransitionStart(this, transition0, z);
        }

        private void suppressLayout(boolean z) {
            if(this.mSuppressLayout && this.mLayoutSuppressed != z) {
                ViewGroup viewGroup0 = this.mParent;
                if(viewGroup0 != null) {
                    this.mLayoutSuppressed = z;
                    ViewGroupUtils.suppressLayout(viewGroup0, z);
                }
            }
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    class OverlayListener extends AnimatorListenerAdapter implements TransitionListener {
        private boolean mHasOverlay;
        private final ViewGroup mOverlayHost;
        private final View mOverlayView;
        private final View mStartView;

        OverlayListener(ViewGroup viewGroup0, View view0, View view1) {
            this.mHasOverlay = true;
            this.mOverlayHost = viewGroup0;
            this.mOverlayView = view0;
            this.mStartView = view1;
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationEnd(Animator animator0) {
            this.removeFromOverlay();
        }

        @Override  // android.animation.Animator$AnimatorListener
        public void onAnimationEnd(Animator animator0, boolean z) {
            if(!z) {
                this.removeFromOverlay();
            }
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationPause(Animator animator0) {
            this.mOverlayHost.getOverlay().remove(this.mOverlayView);
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationResume(Animator animator0) {
            if(this.mOverlayView.getParent() == null) {
                this.mOverlayHost.getOverlay().add(this.mOverlayView);
                return;
            }
            Visibility.this.cancel();
        }

        @Override  // android.animation.Animator$AnimatorListener
        public void onAnimationStart(Animator animator0, boolean z) {
            if(z) {
                this.mStartView.setTag(id.save_overlay_view, this.mOverlayView);
                this.mOverlayHost.getOverlay().add(this.mOverlayView);
                this.mHasOverlay = true;
            }
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionCancel(Transition transition0) {
            if(this.mHasOverlay) {
                this.removeFromOverlay();
            }
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionEnd(Transition transition0) {
            transition0.removeListener(this);
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionEnd(Transition transition0, boolean z) {
            Transition.TransitionListener.-CC.$default$onTransitionEnd(this, transition0, z);
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionPause(Transition transition0) {
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionResume(Transition transition0) {
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionStart(Transition transition0) {
        }

        @Override  // androidx.transition.Transition$TransitionListener
        public void onTransitionStart(Transition transition0, boolean z) {
            Transition.TransitionListener.-CC.$default$onTransitionStart(this, transition0, z);
        }

        private void removeFromOverlay() {
            this.mStartView.setTag(id.save_overlay_view, null);
            this.mOverlayHost.getOverlay().remove(this.mOverlayView);
            this.mHasOverlay = false;
        }
    }

    static class VisibilityInfo {
        ViewGroup mEndParent;
        int mEndVisibility;
        boolean mFadeIn;
        ViewGroup mStartParent;
        int mStartVisibility;
        boolean mVisibilityChange;

    }

    public static final int MODE_IN = 1;
    public static final int MODE_OUT = 2;
    private static final String PROPNAME_PARENT = "android:visibility:parent";
    private static final String PROPNAME_SCREEN_LOCATION = "android:visibility:screenLocation";
    static final String PROPNAME_VISIBILITY = "android:visibility:visibility";
    private int mMode;
    private static final String[] sTransitionProperties;

    static {
        Visibility.sTransitionProperties = new String[]{"android:visibility:visibility", "android:visibility:parent"};
    }

    public Visibility() {
        this.mMode = 3;
    }

    public Visibility(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mMode = 3;
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, Styleable.VISIBILITY_TRANSITION);
        int v = TypedArrayUtils.getNamedInt(typedArray0, ((XmlResourceParser)attributeSet0), "transitionVisibilityMode", 0, 0);
        typedArray0.recycle();
        if(v != 0) {
            this.setMode(v);
        }
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
        Integer integer0 = transitionValues0.view.getVisibility();
        transitionValues0.values.put("android:visibility:visibility", integer0);
        ViewParent viewParent0 = transitionValues0.view.getParent();
        transitionValues0.values.put("android:visibility:parent", viewParent0);
        int[] arr_v = new int[2];
        transitionValues0.view.getLocationOnScreen(arr_v);
        transitionValues0.values.put("android:visibility:screenLocation", arr_v);
    }

    @Override  // androidx.transition.Transition
    public Animator createAnimator(ViewGroup viewGroup0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        VisibilityInfo visibility$VisibilityInfo0 = this.getVisibilityChangeInfo(transitionValues0, transitionValues1);
        if(visibility$VisibilityInfo0.mVisibilityChange && (visibility$VisibilityInfo0.mStartParent != null || visibility$VisibilityInfo0.mEndParent != null)) {
            return visibility$VisibilityInfo0.mFadeIn ? this.onAppear(viewGroup0, transitionValues0, visibility$VisibilityInfo0.mStartVisibility, transitionValues1, visibility$VisibilityInfo0.mEndVisibility) : this.onDisappear(viewGroup0, transitionValues0, visibility$VisibilityInfo0.mStartVisibility, transitionValues1, visibility$VisibilityInfo0.mEndVisibility);
        }
        return null;
    }

    public int getMode() {
        return this.mMode;
    }

    @Override  // androidx.transition.Transition
    public String[] getTransitionProperties() {
        return Visibility.sTransitionProperties;
    }

    private VisibilityInfo getVisibilityChangeInfo(TransitionValues transitionValues0, TransitionValues transitionValues1) {
        VisibilityInfo visibility$VisibilityInfo0 = new VisibilityInfo();
        visibility$VisibilityInfo0.mVisibilityChange = false;
        visibility$VisibilityInfo0.mFadeIn = false;
        if(transitionValues0 == null || !transitionValues0.values.containsKey("android:visibility:visibility")) {
            visibility$VisibilityInfo0.mStartVisibility = -1;
            visibility$VisibilityInfo0.mStartParent = null;
        }
        else {
            visibility$VisibilityInfo0.mStartVisibility = (int)(((Integer)transitionValues0.values.get("android:visibility:visibility")));
            visibility$VisibilityInfo0.mStartParent = (ViewGroup)transitionValues0.values.get("android:visibility:parent");
        }
        if(transitionValues1 == null || !transitionValues1.values.containsKey("android:visibility:visibility")) {
            visibility$VisibilityInfo0.mEndVisibility = -1;
            visibility$VisibilityInfo0.mEndParent = null;
        }
        else {
            visibility$VisibilityInfo0.mEndVisibility = (int)(((Integer)transitionValues1.values.get("android:visibility:visibility")));
            visibility$VisibilityInfo0.mEndParent = (ViewGroup)transitionValues1.values.get("android:visibility:parent");
        }
        if(transitionValues0 == null || transitionValues1 == null) {
            if(transitionValues0 == null && visibility$VisibilityInfo0.mEndVisibility == 0) {
                visibility$VisibilityInfo0.mFadeIn = true;
                visibility$VisibilityInfo0.mVisibilityChange = true;
                return visibility$VisibilityInfo0;
            }
            if(transitionValues1 == null && visibility$VisibilityInfo0.mStartVisibility == 0) {
                visibility$VisibilityInfo0.mFadeIn = false;
                visibility$VisibilityInfo0.mVisibilityChange = true;
            }
        }
        else if(visibility$VisibilityInfo0.mStartVisibility != visibility$VisibilityInfo0.mEndVisibility || visibility$VisibilityInfo0.mStartParent != visibility$VisibilityInfo0.mEndParent) {
            if(visibility$VisibilityInfo0.mStartVisibility == visibility$VisibilityInfo0.mEndVisibility) {
                if(visibility$VisibilityInfo0.mEndParent == null) {
                    visibility$VisibilityInfo0.mFadeIn = false;
                    visibility$VisibilityInfo0.mVisibilityChange = true;
                    return visibility$VisibilityInfo0;
                }
                if(visibility$VisibilityInfo0.mStartParent == null) {
                    visibility$VisibilityInfo0.mFadeIn = true;
                    visibility$VisibilityInfo0.mVisibilityChange = true;
                    return visibility$VisibilityInfo0;
                }
            }
            else {
                if(visibility$VisibilityInfo0.mStartVisibility == 0) {
                    visibility$VisibilityInfo0.mFadeIn = false;
                    visibility$VisibilityInfo0.mVisibilityChange = true;
                    return visibility$VisibilityInfo0;
                }
                if(visibility$VisibilityInfo0.mEndVisibility == 0) {
                    visibility$VisibilityInfo0.mFadeIn = true;
                    visibility$VisibilityInfo0.mVisibilityChange = true;
                    return visibility$VisibilityInfo0;
                }
            }
        }
        return visibility$VisibilityInfo0;
    }

    @Override  // androidx.transition.Transition
    public boolean isTransitionRequired(TransitionValues transitionValues0, TransitionValues transitionValues1) {
        if(transitionValues0 == null && transitionValues1 == null) {
            return false;
        }
        if(transitionValues0 != null && transitionValues1 != null && transitionValues1.values.containsKey("android:visibility:visibility") != transitionValues0.values.containsKey("android:visibility:visibility")) {
            return false;
        }
        VisibilityInfo visibility$VisibilityInfo0 = this.getVisibilityChangeInfo(transitionValues0, transitionValues1);
        return visibility$VisibilityInfo0.mVisibilityChange && (visibility$VisibilityInfo0.mStartVisibility == 0 || visibility$VisibilityInfo0.mEndVisibility == 0);
    }

    public boolean isVisible(TransitionValues transitionValues0) {
        return transitionValues0 == null ? false : ((int)(((Integer)transitionValues0.values.get("android:visibility:visibility")))) == 0 && ((View)transitionValues0.values.get("android:visibility:parent")) != null;
    }

    public Animator onAppear(ViewGroup viewGroup0, View view0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        return null;
    }

    public Animator onAppear(ViewGroup viewGroup0, TransitionValues transitionValues0, int v, TransitionValues transitionValues1, int v1) {
        if((this.mMode & 1) == 1 && transitionValues1 != null) {
            if(transitionValues0 == null) {
                View view0 = (View)transitionValues1.view.getParent();
                return this.getVisibilityChangeInfo(this.getMatchedTransitionValues(view0, false), this.getTransitionValues(view0, false)).mVisibilityChange ? null : this.onAppear(viewGroup0, transitionValues1.view, null, transitionValues1);
            }
            return this.onAppear(viewGroup0, transitionValues1.view, transitionValues0, transitionValues1);
        }
        return null;
    }

    public Animator onDisappear(ViewGroup viewGroup0, View view0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        return null;
    }

    public Animator onDisappear(ViewGroup viewGroup0, TransitionValues transitionValues0, int v, TransitionValues transitionValues1, int v1) {
        boolean z;
        View view3;
        boolean z1;
        View view4;
        if((this.mMode & 2) != 2) {
            return null;
        }
        if(transitionValues0 == null) {
            return null;
        }
        View view0 = transitionValues0.view;
        View view1 = transitionValues1 == null ? null : transitionValues1.view;
        View view2 = (View)view0.getTag(id.save_overlay_view);
        if(view2 == null) {
            if(view1 == null || view1.getParent() == null) {
                if(view1 == null) {
                    view1 = null;
                    view4 = null;
                    z1 = true;
                }
                else {
                    view4 = null;
                    z1 = false;
                }
            }
            else if(v1 == 4 || view0 == view1) {
                view4 = view1;
                z1 = false;
                view1 = null;
            }
            else {
                view1 = null;
                view4 = null;
                z1 = true;
            }
            if(!z1) {
                view2 = view1;
                view3 = view4;
                z = false;
            }
            else if(view0.getParent() == null) {
                view3 = view4;
                z = false;
                view2 = view0;
            }
            else if(view0.getParent() instanceof View) {
                View view5 = (View)view0.getParent();
                if(this.getVisibilityChangeInfo(this.getTransitionValues(view5, true), this.getMatchedTransitionValues(view5, true)).mVisibilityChange) {
                    int v2 = view5.getId();
                    if(view5.getParent() != null || v2 == -1 || viewGroup0.findViewById(v2) == null || !this.mCanRemoveViews) {
                        view2 = view1;
                        view3 = view4;
                        z = false;
                    }
                    else {
                        view3 = view4;
                        z = false;
                        view2 = view0;
                    }
                }
                else {
                    view1 = TransitionUtils.copyViewImage(viewGroup0, view0, view5);
                    view2 = view1;
                    view3 = view4;
                    z = false;
                }
            }
            else {
                view2 = view1;
                view3 = view4;
                z = false;
            }
        }
        else {
            view3 = null;
            z = true;
        }
        if(view2 != null) {
            if(!z) {
                int[] arr_v = (int[])transitionValues0.values.get("android:visibility:screenLocation");
                int v3 = arr_v[0];
                int v4 = arr_v[1];
                int[] arr_v1 = new int[2];
                viewGroup0.getLocationOnScreen(arr_v1);
                view2.offsetLeftAndRight(v3 - arr_v1[0] - view2.getLeft());
                view2.offsetTopAndBottom(v4 - arr_v1[1] - view2.getTop());
                viewGroup0.getOverlay().add(view2);
            }
            Animator animator0 = this.onDisappear(viewGroup0, view2, transitionValues0, transitionValues1);
            if(!z) {
                if(animator0 == null) {
                    viewGroup0.getOverlay().remove(view2);
                    return null;
                }
                view0.setTag(id.save_overlay_view, view2);
                OverlayListener visibility$OverlayListener0 = new OverlayListener(this, viewGroup0, view2, view0);
                animator0.addListener(visibility$OverlayListener0);
                animator0.addPauseListener(visibility$OverlayListener0);
                this.getRootTransition().addListener(visibility$OverlayListener0);
            }
            return animator0;
        }
        if(view3 != null) {
            int v5 = view3.getVisibility();
            ViewUtils.setTransitionVisibility(view3, 0);
            Animator animator1 = this.onDisappear(viewGroup0, view3, transitionValues0, transitionValues1);
            if(animator1 != null) {
                DisappearListener visibility$DisappearListener0 = new DisappearListener(view3, v1, true);
                animator1.addListener(visibility$DisappearListener0);
                this.getRootTransition().addListener(visibility$DisappearListener0);
                return animator1;
            }
            ViewUtils.setTransitionVisibility(view3, v5);
            return null;
        }
        return null;
    }

    public void setMode(int v) {
        if((v & -4) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.mMode = v;
    }
}

