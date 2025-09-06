package okhttp3.internal.http;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy.Type;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import java.util.List;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import okhttp3.HttpUrl;
import okhttp3.Interceptor.Chain;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal._ResponseCommonKt;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.http2.ConnectionShutdownException;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u001E2\u00020\u0001:\u0001\u001EB\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001A\u0010\u0005\u001A\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0002J\u001C\u0010\u000B\u001A\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001A\u00020\b2\b\u0010\f\u001A\u0004\u0018\u00010\rH\u0002J\u0010\u0010\u000E\u001A\u00020\b2\u0006\u0010\u000F\u001A\u00020\u0010H\u0016J\u0018\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u0012H\u0002J(\u0010\u0016\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0017\u001A\u00020\u00182\u0006\u0010\u0019\u001A\u00020\u00062\u0006\u0010\u0015\u001A\u00020\u0012H\u0002J\u0018\u0010\u001A\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0019\u001A\u00020\u0006H\u0002J\u0018\u0010\u001B\u001A\u00020\u001C2\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\u001D\u001A\u00020\u001CH\u0002R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001F"}, d2 = {"Lokhttp3/internal/http/RetryAndFollowUpInterceptor;", "Lokhttp3/Interceptor;", "client", "Lokhttp3/OkHttpClient;", "(Lokhttp3/OkHttpClient;)V", "buildRedirectRequest", "Lokhttp3/Request;", "userResponse", "Lokhttp3/Response;", "method", "", "followUpRequest", "exchange", "Lokhttp3/internal/connection/Exchange;", "intercept", "chain", "Lokhttp3/Interceptor$Chain;", "isRecoverable", "", "e", "Ljava/io/IOException;", "requestSendStarted", "recover", "call", "Lokhttp3/internal/connection/RealCall;", "userRequest", "requestIsOneShot", "retryAfter", "", "defaultDelay", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class RetryAndFollowUpInterceptor implements Interceptor {
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lokhttp3/internal/http/RetryAndFollowUpInterceptor$Companion;", "", "()V", "MAX_FOLLOW_UPS", "", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Companion Companion = null;
    private static final int MAX_FOLLOW_UPS = 20;
    private final OkHttpClient client;

    static {
        RetryAndFollowUpInterceptor.Companion = new Companion(null);
    }

    public RetryAndFollowUpInterceptor(OkHttpClient okHttpClient0) {
        Intrinsics.checkNotNullParameter(okHttpClient0, "client");
        super();
        this.client = okHttpClient0;
    }

    private final Request buildRedirectRequest(Response response0, String s) {
        RequestBody requestBody0 = null;
        if(!this.client.followRedirects()) {
            return null;
        }
        String s1 = Response.header$default(response0, "Location", null, 2, null);
        if(s1 == null) {
            return null;
        }
        HttpUrl httpUrl0 = response0.request().url().resolve(s1);
        if(httpUrl0 == null) {
            return null;
        }
        if(!Intrinsics.areEqual(httpUrl0.scheme(), response0.request().url().scheme()) && !this.client.followSslRedirects()) {
            return null;
        }
        Builder request$Builder0 = response0.request().newBuilder();
        if(HttpMethod.permitsRequestBody(s)) {
            int v = response0.code();
            boolean z = HttpMethod.INSTANCE.redirectsWithBody(s) || (v == 307 || v == 308);
            if(!HttpMethod.INSTANCE.redirectsToGet(s) || (v == 307 || v == 308)) {
                if(z) {
                    requestBody0 = response0.request().body();
                }
                request$Builder0.method(s, requestBody0);
            }
            else {
                request$Builder0.method("GET", null);
            }
            if(!z) {
                request$Builder0.removeHeader("Transfer-Encoding");
                request$Builder0.removeHeader("Content-Length");
                request$Builder0.removeHeader("Content-Type");
            }
        }
        if(!_UtilJvmKt.canReuseConnectionFor(response0.request().url(), httpUrl0)) {
            request$Builder0.removeHeader("Authorization");
        }
        return request$Builder0.url(httpUrl0).build();
    }

    private final Request followUpRequest(Response response0, Exchange exchange0) throws IOException {
        Route route0;
        if(exchange0 == null) {
            route0 = null;
        }
        else {
            RealConnection realConnection0 = exchange0.getConnection$okhttp();
            route0 = realConnection0 == null ? null : realConnection0.route();
        }
        int v = response0.code();
        String s = response0.request().method();
        switch(v) {
            case 307: 
            case 308: {
                return this.buildRedirectRequest(response0, s);
            }
            case 401: {
                return this.client.authenticator().authenticate(route0, response0);
            }
            case 407: {
                Intrinsics.checkNotNull(route0);
                if(route0.proxy().type() != Proxy.Type.HTTP) {
                    throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
                }
                return this.client.proxyAuthenticator().authenticate(route0, response0);
            }
            case 408: {
                if(!this.client.retryOnConnectionFailure()) {
                    return null;
                }
                RequestBody requestBody0 = response0.request().body();
                if(requestBody0 != null && requestBody0.isOneShot()) {
                    return null;
                }
                Response response1 = response0.priorResponse();
                if(response1 != null && response1.code() == 408) {
                    return null;
                }
                return this.retryAfter(response0, 0) <= 0 ? response0.request() : null;
            }
            case 421: {
                RequestBody requestBody1 = response0.request().body();
                if(requestBody1 != null && requestBody1.isOneShot()) {
                    return null;
                }
                if(exchange0 != null && exchange0.isCoalescedConnection$okhttp()) {
                    exchange0.getConnection$okhttp().noCoalescedConnections$okhttp();
                    return response0.request();
                }
                return null;
            }
            case 503: {
                Response response2 = response0.priorResponse();
                if(response2 != null && response2.code() == 503) {
                    return null;
                }
                return this.retryAfter(response0, 0x7FFFFFFF) == 0 ? response0.request() : null;
            }
            default: {
                return v == 300 || v == 301 || v == 302 || v == 303 ? this.buildRedirectRequest(response0, s) : null;
            }
        }
    }

    @Override  // okhttp3.Interceptor
    public Response intercept(Chain interceptor$Chain0) throws IOException {
        Request request1;
        Response response1;
        Intrinsics.checkNotNullParameter(interceptor$Chain0, "chain");
        RealInterceptorChain realInterceptorChain0 = (RealInterceptorChain)interceptor$Chain0;
        Request request0 = realInterceptorChain0.getRequest$okhttp();
        RealCall realCall0 = realInterceptorChain0.getCall$okhttp();
        List list0 = CollectionsKt.emptyList();
        Response response0 = null;
        boolean z = true;
        int v = 0;
        while(true) {
            realCall0.enterNetworkInterceptorExchange(request0, z, realInterceptorChain0);
            if(realCall0.isCanceled()) {
                break;
            }
            try {
                try {
                    response1 = realInterceptorChain0.proceed(request0);
                    goto label_19;
                }
                catch(IOException iOException0) {
                }
                if(!this.recover(iOException0, realCall0, request0, !(iOException0 instanceof ConnectionShutdownException))) {
                    throw _UtilCommonKt.withSuppressed(iOException0, list0);
                }
                list0 = CollectionsKt.plus(list0, iOException0);
            }
            catch(Throwable throwable0) {
                realCall0.exitNetworkInterceptorExchange$okhttp(true);
                throw throwable0;
            }
            realCall0.exitNetworkInterceptorExchange$okhttp(true);
            z = false;
            continue;
            try {
            label_19:
                response0 = response1.newBuilder().request(request0).priorResponse((response0 == null ? null : _ResponseCommonKt.stripBody(response0))).build();
                Exchange exchange0 = realCall0.getInterceptorScopedExchange$okhttp();
                request1 = this.followUpRequest(response0, exchange0);
                if(request1 == null) {
                    if(exchange0 != null && exchange0.isDuplex$okhttp()) {
                        realCall0.timeoutEarlyExit();
                    }
                    goto label_25;
                }
                goto label_27;
            }
            catch(Throwable throwable0) {
                realCall0.exitNetworkInterceptorExchange$okhttp(true);
                throw throwable0;
            }
        label_25:
            realCall0.exitNetworkInterceptorExchange$okhttp(false);
            return response0;
            try {
            label_27:
                RequestBody requestBody0 = request1.body();
                if(requestBody0 != null && requestBody0.isOneShot()) {
                    goto label_37;
                }
                _UtilCommonKt.closeQuietly(response0.body());
                ++v;
                if(v > 20) {
                    throw new ProtocolException("Too many follow-up requests: " + v);
                }
            }
            catch(Throwable throwable0) {
                realCall0.exitNetworkInterceptorExchange$okhttp(true);
                throw throwable0;
            }
            realCall0.exitNetworkInterceptorExchange$okhttp(true);
            request0 = request1;
            z = true;
            continue;
        label_37:
            realCall0.exitNetworkInterceptorExchange$okhttp(false);
            return response0;
        }
        try {
            throw new IOException("Canceled");
        }
        catch(Throwable throwable0) {
            realCall0.exitNetworkInterceptorExchange$okhttp(true);
            throw throwable0;
        }
    }

    private final boolean isRecoverable(IOException iOException0, boolean z) {
        if(iOException0 instanceof ProtocolException) {
            return false;
        }
        if(iOException0 instanceof InterruptedIOException) {
            return iOException0 instanceof SocketTimeoutException && !z;
        }
        return !(iOException0 instanceof SSLHandshakeException) || !(iOException0.getCause() instanceof CertificateException) ? !(iOException0 instanceof SSLPeerUnverifiedException) : false;
    }

    private final boolean recover(IOException iOException0, RealCall realCall0, Request request0, boolean z) {
        if(!this.client.retryOnConnectionFailure()) {
            return false;
        }
        if(z && this.requestIsOneShot(iOException0, request0)) {
            return false;
        }
        return this.isRecoverable(iOException0, z) ? realCall0.retryAfterFailure() : false;
    }

    private final boolean requestIsOneShot(IOException iOException0, Request request0) {
        RequestBody requestBody0 = request0.body();
        return requestBody0 != null && requestBody0.isOneShot() || iOException0 instanceof FileNotFoundException;
    }

    private final int retryAfter(Response response0, int v) {
        String s = Response.header$default(response0, "Retry-After", null, 2, null);
        if(s == null) {
            return v;
        }
        if(new Regex("\\d+").matches(s)) {
            Integer integer0 = Integer.valueOf(s);
            Intrinsics.checkNotNullExpressionValue(integer0, "valueOf(header)");
            return integer0.intValue();
        }
        return 0x7FFFFFFF;
    }
}

