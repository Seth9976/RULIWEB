package androidx.fragment.app.strictmode;

import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001A\u00020\u0005¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/fragment/app/strictmode/WrongFragmentContainerViolation;", "Landroidx/fragment/app/strictmode/Violation;", "fragment", "Landroidx/fragment/app/Fragment;", "container", "Landroid/view/ViewGroup;", "(Landroidx/fragment/app/Fragment;Landroid/view/ViewGroup;)V", "getContainer", "()Landroid/view/ViewGroup;", "fragment_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class WrongFragmentContainerViolation extends Violation {
    private final ViewGroup container;

    public WrongFragmentContainerViolation(Fragment fragment0, ViewGroup viewGroup0) {
        Intrinsics.checkNotNullParameter(fragment0, "fragment");
        Intrinsics.checkNotNullParameter(viewGroup0, "container");
        super(fragment0, "Attempting to add fragment " + fragment0 + " to container " + viewGroup0 + " which is not a FragmentContainerView");
        this.container = viewGroup0;
    }

    public final ViewGroup getContainer() {
        return this.container;
    }
}

