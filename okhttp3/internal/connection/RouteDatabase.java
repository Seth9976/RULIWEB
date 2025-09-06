package okhttp3.internal.connection;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Route;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000E\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\u0005J\u000E\u0010\r\u001A\u00020\u000B2\u0006\u0010\u000E\u001A\u00020\u0005J\u000E\u0010\u000F\u001A\u00020\u00102\u0006\u0010\f\u001A\u00020\u0005R\u0014\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\u00050\u00078F¢\u0006\u0006\u001A\u0004\b\b\u0010\t¨\u0006\u0011"}, d2 = {"Lokhttp3/internal/connection/RouteDatabase;", "", "()V", "_failedRoutes", "", "Lokhttp3/Route;", "failedRoutes", "", "getFailedRoutes", "()Ljava/util/Set;", "connected", "", "route", "failed", "failedRoute", "shouldPostpone", "", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public final class RouteDatabase {
    private final Set _failedRoutes;

    public RouteDatabase() {
        this._failedRoutes = new LinkedHashSet();
    }

    public final void connected(Route route0) {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(route0, "route");
            this._failedRoutes.remove(route0);
        }
    }

    public final void failed(Route route0) {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(route0, "failedRoute");
            this._failedRoutes.add(route0);
        }
    }

    public final Set getFailedRoutes() {
        synchronized(this) {
            return CollectionsKt.toSet(this._failedRoutes);
        }
    }

    public final boolean shouldPostpone(Route route0) {
        synchronized(this) {
            Intrinsics.checkNotNullParameter(route0, "route");
            return this._failedRoutes.contains(route0);
        }
    }
}

