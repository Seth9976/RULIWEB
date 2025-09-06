package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;

abstract class HeaderBehavior extends ViewOffsetBehavior {
    class FlingRunnable implements Runnable {
        private final View layout;
        private final CoordinatorLayout parent;

        FlingRunnable(CoordinatorLayout coordinatorLayout0, View view0) {
            this.parent = coordinatorLayout0;
            this.layout = view0;
        }

        @Override
        public void run() {
            if(this.layout != null && HeaderBehavior.this.scroller != null) {
                if(HeaderBehavior.this.scroller.computeScrollOffset()) {
                    int v = HeaderBehavior.this.scroller.getCurrY();
                    HeaderBehavior.this.setHeaderTopBottomOffset(this.parent, this.layout, v);
                    ViewCompat.postOnAnimation(this.layout, this);
                    return;
                }
                HeaderBehavior.this.onFlingFinished(this.parent, this.layout);
            }
        }
    }

    private static final int INVALID_POINTER = -1;
    private int activePointerId;
    private Runnable flingRunnable;
    private boolean isBeingDragged;
    private int lastMotionY;
    OverScroller scroller;
    private int touchSlop;
    private VelocityTracker velocityTracker;

    public HeaderBehavior() {
        this.activePointerId = -1;
        this.touchSlop = -1;
    }

    public HeaderBehavior(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.activePointerId = -1;
        this.touchSlop = -1;
    }

    boolean canDragView(View view0) {
        return false;
    }

    private void ensureVelocityTracker() {
        if(this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
    }

    final boolean fling(CoordinatorLayout coordinatorLayout0, View view0, int v, int v1, float f) {
        Runnable runnable0 = this.flingRunnable;
        if(runnable0 != null) {
            view0.removeCallbacks(runnable0);
            this.flingRunnable = null;
        }
        if(this.scroller == null) {
            this.scroller = new OverScroller(view0.getContext());
        }
        this.scroller.fling(0, this.getTopAndBottomOffset(), 0, Math.round(f), 0, 0, v, v1);
        if(this.scroller.computeScrollOffset()) {
            FlingRunnable headerBehavior$FlingRunnable0 = new FlingRunnable(this, coordinatorLayout0, view0);
            this.flingRunnable = headerBehavior$FlingRunnable0;
            ViewCompat.postOnAnimation(view0, headerBehavior$FlingRunnable0);
            return true;
        }
        this.onFlingFinished(coordinatorLayout0, view0);
        return false;
    }

    int getMaxDragOffset(View view0) {
        return -view0.getHeight();
    }

    int getScrollRangeForDragFling(View view0) {
        return view0.getHeight();
    }

    int getTopBottomOffsetForScrollingSibling() {
        return this.getTopAndBottomOffset();
    }

    void onFlingFinished(CoordinatorLayout coordinatorLayout0, View view0) {
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout0, View view0, MotionEvent motionEvent0) {
        if(this.touchSlop < 0) {
            this.touchSlop = ViewConfiguration.get(coordinatorLayout0.getContext()).getScaledTouchSlop();
        }
        if(motionEvent0.getActionMasked() == 2 && this.isBeingDragged) {
            int v = this.activePointerId;
            if(v == -1) {
                return false;
            }
            int v1 = motionEvent0.findPointerIndex(v);
            if(v1 == -1) {
                return false;
            }
            int v2 = (int)motionEvent0.getY(v1);
            if(Math.abs(v2 - this.lastMotionY) > this.touchSlop) {
                this.lastMotionY = v2;
                return true;
            }
        }
        if(motionEvent0.getActionMasked() == 0) {
            this.activePointerId = -1;
            int v3 = (int)motionEvent0.getX();
            int v4 = (int)motionEvent0.getY();
            boolean z = this.canDragView(view0) && coordinatorLayout0.isPointInChildBounds(view0, v3, v4);
            this.isBeingDragged = z;
            if(z) {
                this.lastMotionY = v4;
                this.activePointerId = motionEvent0.getPointerId(0);
                this.ensureVelocityTracker();
                if(this.scroller != null && !this.scroller.isFinished()) {
                    this.scroller.abortAnimation();
                    return true;
                }
            }
        }
        VelocityTracker velocityTracker0 = this.velocityTracker;
        if(velocityTracker0 != null) {
            velocityTracker0.addMovement(motionEvent0);
        }
        return false;
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout0, View view0, MotionEvent motionEvent0) {
        boolean z;
        switch(motionEvent0.getActionMasked()) {
            case 1: {
                VelocityTracker velocityTracker0 = this.velocityTracker;
                if(velocityTracker0 == null) {
                    z = false;
                }
                else {
                    velocityTracker0.addMovement(motionEvent0);
                    this.velocityTracker.computeCurrentVelocity(1000);
                    float f = this.velocityTracker.getYVelocity(this.activePointerId);
                    this.fling(coordinatorLayout0, view0, -this.getScrollRangeForDragFling(view0), 0, f);
                    z = true;
                }
                goto label_23;
            }
            case 2: {
                int v = motionEvent0.findPointerIndex(this.activePointerId);
                if(v == -1) {
                    return false;
                }
                int v1 = (int)motionEvent0.getY(v);
                int v2 = this.lastMotionY - v1;
                this.lastMotionY = v1;
                this.scroll(coordinatorLayout0, view0, v2, this.getMaxDragOffset(view0), 0);
                z = false;
                break;
            }
            case 3: {
                z = false;
            label_23:
                this.isBeingDragged = false;
                this.activePointerId = -1;
                VelocityTracker velocityTracker1 = this.velocityTracker;
                if(velocityTracker1 != null) {
                    velocityTracker1.recycle();
                    this.velocityTracker = null;
                }
                break;
            }
            case 6: {
                int v3 = motionEvent0.getActionIndex() == 0 ? 1 : 0;
                this.activePointerId = motionEvent0.getPointerId(v3);
                this.lastMotionY = (int)(motionEvent0.getY(v3) + 0.5f);
                z = false;
                break;
            }
            default: {
                z = false;
            }
        }
        VelocityTracker velocityTracker2 = this.velocityTracker;
        if(velocityTracker2 != null) {
            velocityTracker2.addMovement(motionEvent0);
        }
        return this.isBeingDragged || z;
    }

    final int scroll(CoordinatorLayout coordinatorLayout0, View view0, int v, int v1, int v2) {
        return this.setHeaderTopBottomOffset(coordinatorLayout0, view0, this.getTopBottomOffsetForScrollingSibling() - v, v1, v2);
    }

    int setHeaderTopBottomOffset(CoordinatorLayout coordinatorLayout0, View view0, int v) {
        return this.setHeaderTopBottomOffset(coordinatorLayout0, view0, v, 0x80000000, 0x7FFFFFFF);
    }

    int setHeaderTopBottomOffset(CoordinatorLayout coordinatorLayout0, View view0, int v, int v1, int v2) {
        int v3 = this.getTopAndBottomOffset();
        if(v1 != 0 && v3 >= v1 && v3 <= v2) {
            int v4 = MathUtils.clamp(v, v1, v2);
            if(v3 != v4) {
                this.setTopAndBottomOffset(v4);
                return v3 - v4;
            }
        }
        return 0;
    }
}

