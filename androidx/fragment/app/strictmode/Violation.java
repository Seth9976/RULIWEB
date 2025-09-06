package androidx.fragment.app.strictmode;

import androidx.fragment.app.Fragment;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0004\b&\u0018\u00002\u00060\u0001j\u0002`\u0002B\u001B\b\u0000\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001A\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Landroidx/fragment/app/strictmode/Violation;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "fragment", "Landroidx/fragment/app/Fragment;", "violationMessage", "", "(Landroidx/fragment/app/Fragment;Ljava/lang/String;)V", "getFragment", "()Landroidx/fragment/app/Fragment;", "fragment_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public abstract class Violation extends RuntimeException {
    private final Fragment fragment;

    public Violation(Fragment fragment0, String s) {
        Intrinsics.checkNotNullParameter(fragment0, "fragment");
        super(s);
        this.fragment = fragment0;
    }

    public Violation(Fragment fragment0, String s, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 2) != 0) {
            s = null;
        }
        this(fragment0, s);
    }

    public final Fragment getFragment() {
        return this.fragment;
    }
}

