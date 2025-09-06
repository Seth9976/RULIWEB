package okhttp3;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy.Type;
import java.net.Proxy;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.internal._HostnamesJvmKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000E\n\u0000\u0018\u00002\u00020\u0001B\u001D\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007¢\u0006\u0002\u0010\bJ\r\u0010\u0002\u001A\u00020\u0003H\u0007¢\u0006\u0002\b\fJ\u0013\u0010\r\u001A\u00020\u000E2\b\u0010\u000F\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0010\u001A\u00020\u0011H\u0016J\r\u0010\u0004\u001A\u00020\u0005H\u0007¢\u0006\u0002\b\u0012J\u0006\u0010\u0013\u001A\u00020\u000EJ\r\u0010\u0006\u001A\u00020\u0007H\u0007¢\u0006\u0002\b\u0014J\b\u0010\u0015\u001A\u00020\u0016H\u0016R\u0013\u0010\u0002\u001A\u00020\u00038\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0002\u0010\tR\u0013\u0010\u0004\u001A\u00020\u00058\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0004\u0010\nR\u0013\u0010\u0006\u001A\u00020\u00078\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0006\u0010\u000B¨\u0006\u0017"}, d2 = {"Lokhttp3/Route;", "", "address", "Lokhttp3/Address;", "proxy", "Ljava/net/Proxy;", "socketAddress", "Ljava/net/InetSocketAddress;", "(Lokhttp3/Address;Ljava/net/Proxy;Ljava/net/InetSocketAddress;)V", "()Lokhttp3/Address;", "()Ljava/net/Proxy;", "()Ljava/net/InetSocketAddress;", "-deprecated_address", "equals", "", "other", "hashCode", "", "-deprecated_proxy", "requiresTunnel", "-deprecated_socketAddress", "toString", "", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class Route {
    private final Address address;
    private final Proxy proxy;
    private final InetSocketAddress socketAddress;

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "address", imports = {}))
    public final Address -deprecated_address() {
        return this.address;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "proxy", imports = {}))
    public final Proxy -deprecated_proxy() {
        return this.proxy;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "socketAddress", imports = {}))
    public final InetSocketAddress -deprecated_socketAddress() {
        return this.socketAddress;
    }

    public Route(Address address0, Proxy proxy0, InetSocketAddress inetSocketAddress0) {
        Intrinsics.checkNotNullParameter(address0, "address");
        Intrinsics.checkNotNullParameter(proxy0, "proxy");
        Intrinsics.checkNotNullParameter(inetSocketAddress0, "socketAddress");
        super();
        this.address = address0;
        this.proxy = proxy0;
        this.socketAddress = inetSocketAddress0;
    }

    public final Address address() {
        return this.address;
    }

    // 去混淆评级： 低(40)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof Route && Intrinsics.areEqual(((Route)object0).address, this.address) && Intrinsics.areEqual(((Route)object0).proxy, this.proxy) && Intrinsics.areEqual(((Route)object0).socketAddress, this.socketAddress);
    }

    @Override
    public int hashCode() {
        return ((this.address.hashCode() + 0x20F) * 0x1F + this.proxy.hashCode()) * 0x1F + this.socketAddress.hashCode();
    }

    public final Proxy proxy() {
        return this.proxy;
    }

    public final boolean requiresTunnel() {
        return this.proxy.type() == Proxy.Type.HTTP ? this.address.sslSocketFactory() != null || this.address.protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE) : false;
    }

    public final InetSocketAddress socketAddress() {
        return this.socketAddress;
    }

    @Override
    public String toString() {
        String s2;
        StringBuilder stringBuilder0 = new StringBuilder();
        String s = this.address.url().host();
        InetAddress inetAddress0 = this.socketAddress.getAddress();
        if(inetAddress0 == null) {
            s2 = null;
        }
        else {
            String s1 = inetAddress0.getHostAddress();
            if(s1 == null) {
                s2 = null;
            }
            else {
                Intrinsics.checkNotNullExpressionValue(s1, "hostAddress");
                s2 = _HostnamesJvmKt.toCanonicalHost(s1);
            }
        }
        if(StringsKt.contains$default(s, ':', false, 2, null)) {
            stringBuilder0.append("[");
            stringBuilder0.append(s);
            stringBuilder0.append("]");
        }
        else {
            stringBuilder0.append(s);
        }
        if(this.address.url().port() != this.socketAddress.getPort() || Intrinsics.areEqual(s, s2)) {
            stringBuilder0.append(":");
            stringBuilder0.append(this.address.url().port());
        }
        if(!Intrinsics.areEqual(s, s2)) {
            if(Intrinsics.areEqual(this.proxy, Proxy.NO_PROXY)) {
                stringBuilder0.append(" at ");
            }
            else {
                stringBuilder0.append(" via proxy ");
            }
            if(s2 == null) {
                stringBuilder0.append("<unresolved>");
            }
            else if(StringsKt.contains$default(s2, ':', false, 2, null)) {
                stringBuilder0.append("[");
                stringBuilder0.append(s2);
                stringBuilder0.append("]");
            }
            else {
                stringBuilder0.append(s2);
            }
            stringBuilder0.append(":");
            stringBuilder0.append(this.socketAddress.getPort());
        }
        String s3 = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s3, "StringBuilder().apply(builderAction).toString()");
        return s3;
    }
}

