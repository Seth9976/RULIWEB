package androidx.recyclerview.widget;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;

public class AsyncListUtil {
    public static abstract class DataCallback {
        public abstract void fillData(Object[] arg1, int arg2, int arg3);

        public int getMaxCachedTiles() [...] // Inlined contents

        public void recycleData(Object[] arr_object, int v) {
        }

        public abstract int refreshData();
    }

    public static abstract class ViewCallback {
        public static final int HINT_SCROLL_ASC = 2;
        public static final int HINT_SCROLL_DESC = 1;
        public static final int HINT_SCROLL_NONE;

        public void extendRangeInto(int[] arr_v, int[] arr_v1, int v) {
            int v1 = arr_v[1];
            int v2 = v1 - arr_v[0] + 1;
            arr_v1[0] = arr_v[0] - (v == 1 ? v2 : v2 / 2);
            if(v != 2) {
                v2 /= 2;
            }
            arr_v1[1] = v1 + v2;
        }

        public abstract void getItemRangeInto(int[] arg1);

        public abstract void onDataRefresh();

        public abstract void onItemLoaded(int arg1);
    }

    static final boolean DEBUG = false;
    static final String TAG = "AsyncListUtil";
    boolean mAllowScrollHints;
    private final BackgroundCallback mBackgroundCallback;
    final BackgroundCallback mBackgroundProxy;
    final DataCallback mDataCallback;
    int mDisplayedGeneration;
    int mItemCount;
    private final MainThreadCallback mMainThreadCallback;
    final MainThreadCallback mMainThreadProxy;
    final SparseIntArray mMissingPositions;
    final int[] mPrevRange;
    int mRequestedGeneration;
    private int mScrollHint;
    final Class mTClass;
    final TileList mTileList;
    final int mTileSize;
    final int[] mTmpRange;
    final int[] mTmpRangeExtended;
    final ViewCallback mViewCallback;

    public AsyncListUtil(Class class0, int v, DataCallback asyncListUtil$DataCallback0, ViewCallback asyncListUtil$ViewCallback0) {
        this.mTmpRange = new int[2];
        this.mPrevRange = new int[2];
        this.mTmpRangeExtended = new int[2];
        this.mScrollHint = 0;
        this.mItemCount = 0;
        this.mDisplayedGeneration = 0;
        this.mRequestedGeneration = 0;
        this.mMissingPositions = new SparseIntArray();
        androidx.recyclerview.widget.AsyncListUtil.1 asyncListUtil$10 = new MainThreadCallback() {
            @Override  // androidx.recyclerview.widget.ThreadUtil$MainThreadCallback
            public void addTile(int v, Tile tileList$Tile0) {
                if(!this.isRequestedGeneration(v)) {
                    AsyncListUtil.this.mBackgroundProxy.recycleTile(tileList$Tile0);
                    return;
                }
                Tile tileList$Tile1 = AsyncListUtil.this.mTileList.addOrReplace(tileList$Tile0);
                if(tileList$Tile1 != null) {
                    Log.e("AsyncListUtil", "duplicate tile @" + tileList$Tile1.mStartPosition);
                    AsyncListUtil.this.mBackgroundProxy.recycleTile(tileList$Tile1);
                }
                int v1 = tileList$Tile0.mStartPosition + tileList$Tile0.mItemCount;
                int v2 = 0;
                while(v2 < AsyncListUtil.this.mMissingPositions.size()) {
                    int v3 = AsyncListUtil.this.mMissingPositions.keyAt(v2);
                    if(tileList$Tile0.mStartPosition <= v3 && v3 < v1) {
                        AsyncListUtil.this.mMissingPositions.removeAt(v2);
                        AsyncListUtil.this.mViewCallback.onItemLoaded(v3);
                    }
                    else {
                        ++v2;
                    }
                }
            }

            private boolean isRequestedGeneration(int v) {
                return v == AsyncListUtil.this.mRequestedGeneration;
            }

            private void recycleAllTiles() {
                for(int v = 0; v < AsyncListUtil.this.mTileList.size(); ++v) {
                    Tile tileList$Tile0 = AsyncListUtil.this.mTileList.getAtIndex(v);
                    AsyncListUtil.this.mBackgroundProxy.recycleTile(tileList$Tile0);
                }
                AsyncListUtil.this.mTileList.clear();
            }

            @Override  // androidx.recyclerview.widget.ThreadUtil$MainThreadCallback
            public void removeTile(int v, int v1) {
                if(!this.isRequestedGeneration(v)) {
                    return;
                }
                Tile tileList$Tile0 = AsyncListUtil.this.mTileList.removeAtPos(v1);
                if(tileList$Tile0 == null) {
                    Log.e("AsyncListUtil", "tile not found @" + v1);
                    return;
                }
                AsyncListUtil.this.mBackgroundProxy.recycleTile(tileList$Tile0);
            }

            @Override  // androidx.recyclerview.widget.ThreadUtil$MainThreadCallback
            public void updateItemCount(int v, int v1) {
                if(!this.isRequestedGeneration(v)) {
                    return;
                }
                AsyncListUtil.this.mItemCount = v1;
                AsyncListUtil.this.mViewCallback.onDataRefresh();
                AsyncListUtil.this.mDisplayedGeneration = AsyncListUtil.this.mRequestedGeneration;
                this.recycleAllTiles();
                AsyncListUtil.this.mAllowScrollHints = false;
                AsyncListUtil.this.updateRange();
            }
        };
        this.mMainThreadCallback = asyncListUtil$10;
        androidx.recyclerview.widget.AsyncListUtil.2 asyncListUtil$20 = new BackgroundCallback() {
            private int mFirstRequiredTileStart;
            private int mGeneration;
            private int mItemCount;
            private int mLastRequiredTileStart;
            final SparseBooleanArray mLoadedTiles;
            private Tile mRecycledRoot;

            {
                this.mLoadedTiles = new SparseBooleanArray();
            }

            private Tile acquireTile() {
                Tile tileList$Tile0 = this.mRecycledRoot;
                if(tileList$Tile0 != null) {
                    this.mRecycledRoot = tileList$Tile0.mNext;
                    return tileList$Tile0;
                }
                return new Tile(AsyncListUtil.this.mTClass, AsyncListUtil.this.mTileSize);
            }

            private void addTile(Tile tileList$Tile0) {
                this.mLoadedTiles.put(tileList$Tile0.mStartPosition, true);
                AsyncListUtil.this.mMainThreadProxy.addTile(this.mGeneration, tileList$Tile0);
            }

            private void flushTileCache(int v) {
                while(this.mLoadedTiles.size() >= 10) {
                    int v1 = this.mLoadedTiles.keyAt(0);
                    int v2 = this.mLoadedTiles.keyAt(this.mLoadedTiles.size() - 1);
                    int v3 = this.mFirstRequiredTileStart - v1;
                    int v4 = v2 - this.mLastRequiredTileStart;
                    if(v3 > 0 && (v3 >= v4 || v == 2)) {
                        this.removeTile(v1);
                    }
                    else {
                        if(v4 <= 0 || v3 >= v4 && v != 1) {
                            break;
                        }
                        this.removeTile(v2);
                    }
                }
            }

            private int getTileStart(int v) {
                return v - v % AsyncListUtil.this.mTileSize;
            }

            private boolean isTileLoaded(int v) {
                return this.mLoadedTiles.get(v);
            }

            @Override  // androidx.recyclerview.widget.ThreadUtil$BackgroundCallback
            public void loadTile(int v, int v1) {
                if(this.isTileLoaded(v)) {
                    return;
                }
                Tile tileList$Tile0 = this.acquireTile();
                tileList$Tile0.mStartPosition = v;
                tileList$Tile0.mItemCount = Math.min(AsyncListUtil.this.mTileSize, this.mItemCount - tileList$Tile0.mStartPosition);
                AsyncListUtil.this.mDataCallback.fillData(tileList$Tile0.mItems, tileList$Tile0.mStartPosition, tileList$Tile0.mItemCount);
                this.flushTileCache(v1);
                this.addTile(tileList$Tile0);
            }

            private void log(String s, Object[] arr_object) {
                Log.d("AsyncListUtil", "[BKGR] " + String.format(s, arr_object));
            }

            @Override  // androidx.recyclerview.widget.ThreadUtil$BackgroundCallback
            public void recycleTile(Tile tileList$Tile0) {
                AsyncListUtil.this.mDataCallback.recycleData(tileList$Tile0.mItems, tileList$Tile0.mItemCount);
                tileList$Tile0.mNext = this.mRecycledRoot;
                this.mRecycledRoot = tileList$Tile0;
            }

            @Override  // androidx.recyclerview.widget.ThreadUtil$BackgroundCallback
            public void refresh(int v) {
                this.mGeneration = v;
                this.mLoadedTiles.clear();
                this.mItemCount = AsyncListUtil.this.mDataCallback.refreshData();
                AsyncListUtil.this.mMainThreadProxy.updateItemCount(this.mGeneration, this.mItemCount);
            }

            private void removeTile(int v) {
                this.mLoadedTiles.delete(v);
                AsyncListUtil.this.mMainThreadProxy.removeTile(this.mGeneration, v);
            }

            private void requestTiles(int v, int v1, int v2, boolean z) {
                for(int v3 = v; v3 <= v1; v3 += AsyncListUtil.this.mTileSize) {
                    AsyncListUtil.this.mBackgroundProxy.loadTile((z ? v1 + v - v3 : v3), v2);
                }
            }

            @Override  // androidx.recyclerview.widget.ThreadUtil$BackgroundCallback
            public void updateRange(int v, int v1, int v2, int v3, int v4) {
                if(v > v1) {
                    return;
                }
                int v5 = this.getTileStart(v);
                int v6 = this.getTileStart(v1);
                this.mFirstRequiredTileStart = this.getTileStart(v2);
                int v7 = this.getTileStart(v3);
                this.mLastRequiredTileStart = v7;
                if(v4 == 1) {
                    this.requestTiles(this.mFirstRequiredTileStart, v6, 1, true);
                    this.requestTiles(v6 + AsyncListUtil.this.mTileSize, this.mLastRequiredTileStart, 1, false);
                    return;
                }
                this.requestTiles(v5, v7, v4, false);
                this.requestTiles(this.mFirstRequiredTileStart, v5 - AsyncListUtil.this.mTileSize, v4, true);
            }
        };
        this.mBackgroundCallback = asyncListUtil$20;
        this.mTClass = class0;
        this.mTileSize = v;
        this.mDataCallback = asyncListUtil$DataCallback0;
        this.mViewCallback = asyncListUtil$ViewCallback0;
        this.mTileList = new TileList(v);
        MessageThreadUtil messageThreadUtil0 = new MessageThreadUtil();
        this.mMainThreadProxy = messageThreadUtil0.getMainThreadProxy(asyncListUtil$10);
        this.mBackgroundProxy = messageThreadUtil0.getBackgroundProxy(asyncListUtil$20);
        this.refresh();
    }

    public Object getItem(int v) {
        if(v < 0 || v >= this.mItemCount) {
            throw new IndexOutOfBoundsException(v + " is not within 0 and " + this.mItemCount);
        }
        Object object0 = this.mTileList.getItemAt(v);
        if(object0 == null && !this.isRefreshPending()) {
            this.mMissingPositions.put(v, 0);
        }
        return object0;
    }

    public int getItemCount() {
        return this.mItemCount;
    }

    private boolean isRefreshPending() {
        return this.mRequestedGeneration != this.mDisplayedGeneration;
    }

    void log(String s, Object[] arr_object) {
        Log.d("AsyncListUtil", "[MAIN] " + String.format(s, arr_object));
    }

    public void onRangeChanged() {
        if(this.isRefreshPending()) {
            return;
        }
        this.updateRange();
        this.mAllowScrollHints = true;
    }

    public void refresh() {
        this.mMissingPositions.clear();
        int v = this.mRequestedGeneration + 1;
        this.mRequestedGeneration = v;
        this.mBackgroundProxy.refresh(v);
    }

    void updateRange() {
        this.mViewCallback.getItemRangeInto(this.mTmpRange);
        int[] arr_v = this.mTmpRange;
        int v = arr_v[0];
        int v1 = arr_v[1];
        if(v <= v1 && v >= 0 && v1 < this.mItemCount) {
            if(this.mAllowScrollHints) {
                int[] arr_v1 = this.mPrevRange;
                if(v <= arr_v1[1]) {
                    int v2 = arr_v1[0];
                    if(v2 > v1) {
                        this.mScrollHint = 0;
                    }
                    else if(v < v2) {
                        this.mScrollHint = 1;
                    }
                    else if(v > v2) {
                        this.mScrollHint = 2;
                    }
                }
                else {
                    this.mScrollHint = 0;
                }
            }
            else {
                this.mScrollHint = 0;
            }
            this.mPrevRange[0] = v;
            this.mPrevRange[1] = v1;
            this.mViewCallback.extendRangeInto(arr_v, this.mTmpRangeExtended, this.mScrollHint);
            this.mTmpRangeExtended[0] = Math.min(this.mTmpRange[0], Math.max(this.mTmpRangeExtended[0], 0));
            this.mTmpRangeExtended[1] = Math.max(this.mTmpRange[1], Math.min(this.mTmpRangeExtended[1], this.mItemCount - 1));
            this.mBackgroundProxy.updateRange(this.mTmpRange[0], this.mTmpRange[1], this.mTmpRangeExtended[0], this.mTmpRangeExtended[1], this.mScrollHint);
        }
    }
}

