package androidx.recyclerview.widget;

import android.view.View;

public abstract class SimpleItemAnimator extends ItemAnimator {
    private static final boolean DEBUG = false;
    private static final String TAG = "SimpleItemAnimator";
    boolean mSupportsChangeAnimations;

    public SimpleItemAnimator() {
        this.mSupportsChangeAnimations = true;
    }

    public abstract boolean animateAdd(ViewHolder arg1);

    @Override  // androidx.recyclerview.widget.RecyclerView$ItemAnimator
    public boolean animateAppearance(ViewHolder recyclerView$ViewHolder0, ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo0, ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo1) {
        return recyclerView$ItemAnimator$ItemHolderInfo0 == null || recyclerView$ItemAnimator$ItemHolderInfo0.left == recyclerView$ItemAnimator$ItemHolderInfo1.left && recyclerView$ItemAnimator$ItemHolderInfo0.top == recyclerView$ItemAnimator$ItemHolderInfo1.top ? this.animateAdd(recyclerView$ViewHolder0) : this.animateMove(recyclerView$ViewHolder0, recyclerView$ItemAnimator$ItemHolderInfo0.left, recyclerView$ItemAnimator$ItemHolderInfo0.top, recyclerView$ItemAnimator$ItemHolderInfo1.left, recyclerView$ItemAnimator$ItemHolderInfo1.top);
    }

    public abstract boolean animateChange(ViewHolder arg1, ViewHolder arg2, int arg3, int arg4, int arg5, int arg6);

    @Override  // androidx.recyclerview.widget.RecyclerView$ItemAnimator
    public boolean animateChange(ViewHolder recyclerView$ViewHolder0, ViewHolder recyclerView$ViewHolder1, ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo0, ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo1) {
        int v = recyclerView$ItemAnimator$ItemHolderInfo0.left;
        int v1 = recyclerView$ItemAnimator$ItemHolderInfo0.top;
        return recyclerView$ViewHolder1.shouldIgnore() ? this.animateChange(recyclerView$ViewHolder0, recyclerView$ViewHolder1, v, v1, recyclerView$ItemAnimator$ItemHolderInfo0.left, recyclerView$ItemAnimator$ItemHolderInfo0.top) : this.animateChange(recyclerView$ViewHolder0, recyclerView$ViewHolder1, v, v1, recyclerView$ItemAnimator$ItemHolderInfo1.left, recyclerView$ItemAnimator$ItemHolderInfo1.top);
    }

    @Override  // androidx.recyclerview.widget.RecyclerView$ItemAnimator
    public boolean animateDisappearance(ViewHolder recyclerView$ViewHolder0, ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo0, ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo1) {
        int v = recyclerView$ItemAnimator$ItemHolderInfo0.left;
        int v1 = recyclerView$ItemAnimator$ItemHolderInfo0.top;
        View view0 = recyclerView$ViewHolder0.itemView;
        int v2 = recyclerView$ItemAnimator$ItemHolderInfo1 == null ? view0.getLeft() : recyclerView$ItemAnimator$ItemHolderInfo1.left;
        int v3 = recyclerView$ItemAnimator$ItemHolderInfo1 == null ? view0.getTop() : recyclerView$ItemAnimator$ItemHolderInfo1.top;
        if(!recyclerView$ViewHolder0.isRemoved() && (v != v2 || v1 != v3)) {
            view0.layout(v2, v3, view0.getWidth() + v2, view0.getHeight() + v3);
            return this.animateMove(recyclerView$ViewHolder0, v, v1, v2, v3);
        }
        return this.animateRemove(recyclerView$ViewHolder0);
    }

    public abstract boolean animateMove(ViewHolder arg1, int arg2, int arg3, int arg4, int arg5);

    @Override  // androidx.recyclerview.widget.RecyclerView$ItemAnimator
    public boolean animatePersistence(ViewHolder recyclerView$ViewHolder0, ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo0, ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo1) {
        if(recyclerView$ItemAnimator$ItemHolderInfo0.left == recyclerView$ItemAnimator$ItemHolderInfo1.left && recyclerView$ItemAnimator$ItemHolderInfo0.top == recyclerView$ItemAnimator$ItemHolderInfo1.top) {
            this.dispatchMoveFinished(recyclerView$ViewHolder0);
            return false;
        }
        return this.animateMove(recyclerView$ViewHolder0, recyclerView$ItemAnimator$ItemHolderInfo0.left, recyclerView$ItemAnimator$ItemHolderInfo0.top, recyclerView$ItemAnimator$ItemHolderInfo1.left, recyclerView$ItemAnimator$ItemHolderInfo1.top);
    }

    public abstract boolean animateRemove(ViewHolder arg1);

    // 去混淆评级： 低(20)
    @Override  // androidx.recyclerview.widget.RecyclerView$ItemAnimator
    public boolean canReuseUpdatedViewHolder(ViewHolder recyclerView$ViewHolder0) {
        return !this.mSupportsChangeAnimations || recyclerView$ViewHolder0.isInvalid();
    }

    public final void dispatchAddFinished(ViewHolder recyclerView$ViewHolder0) {
        this.dispatchAnimationFinished(recyclerView$ViewHolder0);
    }

    public final void dispatchAddStarting(ViewHolder recyclerView$ViewHolder0) {
    }

    public final void dispatchChangeFinished(ViewHolder recyclerView$ViewHolder0, boolean z) {
        this.dispatchAnimationFinished(recyclerView$ViewHolder0);
    }

    public final void dispatchChangeStarting(ViewHolder recyclerView$ViewHolder0, boolean z) {
    }

    public final void dispatchMoveFinished(ViewHolder recyclerView$ViewHolder0) {
        this.dispatchAnimationFinished(recyclerView$ViewHolder0);
    }

    public final void dispatchMoveStarting(ViewHolder recyclerView$ViewHolder0) {
    }

    public final void dispatchRemoveFinished(ViewHolder recyclerView$ViewHolder0) {
        this.dispatchAnimationFinished(recyclerView$ViewHolder0);
    }

    public final void dispatchRemoveStarting(ViewHolder recyclerView$ViewHolder0) {
    }

    public boolean getSupportsChangeAnimations() {
        return this.mSupportsChangeAnimations;
    }

    public void onAddFinished(ViewHolder recyclerView$ViewHolder0) {
    }

    public void onAddStarting(ViewHolder recyclerView$ViewHolder0) {
    }

    public void onChangeFinished(ViewHolder recyclerView$ViewHolder0, boolean z) {
    }

    public void onChangeStarting(ViewHolder recyclerView$ViewHolder0, boolean z) {
    }

    public void onMoveFinished(ViewHolder recyclerView$ViewHolder0) {
    }

    public void onMoveStarting(ViewHolder recyclerView$ViewHolder0) {
    }

    public void onRemoveFinished(ViewHolder recyclerView$ViewHolder0) {
    }

    public void onRemoveStarting(ViewHolder recyclerView$ViewHolder0) {
    }

    public void setSupportsChangeAnimations(boolean z) {
        this.mSupportsChangeAnimations = z;
    }
}

