package okhttp3.internal.platform;

import java.security.Provider;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.jsse.BCSSLParameters;
import org.bouncycastle.jsse.BCSSLSocket;
import org.bouncycastle.jsse.provider.BouncyCastleJsseProvider;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J-\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\b\u0010\t\u001A\u0004\u0018\u00010\n2\u0011\u0010\u000B\u001A\r\u0012\t\u0012\u00070\r¢\u0006\u0002\b\u000E0\fH\u0016J\u0012\u0010\u000F\u001A\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001A\u00020\bH\u0016J\b\u0010\u0010\u001A\u00020\u0011H\u0016J\b\u0010\u0012\u001A\u00020\u0013H\u0016J\u0010\u0010\u0014\u001A\u00020\u00132\u0006\u0010\u0015\u001A\u00020\u0016H\u0016R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lokhttp3/internal/platform/BouncyCastlePlatform;", "Lokhttp3/internal/platform/Platform;", "()V", "provider", "Ljava/security/Provider;", "configureTlsExtensions", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "Lkotlin/jvm/JvmSuppressWildcards;", "getSelectedProtocol", "newSSLContext", "Ljavax/net/ssl/SSLContext;", "platformTrustManager", "Ljavax/net/ssl/X509TrustManager;", "trustManager", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class BouncyCastlePlatform extends Platform {
    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001A\u0004\u0018\u00010\u0007R\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0003\u0010\u0005¨\u0006\b"}, d2 = {"Lokhttp3/internal/platform/BouncyCastlePlatform$Companion;", "", "()V", "isSupported", "", "()Z", "buildIfSupported", "Lokhttp3/internal/platform/BouncyCastlePlatform;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        // 去混淆评级： 低(20)
        public final BouncyCastlePlatform buildIfSupported() {
            return this.isSupported() ? new BouncyCastlePlatform(null) : null;
        }

        public final boolean isSupported() {
            return BouncyCastlePlatform.isSupported;
        }
    }

    public static final Companion Companion;
    private static final boolean isSupported;
    private final Provider provider;

    static {
        Companion bouncyCastlePlatform$Companion0 = new Companion(null);
        BouncyCastlePlatform.Companion = bouncyCastlePlatform$Companion0;
        boolean z = false;
        try {
            Class.forName("org.bouncycastle.jsse.provider.BouncyCastleJsseProvider", false, bouncyCastlePlatform$Companion0.getClass().getClassLoader());
            z = true;
        }
        catch(ClassNotFoundException unused_ex) {
        }
        BouncyCastlePlatform.isSupported = z;
    }

    private BouncyCastlePlatform() {
        this.provider = (Provider)new BouncyCastleJsseProvider();
    }

    public BouncyCastlePlatform(DefaultConstructorMarker defaultConstructorMarker0) {
    }

    @Override  // okhttp3.internal.platform.Platform
    public void configureTlsExtensions(SSLSocket sSLSocket0, String s, List list0) {
        Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
        Intrinsics.checkNotNullParameter(list0, "protocols");
        if(sSLSocket0 instanceof BCSSLSocket) {
            BCSSLParameters bCSSLParameters0 = ((BCSSLSocket)sSLSocket0).getParameters();
            Object[] arr_object = Platform.Companion.alpnProtocolNames(list0).toArray(new String[0]);
            Intrinsics.checkNotNull(arr_object, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            bCSSLParameters0.setApplicationProtocols(((String[])arr_object));
            ((BCSSLSocket)sSLSocket0).setParameters(bCSSLParameters0);
            return;
        }
        super.configureTlsExtensions(sSLSocket0, s, list0);
    }

    @Override  // okhttp3.internal.platform.Platform
    public String getSelectedProtocol(SSLSocket sSLSocket0) {
        Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
        if(sSLSocket0 instanceof BCSSLSocket) {
            String s = ((BCSSLSocket)sSLSocket0).getApplicationProtocol();
            return (s == null ? true : Intrinsics.areEqual(s, "")) ? null : s;
        }
        return super.getSelectedProtocol(sSLSocket0);
    }

    @Override  // okhttp3.internal.platform.Platform
    public SSLContext newSSLContext() {
        SSLContext sSLContext0 = SSLContext.getInstance("TLS", this.provider);
        Intrinsics.checkNotNullExpressionValue(sSLContext0, "getInstance(\"TLS\", provider)");
        return sSLContext0;
    }

    @Override  // okhttp3.internal.platform.Platform
    public X509TrustManager platformTrustManager() {
        TrustManagerFactory trustManagerFactory0 = TrustManagerFactory.getInstance("PKIX", "BCJSSE");
        trustManagerFactory0.init(null);
        TrustManager[] arr_trustManager = trustManagerFactory0.getTrustManagers();
        Intrinsics.checkNotNull(arr_trustManager);
        if(arr_trustManager.length == 1) {
            TrustManager trustManager0 = arr_trustManager[0];
            if(trustManager0 instanceof X509TrustManager) {
                Intrinsics.checkNotNull(trustManager0, "null cannot be cast to non-null type javax.net.ssl.X509TrustManager");
                return (X509TrustManager)trustManager0;
            }
        }
        String s = Arrays.toString(arr_trustManager);
        Intrinsics.checkNotNullExpressionValue(s, "toString(this)");
        throw new IllegalStateException(("Unexpected default trust managers: " + s).toString());
    }

    @Override  // okhttp3.internal.platform.Platform
    public X509TrustManager trustManager(SSLSocketFactory sSLSocketFactory0) {
        Intrinsics.checkNotNullParameter(sSLSocketFactory0, "sslSocketFactory");
        throw new UnsupportedOperationException("clientBuilder.sslSocketFactory(SSLSocketFactory) not supported with BouncyCastle");
    }
}

