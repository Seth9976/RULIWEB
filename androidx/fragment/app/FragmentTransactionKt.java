package androidx.fragment.app;

import android.os.Bundle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A;\u0010\u0000\u001A\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00012\b\b\u0001\u0010\u0004\u001A\u00020\u00052\n\b\u0002\u0010\u0006\u001A\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001A\u0004\u0018\u00010\tH\u0086\b\u001A-\u0010\u0000\u001A\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00012\u0006\u0010\u0006\u001A\u00020\u00072\n\b\u0002\u0010\b\u001A\u0004\u0018\u00010\tH\u0086\b\u001A;\u0010\n\u001A\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00012\b\b\u0001\u0010\u0004\u001A\u00020\u00052\n\b\u0002\u0010\u0006\u001A\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001A\u0004\u0018\u00010\tH\u0086\b¨\u0006\u000B"}, d2 = {"add", "Landroidx/fragment/app/FragmentTransaction;", "F", "Landroidx/fragment/app/Fragment;", "containerViewId", "", "tag", "", "args", "Landroid/os/Bundle;", "replace", "fragment-ktx_release"}, k = 2, mv = {1, 4, 1})
public final class FragmentTransactionKt {
    public static final FragmentTransaction add(FragmentTransaction fragmentTransaction0, int v, String s, Bundle bundle0) {
        Intrinsics.checkNotNullParameter(fragmentTransaction0, "$this$add");
        Intrinsics.reifiedOperationMarker(4, "F");
        FragmentTransaction fragmentTransaction1 = fragmentTransaction0.add(v, Fragment.class, bundle0, s);
        Intrinsics.checkNotNullExpressionValue(fragmentTransaction1, "add(containerViewId, F::class.java, args, tag)");
        return fragmentTransaction1;
    }

    public static final FragmentTransaction add(FragmentTransaction fragmentTransaction0, String s, Bundle bundle0) {
        Intrinsics.checkNotNullParameter(fragmentTransaction0, "$this$add");
        Intrinsics.checkNotNullParameter(s, "tag");
        Intrinsics.reifiedOperationMarker(4, "F");
        FragmentTransaction fragmentTransaction1 = fragmentTransaction0.add(Fragment.class, bundle0, s);
        Intrinsics.checkNotNullExpressionValue(fragmentTransaction1, "add(F::class.java, args, tag)");
        return fragmentTransaction1;
    }

    public static FragmentTransaction add$default(FragmentTransaction fragmentTransaction0, int v, String s, Bundle bundle0, int v1, Object object0) {
        if((v1 & 2) != 0) {
            s = null;
        }
        if((v1 & 4) != 0) {
            bundle0 = null;
        }
        Intrinsics.checkNotNullParameter(fragmentTransaction0, "$this$add");
        Intrinsics.reifiedOperationMarker(4, "F");
        FragmentTransaction fragmentTransaction1 = fragmentTransaction0.add(v, Fragment.class, bundle0, s);
        Intrinsics.checkNotNullExpressionValue(fragmentTransaction1, "add(containerViewId, F::class.java, args, tag)");
        return fragmentTransaction1;
    }

    public static FragmentTransaction add$default(FragmentTransaction fragmentTransaction0, String s, Bundle bundle0, int v, Object object0) {
        if((v & 2) != 0) {
            bundle0 = null;
        }
        Intrinsics.checkNotNullParameter(fragmentTransaction0, "$this$add");
        Intrinsics.checkNotNullParameter(s, "tag");
        Intrinsics.reifiedOperationMarker(4, "F");
        FragmentTransaction fragmentTransaction1 = fragmentTransaction0.add(Fragment.class, bundle0, s);
        Intrinsics.checkNotNullExpressionValue(fragmentTransaction1, "add(F::class.java, args, tag)");
        return fragmentTransaction1;
    }

    public static final FragmentTransaction replace(FragmentTransaction fragmentTransaction0, int v, String s, Bundle bundle0) {
        Intrinsics.checkNotNullParameter(fragmentTransaction0, "$this$replace");
        Intrinsics.reifiedOperationMarker(4, "F");
        FragmentTransaction fragmentTransaction1 = fragmentTransaction0.replace(v, Fragment.class, bundle0, s);
        Intrinsics.checkNotNullExpressionValue(fragmentTransaction1, "replace(containerViewId, F::class.java, args, tag)");
        return fragmentTransaction1;
    }

    public static FragmentTransaction replace$default(FragmentTransaction fragmentTransaction0, int v, String s, Bundle bundle0, int v1, Object object0) {
        if((v1 & 2) != 0) {
            s = null;
        }
        if((v1 & 4) != 0) {
            bundle0 = null;
        }
        Intrinsics.checkNotNullParameter(fragmentTransaction0, "$this$replace");
        Intrinsics.reifiedOperationMarker(4, "F");
        FragmentTransaction fragmentTransaction1 = fragmentTransaction0.replace(v, Fragment.class, bundle0, s);
        Intrinsics.checkNotNullExpressionValue(fragmentTransaction1, "replace(containerViewId, F::class.java, args, tag)");
        return fragmentTransaction1;
    }
}

