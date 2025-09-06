package androidx.transition;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import java.util.ArrayList;

class GhostViewHolder extends FrameLayout {
    static class Api21Impl {
        static float getZ(View view0) {
            return view0.getZ();
        }
    }

    private boolean mAttached;
    private ViewGroup mParent;

    GhostViewHolder(ViewGroup viewGroup0) {
        super(viewGroup0.getContext());
        this.setClipChildren(false);
        this.mParent = viewGroup0;
        viewGroup0.setTag(id.ghost_view_holder, this);
        this.mParent.getOverlay().add(this);
        this.mAttached = true;
    }

    void addGhostView(GhostViewPort ghostViewPort0) {
        ArrayList arrayList0 = new ArrayList();
        GhostViewHolder.getParents(ghostViewPort0.mView, arrayList0);
        int v = this.getInsertIndex(arrayList0);
        if(v >= 0 && v < this.getChildCount()) {
            this.addView(ghostViewPort0, v);
            return;
        }
        this.addView(ghostViewPort0);
    }

    static GhostViewHolder getHolder(ViewGroup viewGroup0) {
        return (GhostViewHolder)viewGroup0.getTag(id.ghost_view_holder);
    }

    private int getInsertIndex(ArrayList arrayList0) {
        ArrayList arrayList1 = new ArrayList();
        int v = this.getChildCount() - 1;
        int v1 = 0;
        while(v1 <= v) {
            int v2 = (v1 + v) / 2;
            GhostViewHolder.getParents(((GhostViewPort)this.getChildAt(v2)).mView, arrayList1);
            if(GhostViewHolder.isOnTop(arrayList0, arrayList1)) {
                v1 = v2 + 1;
            }
            else {
                v = v2 - 1;
            }
            arrayList1.clear();
        }
        return v1;
    }

    private static void getParents(View view0, ArrayList arrayList0) {
        ViewParent viewParent0 = view0.getParent();
        if(viewParent0 instanceof ViewGroup) {
            GhostViewHolder.getParents(((View)viewParent0), arrayList0);
        }
        arrayList0.add(view0);
    }

    private static boolean isOnTop(View view0, View view1) {
        ViewGroup viewGroup0 = (ViewGroup)view0.getParent();
        int v = viewGroup0.getChildCount();
        if(Api21Impl.getZ(view0) != Api21Impl.getZ(view1)) {
            return Api21Impl.getZ(view0) > Api21Impl.getZ(view1);
        }
        for(int v1 = 0; v1 < v; ++v1) {
            View view2 = viewGroup0.getChildAt(ViewGroupUtils.getChildDrawingOrder(viewGroup0, v1));
            if(view2 == view0) {
                return false;
            }
            if(view2 == view1) {
                return true;
            }
        }
        return true;
    }

    private static boolean isOnTop(ArrayList arrayList0, ArrayList arrayList1) {
        if(!arrayList0.isEmpty() && !arrayList1.isEmpty() && arrayList0.get(0) == arrayList1.get(0)) {
            int v = Math.min(arrayList0.size(), arrayList1.size());
            for(int v1 = 1; v1 < v; ++v1) {
                View view0 = (View)arrayList0.get(v1);
                View view1 = (View)arrayList1.get(v1);
                if(view0 != view1) {
                    return GhostViewHolder.isOnTop(view0, view1);
                }
            }
            return arrayList1.size() == v;
        }
        return true;
    }

    @Override  // android.view.ViewGroup
    public void onViewAdded(View view0) {
        if(!this.mAttached) {
            throw new IllegalStateException("This GhostViewHolder is detached!");
        }
        super.onViewAdded(view0);
    }

    @Override  // android.view.ViewGroup
    public void onViewRemoved(View view0) {
        super.onViewRemoved(view0);
        if(this.getChildCount() == 1 && this.getChildAt(0) == view0 || this.getChildCount() == 0) {
            this.mParent.setTag(id.ghost_view_holder, null);
            this.mParent.getOverlay().remove(this);
            this.mAttached = false;
        }
    }

    void popToOverlayTop() {
        if(!this.mAttached) {
            throw new IllegalStateException("This GhostViewHolder is detached!");
        }
        this.mParent.getOverlay().remove(this);
        this.mParent.getOverlay().add(this);
    }
}

