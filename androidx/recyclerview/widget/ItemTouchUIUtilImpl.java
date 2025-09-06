package androidx.recyclerview.widget;

import android.graphics.Canvas;
import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.R.id;

class ItemTouchUIUtilImpl implements ItemTouchUIUtil {
    static final ItemTouchUIUtil INSTANCE;

    static {
        ItemTouchUIUtilImpl.INSTANCE = new ItemTouchUIUtilImpl();
    }

    @Override  // androidx.recyclerview.widget.ItemTouchUIUtil
    public void clearView(View view0) {
        Object object0 = view0.getTag(id.item_touch_helper_previous_elevation);
        if(object0 instanceof Float) {
            ViewCompat.setElevation(view0, ((float)(((Float)object0))));
        }
        view0.setTag(id.item_touch_helper_previous_elevation, null);
        view0.setTranslationX(0.0f);
        view0.setTranslationY(0.0f);
    }

    private static float findMaxElevation(RecyclerView recyclerView0, View view0) {
        int v = recyclerView0.getChildCount();
        float f = 0.0f;
        for(int v1 = 0; v1 < v; ++v1) {
            View view1 = recyclerView0.getChildAt(v1);
            if(view1 != view0) {
                float f1 = ViewCompat.getElevation(view1);
                if(f1 > f) {
                    f = f1;
                }
            }
        }
        return f;
    }

    @Override  // androidx.recyclerview.widget.ItemTouchUIUtil
    public void onDraw(Canvas canvas0, RecyclerView recyclerView0, View view0, float f, float f1, int v, boolean z) {
        if(z && view0.getTag(id.item_touch_helper_previous_elevation) == null) {
            Float float0 = ViewCompat.getElevation(view0);
            ViewCompat.setElevation(view0, ItemTouchUIUtilImpl.findMaxElevation(recyclerView0, view0) + 1.0f);
            view0.setTag(id.item_touch_helper_previous_elevation, float0);
        }
        view0.setTranslationX(f);
        view0.setTranslationY(f1);
    }

    @Override  // androidx.recyclerview.widget.ItemTouchUIUtil
    public void onDrawOver(Canvas canvas0, RecyclerView recyclerView0, View view0, float f, float f1, int v, boolean z) {
    }

    @Override  // androidx.recyclerview.widget.ItemTouchUIUtil
    public void onSelected(View view0) {
    }
}

