package androidx.transition;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

public class SidePropagation extends VisibilityPropagation {
    private float mPropagationSpeed;
    private int mSide;

    public SidePropagation() {
        this.mPropagationSpeed = 3.0f;
        this.mSide = 80;
    }

    private int distance(View view0, int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7) {
        int v8 = this.mSide;
        if(v8 != 0x800003) {
            if(v8 == 0x800005) {
                v8 = view0.getLayoutDirection() == 1 ? 3 : 5;
            }
        }
        else if(view0.getLayoutDirection() != 1) {
            v8 = 3;
        }
        else {
            v8 = 5;
        }
        switch(v8) {
            case 3: {
                return v6 - v + Math.abs(v3 - v1);
            }
            case 5: {
                return v - v4 + Math.abs(v3 - v1);
            }
            case 0x30: {
                return v7 - v1 + Math.abs(v2 - v);
            }
            case 80: {
                return v1 - v5 + Math.abs(v2 - v);
            }
            default: {
                return 0;
            }
        }
    }

    private int getMaxDistance(ViewGroup viewGroup0) {
        return this.mSide == 3 || this.mSide == 5 || this.mSide == 0x800003 || this.mSide == 0x800005 ? viewGroup0.getWidth() : viewGroup0.getHeight();
    }

    @Override  // androidx.transition.TransitionPropagation
    public long getStartDelay(ViewGroup viewGroup0, Transition transition0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        int v11;
        int v10;
        int v9;
        int v8;
        int v;
        if(transitionValues0 == null && transitionValues1 == null) {
            return 0L;
        }
        Rect rect0 = transition0.getEpicenter();
        if(transitionValues1 == null || this.getViewVisibility(transitionValues0) == 0) {
            v = -1;
        }
        else {
            transitionValues0 = transitionValues1;
            v = 1;
        }
        int v1 = this.getViewX(transitionValues0);
        int v2 = this.getViewY(transitionValues0);
        int[] arr_v = new int[2];
        viewGroup0.getLocationOnScreen(arr_v);
        int v3 = arr_v[0] + Math.round(viewGroup0.getTranslationX());
        int v4 = arr_v[1] + Math.round(viewGroup0.getTranslationY());
        int v5 = viewGroup0.getWidth() + v3;
        int v6 = viewGroup0.getHeight() + v4;
        if(rect0 == null) {
            v11 = (v3 + v5) / 2;
            v10 = (v4 + v6) / 2;
            v8 = v2;
            v9 = v4;
        }
        else {
            int v7 = rect0.centerX();
            v8 = v2;
            v9 = v4;
            v10 = rect0.centerY();
            v11 = v7;
        }
        float f = ((float)this.distance(viewGroup0, v1, v8, v11, v10, v3, v9, v5, v6)) / ((float)this.getMaxDistance(viewGroup0));
        return (long)Math.round(((float)((transition0.getDuration() >= 0L ? transition0.getDuration() : 300L) * ((long)v))) / this.mPropagationSpeed * f);
    }

    public void setPropagationSpeed(float f) {
        if(f == 0.0f) {
            throw new IllegalArgumentException("propagationSpeed may not be 0");
        }
        this.mPropagationSpeed = f;
    }

    public void setSide(int v) {
        this.mSide = v;
    }
}

