package okhttp3.internal.http1;

import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.Proxy.Type;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response.Builder;
import okhttp3.Response;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.http.ExchangeCodec.Carrier;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingTimeout;
import okio.Sink;
import okio.Source;
import okio.Timeout;

@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000E\n\u0002\b\t\u0018\u0000 ?2\u00020\u0001:\u0007<=>?@ABB\'\u0012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u00A2\u0006\u0002\u0010\nJ\b\u0010\u001B\u001A\u00020\u001CH\u0016J\u0018\u0010\u001D\u001A\u00020\u001E2\u0006\u0010\u001F\u001A\u00020\u00172\u0006\u0010 \u001A\u00020!H\u0016J\u0010\u0010\"\u001A\u00020\u001C2\u0006\u0010#\u001A\u00020$H\u0002J\b\u0010%\u001A\u00020\u001CH\u0016J\b\u0010&\u001A\u00020\u001CH\u0016J\b\u0010\'\u001A\u00020\u001EH\u0002J\u0010\u0010(\u001A\u00020)2\u0006\u0010*\u001A\u00020+H\u0002J\u0010\u0010,\u001A\u00020)2\u0006\u0010-\u001A\u00020!H\u0002J\b\u0010.\u001A\u00020\u001EH\u0002J\b\u0010/\u001A\u00020)H\u0002J\u0010\u00100\u001A\u00020)2\u0006\u00101\u001A\u00020\u0019H\u0016J\u0012\u00102\u001A\u0004\u0018\u0001032\u0006\u00104\u001A\u00020\u0010H\u0016J\u0010\u00105\u001A\u00020!2\u0006\u00101\u001A\u00020\u0019H\u0016J\u000E\u00106\u001A\u00020\u001C2\u0006\u00101\u001A\u00020\u0019J\b\u0010\u0014\u001A\u00020\u0015H\u0016J\u0016\u00107\u001A\u00020\u001C2\u0006\u00108\u001A\u00020\u00152\u0006\u00109\u001A\u00020:J\u0010\u0010;\u001A\u00020\u001C2\u0006\u0010\u001F\u001A\u00020\u0017H\u0016R\u0014\u0010\u0004\u001A\u00020\u0005X\u0096\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR\u0010\u0010\u0002\u001A\u0004\u0018\u00010\u0003X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\r\u001A\u00020\u000EX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0011\u0010\u000F\u001A\u00020\u00108F\u00A2\u0006\u0006\u001A\u0004\b\u000F\u0010\u0011R\u000E\u0010\b\u001A\u00020\tX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0012\u001A\u00020\u0013X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001A\u0004\u0018\u00010\u0015X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0018\u0010\u0016\u001A\u00020\u0010*\u00020\u00178BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0016\u0010\u0018R\u0018\u0010\u0016\u001A\u00020\u0010*\u00020\u00198BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0016\u0010\u001A\u00A8\u0006C"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec;", "Lokhttp3/internal/http/ExchangeCodec;", "client", "Lokhttp3/OkHttpClient;", "carrier", "Lokhttp3/internal/http/ExchangeCodec$Carrier;", "source", "Lokio/BufferedSource;", "sink", "Lokio/BufferedSink;", "(Lokhttp3/OkHttpClient;Lokhttp3/internal/http/ExchangeCodec$Carrier;Lokio/BufferedSource;Lokio/BufferedSink;)V", "getCarrier", "()Lokhttp3/internal/http/ExchangeCodec$Carrier;", "headersReader", "Lokhttp3/internal/http1/HeadersReader;", "isClosed", "", "()Z", "state", "", "trailers", "Lokhttp3/Headers;", "isChunked", "Lokhttp3/Request;", "(Lokhttp3/Request;)Z", "Lokhttp3/Response;", "(Lokhttp3/Response;)Z", "cancel", "", "createRequestBody", "Lokio/Sink;", "request", "contentLength", "", "detachTimeout", "timeout", "Lokio/ForwardingTimeout;", "finishRequest", "flushRequest", "newChunkedSink", "newChunkedSource", "Lokio/Source;", "url", "Lokhttp3/HttpUrl;", "newFixedLengthSource", "length", "newKnownLengthSink", "newUnknownLengthSource", "openResponseBodySource", "response", "readResponseHeaders", "Lokhttp3/Response$Builder;", "expectContinue", "reportedContentLength", "skipConnectBody", "writeRequest", "headers", "requestLine", "", "writeRequestHeaders", "AbstractSource", "ChunkedSink", "ChunkedSource", "Companion", "FixedLengthSource", "KnownLengthSink", "UnknownLengthSource", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class Http1ExchangeCodec implements ExchangeCodec {
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\b¢\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u000EH\u0016J\u0006\u0010\u0012\u001A\u00020\u0013J\b\u0010\t\u001A\u00020\u0014H\u0016R\u001A\u0010\u0003\u001A\u00020\u0004X\u0084\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001A\u00020\nX\u0084\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\f¨\u0006\u0015"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$AbstractSource;", "Lokio/Source;", "(Lokhttp3/internal/http1/Http1ExchangeCodec;)V", "closed", "", "getClosed", "()Z", "setClosed", "(Z)V", "timeout", "Lokio/ForwardingTimeout;", "getTimeout", "()Lokio/ForwardingTimeout;", "read", "", "sink", "Lokio/Buffer;", "byteCount", "responseBodyComplete", "", "Lokio/Timeout;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    abstract class AbstractSource implements Source {
        private boolean closed;
        private final ForwardingTimeout timeout;

        public AbstractSource() {
            this.timeout = new ForwardingTimeout(http1ExchangeCodec0.source.timeout());
        }

        protected final boolean getClosed() {
            return this.closed;
        }

        protected final ForwardingTimeout getTimeout() {
            return this.timeout;
        }

        @Override  // okio.Source
        public long read(Buffer buffer0, long v) {
            Intrinsics.checkNotNullParameter(buffer0, "sink");
            try {
                return Http1ExchangeCodec.this.source.read(buffer0, v);
            }
            catch(IOException iOException0) {
                Http1ExchangeCodec.this.getCarrier().noNewExchanges();
                this.responseBodyComplete();
                throw iOException0;
            }
        }

        public final void responseBodyComplete() {
            switch(Http1ExchangeCodec.this.state) {
                case 5: {
                    Http1ExchangeCodec.this.detachTimeout(this.timeout);
                    Http1ExchangeCodec.this.state = 6;
                    return;
                }
                case 6: {
                    return;
                }
                default: {
                    throw new IllegalStateException("state: " + Http1ExchangeCodec.this.state);
                }
            }
        }

        protected final void setClosed(boolean z) {
            this.closed = z;
        }

        @Override  // okio.Source
        public Timeout timeout() {
            return this.timeout;
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001A\u00020\bH\u0016J\b\u0010\t\u001A\u00020\bH\u0016J\b\u0010\u0005\u001A\u00020\nH\u0016J\u0018\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000FH\u0016R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$ChunkedSink;", "Lokio/Sink;", "(Lokhttp3/internal/http1/Http1ExchangeCodec;)V", "closed", "", "timeout", "Lokio/ForwardingTimeout;", "close", "", "flush", "Lokio/Timeout;", "write", "source", "Lokio/Buffer;", "byteCount", "", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    final class ChunkedSink implements AutoCloseable, Sink {
        private boolean closed;
        private final ForwardingTimeout timeout;

        public ChunkedSink() {
            this.timeout = new ForwardingTimeout(http1ExchangeCodec0.sink.timeout());
        }

        @Override  // okio.Sink
        public void close() {
            synchronized(this) {
                if(this.closed) {
                    return;
                }
                this.closed = true;
                Http1ExchangeCodec.this.sink.writeUtf8("0\r\n\r\n");
                Http1ExchangeCodec.this.detachTimeout(this.timeout);
                Http1ExchangeCodec.this.state = 3;
            }
        }

        @Override  // okio.Sink
        public void flush() {
            synchronized(this) {
                if(this.closed) {
                    return;
                }
                Http1ExchangeCodec.this.sink.flush();
            }
        }

        @Override  // okio.Sink
        public Timeout timeout() {
            return this.timeout;
        }

        @Override  // okio.Sink
        public void write(Buffer buffer0, long v) {
            Intrinsics.checkNotNullParameter(buffer0, "source");
            if(this.closed) {
                throw new IllegalStateException("closed");
            }
            if(v == 0L) {
                return;
            }
            Http1ExchangeCodec.this.sink.writeHexadecimalUnsignedLong(v);
            Http1ExchangeCodec.this.sink.writeUtf8("\r\n");
            Http1ExchangeCodec.this.sink.write(buffer0, v);
            Http1ExchangeCodec.this.sink.writeUtf8("\r\n");
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\n\u001A\u00020\u000BH\u0016J\u0018\u0010\f\u001A\u00020\u00072\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u0007H\u0016J\b\u0010\u0010\u001A\u00020\u000BH\u0002R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\tX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$ChunkedSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec$AbstractSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec;", "url", "Lokhttp3/HttpUrl;", "(Lokhttp3/internal/http1/Http1ExchangeCodec;Lokhttp3/HttpUrl;)V", "bytesRemainingInChunk", "", "hasMoreChunks", "", "close", "", "read", "sink", "Lokio/Buffer;", "byteCount", "readChunkSize", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    final class ChunkedSource extends AbstractSource {
        private long bytesRemainingInChunk;
        private boolean hasMoreChunks;
        private final HttpUrl url;

        public ChunkedSource(HttpUrl httpUrl0) {
            Intrinsics.checkNotNullParameter(httpUrl0, "url");
            Http1ExchangeCodec.this = http1ExchangeCodec0;
            super();
            this.url = httpUrl0;
            this.bytesRemainingInChunk = -1L;
            this.hasMoreChunks = true;
        }

        @Override  // okio.Source
        public void close() {
            if(this.getClosed()) {
                return;
            }
            if(this.hasMoreChunks && !_UtilJvmKt.discard(this, 100, TimeUnit.MILLISECONDS)) {
                Http1ExchangeCodec.this.getCarrier().noNewExchanges();
                this.responseBodyComplete();
            }
            this.setClosed(true);
        }

        @Override  // okhttp3.internal.http1.Http1ExchangeCodec$AbstractSource
        public long read(Buffer buffer0, long v) {
            Intrinsics.checkNotNullParameter(buffer0, "sink");
            if(v < 0L) {
                throw new IllegalArgumentException(("byteCount < 0: " + v).toString());
            }
            if(this.getClosed()) {
                throw new IllegalStateException("closed");
            }
            if(!this.hasMoreChunks) {
                return -1L;
            }
            if(this.bytesRemainingInChunk == 0L || this.bytesRemainingInChunk == -1L) {
                this.readChunkSize();
                if(!this.hasMoreChunks) {
                    return -1L;
                }
            }
            long v1 = super.read(buffer0, Math.min(v, this.bytesRemainingInChunk));
            if(v1 != -1L) {
                this.bytesRemainingInChunk -= v1;
                return v1;
            }
            Http1ExchangeCodec.this.getCarrier().noNewExchanges();
            ProtocolException protocolException0 = new ProtocolException("unexpected end of stream");
            this.responseBodyComplete();
            throw protocolException0;
        }

        private final void readChunkSize() {
            if(this.bytesRemainingInChunk != -1L) {
                Http1ExchangeCodec.this.source.readUtf8LineStrict();
            }
            try {
                this.bytesRemainingInChunk = Http1ExchangeCodec.this.source.readHexadecimalUnsignedLong();
                String s = StringsKt.trim(Http1ExchangeCodec.this.source.readUtf8LineStrict()).toString();
                if(this.bytesRemainingInChunk < 0L || s.length() > 0 && !StringsKt.startsWith$default(s, ";", false, 2, null)) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.bytesRemainingInChunk + s + '\"');
                }
            }
            catch(NumberFormatException numberFormatException0) {
                throw new ProtocolException(numberFormatException0.getMessage());
            }
            if(this.bytesRemainingInChunk == 0L) {
                this.hasMoreChunks = false;
                Headers headers0 = Http1ExchangeCodec.this.headersReader.readHeaders();
                Http1ExchangeCodec.this.trailers = headers0;
                OkHttpClient okHttpClient0 = Http1ExchangeCodec.this.client;
                Intrinsics.checkNotNull(okHttpClient0);
                Headers headers1 = Http1ExchangeCodec.this.trailers;
                Intrinsics.checkNotNull(headers1);
                HttpHeaders.receiveHeaders(okHttpClient0.cookieJar(), this.url, headers1);
                this.responseBodyComplete();
            }
        }
    }

    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$Companion;", "", "()V", "NO_CHUNK_YET", "", "STATE_CLOSED", "", "STATE_IDLE", "STATE_OPEN_REQUEST_BODY", "STATE_OPEN_RESPONSE_BODY", "STATE_READING_RESPONSE_BODY", "STATE_READ_RESPONSE_HEADERS", "STATE_WRITING_REQUEST_BODY", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001A\u00020\u0007H\u0016J\u0018\u0010\b\u001A\u00020\u00042\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\u0004H\u0016R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$FixedLengthSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec$AbstractSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec;", "bytesRemaining", "", "(Lokhttp3/internal/http1/Http1ExchangeCodec;J)V", "close", "", "read", "sink", "Lokio/Buffer;", "byteCount", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    final class FixedLengthSource extends AbstractSource {
        private long bytesRemaining;

        public FixedLengthSource(long v) {
            this.bytesRemaining = v;
            if(v == 0L) {
                this.responseBodyComplete();
            }
        }

        @Override  // okio.Source
        public void close() {
            if(this.getClosed()) {
                return;
            }
            if(this.bytesRemaining != 0L && !_UtilJvmKt.discard(this, 100, TimeUnit.MILLISECONDS)) {
                Http1ExchangeCodec.this.getCarrier().noNewExchanges();
                this.responseBodyComplete();
            }
            this.setClosed(true);
        }

        @Override  // okhttp3.internal.http1.Http1ExchangeCodec$AbstractSource
        public long read(Buffer buffer0, long v) {
            Intrinsics.checkNotNullParameter(buffer0, "sink");
            if(v < 0L) {
                throw new IllegalArgumentException(("byteCount < 0: " + v).toString());
            }
            if(this.getClosed()) {
                throw new IllegalStateException("closed");
            }
            long v1 = this.bytesRemaining;
            if(v1 == 0L) {
                return -1L;
            }
            long v2 = super.read(buffer0, Math.min(v1, v));
            if(v2 != -1L) {
                long v3 = this.bytesRemaining - v2;
                this.bytesRemaining = v3;
                if(v3 == 0L) {
                    this.responseBodyComplete();
                }
                return v2;
            }
            Http1ExchangeCodec.this.getCarrier().noNewExchanges();
            ProtocolException protocolException0 = new ProtocolException("unexpected end of stream");
            this.responseBodyComplete();
            throw protocolException0;
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001A\u00020\bH\u0016J\b\u0010\t\u001A\u00020\bH\u0016J\b\u0010\u0005\u001A\u00020\nH\u0016J\u0018\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000FH\u0016R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$KnownLengthSink;", "Lokio/Sink;", "(Lokhttp3/internal/http1/Http1ExchangeCodec;)V", "closed", "", "timeout", "Lokio/ForwardingTimeout;", "close", "", "flush", "Lokio/Timeout;", "write", "source", "Lokio/Buffer;", "byteCount", "", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    final class KnownLengthSink implements AutoCloseable, Sink {
        private boolean closed;
        private final ForwardingTimeout timeout;

        public KnownLengthSink() {
            this.timeout = new ForwardingTimeout(http1ExchangeCodec0.sink.timeout());
        }

        @Override  // okio.Sink
        public void close() {
            if(this.closed) {
                return;
            }
            this.closed = true;
            Http1ExchangeCodec.this.detachTimeout(this.timeout);
            Http1ExchangeCodec.this.state = 3;
        }

        @Override  // okio.Sink
        public void flush() {
            if(this.closed) {
                return;
            }
            Http1ExchangeCodec.this.sink.flush();
        }

        @Override  // okio.Sink
        public Timeout timeout() {
            return this.timeout;
        }

        @Override  // okio.Sink
        public void write(Buffer buffer0, long v) {
            Intrinsics.checkNotNullParameter(buffer0, "source");
            if(this.closed) {
                throw new IllegalStateException("closed");
            }
            _UtilCommonKt.checkOffsetAndCount(buffer0.size(), 0L, v);
            Http1ExchangeCodec.this.sink.write(buffer0, v);
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0006\u001A\u00020\u0007H\u0016J\u0018\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\tH\u0016R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lokhttp3/internal/http1/Http1ExchangeCodec$UnknownLengthSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec$AbstractSource;", "Lokhttp3/internal/http1/Http1ExchangeCodec;", "(Lokhttp3/internal/http1/Http1ExchangeCodec;)V", "inputExhausted", "", "close", "", "read", "", "sink", "Lokio/Buffer;", "byteCount", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    final class UnknownLengthSource extends AbstractSource {
        private boolean inputExhausted;

        @Override  // okio.Source
        public void close() {
            if(this.getClosed()) {
                return;
            }
            if(!this.inputExhausted) {
                this.responseBodyComplete();
            }
            this.setClosed(true);
        }

        @Override  // okhttp3.internal.http1.Http1ExchangeCodec$AbstractSource
        public long read(Buffer buffer0, long v) {
            Intrinsics.checkNotNullParameter(buffer0, "sink");
            if(v < 0L) {
                throw new IllegalArgumentException(("byteCount < 0: " + v).toString());
            }
            if(this.getClosed()) {
                throw new IllegalStateException("closed");
            }
            if(this.inputExhausted) {
                return -1L;
            }
            long v1 = super.read(buffer0, v);
            if(v1 == -1L) {
                this.inputExhausted = true;
                this.responseBodyComplete();
                return -1L;
            }
            return v1;
        }
    }

    public static final Companion Companion = null;
    private static final long NO_CHUNK_YET = -1L;
    private static final int STATE_CLOSED = 6;
    private static final int STATE_IDLE = 0;
    private static final int STATE_OPEN_REQUEST_BODY = 1;
    private static final int STATE_OPEN_RESPONSE_BODY = 4;
    private static final int STATE_READING_RESPONSE_BODY = 5;
    private static final int STATE_READ_RESPONSE_HEADERS = 3;
    private static final int STATE_WRITING_REQUEST_BODY = 2;
    private final Carrier carrier;
    private final OkHttpClient client;
    private final HeadersReader headersReader;
    private final BufferedSink sink;
    private final BufferedSource source;
    private int state;
    private Headers trailers;

    static {
        Http1ExchangeCodec.Companion = new Companion(null);
    }

    public Http1ExchangeCodec(OkHttpClient okHttpClient0, Carrier exchangeCodec$Carrier0, BufferedSource bufferedSource0, BufferedSink bufferedSink0) {
        Intrinsics.checkNotNullParameter(exchangeCodec$Carrier0, "carrier");
        Intrinsics.checkNotNullParameter(bufferedSource0, "source");
        Intrinsics.checkNotNullParameter(bufferedSink0, "sink");
        super();
        this.client = okHttpClient0;
        this.carrier = exchangeCodec$Carrier0;
        this.source = bufferedSource0;
        this.sink = bufferedSink0;
        this.headersReader = new HeadersReader(bufferedSource0);
    }

    @Override  // okhttp3.internal.http.ExchangeCodec
    public void cancel() {
        this.getCarrier().cancel();
    }

    @Override  // okhttp3.internal.http.ExchangeCodec
    public Sink createRequestBody(Request request0, long v) {
        Intrinsics.checkNotNullParameter(request0, "request");
        RequestBody requestBody0 = request0.body();
        if(requestBody0 != null && requestBody0.isDuplex()) {
            throw new ProtocolException("Duplex connections are not supported for HTTP/1");
        }
        if(this.isChunked(request0)) {
            return this.newChunkedSink();
        }
        if(v == -1L) {
            throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
        }
        return this.newKnownLengthSink();
    }

    private final void detachTimeout(ForwardingTimeout forwardingTimeout0) {
        Timeout timeout0 = forwardingTimeout0.delegate();
        forwardingTimeout0.setDelegate(Timeout.NONE);
        timeout0.clearDeadline();
        timeout0.clearTimeout();
    }

    @Override  // okhttp3.internal.http.ExchangeCodec
    public void finishRequest() {
        this.sink.flush();
    }

    @Override  // okhttp3.internal.http.ExchangeCodec
    public void flushRequest() {
        this.sink.flush();
    }

    @Override  // okhttp3.internal.http.ExchangeCodec
    public Carrier getCarrier() {
        return this.carrier;
    }

    private final boolean isChunked(Request request0) {
        return StringsKt.equals("chunked", request0.header("Transfer-Encoding"), true);
    }

    private final boolean isChunked(Response response0) {
        return StringsKt.equals("chunked", Response.header$default(response0, "Transfer-Encoding", null, 2, null), true);
    }

    public final boolean isClosed() {
        return this.state == 6;
    }

    private final Sink newChunkedSink() {
        if(this.state != 1) {
            throw new IllegalStateException(("state: " + this.state).toString());
        }
        this.state = 2;
        return new ChunkedSink(this);
    }

    private final Source newChunkedSource(HttpUrl httpUrl0) {
        if(this.state != 4) {
            throw new IllegalStateException(("state: " + this.state).toString());
        }
        this.state = 5;
        return new ChunkedSource(this, httpUrl0);
    }

    private final Source newFixedLengthSource(long v) {
        if(this.state != 4) {
            throw new IllegalStateException(("state: " + this.state).toString());
        }
        this.state = 5;
        return new FixedLengthSource(this, v);
    }

    private final Sink newKnownLengthSink() {
        if(this.state != 1) {
            throw new IllegalStateException(("state: " + this.state).toString());
        }
        this.state = 2;
        return new KnownLengthSink(this);
    }

    private final Source newUnknownLengthSource() {
        if(this.state != 4) {
            throw new IllegalStateException(("state: " + this.state).toString());
        }
        this.state = 5;
        this.getCarrier().noNewExchanges();
        return new UnknownLengthSource(this);
    }

    @Override  // okhttp3.internal.http.ExchangeCodec
    public Source openResponseBodySource(Response response0) {
        Intrinsics.checkNotNullParameter(response0, "response");
        if(!HttpHeaders.promisesBody(response0)) {
            return this.newFixedLengthSource(0L);
        }
        if(this.isChunked(response0)) {
            return this.newChunkedSource(response0.request().url());
        }
        long v = _UtilJvmKt.headersContentLength(response0);
        return v == -1L ? this.newUnknownLengthSource() : this.newFixedLengthSource(v);
    }

    @Override  // okhttp3.internal.http.ExchangeCodec
    public Builder readResponseHeaders(boolean z) {
        if(this.state != 1 && (this.state != 2 && this.state != 3)) {
            throw new IllegalStateException(("state: " + this.state).toString());
        }
        try {
            String s = this.headersReader.readLine();
            StatusLine statusLine0 = StatusLine.Companion.parse(s);
            Builder response$Builder0 = new Builder().protocol(statusLine0.protocol).code(statusLine0.code).message(statusLine0.message).headers(this.headersReader.readHeaders()).trailers(okhttp3.internal.http1.Http1ExchangeCodec.readResponseHeaders.responseBuilder.1.INSTANCE);
            if(z && statusLine0.code == 100) {
                return null;
            }
            switch(statusLine0.code) {
                case 100: {
                    this.state = 3;
                    return response$Builder0;
                }
                case 103: {
                    this.state = 3;
                    return response$Builder0;
                }
                default: {
                    this.state = 4;
                    return response$Builder0;
                }
            }
        }
        catch(EOFException eOFException0) {
            throw new IOException("unexpected end of stream on " + this.getCarrier().getRoute().address().url().redact(), eOFException0);
        }

        @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lokhttp3/Headers;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 0x30)
        final class okhttp3.internal.http1.Http1ExchangeCodec.readResponseHeaders.responseBuilder.1 extends Lambda implements Function0 {
            public static final okhttp3.internal.http1.Http1ExchangeCodec.readResponseHeaders.responseBuilder.1 INSTANCE;

            static {
                okhttp3.internal.http1.Http1ExchangeCodec.readResponseHeaders.responseBuilder.1.INSTANCE = new okhttp3.internal.http1.Http1ExchangeCodec.readResponseHeaders.responseBuilder.1();
            }

            okhttp3.internal.http1.Http1ExchangeCodec.readResponseHeaders.responseBuilder.1() {
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            // 去混淆评级： 低(20)
            public final Headers invoke() {
                throw new IllegalStateException("trailers not available");
            }
        }

    }

    @Override  // okhttp3.internal.http.ExchangeCodec
    public long reportedContentLength(Response response0) {
        Intrinsics.checkNotNullParameter(response0, "response");
        if(!HttpHeaders.promisesBody(response0)) {
            return 0L;
        }
        return this.isChunked(response0) ? -1L : _UtilJvmKt.headersContentLength(response0);
    }

    public final void skipConnectBody(Response response0) {
        Intrinsics.checkNotNullParameter(response0, "response");
        long v = _UtilJvmKt.headersContentLength(response0);
        if(v == -1L) {
            return;
        }
        Source source0 = this.newFixedLengthSource(v);
        _UtilJvmKt.skipAll(source0, 0x7FFFFFFF, TimeUnit.MILLISECONDS);
        source0.close();
    }

    @Override  // okhttp3.internal.http.ExchangeCodec
    public Headers trailers() {
        if(this.state != 6) {
            throw new IllegalStateException("too early; can\'t read the trailers yet");
        }
        return this.trailers == null ? _UtilJvmKt.EMPTY_HEADERS : this.trailers;
    }

    public final void writeRequest(Headers headers0, String s) {
        Intrinsics.checkNotNullParameter(headers0, "headers");
        Intrinsics.checkNotNullParameter(s, "requestLine");
        if(this.state != 0) {
            throw new IllegalStateException(("state: " + this.state).toString());
        }
        this.sink.writeUtf8(s).writeUtf8("\r\n");
        int v = headers0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            String s1 = headers0.name(v1);
            this.sink.writeUtf8(s1).writeUtf8(": ").writeUtf8(headers0.value(v1)).writeUtf8("\r\n");
        }
        this.sink.writeUtf8("\r\n");
        this.state = 1;
    }

    @Override  // okhttp3.internal.http.ExchangeCodec
    public void writeRequestHeaders(Request request0) {
        Intrinsics.checkNotNullParameter(request0, "request");
        Proxy.Type proxy$Type0 = this.getCarrier().getRoute().proxy().type();
        Intrinsics.checkNotNullExpressionValue(proxy$Type0, "carrier.route.proxy.type()");
        this.writeRequest(request0.headers(), RequestLine.INSTANCE.get(request0, proxy$Type0));
    }
}

