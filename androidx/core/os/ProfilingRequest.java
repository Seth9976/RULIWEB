package androidx.core.os;

import android.os.Bundle;
import android.os.CancellationSignal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B+\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\b\u0010\u0006\u001A\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001A\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nR\u0013\u0010\b\u001A\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR\u0011\u0010\u0004\u001A\u00020\u0005¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u000ER\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u000F\u0010\u0010R\u0013\u0010\u0006\u001A\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Landroidx/core/os/ProfilingRequest;", "", "profilingType", "", "params", "Landroid/os/Bundle;", "tag", "", "cancellationSignal", "Landroid/os/CancellationSignal;", "(ILandroid/os/Bundle;Ljava/lang/String;Landroid/os/CancellationSignal;)V", "getCancellationSignal", "()Landroid/os/CancellationSignal;", "getParams", "()Landroid/os/Bundle;", "getProfilingType", "()I", "getTag", "()Ljava/lang/String;", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class ProfilingRequest {
    private final CancellationSignal cancellationSignal;
    private final Bundle params;
    private final int profilingType;
    private final String tag;

    public ProfilingRequest(int v, Bundle bundle0, String s, CancellationSignal cancellationSignal0) {
        Intrinsics.checkNotNullParameter(bundle0, "params");
        super();
        this.profilingType = v;
        this.params = bundle0;
        this.tag = s;
        this.cancellationSignal = cancellationSignal0;
    }

    public final CancellationSignal getCancellationSignal() {
        return this.cancellationSignal;
    }

    public final Bundle getParams() {
        return this.params;
    }

    public final int getProfilingType() {
        return this.profilingType;
    }

    public final String getTag() {
        return this.tag;
    }
}

