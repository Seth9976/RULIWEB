package okhttp3.internal.http2;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response.Builder;
import okhttp3.Response;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.http.ExchangeCodec.Carrier;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okio.Sink;
import okio.Source;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 (2\u00020\u0001:\u0001(B%\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u0013\u001A\u00020\u0014H\u0016J\u0018\u0010\u0015\u001A\u00020\u00162\u0006\u0010\u0017\u001A\u00020\u00182\u0006\u0010\u0019\u001A\u00020\u001AH\u0016J\b\u0010\u001B\u001A\u00020\u0014H\u0016J\b\u0010\u001C\u001A\u00020\u0014H\u0016J\u0010\u0010\u001D\u001A\u00020\u001E2\u0006\u0010\u001F\u001A\u00020 H\u0016J\u0012\u0010!\u001A\u0004\u0018\u00010\"2\u0006\u0010#\u001A\u00020\fH\u0016J\u0010\u0010$\u001A\u00020\u001A2\u0006\u0010\u001F\u001A\u00020 H\u0016J\b\u0010%\u001A\u00020&H\u0016J\u0010\u0010\'\u001A\u00020\u00142\u0006\u0010\u0017\u001A\u00020\u0018H\u0016R\u000E\u0010\u000B\u001A\u00020\fX\u0082\u000E¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001A\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u000ER\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u000F\u001A\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001A\u0004\u0018\u00010\u0012X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lokhttp3/internal/http2/Http2ExchangeCodec;", "Lokhttp3/internal/http/ExchangeCodec;", "client", "Lokhttp3/OkHttpClient;", "carrier", "Lokhttp3/internal/http/ExchangeCodec$Carrier;", "chain", "Lokhttp3/internal/http/RealInterceptorChain;", "http2Connection", "Lokhttp3/internal/http2/Http2Connection;", "(Lokhttp3/OkHttpClient;Lokhttp3/internal/http/ExchangeCodec$Carrier;Lokhttp3/internal/http/RealInterceptorChain;Lokhttp3/internal/http2/Http2Connection;)V", "canceled", "", "getCarrier", "()Lokhttp3/internal/http/ExchangeCodec$Carrier;", "protocol", "Lokhttp3/Protocol;", "stream", "Lokhttp3/internal/http2/Http2Stream;", "cancel", "", "createRequestBody", "Lokio/Sink;", "request", "Lokhttp3/Request;", "contentLength", "", "finishRequest", "flushRequest", "openResponseBodySource", "Lokio/Source;", "response", "Lokhttp3/Response;", "readResponseHeaders", "Lokhttp3/Response$Builder;", "expectContinue", "reportedContentLength", "trailers", "Lokhttp3/Headers;", "writeRequestHeaders", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class Http2ExchangeCodec implements ExchangeCodec {
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u000F\u001A\b\u0012\u0004\u0012\u00020\u00100\b2\u0006\u0010\u0011\u001A\u00020\u0012J\u0016\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u00162\u0006\u0010\u0017\u001A\u00020\u0018R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\u00040\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001A\b\u0012\u0004\u0012\u00020\u00040\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\r\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u000E\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lokhttp3/internal/http2/Http2ExchangeCodec$Companion;", "", "()V", "CONNECTION", "", "ENCODING", "HOST", "HTTP_2_SKIPPED_REQUEST_HEADERS", "", "HTTP_2_SKIPPED_RESPONSE_HEADERS", "KEEP_ALIVE", "PROXY_CONNECTION", "TE", "TRANSFER_ENCODING", "UPGRADE", "http2HeadersList", "Lokhttp3/internal/http2/Header;", "request", "Lokhttp3/Request;", "readHttp2HeadersList", "Lokhttp3/Response$Builder;", "headerBlock", "Lokhttp3/Headers;", "protocol", "Lokhttp3/Protocol;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final List http2HeadersList(Request request0) {
            Intrinsics.checkNotNullParameter(request0, "request");
            Headers headers0 = request0.headers();
            ArrayList arrayList0 = new ArrayList(headers0.size() + 4);
            arrayList0.add(new Header(Header.TARGET_METHOD, request0.method()));
            String s = RequestLine.INSTANCE.requestPath(request0.url());
            arrayList0.add(new Header(Header.TARGET_PATH, s));
            String s1 = request0.header("Host");
            if(s1 != null) {
                arrayList0.add(new Header(Header.TARGET_AUTHORITY, s1));
            }
            arrayList0.add(new Header(Header.TARGET_SCHEME, request0.url().scheme()));
            int v = headers0.size();
            for(int v1 = 0; v1 < v; ++v1) {
                String s2 = headers0.name(v1);
                Locale locale0 = Locale.US;
                Intrinsics.checkNotNullExpressionValue(locale0, "US");
                String s3 = s2.toLowerCase(locale0);
                Intrinsics.checkNotNullExpressionValue(s3, "this as java.lang.String).toLowerCase(locale)");
                if(!Http2ExchangeCodec.HTTP_2_SKIPPED_REQUEST_HEADERS.contains(s3) || Intrinsics.areEqual(s3, "te") && Intrinsics.areEqual(headers0.value(v1), "trailers")) {
                    arrayList0.add(new Header(s3, headers0.value(v1)));
                }
            }
            return arrayList0;
        }

        public final Builder readHttp2HeadersList(Headers headers0, Protocol protocol0) {
            Intrinsics.checkNotNullParameter(headers0, "headerBlock");
            Intrinsics.checkNotNullParameter(protocol0, "protocol");
            okhttp3.Headers.Builder headers$Builder0 = new okhttp3.Headers.Builder();
            int v = headers0.size();
            StatusLine statusLine0 = null;
            for(int v1 = 0; v1 < v; ++v1) {
                String s = headers0.name(v1);
                String s1 = headers0.value(v1);
                if(Intrinsics.areEqual(s, ":status")) {
                    statusLine0 = StatusLine.Companion.parse("HTTP/1.1 " + s1);
                }
                else if(!Http2ExchangeCodec.HTTP_2_SKIPPED_RESPONSE_HEADERS.contains(s)) {
                    headers$Builder0.addLenient$okhttp(s, s1);
                }
            }
            if(statusLine0 == null) {
                throw new ProtocolException("Expected \':status\' header not present");
            }
            return new Builder().protocol(protocol0).code(statusLine0.code).message(statusLine0.message).headers(headers$Builder0.build()).trailers(okhttp3.internal.http2.Http2ExchangeCodec.Companion.readHttp2HeadersList.1.INSTANCE);

            @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lokhttp3/Headers;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 0x30)
            final class okhttp3.internal.http2.Http2ExchangeCodec.Companion.readHttp2HeadersList.1 extends Lambda implements Function0 {
                public static final okhttp3.internal.http2.Http2ExchangeCodec.Companion.readHttp2HeadersList.1 INSTANCE;

                static {
                    okhttp3.internal.http2.Http2ExchangeCodec.Companion.readHttp2HeadersList.1.INSTANCE = new okhttp3.internal.http2.Http2ExchangeCodec.Companion.readHttp2HeadersList.1();
                }

                okhttp3.internal.http2.Http2ExchangeCodec.Companion.readHttp2HeadersList.1() {
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
    }

    private static final String CONNECTION = "connection";
    public static final Companion Companion = null;
    private static final String ENCODING = "encoding";
    private static final String HOST = "host";
    private static final List HTTP_2_SKIPPED_REQUEST_HEADERS = null;
    private static final List HTTP_2_SKIPPED_RESPONSE_HEADERS = null;
    private static final String KEEP_ALIVE = "keep-alive";
    private static final String PROXY_CONNECTION = "proxy-connection";
    private static final String TE = "te";
    private static final String TRANSFER_ENCODING = "transfer-encoding";
    private static final String UPGRADE = "upgrade";
    private volatile boolean canceled;
    private final Carrier carrier;
    private final RealInterceptorChain chain;
    private final Http2Connection http2Connection;
    private final Protocol protocol;
    private volatile Http2Stream stream;

    static {
        Http2ExchangeCodec.Companion = new Companion(null);
        Http2ExchangeCodec.HTTP_2_SKIPPED_REQUEST_HEADERS = _UtilJvmKt.immutableListOf(new String[]{"connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade", ":method", ":path", ":scheme", ":authority"});
        Http2ExchangeCodec.HTTP_2_SKIPPED_RESPONSE_HEADERS = _UtilJvmKt.immutableListOf(new String[]{"connection", "host", "keep-alive", "proxy-connection", "te", "transfer-encoding", "encoding", "upgrade"});
    }

    public Http2ExchangeCodec(OkHttpClient okHttpClient0, Carrier exchangeCodec$Carrier0, RealInterceptorChain realInterceptorChain0, Http2Connection http2Connection0) {
        Intrinsics.checkNotNullParameter(okHttpClient0, "client");
        Intrinsics.checkNotNullParameter(exchangeCodec$Carrier0, "carrier");
        Intrinsics.checkNotNullParameter(realInterceptorChain0, "chain");
        Intrinsics.checkNotNullParameter(http2Connection0, "http2Connection");
        super();
        this.carrier = exchangeCodec$Carrier0;
        this.chain = realInterceptorChain0;
        this.http2Connection = http2Connection0;
        this.protocol = okHttpClient0.protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE) ? Protocol.H2_PRIOR_KNOWLEDGE : Protocol.HTTP_2;
    }

    @Override  // okhttp3.internal.http.ExchangeCodec
    public void cancel() {
        this.canceled = true;
        Http2Stream http2Stream0 = this.stream;
        if(http2Stream0 != null) {
            http2Stream0.closeLater(ErrorCode.CANCEL);
        }
    }

    @Override  // okhttp3.internal.http.ExchangeCodec
    public Sink createRequestBody(Request request0, long v) {
        Intrinsics.checkNotNullParameter(request0, "request");
        Http2Stream http2Stream0 = this.stream;
        Intrinsics.checkNotNull(http2Stream0);
        return http2Stream0.getSink();
    }

    @Override  // okhttp3.internal.http.ExchangeCodec
    public void finishRequest() {
        Http2Stream http2Stream0 = this.stream;
        Intrinsics.checkNotNull(http2Stream0);
        http2Stream0.getSink().close();
    }

    @Override  // okhttp3.internal.http.ExchangeCodec
    public void flushRequest() {
        this.http2Connection.flush();
    }

    @Override  // okhttp3.internal.http.ExchangeCodec
    public Carrier getCarrier() {
        return this.carrier;
    }

    @Override  // okhttp3.internal.http.ExchangeCodec
    public Source openResponseBodySource(Response response0) {
        Intrinsics.checkNotNullParameter(response0, "response");
        Http2Stream http2Stream0 = this.stream;
        Intrinsics.checkNotNull(http2Stream0);
        return http2Stream0.getSource$okhttp();
    }

    @Override  // okhttp3.internal.http.ExchangeCodec
    public Builder readResponseHeaders(boolean z) {
        Http2Stream http2Stream0 = this.stream;
        if(http2Stream0 == null) {
            throw new IOException("stream wasn\'t created");
        }
        Headers headers0 = http2Stream0.takeHeaders(z);
        Builder response$Builder0 = Http2ExchangeCodec.Companion.readHttp2HeadersList(headers0, this.protocol);
        return !z || response$Builder0.getCode$okhttp() != 100 ? response$Builder0 : null;
    }

    @Override  // okhttp3.internal.http.ExchangeCodec
    public long reportedContentLength(Response response0) {
        Intrinsics.checkNotNullParameter(response0, "response");
        return HttpHeaders.promisesBody(response0) ? _UtilJvmKt.headersContentLength(response0) : 0L;
    }

    @Override  // okhttp3.internal.http.ExchangeCodec
    public Headers trailers() {
        Http2Stream http2Stream0 = this.stream;
        Intrinsics.checkNotNull(http2Stream0);
        return http2Stream0.trailers();
    }

    @Override  // okhttp3.internal.http.ExchangeCodec
    public void writeRequestHeaders(Request request0) {
        Intrinsics.checkNotNullParameter(request0, "request");
        if(this.stream != null) {
            return;
        }
        List list0 = Http2ExchangeCodec.Companion.http2HeadersList(request0);
        this.stream = this.http2Connection.newStream(list0, request0.body() != null);
        if(!this.canceled) {
            Http2Stream http2Stream0 = this.stream;
            Intrinsics.checkNotNull(http2Stream0);
            http2Stream0.readTimeout().timeout(((long)this.chain.getReadTimeoutMillis$okhttp()), TimeUnit.MILLISECONDS);
            Http2Stream http2Stream1 = this.stream;
            Intrinsics.checkNotNull(http2Stream1);
            http2Stream1.writeTimeout().timeout(((long)this.chain.getWriteTimeoutMillis$okhttp()), TimeUnit.MILLISECONDS);
            return;
        }
        Http2Stream http2Stream2 = this.stream;
        Intrinsics.checkNotNull(http2Stream2);
        http2Stream2.closeLater(ErrorCode.CANCEL);
        throw new IOException("Canceled");
    }
}

