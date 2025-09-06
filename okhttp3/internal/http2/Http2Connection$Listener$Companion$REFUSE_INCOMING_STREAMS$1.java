package okhttp3.internal.http2;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"okhttp3/internal/http2/Http2Connection$Listener$Companion$REFUSE_INCOMING_STREAMS$1", "Lokhttp3/internal/http2/Http2Connection$Listener;", "onStream", "", "stream", "Lokhttp3/internal/http2/Http2Stream;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class Http2Connection.Listener.Companion.REFUSE_INCOMING_STREAMS.1 extends Listener {
    @Override  // okhttp3.internal.http2.Http2Connection$Listener
    public void onStream(Http2Stream http2Stream0) throws IOException {
        Intrinsics.checkNotNullParameter(http2Stream0, "stream");
        http2Stream0.close(ErrorCode.REFUSED_STREAM, null);
    }
}

