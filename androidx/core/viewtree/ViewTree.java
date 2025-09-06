package androidx.core.viewtree;

import android.view.View;
import android.view.ViewParent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u001A\f\u0010\u0000\u001A\u0004\u0018\u00010\u0001*\u00020\u0002\u001A\u0014\u0010\u0003\u001A\u00020\u0004*\u00020\u00022\b\u0010\u0005\u001A\u0004\u0018\u00010\u0001¨\u0006\u0006"}, d2 = {"getParentOrViewTreeDisjointParent", "Landroid/view/ViewParent;", "Landroid/view/View;", "setViewTreeDisjointParent", "", "parent", "core-viewtree_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ViewTree {
    public static final ViewParent getParentOrViewTreeDisjointParent(View view0) {
        Intrinsics.checkNotNullParameter(view0, "<this>");
        ViewParent viewParent0 = view0.getParent();
        if(viewParent0 != null) {
            return viewParent0;
        }
        Object object0 = view0.getTag(id.view_tree_disjoint_parent);
        return object0 instanceof ViewParent ? ((ViewParent)object0) : null;
    }

    public static final void setViewTreeDisjointParent(View view0, ViewParent viewParent0) {
        Intrinsics.checkNotNullParameter(view0, "<this>");
        view0.setTag(id.view_tree_disjoint_parent, viewParent0);
    }
}

