package com.google.android.material.transformation;

import android.animation.Animator.AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.util.Property;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.R.id;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.ArgbEvaluatorCompat;
import com.google.android.material.animation.ChildrenAlphaProperty;
import com.google.android.material.animation.DrawableAlphaProperty;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.animation.MotionTiming;
import com.google.android.material.animation.Positioning;
import com.google.android.material.circularreveal.CircularRevealCompat;
import com.google.android.material.circularreveal.CircularRevealHelper;
import com.google.android.material.circularreveal.CircularRevealWidget.CircularRevealScrimColorProperty;
import com.google.android.material.circularreveal.CircularRevealWidget.RevealInfo;
import com.google.android.material.circularreveal.CircularRevealWidget;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.math.MathUtils;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public abstract class FabTransformationBehavior extends ExpandableTransformationBehavior {
    public static class FabTransformationSpec {
        public Positioning positioning;
        public MotionSpec timings;

    }

    private float dependencyOriginalTranslationX;
    private float dependencyOriginalTranslationY;
    private final int[] tmpArray;
    private final Rect tmpRect;
    private final RectF tmpRectF1;
    private final RectF tmpRectF2;

    public FabTransformationBehavior() {
        this.tmpRect = new Rect();
        this.tmpRectF1 = new RectF();
        this.tmpRectF2 = new RectF();
        this.tmpArray = new int[2];
    }

    public FabTransformationBehavior(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.tmpRect = new Rect();
        this.tmpRectF1 = new RectF();
        this.tmpRectF2 = new RectF();
        this.tmpArray = new int[2];
    }

    private ViewGroup calculateChildContentContainer(View view0) {
        View view1 = view0.findViewById(id.mtrl_child_content_container);
        if(view1 != null) {
            return this.toViewGroupOrNull(view1);
        }
        return view0 instanceof TransformationChildLayout || view0 instanceof TransformationChildCard ? this.toViewGroupOrNull(((ViewGroup)view0).getChildAt(0)) : this.toViewGroupOrNull(view0);
    }

    private void calculateChildVisibleBoundsAtEndOfExpansion(View view0, FabTransformationSpec fabTransformationBehavior$FabTransformationSpec0, MotionTiming motionTiming0, MotionTiming motionTiming1, float f, float f1, float f2, float f3, RectF rectF0) {
        float f4 = this.calculateValueOfAnimationAtEndOfExpansion(fabTransformationBehavior$FabTransformationSpec0, motionTiming0, f, f2);
        float f5 = this.calculateValueOfAnimationAtEndOfExpansion(fabTransformationBehavior$FabTransformationSpec0, motionTiming1, f1, f3);
        view0.getWindowVisibleDisplayFrame(this.tmpRect);
        this.tmpRectF1.set(this.tmpRect);
        this.calculateWindowBounds(view0, this.tmpRectF2);
        this.tmpRectF2.offset(f4, f5);
        this.tmpRectF2.intersect(this.tmpRectF1);
        rectF0.set(this.tmpRectF2);
    }

    private void calculateDependencyWindowBounds(View view0, RectF rectF0) {
        this.calculateWindowBounds(view0, rectF0);
        rectF0.offset(this.dependencyOriginalTranslationX, this.dependencyOriginalTranslationY);
    }

    private Pair calculateMotionTiming(float f, float f1, boolean z, FabTransformationSpec fabTransformationBehavior$FabTransformationSpec0) {
        if(f != 0.0f) {
            int v = Float.compare(f1, 0.0f);
            if(v != 0) {
                return (!z || f1 >= 0.0f) && (z || v <= 0) ? new Pair(fabTransformationBehavior$FabTransformationSpec0.timings.getTiming("translationXCurveDownwards"), fabTransformationBehavior$FabTransformationSpec0.timings.getTiming("translationYCurveDownwards")) : new Pair(fabTransformationBehavior$FabTransformationSpec0.timings.getTiming("translationXCurveUpwards"), fabTransformationBehavior$FabTransformationSpec0.timings.getTiming("translationYCurveUpwards"));
            }
        }
        return new Pair(fabTransformationBehavior$FabTransformationSpec0.timings.getTiming("translationXLinear"), fabTransformationBehavior$FabTransformationSpec0.timings.getTiming("translationYLinear"));
    }

    private float calculateRevealCenterX(View view0, View view1, Positioning positioning0) {
        this.calculateDependencyWindowBounds(view0, this.tmpRectF1);
        this.calculateWindowBounds(view1, this.tmpRectF2);
        float f = this.calculateTranslationX(view0, view1, positioning0);
        this.tmpRectF2.offset(-f, 0.0f);
        return this.tmpRectF1.centerX() - this.tmpRectF2.left;
    }

    private float calculateRevealCenterY(View view0, View view1, Positioning positioning0) {
        this.calculateDependencyWindowBounds(view0, this.tmpRectF1);
        this.calculateWindowBounds(view1, this.tmpRectF2);
        float f = this.calculateTranslationY(view0, view1, positioning0);
        this.tmpRectF2.offset(0.0f, -f);
        return this.tmpRectF1.centerY() - this.tmpRectF2.top;
    }

    private float calculateTranslationX(View view0, View view1, Positioning positioning0) {
        RectF rectF0 = this.tmpRectF1;
        RectF rectF1 = this.tmpRectF2;
        this.calculateDependencyWindowBounds(view0, rectF0);
        this.calculateWindowBounds(view1, rectF1);
        switch(positioning0.gravity & 7) {
            case 1: {
                return rectF1.centerX() - rectF0.centerX() + positioning0.xAdjustment;
            }
            case 3: {
                return rectF1.left - rectF0.left + positioning0.xAdjustment;
            }
            case 5: {
                return rectF1.right - rectF0.right + positioning0.xAdjustment;
            }
            default: {
                return positioning0.xAdjustment + 0.0f;
            }
        }
    }

    private float calculateTranslationY(View view0, View view1, Positioning positioning0) {
        RectF rectF0 = this.tmpRectF1;
        RectF rectF1 = this.tmpRectF2;
        this.calculateDependencyWindowBounds(view0, rectF0);
        this.calculateWindowBounds(view1, rectF1);
        switch(positioning0.gravity & 0x70) {
            case 16: {
                return rectF1.centerY() - rectF0.centerY() + positioning0.yAdjustment;
            }
            case 0x30: {
                return rectF1.top - rectF0.top + positioning0.yAdjustment;
            }
            case 80: {
                return rectF1.bottom - rectF0.bottom + positioning0.yAdjustment;
            }
            default: {
                return positioning0.yAdjustment + 0.0f;
            }
        }
    }

    private float calculateValueOfAnimationAtEndOfExpansion(FabTransformationSpec fabTransformationBehavior$FabTransformationSpec0, MotionTiming motionTiming0, float f, float f1) {
        MotionTiming motionTiming1 = fabTransformationBehavior$FabTransformationSpec0.timings.getTiming("expansion");
        return AnimationUtils.lerp(f, f1, motionTiming0.getInterpolator().getInterpolation(((float)(motionTiming1.getDelay() + motionTiming1.getDuration() + 17L - motionTiming0.getDelay())) / ((float)motionTiming0.getDuration())));
    }

    private void calculateWindowBounds(View view0, RectF rectF0) {
        rectF0.set(0.0f, 0.0f, ((float)view0.getWidth()), ((float)view0.getHeight()));
        view0.getLocationInWindow(this.tmpArray);
        rectF0.offsetTo(((float)this.tmpArray[0]), ((float)this.tmpArray[1]));
        rectF0.offset(((float)(((int)(-view0.getTranslationX())))), ((float)(((int)(-view0.getTranslationY())))));
    }

    private void createChildrenFadeAnimation(View view0, View view1, boolean z, boolean z1, FabTransformationSpec fabTransformationBehavior$FabTransformationSpec0, List list0, List list1) {
        ObjectAnimator objectAnimator0;
        if(view1 instanceof ViewGroup && (!(view1 instanceof CircularRevealWidget) || CircularRevealHelper.STRATEGY != 0)) {
            ViewGroup viewGroup0 = this.calculateChildContentContainer(view1);
            if(viewGroup0 != null) {
                if(z) {
                    if(!z1) {
                        ChildrenAlphaProperty.CHILDREN_ALPHA.set(viewGroup0, 0.0f);
                    }
                    objectAnimator0 = ObjectAnimator.ofFloat(viewGroup0, ChildrenAlphaProperty.CHILDREN_ALPHA, new float[]{1.0f});
                }
                else {
                    objectAnimator0 = ObjectAnimator.ofFloat(viewGroup0, ChildrenAlphaProperty.CHILDREN_ALPHA, new float[]{0.0f});
                }
                fabTransformationBehavior$FabTransformationSpec0.timings.getTiming("contentFade").apply(objectAnimator0);
                list0.add(objectAnimator0);
            }
        }
    }

    private void createColorAnimation(View view0, View view1, boolean z, boolean z1, FabTransformationSpec fabTransformationBehavior$FabTransformationSpec0, List list0, List list1) {
        ObjectAnimator objectAnimator0;
        if(!(view1 instanceof CircularRevealWidget)) {
            return;
        }
        int v = this.getBackgroundTint(view0);
        if(z) {
            if(!z1) {
                ((CircularRevealWidget)view1).setCircularRevealScrimColor(v);
            }
            objectAnimator0 = ObjectAnimator.ofInt(((CircularRevealWidget)view1), CircularRevealScrimColorProperty.CIRCULAR_REVEAL_SCRIM_COLOR, new int[]{0xFFFFFF & v});
        }
        else {
            objectAnimator0 = ObjectAnimator.ofInt(((CircularRevealWidget)view1), CircularRevealScrimColorProperty.CIRCULAR_REVEAL_SCRIM_COLOR, new int[]{v});
        }
        objectAnimator0.setEvaluator(ArgbEvaluatorCompat.getInstance());
        fabTransformationBehavior$FabTransformationSpec0.timings.getTiming("color").apply(objectAnimator0);
        list0.add(objectAnimator0);
    }

    private void createDependencyTranslationAnimation(View view0, View view1, boolean z, FabTransformationSpec fabTransformationBehavior$FabTransformationSpec0, List list0) {
        float f = this.calculateTranslationX(view0, view1, fabTransformationBehavior$FabTransformationSpec0.positioning);
        float f1 = this.calculateTranslationY(view0, view1, fabTransformationBehavior$FabTransformationSpec0.positioning);
        Pair pair0 = this.calculateMotionTiming(f, f1, z, fabTransformationBehavior$FabTransformationSpec0);
        MotionTiming motionTiming0 = (MotionTiming)pair0.first;
        MotionTiming motionTiming1 = (MotionTiming)pair0.second;
        Property property0 = View.TRANSLATION_X;
        if(!z) {
            f = this.dependencyOriginalTranslationX;
        }
        ObjectAnimator objectAnimator0 = ObjectAnimator.ofFloat(view0, property0, new float[]{f});
        Property property1 = View.TRANSLATION_Y;
        if(!z) {
            f1 = this.dependencyOriginalTranslationY;
        }
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(view0, property1, new float[]{f1});
        motionTiming0.apply(objectAnimator0);
        motionTiming1.apply(objectAnimator1);
        list0.add(objectAnimator0);
        list0.add(objectAnimator1);
    }

    private void createElevationAnimation(View view0, View view1, boolean z, boolean z1, FabTransformationSpec fabTransformationBehavior$FabTransformationSpec0, List list0, List list1) {
        ObjectAnimator objectAnimator0;
        float f = ViewCompat.getElevation(view1) - ViewCompat.getElevation(view0);
        if(z) {
            if(!z1) {
                view1.setTranslationZ(-f);
            }
            objectAnimator0 = ObjectAnimator.ofFloat(view1, View.TRANSLATION_Z, new float[]{0.0f});
        }
        else {
            objectAnimator0 = ObjectAnimator.ofFloat(view1, View.TRANSLATION_Z, new float[]{-f});
        }
        fabTransformationBehavior$FabTransformationSpec0.timings.getTiming("elevation").apply(objectAnimator0);
        list0.add(objectAnimator0);
    }

    private void createExpansionAnimation(View view0, View view1, boolean z, boolean z1, FabTransformationSpec fabTransformationBehavior$FabTransformationSpec0, float f, float f1, List list0, List list1) {
        Animator animator0;
        if(!(view1 instanceof CircularRevealWidget)) {
            return;
        }
        float f2 = this.calculateRevealCenterX(view0, view1, fabTransformationBehavior$FabTransformationSpec0.positioning);
        float f3 = this.calculateRevealCenterY(view0, view1, fabTransformationBehavior$FabTransformationSpec0.positioning);
        ((FloatingActionButton)view0).getContentRect(this.tmpRect);
        float f4 = ((float)this.tmpRect.width()) / 2.0f;
        MotionTiming motionTiming0 = fabTransformationBehavior$FabTransformationSpec0.timings.getTiming("expansion");
        if(z) {
            if(!z1) {
                ((CircularRevealWidget)view1).setRevealInfo(new RevealInfo(f2, f3, f4));
            }
            if(z1) {
                f4 = ((CircularRevealWidget)view1).getRevealInfo().radius;
            }
            animator0 = CircularRevealCompat.createCircularReveal(((CircularRevealWidget)view1), f2, f3, MathUtils.distanceToFurthestCorner(f2, f3, 0.0f, 0.0f, f, f1));
            animator0.addListener(new AnimatorListenerAdapter() {
                @Override  // android.animation.AnimatorListenerAdapter
                public void onAnimationEnd(Animator animator0) {
                    RevealInfo circularRevealWidget$RevealInfo0 = ((CircularRevealWidget)view1).getRevealInfo();
                    circularRevealWidget$RevealInfo0.radius = 3.402823E+38f;
                    ((CircularRevealWidget)view1).setRevealInfo(circularRevealWidget$RevealInfo0);
                }
            });
            this.createPreFillRadialExpansion(view1, motionTiming0.getDelay(), ((int)f2), ((int)f3), f4, list0);
        }
        else {
            float f5 = ((CircularRevealWidget)view1).getRevealInfo().radius;
            Animator animator1 = CircularRevealCompat.createCircularReveal(((CircularRevealWidget)view1), f2, f3, f4);
            this.createPreFillRadialExpansion(view1, motionTiming0.getDelay(), ((int)f2), ((int)f3), f5, list0);
            this.createPostFillRadialExpansion(view1, motionTiming0.getDelay(), motionTiming0.getDuration(), fabTransformationBehavior$FabTransformationSpec0.timings.getTotalDuration(), ((int)f2), ((int)f3), f4, list0);
            animator0 = animator1;
        }
        motionTiming0.apply(animator0);
        list0.add(animator0);
        list1.add(CircularRevealCompat.createCircularRevealListener(((CircularRevealWidget)view1)));
    }

    private void createIconFadeAnimation(View view0, View view1, boolean z, boolean z1, FabTransformationSpec fabTransformationBehavior$FabTransformationSpec0, List list0, List list1) {
        ObjectAnimator objectAnimator0;
        if(view1 instanceof CircularRevealWidget && view0 instanceof ImageView) {
            Drawable drawable0 = ((ImageView)view0).getDrawable();
            if(drawable0 != null) {
                drawable0.mutate();
                if(z) {
                    if(!z1) {
                        drawable0.setAlpha(0xFF);
                    }
                    objectAnimator0 = ObjectAnimator.ofInt(drawable0, DrawableAlphaProperty.DRAWABLE_ALPHA_COMPAT, new int[]{0});
                }
                else {
                    objectAnimator0 = ObjectAnimator.ofInt(drawable0, DrawableAlphaProperty.DRAWABLE_ALPHA_COMPAT, new int[]{0xFF});
                }
                objectAnimator0.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator0) {
                        view1.invalidate();
                    }
                });
                fabTransformationBehavior$FabTransformationSpec0.timings.getTiming("iconFade").apply(objectAnimator0);
                list0.add(objectAnimator0);
                list1.add(new AnimatorListenerAdapter() {
                    @Override  // android.animation.AnimatorListenerAdapter
                    public void onAnimationEnd(Animator animator0) {
                        ((CircularRevealWidget)view1).setCircularRevealOverlayDrawable(null);
                    }

                    @Override  // android.animation.AnimatorListenerAdapter
                    public void onAnimationStart(Animator animator0) {
                        ((CircularRevealWidget)view1).setCircularRevealOverlayDrawable(drawable0);
                    }
                });
            }
        }
    }

    private void createPostFillRadialExpansion(View view0, long v, long v1, long v2, int v3, int v4, float f, List list0) {
        long v5 = v + v1;
        if(v5 < v2) {
            Animator animator0 = ViewAnimationUtils.createCircularReveal(view0, v3, v4, f, f);
            animator0.setStartDelay(v5);
            animator0.setDuration(v2 - v5);
            list0.add(animator0);
        }
    }

    private void createPreFillRadialExpansion(View view0, long v, int v1, int v2, float f, List list0) {
        if(v > 0L) {
            Animator animator0 = ViewAnimationUtils.createCircularReveal(view0, v1, v2, f, f);
            animator0.setStartDelay(0L);
            animator0.setDuration(v);
            list0.add(animator0);
        }
    }

    private void createTranslationAnimation(View view0, View view1, boolean z, boolean z1, FabTransformationSpec fabTransformationBehavior$FabTransformationSpec0, List list0, List list1, RectF rectF0) {
        ObjectAnimator objectAnimator1;
        ObjectAnimator objectAnimator0;
        float f = this.calculateTranslationX(view0, view1, fabTransformationBehavior$FabTransformationSpec0.positioning);
        float f1 = this.calculateTranslationY(view0, view1, fabTransformationBehavior$FabTransformationSpec0.positioning);
        Pair pair0 = this.calculateMotionTiming(f, f1, z, fabTransformationBehavior$FabTransformationSpec0);
        MotionTiming motionTiming0 = (MotionTiming)pair0.first;
        MotionTiming motionTiming1 = (MotionTiming)pair0.second;
        if(z) {
            if(!z1) {
                view1.setTranslationX(-f);
                view1.setTranslationY(-f1);
            }
            objectAnimator0 = ObjectAnimator.ofFloat(view1, View.TRANSLATION_X, new float[]{0.0f});
            objectAnimator1 = ObjectAnimator.ofFloat(view1, View.TRANSLATION_Y, new float[]{0.0f});
            this.calculateChildVisibleBoundsAtEndOfExpansion(view1, fabTransformationBehavior$FabTransformationSpec0, motionTiming0, motionTiming1, -f, -f1, 0.0f, 0.0f, rectF0);
        }
        else {
            objectAnimator0 = ObjectAnimator.ofFloat(view1, View.TRANSLATION_X, new float[]{-f});
            objectAnimator1 = ObjectAnimator.ofFloat(view1, View.TRANSLATION_Y, new float[]{-f1});
        }
        motionTiming0.apply(objectAnimator0);
        motionTiming1.apply(objectAnimator1);
        list0.add(objectAnimator0);
        list0.add(objectAnimator1);
    }

    private int getBackgroundTint(View view0) {
        ColorStateList colorStateList0 = ViewCompat.getBackgroundTintList(view0);
        return colorStateList0 == null ? 0 : colorStateList0.getColorForState(view0.getDrawableState(), colorStateList0.getDefaultColor());
    }

    @Override  // com.google.android.material.transformation.ExpandableBehavior
    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout0, View view0, View view1) {
        if(view0.getVisibility() == 8) {
            throw new IllegalStateException("This behavior cannot be attached to a GONE view. Set the view to INVISIBLE instead.");
        }
        if(view1 instanceof FloatingActionButton) {
            int v = ((FloatingActionButton)view1).getExpandedComponentIdHint();
            return v == 0 || v == view0.getId();
        }
        return false;
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public void onAttachedToLayoutParams(LayoutParams coordinatorLayout$LayoutParams0) {
        if(coordinatorLayout$LayoutParams0.dodgeInsetEdges == 0) {
            coordinatorLayout$LayoutParams0.dodgeInsetEdges = 80;
        }
    }

    @Override  // com.google.android.material.transformation.ExpandableTransformationBehavior
    protected AnimatorSet onCreateExpandedStateChangeAnimation(View view0, View view1, boolean z, boolean z1) {
        FabTransformationSpec fabTransformationBehavior$FabTransformationSpec0 = this.onCreateMotionSpec(view1.getContext(), z);
        if(z) {
            this.dependencyOriginalTranslationX = view0.getTranslationX();
            this.dependencyOriginalTranslationY = view0.getTranslationY();
        }
        ArrayList arrayList0 = new ArrayList();
        ArrayList arrayList1 = new ArrayList();
        this.createElevationAnimation(view0, view1, z, z1, fabTransformationBehavior$FabTransformationSpec0, arrayList0, arrayList1);
        this.createTranslationAnimation(view0, view1, z, z1, fabTransformationBehavior$FabTransformationSpec0, arrayList0, arrayList1, this.tmpRectF1);
        float f = this.tmpRectF1.width();
        float f1 = this.tmpRectF1.height();
        this.createDependencyTranslationAnimation(view0, view1, z, fabTransformationBehavior$FabTransformationSpec0, arrayList0);
        this.createIconFadeAnimation(view0, view1, z, z1, fabTransformationBehavior$FabTransformationSpec0, arrayList0, arrayList1);
        this.createExpansionAnimation(view0, view1, z, z1, fabTransformationBehavior$FabTransformationSpec0, f, f1, arrayList0, arrayList1);
        this.createColorAnimation(view0, view1, z, z1, fabTransformationBehavior$FabTransformationSpec0, arrayList0, arrayList1);
        this.createChildrenFadeAnimation(view0, view1, z, z1, fabTransformationBehavior$FabTransformationSpec0, arrayList0, arrayList1);
        AnimatorSet animatorSet0 = new AnimatorSet();
        AnimatorSetCompat.playTogether(animatorSet0, arrayList0);
        animatorSet0.addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                if(!z) {
                    view1.setVisibility(4);
                    view0.setAlpha(1.0f);
                    view0.setVisibility(0);
                }
            }

            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationStart(Animator animator0) {
                if(z) {
                    view1.setVisibility(0);
                    view0.setAlpha(0.0f);
                    view0.setVisibility(4);
                }
            }
        });
        int v = arrayList1.size();
        for(int v1 = 0; v1 < v; ++v1) {
            animatorSet0.addListener(((Animator.AnimatorListener)arrayList1.get(v1)));
        }
        return animatorSet0;
    }

    protected abstract FabTransformationSpec onCreateMotionSpec(Context arg1, boolean arg2);

    // 去混淆评级： 低(20)
    private ViewGroup toViewGroupOrNull(View view0) {
        return view0 instanceof ViewGroup ? ((ViewGroup)view0) : null;
    }
}

