package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionHelper;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.motion.widget.MotionScene.Transition;
import androidx.constraintlayout.widget.ConstraintSet.Constraint;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.R.styleable;
import java.util.ArrayList;

public class Carousel extends MotionHelper {
    public interface Adapter {
        int count();

        void onNewItem(int arg1);

        void populate(View arg1, int arg2);
    }

    private static final boolean DEBUG = false;
    private static final String TAG = "Carousel";
    public static final int TOUCH_UP_CARRY_ON = 2;
    public static final int TOUCH_UP_IMMEDIATE_STOP = 1;
    private Adapter mAdapter;
    private int mAnimateTargetDelay;
    private int mBackwardTransition;
    private float mDampening;
    private int mEmptyViewBehavior;
    private int mFirstViewReference;
    private int mForwardTransition;
    private int mIndex;
    private boolean mInfiniteCarousel;
    int mLastStartId;
    private final ArrayList mList;
    private MotionLayout mMotionLayout;
    private int mNextState;
    private int mPreviousIndex;
    private int mPreviousState;
    private int mStartIndex;
    private int mTargetIndex;
    private int mTouchUpMode;
    Runnable mUpdateRunnable;
    private float mVelocityThreshold;

    public Carousel(Context context0) {
        super(context0);
        this.mAdapter = null;
        this.mList = new ArrayList();
        this.mPreviousIndex = 0;
        this.mIndex = 0;
        this.mFirstViewReference = -1;
        this.mInfiniteCarousel = false;
        this.mBackwardTransition = -1;
        this.mForwardTransition = -1;
        this.mPreviousState = -1;
        this.mNextState = -1;
        this.mDampening = 0.9f;
        this.mStartIndex = 0;
        this.mEmptyViewBehavior = 4;
        this.mTouchUpMode = 1;
        this.mVelocityThreshold = 2.0f;
        this.mTargetIndex = -1;
        this.mAnimateTargetDelay = 200;
        this.mLastStartId = -1;
        this.mUpdateRunnable = new Runnable() {
            @Override
            public void run() {
                Carousel.this.mMotionLayout.setProgress(0.0f);
                Carousel.this.updateItems();
                Carousel.this.mAdapter.onNewItem(Carousel.this.mIndex);
                float f = Carousel.this.mMotionLayout.getVelocity();
                if(Carousel.this.mTouchUpMode == 2 && f > Carousel.this.mVelocityThreshold && Carousel.this.mIndex < Carousel.this.mAdapter.count() - 1) {
                    float f1 = Carousel.this.mDampening;
                    if((Carousel.this.mIndex != 0 || Carousel.this.mPreviousIndex <= Carousel.this.mIndex) && (Carousel.this.mIndex != Carousel.this.mAdapter.count() - 1 || Carousel.this.mPreviousIndex >= Carousel.this.mIndex)) {
                        Carousel.this.mMotionLayout.post(new Runnable() {
                            @Override
                            public void run() {
                                Carousel.this.mMotionLayout.touchAnimateTo(5, 1.0f, f * f1);
                            }
                        });
                    }
                }
            }
        };
    }

    public Carousel(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mAdapter = null;
        this.mList = new ArrayList();
        this.mPreviousIndex = 0;
        this.mIndex = 0;
        this.mFirstViewReference = -1;
        this.mInfiniteCarousel = false;
        this.mBackwardTransition = -1;
        this.mForwardTransition = -1;
        this.mPreviousState = -1;
        this.mNextState = -1;
        this.mDampening = 0.9f;
        this.mStartIndex = 0;
        this.mEmptyViewBehavior = 4;
        this.mTouchUpMode = 1;
        this.mVelocityThreshold = 2.0f;
        this.mTargetIndex = -1;
        this.mAnimateTargetDelay = 200;
        this.mLastStartId = -1;
        this.mUpdateRunnable = new Runnable() {
            @Override
            public void run() {
                Carousel.this.mMotionLayout.setProgress(0.0f);
                Carousel.this.updateItems();
                Carousel.this.mAdapter.onNewItem(Carousel.this.mIndex);
                float f = Carousel.this.mMotionLayout.getVelocity();
                if(Carousel.this.mTouchUpMode == 2 && f > Carousel.this.mVelocityThreshold && Carousel.this.mIndex < Carousel.this.mAdapter.count() - 1) {
                    float f1 = Carousel.this.mDampening;
                    if((Carousel.this.mIndex != 0 || Carousel.this.mPreviousIndex <= Carousel.this.mIndex) && (Carousel.this.mIndex != Carousel.this.mAdapter.count() - 1 || Carousel.this.mPreviousIndex >= Carousel.this.mIndex)) {
                        Carousel.this.mMotionLayout.post(new Runnable() {
                            @Override
                            public void run() {
                                Carousel.this.mMotionLayout.touchAnimateTo(5, 1.0f, f * f1);
                            }
                        });
                    }
                }
            }
        };
        this.init(context0, attributeSet0);
    }

    public Carousel(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mAdapter = null;
        this.mList = new ArrayList();
        this.mPreviousIndex = 0;
        this.mIndex = 0;
        this.mFirstViewReference = -1;
        this.mInfiniteCarousel = false;
        this.mBackwardTransition = -1;
        this.mForwardTransition = -1;
        this.mPreviousState = -1;
        this.mNextState = -1;
        this.mDampening = 0.9f;
        this.mStartIndex = 0;
        this.mEmptyViewBehavior = 4;
        this.mTouchUpMode = 1;
        this.mVelocityThreshold = 2.0f;
        this.mTargetIndex = -1;
        this.mAnimateTargetDelay = 200;
        this.mLastStartId = -1;
        this.mUpdateRunnable = new Runnable() {
            @Override
            public void run() {
                Carousel.this.mMotionLayout.setProgress(0.0f);
                Carousel.this.updateItems();
                Carousel.this.mAdapter.onNewItem(Carousel.this.mIndex);
                float f = Carousel.this.mMotionLayout.getVelocity();
                if(Carousel.this.mTouchUpMode == 2 && f > Carousel.this.mVelocityThreshold && Carousel.this.mIndex < Carousel.this.mAdapter.count() - 1) {
                    float f1 = Carousel.this.mDampening;
                    if((Carousel.this.mIndex != 0 || Carousel.this.mPreviousIndex <= Carousel.this.mIndex) && (Carousel.this.mIndex != Carousel.this.mAdapter.count() - 1 || Carousel.this.mPreviousIndex >= Carousel.this.mIndex)) {
                        Carousel.this.mMotionLayout.post(new Runnable() {
                            @Override
                            public void run() {
                                Carousel.this.mMotionLayout.touchAnimateTo(5, 1.0f, f * f1);
                            }
                        });
                    }
                }
            }
        };
        this.init(context0, attributeSet0);
    }

    private void enableAllTransitions(boolean z) {
        for(Object object0: this.mMotionLayout.getDefinedTransitions()) {
            ((Transition)object0).setEnabled(z);
        }
    }

    private boolean enableTransition(int v, boolean z) {
        if(v == -1) {
            return false;
        }
        MotionLayout motionLayout0 = this.mMotionLayout;
        if(motionLayout0 == null) {
            return false;
        }
        Transition motionScene$Transition0 = motionLayout0.getTransition(v);
        if(motionScene$Transition0 == null) {
            return false;
        }
        if(z == motionScene$Transition0.isEnabled()) {
            return false;
        }
        motionScene$Transition0.setEnabled(z);
        return true;
    }

    public int getCount() {
        return this.mAdapter == null ? 0 : this.mAdapter.count();
    }

    public int getCurrentIndex() {
        return this.mIndex;
    }

    private void init(Context context0, AttributeSet attributeSet0) {
        if(attributeSet0 != null) {
            TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.Carousel);
            int v = typedArray0.getIndexCount();
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = typedArray0.getIndex(v1);
                if(v2 == styleable.Carousel_carousel_firstView) {
                    this.mFirstViewReference = typedArray0.getResourceId(v2, this.mFirstViewReference);
                }
                else if(v2 == styleable.Carousel_carousel_backwardTransition) {
                    this.mBackwardTransition = typedArray0.getResourceId(v2, this.mBackwardTransition);
                }
                else if(v2 == styleable.Carousel_carousel_forwardTransition) {
                    this.mForwardTransition = typedArray0.getResourceId(v2, this.mForwardTransition);
                }
                else if(v2 == styleable.Carousel_carousel_emptyViewsBehavior) {
                    this.mEmptyViewBehavior = typedArray0.getInt(v2, this.mEmptyViewBehavior);
                }
                else if(v2 == styleable.Carousel_carousel_previousState) {
                    this.mPreviousState = typedArray0.getResourceId(v2, this.mPreviousState);
                }
                else if(v2 == styleable.Carousel_carousel_nextState) {
                    this.mNextState = typedArray0.getResourceId(v2, this.mNextState);
                }
                else if(v2 == styleable.Carousel_carousel_touchUp_dampeningFactor) {
                    this.mDampening = typedArray0.getFloat(v2, this.mDampening);
                }
                else if(v2 == styleable.Carousel_carousel_touchUpMode) {
                    this.mTouchUpMode = typedArray0.getInt(v2, this.mTouchUpMode);
                }
                else if(v2 == styleable.Carousel_carousel_touchUp_velocityThreshold) {
                    this.mVelocityThreshold = typedArray0.getFloat(v2, this.mVelocityThreshold);
                }
                else if(v2 == styleable.Carousel_carousel_infinite) {
                    this.mInfiniteCarousel = typedArray0.getBoolean(v2, this.mInfiniteCarousel);
                }
            }
            typedArray0.recycle();
        }
    }

    public boolean isInfinite() {
        return this.mInfiniteCarousel;
    }

    public void jumpToIndex(int v) {
        this.mIndex = Math.max(0, Math.min(this.getCount() - 1, v));
        this.refresh();
    }

    // 检测为 Lambda 实现
    void lambda$updateItems$0$androidx-constraintlayout-helper-widget-Carousel() [...]

    @Override  // androidx.constraintlayout.widget.ConstraintHelper
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(this.getParent() instanceof MotionLayout) {
            MotionLayout motionLayout0 = (MotionLayout)this.getParent();
            this.mList.clear();
            for(int v = 0; v < this.mCount; ++v) {
                int v1 = this.mIds[v];
                View view0 = motionLayout0.getViewById(v1);
                if(this.mFirstViewReference == v1) {
                    this.mStartIndex = v;
                }
                this.mList.add(view0);
            }
            this.mMotionLayout = motionLayout0;
            if(this.mTouchUpMode == 2) {
                Transition motionScene$Transition0 = motionLayout0.getTransition(this.mForwardTransition);
                if(motionScene$Transition0 != null) {
                    motionScene$Transition0.setOnTouchUp(5);
                }
                Transition motionScene$Transition1 = this.mMotionLayout.getTransition(this.mBackwardTransition);
                if(motionScene$Transition1 != null) {
                    motionScene$Transition1.setOnTouchUp(5);
                }
            }
            this.updateItems();
        }
    }

    @Override  // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mList.clear();
    }

    @Override  // androidx.constraintlayout.motion.widget.MotionHelper
    public void onTransitionChange(MotionLayout motionLayout0, int v, int v1, float f) {
        this.mLastStartId = v;
    }

    @Override  // androidx.constraintlayout.motion.widget.MotionHelper
    public void onTransitionCompleted(MotionLayout motionLayout0, int v) {
        int v1 = this.mIndex;
        this.mPreviousIndex = v1;
        if(v == this.mNextState) {
            this.mIndex = v1 + 1;
        }
        else if(v == this.mPreviousState) {
            this.mIndex = v1 - 1;
        }
        if(this.mInfiniteCarousel) {
            if(this.mIndex >= this.mAdapter.count()) {
                this.mIndex = 0;
            }
            if(this.mIndex < 0) {
                this.mIndex = this.mAdapter.count() - 1;
            }
        }
        else {
            if(this.mIndex >= this.mAdapter.count()) {
                this.mIndex = this.mAdapter.count() - 1;
            }
            if(this.mIndex < 0) {
                this.mIndex = 0;
            }
        }
        if(this.mPreviousIndex != this.mIndex) {
            this.mMotionLayout.post(this.mUpdateRunnable);
        }
    }

    public void refresh() {
        int v = this.mList.size();
        for(int v1 = 0; v1 < v; ++v1) {
            View view0 = (View)this.mList.get(v1);
            if(this.mAdapter.count() == 0) {
                this.updateViewVisibility(view0, this.mEmptyViewBehavior);
            }
            else {
                this.updateViewVisibility(view0, 0);
            }
        }
        this.mMotionLayout.rebuildScene();
        this.updateItems();
    }

    public void setAdapter(Adapter carousel$Adapter0) {
        this.mAdapter = carousel$Adapter0;
    }

    public void setInfinite(boolean z) {
        this.mInfiniteCarousel = z;
    }

    public void transitionToIndex(int v, int v1) {
        this.mTargetIndex = Math.max(0, Math.min(this.getCount() - 1, v));
        int v2 = Math.max(0, v1);
        this.mAnimateTargetDelay = v2;
        this.mMotionLayout.setTransitionDuration(v2);
        if(v < this.mIndex) {
            this.mMotionLayout.transitionToState(this.mPreviousState, this.mAnimateTargetDelay);
            return;
        }
        this.mMotionLayout.transitionToState(this.mNextState, this.mAnimateTargetDelay);
    }

    private void updateItems() {
        if(this.mAdapter != null && this.mMotionLayout != null && this.mAdapter.count() != 0) {
            int v = this.mList.size();
            for(int v1 = 0; v1 < v; ++v1) {
                View view0 = (View)this.mList.get(v1);
                int v2 = this.mIndex + v1 - this.mStartIndex;
                if(this.mInfiniteCarousel) {
                    if(v2 < 0) {
                        int v3 = this.mEmptyViewBehavior;
                        if(v3 == 4) {
                            this.updateViewVisibility(view0, 0);
                        }
                        else {
                            this.updateViewVisibility(view0, v3);
                        }
                        if(v2 % this.mAdapter.count() == 0) {
                            this.mAdapter.populate(view0, 0);
                        }
                        else {
                            this.mAdapter.populate(view0, this.mAdapter.count() + v2 % this.mAdapter.count());
                        }
                    }
                    else {
                        if(v2 >= this.mAdapter.count()) {
                            if(v2 == this.mAdapter.count()) {
                                v2 = 0;
                            }
                            else if(v2 > this.mAdapter.count()) {
                                v2 %= this.mAdapter.count();
                            }
                            int v4 = this.mEmptyViewBehavior;
                            if(v4 == 4) {
                                this.updateViewVisibility(view0, 0);
                            }
                            else {
                                this.updateViewVisibility(view0, v4);
                            }
                        }
                        else {
                            this.updateViewVisibility(view0, 0);
                        }
                        this.mAdapter.populate(view0, v2);
                    }
                }
                else if(v2 < 0) {
                    this.updateViewVisibility(view0, this.mEmptyViewBehavior);
                }
                else if(v2 >= this.mAdapter.count()) {
                    this.updateViewVisibility(view0, this.mEmptyViewBehavior);
                }
                else {
                    this.updateViewVisibility(view0, 0);
                    this.mAdapter.populate(view0, v2);
                }
            }
            int v5 = this.mTargetIndex;
            if(v5 != -1 && v5 != this.mIndex) {
                this.mMotionLayout.post(() -> {
                    this.mMotionLayout.setTransitionDuration(this.mAnimateTargetDelay);
                    if(this.mTargetIndex < this.mIndex) {
                        this.mMotionLayout.transitionToState(this.mPreviousState, this.mAnimateTargetDelay);
                        return;
                    }
                    this.mMotionLayout.transitionToState(this.mNextState, this.mAnimateTargetDelay);
                });
            }
            else if(v5 == this.mIndex) {
                this.mTargetIndex = -1;
            }
            if(this.mBackwardTransition == -1 || this.mForwardTransition == -1) {
                Log.w("Carousel", "No backward or forward transitions defined for Carousel!");
            }
            else if(!this.mInfiniteCarousel) {
                int v6 = this.mAdapter.count();
                if(this.mIndex == 0) {
                    this.enableTransition(this.mBackwardTransition, false);
                }
                else {
                    this.enableTransition(this.mBackwardTransition, true);
                    this.mMotionLayout.setTransition(this.mBackwardTransition);
                }
                if(this.mIndex == v6 - 1) {
                    this.enableTransition(this.mForwardTransition, false);
                    return;
                }
                this.enableTransition(this.mForwardTransition, true);
                this.mMotionLayout.setTransition(this.mForwardTransition);
            }
        }
    }

    private boolean updateViewVisibility(int v, View view0, int v1) {
        ConstraintSet constraintSet0 = this.mMotionLayout.getConstraintSet(v);
        if(constraintSet0 == null) {
            return false;
        }
        Constraint constraintSet$Constraint0 = constraintSet0.getConstraint(view0.getId());
        if(constraintSet$Constraint0 == null) {
            return false;
        }
        constraintSet$Constraint0.propertySet.mVisibilityMode = 1;
        view0.setVisibility(v1);
        return true;
    }

    private boolean updateViewVisibility(View view0, int v) {
        MotionLayout motionLayout0 = this.mMotionLayout;
        if(motionLayout0 == null) {
            return false;
        }
        int[] arr_v = motionLayout0.getConstraintSetIds();
        boolean z = false;
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            z |= this.updateViewVisibility(arr_v[v1], view0, v);
        }
        return z;
    }
}

