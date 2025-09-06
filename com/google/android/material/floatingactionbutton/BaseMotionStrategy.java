package com.google.android.material.floatingactionbutton;

import android.animation.Animator.AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Property;
import android.view.View;
import androidx.core.util.Preconditions;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.MotionSpec;
import java.util.ArrayList;
import java.util.List;

abstract class BaseMotionStrategy implements MotionStrategy {
    private final Context context;
    private MotionSpec defaultMotionSpec;
    private final ExtendedFloatingActionButton fab;
    private final ArrayList listeners;
    private MotionSpec motionSpec;
    private final AnimatorTracker tracker;

    BaseMotionStrategy(ExtendedFloatingActionButton extendedFloatingActionButton0, AnimatorTracker animatorTracker0) {
        this.listeners = new ArrayList();
        this.fab = extendedFloatingActionButton0;
        this.context = extendedFloatingActionButton0.getContext();
        this.tracker = animatorTracker0;
    }

    @Override  // com.google.android.material.floatingactionbutton.MotionStrategy
    public final void addAnimationListener(Animator.AnimatorListener animator$AnimatorListener0) {
        this.listeners.add(animator$AnimatorListener0);
    }

    @Override  // com.google.android.material.floatingactionbutton.MotionStrategy
    public AnimatorSet createAnimator() {
        return this.createAnimator(this.getCurrentMotionSpec());
    }

    AnimatorSet createAnimator(MotionSpec motionSpec0) {
        ArrayList arrayList0 = new ArrayList();
        if(motionSpec0.hasPropertyValues("opacity")) {
            arrayList0.add(motionSpec0.getAnimator("opacity", this.fab, View.ALPHA));
        }
        if(motionSpec0.hasPropertyValues("scale")) {
            arrayList0.add(motionSpec0.getAnimator("scale", this.fab, View.SCALE_Y));
            arrayList0.add(motionSpec0.getAnimator("scale", this.fab, View.SCALE_X));
        }
        if(motionSpec0.hasPropertyValues("width")) {
            arrayList0.add(motionSpec0.getAnimator("width", this.fab, ExtendedFloatingActionButton.WIDTH));
        }
        if(motionSpec0.hasPropertyValues("height")) {
            arrayList0.add(motionSpec0.getAnimator("height", this.fab, ExtendedFloatingActionButton.HEIGHT));
        }
        if(motionSpec0.hasPropertyValues("paddingStart")) {
            arrayList0.add(motionSpec0.getAnimator("paddingStart", this.fab, ExtendedFloatingActionButton.PADDING_START));
        }
        if(motionSpec0.hasPropertyValues("paddingEnd")) {
            arrayList0.add(motionSpec0.getAnimator("paddingEnd", this.fab, ExtendedFloatingActionButton.PADDING_END));
        }
        if(motionSpec0.hasPropertyValues("labelOpacity")) {
            com.google.android.material.floatingactionbutton.BaseMotionStrategy.1 baseMotionStrategy$10 = new Property(Float.class, "LABEL_OPACITY_PROPERTY") {
                public Float get(ExtendedFloatingActionButton extendedFloatingActionButton0) {
                    int v = Color.alpha(extendedFloatingActionButton0.originalTextCsl.getColorForState(extendedFloatingActionButton0.getDrawableState(), BaseMotionStrategy.this.fab.originalTextCsl.getDefaultColor()));
                    return AnimationUtils.lerp(0.0f, 1.0f, ((float)Color.alpha(extendedFloatingActionButton0.getCurrentTextColor())) / 255.0f / ((float)v));
                }

                @Override  // android.util.Property
                public Object get(Object object0) {
                    return this.get(((ExtendedFloatingActionButton)object0));
                }

                public void set(ExtendedFloatingActionButton extendedFloatingActionButton0, Float float0) {
                    int v = extendedFloatingActionButton0.originalTextCsl.getColorForState(extendedFloatingActionButton0.getDrawableState(), BaseMotionStrategy.this.fab.originalTextCsl.getDefaultColor());
                    ColorStateList colorStateList0 = ColorStateList.valueOf(Color.argb(((int)(AnimationUtils.lerp(0.0f, ((float)Color.alpha(v)) / 255.0f, ((float)float0)) * 255.0f)), Color.red(v), Color.green(v), Color.blue(v)));
                    if(((float)float0) == 1.0f) {
                        extendedFloatingActionButton0.silentlyUpdateTextColor(extendedFloatingActionButton0.originalTextCsl);
                        return;
                    }
                    extendedFloatingActionButton0.silentlyUpdateTextColor(colorStateList0);
                }

                @Override  // android.util.Property
                public void set(Object object0, Object object1) {
                    this.set(((ExtendedFloatingActionButton)object0), ((Float)object1));
                }
            };
            arrayList0.add(motionSpec0.getAnimator("labelOpacity", this.fab, baseMotionStrategy$10));
        }
        AnimatorSet animatorSet0 = new AnimatorSet();
        AnimatorSetCompat.playTogether(animatorSet0, arrayList0);
        return animatorSet0;
    }

    @Override  // com.google.android.material.floatingactionbutton.MotionStrategy
    public final MotionSpec getCurrentMotionSpec() {
        MotionSpec motionSpec0 = this.motionSpec;
        if(motionSpec0 != null) {
            return motionSpec0;
        }
        if(this.defaultMotionSpec == null) {
            int v = this.getDefaultMotionSpecResource();
            this.defaultMotionSpec = MotionSpec.createFromResource(this.context, v);
        }
        return (MotionSpec)Preconditions.checkNotNull(this.defaultMotionSpec);
    }

    @Override  // com.google.android.material.floatingactionbutton.MotionStrategy
    public final List getListeners() {
        return this.listeners;
    }

    @Override  // com.google.android.material.floatingactionbutton.MotionStrategy
    public MotionSpec getMotionSpec() {
        return this.motionSpec;
    }

    @Override  // com.google.android.material.floatingactionbutton.MotionStrategy
    public void onAnimationCancel() {
        this.tracker.clear();
    }

    @Override  // com.google.android.material.floatingactionbutton.MotionStrategy
    public void onAnimationEnd() {
        this.tracker.clear();
    }

    @Override  // com.google.android.material.floatingactionbutton.MotionStrategy
    public void onAnimationStart(Animator animator0) {
        this.tracker.onNextAnimationStart(animator0);
    }

    @Override  // com.google.android.material.floatingactionbutton.MotionStrategy
    public final void removeAnimationListener(Animator.AnimatorListener animator$AnimatorListener0) {
        this.listeners.remove(animator$AnimatorListener0);
    }

    @Override  // com.google.android.material.floatingactionbutton.MotionStrategy
    public final void setMotionSpec(MotionSpec motionSpec0) {
        this.motionSpec = motionSpec0;
    }
}

