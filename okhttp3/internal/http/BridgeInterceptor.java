package okhttp3.internal.http;

import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.Interceptor.Chain;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal._UtilJvmKt;
import okio.GzipSource;
import okio.Okio;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001A\u00020\u00062\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\t0\bH\u0002J\u0010\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\rH\u0016R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000E"}, d2 = {"Lokhttp3/internal/http/BridgeInterceptor;", "Lokhttp3/Interceptor;", "cookieJar", "Lokhttp3/CookieJar;", "(Lokhttp3/CookieJar;)V", "cookieHeader", "", "cookies", "", "Lokhttp3/Cookie;", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class BridgeInterceptor implements Interceptor {
    private final CookieJar cookieJar;

    public BridgeInterceptor(CookieJar cookieJar0) {
        Intrinsics.checkNotNullParameter(cookieJar0, "cookieJar");
        super();
        this.cookieJar = cookieJar0;
    }

    private final String cookieHeader(List list0) {
        StringBuilder stringBuilder0 = new StringBuilder();
        int v = 0;
        for(Object object0: list0) {
            if(v < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if(v > 0) {
                stringBuilder0.append("; ");
            }
            stringBuilder0.append(((Cookie)object0).name());
            stringBuilder0.append('=');
            stringBuilder0.append(((Cookie)object0).value());
            ++v;
        }
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    @Override  // okhttp3.Interceptor
    public Response intercept(Chain interceptor$Chain0) throws IOException {
        Intrinsics.checkNotNullParameter(interceptor$Chain0, "chain");
        Request request0 = interceptor$Chain0.request();
        Builder request$Builder0 = request0.newBuilder();
        RequestBody requestBody0 = request0.body();
        if(requestBody0 != null) {
            MediaType mediaType0 = requestBody0.contentType();
            if(mediaType0 != null) {
                request$Builder0.header("Content-Type", mediaType0.toString());
            }
            long v = requestBody0.contentLength();
            if(v == -1L) {
                request$Builder0.header("Transfer-Encoding", "chunked");
                request$Builder0.removeHeader("Content-Length");
            }
            else {
                request$Builder0.header("Content-Length", String.valueOf(v));
                request$Builder0.removeHeader("Transfer-Encoding");
            }
        }
        boolean z = false;
        if(request0.header("Host") == null) {
            request$Builder0.header("Host", _UtilJvmKt.toHostHeader$default(request0.url(), false, 1, null));
        }
        if(request0.header("Connection") == null) {
            request$Builder0.header("Connection", "Keep-Alive");
        }
        if(request0.header("Accept-Encoding") == null && request0.header("Range") == null) {
            request$Builder0.header("Accept-Encoding", "gzip");
            z = true;
        }
        List list0 = this.cookieJar.loadForRequest(request0.url());
        if(!list0.isEmpty()) {
            request$Builder0.header("Cookie", this.cookieHeader(list0));
        }
        if(request0.header("User-Agent") == null) {
            request$Builder0.header("User-Agent", "okhttp/5.0.0-alpha.11");
        }
        Request request1 = request$Builder0.build();
        Response response0 = interceptor$Chain0.proceed(request1);
        HttpHeaders.receiveHeaders(this.cookieJar, request1.url(), response0.headers());
        okhttp3.Response.Builder response$Builder0 = response0.newBuilder().request(request1);
        if(z && StringsKt.equals("gzip", Response.header$default(response0, "Content-Encoding", null, 2, null), true) && HttpHeaders.promisesBody(response0)) {
            ResponseBody responseBody0 = response0.body();
            if(responseBody0 != null) {
                GzipSource gzipSource0 = new GzipSource(responseBody0.source());
                response$Builder0.headers(response0.headers().newBuilder().removeAll("Content-Encoding").removeAll("Content-Length").build());
                response$Builder0.body(new RealResponseBody(Response.header$default(response0, "Content-Type", null, 2, null), -1L, Okio.buffer(gzipSource0)));
            }
        }
        return response$Builder0.build();
    }
}

