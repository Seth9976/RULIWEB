package androidx.work.impl.utils;

import android.net.NetworkRequest;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000E\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006J\u000E\u0010\u0007\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006¨\u0006\b"}, d2 = {"Landroidx/work/impl/utils/NetworkRequest31;", "", "()V", "capabilities", "", "request", "Landroid/net/NetworkRequest;", "transportTypes", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class NetworkRequest31 {
    public static final NetworkRequest31 INSTANCE;

    static {
        NetworkRequest31.INSTANCE = new NetworkRequest31();
    }

    public final int[] capabilities(NetworkRequest networkRequest0) {
        Intrinsics.checkNotNullParameter(networkRequest0, "request");
        int[] arr_v = NetworkApi23..ExternalSyntheticApiModelOutline0.m$1(networkRequest0);
        Intrinsics.checkNotNullExpressionValue(arr_v, "request.capabilities");
        return arr_v;
    }

    public final int[] transportTypes(NetworkRequest networkRequest0) {
        Intrinsics.checkNotNullParameter(networkRequest0, "request");
        int[] arr_v = NetworkApi23..ExternalSyntheticApiModelOutline0.m(networkRequest0);
        Intrinsics.checkNotNullExpressionValue(arr_v, "request.transportTypes");
        return arr_v;
    }
}

