package androidx.core.os;

import android.os.Bundle;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001A\u00020\u0004H\u0015J\b\u0010\u0006\u001A\u00020\u0007H\u0015J\b\u0010\b\u001A\u00020\u0000H\u0015J\u000E\u0010\t\u001A\u00020\u00002\u0006\u0010\n\u001A\u00020\u000BJ\u000E\u0010\f\u001A\u00020\u00002\u0006\u0010\r\u001A\u00020\u0007J\u000E\u0010\u000E\u001A\u00020\u00002\u0006\u0010\u000F\u001A\u00020\u0007R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/core/os/SystemTraceRequestBuilder;", "Landroidx/core/os/ProfilingRequestBuilder;", "()V", "mParams", "Landroid/os/Bundle;", "getParams", "getProfilingType", "", "getThis", "setBufferFillPolicy", "bufferFillPolicy", "Landroidx/core/os/BufferFillPolicy;", "setBufferSizeKb", "bufferSizeKb", "setDurationMs", "durationMs", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class SystemTraceRequestBuilder extends ProfilingRequestBuilder {
    private final Bundle mParams;

    public SystemTraceRequestBuilder() {
        this.mParams = new Bundle();
    }

    @Override  // androidx.core.os.ProfilingRequestBuilder
    protected Bundle getParams() {
        return this.mParams;
    }

    @Override  // androidx.core.os.ProfilingRequestBuilder
    protected int getProfilingType() {
        return 4;
    }

    @Override  // androidx.core.os.ProfilingRequestBuilder
    public ProfilingRequestBuilder getThis() {
        return this;
    }

    protected SystemTraceRequestBuilder getThis() [...] // Inlined contents

    public final SystemTraceRequestBuilder setBufferFillPolicy(BufferFillPolicy bufferFillPolicy0) {
        Intrinsics.checkNotNullParameter(bufferFillPolicy0, "bufferFillPolicy");
        int v = bufferFillPolicy0.getValue$core_release();
        this.mParams.putInt("KEY_BUFFER_FILL_POLICY", v);
        return this;
    }

    public final SystemTraceRequestBuilder setBufferSizeKb(int v) {
        this.mParams.putInt("KEY_SIZE_KB", v);
        return this;
    }

    public final SystemTraceRequestBuilder setDurationMs(int v) {
        this.mParams.putInt("KEY_DURATION_MS", v);
        return this;
    }
}

