package androidx.core.os;

import android.os.Bundle;
import android.os.CancellationSignal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\'\u0018\u0000*\u000E\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00002\u00020\u0002B\u0007\b\u0000¢\u0006\u0002\u0010\u0003J\u0006\u0010\b\u001A\u00020\tJ\b\u0010\n\u001A\u00020\u000BH%J\b\u0010\f\u001A\u00020\rH%J\r\u0010\u000E\u001A\u00028\u0000H%¢\u0006\u0002\u0010\u000FJ\u0013\u0010\u0010\u001A\u00028\u00002\u0006\u0010\u0011\u001A\u00020\u0005¢\u0006\u0002\u0010\u0012J\u0013\u0010\u0013\u001A\u00028\u00002\u0006\u0010\u0014\u001A\u00020\u0007¢\u0006\u0002\u0010\u0015R\u0010\u0010\u0004\u001A\u0004\u0018\u00010\u0005X\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001A\u0004\u0018\u00010\u0007X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Landroidx/core/os/ProfilingRequestBuilder;", "T", "", "()V", "mCancellationSignal", "Landroid/os/CancellationSignal;", "mTag", "", "build", "Landroidx/core/os/ProfilingRequest;", "getParams", "Landroid/os/Bundle;", "getProfilingType", "", "getThis", "()Landroidx/core/os/ProfilingRequestBuilder;", "setCancellationSignal", "cancellationSignal", "(Landroid/os/CancellationSignal;)Landroidx/core/os/ProfilingRequestBuilder;", "setTag", "tag", "(Ljava/lang/String;)Landroidx/core/os/ProfilingRequestBuilder;", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class ProfilingRequestBuilder {
    private CancellationSignal mCancellationSignal;
    private String mTag;

    public final ProfilingRequest build() {
        return new ProfilingRequest(this.getProfilingType(), this.getParams(), this.mTag, this.mCancellationSignal);
    }

    protected abstract Bundle getParams();

    protected abstract int getProfilingType();

    protected abstract ProfilingRequestBuilder getThis();

    public final ProfilingRequestBuilder setCancellationSignal(CancellationSignal cancellationSignal0) {
        Intrinsics.checkNotNullParameter(cancellationSignal0, "cancellationSignal");
        this.mCancellationSignal = cancellationSignal0;
        return this.getThis();
    }

    public final ProfilingRequestBuilder setTag(String s) {
        Intrinsics.checkNotNullParameter(s, "tag");
        this.mTag = s;
        return this.getThis();
    }
}

