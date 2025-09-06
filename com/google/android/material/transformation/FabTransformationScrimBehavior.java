package com.google.android.material.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.MotionTiming;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class FabTransformationScrimBehavior extends ExpandableTransformationBehavior {
    public static final long COLLAPSE_DELAY = 0L;
    public static final long COLLAPSE_DURATION = 150L;
    public static final long EXPAND_DELAY = 75L;
    public static final long EXPAND_DURATION = 150L;
    private final MotionTiming collapseTiming;
    private final MotionTiming expandTiming;

    public FabTransformationScrimBehavior() {
        this.expandTiming = new MotionTiming(75L, 150L);
        this.collapseTiming = new MotionTiming(0L, 150L);
    }

    public FabTransformationScrimBehavior(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.expandTiming = new MotionTiming(75L, 150L);
        this.collapseTiming = new MotionTiming(0L, 150L);
    }

    private void createScrimAnimation(View view0, boolean z, boolean z1, List list0, List list1) {
        ObjectAnimator objectAnimator0;
        MotionTiming motionTiming0 = z ? this.expandTiming : this.collapseTiming;
        if(z) {
            if(!z1) {
                view0.setAlpha(0.0f);
            }
            objectAnimator0 = ObjectAnimator.ofFloat(view0, View.ALPHA, new float[]{1.0f});
        }
        else {
            objectAnimator0 = ObjectAnimator.ofFloat(view0, View.ALPHA, new float[]{0.0f});
        }
        motionTiming0.apply(objectAnimator0);
        list0.add(objectAnimator0);
    }

    @Override  // com.google.android.material.transformation.ExpandableBehavior
    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout0, View view0, View view1) {
        return view1 instanceof FloatingActionButton;
    }

    @Override  // com.google.android.material.transformation.ExpandableTransformationBehavior
    protected AnimatorSet onCreateExpandedStateChangeAnimation(View view0, View view1, boolean z, boolean z1) {
        ArrayList arrayList0 = new ArrayList();
        this.createScrimAnimation(view1, z, z1, arrayList0, new ArrayList());
        AnimatorSet animatorSet0 = new AnimatorSet();
        AnimatorSetCompat.playTogether(animatorSet0, arrayList0);
        animatorSet0.addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                if(!z) {
                    view1.setVisibility(4);
                }
            }

            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationStart(Animator animator0) {
                if(z) {
                    view1.setVisibility(0);
                }
            }
        });
        return animatorSet0;
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout0, View view0, MotionEvent motionEvent0) {
        return super.onTouchEvent(coordinatorLayout0, view0, motionEvent0);
    }
}

