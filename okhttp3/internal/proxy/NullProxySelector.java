package okhttp3.internal.proxy;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001A\u00020\u00042\b\u0010\u0005\u001A\u0004\u0018\u00010\u00062\b\u0010\u0007\u001A\u0004\u0018\u00010\b2\b\u0010\t\u001A\u0004\u0018\u00010\nH\u0016J\u0018\u0010\u000B\u001A\b\u0012\u0004\u0012\u00020\r0\f2\b\u0010\u0005\u001A\u0004\u0018\u00010\u0006H\u0016¨\u0006\u000E"}, d2 = {"Lokhttp3/internal/proxy/NullProxySelector;", "Ljava/net/ProxySelector;", "()V", "connectFailed", "", "uri", "Ljava/net/URI;", "sa", "Ljava/net/SocketAddress;", "ioe", "Ljava/io/IOException;", "select", "", "Ljava/net/Proxy;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class NullProxySelector extends ProxySelector {
    public static final NullProxySelector INSTANCE;

    static {
        NullProxySelector.INSTANCE = new NullProxySelector();
    }

    @Override
    public void connectFailed(URI uRI0, SocketAddress socketAddress0, IOException iOException0) {
    }

    @Override
    public List select(URI uRI0) {
        if(uRI0 == null) {
            throw new IllegalArgumentException("uri must not be null");
        }
        return CollectionsKt.listOf(Proxy.NO_PROXY);
    }
}

