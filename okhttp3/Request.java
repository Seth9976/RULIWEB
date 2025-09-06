package okhttp3;

import java.net.URL;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import okhttp3.internal._RequestCommonKt;
import okhttp3.internal._UtilCommonKt;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u00013B/\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001A\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001A\u00020\u0007\u0012\n\b\u0002\u0010\b\u001A\u0004\u0018\u00010\t\u00A2\u0006\u0002\u0010\nB\u000F\b\u0000\u0012\u0006\u0010\u000B\u001A\u00020\f\u00A2\u0006\u0002\u0010\rJ\u000F\u0010\b\u001A\u0004\u0018\u00010\tH\u0007\u00A2\u0006\u0002\b!J\r\u0010\u000F\u001A\u00020\u0010H\u0007\u00A2\u0006\u0002\b\"J\u0010\u0010#\u001A\u0004\u0018\u00010\u00072\u0006\u0010$\u001A\u00020\u0007J\r\u0010\u0004\u001A\u00020\u0005H\u0007\u00A2\u0006\u0002\b%J\u0014\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00070&2\u0006\u0010$\u001A\u00020\u0007J\r\u0010\u0006\u001A\u00020\u0007H\u0007\u00A2\u0006\u0002\b\'J\u0006\u0010(\u001A\u00020\fJ\b\u0010)\u001A\u0004\u0018\u00010\u0001J\u001E\u0010)\u001A\u0004\u0018\u0001H*\"\n\b\u0000\u0010*\u0018\u0001*\u00020\u0001H\u0087\b\u00A2\u0006\u0004\b+\u0010,J#\u0010)\u001A\u0004\u0018\u0001H*\"\u0004\b\u0000\u0010*2\u000E\u0010-\u001A\n\u0012\u0006\b\u0001\u0012\u0002H*0.\u00A2\u0006\u0002\u0010/J%\u0010)\u001A\u0004\u0018\u0001H*\"\b\b\u0000\u0010**\u00020\u00012\f\u0010-\u001A\b\u0012\u0004\u0012\u0002H*0\u001D\u00A2\u0006\u0002\u00100J\b\u00101\u001A\u00020\u0007H\u0016J\r\u0010\u0002\u001A\u00020\u0003H\u0007\u00A2\u0006\u0002\b2R\u0015\u0010\b\u001A\u0004\u0018\u00010\t8G\u00A2\u0006\b\n\u0000\u001A\u0004\b\b\u0010\u000ER\u0011\u0010\u000F\u001A\u00020\u00108G\u00A2\u0006\u0006\u001A\u0004\b\u000F\u0010\u0011R\u0013\u0010\u0004\u001A\u00020\u00058G\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0004\u0010\u0012R\u0011\u0010\u0013\u001A\u00020\u00148F\u00A2\u0006\u0006\u001A\u0004\b\u0013\u0010\u0015R\u001C\u0010\u0016\u001A\u0004\u0018\u00010\u0010X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0017\u0010\u0011\"\u0004\b\u0018\u0010\u0019R\u0013\u0010\u0006\u001A\u00020\u00078G\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0006\u0010\u001AR$\u0010\u001B\u001A\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001D\u0012\u0004\u0012\u00020\u00010\u001CX\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001E\u0010\u001FR\u0013\u0010\u0002\u001A\u00020\u00038G\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0002\u0010 \u00A8\u00064"}, d2 = {"Lokhttp3/Request;", "", "url", "Lokhttp3/HttpUrl;", "headers", "Lokhttp3/Headers;", "method", "", "body", "Lokhttp3/RequestBody;", "(Lokhttp3/HttpUrl;Lokhttp3/Headers;Ljava/lang/String;Lokhttp3/RequestBody;)V", "builder", "Lokhttp3/Request$Builder;", "(Lokhttp3/Request$Builder;)V", "()Lokhttp3/RequestBody;", "cacheControl", "Lokhttp3/CacheControl;", "()Lokhttp3/CacheControl;", "()Lokhttp3/Headers;", "isHttps", "", "()Z", "lazyCacheControl", "getLazyCacheControl$okhttp", "setLazyCacheControl$okhttp", "(Lokhttp3/CacheControl;)V", "()Ljava/lang/String;", "tags", "", "Lkotlin/reflect/KClass;", "getTags$okhttp", "()Ljava/util/Map;", "()Lokhttp3/HttpUrl;", "-deprecated_body", "-deprecated_cacheControl", "header", "name", "-deprecated_headers", "", "-deprecated_method", "newBuilder", "tag", "T", "reifiedTag", "()Ljava/lang/Object;", "type", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/Object;", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "toString", "-deprecated_url", "Builder", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class Request {
    @Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000E\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0007\b\u0016\u00A2\u0006\u0002\u0010\u0002B\u000F\b\u0010\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u00A2\u0006\u0002\u0010\u0005J\u0018\u0010%\u001A\u00020\u00002\u0006\u0010&\u001A\u00020\u00132\u0006\u0010\'\u001A\u00020\u0013H\u0016J\b\u0010(\u001A\u00020\u0004H\u0016J\u0010\u0010)\u001A\u00020\u00002\u0006\u0010)\u001A\u00020*H\u0016J\u0014\u0010+\u001A\u00020\u00002\n\b\u0002\u0010\u0006\u001A\u0004\u0018\u00010\u0007H\u0017J\b\u0010,\u001A\u00020\u0000H\u0016J\b\u0010-\u001A\u00020\u0000H\u0016J\u0018\u0010.\u001A\u00020\u00002\u0006\u0010&\u001A\u00020\u00132\u0006\u0010\'\u001A\u00020\u0013H\u0016J\u0010\u0010\f\u001A\u00020\u00002\u0006\u0010\f\u001A\u00020/H\u0016J\u001A\u0010\u0012\u001A\u00020\u00002\u0006\u0010\u0012\u001A\u00020\u00132\b\u0010\u0006\u001A\u0004\u0018\u00010\u0007H\u0016J\u0010\u00100\u001A\u00020\u00002\u0006\u0010\u0006\u001A\u00020\u0007H\u0016J\u0010\u00101\u001A\u00020\u00002\u0006\u0010\u0006\u001A\u00020\u0007H\u0016J\u0010\u00102\u001A\u00020\u00002\u0006\u0010\u0006\u001A\u00020\u0007H\u0016J\u0010\u00103\u001A\u00020\u00002\u0006\u0010&\u001A\u00020\u0013H\u0016J&\u00104\u001A\u00020\u0000\"\n\b\u0000\u00105\u0018\u0001*\u00020\u00012\b\u00104\u001A\u0004\u0018\u0001H5H\u0087\b\u00A2\u0006\u0004\b6\u00107J-\u00104\u001A\u00020\u0000\"\u0004\b\u0000\u001052\u000E\u00108\u001A\n\u0012\u0006\b\u0000\u0012\u0002H5092\b\u00104\u001A\u0004\u0018\u0001H5H\u0016\u00A2\u0006\u0002\u0010:J\u0012\u00104\u001A\u00020\u00002\b\u00104\u001A\u0004\u0018\u00010\u0001H\u0016J-\u00104\u001A\u00020\u0000\"\b\b\u0000\u00105*\u00020\u00012\f\u00108\u001A\b\u0012\u0004\u0012\u0002H50\u001A2\b\u00104\u001A\u0004\u0018\u0001H5\u00A2\u0006\u0002\u0010;J\u0010\u0010\u001F\u001A\u00020\u00002\u0006\u0010\u001F\u001A\u00020<H\u0016J\u0010\u0010\u001F\u001A\u00020\u00002\u0006\u0010\u001F\u001A\u00020\u0013H\u0016J\u0010\u0010\u001F\u001A\u00020\u00002\u0006\u0010\u001F\u001A\u00020 H\u0016R\u001C\u0010\u0006\u001A\u0004\u0018\u00010\u0007X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000BR\u001A\u0010\f\u001A\u00020\rX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u000E\u0010\u000F\"\u0004\b\u0010\u0010\u0011R\u001A\u0010\u0012\u001A\u00020\u0013X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R*\u0010\u0018\u001A\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001A\u0012\u0004\u0012\u00020\u00010\u0019X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u001B\u0010\u001C\"\u0004\b\u001D\u0010\u001ER\u001C\u0010\u001F\u001A\u0004\u0018\u00010 X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b!\u0010\"\"\u0004\b#\u0010$\u00A8\u0006="}, d2 = {"Lokhttp3/Request$Builder;", "", "()V", "request", "Lokhttp3/Request;", "(Lokhttp3/Request;)V", "body", "Lokhttp3/RequestBody;", "getBody$okhttp", "()Lokhttp3/RequestBody;", "setBody$okhttp", "(Lokhttp3/RequestBody;)V", "headers", "Lokhttp3/Headers$Builder;", "getHeaders$okhttp", "()Lokhttp3/Headers$Builder;", "setHeaders$okhttp", "(Lokhttp3/Headers$Builder;)V", "method", "", "getMethod$okhttp", "()Ljava/lang/String;", "setMethod$okhttp", "(Ljava/lang/String;)V", "tags", "", "Lkotlin/reflect/KClass;", "getTags$okhttp", "()Ljava/util/Map;", "setTags$okhttp", "(Ljava/util/Map;)V", "url", "Lokhttp3/HttpUrl;", "getUrl$okhttp", "()Lokhttp3/HttpUrl;", "setUrl$okhttp", "(Lokhttp3/HttpUrl;)V", "addHeader", "name", "value", "build", "cacheControl", "Lokhttp3/CacheControl;", "delete", "get", "head", "header", "Lokhttp3/Headers;", "patch", "post", "put", "removeHeader", "tag", "T", "reifiedTag", "(Ljava/lang/Object;)Lokhttp3/Request$Builder;", "type", "Ljava/lang/Class;", "(Ljava/lang/Class;Ljava/lang/Object;)Lokhttp3/Request$Builder;", "(Lkotlin/reflect/KClass;Ljava/lang/Object;)Lokhttp3/Request$Builder;", "Ljava/net/URL;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static class Builder {
        private RequestBody body;
        private okhttp3.Headers.Builder headers;
        private String method;
        private Map tags;
        private HttpUrl url;

        public Builder() {
            this.tags = MapsKt.emptyMap();
            this.method = "GET";
            this.headers = new okhttp3.Headers.Builder();
        }

        public Builder(Request request0) {
            Intrinsics.checkNotNullParameter(request0, "request");
            super();
            this.tags = MapsKt.emptyMap();
            this.url = request0.url();
            this.method = request0.method();
            this.body = request0.body();
            this.tags = request0.getTags$okhttp().isEmpty() ? MapsKt.emptyMap() : MapsKt.toMutableMap(request0.getTags$okhttp());
            this.headers = request0.headers().newBuilder();
        }

        public Builder addHeader(String s, String s1) {
            Intrinsics.checkNotNullParameter(s, "name");
            Intrinsics.checkNotNullParameter(s1, "value");
            return _RequestCommonKt.commonAddHeader(this, s, s1);
        }

        public Request build() {
            return new Request(this);
        }

        public Builder cacheControl(CacheControl cacheControl0) {
            Intrinsics.checkNotNullParameter(cacheControl0, "cacheControl");
            return _RequestCommonKt.commonCacheControl(this, cacheControl0);
        }

        public final Builder delete() {
            return Builder.delete$default(this, null, 1, null);
        }

        public Builder delete(RequestBody requestBody0) {
            return _RequestCommonKt.commonDelete(this, requestBody0);
        }

        public static Builder delete$default(Builder request$Builder0, RequestBody requestBody0, int v, Object object0) {
            if(object0 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: delete");
            }
            if((v & 1) != 0) {
                requestBody0 = _UtilCommonKt.getCommonEmptyRequestBody();
            }
            return request$Builder0.delete(requestBody0);
        }

        public Builder get() {
            return _RequestCommonKt.commonGet(this);
        }

        public final RequestBody getBody$okhttp() {
            return this.body;
        }

        public final okhttp3.Headers.Builder getHeaders$okhttp() {
            return this.headers;
        }

        public final String getMethod$okhttp() [...] // 潜在的解密器

        public final Map getTags$okhttp() {
            return this.tags;
        }

        public final HttpUrl getUrl$okhttp() {
            return this.url;
        }

        public Builder head() {
            return _RequestCommonKt.commonHead(this);
        }

        public Builder header(String s, String s1) {
            Intrinsics.checkNotNullParameter(s, "name");
            Intrinsics.checkNotNullParameter(s1, "value");
            return _RequestCommonKt.commonHeader(this, s, s1);
        }

        public Builder headers(Headers headers0) {
            Intrinsics.checkNotNullParameter(headers0, "headers");
            return _RequestCommonKt.commonHeaders(this, headers0);
        }

        public Builder method(String s, RequestBody requestBody0) {
            Intrinsics.checkNotNullParameter(s, "method");
            return _RequestCommonKt.commonMethod(this, s, requestBody0);
        }

        public Builder patch(RequestBody requestBody0) {
            Intrinsics.checkNotNullParameter(requestBody0, "body");
            return _RequestCommonKt.commonPatch(this, requestBody0);
        }

        public Builder post(RequestBody requestBody0) {
            Intrinsics.checkNotNullParameter(requestBody0, "body");
            return _RequestCommonKt.commonPost(this, requestBody0);
        }

        public Builder put(RequestBody requestBody0) {
            Intrinsics.checkNotNullParameter(requestBody0, "body");
            return _RequestCommonKt.commonPut(this, requestBody0);
        }

        public final Builder reifiedTag(Object object0) {
            Intrinsics.reifiedOperationMarker(4, "T");
            return this.tag(Reflection.getOrCreateKotlinClass(Object.class), object0);
        }

        public Builder removeHeader(String s) {
            Intrinsics.checkNotNullParameter(s, "name");
            return _RequestCommonKt.commonRemoveHeader(this, s);
        }

        public final void setBody$okhttp(RequestBody requestBody0) {
            this.body = requestBody0;
        }

        public final void setHeaders$okhttp(okhttp3.Headers.Builder headers$Builder0) {
            Intrinsics.checkNotNullParameter(headers$Builder0, "<set-?>");
            this.headers = headers$Builder0;
        }

        public final void setMethod$okhttp(String s) {
            Intrinsics.checkNotNullParameter(s, "<set-?>");
            this.method = s;
        }

        public final void setTags$okhttp(Map map0) {
            Intrinsics.checkNotNullParameter(map0, "<set-?>");
            this.tags = map0;
        }

        public final void setUrl$okhttp(HttpUrl httpUrl0) {
            this.url = httpUrl0;
        }

        public Builder tag(Class class0, Object object0) {
            Intrinsics.checkNotNullParameter(class0, "type");
            return _RequestCommonKt.commonTag(this, JvmClassMappingKt.getKotlinClass(class0), object0);
        }

        public Builder tag(Object object0) {
            return _RequestCommonKt.commonTag(this, Reflection.getOrCreateKotlinClass(Object.class), object0);
        }

        public final Builder tag(KClass kClass0, Object object0) {
            Intrinsics.checkNotNullParameter(kClass0, "type");
            return _RequestCommonKt.commonTag(this, kClass0, object0);
        }

        public Builder url(String s) {
            Intrinsics.checkNotNullParameter(s, "url");
            String s1 = _RequestCommonKt.canonicalUrl(s);
            return this.url(HttpUrl.Companion.get(s1));
        }

        public Builder url(URL uRL0) {
            Intrinsics.checkNotNullParameter(uRL0, "url");
            String s = uRL0.toString();
            Intrinsics.checkNotNullExpressionValue(s, "url.toString()");
            return this.url(HttpUrl.Companion.get(s));
        }

        public Builder url(HttpUrl httpUrl0) {
            Intrinsics.checkNotNullParameter(httpUrl0, "url");
            this.url = httpUrl0;
            return this;
        }
    }

    private final RequestBody body;
    private final Headers headers;
    private CacheControl lazyCacheControl;
    private final String method;
    private final Map tags;
    private final HttpUrl url;

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "body", imports = {}))
    public final RequestBody -deprecated_body() {
        return this.body;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "cacheControl", imports = {}))
    public final CacheControl -deprecated_cacheControl() {
        return this.cacheControl();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "headers", imports = {}))
    public final Headers -deprecated_headers() {
        return this.headers;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "method", imports = {}))
    public final String -deprecated_method() {
        return this.method;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "url", imports = {}))
    public final HttpUrl -deprecated_url() {
        return this.url;
    }

    public Request(HttpUrl httpUrl0, Headers headers0, String s, RequestBody requestBody0) {
        Intrinsics.checkNotNullParameter(httpUrl0, "url");
        Intrinsics.checkNotNullParameter(headers0, "headers");
        Intrinsics.checkNotNullParameter(s, "method");
        Builder request$Builder0 = new Builder().url(httpUrl0).headers(headers0);
        if(Intrinsics.areEqual(s, "\u0000")) {
            s = requestBody0 == null ? "GET" : "POST";
        }
        this(request$Builder0.method(s, requestBody0));
    }

    public Request(HttpUrl httpUrl0, Headers headers0, String s, RequestBody requestBody0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 2) != 0) {
            headers0 = Headers.Companion.of(new String[0]);
        }
        if((v & 4) != 0) {
            s = "\u0000";
        }
        if((v & 8) != 0) {
            requestBody0 = null;
        }
        this(httpUrl0, headers0, s, requestBody0);
    }

    public Request(Builder request$Builder0) {
        Intrinsics.checkNotNullParameter(request$Builder0, "builder");
        super();
        HttpUrl httpUrl0 = request$Builder0.getUrl$okhttp();
        if(httpUrl0 == null) {
            throw new IllegalStateException("url == null");
        }
        this.url = httpUrl0;
        this.method = "GET";
        this.headers = request$Builder0.getHeaders$okhttp().build();
        this.body = request$Builder0.getBody$okhttp();
        this.tags = MapsKt.toMap(request$Builder0.getTags$okhttp());
    }

    public final RequestBody body() {
        return this.body;
    }

    public final CacheControl cacheControl() {
        CacheControl cacheControl0 = this.lazyCacheControl;
        if(cacheControl0 == null) {
            cacheControl0 = CacheControl.Companion.parse(this.headers);
            this.lazyCacheControl = cacheControl0;
        }
        return cacheControl0;
    }

    public final CacheControl getLazyCacheControl$okhttp() {
        return this.lazyCacheControl;
    }

    public final Map getTags$okhttp() {
        return this.tags;
    }

    public final String header(String s) {
        Intrinsics.checkNotNullParameter(s, "name");
        return _RequestCommonKt.commonHeader(this, s);
    }

    public final List headers(String s) {
        Intrinsics.checkNotNullParameter(s, "name");
        return _RequestCommonKt.commonHeaders(this, s);
    }

    public final Headers headers() {
        return this.headers;
    }

    public final boolean isHttps() {
        return this.url.isHttps();
    }

    public final String method() {
        return this.method;
    }

    public final Builder newBuilder() {
        return new Builder(this);
    }

    public final Object reifiedTag() {
        Intrinsics.reifiedOperationMarker(4, "T");
        return this.tag(Reflection.getOrCreateKotlinClass(Object.class));
    }

    public final void setLazyCacheControl$okhttp(CacheControl cacheControl0) {
        this.lazyCacheControl = cacheControl0;
    }

    public final Object tag() {
        return this.tag(Reflection.getOrCreateKotlinClass(Object.class));
    }

    public final Object tag(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "type");
        return this.tag(JvmClassMappingKt.getKotlinClass(class0));
    }

    public final Object tag(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "type");
        return JvmClassMappingKt.getJavaClass(kClass0).cast(this.tags.get(kClass0));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder("Request{method=");
        stringBuilder0.append(this.method);
        stringBuilder0.append(", url=");
        stringBuilder0.append(this.url);
        if(this.headers.size() != 0) {
            stringBuilder0.append(", headers=[");
            int v = 0;
            for(Object object0: this.headers) {
                if(v < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                String s = (String)((Pair)object0).component1();
                String s1 = (String)((Pair)object0).component2();
                if(v > 0) {
                    stringBuilder0.append(", ");
                }
                stringBuilder0.append(s);
                stringBuilder0.append(':');
                if(_UtilCommonKt.isSensitiveHeader(s)) {
                    s1 = "██";
                }
                stringBuilder0.append(s1);
                ++v;
            }
            stringBuilder0.append(']');
        }
        if(!this.tags.isEmpty()) {
            stringBuilder0.append(", tags=");
            stringBuilder0.append(this.tags);
        }
        stringBuilder0.append('}');
        String s2 = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s2, "StringBuilder().apply(builderAction).toString()");
        return s2;
    }

    public final HttpUrl url() {
        return this.url;
    }
}

