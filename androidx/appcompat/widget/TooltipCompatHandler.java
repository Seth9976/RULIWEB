package androidx.appcompat.widget;

import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnHoverListener;
import android.view.View.OnLongClickListener;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat;

class TooltipCompatHandler implements View.OnAttachStateChangeListener, View.OnHoverListener, View.OnLongClickListener {
    private static final long HOVER_HIDE_TIMEOUT_MS = 15000L;
    private static final long HOVER_HIDE_TIMEOUT_SHORT_MS = 3000L;
    private static final long LONG_CLICK_HIDE_TIMEOUT_MS = 2500L;
    private static final String TAG = "TooltipCompatHandler";
    private final View mAnchor;
    private int mAnchorX;
    private int mAnchorY;
    private boolean mForceNextChangeSignificant;
    private boolean mFromTouch;
    private final Runnable mHideRunnable;
    private final int mHoverSlop;
    private TooltipPopup mPopup;
    private final Runnable mShowRunnable;
    private final CharSequence mTooltipText;
    private static TooltipCompatHandler sActiveHandler;
    private static TooltipCompatHandler sPendingHandler;

    private TooltipCompatHandler(View view0, CharSequence charSequence0) {
        this.mShowRunnable = () -> this.show(false);
        this.mHideRunnable = () -> {
            if(TooltipCompatHandler.sActiveHandler == this) {
                TooltipCompatHandler.sActiveHandler = null;
                TooltipPopup tooltipPopup0 = this.mPopup;
                if(tooltipPopup0 == null) {
                    Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
                }
                else {
                    tooltipPopup0.hide();
                    this.mPopup = null;
                    this.forceNextChangeSignificant();
                    this.mAnchor.removeOnAttachStateChangeListener(this);
                }
            }
            if(TooltipCompatHandler.sPendingHandler == this) {
                TooltipCompatHandler.setPendingHandler(null);
            }
            this.mAnchor.removeCallbacks(this.mHideRunnable);
        };
        this.mAnchor = view0;
        this.mTooltipText = charSequence0;
        this.mHoverSlop = ViewConfigurationCompat.getScaledHoverSlop(ViewConfiguration.get(view0.getContext()));
        this.forceNextChangeSignificant();
        view0.setOnLongClickListener(this);
        view0.setOnHoverListener(this);
    }

    private void cancelPendingShow() {
        this.mAnchor.removeCallbacks(this.mShowRunnable);
    }

    private void forceNextChangeSignificant() {
        this.mForceNextChangeSignificant = true;
    }

    // 检测为 Lambda 实现
    void hide() [...]

    // 检测为 Lambda 实现
    void lambda$new$0$androidx-appcompat-widget-TooltipCompatHandler() [...]

    @Override  // android.view.View$OnHoverListener
    public boolean onHover(View view0, MotionEvent motionEvent0) {
        if(this.mPopup != null && this.mFromTouch) {
            return false;
        }
        AccessibilityManager accessibilityManager0 = (AccessibilityManager)this.mAnchor.getContext().getSystemService("accessibility");
        if(accessibilityManager0.isEnabled() && accessibilityManager0.isTouchExplorationEnabled()) {
            return false;
        }
        switch(motionEvent0.getAction()) {
            case 7: {
                if(this.mAnchor.isEnabled() && this.mPopup == null && this.updateAnchorPos(motionEvent0)) {
                    TooltipCompatHandler.setPendingHandler(this);
                }
                return false;
            }
            case 10: {
                this.forceNextChangeSignificant();
                this.hide();
                return false;
            }
            default: {
                return false;
            }
        }
    }

    @Override  // android.view.View$OnLongClickListener
    public boolean onLongClick(View view0) {
        this.mAnchorX = view0.getWidth() / 2;
        this.mAnchorY = view0.getHeight() / 2;
        this.show(true);
        return true;
    }

    @Override  // android.view.View$OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view0) {
    }

    @Override  // android.view.View$OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view0) {
        this.hide();
    }

    private void scheduleShow() {
        long v = (long)ViewConfiguration.getLongPressTimeout();
        this.mAnchor.postDelayed(this.mShowRunnable, v);
    }

    private static void setPendingHandler(TooltipCompatHandler tooltipCompatHandler0) {
        TooltipCompatHandler tooltipCompatHandler1 = TooltipCompatHandler.sPendingHandler;
        if(tooltipCompatHandler1 != null) {
            tooltipCompatHandler1.cancelPendingShow();
        }
        TooltipCompatHandler.sPendingHandler = tooltipCompatHandler0;
        if(tooltipCompatHandler0 != null) {
            tooltipCompatHandler0.scheduleShow();
        }
    }

    public static void setTooltipText(View view0, CharSequence charSequence0) {
        if(TooltipCompatHandler.sPendingHandler != null && TooltipCompatHandler.sPendingHandler.mAnchor == view0) {
            TooltipCompatHandler.setPendingHandler(null);
        }
        if(TextUtils.isEmpty(charSequence0)) {
            TooltipCompatHandler tooltipCompatHandler0 = TooltipCompatHandler.sActiveHandler;
            if(tooltipCompatHandler0 != null && tooltipCompatHandler0.mAnchor == view0) {
                tooltipCompatHandler0.hide();
            }
            view0.setOnLongClickListener(null);
            view0.setLongClickable(false);
            view0.setOnHoverListener(null);
            return;
        }
        new TooltipCompatHandler(view0, charSequence0);
    }

    void show(boolean z) {
        long v2;
        long v1;
        long v;
        if(!this.mAnchor.isAttachedToWindow()) {
            return;
        }
        TooltipCompatHandler.setPendingHandler(null);
        TooltipCompatHandler tooltipCompatHandler0 = TooltipCompatHandler.sActiveHandler;
        if(tooltipCompatHandler0 != null) {
            tooltipCompatHandler0.hide();
        }
        TooltipCompatHandler.sActiveHandler = this;
        this.mFromTouch = z;
        TooltipPopup tooltipPopup0 = new TooltipPopup(this.mAnchor.getContext());
        this.mPopup = tooltipPopup0;
        tooltipPopup0.show(this.mAnchor, this.mAnchorX, this.mAnchorY, this.mFromTouch, this.mTooltipText);
        this.mAnchor.addOnAttachStateChangeListener(this);
        if(this.mFromTouch) {
            v = 2500L;
        }
        else {
            if((ViewCompat.getWindowSystemUiVisibility(this.mAnchor) & 1) == 1) {
                v1 = (long)ViewConfiguration.getLongPressTimeout();
                v2 = 3000L;
            }
            else {
                v1 = (long)ViewConfiguration.getLongPressTimeout();
                v2 = 15000L;
            }
            v = v2 - v1;
        }
        this.mAnchor.removeCallbacks(this.mHideRunnable);
        this.mAnchor.postDelayed(this.mHideRunnable, v);
    }

    private boolean updateAnchorPos(MotionEvent motionEvent0) {
        int v = (int)motionEvent0.getX();
        int v1 = (int)motionEvent0.getY();
        if(!this.mForceNextChangeSignificant && Math.abs(v - this.mAnchorX) <= this.mHoverSlop && Math.abs(v1 - this.mAnchorY) <= this.mHoverSlop) {
            return false;
        }
        this.mAnchorX = v;
        this.mAnchorY = v1;
        this.mForceNextChangeSignificant = false;
        return true;
    }
}

