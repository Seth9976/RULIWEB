package androidx.recyclerview.widget;

import java.util.List;

public abstract class ListAdapter extends Adapter {
    final AsyncListDiffer mDiffer;
    private final ListListener mListener;

    protected ListAdapter(AsyncDifferConfig asyncDifferConfig0) {
        androidx.recyclerview.widget.ListAdapter.1 listAdapter$10 = new ListListener() {
            @Override  // androidx.recyclerview.widget.AsyncListDiffer$ListListener
            public void onCurrentListChanged(List list0, List list1) {
            }
        };
        this.mListener = listAdapter$10;
        AsyncListDiffer asyncListDiffer0 = new AsyncListDiffer(new AdapterListUpdateCallback(this), asyncDifferConfig0);
        this.mDiffer = asyncListDiffer0;
        asyncListDiffer0.addListListener(listAdapter$10);
    }

    protected ListAdapter(ItemCallback diffUtil$ItemCallback0) {
        androidx.recyclerview.widget.ListAdapter.1 listAdapter$10 = new ListListener() {
            @Override  // androidx.recyclerview.widget.AsyncListDiffer$ListListener
            public void onCurrentListChanged(List list0, List list1) {
            }
        };
        this.mListener = listAdapter$10;
        AsyncListDiffer asyncListDiffer0 = new AsyncListDiffer(new AdapterListUpdateCallback(this), new Builder(diffUtil$ItemCallback0).build());
        this.mDiffer = asyncListDiffer0;
        asyncListDiffer0.addListListener(listAdapter$10);
    }

    public List getCurrentList() {
        return this.mDiffer.getCurrentList();
    }

    protected Object getItem(int v) {
        return this.mDiffer.getCurrentList().get(v);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
    public int getItemCount() {
        return this.mDiffer.getCurrentList().size();
    }

    public void onCurrentListChanged(List list0, List list1) {
    }

    public void submitList(List list0) {
        this.mDiffer.submitList(list0);
    }

    public void submitList(List list0, Runnable runnable0) {
        this.mDiffer.submitList(list0, runnable0);
    }
}

