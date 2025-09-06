package okhttp3.internal.connection;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy.Type;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.Route;
import okhttp3.internal._HostnamesCommonKt;
import okhttp3.internal._UtilJvmKt;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \"2\u00020\u0001:\u0002\"#B-\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u0012\u0006\u0010\n\u001A\u00020\u000B¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001A\u00020\tH\u0086\u0002J\b\u0010\u0018\u001A\u00020\tH\u0002J\t\u0010\u0019\u001A\u00020\u001AH\u0086\u0002J\b\u0010\u001B\u001A\u00020\u0016H\u0002J\u0010\u0010\u001C\u001A\u00020\u001D2\u0006\u0010\u001E\u001A\u00020\u0016H\u0002J\u001A\u0010\u001F\u001A\u00020\u001D2\u0006\u0010 \u001A\u00020!2\b\u0010\u001E\u001A\u0004\u0018\u00010\u0016H\u0002R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u000BX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001A\b\u0012\u0004\u0012\u00020\u000F0\u000EX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0010\u001A\u00020\u0011X\u0082\u000E¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001A\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001A\b\u0012\u0004\u0012\u00020\u00160\u000EX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lokhttp3/internal/connection/RouteSelector;", "", "address", "Lokhttp3/Address;", "routeDatabase", "Lokhttp3/internal/connection/RouteDatabase;", "call", "Lokhttp3/Call;", "fastFallback", "", "eventListener", "Lokhttp3/EventListener;", "(Lokhttp3/Address;Lokhttp3/internal/connection/RouteDatabase;Lokhttp3/Call;ZLokhttp3/EventListener;)V", "inetSocketAddresses", "", "Ljava/net/InetSocketAddress;", "nextProxyIndex", "", "postponedRoutes", "", "Lokhttp3/Route;", "proxies", "Ljava/net/Proxy;", "hasNext", "hasNextProxy", "next", "Lokhttp3/internal/connection/RouteSelector$Selection;", "nextProxy", "resetNextInetSocketAddress", "", "proxy", "resetNextProxy", "url", "Lokhttp3/HttpUrl;", "Companion", "Selection", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class RouteSelector {
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0015\u0010\u0003\u001A\u00020\u0004*\u00020\u00058F¢\u0006\u0006\u001A\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lokhttp3/internal/connection/RouteSelector$Companion;", "", "()V", "socketHost", "", "Ljava/net/InetSocketAddress;", "getSocketHost", "(Ljava/net/InetSocketAddress;)Ljava/lang/String;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final String getSocketHost(InetSocketAddress inetSocketAddress0) {
            Intrinsics.checkNotNullParameter(inetSocketAddress0, "<this>");
            InetAddress inetAddress0 = inetSocketAddress0.getAddress();
            if(inetAddress0 == null) {
                String s = inetSocketAddress0.getHostName();
                Intrinsics.checkNotNullExpressionValue(s, "hostName");
                return s;
            }
            String s1 = inetAddress0.getHostAddress();
            Intrinsics.checkNotNullExpressionValue(s1, "address.hostAddress");
            return s1;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\n\u001A\u00020\u000BH\u0086\u0002J\t\u0010\f\u001A\u00020\u0004H\u0086\u0002R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u000E¢\u0006\u0002\n\u0000R\u0017\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\t¨\u0006\r"}, d2 = {"Lokhttp3/internal/connection/RouteSelector$Selection;", "", "routes", "", "Lokhttp3/Route;", "(Ljava/util/List;)V", "nextRouteIndex", "", "getRoutes", "()Ljava/util/List;", "hasNext", "", "next", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public static final class Selection {
        private int nextRouteIndex;
        private final List routes;

        public Selection(List list0) {
            Intrinsics.checkNotNullParameter(list0, "routes");
            super();
            this.routes = list0;
        }

        public final List getRoutes() {
            return this.routes;
        }

        public final boolean hasNext() {
            return this.nextRouteIndex < this.routes.size();
        }

        public final Route next() {
            if(!this.hasNext()) {
                throw new NoSuchElementException();
            }
            int v = this.nextRouteIndex;
            this.nextRouteIndex = v + 1;
            return (Route)this.routes.get(v);
        }
    }

    public static final Companion Companion;
    private final Address address;
    private final Call call;
    private final EventListener eventListener;
    private final boolean fastFallback;
    private List inetSocketAddresses;
    private int nextProxyIndex;
    private final List postponedRoutes;
    private List proxies;
    private final RouteDatabase routeDatabase;

    static {
        RouteSelector.Companion = new Companion(null);
    }

    public RouteSelector(Address address0, RouteDatabase routeDatabase0, Call call0, boolean z, EventListener eventListener0) {
        Intrinsics.checkNotNullParameter(address0, "address");
        Intrinsics.checkNotNullParameter(routeDatabase0, "routeDatabase");
        Intrinsics.checkNotNullParameter(call0, "call");
        Intrinsics.checkNotNullParameter(eventListener0, "eventListener");
        super();
        this.address = address0;
        this.routeDatabase = routeDatabase0;
        this.call = call0;
        this.fastFallback = z;
        this.eventListener = eventListener0;
        this.proxies = CollectionsKt.emptyList();
        this.inetSocketAddresses = CollectionsKt.emptyList();
        this.postponedRoutes = new ArrayList();
        this.resetNextProxy(address0.url(), address0.proxy());
    }

    // 去混淆评级： 低(20)
    public final boolean hasNext() {
        return this.hasNextProxy() || !this.postponedRoutes.isEmpty();
    }

    private final boolean hasNextProxy() {
        return this.nextProxyIndex < this.proxies.size();
    }

    public final Selection next() throws IOException {
        if(!this.hasNext()) {
            throw new NoSuchElementException();
        }
        List list0 = new ArrayList();
        while(this.hasNextProxy()) {
            Proxy proxy0 = this.nextProxy();
            for(Object object0: this.inetSocketAddresses) {
                Route route0 = new Route(this.address, proxy0, ((InetSocketAddress)object0));
                if(this.routeDatabase.shouldPostpone(route0)) {
                    this.postponedRoutes.add(route0);
                }
                else {
                    list0.add(route0);
                }
            }
            if(!list0.isEmpty()) {
                break;
            }
        }
        if(list0.isEmpty()) {
            CollectionsKt.addAll(list0, this.postponedRoutes);
            this.postponedRoutes.clear();
        }
        return new Selection(list0);
    }

    private final Proxy nextProxy() throws IOException {
        if(!this.hasNextProxy()) {
            throw new SocketException("No route to " + this.address.url().host() + "; exhausted proxy configurations: " + this.proxies);
        }
        int v = this.nextProxyIndex;
        this.nextProxyIndex = v + 1;
        Proxy proxy0 = (Proxy)this.proxies.get(v);
        this.resetNextInetSocketAddress(proxy0);
        return proxy0;
    }

    private final void resetNextInetSocketAddress(Proxy proxy0) throws IOException {
        List list1;
        int v;
        String s;
        List list0 = new ArrayList();
        this.inetSocketAddresses = list0;
        if(proxy0.type() == Proxy.Type.DIRECT || proxy0.type() == Proxy.Type.SOCKS) {
            s = this.address.url().host();
            v = this.address.url().port();
        }
        else {
            SocketAddress socketAddress0 = proxy0.address();
            if(!(socketAddress0 instanceof InetSocketAddress)) {
                throw new IllegalArgumentException(("Proxy.address() is not an InetSocketAddress: " + socketAddress0.getClass()).toString());
            }
            Intrinsics.checkNotNullExpressionValue(socketAddress0, "proxyAddress");
            s = RouteSelector.Companion.getSocketHost(((InetSocketAddress)socketAddress0));
            v = ((InetSocketAddress)socketAddress0).getPort();
        }
        if(1 > v || v >= 0x10000) {
            throw new SocketException("No route to " + s + ':' + v + "; port is out of range");
        }
        if(proxy0.type() == Proxy.Type.SOCKS) {
            list0.add(InetSocketAddress.createUnresolved(s, v));
            return;
        }
        if(_HostnamesCommonKt.canParseAsIpAddress(s)) {
            list1 = CollectionsKt.listOf(InetAddress.getByName(s));
        }
        else {
            this.eventListener.dnsStart(this.call, s);
            list1 = this.address.dns().lookup(s);
            if(list1.isEmpty()) {
                throw new UnknownHostException(this.address.dns() + " returned no addresses for " + s);
            }
            this.eventListener.dnsEnd(this.call, s, list1);
        }
        if(this.fastFallback) {
            list1 = InetAddressOrderKt.reorderForHappyEyeballs(list1);
        }
        for(Object object0: list1) {
            list0.add(new InetSocketAddress(((InetAddress)object0), v));
        }
    }

    private final void resetNextProxy(HttpUrl httpUrl0, Proxy proxy0) {
        this.eventListener.proxySelectStart(this.call, httpUrl0);
        List list0 = RouteSelector.resetNextProxy$selectProxies(proxy0, httpUrl0, this);
        this.proxies = list0;
        this.nextProxyIndex = 0;
        this.eventListener.proxySelectEnd(this.call, httpUrl0, list0);
    }

    private static final List resetNextProxy$selectProxies(Proxy proxy0, HttpUrl httpUrl0, RouteSelector routeSelector0) {
        if(proxy0 != null) {
            return CollectionsKt.listOf(proxy0);
        }
        URI uRI0 = httpUrl0.uri();
        if(uRI0.getHost() == null) {
            return _UtilJvmKt.immutableListOf(new Proxy[]{Proxy.NO_PROXY});
        }
        List list0 = routeSelector0.address.proxySelector().select(uRI0);
        if(list0 != null && !list0.isEmpty()) {
            Intrinsics.checkNotNullExpressionValue(list0, "proxiesOrNull");
            return _UtilJvmKt.toImmutableList(list0);
        }
        return _UtilJvmKt.immutableListOf(new Proxy[]{Proxy.NO_PROXY});
    }
}

