package okhttp3;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import okhttp3.internal._ResponseCommonKt;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000B\n\u0002\b\u000E\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000B\u0018\u00002\u00020\u0001:\u0001KB\u0087\u0001\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u0012\b\u0010\n\u001A\u0004\u0018\u00010\u000B\u0012\u0006\u0010\f\u001A\u00020\r\u0012\u0006\u0010\u000E\u001A\u00020\u000F\u0012\b\u0010\u0010\u001A\u0004\u0018\u00010\u0000\u0012\b\u0010\u0011\u001A\u0004\u0018\u00010\u0000\u0012\b\u0010\u0012\u001A\u0004\u0018\u00010\u0000\u0012\u0006\u0010\u0013\u001A\u00020\u0014\u0012\u0006\u0010\u0015\u001A\u00020\u0014\u0012\b\u0010\u0016\u001A\u0004\u0018\u00010\u0017\u0012\f\u0010\u0018\u001A\b\u0012\u0004\u0012\u00020\r0\u0019\u00A2\u0006\u0002\u0010\u001AJ\r\u0010\u000E\u001A\u00020\u000FH\u0007\u00A2\u0006\u0002\b0J\r\u0010\u001C\u001A\u00020\u001DH\u0007\u00A2\u0006\u0002\b1J\u000F\u0010\u0011\u001A\u0004\u0018\u00010\u0000H\u0007\u00A2\u0006\u0002\b2J\f\u00103\u001A\b\u0012\u0004\u0012\u00020504J\b\u00106\u001A\u000207H\u0016J\r\u0010\b\u001A\u00020\tH\u0007\u00A2\u0006\u0002\b8J\u000F\u0010\n\u001A\u0004\u0018\u00010\u000BH\u0007\u00A2\u0006\u0002\b9J\u001E\u0010:\u001A\u0004\u0018\u00010\u00072\u0006\u0010;\u001A\u00020\u00072\n\b\u0002\u0010<\u001A\u0004\u0018\u00010\u0007H\u0007J\r\u0010\f\u001A\u00020\rH\u0007\u00A2\u0006\u0002\b=J\u0014\u0010\f\u001A\b\u0012\u0004\u0012\u00020\u0007042\u0006\u0010;\u001A\u00020\u0007J\r\u0010\u0006\u001A\u00020\u0007H\u0007\u00A2\u0006\u0002\b>J\u000F\u0010\u0010\u001A\u0004\u0018\u00010\u0000H\u0007\u00A2\u0006\u0002\b?J\u0006\u0010@\u001A\u00020AJ\u000E\u0010B\u001A\u00020\u000F2\u0006\u0010C\u001A\u00020\u0014J\u000F\u0010\u0012\u001A\u0004\u0018\u00010\u0000H\u0007\u00A2\u0006\u0002\bDJ\r\u0010\u0004\u001A\u00020\u0005H\u0007\u00A2\u0006\u0002\bEJ\r\u0010\u0015\u001A\u00020\u0014H\u0007\u00A2\u0006\u0002\bFJ\r\u0010\u0002\u001A\u00020\u0003H\u0007\u00A2\u0006\u0002\bGJ\r\u0010\u0013\u001A\u00020\u0014H\u0007\u00A2\u0006\u0002\bHJ\b\u0010I\u001A\u00020\u0007H\u0016J\u0006\u0010J\u001A\u00020\rR\u0013\u0010\u000E\u001A\u00020\u000F8\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\u001BR\u0011\u0010\u001C\u001A\u00020\u001D8G\u00A2\u0006\u0006\u001A\u0004\b\u001C\u0010\u001ER\u0015\u0010\u0011\u001A\u0004\u0018\u00010\u00008\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0011\u0010\u001FR\u0013\u0010\b\u001A\u00020\t8\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\b\u0010 R\u0018\u0010\u0016\u001A\u0004\u0018\u00010\u00178\u0001X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0016\u0010!R\u0015\u0010\n\u001A\u0004\u0018\u00010\u000B8\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\n\u0010\"R\u0013\u0010\f\u001A\u00020\r8\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\f\u0010#R\u0011\u0010$\u001A\u00020%\u00A2\u0006\b\n\u0000\u001A\u0004\b$\u0010&R\u0011\u0010\'\u001A\u00020%\u00A2\u0006\b\n\u0000\u001A\u0004\b\'\u0010&R\u001C\u0010(\u001A\u0004\u0018\u00010\u001DX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b)\u0010\u001E\"\u0004\b*\u0010+R\u0013\u0010\u0006\u001A\u00020\u00078\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0006\u0010,R\u0015\u0010\u0010\u001A\u0004\u0018\u00010\u00008\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0010\u0010\u001FR\u0015\u0010\u0012\u001A\u0004\u0018\u00010\u00008\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0012\u0010\u001FR\u0013\u0010\u0004\u001A\u00020\u00058\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0004\u0010-R\u0013\u0010\u0015\u001A\u00020\u00148\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0015\u0010.R\u0013\u0010\u0002\u001A\u00020\u00038\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0002\u0010/R\u0013\u0010\u0013\u001A\u00020\u00148\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010.R\u0014\u0010\u0018\u001A\b\u0012\u0004\u0012\u00020\r0\u0019X\u0082\u000E\u00A2\u0006\u0002\n\u0000\u00A8\u0006L"}, d2 = {"Lokhttp3/Response;", "Ljava/io/Closeable;", "request", "Lokhttp3/Request;", "protocol", "Lokhttp3/Protocol;", "message", "", "code", "", "handshake", "Lokhttp3/Handshake;", "headers", "Lokhttp3/Headers;", "body", "Lokhttp3/ResponseBody;", "networkResponse", "cacheResponse", "priorResponse", "sentRequestAtMillis", "", "receivedResponseAtMillis", "exchange", "Lokhttp3/internal/connection/Exchange;", "trailersFn", "Lkotlin/Function0;", "(Lokhttp3/Request;Lokhttp3/Protocol;Ljava/lang/String;ILokhttp3/Handshake;Lokhttp3/Headers;Lokhttp3/ResponseBody;Lokhttp3/Response;Lokhttp3/Response;Lokhttp3/Response;JJLokhttp3/internal/connection/Exchange;Lkotlin/jvm/functions/Function0;)V", "()Lokhttp3/ResponseBody;", "cacheControl", "Lokhttp3/CacheControl;", "()Lokhttp3/CacheControl;", "()Lokhttp3/Response;", "()I", "()Lokhttp3/internal/connection/Exchange;", "()Lokhttp3/Handshake;", "()Lokhttp3/Headers;", "isRedirect", "", "()Z", "isSuccessful", "lazyCacheControl", "getLazyCacheControl$okhttp", "setLazyCacheControl$okhttp", "(Lokhttp3/CacheControl;)V", "()Ljava/lang/String;", "()Lokhttp3/Protocol;", "()J", "()Lokhttp3/Request;", "-deprecated_body", "-deprecated_cacheControl", "-deprecated_cacheResponse", "challenges", "", "Lokhttp3/Challenge;", "close", "", "-deprecated_code", "-deprecated_handshake", "header", "name", "defaultValue", "-deprecated_headers", "-deprecated_message", "-deprecated_networkResponse", "newBuilder", "Lokhttp3/Response$Builder;", "peekBody", "byteCount", "-deprecated_priorResponse", "-deprecated_protocol", "-deprecated_receivedResponseAtMillis", "-deprecated_request", "-deprecated_sentRequestAtMillis", "toString", "trailers", "Builder", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class Response implements Closeable, AutoCloseable {
    @Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000E\n\u0002\b\u000B\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B\u0007\b\u0016\u00A2\u0006\u0002\u0010\u0002B\u000F\b\u0010\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u00A2\u0006\u0002\u0010\u0005J\u0018\u0010P\u001A\u00020\u00002\u0006\u0010Q\u001A\u00020)2\u0006\u0010R\u001A\u00020)H\u0016J\u0010\u0010\u0006\u001A\u00020\u00002\u0006\u0010\u0006\u001A\u00020\u0007H\u0016J\b\u0010S\u001A\u00020\u0004H\u0016J\u0012\u0010\f\u001A\u00020\u00002\b\u0010\f\u001A\u0004\u0018\u00010\u0004H\u0016J\u0010\u0010\u0010\u001A\u00020\u00002\u0006\u0010\u0010\u001A\u00020\u0011H\u0016J\u0012\u0010\u001C\u001A\u00020\u00002\b\u0010\u001C\u001A\u0004\u0018\u00010\u001DH\u0016J\u0018\u0010T\u001A\u00020\u00002\u0006\u0010Q\u001A\u00020)2\u0006\u0010R\u001A\u00020)H\u0016J\u0010\u0010\"\u001A\u00020\u00002\u0006\u0010\"\u001A\u00020KH\u0016J\u0015\u0010U\u001A\u00020V2\u0006\u0010\u0016\u001A\u00020\u0017H\u0000\u00A2\u0006\u0002\bWJ\u0010\u0010(\u001A\u00020\u00002\u0006\u0010(\u001A\u00020)H\u0016J\u0012\u0010.\u001A\u00020\u00002\b\u0010.\u001A\u0004\u0018\u00010\u0004H\u0016J\u0012\u00101\u001A\u00020\u00002\b\u00101\u001A\u0004\u0018\u00010\u0004H\u0016J\u0010\u00104\u001A\u00020\u00002\u0006\u00104\u001A\u000205H\u0016J\u0010\u0010:\u001A\u00020\u00002\u0006\u0010:\u001A\u00020;H\u0016J\u0010\u0010X\u001A\u00020\u00002\u0006\u0010Q\u001A\u00020)H\u0016J\u0010\u0010@\u001A\u00020\u00002\u0006\u0010@\u001A\u00020AH\u0016J\u0010\u0010F\u001A\u00020\u00002\u0006\u0010F\u001A\u00020;H\u0016J\u0016\u0010Y\u001A\u00020\u00002\f\u0010I\u001A\b\u0012\u0004\u0012\u00020K0JH\u0016R\u001A\u0010\u0006\u001A\u00020\u0007X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000BR\u001C\u0010\f\u001A\u0004\u0018\u00010\u0004X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\r\u0010\u000E\"\u0004\b\u000F\u0010\u0005R\u001A\u0010\u0010\u001A\u00020\u0011X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001C\u0010\u0016\u001A\u0004\u0018\u00010\u0017X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0018\u0010\u0019\"\u0004\b\u001A\u0010\u001BR\u001C\u0010\u001C\u001A\u0004\u0018\u00010\u001DX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u001E\u0010\u001F\"\u0004\b \u0010!R\u001A\u0010\"\u001A\u00020#X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b$\u0010%\"\u0004\b&\u0010\'R\u001C\u0010(\u001A\u0004\u0018\u00010)X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001C\u0010.\u001A\u0004\u0018\u00010\u0004X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b/\u0010\u000E\"\u0004\b0\u0010\u0005R\u001C\u00101\u001A\u0004\u0018\u00010\u0004X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b2\u0010\u000E\"\u0004\b3\u0010\u0005R\u001C\u00104\u001A\u0004\u0018\u000105X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b6\u00107\"\u0004\b8\u00109R\u001A\u0010:\u001A\u00020;X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b<\u0010=\"\u0004\b>\u0010?R\u001C\u0010@\u001A\u0004\u0018\u00010AX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\bB\u0010C\"\u0004\bD\u0010ER\u001A\u0010F\u001A\u00020;X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\bG\u0010=\"\u0004\bH\u0010?R \u0010I\u001A\b\u0012\u0004\u0012\u00020K0JX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\bL\u0010M\"\u0004\bN\u0010O\u00A8\u0006Z"}, d2 = {"Lokhttp3/Response$Builder;", "", "()V", "response", "Lokhttp3/Response;", "(Lokhttp3/Response;)V", "body", "Lokhttp3/ResponseBody;", "getBody$okhttp", "()Lokhttp3/ResponseBody;", "setBody$okhttp", "(Lokhttp3/ResponseBody;)V", "cacheResponse", "getCacheResponse$okhttp", "()Lokhttp3/Response;", "setCacheResponse$okhttp", "code", "", "getCode$okhttp", "()I", "setCode$okhttp", "(I)V", "exchange", "Lokhttp3/internal/connection/Exchange;", "getExchange$okhttp", "()Lokhttp3/internal/connection/Exchange;", "setExchange$okhttp", "(Lokhttp3/internal/connection/Exchange;)V", "handshake", "Lokhttp3/Handshake;", "getHandshake$okhttp", "()Lokhttp3/Handshake;", "setHandshake$okhttp", "(Lokhttp3/Handshake;)V", "headers", "Lokhttp3/Headers$Builder;", "getHeaders$okhttp", "()Lokhttp3/Headers$Builder;", "setHeaders$okhttp", "(Lokhttp3/Headers$Builder;)V", "message", "", "getMessage$okhttp", "()Ljava/lang/String;", "setMessage$okhttp", "(Ljava/lang/String;)V", "networkResponse", "getNetworkResponse$okhttp", "setNetworkResponse$okhttp", "priorResponse", "getPriorResponse$okhttp", "setPriorResponse$okhttp", "protocol", "Lokhttp3/Protocol;", "getProtocol$okhttp", "()Lokhttp3/Protocol;", "setProtocol$okhttp", "(Lokhttp3/Protocol;)V", "receivedResponseAtMillis", "", "getReceivedResponseAtMillis$okhttp", "()J", "setReceivedResponseAtMillis$okhttp", "(J)V", "request", "Lokhttp3/Request;", "getRequest$okhttp", "()Lokhttp3/Request;", "setRequest$okhttp", "(Lokhttp3/Request;)V", "sentRequestAtMillis", "getSentRequestAtMillis$okhttp", "setSentRequestAtMillis$okhttp", "trailersFn", "Lkotlin/Function0;", "Lokhttp3/Headers;", "getTrailersFn$okhttp", "()Lkotlin/jvm/functions/Function0;", "setTrailersFn$okhttp", "(Lkotlin/jvm/functions/Function0;)V", "addHeader", "name", "value", "build", "header", "initExchange", "", "initExchange$okhttp", "removeHeader", "trailers", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static class Builder {
        private ResponseBody body;
        private Response cacheResponse;
        private int code;
        private Exchange exchange;
        private Handshake handshake;
        private okhttp3.Headers.Builder headers;
        private String message;
        private Response networkResponse;
        private Response priorResponse;
        private Protocol protocol;
        private long receivedResponseAtMillis;
        private Request request;
        private long sentRequestAtMillis;
        private Function0 trailersFn;

        public Builder() {
            this.code = -1;
            this.body = _UtilCommonKt.getCommonEmptyResponse();
            this.trailersFn = okhttp3.Response.Builder.trailersFn.1.INSTANCE;
            this.headers = new okhttp3.Headers.Builder();

            @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lokhttp3/Headers;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = 0x30)
            final class okhttp3.Response.Builder.trailersFn.1 extends Lambda implements Function0 {
                public static final okhttp3.Response.Builder.trailersFn.1 INSTANCE;

                static {
                    okhttp3.Response.Builder.trailersFn.1.INSTANCE = new okhttp3.Response.Builder.trailersFn.1();
                }

                okhttp3.Response.Builder.trailersFn.1() {
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final Headers invoke() {
                    return Headers.Companion.of(new String[0]);
                }
            }

        }

        public Builder(Response response0) {
            Intrinsics.checkNotNullParameter(response0, "response");
            super();
            this.code = -1;
            this.body = _UtilCommonKt.getCommonEmptyResponse();
            this.trailersFn = okhttp3.Response.Builder.trailersFn.1.INSTANCE;
            this.request = response0.request();
            this.protocol = response0.protocol();
            this.code = response0.code();
            this.message = response0.message();
            this.handshake = response0.handshake();
            this.headers = response0.headers().newBuilder();
            this.body = response0.body();
            this.networkResponse = response0.networkResponse();
            this.cacheResponse = response0.cacheResponse();
            this.priorResponse = response0.priorResponse();
            this.sentRequestAtMillis = response0.sentRequestAtMillis();
            this.receivedResponseAtMillis = response0.receivedResponseAtMillis();
            this.exchange = response0.exchange();
            this.trailersFn = response0.trailersFn;
        }

        public Builder addHeader(String s, String s1) {
            Intrinsics.checkNotNullParameter(s, "name");
            Intrinsics.checkNotNullParameter(s1, "value");
            return _ResponseCommonKt.commonAddHeader(this, s, s1);
        }

        public Builder body(ResponseBody responseBody0) {
            Intrinsics.checkNotNullParameter(responseBody0, "body");
            return _ResponseCommonKt.commonBody(this, responseBody0);
        }

        public Response build() {
            int v = this.code;
            if(v < 0) {
                throw new IllegalStateException(("code < 0: " + this.code).toString());
            }
            Request request0 = this.request;
            if(request0 == null) {
                throw new IllegalStateException("request == null");
            }
            Protocol protocol0 = this.protocol;
            if(protocol0 == null) {
                throw new IllegalStateException("protocol == null");
            }
            String s = this.message;
            if(s == null) {
                throw new IllegalStateException("message == null");
            }
            return new Response(request0, protocol0, s, v, this.handshake, this.headers.build(), this.body, this.networkResponse, this.cacheResponse, this.priorResponse, this.sentRequestAtMillis, this.receivedResponseAtMillis, this.exchange, this.trailersFn);
        }

        public Builder cacheResponse(Response response0) {
            return _ResponseCommonKt.commonCacheResponse(this, response0);
        }

        public Builder code(int v) {
            return _ResponseCommonKt.commonCode(this, v);
        }

        public final ResponseBody getBody$okhttp() {
            return this.body;
        }

        public final Response getCacheResponse$okhttp() {
            return this.cacheResponse;
        }

        public final int getCode$okhttp() {
            return this.code;
        }

        public final Exchange getExchange$okhttp() {
            return this.exchange;
        }

        public final Handshake getHandshake$okhttp() {
            return this.handshake;
        }

        public final okhttp3.Headers.Builder getHeaders$okhttp() {
            return this.headers;
        }

        public final String getMessage$okhttp() {
            return this.message;
        }

        public final Response getNetworkResponse$okhttp() {
            return this.networkResponse;
        }

        public final Response getPriorResponse$okhttp() {
            return this.priorResponse;
        }

        public final Protocol getProtocol$okhttp() {
            return this.protocol;
        }

        public final long getReceivedResponseAtMillis$okhttp() {
            return this.receivedResponseAtMillis;
        }

        public final Request getRequest$okhttp() {
            return this.request;
        }

        public final long getSentRequestAtMillis$okhttp() {
            return this.sentRequestAtMillis;
        }

        public final Function0 getTrailersFn$okhttp() {
            return this.trailersFn;
        }

        public Builder handshake(Handshake handshake0) {
            this.handshake = handshake0;
            return this;
        }

        public Builder header(String s, String s1) {
            Intrinsics.checkNotNullParameter(s, "name");
            Intrinsics.checkNotNullParameter(s1, "value");
            return _ResponseCommonKt.commonHeader(this, s, s1);
        }

        public Builder headers(Headers headers0) {
            Intrinsics.checkNotNullParameter(headers0, "headers");
            return _ResponseCommonKt.commonHeaders(this, headers0);
        }

        public final void initExchange$okhttp(Exchange exchange0) {
            Intrinsics.checkNotNullParameter(exchange0, "exchange");
            this.exchange = exchange0;
            this.trailersFn = new Function0() {
                final Exchange $exchange;

                {
                    this.$exchange = exchange0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final Headers invoke() {
                    return this.$exchange.trailers();
                }
            };
        }

        public Builder message(String s) {
            Intrinsics.checkNotNullParameter(s, "message");
            return _ResponseCommonKt.commonMessage(this, s);
        }

        public Builder networkResponse(Response response0) {
            return _ResponseCommonKt.commonNetworkResponse(this, response0);
        }

        public Builder priorResponse(Response response0) {
            return _ResponseCommonKt.commonPriorResponse(this, response0);
        }

        public Builder protocol(Protocol protocol0) {
            Intrinsics.checkNotNullParameter(protocol0, "protocol");
            return _ResponseCommonKt.commonProtocol(this, protocol0);
        }

        public Builder receivedResponseAtMillis(long v) {
            this.receivedResponseAtMillis = v;
            return this;
        }

        public Builder removeHeader(String s) {
            Intrinsics.checkNotNullParameter(s, "name");
            return _ResponseCommonKt.commonRemoveHeader(this, s);
        }

        public Builder request(Request request0) {
            Intrinsics.checkNotNullParameter(request0, "request");
            return _ResponseCommonKt.commonRequest(this, request0);
        }

        public Builder sentRequestAtMillis(long v) {
            this.sentRequestAtMillis = v;
            return this;
        }

        public final void setBody$okhttp(ResponseBody responseBody0) {
            Intrinsics.checkNotNullParameter(responseBody0, "<set-?>");
            this.body = responseBody0;
        }

        public final void setCacheResponse$okhttp(Response response0) {
            this.cacheResponse = response0;
        }

        public final void setCode$okhttp(int v) {
            this.code = v;
        }

        public final void setExchange$okhttp(Exchange exchange0) {
            this.exchange = exchange0;
        }

        public final void setHandshake$okhttp(Handshake handshake0) {
            this.handshake = handshake0;
        }

        public final void setHeaders$okhttp(okhttp3.Headers.Builder headers$Builder0) {
            Intrinsics.checkNotNullParameter(headers$Builder0, "<set-?>");
            this.headers = headers$Builder0;
        }

        public final void setMessage$okhttp(String s) {
            this.message = s;
        }

        public final void setNetworkResponse$okhttp(Response response0) {
            this.networkResponse = response0;
        }

        public final void setPriorResponse$okhttp(Response response0) {
            this.priorResponse = response0;
        }

        public final void setProtocol$okhttp(Protocol protocol0) {
            this.protocol = protocol0;
        }

        public final void setReceivedResponseAtMillis$okhttp(long v) {
            this.receivedResponseAtMillis = v;
        }

        public final void setRequest$okhttp(Request request0) {
            this.request = request0;
        }

        public final void setSentRequestAtMillis$okhttp(long v) {
            this.sentRequestAtMillis = v;
        }

        public final void setTrailersFn$okhttp(Function0 function00) {
            Intrinsics.checkNotNullParameter(function00, "<set-?>");
            this.trailersFn = function00;
        }

        public Builder trailers(Function0 function00) {
            Intrinsics.checkNotNullParameter(function00, "trailersFn");
            return _ResponseCommonKt.commonTrailers(this, function00);
        }
    }

    private final ResponseBody body;
    private final Response cacheResponse;
    private final int code;
    private final Exchange exchange;
    private final Handshake handshake;
    private final Headers headers;
    private final boolean isRedirect;
    private final boolean isSuccessful;
    private CacheControl lazyCacheControl;
    private final String message;
    private final Response networkResponse;
    private final Response priorResponse;
    private final Protocol protocol;
    private final long receivedResponseAtMillis;
    private final Request request;
    private final long sentRequestAtMillis;
    private Function0 trailersFn;

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "body", imports = {}))
    public final ResponseBody -deprecated_body() {
        return this.body;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "cacheControl", imports = {}))
    public final CacheControl -deprecated_cacheControl() {
        return this.cacheControl();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "cacheResponse", imports = {}))
    public final Response -deprecated_cacheResponse() {
        return this.cacheResponse;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "code", imports = {}))
    public final int -deprecated_code() {
        return this.code;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "handshake", imports = {}))
    public final Handshake -deprecated_handshake() {
        return this.handshake;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "headers", imports = {}))
    public final Headers -deprecated_headers() {
        return this.headers;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "message", imports = {}))
    public final String -deprecated_message() {
        return this.message;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "networkResponse", imports = {}))
    public final Response -deprecated_networkResponse() {
        return this.networkResponse;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "priorResponse", imports = {}))
    public final Response -deprecated_priorResponse() {
        return this.priorResponse;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "protocol", imports = {}))
    public final Protocol -deprecated_protocol() {
        return this.protocol;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "receivedResponseAtMillis", imports = {}))
    public final long -deprecated_receivedResponseAtMillis() {
        return this.receivedResponseAtMillis;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "request", imports = {}))
    public final Request -deprecated_request() {
        return this.request;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "sentRequestAtMillis", imports = {}))
    public final long -deprecated_sentRequestAtMillis() {
        return this.sentRequestAtMillis;
    }

    public Response(Request request0, Protocol protocol0, String s, int v, Handshake handshake0, Headers headers0, ResponseBody responseBody0, Response response0, Response response1, Response response2, long v1, long v2, Exchange exchange0, Function0 function00) {
        Intrinsics.checkNotNullParameter(request0, "request");
        Intrinsics.checkNotNullParameter(protocol0, "protocol");
        Intrinsics.checkNotNullParameter(s, "message");
        Intrinsics.checkNotNullParameter(headers0, "headers");
        Intrinsics.checkNotNullParameter(responseBody0, "body");
        Intrinsics.checkNotNullParameter(function00, "trailersFn");
        super();
        this.request = request0;
        this.protocol = protocol0;
        this.message = s;
        this.code = v;
        this.handshake = handshake0;
        this.headers = headers0;
        this.body = responseBody0;
        this.networkResponse = response0;
        this.cacheResponse = response1;
        this.priorResponse = response2;
        this.sentRequestAtMillis = v1;
        this.receivedResponseAtMillis = v2;
        this.exchange = exchange0;
        this.trailersFn = function00;
        this.isSuccessful = _ResponseCommonKt.getCommonIsSuccessful(this);
        this.isRedirect = _ResponseCommonKt.getCommonIsRedirect(this);
    }

    public final ResponseBody body() {
        return this.body;
    }

    public final CacheControl cacheControl() {
        return _ResponseCommonKt.getCommonCacheControl(this);
    }

    public final Response cacheResponse() {
        return this.cacheResponse;
    }

    public final List challenges() {
        Headers headers0 = this.headers;
        switch(this.code) {
            case 401: {
                return HttpHeaders.parseChallenges(headers0, "WWW-Authenticate");
            }
            case 407: {
                return HttpHeaders.parseChallenges(headers0, "Proxy-Authenticate");
            }
            default: {
                return CollectionsKt.emptyList();
            }
        }
    }

    @Override
    public void close() {
        _ResponseCommonKt.commonClose(this);
    }

    public final int code() {
        return this.code;
    }

    public final Exchange exchange() {
        return this.exchange;
    }

    public final CacheControl getLazyCacheControl$okhttp() {
        return this.lazyCacheControl;
    }

    public final Handshake handshake() {
        return this.handshake;
    }

    public final String header(String s) {
        Intrinsics.checkNotNullParameter(s, "name");
        return Response.header$default(this, s, null, 2, null);
    }

    public final String header(String s, String s1) {
        Intrinsics.checkNotNullParameter(s, "name");
        return _ResponseCommonKt.commonHeader(this, s, s1);
    }

    public static String header$default(Response response0, String s, String s1, int v, Object object0) {
        if((v & 2) != 0) {
            s1 = null;
        }
        return response0.header(s, s1);
    }

    public final List headers(String s) {
        Intrinsics.checkNotNullParameter(s, "name");
        return _ResponseCommonKt.commonHeaders(this, s);
    }

    public final Headers headers() {
        return this.headers;
    }

    public final boolean isRedirect() {
        return this.isRedirect;
    }

    public final boolean isSuccessful() {
        return this.isSuccessful;
    }

    public final String message() {
        return this.message;
    }

    public final Response networkResponse() {
        return this.networkResponse;
    }

    public final Builder newBuilder() {
        return _ResponseCommonKt.commonNewBuilder(this);
    }

    public final ResponseBody peekBody(long v) throws IOException {
        BufferedSource bufferedSource0 = this.body.source().peek();
        Buffer buffer0 = new Buffer();
        bufferedSource0.request(v);
        buffer0.write(bufferedSource0, Math.min(v, bufferedSource0.getBuffer().size()));
        MediaType mediaType0 = this.body.contentType();
        return ResponseBody.Companion.create(buffer0, mediaType0, buffer0.size());
    }

    public final Response priorResponse() {
        return this.priorResponse;
    }

    public final Protocol protocol() {
        return this.protocol;
    }

    public final long receivedResponseAtMillis() {
        return this.receivedResponseAtMillis;
    }

    public final Request request() {
        return this.request;
    }

    public final long sentRequestAtMillis() {
        return this.sentRequestAtMillis;
    }

    public final void setLazyCacheControl$okhttp(CacheControl cacheControl0) {
        this.lazyCacheControl = cacheControl0;
    }

    @Override
    public String toString() {
        return _ResponseCommonKt.commonToString(this);
    }

    public final Headers trailers() throws IOException {
        return (Headers)this.trailersFn.invoke();
    }
}

