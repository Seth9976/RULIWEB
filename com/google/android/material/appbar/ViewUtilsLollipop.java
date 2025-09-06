package com.google.android.material.appbar;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import com.google.android.material.R.attr;
import com.google.android.material.R.integer;
import com.google.android.material.internal.ThemeEnforcement;

class ViewUtilsLollipop {
    private static final int[] STATE_LIST_ANIM_ATTRS;

    static {
        ViewUtilsLollipop.STATE_LIST_ANIM_ATTRS = new int[]{0x1010448};
    }

    static void setBoundsViewOutlineProvider(View view0) {
        view0.setOutlineProvider(ViewOutlineProvider.BOUNDS);
    }

    static void setDefaultAppBarLayoutStateListAnimator(View view0, float f) {
        int v = view0.getResources().getInteger(integer.app_bar_elevation_anim_duration);
        StateListAnimator stateListAnimator0 = new StateListAnimator();
        ObjectAnimator objectAnimator0 = ObjectAnimator.ofFloat(view0, "elevation", new float[]{0.0f}).setDuration(((long)v));
        stateListAnimator0.addState(new int[]{0x101009E, attr.state_liftable, -attr.state_lifted}, objectAnimator0);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(view0, "elevation", new float[]{f}).setDuration(((long)v));
        stateListAnimator0.addState(new int[]{0x101009E}, objectAnimator1);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(view0, "elevation", new float[]{0.0f}).setDuration(0L);
        stateListAnimator0.addState(new int[0], objectAnimator2);
        view0.setStateListAnimator(stateListAnimator0);
    }

    static void setStateListAnimatorFromAttrs(View view0, AttributeSet attributeSet0, int v, int v1) {
        Context context0 = view0.getContext();
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(context0, attributeSet0, ViewUtilsLollipop.STATE_LIST_ANIM_ATTRS, v, v1, new int[0]);
        try {
            if(typedArray0.hasValue(0)) {
                view0.setStateListAnimator(AnimatorInflater.loadStateListAnimator(context0, typedArray0.getResourceId(0, 0)));
            }
        }
        finally {
            typedArray0.recycle();
        }
    }
}

