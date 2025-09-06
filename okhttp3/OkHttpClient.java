package okhttp3;

import java.net.Proxy;
import java.net.ProxySelector;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.connection.RouteDatabase;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.proxy.NullProxySelector;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.OkHostnameVerifier;
import okhttp3.internal.ws.RealWebSocket;
import retrofit2.Platform..ExternalSyntheticApiModelOutline0;

@Metadata(d1 = {"\u0000\u00F2\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 }2\u00020\u00012\u00020\u0002:\u0002|}B\u0007\b\u0016\u00A2\u0006\u0002\u0010\u0003B\u000F\b\u0000\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u00A2\u0006\u0002\u0010\u0006J\r\u0010\u0007\u001A\u00020\bH\u0007\u00A2\u0006\u0002\bWJ\u000F\u0010\n\u001A\u0004\u0018\u00010\u000BH\u0007\u00A2\u0006\u0002\bXJ\r\u0010\r\u001A\u00020\u000EH\u0007\u00A2\u0006\u0002\bYJ\r\u0010\u0013\u001A\u00020\u0014H\u0007\u00A2\u0006\u0002\bZJ\r\u0010\u0016\u001A\u00020\u000EH\u0007\u00A2\u0006\u0002\b[J\r\u0010\u0017\u001A\u00020\u0018H\u0007\u00A2\u0006\u0002\b\\J\u0013\u0010\u001A\u001A\b\u0012\u0004\u0012\u00020\u001C0\u001BH\u0007\u00A2\u0006\u0002\b]J\r\u0010\u001E\u001A\u00020\u001FH\u0007\u00A2\u0006\u0002\b^J\r\u0010!\u001A\u00020\"H\u0007\u00A2\u0006\u0002\b_J\r\u0010$\u001A\u00020%H\u0007\u00A2\u0006\u0002\b`J\r\u0010\'\u001A\u00020(H\u0007\u00A2\u0006\u0002\baJ\r\u0010-\u001A\u00020+H\u0007\u00A2\u0006\u0002\bbJ\r\u0010.\u001A\u00020+H\u0007\u00A2\u0006\u0002\bcJ\r\u0010/\u001A\u000200H\u0007\u00A2\u0006\u0002\bdJ\u0013\u00102\u001A\b\u0012\u0004\u0012\u0002030\u001BH\u0007\u00A2\u0006\u0002\beJ\u0013\u00107\u001A\b\u0012\u0004\u0012\u0002030\u001BH\u0007\u00A2\u0006\u0002\bfJ\b\u0010g\u001A\u00020\u0005H\u0016J\u0010\u0010h\u001A\u00020i2\u0006\u0010j\u001A\u00020kH\u0016J\u0018\u0010l\u001A\u00020m2\u0006\u0010j\u001A\u00020k2\u0006\u0010n\u001A\u00020oH\u0016J\r\u00108\u001A\u00020\u000EH\u0007\u00A2\u0006\u0002\bpJ\u0013\u00109\u001A\b\u0012\u0004\u0012\u00020:0\u001BH\u0007\u00A2\u0006\u0002\bqJ\u000F\u0010;\u001A\u0004\u0018\u00010<H\u0007\u00A2\u0006\u0002\brJ\r\u0010>\u001A\u00020\bH\u0007\u00A2\u0006\u0002\bsJ\r\u0010?\u001A\u00020@H\u0007\u00A2\u0006\u0002\btJ\r\u0010B\u001A\u00020\u000EH\u0007\u00A2\u0006\u0002\buJ\r\u0010C\u001A\u00020+H\u0007\u00A2\u0006\u0002\bvJ\r\u0010H\u001A\u00020IH\u0007\u00A2\u0006\u0002\bwJ\r\u0010K\u001A\u00020LH\u0007\u00A2\u0006\u0002\bxJ\b\u0010y\u001A\u00020zH\u0002J\r\u0010S\u001A\u00020\u000EH\u0007\u00A2\u0006\u0002\b{R\u0013\u0010\u0007\u001A\u00020\b8G\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\tR\u0015\u0010\n\u001A\u0004\u0018\u00010\u000B8G\u00A2\u0006\b\n\u0000\u001A\u0004\b\n\u0010\fR\u0013\u0010\r\u001A\u00020\u000E8G\u00A2\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u000FR\u0015\u0010\u0010\u001A\u0004\u0018\u00010\u00118G\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0010\u0010\u0012R\u0013\u0010\u0013\u001A\u00020\u00148G\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\u0015R\u0013\u0010\u0016\u001A\u00020\u000E8G\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0016\u0010\u000FR\u0013\u0010\u0017\u001A\u00020\u00188G\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0017\u0010\u0019R\u0019\u0010\u001A\u001A\b\u0012\u0004\u0012\u00020\u001C0\u001B8G\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001A\u0010\u001DR\u0013\u0010\u001E\u001A\u00020\u001F8G\u00A2\u0006\b\n\u0000\u001A\u0004\b\u001E\u0010 R\u0013\u0010!\u001A\u00020\"8G\u00A2\u0006\b\n\u0000\u001A\u0004\b!\u0010#R\u0013\u0010$\u001A\u00020%8G\u00A2\u0006\b\n\u0000\u001A\u0004\b$\u0010&R\u0013\u0010\'\u001A\u00020(8G\u00A2\u0006\b\n\u0000\u001A\u0004\b\'\u0010)R\u0013\u0010*\u001A\u00020+8G\u00A2\u0006\b\n\u0000\u001A\u0004\b*\u0010,R\u0013\u0010-\u001A\u00020+8G\u00A2\u0006\b\n\u0000\u001A\u0004\b-\u0010,R\u0013\u0010.\u001A\u00020+8G\u00A2\u0006\b\n\u0000\u001A\u0004\b.\u0010,R\u0013\u0010/\u001A\u0002008G\u00A2\u0006\b\n\u0000\u001A\u0004\b/\u00101R\u0019\u00102\u001A\b\u0012\u0004\u0012\u0002030\u001B8G\u00A2\u0006\b\n\u0000\u001A\u0004\b2\u0010\u001DR\u0013\u00104\u001A\u0002058G\u00A2\u0006\b\n\u0000\u001A\u0004\b4\u00106R\u0019\u00107\u001A\b\u0012\u0004\u0012\u0002030\u001B8G\u00A2\u0006\b\n\u0000\u001A\u0004\b7\u0010\u001DR\u0013\u00108\u001A\u00020\u000E8G\u00A2\u0006\b\n\u0000\u001A\u0004\b8\u0010\u000FR\u0019\u00109\u001A\b\u0012\u0004\u0012\u00020:0\u001B8G\u00A2\u0006\b\n\u0000\u001A\u0004\b9\u0010\u001DR\u0015\u0010;\u001A\u0004\u0018\u00010<8G\u00A2\u0006\b\n\u0000\u001A\u0004\b;\u0010=R\u0013\u0010>\u001A\u00020\b8G\u00A2\u0006\b\n\u0000\u001A\u0004\b>\u0010\tR\u0013\u0010?\u001A\u00020@8G\u00A2\u0006\b\n\u0000\u001A\u0004\b?\u0010AR\u0013\u0010B\u001A\u00020\u000E8G\u00A2\u0006\b\n\u0000\u001A\u0004\bB\u0010\u000FR\u0013\u0010C\u001A\u00020+8G\u00A2\u0006\b\n\u0000\u001A\u0004\bC\u0010,R\u0014\u0010D\u001A\u00020EX\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\bF\u0010GR\u0013\u0010H\u001A\u00020I8G\u00A2\u0006\b\n\u0000\u001A\u0004\bH\u0010JR\u0011\u0010K\u001A\u00020L8G\u00A2\u0006\u0006\u001A\u0004\bK\u0010MR\u0010\u0010N\u001A\u0004\u0018\u00010LX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0014\u0010O\u001A\u00020PX\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\bQ\u0010RR\u0013\u0010S\u001A\u00020\u000E8G\u00A2\u0006\b\n\u0000\u001A\u0004\bS\u0010\u000FR\u0015\u0010T\u001A\u0004\u0018\u00010U8G\u00A2\u0006\b\n\u0000\u001A\u0004\bT\u0010V\u00A8\u0006~"}, d2 = {"Lokhttp3/OkHttpClient;", "Lokhttp3/Call$Factory;", "Lokhttp3/WebSocket$Factory;", "()V", "builder", "Lokhttp3/OkHttpClient$Builder;", "(Lokhttp3/OkHttpClient$Builder;)V", "authenticator", "Lokhttp3/Authenticator;", "()Lokhttp3/Authenticator;", "cache", "Lokhttp3/Cache;", "()Lokhttp3/Cache;", "callTimeoutMillis", "", "()I", "certificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "()Lokhttp3/internal/tls/CertificateChainCleaner;", "certificatePinner", "Lokhttp3/CertificatePinner;", "()Lokhttp3/CertificatePinner;", "connectTimeoutMillis", "connectionPool", "Lokhttp3/ConnectionPool;", "()Lokhttp3/ConnectionPool;", "connectionSpecs", "", "Lokhttp3/ConnectionSpec;", "()Ljava/util/List;", "cookieJar", "Lokhttp3/CookieJar;", "()Lokhttp3/CookieJar;", "dispatcher", "Lokhttp3/Dispatcher;", "()Lokhttp3/Dispatcher;", "dns", "Lokhttp3/Dns;", "()Lokhttp3/Dns;", "eventListenerFactory", "Lokhttp3/EventListener$Factory;", "()Lokhttp3/EventListener$Factory;", "fastFallback", "", "()Z", "followRedirects", "followSslRedirects", "hostnameVerifier", "Ljavax/net/ssl/HostnameVerifier;", "()Ljavax/net/ssl/HostnameVerifier;", "interceptors", "Lokhttp3/Interceptor;", "minWebSocketMessageToCompress", "", "()J", "networkInterceptors", "pingIntervalMillis", "protocols", "Lokhttp3/Protocol;", "proxy", "Ljava/net/Proxy;", "()Ljava/net/Proxy;", "proxyAuthenticator", "proxySelector", "Ljava/net/ProxySelector;", "()Ljava/net/ProxySelector;", "readTimeoutMillis", "retryOnConnectionFailure", "routeDatabase", "Lokhttp3/internal/connection/RouteDatabase;", "getRouteDatabase$okhttp", "()Lokhttp3/internal/connection/RouteDatabase;", "socketFactory", "Ljavax/net/SocketFactory;", "()Ljavax/net/SocketFactory;", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "()Ljavax/net/ssl/SSLSocketFactory;", "sslSocketFactoryOrNull", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "getTaskRunner$okhttp", "()Lokhttp3/internal/concurrent/TaskRunner;", "writeTimeoutMillis", "x509TrustManager", "Ljavax/net/ssl/X509TrustManager;", "()Ljavax/net/ssl/X509TrustManager;", "-deprecated_authenticator", "-deprecated_cache", "-deprecated_callTimeoutMillis", "-deprecated_certificatePinner", "-deprecated_connectTimeoutMillis", "-deprecated_connectionPool", "-deprecated_connectionSpecs", "-deprecated_cookieJar", "-deprecated_dispatcher", "-deprecated_dns", "-deprecated_eventListenerFactory", "-deprecated_followRedirects", "-deprecated_followSslRedirects", "-deprecated_hostnameVerifier", "-deprecated_interceptors", "-deprecated_networkInterceptors", "newBuilder", "newCall", "Lokhttp3/Call;", "request", "Lokhttp3/Request;", "newWebSocket", "Lokhttp3/WebSocket;", "listener", "Lokhttp3/WebSocketListener;", "-deprecated_pingIntervalMillis", "-deprecated_protocols", "-deprecated_proxy", "-deprecated_proxyAuthenticator", "-deprecated_proxySelector", "-deprecated_readTimeoutMillis", "-deprecated_retryOnConnectionFailure", "-deprecated_socketFactory", "-deprecated_sslSocketFactory", "verifyClientState", "", "-deprecated_writeTimeoutMillis", "Builder", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public class OkHttpClient implements Factory, okhttp3.WebSocket.Factory {
    @Metadata(d1 = {"\u0000\u0080\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\u000B\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000B\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000F\b\u0010\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u00A2\u0006\u0002\u0010\u0004B\u0005\u00A2\u0006\u0002\u0010\u0005J?\u0010\u00A7\u0001\u001A\u00020\u00002*\b\u0004\u0010\u00A8\u0001\u001A#\u0012\u0017\u0012\u00150\u00AA\u0001\u00A2\u0006\u000F\b\u00AB\u0001\u0012\n\b\u00AC\u0001\u0012\u0005\b\b(\u00AD\u0001\u0012\u0005\u0012\u00030\u00AE\u00010\u00A9\u0001H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0003\b\u00AF\u0001J\u0010\u0010\u00A7\u0001\u001A\u00020\u00002\u0007\u0010\u00B0\u0001\u001A\u00020`J?\u0010\u00B1\u0001\u001A\u00020\u00002*\b\u0004\u0010\u00A8\u0001\u001A#\u0012\u0017\u0012\u00150\u00AA\u0001\u00A2\u0006\u000F\b\u00AB\u0001\u0012\n\b\u00AC\u0001\u0012\u0005\b\b(\u00AD\u0001\u0012\u0005\u0012\u00030\u00AE\u00010\u00A9\u0001H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0003\b\u00B2\u0001J\u0010\u0010\u00B1\u0001\u001A\u00020\u00002\u0007\u0010\u00B0\u0001\u001A\u00020`J\u000E\u0010\u0006\u001A\u00020\u00002\u0006\u0010\u0006\u001A\u00020\u0007J\u0007\u0010\u00B3\u0001\u001A\u00020\u0003J\u0010\u0010\f\u001A\u00020\u00002\b\u0010\f\u001A\u0004\u0018\u00010\rJ\u0012\u0010\u0012\u001A\u00020\u00002\b\u0010\u00B4\u0001\u001A\u00030\u00B5\u0001H\u0007J\u0019\u0010\u0012\u001A\u00020\u00002\u0007\u0010\u00B6\u0001\u001A\u00020c2\b\u0010\u00B7\u0001\u001A\u00030\u00B8\u0001J\u000E\u0010\u001E\u001A\u00020\u00002\u0006\u0010\u001E\u001A\u00020\u001FJ\u0012\u0010$\u001A\u00020\u00002\b\u0010\u00B4\u0001\u001A\u00030\u00B5\u0001H\u0007J\u0019\u0010$\u001A\u00020\u00002\u0007\u0010\u00B6\u0001\u001A\u00020c2\b\u0010\u00B7\u0001\u001A\u00030\u00B8\u0001J\u000E\u0010\'\u001A\u00020\u00002\u0006\u0010\'\u001A\u00020(J\u0014\u0010-\u001A\u00020\u00002\f\u0010-\u001A\b\u0012\u0004\u0012\u00020/0.J\u000E\u00104\u001A\u00020\u00002\u0006\u00104\u001A\u000205J\u000E\u0010:\u001A\u00020\u00002\u0006\u0010:\u001A\u00020;J\u000E\u0010@\u001A\u00020\u00002\u0006\u0010@\u001A\u00020AJ\u0011\u0010\u00B9\u0001\u001A\u00020\u00002\b\u0010\u00B9\u0001\u001A\u00030\u00BA\u0001J\u000E\u0010F\u001A\u00020\u00002\u0006\u0010F\u001A\u00020GJ\u000E\u0010L\u001A\u00020\u00002\u0006\u0010L\u001A\u00020MJ\u000E\u0010R\u001A\u00020\u00002\u0006\u0010R\u001A\u00020MJ\u000F\u0010U\u001A\u00020\u00002\u0007\u0010\u00BB\u0001\u001A\u00020MJ\u000E\u0010X\u001A\u00020\u00002\u0006\u0010X\u001A\u00020YJ\f\u0010^\u001A\b\u0012\u0004\u0012\u00020`0_J\u000F\u0010b\u001A\u00020\u00002\u0007\u0010\u00BC\u0001\u001A\u00020cJ\f\u0010h\u001A\b\u0012\u0004\u0012\u00020`0_J\u0012\u0010j\u001A\u00020\u00002\b\u0010\u00B4\u0001\u001A\u00030\u00B5\u0001H\u0007J\u0019\u0010j\u001A\u00020\u00002\u0007\u0010\u00BD\u0001\u001A\u00020c2\b\u0010\u00B7\u0001\u001A\u00030\u00B8\u0001J\u0014\u0010m\u001A\u00020\u00002\f\u0010m\u001A\b\u0012\u0004\u0012\u00020n0.J\u0010\u0010q\u001A\u00020\u00002\b\u0010q\u001A\u0004\u0018\u00010rJ\u000E\u0010w\u001A\u00020\u00002\u0006\u0010w\u001A\u00020\u0007J\u000E\u0010z\u001A\u00020\u00002\u0006\u0010z\u001A\u00020{J\u0013\u0010\u0080\u0001\u001A\u00020\u00002\b\u0010\u00B4\u0001\u001A\u00030\u00B5\u0001H\u0007J\u001A\u0010\u0080\u0001\u001A\u00020\u00002\u0007\u0010\u00B6\u0001\u001A\u00020c2\b\u0010\u00B7\u0001\u001A\u00030\u00B8\u0001J\u0010\u0010\u0083\u0001\u001A\u00020\u00002\u0007\u0010\u0083\u0001\u001A\u00020MJ\u0011\u0010\u008C\u0001\u001A\u00020\u00002\b\u0010\u008C\u0001\u001A\u00030\u008D\u0001J\u0013\u0010\u00BE\u0001\u001A\u00020\u00002\b\u0010\u00BE\u0001\u001A\u00030\u0093\u0001H\u0007J\u001B\u0010\u00BE\u0001\u001A\u00020\u00002\b\u0010\u00BE\u0001\u001A\u00030\u0093\u00012\b\u0010\u00BF\u0001\u001A\u00030\u00A2\u0001J\u0013\u0010\u009E\u0001\u001A\u00020\u00002\b\u0010\u00B4\u0001\u001A\u00030\u00B5\u0001H\u0007J\u001A\u0010\u009E\u0001\u001A\u00020\u00002\u0007\u0010\u00B6\u0001\u001A\u00020c2\b\u0010\u00B7\u0001\u001A\u00030\u00B8\u0001R\u001A\u0010\u0006\u001A\u00020\u0007X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000BR\u001C\u0010\f\u001A\u0004\u0018\u00010\rX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u000E\u0010\u000F\"\u0004\b\u0010\u0010\u0011R\u001A\u0010\u0012\u001A\u00020\u0013X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001C\u0010\u0018\u001A\u0004\u0018\u00010\u0019X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u001A\u0010\u001B\"\u0004\b\u001C\u0010\u001DR\u001A\u0010\u001E\u001A\u00020\u001FX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001A\u0010$\u001A\u00020\u0013X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b%\u0010\u0015\"\u0004\b&\u0010\u0017R\u001A\u0010\'\u001A\u00020(X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b)\u0010*\"\u0004\b+\u0010,R \u0010-\u001A\b\u0012\u0004\u0012\u00020/0.X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b0\u00101\"\u0004\b2\u00103R\u001A\u00104\u001A\u000205X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b6\u00107\"\u0004\b8\u00109R\u001A\u0010:\u001A\u00020;X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b<\u0010=\"\u0004\b>\u0010?R\u001A\u0010@\u001A\u00020AX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\bB\u0010C\"\u0004\bD\u0010ER\u001A\u0010F\u001A\u00020GX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\bH\u0010I\"\u0004\bJ\u0010KR\u001A\u0010L\u001A\u00020MX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\bN\u0010O\"\u0004\bP\u0010QR\u001A\u0010R\u001A\u00020MX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\bS\u0010O\"\u0004\bT\u0010QR\u001A\u0010U\u001A\u00020MX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\bV\u0010O\"\u0004\bW\u0010QR\u001A\u0010X\u001A\u00020YX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\bZ\u0010[\"\u0004\b\\\u0010]R\u001A\u0010^\u001A\b\u0012\u0004\u0012\u00020`0_X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\ba\u00101R\u001A\u0010b\u001A\u00020cX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\bd\u0010e\"\u0004\bf\u0010gR\u001A\u0010h\u001A\b\u0012\u0004\u0012\u00020`0_X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\bi\u00101R\u001A\u0010j\u001A\u00020\u0013X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\bk\u0010\u0015\"\u0004\bl\u0010\u0017R \u0010m\u001A\b\u0012\u0004\u0012\u00020n0.X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\bo\u00101\"\u0004\bp\u00103R\u001C\u0010q\u001A\u0004\u0018\u00010rX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\bs\u0010t\"\u0004\bu\u0010vR\u001A\u0010w\u001A\u00020\u0007X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\bx\u0010\t\"\u0004\by\u0010\u000BR\u001C\u0010z\u001A\u0004\u0018\u00010{X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b|\u0010}\"\u0004\b~\u0010\u007FR\u001D\u0010\u0080\u0001\u001A\u00020\u0013X\u0080\u000E\u00A2\u0006\u0010\n\u0000\u001A\u0005\b\u0081\u0001\u0010\u0015\"\u0005\b\u0082\u0001\u0010\u0017R\u001D\u0010\u0083\u0001\u001A\u00020MX\u0080\u000E\u00A2\u0006\u0010\n\u0000\u001A\u0005\b\u0084\u0001\u0010O\"\u0005\b\u0085\u0001\u0010QR\"\u0010\u0086\u0001\u001A\u0005\u0018\u00010\u0087\u0001X\u0080\u000E\u00A2\u0006\u0012\n\u0000\u001A\u0006\b\u0088\u0001\u0010\u0089\u0001\"\u0006\b\u008A\u0001\u0010\u008B\u0001R \u0010\u008C\u0001\u001A\u00030\u008D\u0001X\u0080\u000E\u00A2\u0006\u0012\n\u0000\u001A\u0006\b\u008E\u0001\u0010\u008F\u0001\"\u0006\b\u0090\u0001\u0010\u0091\u0001R\"\u0010\u0092\u0001\u001A\u0005\u0018\u00010\u0093\u0001X\u0080\u000E\u00A2\u0006\u0012\n\u0000\u001A\u0006\b\u0094\u0001\u0010\u0095\u0001\"\u0006\b\u0096\u0001\u0010\u0097\u0001R\"\u0010\u0098\u0001\u001A\u0005\u0018\u00010\u0099\u0001X\u0080\u000E\u00A2\u0006\u0012\n\u0000\u001A\u0006\b\u009A\u0001\u0010\u009B\u0001\"\u0006\b\u009C\u0001\u0010\u009D\u0001R\u001D\u0010\u009E\u0001\u001A\u00020\u0013X\u0080\u000E\u00A2\u0006\u0010\n\u0000\u001A\u0005\b\u009F\u0001\u0010\u0015\"\u0005\b\u00A0\u0001\u0010\u0017R\"\u0010\u00A1\u0001\u001A\u0005\u0018\u00010\u00A2\u0001X\u0080\u000E\u00A2\u0006\u0012\n\u0000\u001A\u0006\b\u00A3\u0001\u0010\u00A4\u0001\"\u0006\b\u00A5\u0001\u0010\u00A6\u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006\u00C0\u0001"}, d2 = {"Lokhttp3/OkHttpClient$Builder;", "", "okHttpClient", "Lokhttp3/OkHttpClient;", "(Lokhttp3/OkHttpClient;)V", "()V", "authenticator", "Lokhttp3/Authenticator;", "getAuthenticator$okhttp", "()Lokhttp3/Authenticator;", "setAuthenticator$okhttp", "(Lokhttp3/Authenticator;)V", "cache", "Lokhttp3/Cache;", "getCache$okhttp", "()Lokhttp3/Cache;", "setCache$okhttp", "(Lokhttp3/Cache;)V", "callTimeout", "", "getCallTimeout$okhttp", "()I", "setCallTimeout$okhttp", "(I)V", "certificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "getCertificateChainCleaner$okhttp", "()Lokhttp3/internal/tls/CertificateChainCleaner;", "setCertificateChainCleaner$okhttp", "(Lokhttp3/internal/tls/CertificateChainCleaner;)V", "certificatePinner", "Lokhttp3/CertificatePinner;", "getCertificatePinner$okhttp", "()Lokhttp3/CertificatePinner;", "setCertificatePinner$okhttp", "(Lokhttp3/CertificatePinner;)V", "connectTimeout", "getConnectTimeout$okhttp", "setConnectTimeout$okhttp", "connectionPool", "Lokhttp3/ConnectionPool;", "getConnectionPool$okhttp", "()Lokhttp3/ConnectionPool;", "setConnectionPool$okhttp", "(Lokhttp3/ConnectionPool;)V", "connectionSpecs", "", "Lokhttp3/ConnectionSpec;", "getConnectionSpecs$okhttp", "()Ljava/util/List;", "setConnectionSpecs$okhttp", "(Ljava/util/List;)V", "cookieJar", "Lokhttp3/CookieJar;", "getCookieJar$okhttp", "()Lokhttp3/CookieJar;", "setCookieJar$okhttp", "(Lokhttp3/CookieJar;)V", "dispatcher", "Lokhttp3/Dispatcher;", "getDispatcher$okhttp", "()Lokhttp3/Dispatcher;", "setDispatcher$okhttp", "(Lokhttp3/Dispatcher;)V", "dns", "Lokhttp3/Dns;", "getDns$okhttp", "()Lokhttp3/Dns;", "setDns$okhttp", "(Lokhttp3/Dns;)V", "eventListenerFactory", "Lokhttp3/EventListener$Factory;", "getEventListenerFactory$okhttp", "()Lokhttp3/EventListener$Factory;", "setEventListenerFactory$okhttp", "(Lokhttp3/EventListener$Factory;)V", "fastFallback", "", "getFastFallback$okhttp", "()Z", "setFastFallback$okhttp", "(Z)V", "followRedirects", "getFollowRedirects$okhttp", "setFollowRedirects$okhttp", "followSslRedirects", "getFollowSslRedirects$okhttp", "setFollowSslRedirects$okhttp", "hostnameVerifier", "Ljavax/net/ssl/HostnameVerifier;", "getHostnameVerifier$okhttp", "()Ljavax/net/ssl/HostnameVerifier;", "setHostnameVerifier$okhttp", "(Ljavax/net/ssl/HostnameVerifier;)V", "interceptors", "", "Lokhttp3/Interceptor;", "getInterceptors$okhttp", "minWebSocketMessageToCompress", "", "getMinWebSocketMessageToCompress$okhttp", "()J", "setMinWebSocketMessageToCompress$okhttp", "(J)V", "networkInterceptors", "getNetworkInterceptors$okhttp", "pingInterval", "getPingInterval$okhttp", "setPingInterval$okhttp", "protocols", "Lokhttp3/Protocol;", "getProtocols$okhttp", "setProtocols$okhttp", "proxy", "Ljava/net/Proxy;", "getProxy$okhttp", "()Ljava/net/Proxy;", "setProxy$okhttp", "(Ljava/net/Proxy;)V", "proxyAuthenticator", "getProxyAuthenticator$okhttp", "setProxyAuthenticator$okhttp", "proxySelector", "Ljava/net/ProxySelector;", "getProxySelector$okhttp", "()Ljava/net/ProxySelector;", "setProxySelector$okhttp", "(Ljava/net/ProxySelector;)V", "readTimeout", "getReadTimeout$okhttp", "setReadTimeout$okhttp", "retryOnConnectionFailure", "getRetryOnConnectionFailure$okhttp", "setRetryOnConnectionFailure$okhttp", "routeDatabase", "Lokhttp3/internal/connection/RouteDatabase;", "getRouteDatabase$okhttp", "()Lokhttp3/internal/connection/RouteDatabase;", "setRouteDatabase$okhttp", "(Lokhttp3/internal/connection/RouteDatabase;)V", "socketFactory", "Ljavax/net/SocketFactory;", "getSocketFactory$okhttp", "()Ljavax/net/SocketFactory;", "setSocketFactory$okhttp", "(Ljavax/net/SocketFactory;)V", "sslSocketFactoryOrNull", "Ljavax/net/ssl/SSLSocketFactory;", "getSslSocketFactoryOrNull$okhttp", "()Ljavax/net/ssl/SSLSocketFactory;", "setSslSocketFactoryOrNull$okhttp", "(Ljavax/net/ssl/SSLSocketFactory;)V", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "getTaskRunner$okhttp", "()Lokhttp3/internal/concurrent/TaskRunner;", "setTaskRunner$okhttp", "(Lokhttp3/internal/concurrent/TaskRunner;)V", "writeTimeout", "getWriteTimeout$okhttp", "setWriteTimeout$okhttp", "x509TrustManagerOrNull", "Ljavax/net/ssl/X509TrustManager;", "getX509TrustManagerOrNull$okhttp", "()Ljavax/net/ssl/X509TrustManager;", "setX509TrustManagerOrNull$okhttp", "(Ljavax/net/ssl/X509TrustManager;)V", "addInterceptor", "block", "Lkotlin/Function1;", "Lokhttp3/Interceptor$Chain;", "Lkotlin/ParameterName;", "name", "chain", "Lokhttp3/Response;", "-addInterceptor", "interceptor", "addNetworkInterceptor", "-addNetworkInterceptor", "build", "duration", "Ljava/time/Duration;", "timeout", "unit", "Ljava/util/concurrent/TimeUnit;", "eventListener", "Lokhttp3/EventListener;", "followProtocolRedirects", "bytes", "interval", "sslSocketFactory", "trustManager", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Builder {
        private Authenticator authenticator;
        private Cache cache;
        private int callTimeout;
        private CertificateChainCleaner certificateChainCleaner;
        private CertificatePinner certificatePinner;
        private int connectTimeout;
        private ConnectionPool connectionPool;
        private List connectionSpecs;
        private CookieJar cookieJar;
        private Dispatcher dispatcher;
        private Dns dns;
        private okhttp3.EventListener.Factory eventListenerFactory;
        private boolean fastFallback;
        private boolean followRedirects;
        private boolean followSslRedirects;
        private HostnameVerifier hostnameVerifier;
        private final List interceptors;
        private long minWebSocketMessageToCompress;
        private final List networkInterceptors;
        private int pingInterval;
        private List protocols;
        private Proxy proxy;
        private Authenticator proxyAuthenticator;
        private ProxySelector proxySelector;
        private int readTimeout;
        private boolean retryOnConnectionFailure;
        private RouteDatabase routeDatabase;
        private SocketFactory socketFactory;
        private SSLSocketFactory sslSocketFactoryOrNull;
        private TaskRunner taskRunner;
        private int writeTimeout;
        private X509TrustManager x509TrustManagerOrNull;

        public final Builder -addInterceptor(Function1 function10) {
            Intrinsics.checkNotNullParameter(function10, "block");
            return this.addInterceptor(new Interceptor() {
                @Override  // okhttp3.Interceptor
                public final Response intercept(Chain interceptor$Chain0) {
                    Intrinsics.checkNotNullParameter(interceptor$Chain0, "chain");
                    return (Response)this.$block.invoke(interceptor$Chain0);
                }
            });
        }

        public final Builder -addNetworkInterceptor(Function1 function10) {
            Intrinsics.checkNotNullParameter(function10, "block");
            return this.addNetworkInterceptor(new Interceptor() {
                @Override  // okhttp3.Interceptor
                public final Response intercept(Chain interceptor$Chain0) {
                    Intrinsics.checkNotNullParameter(interceptor$Chain0, "chain");
                    return (Response)this.$block.invoke(interceptor$Chain0);
                }
            });
        }

        public Builder() {
            this.dispatcher = new Dispatcher();
            this.connectionPool = new ConnectionPool();
            this.interceptors = new ArrayList();
            this.networkInterceptors = new ArrayList();
            this.eventListenerFactory = _UtilJvmKt.asFactory(EventListener.NONE);
            this.retryOnConnectionFailure = true;
            this.fastFallback = true;
            this.authenticator = Authenticator.NONE;
            this.followRedirects = true;
            this.followSslRedirects = true;
            this.cookieJar = CookieJar.NO_COOKIES;
            this.dns = Dns.SYSTEM;
            this.proxyAuthenticator = Authenticator.NONE;
            SocketFactory socketFactory0 = SocketFactory.getDefault();
            Intrinsics.checkNotNullExpressionValue(socketFactory0, "getDefault()");
            this.socketFactory = socketFactory0;
            this.connectionSpecs = OkHttpClient.Companion.getDEFAULT_CONNECTION_SPECS$okhttp();
            this.protocols = OkHttpClient.Companion.getDEFAULT_PROTOCOLS$okhttp();
            this.hostnameVerifier = OkHostnameVerifier.INSTANCE;
            this.certificatePinner = CertificatePinner.DEFAULT;
            this.connectTimeout = 10000;
            this.readTimeout = 10000;
            this.writeTimeout = 10000;
            this.minWebSocketMessageToCompress = 0x400L;
        }

        public Builder(OkHttpClient okHttpClient0) {
            Intrinsics.checkNotNullParameter(okHttpClient0, "okHttpClient");
            this();
            this.dispatcher = okHttpClient0.dispatcher();
            this.connectionPool = okHttpClient0.connectionPool();
            CollectionsKt.addAll(this.interceptors, okHttpClient0.interceptors());
            CollectionsKt.addAll(this.networkInterceptors, okHttpClient0.networkInterceptors());
            this.eventListenerFactory = okHttpClient0.eventListenerFactory();
            this.retryOnConnectionFailure = okHttpClient0.retryOnConnectionFailure();
            this.fastFallback = okHttpClient0.fastFallback();
            this.authenticator = okHttpClient0.authenticator();
            this.followRedirects = okHttpClient0.followRedirects();
            this.followSslRedirects = okHttpClient0.followSslRedirects();
            this.cookieJar = okHttpClient0.cookieJar();
            this.cache = okHttpClient0.cache();
            this.dns = okHttpClient0.dns();
            this.proxy = okHttpClient0.proxy();
            this.proxySelector = okHttpClient0.proxySelector();
            this.proxyAuthenticator = okHttpClient0.proxyAuthenticator();
            this.socketFactory = okHttpClient0.socketFactory();
            this.sslSocketFactoryOrNull = okHttpClient0.sslSocketFactoryOrNull;
            this.x509TrustManagerOrNull = okHttpClient0.x509TrustManager();
            this.connectionSpecs = okHttpClient0.connectionSpecs();
            this.protocols = okHttpClient0.protocols();
            this.hostnameVerifier = okHttpClient0.hostnameVerifier();
            this.certificatePinner = okHttpClient0.certificatePinner();
            this.certificateChainCleaner = okHttpClient0.certificateChainCleaner();
            this.callTimeout = okHttpClient0.callTimeoutMillis();
            this.connectTimeout = okHttpClient0.connectTimeoutMillis();
            this.readTimeout = okHttpClient0.readTimeoutMillis();
            this.writeTimeout = okHttpClient0.writeTimeoutMillis();
            this.pingInterval = okHttpClient0.pingIntervalMillis();
            this.minWebSocketMessageToCompress = okHttpClient0.minWebSocketMessageToCompress();
            this.routeDatabase = okHttpClient0.getRouteDatabase$okhttp();
            this.taskRunner = okHttpClient0.getTaskRunner$okhttp();
        }

        public final Builder addInterceptor(Interceptor interceptor0) {
            Intrinsics.checkNotNullParameter(interceptor0, "interceptor");
            this.interceptors.add(interceptor0);
            return this;
        }

        public final Builder addNetworkInterceptor(Interceptor interceptor0) {
            Intrinsics.checkNotNullParameter(interceptor0, "interceptor");
            this.networkInterceptors.add(interceptor0);
            return this;
        }

        public final Builder authenticator(Authenticator authenticator0) {
            Intrinsics.checkNotNullParameter(authenticator0, "authenticator");
            this.authenticator = authenticator0;
            return this;
        }

        public final OkHttpClient build() {
            return new OkHttpClient(this);
        }

        public final Builder cache(Cache cache0) {
            this.cache = cache0;
            return this;
        }

        public final Builder callTimeout(long v, TimeUnit timeUnit0) {
            Intrinsics.checkNotNullParameter(timeUnit0, "unit");
            this.callTimeout = _UtilJvmKt.checkDuration("timeout", v, timeUnit0);
            return this;
        }

        public final Builder callTimeout(Duration duration0) {
            Intrinsics.checkNotNullParameter(duration0, "duration");
            this.callTimeout(Platform..ExternalSyntheticApiModelOutline0.m(duration0), TimeUnit.MILLISECONDS);
            return this;
        }

        public final Builder certificatePinner(CertificatePinner certificatePinner0) {
            Intrinsics.checkNotNullParameter(certificatePinner0, "certificatePinner");
            if(!Intrinsics.areEqual(certificatePinner0, this.certificatePinner)) {
                this.routeDatabase = null;
            }
            this.certificatePinner = certificatePinner0;
            return this;
        }

        public final Builder connectTimeout(long v, TimeUnit timeUnit0) {
            Intrinsics.checkNotNullParameter(timeUnit0, "unit");
            this.connectTimeout = _UtilJvmKt.checkDuration("timeout", v, timeUnit0);
            return this;
        }

        public final Builder connectTimeout(Duration duration0) {
            Intrinsics.checkNotNullParameter(duration0, "duration");
            this.connectTimeout(Platform..ExternalSyntheticApiModelOutline0.m(duration0), TimeUnit.MILLISECONDS);
            return this;
        }

        public final Builder connectionPool(ConnectionPool connectionPool0) {
            Intrinsics.checkNotNullParameter(connectionPool0, "connectionPool");
            this.connectionPool = connectionPool0;
            return this;
        }

        public final Builder connectionSpecs(List list0) {
            Intrinsics.checkNotNullParameter(list0, "connectionSpecs");
            if(!Intrinsics.areEqual(list0, this.connectionSpecs)) {
                this.routeDatabase = null;
            }
            this.connectionSpecs = _UtilJvmKt.toImmutableList(list0);
            return this;
        }

        public final Builder cookieJar(CookieJar cookieJar0) {
            Intrinsics.checkNotNullParameter(cookieJar0, "cookieJar");
            this.cookieJar = cookieJar0;
            return this;
        }

        public final Builder dispatcher(Dispatcher dispatcher0) {
            Intrinsics.checkNotNullParameter(dispatcher0, "dispatcher");
            this.dispatcher = dispatcher0;
            return this;
        }

        public final Builder dns(Dns dns0) {
            Intrinsics.checkNotNullParameter(dns0, "dns");
            if(!Intrinsics.areEqual(dns0, this.dns)) {
                this.routeDatabase = null;
            }
            this.dns = dns0;
            return this;
        }

        public final Builder eventListener(EventListener eventListener0) {
            Intrinsics.checkNotNullParameter(eventListener0, "eventListener");
            this.eventListenerFactory = _UtilJvmKt.asFactory(eventListener0);
            return this;
        }

        public final Builder eventListenerFactory(okhttp3.EventListener.Factory eventListener$Factory0) {
            Intrinsics.checkNotNullParameter(eventListener$Factory0, "eventListenerFactory");
            this.eventListenerFactory = eventListener$Factory0;
            return this;
        }

        public final Builder fastFallback(boolean z) {
            this.fastFallback = z;
            return this;
        }

        public final Builder followRedirects(boolean z) {
            this.followRedirects = z;
            return this;
        }

        public final Builder followSslRedirects(boolean z) {
            this.followSslRedirects = z;
            return this;
        }

        public final Authenticator getAuthenticator$okhttp() {
            return this.authenticator;
        }

        public final Cache getCache$okhttp() {
            return this.cache;
        }

        public final int getCallTimeout$okhttp() {
            return this.callTimeout;
        }

        public final CertificateChainCleaner getCertificateChainCleaner$okhttp() {
            return this.certificateChainCleaner;
        }

        public final CertificatePinner getCertificatePinner$okhttp() {
            return this.certificatePinner;
        }

        public final int getConnectTimeout$okhttp() {
            return this.connectTimeout;
        }

        public final ConnectionPool getConnectionPool$okhttp() {
            return this.connectionPool;
        }

        public final List getConnectionSpecs$okhttp() {
            return this.connectionSpecs;
        }

        public final CookieJar getCookieJar$okhttp() {
            return this.cookieJar;
        }

        public final Dispatcher getDispatcher$okhttp() {
            return this.dispatcher;
        }

        public final Dns getDns$okhttp() {
            return this.dns;
        }

        public final okhttp3.EventListener.Factory getEventListenerFactory$okhttp() {
            return this.eventListenerFactory;
        }

        public final boolean getFastFallback$okhttp() {
            return this.fastFallback;
        }

        public final boolean getFollowRedirects$okhttp() {
            return this.followRedirects;
        }

        public final boolean getFollowSslRedirects$okhttp() {
            return this.followSslRedirects;
        }

        public final HostnameVerifier getHostnameVerifier$okhttp() {
            return this.hostnameVerifier;
        }

        public final List getInterceptors$okhttp() {
            return this.interceptors;
        }

        public final long getMinWebSocketMessageToCompress$okhttp() {
            return this.minWebSocketMessageToCompress;
        }

        public final List getNetworkInterceptors$okhttp() {
            return this.networkInterceptors;
        }

        public final int getPingInterval$okhttp() {
            return this.pingInterval;
        }

        public final List getProtocols$okhttp() {
            return this.protocols;
        }

        public final Proxy getProxy$okhttp() {
            return this.proxy;
        }

        public final Authenticator getProxyAuthenticator$okhttp() {
            return this.proxyAuthenticator;
        }

        public final ProxySelector getProxySelector$okhttp() {
            return this.proxySelector;
        }

        public final int getReadTimeout$okhttp() {
            return this.readTimeout;
        }

        public final boolean getRetryOnConnectionFailure$okhttp() {
            return this.retryOnConnectionFailure;
        }

        public final RouteDatabase getRouteDatabase$okhttp() {
            return this.routeDatabase;
        }

        public final SocketFactory getSocketFactory$okhttp() {
            return this.socketFactory;
        }

        public final SSLSocketFactory getSslSocketFactoryOrNull$okhttp() {
            return this.sslSocketFactoryOrNull;
        }

        public final TaskRunner getTaskRunner$okhttp() {
            return this.taskRunner;
        }

        public final int getWriteTimeout$okhttp() {
            return this.writeTimeout;
        }

        public final X509TrustManager getX509TrustManagerOrNull$okhttp() {
            return this.x509TrustManagerOrNull;
        }

        public final Builder hostnameVerifier(HostnameVerifier hostnameVerifier0) {
            Intrinsics.checkNotNullParameter(hostnameVerifier0, "hostnameVerifier");
            if(!Intrinsics.areEqual(hostnameVerifier0, this.hostnameVerifier)) {
                this.routeDatabase = null;
            }
            this.hostnameVerifier = hostnameVerifier0;
            return this;
        }

        public final List interceptors() {
            return this.interceptors;
        }

        public final Builder minWebSocketMessageToCompress(long v) {
            if(v < 0L) {
                throw new IllegalArgumentException(("minWebSocketMessageToCompress must be positive: " + v).toString());
            }
            this.minWebSocketMessageToCompress = v;
            return this;
        }

        public final List networkInterceptors() {
            return this.networkInterceptors;
        }

        public final Builder pingInterval(long v, TimeUnit timeUnit0) {
            Intrinsics.checkNotNullParameter(timeUnit0, "unit");
            this.pingInterval = _UtilJvmKt.checkDuration("interval", v, timeUnit0);
            return this;
        }

        public final Builder pingInterval(Duration duration0) {
            Intrinsics.checkNotNullParameter(duration0, "duration");
            this.pingInterval(Platform..ExternalSyntheticApiModelOutline0.m(duration0), TimeUnit.MILLISECONDS);
            return this;
        }

        public final Builder protocols(List list0) {
            Intrinsics.checkNotNullParameter(list0, "protocols");
            List list1 = CollectionsKt.toMutableList(list0);
            if(!list1.contains(Protocol.H2_PRIOR_KNOWLEDGE) && !list1.contains(Protocol.HTTP_1_1)) {
                throw new IllegalArgumentException(("protocols must contain h2_prior_knowledge or http/1.1: " + list1).toString());
            }
            if(list1.contains(Protocol.H2_PRIOR_KNOWLEDGE) && list1.size() > 1) {
                throw new IllegalArgumentException(("protocols containing h2_prior_knowledge cannot use other protocols: " + list1).toString());
            }
            if(list1.contains(Protocol.HTTP_1_0)) {
                throw new IllegalArgumentException(("protocols must not contain http/1.0: " + list1).toString());
            }
            Intrinsics.checkNotNull(list1, "null cannot be cast to non-null type kotlin.collections.List<okhttp3.Protocol?>");
            if(list1.contains(null)) {
                throw new IllegalArgumentException("protocols must not contain null");
            }
            list1.remove(Protocol.SPDY_3);
            if(!Intrinsics.areEqual(list1, this.protocols)) {
                this.routeDatabase = null;
            }
            List list2 = Collections.unmodifiableList(list1);
            Intrinsics.checkNotNullExpressionValue(list2, "unmodifiableList(protocolsCopy)");
            this.protocols = list2;
            return this;
        }

        public final Builder proxy(Proxy proxy0) {
            if(!Intrinsics.areEqual(proxy0, this.proxy)) {
                this.routeDatabase = null;
            }
            this.proxy = proxy0;
            return this;
        }

        public final Builder proxyAuthenticator(Authenticator authenticator0) {
            Intrinsics.checkNotNullParameter(authenticator0, "proxyAuthenticator");
            if(!Intrinsics.areEqual(authenticator0, this.proxyAuthenticator)) {
                this.routeDatabase = null;
            }
            this.proxyAuthenticator = authenticator0;
            return this;
        }

        public final Builder proxySelector(ProxySelector proxySelector0) {
            Intrinsics.checkNotNullParameter(proxySelector0, "proxySelector");
            if(!Intrinsics.areEqual(proxySelector0, this.proxySelector)) {
                this.routeDatabase = null;
            }
            this.proxySelector = proxySelector0;
            return this;
        }

        public final Builder readTimeout(long v, TimeUnit timeUnit0) {
            Intrinsics.checkNotNullParameter(timeUnit0, "unit");
            this.readTimeout = _UtilJvmKt.checkDuration("timeout", v, timeUnit0);
            return this;
        }

        public final Builder readTimeout(Duration duration0) {
            Intrinsics.checkNotNullParameter(duration0, "duration");
            this.readTimeout(Platform..ExternalSyntheticApiModelOutline0.m(duration0), TimeUnit.MILLISECONDS);
            return this;
        }

        public final Builder retryOnConnectionFailure(boolean z) {
            this.retryOnConnectionFailure = z;
            return this;
        }

        public final void setAuthenticator$okhttp(Authenticator authenticator0) {
            Intrinsics.checkNotNullParameter(authenticator0, "<set-?>");
            this.authenticator = authenticator0;
        }

        public final void setCache$okhttp(Cache cache0) {
            this.cache = cache0;
        }

        public final void setCallTimeout$okhttp(int v) {
            this.callTimeout = v;
        }

        public final void setCertificateChainCleaner$okhttp(CertificateChainCleaner certificateChainCleaner0) {
            this.certificateChainCleaner = certificateChainCleaner0;
        }

        public final void setCertificatePinner$okhttp(CertificatePinner certificatePinner0) {
            Intrinsics.checkNotNullParameter(certificatePinner0, "<set-?>");
            this.certificatePinner = certificatePinner0;
        }

        public final void setConnectTimeout$okhttp(int v) {
            this.connectTimeout = v;
        }

        public final void setConnectionPool$okhttp(ConnectionPool connectionPool0) {
            Intrinsics.checkNotNullParameter(connectionPool0, "<set-?>");
            this.connectionPool = connectionPool0;
        }

        public final void setConnectionSpecs$okhttp(List list0) {
            Intrinsics.checkNotNullParameter(list0, "<set-?>");
            this.connectionSpecs = list0;
        }

        public final void setCookieJar$okhttp(CookieJar cookieJar0) {
            Intrinsics.checkNotNullParameter(cookieJar0, "<set-?>");
            this.cookieJar = cookieJar0;
        }

        public final void setDispatcher$okhttp(Dispatcher dispatcher0) {
            Intrinsics.checkNotNullParameter(dispatcher0, "<set-?>");
            this.dispatcher = dispatcher0;
        }

        public final void setDns$okhttp(Dns dns0) {
            Intrinsics.checkNotNullParameter(dns0, "<set-?>");
            this.dns = dns0;
        }

        public final void setEventListenerFactory$okhttp(okhttp3.EventListener.Factory eventListener$Factory0) {
            Intrinsics.checkNotNullParameter(eventListener$Factory0, "<set-?>");
            this.eventListenerFactory = eventListener$Factory0;
        }

        public final void setFastFallback$okhttp(boolean z) {
            this.fastFallback = z;
        }

        public final void setFollowRedirects$okhttp(boolean z) {
            this.followRedirects = z;
        }

        public final void setFollowSslRedirects$okhttp(boolean z) {
            this.followSslRedirects = z;
        }

        public final void setHostnameVerifier$okhttp(HostnameVerifier hostnameVerifier0) {
            Intrinsics.checkNotNullParameter(hostnameVerifier0, "<set-?>");
            this.hostnameVerifier = hostnameVerifier0;
        }

        public final void setMinWebSocketMessageToCompress$okhttp(long v) {
            this.minWebSocketMessageToCompress = v;
        }

        public final void setPingInterval$okhttp(int v) {
            this.pingInterval = v;
        }

        public final void setProtocols$okhttp(List list0) {
            Intrinsics.checkNotNullParameter(list0, "<set-?>");
            this.protocols = list0;
        }

        public final void setProxy$okhttp(Proxy proxy0) {
            this.proxy = proxy0;
        }

        public final void setProxyAuthenticator$okhttp(Authenticator authenticator0) {
            Intrinsics.checkNotNullParameter(authenticator0, "<set-?>");
            this.proxyAuthenticator = authenticator0;
        }

        public final void setProxySelector$okhttp(ProxySelector proxySelector0) {
            this.proxySelector = proxySelector0;
        }

        public final void setReadTimeout$okhttp(int v) {
            this.readTimeout = v;
        }

        public final void setRetryOnConnectionFailure$okhttp(boolean z) {
            this.retryOnConnectionFailure = z;
        }

        public final void setRouteDatabase$okhttp(RouteDatabase routeDatabase0) {
            this.routeDatabase = routeDatabase0;
        }

        public final void setSocketFactory$okhttp(SocketFactory socketFactory0) {
            Intrinsics.checkNotNullParameter(socketFactory0, "<set-?>");
            this.socketFactory = socketFactory0;
        }

        public final void setSslSocketFactoryOrNull$okhttp(SSLSocketFactory sSLSocketFactory0) {
            this.sslSocketFactoryOrNull = sSLSocketFactory0;
        }

        public final void setTaskRunner$okhttp(TaskRunner taskRunner0) {
            this.taskRunner = taskRunner0;
        }

        public final void setWriteTimeout$okhttp(int v) {
            this.writeTimeout = v;
        }

        public final void setX509TrustManagerOrNull$okhttp(X509TrustManager x509TrustManager0) {
            this.x509TrustManagerOrNull = x509TrustManager0;
        }

        public final Builder socketFactory(SocketFactory socketFactory0) {
            Intrinsics.checkNotNullParameter(socketFactory0, "socketFactory");
            if(socketFactory0 instanceof SSLSocketFactory) {
                throw new IllegalArgumentException("socketFactory instanceof SSLSocketFactory");
            }
            if(!Intrinsics.areEqual(socketFactory0, this.socketFactory)) {
                this.routeDatabase = null;
            }
            this.socketFactory = socketFactory0;
            return this;
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Use the sslSocketFactory overload that accepts a X509TrustManager.")
        public final Builder sslSocketFactory(SSLSocketFactory sSLSocketFactory0) {
            Intrinsics.checkNotNullParameter(sSLSocketFactory0, "sslSocketFactory");
            if(!Intrinsics.areEqual(sSLSocketFactory0, this.sslSocketFactoryOrNull)) {
                this.routeDatabase = null;
            }
            this.sslSocketFactoryOrNull = sSLSocketFactory0;
            X509TrustManager x509TrustManager0 = Platform.Companion.get().trustManager(sSLSocketFactory0);
            if(x509TrustManager0 == null) {
                throw new IllegalStateException("Unable to extract the trust manager on " + Platform.Companion.get() + ", sslSocketFactory is " + sSLSocketFactory0.getClass());
            }
            this.x509TrustManagerOrNull = x509TrustManager0;
            Platform platform0 = Platform.Companion.get();
            X509TrustManager x509TrustManager1 = this.x509TrustManagerOrNull;
            Intrinsics.checkNotNull(x509TrustManager1);
            this.certificateChainCleaner = platform0.buildCertificateChainCleaner(x509TrustManager1);
            return this;
        }

        public final Builder sslSocketFactory(SSLSocketFactory sSLSocketFactory0, X509TrustManager x509TrustManager0) {
            Intrinsics.checkNotNullParameter(sSLSocketFactory0, "sslSocketFactory");
            Intrinsics.checkNotNullParameter(x509TrustManager0, "trustManager");
            if(!Intrinsics.areEqual(sSLSocketFactory0, this.sslSocketFactoryOrNull) || !Intrinsics.areEqual(x509TrustManager0, this.x509TrustManagerOrNull)) {
                this.routeDatabase = null;
            }
            this.sslSocketFactoryOrNull = sSLSocketFactory0;
            this.certificateChainCleaner = CertificateChainCleaner.Companion.get(x509TrustManager0);
            this.x509TrustManagerOrNull = x509TrustManager0;
            return this;
        }

        public final Builder writeTimeout(long v, TimeUnit timeUnit0) {
            Intrinsics.checkNotNullParameter(timeUnit0, "unit");
            this.writeTimeout = _UtilJvmKt.checkDuration("timeout", v, timeUnit0);
            return this;
        }

        public final Builder writeTimeout(Duration duration0) {
            Intrinsics.checkNotNullParameter(duration0, "duration");
            this.writeTimeout(Platform..ExternalSyntheticApiModelOutline0.m(duration0), TimeUnit.MILLISECONDS);
            return this;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001A\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0006\u0010\u0007R\u001A\u0010\b\u001A\b\u0012\u0004\u0012\u00020\t0\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u0007¨\u0006\u000B"}, d2 = {"Lokhttp3/OkHttpClient$Companion;", "", "()V", "DEFAULT_CONNECTION_SPECS", "", "Lokhttp3/ConnectionSpec;", "getDEFAULT_CONNECTION_SPECS$okhttp", "()Ljava/util/List;", "DEFAULT_PROTOCOLS", "Lokhttp3/Protocol;", "getDEFAULT_PROTOCOLS$okhttp", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final List getDEFAULT_CONNECTION_SPECS$okhttp() {
            return OkHttpClient.DEFAULT_CONNECTION_SPECS;
        }

        public final List getDEFAULT_PROTOCOLS$okhttp() {
            return OkHttpClient.DEFAULT_PROTOCOLS;
        }
    }

    public static final Companion Companion;
    private static final List DEFAULT_CONNECTION_SPECS;
    private static final List DEFAULT_PROTOCOLS;
    private final Authenticator authenticator;
    private final Cache cache;
    private final int callTimeoutMillis;
    private final CertificateChainCleaner certificateChainCleaner;
    private final CertificatePinner certificatePinner;
    private final int connectTimeoutMillis;
    private final ConnectionPool connectionPool;
    private final List connectionSpecs;
    private final CookieJar cookieJar;
    private final Dispatcher dispatcher;
    private final Dns dns;
    private final okhttp3.EventListener.Factory eventListenerFactory;
    private final boolean fastFallback;
    private final boolean followRedirects;
    private final boolean followSslRedirects;
    private final HostnameVerifier hostnameVerifier;
    private final List interceptors;
    private final long minWebSocketMessageToCompress;
    private final List networkInterceptors;
    private final int pingIntervalMillis;
    private final List protocols;
    private final Proxy proxy;
    private final Authenticator proxyAuthenticator;
    private final ProxySelector proxySelector;
    private final int readTimeoutMillis;
    private final boolean retryOnConnectionFailure;
    private final RouteDatabase routeDatabase;
    private final SocketFactory socketFactory;
    private final SSLSocketFactory sslSocketFactoryOrNull;
    private final TaskRunner taskRunner;
    private final int writeTimeoutMillis;
    private final X509TrustManager x509TrustManager;

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "authenticator", imports = {}))
    public final Authenticator -deprecated_authenticator() {
        return this.authenticator;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "cache", imports = {}))
    public final Cache -deprecated_cache() {
        return this.cache;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "callTimeoutMillis", imports = {}))
    public final int -deprecated_callTimeoutMillis() {
        return this.callTimeoutMillis;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "certificatePinner", imports = {}))
    public final CertificatePinner -deprecated_certificatePinner() {
        return this.certificatePinner;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "connectTimeoutMillis", imports = {}))
    public final int -deprecated_connectTimeoutMillis() {
        return this.connectTimeoutMillis;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "connectionPool", imports = {}))
    public final ConnectionPool -deprecated_connectionPool() {
        return this.connectionPool;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "connectionSpecs", imports = {}))
    public final List -deprecated_connectionSpecs() {
        return this.connectionSpecs;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "cookieJar", imports = {}))
    public final CookieJar -deprecated_cookieJar() {
        return this.cookieJar;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "dispatcher", imports = {}))
    public final Dispatcher -deprecated_dispatcher() {
        return this.dispatcher;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "dns", imports = {}))
    public final Dns -deprecated_dns() {
        return this.dns;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "eventListenerFactory", imports = {}))
    public final okhttp3.EventListener.Factory -deprecated_eventListenerFactory() {
        return this.eventListenerFactory;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "followRedirects", imports = {}))
    public final boolean -deprecated_followRedirects() {
        return this.followRedirects;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "followSslRedirects", imports = {}))
    public final boolean -deprecated_followSslRedirects() {
        return this.followSslRedirects;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "hostnameVerifier", imports = {}))
    public final HostnameVerifier -deprecated_hostnameVerifier() {
        return this.hostnameVerifier;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "interceptors", imports = {}))
    public final List -deprecated_interceptors() {
        return this.interceptors;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "networkInterceptors", imports = {}))
    public final List -deprecated_networkInterceptors() {
        return this.networkInterceptors;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "pingIntervalMillis", imports = {}))
    public final int -deprecated_pingIntervalMillis() {
        return this.pingIntervalMillis;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "protocols", imports = {}))
    public final List -deprecated_protocols() {
        return this.protocols;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "proxy", imports = {}))
    public final Proxy -deprecated_proxy() {
        return this.proxy;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "proxyAuthenticator", imports = {}))
    public final Authenticator -deprecated_proxyAuthenticator() {
        return this.proxyAuthenticator;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "proxySelector", imports = {}))
    public final ProxySelector -deprecated_proxySelector() {
        return this.proxySelector;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "readTimeoutMillis", imports = {}))
    public final int -deprecated_readTimeoutMillis() {
        return this.readTimeoutMillis;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "retryOnConnectionFailure", imports = {}))
    public final boolean -deprecated_retryOnConnectionFailure() {
        return this.retryOnConnectionFailure;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "socketFactory", imports = {}))
    public final SocketFactory -deprecated_socketFactory() {
        return this.socketFactory;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "sslSocketFactory", imports = {}))
    public final SSLSocketFactory -deprecated_sslSocketFactory() {
        return this.sslSocketFactory();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "writeTimeoutMillis", imports = {}))
    public final int -deprecated_writeTimeoutMillis() {
        return this.writeTimeoutMillis;
    }

    static {
        OkHttpClient.Companion = new Companion(null);
        OkHttpClient.DEFAULT_PROTOCOLS = _UtilJvmKt.immutableListOf(new Protocol[]{Protocol.HTTP_2, Protocol.HTTP_1_1});
        OkHttpClient.DEFAULT_CONNECTION_SPECS = _UtilJvmKt.immutableListOf(new ConnectionSpec[]{ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT});
    }

    public OkHttpClient() {
        this(new Builder());
    }

    public OkHttpClient(Builder okHttpClient$Builder0) {
        Intrinsics.checkNotNullParameter(okHttpClient$Builder0, "builder");
        ProxySelector proxySelector0;
        super();
        this.dispatcher = okHttpClient$Builder0.getDispatcher$okhttp();
        this.connectionPool = okHttpClient$Builder0.getConnectionPool$okhttp();
        this.interceptors = _UtilJvmKt.toImmutableList(okHttpClient$Builder0.getInterceptors$okhttp());
        this.networkInterceptors = _UtilJvmKt.toImmutableList(okHttpClient$Builder0.getNetworkInterceptors$okhttp());
        this.eventListenerFactory = okHttpClient$Builder0.getEventListenerFactory$okhttp();
        this.retryOnConnectionFailure = okHttpClient$Builder0.getRetryOnConnectionFailure$okhttp();
        this.fastFallback = okHttpClient$Builder0.getFastFallback$okhttp();
        this.authenticator = okHttpClient$Builder0.getAuthenticator$okhttp();
        this.followRedirects = okHttpClient$Builder0.getFollowRedirects$okhttp();
        this.followSslRedirects = okHttpClient$Builder0.getFollowSslRedirects$okhttp();
        this.cookieJar = okHttpClient$Builder0.getCookieJar$okhttp();
        this.cache = okHttpClient$Builder0.getCache$okhttp();
        this.dns = okHttpClient$Builder0.getDns$okhttp();
        this.proxy = okHttpClient$Builder0.getProxy$okhttp();
        if(okHttpClient$Builder0.getProxy$okhttp() == null) {
            proxySelector0 = okHttpClient$Builder0.getProxySelector$okhttp() == null ? ProxySelector.getDefault() : okHttpClient$Builder0.getProxySelector$okhttp();
            if(proxySelector0 == null) {
                proxySelector0 = NullProxySelector.INSTANCE;
            }
        }
        else {
            proxySelector0 = NullProxySelector.INSTANCE;
        }
        this.proxySelector = proxySelector0;
        this.proxyAuthenticator = okHttpClient$Builder0.getProxyAuthenticator$okhttp();
        this.socketFactory = okHttpClient$Builder0.getSocketFactory$okhttp();
        List list0 = okHttpClient$Builder0.getConnectionSpecs$okhttp();
        this.connectionSpecs = list0;
        this.protocols = okHttpClient$Builder0.getProtocols$okhttp();
        this.hostnameVerifier = okHttpClient$Builder0.getHostnameVerifier$okhttp();
        this.callTimeoutMillis = okHttpClient$Builder0.getCallTimeout$okhttp();
        this.connectTimeoutMillis = okHttpClient$Builder0.getConnectTimeout$okhttp();
        this.readTimeoutMillis = okHttpClient$Builder0.getReadTimeout$okhttp();
        this.writeTimeoutMillis = okHttpClient$Builder0.getWriteTimeout$okhttp();
        this.pingIntervalMillis = okHttpClient$Builder0.getPingInterval$okhttp();
        this.minWebSocketMessageToCompress = okHttpClient$Builder0.getMinWebSocketMessageToCompress$okhttp();
        this.routeDatabase = okHttpClient$Builder0.getRouteDatabase$okhttp() == null ? new RouteDatabase() : okHttpClient$Builder0.getRouteDatabase$okhttp();
        this.taskRunner = okHttpClient$Builder0.getTaskRunner$okhttp() == null ? TaskRunner.INSTANCE : okHttpClient$Builder0.getTaskRunner$okhttp();
        if(list0 instanceof Collection && list0.isEmpty()) {
        label_64:
            this.sslSocketFactoryOrNull = null;
            this.certificateChainCleaner = null;
            this.x509TrustManager = null;
            this.certificatePinner = CertificatePinner.DEFAULT;
        }
        else {
            Iterator iterator0 = list0.iterator();
            while(true) {
                if(!iterator0.hasNext()) {
                    goto label_64;
                }
                Object object0 = iterator0.next();
                if(((ConnectionSpec)object0).isTls()) {
                    if(okHttpClient$Builder0.getSslSocketFactoryOrNull$okhttp() == null) {
                        X509TrustManager x509TrustManager1 = Platform.Companion.get().platformTrustManager();
                        this.x509TrustManager = x509TrustManager1;
                        Platform platform0 = Platform.Companion.get();
                        Intrinsics.checkNotNull(x509TrustManager1);
                        this.sslSocketFactoryOrNull = platform0.newSslSocketFactory(x509TrustManager1);
                        Intrinsics.checkNotNull(x509TrustManager1);
                        CertificateChainCleaner certificateChainCleaner1 = CertificateChainCleaner.Companion.get(x509TrustManager1);
                        this.certificateChainCleaner = certificateChainCleaner1;
                        Intrinsics.checkNotNull(certificateChainCleaner1);
                        this.certificatePinner = okHttpClient$Builder0.getCertificatePinner$okhttp().withCertificateChainCleaner$okhttp(certificateChainCleaner1);
                    }
                    else {
                        this.sslSocketFactoryOrNull = okHttpClient$Builder0.getSslSocketFactoryOrNull$okhttp();
                        CertificateChainCleaner certificateChainCleaner0 = okHttpClient$Builder0.getCertificateChainCleaner$okhttp();
                        Intrinsics.checkNotNull(certificateChainCleaner0);
                        this.certificateChainCleaner = certificateChainCleaner0;
                        X509TrustManager x509TrustManager0 = okHttpClient$Builder0.getX509TrustManagerOrNull$okhttp();
                        Intrinsics.checkNotNull(x509TrustManager0);
                        this.x509TrustManager = x509TrustManager0;
                        Intrinsics.checkNotNull(certificateChainCleaner0);
                        this.certificatePinner = okHttpClient$Builder0.getCertificatePinner$okhttp().withCertificateChainCleaner$okhttp(certificateChainCleaner0);
                    }
                    break;
                }
            }
        }
        this.verifyClientState();
    }

    public final Authenticator authenticator() {
        return this.authenticator;
    }

    public final Cache cache() {
        return this.cache;
    }

    public final int callTimeoutMillis() {
        return this.callTimeoutMillis;
    }

    public final CertificateChainCleaner certificateChainCleaner() {
        return this.certificateChainCleaner;
    }

    public final CertificatePinner certificatePinner() {
        return this.certificatePinner;
    }

    public final int connectTimeoutMillis() {
        return this.connectTimeoutMillis;
    }

    public final ConnectionPool connectionPool() {
        return this.connectionPool;
    }

    public final List connectionSpecs() {
        return this.connectionSpecs;
    }

    public final CookieJar cookieJar() {
        return this.cookieJar;
    }

    public final Dispatcher dispatcher() {
        return this.dispatcher;
    }

    public final Dns dns() {
        return this.dns;
    }

    public final okhttp3.EventListener.Factory eventListenerFactory() {
        return this.eventListenerFactory;
    }

    public final boolean fastFallback() {
        return this.fastFallback;
    }

    public final boolean followRedirects() {
        return this.followRedirects;
    }

    public final boolean followSslRedirects() {
        return this.followSslRedirects;
    }

    public final RouteDatabase getRouteDatabase$okhttp() {
        return this.routeDatabase;
    }

    public final TaskRunner getTaskRunner$okhttp() {
        return this.taskRunner;
    }

    public final HostnameVerifier hostnameVerifier() {
        return this.hostnameVerifier;
    }

    public final List interceptors() {
        return this.interceptors;
    }

    public final long minWebSocketMessageToCompress() {
        return this.minWebSocketMessageToCompress;
    }

    public final List networkInterceptors() {
        return this.networkInterceptors;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    @Override  // okhttp3.Call$Factory
    public Call newCall(Request request0) {
        Intrinsics.checkNotNullParameter(request0, "request");
        return new RealCall(this, request0, false);
    }

    @Override  // okhttp3.WebSocket$Factory
    public WebSocket newWebSocket(Request request0, WebSocketListener webSocketListener0) {
        Intrinsics.checkNotNullParameter(request0, "request");
        Intrinsics.checkNotNullParameter(webSocketListener0, "listener");
        Random random0 = new Random();
        RealWebSocket realWebSocket0 = new RealWebSocket(this.taskRunner, request0, webSocketListener0, random0, ((long)this.pingIntervalMillis), null, this.minWebSocketMessageToCompress);
        realWebSocket0.connect(this);
        return realWebSocket0;
    }

    public final int pingIntervalMillis() {
        return this.pingIntervalMillis;
    }

    public final List protocols() {
        return this.protocols;
    }

    public final Proxy proxy() {
        return this.proxy;
    }

    public final Authenticator proxyAuthenticator() {
        return this.proxyAuthenticator;
    }

    public final ProxySelector proxySelector() {
        return this.proxySelector;
    }

    public final int readTimeoutMillis() {
        return this.readTimeoutMillis;
    }

    public final boolean retryOnConnectionFailure() {
        return this.retryOnConnectionFailure;
    }

    public final SocketFactory socketFactory() {
        return this.socketFactory;
    }

    public final SSLSocketFactory sslSocketFactory() {
        SSLSocketFactory sSLSocketFactory0 = this.sslSocketFactoryOrNull;
        if(sSLSocketFactory0 == null) {
            throw new IllegalStateException("CLEARTEXT-only client");
        }
        return sSLSocketFactory0;
    }

    private final void verifyClientState() {
        Intrinsics.checkNotNull(this.interceptors, "null cannot be cast to non-null type kotlin.collections.List<okhttp3.Interceptor?>");
        if(this.interceptors.contains(null)) {
            throw new IllegalStateException(("Null interceptor: " + this.interceptors).toString());
        }
        Intrinsics.checkNotNull(this.networkInterceptors, "null cannot be cast to non-null type kotlin.collections.List<okhttp3.Interceptor?>");
        if(this.networkInterceptors.contains(null)) {
            throw new IllegalStateException(("Null network interceptor: " + this.networkInterceptors).toString());
        }
        Iterable iterable0 = this.connectionSpecs;
        if(!(iterable0 instanceof Collection) || !((Collection)iterable0).isEmpty()) {
            for(Object object0: iterable0) {
                if(!((ConnectionSpec)object0).isTls()) {
                    continue;
                }
                if(this.sslSocketFactoryOrNull == null) {
                    throw new IllegalStateException("sslSocketFactory == null");
                }
                if(this.certificateChainCleaner == null) {
                    throw new IllegalStateException("certificateChainCleaner == null");
                }
                if(this.x509TrustManager == null) {
                    throw new IllegalStateException("x509TrustManager == null");
                }
                return;
            }
        }
        if(this.sslSocketFactoryOrNull != null || this.certificateChainCleaner != null || this.x509TrustManager != null || !Intrinsics.areEqual(this.certificatePinner, CertificatePinner.DEFAULT)) {
            throw new IllegalStateException("Check failed.");
        }
    }

    public final int writeTimeoutMillis() {
        return this.writeTimeoutMillis;
    }

    public final X509TrustManager x509TrustManager() {
        return this.x509TrustManager;
    }
}

