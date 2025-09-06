package androidx.savedstate;

import android.view.View;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001A\u000E\u0010\u0000\u001A\u0004\u0018\u00010\u0001*\u00020\u0002H\u0007¨\u0006\u0003"}, d2 = {"findViewTreeSavedStateRegistryOwner", "Landroidx/savedstate/SavedStateRegistryOwner;", "Landroid/view/View;", "savedstate-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ViewKt {
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Replaced by View.findViewTreeSavedStateRegistryOwner() from savedstate module", replaceWith = @ReplaceWith(expression = "findViewTreeSavedStateRegistryOwner()", imports = {"androidx.savedstate.findViewTreeSavedStateRegistryOwner"}))
    public static final SavedStateRegistryOwner findViewTreeSavedStateRegistryOwner(View view0) {
        Intrinsics.checkNotNullParameter(view0, "<this>");
        return ViewTreeSavedStateRegistryOwner.get(view0);
    }
}

