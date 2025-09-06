package okhttp3.internal.connection;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.security.cert.CertificateException;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001A\u0012\u0010\u0000\u001A\u00020\u00012\n\u0010\u0002\u001A\u00060\u0003j\u0002`\u0004¨\u0006\u0005"}, d2 = {"retryTlsHandshake", "", "e", "Ljava/io/IOException;", "Lokio/IOException;", "okhttp"}, k = 2, mv = {1, 7, 1}, xi = 0x30)
public final class TlsHandshakeKt {
    public static final boolean retryTlsHandshake(IOException iOException0) {
        Intrinsics.checkNotNullParameter(iOException0, "e");
        if(iOException0 instanceof ProtocolException) {
            return false;
        }
        if(iOException0 instanceof InterruptedIOException) {
            return false;
        }
        if(iOException0 instanceof SSLHandshakeException && iOException0.getCause() instanceof CertificateException) {
            return false;
        }
        return iOException0 instanceof SSLPeerUnverifiedException ? false : iOException0 instanceof SSLException;
    }
}

