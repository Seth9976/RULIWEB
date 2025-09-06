package com.google.android.material.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import java.util.List;

public class AnimatorSetCompat {
    public static void playTogether(AnimatorSet animatorSet0, List list0) {
        int v = list0.size();
        long v1 = 0L;
        for(int v2 = 0; v2 < v; ++v2) {
            Animator animator0 = (Animator)list0.get(v2);
            v1 = Math.max(v1, animator0.getStartDelay() + animator0.getDuration());
        }
        ValueAnimator valueAnimator0 = ValueAnimator.ofInt(new int[]{0, 0});
        valueAnimator0.setDuration(v1);
        list0.add(0, valueAnimator0);
        animatorSet0.playTogether(list0);
    }
}

