package okhttp3.internal.platform.android;

import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.platform.BouncyCastlePlatform;
import okhttp3.internal.platform.Platform;
import org.bouncycastle.jsse.BCSSLParameters;
import org.bouncycastle.jsse.BCSSLSocket;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0003\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\b\u0010\u0007\u001A\u0004\u0018\u00010\b2\f\u0010\t\u001A\b\u0012\u0004\u0012\u00020\u000B0\nH\u0016J\u0012\u0010\f\u001A\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001A\u00020\u0006H\u0016J\b\u0010\r\u001A\u00020\u000EH\u0016J\u0010\u0010\u000F\u001A\u00020\u000E2\u0006\u0010\u0005\u001A\u00020\u0006H\u0016¨\u0006\u0011"}, d2 = {"Lokhttp3/internal/platform/android/BouncyCastleSocketAdapter;", "Lokhttp3/internal/platform/android/SocketAdapter;", "()V", "configureTlsExtensions", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "getSelectedProtocol", "isSupported", "", "matchesSocket", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class BouncyCastleSocketAdapter implements SocketAdapter {
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lokhttp3/internal/platform/android/BouncyCastleSocketAdapter$Companion;", "", "()V", "factory", "Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "getFactory", "()Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final Factory getFactory() {
            return BouncyCastleSocketAdapter.factory;
        }
    }

    public static final Companion Companion;
    private static final Factory factory;

    static {
        BouncyCastleSocketAdapter.Companion = new Companion(null);
        BouncyCastleSocketAdapter.factory = new BouncyCastleSocketAdapter.Companion.factory.1();
    }

    @Override  // okhttp3.internal.platform.android.SocketAdapter
    public void configureTlsExtensions(SSLSocket sSLSocket0, String s, List list0) {
        Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
        Intrinsics.checkNotNullParameter(list0, "protocols");
        if(this.matchesSocket(sSLSocket0)) {
            BCSSLParameters bCSSLParameters0 = ((BCSSLSocket)sSLSocket0).getParameters();
            Object[] arr_object = Platform.Companion.alpnProtocolNames(list0).toArray(new String[0]);
            Intrinsics.checkNotNull(arr_object, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            bCSSLParameters0.setApplicationProtocols(((String[])arr_object));
            ((BCSSLSocket)sSLSocket0).setParameters(bCSSLParameters0);
        }
    }

    @Override  // okhttp3.internal.platform.android.SocketAdapter
    public String getSelectedProtocol(SSLSocket sSLSocket0) {
        Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
        String s = ((BCSSLSocket)sSLSocket0).getApplicationProtocol();
        return (s == null ? true : Intrinsics.areEqual(s, "")) ? null : s;
    }

    @Override  // okhttp3.internal.platform.android.SocketAdapter
    public boolean isSupported() {
        return BouncyCastlePlatform.Companion.isSupported();
    }

    @Override  // okhttp3.internal.platform.android.SocketAdapter
    public boolean matchesSocket(SSLSocket sSLSocket0) {
        Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
        return sSLSocket0 instanceof BCSSLSocket;
    }

    @Override  // okhttp3.internal.platform.android.SocketAdapter
    public boolean matchesSocketFactory(SSLSocketFactory sSLSocketFactory0) {
        return SocketAdapter.-CC.$default$matchesSocketFactory(this, sSLSocketFactory0);
    }

    @Override  // okhttp3.internal.platform.android.SocketAdapter
    public X509TrustManager trustManager(SSLSocketFactory sSLSocketFactory0) {
        return SocketAdapter.-CC.$default$trustManager(this, sSLSocketFactory0);
    }
}

