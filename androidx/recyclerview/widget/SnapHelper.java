package androidx.recyclerview.widget;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

public abstract class SnapHelper extends OnFlingListener {
    static final float MILLISECONDS_PER_INCH = 100.0f;
    private Scroller mGravityScroller;
    RecyclerView mRecyclerView;
    private final OnScrollListener mScrollListener;

    public SnapHelper() {
        this.mScrollListener = new OnScrollListener() {
            boolean mScrolled;

            {
                this.mScrolled = false;
            }

            @Override  // androidx.recyclerview.widget.RecyclerView$OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView0, int v) {
                super.onScrollStateChanged(recyclerView0, v);
                if(v == 0 && this.mScrolled) {
                    this.mScrolled = false;
                    SnapHelper.this.snapToTargetExistingView();
                }
            }

            @Override  // androidx.recyclerview.widget.RecyclerView$OnScrollListener
            public void onScrolled(RecyclerView recyclerView0, int v, int v1) {
                if(v == 0 && v1 == 0) {
                    return;
                }
                this.mScrolled = true;
            }
        };
    }

    public void attachToRecyclerView(RecyclerView recyclerView0) throws IllegalStateException {
        RecyclerView recyclerView1 = this.mRecyclerView;
        if(recyclerView1 != recyclerView0) {
            if(recyclerView1 != null) {
                this.destroyCallbacks();
            }
            this.mRecyclerView = recyclerView0;
            if(recyclerView0 != null) {
                this.setupCallbacks();
                this.mGravityScroller = new Scroller(this.mRecyclerView.getContext(), new DecelerateInterpolator());
                this.snapToTargetExistingView();
            }
        }
    }

    public abstract int[] calculateDistanceToFinalSnap(LayoutManager arg1, View arg2);

    public int[] calculateScrollDistance(int v, int v1) {
        this.mGravityScroller.fling(0, 0, v, v1, 0x80000000, 0x7FFFFFFF, 0x80000000, 0x7FFFFFFF);
        return new int[]{this.mGravityScroller.getFinalX(), this.mGravityScroller.getFinalY()};
    }

    protected SmoothScroller createScroller(LayoutManager recyclerView$LayoutManager0) {
        return this.createSnapScroller(recyclerView$LayoutManager0);
    }

    @Deprecated
    protected LinearSmoothScroller createSnapScroller(LayoutManager recyclerView$LayoutManager0) {
        return !(recyclerView$LayoutManager0 instanceof ScrollVectorProvider) ? null : new LinearSmoothScroller(this.mRecyclerView.getContext()) {
            @Override  // androidx.recyclerview.widget.LinearSmoothScroller
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics0) {
                return 100.0f / ((float)displayMetrics0.densityDpi);
            }

            @Override  // androidx.recyclerview.widget.LinearSmoothScroller
            protected void onTargetFound(View view0, State recyclerView$State0, Action recyclerView$SmoothScroller$Action0) {
                if(SnapHelper.this.mRecyclerView != null) {
                    int[] arr_v = SnapHelper.this.calculateDistanceToFinalSnap(SnapHelper.this.mRecyclerView.getLayoutManager(), view0);
                    int v = arr_v[0];
                    int v1 = arr_v[1];
                    int v2 = this.calculateTimeForDeceleration(Math.max(Math.abs(v), Math.abs(v1)));
                    if(v2 > 0) {
                        recyclerView$SmoothScroller$Action0.update(v, v1, v2, this.mDecelerateInterpolator);
                    }
                }
            }
        };
    }

    private void destroyCallbacks() {
        this.mRecyclerView.removeOnScrollListener(this.mScrollListener);
        this.mRecyclerView.setOnFlingListener(null);
    }

    public abstract View findSnapView(LayoutManager arg1);

    public abstract int findTargetSnapPosition(LayoutManager arg1, int arg2, int arg3);

    @Override  // androidx.recyclerview.widget.RecyclerView$OnFlingListener
    public boolean onFling(int v, int v1) {
        LayoutManager recyclerView$LayoutManager0 = this.mRecyclerView.getLayoutManager();
        if(recyclerView$LayoutManager0 == null) {
            return false;
        }
        if(this.mRecyclerView.getAdapter() == null) {
            return false;
        }
        int v2 = this.mRecyclerView.getMinFlingVelocity();
        return (Math.abs(v1) > v2 || Math.abs(v) > v2) && this.snapFromFling(recyclerView$LayoutManager0, v, v1);
    }

    private void setupCallbacks() throws IllegalStateException {
        if(this.mRecyclerView.getOnFlingListener() != null) {
            throw new IllegalStateException("An instance of OnFlingListener already set.");
        }
        this.mRecyclerView.addOnScrollListener(this.mScrollListener);
        this.mRecyclerView.setOnFlingListener(this);
    }

    private boolean snapFromFling(LayoutManager recyclerView$LayoutManager0, int v, int v1) {
        if(!(recyclerView$LayoutManager0 instanceof ScrollVectorProvider)) {
            return false;
        }
        SmoothScroller recyclerView$SmoothScroller0 = this.createScroller(recyclerView$LayoutManager0);
        if(recyclerView$SmoothScroller0 == null) {
            return false;
        }
        int v2 = this.findTargetSnapPosition(recyclerView$LayoutManager0, v, v1);
        if(v2 == -1) {
            return false;
        }
        recyclerView$SmoothScroller0.setTargetPosition(v2);
        recyclerView$LayoutManager0.startSmoothScroll(recyclerView$SmoothScroller0);
        return true;
    }

    void snapToTargetExistingView() {
        RecyclerView recyclerView0 = this.mRecyclerView;
        if(recyclerView0 != null) {
            LayoutManager recyclerView$LayoutManager0 = recyclerView0.getLayoutManager();
            if(recyclerView$LayoutManager0 != null) {
                View view0 = this.findSnapView(recyclerView$LayoutManager0);
                if(view0 != null) {
                    int[] arr_v = this.calculateDistanceToFinalSnap(recyclerView$LayoutManager0, view0);
                    int v = arr_v[0];
                    if(v != 0 || arr_v[1] != 0) {
                        this.mRecyclerView.smoothScrollBy(v, arr_v[1]);
                    }
                }
            }
        }
    }
}

