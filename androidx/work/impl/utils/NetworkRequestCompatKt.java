package androidx.work.impl.utils;

import android.net.NetworkRequest;
import android.os.Build.VERSION;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\u0015\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0015\u0010\u0000\u001A\u00020\u0001*\u00020\u00028G¢\u0006\u0006\u001A\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001A\u00020\u0001*\u00020\u00028G¢\u0006\u0006\u001A\u0004\b\u0006\u0010\u0004¨\u0006\u0007"}, d2 = {"capabilitiesCompat", "", "Landroid/net/NetworkRequest;", "getCapabilitiesCompat", "(Landroid/net/NetworkRequest;)[I", "transportTypesCompat", "getTransportTypesCompat", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class NetworkRequestCompatKt {
    public static final int[] getCapabilitiesCompat(NetworkRequest networkRequest0) {
        Intrinsics.checkNotNullParameter(networkRequest0, "<this>");
        if(Build.VERSION.SDK_INT >= 0x1F) {
            return NetworkRequest31.INSTANCE.capabilities(networkRequest0);
        }
        Collection collection0 = new ArrayList();
        for(int v = 0; v < 29; ++v) {
            int v1 = new int[]{17, 5, 2, 10, 29, 19, 3, 0x20, 7, 4, 12, 23, 0, 33, 20, 11, 13, 18, 21, 15, 35, 34, 8, 1, 25, 14, 16, 6, 9}[v];
            if(NetworkRequest28.INSTANCE.hasCapability$work_runtime_release(networkRequest0, v1)) {
                collection0.add(v1);
            }
        }
        return CollectionsKt.toIntArray(((List)collection0));
    }

    public static final int[] getTransportTypesCompat(NetworkRequest networkRequest0) {
        Intrinsics.checkNotNullParameter(networkRequest0, "<this>");
        if(Build.VERSION.SDK_INT >= 0x1F) {
            return NetworkRequest31.INSTANCE.transportTypes(networkRequest0);
        }
        Collection collection0 = new ArrayList();
        for(int v = 0; v < 9; ++v) {
            int v1 = new int[]{2, 0, 3, 6, 9, 8, 4, 1, 5}[v];
            if(NetworkRequest28.INSTANCE.hasTransport$work_runtime_release(networkRequest0, v1)) {
                collection0.add(v1);
            }
        }
        return CollectionsKt.toIntArray(((List)collection0));
    }
}

