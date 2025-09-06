package okhttp3.internal.platform;

import java.security.Provider;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.conscrypt.Conscrypt.Version;
import org.conscrypt.Conscrypt;
import org.conscrypt.ConscryptHostnameVerifier;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00182\u00020\u0001:\u0002\u0018\u0019B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J-\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\b\u0010\t\u001A\u0004\u0018\u00010\n2\u0011\u0010\u000B\u001A\r\u0012\t\u0012\u00070\r¢\u0006\u0002\b\u000E0\fH\u0016J\u0012\u0010\u000F\u001A\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001A\u00020\bH\u0016J\b\u0010\u0010\u001A\u00020\u0011H\u0016J\u0010\u0010\u0012\u001A\u00020\u00132\u0006\u0010\u0014\u001A\u00020\u0015H\u0016J\b\u0010\u0016\u001A\u00020\u0015H\u0016J\u0012\u0010\u0014\u001A\u0004\u0018\u00010\u00152\u0006\u0010\u0017\u001A\u00020\u0013H\u0016R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001A"}, d2 = {"Lokhttp3/internal/platform/ConscryptPlatform;", "Lokhttp3/internal/platform/Platform;", "()V", "provider", "Ljava/security/Provider;", "configureTlsExtensions", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "Lkotlin/jvm/JvmSuppressWildcards;", "getSelectedProtocol", "newSSLContext", "Ljavax/net/ssl/SSLContext;", "newSslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "platformTrustManager", "sslSocketFactory", "Companion", "DisabledHostnameVerifier", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class ConscryptPlatform extends Platform {
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0006\u001A\u00020\u00042\u0006\u0010\u0007\u001A\u00020\b2\b\b\u0002\u0010\t\u001A\u00020\b2\b\b\u0002\u0010\n\u001A\u00020\bJ\b\u0010\u000B\u001A\u0004\u0018\u00010\fR\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0003\u0010\u0005¨\u0006\r"}, d2 = {"Lokhttp3/internal/platform/ConscryptPlatform$Companion;", "", "()V", "isSupported", "", "()Z", "atLeastVersion", "major", "", "minor", "patch", "buildIfSupported", "Lokhttp3/internal/platform/ConscryptPlatform;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final boolean atLeastVersion(int v, int v1, int v2) {
            Conscrypt.Version conscrypt$Version0 = Conscrypt.version();
            if(conscrypt$Version0 == null) {
                return false;
            }
            if(conscrypt$Version0.major() != v) {
                return conscrypt$Version0.major() > v;
            }
            return conscrypt$Version0.minor() == v1 ? conscrypt$Version0.patch() >= v2 : conscrypt$Version0.minor() > v1;
        }

        public static boolean atLeastVersion$default(Companion conscryptPlatform$Companion0, int v, int v1, int v2, int v3, Object object0) {
            if((v3 & 2) != 0) {
                v1 = 0;
            }
            if((v3 & 4) != 0) {
                v2 = 0;
            }
            return conscryptPlatform$Companion0.atLeastVersion(v, v1, v2);
        }

        // 去混淆评级： 低(30)
        public final ConscryptPlatform buildIfSupported() {
            return null;
        }

        // 去混淆评级： 低(20)
        public final boolean isSupported() [...] // 潜在的解密器
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J3\u0010\u0003\u001A\u00020\u00042\u0010\u0010\u0005\u001A\f\u0012\u0006\b\u0001\u0012\u00020\u0007\u0018\u00010\u00062\b\u0010\b\u001A\u0004\u0018\u00010\t2\b\u0010\n\u001A\u0004\u0018\u00010\u000BH\u0016¢\u0006\u0002\u0010\fJ\u001A\u0010\u0003\u001A\u00020\u00042\b\u0010\b\u001A\u0004\u0018\u00010\t2\b\u0010\n\u001A\u0004\u0018\u00010\u000B¨\u0006\r"}, d2 = {"Lokhttp3/internal/platform/ConscryptPlatform$DisabledHostnameVerifier;", "Lorg/conscrypt/ConscryptHostnameVerifier;", "()V", "verify", "", "certs", "", "Ljava/security/cert/X509Certificate;", "hostname", "", "session", "Ljavax/net/ssl/SSLSession;", "([Ljava/security/cert/X509Certificate;Ljava/lang/String;Ljavax/net/ssl/SSLSession;)Z", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class DisabledHostnameVerifier implements ConscryptHostnameVerifier {
        public static final DisabledHostnameVerifier INSTANCE;

        static {
            DisabledHostnameVerifier.INSTANCE = new DisabledHostnameVerifier();
        }

        public final boolean verify(String s, SSLSession sSLSession0) {
            return true;
        }

        public boolean verify(X509Certificate[] arr_x509Certificate, String s, SSLSession sSLSession0) {
            return true;
        }
    }

    public static final Companion Companion;
    private static final boolean isSupported;
    private final Provider provider;

    static {
        Companion conscryptPlatform$Companion0 = new Companion(null);
        ConscryptPlatform.Companion = conscryptPlatform$Companion0;
        boolean z = false;
        try {
            Class.forName("org.conscrypt.Conscrypt$Version", false, conscryptPlatform$Companion0.getClass().getClassLoader());
            if(Conscrypt.isAvailable() && conscryptPlatform$Companion0.atLeastVersion(2, 1, 0)) {
                z = true;
            }
        }
        catch(NoClassDefFoundError | ClassNotFoundException unused_ex) {
        }
        ConscryptPlatform.isSupported = z;
    }

    private ConscryptPlatform() {
        Provider provider0 = Conscrypt.newProvider();
        Intrinsics.checkNotNullExpressionValue(provider0, "newProvider()");
        this.provider = provider0;
    }

    public ConscryptPlatform(DefaultConstructorMarker defaultConstructorMarker0) {
    }

    public static final boolean access$isSupported$cp() [...] // 潜在的解密器

    @Override  // okhttp3.internal.platform.Platform
    public void configureTlsExtensions(SSLSocket sSLSocket0, String s, List list0) {
        Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
        Intrinsics.checkNotNullParameter(list0, "protocols");
        if(Conscrypt.isConscrypt(sSLSocket0)) {
            Conscrypt.setUseSessionTickets(sSLSocket0, true);
            Object[] arr_object = Platform.Companion.alpnProtocolNames(list0).toArray(new String[0]);
            Intrinsics.checkNotNull(arr_object, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            Conscrypt.setApplicationProtocols(sSLSocket0, ((String[])arr_object));
            return;
        }
        super.configureTlsExtensions(sSLSocket0, s, list0);
    }

    @Override  // okhttp3.internal.platform.Platform
    public String getSelectedProtocol(SSLSocket sSLSocket0) {
        Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
        return Conscrypt.isConscrypt(sSLSocket0) ? Conscrypt.getApplicationProtocol(sSLSocket0) : super.getSelectedProtocol(sSLSocket0);
    }

    @Override  // okhttp3.internal.platform.Platform
    public SSLContext newSSLContext() {
        SSLContext sSLContext0 = SSLContext.getInstance("TLS", this.provider);
        Intrinsics.checkNotNullExpressionValue(sSLContext0, "getInstance(\"TLS\", provider)");
        return sSLContext0;
    }

    @Override  // okhttp3.internal.platform.Platform
    public SSLSocketFactory newSslSocketFactory(X509TrustManager x509TrustManager0) {
        Intrinsics.checkNotNullParameter(x509TrustManager0, "trustManager");
        SSLContext sSLContext0 = this.newSSLContext();
        sSLContext0.init(null, new TrustManager[]{x509TrustManager0}, null);
        SSLSocketFactory sSLSocketFactory0 = sSLContext0.getSocketFactory();
        Intrinsics.checkNotNullExpressionValue(sSLSocketFactory0, "newSSLContext().apply {\n…null)\n    }.socketFactory");
        return sSLSocketFactory0;
    }

    @Override  // okhttp3.internal.platform.Platform
    public X509TrustManager platformTrustManager() {
        TrustManagerFactory trustManagerFactory0 = TrustManagerFactory.getInstance("PKIX");
        trustManagerFactory0.init(null);
        TrustManager[] arr_trustManager = trustManagerFactory0.getTrustManagers();
        Intrinsics.checkNotNull(arr_trustManager);
        if(arr_trustManager.length == 1) {
            TrustManager trustManager0 = arr_trustManager[0];
            if(trustManager0 instanceof X509TrustManager) {
                Intrinsics.checkNotNull(trustManager0, "null cannot be cast to non-null type javax.net.ssl.X509TrustManager");
                Conscrypt.setHostnameVerifier(((X509TrustManager)trustManager0), DisabledHostnameVerifier.INSTANCE);
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
        return null;
    }
}

