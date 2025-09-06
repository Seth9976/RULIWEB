package okhttp3.internal.connection;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownServiceException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.ConnectionSpec;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request.Builder;
import okhttp3.Request;
import okhttp3.Route;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.platform.Platform;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t\u00A2\u0006\u0002\u0010\nJ\u0010\u0010\u001A\u001A\u00020\u001B2\u0006\u0010\u001C\u001A\u00020\u0015H\u0002J\u0012\u0010\u001D\u001A\u00020\u00132\b\u0010\u001E\u001A\u0004\u0018\u00010\u001FH\u0016J\b\u0010 \u001A\u00020\u0013H\u0016J\b\u0010!\u001A\u00020\u000FH\u0016J\b\u0010\"\u001A\u00020#H\u0002J\'\u0010$\u001A\u00020#2\u0006\u0010\u001C\u001A\u00020\u00152\u0010\b\u0002\u0010%\u001A\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010&H\u0000\u00A2\u0006\u0002\b\'J\n\u0010(\u001A\u0004\u0018\u00010)H\u0002J-\u0010*\u001A\u0004\u0018\u00010)2\n\b\u0002\u0010+\u001A\u0004\u0018\u00010#2\u0010\b\u0002\u0010%\u001A\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010&H\u0000\u00A2\u0006\u0002\b,J\u0012\u0010-\u001A\u0004\u0018\u00010\u00152\u0006\u0010.\u001A\u00020\u001FH\u0002J\u0010\u0010/\u001A\u00020\u00132\u0006\u00100\u001A\u000201H\u0016R\u0014\u0010\u0004\u001A\u00020\u0005X\u0096\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001A\u0010\r\u001A\b\u0012\u0004\u0012\u00020\u000F0\u000EX\u0096\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0010\u0010\u0011R\u000E\u0010\u0012\u001A\u00020\u0013X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001A\u0004\u0018\u00010\u0015X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001A\u0004\u0018\u00010\u0017X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001A\u0004\u0018\u00010\u0019X\u0082\u000E\u00A2\u0006\u0002\n\u0000\u00A8\u00062"}, d2 = {"Lokhttp3/internal/connection/RealRoutePlanner;", "Lokhttp3/internal/connection/RoutePlanner;", "client", "Lokhttp3/OkHttpClient;", "address", "Lokhttp3/Address;", "call", "Lokhttp3/internal/connection/RealCall;", "chain", "Lokhttp3/internal/http/RealInterceptorChain;", "(Lokhttp3/OkHttpClient;Lokhttp3/Address;Lokhttp3/internal/connection/RealCall;Lokhttp3/internal/http/RealInterceptorChain;)V", "getAddress", "()Lokhttp3/Address;", "deferredPlans", "Lkotlin/collections/ArrayDeque;", "Lokhttp3/internal/connection/RoutePlanner$Plan;", "getDeferredPlans", "()Lkotlin/collections/ArrayDeque;", "doExtensiveHealthChecks", "", "nextRouteToTry", "Lokhttp3/Route;", "routeSelection", "Lokhttp3/internal/connection/RouteSelector$Selection;", "routeSelector", "Lokhttp3/internal/connection/RouteSelector;", "createTunnelRequest", "Lokhttp3/Request;", "route", "hasNext", "failedConnection", "Lokhttp3/internal/connection/RealConnection;", "isCanceled", "plan", "planConnect", "Lokhttp3/internal/connection/ConnectPlan;", "planConnectToRoute", "routes", "", "planConnectToRoute$okhttp", "planReuseCallConnection", "Lokhttp3/internal/connection/ReusePlan;", "planReusePooledConnection", "planToReplace", "planReusePooledConnection$okhttp", "retryRoute", "connection", "sameHostAndPort", "url", "Lokhttp3/HttpUrl;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class RealRoutePlanner implements RoutePlanner {
    private final Address address;
    private final RealCall call;
    private final OkHttpClient client;
    private final ArrayDeque deferredPlans;
    private final boolean doExtensiveHealthChecks;
    private Route nextRouteToTry;
    private Selection routeSelection;
    private RouteSelector routeSelector;

    public RealRoutePlanner(OkHttpClient okHttpClient0, Address address0, RealCall realCall0, RealInterceptorChain realInterceptorChain0) {
        Intrinsics.checkNotNullParameter(okHttpClient0, "client");
        Intrinsics.checkNotNullParameter(address0, "address");
        Intrinsics.checkNotNullParameter(realCall0, "call");
        Intrinsics.checkNotNullParameter(realInterceptorChain0, "chain");
        super();
        this.client = okHttpClient0;
        this.address = address0;
        this.call = realCall0;
        this.doExtensiveHealthChecks = !Intrinsics.areEqual(realInterceptorChain0.getRequest$okhttp().method(), "GET");
        this.deferredPlans = new ArrayDeque();
    }

    private final Request createTunnelRequest(Route route0) throws IOException {
        Request request0 = new Builder().url(route0.address().url()).method("CONNECT", null).header("Host", _UtilJvmKt.toHostHeader(route0.address().url(), true)).header("Proxy-Connection", "Keep-Alive").header("User-Agent", "okhttp/5.0.0-alpha.11").build();
        Request request1 = route0.address().proxyAuthenticator().authenticate(route0, new okhttp3.Response.Builder().request(request0).protocol(Protocol.HTTP_1_1).code(407).message("Preemptive Authenticate").sentRequestAtMillis(-1L).receivedResponseAtMillis(-1L).header("Proxy-Authenticate", "OkHttp-Preemptive").build());
        return request1 == null ? request0 : request1;
    }

    @Override  // okhttp3.internal.connection.RoutePlanner
    public Address getAddress() {
        return this.address;
    }

    @Override  // okhttp3.internal.connection.RoutePlanner
    public ArrayDeque getDeferredPlans() {
        return this.deferredPlans;
    }

    @Override  // okhttp3.internal.connection.RoutePlanner
    public boolean hasNext(RealConnection realConnection0) {
        if(!this.getDeferredPlans().isEmpty()) {
            return true;
        }
        if(this.nextRouteToTry != null) {
            return true;
        }
        if(realConnection0 != null) {
            Route route0 = this.retryRoute(realConnection0);
            if(route0 != null) {
                this.nextRouteToTry = route0;
                return true;
            }
        }
        if(this.routeSelection != null && this.routeSelection.hasNext()) {
            return true;
        }
        return this.routeSelector == null ? true : this.routeSelector.hasNext();
    }

    @Override  // okhttp3.internal.connection.RoutePlanner
    public boolean isCanceled() {
        return this.call.isCanceled();
    }

    @Override  // okhttp3.internal.connection.RoutePlanner
    public Plan plan() throws IOException {
        ReusePlan reusePlan0 = this.planReuseCallConnection();
        if(reusePlan0 != null) {
            return reusePlan0;
        }
        ReusePlan reusePlan1 = RealRoutePlanner.planReusePooledConnection$okhttp$default(this, null, null, 3, null);
        if(reusePlan1 != null) {
            return reusePlan1;
        }
        if(!this.getDeferredPlans().isEmpty()) {
            return (Plan)this.getDeferredPlans().removeFirst();
        }
        ConnectPlan connectPlan0 = this.planConnect();
        ReusePlan reusePlan2 = this.planReusePooledConnection$okhttp(connectPlan0, connectPlan0.getRoutes$okhttp());
        return reusePlan2 != null ? reusePlan2 : connectPlan0;
    }

    private final ConnectPlan planConnect() throws IOException {
        Route route0 = this.nextRouteToTry;
        if(route0 != null) {
            this.nextRouteToTry = null;
            return RealRoutePlanner.planConnectToRoute$okhttp$default(this, route0, null, 2, null);
        }
        Selection routeSelector$Selection0 = this.routeSelection;
        if(routeSelector$Selection0 != null && routeSelector$Selection0.hasNext()) {
            return RealRoutePlanner.planConnectToRoute$okhttp$default(this, routeSelector$Selection0.next(), null, 2, null);
        }
        RouteSelector routeSelector0 = this.routeSelector;
        if(routeSelector0 == null) {
            RouteSelector routeSelector1 = new RouteSelector(this.getAddress(), this.call.getClient().getRouteDatabase$okhttp(), this.call, this.client.fastFallback(), this.call.getEventListener$okhttp());
            this.routeSelector = routeSelector1;
            routeSelector0 = routeSelector1;
        }
        if(!routeSelector0.hasNext()) {
            throw new IOException("exhausted all routes");
        }
        Selection routeSelector$Selection1 = routeSelector0.next();
        this.routeSelection = routeSelector$Selection1;
        if(this.call.isCanceled()) {
            throw new IOException("Canceled");
        }
        return this.planConnectToRoute$okhttp(routeSelector$Selection1.next(), routeSelector$Selection1.getRoutes());
    }

    public final ConnectPlan planConnectToRoute$okhttp(Route route0, List list0) throws IOException {
        Intrinsics.checkNotNullParameter(route0, "route");
        if(route0.address().sslSocketFactory() == null) {
            if(!route0.address().connectionSpecs().contains(ConnectionSpec.CLEARTEXT)) {
                throw new UnknownServiceException("CLEARTEXT communication not enabled for client");
            }
            String s = route0.address().url().host();
            if(!Platform.Companion.get().isCleartextTrafficPermitted(s)) {
                throw new UnknownServiceException("CLEARTEXT communication to " + s + " not permitted by network security policy");
            }
        }
        else if(route0.address().protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE)) {
            throw new UnknownServiceException("H2_PRIOR_KNOWLEDGE cannot be used with HTTPS");
        }
        if(route0.requiresTunnel()) {
            Request request0 = this.createTunnelRequest(route0);
            return new ConnectPlan(this.client, this.call, this, route0, list0, 0, request0, -1, false);
        }
        return new ConnectPlan(this.client, this.call, this, route0, list0, 0, null, -1, false);
    }

    public static ConnectPlan planConnectToRoute$okhttp$default(RealRoutePlanner realRoutePlanner0, Route route0, List list0, int v, Object object0) throws IOException {
        if((v & 2) != 0) {
            list0 = null;
        }
        return realRoutePlanner0.planConnectToRoute$okhttp(route0, list0);
    }

    private final ReusePlan planReuseCallConnection() {
        Socket socket0;
        RealConnection realConnection0 = this.call.getConnection();
        if(realConnection0 == null) {
            return null;
        }
        __monitor_enter(realConnection0);
        boolean z = realConnection0.isHealthy(this.doExtensiveHealthChecks);
        try {
            if(z) {
                socket0 = realConnection0.getNoNewExchanges() || !this.sameHostAndPort(realConnection0.route().address().url()) ? this.call.releaseConnectionNoEvents$okhttp() : null;
            }
            else {
                realConnection0.setNoNewExchanges(true);
                socket0 = this.call.releaseConnectionNoEvents$okhttp();
            }
        }
        finally {
            __monitor_exit(realConnection0);
        }
        if(this.call.getConnection() != null) {
            if(socket0 != null) {
                throw new IllegalStateException("Check failed.");
            }
            return new ReusePlan(realConnection0);
        }
        if(socket0 != null) {
            _UtilJvmKt.closeQuietly(socket0);
        }
        this.call.getEventListener$okhttp().connectionReleased(this.call, realConnection0);
        return null;
    }

    public final ReusePlan planReusePooledConnection$okhttp(ConnectPlan connectPlan0, List list0) {
        boolean z = connectPlan0 != null && connectPlan0.isReady();
        RealConnection realConnection0 = this.client.connectionPool().getDelegate$okhttp().callAcquirePooledConnection(this.doExtensiveHealthChecks, this.getAddress(), this.call, list0, z);
        if(realConnection0 == null) {
            return null;
        }
        if(connectPlan0 != null) {
            this.nextRouteToTry = connectPlan0.getRoute();
            connectPlan0.closeQuietly();
        }
        this.call.getEventListener$okhttp().connectionAcquired(this.call, realConnection0);
        return new ReusePlan(realConnection0);
    }

    public static ReusePlan planReusePooledConnection$okhttp$default(RealRoutePlanner realRoutePlanner0, ConnectPlan connectPlan0, List list0, int v, Object object0) {
        if((v & 1) != 0) {
            connectPlan0 = null;
        }
        if((v & 2) != 0) {
            list0 = null;
        }
        return realRoutePlanner0.planReusePooledConnection$okhttp(connectPlan0, list0);
    }

    private final Route retryRoute(RealConnection realConnection0) {
        synchronized(realConnection0) {
            if(realConnection0.getRouteFailureCount$okhttp() != 0) {
                return null;
            }
            if(!realConnection0.getNoNewExchanges()) {
                return null;
            }
            return !_UtilJvmKt.canReuseConnectionFor(realConnection0.route().address().url(), this.getAddress().url()) ? null : realConnection0.route();
        }
    }

    @Override  // okhttp3.internal.connection.RoutePlanner
    public boolean sameHostAndPort(HttpUrl httpUrl0) {
        Intrinsics.checkNotNullParameter(httpUrl0, "url");
        HttpUrl httpUrl1 = this.getAddress().url();
        return httpUrl0.port() == httpUrl1.port() && Intrinsics.areEqual(httpUrl0.host(), httpUrl1.host());
    }
}

