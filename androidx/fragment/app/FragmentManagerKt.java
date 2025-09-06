package androidx.fragment.app;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001A3\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001A\u00020\u00042\u0017\u0010\u0005\u001A\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\bH\u0086\bø\u0001\u0000\u001A3\u0010\t\u001A\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001A\u00020\u00042\u0017\u0010\u0005\u001A\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\bH\u0086\bø\u0001\u0000\u001A=\u0010\n\u001A\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u000B\u001A\u00020\u00042\b\b\u0002\u0010\u0003\u001A\u00020\u00042\u0017\u0010\u0005\u001A\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0006¢\u0006\u0002\b\bH\u0087\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\f"}, d2 = {"commit", "", "Landroidx/fragment/app/FragmentManager;", "allowStateLoss", "", "body", "Lkotlin/Function1;", "Landroidx/fragment/app/FragmentTransaction;", "Lkotlin/ExtensionFunctionType;", "commitNow", "transaction", "now", "fragment-ktx_release"}, k = 2, mv = {1, 4, 1})
public final class FragmentManagerKt {
    public static final void commit(FragmentManager fragmentManager0, boolean z, Function1 function10) {
        Intrinsics.checkNotNullParameter(fragmentManager0, "$this$commit");
        Intrinsics.checkNotNullParameter(function10, "body");
        FragmentTransaction fragmentTransaction0 = fragmentManager0.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransaction0, "beginTransaction()");
        function10.invoke(fragmentTransaction0);
        if(z) {
            fragmentTransaction0.commitAllowingStateLoss();
            return;
        }
        fragmentTransaction0.commit();
    }

    public static void commit$default(FragmentManager fragmentManager0, boolean z, Function1 function10, int v, Object object0) {
        if((v & 1) != 0) {
            z = false;
        }
        Intrinsics.checkNotNullParameter(fragmentManager0, "$this$commit");
        Intrinsics.checkNotNullParameter(function10, "body");
        FragmentTransaction fragmentTransaction0 = fragmentManager0.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransaction0, "beginTransaction()");
        function10.invoke(fragmentTransaction0);
        if(z) {
            fragmentTransaction0.commitAllowingStateLoss();
            return;
        }
        fragmentTransaction0.commit();
    }

    public static final void commitNow(FragmentManager fragmentManager0, boolean z, Function1 function10) {
        Intrinsics.checkNotNullParameter(fragmentManager0, "$this$commitNow");
        Intrinsics.checkNotNullParameter(function10, "body");
        FragmentTransaction fragmentTransaction0 = fragmentManager0.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransaction0, "beginTransaction()");
        function10.invoke(fragmentTransaction0);
        if(z) {
            fragmentTransaction0.commitNowAllowingStateLoss();
            return;
        }
        fragmentTransaction0.commitNow();
    }

    public static void commitNow$default(FragmentManager fragmentManager0, boolean z, Function1 function10, int v, Object object0) {
        if((v & 1) != 0) {
            z = false;
        }
        Intrinsics.checkNotNullParameter(fragmentManager0, "$this$commitNow");
        Intrinsics.checkNotNullParameter(function10, "body");
        FragmentTransaction fragmentTransaction0 = fragmentManager0.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransaction0, "beginTransaction()");
        function10.invoke(fragmentTransaction0);
        if(z) {
            fragmentTransaction0.commitNowAllowingStateLoss();
            return;
        }
        fragmentTransaction0.commitNow();
    }

    @Deprecated(message = "Use commit { .. } or commitNow { .. } extensions")
    public static final void transaction(FragmentManager fragmentManager0, boolean z, boolean z1, Function1 function10) {
        Intrinsics.checkNotNullParameter(fragmentManager0, "$this$transaction");
        Intrinsics.checkNotNullParameter(function10, "body");
        FragmentTransaction fragmentTransaction0 = fragmentManager0.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransaction0, "beginTransaction()");
        function10.invoke(fragmentTransaction0);
        if(z) {
            if(z1) {
                fragmentTransaction0.commitNowAllowingStateLoss();
                return;
            }
            fragmentTransaction0.commitNow();
            return;
        }
        if(z1) {
            fragmentTransaction0.commitAllowingStateLoss();
            return;
        }
        fragmentTransaction0.commit();
    }

    public static void transaction$default(FragmentManager fragmentManager0, boolean z, boolean z1, Function1 function10, int v, Object object0) {
        if((v & 1) != 0) {
            z = false;
        }
        if((v & 2) != 0) {
            z1 = false;
        }
        Intrinsics.checkNotNullParameter(fragmentManager0, "$this$transaction");
        Intrinsics.checkNotNullParameter(function10, "body");
        FragmentTransaction fragmentTransaction0 = fragmentManager0.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransaction0, "beginTransaction()");
        function10.invoke(fragmentTransaction0);
        if(z) {
            if(z1) {
                fragmentTransaction0.commitNowAllowingStateLoss();
                return;
            }
            fragmentTransaction0.commitNow();
            return;
        }
        if(z1) {
            fragmentTransaction0.commitAllowingStateLoss();
            return;
        }
        fragmentTransaction0.commit();
    }
}

