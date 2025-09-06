package androidx.core.os;

import android.os.Bundle;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001A\u00020\u0004H\u0015J\b\u0010\u0006\u001A\u00020\u0007H\u0015J\b\u0010\b\u001A\u00020\u0000H\u0015J\u000E\u0010\t\u001A\u00020\u00002\u0006\u0010\n\u001A\u00020\u0007J\u000E\u0010\u000B\u001A\u00020\u00002\u0006\u0010\f\u001A\u00020\u0007J\u000E\u0010\r\u001A\u00020\u00002\u0006\u0010\u000E\u001A\u00020\u000FJ\u000E\u0010\u0010\u001A\u00020\u00002\u0006\u0010\u0011\u001A\u00020\u0012R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/core/os/HeapProfileRequestBuilder;", "Landroidx/core/os/ProfilingRequestBuilder;", "()V", "mParams", "Landroid/os/Bundle;", "getParams", "getProfilingType", "", "getThis", "setBufferSizeKb", "bufferSizeKb", "setDurationMs", "durationMs", "setSamplingIntervalBytes", "samplingIntervalBytes", "", "setTrackJavaAllocations", "traceJavaAllocations", "", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class HeapProfileRequestBuilder extends ProfilingRequestBuilder {
    private final Bundle mParams;

    public HeapProfileRequestBuilder() {
        this.mParams = new Bundle();
    }

    @Override  // androidx.core.os.ProfilingRequestBuilder
    protected Bundle getParams() {
        return this.mParams;
    }

    @Override  // androidx.core.os.ProfilingRequestBuilder
    protected int getProfilingType() {
        return 2;
    }

    protected HeapProfileRequestBuilder getThis() [...] // Inlined contents

    @Override  // androidx.core.os.ProfilingRequestBuilder
    public ProfilingRequestBuilder getThis() {
        return this;
    }

    public final HeapProfileRequestBuilder setBufferSizeKb(int v) {
        this.mParams.putInt("KEY_SIZE_KB", v);
        return this;
    }

    public final HeapProfileRequestBuilder setDurationMs(int v) {
        this.mParams.putInt("KEY_DURATION_MS", v);
        return this;
    }

    public final HeapProfileRequestBuilder setSamplingIntervalBytes(long v) {
        this.mParams.putLong("KEY_SAMPLING_INTERVAL_BYTES", v);
        return this;
    }

    public final HeapProfileRequestBuilder setTrackJavaAllocations(boolean z) {
        this.mParams.putBoolean("KEY_TRACK_JAVA_ALLOCATIONS", z);
        return this;
    }
}

