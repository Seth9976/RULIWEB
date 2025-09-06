package androidx.savedstate;

import android.view.View;
import android.view.ViewParent;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u001A\u0013\u0010\u0000\u001A\u0004\u0018\u00010\u0001*\u00020\u0002H\u0007¢\u0006\u0002\b\u0003\u001A\u001B\u0010\u0004\u001A\u00020\u0005*\u00020\u00022\b\u0010\u0006\u001A\u0004\u0018\u00010\u0001H\u0007¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"findViewTreeSavedStateRegistryOwner", "Landroidx/savedstate/SavedStateRegistryOwner;", "Landroid/view/View;", "get", "setViewTreeSavedStateRegistryOwner", "", "owner", "set", "savedstate_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ViewTreeSavedStateRegistryOwner {
    public static final SavedStateRegistryOwner get(View view0) {
        Intrinsics.checkNotNullParameter(view0, "<this>");
        return (SavedStateRegistryOwner)SequencesKt.firstOrNull(SequencesKt.mapNotNull(SequencesKt.generateSequence(view0, androidx.savedstate.ViewTreeSavedStateRegistryOwner.findViewTreeSavedStateRegistryOwner.1.INSTANCE), androidx.savedstate.ViewTreeSavedStateRegistryOwner.findViewTreeSavedStateRegistryOwner.2.INSTANCE));

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001A\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001A\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroid/view/View;", "view", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class androidx.savedstate.ViewTreeSavedStateRegistryOwner.findViewTreeSavedStateRegistryOwner.1 extends Lambda implements Function1 {
            public static final androidx.savedstate.ViewTreeSavedStateRegistryOwner.findViewTreeSavedStateRegistryOwner.1 INSTANCE;

            static {
                androidx.savedstate.ViewTreeSavedStateRegistryOwner.findViewTreeSavedStateRegistryOwner.1.INSTANCE = new androidx.savedstate.ViewTreeSavedStateRegistryOwner.findViewTreeSavedStateRegistryOwner.1();
            }

            androidx.savedstate.ViewTreeSavedStateRegistryOwner.findViewTreeSavedStateRegistryOwner.1() {
                super(1);
            }

            public final View invoke(View view0) {
                Intrinsics.checkNotNullParameter(view0, "view");
                ViewParent viewParent0 = view0.getParent();
                return viewParent0 instanceof View ? ((View)viewParent0) : null;
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((View)object0));
            }
        }


        @Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/savedstate/SavedStateRegistryOwner;", "view", "Landroid/view/View;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class androidx.savedstate.ViewTreeSavedStateRegistryOwner.findViewTreeSavedStateRegistryOwner.2 extends Lambda implements Function1 {
            public static final androidx.savedstate.ViewTreeSavedStateRegistryOwner.findViewTreeSavedStateRegistryOwner.2 INSTANCE;

            static {
                androidx.savedstate.ViewTreeSavedStateRegistryOwner.findViewTreeSavedStateRegistryOwner.2.INSTANCE = new androidx.savedstate.ViewTreeSavedStateRegistryOwner.findViewTreeSavedStateRegistryOwner.2();
            }

            androidx.savedstate.ViewTreeSavedStateRegistryOwner.findViewTreeSavedStateRegistryOwner.2() {
                super(1);
            }

            public final SavedStateRegistryOwner invoke(View view0) {
                Intrinsics.checkNotNullParameter(view0, "view");
                Object object0 = view0.getTag(id.view_tree_saved_state_registry_owner);
                return object0 instanceof SavedStateRegistryOwner ? ((SavedStateRegistryOwner)object0) : null;
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((View)object0));
            }
        }

    }

    public static final void set(View view0, SavedStateRegistryOwner savedStateRegistryOwner0) {
        Intrinsics.checkNotNullParameter(view0, "<this>");
        view0.setTag(id.view_tree_saved_state_registry_owner, savedStateRegistryOwner0);
    }
}

