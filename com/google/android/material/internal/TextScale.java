package com.google.android.material.internal;

import android.animation.Animator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.transition.Transition;
import androidx.transition.TransitionValues;
import java.util.Map;

public class TextScale extends Transition {
    private static final String PROPNAME_SCALE = "android:textscale:scale";

    @Override  // androidx.transition.Transition
    public void captureEndValues(TransitionValues transitionValues0) {
        this.captureValues(transitionValues0);
    }

    @Override  // androidx.transition.Transition
    public void captureStartValues(TransitionValues transitionValues0) {
        this.captureValues(transitionValues0);
    }

    private void captureValues(TransitionValues transitionValues0) {
        if(transitionValues0.view instanceof TextView) {
            Float float0 = ((TextView)transitionValues0.view).getScaleX();
            transitionValues0.values.put("android:textscale:scale", float0);
        }
    }

    @Override  // androidx.transition.Transition
    public Animator createAnimator(ViewGroup viewGroup0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        Animator animator0 = null;
        if(transitionValues0 != null && transitionValues1 != null && transitionValues0.view instanceof TextView && transitionValues1.view instanceof TextView) {
            TextView textView0 = (TextView)transitionValues1.view;
            Map map0 = transitionValues1.values;
            float f = 1.0f;
            float f1 = transitionValues0.values.get("android:textscale:scale") == null ? 1.0f : ((float)(((Float)transitionValues0.values.get("android:textscale:scale"))));
            if(map0.get("android:textscale:scale") != null) {
                f = (float)(((Float)map0.get("android:textscale:scale")));
            }
            if(f1 == f) {
                return null;
            }
            animator0 = ValueAnimator.ofFloat(new float[]{f1, f});
            ((ValueAnimator)animator0).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator0) {
                    float f = (float)(((Float)valueAnimator0.getAnimatedValue()));
                    textView0.setScaleX(f);
                    textView0.setScaleY(f);
                }
            });
        }
        return animator0;
    }
}

