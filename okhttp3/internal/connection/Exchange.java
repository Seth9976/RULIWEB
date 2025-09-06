package okhttp3.internal.connection;

import java.io.IOException;
import java.net.ProtocolException;
import java.net.SocketException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.EventListener;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response.Builder;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.ExchangeCodec.Carrier;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.RealResponseBody;
import okhttp3.internal.ws.RealWebSocket.Streams;
import okio.Buffer;
import okio.ForwardingSink;
import okio.ForwardingSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000B\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0002CDB%\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u00A2\u0006\u0002\u0010\nJ7\u0010\u001E\u001A\u0002H\u001F\"\n\b\u0000\u0010\u001F*\u0004\u0018\u00010 2\u0006\u0010!\u001A\u00020\"2\u0006\u0010#\u001A\u00020\u00162\u0006\u0010$\u001A\u00020\u00162\u0006\u0010%\u001A\u0002H\u001F\u00A2\u0006\u0002\u0010&J\u0006\u0010\'\u001A\u00020(J\u0016\u0010)\u001A\u00020*2\u0006\u0010+\u001A\u00020,2\u0006\u0010-\u001A\u00020\u0016J\u0006\u0010.\u001A\u00020(J\u0006\u0010/\u001A\u00020(J\u0006\u00100\u001A\u00020(J\u0006\u00101\u001A\u000202J\u0006\u00103\u001A\u00020(J\u0006\u00104\u001A\u00020(J\u000E\u00105\u001A\u0002062\u0006\u00107\u001A\u000208J\u0010\u00109\u001A\u0004\u0018\u00010:2\u0006\u0010;\u001A\u00020\u0016J\u000E\u0010<\u001A\u00020(2\u0006\u00107\u001A\u000208J\u0006\u0010=\u001A\u00020(J\u0010\u0010>\u001A\u00020(2\u0006\u0010%\u001A\u00020 H\u0002J\u0006\u0010?\u001A\u00020@J\u0006\u0010A\u001A\u00020(J\u000E\u0010B\u001A\u00020(2\u0006\u0010+\u001A\u00020,R\u0014\u0010\u0002\u001A\u00020\u0003X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR\u000E\u0010\b\u001A\u00020\tX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0014\u0010\r\u001A\u00020\u000E8@X\u0080\u0004\u00A2\u0006\u0006\u001A\u0004\b\u000F\u0010\u0010R\u0014\u0010\u0004\u001A\u00020\u0005X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0006\u001A\u00020\u0007X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\u0014R\u001E\u0010\u0017\u001A\u00020\u00162\u0006\u0010\u0015\u001A\u00020\u0016@BX\u0080\u000E\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001A\u001A\u00020\u00168@X\u0080\u0004\u00A2\u0006\u0006\u001A\u0004\b\u001B\u0010\u0019R\u001E\u0010\u001C\u001A\u00020\u00162\u0006\u0010\u0015\u001A\u00020\u0016@BX\u0080\u000E\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001D\u0010\u0019\u00A8\u0006E"}, d2 = {"Lokhttp3/internal/connection/Exchange;", "", "call", "Lokhttp3/internal/connection/RealCall;", "eventListener", "Lokhttp3/EventListener;", "finder", "Lokhttp3/internal/connection/ExchangeFinder;", "codec", "Lokhttp3/internal/http/ExchangeCodec;", "(Lokhttp3/internal/connection/RealCall;Lokhttp3/EventListener;Lokhttp3/internal/connection/ExchangeFinder;Lokhttp3/internal/http/ExchangeCodec;)V", "getCall$okhttp", "()Lokhttp3/internal/connection/RealCall;", "connection", "Lokhttp3/internal/connection/RealConnection;", "getConnection$okhttp", "()Lokhttp3/internal/connection/RealConnection;", "getEventListener$okhttp", "()Lokhttp3/EventListener;", "getFinder$okhttp", "()Lokhttp3/internal/connection/ExchangeFinder;", "<set-?>", "", "hasFailure", "getHasFailure$okhttp", "()Z", "isCoalescedConnection", "isCoalescedConnection$okhttp", "isDuplex", "isDuplex$okhttp", "bodyComplete", "E", "Ljava/io/IOException;", "bytesRead", "", "responseDone", "requestDone", "e", "(JZZLjava/io/IOException;)Ljava/io/IOException;", "cancel", "", "createRequestBody", "Lokio/Sink;", "request", "Lokhttp3/Request;", "duplex", "detachWithViolence", "finishRequest", "flushRequest", "newWebSocketStreams", "Lokhttp3/internal/ws/RealWebSocket$Streams;", "noNewExchangesOnConnection", "noRequestBody", "openResponseBody", "Lokhttp3/ResponseBody;", "response", "Lokhttp3/Response;", "readResponseHeaders", "Lokhttp3/Response$Builder;", "expectContinue", "responseHeadersEnd", "responseHeadersStart", "trackFailure", "trailers", "Lokhttp3/Headers;", "webSocketUpgradeFailed", "writeRequestHeaders", "RequestBodySink", "ResponseBodySource", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class Exchange {
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u000B\u001A\u00020\fH\u0016J!\u0010\r\u001A\u0002H\u000E\"\n\b\u0000\u0010\u000E*\u0004\u0018\u00010\u000F2\u0006\u0010\u0010\u001A\u0002H\u000EH\u0002¢\u0006\u0002\u0010\u0011J\b\u0010\u0012\u001A\u00020\fH\u0016J\u0018\u0010\u0013\u001A\u00020\f2\u0006\u0010\u0014\u001A\u00020\u00152\u0006\u0010\u0016\u001A\u00020\u0005H\u0016R\u000E\u0010\u0007\u001A\u00020\u0005X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\tX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\tX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lokhttp3/internal/connection/Exchange$RequestBodySink;", "Lokio/ForwardingSink;", "delegate", "Lokio/Sink;", "contentLength", "", "(Lokhttp3/internal/connection/Exchange;Lokio/Sink;J)V", "bytesReceived", "closed", "", "completed", "close", "", "complete", "E", "Ljava/io/IOException;", "e", "(Ljava/io/IOException;)Ljava/io/IOException;", "flush", "write", "source", "Lokio/Buffer;", "byteCount", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    final class RequestBodySink extends ForwardingSink {
        private long bytesReceived;
        private boolean closed;
        private boolean completed;
        private final long contentLength;

        public RequestBodySink(Sink sink0, long v) {
            Intrinsics.checkNotNullParameter(sink0, "delegate");
            Exchange.this = exchange0;
            super(sink0);
            this.contentLength = v;
        }

        @Override  // okio.ForwardingSink
        public void close() throws IOException {
            if(this.closed) {
                return;
            }
            this.closed = true;
            if(this.contentLength != -1L && this.bytesReceived != this.contentLength) {
                throw new ProtocolException("unexpected end of stream");
            }
            try {
                super.close();
                this.complete(null);
            }
            catch(IOException iOException0) {
                throw this.complete(iOException0);
            }
        }

        private final IOException complete(IOException iOException0) {
            if(this.completed) {
                return iOException0;
            }
            this.completed = true;
            return Exchange.this.bodyComplete(this.bytesReceived, false, true, iOException0);
        }

        @Override  // okio.ForwardingSink
        public void flush() throws IOException {
            try {
                super.flush();
            }
            catch(IOException iOException0) {
                throw this.complete(iOException0);
            }
        }

        @Override  // okio.ForwardingSink
        public void write(Buffer buffer0, long v) throws IOException {
            Intrinsics.checkNotNullParameter(buffer0, "source");
            if(!this.closed) {
                if(this.contentLength != -1L && this.bytesReceived + v > this.contentLength) {
                    throw new ProtocolException("expected " + this.contentLength + " bytes but received " + (this.bytesReceived + v));
                }
                try {
                    super.write(buffer0, v);
                    this.bytesReceived += v;
                    return;
                }
                catch(IOException iOException0) {
                    throw this.complete(iOException0);
                }
            }
            throw new IllegalStateException("closed");
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\f\u001A\u00020\rH\u0016J\u001F\u0010\u000E\u001A\u0002H\u000F\"\n\b\u0000\u0010\u000F*\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001A\u0002H\u000F¢\u0006\u0002\u0010\u0012J\u0018\u0010\u0013\u001A\u00020\u00052\u0006\u0010\u0014\u001A\u00020\u00152\u0006\u0010\u0016\u001A\u00020\u0005H\u0016R\u000E\u0010\u0007\u001A\u00020\u0005X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\tX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\tX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\tX\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lokhttp3/internal/connection/Exchange$ResponseBodySource;", "Lokio/ForwardingSource;", "delegate", "Lokio/Source;", "contentLength", "", "(Lokhttp3/internal/connection/Exchange;Lokio/Source;J)V", "bytesReceived", "closed", "", "completed", "invokeStartEvent", "close", "", "complete", "E", "Ljava/io/IOException;", "e", "(Ljava/io/IOException;)Ljava/io/IOException;", "read", "sink", "Lokio/Buffer;", "byteCount", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public final class ResponseBodySource extends ForwardingSource {
        private long bytesReceived;
        private boolean closed;
        private boolean completed;
        private final long contentLength;
        private boolean invokeStartEvent;

        public ResponseBodySource(Source source0, long v) {
            Intrinsics.checkNotNullParameter(source0, "delegate");
            Exchange.this = exchange0;
            super(source0);
            this.contentLength = v;
            this.invokeStartEvent = true;
            if(v == 0L) {
                this.complete(null);
            }
        }

        @Override  // okio.ForwardingSource
        public void close() throws IOException {
            if(this.closed) {
                return;
            }
            try {
                this.closed = true;
                super.close();
                this.complete(null);
            }
            catch(IOException iOException0) {
                throw this.complete(iOException0);
            }
        }

        public final IOException complete(IOException iOException0) {
            if(this.completed) {
                return iOException0;
            }
            this.completed = true;
            if(iOException0 == null && this.invokeStartEvent) {
                this.invokeStartEvent = false;
                Exchange.this.getEventListener$okhttp().responseBodyStart(Exchange.this.getCall$okhttp());
            }
            return Exchange.this.bodyComplete(this.bytesReceived, true, false, iOException0);
        }

        @Override  // okio.ForwardingSource
        public long read(Buffer buffer0, long v) throws IOException {
            Intrinsics.checkNotNullParameter(buffer0, "sink");
            if(!this.closed) {
                try {
                    long v1 = this.delegate().read(buffer0, v);
                    if(this.invokeStartEvent) {
                        this.invokeStartEvent = false;
                        Exchange.this.getEventListener$okhttp().responseBodyStart(Exchange.this.getCall$okhttp());
                    }
                    if(v1 == -1L) {
                        this.complete(null);
                        return -1L;
                    }
                    long v2 = this.bytesReceived + v1;
                    long v3 = this.contentLength;
                    if(v3 != -1L && v2 > v3) {
                        throw new ProtocolException("expected " + this.contentLength + " bytes but received " + v2);
                    }
                    this.bytesReceived = v2;
                    if(v2 == v3) {
                        this.complete(null);
                    }
                    return v1;
                }
                catch(IOException iOException0) {
                    throw this.complete(iOException0);
                }
            }
            throw new IllegalStateException("closed");
        }
    }

    private final RealCall call;
    private final ExchangeCodec codec;
    private final EventListener eventListener;
    private final ExchangeFinder finder;
    private boolean hasFailure;
    private boolean isDuplex;

    public Exchange(RealCall realCall0, EventListener eventListener0, ExchangeFinder exchangeFinder0, ExchangeCodec exchangeCodec0) {
        Intrinsics.checkNotNullParameter(realCall0, "call");
        Intrinsics.checkNotNullParameter(eventListener0, "eventListener");
        Intrinsics.checkNotNullParameter(exchangeFinder0, "finder");
        Intrinsics.checkNotNullParameter(exchangeCodec0, "codec");
        super();
        this.call = realCall0;
        this.eventListener = eventListener0;
        this.finder = exchangeFinder0;
        this.codec = exchangeCodec0;
    }

    public final IOException bodyComplete(long v, boolean z, boolean z1, IOException iOException0) {
        if(iOException0 != null) {
            this.trackFailure(iOException0);
        }
        if(z1) {
            if(iOException0 == null) {
                this.eventListener.requestBodyEnd(this.call, v);
            }
            else {
                this.eventListener.requestFailed(this.call, iOException0);
            }
        }
        if(z) {
            if(iOException0 != null) {
                this.eventListener.responseFailed(this.call, iOException0);
                return this.call.messageDone$okhttp(this, z1, true, iOException0);
            }
            this.eventListener.responseBodyEnd(this.call, v);
        }
        return this.call.messageDone$okhttp(this, z1, z, iOException0);
    }

    public final void cancel() {
        this.codec.cancel();
    }

    public final Sink createRequestBody(Request request0, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(request0, "request");
        this.isDuplex = z;
        RequestBody requestBody0 = request0.body();
        Intrinsics.checkNotNull(requestBody0);
        long v = requestBody0.contentLength();
        this.eventListener.requestBodyStart(this.call);
        return new RequestBodySink(this, this.codec.createRequestBody(request0, v), v);
    }

    public final void detachWithViolence() {
        this.codec.cancel();
        this.call.messageDone$okhttp(this, true, true, null);
    }

    public final void finishRequest() throws IOException {
        try {
            this.codec.finishRequest();
        }
        catch(IOException iOException0) {
            this.eventListener.requestFailed(this.call, iOException0);
            this.trackFailure(iOException0);
            throw iOException0;
        }
    }

    public final void flushRequest() throws IOException {
        try {
            this.codec.flushRequest();
        }
        catch(IOException iOException0) {
            this.eventListener.requestFailed(this.call, iOException0);
            this.trackFailure(iOException0);
            throw iOException0;
        }
    }

    public final RealCall getCall$okhttp() {
        return this.call;
    }

    public final RealConnection getConnection$okhttp() {
        Carrier exchangeCodec$Carrier0 = this.codec.getCarrier();
        RealConnection realConnection0 = exchangeCodec$Carrier0 instanceof RealConnection ? ((RealConnection)exchangeCodec$Carrier0) : null;
        if(realConnection0 == null) {
            throw new IllegalStateException("no connection for CONNECT tunnels");
        }
        return realConnection0;
    }

    public final EventListener getEventListener$okhttp() {
        return this.eventListener;
    }

    public final ExchangeFinder getFinder$okhttp() {
        return this.finder;
    }

    public final boolean getHasFailure$okhttp() {
        return this.hasFailure;
    }

    public final boolean isCoalescedConnection$okhttp() {
        return !Intrinsics.areEqual(this.finder.getRoutePlanner().getAddress().url().host(), this.codec.getCarrier().getRoute().address().url().host());
    }

    public final boolean isDuplex$okhttp() {
        return this.isDuplex;
    }

    public final Streams newWebSocketStreams() throws SocketException {
        this.call.timeoutEarlyExit();
        Carrier exchangeCodec$Carrier0 = this.codec.getCarrier();
        Intrinsics.checkNotNull(exchangeCodec$Carrier0, "null cannot be cast to non-null type okhttp3.internal.connection.RealConnection");
        return ((RealConnection)exchangeCodec$Carrier0).newWebSocketStreams$okhttp(this);
    }

    public final void noNewExchangesOnConnection() {
        this.codec.getCarrier().noNewExchanges();
    }

    public final void noRequestBody() {
        this.call.messageDone$okhttp(this, true, false, null);
    }

    public final ResponseBody openResponseBody(Response response0) throws IOException {
        Intrinsics.checkNotNullParameter(response0, "response");
        try {
            String s = Response.header$default(response0, "Content-Type", null, 2, null);
            long v = this.codec.reportedContentLength(response0);
            return new RealResponseBody(s, v, Okio.buffer(new ResponseBodySource(this, this.codec.openResponseBodySource(response0), v)));
        }
        catch(IOException iOException0) {
            this.eventListener.responseFailed(this.call, iOException0);
            this.trackFailure(iOException0);
            throw iOException0;
        }
    }

    public final Builder readResponseHeaders(boolean z) throws IOException {
        try {
            Builder response$Builder0 = this.codec.readResponseHeaders(z);
            if(response$Builder0 != null) {
                response$Builder0.initExchange$okhttp(this);
            }
            return response$Builder0;
        }
        catch(IOException iOException0) {
            this.eventListener.responseFailed(this.call, iOException0);
            this.trackFailure(iOException0);
            throw iOException0;
        }
    }

    public final void responseHeadersEnd(Response response0) {
        Intrinsics.checkNotNullParameter(response0, "response");
        this.eventListener.responseHeadersEnd(this.call, response0);
    }

    public final void responseHeadersStart() {
        this.eventListener.responseHeadersStart(this.call);
    }

    private final void trackFailure(IOException iOException0) {
        this.hasFailure = true;
        this.codec.getCarrier().trackFailure(this.call, iOException0);
    }

    public final Headers trailers() throws IOException {
        return this.codec.trailers();
    }

    public final void webSocketUpgradeFailed() {
        this.bodyComplete(-1L, true, true, null);
    }

    public final void writeRequestHeaders(Request request0) throws IOException {
        Intrinsics.checkNotNullParameter(request0, "request");
        try {
            this.eventListener.requestHeadersStart(this.call);
            this.codec.writeRequestHeaders(request0);
            this.eventListener.requestHeadersEnd(this.call, request0);
        }
        catch(IOException iOException0) {
            this.eventListener.requestFailed(this.call, iOException0);
            this.trackFailure(iOException0);
            throw iOException0;
        }
    }
}

