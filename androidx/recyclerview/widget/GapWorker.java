package androidx.recyclerview.widget;

import android.os.Trace;
import androidx.core.os.TraceCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

final class GapWorker implements Runnable {
    static class LayoutPrefetchRegistryImpl implements LayoutPrefetchRegistry {
        int mCount;
        int[] mPrefetchArray;
        int mPrefetchDx;
        int mPrefetchDy;

        @Override  // androidx.recyclerview.widget.RecyclerView$LayoutManager$LayoutPrefetchRegistry
        public void addPosition(int v, int v1) {
            if(v < 0) {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            }
            if(v1 < 0) {
                throw new IllegalArgumentException("Pixel distance must be non-negative");
            }
            int v2 = this.mCount;
            int[] arr_v = this.mPrefetchArray;
            if(arr_v == null) {
                int[] arr_v1 = new int[4];
                this.mPrefetchArray = arr_v1;
                Arrays.fill(arr_v1, -1);
            }
            else if(v2 * 2 >= arr_v.length) {
                int[] arr_v2 = new int[v2 * 4];
                this.mPrefetchArray = arr_v2;
                System.arraycopy(arr_v, 0, arr_v2, 0, arr_v.length);
            }
            int[] arr_v3 = this.mPrefetchArray;
            arr_v3[v2 * 2] = v;
            arr_v3[v2 * 2 + 1] = v1;
            ++this.mCount;
        }

        void clearPrefetchPositions() {
            int[] arr_v = this.mPrefetchArray;
            if(arr_v != null) {
                Arrays.fill(arr_v, -1);
            }
            this.mCount = 0;
        }

        void collectPrefetchPositionsFromView(RecyclerView recyclerView0, boolean z) {
            this.mCount = 0;
            int[] arr_v = this.mPrefetchArray;
            if(arr_v != null) {
                Arrays.fill(arr_v, -1);
            }
            LayoutManager recyclerView$LayoutManager0 = recyclerView0.mLayout;
            if(recyclerView0.mAdapter != null && recyclerView$LayoutManager0 != null && recyclerView$LayoutManager0.isItemPrefetchEnabled()) {
                if(!z) {
                    if(!recyclerView0.hasPendingAdapterUpdates()) {
                        recyclerView$LayoutManager0.collectAdjacentPrefetchPositions(this.mPrefetchDx, this.mPrefetchDy, recyclerView0.mState, this);
                    }
                }
                else if(!recyclerView0.mAdapterHelper.hasPendingUpdates()) {
                    recyclerView$LayoutManager0.collectInitialPrefetchPositions(recyclerView0.mAdapter.getItemCount(), this);
                }
                if(this.mCount > recyclerView$LayoutManager0.mPrefetchMaxCountObserved) {
                    recyclerView$LayoutManager0.mPrefetchMaxCountObserved = this.mCount;
                    recyclerView$LayoutManager0.mPrefetchMaxObservedInInitialPrefetch = z;
                    recyclerView0.mRecycler.updateViewCacheSize();
                }
            }
        }

        boolean lastPrefetchIncludedPosition(int v) {
            if(this.mPrefetchArray != null) {
                int v1 = this.mCount * 2;
                for(int v2 = 0; v2 < v1; v2 += 2) {
                    if(this.mPrefetchArray[v2] == v) {
                        return true;
                    }
                }
            }
            return false;
        }

        void setPrefetchVector(int v, int v1) {
            this.mPrefetchDx = v;
            this.mPrefetchDy = v1;
        }
    }

    static class Task {
        public int distanceToItem;
        public boolean neededNextFrame;
        public int position;
        public RecyclerView view;
        public int viewVelocity;

        public void clear() {
            this.neededNextFrame = false;
            this.viewVelocity = 0;
            this.distanceToItem = 0;
            this.view = null;
            this.position = 0;
        }
    }

    long mFrameIntervalNs;
    long mPostTimeNs;
    ArrayList mRecyclerViews;
    private final ArrayList mTasks;
    static final ThreadLocal sGapWorker;
    static Comparator sTaskComparator;

    static {
        GapWorker.sGapWorker = new ThreadLocal();
        GapWorker.sTaskComparator = new Comparator() {
            public int compare(Task gapWorker$Task0, Task gapWorker$Task1) {
                if((gapWorker$Task0.view == null ? 1 : 0) != (gapWorker$Task1.view == null ? 1 : 0)) {
                    return gapWorker$Task0.view == null ? 1 : -1;
                }
                if(gapWorker$Task0.neededNextFrame != gapWorker$Task1.neededNextFrame) {
                    return gapWorker$Task0.neededNextFrame ? -1 : 1;
                }
                int v = gapWorker$Task1.viewVelocity - gapWorker$Task0.viewVelocity;
                if(v != 0) {
                    return v;
                }
                int v1 = gapWorker$Task0.distanceToItem - gapWorker$Task1.distanceToItem;
                return v1 == 0 ? 0 : v1;
            }

            @Override
            public int compare(Object object0, Object object1) {
                return this.compare(((Task)object0), ((Task)object1));
            }
        };
    }

    GapWorker() {
        this.mRecyclerViews = new ArrayList();
        this.mTasks = new ArrayList();
    }

    public void add(RecyclerView recyclerView0) {
        if(RecyclerView.sDebugAssertionsEnabled && this.mRecyclerViews.contains(recyclerView0)) {
            throw new IllegalStateException("RecyclerView already present in worker list!");
        }
        this.mRecyclerViews.add(recyclerView0);
    }

    private void buildTaskList() {
        Task gapWorker$Task0;
        int v = this.mRecyclerViews.size();
        int v2 = 0;
        for(int v1 = 0; v1 < v; ++v1) {
            RecyclerView recyclerView0 = (RecyclerView)this.mRecyclerViews.get(v1);
            if(recyclerView0.getWindowVisibility() == 0) {
                recyclerView0.mPrefetchRegistry.collectPrefetchPositionsFromView(recyclerView0, false);
                v2 += recyclerView0.mPrefetchRegistry.mCount;
            }
        }
        this.mTasks.ensureCapacity(v2);
        int v4 = 0;
        for(int v3 = 0; v3 < v; ++v3) {
            RecyclerView recyclerView1 = (RecyclerView)this.mRecyclerViews.get(v3);
            if(recyclerView1.getWindowVisibility() == 0) {
                LayoutPrefetchRegistryImpl gapWorker$LayoutPrefetchRegistryImpl0 = recyclerView1.mPrefetchRegistry;
                int v5 = Math.abs(gapWorker$LayoutPrefetchRegistryImpl0.mPrefetchDx) + Math.abs(gapWorker$LayoutPrefetchRegistryImpl0.mPrefetchDy);
                for(int v6 = 0; v6 < gapWorker$LayoutPrefetchRegistryImpl0.mCount * 2; v6 += 2) {
                    if(v4 >= this.mTasks.size()) {
                        gapWorker$Task0 = new Task();
                        this.mTasks.add(gapWorker$Task0);
                    }
                    else {
                        gapWorker$Task0 = (Task)this.mTasks.get(v4);
                    }
                    int v7 = gapWorker$LayoutPrefetchRegistryImpl0.mPrefetchArray[v6 + 1];
                    gapWorker$Task0.neededNextFrame = v7 <= v5;
                    gapWorker$Task0.viewVelocity = v5;
                    gapWorker$Task0.distanceToItem = v7;
                    gapWorker$Task0.view = recyclerView1;
                    gapWorker$Task0.position = gapWorker$LayoutPrefetchRegistryImpl0.mPrefetchArray[v6];
                    ++v4;
                }
            }
        }
        Collections.sort(this.mTasks, GapWorker.sTaskComparator);
    }

    private void flushTaskWithDeadline(Task gapWorker$Task0, long v) {
        ViewHolder recyclerView$ViewHolder0 = this.prefetchPositionWithDeadline(gapWorker$Task0.view, gapWorker$Task0.position, (gapWorker$Task0.neededNextFrame ? 0x7FFFFFFFFFFFFFFFL : v));
        if(recyclerView$ViewHolder0 != null && recyclerView$ViewHolder0.mNestedRecyclerView != null && recyclerView$ViewHolder0.isBound() && !recyclerView$ViewHolder0.isInvalid()) {
            this.prefetchInnerRecyclerViewWithDeadline(((RecyclerView)recyclerView$ViewHolder0.mNestedRecyclerView.get()), v);
        }
    }

    private void flushTasksWithDeadline(long v) {
        for(int v1 = 0; v1 < this.mTasks.size(); ++v1) {
            Task gapWorker$Task0 = (Task)this.mTasks.get(v1);
            if(gapWorker$Task0.view == null) {
                break;
            }
            this.flushTaskWithDeadline(gapWorker$Task0, v);
            gapWorker$Task0.clear();
        }
    }

    static boolean isPrefetchPositionAttached(RecyclerView recyclerView0, int v) {
        int v1 = recyclerView0.mChildHelper.getUnfilteredChildCount();
        for(int v2 = 0; v2 < v1; ++v2) {
            ViewHolder recyclerView$ViewHolder0 = RecyclerView.getChildViewHolderInt(recyclerView0.mChildHelper.getUnfilteredChildAt(v2));
            if(recyclerView$ViewHolder0.mPosition == v && !recyclerView$ViewHolder0.isInvalid()) {
                return true;
            }
        }
        return false;
    }

    void postFromTraversal(RecyclerView recyclerView0, int v, int v1) {
        if(recyclerView0.isAttachedToWindow()) {
            if(RecyclerView.sDebugAssertionsEnabled && !this.mRecyclerViews.contains(recyclerView0)) {
                throw new IllegalStateException("attempting to post unregistered view!");
            }
            if(this.mPostTimeNs == 0L) {
                this.mPostTimeNs = 0x11DFB3969F20L;
                recyclerView0.post(this);
            }
        }
        recyclerView0.mPrefetchRegistry.setPrefetchVector(v, v1);
    }

    void prefetch(long v) {
        this.buildTaskList();
        this.flushTasksWithDeadline(v);
    }

    private void prefetchInnerRecyclerViewWithDeadline(RecyclerView recyclerView0, long v) {
        if(recyclerView0 != null) {
            if(recyclerView0.mDataSetHasChangedAfterLayout && recyclerView0.mChildHelper.getUnfilteredChildCount() != 0) {
                recyclerView0.removeAndRecycleViews();
            }
            LayoutPrefetchRegistryImpl gapWorker$LayoutPrefetchRegistryImpl0 = recyclerView0.mPrefetchRegistry;
            gapWorker$LayoutPrefetchRegistryImpl0.collectPrefetchPositionsFromView(recyclerView0, true);
            if(gapWorker$LayoutPrefetchRegistryImpl0.mCount != 0) {
                try {
                    Trace.beginSection((v == 0x7FFFFFFFFFFFFFFFL ? "RV Nested Prefetch" : "RV Nested Prefetch forced - needed next frame"));
                    recyclerView0.mState.prepareForNestedPrefetch(recyclerView0.mAdapter);
                    for(int v2 = 0; v2 < gapWorker$LayoutPrefetchRegistryImpl0.mCount * 2; v2 += 2) {
                        this.prefetchPositionWithDeadline(recyclerView0, gapWorker$LayoutPrefetchRegistryImpl0.mPrefetchArray[v2], v);
                    }
                }
                finally {
                    Trace.endSection();
                }
            }
        }
    }

    private ViewHolder prefetchPositionWithDeadline(RecyclerView recyclerView0, int v, long v1) {
        if(GapWorker.isPrefetchPositionAttached(recyclerView0, v)) {
            return null;
        }
        try {
            Recycler recyclerView$Recycler0 = recyclerView0.mRecycler;
            if(v1 == 0x7FFFFFFFFFFFFFFFL && TraceCompat.isEnabled()) {
                Trace.beginSection("RV Prefetch forced - needed next frame");
            }
            recyclerView0.onEnterLayoutOrScroll();
            ViewHolder recyclerView$ViewHolder0 = recyclerView$Recycler0.tryGetViewHolderForPositionByDeadline(v, false, v1);
            if(recyclerView$ViewHolder0 != null) {
                if(!recyclerView$ViewHolder0.isBound() || recyclerView$ViewHolder0.isInvalid()) {
                    recyclerView$Recycler0.addViewHolderToRecycledViewPool(recyclerView$ViewHolder0, false);
                }
                else {
                    recyclerView$Recycler0.recycleView(recyclerView$ViewHolder0.itemView);
                }
            }
            return recyclerView$ViewHolder0;
        }
        finally {
            recyclerView0.onExitLayoutOrScroll(false);
            Trace.endSection();
        }
    }

    public void remove(RecyclerView recyclerView0) {
        boolean z = this.mRecyclerViews.remove(recyclerView0);
        if(RecyclerView.sDebugAssertionsEnabled && !z) {
            throw new IllegalStateException("RecyclerView removal failed!");
        }
    }

    @Override
    public void run() {
        try {
            Trace.beginSection("RV Prefetch");
            if(!this.mRecyclerViews.isEmpty()) {
                int v1 = this.mRecyclerViews.size();
                long v3 = 0L;
                for(int v2 = 0; v2 < v1; ++v2) {
                    RecyclerView recyclerView0 = (RecyclerView)this.mRecyclerViews.get(v2);
                    if(recyclerView0.getWindowVisibility() == 0) {
                        v3 = Math.max(recyclerView0.getDrawingTime(), v3);
                    }
                }
                if(v3 != 0L) {
                    this.prefetch(TimeUnit.MILLISECONDS.toNanos(v3) + this.mFrameIntervalNs);
                }
            }
        }
        finally {
            this.mPostTimeNs = 0L;
            Trace.endSection();
        }
    }
}

