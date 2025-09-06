package androidx.work;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001C\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\u001A\"\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00030\u0005H\u0080\bø\u0001\u0000\u001A*\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0006\u001A\u00020\u00072\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00030\u0005H\u0080\bø\u0001\u0000\u001A\"\u0010\b\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00030\u0005H\u0080\bø\u0001\u0000\u001A*\u0010\b\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0006\u001A\u00020\u00072\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00030\u0005H\u0080\bø\u0001\u0000\u001A\"\u0010\t\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00030\u0005H\u0080\bø\u0001\u0000\u001A*\u0010\t\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0006\u001A\u00020\u00072\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00030\u0005H\u0080\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\n"}, d2 = {"logd", "", "tag", "", "block", "Lkotlin/Function0;", "t", "", "loge", "logi", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class LoggerExtKt {
    public static final void logd(String s, Throwable throwable0, Function0 function00) {
        Intrinsics.checkNotNullParameter(s, "tag");
        Intrinsics.checkNotNullParameter(throwable0, "t");
        Intrinsics.checkNotNullParameter(function00, "block");
        Logger.get().debug(s, ((String)function00.invoke()), throwable0);
    }

    public static final void logd(String s, Function0 function00) {
        Intrinsics.checkNotNullParameter(s, "tag");
        Intrinsics.checkNotNullParameter(function00, "block");
        Logger.get().debug(s, ((String)function00.invoke()));
    }

    public static final void loge(String s, Throwable throwable0, Function0 function00) {
        Intrinsics.checkNotNullParameter(s, "tag");
        Intrinsics.checkNotNullParameter(throwable0, "t");
        Intrinsics.checkNotNullParameter(function00, "block");
        Logger.get().error(s, ((String)function00.invoke()), throwable0);
    }

    public static final void loge(String s, Function0 function00) {
        Intrinsics.checkNotNullParameter(s, "tag");
        Intrinsics.checkNotNullParameter(function00, "block");
        Logger.get().error(s, ((String)function00.invoke()));
    }

    public static final void logi(String s, Throwable throwable0, Function0 function00) {
        Intrinsics.checkNotNullParameter(s, "tag");
        Intrinsics.checkNotNullParameter(throwable0, "t");
        Intrinsics.checkNotNullParameter(function00, "block");
        Logger.get().info(s, ((String)function00.invoke()), throwable0);
    }

    public static final void logi(String s, Function0 function00) {
        Intrinsics.checkNotNullParameter(s, "tag");
        Intrinsics.checkNotNullParameter(function00, "block");
        Logger.get().info(s, ((String)function00.invoke()));
    }
}

