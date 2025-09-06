package okhttp3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000 ?2\u00020\u0001:\u0002?@B\u0005\u00A2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0016J\u0018\u0010\t\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\n\u001A\u00020\bH\u0016J\u0010\u0010\u000B\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J\u0010\u0010\f\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J\u0018\u0010\r\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u000E\u001A\u00020\u000FH\u0016J\u0010\u0010\u0010\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J\u0010\u0010\u0011\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J*\u0010\u0012\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u00162\b\u0010\u0017\u001A\u0004\u0018\u00010\u0018H\u0016J2\u0010\u0019\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u00162\b\u0010\u0017\u001A\u0004\u0018\u00010\u00182\u0006\u0010\u000E\u001A\u00020\u000FH\u0016J \u0010\u001A\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u0016H\u0016J\u0018\u0010\u001B\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u001C\u001A\u00020\u001DH\u0016J\u0018\u0010\u001E\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u001C\u001A\u00020\u001DH\u0016J+\u0010\u001F\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010 \u001A\u00020!2\u0011\u0010\"\u001A\r\u0012\t\u0012\u00070$\u00A2\u0006\u0002\b%0#H\u0016J\u0018\u0010&\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010 \u001A\u00020!H\u0016J+\u0010\'\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010(\u001A\u00020)2\u0011\u0010*\u001A\r\u0012\t\u0012\u00070\u0016\u00A2\u0006\u0002\b%0#H\u0016J\u0018\u0010+\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010(\u001A\u00020)H\u0016J\u0018\u0010,\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010-\u001A\u00020.H\u0016J\u0010\u0010/\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J\u0018\u00100\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u000E\u001A\u00020\u000FH\u0016J\u0018\u00101\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u00102\u001A\u000203H\u0016J\u0010\u00104\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J\u0018\u00105\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010-\u001A\u00020.H\u0016J\u0010\u00106\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J\u0018\u00107\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u000E\u001A\u00020\u000FH\u0016J\u0018\u00108\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\n\u001A\u00020\bH\u0016J\u0010\u00109\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J\u0018\u0010:\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\n\u001A\u00020\bH\u0016J\u001A\u0010;\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\b\u0010<\u001A\u0004\u0018\u00010=H\u0016J\u0010\u0010>\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016\u00A8\u0006A"}, d2 = {"Lokhttp3/EventListener;", "", "()V", "cacheConditionalHit", "", "call", "Lokhttp3/Call;", "cachedResponse", "Lokhttp3/Response;", "cacheHit", "response", "cacheMiss", "callEnd", "callFailed", "ioe", "Ljava/io/IOException;", "callStart", "canceled", "connectEnd", "inetSocketAddress", "Ljava/net/InetSocketAddress;", "proxy", "Ljava/net/Proxy;", "protocol", "Lokhttp3/Protocol;", "connectFailed", "connectStart", "connectionAcquired", "connection", "Lokhttp3/Connection;", "connectionReleased", "dnsEnd", "domainName", "", "inetAddressList", "", "Ljava/net/InetAddress;", "Lkotlin/jvm/JvmSuppressWildcards;", "dnsStart", "proxySelectEnd", "url", "Lokhttp3/HttpUrl;", "proxies", "proxySelectStart", "requestBodyEnd", "byteCount", "", "requestBodyStart", "requestFailed", "requestHeadersEnd", "request", "Lokhttp3/Request;", "requestHeadersStart", "responseBodyEnd", "responseBodyStart", "responseFailed", "responseHeadersEnd", "responseHeadersStart", "satisfactionFailure", "secureConnectEnd", "handshake", "Lokhttp3/Handshake;", "secureConnectStart", "Companion", "Factory", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public abstract class EventListener {
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lokhttp3/EventListener$Companion;", "", "()V", "NONE", "Lokhttp3/EventListener;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Lokhttp3/EventListener$Factory;", "", "create", "Lokhttp3/EventListener;", "call", "Lokhttp3/Call;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public interface Factory {
        EventListener create(Call arg1);
    }

    public static final Companion Companion;
    public static final EventListener NONE;

    static {
        EventListener.Companion = new Companion(null);
        EventListener.NONE = new EventListener.Companion.NONE.1();
    }

    public void cacheConditionalHit(Call call0, Response response0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(response0, "cachedResponse");
    }

    public void cacheHit(Call call0, Response response0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(response0, "response");
    }

    public void cacheMiss(Call call0) {
        Intrinsics.checkNotNullParameter(call0, "call");
    }

    public void callEnd(Call call0) {
        Intrinsics.checkNotNullParameter(call0, "call");
    }

    public void callFailed(Call call0, IOException iOException0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(iOException0, "ioe");
    }

    public void callStart(Call call0) {
        Intrinsics.checkNotNullParameter(call0, "call");
    }

    public void canceled(Call call0) {
        Intrinsics.checkNotNullParameter(call0, "call");
    }

    public void connectEnd(Call call0, InetSocketAddress inetSocketAddress0, Proxy proxy0, Protocol protocol0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(inetSocketAddress0, "inetSocketAddress");
        Intrinsics.checkNotNullParameter(proxy0, "proxy");
    }

    public void connectFailed(Call call0, InetSocketAddress inetSocketAddress0, Proxy proxy0, Protocol protocol0, IOException iOException0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(inetSocketAddress0, "inetSocketAddress");
        Intrinsics.checkNotNullParameter(proxy0, "proxy");
        Intrinsics.checkNotNullParameter(iOException0, "ioe");
    }

    public void connectStart(Call call0, InetSocketAddress inetSocketAddress0, Proxy proxy0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(inetSocketAddress0, "inetSocketAddress");
        Intrinsics.checkNotNullParameter(proxy0, "proxy");
    }

    public void connectionAcquired(Call call0, Connection connection0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(connection0, "connection");
    }

    public void connectionReleased(Call call0, Connection connection0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(connection0, "connection");
    }

    public void dnsEnd(Call call0, String s, List list0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(s, "domainName");
        Intrinsics.checkNotNullParameter(list0, "inetAddressList");
    }

    public void dnsStart(Call call0, String s) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(s, "domainName");
    }

    public void proxySelectEnd(Call call0, HttpUrl httpUrl0, List list0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(httpUrl0, "url");
        Intrinsics.checkNotNullParameter(list0, "proxies");
    }

    public void proxySelectStart(Call call0, HttpUrl httpUrl0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(httpUrl0, "url");
    }

    public void requestBodyEnd(Call call0, long v) {
        Intrinsics.checkNotNullParameter(call0, "call");
    }

    public void requestBodyStart(Call call0) {
        Intrinsics.checkNotNullParameter(call0, "call");
    }

    public void requestFailed(Call call0, IOException iOException0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(iOException0, "ioe");
    }

    public void requestHeadersEnd(Call call0, Request request0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(request0, "request");
    }

    public void requestHeadersStart(Call call0) {
        Intrinsics.checkNotNullParameter(call0, "call");
    }

    public void responseBodyEnd(Call call0, long v) {
        Intrinsics.checkNotNullParameter(call0, "call");
    }

    public void responseBodyStart(Call call0) {
        Intrinsics.checkNotNullParameter(call0, "call");
    }

    public void responseFailed(Call call0, IOException iOException0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(iOException0, "ioe");
    }

    public void responseHeadersEnd(Call call0, Response response0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(response0, "response");
    }

    public void responseHeadersStart(Call call0) {
        Intrinsics.checkNotNullParameter(call0, "call");
    }

    public void satisfactionFailure(Call call0, Response response0) {
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(response0, "response");
    }

    public void secureConnectEnd(Call call0, Handshake handshake0) {
        Intrinsics.checkNotNullParameter(call0, "call");
    }

    public void secureConnectStart(Call call0) {
        Intrinsics.checkNotNullParameter(call0, "call");
    }
}

