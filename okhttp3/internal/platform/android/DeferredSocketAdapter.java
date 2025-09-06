package okhttp3.internal.platform.android;

import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0014B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J(\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\t2\b\u0010\n\u001A\u0004\u0018\u00010\u000B2\f\u0010\f\u001A\b\u0012\u0004\u0012\u00020\u000E0\rH\u0016J\u0012\u0010\u000F\u001A\u0004\u0018\u00010\u00012\u0006\u0010\b\u001A\u00020\tH\u0002J\u0012\u0010\u0010\u001A\u0004\u0018\u00010\u000B2\u0006\u0010\b\u001A\u00020\tH\u0016J\b\u0010\u0011\u001A\u00020\u0012H\u0016J\u0010\u0010\u0013\u001A\u00020\u00122\u0006\u0010\b\u001A\u00020\tH\u0016R\u0010\u0010\u0005\u001A\u0004\u0018\u00010\u0001X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lokhttp3/internal/platform/android/DeferredSocketAdapter;", "Lokhttp3/internal/platform/android/SocketAdapter;", "socketAdapterFactory", "Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "(Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;)V", "delegate", "configureTlsExtensions", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "getDelegate", "getSelectedProtocol", "isSupported", "", "matchesSocket", "Factory", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class DeferredSocketAdapter implements SocketAdapter {
    @Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H&J\u0010\u0010\u0006\u001A\u00020\u00072\u0006\u0010\u0004\u001A\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Lokhttp3/internal/platform/android/DeferredSocketAdapter$Factory;", "", "create", "Lokhttp3/internal/platform/android/SocketAdapter;", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "matchesSocket", "", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public interface Factory {
        SocketAdapter create(SSLSocket arg1);

        boolean matchesSocket(SSLSocket arg1);
    }

    private SocketAdapter delegate;
    private final Factory socketAdapterFactory;

    public DeferredSocketAdapter(Factory deferredSocketAdapter$Factory0) {
        Intrinsics.checkNotNullParameter(deferredSocketAdapter$Factory0, "socketAdapterFactory");
        super();
        this.socketAdapterFactory = deferredSocketAdapter$Factory0;
    }

    @Override  // okhttp3.internal.platform.android.SocketAdapter
    public void configureTlsExtensions(SSLSocket sSLSocket0, String s, List list0) {
        Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
        Intrinsics.checkNotNullParameter(list0, "protocols");
        SocketAdapter socketAdapter0 = this.getDelegate(sSLSocket0);
        if(socketAdapter0 != null) {
            socketAdapter0.configureTlsExtensions(sSLSocket0, s, list0);
        }
    }

    private final SocketAdapter getDelegate(SSLSocket sSLSocket0) {
        synchronized(this) {
            if(this.delegate == null && this.socketAdapterFactory.matchesSocket(sSLSocket0)) {
                this.delegate = this.socketAdapterFactory.create(sSLSocket0);
            }
            return this.delegate;
        }
    }

    @Override  // okhttp3.internal.platform.android.SocketAdapter
    public String getSelectedProtocol(SSLSocket sSLSocket0) {
        Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
        SocketAdapter socketAdapter0 = this.getDelegate(sSLSocket0);
        return socketAdapter0 == null ? null : socketAdapter0.getSelectedProtocol(sSLSocket0);
    }

    @Override  // okhttp3.internal.platform.android.SocketAdapter
    public boolean isSupported() {
        return true;
    }

    @Override  // okhttp3.internal.platform.android.SocketAdapter
    public boolean matchesSocket(SSLSocket sSLSocket0) {
        Intrinsics.checkNotNullParameter(sSLSocket0, "sslSocket");
        return this.socketAdapterFactory.matchesSocket(sSLSocket0);
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

