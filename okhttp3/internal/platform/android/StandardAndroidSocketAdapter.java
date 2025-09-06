package okhttp3.internal.platform.android;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.platform.Platform;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000E2\u00020\u0001:\u0001\u000EB1\u0012\u000E\u0010\u0002\u001A\n\u0012\u0006\b\u0000\u0012\u00020\u00040\u0003\u0012\u000E\u0010\u0005\u001A\n\u0012\u0006\b\u0000\u0012\u00020\u00060\u0003\u0012\n\u0010\u0007\u001A\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\u0006H\u0016J\u0012\u0010\f\u001A\u0004\u0018\u00010\r2\u0006\u0010\u000B\u001A\u00020\u0006H\u0016R\u0012\u0010\u0007\u001A\u0006\u0012\u0002\b\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001A\n\u0012\u0006\b\u0000\u0012\u00020\u00060\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000F"}, d2 = {"Lokhttp3/internal/platform/android/StandardAndroidSocketAdapter;", "Lokhttp3/internal/platform/android/AndroidSocketAdapter;", "sslSocketClass", "Ljava/lang/Class;", "Ljavax/net/ssl/SSLSocket;", "sslSocketFactoryClass", "Ljavax/net/ssl/SSLSocketFactory;", "paramClass", "(Ljava/lang/Class;Ljava/lang/Class;Ljava/lang/Class;)V", "matchesSocketFactory", "", "sslSocketFactory", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class StandardAndroidSocketAdapter extends AndroidSocketAdapter {
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001A\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0005\u001A\u00020\u0006¨\u0006\u0007"}, d2 = {"Lokhttp3/internal/platform/android/StandardAndroidSocketAdapter$Companion;", "", "()V", "buildIfSupported", "Lokhttp3/internal/platform/android/SocketAdapter;", "packageName", "", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final SocketAdapter buildIfSupported(String s) {
            Intrinsics.checkNotNullParameter(s, "packageName");
            try {
                Class class0 = Class.forName((s + ".OpenSSLSocketImpl"));
                Intrinsics.checkNotNull(class0, "null cannot be cast to non-null type java.lang.Class<in javax.net.ssl.SSLSocket>");
                Class class1 = Class.forName((s + ".OpenSSLSocketFactoryImpl"));
                Intrinsics.checkNotNull(class1, "null cannot be cast to non-null type java.lang.Class<in javax.net.ssl.SSLSocketFactory>");
                Class class2 = Class.forName((s + ".SSLParametersImpl"));
                Intrinsics.checkNotNullExpressionValue(class2, "paramsClass");
                return new StandardAndroidSocketAdapter(class0, class1, class2);
            }
            catch(Exception exception0) {
                Platform.Companion.get().log("unable to load android socket classes", 5, exception0);
                return null;
            }
        }

        public static SocketAdapter buildIfSupported$default(Companion standardAndroidSocketAdapter$Companion0, String s, int v, Object object0) {
            if((v & 1) != 0) {
                s = "com.android.org.conscrypt";
            }
            return standardAndroidSocketAdapter$Companion0.buildIfSupported(s);
        }
    }

    public static final Companion Companion;
    private final Class paramClass;
    private final Class sslSocketFactoryClass;

    static {
        StandardAndroidSocketAdapter.Companion = new Companion(null);
    }

    public StandardAndroidSocketAdapter(Class class0, Class class1, Class class2) {
        Intrinsics.checkNotNullParameter(class0, "sslSocketClass");
        Intrinsics.checkNotNullParameter(class1, "sslSocketFactoryClass");
        Intrinsics.checkNotNullParameter(class2, "paramClass");
        super(class0);
        this.sslSocketFactoryClass = class1;
        this.paramClass = class2;
    }

    @Override  // okhttp3.internal.platform.android.AndroidSocketAdapter
    public boolean matchesSocketFactory(SSLSocketFactory sSLSocketFactory0) {
        Intrinsics.checkNotNullParameter(sSLSocketFactory0, "sslSocketFactory");
        return this.sslSocketFactoryClass.isInstance(sSLSocketFactory0);
    }

    @Override  // okhttp3.internal.platform.android.AndroidSocketAdapter
    public X509TrustManager trustManager(SSLSocketFactory sSLSocketFactory0) {
        Intrinsics.checkNotNullParameter(sSLSocketFactory0, "sslSocketFactory");
        Object object0 = _UtilJvmKt.readFieldOrNull(sSLSocketFactory0, this.paramClass, "sslParameters");
        Intrinsics.checkNotNull(object0);
        X509TrustManager x509TrustManager0 = (X509TrustManager)_UtilJvmKt.readFieldOrNull(object0, X509TrustManager.class, "x509TrustManager");
        return x509TrustManager0 == null ? ((X509TrustManager)_UtilJvmKt.readFieldOrNull(object0, X509TrustManager.class, "trustManager")) : x509TrustManager0;
    }
}

