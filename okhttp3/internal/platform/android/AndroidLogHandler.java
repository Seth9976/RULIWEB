package okhttp3.internal.platform.android;

import java.util.logging.Handler;
import java.util.logging.LogRecord;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001A\u00020\u0004H\u0016J\b\u0010\u0005\u001A\u00020\u0004H\u0016J\u0010\u0010\u0006\u001A\u00020\u00042\u0006\u0010\u0007\u001A\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lokhttp3/internal/platform/android/AndroidLogHandler;", "Ljava/util/logging/Handler;", "()V", "close", "", "flush", "publish", "record", "Ljava/util/logging/LogRecord;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class AndroidLogHandler extends Handler {
    public static final AndroidLogHandler INSTANCE;

    static {
        AndroidLogHandler.INSTANCE = new AndroidLogHandler();
    }

    @Override
    public void close() {
    }

    @Override
    public void flush() {
    }

    @Override
    public void publish(LogRecord logRecord0) {
        Intrinsics.checkNotNullParameter(logRecord0, "record");
        String s = logRecord0.getLoggerName();
        Intrinsics.checkNotNullExpressionValue(s, "record.loggerName");
        int v = AndroidLogKt.access$getAndroidLevel(logRecord0);
        String s1 = logRecord0.getMessage();
        Intrinsics.checkNotNullExpressionValue(s1, "record.message");
        Throwable throwable0 = logRecord0.getThrown();
        AndroidLog.INSTANCE.androidLog$okhttp(s, v, s1, throwable0);
    }
}

