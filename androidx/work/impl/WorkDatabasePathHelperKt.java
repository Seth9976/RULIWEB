package androidx.work.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000E\n\u0002\b\u0004\"\u0016\u0010\u0000\u001A\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0003\"\u000E\u0010\u0004\u001A\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000E\u0010\u0005\u001A\u00020\u0002X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"DATABASE_EXTRA_FILES", "", "", "[Ljava/lang/String;", "TAG", "WORK_DATABASE_NAME", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class WorkDatabasePathHelperKt {
    private static final String[] DATABASE_EXTRA_FILES = null;
    private static final String TAG = null;
    public static final String WORK_DATABASE_NAME = "androidx.work.workdb";

    static {
        Intrinsics.checkNotNullExpressionValue("WM-WrkDbPathHelper", "tagWithPrefix(\"WrkDbPathHelper\")");
        WorkDatabasePathHelperKt.TAG = "WM-WrkDbPathHelper";
        WorkDatabasePathHelperKt.DATABASE_EXTRA_FILES = new String[]{"-journal", "-shm", "-wal"};
    }

    // 去混淆评级： 低(20)
    public static final String access$getTAG$p() [...] // 潜在的解密器
}

