package okhttp3.internal.http;

import java.io.IOException;
import kotlin.Metadata;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response.Builder;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal.connection.RealCall;
import okio.Sink;
import okio.Source;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000 \u001D2\u00020\u0001:\u0002\u001C\u001DJ\b\u0010\u0006\u001A\u00020\u0007H&J\u0018\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\rH&J\b\u0010\u000E\u001A\u00020\u0007H&J\b\u0010\u000F\u001A\u00020\u0007H&J\u0010\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u0013H&J\u0012\u0010\u0014\u001A\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001A\u00020\u0017H&J\u0010\u0010\u0018\u001A\u00020\r2\u0006\u0010\u0012\u001A\u00020\u0013H&J\b\u0010\u0019\u001A\u00020\u001AH&J\u0010\u0010\u001B\u001A\u00020\u00072\u0006\u0010\n\u001A\u00020\u000BH&R\u0012\u0010\u0002\u001A\u00020\u0003X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001EÀ\u0006\u0001"}, d2 = {"Lokhttp3/internal/http/ExchangeCodec;", "", "carrier", "Lokhttp3/internal/http/ExchangeCodec$Carrier;", "getCarrier", "()Lokhttp3/internal/http/ExchangeCodec$Carrier;", "cancel", "", "createRequestBody", "Lokio/Sink;", "request", "Lokhttp3/Request;", "contentLength", "", "finishRequest", "flushRequest", "openResponseBodySource", "Lokio/Source;", "response", "Lokhttp3/Response;", "readResponseHeaders", "Lokhttp3/Response$Builder;", "expectContinue", "", "reportedContentLength", "trailers", "Lokhttp3/Headers;", "writeRequestHeaders", "Carrier", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public interface ExchangeCodec {
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0006\u001A\u00020\u0007H&J\b\u0010\b\u001A\u00020\u0007H&J\u001A\u0010\t\u001A\u00020\u00072\u0006\u0010\n\u001A\u00020\u000B2\b\u0010\f\u001A\u0004\u0018\u00010\rH&R\u0012\u0010\u0002\u001A\u00020\u0003X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000EÀ\u0006\u0001"}, d2 = {"Lokhttp3/internal/http/ExchangeCodec$Carrier;", "", "route", "Lokhttp3/Route;", "getRoute", "()Lokhttp3/Route;", "cancel", "", "noNewExchanges", "trackFailure", "call", "Lokhttp3/internal/connection/RealCall;", "e", "Ljava/io/IOException;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public interface Carrier {
        void cancel();

        Route getRoute();

        void noNewExchanges();

        void trackFailure(RealCall arg1, IOException arg2);
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lokhttp3/internal/http/ExchangeCodec$Companion;", "", "()V", "DISCARD_STREAM_TIMEOUT_MILLIS", "", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        static final Companion $$INSTANCE = null;
        public static final int DISCARD_STREAM_TIMEOUT_MILLIS = 100;

        static {
            Companion.$$INSTANCE = new Companion();
        }
    }

    public static final Companion Companion = null;
    public static final int DISCARD_STREAM_TIMEOUT_MILLIS = 100;

    static {
        ExchangeCodec.Companion = Companion.$$INSTANCE;
    }

    void cancel();

    Sink createRequestBody(Request arg1, long arg2) throws IOException;

    void finishRequest() throws IOException;

    void flushRequest() throws IOException;

    Carrier getCarrier();

    Source openResponseBodySource(Response arg1) throws IOException;

    Builder readResponseHeaders(boolean arg1) throws IOException;

    long reportedContentLength(Response arg1) throws IOException;

    Headers trailers() throws IOException;

    void writeRequestHeaders(Request arg1) throws IOException;
}

