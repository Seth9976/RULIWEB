package okhttp3.internal;

import java.util.concurrent.ThreadFactory;

public final class _UtilJvmKt..ExternalSyntheticLambda1 implements ThreadFactory {
    public final String f$0;
    public final boolean f$1;

    public _UtilJvmKt..ExternalSyntheticLambda1(String s, boolean z) {
        this.f$0 = s;
        this.f$1 = z;
    }

    @Override
    public final Thread newThread(Runnable runnable0) {
        return _UtilJvmKt.$r8$lambda$spKDnWFkDT_DsdezkqD7R0-8_OE(this.f$0, this.f$1, runnable0);
    }
}

