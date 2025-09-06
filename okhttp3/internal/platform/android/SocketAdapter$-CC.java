package okhttp3.internal.platform.android;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.jvm.internal.Intrinsics;

public final class SocketAdapter.-CC {
    public static boolean $default$matchesSocketFactory(SocketAdapter _this, SSLSocketFactory sSLSocketFactory0) {
        Intrinsics.checkNotNullParameter(sSLSocketFactory0, "sslSocketFactory");
        return false;
    }

    public static X509TrustManager $default$trustManager(SocketAdapter _this, SSLSocketFactory sSLSocketFactory0) {
        Intrinsics.checkNotNullParameter(sSLSocketFactory0, "sslSocketFactory");
        return null;
    }
}

