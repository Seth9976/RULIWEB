package androidx.work.impl.utils;

import android.app.Application;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001A\u00020\u00048F¢\u0006\u0006\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/work/impl/utils/Api28Impl;", "", "()V", "processName", "", "getProcessName", "()Ljava/lang/String;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class Api28Impl {
    public static final Api28Impl INSTANCE;

    static {
        Api28Impl.INSTANCE = new Api28Impl();
    }

    public final String getProcessName() {
        String s = Application.getProcessName();
        Intrinsics.checkNotNullExpressionValue(s, "getProcessName()");
        return s;
    }
}

