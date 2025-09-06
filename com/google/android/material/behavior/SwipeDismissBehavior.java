package com.google.android.material.behavior;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand.CommandArguments;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.widget.ViewDragHelper.Callback;
import androidx.customview.widget.ViewDragHelper;

public class SwipeDismissBehavior extends Behavior {
    public interface OnDismissListener {
        void onDismiss(View arg1);

        void onDragStateChanged(int arg1);
    }

    class SettleRunnable implements Runnable {
        private final boolean dismiss;
        private final View view;

        SettleRunnable(View view0, boolean z) {
            this.view = view0;
            this.dismiss = z;
        }

        @Override
        public void run() {
            if(SwipeDismissBehavior.this.viewDragHelper != null && SwipeDismissBehavior.this.viewDragHelper.continueSettling(true)) {
                ViewCompat.postOnAnimation(this.view, this);
                return;
            }
            if(this.dismiss && SwipeDismissBehavior.this.listener != null) {
                SwipeDismissBehavior.this.listener.onDismiss(this.view);
            }
        }
    }

    private static final float DEFAULT_ALPHA_END_DISTANCE = 0.5f;
    private static final float DEFAULT_ALPHA_START_DISTANCE = 0.0f;
    private static final float DEFAULT_DRAG_DISMISS_THRESHOLD = 0.5f;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    public static final int SWIPE_DIRECTION_ANY = 2;
    public static final int SWIPE_DIRECTION_END_TO_START = 1;
    public static final int SWIPE_DIRECTION_START_TO_END;
    float alphaEndSwipeDistance;
    float alphaStartSwipeDistance;
    private final Callback dragCallback;
    float dragDismissThreshold;
    private boolean interceptingEvents;
    OnDismissListener listener;
    private boolean requestingDisallowInterceptTouchEvent;
    private float sensitivity;
    private boolean sensitivitySet;
    int swipeDirection;
    ViewDragHelper viewDragHelper;

    public SwipeDismissBehavior() {
        this.sensitivity = 0.0f;
        this.swipeDirection = 2;
        this.dragDismissThreshold = 0.5f;
        this.alphaStartSwipeDistance = 0.0f;
        this.alphaEndSwipeDistance = 0.5f;
        this.dragCallback = new Callback() {
            private static final int INVALID_POINTER_ID = -1;
            private int activePointerId;
            private int originalCapturedViewLeft;

            {
                this.activePointerId = -1;
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public int clampViewPositionHorizontal(View view0, int v, int v1) {
                int v2;
                boolean z = ViewCompat.getLayoutDirection(view0) == 1;
                switch(SwipeDismissBehavior.this.swipeDirection) {
                    case 0: {
                        if(z) {
                            return SwipeDismissBehavior.clamp(this.originalCapturedViewLeft - view0.getWidth(), v, this.originalCapturedViewLeft);
                        }
                        v2 = this.originalCapturedViewLeft;
                        return SwipeDismissBehavior.clamp(v2, v, view0.getWidth() + v2);
                    }
                    case 1: {
                        if(z) {
                            v2 = this.originalCapturedViewLeft;
                            return SwipeDismissBehavior.clamp(v2, v, view0.getWidth() + v2);
                        }
                        return SwipeDismissBehavior.clamp(this.originalCapturedViewLeft - view0.getWidth(), v, this.originalCapturedViewLeft);
                    }
                    default: {
                        v2 = this.originalCapturedViewLeft - view0.getWidth();
                        int v3 = this.originalCapturedViewLeft;
                        return SwipeDismissBehavior.clamp(v2, v, view0.getWidth() + v3);
                    }
                }
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public int clampViewPositionVertical(View view0, int v, int v1) {
                return view0.getTop();
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public int getViewHorizontalDragRange(View view0) {
                return view0.getWidth();
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public void onViewCaptured(View view0, int v) {
                this.activePointerId = v;
                this.originalCapturedViewLeft = view0.getLeft();
                ViewParent viewParent0 = view0.getParent();
                if(viewParent0 != null) {
                    SwipeDismissBehavior.this.requestingDisallowInterceptTouchEvent = true;
                    viewParent0.requestDisallowInterceptTouchEvent(true);
                    SwipeDismissBehavior.this.requestingDisallowInterceptTouchEvent = false;
                }
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public void onViewDragStateChanged(int v) {
                if(SwipeDismissBehavior.this.listener != null) {
                    SwipeDismissBehavior.this.listener.onDragStateChanged(v);
                }
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public void onViewPositionChanged(View view0, int v, int v1, int v2, int v3) {
                float f = ((float)view0.getWidth()) * SwipeDismissBehavior.this.alphaStartSwipeDistance;
                float f1 = ((float)view0.getWidth()) * SwipeDismissBehavior.this.alphaEndSwipeDistance;
                float f2 = (float)Math.abs(v - this.originalCapturedViewLeft);
                if(f2 <= f) {
                    view0.setAlpha(1.0f);
                    return;
                }
                if(f2 >= f1) {
                    view0.setAlpha(0.0f);
                    return;
                }
                view0.setAlpha(SwipeDismissBehavior.clamp(0.0f, 1.0f - (f2 - f) / (f1 - f), 1.0f));
            }

            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public void onViewReleased(View view0, float f, float f1) {
                boolean z;
                int v3;
                this.activePointerId = -1;
                int v = view0.getWidth();
                if(this.shouldDismiss(view0, f)) {
                    if(f >= 0.0f) {
                        int v1 = view0.getLeft();
                        int v2 = this.originalCapturedViewLeft;
                        v3 = v1 >= v2 ? v2 + v : this.originalCapturedViewLeft - v;
                    }
                    else {
                        v3 = this.originalCapturedViewLeft - v;
                    }
                    z = true;
                }
                else {
                    v3 = this.originalCapturedViewLeft;
                    z = false;
                }
                if(SwipeDismissBehavior.this.viewDragHelper.settleCapturedViewAt(v3, view0.getTop())) {
                    ViewCompat.postOnAnimation(view0, new SettleRunnable(SwipeDismissBehavior.this, view0, z));
                    return;
                }
                if(z && SwipeDismissBehavior.this.listener != null) {
                    SwipeDismissBehavior.this.listener.onDismiss(view0);
                }
            }

            private boolean shouldDismiss(View view0, float f) {
                int v = Float.compare(f, 0.0f);
                if(v != 0) {
                    boolean z = ViewCompat.getLayoutDirection(view0) == 1;
                    switch(SwipeDismissBehavior.this.swipeDirection) {
                        case 0: {
                            return z ? f < 0.0f : v > 0;
                        }
                        case 1: {
                            return z ? v > 0 : f < 0.0f;
                        }
                        case 2: {
                            return true;
                        }
                        default: {
                            return false;
                        }
                    }
                }
                return Math.abs(view0.getLeft() - this.originalCapturedViewLeft) >= Math.round(((float)view0.getWidth()) * SwipeDismissBehavior.this.dragDismissThreshold);
            }

            // 去混淆评级： 低(20)
            @Override  // androidx.customview.widget.ViewDragHelper$Callback
            public boolean tryCaptureView(View view0, int v) {
                return (this.activePointerId == -1 || this.activePointerId == v) && SwipeDismissBehavior.this.canSwipeDismissView(view0);
            }
        };
    }

    public boolean canSwipeDismissView(View view0) {
        return true;
    }

    static float clamp(float f, float f1, float f2) {
        return Math.min(Math.max(f, f1), f2);
    }

    static int clamp(int v, int v1, int v2) {
        return Math.min(Math.max(v, v1), v2);
    }

    private void ensureViewDragHelper(ViewGroup viewGroup0) {
        if(this.viewDragHelper == null) {
            this.viewDragHelper = this.sensitivitySet ? ViewDragHelper.create(viewGroup0, this.sensitivity, this.dragCallback) : ViewDragHelper.create(viewGroup0, this.dragCallback);
        }
    }

    static float fraction(float f, float f1, float f2) [...] // Inlined contents

    public int getDragState() {
        return this.viewDragHelper == null ? 0 : this.viewDragHelper.getViewDragState();
    }

    public OnDismissListener getListener() {
        return this.listener;
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout0, View view0, MotionEvent motionEvent0) {
        boolean z = this.interceptingEvents;
        int v = motionEvent0.getActionMasked();
        switch(v) {
            case 0: {
                z = coordinatorLayout0.isPointInChildBounds(view0, ((int)motionEvent0.getX()), ((int)motionEvent0.getY()));
                this.interceptingEvents = z;
                break;
            }
            case 1: {
                this.interceptingEvents = false;
                break;
            }
            default: {
                if(v == 3) {
                    this.interceptingEvents = false;
                }
            }
        }
        if(z) {
            this.ensureViewDragHelper(coordinatorLayout0);
            return !this.requestingDisallowInterceptTouchEvent && this.viewDragHelper.shouldInterceptTouchEvent(motionEvent0);
        }
        return false;
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout0, View view0, int v) {
        boolean z = super.onLayoutChild(coordinatorLayout0, view0, v);
        if(ViewCompat.getImportantForAccessibility(view0) == 0) {
            ViewCompat.setImportantForAccessibility(view0, 1);
            this.updateAccessibilityActions(view0);
        }
        return z;
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout0, View view0, MotionEvent motionEvent0) {
        if(this.viewDragHelper != null) {
            if(!this.requestingDisallowInterceptTouchEvent || motionEvent0.getActionMasked() != 3) {
                this.viewDragHelper.processTouchEvent(motionEvent0);
            }
            return true;
        }
        return false;
    }

    public void setDragDismissDistance(float f) {
        this.dragDismissThreshold = SwipeDismissBehavior.clamp(0.0f, f, 1.0f);
    }

    public void setEndAlphaSwipeDistance(float f) {
        this.alphaEndSwipeDistance = SwipeDismissBehavior.clamp(0.0f, f, 1.0f);
    }

    public void setListener(OnDismissListener swipeDismissBehavior$OnDismissListener0) {
        this.listener = swipeDismissBehavior$OnDismissListener0;
    }

    public void setSensitivity(float f) {
        this.sensitivity = f;
        this.sensitivitySet = true;
    }

    public void setStartAlphaSwipeDistance(float f) {
        this.alphaStartSwipeDistance = SwipeDismissBehavior.clamp(0.0f, f, 1.0f);
    }

    public void setSwipeDirection(int v) {
        this.swipeDirection = v;
    }

    private void updateAccessibilityActions(View view0) {
        ViewCompat.removeAccessibilityAction(view0, 0x100000);
        if(this.canSwipeDismissView(view0)) {
            com.google.android.material.behavior.SwipeDismissBehavior.2 swipeDismissBehavior$20 = new AccessibilityViewCommand() {
                @Override  // androidx.core.view.accessibility.AccessibilityViewCommand
                public boolean perform(View view0, CommandArguments accessibilityViewCommand$CommandArguments0) {
                    boolean z = false;
                    if(SwipeDismissBehavior.this.canSwipeDismissView(view0)) {
                        if(ViewCompat.getLayoutDirection(view0) == 1) {
                            z = true;
                        }
                        ViewCompat.offsetLeftAndRight(view0, ((SwipeDismissBehavior.this.swipeDirection != 0 || !z) && (SwipeDismissBehavior.this.swipeDirection != 1 || z) ? view0.getWidth() : -view0.getWidth()));
                        view0.setAlpha(0.0f);
                        if(SwipeDismissBehavior.this.listener != null) {
                            SwipeDismissBehavior.this.listener.onDismiss(view0);
                        }
                        return true;
                    }
                    return false;
                }
            };
            ViewCompat.replaceAccessibilityAction(view0, AccessibilityActionCompat.ACTION_DISMISS, null, swipeDismissBehavior$20);
        }
    }
}

