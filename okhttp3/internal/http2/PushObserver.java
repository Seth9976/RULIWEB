package okhttp3.internal.http2;

import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSource;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014J(\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\u00052\u0006\u0010\t\u001A\u00020\u0003H&J&\u0010\n\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\f\u0010\u000B\u001A\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\t\u001A\u00020\u0003H&J\u001E\u0010\u000E\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u00020\r0\fH&J\u0018\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0012\u001A\u00020\u0013H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0015À\u0006\u0001"}, d2 = {"Lokhttp3/internal/http2/PushObserver;", "", "onData", "", "streamId", "", "source", "Lokio/BufferedSource;", "byteCount", "last", "onHeaders", "responseHeaders", "", "Lokhttp3/internal/http2/Header;", "onRequest", "requestHeaders", "onReset", "", "errorCode", "Lokhttp3/internal/http2/ErrorCode;", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public interface PushObserver {
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0001¨\u0006\u0006"}, d2 = {"Lokhttp3/internal/http2/PushObserver$Companion;", "", "()V", "CANCEL", "Lokhttp3/internal/http2/PushObserver;", "PushObserverCancel", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u00062\u0006\u0010\n\u001A\u00020\u0004H\u0016J&\u0010\u000B\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\f\u0010\f\u001A\b\u0012\u0004\u0012\u00020\u000E0\r2\u0006\u0010\n\u001A\u00020\u0004H\u0016J\u001E\u0010\u000F\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\f\u0010\u0010\u001A\b\u0012\u0004\u0012\u00020\u000E0\rH\u0016J\u0018\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0013\u001A\u00020\u0014H\u0016¨\u0006\u0015"}, d2 = {"Lokhttp3/internal/http2/PushObserver$Companion$PushObserverCancel;", "Lokhttp3/internal/http2/PushObserver;", "()V", "onData", "", "streamId", "", "source", "Lokio/BufferedSource;", "byteCount", "last", "onHeaders", "responseHeaders", "", "Lokhttp3/internal/http2/Header;", "onRequest", "requestHeaders", "onReset", "", "errorCode", "Lokhttp3/internal/http2/ErrorCode;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
        static final class PushObserverCancel implements PushObserver {
            @Override  // okhttp3.internal.http2.PushObserver
            public boolean onData(int v, BufferedSource bufferedSource0, int v1, boolean z) throws IOException {
                Intrinsics.checkNotNullParameter(bufferedSource0, "source");
                bufferedSource0.skip(((long)v1));
                return true;
            }

            @Override  // okhttp3.internal.http2.PushObserver
            public boolean onHeaders(int v, List list0, boolean z) {
                Intrinsics.checkNotNullParameter(list0, "responseHeaders");
                return true;
            }

            @Override  // okhttp3.internal.http2.PushObserver
            public boolean onRequest(int v, List list0) {
                Intrinsics.checkNotNullParameter(list0, "requestHeaders");
                return true;
            }

            @Override  // okhttp3.internal.http2.PushObserver
            public void onReset(int v, ErrorCode errorCode0) {
                Intrinsics.checkNotNullParameter(errorCode0, "errorCode");
            }
        }

        static final Companion $$INSTANCE;

        static {
            Companion.$$INSTANCE = new Companion();
        }
    }

    public static final PushObserver CANCEL;
    public static final Companion Companion;

    static {
        PushObserver.Companion = Companion.$$INSTANCE;
        PushObserver.CANCEL = new PushObserverCancel();
    }

    boolean onData(int arg1, BufferedSource arg2, int arg3, boolean arg4) throws IOException;

    boolean onHeaders(int arg1, List arg2, boolean arg3);

    boolean onRequest(int arg1, List arg2);

    void onReset(int arg1, ErrorCode arg2);
}

