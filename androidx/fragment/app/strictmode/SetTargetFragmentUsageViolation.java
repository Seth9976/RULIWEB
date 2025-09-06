package androidx.fragment.app.strictmode;

import androidx.fragment.app.Fragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001F\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0005\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000B¨\u0006\f"}, d2 = {"Landroidx/fragment/app/strictmode/SetTargetFragmentUsageViolation;", "Landroidx/fragment/app/strictmode/TargetFragmentUsageViolation;", "fragment", "Landroidx/fragment/app/Fragment;", "targetFragment", "requestCode", "", "(Landroidx/fragment/app/Fragment;Landroidx/fragment/app/Fragment;I)V", "getRequestCode", "()I", "getTargetFragment", "()Landroidx/fragment/app/Fragment;", "fragment_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class SetTargetFragmentUsageViolation extends TargetFragmentUsageViolation {
    private final int requestCode;
    private final Fragment targetFragment;

    public SetTargetFragmentUsageViolation(Fragment fragment0, Fragment fragment1, int v) {
        Intrinsics.checkNotNullParameter(fragment0, "fragment");
        Intrinsics.checkNotNullParameter(fragment1, "targetFragment");
        super(fragment0, "Attempting to set target fragment " + fragment1 + " with request code " + v + " for fragment " + fragment0);
        this.targetFragment = fragment1;
        this.requestCode = v;
    }

    public final int getRequestCode() {
        return this.requestCode;
    }

    public final Fragment getTargetFragment() {
        return this.targetFragment;
    }
}

