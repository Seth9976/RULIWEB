package okhttp3.internal.platform.android;

import javax.net.ssl.SSLSocket;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.platform.BouncyCastlePlatform;
import org.bouncycastle.jsse.BCSSLSocket;

@Metadata(d1 = {"\u0000\u001D\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H\u0016J\u0010\u0010\u0006\u001A\u00020\u00072\u0006\u0010\u0004\u001A\u00020\u0005H\u0016¨\u0006\b"}, d2 = {"okhttp3/internal/platform/android/BouncyCastleSocketAdapter$Companion$factory$1", "Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "create", "Lokhttp3/internal/platform/android/SocketAdapter;", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "matchesSocket", "", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class BouncyCastleSocketAdapter.Companion.factory.1 implements Factory {
    @Override  // okhttp3.internal.platform.android.DeferredSocketAdapter$Factory
    public SocketAdapter create(SSLSocket sSLSocket0) {
        Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
        return new BouncyCastleSocketAdapter();
    }

    @Override  // okhttp3.internal.platform.android.DeferredSocketAdapter$Factory
    public boolean matchesSocket(SSLSocket sSLSocket0) {
        Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
        return BouncyCastlePlatform.Companion.isSupported() && sSLSocket0 instanceof BCSSLSocket;
    }
}

