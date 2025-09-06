package androidx.transition;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

public class Explode extends Visibility {
    private static final String PROPNAME_SCREEN_BOUNDS = "android:explode:screenBounds";
    private int[] mTempLoc;
    private static final TimeInterpolator sAccelerate;
    private static final TimeInterpolator sDecelerate;

    static {
        Explode.sDecelerate = new DecelerateInterpolator();
        Explode.sAccelerate = new AccelerateInterpolator();
    }

    public Explode() {
        this.mTempLoc = new int[2];
        this.setPropagation(new CircularPropagation());
    }

    public Explode(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mTempLoc = new int[2];
        this.setPropagation(new CircularPropagation());
    }

    private static float calculateDistance(float f, float f1) {
        return (float)Math.sqrt(f * f + f1 * f1);
    }

    private static float calculateMaxDistance(View view0, int v, int v1) {
        return Explode.calculateDistance(Math.max(v, view0.getWidth() - v), Math.max(v1, view0.getHeight() - v1));
    }

    private void calculateOut(View view0, Rect rect0, int[] arr_v) {
        int v3;
        int v2;
        view0.getLocationOnScreen(this.mTempLoc);
        int[] arr_v1 = this.mTempLoc;
        int v = arr_v1[0];
        int v1 = arr_v1[1];
        Rect rect1 = this.getEpicenter();
        if(rect1 == null) {
            v2 = view0.getWidth() / 2 + v + Math.round(view0.getTranslationX());
            v3 = view0.getHeight() / 2 + v1 + Math.round(view0.getTranslationY());
        }
        else {
            int v4 = rect1.centerX();
            v3 = rect1.centerY();
            v2 = v4;
        }
        float f = (float)(rect0.centerX() - v2);
        float f1 = (float)(rect0.centerY() - v3);
        if(f == 0.0f && f1 == 0.0f) {
            f = ((float)(Math.random() * 2.0)) - 1.0f;
            f1 = ((float)(Math.random() * 2.0)) - 1.0f;
        }
        float f2 = Explode.calculateDistance(f, f1);
        float f3 = Explode.calculateMaxDistance(view0, v2 - v, v3 - v1);
        arr_v[0] = Math.round(f / f2 * f3);
        arr_v[1] = Math.round(f3 * (f1 / f2));
    }

    @Override  // androidx.transition.Visibility
    public void captureEndValues(TransitionValues transitionValues0) {
        super.captureEndValues(transitionValues0);
        this.captureValues(transitionValues0);
    }

    @Override  // androidx.transition.Visibility
    public void captureStartValues(TransitionValues transitionValues0) {
        super.captureStartValues(transitionValues0);
        this.captureValues(transitionValues0);
    }

    private void captureValues(TransitionValues transitionValues0) {
        View view0 = transitionValues0.view;
        view0.getLocationOnScreen(this.mTempLoc);
        int[] arr_v = this.mTempLoc;
        int v = arr_v[0];
        int v1 = arr_v[1];
        Rect rect0 = new Rect(v, v1, view0.getWidth() + v, view0.getHeight() + v1);
        transitionValues0.values.put("android:explode:screenBounds", rect0);
    }

    @Override  // androidx.transition.Transition
    public boolean isSeekingSupported() {
        return true;
    }

    @Override  // androidx.transition.Visibility
    public Animator onAppear(ViewGroup viewGroup0, View view0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        if(transitionValues1 == null) {
            return null;
        }
        Rect rect0 = (Rect)transitionValues1.values.get("android:explode:screenBounds");
        float f = view0.getTranslationX();
        float f1 = view0.getTranslationY();
        this.calculateOut(viewGroup0, rect0, this.mTempLoc);
        return TranslationAnimationCreator.createAnimation(view0, transitionValues1, rect0.left, rect0.top, f + ((float)this.mTempLoc[0]), f1 + ((float)this.mTempLoc[1]), f, f1, Explode.sDecelerate, this);
    }

    @Override  // androidx.transition.Visibility
    public Animator onDisappear(ViewGroup viewGroup0, View view0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        float f3;
        float f2;
        if(transitionValues0 == null) {
            return null;
        }
        Rect rect0 = (Rect)transitionValues0.values.get("android:explode:screenBounds");
        int v = rect0.left;
        int v1 = rect0.top;
        float f = view0.getTranslationX();
        float f1 = view0.getTranslationY();
        int[] arr_v = (int[])transitionValues0.view.getTag(id.transition_position);
        if(arr_v == null) {
            f2 = f;
            f3 = f1;
        }
        else {
            f2 = ((float)(arr_v[0] - rect0.left)) + f;
            f3 = ((float)(arr_v[1] - rect0.top)) + f1;
            rect0.offsetTo(arr_v[0], arr_v[1]);
        }
        this.calculateOut(viewGroup0, rect0, this.mTempLoc);
        return TranslationAnimationCreator.createAnimation(view0, transitionValues0, v, v1, f, f1, f2 + ((float)this.mTempLoc[0]), f3 + ((float)this.mTempLoc[1]), Explode.sAccelerate, this);
    }
}

