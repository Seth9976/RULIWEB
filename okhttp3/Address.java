package okhttp3;

import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import java.util.Objects;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal._UtilJvmKt;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000B\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\u000F\u0018\u00002\u00020\u0001By\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u0012\b\u0010\n\u001A\u0004\u0018\u00010\u000B\u0012\b\u0010\f\u001A\u0004\u0018\u00010\r\u0012\b\u0010\u000E\u001A\u0004\u0018\u00010\u000F\u0012\u0006\u0010\u0010\u001A\u00020\u0011\u0012\b\u0010\u0012\u001A\u0004\u0018\u00010\u0013\u0012\f\u0010\u0014\u001A\b\u0012\u0004\u0012\u00020\u00160\u0015\u0012\f\u0010\u0017\u001A\b\u0012\u0004\u0012\u00020\u00180\u0015\u0012\u0006\u0010\u0019\u001A\u00020\u001A\u00A2\u0006\u0002\u0010\u001BJ\u000F\u0010\u000E\u001A\u0004\u0018\u00010\u000FH\u0007\u00A2\u0006\u0002\b(J\u0013\u0010\u0017\u001A\b\u0012\u0004\u0012\u00020\u00180\u0015H\u0007\u00A2\u0006\u0002\b)J\r\u0010\u0006\u001A\u00020\u0007H\u0007\u00A2\u0006\u0002\b*J\u0013\u0010+\u001A\u00020,2\b\u0010-\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\u0015\u0010.\u001A\u00020,2\u0006\u0010/\u001A\u00020\u0000H\u0000\u00A2\u0006\u0002\b0J\b\u00101\u001A\u00020\u0005H\u0016J\u000F\u0010\f\u001A\u0004\u0018\u00010\rH\u0007\u00A2\u0006\u0002\b2J\u0013\u0010\u0014\u001A\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0007\u00A2\u0006\u0002\b3J\u000F\u0010\u0012\u001A\u0004\u0018\u00010\u0013H\u0007\u00A2\u0006\u0002\b4J\r\u0010\u0010\u001A\u00020\u0011H\u0007\u00A2\u0006\u0002\b5J\r\u0010\u0019\u001A\u00020\u001AH\u0007\u00A2\u0006\u0002\b6J\r\u0010\b\u001A\u00020\tH\u0007\u00A2\u0006\u0002\b7J\u000F\u0010\n\u001A\u0004\u0018\u00010\u000BH\u0007\u00A2\u0006\u0002\b8J\b\u00109\u001A\u00020\u0003H\u0016J\r\u0010%\u001A\u00020&H\u0007\u00A2\u0006\u0002\b:R\u0015\u0010\u000E\u001A\u0004\u0018\u00010\u000F8\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\u001CR\u0019\u0010\u0017\u001A\b\u0012\u0004\u0012\u00020\u00180\u00158G\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0017\u0010\u001DR\u0013\u0010\u0006\u001A\u00020\u00078\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0006\u0010\u001ER\u0015\u0010\f\u001A\u0004\u0018\u00010\r8\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\f\u0010\u001FR\u0019\u0010\u0014\u001A\b\u0012\u0004\u0012\u00020\u00160\u00158G\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0014\u0010\u001DR\u0015\u0010\u0012\u001A\u0004\u0018\u00010\u00138\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0012\u0010 R\u0013\u0010\u0010\u001A\u00020\u00118\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0010\u0010!R\u0013\u0010\u0019\u001A\u00020\u001A8\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0019\u0010\"R\u0013\u0010\b\u001A\u00020\t8\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\b\u0010#R\u0015\u0010\n\u001A\u0004\u0018\u00010\u000B8\u0007\u00A2\u0006\b\n\u0000\u001A\u0004\b\n\u0010$R\u0013\u0010%\u001A\u00020&8G\u00A2\u0006\b\n\u0000\u001A\u0004\b%\u0010\'\u00A8\u0006;"}, d2 = {"Lokhttp3/Address;", "", "uriHost", "", "uriPort", "", "dns", "Lokhttp3/Dns;", "socketFactory", "Ljavax/net/SocketFactory;", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "hostnameVerifier", "Ljavax/net/ssl/HostnameVerifier;", "certificatePinner", "Lokhttp3/CertificatePinner;", "proxyAuthenticator", "Lokhttp3/Authenticator;", "proxy", "Ljava/net/Proxy;", "protocols", "", "Lokhttp3/Protocol;", "connectionSpecs", "Lokhttp3/ConnectionSpec;", "proxySelector", "Ljava/net/ProxySelector;", "(Ljava/lang/String;ILokhttp3/Dns;Ljavax/net/SocketFactory;Ljavax/net/ssl/SSLSocketFactory;Ljavax/net/ssl/HostnameVerifier;Lokhttp3/CertificatePinner;Lokhttp3/Authenticator;Ljava/net/Proxy;Ljava/util/List;Ljava/util/List;Ljava/net/ProxySelector;)V", "()Lokhttp3/CertificatePinner;", "()Ljava/util/List;", "()Lokhttp3/Dns;", "()Ljavax/net/ssl/HostnameVerifier;", "()Ljava/net/Proxy;", "()Lokhttp3/Authenticator;", "()Ljava/net/ProxySelector;", "()Ljavax/net/SocketFactory;", "()Ljavax/net/ssl/SSLSocketFactory;", "url", "Lokhttp3/HttpUrl;", "()Lokhttp3/HttpUrl;", "-deprecated_certificatePinner", "-deprecated_connectionSpecs", "-deprecated_dns", "equals", "", "other", "equalsNonHost", "that", "equalsNonHost$okhttp", "hashCode", "-deprecated_hostnameVerifier", "-deprecated_protocols", "-deprecated_proxy", "-deprecated_proxyAuthenticator", "-deprecated_proxySelector", "-deprecated_socketFactory", "-deprecated_sslSocketFactory", "toString", "-deprecated_url", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class Address {
    private final CertificatePinner certificatePinner;
    private final List connectionSpecs;
    private final Dns dns;
    private final HostnameVerifier hostnameVerifier;
    private final List protocols;
    private final Proxy proxy;
    private final Authenticator proxyAuthenticator;
    private final ProxySelector proxySelector;
    private final SocketFactory socketFactory;
    private final SSLSocketFactory sslSocketFactory;
    private final HttpUrl url;

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "certificatePinner", imports = {}))
    public final CertificatePinner -deprecated_certificatePinner() {
        return this.certificatePinner;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "connectionSpecs", imports = {}))
    public final List -deprecated_connectionSpecs() {
        return this.connectionSpecs;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "dns", imports = {}))
    public final Dns -deprecated_dns() {
        return this.dns;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "hostnameVerifier", imports = {}))
    public final HostnameVerifier -deprecated_hostnameVerifier() {
        return this.hostnameVerifier;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "protocols", imports = {}))
    public final List -deprecated_protocols() {
        return this.protocols;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "proxy", imports = {}))
    public final Proxy -deprecated_proxy() {
        return this.proxy;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "proxyAuthenticator", imports = {}))
    public final Authenticator -deprecated_proxyAuthenticator() {
        return this.proxyAuthenticator;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "proxySelector", imports = {}))
    public final ProxySelector -deprecated_proxySelector() {
        return this.proxySelector;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "socketFactory", imports = {}))
    public final SocketFactory -deprecated_socketFactory() {
        return this.socketFactory;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "sslSocketFactory", imports = {}))
    public final SSLSocketFactory -deprecated_sslSocketFactory() {
        return this.sslSocketFactory;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "url", imports = {}))
    public final HttpUrl -deprecated_url() {
        return this.url;
    }

    public Address(String s, int v, Dns dns0, SocketFactory socketFactory0, SSLSocketFactory sSLSocketFactory0, HostnameVerifier hostnameVerifier0, CertificatePinner certificatePinner0, Authenticator authenticator0, Proxy proxy0, List list0, List list1, ProxySelector proxySelector0) {
        Intrinsics.checkNotNullParameter(s, "uriHost");
        Intrinsics.checkNotNullParameter(dns0, "dns");
        Intrinsics.checkNotNullParameter(socketFactory0, "socketFactory");
        Intrinsics.checkNotNullParameter(authenticator0, "proxyAuthenticator");
        Intrinsics.checkNotNullParameter(list0, "protocols");
        Intrinsics.checkNotNullParameter(list1, "connectionSpecs");
        Intrinsics.checkNotNullParameter(proxySelector0, "proxySelector");
        super();
        this.dns = dns0;
        this.socketFactory = socketFactory0;
        this.sslSocketFactory = sSLSocketFactory0;
        this.hostnameVerifier = hostnameVerifier0;
        this.certificatePinner = certificatePinner0;
        this.proxyAuthenticator = authenticator0;
        this.proxy = proxy0;
        this.proxySelector = proxySelector0;
        this.url = new Builder().scheme((sSLSocketFactory0 == null ? "http" : "https")).host(s).port(v).build();
        this.protocols = _UtilJvmKt.toImmutableList(list0);
        this.connectionSpecs = _UtilJvmKt.toImmutableList(list1);
    }

    public final CertificatePinner certificatePinner() {
        return this.certificatePinner;
    }

    public final List connectionSpecs() {
        return this.connectionSpecs;
    }

    public final Dns dns() {
        return this.dns;
    }

    // 去混淆评级： 低(30)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof Address && Intrinsics.areEqual(this.url, ((Address)object0).url) && this.equalsNonHost$okhttp(((Address)object0));
    }

    // 去混淆评级： 低(45)
    public final boolean equalsNonHost$okhttp(Address address0) {
        Intrinsics.checkNotNullParameter(address0, "that");
        return Intrinsics.areEqual(this.dns, address0.dns) && Intrinsics.areEqual(this.proxyAuthenticator, address0.proxyAuthenticator) && Intrinsics.areEqual(this.protocols, address0.protocols) && Intrinsics.areEqual(this.connectionSpecs, address0.connectionSpecs) && Intrinsics.areEqual(this.proxySelector, address0.proxySelector) && Intrinsics.areEqual(this.proxy, address0.proxy) && Intrinsics.areEqual(this.sslSocketFactory, address0.sslSocketFactory) && Intrinsics.areEqual(this.hostnameVerifier, address0.hostnameVerifier) && Intrinsics.areEqual(this.certificatePinner, address0.certificatePinner) && this.url.port() == address0.url.port();
    }

    @Override
    public int hashCode() {
        int v = Objects.hashCode(this.proxy);
        int v1 = Objects.hashCode(this.sslSocketFactory);
        int v2 = Objects.hashCode(this.hostnameVerifier);
        int v3 = Objects.hashCode(this.certificatePinner);
        return (((((((((this.url.hashCode() + 0x20F) * 0x1F + this.dns.hashCode()) * 0x1F + this.proxyAuthenticator.hashCode()) * 0x1F + this.protocols.hashCode()) * 0x1F + this.connectionSpecs.hashCode()) * 0x1F + this.proxySelector.hashCode()) * 0x1F + v) * 0x1F + v1) * 0x1F + v2) * 0x1F + v3;
    }

    public final HostnameVerifier hostnameVerifier() {
        return this.hostnameVerifier;
    }

    public final List protocols() {
        return this.protocols;
    }

    public final Proxy proxy() {
        return this.proxy;
    }

    public final Authenticator proxyAuthenticator() {
        return this.proxyAuthenticator;
    }

    public final ProxySelector proxySelector() {
        return this.proxySelector;
    }

    public final SocketFactory socketFactory() {
        return this.socketFactory;
    }

    public final SSLSocketFactory sslSocketFactory() {
        return this.sslSocketFactory;
    }

    @Override
    public String toString() {
        Proxy proxy0;
        StringBuilder stringBuilder1;
        StringBuilder stringBuilder0 = new StringBuilder("Address{");
        stringBuilder0.append(this.url.host());
        stringBuilder0.append(':');
        stringBuilder0.append(this.url.port());
        stringBuilder0.append(", ");
        if(this.proxy == null) {
            stringBuilder1 = new StringBuilder("proxySelector=");
            proxy0 = this.proxySelector;
        }
        else {
            stringBuilder1 = new StringBuilder("proxy=");
            proxy0 = this.proxy;
        }
        stringBuilder1.append(proxy0);
        stringBuilder0.append(stringBuilder1.toString());
        stringBuilder0.append('}');
        return stringBuilder0.toString();
    }

    public final HttpUrl url() {
        return this.url;
    }
}

