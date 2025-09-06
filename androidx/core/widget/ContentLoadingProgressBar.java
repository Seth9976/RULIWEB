package androidx.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class ContentLoadingProgressBar extends ProgressBar {
    private static final int MIN_DELAY_MS = 500;
    private static final int MIN_SHOW_TIME_MS = 500;
    private final Runnable mDelayedHide;
    private final Runnable mDelayedShow;
    boolean mDismissed;
    boolean mPostedHide;
    boolean mPostedShow;
    long mStartTime;

    public ContentLoadingProgressBar(Context context0) {
        this(context0, null);
    }

    public ContentLoadingProgressBar(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0, 0);
        this.mStartTime = -1L;
        this.mPostedHide = false;
        this.mPostedShow = false;
        this.mDismissed = false;
        this.mDelayedHide = () -> {
            this.mPostedHide = false;
            this.mStartTime = -1L;
            this.setVisibility(8);
        };
        this.mDelayedShow = () -> {
            this.mPostedShow = false;
            if(!this.mDismissed) {
                this.mStartTime = System.currentTimeMillis();
                this.setVisibility(0);
            }
        };
    }

    public void hide() {
        this.post(() -> {
            this.mDismissed = true;
            this.removeCallbacks(this.mDelayedShow);
            this.mPostedShow = false;
            long v = System.currentTimeMillis() - this.mStartTime;
            if(v < 500L && this.mStartTime != -1L) {
                if(!this.mPostedHide) {
                    this.postDelayed(this.mDelayedHide, 500L - v);
                    this.mPostedHide = true;
                }
                return;
            }
            this.setVisibility(8);
        });
    }

    // 检测为 Lambda 实现
    private void hideOnUiThread() [...]

    // 检测为 Lambda 实现
    void lambda$new$0$androidx-core-widget-ContentLoadingProgressBar() [...]

    // 检测为 Lambda 实现
    void lambda$new$1$androidx-core-widget-ContentLoadingProgressBar() [...]

    @Override  // android.widget.ProgressBar
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.removeCallbacks();
    }

    @Override  // android.widget.ProgressBar
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.removeCallbacks();
    }

    private void removeCallbacks() {
        this.removeCallbacks(this.mDelayedHide);
        this.removeCallbacks(this.mDelayedShow);
    }

    public void show() {
        this.post(() -> {
            this.mStartTime = -1L;
            this.mDismissed = false;
            this.removeCallbacks(this.mDelayedHide);
            this.mPostedHide = false;
            if(!this.mPostedShow) {
                this.postDelayed(this.mDelayedShow, 500L);
                this.mPostedShow = true;
            }
        });
    }

    // 检测为 Lambda 实现
    private void showOnUiThread() [...]
}

