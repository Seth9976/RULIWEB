package androidx.recyclerview.widget;

public final class AdapterListUpdateCallback implements ListUpdateCallback {
    private final Adapter mAdapter;

    public AdapterListUpdateCallback(Adapter recyclerView$Adapter0) {
        this.mAdapter = recyclerView$Adapter0;
    }

    @Override  // androidx.recyclerview.widget.ListUpdateCallback
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

