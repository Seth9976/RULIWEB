package androidx.activity;

import android.view.View;
import android.view.ViewParent;
import androidx.core.viewtree.ViewTree;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u001A\u0013\u0010\u0000\u001A\u0004\u0018\u00010\u0001*\u00020\u0002H\u0007¢\u0006\u0002\b\u0003\u001A\u0019\u0010\u0004\u001A\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001A\u00020\u0001H\u0007¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"findViewTreeOnBackPressedDispatcherOwner", "Landroidx/activity/OnBackPressedDispatcherOwner;", "Landroid/view/View;", "get", "setViewTreeOnBackPressedDispatcherOwner", "", "onBackPressedDispatcherOwner", "set", "activity_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ViewTreeOnBackPressedDispatcherOwner {
    public static final OnBackPressedDispatcherOwner get(View view0) {
        Intrinsics.checkNotNullParameter(view0, "<this>");
        while(view0 != null) {
            Object object0 = view0.getTag(id.view_tree_on_back_pressed_dispatcher_owner);
            OnBackPressedDispatcherOwner onBackPressedDispatcherOwner0 = object0 instanceof OnBackPressedDispatcherOwner ? ((OnBackPressedDispatcherOwner)object0) : null;
            if(onBackPressedDispatcherOwner0 != null) {
                return onBackPressedDispatcherOwner0;
            }
            ViewParent viewParent0 = ViewTree.getParentOrViewTreeDisjointParent(view0);
            view0 = viewParent0 instanceof View ? ((View)viewParent0) : null;
        }
        return null;
    }

    public static final void set(View view0, OnBackPressedDispatcherOwner onBackPressedDispatcherOwner0) {
        Intrinsics.checkNotNullParameter(view0, "<this>");
        Intrinsics.checkNotNullParameter(onBackPressedDispatcherOwner0, "onBackPressedDispatcherOwner");
        view0.setTag(id.view_tree_on_back_pressed_dispatcher_owner, onBackPressedDispatcherOwner0);
    }
}

