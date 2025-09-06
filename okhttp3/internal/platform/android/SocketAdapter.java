package okhttp3.internal.platform.android;

import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\b\u0010\u0006\u001A\u0004\u0018\u00010\u00072\f\u0010\b\u001A\b\u0012\u0004\u0012\u00020\n0\tH&J\u0012\u0010\u000B\u001A\u0004\u0018\u00010\u00072\u0006\u0010\u0004\u001A\u00020\u0005H&J\b\u0010\f\u001A\u00020\rH&J\u0010\u0010\u000E\u001A\u00020\r2\u0006\u0010\u0004\u001A\u00020\u0005H&J\u0010\u0010\u000F\u001A\u00020\r2\u0006\u0010\u0010\u001A\u00020\u0011H\u0016J\u0012\u0010\u0012\u001A\u0004\u0018\u00010\u00132\u0006\u0010\u0010\u001A\u00020\u0011H\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0014À\u0006\u0001"}, d2 = {"Lokhttp3/internal/platform/android/SocketAdapter;", "", "configureTlsExtensions", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "getSelectedProtocol", "isSupported", "", "matchesSocket", "matchesSocketFactory", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public interface SocketAdapter {
    void configureTlsExtensions(SSLSocket arg1, String arg2, List arg3);

    String getSelectedProtocol(SSLSocket arg1);

    boolean isSupported();

    boolean matchesSocket(SSLSocket arg1);

    boolean matchesSocketFactory(SSLSocketFactory arg1);

    X509TrustManager trustManager(SSLSocketFactory arg1);
}

