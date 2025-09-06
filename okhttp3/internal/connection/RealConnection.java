package okhttp3.internal.connection;

import java.io.IOException;
import java.net.Proxy.Type;
import java.net.Socket;
import java.net.SocketException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.CertificatePinner;
import okhttp3.CipherSuite;
import okhttp3.Connection;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Route;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.http.ExchangeCodec.Carrier;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http1.Http1ExchangeCodec;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Http2Connection.Builder;
import okhttp3.internal.http2.Http2Connection.Listener;
import okhttp3.internal.http2.Http2Connection;
import okhttp3.internal.http2.Http2ExchangeCodec;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.internal.http2.Settings;
import okhttp3.internal.http2.StreamResetException;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.internal.ws.RealWebSocket.Streams;
import okio.BufferedSink;
import okio.BufferedSource;

@Metadata(d1 = {"\u0000\u00D4\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000E\n\u0002\b\u0005\u0018\u0000 o2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001oBa\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u0012\b\u0010\n\u001A\u0004\u0018\u00010\u000B\u0012\b\u0010\f\u001A\u0004\u0018\u00010\u000B\u0012\b\u0010\r\u001A\u0004\u0018\u00010\u000E\u0012\b\u0010\u000F\u001A\u0004\u0018\u00010\u0010\u0012\b\u0010\u0011\u001A\u0004\u0018\u00010\u0012\u0012\b\u0010\u0013\u001A\u0004\u0018\u00010\u0014\u0012\u0006\u0010\u0015\u001A\u00020\u0016\u00A2\u0006\u0002\u0010\u0017J\b\u0010=\u001A\u00020>H\u0016J\u0018\u0010?\u001A\u00020*2\u0006\u0010@\u001A\u00020A2\u0006\u0010\r\u001A\u00020\u000EH\u0002J%\u0010B\u001A\u00020>2\u0006\u0010C\u001A\u00020D2\u0006\u0010E\u001A\u00020\t2\u0006\u0010F\u001A\u00020GH\u0000\u00A2\u0006\u0002\bHJ\n\u0010\r\u001A\u0004\u0018\u00010\u000EH\u0016J\r\u0010I\u001A\u00020>H\u0000\u00A2\u0006\u0002\bJJ%\u0010K\u001A\u00020*2\u0006\u0010L\u001A\u00020M2\u000E\u0010N\u001A\n\u0012\u0004\u0012\u00020\t\u0018\u00010OH\u0000\u00A2\u0006\u0002\bPJ\u000E\u0010Q\u001A\u00020*2\u0006\u0010R\u001A\u00020*J\u001D\u0010S\u001A\u00020T2\u0006\u0010C\u001A\u00020D2\u0006\u0010U\u001A\u00020VH\u0000\u00A2\u0006\u0002\bWJ\u0015\u0010X\u001A\u00020Y2\u0006\u0010Z\u001A\u00020[H\u0000\u00A2\u0006\u0002\b\\J\r\u0010-\u001A\u00020>H\u0000\u00A2\u0006\u0002\b]J\b\u0010.\u001A\u00020>H\u0016J\u0018\u0010^\u001A\u00020>2\u0006\u0010_\u001A\u00020\"2\u0006\u0010`\u001A\u00020aH\u0016J\u0010\u0010b\u001A\u00020>2\u0006\u0010c\u001A\u00020dH\u0016J\b\u0010\u000F\u001A\u00020\u0010H\u0016J\b\u0010\b\u001A\u00020\tH\u0016J\u0016\u0010e\u001A\u00020*2\f\u0010f\u001A\b\u0012\u0004\u0012\u00020\t0OH\u0002J\b\u0010\f\u001A\u00020\u000BH\u0016J\u0006\u0010g\u001A\u00020>J\b\u0010h\u001A\u00020>H\u0002J\u0010\u0010i\u001A\u00020*2\u0006\u0010@\u001A\u00020AH\u0002J\b\u0010j\u001A\u00020kH\u0016J\u001A\u0010l\u001A\u00020>2\u0006\u0010m\u001A\u00020\u001C2\b\u0010n\u001A\u0004\u0018\u00010GH\u0016R\u000E\u0010\u0018\u001A\u00020\u0016X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u001D\u0010\u0019\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001C0\u001B0\u001A\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001D\u0010\u001ER\u0011\u0010\u0006\u001A\u00020\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001F\u0010 R\u0010\u0010\r\u001A\u0004\u0018\u00010\u000EX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010!\u001A\u0004\u0018\u00010\"X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u001A\u0010#\u001A\u00020$X\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b%\u0010&\"\u0004\b\'\u0010(R\u0014\u0010)\u001A\u00020*8@X\u0080\u0004\u00A2\u0006\u0006\u001A\u0004\b+\u0010,R\u000E\u0010-\u001A\u00020*X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u001A\u0010.\u001A\u00020*X\u0086\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b/\u0010,\"\u0004\b0\u00101R\u000E\u0010\u0015\u001A\u00020\u0016X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u000F\u001A\u0004\u0018\u00010\u0010X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\n\u001A\u0004\u0018\u00010\u000BX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u00102\u001A\u00020\u0016X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010\b\u001A\u00020\tX\u0096\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b3\u00104R\u001A\u00105\u001A\u00020\u0016X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b6\u00107\"\u0004\b8\u00109R\u0010\u0010\u0013\u001A\u0004\u0018\u00010\u0014X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\f\u001A\u0004\u0018\u00010\u000BX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001A\u0004\u0018\u00010\u0012X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010:\u001A\u00020\u0016X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001A\u00020\u0005\u00A2\u0006\b\n\u0000\u001A\u0004\b;\u0010<\u00A8\u0006p"}, d2 = {"Lokhttp3/internal/connection/RealConnection;", "Lokhttp3/internal/http2/Http2Connection$Listener;", "Lokhttp3/Connection;", "Lokhttp3/internal/http/ExchangeCodec$Carrier;", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "connectionPool", "Lokhttp3/internal/connection/RealConnectionPool;", "route", "Lokhttp3/Route;", "rawSocket", "Ljava/net/Socket;", "socket", "handshake", "Lokhttp3/Handshake;", "protocol", "Lokhttp3/Protocol;", "source", "Lokio/BufferedSource;", "sink", "Lokio/BufferedSink;", "pingIntervalMillis", "", "(Lokhttp3/internal/concurrent/TaskRunner;Lokhttp3/internal/connection/RealConnectionPool;Lokhttp3/Route;Ljava/net/Socket;Ljava/net/Socket;Lokhttp3/Handshake;Lokhttp3/Protocol;Lokio/BufferedSource;Lokio/BufferedSink;I)V", "allocationLimit", "calls", "", "Ljava/lang/ref/Reference;", "Lokhttp3/internal/connection/RealCall;", "getCalls", "()Ljava/util/List;", "getConnectionPool", "()Lokhttp3/internal/connection/RealConnectionPool;", "http2Connection", "Lokhttp3/internal/http2/Http2Connection;", "idleAtNs", "", "getIdleAtNs", "()J", "setIdleAtNs", "(J)V", "isMultiplexed", "", "isMultiplexed$okhttp", "()Z", "noCoalescedConnections", "noNewExchanges", "getNoNewExchanges", "setNoNewExchanges", "(Z)V", "refusedStreamCount", "getRoute", "()Lokhttp3/Route;", "routeFailureCount", "getRouteFailureCount$okhttp", "()I", "setRouteFailureCount$okhttp", "(I)V", "successCount", "getTaskRunner", "()Lokhttp3/internal/concurrent/TaskRunner;", "cancel", "", "certificateSupportHost", "url", "Lokhttp3/HttpUrl;", "connectFailed", "client", "Lokhttp3/OkHttpClient;", "failedRoute", "failure", "Ljava/io/IOException;", "connectFailed$okhttp", "incrementSuccessCount", "incrementSuccessCount$okhttp", "isEligible", "address", "Lokhttp3/Address;", "routes", "", "isEligible$okhttp", "isHealthy", "doExtensiveChecks", "newCodec", "Lokhttp3/internal/http/ExchangeCodec;", "chain", "Lokhttp3/internal/http/RealInterceptorChain;", "newCodec$okhttp", "newWebSocketStreams", "Lokhttp3/internal/ws/RealWebSocket$Streams;", "exchange", "Lokhttp3/internal/connection/Exchange;", "newWebSocketStreams$okhttp", "noCoalescedConnections$okhttp", "onSettings", "connection", "settings", "Lokhttp3/internal/http2/Settings;", "onStream", "stream", "Lokhttp3/internal/http2/Http2Stream;", "routeMatchesAny", "candidates", "start", "startHttp2", "supportsUrl", "toString", "", "trackFailure", "call", "e", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class RealConnection extends Listener implements Connection, Carrier {
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u0004R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lokhttp3/internal/connection/RealConnection$Companion;", "", "()V", "IDLE_CONNECTION_HEALTHY_NS", "", "newTestConnection", "Lokhttp3/internal/connection/RealConnection;", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "connectionPool", "Lokhttp3/internal/connection/RealConnectionPool;", "route", "Lokhttp3/Route;", "socket", "Ljava/net/Socket;", "idleAtNs", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final RealConnection newTestConnection(TaskRunner taskRunner0, RealConnectionPool realConnectionPool0, Route route0, Socket socket0, long v) {
            Intrinsics.checkNotNullParameter(taskRunner0, "taskRunner");
            Intrinsics.checkNotNullParameter(realConnectionPool0, "connectionPool");
            Intrinsics.checkNotNullParameter(route0, "route");
            Intrinsics.checkNotNullParameter(socket0, "socket");
            RealConnection realConnection0 = new RealConnection(taskRunner0, realConnectionPool0, route0, null, socket0, null, null, null, null, 0);
            realConnection0.setIdleAtNs(v);
            return realConnection0;
        }
    }

    public static final Companion Companion = null;
    public static final long IDLE_CONNECTION_HEALTHY_NS = 10000000000L;
    private int allocationLimit;
    private final List calls;
    private final RealConnectionPool connectionPool;
    private Handshake handshake;
    private Http2Connection http2Connection;
    private long idleAtNs;
    private boolean noCoalescedConnections;
    private boolean noNewExchanges;
    private final int pingIntervalMillis;
    private Protocol protocol;
    private Socket rawSocket;
    private int refusedStreamCount;
    private final Route route;
    private int routeFailureCount;
    private BufferedSink sink;
    private Socket socket;
    private BufferedSource source;
    private int successCount;
    private final TaskRunner taskRunner;

    static {
        RealConnection.Companion = new Companion(null);
    }

    public RealConnection(TaskRunner taskRunner0, RealConnectionPool realConnectionPool0, Route route0, Socket socket0, Socket socket1, Handshake handshake0, Protocol protocol0, BufferedSource bufferedSource0, BufferedSink bufferedSink0, int v) {
        Intrinsics.checkNotNullParameter(taskRunner0, "taskRunner");
        Intrinsics.checkNotNullParameter(realConnectionPool0, "connectionPool");
        Intrinsics.checkNotNullParameter(route0, "route");
        super();
        this.taskRunner = taskRunner0;
        this.connectionPool = realConnectionPool0;
        this.route = route0;
        this.rawSocket = socket0;
        this.socket = socket1;
        this.handshake = handshake0;
        this.protocol = protocol0;
        this.source = bufferedSource0;
        this.sink = bufferedSink0;
        this.pingIntervalMillis = v;
        this.allocationLimit = 1;
        this.calls = new ArrayList();
        this.idleAtNs = 0x7FFFFFFFFFFFFFFFL;
    }

    @Override  // okhttp3.internal.http.ExchangeCodec$Carrier
    public void cancel() {
        Socket socket0 = this.rawSocket;
        if(socket0 != null) {
            _UtilJvmKt.closeQuietly(socket0);
        }
    }

    private final boolean certificateSupportHost(HttpUrl httpUrl0, Handshake handshake0) {
        List list0 = handshake0.peerCertificates();
        if(!list0.isEmpty()) {
            Object object0 = list0.get(0);
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type java.security.cert.X509Certificate");
            return OkHostnameVerifier.INSTANCE.verify(httpUrl0.host(), ((X509Certificate)object0));
        }
        return false;
    }

    public final void connectFailed$okhttp(OkHttpClient okHttpClient0, Route route0, IOException iOException0) {
        Intrinsics.checkNotNullParameter(okHttpClient0, "client");
        Intrinsics.checkNotNullParameter(route0, "failedRoute");
        Intrinsics.checkNotNullParameter(iOException0, "failure");
        if(route0.proxy().type() != Proxy.Type.DIRECT) {
            Address address0 = route0.address();
            address0.proxySelector().connectFailed(address0.url().uri(), route0.proxy().address(), iOException0);
        }
        okHttpClient0.getRouteDatabase$okhttp().failed(route0);
    }

    public final List getCalls() {
        return this.calls;
    }

    public final RealConnectionPool getConnectionPool() {
        return this.connectionPool;
    }

    public final long getIdleAtNs() {
        return this.idleAtNs;
    }

    public final boolean getNoNewExchanges() {
        return this.noNewExchanges;
    }

    @Override  // okhttp3.internal.http.ExchangeCodec$Carrier
    public Route getRoute() {
        return this.route;
    }

    public final int getRouteFailureCount$okhttp() {
        return this.routeFailureCount;
    }

    public final TaskRunner getTaskRunner() {
        return this.taskRunner;
    }

    @Override  // okhttp3.Connection
    public Handshake handshake() {
        return this.handshake;
    }

    public final void incrementSuccessCount$okhttp() {
        synchronized(this) {
            ++this.successCount;
        }
    }

    public final boolean isEligible$okhttp(Address address0, List list0) {
        Intrinsics.checkNotNullParameter(address0, "address");
        if(_UtilJvmKt.assertionsEnabled && !Thread.holdsLock(this)) {
            throw new AssertionError("Thread jeb-dexdec-sb-st-13453 MUST hold lock on " + this);
        }
        if(this.calls.size() >= this.allocationLimit || this.noNewExchanges || !this.getRoute().address().equalsNonHost$okhttp(address0)) {
            return false;
        }
        if(Intrinsics.areEqual(address0.url().host(), this.route().address().url().host())) {
            return true;
        }
        if(this.http2Connection == null) {
            return false;
        }
        if(list0 == null || !this.routeMatchesAny(list0) || address0.hostnameVerifier() != OkHostnameVerifier.INSTANCE) {
            return false;
        }
        if(!this.supportsUrl(address0.url())) {
            return false;
        }
        try {
            CertificatePinner certificatePinner0 = address0.certificatePinner();
            Intrinsics.checkNotNull(certificatePinner0);
            Handshake handshake0 = this.handshake();
            Intrinsics.checkNotNull(handshake0);
            certificatePinner0.check(address0.url().host(), handshake0.peerCertificates());
            return true;
        }
        catch(SSLPeerUnverifiedException unused_ex) {
            return false;
        }
    }

    public final boolean isHealthy(boolean z) {
        if(_UtilJvmKt.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread jeb-dexdec-sb-st-13471 MUST NOT hold lock on " + this);
        }
        long v = System.nanoTime();
        Socket socket0 = this.rawSocket;
        Intrinsics.checkNotNull(socket0);
        Socket socket1 = this.socket;
        Intrinsics.checkNotNull(socket1);
        BufferedSource bufferedSource0 = this.source;
        Intrinsics.checkNotNull(bufferedSource0);
        if(!socket0.isClosed() && !socket1.isClosed() && !socket1.isInputShutdown() && !socket1.isOutputShutdown()) {
            Http2Connection http2Connection0 = this.http2Connection;
            if(http2Connection0 != null) {
                return http2Connection0.isHealthy(v);
            }
            return v - this.idleAtNs < 10000000000L || !z ? true : _UtilJvmKt.isHealthy(socket1, bufferedSource0);
        }
        return false;
    }

    public final boolean isMultiplexed$okhttp() {
        return this.http2Connection != null;
    }

    public final ExchangeCodec newCodec$okhttp(OkHttpClient okHttpClient0, RealInterceptorChain realInterceptorChain0) throws SocketException {
        Intrinsics.checkNotNullParameter(okHttpClient0, "client");
        Intrinsics.checkNotNullParameter(realInterceptorChain0, "chain");
        Socket socket0 = this.socket;
        Intrinsics.checkNotNull(socket0);
        BufferedSource bufferedSource0 = this.source;
        Intrinsics.checkNotNull(bufferedSource0);
        BufferedSink bufferedSink0 = this.sink;
        Intrinsics.checkNotNull(bufferedSink0);
        Http2Connection http2Connection0 = this.http2Connection;
        if(http2Connection0 != null) {
            return new Http2ExchangeCodec(okHttpClient0, this, realInterceptorChain0, http2Connection0);
        }
        socket0.setSoTimeout(realInterceptorChain0.readTimeoutMillis());
        bufferedSource0.timeout().timeout(((long)realInterceptorChain0.getReadTimeoutMillis$okhttp()), TimeUnit.MILLISECONDS);
        bufferedSink0.timeout().timeout(((long)realInterceptorChain0.getWriteTimeoutMillis$okhttp()), TimeUnit.MILLISECONDS);
        return new Http1ExchangeCodec(okHttpClient0, this, bufferedSource0, bufferedSink0);
    }

    public final Streams newWebSocketStreams$okhttp(Exchange exchange0) throws SocketException {
        Intrinsics.checkNotNullParameter(exchange0, "exchange");
        Socket socket0 = this.socket;
        Intrinsics.checkNotNull(socket0);
        BufferedSource bufferedSource0 = this.source;
        Intrinsics.checkNotNull(bufferedSource0);
        BufferedSink bufferedSink0 = this.sink;
        Intrinsics.checkNotNull(bufferedSink0);
        socket0.setSoTimeout(0);
        this.noNewExchanges();
        return new Streams(bufferedSink0, exchange0) {
            final Exchange $exchange;

            {
                this.$exchange = exchange0;
                super(true, bufferedSource0, bufferedSink0);
            }

            @Override
            public void close() {
                this.$exchange.bodyComplete(-1L, true, true, null);
            }
        };
    }

    public final void noCoalescedConnections$okhttp() {
        synchronized(this) {
            this.noCoalescedConnections = true;
        }
    }

    @Override  // okhttp3.internal.http.ExchangeCodec$Carrier
    public void noNewExchanges() {
        synchronized(this) {
            this.noNewExchanges = true;
        }
    }

    @Override  // okhttp3.internal.http2.Http2Connection$Listener
    public void onSettings(Http2Connection http2Connection0, Settings settings0) {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(http2Connection0, "connection");
            Intrinsics.checkNotNullParameter(settings0, "settings");
            this.allocationLimit = settings0.getMaxConcurrentStreams();
        }
    }

    @Override  // okhttp3.internal.http2.Http2Connection$Listener
    public void onStream(Http2Stream http2Stream0) throws IOException {
        Intrinsics.checkNotNullParameter(http2Stream0, "stream");
        http2Stream0.close(ErrorCode.REFUSED_STREAM, null);
    }

    @Override  // okhttp3.Connection
    public Protocol protocol() {
        Protocol protocol0 = this.protocol;
        Intrinsics.checkNotNull(protocol0);
        return protocol0;
    }

    @Override  // okhttp3.Connection
    public Route route() {
        return this.getRoute();
    }

    private final boolean routeMatchesAny(List list0) {
        if(list0 instanceof Collection && list0.isEmpty()) {
            return false;
        }
        for(Object object0: list0) {
            if(((Route)object0).proxy().type() == Proxy.Type.DIRECT && this.getRoute().proxy().type() == Proxy.Type.DIRECT && Intrinsics.areEqual(this.getRoute().socketAddress(), ((Route)object0).socketAddress())) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    public final void setIdleAtNs(long v) {
        this.idleAtNs = v;
    }

    public final void setNoNewExchanges(boolean z) {
        this.noNewExchanges = z;
    }

    public final void setRouteFailureCount$okhttp(int v) {
        this.routeFailureCount = v;
    }

    @Override  // okhttp3.Connection
    public Socket socket() {
        Socket socket0 = this.socket;
        Intrinsics.checkNotNull(socket0);
        return socket0;
    }

    public final void start() throws IOException {
        this.idleAtNs = System.nanoTime();
        if(this.protocol != Protocol.HTTP_2 && this.protocol != Protocol.H2_PRIOR_KNOWLEDGE) {
            return;
        }
        this.startHttp2();
    }

    private final void startHttp2() throws IOException {
        Socket socket0 = this.socket;
        Intrinsics.checkNotNull(socket0);
        BufferedSource bufferedSource0 = this.source;
        Intrinsics.checkNotNull(bufferedSource0);
        BufferedSink bufferedSink0 = this.sink;
        Intrinsics.checkNotNull(bufferedSink0);
        socket0.setSoTimeout(0);
        Http2Connection http2Connection0 = new Builder(true, this.taskRunner).socket(socket0, this.getRoute().address().url().host(), bufferedSource0, bufferedSink0).listener(this).pingIntervalMillis(this.pingIntervalMillis).build();
        this.http2Connection = http2Connection0;
        this.allocationLimit = Http2Connection.Companion.getDEFAULT_SETTINGS().getMaxConcurrentStreams();
        Http2Connection.start$default(http2Connection0, false, 1, null);
    }

    private final boolean supportsUrl(HttpUrl httpUrl0) {
        if(_UtilJvmKt.assertionsEnabled && !Thread.holdsLock(this)) {
            throw new AssertionError("Thread jeb-dexdec-sb-st-13318 MUST hold lock on " + this);
        }
        HttpUrl httpUrl1 = this.getRoute().address().url();
        if(httpUrl0.port() != httpUrl1.port()) {
            return false;
        }
        if(Intrinsics.areEqual(httpUrl0.host(), httpUrl1.host())) {
            return true;
        }
        if(!this.noCoalescedConnections) {
            Handshake handshake0 = this.handshake;
            if(handshake0 != null) {
                Intrinsics.checkNotNull(handshake0);
                return this.certificateSupportHost(httpUrl0, handshake0);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        CipherSuite cipherSuite0;
        StringBuilder stringBuilder0 = new StringBuilder("Connection{");
        stringBuilder0.append(this.getRoute().address().url().host());
        stringBuilder0.append(':');
        stringBuilder0.append(this.getRoute().address().url().port());
        stringBuilder0.append(", proxy=");
        stringBuilder0.append(this.getRoute().proxy());
        stringBuilder0.append(" hostAddress=");
        stringBuilder0.append(this.getRoute().socketAddress());
        stringBuilder0.append(" cipherSuite=");
        Handshake handshake0 = this.handshake;
        if(handshake0 == null) {
            cipherSuite0 = "none";
        }
        else {
            cipherSuite0 = handshake0.cipherSuite();
            if(cipherSuite0 == null) {
                cipherSuite0 = "none";
            }
        }
        stringBuilder0.append(cipherSuite0);
        stringBuilder0.append(" protocol=");
        stringBuilder0.append(this.protocol);
        stringBuilder0.append('}');
        return stringBuilder0.toString();
    }

    @Override  // okhttp3.internal.http.ExchangeCodec$Carrier
    public void trackFailure(RealCall realCall0, IOException iOException0) {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(realCall0, "call");
            if(!(iOException0 instanceof StreamResetException)) {
                if(!this.isMultiplexed$okhttp() || iOException0 instanceof ConnectionShutdownException) {
                    this.noNewExchanges = true;
                    if(this.successCount == 0) {
                        if(iOException0 != null) {
                            this.connectFailed$okhttp(realCall0.getClient(), this.getRoute(), iOException0);
                        }
                        ++this.routeFailureCount;
                    }
                }
            }
            else if(((StreamResetException)iOException0).errorCode == ErrorCode.REFUSED_STREAM) {
                int v1 = this.refusedStreamCount + 1;
                this.refusedStreamCount = v1;
                if(v1 > 1) {
                    this.noNewExchanges = true;
                    ++this.routeFailureCount;
                }
            }
            else if(((StreamResetException)iOException0).errorCode != ErrorCode.CANCEL || !realCall0.isCanceled()) {
                this.noNewExchanges = true;
                ++this.routeFailureCount;
            }
        }
    }
}

