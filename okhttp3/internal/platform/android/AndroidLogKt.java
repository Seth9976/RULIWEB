package okhttp3.internal.platform.android;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0018\u0010\u0000\u001A\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001A\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"androidLevel", "", "Ljava/util/logging/LogRecord;", "getAndroidLevel", "(Ljava/util/logging/LogRecord;)I", "okhttp"}, k = 2, mv = {1, 7, 1}, xi = 0x30)
public final class AndroidLogKt {
    public static final int access$getAndroidLevel(LogRecord logRecord0) {
        return AndroidLogKt.getAndroidLevel(logRecord0);
    }

    private static final int getAndroidLevel(LogRecord logRecord0) {
        if(logRecord0.getLevel().intValue() > Level.INFO.intValue()) {
            return 5;
        }
        return logRecord0.getLevel().intValue() == Level.INFO.intValue() ? 4 : 3;
    }
}

