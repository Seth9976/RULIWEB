package okhttp3.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Cache;
import okhttp3.Dispatcher;
import okhttp3.Response;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealCall.AsyncCall;
import okhttp3.internal.connection.RealConnection;
import okio.FileSystem;
import okio.Path;

@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001A\u001E\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u00132\u0006\u0010\u0014\u001A\u00020\n2\u0006\u0010\u0015\u001A\u00020\u0016\u001A\u0016\u0010\u0017\u001A\u00020\u0018*\u00020\u00192\n\u0010\u001A\u001A\u00060\u001BR\u00020\u001C\"\u0015\u0010\u0000\u001A\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001A\u0004\b\u0003\u0010\u0004\"\u0017\u0010\u0005\u001A\u0004\u0018\u00010\u0002*\u00020\u00068F¢\u0006\u0006\u001A\u0004\b\u0007\u0010\b\"(\u0010\u000B\u001A\u00020\n*\u00020\u00012\u0006\u0010\t\u001A\u00020\n8F@FX\u0086\u000E¢\u0006\f\u001A\u0004\b\f\u0010\r\"\u0004\b\u000E\u0010\u000F¨\u0006\u001D"}, d2 = {"connectionAccessor", "Lokhttp3/internal/connection/RealConnection;", "Lokhttp3/internal/connection/Exchange;", "getConnectionAccessor", "(Lokhttp3/internal/connection/Exchange;)Lokhttp3/internal/connection/RealConnection;", "exchangeAccessor", "Lokhttp3/Response;", "getExchangeAccessor", "(Lokhttp3/Response;)Lokhttp3/internal/connection/Exchange;", "value", "", "idleAtNsAccessor", "getIdleAtNsAccessor", "(Lokhttp3/internal/connection/RealConnection;)J", "setIdleAtNsAccessor", "(Lokhttp3/internal/connection/RealConnection;J)V", "buildCache", "Lokhttp3/Cache;", "file", "Lokio/Path;", "maxSize", "fileSystem", "Lokio/FileSystem;", "finishedAccessor", "", "Lokhttp3/Dispatcher;", "call", "Lokhttp3/internal/connection/RealCall$AsyncCall;", "Lokhttp3/internal/connection/RealCall;", "okhttp"}, k = 2, mv = {1, 7, 1}, xi = 0x30)
public final class NativeImageTestsAccessorsKt {
    public static final Cache buildCache(Path path0, long v, FileSystem fileSystem0) {
        Intrinsics.checkNotNullParameter(path0, "file");
        Intrinsics.checkNotNullParameter(fileSystem0, "fileSystem");
        return new Cache(path0, v, fileSystem0);
    }

    public static final void finishedAccessor(Dispatcher dispatcher0, AsyncCall realCall$AsyncCall0) {
        Intrinsics.checkNotNullParameter(dispatcher0, "<this>");
        Intrinsics.checkNotNullParameter(realCall$AsyncCall0, "call");
        dispatcher0.finished$okhttp(realCall$AsyncCall0);
    }

    public static final RealConnection getConnectionAccessor(Exchange exchange0) {
        Intrinsics.checkNotNullParameter(exchange0, "<this>");
        return exchange0.getConnection$okhttp();
    }

    public static final Exchange getExchangeAccessor(Response response0) {
        Intrinsics.checkNotNullParameter(response0, "<this>");
        return response0.exchange();
    }

    public static final long getIdleAtNsAccessor(RealConnection realConnection0) {
        Intrinsics.checkNotNullParameter(realConnection0, "<this>");
        return realConnection0.getIdleAtNs();
    }

    public static final void setIdleAtNsAccessor(RealConnection realConnection0, long v) {
        Intrinsics.checkNotNullParameter(realConnection0, "<this>");
        realConnection0.setIdleAtNs(v);
    }
}

