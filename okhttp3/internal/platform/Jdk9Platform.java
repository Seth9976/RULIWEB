package okhttp3.internal.platform;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import retrofit2.Platform..ExternalSyntheticApiModelOutline0;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0002J-\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\b\u0010\u0007\u001A\u0004\u0018\u00010\b2\u0011\u0010\t\u001A\r\u0012\t\u0012\u00070\u000B¢\u0006\u0002\b\f0\nH\u0017J\u0012\u0010\r\u001A\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001A\u00020\u0006H\u0017J\b\u0010\u000E\u001A\u00020\u000FH\u0016J\u0012\u0010\u0010\u001A\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001A\u00020\u0013H\u0016¨\u0006\u0015"}, d2 = {"Lokhttp3/internal/platform/Jdk9Platform;", "Lokhttp3/internal/platform/Platform;", "()V", "configureTlsExtensions", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "Lkotlin/jvm/JvmSuppressWildcards;", "getSelectedProtocol", "newSSLContext", "Ljavax/net/ssl/SSLContext;", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public class Jdk9Platform extends Platform {
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u000B\u001A\u0004\u0018\u00010\fR\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0003\u0010\u0005R\u0015\u0010\u0006\u001A\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\n\u001A\u0004\b\b\u0010\t¨\u0006\r"}, d2 = {"Lokhttp3/internal/platform/Jdk9Platform$Companion;", "", "()V", "isAvailable", "", "()Z", "majorVersion", "", "getMajorVersion", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "buildIfSupported", "Lokhttp3/internal/platform/Jdk9Platform;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        // 去混淆评级： 低(20)
        public final Jdk9Platform buildIfSupported() {
            return this.isAvailable() ? new Jdk9Platform() : null;
        }

        public final Integer getMajorVersion() {
            return Jdk9Platform.majorVersion;
        }

        public final boolean isAvailable() {
            return Jdk9Platform.isAvailable;
        }
    }

    public static final Companion Companion;
    private static final boolean isAvailable;
    private static final Integer majorVersion;

    static {
        Jdk9Platform.Companion = new Companion(null);
        Integer integer0 = StringsKt.toIntOrNull("0.9");
        Jdk9Platform.majorVersion = integer0;
        boolean z = true;
        if(integer0 == null) {
            try {
                SSLSocket.class.getMethod("getApplicationProtocol", null);
                Jdk9Platform.isAvailable = z;
                return;
            }
            catch(NoSuchMethodException unused_ex) {
            }
            z = false;
        }
        else if(((int)integer0) < 9) {
            z = false;
            Jdk9Platform.isAvailable = z;
            return;
        }
        Jdk9Platform.isAvailable = z;
    }

    @Override  // okhttp3.internal.platform.Platform
    public void configureTlsExtensions(SSLSocket sSLSocket0, String s, List list0) {
        Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
        Intrinsics.checkNotNullParameter(list0, "protocols");
        SSLParameters sSLParameters0 = sSLSocket0.getSSLParameters();
        Object[] arr_object = Platform.Companion.alpnProtocolNames(list0).toArray(new String[0]);
        Intrinsics.checkNotNull(arr_object, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        Platform..ExternalSyntheticApiModelOutline0.m(sSLParameters0, ((String[])arr_object));
        sSLSocket0.setSSLParameters(sSLParameters0);
    }

    @Override  // okhttp3.internal.platform.Platform
    public String getSelectedProtocol(SSLSocket sSLSocket0) {
        Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
        try {
            String s = Platform..ExternalSyntheticApiModelOutline0.m(sSLSocket0);
            return (s == null ? true : Intrinsics.areEqual(s, "")) ? null : s;
        }
        catch(UnsupportedOperationException unused_ex) {
            return null;
        }
    }

    @Override  // okhttp3.internal.platform.Platform
    public SSLContext newSSLContext() {
        SSLContext sSLContext1;
        if(Jdk9Platform.majorVersion != null && ((int)Jdk9Platform.majorVersion) >= 9) {
            SSLContext sSLContext0 = SSLContext.getInstance("TLS");
            Intrinsics.checkNotNullExpressionValue(sSLContext0, "getInstance(\"TLS\")");
            return sSLContext0;
        }
        try {
            sSLContext1 = SSLContext.getInstance("TLSv1.3");
        }
        catch(NoSuchAlgorithmException unused_ex) {
            sSLContext1 = SSLContext.getInstance("TLS");
        }
        Intrinsics.checkNotNullExpressionValue(sSLContext1, "try {\n          // Based…Instance(\"TLS\")\n        }");
        return sSLContext1;
    }

    @Override  // okhttp3.internal.platform.Platform
    public X509TrustManager trustManager(SSLSocketFactory sSLSocketFactory0) {
        Intrinsics.checkNotNullParameter(sSLSocketFactory0, "sslSocketFactory");
        throw new UnsupportedOperationException("clientBuilder.sslSocketFactory(SSLSocketFactory) not supported on JDK 8 (>= 252) or JDK 9+");
    }
}

