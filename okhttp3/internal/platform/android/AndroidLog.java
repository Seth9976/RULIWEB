package okhttp3.internal.platform.android;

import android.util.Log;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.OkHttpClient;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J/\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\n2\u0006\u0010\u000E\u001A\u00020\u00042\u0006\u0010\u000F\u001A\u00020\n2\b\u0010\u0010\u001A\u0004\u0018\u00010\u0011H\u0000¢\u0006\u0002\b\u0012J\u0006\u0010\u0013\u001A\u00020\fJ\u0018\u0010\u0014\u001A\u00020\f2\u0006\u0010\u0015\u001A\u00020\n2\u0006\u0010\u0016\u001A\u00020\nH\u0002J\u0010\u0010\u0017\u001A\u00020\n2\u0006\u0010\r\u001A\u00020\nH\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001A\u0010\b\u001A\u000E\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lokhttp3/internal/platform/android/AndroidLog;", "", "()V", "MAX_LOG_LENGTH", "", "configuredLoggers", "Ljava/util/concurrent/CopyOnWriteArraySet;", "Ljava/util/logging/Logger;", "knownLoggers", "", "", "androidLog", "", "loggerName", "logLevel", "message", "t", "", "androidLog$okhttp", "enable", "enableLogging", "logger", "tag", "loggerTag", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class AndroidLog {
    public static final AndroidLog INSTANCE = null;
    private static final int MAX_LOG_LENGTH = 4000;
    private static final CopyOnWriteArraySet configuredLoggers;
    private static final Map knownLoggers;

    static {
        AndroidLog.INSTANCE = new AndroidLog();
        AndroidLog.configuredLoggers = new CopyOnWriteArraySet();
        LinkedHashMap linkedHashMap0 = new LinkedHashMap();
        Package package0 = OkHttpClient.class.getPackage();
        String s = package0 == null ? null : package0.getName();
        if(s != null) {
            linkedHashMap0.put(s, "OkHttp");
        }
        Intrinsics.checkNotNullExpressionValue("okhttp3.OkHttpClient", "OkHttpClient::class.java.name");
        linkedHashMap0.put("okhttp3.OkHttpClient", "okhttp.OkHttpClient");
        Intrinsics.checkNotNullExpressionValue("okhttp3.internal.http2.Http2", "Http2::class.java.name");
        linkedHashMap0.put("okhttp3.internal.http2.Http2", "okhttp.Http2");
        Intrinsics.checkNotNullExpressionValue("okhttp3.internal.concurrent.TaskRunner", "TaskRunner::class.java.name");
        linkedHashMap0.put("okhttp3.internal.concurrent.TaskRunner", "okhttp.TaskRunner");
        linkedHashMap0.put("okhttp3.mockwebserver.MockWebServer", "okhttp.MockWebServer");
        AndroidLog.knownLoggers = MapsKt.toMap(linkedHashMap0);
    }

    public final void androidLog$okhttp(String s, int v, String s1, Throwable throwable0) {
        Intrinsics.checkNotNullParameter(s, "loggerName");
        Intrinsics.checkNotNullParameter(s1, "message");
        String s2 = this.loggerTag(s);
        if(Log.isLoggable(s2, v)) {
            if(throwable0 != null) {
                s1 = s1 + '\n' + Log.getStackTraceString(throwable0);
            }
            int v1 = s1.length();
            for(int v2 = 0; v2 < v1; v2 = v4 + 1) {
                int v3 = StringsKt.indexOf$default(s1, '\n', v2, false, 4, null);
                if(v3 == -1) {
                    v3 = v1;
                }
                while(true) {
                    int v4 = Math.min(v3, v2 + 4000);
                    String s3 = s1.substring(v2, v4);
                    Intrinsics.checkNotNullExpressionValue(s3, "this as java.lang.String…ing(startIndex, endIndex)");
                    Log.println(v, s2, s3);
                    if(v4 >= v3) {
                        break;
                    }
                    v2 = v4;
                }
            }
        }
    }

    public final void enable() {
        for(Object object0: AndroidLog.knownLoggers.entrySet()) {
            this.enableLogging(((String)((Map.Entry)object0).getKey()), ((String)((Map.Entry)object0).getValue()));
        }
    }

    private final void enableLogging(String s, String s1) {
        Level level0;
        Logger logger0 = Logger.getLogger(s);
        if(AndroidLog.configuredLoggers.add(logger0)) {
            logger0.setUseParentHandlers(false);
            if(Log.isLoggable(s1, 3)) {
                level0 = Level.FINE;
            }
            else {
                level0 = Log.isLoggable(s1, 4) ? Level.INFO : Level.WARNING;
            }
            logger0.setLevel(level0);
            logger0.addHandler(AndroidLogHandler.INSTANCE);
        }
    }

    private final String loggerTag(String s) {
        String s1 = (String)AndroidLog.knownLoggers.get(s);
        return s1 == null ? StringsKt.take(s, 23) : s1;
    }
}

