package androidx.activity;

import android.view.View;
import android.view.ViewParent;
import androidx.core.viewtree.ViewTree;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u001A\u0013\u0010\u0000\u001A\u0004\u0018\u00010\u0001*\u00020\u0002H\u0007¢\u0006\u0002\b\u0003\u001A\u0019\u0010\u0004\u001A\u00020\u0005*\u00020\u00022\u0006\u0010\u0006\u001A\u00020\u0001H\u0007¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"findViewTreeFullyDrawnReporterOwner", "Landroidx/activity/FullyDrawnReporterOwner;", "Landroid/view/View;", "get", "setViewTreeFullyDrawnReporterOwner", "", "fullyDrawnReporterOwner", "set", "activity_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ViewTreeFullyDrawnReporterOwner {
    public static final FullyDrawnReporterOwner get(View view0) {
        Intrinsics.checkNotNullParameter(view0, "<this>");
        while(view0 != null) {
            Object object0 = view0.getTag(id.report_drawn);
            FullyDrawnReporterOwner fullyDrawnReporterOwner0 = object0 instanceof FullyDrawnReporterOwner ? ((FullyDrawnReporterOwner)object0) : null;
            if(fullyDrawnReporterOwner0 != null) {
                return fullyDrawnReporterOwner0;
            }
            ViewParent viewParent0 = ViewTree.getParentOrViewTreeDisjointParent(view0);
            view0 = viewParent0 instanceof View ? ((View)viewParent0) : null;
        }
        return null;
    }

    public static final void set(View view0, FullyDrawnReporterOwner fullyDrawnReporterOwner0) {
        Intrinsics.checkNotNullParameter(view0, "<this>");
        Intrinsics.checkNotNullParameter(fullyDrawnReporterOwner0, "fullyDrawnReporterOwner");
        view0.setTag(id.report_drawn, fullyDrawnReporterOwner0);
    }
}

