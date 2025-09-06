package androidx.recyclerview.widget;

public abstract class SortedListAdapterCallback extends Callback {
    final Adapter mAdapter;

    public SortedListAdapterCallback(Adapter recyclerView$Adapter0) {
        this.mAdapter = recyclerView$Adapter0;
    }

    @Override  // androidx.recyclerview.widget.SortedList$Callback
    public void onChanged(int v, int v1) {
        this.mAdapter.notifyItemRangeChanged(v, v1);
    }

    @Override  // androidx.recyclerview.widget.SortedList$Callback
    public void onChanged(int v, int v1, Object object0) {
        this.mAdapter.notifyItemRangeChanged(v, v1, object0);
    }

    @Override  // androidx.recyclerview.widget.ListUpdateCallback
    public void onInserted(int v, int v1) {
        this.mAdapter.notifyItemRangeInserted(v, v1);
    }

    @Override  // androidx.recyclerview.widget.ListUpdateCallback
    public void onMoved(int v, int v1) {
        this.mAdapter.notifyItemMoved(v, v1);
    }

    @Override  // androidx.recyclerview.widget.ListUpdateCallback
    public void onRemoved(int v, int v1) {
        this.mAdapter.notifyItemRangeRemoved(v, v1);
    }
}

