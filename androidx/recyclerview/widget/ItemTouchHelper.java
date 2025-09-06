package androidx.recyclerview.widget;

import android.animation.Animator.AnimatorListener;
import android.animation.Animator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.R.dimen;
import java.util.ArrayList;
import java.util.List;

public class ItemTouchHelper extends ItemDecoration implements OnChildAttachStateChangeListener {
    public static abstract class Callback {
        private static final int ABS_HORIZONTAL_DIR_FLAGS = 0xC0C0C;
        public static final int DEFAULT_DRAG_ANIMATION_DURATION = 200;
        public static final int DEFAULT_SWIPE_ANIMATION_DURATION = 0xFA;
        private static final long DRAG_SCROLL_ACCELERATION_LIMIT_TIME_MS = 2000L;
        static final int RELATIVE_DIR_FLAGS = 0x303030;
        private int mCachedMaxScrollSpeed;
        private static final Interpolator sDragScrollInterpolator;
        private static final Interpolator sDragViewScrollCapInterpolator;

        static {
            Callback.sDragScrollInterpolator = new Interpolator() {
                @Override  // android.animation.TimeInterpolator
                public float getInterpolation(float f) {
                    return f * f * f * f * f;
                }
            };
            Callback.sDragViewScrollCapInterpolator = new Interpolator() {
                @Override  // android.animation.TimeInterpolator
                public float getInterpolation(float f) {
                    return (f - 1.0f) * (f - 1.0f) * (f - 1.0f) * (f - 1.0f) * (f - 1.0f) + 1.0f;
                }
            };
        }

        public Callback() {
            this.mCachedMaxScrollSpeed = -1;
        }

        public boolean canDropOver(RecyclerView recyclerView0, ViewHolder recyclerView$ViewHolder0, ViewHolder recyclerView$ViewHolder1) [...] // Inlined contents

        public ViewHolder chooseDropTarget(ViewHolder recyclerView$ViewHolder0, List list0, int v, int v1) {
            int v2 = recyclerView$ViewHolder0.itemView.getWidth();
            int v3 = recyclerView$ViewHolder0.itemView.getHeight();
            int v4 = v - recyclerView$ViewHolder0.itemView.getLeft();
            int v5 = v1 - recyclerView$ViewHolder0.itemView.getTop();
            int v6 = list0.size();
            ViewHolder recyclerView$ViewHolder1 = null;
            int v7 = -1;
            for(int v8 = 0; v8 < v6; ++v8) {
                ViewHolder recyclerView$ViewHolder2 = (ViewHolder)list0.get(v8);
                if(v4 > 0) {
                    int v9 = recyclerView$ViewHolder2.itemView.getRight() - (v2 + v);
                    if(v9 < 0 && recyclerView$ViewHolder2.itemView.getRight() > recyclerView$ViewHolder0.itemView.getRight()) {
                        int v10 = Math.abs(v9);
                        if(v10 > v7) {
                            recyclerView$ViewHolder1 = recyclerView$ViewHolder2;
                            v7 = v10;
                        }
                    }
                }
                if(v4 < 0) {
                    int v11 = recyclerView$ViewHolder2.itemView.getLeft() - v;
                    if(v11 > 0 && recyclerView$ViewHolder2.itemView.getLeft() < recyclerView$ViewHolder0.itemView.getLeft()) {
                        int v12 = Math.abs(v11);
                        if(v12 > v7) {
                            recyclerView$ViewHolder1 = recyclerView$ViewHolder2;
                            v7 = v12;
                        }
                    }
                }
                if(v5 < 0) {
                    int v13 = recyclerView$ViewHolder2.itemView.getTop() - v1;
                    if(v13 > 0 && recyclerView$ViewHolder2.itemView.getTop() < recyclerView$ViewHolder0.itemView.getTop()) {
                        int v14 = Math.abs(v13);
                        if(v14 > v7) {
                            recyclerView$ViewHolder1 = recyclerView$ViewHolder2;
                            v7 = v14;
                        }
                    }
                }
                if(v5 > 0) {
                    int v15 = recyclerView$ViewHolder2.itemView.getBottom() - (v1 + v3);
                    if(v15 < 0 && recyclerView$ViewHolder2.itemView.getBottom() > recyclerView$ViewHolder0.itemView.getBottom()) {
                        int v16 = Math.abs(v15);
                        if(v16 > v7) {
                            recyclerView$ViewHolder1 = recyclerView$ViewHolder2;
                            v7 = v16;
                        }
                    }
                }
            }
            return recyclerView$ViewHolder1;
        }

        public void clearView(RecyclerView recyclerView0, ViewHolder recyclerView$ViewHolder0) {
            ItemTouchUIUtilImpl.INSTANCE.clearView(recyclerView$ViewHolder0.itemView);
        }

        public int convertToAbsoluteDirection(int v, int v1) {
            if((v & 0x303030) == 0) {
                return v;
            }
            if(v1 == 0) {
                return 0xFFCFCFCF & v | (v & 0x303030) >> 2;
            }
            int v2 = (v & 0x303030) >> 1;
            return 0xFFCFCFCF & v | 0xFFCFCFCF & v2 | (v2 & 0x303030) >> 2;
        }

        public static int convertToRelativeDirection(int v, int v1) {
            if((v & 0xC0C0C) == 0) {
                return v;
            }
            if(v1 == 0) {
                return 0xFFF3F3F3 & v | (v & 0xC0C0C) << 2;
            }
            int v2 = (v & 0xC0C0C) << 1;
            return 0xFFF3F3F3 & v | 0xFFF3F3F3 & v2 | (v2 & 0xC0C0C) << 2;
        }

        final int getAbsoluteMovementFlags(RecyclerView recyclerView0, ViewHolder recyclerView$ViewHolder0) {
            return this.convertToAbsoluteDirection(this.getMovementFlags(recyclerView0, recyclerView$ViewHolder0), recyclerView0.getLayoutDirection());
        }

        public long getAnimationDuration(RecyclerView recyclerView0, int v, float f, float f1) {
            ItemAnimator recyclerView$ItemAnimator0 = recyclerView0.getItemAnimator();
            if(recyclerView$ItemAnimator0 == null) {
                return v == 8 ? 200L : 0xFAL;
            }
            return v == 8 ? recyclerView$ItemAnimator0.getMoveDuration() : recyclerView$ItemAnimator0.getRemoveDuration();
        }

        public int getBoundingBoxMargin() [...] // Inlined contents

        public static ItemTouchUIUtil getDefaultUIUtil() {
            return ItemTouchUIUtilImpl.INSTANCE;
        }

        private int getMaxDragScroll(RecyclerView recyclerView0) {
            if(this.mCachedMaxScrollSpeed == -1) {
                this.mCachedMaxScrollSpeed = recyclerView0.getResources().getDimensionPixelSize(dimen.item_touch_helper_max_drag_scroll_per_frame);
            }
            return this.mCachedMaxScrollSpeed;
        }

        public float getMoveThreshold(ViewHolder recyclerView$ViewHolder0) [...] // Inlined contents

        public abstract int getMovementFlags(RecyclerView arg1, ViewHolder arg2);

        public float getSwipeEscapeVelocity(float f) [...] // Inlined contents

        public float getSwipeThreshold(ViewHolder recyclerView$ViewHolder0) [...] // Inlined contents

        public float getSwipeVelocityThreshold(float f) [...] // Inlined contents

        boolean hasDragFlag(RecyclerView recyclerView0, ViewHolder recyclerView$ViewHolder0) {
            return (this.getAbsoluteMovementFlags(recyclerView0, recyclerView$ViewHolder0) & 0xFF0000) != 0;
        }

        boolean hasSwipeFlag(RecyclerView recyclerView0, ViewHolder recyclerView$ViewHolder0) {
            return (this.getAbsoluteMovementFlags(recyclerView0, recyclerView$ViewHolder0) & 0xFF00) != 0;
        }

        public int interpolateOutOfBoundsScroll(RecyclerView recyclerView0, int v, int v1, int v2, long v3) {
            float f = 1.0f;
            int v4 = (int)(((float)(((int)Math.signum(v1)) * this.getMaxDragScroll(recyclerView0))) * Callback.sDragViewScrollCapInterpolator.getInterpolation(Math.min(1.0f, ((float)Math.abs(v1)) * 1.0f / ((float)v))));
            if(v3 <= 2000L) {
                f = ((float)v3) / 2000.0f;
            }
            int v5 = (int)(((float)v4) * Callback.sDragScrollInterpolator.getInterpolation(f));
            if(v5 == 0) {
                return v1 <= 0 ? -1 : 1;
            }
            return v5;
        }

        public boolean isItemViewSwipeEnabled() [...] // Inlined contents

        public boolean isLongPressDragEnabled() [...] // Inlined contents

        public static int makeFlag(int v, int v1) [...] // Inlined contents

        public static int makeMovementFlags(int v, int v1) {
            return v << 16 | (v1 << 8 | (v1 | v));
        }

        public void onChildDraw(Canvas canvas0, RecyclerView recyclerView0, ViewHolder recyclerView$ViewHolder0, float f, float f1, int v, boolean z) {
            ItemTouchUIUtilImpl.INSTANCE.onDraw(canvas0, recyclerView0, recyclerView$ViewHolder0.itemView, f, f1, v, z);
        }

        public void onChildDrawOver(Canvas canvas0, RecyclerView recyclerView0, ViewHolder recyclerView$ViewHolder0, float f, float f1, int v, boolean z) {
            ItemTouchUIUtilImpl.INSTANCE.onDrawOver(canvas0, recyclerView0, recyclerView$ViewHolder0.itemView, f, f1, v, z);
        }

        void onDraw(Canvas canvas0, RecyclerView recyclerView0, ViewHolder recyclerView$ViewHolder0, List list0, int v, float f, float f1) {
            int v1 = list0.size();
            for(int v2 = 0; v2 < v1; ++v2) {
                RecoverAnimation itemTouchHelper$RecoverAnimation0 = (RecoverAnimation)list0.get(v2);
                itemTouchHelper$RecoverAnimation0.update();
                int v3 = canvas0.save();
                this.onChildDraw(canvas0, recyclerView0, itemTouchHelper$RecoverAnimation0.mViewHolder, itemTouchHelper$RecoverAnimation0.mX, itemTouchHelper$RecoverAnimation0.mY, itemTouchHelper$RecoverAnimation0.mActionState, false);
                canvas0.restoreToCount(v3);
            }
            if(recyclerView$ViewHolder0 != null) {
                int v4 = canvas0.save();
                this.onChildDraw(canvas0, recyclerView0, recyclerView$ViewHolder0, f, f1, v, true);
                canvas0.restoreToCount(v4);
            }
        }

        void onDrawOver(Canvas canvas0, RecyclerView recyclerView0, ViewHolder recyclerView$ViewHolder0, List list0, int v, float f, float f1) {
            int v1 = list0.size();
            boolean z = false;
            for(int v2 = 0; v2 < v1; ++v2) {
                RecoverAnimation itemTouchHelper$RecoverAnimation0 = (RecoverAnimation)list0.get(v2);
                int v3 = canvas0.save();
                this.onChildDrawOver(canvas0, recyclerView0, itemTouchHelper$RecoverAnimation0.mViewHolder, itemTouchHelper$RecoverAnimation0.mX, itemTouchHelper$RecoverAnimation0.mY, itemTouchHelper$RecoverAnimation0.mActionState, false);
                canvas0.restoreToCount(v3);
            }
            if(recyclerView$ViewHolder0 != null) {
                int v4 = canvas0.save();
                this.onChildDrawOver(canvas0, recyclerView0, recyclerView$ViewHolder0, f, f1, v, true);
                canvas0.restoreToCount(v4);
            }
            for(int v5 = v1 - 1; v5 >= 0; --v5) {
                RecoverAnimation itemTouchHelper$RecoverAnimation1 = (RecoverAnimation)list0.get(v5);
                if(itemTouchHelper$RecoverAnimation1.mEnded && !itemTouchHelper$RecoverAnimation1.mIsPendingCleanup) {
                    list0.remove(v5);
                }
                else if(!itemTouchHelper$RecoverAnimation1.mEnded) {
                    z = true;
                }
            }
            if(z) {
                recyclerView0.invalidate();
            }
        }

        public abstract boolean onMove(RecyclerView arg1, ViewHolder arg2, ViewHolder arg3);

        public void onMoved(RecyclerView recyclerView0, ViewHolder recyclerView$ViewHolder0, int v, ViewHolder recyclerView$ViewHolder1, int v1, int v2, int v3) {
            LayoutManager recyclerView$LayoutManager0 = recyclerView0.getLayoutManager();
            if(recyclerView$LayoutManager0 instanceof ViewDropHandler) {
                ((ViewDropHandler)recyclerView$LayoutManager0).prepareForDrop(recyclerView$ViewHolder0.itemView, recyclerView$ViewHolder1.itemView, v2, v3);
                return;
            }
            if(recyclerView$LayoutManager0.canScrollHorizontally()) {
                if(recyclerView$LayoutManager0.getDecoratedLeft(recyclerView$ViewHolder1.itemView) <= recyclerView0.getPaddingLeft()) {
                    recyclerView0.scrollToPosition(v1);
                }
                if(recyclerView$LayoutManager0.getDecoratedRight(recyclerView$ViewHolder1.itemView) >= recyclerView0.getWidth() - recyclerView0.getPaddingRight()) {
                    recyclerView0.scrollToPosition(v1);
                }
            }
            if(recyclerView$LayoutManager0.canScrollVertically()) {
                if(recyclerView$LayoutManager0.getDecoratedTop(recyclerView$ViewHolder1.itemView) <= recyclerView0.getPaddingTop()) {
                    recyclerView0.scrollToPosition(v1);
                }
                if(recyclerView$LayoutManager0.getDecoratedBottom(recyclerView$ViewHolder1.itemView) >= recyclerView0.getHeight() - recyclerView0.getPaddingBottom()) {
                    recyclerView0.scrollToPosition(v1);
                }
            }
        }

        public void onSelectedChanged(ViewHolder recyclerView$ViewHolder0, int v) {
            if(recyclerView$ViewHolder0 != null) {
                ItemTouchUIUtilImpl.INSTANCE.onSelected(recyclerView$ViewHolder0.itemView);
            }
        }

        public abstract void onSwiped(ViewHolder arg1, int arg2);
    }

    class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {
        private boolean mShouldReactToLongPress;

        ItemTouchHelperGestureListener() {
            this.mShouldReactToLongPress = true;
        }

        void doNotReactToLongPress() {
            this.mShouldReactToLongPress = false;
        }

        @Override  // android.view.GestureDetector$SimpleOnGestureListener
        public boolean onDown(MotionEvent motionEvent0) {
            return true;
        }

        @Override  // android.view.GestureDetector$SimpleOnGestureListener
        public void onLongPress(MotionEvent motionEvent0) {
            if(this.mShouldReactToLongPress) {
                View view0 = ItemTouchHelper.this.findChildView(motionEvent0);
                if(view0 != null) {
                    ViewHolder recyclerView$ViewHolder0 = ItemTouchHelper.this.mRecyclerView.getChildViewHolder(view0);
                    if(recyclerView$ViewHolder0 != null && ItemTouchHelper.this.mCallback.hasDragFlag(ItemTouchHelper.this.mRecyclerView, recyclerView$ViewHolder0) && motionEvent0.getPointerId(0) == ItemTouchHelper.this.mActivePointerId) {
                        int v = motionEvent0.findPointerIndex(ItemTouchHelper.this.mActivePointerId);
                        float f = motionEvent0.getX(v);
                        float f1 = motionEvent0.getY(v);
                        ItemTouchHelper.this.mInitialTouchX = f;
                        ItemTouchHelper.this.mInitialTouchY = f1;
                        ItemTouchHelper.this.mDy = 0.0f;
                        ItemTouchHelper.this.mDx = 0.0f;
                        ItemTouchHelper.this.select(recyclerView$ViewHolder0, 2);
                    }
                }
            }
        }
    }

    static class RecoverAnimation implements Animator.AnimatorListener {
        final int mActionState;
        final int mAnimationType;
        boolean mEnded;
        private float mFraction;
        boolean mIsPendingCleanup;
        boolean mOverridden;
        final float mStartDx;
        final float mStartDy;
        final float mTargetX;
        final float mTargetY;
        final ValueAnimator mValueAnimator;
        final ViewHolder mViewHolder;
        float mX;
        float mY;

        RecoverAnimation(ViewHolder recyclerView$ViewHolder0, int v, int v1, float f, float f1, float f2, float f3) {
            this.mOverridden = false;
            this.mEnded = false;
            this.mActionState = v1;
            this.mAnimationType = v;
            this.mViewHolder = recyclerView$ViewHolder0;
            this.mStartDx = f;
            this.mStartDy = f1;
            this.mTargetX = f2;
            this.mTargetY = f3;
            ValueAnimator valueAnimator0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.mValueAnimator = valueAnimator0;
            valueAnimator0.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator0) {
                    float f = valueAnimator0.getAnimatedFraction();
                    RecoverAnimation.this.setFraction(f);
                }
            });
            valueAnimator0.setTarget(recyclerView$ViewHolder0.itemView);
            valueAnimator0.addListener(this);
            this.setFraction(0.0f);
        }

        public void cancel() {
            this.mValueAnimator.cancel();
        }

        @Override  // android.animation.Animator$AnimatorListener
        public void onAnimationCancel(Animator animator0) {
            this.setFraction(1.0f);
        }

        @Override  // android.animation.Animator$AnimatorListener
        public void onAnimationEnd(Animator animator0) {
            if(!this.mEnded) {
                this.mViewHolder.setIsRecyclable(true);
            }
            this.mEnded = true;
        }

        @Override  // android.animation.Animator$AnimatorListener
        public void onAnimationRepeat(Animator animator0) {
        }

        @Override  // android.animation.Animator$AnimatorListener
        public void onAnimationStart(Animator animator0) {
        }

        public void setDuration(long v) {
            this.mValueAnimator.setDuration(v);
        }

        public void setFraction(float f) {
            this.mFraction = f;
        }

        public void start() {
            this.mViewHolder.setIsRecyclable(false);
            this.mValueAnimator.start();
        }

        public void update() {
            float f = this.mStartDx;
            float f1 = this.mTargetX;
            this.mX = f == f1 ? this.mViewHolder.itemView.getTranslationX() : f + this.mFraction * (f1 - f);
            float f2 = this.mStartDy;
            float f3 = this.mTargetY;
            if(f2 == f3) {
                this.mY = this.mViewHolder.itemView.getTranslationY();
                return;
            }
            this.mY = f2 + this.mFraction * (f3 - f2);
        }
    }

    public static abstract class SimpleCallback extends Callback {
        private int mDefaultDragDirs;
        private int mDefaultSwipeDirs;

        public SimpleCallback(int v, int v1) {
            this.mDefaultSwipeDirs = v1;
            this.mDefaultDragDirs = v;
        }

        public int getDragDirs(RecyclerView recyclerView0, ViewHolder recyclerView$ViewHolder0) {
            return this.mDefaultDragDirs;
        }

        @Override  // androidx.recyclerview.widget.ItemTouchHelper$Callback
        public int getMovementFlags(RecyclerView recyclerView0, ViewHolder recyclerView$ViewHolder0) {
            return SimpleCallback.makeMovementFlags(this.getDragDirs(recyclerView0, recyclerView$ViewHolder0), this.getSwipeDirs(recyclerView0, recyclerView$ViewHolder0));
        }

        public int getSwipeDirs(RecyclerView recyclerView0, ViewHolder recyclerView$ViewHolder0) {
            return this.mDefaultSwipeDirs;
        }

        public void setDefaultDragDirs(int v) {
            this.mDefaultDragDirs = v;
        }

        public void setDefaultSwipeDirs(int v) {
            this.mDefaultSwipeDirs = v;
        }
    }

    public interface ViewDropHandler {
        void prepareForDrop(View arg1, View arg2, int arg3, int arg4);
    }

    static final int ACTION_MODE_DRAG_MASK = 0xFF0000;
    private static final int ACTION_MODE_IDLE_MASK = 0xFF;
    static final int ACTION_MODE_SWIPE_MASK = 0xFF00;
    public static final int ACTION_STATE_DRAG = 2;
    public static final int ACTION_STATE_IDLE = 0;
    public static final int ACTION_STATE_SWIPE = 1;
    private static final int ACTIVE_POINTER_ID_NONE = -1;
    public static final int ANIMATION_TYPE_DRAG = 8;
    public static final int ANIMATION_TYPE_SWIPE_CANCEL = 4;
    public static final int ANIMATION_TYPE_SWIPE_SUCCESS = 2;
    private static final boolean DEBUG = false;
    static final int DIRECTION_FLAG_COUNT = 8;
    public static final int DOWN = 2;
    public static final int END = 0x20;
    public static final int LEFT = 4;
    private static final int PIXELS_PER_SECOND = 1000;
    public static final int RIGHT = 8;
    public static final int START = 16;
    private static final String TAG = "ItemTouchHelper";
    public static final int UP = 1;
    private int mActionState;
    int mActivePointerId;
    Callback mCallback;
    private ChildDrawingOrderCallback mChildDrawingOrderCallback;
    private List mDistances;
    private long mDragScrollStartTimeInMs;
    float mDx;
    float mDy;
    GestureDetector mGestureDetector;
    float mInitialTouchX;
    float mInitialTouchY;
    private ItemTouchHelperGestureListener mItemTouchHelperGestureListener;
    private float mMaxSwipeVelocity;
    private final OnItemTouchListener mOnItemTouchListener;
    View mOverdrawChild;
    int mOverdrawChildPosition;
    final List mPendingCleanup;
    List mRecoverAnimations;
    RecyclerView mRecyclerView;
    final Runnable mScrollRunnable;
    ViewHolder mSelected;
    int mSelectedFlags;
    private float mSelectedStartX;
    private float mSelectedStartY;
    private int mSlop;
    private List mSwapTargets;
    private float mSwipeEscapeVelocity;
    private final float[] mTmpPosition;
    private Rect mTmpRect;
    VelocityTracker mVelocityTracker;

    public ItemTouchHelper(Callback itemTouchHelper$Callback0) {
        this.mPendingCleanup = new ArrayList();
        this.mTmpPosition = new float[2];
        this.mSelected = null;
        this.mActivePointerId = -1;
        this.mActionState = 0;
        this.mRecoverAnimations = new ArrayList();
        this.mScrollRunnable = new Runnable() {
            @Override
            public void run() {
                if(ItemTouchHelper.this.mSelected != null && ItemTouchHelper.this.scrollIfNecessary()) {
                    if(ItemTouchHelper.this.mSelected != null) {
                        ItemTouchHelper.this.moveIfNecessary(ItemTouchHelper.this.mSelected);
                    }
                    ItemTouchHelper.this.mRecyclerView.removeCallbacks(ItemTouchHelper.this.mScrollRunnable);
                    ViewCompat.postOnAnimation(ItemTouchHelper.this.mRecyclerView, this);
                }
            }
        };
        this.mChildDrawingOrderCallback = null;
        this.mOverdrawChild = null;
        this.mOverdrawChildPosition = -1;
        this.mOnItemTouchListener = new OnItemTouchListener() {
            @Override  // androidx.recyclerview.widget.RecyclerView$OnItemTouchListener
            public boolean onInterceptTouchEvent(RecyclerView recyclerView0, MotionEvent motionEvent0) {
                ItemTouchHelper.this.mGestureDetector.onTouchEvent(motionEvent0);
                int v = motionEvent0.getActionMasked();
                if(v == 0) {
                    ItemTouchHelper.this.mActivePointerId = motionEvent0.getPointerId(0);
                    ItemTouchHelper.this.mInitialTouchX = motionEvent0.getX();
                    ItemTouchHelper.this.mInitialTouchY = motionEvent0.getY();
                    ItemTouchHelper.this.obtainVelocityTracker();
                    if(ItemTouchHelper.this.mSelected == null) {
                        RecoverAnimation itemTouchHelper$RecoverAnimation0 = ItemTouchHelper.this.findAnimation(motionEvent0);
                        if(itemTouchHelper$RecoverAnimation0 != null) {
                            ItemTouchHelper.this.mInitialTouchX -= itemTouchHelper$RecoverAnimation0.mX;
                            ItemTouchHelper.this.mInitialTouchY -= itemTouchHelper$RecoverAnimation0.mY;
                            ItemTouchHelper.this.endRecoverAnimation(itemTouchHelper$RecoverAnimation0.mViewHolder, true);
                            if(ItemTouchHelper.this.mPendingCleanup.remove(itemTouchHelper$RecoverAnimation0.mViewHolder.itemView)) {
                                ItemTouchHelper.this.mCallback.clearView(ItemTouchHelper.this.mRecyclerView, itemTouchHelper$RecoverAnimation0.mViewHolder);
                            }
                            ItemTouchHelper.this.select(itemTouchHelper$RecoverAnimation0.mViewHolder, itemTouchHelper$RecoverAnimation0.mActionState);
                            ItemTouchHelper.this.updateDxDy(motionEvent0, ItemTouchHelper.this.mSelectedFlags, 0);
                        }
                    }
                }
                else if(v == 1 || v == 3) {
                    ItemTouchHelper.this.mActivePointerId = -1;
                    ItemTouchHelper.this.select(null, 0);
                }
                else if(ItemTouchHelper.this.mActivePointerId != -1) {
                    int v1 = motionEvent0.findPointerIndex(ItemTouchHelper.this.mActivePointerId);
                    if(v1 >= 0) {
                        ItemTouchHelper.this.checkSelectForSwipe(v, motionEvent0, v1);
                    }
                }
                if(ItemTouchHelper.this.mVelocityTracker != null) {
                    ItemTouchHelper.this.mVelocityTracker.addMovement(motionEvent0);
                }
                return ItemTouchHelper.this.mSelected != null;
            }

            @Override  // androidx.recyclerview.widget.RecyclerView$OnItemTouchListener
            public void onRequestDisallowInterceptTouchEvent(boolean z) {
                if(!z) {
                    return;
                }
                ItemTouchHelper.this.select(null, 0);
            }

            @Override  // androidx.recyclerview.widget.RecyclerView$OnItemTouchListener
            public void onTouchEvent(RecyclerView recyclerView0, MotionEvent motionEvent0) {
                ItemTouchHelper.this.mGestureDetector.onTouchEvent(motionEvent0);
                if(ItemTouchHelper.this.mVelocityTracker != null) {
                    ItemTouchHelper.this.mVelocityTracker.addMovement(motionEvent0);
                }
                if(ItemTouchHelper.this.mActivePointerId != -1) {
                    int v = motionEvent0.getActionMasked();
                    int v1 = motionEvent0.findPointerIndex(ItemTouchHelper.this.mActivePointerId);
                    if(v1 >= 0) {
                        ItemTouchHelper.this.checkSelectForSwipe(v, motionEvent0, v1);
                    }
                    int v2 = 0;
                    ViewHolder recyclerView$ViewHolder0 = ItemTouchHelper.this.mSelected;
                    if(recyclerView$ViewHolder0 != null) {
                        switch(v) {
                            case 1: {
                                ItemTouchHelper.this.select(null, 0);
                                ItemTouchHelper.this.mActivePointerId = -1;
                                return;
                            }
                            case 2: {
                                if(v1 >= 0) {
                                    ItemTouchHelper.this.updateDxDy(motionEvent0, ItemTouchHelper.this.mSelectedFlags, v1);
                                    ItemTouchHelper.this.moveIfNecessary(recyclerView$ViewHolder0);
                                    ItemTouchHelper.this.mRecyclerView.removeCallbacks(ItemTouchHelper.this.mScrollRunnable);
                                    ItemTouchHelper.this.mScrollRunnable.run();
                                    ItemTouchHelper.this.mRecyclerView.invalidate();
                                    return;
                                }
                                break;
                            }
                            case 3: {
                                if(ItemTouchHelper.this.mVelocityTracker != null) {
                                    ItemTouchHelper.this.mVelocityTracker.clear();
                                }
                                ItemTouchHelper.this.select(null, 0);
                                ItemTouchHelper.this.mActivePointerId = -1;
                                return;
                            }
                            case 6: {
                                int v3 = motionEvent0.getActionIndex();
                                if(motionEvent0.getPointerId(v3) == ItemTouchHelper.this.mActivePointerId) {
                                    if(v3 == 0) {
                                        v2 = 1;
                                    }
                                    ItemTouchHelper.this.mActivePointerId = motionEvent0.getPointerId(v2);
                                    ItemTouchHelper.this.updateDxDy(motionEvent0, ItemTouchHelper.this.mSelectedFlags, v3);
                                    return;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        };
        this.mCallback = itemTouchHelper$Callback0;
    }

    private void addChildDrawingOrderCallback() {

        class androidx.recyclerview.widget.ItemTouchHelper.5 implements ChildDrawingOrderCallback {
            @Override  // androidx.recyclerview.widget.RecyclerView$ChildDrawingOrderCallback
            public int onGetChildDrawingOrder(int v, int v1) {
                if(ItemTouchHelper.this.mOverdrawChild != null) {
                    int v2 = ItemTouchHelper.this.mOverdrawChildPosition;
                    if(v2 == -1) {
                        v2 = ItemTouchHelper.this.mRecyclerView.indexOfChild(ItemTouchHelper.this.mOverdrawChild);
                        ItemTouchHelper.this.mOverdrawChildPosition = v2;
                    }
                    if(v1 == v - 1) {
                        return v2;
                    }
                    return v1 >= v2 ? v1 + 1 : v1;
                }
                return v1;
            }
        }

    }

    public void attachToRecyclerView(RecyclerView recyclerView0) {
        RecyclerView recyclerView1 = this.mRecyclerView;
        if(recyclerView1 != recyclerView0) {
            if(recyclerView1 != null) {
                this.destroyCallbacks();
            }
            this.mRecyclerView = recyclerView0;
            if(recyclerView0 != null) {
                Resources resources0 = recyclerView0.getResources();
                this.mSwipeEscapeVelocity = resources0.getDimension(dimen.item_touch_helper_swipe_escape_velocity);
                this.mMaxSwipeVelocity = resources0.getDimension(dimen.item_touch_helper_swipe_escape_max_velocity);
                this.setupCallbacks();
            }
        }
    }

    private int checkHorizontalSwipe(ViewHolder recyclerView$ViewHolder0, int v) {
        int v1 = 8;
        if((v & 12) != 0) {
            int v2 = this.mDx > 0.0f ? 8 : 4;
            VelocityTracker velocityTracker0 = this.mVelocityTracker;
            if(velocityTracker0 != null && this.mActivePointerId > -1) {
                velocityTracker0.computeCurrentVelocity(1000, this.mMaxSwipeVelocity);
                float f = this.mVelocityTracker.getXVelocity(this.mActivePointerId);
                float f1 = this.mVelocityTracker.getYVelocity(this.mActivePointerId);
                if(f <= 0.0f) {
                    v1 = 4;
                }
                float f2 = Math.abs(f);
                if((v1 & v) != 0 && v2 == v1 && f2 >= this.mSwipeEscapeVelocity && f2 > Math.abs(f1)) {
                    return v1;
                }
            }
            float f3 = (float)this.mRecyclerView.getWidth();
            return (v & v2) == 0 || Math.abs(this.mDx) <= f3 * 0.5f ? 0 : v2;
        }
        return 0;
    }

    void checkSelectForSwipe(int v, MotionEvent motionEvent0, int v1) {
        if(this.mSelected == null && v == 2 && this.mActionState != 2 && this.mRecyclerView.getScrollState() != 1) {
            ViewHolder recyclerView$ViewHolder0 = this.findSwipedView(motionEvent0);
            if(recyclerView$ViewHolder0 != null) {
                int v2 = (this.mCallback.getAbsoluteMovementFlags(this.mRecyclerView, recyclerView$ViewHolder0) & 0xFF00) >> 8;
                if(v2 != 0) {
                    float f = motionEvent0.getX(v1);
                    float f1 = motionEvent0.getY(v1);
                    float f2 = f - this.mInitialTouchX;
                    float f3 = f1 - this.mInitialTouchY;
                    float f4 = Math.abs(f2);
                    float f5 = Math.abs(f3);
                    if(f4 >= ((float)this.mSlop) || f5 >= ((float)this.mSlop)) {
                        if(f4 > f5) {
                            if((f2 >= 0.0f || (v2 & 4) != 0) && (f2 <= 0.0f || (v2 & 8) != 0)) {
                                this.mDy = 0.0f;
                                this.mDx = 0.0f;
                                this.mActivePointerId = motionEvent0.getPointerId(0);
                                this.select(recyclerView$ViewHolder0, 1);
                            }
                        }
                        else if((f3 >= 0.0f || (v2 & 1) != 0) && (f3 <= 0.0f || (v2 & 2) != 0)) {
                            this.mDy = 0.0f;
                            this.mDx = 0.0f;
                            this.mActivePointerId = motionEvent0.getPointerId(0);
                            this.select(recyclerView$ViewHolder0, 1);
                        }
                    }
                }
            }
        }
    }

    private int checkVerticalSwipe(ViewHolder recyclerView$ViewHolder0, int v) {
        int v1 = 2;
        if((v & 3) != 0) {
            int v2 = this.mDy > 0.0f ? 2 : 1;
            VelocityTracker velocityTracker0 = this.mVelocityTracker;
            if(velocityTracker0 != null && this.mActivePointerId > -1) {
                velocityTracker0.computeCurrentVelocity(1000, this.mMaxSwipeVelocity);
                float f = this.mVelocityTracker.getXVelocity(this.mActivePointerId);
                float f1 = this.mVelocityTracker.getYVelocity(this.mActivePointerId);
                if(f1 <= 0.0f) {
                    v1 = 1;
                }
                float f2 = Math.abs(f1);
                if((v1 & v) != 0 && v1 == v2 && f2 >= this.mSwipeEscapeVelocity && f2 > Math.abs(f)) {
                    return v1;
                }
            }
            float f3 = (float)this.mRecyclerView.getHeight();
            return (v & v2) == 0 || Math.abs(this.mDy) <= f3 * 0.5f ? 0 : v2;
        }
        return 0;
    }

    private void destroyCallbacks() {
        this.mRecyclerView.removeItemDecoration(this);
        this.mRecyclerView.removeOnItemTouchListener(this.mOnItemTouchListener);
        this.mRecyclerView.removeOnChildAttachStateChangeListener(this);
        for(int v = this.mRecoverAnimations.size() - 1; v >= 0; --v) {
            RecoverAnimation itemTouchHelper$RecoverAnimation0 = (RecoverAnimation)this.mRecoverAnimations.get(0);
            itemTouchHelper$RecoverAnimation0.cancel();
            this.mCallback.clearView(this.mRecyclerView, itemTouchHelper$RecoverAnimation0.mViewHolder);
        }
        this.mRecoverAnimations.clear();
        this.mOverdrawChild = null;
        this.mOverdrawChildPosition = -1;
        this.releaseVelocityTracker();
        this.stopGestureDetection();
    }

    void endRecoverAnimation(ViewHolder recyclerView$ViewHolder0, boolean z) {
        for(int v = this.mRecoverAnimations.size() - 1; v >= 0; --v) {
            RecoverAnimation itemTouchHelper$RecoverAnimation0 = (RecoverAnimation)this.mRecoverAnimations.get(v);
            if(itemTouchHelper$RecoverAnimation0.mViewHolder == recyclerView$ViewHolder0) {
                itemTouchHelper$RecoverAnimation0.mOverridden |= z;
                if(!itemTouchHelper$RecoverAnimation0.mEnded) {
                    itemTouchHelper$RecoverAnimation0.cancel();
                }
                this.mRecoverAnimations.remove(v);
                return;
            }
        }
    }

    RecoverAnimation findAnimation(MotionEvent motionEvent0) {
        if(this.mRecoverAnimations.isEmpty()) {
            return null;
        }
        View view0 = this.findChildView(motionEvent0);
        for(int v = this.mRecoverAnimations.size() - 1; v >= 0; --v) {
            RecoverAnimation itemTouchHelper$RecoverAnimation0 = (RecoverAnimation)this.mRecoverAnimations.get(v);
            if(itemTouchHelper$RecoverAnimation0.mViewHolder.itemView == view0) {
                return itemTouchHelper$RecoverAnimation0;
            }
        }
        return null;
    }

    View findChildView(MotionEvent motionEvent0) {
        float f = motionEvent0.getX();
        float f1 = motionEvent0.getY();
        ViewHolder recyclerView$ViewHolder0 = this.mSelected;
        if(recyclerView$ViewHolder0 != null) {
            View view0 = recyclerView$ViewHolder0.itemView;
            if(ItemTouchHelper.hitTest(view0, f, f1, this.mSelectedStartX + this.mDx, this.mSelectedStartY + this.mDy)) {
                return view0;
            }
        }
        for(int v = this.mRecoverAnimations.size() - 1; v >= 0; --v) {
            RecoverAnimation itemTouchHelper$RecoverAnimation0 = (RecoverAnimation)this.mRecoverAnimations.get(v);
            View view1 = itemTouchHelper$RecoverAnimation0.mViewHolder.itemView;
            if(ItemTouchHelper.hitTest(view1, f, f1, itemTouchHelper$RecoverAnimation0.mX, itemTouchHelper$RecoverAnimation0.mY)) {
                return view1;
            }
        }
        return this.mRecyclerView.findChildViewUnder(f, f1);
    }

    private List findSwapTargets(ViewHolder recyclerView$ViewHolder0) {
        List list0 = this.mSwapTargets;
        if(list0 == null) {
            this.mSwapTargets = new ArrayList();
            this.mDistances = new ArrayList();
        }
        else {
            list0.clear();
            this.mDistances.clear();
        }
        int v = Math.round(this.mSelectedStartX + this.mDx);
        int v1 = Math.round(this.mSelectedStartY + this.mDy);
        int v2 = recyclerView$ViewHolder0.itemView.getWidth() + v;
        int v3 = recyclerView$ViewHolder0.itemView.getHeight() + v1;
        LayoutManager recyclerView$LayoutManager0 = this.mRecyclerView.getLayoutManager();
        int v4 = recyclerView$LayoutManager0.getChildCount();
        for(int v5 = 0; v5 < v4; ++v5) {
            View view0 = recyclerView$LayoutManager0.getChildAt(v5);
            if(view0 != recyclerView$ViewHolder0.itemView && view0.getBottom() >= v1 && view0.getTop() <= v3 && view0.getRight() >= v && view0.getLeft() <= v2) {
                ViewHolder recyclerView$ViewHolder1 = this.mRecyclerView.getChildViewHolder(view0);
                int v6 = Math.abs((v + v2) / 2 - (view0.getLeft() + view0.getRight()) / 2);
                int v7 = Math.abs((v1 + v3) / 2 - (view0.getTop() + view0.getBottom()) / 2);
                int v8 = v6 * v6 + v7 * v7;
                int v9 = this.mSwapTargets.size();
                int v11 = 0;
                for(int v10 = 0; v10 < v9 && v8 > ((int)(((Integer)this.mDistances.get(v10)))); ++v10) {
                    ++v11;
                }
                this.mSwapTargets.add(v11, recyclerView$ViewHolder1);
                this.mDistances.add(v11, v8);
            }
        }
        return this.mSwapTargets;
    }

    private ViewHolder findSwipedView(MotionEvent motionEvent0) {
        LayoutManager recyclerView$LayoutManager0 = this.mRecyclerView.getLayoutManager();
        int v = this.mActivePointerId;
        if(v == -1) {
            return null;
        }
        int v1 = motionEvent0.findPointerIndex(v);
        float f = Math.abs(motionEvent0.getX(v1) - this.mInitialTouchX);
        float f1 = Math.abs(motionEvent0.getY(v1) - this.mInitialTouchY);
        if(f < ((float)this.mSlop) && f1 < ((float)this.mSlop)) {
            return null;
        }
        if(f > f1 && recyclerView$LayoutManager0.canScrollHorizontally()) {
            return null;
        }
        if(f1 > f && recyclerView$LayoutManager0.canScrollVertically()) {
            return null;
        }
        View view0 = this.findChildView(motionEvent0);
        return view0 == null ? null : this.mRecyclerView.getChildViewHolder(view0);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$ItemDecoration
    public void getItemOffsets(Rect rect0, View view0, RecyclerView recyclerView0, State recyclerView$State0) {
        rect0.setEmpty();
    }

    private void getSelectedDxDy(float[] arr_f) {
        arr_f[0] = (this.mSelectedFlags & 12) == 0 ? this.mSelected.itemView.getTranslationX() : this.mSelectedStartX + this.mDx - ((float)this.mSelected.itemView.getLeft());
        if((this.mSelectedFlags & 3) != 0) {
            arr_f[1] = this.mSelectedStartY + this.mDy - ((float)this.mSelected.itemView.getTop());
            return;
        }
        arr_f[1] = this.mSelected.itemView.getTranslationY();
    }

    boolean hasRunningRecoverAnim() {
        int v = this.mRecoverAnimations.size();
        for(int v1 = 0; v1 < v; ++v1) {
            if(!((RecoverAnimation)this.mRecoverAnimations.get(v1)).mEnded) {
                return true;
            }
        }
        return false;
    }

    private static boolean hitTest(View view0, float f, float f1, float f2, float f3) {
        return f >= f2 && f <= f2 + ((float)view0.getWidth()) && f1 >= f3 && f1 <= f3 + ((float)view0.getHeight());
    }

    void moveIfNecessary(ViewHolder recyclerView$ViewHolder0) {
        if(!this.mRecyclerView.isLayoutRequested() && this.mActionState == 2) {
            int v = (int)(this.mSelectedStartX + this.mDx);
            int v1 = (int)(this.mSelectedStartY + this.mDy);
            if(((float)Math.abs(v1 - recyclerView$ViewHolder0.itemView.getTop())) >= ((float)recyclerView$ViewHolder0.itemView.getHeight()) * 0.5f || ((float)Math.abs(v - recyclerView$ViewHolder0.itemView.getLeft())) >= ((float)recyclerView$ViewHolder0.itemView.getWidth()) * 0.5f) {
                List list0 = this.findSwapTargets(recyclerView$ViewHolder0);
                if(list0.size() != 0) {
                    ViewHolder recyclerView$ViewHolder1 = this.mCallback.chooseDropTarget(recyclerView$ViewHolder0, list0, v, v1);
                    if(recyclerView$ViewHolder1 == null) {
                        this.mSwapTargets.clear();
                        this.mDistances.clear();
                        return;
                    }
                    int v2 = recyclerView$ViewHolder1.getAbsoluteAdapterPosition();
                    int v3 = recyclerView$ViewHolder0.getAbsoluteAdapterPosition();
                    if(this.mCallback.onMove(this.mRecyclerView, recyclerView$ViewHolder0, recyclerView$ViewHolder1)) {
                        this.mCallback.onMoved(this.mRecyclerView, recyclerView$ViewHolder0, v3, recyclerView$ViewHolder1, v2, v, v1);
                    }
                }
            }
        }
    }

    void obtainVelocityTracker() {
        VelocityTracker velocityTracker0 = this.mVelocityTracker;
        if(velocityTracker0 != null) {
            velocityTracker0.recycle();
        }
        this.mVelocityTracker = VelocityTracker.obtain();
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$OnChildAttachStateChangeListener
    public void onChildViewAttachedToWindow(View view0) {
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$OnChildAttachStateChangeListener
    public void onChildViewDetachedFromWindow(View view0) {
        this.removeChildDrawingOrderCallbackIfNecessary(view0);
        ViewHolder recyclerView$ViewHolder0 = this.mRecyclerView.getChildViewHolder(view0);
        if(recyclerView$ViewHolder0 != null) {
            if(this.mSelected != null && recyclerView$ViewHolder0 == this.mSelected) {
                this.select(null, 0);
                return;
            }
            this.endRecoverAnimation(recyclerView$ViewHolder0, false);
            if(this.mPendingCleanup.remove(recyclerView$ViewHolder0.itemView)) {
                this.mCallback.clearView(this.mRecyclerView, recyclerView$ViewHolder0);
            }
        }
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$ItemDecoration
    public void onDraw(Canvas canvas0, RecyclerView recyclerView0, State recyclerView$State0) {
        float f2;
        float f1;
        this.mOverdrawChildPosition = -1;
        if(this.mSelected == null) {
            f2 = 0.0f;
            f1 = 0.0f;
        }
        else {
            this.getSelectedDxDy(this.mTmpPosition);
            float f = this.mTmpPosition[0];
            f1 = this.mTmpPosition[1];
            f2 = f;
        }
        this.mCallback.onDraw(canvas0, recyclerView0, this.mSelected, this.mRecoverAnimations, this.mActionState, f2, f1);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$ItemDecoration
    public void onDrawOver(Canvas canvas0, RecyclerView recyclerView0, State recyclerView$State0) {
        float f2;
        float f1;
        if(this.mSelected == null) {
            f2 = 0.0f;
            f1 = 0.0f;
        }
        else {
            this.getSelectedDxDy(this.mTmpPosition);
            float f = this.mTmpPosition[0];
            f1 = this.mTmpPosition[1];
            f2 = f;
        }
        this.mCallback.onDrawOver(canvas0, recyclerView0, this.mSelected, this.mRecoverAnimations, this.mActionState, f2, f1);
    }

    void postDispatchSwipe(RecoverAnimation itemTouchHelper$RecoverAnimation0, int v) {
        this.mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                if(ItemTouchHelper.this.mRecyclerView != null && ItemTouchHelper.this.mRecyclerView.isAttachedToWindow() && !itemTouchHelper$RecoverAnimation0.mOverridden && itemTouchHelper$RecoverAnimation0.mViewHolder.getAbsoluteAdapterPosition() != -1) {
                    ItemAnimator recyclerView$ItemAnimator0 = ItemTouchHelper.this.mRecyclerView.getItemAnimator();
                    if((recyclerView$ItemAnimator0 == null || !recyclerView$ItemAnimator0.isRunning(null)) && !ItemTouchHelper.this.hasRunningRecoverAnim()) {
                        ItemTouchHelper.this.mCallback.onSwiped(itemTouchHelper$RecoverAnimation0.mViewHolder, v);
                        return;
                    }
                    ItemTouchHelper.this.mRecyclerView.post(this);
                }
            }
        });
    }

    private void releaseVelocityTracker() {
        VelocityTracker velocityTracker0 = this.mVelocityTracker;
        if(velocityTracker0 != null) {
            velocityTracker0.recycle();
            this.mVelocityTracker = null;
        }
    }

    void removeChildDrawingOrderCallbackIfNecessary(View view0) {
        if(view0 == this.mOverdrawChild) {
            this.mOverdrawChild = null;
            if(this.mChildDrawingOrderCallback != null) {
                this.mRecyclerView.setChildDrawingOrderCallback(null);
            }
        }
    }

    boolean scrollIfNecessary() {
        int v6;
        int v4;
        if(this.mSelected == null) {
            this.mDragScrollStartTimeInMs = 0x8000000000000000L;
            return false;
        }
        long v = System.currentTimeMillis();
        long v1 = this.mDragScrollStartTimeInMs == 0x8000000000000000L ? 0L : v - this.mDragScrollStartTimeInMs;
        LayoutManager recyclerView$LayoutManager0 = this.mRecyclerView.getLayoutManager();
        if(this.mTmpRect == null) {
            this.mTmpRect = new Rect();
        }
        recyclerView$LayoutManager0.calculateItemDecorationsForChild(this.mSelected.itemView, this.mTmpRect);
        if(recyclerView$LayoutManager0.canScrollHorizontally()) {
            int v2 = (int)(this.mSelectedStartX + this.mDx);
            int v3 = v2 - this.mTmpRect.left - this.mRecyclerView.getPaddingLeft();
            float f = this.mDx;
            if(f < 0.0f && v3 < 0) {
                v4 = v3;
            }
            else if(f > 0.0f) {
                v3 = v2 + this.mSelected.itemView.getWidth() + this.mTmpRect.right - (this.mRecyclerView.getWidth() - this.mRecyclerView.getPaddingRight());
                v4 = v3 > 0 ? v3 : 0;
            }
            else {
                v4 = 0;
            }
        }
        else {
            v4 = 0;
        }
        if(recyclerView$LayoutManager0.canScrollVertically()) {
            int v5 = (int)(this.mSelectedStartY + this.mDy);
            v6 = v5 - this.mTmpRect.top - this.mRecyclerView.getPaddingTop();
            float f1 = this.mDy;
            if(f1 >= 0.0f || v6 >= 0) {
                if(f1 > 0.0f) {
                    v6 = v5 + this.mSelected.itemView.getHeight() + this.mTmpRect.bottom - (this.mRecyclerView.getHeight() - this.mRecyclerView.getPaddingBottom());
                    if(v6 <= 0) {
                        v6 = 0;
                    }
                }
                else {
                    v6 = 0;
                }
            }
        }
        else {
            v6 = 0;
        }
        if(v4 != 0) {
            v4 = this.mCallback.interpolateOutOfBoundsScroll(this.mRecyclerView, this.mSelected.itemView.getWidth(), v4, this.mRecyclerView.getWidth(), v1);
        }
        if(v6 != 0) {
            v6 = this.mCallback.interpolateOutOfBoundsScroll(this.mRecyclerView, this.mSelected.itemView.getHeight(), v6, this.mRecyclerView.getHeight(), v1);
        }
        if(v4 == 0 && v6 == 0) {
            this.mDragScrollStartTimeInMs = 0x8000000000000000L;
            return false;
        }
        if(this.mDragScrollStartTimeInMs == 0x8000000000000000L) {
            this.mDragScrollStartTimeInMs = v;
        }
        this.mRecyclerView.scrollBy(v4, v6);
        return true;
    }

    void select(ViewHolder recyclerView$ViewHolder0, int v) {
        boolean z1;
        boolean z;
        float f1;
        float f;
        if(recyclerView$ViewHolder0 == this.mSelected && v == this.mActionState) {
            return;
        }
        this.mDragScrollStartTimeInMs = 0x8000000000000000L;
        int v1 = this.mActionState;
        this.endRecoverAnimation(recyclerView$ViewHolder0, true);
        this.mActionState = v;
        if(v == 2) {
            if(recyclerView$ViewHolder0 == null) {
                throw new IllegalArgumentException("Must pass a ViewHolder when dragging");
            }
            this.mOverdrawChild = recyclerView$ViewHolder0.itemView;
        }
        ViewHolder recyclerView$ViewHolder1 = this.mSelected;
        if(recyclerView$ViewHolder1 == null) {
            z = false;
            z1 = false;
        }
        else {
            if(recyclerView$ViewHolder1.itemView.getParent() == null) {
                z = false;
                this.removeChildDrawingOrderCallbackIfNecessary(recyclerView$ViewHolder1.itemView);
                this.mCallback.clearView(this.mRecyclerView, recyclerView$ViewHolder1);
                z1 = false;
            }
            else {
                int v2 = v1 == 2 ? 0 : this.swipeIfNecessary(recyclerView$ViewHolder1);
                this.releaseVelocityTracker();
                int v3 = 4;
                switch(v2) {
                    case 1: 
                    case 2: {
                        f1 = Math.signum(this.mDy) * ((float)this.mRecyclerView.getHeight());
                        f = 0.0f;
                        break;
                    }
                    case 4: 
                    case 8: {
                        f = Math.signum(this.mDx) * ((float)this.mRecyclerView.getWidth());
                        f1 = 0.0f;
                        break;
                    }
                    default: {
                        f = v2 == 16 || v2 == 0x20 ? Math.signum(this.mDx) * ((float)this.mRecyclerView.getWidth()) : 0.0f;
                        f1 = 0.0f;
                    }
                }
                if(v1 == 2) {
                    v3 = 8;
                }
                else if(v2 > 0) {
                    v3 = 2;
                }
                this.getSelectedDxDy(this.mTmpPosition);
                float f2 = this.mTmpPosition[0];
                float f3 = this.mTmpPosition[1];
                z = false;
                androidx.recyclerview.widget.ItemTouchHelper.3 itemTouchHelper$30 = new RecoverAnimation(recyclerView$ViewHolder1, v3, v1, f2, f3, f, f1) {
                    @Override  // androidx.recyclerview.widget.ItemTouchHelper$RecoverAnimation
                    public void onAnimationEnd(Animator animator0) {
                        super.onAnimationEnd(animator0);
                        if(!this.mOverridden) {
                            if(v2 <= 0) {
                                ItemTouchHelper.this.mCallback.clearView(ItemTouchHelper.this.mRecyclerView, recyclerView$ViewHolder1);
                            }
                            else {
                                ItemTouchHelper.this.mPendingCleanup.add(recyclerView$ViewHolder1.itemView);
                                this.mIsPendingCleanup = true;
                                int v = v2;
                                if(v > 0) {
                                    ItemTouchHelper.this.postDispatchSwipe(this, v);
                                }
                            }
                            if(ItemTouchHelper.this.mOverdrawChild == recyclerView$ViewHolder1.itemView) {
                                ItemTouchHelper.this.removeChildDrawingOrderCallbackIfNecessary(recyclerView$ViewHolder1.itemView);
                            }
                        }
                    }
                };
                itemTouchHelper$30.setDuration(this.mCallback.getAnimationDuration(this.mRecyclerView, v3, f - f2, f1 - f3));
                this.mRecoverAnimations.add(itemTouchHelper$30);
                itemTouchHelper$30.start();
                z1 = true;
            }
            this.mSelected = null;
        }
        if(recyclerView$ViewHolder0 != null) {
            this.mSelectedFlags = (this.mCallback.getAbsoluteMovementFlags(this.mRecyclerView, recyclerView$ViewHolder0) & (1 << v * 8 + 8) - 1) >> this.mActionState * 8;
            this.mSelectedStartX = (float)recyclerView$ViewHolder0.itemView.getLeft();
            this.mSelectedStartY = (float)recyclerView$ViewHolder0.itemView.getTop();
            this.mSelected = recyclerView$ViewHolder0;
            if(v == 2) {
                recyclerView$ViewHolder0.itemView.performHapticFeedback(0);
            }
        }
        ViewParent viewParent0 = this.mRecyclerView.getParent();
        if(viewParent0 != null) {
            if(this.mSelected != null) {
                z = true;
            }
            viewParent0.requestDisallowInterceptTouchEvent(z);
        }
        if(!z1) {
            this.mRecyclerView.getLayoutManager().requestSimpleAnimationsInNextLayout();
        }
        this.mCallback.onSelectedChanged(this.mSelected, this.mActionState);
        this.mRecyclerView.invalidate();
    }

    private void setupCallbacks() {
        this.mSlop = ViewConfiguration.get(this.mRecyclerView.getContext()).getScaledTouchSlop();
        this.mRecyclerView.addItemDecoration(this);
        this.mRecyclerView.addOnItemTouchListener(this.mOnItemTouchListener);
        this.mRecyclerView.addOnChildAttachStateChangeListener(this);
        this.startGestureDetection();
    }

    public void startDrag(ViewHolder recyclerView$ViewHolder0) {
        if(!this.mCallback.hasDragFlag(this.mRecyclerView, recyclerView$ViewHolder0)) {
            Log.e("ItemTouchHelper", "Start drag has been called but dragging is not enabled");
            return;
        }
        if(recyclerView$ViewHolder0.itemView.getParent() != this.mRecyclerView) {
            Log.e("ItemTouchHelper", "Start drag has been called with a view holder which is not a child of the RecyclerView which is controlled by this ItemTouchHelper.");
            return;
        }
        this.obtainVelocityTracker();
        this.mDy = 0.0f;
        this.mDx = 0.0f;
        this.select(recyclerView$ViewHolder0, 2);
    }

    private void startGestureDetection() {
        this.mItemTouchHelperGestureListener = new ItemTouchHelperGestureListener(this);
        this.mGestureDetector = new GestureDetector(this.mRecyclerView.getContext(), this.mItemTouchHelperGestureListener);
    }

    public void startSwipe(ViewHolder recyclerView$ViewHolder0) {
        if(!this.mCallback.hasSwipeFlag(this.mRecyclerView, recyclerView$ViewHolder0)) {
            Log.e("ItemTouchHelper", "Start swipe has been called but swiping is not enabled");
            return;
        }
        if(recyclerView$ViewHolder0.itemView.getParent() != this.mRecyclerView) {
            Log.e("ItemTouchHelper", "Start swipe has been called with a view holder which is not a child of the RecyclerView controlled by this ItemTouchHelper.");
            return;
        }
        this.obtainVelocityTracker();
        this.mDy = 0.0f;
        this.mDx = 0.0f;
        this.select(recyclerView$ViewHolder0, 1);
    }

    private void stopGestureDetection() {
        ItemTouchHelperGestureListener itemTouchHelper$ItemTouchHelperGestureListener0 = this.mItemTouchHelperGestureListener;
        if(itemTouchHelper$ItemTouchHelperGestureListener0 != null) {
            itemTouchHelper$ItemTouchHelperGestureListener0.doNotReactToLongPress();
            this.mItemTouchHelperGestureListener = null;
        }
        if(this.mGestureDetector != null) {
            this.mGestureDetector = null;
        }
    }

    private int swipeIfNecessary(ViewHolder recyclerView$ViewHolder0) {
        if(this.mActionState == 2) {
            return 0;
        }
        int v = this.mCallback.getMovementFlags(this.mRecyclerView, recyclerView$ViewHolder0);
        int v1 = (this.mCallback.convertToAbsoluteDirection(v, this.mRecyclerView.getLayoutDirection()) & 0xFF00) >> 8;
        if(v1 == 0) {
            return 0;
        }
        int v2 = (v & 0xFF00) >> 8;
        if(Math.abs(this.mDx) > Math.abs(this.mDy)) {
            int v3 = this.checkHorizontalSwipe(recyclerView$ViewHolder0, v1);
            if(v3 > 0) {
                return (v2 & v3) == 0 ? Callback.convertToRelativeDirection(v3, this.mRecyclerView.getLayoutDirection()) : v3;
            }
            int v4 = this.checkVerticalSwipe(recyclerView$ViewHolder0, v1);
            return v4 <= 0 ? 0 : v4;
        }
        int v5 = this.checkVerticalSwipe(recyclerView$ViewHolder0, v1);
        if(v5 > 0) {
            return v5;
        }
        int v6 = this.checkHorizontalSwipe(recyclerView$ViewHolder0, v1);
        if(v6 > 0) {
            return (v2 & v6) == 0 ? Callback.convertToRelativeDirection(v6, this.mRecyclerView.getLayoutDirection()) : v6;
        }
        return 0;
    }

    void updateDxDy(MotionEvent motionEvent0, int v, int v1) {
        float f = motionEvent0.getX(v1);
        float f1 = motionEvent0.getY(v1);
        float f2 = f - this.mInitialTouchX;
        this.mDx = f2;
        this.mDy = f1 - this.mInitialTouchY;
        if((v & 4) == 0) {
            this.mDx = Math.max(0.0f, f2);
        }
        if((v & 8) == 0) {
            this.mDx = Math.min(0.0f, this.mDx);
        }
        if((v & 1) == 0) {
            this.mDy = Math.max(0.0f, this.mDy);
        }
        if((v & 2) == 0) {
            this.mDy = Math.min(0.0f, this.mDy);
        }
    }
}

