package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.MotionEvent;

class FastScroller extends ItemDecoration implements OnItemTouchListener {
    class AnimatorListener extends AnimatorListenerAdapter {
        private boolean mCanceled;

        AnimatorListener() {
            this.mCanceled = false;
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationCancel(Animator animator0) {
            this.mCanceled = true;
        }

        @Override  // android.animation.AnimatorListenerAdapter
        public void onAnimationEnd(Animator animator0) {
            if(this.mCanceled) {
                this.mCanceled = false;
                return;
            }
            if(((float)(((Float)FastScroller.this.mShowHideAnimator.getAnimatedValue()))) == 0.0f) {
                FastScroller.this.mAnimationState = 0;
                FastScroller.this.setState(0);
                return;
            }
            FastScroller.this.mAnimationState = 2;
            FastScroller.this.requestRedraw();
        }
    }

    class AnimatorUpdater implements ValueAnimator.AnimatorUpdateListener {
        @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator0) {
            float f = (float)(((Float)valueAnimator0.getAnimatedValue()));
            FastScroller.this.mVerticalThumbDrawable.setAlpha(((int)(f * 255.0f)));
            FastScroller.this.mVerticalTrackDrawable.setAlpha(((int)(f * 255.0f)));
            FastScroller.this.requestRedraw();
        }
    }

    private static final int ANIMATION_STATE_FADING_IN = 1;
    private static final int ANIMATION_STATE_FADING_OUT = 3;
    private static final int ANIMATION_STATE_IN = 2;
    private static final int ANIMATION_STATE_OUT = 0;
    private static final int DRAG_NONE = 0;
    private static final int DRAG_X = 1;
    private static final int DRAG_Y = 2;
    private static final int[] EMPTY_STATE_SET = null;
    private static final int HIDE_DELAY_AFTER_DRAGGING_MS = 1200;
    private static final int HIDE_DELAY_AFTER_VISIBLE_MS = 1500;
    private static final int HIDE_DURATION_MS = 500;
    private static final int[] PRESSED_STATE_SET = null;
    private static final int SCROLLBAR_FULL_OPAQUE = 0xFF;
    private static final int SHOW_DURATION_MS = 500;
    private static final int STATE_DRAGGING = 2;
    private static final int STATE_HIDDEN = 0;
    private static final int STATE_VISIBLE = 1;
    int mAnimationState;
    private int mDragState;
    private final Runnable mHideRunnable;
    float mHorizontalDragX;
    private final int[] mHorizontalRange;
    int mHorizontalThumbCenterX;
    private final StateListDrawable mHorizontalThumbDrawable;
    private final int mHorizontalThumbHeight;
    int mHorizontalThumbWidth;
    private final Drawable mHorizontalTrackDrawable;
    private final int mHorizontalTrackHeight;
    private final int mMargin;
    private boolean mNeedHorizontalScrollbar;
    private boolean mNeedVerticalScrollbar;
    private final OnScrollListener mOnScrollListener;
    private RecyclerView mRecyclerView;
    private int mRecyclerViewHeight;
    private int mRecyclerViewWidth;
    private final int mScrollbarMinimumRange;
    final ValueAnimator mShowHideAnimator;
    private int mState;
    float mVerticalDragY;
    private final int[] mVerticalRange;
    int mVerticalThumbCenterY;
    final StateListDrawable mVerticalThumbDrawable;
    int mVerticalThumbHeight;
    private final int mVerticalThumbWidth;
    final Drawable mVerticalTrackDrawable;
    private final int mVerticalTrackWidth;

    static {
        FastScroller.PRESSED_STATE_SET = new int[]{0x10100A7};
        FastScroller.EMPTY_STATE_SET = new int[0];
    }

    FastScroller(RecyclerView recyclerView0, StateListDrawable stateListDrawable0, Drawable drawable0, StateListDrawable stateListDrawable1, Drawable drawable1, int v, int v1, int v2) {
        this.mRecyclerViewWidth = 0;
        this.mRecyclerViewHeight = 0;
        this.mNeedVerticalScrollbar = false;
        this.mNeedHorizontalScrollbar = false;
        this.mState = 0;
        this.mDragState = 0;
        this.mVerticalRange = new int[2];
        this.mHorizontalRange = new int[2];
        ValueAnimator valueAnimator0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.mShowHideAnimator = valueAnimator0;
        this.mAnimationState = 0;
        this.mHideRunnable = () -> {
            int v1 = FastScroller.this.mAnimationState;
            if(v1 == 1) {
                FastScroller.this.mShowHideAnimator.cancel();
            }
            else if(v1 != 2) {
                return;
            }
            FastScroller.this.mAnimationState = 3;
            float[] arr_f = {((float)(((Float)FastScroller.this.mShowHideAnimator.getAnimatedValue()))), 0.0f};
            FastScroller.this.mShowHideAnimator.setFloatValues(arr_f);
            FastScroller.this.mShowHideAnimator.setDuration(((long)500));
            FastScroller.this.mShowHideAnimator.start();
        };
        this.mOnScrollListener = new OnScrollListener() {
            @Override  // androidx.recyclerview.widget.RecyclerView$OnScrollListener
            public void onScrolled(RecyclerView recyclerView0, int v, int v1) {
                int v2 = recyclerView0.computeHorizontalScrollOffset();
                int v3 = recyclerView0.computeVerticalScrollOffset();
                FastScroller.this.updateScrollPosition(v2, v3);
            }
        };
        this.mVerticalThumbDrawable = stateListDrawable0;
        this.mVerticalTrackDrawable = drawable0;
        this.mHorizontalThumbDrawable = stateListDrawable1;
        this.mHorizontalTrackDrawable = drawable1;
        this.mVerticalThumbWidth = Math.max(v, stateListDrawable0.getIntrinsicWidth());
        this.mVerticalTrackWidth = Math.max(v, drawable0.getIntrinsicWidth());
        this.mHorizontalThumbHeight = Math.max(v, stateListDrawable1.getIntrinsicWidth());
        this.mHorizontalTrackHeight = Math.max(v, drawable1.getIntrinsicWidth());
        this.mScrollbarMinimumRange = v1;
        this.mMargin = v2;
        stateListDrawable0.setAlpha(0xFF);
        drawable0.setAlpha(0xFF);
        valueAnimator0.addListener(new AnimatorListener(this));
        valueAnimator0.addUpdateListener(new AnimatorUpdater(this));
        this.attachToRecyclerView(recyclerView0);
    }

    public void attachToRecyclerView(RecyclerView recyclerView0) {
        RecyclerView recyclerView1 = this.mRecyclerView;
        if(recyclerView1 != recyclerView0) {
            if(recyclerView1 != null) {
                this.destroyCallbacks();
            }
            this.mRecyclerView = recyclerView0;
            if(recyclerView0 != null) {
                this.setupCallbacks();
            }
        }
    }

    private void cancelHide() {
        this.mRecyclerView.removeCallbacks(this.mHideRunnable);
    }

    private void destroyCallbacks() {
        this.mRecyclerView.removeItemDecoration(this);
        this.mRecyclerView.removeOnItemTouchListener(this);
        this.mRecyclerView.removeOnScrollListener(this.mOnScrollListener);
        this.cancelHide();
    }

    private void drawHorizontalScrollbar(Canvas canvas0) {
        int v = this.mRecyclerViewHeight - this.mHorizontalThumbHeight;
        int v1 = this.mHorizontalThumbCenterX - this.mHorizontalThumbWidth / 2;
        this.mHorizontalThumbDrawable.setBounds(0, 0, this.mHorizontalThumbWidth, this.mHorizontalThumbHeight);
        this.mHorizontalTrackDrawable.setBounds(0, 0, this.mRecyclerViewWidth, this.mHorizontalTrackHeight);
        canvas0.translate(0.0f, ((float)v));
        this.mHorizontalTrackDrawable.draw(canvas0);
        canvas0.translate(((float)v1), 0.0f);
        this.mHorizontalThumbDrawable.draw(canvas0);
        canvas0.translate(((float)(-v1)), ((float)(-v)));
    }

    private void drawVerticalScrollbar(Canvas canvas0) {
        int v = this.mRecyclerViewWidth - this.mVerticalThumbWidth;
        int v1 = this.mVerticalThumbCenterY - this.mVerticalThumbHeight / 2;
        this.mVerticalThumbDrawable.setBounds(0, 0, this.mVerticalThumbWidth, this.mVerticalThumbHeight);
        this.mVerticalTrackDrawable.setBounds(0, 0, this.mVerticalTrackWidth, this.mRecyclerViewHeight);
        if(this.isLayoutRTL()) {
            this.mVerticalTrackDrawable.draw(canvas0);
            canvas0.translate(((float)this.mVerticalThumbWidth), ((float)v1));
            canvas0.scale(-1.0f, 1.0f);
            this.mVerticalThumbDrawable.draw(canvas0);
            canvas0.scale(-1.0f, 1.0f);
            canvas0.translate(((float)(-this.mVerticalThumbWidth)), ((float)(-v1)));
            return;
        }
        canvas0.translate(((float)v), 0.0f);
        this.mVerticalTrackDrawable.draw(canvas0);
        canvas0.translate(0.0f, ((float)v1));
        this.mVerticalThumbDrawable.draw(canvas0);
        canvas0.translate(((float)(-v)), ((float)(-v1)));
    }

    private int[] getHorizontalRange() {
        this.mHorizontalRange[0] = this.mMargin;
        this.mHorizontalRange[1] = this.mRecyclerViewWidth - this.mMargin;
        return this.mHorizontalRange;
    }

    Drawable getHorizontalThumbDrawable() {
        return this.mHorizontalThumbDrawable;
    }

    Drawable getHorizontalTrackDrawable() {
        return this.mHorizontalTrackDrawable;
    }

    private int[] getVerticalRange() {
        this.mVerticalRange[0] = this.mMargin;
        this.mVerticalRange[1] = this.mRecyclerViewHeight - this.mMargin;
        return this.mVerticalRange;
    }

    Drawable getVerticalThumbDrawable() {
        return this.mVerticalThumbDrawable;
    }

    Drawable getVerticalTrackDrawable() {
        return this.mVerticalTrackDrawable;
    }

    // 检测为 Lambda 实现
    void hide(int v) [...]

    private void horizontalScrollTo(float f) {
        int[] arr_v = this.getHorizontalRange();
        float f1 = Math.max(arr_v[0], Math.min(arr_v[1], f));
        if(Math.abs(((float)this.mHorizontalThumbCenterX) - f1) < 2.0f) {
            return;
        }
        int v = this.scrollTo(this.mHorizontalDragX, f1, arr_v, this.mRecyclerView.computeHorizontalScrollRange(), this.mRecyclerView.computeHorizontalScrollOffset(), this.mRecyclerViewWidth);
        if(v != 0) {
            this.mRecyclerView.scrollBy(v, 0);
        }
        this.mHorizontalDragX = f1;
    }

    public boolean isDragging() {
        return this.mState == 2;
    }

    private boolean isLayoutRTL() {
        return this.mRecyclerView.getLayoutDirection() == 1;
    }

    boolean isPointInsideHorizontalThumb(float f, float f1) {
        return f1 >= ((float)(this.mRecyclerViewHeight - this.mHorizontalThumbHeight)) && (f >= ((float)(this.mHorizontalThumbCenterX - this.mHorizontalThumbWidth / 2)) && f <= ((float)(this.mHorizontalThumbCenterX + this.mHorizontalThumbWidth / 2)));
    }

    boolean isPointInsideVerticalThumb(float f, float f1) {
        if(this.isLayoutRTL()) {
            return f <= ((float)this.mVerticalThumbWidth) ? f1 >= ((float)(this.mVerticalThumbCenterY - this.mVerticalThumbHeight / 2)) && f1 <= ((float)(this.mVerticalThumbCenterY + this.mVerticalThumbHeight / 2)) : false;
        }
        return f >= ((float)(this.mRecyclerViewWidth - this.mVerticalThumbWidth)) ? f1 >= ((float)(this.mVerticalThumbCenterY - this.mVerticalThumbHeight / 2)) && f1 <= ((float)(this.mVerticalThumbCenterY + this.mVerticalThumbHeight / 2)) : false;
    }

    boolean isVisible() {
        return this.mState == 1;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$ItemDecoration
    public void onDrawOver(Canvas canvas0, RecyclerView recyclerView0, State recyclerView$State0) {
        if(this.mRecyclerViewWidth == this.mRecyclerView.getWidth() && this.mRecyclerViewHeight == this.mRecyclerView.getHeight()) {
            if(this.mAnimationState != 0) {
                if(this.mNeedVerticalScrollbar) {
                    this.drawVerticalScrollbar(canvas0);
                }
                if(this.mNeedHorizontalScrollbar) {
                    this.drawHorizontalScrollbar(canvas0);
                }
            }
            return;
        }
        this.mRecyclerViewWidth = this.mRecyclerView.getWidth();
        this.mRecyclerViewHeight = this.mRecyclerView.getHeight();
        this.setState(0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$OnItemTouchListener
    public boolean onInterceptTouchEvent(RecyclerView recyclerView0, MotionEvent motionEvent0) {
        int v = this.mState;
        if(v == 1) {
            boolean z = this.isPointInsideVerticalThumb(motionEvent0.getX(), motionEvent0.getY());
            boolean z1 = this.isPointInsideHorizontalThumb(motionEvent0.getX(), motionEvent0.getY());
            if(motionEvent0.getAction() == 0 && (z || z1)) {
                if(z1) {
                    this.mDragState = 1;
                    this.mHorizontalDragX = (float)(((int)motionEvent0.getX()));
                }
                else if(z) {
                    this.mDragState = 2;
                    this.mVerticalDragY = (float)(((int)motionEvent0.getY()));
                }
                this.setState(2);
                return true;
            }
            return false;
        }
        return v == 2;
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$OnItemTouchListener
    public void onTouchEvent(RecyclerView recyclerView0, MotionEvent motionEvent0) {
        if(this.mState != 0) {
            switch(motionEvent0.getAction()) {
                case 0: {
                    boolean z = this.isPointInsideVerticalThumb(motionEvent0.getX(), motionEvent0.getY());
                    boolean z1 = this.isPointInsideHorizontalThumb(motionEvent0.getX(), motionEvent0.getY());
                    if(z || z1) {
                        if(z1) {
                            this.mDragState = 1;
                            this.mHorizontalDragX = (float)(((int)motionEvent0.getX()));
                        }
                        else if(z) {
                            this.mDragState = 2;
                            this.mVerticalDragY = (float)(((int)motionEvent0.getY()));
                        }
                        this.setState(2);
                        return;
                    }
                    break;
                }
                case 1: {
                    if(this.mState == 2) {
                        this.mVerticalDragY = 0.0f;
                        this.mHorizontalDragX = 0.0f;
                        this.setState(1);
                        this.mDragState = 0;
                        return;
                    }
                label_21:
                    if(motionEvent0.getAction() == 2 && this.mState == 2) {
                        this.show();
                        if(this.mDragState == 1) {
                            this.horizontalScrollTo(motionEvent0.getX());
                        }
                        if(this.mDragState == 2) {
                            this.verticalScrollTo(motionEvent0.getY());
                        }
                    }
                    break;
                }
                default: {
                    goto label_21;
                }
            }
        }
    }

    void requestRedraw() {
        this.mRecyclerView.invalidate();
    }

    private void resetHideDelay(int v) {
        this.cancelHide();
        this.mRecyclerView.postDelayed(this.mHideRunnable, ((long)v));
    }

    private int scrollTo(float f, float f1, int[] arr_v, int v, int v1, int v2) {
        int v3 = arr_v[1] - arr_v[0];
        if(v3 == 0) {
            return 0;
        }
        int v4 = v - v2;
        int v5 = (int)((f1 - f) / ((float)v3) * ((float)v4));
        return v1 + v5 >= v4 || v1 + v5 < 0 ? 0 : v5;
    }

    void setState(int v) {
        if(v == 2 && this.mState != 2) {
            this.mVerticalThumbDrawable.setState(FastScroller.PRESSED_STATE_SET);
            this.cancelHide();
        }
        if(v == 0) {
            this.requestRedraw();
        }
        else {
            this.show();
        }
        if(this.mState == 2 && v != 2) {
            this.mVerticalThumbDrawable.setState(FastScroller.EMPTY_STATE_SET);
            this.resetHideDelay(1200);
        }
        else if(v == 1) {
            this.resetHideDelay(1500);
        }
        this.mState = v;
    }

    private void setupCallbacks() {
        this.mRecyclerView.addItemDecoration(this);
        this.mRecyclerView.addOnItemTouchListener(this);
        this.mRecyclerView.addOnScrollListener(this.mOnScrollListener);
    }

    public void show() {
        int v = this.mAnimationState;
        if(v != 0) {
            if(v != 3) {
                return;
            }
            this.mShowHideAnimator.cancel();
        }
        this.mAnimationState = 1;
        float[] arr_f = {((float)(((Float)this.mShowHideAnimator.getAnimatedValue()))), 1.0f};
        this.mShowHideAnimator.setFloatValues(arr_f);
        this.mShowHideAnimator.setDuration(500L);
        this.mShowHideAnimator.setStartDelay(0L);
        this.mShowHideAnimator.start();
    }

    void updateScrollPosition(int v, int v1) {
        int v2 = this.mRecyclerView.computeVerticalScrollRange();
        int v3 = this.mRecyclerViewHeight;
        this.mNeedVerticalScrollbar = v2 - v3 > 0 && v3 >= this.mScrollbarMinimumRange;
        int v4 = this.mRecyclerView.computeHorizontalScrollRange();
        int v5 = this.mRecyclerViewWidth;
        boolean z = v4 - v5 > 0 && v5 >= this.mScrollbarMinimumRange;
        this.mNeedHorizontalScrollbar = z;
        boolean z1 = this.mNeedVerticalScrollbar;
        if(!z1 && !z) {
            if(this.mState == 0) {
                return;
            }
            this.setState(0);
            return;
        }
        if(z1) {
            this.mVerticalThumbCenterY = (int)(((float)v3) * (((float)v1) + ((float)v3) / 2.0f) / ((float)v2));
            this.mVerticalThumbHeight = Math.min(v3, v3 * v3 / v2);
        }
        if(this.mNeedHorizontalScrollbar) {
            this.mHorizontalThumbCenterX = (int)(((float)v5) * (((float)v) + ((float)v5) / 2.0f) / ((float)v4));
            this.mHorizontalThumbWidth = Math.min(v5, v5 * v5 / v4);
        }
        if(this.mState != 0 && this.mState != 1) {
            return;
        }
        this.setState(1);
    }

    private void verticalScrollTo(float f) {
        int[] arr_v = this.getVerticalRange();
        float f1 = Math.max(arr_v[0], Math.min(arr_v[1], f));
        if(Math.abs(((float)this.mVerticalThumbCenterY) - f1) < 2.0f) {
            return;
        }
        int v = this.scrollTo(this.mVerticalDragY, f1, arr_v, this.mRecyclerView.computeVerticalScrollRange(), this.mRecyclerView.computeVerticalScrollOffset(), this.mRecyclerViewHeight);
        if(v != 0) {
            this.mRecyclerView.scrollBy(0, v);
        }
        this.mVerticalDragY = f1;
    }

    class androidx.recyclerview.widget.FastScroller.1 implements Runnable {
        @Override
        public void run() {
            FastScroller.this.hide(500);
        }
    }

}

