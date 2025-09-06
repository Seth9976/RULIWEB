package okhttp3.internal.platform;

import android.os.Build.VERSION;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.platform.android.AndroidCertificateChainCleaner;
import okhttp3.internal.platform.android.AndroidSocketAdapter;
import okhttp3.internal.platform.android.BouncyCastleSocketAdapter;
import okhttp3.internal.platform.android.ConscryptSocketAdapter;
import okhttp3.internal.platform.android.DeferredSocketAdapter;
import okhttp3.internal.platform.android.SocketAdapter;
import okhttp3.internal.platform.android.StandardAndroidSocketAdapter;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.TrustRootIndex;
import retrofit2.Platform..ExternalSyntheticApiModelOutline0;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 !2\u00020\u0001:\u0002!\"B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\tH\u0016J\u0010\u0010\n\u001A\u00020\u000B2\u0006\u0010\b\u001A\u00020\tH\u0016J-\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000F2\b\u0010\u0010\u001A\u0004\u0018\u00010\u00112\u0011\u0010\u0012\u001A\r\u0012\t\u0012\u00070\u0013¢\u0006\u0002\b\u00140\u0004H\u0016J \u0010\u0015\u001A\u00020\r2\u0006\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00020\u00192\u0006\u0010\u001A\u001A\u00020\u001BH\u0016J\u0012\u0010\u001C\u001A\u0004\u0018\u00010\u00112\u0006\u0010\u000E\u001A\u00020\u000FH\u0016J\u0010\u0010\u001D\u001A\u00020\u001E2\u0006\u0010\u0010\u001A\u00020\u0011H\u0016J\u0012\u0010\b\u001A\u0004\u0018\u00010\t2\u0006\u0010\u001F\u001A\u00020 H\u0016R\u0014\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lokhttp3/internal/platform/AndroidPlatform;", "Lokhttp3/internal/platform/Platform;", "()V", "socketAdapters", "", "Lokhttp3/internal/platform/android/SocketAdapter;", "buildCertificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "buildTrustRootIndex", "Lokhttp3/internal/tls/TrustRootIndex;", "configureTlsExtensions", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", "", "protocols", "Lokhttp3/Protocol;", "Lkotlin/jvm/JvmSuppressWildcards;", "connectSocket", "socket", "Ljava/net/Socket;", "address", "Ljava/net/InetSocketAddress;", "connectTimeout", "", "getSelectedProtocol", "isCleartextTrafficPermitted", "", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "Companion", "CustomTrustRootIndex", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class AndroidPlatform extends Platform {
    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001A\u0004\u0018\u00010\u0007R\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0003\u0010\u0005¨\u0006\b"}, d2 = {"Lokhttp3/internal/platform/AndroidPlatform$Companion;", "", "()V", "isSupported", "", "()Z", "buildIfSupported", "Lokhttp3/internal/platform/Platform;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final Platform buildIfSupported() {
            return this.isSupported() ? new AndroidPlatform() : null;
        }

        public final boolean isSupported() {
            return AndroidPlatform.isSupported;
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u0007\u001A\u00020\u0003HÂ\u0003J\t\u0010\b\u001A\u00020\u0005HÂ\u0003J\u001D\u0010\t\u001A\u00020\u00002\b\b\u0002\u0010\u0002\u001A\u00020\u00032\b\b\u0002\u0010\u0004\u001A\u00020\u0005HÆ\u0001J\u0013\u0010\n\u001A\u00020\u000B2\b\u0010\f\u001A\u0004\u0018\u00010\rHÖ\u0003J\u0012\u0010\u000E\u001A\u0004\u0018\u00010\u000F2\u0006\u0010\u0010\u001A\u00020\u000FH\u0016J\t\u0010\u0011\u001A\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001A\u00020\u0014HÖ\u0001R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lokhttp3/internal/platform/AndroidPlatform$CustomTrustRootIndex;", "Lokhttp3/internal/tls/TrustRootIndex;", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "findByIssuerAndSignatureMethod", "Ljava/lang/reflect/Method;", "(Ljavax/net/ssl/X509TrustManager;Ljava/lang/reflect/Method;)V", "component1", "component2", "copy", "equals", "", "other", "", "findByIssuerAndSignature", "Ljava/security/cert/X509Certificate;", "cert", "hashCode", "", "toString", "", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class CustomTrustRootIndex implements TrustRootIndex {
        private final Method findByIssuerAndSignatureMethod;
        private final X509TrustManager trustManager;

        public CustomTrustRootIndex(X509TrustManager x509TrustManager0, Method method0) {
            Intrinsics.checkNotNullParameter(x509TrustManager0, "trustManager");
            Intrinsics.checkNotNullParameter(method0, "findByIssuerAndSignatureMethod");
            super();
            this.trustManager = x509TrustManager0;
            this.findByIssuerAndSignatureMethod = method0;
        }

        private final X509TrustManager component1() {
            return this.trustManager;
        }

        private final Method component2() {
            return this.findByIssuerAndSignatureMethod;
        }

        public final CustomTrustRootIndex copy(X509TrustManager x509TrustManager0, Method method0) {
            Intrinsics.checkNotNullParameter(x509TrustManager0, "trustManager");
            Intrinsics.checkNotNullParameter(method0, "findByIssuerAndSignatureMethod");
            return new CustomTrustRootIndex(x509TrustManager0, method0);
        }

        public static CustomTrustRootIndex copy$default(CustomTrustRootIndex androidPlatform$CustomTrustRootIndex0, X509TrustManager x509TrustManager0, Method method0, int v, Object object0) {
            if((v & 1) != 0) {
                x509TrustManager0 = androidPlatform$CustomTrustRootIndex0.trustManager;
            }
            if((v & 2) != 0) {
                method0 = androidPlatform$CustomTrustRootIndex0.findByIssuerAndSignatureMethod;
            }
            return androidPlatform$CustomTrustRootIndex0.copy(x509TrustManager0, method0);
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            if(!(object0 instanceof CustomTrustRootIndex)) {
                return false;
            }
            return Intrinsics.areEqual(this.trustManager, ((CustomTrustRootIndex)object0).trustManager) ? Intrinsics.areEqual(this.findByIssuerAndSignatureMethod, ((CustomTrustRootIndex)object0).findByIssuerAndSignatureMethod) : false;
        }

        @Override  // okhttp3.internal.tls.TrustRootIndex
        public X509Certificate findByIssuerAndSignature(X509Certificate x509Certificate0) {
            Intrinsics.checkNotNullParameter(x509Certificate0, "cert");
            try {
                Object object0 = this.findByIssuerAndSignatureMethod.invoke(this.trustManager, x509Certificate0);
                Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type java.security.cert.TrustAnchor");
                return ((TrustAnchor)object0).getTrustedCert();
            }
            catch(IllegalAccessException illegalAccessException0) {
                throw new AssertionError("unable to get issues and signature", illegalAccessException0);
            }
            catch(InvocationTargetException unused_ex) {
                return null;
            }
        }

        @Override
        public int hashCode() {
            return this.trustManager.hashCode() * 0x1F + this.findByIssuerAndSignatureMethod.hashCode();
        }

        @Override
        public String toString() {
            return "CustomTrustRootIndex(trustManager=" + this.trustManager + ", findByIssuerAndSignatureMethod=" + this.findByIssuerAndSignatureMethod + ')';
        }
    }

    public static final Companion Companion;
    private static final boolean isSupported;
    private final List socketAdapters;

    static {
        AndroidPlatform.Companion = new Companion(null);
        AndroidPlatform.isSupported = Platform.Companion.isAndroid() && Build.VERSION.SDK_INT < 30;
    }

    public AndroidPlatform() {
        Iterable iterable0 = CollectionsKt.listOfNotNull(new SocketAdapter[]{okhttp3.internal.platform.android.StandardAndroidSocketAdapter.Companion.buildIfSupported$default(StandardAndroidSocketAdapter.Companion, null, 1, null), new DeferredSocketAdapter(AndroidSocketAdapter.Companion.getPlayProviderFactory()), new DeferredSocketAdapter(ConscryptSocketAdapter.Companion.getFactory()), new DeferredSocketAdapter(BouncyCastleSocketAdapter.Companion.getFactory())});
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            if(((SocketAdapter)object0).isSupported()) {
                collection0.add(object0);
            }
        }
        this.socketAdapters = (List)collection0;
    }

    @Override  // okhttp3.internal.platform.Platform
    public CertificateChainCleaner buildCertificateChainCleaner(X509TrustManager x509TrustManager0) {
        Intrinsics.checkNotNullParameter(x509TrustManager0, "trustManager");
        AndroidCertificateChainCleaner androidCertificateChainCleaner0 = AndroidCertificateChainCleaner.Companion.buildIfSupported(x509TrustManager0);
        return androidCertificateChainCleaner0 != null ? androidCertificateChainCleaner0 : super.buildCertificateChainCleaner(x509TrustManager0);
    }

    @Override  // okhttp3.internal.platform.Platform
    public TrustRootIndex buildTrustRootIndex(X509TrustManager x509TrustManager0) {
        Intrinsics.checkNotNullParameter(x509TrustManager0, "trustManager");
        try {
            Method method0 = x509TrustManager0.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            method0.setAccessible(true);
            Intrinsics.checkNotNullExpressionValue(method0, "method");
            return new CustomTrustRootIndex(x509TrustManager0, method0);
        }
        catch(NoSuchMethodException unused_ex) {
            return super.buildTrustRootIndex(x509TrustManager0);
        }
    }

    @Override  // okhttp3.internal.platform.Platform
    public void configureTlsExtensions(SSLSocket sSLSocket0, String s, List list0) {
        Object object0 = null;
        Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
        Intrinsics.checkNotNullParameter(list0, "protocols");
        for(Object object1: this.socketAdapters) {
            if(((SocketAdapter)object1).matchesSocket(sSLSocket0)) {
                object0 = object1;
                break;
            }
        }
        if(((SocketAdapter)object0) != null) {
            ((SocketAdapter)object0).configureTlsExtensions(sSLSocket0, s, list0);
        }
    }

    @Override  // okhttp3.internal.platform.Platform
    public void connectSocket(Socket socket0, InetSocketAddress inetSocketAddress0, int v) throws IOException {
        Intrinsics.checkNotNullParameter(socket0, "socket");
        Intrinsics.checkNotNullParameter(inetSocketAddress0, "address");
        try {
            socket0.connect(inetSocketAddress0, v);
            return;
        }
        catch(ClassCastException classCastException0) {
            if(Build.VERSION.SDK_INT == 26) {
                throw new IOException("Exception in connect", classCastException0);
            }
            throw classCastException0;
        }
    }

    @Override  // okhttp3.internal.platform.Platform
    public String getSelectedProtocol(SSLSocket sSLSocket0) {
        Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
        for(Object object0: this.socketAdapters) {
            if(((SocketAdapter)object0).matchesSocket(sSLSocket0)) {
                return ((SocketAdapter)object0) == null ? null : ((SocketAdapter)object0).getSelectedProtocol(sSLSocket0);
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    @Override  // okhttp3.internal.platform.Platform
    public boolean isCleartextTrafficPermitted(String s) {
        Intrinsics.checkNotNullParameter(s, "hostname");
        if(Build.VERSION.SDK_INT >= 24) {
            return Platform..ExternalSyntheticApiModelOutline0.m(Platform..ExternalSyntheticApiModelOutline0.m(), s);
        }
        return Build.VERSION.SDK_INT < 23 ? true : Platform..ExternalSyntheticApiModelOutline0.m(Platform..ExternalSyntheticApiModelOutline0.m());
    }

    @Override  // okhttp3.internal.platform.Platform
    public X509TrustManager trustManager(SSLSocketFactory sSLSocketFactory0) {
        Intrinsics.checkNotNullParameter(sSLSocketFactory0, "sslSocketFactory");
        for(Object object0: this.socketAdapters) {
            if(((SocketAdapter)object0).matchesSocketFactory(sSLSocketFactory0)) {
                return ((SocketAdapter)object0) == null ? null : ((SocketAdapter)object0).trustManager(sSLSocketFactory0);
            }
            if(false) {
                break;
            }
        }
        return null;
    }
}

