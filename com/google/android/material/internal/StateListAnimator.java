package com.google.android.material.internal;

import android.animation.Animator.AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.StateSet;
import java.util.ArrayList;

public final class StateListAnimator {
    static class Tuple {
        final ValueAnimator animator;
        final int[] specs;

        Tuple(int[] arr_v, ValueAnimator valueAnimator0) {
            this.specs = arr_v;
            this.animator = valueAnimator0;
        }
    }

    private final Animator.AnimatorListener animationListener;
    private Tuple lastMatch;
    ValueAnimator runningAnimator;
    private final ArrayList tuples;

    public StateListAnimator() {
        this.tuples = new ArrayList();
        this.lastMatch = null;
        this.runningAnimator = null;
        this.animationListener = new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                if(StateListAnimator.this.runningAnimator == animator0) {
                    StateListAnimator.this.runningAnimator = null;
                }
            }
        };
    }

    public void addState(int[] arr_v, ValueAnimator valueAnimator0) {
        Tuple stateListAnimator$Tuple0 = new Tuple(arr_v, valueAnimator0);
        valueAnimator0.addListener(this.animationListener);
        this.tuples.add(stateListAnimator$Tuple0);
    }

    private void cancel() {
        ValueAnimator valueAnimator0 = this.runningAnimator;
        if(valueAnimator0 != null) {
            valueAnimator0.cancel();
            this.runningAnimator = null;
        }
    }

    public void jumpToCurrentState() {
        ValueAnimator valueAnimator0 = this.runningAnimator;
        if(valueAnimator0 != null) {
            valueAnimator0.end();
            this.runningAnimator = null;
        }
    }

    public void setState(int[] arr_v) {
        Tuple stateListAnimator$Tuple0;
        int v = this.tuples.size();
        for(int v1 = 0; true; ++v1) {
            stateListAnimator$Tuple0 = null;
            if(v1 >= v) {
                break;
            }
            Tuple stateListAnimator$Tuple1 = (Tuple)this.tuples.get(v1);
            if(StateSet.stateSetMatches(stateListAnimator$Tuple1.specs, arr_v)) {
                stateListAnimator$Tuple0 = stateListAnimator$Tuple1;
                break;
            }
        }
        Tuple stateListAnimator$Tuple2 = this.lastMatch;
        if(stateListAnimator$Tuple0 != stateListAnimator$Tuple2) {
            if(stateListAnimator$Tuple2 != null) {
                this.cancel();
            }
            this.lastMatch = stateListAnimator$Tuple0;
            if(stateListAnimator$Tuple0 != null) {
                this.start(stateListAnimator$Tuple0);
            }
        }
    }

    private void start(Tuple stateListAnimator$Tuple0) {
        this.runningAnimator = stateListAnimator$Tuple0.animator;
        stateListAnimator$Tuple0.animator.start();
    }
}

