package androidx.work.impl.utils;

import android.net.NetworkRequest.Builder;
import android.net.NetworkRequest;
import androidx.work.Logger;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u0006H\u0007J\u001D\u0010\b\u001A\u00020\t2\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u0006H\u0000¢\u0006\u0002\b\nJ\u001D\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u00042\u0006\u0010\u000E\u001A\u00020\u000FH\u0000¢\u0006\u0002\b\u0010J\u001D\u0010\u0011\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u00042\u0006\u0010\u0012\u001A\u00020\u000FH\u0000¢\u0006\u0002\b\u0013¨\u0006\u0014"}, d2 = {"Landroidx/work/impl/utils/NetworkRequest28;", "", "()V", "createNetworkRequest", "Landroid/net/NetworkRequest;", "capabilities", "", "transports", "createNetworkRequestCompat", "Landroidx/work/impl/utils/NetworkRequestCompat;", "createNetworkRequestCompat$work_runtime_release", "hasCapability", "", "request", "capability", "", "hasCapability$work_runtime_release", "hasTransport", "transport", "hasTransport$work_runtime_release", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class NetworkRequest28 {
    public static final NetworkRequest28 INSTANCE;

    static {
        NetworkRequest28.INSTANCE = new NetworkRequest28();
    }

    @JvmStatic
    public static final NetworkRequest createNetworkRequest(int[] arr_v, int[] arr_v1) {
        Intrinsics.checkNotNullParameter(arr_v, "capabilities");
        Intrinsics.checkNotNullParameter(arr_v1, "transports");
        NetworkRequest.Builder networkRequest$Builder0 = new NetworkRequest.Builder();
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            int v2 = arr_v[v1];
            try {
                networkRequest$Builder0.addCapability(v2);
            }
            catch(IllegalArgumentException illegalArgumentException0) {
                Logger.get().warning("WM-NetworkRequestCompat", "Ignoring adding capability \'" + v2 + '\'', illegalArgumentException0);
            }
        }
        for(int v = 0; v < arr_v1.length; ++v) {
            networkRequest$Builder0.addTransportType(arr_v1[v]);
        }
        NetworkRequest networkRequest0 = networkRequest$Builder0.build();
        Intrinsics.checkNotNullExpressionValue(networkRequest0, "networkRequest.build()");
        return networkRequest0;
    }

    public final NetworkRequestCompat createNetworkRequestCompat$work_runtime_release(int[] arr_v, int[] arr_v1) {
        Intrinsics.checkNotNullParameter(arr_v, "capabilities");
        Intrinsics.checkNotNullParameter(arr_v1, "transports");
        return new NetworkRequestCompat(NetworkRequest28.createNetworkRequest(arr_v, arr_v1));
    }

    public final boolean hasCapability$work_runtime_release(NetworkRequest networkRequest0, int v) {
        Intrinsics.checkNotNullParameter(networkRequest0, "request");
        return NetworkApi23..ExternalSyntheticApiModelOutline0.m$1(networkRequest0, v);
    }

    public final boolean hasTransport$work_runtime_release(NetworkRequest networkRequest0, int v) {
        Intrinsics.checkNotNullParameter(networkRequest0, "request");
        return NetworkApi23..ExternalSyntheticApiModelOutline0.m(networkRequest0, v);
    }
}

