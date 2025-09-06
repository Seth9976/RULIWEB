package okhttp3.internal.http;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.Interceptor.Chain;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealCall;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001A\u00020\b\u0012\b\u0010\t\u001A\u0004\u0018\u00010\n\u0012\u0006\u0010\u000B\u001A\u00020\f\u0012\u0006\u0010\r\u001A\u00020\b\u0012\u0006\u0010\u000E\u001A\u00020\b\u0012\u0006\u0010\u000F\u001A\u00020\b\u00A2\u0006\u0002\u0010\u0010J\b\u0010\u0002\u001A\u00020\u001CH\u0016J\b\u0010\r\u001A\u00020\bH\u0016J\n\u0010\u001D\u001A\u0004\u0018\u00010\u001EH\u0016JK\u0010\u001F\u001A\u00020\u00002\b\b\u0002\u0010\u0007\u001A\u00020\b2\n\b\u0002\u0010\t\u001A\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000B\u001A\u00020\f2\b\b\u0002\u0010\r\u001A\u00020\b2\b\b\u0002\u0010\u000E\u001A\u00020\b2\b\b\u0002\u0010\u000F\u001A\u00020\bH\u0000\u00A2\u0006\u0002\b J\u0010\u0010!\u001A\u00020\"2\u0006\u0010\u000B\u001A\u00020\fH\u0016J\b\u0010\u000E\u001A\u00020\bH\u0016J\b\u0010\u000B\u001A\u00020\fH\u0016J\u0018\u0010#\u001A\u00020\u00012\u0006\u0010$\u001A\u00020\b2\u0006\u0010%\u001A\u00020&H\u0016J\u0018\u0010\'\u001A\u00020\u00012\u0006\u0010$\u001A\u00020\b2\u0006\u0010%\u001A\u00020&H\u0016J\u0018\u0010(\u001A\u00020\u00012\u0006\u0010$\u001A\u00020\b2\u0006\u0010%\u001A\u00020&H\u0016J\b\u0010\u000F\u001A\u00020\bH\u0016R\u0014\u0010\u0002\u001A\u00020\u0003X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0011\u0010\u0012R\u000E\u0010\u0013\u001A\u00020\bX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010\r\u001A\u00020\bX\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0014\u0010\u0015R\u0016\u0010\t\u001A\u0004\u0018\u00010\nX\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0016\u0010\u0017R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u000E\u001A\u00020\bX\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0018\u0010\u0015R\u0014\u0010\u000B\u001A\u00020\fX\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0019\u0010\u001AR\u0014\u0010\u000F\u001A\u00020\bX\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001B\u0010\u0015\u00A8\u0006)"}, d2 = {"Lokhttp3/internal/http/RealInterceptorChain;", "Lokhttp3/Interceptor$Chain;", "call", "Lokhttp3/internal/connection/RealCall;", "interceptors", "", "Lokhttp3/Interceptor;", "index", "", "exchange", "Lokhttp3/internal/connection/Exchange;", "request", "Lokhttp3/Request;", "connectTimeoutMillis", "readTimeoutMillis", "writeTimeoutMillis", "(Lokhttp3/internal/connection/RealCall;Ljava/util/List;ILokhttp3/internal/connection/Exchange;Lokhttp3/Request;III)V", "getCall$okhttp", "()Lokhttp3/internal/connection/RealCall;", "calls", "getConnectTimeoutMillis$okhttp", "()I", "getExchange$okhttp", "()Lokhttp3/internal/connection/Exchange;", "getReadTimeoutMillis$okhttp", "getRequest$okhttp", "()Lokhttp3/Request;", "getWriteTimeoutMillis$okhttp", "Lokhttp3/Call;", "connection", "Lokhttp3/Connection;", "copy", "copy$okhttp", "proceed", "Lokhttp3/Response;", "withConnectTimeout", "timeout", "unit", "Ljava/util/concurrent/TimeUnit;", "withReadTimeout", "withWriteTimeout", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class RealInterceptorChain implements Chain {
    private final RealCall call;
    private int calls;
    private final int connectTimeoutMillis;
    private final Exchange exchange;
    private final int index;
    private final List interceptors;
    private final int readTimeoutMillis;
    private final Request request;
    private final int writeTimeoutMillis;

    public RealInterceptorChain(RealCall realCall0, List list0, int v, Exchange exchange0, Request request0, int v1, int v2, int v3) {
        Intrinsics.checkNotNullParameter(realCall0, "call");
        Intrinsics.checkNotNullParameter(list0, "interceptors");
        Intrinsics.checkNotNullParameter(request0, "request");
        super();
        this.call = realCall0;
        this.interceptors = list0;
        this.index = v;
        this.exchange = exchange0;
        this.request = request0;
        this.connectTimeoutMillis = v1;
        this.readTimeoutMillis = v2;
        this.writeTimeoutMillis = v3;
    }

    @Override  // okhttp3.Interceptor$Chain
    public Call call() {
        return this.call;
    }

    @Override  // okhttp3.Interceptor$Chain
    public int connectTimeoutMillis() {
        return this.connectTimeoutMillis;
    }

    @Override  // okhttp3.Interceptor$Chain
    public Connection connection() {
        return this.exchange == null ? null : this.exchange.getConnection$okhttp();
    }

    public final RealInterceptorChain copy$okhttp(int v, Exchange exchange0, Request request0, int v1, int v2, int v3) {
        Intrinsics.checkNotNullParameter(request0, "request");
        return new RealInterceptorChain(this.call, this.interceptors, v, exchange0, request0, v1, v2, v3);
    }

    public static RealInterceptorChain copy$okhttp$default(RealInterceptorChain realInterceptorChain0, int v, Exchange exchange0, Request request0, int v1, int v2, int v3, int v4, Object object0) {
        if((v4 & 1) != 0) {
            v = realInterceptorChain0.index;
        }
        if((v4 & 2) != 0) {
            exchange0 = realInterceptorChain0.exchange;
        }
        if((v4 & 4) != 0) {
            request0 = realInterceptorChain0.request;
        }
        if((v4 & 8) != 0) {
            v1 = realInterceptorChain0.connectTimeoutMillis;
        }
        if((v4 & 16) != 0) {
            v2 = realInterceptorChain0.readTimeoutMillis;
        }
        if((v4 & 0x20) != 0) {
            v3 = realInterceptorChain0.writeTimeoutMillis;
        }
        return realInterceptorChain0.copy$okhttp(v, exchange0, request0, v1, v2, v3);
    }

    public final RealCall getCall$okhttp() {
        return this.call;
    }

    public final int getConnectTimeoutMillis$okhttp() {
        return this.connectTimeoutMillis;
    }

    public final Exchange getExchange$okhttp() {
        return this.exchange;
    }

    public final int getReadTimeoutMillis$okhttp() {
        return this.readTimeoutMillis;
    }

    public final Request getRequest$okhttp() {
        return this.request;
    }

    public final int getWriteTimeoutMillis$okhttp() {
        return this.writeTimeoutMillis;
    }

    @Override  // okhttp3.Interceptor$Chain
    public Response proceed(Request request0) throws IOException {
        Intrinsics.checkNotNullParameter(request0, "request");
        if(this.index >= this.interceptors.size()) {
            throw new IllegalStateException("Check failed.");
        }
        ++this.calls;
        Exchange exchange0 = this.exchange;
        if(exchange0 != null) {
            if(!exchange0.getFinder$okhttp().getRoutePlanner().sameHostAndPort(request0.url())) {
                throw new IllegalStateException(("network interceptor " + this.interceptors.get(this.index - 1) + " must retain the same host and port").toString());
            }
            if(this.calls != 1) {
                throw new IllegalStateException(("network interceptor " + this.interceptors.get(this.index - 1) + " must call proceed() exactly once").toString());
            }
        }
        RealInterceptorChain realInterceptorChain0 = RealInterceptorChain.copy$okhttp$default(this, this.index + 1, null, request0, 0, 0, 0, 58, null);
        Interceptor interceptor0 = (Interceptor)this.interceptors.get(this.index);
        Response response0 = interceptor0.intercept(realInterceptorChain0);
        if(response0 == null) {
            throw new NullPointerException("interceptor " + interceptor0 + " returned null");
        }
        if(this.exchange != null && this.index + 1 < this.interceptors.size() && realInterceptorChain0.calls != 1) {
            throw new IllegalStateException(("network interceptor " + interceptor0 + " must call proceed() exactly once").toString());
        }
        return response0;
    }

    @Override  // okhttp3.Interceptor$Chain
    public int readTimeoutMillis() {
        return this.readTimeoutMillis;
    }

    @Override  // okhttp3.Interceptor$Chain
    public Request request() {
        return this.request;
    }

    @Override  // okhttp3.Interceptor$Chain
    public Chain withConnectTimeout(int v, TimeUnit timeUnit0) {
        Intrinsics.checkNotNullParameter(timeUnit0, "unit");
        if(this.exchange != null) {
            throw new IllegalStateException("Timeouts can\'t be adjusted in a network interceptor");
        }
        return RealInterceptorChain.copy$okhttp$default(this, 0, null, null, _UtilJvmKt.checkDuration("connectTimeout", ((long)v), timeUnit0), 0, 0, 55, null);
    }

    @Override  // okhttp3.Interceptor$Chain
    public Chain withReadTimeout(int v, TimeUnit timeUnit0) {
        Intrinsics.checkNotNullParameter(timeUnit0, "unit");
        if(this.exchange != null) {
            throw new IllegalStateException("Timeouts can\'t be adjusted in a network interceptor");
        }
        return RealInterceptorChain.copy$okhttp$default(this, 0, null, null, 0, _UtilJvmKt.checkDuration("readTimeout", ((long)v), timeUnit0), 0, 0x2F, null);
    }

    @Override  // okhttp3.Interceptor$Chain
    public Chain withWriteTimeout(int v, TimeUnit timeUnit0) {
        Intrinsics.checkNotNullParameter(timeUnit0, "unit");
        if(this.exchange != null) {
            throw new IllegalStateException("Timeouts can\'t be adjusted in a network interceptor");
        }
        return RealInterceptorChain.copy$okhttp$default(this, 0, null, null, 0, 0, _UtilJvmKt.checkDuration("writeTimeout", ((long)v), timeUnit0), 0x1F, null);
    }

    @Override  // okhttp3.Interceptor$Chain
    public int writeTimeoutMillis() {
        return this.writeTimeoutMillis;
    }
}

