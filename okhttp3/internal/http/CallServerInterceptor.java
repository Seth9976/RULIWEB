package okhttp3.internal.http;

import java.io.IOException;
import java.net.ProtocolException;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Interceptor.Chain;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response.Builder;
import okhttp3.Response;
import okhttp3.internal._ResponseCommonKt;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.http2.ConnectionShutdownException;
import okio.BufferedSink;
import okio.Okio;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0016J\u0018\u0010\t\u001A\u00020\u00032\u0006\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\rH\u0002R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000E"}, d2 = {"Lokhttp3/internal/http/CallServerInterceptor;", "Lokhttp3/Interceptor;", "forWebSocket", "", "(Z)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "shouldIgnoreAndWaitForRealResponse", "code", "", "exchange", "Lokhttp3/internal/connection/Exchange;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class CallServerInterceptor implements Interceptor {
    private final boolean forWebSocket;

    public CallServerInterceptor(boolean z) {
        this.forWebSocket = z;
    }

    @Override  // okhttp3.Interceptor
    public Response intercept(Chain interceptor$Chain0) throws IOException {
        boolean z;
        Builder response$Builder0;
        Intrinsics.checkNotNullParameter(interceptor$Chain0, "chain");
        Exchange exchange0 = ((RealInterceptorChain)interceptor$Chain0).getExchange$okhttp();
        Intrinsics.checkNotNull(exchange0);
        Request request0 = ((RealInterceptorChain)interceptor$Chain0).getRequest$okhttp();
        RequestBody requestBody0 = request0.body();
        long v = System.currentTimeMillis();
        try {
            exchange0.writeRequestHeaders(request0);
            if(!HttpMethod.permitsRequestBody(request0.method()) || requestBody0 == null) {
                goto label_31;
            }
            else {
                if(StringsKt.equals("100-continue", request0.header("Expect"), true)) {
                    exchange0.flushRequest();
                    response$Builder0 = exchange0.readResponseHeaders(true);
                    goto label_11;
                }
                else {
                    goto label_16;
                }
                goto label_18;
            }
            goto label_39;
        }
        catch(IOException iOException0) {
            response$Builder0 = null;
            z = true;
            goto label_47;
        }
        try {
        label_11:
            exchange0.responseHeadersStart();
            z = false;
            goto label_18;
        }
        catch(IOException iOException0) {
            z = true;
            goto label_47;
        }
        try {
        label_16:
            response$Builder0 = null;
            z = true;
        }
        catch(IOException iOException0) {
            response$Builder0 = null;
            z = true;
            goto label_47;
        }
        try {
        label_18:
            if(response$Builder0 != null) {
                exchange0.noRequestBody();
                if(!exchange0.getConnection$okhttp().isMultiplexed$okhttp()) {
                    exchange0.noNewExchangesOnConnection();
                }
            }
            else if(requestBody0.isDuplex()) {
                exchange0.flushRequest();
                requestBody0.writeTo(Okio.buffer(exchange0.createRequestBody(request0, true)));
            }
            else {
                BufferedSink bufferedSink0 = Okio.buffer(exchange0.createRequestBody(request0, false));
                requestBody0.writeTo(bufferedSink0);
                bufferedSink0.close();
            }
            goto label_39;
        }
        catch(IOException iOException0) {
            goto label_47;
        }
        try {
        label_31:
            exchange0.noRequestBody();
            response$Builder0 = null;
            z = true;
            goto label_39;
        }
        catch(IOException iOException0) {
            response$Builder0 = null;
        }
        z = true;
        goto label_47;
        try {
        label_39:
            if(requestBody0 == null) {
                exchange0.finishRequest();
            }
            else if(!requestBody0.isDuplex()) {
                exchange0.finishRequest();
            }
            iOException0 = null;
            goto label_49;
        }
        catch(IOException iOException0) {
        }
    label_47:
        if(iOException0 instanceof ConnectionShutdownException || !exchange0.getHasFailure$okhttp()) {
            throw iOException0;
        }
        try {
        label_49:
            if(response$Builder0 == null) {
                response$Builder0 = exchange0.readResponseHeaders(false);
                Intrinsics.checkNotNull(response$Builder0);
                if(z) {
                    exchange0.responseHeadersStart();
                    z = false;
                }
            }
            Response response0 = response$Builder0.request(request0).handshake(exchange0.getConnection$okhttp().handshake()).sentRequestAtMillis(v).receivedResponseAtMillis(System.currentTimeMillis()).build();
            int v1 = response0.code();
            if(this.shouldIgnoreAndWaitForRealResponse(v1, exchange0)) {
                Builder response$Builder1 = exchange0.readResponseHeaders(false);
                Intrinsics.checkNotNull(response$Builder1);
                if(z) {
                    exchange0.responseHeadersStart();
                }
                response0 = response$Builder1.request(request0).handshake(exchange0.getConnection$okhttp().handshake()).sentRequestAtMillis(v).receivedResponseAtMillis(System.currentTimeMillis()).build();
                v1 = response0.code();
            }
            exchange0.responseHeadersEnd(response0);
            Response response1 = !this.forWebSocket || v1 != 101 ? response0.newBuilder().body(exchange0.openResponseBody(response0)).build() : _ResponseCommonKt.stripBody(response0);
            if(StringsKt.equals("close", response1.request().header("Connection"), true) || StringsKt.equals("close", Response.header$default(response1, "Connection", null, 2, null), true)) {
                exchange0.noNewExchangesOnConnection();
            }
            if((v1 == 204 || v1 == 205) && response1.body().contentLength() > 0L) {
                throw new ProtocolException("HTTP " + v1 + " had non-zero Content-Length: " + response1.body().contentLength());
            }
            return response1;
        }
        catch(IOException iOException1) {
            if(iOException0 == null) {
                throw iOException1;
            }
            ExceptionsKt.addSuppressed(iOException0, iOException1);
            throw iOException0;
        }
    }

    private final boolean shouldIgnoreAndWaitForRealResponse(int v, Exchange exchange0) {
        return v == 100 || v == 103;
    }
}

