package okhttp3.internal.platform.android;

import android.net.http.X509TrustManagerExtensions;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.tls.CertificateChainCleaner;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J$\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\n\u001A\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u000B\u001A\u00020\fH\u0017J\u0013\u0010\r\u001A\u00020\u000E2\b\u0010\u000F\u001A\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0011\u001A\u00020\u0012H\u0016R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lokhttp3/internal/platform/android/AndroidCertificateChainCleaner;", "Lokhttp3/internal/tls/CertificateChainCleaner;", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "x509TrustManagerExtensions", "Landroid/net/http/X509TrustManagerExtensions;", "(Ljavax/net/ssl/X509TrustManager;Landroid/net/http/X509TrustManagerExtensions;)V", "clean", "", "Ljava/security/cert/Certificate;", "chain", "hostname", "", "equals", "", "other", "", "hashCode", "", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class AndroidCertificateChainCleaner extends CertificateChainCleaner {
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001A\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lokhttp3/internal/platform/android/AndroidCertificateChainCleaner$Companion;", "", "()V", "buildIfSupported", "Lokhttp3/internal/platform/android/AndroidCertificateChainCleaner;", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final AndroidCertificateChainCleaner buildIfSupported(X509TrustManager x509TrustManager0) {
            X509TrustManagerExtensions x509TrustManagerExtensions0;
            Intrinsics.checkNotNullParameter(x509TrustManager0, "trustManager");
            try {
                x509TrustManagerExtensions0 = null;
                x509TrustManagerExtensions0 = new X509TrustManagerExtensions(x509TrustManager0);
            }
            catch(IllegalArgumentException unused_ex) {
            }
            return x509TrustManagerExtensions0 == null ? null : new AndroidCertificateChainCleaner(x509TrustManager0, x509TrustManagerExtensions0);
        }
    }

    public static final Companion Companion;
    private final X509TrustManager trustManager;
    private final X509TrustManagerExtensions x509TrustManagerExtensions;

    static {
        AndroidCertificateChainCleaner.Companion = new Companion(null);
    }

    public AndroidCertificateChainCleaner(X509TrustManager x509TrustManager0, X509TrustManagerExtensions x509TrustManagerExtensions0) {
        Intrinsics.checkNotNullParameter(x509TrustManager0, "trustManager");
        Intrinsics.checkNotNullParameter(x509TrustManagerExtensions0, "x509TrustManagerExtensions");
        super();
        this.trustManager = x509TrustManager0;
        this.x509TrustManagerExtensions = x509TrustManagerExtensions0;
    }

    @Override  // okhttp3.internal.tls.CertificateChainCleaner
    public List clean(List list0, String s) throws SSLPeerUnverifiedException {
        Intrinsics.checkNotNullParameter(list0, "chain");
        Intrinsics.checkNotNullParameter(s, "hostname");
        Object[] arr_object = list0.toArray(new X509Certificate[0]);
        Intrinsics.checkNotNull(arr_object, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        X509Certificate[] arr_x509Certificate = (X509Certificate[])arr_object;
        try {
            List list1 = this.x509TrustManagerExtensions.checkServerTrusted(arr_x509Certificate, "RSA", s);
            Intrinsics.checkNotNullExpressionValue(list1, "x509TrustManagerExtensio…ficates, \"RSA\", hostname)");
            return list1;
        }
        catch(CertificateException certificateException0) {
            SSLPeerUnverifiedException sSLPeerUnverifiedException0 = new SSLPeerUnverifiedException(certificateException0.getMessage());
            sSLPeerUnverifiedException0.initCause(certificateException0);
            throw sSLPeerUnverifiedException0;
        }
    }

    @Override
    public boolean equals(Object object0) {
        return object0 instanceof AndroidCertificateChainCleaner && ((AndroidCertificateChainCleaner)object0).trustManager == this.trustManager;
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this.trustManager);
    }
}

