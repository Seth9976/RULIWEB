package androidx.viewpager2.widget;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import androidx.recyclerview.widget.RecyclerView;

final class FakeDrag {
    private int mActualDraggedDistance;
    private long mFakeDragBeginTime;
    private int mMaximumVelocity;
    private final RecyclerView mRecyclerView;
    private float mRequestedDragDistance;
    private final ScrollEventAdapter mScrollEventAdapter;
    private VelocityTracker mVelocityTracker;
    private final ViewPager2 mViewPager;

    FakeDrag(ViewPager2 viewPager20, ScrollEventAdapter scrollEventAdapter0, RecyclerView recyclerView0) {
        this.mViewPager = viewPager20;
        this.mScrollEventAdapter = scrollEventAdapter0;
        this.mRecyclerView = recyclerView0;
    }

    private void addFakeMotionEvent(long v, int v1, float f, float f1) {
        MotionEvent motionEvent0 = MotionEvent.obtain(this.mFakeDragBeginTime, v, v1, f, f1, 0);
        this.mVelocityTracker.addMovement(motionEvent0);
        motionEvent0.recycle();
    }

    boolean beginFakeDrag() {
        if(this.mScrollEventAdapter.isDragging()) {
            return false;
        }
        this.mActualDraggedDistance = 0;
        this.mRequestedDragDistance = 0.0f;
        this.mFakeDragBeginTime = SystemClock.uptimeMillis();
        this.beginFakeVelocityTracker();
        this.mScrollEventAdapter.notifyBeginFakeDrag();
        if(!this.mScrollEventAdapter.isIdle()) {
            this.mRecyclerView.stopScroll();
        }
        this.addFakeMotionEvent(this.mFakeDragBeginTime, 0, 0.0f, 0.0f);
        return true;
    }

    private void beginFakeVelocityTracker() {
        VelocityTracker velocityTracker0 = this.mVelocityTracker;
        if(velocityTracker0 == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
            this.mMaximumVelocity = ViewConfiguration.get(this.mViewPager.getContext()).getScaledMaximumFlingVelocity();
            return;
        }
        velocityTracker0.clear();
    }

    boolean endFakeDrag() {
        if(!this.mScrollEventAdapter.isFakeDragging()) {
            return false;
        }
        this.mScrollEventAdapter.notifyEndFakeDrag();
        VelocityTracker velocityTracker0 = this.mVelocityTracker;
        velocityTracker0.computeCurrentVelocity(1000, ((float)this.mMaximumVelocity));
        int v = (int)velocityTracker0.getXVelocity();
        int v1 = (int)velocityTracker0.getYVelocity();
        if(!this.mRecyclerView.fling(v, v1)) {
            this.mViewPager.snapToPage();
        }
        return true;
    }

    boolean fakeDragBy(float f) {
        int v = 0;
        if(!this.mScrollEventAdapter.isFakeDragging()) {
            return false;
        }
        float f1 = this.mRequestedDragDistance - f;
        this.mRequestedDragDistance = f1;
        int v1 = Math.round(f1 - ((float)this.mActualDraggedDistance));
        this.mActualDraggedDistance += v1;
        long v2 = SystemClock.uptimeMillis();
        boolean z = this.mViewPager.getOrientation() == 0;
        if(!z) {
            v = v1;
        }
        float f2 = z ? this.mRequestedDragDistance : 0.0f;
        float f3 = z ? 0.0f : this.mRequestedDragDistance;
        this.mRecyclerView.scrollBy((z ? v1 : 0), v);
        this.addFakeMotionEvent(v2, 2, f2, f3);
        return true;
    }

    boolean isFakeDragging() {
        return this.mScrollEventAdapter.isFakeDragging();
    }
}

