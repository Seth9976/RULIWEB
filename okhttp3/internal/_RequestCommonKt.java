package okhttp3.internal;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Request.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.internal.http.HttpMethod;

@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001A\u0010\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0001H\u0000\u001A\u001A\u0010\u0003\u001A\u00020\u0004*\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00012\u0006\u0010\u0006\u001A\u00020\u0001\u001A\n\u0010\u0007\u001A\u00020\b*\u00020\t\u001A\u0012\u0010\u0007\u001A\u00020\u0004*\u00020\u00042\u0006\u0010\n\u001A\u00020\b\u001A\u0014\u0010\u000B\u001A\u00020\u0004*\u00020\u00042\b\u0010\f\u001A\u0004\u0018\u00010\r\u001A\n\u0010\u000E\u001A\u00020\u0004*\u00020\u0004\u001A\n\u0010\u000F\u001A\u00020\u0004*\u00020\u0004\u001A\u0014\u0010\u0010\u001A\u0004\u0018\u00010\u0001*\u00020\t2\u0006\u0010\u0005\u001A\u00020\u0001\u001A\u001A\u0010\u0010\u001A\u00020\u0004*\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00012\u0006\u0010\u0006\u001A\u00020\u0001\u001A\u0018\u0010\u0011\u001A\b\u0012\u0004\u0012\u00020\u00010\u0012*\u00020\t2\u0006\u0010\u0005\u001A\u00020\u0001\u001A\u0012\u0010\u0011\u001A\u00020\u0004*\u00020\u00042\u0006\u0010\u0013\u001A\u00020\u0014\u001A\u001C\u0010\u0015\u001A\u00020\u0004*\u00020\u00042\u0006\u0010\u0016\u001A\u00020\u00012\b\u0010\f\u001A\u0004\u0018\u00010\r\u001A\n\u0010\u0017\u001A\u00020\u0004*\u00020\t\u001A\u0012\u0010\u0018\u001A\u00020\u0004*\u00020\u00042\u0006\u0010\f\u001A\u00020\r\u001A\u0012\u0010\u0019\u001A\u00020\u0004*\u00020\u00042\u0006\u0010\f\u001A\u00020\r\u001A\u0012\u0010\u001A\u001A\u00020\u0004*\u00020\u00042\u0006\u0010\f\u001A\u00020\r\u001A\u0012\u0010\u001B\u001A\u00020\u0004*\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0001\u001A1\u0010\u001C\u001A\u00020\u0004\"\b\b\u0000\u0010\u001D*\u00020\u001E*\u00020\u00042\f\u0010\u001F\u001A\b\u0012\u0004\u0012\u0002H\u001D0 2\b\u0010!\u001A\u0004\u0018\u0001H\u001D¢\u0006\u0002\u0010\"¨\u0006#"}, d2 = {"canonicalUrl", "", "url", "commonAddHeader", "Lokhttp3/Request$Builder;", "name", "value", "commonCacheControl", "Lokhttp3/CacheControl;", "Lokhttp3/Request;", "cacheControl", "commonDelete", "body", "Lokhttp3/RequestBody;", "commonGet", "commonHead", "commonHeader", "commonHeaders", "", "headers", "Lokhttp3/Headers;", "commonMethod", "method", "commonNewBuilder", "commonPatch", "commonPost", "commonPut", "commonRemoveHeader", "commonTag", "T", "", "type", "Lkotlin/reflect/KClass;", "tag", "(Lokhttp3/Request$Builder;Lkotlin/reflect/KClass;Ljava/lang/Object;)Lokhttp3/Request$Builder;", "okhttp"}, k = 2, mv = {1, 7, 1}, xi = 0x30)
public final class _RequestCommonKt {
    public static final String canonicalUrl(String s) {
        Intrinsics.checkNotNullParameter(s, "url");
        if(StringsKt.startsWith(s, "ws:", true)) {
            String s1 = s.substring(3);
            Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String).substring(startIndex)");
            return "http:" + s1;
        }
        if(StringsKt.startsWith(s, "wss:", true)) {
            String s2 = s.substring(4);
            Intrinsics.checkNotNullExpressionValue(s2, "this as java.lang.String).substring(startIndex)");
            return "https:" + s2;
        }
        return s;
    }

    public static final Builder commonAddHeader(Builder request$Builder0, String s, String s1) {
        Intrinsics.checkNotNullParameter(request$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(s1, "value");
        request$Builder0.getHeaders$okhttp().add(s, s1);
        return request$Builder0;
    }

    public static final CacheControl commonCacheControl(Request request0) {
        Intrinsics.checkNotNullParameter(request0, "<this>");
        CacheControl cacheControl0 = request0.getLazyCacheControl$okhttp();
        if(cacheControl0 == null) {
            cacheControl0 = CacheControl.Companion.parse(request0.headers());
            request0.setLazyCacheControl$okhttp(cacheControl0);
        }
        return cacheControl0;
    }

    public static final Builder commonCacheControl(Builder request$Builder0, CacheControl cacheControl0) {
        Intrinsics.checkNotNullParameter(request$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(cacheControl0, "cacheControl");
        String s = cacheControl0.toString();
        return s.length() == 0 ? request$Builder0.removeHeader("Cache-Control") : request$Builder0.header("Cache-Control", s);
    }

    public static final Builder commonDelete(Builder request$Builder0, RequestBody requestBody0) {
        Intrinsics.checkNotNullParameter(request$Builder0, "<this>");
        return request$Builder0.method("DELETE", requestBody0);
    }

    public static final Builder commonGet(Builder request$Builder0) {
        Intrinsics.checkNotNullParameter(request$Builder0, "<this>");
        return request$Builder0.method("GET", null);
    }

    public static final Builder commonHead(Builder request$Builder0) {
        Intrinsics.checkNotNullParameter(request$Builder0, "<this>");
        return request$Builder0.method("HEAD", null);
    }

    public static final String commonHeader(Request request0, String s) {
        Intrinsics.checkNotNullParameter(request0, "<this>");
        Intrinsics.checkNotNullParameter(s, "name");
        return request0.headers().get(s);
    }

    public static final Builder commonHeader(Builder request$Builder0, String s, String s1) {
        Intrinsics.checkNotNullParameter(request$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(s1, "value");
        request$Builder0.getHeaders$okhttp().set(s, s1);
        return request$Builder0;
    }

    public static final List commonHeaders(Request request0, String s) {
        Intrinsics.checkNotNullParameter(request0, "<this>");
        Intrinsics.checkNotNullParameter(s, "name");
        return request0.headers().values(s);
    }

    public static final Builder commonHeaders(Builder request$Builder0, Headers headers0) {
        Intrinsics.checkNotNullParameter(request$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(headers0, "headers");
        request$Builder0.setHeaders$okhttp(headers0.newBuilder());
        return request$Builder0;
    }

    public static final Builder commonMethod(Builder request$Builder0, String s, RequestBody requestBody0) {
        Intrinsics.checkNotNullParameter(request$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(s, "method");
        if(s.length() <= 0) {
            throw new IllegalArgumentException("method.isEmpty() == true");
        }
        if(requestBody0 == null) {
            if(HttpMethod.requiresRequestBody(s)) {
                throw new IllegalArgumentException(("method " + s + " must have a request body.").toString());
            }
        }
        else if(!HttpMethod.permitsRequestBody(s)) {
            throw new IllegalArgumentException(("method " + s + " must not have a request body.").toString());
        }
        request$Builder0.setMethod$okhttp(s);
        request$Builder0.setBody$okhttp(requestBody0);
        return request$Builder0;
    }

    public static final Builder commonNewBuilder(Request request0) {
        Intrinsics.checkNotNullParameter(request0, "<this>");
        return new Builder(request0);
    }

    public static final Builder commonPatch(Builder request$Builder0, RequestBody requestBody0) {
        Intrinsics.checkNotNullParameter(request$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(requestBody0, "body");
        return request$Builder0.method("PATCH", requestBody0);
    }

    public static final Builder commonPost(Builder request$Builder0, RequestBody requestBody0) {
        Intrinsics.checkNotNullParameter(request$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(requestBody0, "body");
        return request$Builder0.method("POST", requestBody0);
    }

    public static final Builder commonPut(Builder request$Builder0, RequestBody requestBody0) {
        Intrinsics.checkNotNullParameter(request$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(requestBody0, "body");
        return request$Builder0.method("PUT", requestBody0);
    }

    public static final Builder commonRemoveHeader(Builder request$Builder0, String s) {
        Intrinsics.checkNotNullParameter(request$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(s, "name");
        request$Builder0.getHeaders$okhttp().removeAll(s);
        return request$Builder0;
    }

    public static final Builder commonTag(Builder request$Builder0, KClass kClass0, Object object0) {
        Map map1;
        Intrinsics.checkNotNullParameter(request$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(kClass0, "type");
        if(object0 == null) {
            if(!request$Builder0.getTags$okhttp().isEmpty()) {
                Map map0 = request$Builder0.getTags$okhttp();
                Intrinsics.checkNotNull(map0, "null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.reflect.KClass<*>, kotlin.Any>");
                TypeIntrinsics.asMutableMap(map0).remove(kClass0);
            }
            return request$Builder0;
        }
        if(request$Builder0.getTags$okhttp().isEmpty()) {
            map1 = new LinkedHashMap();
            request$Builder0.setTags$okhttp(map1);
        }
        else {
            Map map2 = request$Builder0.getTags$okhttp();
            Intrinsics.checkNotNull(map2, "null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.reflect.KClass<*>, kotlin.Any>");
            map1 = TypeIntrinsics.asMutableMap(map2);
        }
        map1.put(kClass0, object0);
        return request$Builder0;
    }
}

