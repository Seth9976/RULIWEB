package okhttp3.internal.platform;

import android.os.Build.VERSION;
import android.util.CloseGuard;
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
import okhttp3.internal.platform.android.Android10SocketAdapter;
import okhttp3.internal.platform.android.AndroidCertificateChainCleaner;
import okhttp3.internal.platform.android.AndroidSocketAdapter;
import okhttp3.internal.platform.android.BouncyCastleSocketAdapter;
import okhttp3.internal.platform.android.ConscryptSocketAdapter;
import okhttp3.internal.platform.android.DeferredSocketAdapter;
import okhttp3.internal.platform.android.SocketAdapter;
import okhttp3.internal.tls.CertificateChainCleaner;
import retrofit2.Platform..ExternalSyntheticApiModelOutline0;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u001D2\u00020\u0001:\u0001\u001DB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\tH\u0016J(\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\r2\b\u0010\u000E\u001A\u0004\u0018\u00010\u000F2\f\u0010\u0010\u001A\b\u0012\u0004\u0012\u00020\u00110\u0004H\u0016J\u0012\u0010\u0012\u001A\u0004\u0018\u00010\u000F2\u0006\u0010\f\u001A\u00020\rH\u0016J\u0012\u0010\u0013\u001A\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001A\u00020\u000FH\u0016J\u0010\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u000E\u001A\u00020\u000FH\u0017J\u001A\u0010\u0018\u001A\u00020\u000B2\u0006\u0010\u0019\u001A\u00020\u000F2\b\u0010\u001A\u001A\u0004\u0018\u00010\u0014H\u0016J\u0012\u0010\b\u001A\u0004\u0018\u00010\t2\u0006\u0010\u001B\u001A\u00020\u001CH\u0016R\u0014\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001E"}, d2 = {"Lokhttp3/internal/platform/Android10Platform;", "Lokhttp3/internal/platform/Platform;", "()V", "socketAdapters", "", "Lokhttp3/internal/platform/android/SocketAdapter;", "buildCertificateChainCleaner", "Lokhttp3/internal/tls/CertificateChainCleaner;", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "configureTlsExtensions", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", "", "protocols", "Lokhttp3/Protocol;", "getSelectedProtocol", "getStackTraceForCloseable", "", "closer", "isCleartextTrafficPermitted", "", "logCloseableLeak", "message", "stackTrace", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class Android10Platform extends Platform {
    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001A\u0004\u0018\u00010\u0007R\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0003\u0010\u0005¨\u0006\b"}, d2 = {"Lokhttp3/internal/platform/Android10Platform$Companion;", "", "()V", "isSupported", "", "()Z", "buildIfSupported", "Lokhttp3/internal/platform/Platform;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final Platform buildIfSupported() {
            return this.isSupported() ? new Android10Platform() : null;
        }

        public final boolean isSupported() {
            return Android10Platform.isSupported;
        }
    }

    public static final Companion Companion;
    private static final boolean isSupported;
    private final List socketAdapters;

    static {
        Android10Platform.Companion = new Companion(null);
        Android10Platform.isSupported = Platform.Companion.isAndroid() && Build.VERSION.SDK_INT >= 29;
    }

    public Android10Platform() {
        Iterable iterable0 = CollectionsKt.listOfNotNull(new SocketAdapter[]{Android10SocketAdapter.Companion.buildIfSupported(), new DeferredSocketAdapter(AndroidSocketAdapter.Companion.getPlayProviderFactory()), new DeferredSocketAdapter(ConscryptSocketAdapter.Companion.getFactory()), new DeferredSocketAdapter(BouncyCastleSocketAdapter.Companion.getFactory())});
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
    public Object getStackTraceForCloseable(String s) {
        Intrinsics.checkNotNullParameter(s, "closer");
        if(Build.VERSION.SDK_INT >= 30) {
            CloseGuard closeGuard0 = Platform..ExternalSyntheticApiModelOutline0.m();
            Platform..ExternalSyntheticApiModelOutline0.m(closeGuard0, s);
            return closeGuard0;
        }
        return super.getStackTraceForCloseable(s);
    }

    @Override  // okhttp3.internal.platform.Platform
    public boolean isCleartextTrafficPermitted(String s) {
        Intrinsics.checkNotNullParameter(s, "hostname");
        return Platform..ExternalSyntheticApiModelOutline0.m(Platform..ExternalSyntheticApiModelOutline0.m(), s);
    }

    @Override  // okhttp3.internal.platform.Platform
    public void logCloseableLeak(String s, Object object0) {
        Intrinsics.checkNotNullParameter(s, "message");
        if(Build.VERSION.SDK_INT >= 30) {
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type android.util.CloseGuard");
            Platform..ExternalSyntheticApiModelOutline0.m(((CloseGuard)object0));
            return;
        }
        super.logCloseableLeak(s, object0);
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

