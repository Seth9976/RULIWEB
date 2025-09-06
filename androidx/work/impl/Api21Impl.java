package androidx.work.impl;

import android.content.Context;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000E\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006¨\u0006\u0007"}, d2 = {"Landroidx/work/impl/Api21Impl;", "", "()V", "getNoBackupFilesDir", "Ljava/io/File;", "context", "Landroid/content/Context;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class Api21Impl {
    public static final Api21Impl INSTANCE;

    static {
        Api21Impl.INSTANCE = new Api21Impl();
    }

    public final File getNoBackupFilesDir(Context context0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        File file0 = context0.getNoBackupFilesDir();
        Intrinsics.checkNotNullExpressionValue(file0, "context.noBackupFilesDir");
        return file0;
    }
}

