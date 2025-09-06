package okhttp3.internal.cache;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response.Builder;
import okhttp3.Response;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal.http.DatesKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u000B2\u00020\u0001:\u0002\u000B\fB\u001B\b\u0000\u0012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0013\u0010\u0004\u001A\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001A\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lokhttp3/internal/cache/CacheStrategy;", "", "networkRequest", "Lokhttp3/Request;", "cacheResponse", "Lokhttp3/Response;", "(Lokhttp3/Request;Lokhttp3/Response;)V", "getCacheResponse", "()Lokhttp3/Response;", "getNetworkRequest", "()Lokhttp3/Request;", "Companion", "Factory", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class CacheStrategy {
    @Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b¨\u0006\t"}, d2 = {"Lokhttp3/internal/cache/CacheStrategy$Companion;", "", "()V", "isCacheable", "", "response", "Lokhttp3/Response;", "request", "Lokhttp3/Request;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final boolean isCacheable(Response response0, Request request0) {
            Intrinsics.checkNotNullParameter(response0, "response");
            Intrinsics.checkNotNullParameter(request0, "request");
            switch(response0.code()) {
                case 302: 
                case 307: {
                    return Response.header$default(response0, "Expires", null, 2, null) != null || response0.cacheControl().maxAgeSeconds() != -1 || response0.cacheControl().isPublic() || response0.cacheControl().isPrivate() ? !response0.cacheControl().noStore() && !request0.cacheControl().noStore() : false;
                }
                case 200: 
                case 203: 
                case 204: 
                case 300: 
                case 301: 
                case 308: 
                case 404: 
                case 405: 
                case 410: 
                case 414: 
                case 501: {
                    return !response0.cacheControl().noStore() && !request0.cacheControl().noStore();
                }
                default: {
                    return false;
                }
            }
        }
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001F\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\b\u0010\u0006\u001A\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0017\u001A\u00020\u0003H\u0002J\u0006\u0010\u0018\u001A\u00020\u0019J\b\u0010\u001A\u001A\u00020\u0019H\u0002J\b\u0010\u001B\u001A\u00020\u0003H\u0002J\u0010\u0010\u001C\u001A\u00020\u001D2\u0006\u0010\u0004\u001A\u00020\u0005H\u0002J\b\u0010\u001E\u001A\u00020\u001DH\u0002R\u000E\u0010\t\u001A\u00020\nX\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001A\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000B\u001A\u0004\u0018\u00010\fX\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001A\u0004\u0018\u00010\u000EX\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\u000F\u001A\u0004\u0018\u00010\u000EX\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001A\u0004\u0018\u00010\fX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0011\u001A\u00020\u0003X\u0082\u000E¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001A\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0012\u0010\u0013R\u000E\u0010\u0014\u001A\u00020\u0003X\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001A\u0004\u0018\u00010\u000EX\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001A\u0004\u0018\u00010\fX\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u001F"}, d2 = {"Lokhttp3/internal/cache/CacheStrategy$Factory;", "", "nowMillis", "", "request", "Lokhttp3/Request;", "cacheResponse", "Lokhttp3/Response;", "(JLokhttp3/Request;Lokhttp3/Response;)V", "ageSeconds", "", "etag", "", "expires", "Ljava/util/Date;", "lastModified", "lastModifiedString", "receivedResponseMillis", "getRequest$okhttp", "()Lokhttp3/Request;", "sentRequestMillis", "servedDate", "servedDateString", "cacheResponseAge", "compute", "Lokhttp3/internal/cache/CacheStrategy;", "computeCandidate", "computeFreshnessLifetime", "hasConditions", "", "isFreshnessLifetimeHeuristic", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Factory {
        private int ageSeconds;
        private final Response cacheResponse;
        private String etag;
        private Date expires;
        private Date lastModified;
        private String lastModifiedString;
        private final long nowMillis;
        private long receivedResponseMillis;
        private final Request request;
        private long sentRequestMillis;
        private Date servedDate;
        private String servedDateString;

        public Factory(long v, Request request0, Response response0) {
            Intrinsics.checkNotNullParameter(request0, "request");
            super();
            this.nowMillis = v;
            this.request = request0;
            this.cacheResponse = response0;
            this.ageSeconds = -1;
            if(response0 != null) {
                this.sentRequestMillis = response0.sentRequestAtMillis();
                this.receivedResponseMillis = response0.receivedResponseAtMillis();
                Headers headers0 = response0.headers();
                int v1 = headers0.size();
                for(int v2 = 0; v2 < v1; ++v2) {
                    String s = headers0.name(v2);
                    String s1 = headers0.value(v2);
                    if(StringsKt.equals(s, "Date", true)) {
                        this.servedDate = DatesKt.toHttpDateOrNull(s1);
                        this.servedDateString = s1;
                    }
                    else if(StringsKt.equals(s, "Expires", true)) {
                        this.expires = DatesKt.toHttpDateOrNull(s1);
                    }
                    else if(StringsKt.equals(s, "Last-Modified", true)) {
                        this.lastModified = DatesKt.toHttpDateOrNull(s1);
                        this.lastModifiedString = s1;
                    }
                    else if(StringsKt.equals(s, "ETag", true)) {
                        this.etag = s1;
                    }
                    else if(StringsKt.equals(s, "Age", true)) {
                        this.ageSeconds = _UtilCommonKt.toNonNegativeInt(s1, -1);
                    }
                }
            }
        }

        private final long cacheResponseAge() {
            long v = this.servedDate == null ? 0L : Math.max(0L, this.receivedResponseMillis - this.servedDate.getTime());
            if(this.ageSeconds != -1) {
                v = Math.max(v, TimeUnit.SECONDS.toMillis(((long)this.ageSeconds)));
            }
            return v + (this.receivedResponseMillis - this.sentRequestMillis) + (this.nowMillis - this.receivedResponseMillis);
        }

        public final CacheStrategy compute() {
            CacheStrategy cacheStrategy0 = this.computeCandidate();
            return cacheStrategy0.getNetworkRequest() == null || !this.request.cacheControl().onlyIfCached() ? cacheStrategy0 : new CacheStrategy(null, null);
        }

        private final CacheStrategy computeCandidate() {
            String s1;
            if(this.cacheResponse == null) {
                return new CacheStrategy(this.request, null);
            }
            if(this.request.isHttps() && this.cacheResponse.handshake() == null) {
                return new CacheStrategy(this.request, null);
            }
            if(!CacheStrategy.Companion.isCacheable(this.cacheResponse, this.request)) {
                return new CacheStrategy(this.request, null);
            }
            CacheControl cacheControl0 = this.request.cacheControl();
            if(!cacheControl0.noCache() && !this.hasConditions(this.request)) {
                CacheControl cacheControl1 = this.cacheResponse.cacheControl();
                long v = this.cacheResponseAge();
                long v1 = this.computeFreshnessLifetime();
                if(cacheControl0.maxAgeSeconds() != -1) {
                    v1 = Math.min(v1, TimeUnit.SECONDS.toMillis(((long)cacheControl0.maxAgeSeconds())));
                }
                long v2 = 0L;
                long v3 = cacheControl0.minFreshSeconds() == -1 ? 0L : TimeUnit.SECONDS.toMillis(((long)cacheControl0.minFreshSeconds()));
                if(!cacheControl1.mustRevalidate() && cacheControl0.maxStaleSeconds() != -1) {
                    v2 = TimeUnit.SECONDS.toMillis(((long)cacheControl0.maxStaleSeconds()));
                }
                if(!cacheControl1.noCache()) {
                    long v4 = v3 + v;
                    if(v4 < v2 + v1) {
                        Builder response$Builder0 = this.cacheResponse.newBuilder();
                        if(v4 >= v1) {
                            response$Builder0.addHeader("Warning", "110 HttpURLConnection \"Response is stale\"");
                        }
                        if(v > 86400000L && this.isFreshnessLifetimeHeuristic()) {
                            response$Builder0.addHeader("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
                        }
                        return new CacheStrategy(null, response$Builder0.build());
                    }
                }
                String s = this.etag;
                if(s == null) {
                    if(this.lastModified != null) {
                        s = this.lastModifiedString;
                    }
                    else if(this.servedDate != null) {
                        s = this.servedDateString;
                    }
                    else {
                        return new CacheStrategy(this.request, null);
                    }
                    s1 = "If-Modified-Since";
                }
                else {
                    s1 = "If-None-Match";
                }
                okhttp3.Headers.Builder headers$Builder0 = this.request.headers().newBuilder();
                Intrinsics.checkNotNull(s);
                headers$Builder0.addLenient$okhttp(s1, s);
                return new CacheStrategy(this.request.newBuilder().headers(headers$Builder0.build()).build(), this.cacheResponse);
            }
            return new CacheStrategy(this.request, null);
        }

        private final long computeFreshnessLifetime() {
            Intrinsics.checkNotNull(this.cacheResponse);
            CacheControl cacheControl0 = this.cacheResponse.cacheControl();
            if(cacheControl0.maxAgeSeconds() != -1) {
                return TimeUnit.SECONDS.toMillis(((long)cacheControl0.maxAgeSeconds()));
            }
            Date date0 = this.expires;
            if(date0 != null) {
                long v = this.servedDate == null ? this.receivedResponseMillis : this.servedDate.getTime();
                long v1 = date0.getTime() - v;
                return v1 <= 0L ? 0L : v1;
            }
            if(this.lastModified != null && this.cacheResponse.request().url().query() == null) {
                long v2 = this.servedDate == null ? this.sentRequestMillis : this.servedDate.getTime();
                Date date1 = this.lastModified;
                Intrinsics.checkNotNull(date1);
                long v3 = v2 - date1.getTime();
                return v3 <= 0L ? 0L : v3 / 10L;
            }
            return 0L;
        }

        public final Request getRequest$okhttp() {
            return this.request;
        }

        private final boolean hasConditions(Request request0) {
            return request0.header("If-Modified-Since") != null || request0.header("If-None-Match") != null;
        }

        private final boolean isFreshnessLifetimeHeuristic() {
            Intrinsics.checkNotNull(this.cacheResponse);
            return this.cacheResponse.cacheControl().maxAgeSeconds() == -1 && this.expires == null;
        }
    }

    public static final Companion Companion;
    private final Response cacheResponse;
    private final Request networkRequest;

    static {
        CacheStrategy.Companion = new Companion(null);
    }

    public CacheStrategy(Request request0, Response response0) {
        this.networkRequest = request0;
        this.cacheResponse = response0;
    }

    public final Response getCacheResponse() {
        return this.cacheResponse;
    }

    public final Request getNetworkRequest() {
        return this.networkRequest;
    }
}

