package okhttp3.internal.cache;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.Headers.Builder;
import okhttp3.Headers;
import okhttp3.Interceptor.Chain;
import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal._ResponseCommonKt;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.RealResponseBody;
import okio.Buffer;
import okio.Okio;
import okio.Sink;
import okio.Timeout;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000F2\u00020\u0001:\u0001\u000FB\u000F\u0012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u001A\u0010\u0007\u001A\u00020\b2\b\u0010\t\u001A\u0004\u0018\u00010\n2\u0006\u0010\u000B\u001A\u00020\bH\u0002J\u0010\u0010\f\u001A\u00020\b2\u0006\u0010\r\u001A\u00020\u000EH\u0016R\u0016\u0010\u0002\u001A\u0004\u0018\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lokhttp3/internal/cache/CacheInterceptor;", "Lokhttp3/Interceptor;", "cache", "Lokhttp3/Cache;", "(Lokhttp3/Cache;)V", "getCache$okhttp", "()Lokhttp3/Cache;", "cacheWritingResponse", "Lokhttp3/Response;", "cacheRequest", "Lokhttp3/internal/cache/CacheRequest;", "response", "intercept", "chain", "Lokhttp3/Interceptor$Chain;", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class CacheInterceptor implements Interceptor {
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00042\u0006\u0010\u0006\u001A\u00020\u0004H\u0002J\u0010\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0002J\u0010\u0010\u000B\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0002¨\u0006\f"}, d2 = {"Lokhttp3/internal/cache/CacheInterceptor$Companion;", "", "()V", "combine", "Lokhttp3/Headers;", "cachedHeaders", "networkHeaders", "isContentSpecificHeader", "", "fieldName", "", "isEndToEnd", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        private final Headers combine(Headers headers0, Headers headers1) {
            Builder headers$Builder0 = new Builder();
            int v = headers0.size();
            for(int v2 = 0; v2 < v; ++v2) {
                String s = headers0.name(v2);
                String s1 = headers0.value(v2);
                if((!StringsKt.equals("Warning", s, true) || !StringsKt.startsWith$default(s1, "1", false, 2, null)) && (this.isContentSpecificHeader(s) || !this.isEndToEnd(s) || headers1.get(s) == null)) {
                    headers$Builder0.addLenient$okhttp(s, s1);
                }
            }
            int v3 = headers1.size();
            for(int v1 = 0; v1 < v3; ++v1) {
                String s2 = headers1.name(v1);
                if(!this.isContentSpecificHeader(s2) && this.isEndToEnd(s2)) {
                    headers$Builder0.addLenient$okhttp(s2, headers1.value(v1));
                }
            }
            return headers$Builder0.build();
        }

        // 去混淆评级： 低(30)
        private final boolean isContentSpecificHeader(String s) {
            return StringsKt.equals("Content-Length", s, true) || StringsKt.equals("Content-Encoding", s, true) || StringsKt.equals("Content-Type", s, true);
        }

        // 去混淆评级： 中等(80)
        private final boolean isEndToEnd(String s) {
            return !StringsKt.equals("Connection", s, true) && !StringsKt.equals("Keep-Alive", s, true) && !StringsKt.equals("Proxy-Authenticate", s, true) && !StringsKt.equals("Proxy-Authorization", s, true) && !StringsKt.equals("TE", s, true) && !StringsKt.equals("Trailers", s, true) && !StringsKt.equals("Transfer-Encoding", s, true) && !StringsKt.equals("Upgrade", s, true);
        }
    }

    public static final Companion Companion;
    private final Cache cache;

    static {
        CacheInterceptor.Companion = new Companion(null);
    }

    public CacheInterceptor(Cache cache0) {
        this.cache = cache0;
    }

    private final Response cacheWritingResponse(CacheRequest cacheRequest0, Response response0) throws IOException {
        if(cacheRequest0 == null) {
            return response0;
        }
        Sink sink0 = cacheRequest0.body();
        okhttp3.internal.cache.CacheInterceptor.cacheWritingResponse.cacheWritingSource.1 cacheInterceptor$cacheWritingResponse$cacheWritingSource$10 = new Object() {
            private boolean cacheRequestClosed;

            @Override  // okio.Source
            public void close() throws IOException {
                if(!this.cacheRequestClosed && !_UtilJvmKt.discard(this, 100, TimeUnit.MILLISECONDS)) {
                    this.cacheRequestClosed = true;
                    Okio.buffer(sink0).abort();
                }
                cacheRequest0.close();
            }

            @Override  // okio.Source
            public long read(Buffer buffer0, long v) throws IOException {
                long v1;
                Intrinsics.checkNotNullParameter(buffer0, "sink");
                try {
                    v1 = cacheRequest0.read(buffer0, v);
                }
                catch(IOException iOException0) {
                    if(!this.cacheRequestClosed) {
                        this.cacheRequestClosed = true;
                        Okio.buffer(sink0).abort();
                    }
                    throw iOException0;
                }
                if(v1 == -1L) {
                    if(!this.cacheRequestClosed) {
                        this.cacheRequestClosed = true;
                        this.$cacheBody.close();
                    }
                    return -1L;
                }
                buffer0.copyTo(this.$cacheBody.getBuffer(), buffer0.size() - v1, v1);
                this.$cacheBody.emitCompleteSegments();
                return v1;
            }

            @Override  // okio.Source
            public Timeout timeout() {
                return cacheRequest0.timeout();
            }
        };
        String s = Response.header$default(response0, "Content-Type", null, 2, null);
        long v = response0.body().contentLength();
        return response0.newBuilder().body(new RealResponseBody(s, v, Okio.buffer(cacheInterceptor$cacheWritingResponse$cacheWritingSource$10))).build();
    }

    public final Cache getCache$okhttp() {
        return this.cache;
    }

    @Override  // okhttp3.Interceptor
    public Response intercept(Chain interceptor$Chain0) throws IOException {
        Response response5;
        EventListener eventListener0;
        Intrinsics.checkNotNullParameter(interceptor$Chain0, "chain");
        Call call0 = interceptor$Chain0.call();
        Response response0 = null;
        Response response1 = this.cache == null ? null : this.cache.get$okhttp(interceptor$Chain0.request());
        CacheStrategy cacheStrategy0 = new Factory(System.currentTimeMillis(), interceptor$Chain0.request(), response1).compute();
        Request request0 = cacheStrategy0.getNetworkRequest();
        Response response2 = cacheStrategy0.getCacheResponse();
        Cache cache0 = this.cache;
        if(cache0 != null) {
            cache0.trackResponse$okhttp(cacheStrategy0);
        }
        RealCall realCall0 = call0 instanceof RealCall ? ((RealCall)call0) : null;
        if(realCall0 == null) {
            eventListener0 = EventListener.NONE;
        }
        else {
            eventListener0 = realCall0.getEventListener$okhttp();
            if(eventListener0 == null) {
                eventListener0 = EventListener.NONE;
            }
        }
        if(response1 != null && response2 == null) {
            _UtilCommonKt.closeQuietly(response1.body());
        }
        if(request0 == null && response2 == null) {
            Response response3 = new okhttp3.Response.Builder().request(interceptor$Chain0.request()).protocol(Protocol.HTTP_1_1).code(504).message("Unsatisfiable Request (only-if-cached)").sentRequestAtMillis(-1L).receivedResponseAtMillis(System.currentTimeMillis()).build();
            eventListener0.satisfactionFailure(call0, response3);
            return response3;
        }
        if(request0 == null) {
            Intrinsics.checkNotNull(response2);
            Response response4 = response2.newBuilder().cacheResponse(_ResponseCommonKt.stripBody(response2)).build();
            eventListener0.cacheHit(call0, response4);
            return response4;
        }
        if(response2 != null) {
            eventListener0.cacheConditionalHit(call0, response2);
        }
        else if(this.cache != null) {
            eventListener0.cacheMiss(call0);
        }
        try {
            response5 = interceptor$Chain0.proceed(request0);
        }
        catch(Throwable throwable0) {
            if(response1 != null) {
                _UtilCommonKt.closeQuietly(response1.body());
            }
            throw throwable0;
        }
        if(response5 == null && response1 != null) {
            _UtilCommonKt.closeQuietly(response1.body());
        }
        if(response2 != null) {
            if(response5 != null && response5.code() == 304) {
                Response response6 = response2.newBuilder().headers(CacheInterceptor.Companion.combine(response2.headers(), response5.headers())).sentRequestAtMillis(response5.sentRequestAtMillis()).receivedResponseAtMillis(response5.receivedResponseAtMillis()).cacheResponse(_ResponseCommonKt.stripBody(response2)).networkResponse(_ResponseCommonKt.stripBody(response5)).build();
                response5.body().close();
                Intrinsics.checkNotNull(this.cache);
                this.cache.trackConditionalCacheHit$okhttp();
                this.cache.update$okhttp(response2, response6);
                eventListener0.cacheHit(call0, response6);
                return response6;
            }
            _UtilCommonKt.closeQuietly(response2.body());
        }
        Intrinsics.checkNotNull(response5);
        okhttp3.Response.Builder response$Builder0 = response5.newBuilder();
        if(response2 != null) {
            response0 = _ResponseCommonKt.stripBody(response2);
        }
        Response response7 = response$Builder0.cacheResponse(response0).networkResponse(_ResponseCommonKt.stripBody(response5)).build();
        if(this.cache != null) {
            if(HttpHeaders.promisesBody(response7) && CacheStrategy.Companion.isCacheable(response7, request0)) {
                Response response8 = this.cacheWritingResponse(this.cache.put$okhttp(response7), response7);
                if(response2 != null) {
                    eventListener0.cacheMiss(call0);
                }
                return response8;
            }
            if(HttpMethod.invalidatesCache(request0.method())) {
                try {
                    this.cache.remove$okhttp(request0);
                }
                catch(IOException unused_ex) {
                }
            }
        }
        return response7;
    }
}

