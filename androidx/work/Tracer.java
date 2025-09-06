package androidx.work;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0000\bg\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0007H&J\u0010\u0010\b\u001A\u00020\u00032\u0006\u0010\t\u001A\u00020\u0005H&J\u0018\u0010\n\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0007H&J\b\u0010\u000B\u001A\u00020\u0003H&J\b\u0010\f\u001A\u00020\rH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000EÀ\u0006\u0001"}, d2 = {"Landroidx/work/Tracer;", "", "beginAsyncSection", "", "methodName", "", "cookie", "", "beginSection", "label", "endAsyncSection", "endSection", "isEnabled", "", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface Tracer {
    void beginAsyncSection(String arg1, int arg2);

    void beginSection(String arg1);

    void endAsyncSection(String arg1, int arg2);

    void endSection();

    boolean isEnabled();
}

