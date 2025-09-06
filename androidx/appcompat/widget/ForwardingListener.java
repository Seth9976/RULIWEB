package androidx.appcompat.widget;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnTouchListener;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import androidx.appcompat.view.menu.ShowableListMenu;

public abstract class ForwardingListener implements View.OnAttachStateChangeListener, View.OnTouchListener {
    class DisallowIntercept implements Runnable {
        @Override
        public void run() {
            ViewParent viewParent0 = ForwardingListener.this.mSrc.getParent();
            if(viewParent0 != null) {
                viewParent0.requestDisallowInterceptTouchEvent(true);
            }
        }
    }

    class TriggerLongPress implements Runnable {
        @Override
        public void run() {
            ForwardingListener.this.onLongPress();
        }
    }

    private int mActivePointerId;
    private Runnable mDisallowIntercept;
    private boolean mForwarding;
    private final int mLongPressTimeout;
    private final float mScaledTouchSlop;
    final View mSrc;
    private final int mTapTimeout;
    private final int[] mTmpLocation;
    private Runnable mTriggerLongPress;

    public ForwardingListener(View view0) {
        this.mTmpLocation = new int[2];
        this.mSrc = view0;
        view0.setLongClickable(true);
        view0.addOnAttachStateChangeListener(this);
        this.mScaledTouchSlop = (float)ViewConfiguration.get(view0.getContext()).getScaledTouchSlop();
        this.mTapTimeout = 100;
        this.mLongPressTimeout = (ViewConfiguration.getLongPressTimeout() + 100) / 2;
    }

    private void clearCallbacks() {
        Runnable runnable0 = this.mTriggerLongPress;
        if(runnable0 != null) {
            this.mSrc.removeCallbacks(runnable0);
        }
        Runnable runnable1 = this.mDisallowIntercept;
        if(runnable1 != null) {
            this.mSrc.removeCallbacks(runnable1);
        }
    }

    public abstract ShowableListMenu getPopup();

    protected boolean onForwardingStarted() {
        ShowableListMenu showableListMenu0 = this.getPopup();
        if(showableListMenu0 != null && !showableListMenu0.isShowing()) {
            showableListMenu0.show();
        }
        return true;
    }

    protected boolean onForwardingStopped() {
        ShowableListMenu showableListMenu0 = this.getPopup();
        if(showableListMenu0 != null && showableListMenu0.isShowing()) {
            showableListMenu0.dismiss();
        }
        return true;
    }

    // 检测为 Lambda 实现
    void onLongPress() [...]

    @Override  // android.view.View$OnTouchListener
    public boolean onTouch(View view0, MotionEvent motionEvent0) {
        boolean z1;
        boolean z = this.mForwarding;
        if(!z) {
            z1 = this.onTouchObserved(motionEvent0) && this.onForwardingStarted();
            if(z1) {
                long v = SystemClock.uptimeMillis();
                MotionEvent motionEvent1 = MotionEvent.obtain(v, v, 3, 0.0f, 0.0f, 0);
                this.mSrc.onTouchEvent(motionEvent1);
                motionEvent1.recycle();
            }
        }
        else if(!this.onTouchForwarded(motionEvent0) && this.onForwardingStopped()) {
            z1 = false;
        }
        else {
            z1 = true;
        }
        this.mForwarding = z1;
        return z1 || z;
    }

    private boolean onTouchForwarded(MotionEvent motionEvent0) {
        View view0 = this.mSrc;
        ShowableListMenu showableListMenu0 = this.getPopup();
        if(showableListMenu0 != null && showableListMenu0.isShowing()) {
            DropDownListView dropDownListView0 = (DropDownListView)showableListMenu0.getListView();
            if(dropDownListView0 != null && dropDownListView0.isShown()) {
                MotionEvent motionEvent1 = MotionEvent.obtainNoHistory(motionEvent0);
                this.toGlobalMotionEvent(view0, motionEvent1);
                this.toLocalMotionEvent(dropDownListView0, motionEvent1);
                boolean z = dropDownListView0.onForwardedEvent(motionEvent1, this.mActivePointerId);
                motionEvent1.recycle();
                switch(motionEvent0.getActionMasked()) {
                    case 1: 
                    case 3: {
                        return false;
                    }
                    default: {
                        return z;
                    }
                }
            }
        }
        return false;
    }

    private boolean onTouchObserved(MotionEvent motionEvent0) {
        View view0 = this.mSrc;
        if(!view0.isEnabled()) {
            return false;
        }
        switch(motionEvent0.getActionMasked()) {
            case 0: {
                this.mActivePointerId = motionEvent0.getPointerId(0);
                if(this.mDisallowIntercept == null) {
                    this.mDisallowIntercept = new DisallowIntercept(this);
                }
                view0.postDelayed(this.mDisallowIntercept, ((long)this.mTapTimeout));
                if(this.mTriggerLongPress == null) {
                    this.mTriggerLongPress = () -> {
                        ForwardingListener.this.clearCallbacks();
                        View view0 = ForwardingListener.this.mSrc;
                        if(view0.isEnabled() && !view0.isLongClickable() && ForwardingListener.this.onForwardingStarted()) {
                            view0.getParent().requestDisallowInterceptTouchEvent(true);
                            long v = SystemClock.uptimeMillis();
                            MotionEvent motionEvent0 = MotionEvent.obtain(v, v, 3, 0.0f, 0.0f, 0);
                            view0.onTouchEvent(motionEvent0);
                            motionEvent0.recycle();
                            ForwardingListener.this.mForwarding = true;
                        }
                    };
                }
                view0.postDelayed(this.mTriggerLongPress, ((long)this.mLongPressTimeout));
                return false;
            }
            case 2: {
                int v = motionEvent0.findPointerIndex(this.mActivePointerId);
                if(v >= 0 && !ForwardingListener.pointInView(view0, motionEvent0.getX(v), motionEvent0.getY(v), this.mScaledTouchSlop)) {
                    this.clearCallbacks();
                    view0.getParent().requestDisallowInterceptTouchEvent(true);
                    return true;
                }
                return false;
            }
            case 1: 
            case 3: {
                this.clearCallbacks();
                return false;
            }
            default: {
                return false;
            }
        }
    }

    @Override  // android.view.View$OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view0) {
    }

    @Override  // android.view.View$OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view0) {
        this.mForwarding = false;
        this.mActivePointerId = -1;
        Runnable runnable0 = this.mDisallowIntercept;
        if(runnable0 != null) {
            this.mSrc.removeCallbacks(runnable0);
        }
    }

    private static boolean pointInView(View view0, float f, float f1, float f2) {
        return f >= -f2 && f1 >= -f2 && f < ((float)(view0.getRight() - view0.getLeft())) + f2 && f1 < ((float)(view0.getBottom() - view0.getTop())) + f2;
    }

    private boolean toGlobalMotionEvent(View view0, MotionEvent motionEvent0) {
        view0.getLocationOnScreen(this.mTmpLocation);
        motionEvent0.offsetLocation(((float)this.mTmpLocation[0]), ((float)this.mTmpLocation[1]));
        return true;
    }

    private boolean toLocalMotionEvent(View view0, MotionEvent motionEvent0) {
        view0.getLocationOnScreen(this.mTmpLocation);
        motionEvent0.offsetLocation(((float)(-this.mTmpLocation[0])), ((float)(-this.mTmpLocation[1])));
        return true;
    }
}

