package com.google.android.material.transition.platform;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import com.google.android.material.R.dimen;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class SlideDistanceProvider implements VisibilityAnimatorProvider {
    @Retention(RetentionPolicy.SOURCE)
    public @interface GravityFlag {
    }

    private static final int DEFAULT_DISTANCE = -1;
    private int slideDistance;
    private int slideEdge;

    public SlideDistanceProvider(int v) {
        this.slideDistance = -1;
        this.slideEdge = v;
    }

    @Override  // com.google.android.material.transition.platform.VisibilityAnimatorProvider
    public Animator createAppear(ViewGroup viewGroup0, View view0) {
        return SlideDistanceProvider.createTranslationAppearAnimator(viewGroup0, view0, this.slideEdge, this.getSlideDistanceOrDefault(view0.getContext()));
    }

    @Override  // com.google.android.material.transition.platform.VisibilityAnimatorProvider
    public Animator createDisappear(ViewGroup viewGroup0, View view0) {
        return SlideDistanceProvider.createTranslationDisappearAnimator(viewGroup0, view0, this.slideEdge, this.getSlideDistanceOrDefault(view0.getContext()));
    }

    private static Animator createTranslationAppearAnimator(View view0, View view1, int v, int v1) {
        float f = view1.getTranslationX();
        float f1 = view1.getTranslationY();
        switch(v) {
            case 3: {
                return SlideDistanceProvider.createTranslationXAnimator(view1, ((float)v1) + f, f, f);
            }
            case 5: {
                return SlideDistanceProvider.createTranslationXAnimator(view1, f - ((float)v1), f, f);
            }
            case 0x30: {
                return SlideDistanceProvider.createTranslationYAnimator(view1, f1 - ((float)v1), f1, f1);
            }
            case 80: {
                return SlideDistanceProvider.createTranslationYAnimator(view1, ((float)v1) + f1, f1, f1);
            }
            case 0x800003: {
                return SlideDistanceProvider.isRtl(view0) ? SlideDistanceProvider.createTranslationXAnimator(view1, ((float)v1) + f, f, f) : SlideDistanceProvider.createTranslationXAnimator(view1, f - ((float)v1), f, f);
            }
            case 0x800005: {
                return SlideDistanceProvider.isRtl(view0) ? SlideDistanceProvider.createTranslationXAnimator(view1, f - ((float)v1), f, f) : SlideDistanceProvider.createTranslationXAnimator(view1, ((float)v1) + f, f, f);
            }
            default: {
                throw new IllegalArgumentException("Invalid slide direction: " + v);
            }
        }
    }

    private static Animator createTranslationDisappearAnimator(View view0, View view1, int v, int v1) {
        float f = view1.getTranslationX();
        float f1 = view1.getTranslationY();
        switch(v) {
            case 3: {
                return SlideDistanceProvider.createTranslationXAnimator(view1, f, f - ((float)v1), f);
            }
            case 5: {
                return SlideDistanceProvider.createTranslationXAnimator(view1, f, ((float)v1) + f, f);
            }
            case 0x30: {
                return SlideDistanceProvider.createTranslationYAnimator(view1, f1, ((float)v1) + f1, f1);
            }
            case 80: {
                return SlideDistanceProvider.createTranslationYAnimator(view1, f1, f1 - ((float)v1), f1);
            }
            case 0x800003: {
                return SlideDistanceProvider.isRtl(view0) ? SlideDistanceProvider.createTranslationXAnimator(view1, f, f - ((float)v1), f) : SlideDistanceProvider.createTranslationXAnimator(view1, f, ((float)v1) + f, f);
            }
            case 0x800005: {
                return SlideDistanceProvider.isRtl(view0) ? SlideDistanceProvider.createTranslationXAnimator(view1, f, ((float)v1) + f, f) : SlideDistanceProvider.createTranslationXAnimator(view1, f, f - ((float)v1), f);
            }
            default: {
                throw new IllegalArgumentException("Invalid slide direction: " + v);
            }
        }
    }

    private static Animator createTranslationXAnimator(View view0, float f, float f1, float f2) {
        Animator animator0 = ObjectAnimator.ofPropertyValuesHolder(view0, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.TRANSLATION_X, new float[]{f, f1})});
        ((ObjectAnimator)animator0).addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                view0.setTranslationX(f2);
            }
        });
        return animator0;
    }

    private static Animator createTranslationYAnimator(View view0, float f, float f1, float f2) {
        Animator animator0 = ObjectAnimator.ofPropertyValuesHolder(view0, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, new float[]{f, f1})});
        ((ObjectAnimator)animator0).addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                view0.setTranslationY(f2);
            }
        });
        return animator0;
    }

    public int getSlideDistance() {
        return this.slideDistance;
    }

    private int getSlideDistanceOrDefault(Context context0) {
        int v = this.slideDistance;
        return v == -1 ? context0.getResources().getDimensionPixelSize(dimen.mtrl_transition_shared_axis_slide_distance) : v;
    }

    public int getSlideEdge() {
        return this.slideEdge;
    }

    private static boolean isRtl(View view0) {
        return ViewCompat.getLayoutDirection(view0) == 1;
    }

    public void setSlideDistance(int v) {
        if(v < 0) {
            throw new IllegalArgumentException("Slide distance must be positive. If attempting to reverse the direction of the slide, use setSlideEdge(int) instead.");
        }
        this.slideDistance = v;
    }

    public void setSlideEdge(int v) {
        this.slideEdge = v;
    }
}

