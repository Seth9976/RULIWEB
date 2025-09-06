package okhttp3.internal.platform.android;

import android.os.Build.VERSION;
import java.io.IOException;
import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.platform.Platform;
import retrofit2.Platform..ExternalSyntheticApiModelOutline0;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\b\u0010\u0007\u001A\u0004\u0018\u00010\b2\f\u0010\t\u001A\b\u0012\u0004\u0012\u00020\u000B0\nH\u0017J\u0012\u0010\f\u001A\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001A\u00020\u0006H\u0017J\b\u0010\r\u001A\u00020\u000EH\u0016J\u0010\u0010\u000F\u001A\u00020\u000E2\u0006\u0010\u0005\u001A\u00020\u0006H\u0016¨\u0006\u0011"}, d2 = {"Lokhttp3/internal/platform/android/Android10SocketAdapter;", "Lokhttp3/internal/platform/android/SocketAdapter;", "()V", "configureTlsExtensions", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "getSelectedProtocol", "isSupported", "", "matchesSocket", "Companion", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class Android10SocketAdapter implements SocketAdapter {
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\b\u0087\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001A\u0004\u0018\u00010\u0004J\u0006\u0010\u0005\u001A\u00020\u0006¨\u0006\u0007"}, d2 = {"Lokhttp3/internal/platform/android/Android10SocketAdapter$Companion;", "", "()V", "buildIfSupported", "Lokhttp3/internal/platform/android/SocketAdapter;", "isSupported", "", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final SocketAdapter buildIfSupported() {
            return this.isSupported() ? new Android10SocketAdapter() : null;
        }

        public final boolean isSupported() {
            return Platform.Companion.isAndroid() && Build.VERSION.SDK_INT >= 29;
        }
    }

    public static final Companion Companion;

    static {
        Android10SocketAdapter.Companion = new Companion(null);
    }

    @Override  // okhttp3.internal.platform.android.SocketAdapter
    public void configureTlsExtensions(SSLSocket sSLSocket0, String s, List list0) {
        Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
        Intrinsics.checkNotNullParameter(list0, "protocols");
        try {
            Platform..ExternalSyntheticApiModelOutline0.m(sSLSocket0, true);
            SSLParameters sSLParameters0 = sSLSocket0.getSSLParameters();
            Object[] arr_object = Platform.Companion.alpnProtocolNames(list0).toArray(new String[0]);
            Intrinsics.checkNotNull(arr_object, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            Platform..ExternalSyntheticApiModelOutline0.m(sSLParameters0, ((String[])arr_object));
            sSLSocket0.setSSLParameters(sSLParameters0);
        }
        catch(IllegalArgumentException illegalArgumentException0) {
            throw new IOException("Android internal error", illegalArgumentException0);
        }
    }

    @Override  // okhttp3.internal.platform.android.SocketAdapter
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

    @Override  // okhttp3.internal.platform.android.SocketAdapter
    public boolean isSupported() {
        return Android10SocketAdapter.Companion.isSupported();
    }

    @Override  // okhttp3.internal.platform.android.SocketAdapter
    public boolean matchesSocket(SSLSocket sSLSocket0) {
        Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
        return Platform..ExternalSyntheticApiModelOutline0.m(sSLSocket0);
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

