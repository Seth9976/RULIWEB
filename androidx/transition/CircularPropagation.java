package androidx.transition;

import android.graphics.Rect;
import android.view.ViewGroup;

public class CircularPropagation extends VisibilityPropagation {
    private float mPropagationSpeed;

    public CircularPropagation() {
        this.mPropagationSpeed = 3.0f;
    }

    private static float distance(float f, float f1, float f2, float f3) {
        return (float)Math.sqrt((f2 - f) * (f2 - f) + (f3 - f1) * (f3 - f1));
    }

    @Override  // androidx.transition.TransitionPropagation
    public long getStartDelay(ViewGroup viewGroup0, Transition transition0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        int v4;
        int v3;
        int v;
        if(transitionValues0 == null && transitionValues1 == null) {
            return 0L;
        }
        if(transitionValues1 == null || this.getViewVisibility(transitionValues0) == 0) {
            v = -1;
        }
        else {
            transitionValues0 = transitionValues1;
            v = 1;
        }
        int v1 = this.getViewX(transitionValues0);
        int v2 = this.getViewY(transitionValues0);
        Rect rect0 = transition0.getEpicenter();
        if(rect0 == null) {
            int[] arr_v = new int[2];
            viewGroup0.getLocationOnScreen(arr_v);
            int v5 = Math.round(((float)(arr_v[0] + viewGroup0.getWidth() / 2)) + viewGroup0.getTranslationX());
            v4 = Math.round(((float)(arr_v[1] + viewGroup0.getHeight() / 2)) + viewGroup0.getTranslationY());
            v3 = v5;
        }
        else {
            v3 = rect0.centerX();
            v4 = rect0.centerY();
        }
        float f = CircularPropagation.distance(0.0f, 0.0f, viewGroup0.getWidth(), viewGroup0.getHeight());
        return (long)Math.round(((float)((transition0.getDuration() >= 0L ? transition0.getDuration() : 300L) * ((long)v))) / this.mPropagationSpeed * (CircularPropagation.distance(v1, v2, v3, v4) / f));
    }

    public void setPropagationSpeed(float f) {
        if(f == 0.0f) {
            throw new IllegalArgumentException("propagationSpeed may not be 0");
        }
        this.mPropagationSpeed = f;
    }
}

