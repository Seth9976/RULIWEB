package androidx.work.impl.constraints;

import android.net.ConnectivityManager.NetworkCallback;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import androidx.work.Logger;
import androidx.work.impl.utils.NetworkApi23..ExternalSyntheticApiModelOutline0;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J4\u0010\f\u001A\b\u0012\u0004\u0012\u00020\b0\r2\u0006\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u00052\u0016\u0010\u0011\u001A\u0012\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006j\u0002`\tJ\u0018\u0010\u0012\u001A\u00020\b2\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u0016H\u0016J\u0010\u0010\u0017\u001A\u00020\b2\u0006\u0010\u0013\u001A\u00020\u0014H\u0016R,\u0010\u0003\u001A\u001E\u0012\u0004\u0012\u00020\u0005\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006j\u0002`\t0\u00048\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u000BX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Landroidx/work/impl/constraints/SharedNetworkCallback;", "Landroid/net/ConnectivityManager$NetworkCallback;", "()V", "requests", "", "Landroid/net/NetworkRequest;", "Lkotlin/Function1;", "Landroidx/work/impl/constraints/ConstraintsState;", "", "Landroidx/work/impl/constraints/OnConstraintState;", "requestsLock", "", "addCallback", "Lkotlin/Function0;", "connManager", "Landroid/net/ConnectivityManager;", "networkRequest", "onConstraintState", "onCapabilitiesChanged", "network", "Landroid/net/Network;", "networkCapabilities", "Landroid/net/NetworkCapabilities;", "onLost", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class SharedNetworkCallback extends ConnectivityManager.NetworkCallback {
    public static final SharedNetworkCallback INSTANCE;
    private static final Map requests;
    private static final Object requestsLock;

    static {
        SharedNetworkCallback.INSTANCE = new SharedNetworkCallback();
        SharedNetworkCallback.requestsLock = new Object();
        SharedNetworkCallback.requests = new LinkedHashMap();
    }

    public final Function0 addCallback(ConnectivityManager connectivityManager0, NetworkRequest networkRequest0, Function1 function10) {
        Intrinsics.checkNotNullParameter(connectivityManager0, "connManager");
        Intrinsics.checkNotNullParameter(networkRequest0, "networkRequest");
        Intrinsics.checkNotNullParameter(function10, "onConstraintState");
        synchronized(SharedNetworkCallback.requestsLock) {
            SharedNetworkCallback.requests.put(networkRequest0, function10);
            if(SharedNetworkCallback.requests.isEmpty()) {
                Logger.get().debug("WM-WorkConstraintsTrack", "NetworkRequestConstraintController register shared callback");
                NetworkApi23..ExternalSyntheticApiModelOutline0.m(connectivityManager0, this);
            }
        }
        return new Function0(connectivityManager0, this) {
            final ConnectivityManager $connManager;
            final NetworkRequest $networkRequest;

            {
                this.$networkRequest = networkRequest0;
                this.$connManager = connectivityManager0;
                SharedNetworkCallback.this = sharedNetworkCallback0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                this.invoke();
                return Unit.INSTANCE;
            }

            public final void invoke() {
                Object object0 = SharedNetworkCallback.requestsLock;
                ConnectivityManager connectivityManager0 = this.$connManager;
                SharedNetworkCallback sharedNetworkCallback0 = SharedNetworkCallback.this;
                synchronized(object0) {
                    SharedNetworkCallback.requests.remove(this.$networkRequest);
                    if(SharedNetworkCallback.requests.isEmpty()) {
                        Logger.get().debug("WM-WorkConstraintsTrack", "NetworkRequestConstraintController unregister shared callback");
                        connectivityManager0.unregisterNetworkCallback(sharedNetworkCallback0);
                    }
                }
            }
        };
    }

    @Override  // android.net.ConnectivityManager$NetworkCallback
    public void onCapabilitiesChanged(Network network0, NetworkCapabilities networkCapabilities0) {
        Intrinsics.checkNotNullParameter(network0, "network");
        Intrinsics.checkNotNullParameter(networkCapabilities0, "networkCapabilities");
        Logger.get().debug("WM-WorkConstraintsTrack", "NetworkRequestConstraintController onCapabilitiesChanged callback");
        synchronized(SharedNetworkCallback.requestsLock) {
            List list0 = CollectionsKt.toList(SharedNetworkCallback.requests.entrySet());
        }
        for(Object object0: list0) {
            NetworkRequest networkRequest0 = (NetworkRequest)((Map.Entry)object0).getKey();
            Function1 function10 = (Function1)((Map.Entry)object0).getValue();
            ConstraintsState constraintsState0 = NetworkApi23..ExternalSyntheticApiModelOutline0.m(networkRequest0, networkCapabilities0) ? ConstraintsMet.INSTANCE : new ConstraintsNotMet(7);
            function10.invoke(constraintsState0);
        }
    }

    @Override  // android.net.ConnectivityManager$NetworkCallback
    public void onLost(Network network0) {
        Intrinsics.checkNotNullParameter(network0, "network");
        Logger.get().debug("WM-WorkConstraintsTrack", "NetworkRequestConstraintController onLost callback");
        synchronized(SharedNetworkCallback.requestsLock) {
            List list0 = CollectionsKt.toList(SharedNetworkCallback.requests.values());
        }
        for(Object object0: list0) {
            ((Function1)object0).invoke(new ConstraintsNotMet(7));
        }
    }
}

