package androidx.fragment.app;

import android.os.Bundle;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001A\u0012\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u0004\u001A\u0012\u0010\u0005\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u0004\u001A\u001A\u0010\u0006\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0007\u001A\u00020\b\u001AJ\u0010\t\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u000426\u0010\n\u001A2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0003\u0012\u0013\u0012\u00110\b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000E\u0012\u0004\u0012\u00020\u00010\u000B¨\u0006\u000F"}, d2 = {"clearFragmentResult", "", "Landroidx/fragment/app/Fragment;", "requestKey", "", "clearFragmentResultListener", "setFragmentResult", "result", "Landroid/os/Bundle;", "setFragmentResultListener", "listener", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "bundle", "fragment-ktx_release"}, k = 2, mv = {1, 4, 1})
public final class FragmentKt {
    public static final void clearFragmentResult(Fragment fragment0, String s) {
        Intrinsics.checkNotNullParameter(fragment0, "$this$clearFragmentResult");
        Intrinsics.checkNotNullParameter(s, "requestKey");
        fragment0.getParentFragmentManager().clearFragmentResult(s);
    }

    public static final void clearFragmentResultListener(Fragment fragment0, String s) {
        Intrinsics.checkNotNullParameter(fragment0, "$this$clearFragmentResultListener");
        Intrinsics.checkNotNullParameter(s, "requestKey");
        fragment0.getParentFragmentManager().clearFragmentResultListener(s);
    }

    public static final void setFragmentResult(Fragment fragment0, String s, Bundle bundle0) {
        Intrinsics.checkNotNullParameter(fragment0, "$this$setFragmentResult");
        Intrinsics.checkNotNullParameter(s, "requestKey");
        Intrinsics.checkNotNullParameter(bundle0, "result");
        fragment0.getParentFragmentManager().setFragmentResult(s, bundle0);
    }

    public static final void setFragmentResultListener(Fragment fragment0, String s, Function2 function20) {
        Intrinsics.checkNotNullParameter(fragment0, "$this$setFragmentResultListener");
        Intrinsics.checkNotNullParameter(s, "requestKey");
        Intrinsics.checkNotNullParameter(function20, "listener");
        fragment0.getParentFragmentManager().setFragmentResultListener(s, fragment0, new FragmentKt.sam.androidx_fragment_app_FragmentResultListener.0(function20));
    }
}

