package okhttp3.internal;

import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response.Builder;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

@Metadata(d1 = {"\u0000j\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\u001A\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000E2\b\u0010\u000F\u001A\u0004\u0018\u00010\u0002H\u0002\u001A\u001A\u0010\u0010\u001A\u00020\u0011*\u00020\u00112\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u0012\u001A\u00020\u000E\u001A\u0012\u0010\u0013\u001A\u00020\u0011*\u00020\u00112\u0006\u0010\u0014\u001A\u00020\u0015\u001A\u0014\u0010\u0016\u001A\u00020\u0011*\u00020\u00112\b\u0010\u0017\u001A\u0004\u0018\u00010\u0002\u001A\n\u0010\u0018\u001A\u00020\f*\u00020\u0002\u001A\u0012\u0010\u0019\u001A\u00020\u0011*\u00020\u00112\u0006\u0010\u001A\u001A\u00020\u001B\u001A \u0010\u001C\u001A\u0004\u0018\u00010\u000E*\u00020\u00022\u0006\u0010\r\u001A\u00020\u000E2\b\u0010\u001D\u001A\u0004\u0018\u00010\u000EH\u0007\u001A\u001A\u0010\u001C\u001A\u00020\u0011*\u00020\u00112\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u0012\u001A\u00020\u000E\u001A\u0018\u0010\u001E\u001A\b\u0012\u0004\u0012\u00020\u000E0\u001F*\u00020\u00022\u0006\u0010\r\u001A\u00020\u000E\u001A\u0012\u0010\u001E\u001A\u00020\u0011*\u00020\u00112\u0006\u0010 \u001A\u00020!\u001A\u0012\u0010\"\u001A\u00020\u0011*\u00020\u00112\u0006\u0010#\u001A\u00020\u000E\u001A\u0014\u0010$\u001A\u00020\u0011*\u00020\u00112\b\u0010%\u001A\u0004\u0018\u00010\u0002\u001A\n\u0010&\u001A\u00020\u0011*\u00020\u0002\u001A\u0012\u0010\'\u001A\u00020\u0015*\u00020\u00022\u0006\u0010(\u001A\u00020)\u001A\u0014\u0010*\u001A\u00020\u0011*\u00020\u00112\b\u0010+\u001A\u0004\u0018\u00010\u0002\u001A\u0012\u0010,\u001A\u00020\u0011*\u00020\u00112\u0006\u0010-\u001A\u00020.\u001A\u0012\u0010/\u001A\u00020\u0011*\u00020\u00112\u0006\u0010\r\u001A\u00020\u000E\u001A\u0012\u00100\u001A\u00020\u0011*\u00020\u00112\u0006\u00101\u001A\u000202\u001A\n\u00103\u001A\u00020\u000E*\u00020\u0002\u001A\u0018\u00104\u001A\u00020\u0011*\u00020\u00112\f\u00105\u001A\b\u0012\u0004\u0012\u00020!06\u001A\n\u00107\u001A\u00020\u0002*\u00020\u0002\"\u0015\u0010\u0000\u001A\u00020\u0001*\u00020\u00028F\u00A2\u0006\u0006\u001A\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001A\u00020\u0006*\u00020\u00028F\u00A2\u0006\u0006\u001A\u0004\b\u0007\u0010\b\"\u0015\u0010\t\u001A\u00020\u0006*\u00020\u00028F\u00A2\u0006\u0006\u001A\u0004\b\n\u0010\b\u00A8\u00068"}, d2 = {"commonCacheControl", "Lokhttp3/CacheControl;", "Lokhttp3/Response;", "getCommonCacheControl", "(Lokhttp3/Response;)Lokhttp3/CacheControl;", "commonIsRedirect", "", "getCommonIsRedirect", "(Lokhttp3/Response;)Z", "commonIsSuccessful", "getCommonIsSuccessful", "checkSupportResponse", "", "name", "", "response", "commonAddHeader", "Lokhttp3/Response$Builder;", "value", "commonBody", "body", "Lokhttp3/ResponseBody;", "commonCacheResponse", "cacheResponse", "commonClose", "commonCode", "code", "", "commonHeader", "defaultValue", "commonHeaders", "", "headers", "Lokhttp3/Headers;", "commonMessage", "message", "commonNetworkResponse", "networkResponse", "commonNewBuilder", "commonPeekBody", "byteCount", "", "commonPriorResponse", "priorResponse", "commonProtocol", "protocol", "Lokhttp3/Protocol;", "commonRemoveHeader", "commonRequest", "request", "Lokhttp3/Request;", "commonToString", "commonTrailers", "trailersFn", "Lkotlin/Function0;", "stripBody", "okhttp"}, k = 2, mv = {1, 7, 1}, xi = 0x30)
public final class _ResponseCommonKt {
    private static final void checkSupportResponse(String s, Response response0) {
        if(response0 != null) {
            if(response0.networkResponse() != null) {
                throw new IllegalArgumentException((s + ".networkResponse != null").toString());
            }
            if(response0.cacheResponse() != null) {
                throw new IllegalArgumentException((s + ".cacheResponse != null").toString());
            }
            if(response0.priorResponse() != null) {
                throw new IllegalArgumentException((s + ".priorResponse != null").toString());
            }
        }
    }

    public static final Builder commonAddHeader(Builder response$Builder0, String s, String s1) {
        Intrinsics.checkNotNullParameter(response$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(s1, "value");
        response$Builder0.getHeaders$okhttp().add(s, s1);
        return response$Builder0;
    }

    public static final Builder commonBody(Builder response$Builder0, ResponseBody responseBody0) {
        Intrinsics.checkNotNullParameter(response$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(responseBody0, "body");
        response$Builder0.setBody$okhttp(responseBody0);
        return response$Builder0;
    }

    public static final Builder commonCacheResponse(Builder response$Builder0, Response response0) {
        Intrinsics.checkNotNullParameter(response$Builder0, "<this>");
        _ResponseCommonKt.checkSupportResponse("cacheResponse", response0);
        response$Builder0.setCacheResponse$okhttp(response0);
        return response$Builder0;
    }

    public static final void commonClose(Response response0) {
        Intrinsics.checkNotNullParameter(response0, "<this>");
        response0.body().close();
    }

    public static final Builder commonCode(Builder response$Builder0, int v) {
        Intrinsics.checkNotNullParameter(response$Builder0, "<this>");
        response$Builder0.setCode$okhttp(v);
        return response$Builder0;
    }

    public static final String commonHeader(Response response0, String s, String s1) {
        Intrinsics.checkNotNullParameter(response0, "<this>");
        Intrinsics.checkNotNullParameter(s, "name");
        String s2 = response0.headers().get(s);
        return s2 == null ? s1 : s2;
    }

    public static final Builder commonHeader(Builder response$Builder0, String s, String s1) {
        Intrinsics.checkNotNullParameter(response$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(s, "name");
        Intrinsics.checkNotNullParameter(s1, "value");
        response$Builder0.getHeaders$okhttp().set(s, s1);
        return response$Builder0;
    }

    public static final List commonHeaders(Response response0, String s) {
        Intrinsics.checkNotNullParameter(response0, "<this>");
        Intrinsics.checkNotNullParameter(s, "name");
        return response0.headers().values(s);
    }

    public static final Builder commonHeaders(Builder response$Builder0, Headers headers0) {
        Intrinsics.checkNotNullParameter(response$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(headers0, "headers");
        response$Builder0.setHeaders$okhttp(headers0.newBuilder());
        return response$Builder0;
    }

    public static final Builder commonMessage(Builder response$Builder0, String s) {
        Intrinsics.checkNotNullParameter(response$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(s, "message");
        response$Builder0.setMessage$okhttp(s);
        return response$Builder0;
    }

    public static final Builder commonNetworkResponse(Builder response$Builder0, Response response0) {
        Intrinsics.checkNotNullParameter(response$Builder0, "<this>");
        _ResponseCommonKt.checkSupportResponse("networkResponse", response0);
        response$Builder0.setNetworkResponse$okhttp(response0);
        return response$Builder0;
    }

    public static final Builder commonNewBuilder(Response response0) {
        Intrinsics.checkNotNullParameter(response0, "<this>");
        return new Builder(response0);
    }

    public static final ResponseBody commonPeekBody(Response response0, long v) throws IOException {
        Intrinsics.checkNotNullParameter(response0, "<this>");
        BufferedSource bufferedSource0 = response0.body().source().peek();
        Buffer buffer0 = new Buffer();
        bufferedSource0.request(v);
        buffer0.write(bufferedSource0, Math.min(v, bufferedSource0.getBuffer().size()));
        MediaType mediaType0 = response0.body().contentType();
        return ResponseBody.Companion.create(buffer0, mediaType0, buffer0.size());
    }

    public static final Builder commonPriorResponse(Builder response$Builder0, Response response0) {
        Intrinsics.checkNotNullParameter(response$Builder0, "<this>");
        response$Builder0.setPriorResponse$okhttp(response0);
        return response$Builder0;
    }

    public static final Builder commonProtocol(Builder response$Builder0, Protocol protocol0) {
        Intrinsics.checkNotNullParameter(response$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(protocol0, "protocol");
        response$Builder0.setProtocol$okhttp(protocol0);
        return response$Builder0;
    }

    public static final Builder commonRemoveHeader(Builder response$Builder0, String s) {
        Intrinsics.checkNotNullParameter(response$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(s, "name");
        response$Builder0.getHeaders$okhttp().removeAll(s);
        return response$Builder0;
    }

    public static final Builder commonRequest(Builder response$Builder0, Request request0) {
        Intrinsics.checkNotNullParameter(response$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(request0, "request");
        response$Builder0.setRequest$okhttp(request0);
        return response$Builder0;
    }

    public static final String commonToString(Response response0) {
        Intrinsics.checkNotNullParameter(response0, "<this>");
        return "Response{protocol=" + response0.protocol() + ", code=" + response0.code() + ", message=" + response0.message() + ", url=" + response0.request().url() + '}';
    }

    public static final Builder commonTrailers(Builder response$Builder0, Function0 function00) {
        Intrinsics.checkNotNullParameter(response$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(function00, "trailersFn");
        response$Builder0.setTrailersFn$okhttp(function00);
        return response$Builder0;
    }

    public static final CacheControl getCommonCacheControl(Response response0) {
        Intrinsics.checkNotNullParameter(response0, "<this>");
        CacheControl cacheControl0 = response0.getLazyCacheControl$okhttp();
        if(cacheControl0 == null) {
            cacheControl0 = CacheControl.Companion.parse(response0.headers());
            response0.setLazyCacheControl$okhttp(cacheControl0);
        }
        return cacheControl0;
    }

    public static final boolean getCommonIsRedirect(Response response0) {
        Intrinsics.checkNotNullParameter(response0, "<this>");
        int v = response0.code();
        return v == 307 || v == 308 || (v == 300 || v == 301 || v == 302 || v == 303);
    }

    public static final boolean getCommonIsSuccessful(Response response0) {
        Intrinsics.checkNotNullParameter(response0, "<this>");
        int v = response0.code();
        return 200 <= v && v < 300;
    }

    public static final Response stripBody(Response response0) {
        Intrinsics.checkNotNullParameter(response0, "<this>");
        return response0.newBuilder().body(new UnreadableResponseBody(response0.body().contentType(), response0.body().contentLength())).build();
    }
}

