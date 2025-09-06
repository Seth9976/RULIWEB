package androidx.recyclerview.widget;

import android.util.Log;
import android.util.Pair;
import android.view.ViewGroup;
import androidx.core.util.Preconditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;

class ConcatAdapterController implements Callback {
    static class WrapperAndLocalPosition {
        boolean mInUse;
        int mLocalPosition;
        NestedAdapterWrapper mWrapper;

    }

    private List mAttachedRecyclerViews;
    private final IdentityHashMap mBinderLookup;
    private final ConcatAdapter mConcatAdapter;
    private WrapperAndLocalPosition mReusableHolder;
    private final StableIdMode mStableIdMode;
    private final StableIdStorage mStableIdStorage;
    private final ViewTypeStorage mViewTypeStorage;
    private List mWrappers;

    ConcatAdapterController(ConcatAdapter concatAdapter0, Config concatAdapter$Config0) {
        this.mAttachedRecyclerViews = new ArrayList();
        this.mBinderLookup = new IdentityHashMap();
        this.mWrappers = new ArrayList();
        this.mReusableHolder = new WrapperAndLocalPosition();
        this.mConcatAdapter = concatAdapter0;
        this.mViewTypeStorage = concatAdapter$Config0.isolateViewTypes ? new IsolatedViewTypeStorage() : new SharedIdRangeViewTypeStorage();
        this.mStableIdMode = concatAdapter$Config0.stableIdMode;
        if(concatAdapter$Config0.stableIdMode == StableIdMode.NO_STABLE_IDS) {
            this.mStableIdStorage = new NoStableIdStorage();
            return;
        }
        if(concatAdapter$Config0.stableIdMode == StableIdMode.ISOLATED_STABLE_IDS) {
            this.mStableIdStorage = new IsolatedStableIdStorage();
            return;
        }
        if(concatAdapter$Config0.stableIdMode != StableIdMode.SHARED_STABLE_IDS) {
            throw new IllegalArgumentException("unknown stable id mode");
        }
        this.mStableIdStorage = new SharedPoolStableIdStorage();
    }

    boolean addAdapter(int v, Adapter recyclerView$Adapter0) {
        if(v < 0 || v > this.mWrappers.size()) {
            throw new IndexOutOfBoundsException("Index must be between 0 and " + this.mWrappers.size() + ". Given:" + v);
        }
        if(this.hasStableIds()) {
            Preconditions.checkArgument(recyclerView$Adapter0.hasStableIds(), "All sub adapters must have stable ids when stable id mode is ISOLATED_STABLE_IDS or SHARED_STABLE_IDS");
        }
        else if(recyclerView$Adapter0.hasStableIds()) {
            Log.w("ConcatAdapter", "Stable ids in the adapter will be ignored as the ConcatAdapter is configured not to have stable ids");
        }
        if(this.findWrapperFor(recyclerView$Adapter0) != null) {
            return false;
        }
        StableIdLookup stableIdStorage$StableIdLookup0 = this.mStableIdStorage.createStableIdLookup();
        NestedAdapterWrapper nestedAdapterWrapper0 = new NestedAdapterWrapper(recyclerView$Adapter0, this, this.mViewTypeStorage, stableIdStorage$StableIdLookup0);
        this.mWrappers.add(v, nestedAdapterWrapper0);
        for(Object object0: this.mAttachedRecyclerViews) {
            RecyclerView recyclerView0 = (RecyclerView)((WeakReference)object0).get();
            if(recyclerView0 != null) {
                recyclerView$Adapter0.onAttachedToRecyclerView(recyclerView0);
            }
        }
        if(nestedAdapterWrapper0.getCachedItemCount() > 0) {
            int v1 = this.countItemsBefore(nestedAdapterWrapper0);
            this.mConcatAdapter.notifyItemRangeInserted(v1, nestedAdapterWrapper0.getCachedItemCount());
        }
        this.calculateAndUpdateStateRestorationPolicy();
        return true;
    }

    boolean addAdapter(Adapter recyclerView$Adapter0) {
        return this.addAdapter(this.mWrappers.size(), recyclerView$Adapter0);
    }

    private void calculateAndUpdateStateRestorationPolicy() {
        StateRestorationPolicy recyclerView$Adapter$StateRestorationPolicy0 = this.computeStateRestorationPolicy();
        if(recyclerView$Adapter$StateRestorationPolicy0 != this.mConcatAdapter.getStateRestorationPolicy()) {
            this.mConcatAdapter.internalSetStateRestorationPolicy(recyclerView$Adapter$StateRestorationPolicy0);
        }
    }

    public boolean canRestoreState() {
        for(Object object0: this.mWrappers) {
            if(!((NestedAdapterWrapper)object0).adapter.canRestoreState()) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }

    private StateRestorationPolicy computeStateRestorationPolicy() {
        for(Object object0: this.mWrappers) {
            StateRestorationPolicy recyclerView$Adapter$StateRestorationPolicy0 = ((NestedAdapterWrapper)object0).adapter.getStateRestorationPolicy();
            if(recyclerView$Adapter$StateRestorationPolicy0 == StateRestorationPolicy.PREVENT) {
                return StateRestorationPolicy.PREVENT;
            }
            if(recyclerView$Adapter$StateRestorationPolicy0 == StateRestorationPolicy.PREVENT_WHEN_EMPTY && ((NestedAdapterWrapper)object0).getCachedItemCount() == 0) {
                return StateRestorationPolicy.PREVENT;
            }
            if(false) {
                break;
            }
        }
        return StateRestorationPolicy.ALLOW;
    }

    private int countItemsBefore(NestedAdapterWrapper nestedAdapterWrapper0) {
        int v = 0;
        for(Object object0: this.mWrappers) {
            if(((NestedAdapterWrapper)object0) == nestedAdapterWrapper0) {
                break;
            }
            v += ((NestedAdapterWrapper)object0).getCachedItemCount();
        }
        return v;
    }

    private WrapperAndLocalPosition findWrapperAndLocalPosition(int v) {
        WrapperAndLocalPosition concatAdapterController$WrapperAndLocalPosition0;
        if(this.mReusableHolder.mInUse) {
            concatAdapterController$WrapperAndLocalPosition0 = new WrapperAndLocalPosition();
        }
        else {
            this.mReusableHolder.mInUse = true;
            concatAdapterController$WrapperAndLocalPosition0 = this.mReusableHolder;
        }
        int v1 = v;
        for(Object object0: this.mWrappers) {
            NestedAdapterWrapper nestedAdapterWrapper0 = (NestedAdapterWrapper)object0;
            if(nestedAdapterWrapper0.getCachedItemCount() > v1) {
                concatAdapterController$WrapperAndLocalPosition0.mWrapper = nestedAdapterWrapper0;
                concatAdapterController$WrapperAndLocalPosition0.mLocalPosition = v1;
                break;
            }
            v1 -= nestedAdapterWrapper0.getCachedItemCount();
        }
        if(concatAdapterController$WrapperAndLocalPosition0.mWrapper == null) {
            throw new IllegalArgumentException("Cannot find wrapper for " + v);
        }
        return concatAdapterController$WrapperAndLocalPosition0;
    }

    private NestedAdapterWrapper findWrapperFor(Adapter recyclerView$Adapter0) {
        int v = this.indexOfWrapper(recyclerView$Adapter0);
        return v == -1 ? null : ((NestedAdapterWrapper)this.mWrappers.get(v));
    }

    public Adapter getBoundAdapter(ViewHolder recyclerView$ViewHolder0) {
        NestedAdapterWrapper nestedAdapterWrapper0 = (NestedAdapterWrapper)this.mBinderLookup.get(recyclerView$ViewHolder0);
        return nestedAdapterWrapper0 == null ? null : nestedAdapterWrapper0.adapter;
    }

    public List getCopyOfAdapters() {
        if(this.mWrappers.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        List list0 = new ArrayList(this.mWrappers.size());
        for(Object object0: this.mWrappers) {
            list0.add(((NestedAdapterWrapper)object0).adapter);
        }
        return list0;
    }

    public long getItemId(int v) {
        WrapperAndLocalPosition concatAdapterController$WrapperAndLocalPosition0 = this.findWrapperAndLocalPosition(v);
        long v1 = concatAdapterController$WrapperAndLocalPosition0.mWrapper.getItemId(concatAdapterController$WrapperAndLocalPosition0.mLocalPosition);
        this.releaseWrapperAndLocalPosition(concatAdapterController$WrapperAndLocalPosition0);
        return v1;
    }

    public int getItemViewType(int v) {
        WrapperAndLocalPosition concatAdapterController$WrapperAndLocalPosition0 = this.findWrapperAndLocalPosition(v);
        int v1 = concatAdapterController$WrapperAndLocalPosition0.mWrapper.getItemViewType(concatAdapterController$WrapperAndLocalPosition0.mLocalPosition);
        this.releaseWrapperAndLocalPosition(concatAdapterController$WrapperAndLocalPosition0);
        return v1;
    }

    public int getLocalAdapterPosition(Adapter recyclerView$Adapter0, ViewHolder recyclerView$ViewHolder0, int v) {
        NestedAdapterWrapper nestedAdapterWrapper0 = (NestedAdapterWrapper)this.mBinderLookup.get(recyclerView$ViewHolder0);
        if(nestedAdapterWrapper0 == null) {
            return -1;
        }
        int v1 = v - this.countItemsBefore(nestedAdapterWrapper0);
        int v2 = nestedAdapterWrapper0.adapter.getItemCount();
        if(v1 < 0 || v1 >= v2) {
            throw new IllegalStateException("Detected inconsistent adapter updates. The local position of the view holder maps to " + v1 + " which is out of bounds for the adapter with size " + v2 + ".Make sure to immediately call notify methods in your adapter when you change the backing dataviewHolder:" + recyclerView$ViewHolder0 + "adapter:" + recyclerView$Adapter0);
        }
        return nestedAdapterWrapper0.adapter.findRelativeAdapterPositionIn(recyclerView$Adapter0, recyclerView$ViewHolder0, v1);
    }

    public int getTotalCount() {
        int v = 0;
        for(Object object0: this.mWrappers) {
            v += ((NestedAdapterWrapper)object0).getCachedItemCount();
        }
        return v;
    }

    public Pair getWrappedAdapterAndPosition(int v) {
        WrapperAndLocalPosition concatAdapterController$WrapperAndLocalPosition0 = this.findWrapperAndLocalPosition(v);
        Pair pair0 = new Pair(concatAdapterController$WrapperAndLocalPosition0.mWrapper.adapter, concatAdapterController$WrapperAndLocalPosition0.mLocalPosition);
        this.releaseWrapperAndLocalPosition(concatAdapterController$WrapperAndLocalPosition0);
        return pair0;
    }

    private NestedAdapterWrapper getWrapper(ViewHolder recyclerView$ViewHolder0) {
        NestedAdapterWrapper nestedAdapterWrapper0 = (NestedAdapterWrapper)this.mBinderLookup.get(recyclerView$ViewHolder0);
        if(nestedAdapterWrapper0 == null) {
            throw new IllegalStateException("Cannot find wrapper for " + recyclerView$ViewHolder0 + ", seems like it is not bound by this adapter: " + this);
        }
        return nestedAdapterWrapper0;
    }

    public boolean hasStableIds() {
        return this.mStableIdMode != StableIdMode.NO_STABLE_IDS;
    }

    private int indexOfWrapper(Adapter recyclerView$Adapter0) {
        int v = this.mWrappers.size();
        for(int v1 = 0; v1 < v; ++v1) {
            if(((NestedAdapterWrapper)this.mWrappers.get(v1)).adapter == recyclerView$Adapter0) {
                return v1;
            }
        }
        return -1;
    }

    private boolean isAttachedTo(RecyclerView recyclerView0) {
        for(Object object0: this.mAttachedRecyclerViews) {
            if(((WeakReference)object0).get() == recyclerView0) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView0) {
        if(!this.isAttachedTo(recyclerView0)) {
            this.mAttachedRecyclerViews.add(new WeakReference(recyclerView0));
            for(Object object0: this.mWrappers) {
                ((NestedAdapterWrapper)object0).adapter.onAttachedToRecyclerView(recyclerView0);
            }
        }
    }

    public void onBindViewHolder(ViewHolder recyclerView$ViewHolder0, int v) {
        WrapperAndLocalPosition concatAdapterController$WrapperAndLocalPosition0 = this.findWrapperAndLocalPosition(v);
        this.mBinderLookup.put(recyclerView$ViewHolder0, concatAdapterController$WrapperAndLocalPosition0.mWrapper);
        concatAdapterController$WrapperAndLocalPosition0.mWrapper.onBindViewHolder(recyclerView$ViewHolder0, concatAdapterController$WrapperAndLocalPosition0.mLocalPosition);
        this.releaseWrapperAndLocalPosition(concatAdapterController$WrapperAndLocalPosition0);
    }

    @Override  // androidx.recyclerview.widget.NestedAdapterWrapper$Callback
    public void onChanged(NestedAdapterWrapper nestedAdapterWrapper0) {
        this.mConcatAdapter.notifyDataSetChanged();
        this.calculateAndUpdateStateRestorationPolicy();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup0, int v) {
        return this.mViewTypeStorage.getWrapperForGlobalType(v).onCreateViewHolder(viewGroup0, v);
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView0) {
        for(int v = this.mAttachedRecyclerViews.size() - 1; v >= 0; --v) {
            WeakReference weakReference0 = (WeakReference)this.mAttachedRecyclerViews.get(v);
            if(weakReference0.get() == null) {
                this.mAttachedRecyclerViews.remove(v);
            }
            else if(weakReference0.get() == recyclerView0) {
                this.mAttachedRecyclerViews.remove(v);
                break;
            }
        }
        for(Object object0: this.mWrappers) {
            ((NestedAdapterWrapper)object0).adapter.onDetachedFromRecyclerView(recyclerView0);
        }
    }

    public boolean onFailedToRecycleView(ViewHolder recyclerView$ViewHolder0) {
        NestedAdapterWrapper nestedAdapterWrapper0 = (NestedAdapterWrapper)this.mBinderLookup.get(recyclerView$ViewHolder0);
        if(nestedAdapterWrapper0 == null) {
            throw new IllegalStateException("Cannot find wrapper for " + recyclerView$ViewHolder0 + ", seems like it is not bound by this adapter: " + this);
        }
        this.mBinderLookup.remove(recyclerView$ViewHolder0);
        return nestedAdapterWrapper0.adapter.onFailedToRecycleView(recyclerView$ViewHolder0);
    }

    @Override  // androidx.recyclerview.widget.NestedAdapterWrapper$Callback
    public void onItemRangeChanged(NestedAdapterWrapper nestedAdapterWrapper0, int v, int v1) {
        int v2 = this.countItemsBefore(nestedAdapterWrapper0);
        this.mConcatAdapter.notifyItemRangeChanged(v + v2, v1);
    }

    @Override  // androidx.recyclerview.widget.NestedAdapterWrapper$Callback
    public void onItemRangeChanged(NestedAdapterWrapper nestedAdapterWrapper0, int v, int v1, Object object0) {
        int v2 = this.countItemsBefore(nestedAdapterWrapper0);
        this.mConcatAdapter.notifyItemRangeChanged(v + v2, v1, object0);
    }

    @Override  // androidx.recyclerview.widget.NestedAdapterWrapper$Callback
    public void onItemRangeInserted(NestedAdapterWrapper nestedAdapterWrapper0, int v, int v1) {
        int v2 = this.countItemsBefore(nestedAdapterWrapper0);
        this.mConcatAdapter.notifyItemRangeInserted(v + v2, v1);
    }

    @Override  // androidx.recyclerview.widget.NestedAdapterWrapper$Callback
    public void onItemRangeMoved(NestedAdapterWrapper nestedAdapterWrapper0, int v, int v1) {
        int v2 = this.countItemsBefore(nestedAdapterWrapper0);
        this.mConcatAdapter.notifyItemMoved(v + v2, v1 + v2);
    }

    @Override  // androidx.recyclerview.widget.NestedAdapterWrapper$Callback
    public void onItemRangeRemoved(NestedAdapterWrapper nestedAdapterWrapper0, int v, int v1) {
        int v2 = this.countItemsBefore(nestedAdapterWrapper0);
        this.mConcatAdapter.notifyItemRangeRemoved(v + v2, v1);
    }

    @Override  // androidx.recyclerview.widget.NestedAdapterWrapper$Callback
    public void onStateRestorationPolicyChanged(NestedAdapterWrapper nestedAdapterWrapper0) {
        this.calculateAndUpdateStateRestorationPolicy();
    }

    public void onViewAttachedToWindow(ViewHolder recyclerView$ViewHolder0) {
        this.getWrapper(recyclerView$ViewHolder0).adapter.onViewAttachedToWindow(recyclerView$ViewHolder0);
    }

    public void onViewDetachedFromWindow(ViewHolder recyclerView$ViewHolder0) {
        this.getWrapper(recyclerView$ViewHolder0).adapter.onViewDetachedFromWindow(recyclerView$ViewHolder0);
    }

    public void onViewRecycled(ViewHolder recyclerView$ViewHolder0) {
        NestedAdapterWrapper nestedAdapterWrapper0 = (NestedAdapterWrapper)this.mBinderLookup.get(recyclerView$ViewHolder0);
        if(nestedAdapterWrapper0 == null) {
            throw new IllegalStateException("Cannot find wrapper for " + recyclerView$ViewHolder0 + ", seems like it is not bound by this adapter: " + this);
        }
        nestedAdapterWrapper0.adapter.onViewRecycled(recyclerView$ViewHolder0);
        this.mBinderLookup.remove(recyclerView$ViewHolder0);
    }

    private void releaseWrapperAndLocalPosition(WrapperAndLocalPosition concatAdapterController$WrapperAndLocalPosition0) {
        concatAdapterController$WrapperAndLocalPosition0.mInUse = false;
        concatAdapterController$WrapperAndLocalPosition0.mWrapper = null;
        concatAdapterController$WrapperAndLocalPosition0.mLocalPosition = -1;
        this.mReusableHolder = concatAdapterController$WrapperAndLocalPosition0;
    }

    boolean removeAdapter(Adapter recyclerView$Adapter0) {
        int v = this.indexOfWrapper(recyclerView$Adapter0);
        if(v == -1) {
            return false;
        }
        NestedAdapterWrapper nestedAdapterWrapper0 = (NestedAdapterWrapper)this.mWrappers.get(v);
        int v1 = this.countItemsBefore(nestedAdapterWrapper0);
        this.mWrappers.remove(v);
        this.mConcatAdapter.notifyItemRangeRemoved(v1, nestedAdapterWrapper0.getCachedItemCount());
        for(Object object0: this.mAttachedRecyclerViews) {
            RecyclerView recyclerView0 = (RecyclerView)((WeakReference)object0).get();
            if(recyclerView0 != null) {
                recyclerView$Adapter0.onDetachedFromRecyclerView(recyclerView0);
            }
        }
        nestedAdapterWrapper0.dispose();
        this.calculateAndUpdateStateRestorationPolicy();
        return true;
    }
}

