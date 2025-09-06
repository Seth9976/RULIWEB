package okhttp3.internal.connection;

import java.io.IOException;
import java.net.ConnectException;
import java.net.ProtocolException;
import java.net.Proxy.Type;
import java.net.Socket;
import java.net.UnknownServiceException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Address;
import okhttp3.CertificatePinner;
import okhttp3.ConnectionSpec;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response.Builder;
import okhttp3.Response;
import okhttp3.Route;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.http.ExchangeCodec.Carrier;
import okhttp3.internal.http1.Http1ExchangeCodec;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.OkHostnameVerifier;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

@Metadata(d1 = {"\u0000\u009A\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000F\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 M2\u00020\u00012\u00020\u0002:\u0001MBW\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\u0007\u001A\u00020\b\u0012\u0006\u0010\t\u001A\u00020\n\u0012\u000E\u0010\u000B\u001A\n\u0012\u0004\u0012\u00020\n\u0018\u00010\f\u0012\u0006\u0010\r\u001A\u00020\u000E\u0012\b\u0010\u000F\u001A\u0004\u0018\u00010\u0010\u0012\u0006\u0010\u0011\u001A\u00020\u000E\u0012\u0006\u0010\u0012\u001A\u00020\u0013\u00A2\u0006\u0002\u0010\u0014J\b\u00102\u001A\u000203H\u0016J\u0006\u00104\u001A\u000203J\b\u00105\u001A\u000203H\u0002J\b\u00106\u001A\u000207H\u0016J\u0018\u00108\u001A\u0002032\u0006\u00109\u001A\u00020:2\u0006\u0010;\u001A\u00020<H\u0002J\b\u0010=\u001A\u000207H\u0016J\r\u0010>\u001A\u000207H\u0000\u00A2\u0006\u0002\b?J2\u0010@\u001A\u00020\u00002\b\b\u0002\u0010\r\u001A\u00020\u000E2\n\b\u0002\u0010\u000F\u001A\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0011\u001A\u00020\u000E2\b\b\u0002\u0010\u0012\u001A\u00020\u0013H\u0002J\n\u0010A\u001A\u0004\u0018\u00010\u0010H\u0002J\b\u0010B\u001A\u00020\u0017H\u0016J%\u0010C\u001A\u0004\u0018\u00010\u00002\f\u0010D\u001A\b\u0012\u0004\u0012\u00020<0\f2\u0006\u00109\u001A\u00020:H\u0000\u00A2\u0006\u0002\bEJ\b\u0010F\u001A\u000203H\u0016J#\u0010G\u001A\u00020\u00002\f\u0010D\u001A\b\u0012\u0004\u0012\u00020<0\f2\u0006\u00109\u001A\u00020:H\u0000\u00A2\u0006\u0002\bHJ\b\u0010I\u001A\u00020\u0001H\u0016J\u001A\u0010J\u001A\u0002032\u0006\u0010\u0005\u001A\u00020\u00062\b\u0010K\u001A\u0004\u0018\u00010LH\u0016R\u000E\u0010\r\u001A\u00020\u000EX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0015\u001A\u00020\u0013X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001A\u0004\u0018\u00010\u0017X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001A\u00020\u000EX\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0018\u0010\u0019R\u000E\u0010\u001A\u001A\u00020\u001BX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u001C\u001A\u0004\u0018\u00010\u001DX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u001E\u001A\u00020\u00138VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u001E\u0010\u001FR\u0014\u0010\u0012\u001A\u00020\u0013X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b \u0010\u001FR\u0010\u0010!\u001A\u0004\u0018\u00010\"X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010#\u001A\u0004\u0018\u00010$X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010\t\u001A\u00020\nX\u0096\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b%\u0010&R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001C\u0010\u000B\u001A\n\u0012\u0004\u0012\u00020\n\u0018\u00010\fX\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\'\u0010(R\u0010\u0010)\u001A\u0004\u0018\u00010*X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u001C\u0010+\u001A\u0004\u0018\u00010$X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b,\u0010-\"\u0004\b.\u0010/R\u0010\u00100\u001A\u0004\u0018\u000101X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u000F\u001A\u0004\u0018\u00010\u0010X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006N"}, d2 = {"Lokhttp3/internal/connection/ConnectPlan;", "Lokhttp3/internal/connection/RoutePlanner$Plan;", "Lokhttp3/internal/http/ExchangeCodec$Carrier;", "client", "Lokhttp3/OkHttpClient;", "call", "Lokhttp3/internal/connection/RealCall;", "routePlanner", "Lokhttp3/internal/connection/RealRoutePlanner;", "route", "Lokhttp3/Route;", "routes", "", "attempt", "", "tunnelRequest", "Lokhttp3/Request;", "connectionSpecIndex", "isTlsFallback", "", "(Lokhttp3/OkHttpClient;Lokhttp3/internal/connection/RealCall;Lokhttp3/internal/connection/RealRoutePlanner;Lokhttp3/Route;Ljava/util/List;ILokhttp3/Request;IZ)V", "canceled", "connection", "Lokhttp3/internal/connection/RealConnection;", "getConnectionSpecIndex$okhttp", "()I", "eventListener", "Lokhttp3/EventListener;", "handshake", "Lokhttp3/Handshake;", "isReady", "()Z", "isTlsFallback$okhttp", "protocol", "Lokhttp3/Protocol;", "rawSocket", "Ljava/net/Socket;", "getRoute", "()Lokhttp3/Route;", "getRoutes$okhttp", "()Ljava/util/List;", "sink", "Lokio/BufferedSink;", "socket", "getSocket$okhttp", "()Ljava/net/Socket;", "setSocket$okhttp", "(Ljava/net/Socket;)V", "source", "Lokio/BufferedSource;", "cancel", "", "closeQuietly", "connectSocket", "connectTcp", "Lokhttp3/internal/connection/RoutePlanner$ConnectResult;", "connectTls", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "connectionSpec", "Lokhttp3/ConnectionSpec;", "connectTlsEtc", "connectTunnel", "connectTunnel$okhttp", "copy", "createTunnel", "handleSuccess", "nextConnectionSpec", "connectionSpecs", "nextConnectionSpec$okhttp", "noNewExchanges", "planWithCurrentOrInitialConnectionSpec", "planWithCurrentOrInitialConnectionSpec$okhttp", "retry", "trackFailure", "e", "Ljava/io/IOException;", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class ConnectPlan implements Plan, Carrier {
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lokhttp3/internal/connection/ConnectPlan$Companion;", "", "()V", "MAX_TUNNEL_ATTEMPTS", "", "NPE_THROW_WITH_NULL", "", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    @Metadata(k = 3, mv = {1, 7, 1}, xi = 0x30)
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[Proxy.Type.values().length];
            arr_v[Proxy.Type.DIRECT.ordinal()] = 1;
            arr_v[Proxy.Type.HTTP.ordinal()] = 2;
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    public static final Companion Companion = null;
    private static final int MAX_TUNNEL_ATTEMPTS = 21;
    private static final String NPE_THROW_WITH_NULL = "throw with null exception";
    private final int attempt;
    private final RealCall call;
    private volatile boolean canceled;
    private final OkHttpClient client;
    private RealConnection connection;
    private final int connectionSpecIndex;
    private final EventListener eventListener;
    private Handshake handshake;
    private final boolean isTlsFallback;
    private Protocol protocol;
    private Socket rawSocket;
    private final Route route;
    private final RealRoutePlanner routePlanner;
    private final List routes;
    private BufferedSink sink;
    private Socket socket;
    private BufferedSource source;
    private final Request tunnelRequest;

    static {
        ConnectPlan.Companion = new Companion(null);
    }

    public ConnectPlan(OkHttpClient okHttpClient0, RealCall realCall0, RealRoutePlanner realRoutePlanner0, Route route0, List list0, int v, Request request0, int v1, boolean z) {
        Intrinsics.checkNotNullParameter(okHttpClient0, "client");
        Intrinsics.checkNotNullParameter(realCall0, "call");
        Intrinsics.checkNotNullParameter(realRoutePlanner0, "routePlanner");
        Intrinsics.checkNotNullParameter(route0, "route");
        super();
        this.client = okHttpClient0;
        this.call = realCall0;
        this.routePlanner = realRoutePlanner0;
        this.route = route0;
        this.routes = list0;
        this.attempt = v;
        this.tunnelRequest = request0;
        this.connectionSpecIndex = v1;
        this.isTlsFallback = z;
        this.eventListener = realCall0.getEventListener$okhttp();
    }

    @Override  // okhttp3.internal.connection.RoutePlanner$Plan, okhttp3.internal.http.ExchangeCodec$Carrier
    public void cancel() {
        this.canceled = true;
        Socket socket0 = this.rawSocket;
        if(socket0 != null) {
            _UtilJvmKt.closeQuietly(socket0);
        }
    }

    public final void closeQuietly() {
        Socket socket0 = this.socket;
        if(socket0 != null) {
            _UtilJvmKt.closeQuietly(socket0);
        }
    }

    private final void connectSocket() throws IOException {
        Socket socket0;
        Proxy.Type proxy$Type0 = this.getRoute().proxy().type();
        int v = proxy$Type0 == null ? -1 : WhenMappings.$EnumSwitchMapping$0[proxy$Type0.ordinal()];
        if(v == 1 || v == 2) {
            socket0 = this.getRoute().address().socketFactory().createSocket();
            Intrinsics.checkNotNull(socket0);
        }
        else {
            socket0 = new Socket(this.getRoute().proxy());
        }
        this.rawSocket = socket0;
        if(this.canceled) {
            throw new IOException("canceled");
        }
        socket0.setSoTimeout(this.client.readTimeoutMillis());
        try {
            Platform.Companion.get().connectSocket(socket0, this.getRoute().socketAddress(), this.client.connectTimeoutMillis());
        }
        catch(ConnectException connectException0) {
            ConnectException connectException1 = new ConnectException("Failed to connect to " + this.getRoute().socketAddress());
            connectException1.initCause(connectException0);
            throw connectException1;
        }
        try {
            this.source = Okio.buffer(Okio.source(socket0));
            this.sink = Okio.buffer(Okio.sink(socket0));
        }
        catch(NullPointerException nullPointerException0) {
            if(Intrinsics.areEqual(nullPointerException0.getMessage(), "throw with null exception")) {
                throw new IOException(nullPointerException0);
            }
        }
    }

    @Override  // okhttp3.internal.connection.RoutePlanner$Plan
    public ConnectResult connectTcp() {
        ConnectResult routePlanner$ConnectResult1;
        ConnectResult routePlanner$ConnectResult0;
        if(this.rawSocket != null) {
            throw new IllegalStateException("TCP already connected");
        }
        this.call.getPlansToCancel$okhttp().add(this);
        boolean z = false;
        try {
            try {
                this.eventListener.connectStart(this.call, this.getRoute().socketAddress(), this.getRoute().proxy());
                this.connectSocket();
                z = true;
                routePlanner$ConnectResult0 = new ConnectResult(this, null, null, 6, null);
                goto label_24;
            }
            catch(IOException iOException0) {
            }
            this.eventListener.connectFailed(this.call, this.getRoute().socketAddress(), this.getRoute().proxy(), null, iOException0);
            routePlanner$ConnectResult1 = new ConnectResult(this, null, iOException0, 2, null);
        }
        catch(Throwable throwable0) {
            goto label_18;
        }
        this.call.getPlansToCancel$okhttp().remove(this);
        if(!z) {
            Socket socket0 = this.rawSocket;
            if(socket0 != null) {
                _UtilJvmKt.closeQuietly(socket0);
            }
        }
        return routePlanner$ConnectResult1;
    label_18:
        this.call.getPlansToCancel$okhttp().remove(this);
        if(!z) {
            Socket socket1 = this.rawSocket;
            if(socket1 != null) {
                _UtilJvmKt.closeQuietly(socket1);
            }
        }
        throw throwable0;
    label_24:
        this.call.getPlansToCancel$okhttp().remove(this);
        return routePlanner$ConnectResult0;
    }

    private final void connectTls(SSLSocket sSLSocket0, ConnectionSpec connectionSpec0) throws IOException {
        Address address0 = this.getRoute().address();
        try {
            if(connectionSpec0.supportsTlsExtensions()) {
                Platform.Companion.get().configureTlsExtensions(sSLSocket0, address0.url().host(), address0.protocols());
            }
            sSLSocket0.startHandshake();
            SSLSession sSLSession0 = sSLSocket0.getSession();
            Intrinsics.checkNotNullExpressionValue(sSLSession0, "sslSocketSession");
            Handshake handshake0 = Handshake.Companion.get(sSLSession0);
            HostnameVerifier hostnameVerifier0 = address0.hostnameVerifier();
            Intrinsics.checkNotNull(hostnameVerifier0);
            String s = null;
            if(!hostnameVerifier0.verify(address0.url().host(), sSLSession0)) {
                List list0 = handshake0.peerCertificates();
                if(list0.isEmpty()) {
                    throw new SSLPeerUnverifiedException("Hostname " + address0.url().host() + " not verified (no certificates)");
                }
                Object object0 = list0.get(0);
                Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type java.security.cert.X509Certificate");
                throw new SSLPeerUnverifiedException(StringsKt.trimMargin$default(("\n            |Hostname " + address0.url().host() + " not verified:\n            |    certificate: " + CertificatePinner.Companion.pin(((X509Certificate)object0)) + "\n            |    DN: " + ((X509Certificate)object0).getSubjectDN().getName() + "\n            |    subjectAltNames: " + OkHostnameVerifier.INSTANCE.allSubjectAltNames(((X509Certificate)object0)) + "\n            "), null, 1, null));
            }
            CertificatePinner certificatePinner0 = address0.certificatePinner();
            Intrinsics.checkNotNull(certificatePinner0);
            Handshake handshake1 = new Handshake(handshake0.tlsVersion(), handshake0.cipherSuite(), handshake0.localCertificates(), new Function0(handshake0, address0) {
                final Address $address;
                final CertificatePinner $certificatePinner;
                final Handshake $unverifiedHandshake;

                {
                    this.$certificatePinner = certificatePinner0;
                    this.$unverifiedHandshake = handshake0;
                    this.$address = address0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final List invoke() {
                    CertificateChainCleaner certificateChainCleaner0 = this.$certificatePinner.getCertificateChainCleaner$okhttp();
                    Intrinsics.checkNotNull(certificateChainCleaner0);
                    return certificateChainCleaner0.clean(this.$unverifiedHandshake.peerCertificates(), this.$address.url().host());
                }
            });
            this.handshake = handshake1;
            certificatePinner0.check$okhttp(address0.url().host(), new Function0() {
                final Handshake $handshake;

                {
                    this.$handshake = handshake0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final List invoke() {
                    Iterable iterable0 = this.$handshake.peerCertificates();
                    ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
                    for(Object object0: iterable0) {
                        Intrinsics.checkNotNull(((Certificate)object0), "null cannot be cast to non-null type java.security.cert.X509Certificate");
                        arrayList0.add(((X509Certificate)(((Certificate)object0))));
                    }
                    return arrayList0;
                }
            });
            if(connectionSpec0.supportsTlsExtensions()) {
                s = Platform.Companion.get().getSelectedProtocol(sSLSocket0);
            }
            this.socket = sSLSocket0;
            this.source = Okio.buffer(Okio.source(sSLSocket0));
            this.sink = Okio.buffer(Okio.sink(sSLSocket0));
            this.protocol = s == null ? Protocol.HTTP_1_1 : Protocol.Companion.get(s);
        }
        catch(Throwable throwable0) {
            Platform.Companion.get().afterHandshake(sSLSocket0);
            _UtilJvmKt.closeQuietly(sSLSocket0);
            throw throwable0;
        }
        Platform.Companion.get().afterHandshake(sSLSocket0);
    }

    @Override  // okhttp3.internal.connection.RoutePlanner$Plan
    public ConnectResult connectTlsEtc() {
        ConnectPlan connectPlan3;
        IOException iOException2;
        ConnectResult routePlanner$ConnectResult2;
        ConnectResult routePlanner$ConnectResult1;
        ConnectPlan connectPlan2;
        ConnectionSpec connectionSpec0;
        ConnectPlan connectPlan1;
        SSLSocket sSLSocket0;
        ConnectResult routePlanner$ConnectResult0;
        if(this.rawSocket == null) {
            throw new IllegalStateException("TCP not connected");
        }
        if(this.isReady()) {
            throw new IllegalStateException("already connected");
        }
        List list0 = this.getRoute().address().connectionSpecs();
        this.call.getPlansToCancel$okhttp().add(this);
        ConnectPlan connectPlan0 = null;
        boolean z = false;
        try {
            if(this.tunnelRequest == null) {
                goto label_10;
            }
            routePlanner$ConnectResult0 = this.connectTunnel$okhttp();
            if(routePlanner$ConnectResult0.getNextPlan() == null) {
                goto label_9;
            }
            goto label_84;
        }
        catch(IOException iOException0) {
            iOException2 = iOException0;
            connectPlan3 = null;
            goto label_55;
        }
        catch(Throwable throwable0) {
            goto label_73;
        }
    label_9:
        if(routePlanner$ConnectResult0.getThrowable() == null) {
            try {
                try {
                label_10:
                    if(this.getRoute().address().sslSocketFactory() == null) {
                        this.socket = this.rawSocket;
                        this.protocol = this.getRoute().address().protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE) ? Protocol.H2_PRIOR_KNOWLEDGE : Protocol.HTTP_1_1;
                        connectPlan2 = null;
                    }
                    else {
                        BufferedSource bufferedSource0 = this.source;
                        if(bufferedSource0 != null) {
                            Buffer buffer0 = bufferedSource0.getBuffer();
                            if(buffer0 != null && !buffer0.exhausted()) {
                                throw new IOException("TLS tunnel buffered too many bytes!");
                            }
                        }
                        BufferedSink bufferedSink0 = this.sink;
                        if(bufferedSink0 != null) {
                            Buffer buffer1 = bufferedSink0.getBuffer();
                            if(buffer1 != null && !buffer1.exhausted()) {
                                throw new IOException("TLS tunnel buffered too many bytes!");
                            }
                        }
                        this.eventListener.secureConnectStart(this.call);
                        Socket socket0 = this.getRoute().address().sslSocketFactory().createSocket(this.rawSocket, this.getRoute().address().url().host(), this.getRoute().address().url().port(), true);
                        Intrinsics.checkNotNull(socket0, "null cannot be cast to non-null type javax.net.ssl.SSLSocket");
                        sSLSocket0 = (SSLSocket)socket0;
                        connectPlan1 = this.planWithCurrentOrInitialConnectionSpec$okhttp(list0, sSLSocket0);
                        connectionSpec0 = (ConnectionSpec)list0.get(connectPlan1.connectionSpecIndex);
                        connectPlan2 = connectPlan1.nextConnectionSpec$okhttp(list0, sSLSocket0);
                        goto label_27;
                    }
                    goto label_39;
                }
                catch(IOException iOException0) {
                    iOException2 = iOException0;
                    connectPlan3 = null;
                    goto label_55;
                }
                try {
                label_27:
                    connectionSpec0.apply$okhttp(sSLSocket0, connectPlan1.isTlsFallback);
                    this.connectTls(sSLSocket0, connectionSpec0);
                    this.eventListener.secureConnectEnd(this.call, this.handshake);
                    goto label_39;
                }
                catch(IOException iOException1) {
                    iOException2 = iOException1;
                    connectPlan3 = connectPlan2;
                    goto label_55;
                }
                try {
                    this.socket = this.rawSocket;
                    this.protocol = this.getRoute().address().protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE) ? Protocol.H2_PRIOR_KNOWLEDGE : Protocol.HTTP_1_1;
                    connectPlan2 = null;
                    goto label_39;
                }
                catch(IOException iOException0) {
                }
            }
            catch(Throwable throwable0) {
                goto label_73;
            }
            iOException2 = iOException0;
            connectPlan3 = null;
            goto label_55;
            try {
            label_39:
                RealConnectionPool realConnectionPool0 = this.client.connectionPool().getDelegate$okhttp();
                RealConnection realConnection0 = new RealConnection(this.client.getTaskRunner$okhttp(), realConnectionPool0, this.getRoute(), this.rawSocket, this.socket, this.handshake, this.protocol, this.source, this.sink, this.client.pingIntervalMillis());
                this.connection = realConnection0;
                realConnection0.start();
                this.eventListener.connectEnd(this.call, this.getRoute().socketAddress(), this.getRoute().proxy(), this.protocol);
                routePlanner$ConnectResult1 = new ConnectResult(this, null, null, 6, null);
                goto label_82;
            }
            catch(IOException iOException1) {
                iOException2 = iOException1;
                connectPlan3 = connectPlan2;
                goto label_55;
                try {
                    routePlanner$ConnectResult1 = new ConnectResult(this, null, null, 6, null);
                    goto label_82;
                }
                catch(IOException iOException3) {
                }
                catch(Throwable throwable0) {
                    goto label_72;
                }
                iOException2 = iOException3;
                connectPlan3 = connectPlan2;
                z = true;
                try {
                label_55:
                    this.eventListener.connectFailed(this.call, this.getRoute().socketAddress(), this.getRoute().proxy(), null, iOException2);
                    if(this.client.retryOnConnectionFailure() && TlsHandshakeKt.retryTlsHandshake(iOException2)) {
                        connectPlan0 = connectPlan3;
                    }
                    routePlanner$ConnectResult2 = new ConnectResult(this, connectPlan0, iOException2);
                    goto label_62;
                }
                catch(Throwable throwable0) {
                }
                goto label_73;
            }
            catch(Throwable throwable0) {
                goto label_73;
            }
        label_62:
            this.call.getPlansToCancel$okhttp().remove(this);
            if(!z) {
                Socket socket1 = this.socket;
                if(socket1 != null) {
                    _UtilJvmKt.closeQuietly(socket1);
                }
                Socket socket2 = this.rawSocket;
                if(socket2 != null) {
                    _UtilJvmKt.closeQuietly(socket2);
                }
            }
            return routePlanner$ConnectResult2;
        label_72:
            z = true;
        label_73:
            this.call.getPlansToCancel$okhttp().remove(this);
            if(!z) {
                Socket socket3 = this.socket;
                if(socket3 != null) {
                    _UtilJvmKt.closeQuietly(socket3);
                }
                Socket socket4 = this.rawSocket;
                if(socket4 != null) {
                    _UtilJvmKt.closeQuietly(socket4);
                }
            }
            throw throwable0;
        label_82:
            this.call.getPlansToCancel$okhttp().remove(this);
            return routePlanner$ConnectResult1;
        }
    label_84:
        this.call.getPlansToCancel$okhttp().remove(this);
        Socket socket5 = this.socket;
        if(socket5 != null) {
            _UtilJvmKt.closeQuietly(socket5);
        }
        Socket socket6 = this.rawSocket;
        if(socket6 != null) {
            _UtilJvmKt.closeQuietly(socket6);
        }
        return routePlanner$ConnectResult0;
    }

    public final ConnectResult connectTunnel$okhttp() throws IOException {
        Request request0 = this.createTunnel();
        if(request0 == null) {
            return new ConnectResult(this, null, null, 6, null);
        }
        Socket socket0 = this.rawSocket;
        if(socket0 != null) {
            _UtilJvmKt.closeQuietly(socket0);
        }
        int v = this.attempt + 1;
        if(v < 21) {
            this.eventListener.connectEnd(this.call, this.getRoute().socketAddress(), this.getRoute().proxy(), null);
            return new ConnectResult(this, ConnectPlan.copy$default(this, v, request0, 0, false, 12, null), null, 4, null);
        }
        ProtocolException protocolException0 = new ProtocolException("Too many tunnel connections attempted: 21");
        this.eventListener.connectFailed(this.call, this.getRoute().socketAddress(), this.getRoute().proxy(), null, protocolException0);
        return new ConnectResult(this, null, protocolException0, 2, null);
    }

    private final ConnectPlan copy(int v, Request request0, int v1, boolean z) {
        return new ConnectPlan(this.client, this.call, this.routePlanner, this.getRoute(), this.routes, v, request0, v1, z);
    }

    static ConnectPlan copy$default(ConnectPlan connectPlan0, int v, Request request0, int v1, boolean z, int v2, Object object0) {
        if((v2 & 1) != 0) {
            v = connectPlan0.attempt;
        }
        if((v2 & 2) != 0) {
            request0 = connectPlan0.tunnelRequest;
        }
        if((v2 & 4) != 0) {
            v1 = connectPlan0.connectionSpecIndex;
        }
        if((v2 & 8) != 0) {
            z = connectPlan0.isTlsFallback;
        }
        return connectPlan0.copy(v, request0, v1, z);
    }

    private final Request createTunnel() throws IOException {
        Request request1;
        Request request0 = this.tunnelRequest;
        Intrinsics.checkNotNull(request0);
        String s = "CONNECT " + _UtilJvmKt.toHostHeader(this.getRoute().address().url(), true) + " HTTP/1.1";
    alab1:
        while(true) {
            BufferedSource bufferedSource0 = this.source;
            Intrinsics.checkNotNull(bufferedSource0);
            BufferedSink bufferedSink0 = this.sink;
            Intrinsics.checkNotNull(bufferedSink0);
            Http1ExchangeCodec http1ExchangeCodec0 = new Http1ExchangeCodec(null, this, bufferedSource0, bufferedSink0);
            bufferedSource0.timeout().timeout(((long)this.client.readTimeoutMillis()), TimeUnit.MILLISECONDS);
            bufferedSink0.timeout().timeout(((long)this.client.writeTimeoutMillis()), TimeUnit.MILLISECONDS);
            http1ExchangeCodec0.writeRequest(request0.headers(), s);
            http1ExchangeCodec0.finishRequest();
            Builder response$Builder0 = http1ExchangeCodec0.readResponseHeaders(false);
            Intrinsics.checkNotNull(response$Builder0);
            Response response0 = response$Builder0.request(request0).build();
            http1ExchangeCodec0.skipConnectBody(response0);
            switch(response0.code()) {
                case 200: {
                    return null;
                }
                case 407: {
                    request1 = this.getRoute().address().proxyAuthenticator().authenticate(this.getRoute(), response0);
                    if(request1 == null) {
                        break alab1;
                    }
                    if(StringsKt.equals("close", Response.header$default(response0, "Connection", null, 2, null), true)) {
                        return request1;
                    }
                    break;
                }
                default: {
                    throw new IOException("Unexpected response code for CONNECT: " + response0.code());
                }
            }
            request0 = request1;
        }
        throw new IOException("Failed to authenticate with proxy");
    }

    public final int getConnectionSpecIndex$okhttp() {
        return this.connectionSpecIndex;
    }

    @Override  // okhttp3.internal.http.ExchangeCodec$Carrier
    public Route getRoute() {
        return this.route;
    }

    public final List getRoutes$okhttp() {
        return this.routes;
    }

    public final Socket getSocket$okhttp() {
        return this.socket;
    }

    @Override  // okhttp3.internal.connection.RoutePlanner$Plan
    public RealConnection handleSuccess() {
        this.call.getClient().getRouteDatabase$okhttp().connected(this.getRoute());
        ReusePlan reusePlan0 = this.routePlanner.planReusePooledConnection$okhttp(this, this.routes);
        if(reusePlan0 != null) {
            return reusePlan0.getConnection();
        }
        RealConnection realConnection0 = this.connection;
        Intrinsics.checkNotNull(realConnection0);
        synchronized(realConnection0) {
            this.client.connectionPool().getDelegate$okhttp().put(realConnection0);
            this.call.acquireConnectionNoEvents(realConnection0);
        }
        this.eventListener.connectionAcquired(this.call, realConnection0);
        return realConnection0;
    }

    @Override  // okhttp3.internal.connection.RoutePlanner$Plan
    public boolean isReady() {
        return this.protocol != null;
    }

    public final boolean isTlsFallback$okhttp() {
        return this.isTlsFallback;
    }

    public final ConnectPlan nextConnectionSpec$okhttp(List list0, SSLSocket sSLSocket0) {
        Intrinsics.checkNotNullParameter(list0, "connectionSpecs");
        Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
        int v = list0.size();
        for(int v1 = this.connectionSpecIndex + 1; v1 < v; ++v1) {
            if(((ConnectionSpec)list0.get(v1)).isCompatible(sSLSocket0)) {
                return this.connectionSpecIndex == -1 ? ConnectPlan.copy$default(this, 0, null, v1, false, 3, null) : ConnectPlan.copy$default(this, 0, null, v1, true, 3, null);
            }
        }
        return null;
    }

    @Override  // okhttp3.internal.http.ExchangeCodec$Carrier
    public void noNewExchanges() {
    }

    public final ConnectPlan planWithCurrentOrInitialConnectionSpec$okhttp(List list0, SSLSocket sSLSocket0) throws IOException {
        Intrinsics.checkNotNullParameter(list0, "connectionSpecs");
        Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
        if(this.connectionSpecIndex != -1) {
            return this;
        }
        ConnectPlan connectPlan0 = this.nextConnectionSpec$okhttp(list0, sSLSocket0);
        if(connectPlan0 != null) {
            return connectPlan0;
        }
        String[] arr_s = sSLSocket0.getEnabledProtocols();
        Intrinsics.checkNotNull(arr_s);
        String s = Arrays.toString(arr_s);
        Intrinsics.checkNotNullExpressionValue(s, "toString(this)");
        throw new UnknownServiceException("Unable to find acceptable protocols. isFallback=" + this.isTlsFallback + ", modes=" + list0 + ", supported protocols=" + s);
    }

    @Override  // okhttp3.internal.connection.RoutePlanner$Plan
    public Plan retry() {
        return new ConnectPlan(this.client, this.call, this.routePlanner, this.getRoute(), this.routes, this.attempt, this.tunnelRequest, this.connectionSpecIndex, this.isTlsFallback);
    }

    public final void setSocket$okhttp(Socket socket0) {
        this.socket = socket0;
    }

    @Override  // okhttp3.internal.http.ExchangeCodec$Carrier
    public void trackFailure(RealCall realCall0, IOException iOException0) {
        Intrinsics.checkNotNullParameter(realCall0, "call");
    }
}

