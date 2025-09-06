package okhttp3.logging;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001CB\u000F\b\u0002\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u00A2\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\fH\u0016J\u0018\u0010\r\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000E\u001A\u00020\fH\u0016J\u0010\u0010\u000F\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0016J\u0010\u0010\u0010\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0016J\u0018\u0010\u0011\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u0012\u001A\u00020\u0013H\u0016J\u0010\u0010\u0014\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0016J\u0010\u0010\u0015\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0016J*\u0010\u0016\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u0017\u001A\u00020\u00182\u0006\u0010\u0019\u001A\u00020\u001A2\b\u0010\u001B\u001A\u0004\u0018\u00010\u001CH\u0016J2\u0010\u001D\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u0017\u001A\u00020\u00182\u0006\u0010\u0019\u001A\u00020\u001A2\b\u0010\u001B\u001A\u0004\u0018\u00010\u001C2\u0006\u0010\u0012\u001A\u00020\u0013H\u0016J \u0010\u001E\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u0017\u001A\u00020\u00182\u0006\u0010\u0019\u001A\u00020\u001AH\u0016J\u0018\u0010\u001F\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010 \u001A\u00020!H\u0016J\u0018\u0010\"\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010 \u001A\u00020!H\u0016J&\u0010#\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010$\u001A\u00020%2\f\u0010&\u001A\b\u0012\u0004\u0012\u00020(0\'H\u0016J\u0018\u0010)\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010$\u001A\u00020%H\u0016J\u0010\u0010*\u001A\u00020\b2\u0006\u0010+\u001A\u00020%H\u0002J&\u0010,\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010-\u001A\u00020.2\f\u0010/\u001A\b\u0012\u0004\u0012\u00020\u001A0\'H\u0016J\u0018\u00100\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010-\u001A\u00020.H\u0016J\u0018\u00101\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u00102\u001A\u00020\u0006H\u0016J\u0010\u00103\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0016J\u0018\u00104\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u0012\u001A\u00020\u0013H\u0016J\u0018\u00105\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u00106\u001A\u000207H\u0016J\u0010\u00108\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0016J\u0018\u00109\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u00102\u001A\u00020\u0006H\u0016J\u0010\u0010:\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0016J\u0018\u0010;\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u0012\u001A\u00020\u0013H\u0016J\u0018\u0010<\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000E\u001A\u00020\fH\u0016J\u0010\u0010=\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0016J\u0018\u0010>\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000E\u001A\u00020\fH\u0016J\u001A\u0010?\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\b\u0010@\u001A\u0004\u0018\u00010AH\u0016J\u0010\u0010B\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0016R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u000E\u00A2\u0006\u0002\n\u0000\u00A8\u0006D"}, d2 = {"Lokhttp3/logging/LoggingEventListener;", "Lokhttp3/EventListener;", "logger", "Lokhttp3/logging/HttpLoggingInterceptor$Logger;", "(Lokhttp3/logging/HttpLoggingInterceptor$Logger;)V", "startNs", "", "cacheConditionalHit", "", "call", "Lokhttp3/Call;", "cachedResponse", "Lokhttp3/Response;", "cacheHit", "response", "cacheMiss", "callEnd", "callFailed", "ioe", "Ljava/io/IOException;", "callStart", "canceled", "connectEnd", "inetSocketAddress", "Ljava/net/InetSocketAddress;", "proxy", "Ljava/net/Proxy;", "protocol", "Lokhttp3/Protocol;", "connectFailed", "connectStart", "connectionAcquired", "connection", "Lokhttp3/Connection;", "connectionReleased", "dnsEnd", "domainName", "", "inetAddressList", "", "Ljava/net/InetAddress;", "dnsStart", "logWithTime", "message", "proxySelectEnd", "url", "Lokhttp3/HttpUrl;", "proxies", "proxySelectStart", "requestBodyEnd", "byteCount", "requestBodyStart", "requestFailed", "requestHeadersEnd", "request", "Lokhttp3/Request;", "requestHeadersStart", "responseBodyEnd", "responseBodyStart", "responseFailed", "responseHeadersEnd", "responseHeadersStart", "satisfactionFailure", "secureConnectEnd", "handshake", "Lokhttp3/Handshake;", "secureConnectStart", "Factory", "okhttp-logging-interceptor"}, k = 1, mv = {1, 4, 0})
public final class LoggingEventListener extends EventListener {
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0016R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lokhttp3/logging/LoggingEventListener$Factory;", "Lokhttp3/EventListener$Factory;", "logger", "Lokhttp3/logging/HttpLoggingInterceptor$Logger;", "(Lokhttp3/logging/HttpLoggingInterceptor$Logger;)V", "create", "Lokhttp3/EventListener;", "call", "Lokhttp3/Call;", "okhttp-logging-interceptor"}, k = 1, mv = {1, 4, 0})
    public static class Factory implements okhttp3.EventListener.Factory {
        private final Logger logger;

        public Factory() {
            this(null, 1, null);
        }

        public Factory(Logger httpLoggingInterceptor$Logger0) {
            Intrinsics.checkNotNullParameter(httpLoggingInterceptor$Logger0, "logger");
            super();
            this.logger = httpLoggingInterceptor$Logger0;
        }

        public Factory(Logger httpLoggingInterceptor$Logger0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
            if((v & 1) != 0) {
                httpLoggingInterceptor$Logger0 = Logger.DEFAULT;
            }
            this(httpLoggingInterceptor$Logger0);
        }

        @Override  // okhttp3.EventListener$Factory
        public EventListener create(Call call0) {
            Intrinsics.checkNotNullParameter(call0, "call");
            return new LoggingEventListener(this.logger, null);
        }
    }

    private final Logger logger;
    private long startNs;

    private LoggingEventListener(Logger httpLoggingInterceptor$Logger0) {
        this.logger = httpLoggingInterceptor$Logger0;
    }

    public LoggingEventListener(Logger httpLoggingInterceptor$Logger0, DefaultConstructorMarker defaultConstructorMarker0) {
        this(httpLoggingInterceptor$Logger0);
    }

    @Override  // okhttp3.EventListener
    public void cacheConditionalHit(Call call0, Response response0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(response0, "cachedResponse");
        this.logWithTime("cacheConditionalHit: " + response0);
    }

    @Override  // okhttp3.EventListener
    public void cacheHit(Call call0, Response response0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(response0, "response");
        this.logWithTime("cacheHit: " + response0);
    }

    @Override  // okhttp3.EventListener
    public void cacheMiss(Call call0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        this.logWithTime("cacheMiss");
    }

    @Override  // okhttp3.EventListener
    public void callEnd(Call call0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        this.logWithTime("callEnd");
    }

    @Override  // okhttp3.EventListener
    public void callFailed(Call call0, IOException iOException0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(iOException0, "ioe");
        this.logWithTime("callFailed: " + iOException0);
    }

    @Override  // okhttp3.EventListener
    public void callStart(Call call0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        this.startNs = System.nanoTime();
        this.logWithTime("callStart: " + call0.request());
    }

    @Override  // okhttp3.EventListener
    public void canceled(Call call0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        this.logWithTime("canceled");
    }

    @Override  // okhttp3.EventListener
    public void connectEnd(Call call0, InetSocketAddress inetSocketAddress0, Proxy proxy0, Protocol protocol0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(inetSocketAddress0, "inetSocketAddress");
        Intrinsics.checkNotNullParameter(proxy0, "proxy");
        this.logWithTime("connectEnd: " + protocol0);
    }

    @Override  // okhttp3.EventListener
    public void connectFailed(Call call0, InetSocketAddress inetSocketAddress0, Proxy proxy0, Protocol protocol0, IOException iOException0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(inetSocketAddress0, "inetSocketAddress");
        Intrinsics.checkNotNullParameter(proxy0, "proxy");
        Intrinsics.checkNotNullParameter(iOException0, "ioe");
        this.logWithTime("connectFailed: " + protocol0 + ' ' + iOException0);
    }

    @Override  // okhttp3.EventListener
    public void connectStart(Call call0, InetSocketAddress inetSocketAddress0, Proxy proxy0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(inetSocketAddress0, "inetSocketAddress");
        Intrinsics.checkNotNullParameter(proxy0, "proxy");
        this.logWithTime("connectStart: " + inetSocketAddress0 + ' ' + proxy0);
    }

    @Override  // okhttp3.EventListener
    public void connectionAcquired(Call call0, Connection connection0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(connection0, "connection");
        this.logWithTime("connectionAcquired: " + connection0);
    }

    @Override  // okhttp3.EventListener
    public void connectionReleased(Call call0, Connection connection0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(connection0, "connection");
        this.logWithTime("connectionReleased");
    }

    @Override  // okhttp3.EventListener
    public void dnsEnd(Call call0, String s, List list0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(s, "domainName");
        Intrinsics.checkNotNullParameter(list0, "inetAddressList");
        this.logWithTime("dnsEnd: " + list0);
    }

    @Override  // okhttp3.EventListener
    public void dnsStart(Call call0, String s) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(s, "domainName");
        this.logWithTime("dnsStart: " + s);
    }

    private final void logWithTime(String s) {
        long v = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - this.startNs);
        this.logger.log("[" + v + " ms] " + s);
    }

    @Override  // okhttp3.EventListener
    public void proxySelectEnd(Call call0, HttpUrl httpUrl0, List list0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(httpUrl0, "url");
        Intrinsics.checkNotNullParameter(list0, "proxies");
        this.logWithTime("proxySelectEnd: " + list0);
    }

    @Override  // okhttp3.EventListener
    public void proxySelectStart(Call call0, HttpUrl httpUrl0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(httpUrl0, "url");
        this.logWithTime("proxySelectStart: " + httpUrl0);
    }

    @Override  // okhttp3.EventListener
    public void requestBodyEnd(Call call0, long v) {
        Intrinsics.checkNotNullParameter(call0, "call");
        this.logWithTime("requestBodyEnd: byteCount=" + v);
    }

    @Override  // okhttp3.EventListener
    public void requestBodyStart(Call call0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        this.logWithTime("requestBodyStart");
    }

    @Override  // okhttp3.EventListener
    public void requestFailed(Call call0, IOException iOException0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(iOException0, "ioe");
        this.logWithTime("requestFailed: " + iOException0);
    }

    @Override  // okhttp3.EventListener
    public void requestHeadersEnd(Call call0, Request request0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(request0, "request");
        this.logWithTime("requestHeadersEnd");
    }

    @Override  // okhttp3.EventListener
    public void requestHeadersStart(Call call0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        this.logWithTime("requestHeadersStart");
    }

    @Override  // okhttp3.EventListener
    public void responseBodyEnd(Call call0, long v) {
        Intrinsics.checkNotNullParameter(call0, "call");
        this.logWithTime("responseBodyEnd: byteCount=" + v);
    }

    @Override  // okhttp3.EventListener
    public void responseBodyStart(Call call0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        this.logWithTime("responseBodyStart");
    }

    @Override  // okhttp3.EventListener
    public void responseFailed(Call call0, IOException iOException0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(iOException0, "ioe");
        this.logWithTime("responseFailed: " + iOException0);
    }

    @Override  // okhttp3.EventListener
    public void responseHeadersEnd(Call call0, Response response0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(response0, "response");
        this.logWithTime("responseHeadersEnd: " + response0);
    }

    @Override  // okhttp3.EventListener
    public void responseHeadersStart(Call call0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        this.logWithTime("responseHeadersStart");
    }

    @Override  // okhttp3.EventListener
    public void satisfactionFailure(Call call0, Response response0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(response0, "response");
        this.logWithTime("satisfactionFailure: " + response0);
    }

    @Override  // okhttp3.EventListener
    public void secureConnectEnd(Call call0, Handshake handshake0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        this.logWithTime("secureConnectEnd: " + handshake0);
    }

    @Override  // okhttp3.EventListener
    public void secureConnectStart(Call call0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        this.logWithTime("secureConnectStart");
    }
}

