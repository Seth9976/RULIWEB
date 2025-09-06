package androidx.recyclerview.widget;

import android.view.ViewGroup;
import androidx.core.util.Preconditions;

class NestedAdapterWrapper {
    interface Callback {
        void onChanged(NestedAdapterWrapper arg1);

        void onItemRangeChanged(NestedAdapterWrapper arg1, int arg2, int arg3);

        void onItemRangeChanged(NestedAdapterWrapper arg1, int arg2, int arg3, Object arg4);

        void onItemRangeInserted(NestedAdapterWrapper arg1, int arg2, int arg3);

        void onItemRangeMoved(NestedAdapterWrapper arg1, int arg2, int arg3);

        void onItemRangeRemoved(NestedAdapterWrapper arg1, int arg2, int arg3);

        void onStateRestorationPolicyChanged(NestedAdapterWrapper arg1);
    }

    public final Adapter adapter;
    private AdapterDataObserver mAdapterObserver;
    int mCachedItemCount;
    final Callback mCallback;
    private final StableIdLookup mStableIdLookup;
    private final ViewTypeLookup mViewTypeLookup;

    NestedAdapterWrapper(Adapter recyclerView$Adapter0, Callback nestedAdapterWrapper$Callback0, ViewTypeStorage viewTypeStorage0, StableIdLookup stableIdStorage$StableIdLookup0) {
        this.mAdapterObserver = new AdapterDataObserver() {
            @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
            public void onChanged() {
                NestedAdapterWrapper.this.mCachedItemCount = NestedAdapterWrapper.this.adapter.getItemCount();
                NestedAdapterWrapper.this.mCallback.onChanged(NestedAdapterWrapper.this);
            }

            @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
            public void onItemRangeChanged(int v, int v1) {
                NestedAdapterWrapper.this.mCallback.onItemRangeChanged(NestedAdapterWrapper.this, v, v1, null);
            }

            @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
            public void onItemRangeChanged(int v, int v1, Object object0) {
                NestedAdapterWrapper.this.mCallback.onItemRangeChanged(NestedAdapterWrapper.this, v, v1, object0);
            }

            @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
            public void onItemRangeInserted(int v, int v1) {
                NestedAdapterWrapper.this.mCachedItemCount += v1;
                NestedAdapterWrapper.this.mCallback.onItemRangeInserted(NestedAdapterWrapper.this, v, v1);
                if(NestedAdapterWrapper.this.mCachedItemCount > 0 && NestedAdapterWrapper.this.adapter.getStateRestorationPolicy() == StateRestorationPolicy.PREVENT_WHEN_EMPTY) {
                    NestedAdapterWrapper.this.mCallback.onStateRestorationPolicyChanged(NestedAdapterWrapper.this);
                }
            }

            @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
            public void onItemRangeMoved(int v, int v1, int v2) {
                Preconditions.checkArgument(v2 == 1, "moving more than 1 item is not supported in RecyclerView");
                NestedAdapterWrapper.this.mCallback.onItemRangeMoved(NestedAdapterWrapper.this, v, v1);
            }

            @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
            public void onItemRangeRemoved(int v, int v1) {
                NestedAdapterWrapper.this.mCachedItemCount -= v1;
                NestedAdapterWrapper.this.mCallback.onItemRangeRemoved(NestedAdapterWrapper.this, v, v1);
                if(NestedAdapterWrapper.this.mCachedItemCount < 1 && NestedAdapterWrapper.this.adapter.getStateRestorationPolicy() == StateRestorationPolicy.PREVENT_WHEN_EMPTY) {
                    NestedAdapterWrapper.this.mCallback.onStateRestorationPolicyChanged(NestedAdapterWrapper.this);
                }
            }

            @Override  // androidx.recyclerview.widget.RecyclerView$AdapterDataObserver
            public void onStateRestorationPolicyChanged() {
                NestedAdapterWrapper.this.mCallback.onStateRestorationPolicyChanged(NestedAdapterWrapper.this);
            }
        };
        this.adapter = recyclerView$Adapter0;
        this.mCallback = nestedAdapterWrapper$Callback0;
        this.mViewTypeLookup = viewTypeStorage0.createViewTypeWrapper(this);
        this.mStableIdLookup = stableIdStorage$StableIdLookup0;
        this.mCachedItemCount = recyclerView$Adapter0.getItemCount();
        recyclerView$Adapter0.registerAdapterDataObserver(this.mAdapterObserver);
    }

    void dispose() {
        this.adapter.unregisterAdapterDataObserver(this.mAdapterObserver);
        this.mViewTypeLookup.dispose();
    }

    int getCachedItemCount() {
        return this.mCachedItemCount;
    }

    public long getItemId(int v) {
        return this.mStableIdLookup.localToGlobal(this.adapter.getItemId(v));
    }

    int getItemViewType(int v) {
        return this.mViewTypeLookup.localToGlobal(this.adapter.getItemViewType(v));
    }

    void onBindViewHolder(ViewHolder recyclerView$ViewHolder0, int v) {
        this.adapter.bindViewHolder(recyclerView$ViewHolder0, v);
    }

    ViewHolder onCreateViewHolder(ViewGroup viewGroup0, int v) {
        int v1 = this.mViewTypeLookup.globalToLocal(v);
        return this.adapter.onCreateViewHolder(viewGroup0, v1);
    }
}

